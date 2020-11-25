package com.resources.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.resources.dao.CustomerDAO;
import com.resources.entity.Vendor;
import com.resources.entity.VendorFile;
import com.resources.entity.VendorModel;
import com.resources.service.VendorService;

@Controller
@RequestMapping("/management")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping(value = "/ajaxdata", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ajaxReq(@RequestBody Map<String, Object> data) {
		String text = (String) data.get("text");
		String name = (String) data.get("name");
		System.out.println(name + ", " + text);
		Map<String, Object> rmap = new HashMap<String, Object>();
		VendorModel vendormodel = new VendorModel();
		vendormodel.setVendorname("Vend-ajax");
		vendormodel.setVendorproduct("vendorproduct");
		rmap.put("vendorname", vendormodel.getVendorname());
		rmap.put("vendorproduct", vendormodel.getVendorproduct());
		rmap.put("success", true);
		return rmap;
	}

	@GetMapping("/showVendor")
	public String showVendorForm(Model theModel, HttpSession session) {
		VendorModel vendormodel = new VendorModel();
		theModel.addAttribute("theVendor", vendormodel);
		int noOfcustomers = customerDAO.getCustomers().size();
		System.out.println("noOfcustomers: " + noOfcustomers);
		theModel.addAttribute("totalcusts", Integer.toString(noOfcustomers));
		List<Vendor> allVendors = vendorService.getVendorDetails();
		theModel.addAttribute("allVendors", allVendors);
		Gson gson = new Gson();
		String json = gson.toJson(allVendors);
		System.out.println(json);
		theModel.addAttribute("dtablejson", json);
		String username = (String) session.getAttribute("username");
		System.out.println("from session: " + username);
		return "vendors";
	}

	@PostMapping("/submitvendor")
	public String submitVendor(@ModelAttribute("theVendor") VendorModel vendormodel, Model model,
			@RequestParam("file") MultipartFile[] files) {
		System.out.println(vendormodel.getVendorname() + " " + vendormodel.getVendorproduct());
		Vendor vendor = new Vendor(vendormodel.getVendorname(), vendormodel.getVendorproduct());

		for (MultipartFile multipartFile : files) {
			String filename = multipartFile.getOriginalFilename();
			vendor.addVendorFile(new VendorFile(filename));
			try {
				byte[] bytes = multipartFile.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("E:\\" + filename)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		vendorService.saveVendor(vendor);

		List<Vendor> allVendors = vendorService.getVendorDetails();
		model.addAttribute("allVendors", allVendors);
		Gson gson = new Gson();
		String json = gson.toJson(allVendors);
		System.out.println(json);
		model.addAttribute("dtablejson", json);
		return "vendors";
	}

}
