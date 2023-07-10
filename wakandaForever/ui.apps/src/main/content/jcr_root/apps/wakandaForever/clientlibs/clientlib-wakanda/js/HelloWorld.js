$(document).ready(function(){
  $(".heading").on("mouseover", function(){
    $(this).css("color","red");
  });
  $(".heading").on("mouseleave",function(){
  	$(this).css("color","white");
    });
//   var currentPageUrl = window.location.pathName;
//   var path = currentPageUrl.slice(0,currentPageUrl.indexOf("."));
//          console.log("Current Path : :",path,currentPageUrl);

   $('.heading').on("click",function() {
       const somePath = $("#dataSomething").attr("data-resource-path");
       console.log("SOME PATH :::: ",somePath);
           // Perform an AJAX request
           $.ajax({
             url: `${somePath}.json`, // Replace with your endpoint URL
             type: 'GET', // Or 'GET', depending on your endpoint
             dataType: 'json', // If the endpoint returns JSON
             success: function(response) {
               // Handle the response from the server
               console.log(response);
             },
             error: function(xhr, status, error) {
               // Handle errors
               console.log(error);
             }
           });
         });
});
