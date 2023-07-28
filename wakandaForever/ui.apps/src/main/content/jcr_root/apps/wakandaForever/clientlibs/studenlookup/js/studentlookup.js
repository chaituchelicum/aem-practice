$(document).ready(function(){
  $("#search-btn").on("click",function(){
     var searchText = $("#search-box").val();
        var searchUrl = $("#parent-container").attr("data-resource-path");
                   // Perform an AJAX request
                   $.ajax({
                     url: `${searchUrl}.json`, // Replace with your endpoint URL
                     type: 'GET', // Or 'GET', depending on your endpoint
                     data: {
                     filter: searchText
                     },
                     dataType: 'json', // If the endpoint returns JSON
                     success: function(response) {
                         if(response.length > 0) {
                             var table = $('<table></table>').addClass("students-table");
                             var headerRow = $('<tr></tr>');
                             headerRow.append('<th>Student Name</th>');
                             headerRow.append('<th>Roll Number</th>');
                             headerRow.append('<th>Class</th>');
                             table.append(headerRow);

                             // Loop through the JSON data to populate the table
                             response.forEach(function(item) {
                                 var row = $('<tr></tr>');
                                 row.append('<td>' + item.studentName + '</td>');
                                 row.append('<td>' + item.rollNumber + '</td>');
                                 row.append('<td>' + item['class'] + '</td>');
                                 table.append(row);
                             });

                             // Append the table to the div with the ID "tableContainer"
                             $('#students-container').append(table);
                         }else{
                             alert("NO STUDENTS");
                         }
                     },
                     error: function(xhr, status, error) {
                       // Handle errors
                       console.log(error);
                     }
                   });
  })
    $("#search-box").on("input",function (){
        var element = $(".students-table")
        if (element.length > 0){
            element.remove();
        }
    });
})
