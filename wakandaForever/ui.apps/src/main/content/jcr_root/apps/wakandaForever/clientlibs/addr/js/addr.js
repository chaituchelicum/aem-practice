$(document).ready(function(){
  $("#submitButton").on("click",function(){
     var inputAddress = $("#addLookup").val();
     if(inputAddress.length>0){
        var yourAddress = $("#divPath").attr("data-resource-path");
                   // Perform an AJAX request
                   $.ajax({
                     url: `${yourAddress}.json`, // Replace with your endpoint URL
                     type: 'GET', // Or 'GET', depending on your endpoint
                     data: {
                     postCode: inputAddress
                     },
                     dataType: 'json', // If the endpoint returns JSON
                     success: function(response) {

                         if(response.code === 2000) {
                             var selectElement = $('<select>', {
                                 id: "addressSelect"
                             });

                             // Step 3: Iterate through the JSON array
                             $.each(response.result, function (index, item) {
                                 // Step 4: Build the option elements
                                 var optionElement = $('<option>', {
                                     id: item.premise,
                                     value: item.line_1 + "," + item.post_town,
                                     text: item.line_1 + "," + item.post_town
                                 });

                                 // Step 5: Append the option elements to the select element
                                 selectElement.append(optionElement);
                             });

                             // Append the select element to the container
                             $('#my-div').append(selectElement);
                             console.log(response);
                         }else{
                             alert(response.message);
                         }
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
    $("#addLookup").on("input",function (){
        var element = $("#my-div select")
        if (element.length > 0){
            element.remove();
        }
    });
})
