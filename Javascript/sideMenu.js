//Author Sam Vitello
//Uses JQUERY to change side menu 

$(document).ready(function(){
	var cur_selected = "none";
	var sel_sub;

	$("#utensils-option").hide();
	$("#cups-option").hide();
	
	$("#utensils").click(function(){
		if (cur_selected != "utensils"){
			changeCategory("utensils");
			$("#utensils").toggleClass("catList","catList-active");
			$("#utensils-option").slideDown();
			if (cur_selected != "none" ){
				$("#".concat(cur_selected)).toggleClass("catList","catList-active");
				$("#".concat(sel_sub)).slideUp();
			}
			
			cur_selected = "utensils";
			sel_sub = "utensils-option";
		}
	});
	
	$("#cups").click(function(){
		if (cur_selected != "cups"){
			$("#cups").toggleClass("catList","catList-active");
			$("#cups-option").slideDown();
			if (cur_selected != "none"){
				$("#".concat(cur_selected)).toggleClass("catList","catList-active");
				$("#".concat(sel_sub)).slideUp();
			}
			cur_selected = "cups";
			sel_sub = "cups-option";
		}
	});
	
});