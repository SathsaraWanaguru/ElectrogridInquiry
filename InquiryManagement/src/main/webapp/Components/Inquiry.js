
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateInquiryForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hididinquirySave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "InquiryAPI", 
 type : type, 
 data : $("#formInq").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onInquirySaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hididinquirySave").val($(this).data("idinquiry")); 
 $("#inquiryNumber").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#inquiryName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#inquiryArea").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#inquiryPnumber").val($(this).closest("tr").find('td:eq(3)').text());
 $("#inquiryeMail").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "InquiryAPI", 
 type : "DELETE", 
 data : "idinquiry=" + $(this).data("inquiryid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onInquiryDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateInquiryForm() 
{ 
// Number
if ($("#inquiryNumber").val().trim() == "") 
 { 
 return "Insert Inquiry Number."; 
 } 
 // is numerical value
var inqNumber = $("#inquiryNumber").val().trim(); 
if (!$.isNumeric(inqNumber)) 
 { 
 return "Insert a numerical value for inquiry number."; 
 } 
// convert to decimal price
 $("#inquiryNumber").val(parseFloat(inqNumber).toFixed(2)); 
// DESCRIPTION------------------------

// NAME
if ($("#inquiryName").val().trim() == "") 
 { 
 return "Insert Inquiry Name."; 
 } 
// Area-------------------------------
if ($("#inquiryArea").val().trim() == "") 
 { 
 return "Insert Area."; 
 } 
if ($("#inquiryPnumber").val().trim() == "") 
 { 
 return "Insert inquiry Phone number."; 
 }
 if ($("#inquiryMail").val().trim() == "") 
 { 
 return "Insert inquiry mail."; 
 } 
return true; 
}

function onInquirySaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divInquiryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidInquiryIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onInquiryDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divInquiryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




