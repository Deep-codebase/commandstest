function success(){
	alert('success');
}
function error(){
	alert('error');
}
function sendAjax(urljson) {
	var text = $('#vendorname').val();
	var name = $('#vendorproduct').val();
	$.ajax({
	    "type" : 'POST',
	    "url" :urljson,       
	    "data" : JSON.stringify({"text" : text, "name" : name}),               
	    "success" : success,
	    "error" : error,
	    contentType: "application/json",
	    dataType: "json",
	   });
}