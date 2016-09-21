
$(function(){
	 $(".clickmaterailtypecourseid").each(function() {
			$(this).click(function(){
				var courseid =$(this).attr('id');
				window.location.href="/course/familycourselist?courseid="+courseid
			})
		})
	
});