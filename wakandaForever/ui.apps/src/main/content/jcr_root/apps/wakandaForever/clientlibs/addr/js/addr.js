$(document).ready(function(){
  $("#submitButton").on("click",function(){
     var inputAddress = $("#addLookup").val();
     if(inputAddress.length>0){
        var yourAddress = $("#divPath").attr("data-resource-path");
                   // Perform an AJAX request
         var div = $("#my-div");
                   $.ajax({
                     url: `${yourAddress}.json`, // Replace with your endpoint URL
                     type: 'GET', // Or 'GET', depending on your endpoint
                     data: {
                     postCode: inputAddress
                     },
                     dataType: 'json', // If the endpoint returns JSON
                     success: function(response) {
                         $("#my-div").append("<p>HIII RAJA</p>");
                       $("#my-div").append("<p>"+JSON.stringify(response)+"</p>");

                       //div.append(response);
                         // div.html(response);

                       // Handle the response from the server
                       console.log(response);
                     },
                     error: function(xhr, status, error) {
                       // Handle errors
                       console.log(error);
                     }
                   });
     }else{
        alert("Please enter any address");
     }
  })

})