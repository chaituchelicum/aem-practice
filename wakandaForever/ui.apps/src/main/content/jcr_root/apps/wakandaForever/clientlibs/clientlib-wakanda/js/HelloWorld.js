$(document).ready(function(){
  $(".heading").on("mouseover", function(){
    $(this).css("color","red");
  });
  $(".heading").on("mouseleave",function(){
  	$(this).css("color","white");
    });
});