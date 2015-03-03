//Author Sam Vitello
//Uses AJAX to load catalog 

var cur_type = 'products';
var page = 0;
var sort = -1;
function disp_catalog(){
		var xmlhttp;
		if (window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest();
		}else{
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				xmlDoc=xmlhttp.responseText;
				document.getElementById("prod").innerHTML = "";
				document.getElementById("prod").innerHTML += xmlDoc;
				document.getElementById("prod").innerHTML += "<button align='center' id='add-to-cart' onclick='addToCart()'>Add To Cart</button>";
				
			}
		}
		
		xmlhttp.open("GET","catalog/php/handleCatDisplay.php? disp= "+ page +"&cur_disp=" + cur_type +"&sort_by="+sort, true);
		xmlhttp.send();
}

function disp_next(){
	page += 6;
	disp_catalog();
}
function disp_prev(){
	if (page > 0)
		page -= 6;
	disp_catalog();
}
function changeCategory(cat){
	cur_type = cat;
	page = 0;
	sort = -1;
	disp_catalog();
}