  
//search_start
$(function () {
    $('#search_start').submit(function (e) {
        e.preventDefault()  // prevent the form from 'submitting'
        console.log("--- search_start --- ");
        
        var url = e.target.action;  // get the target
        var data = $(this).serialize(); // get form data 
        var convertedData = decodeURIComponent(data);
        var params = convertedData.replace(/\+/g, ' '); 
        
        params = str2json(params)
        
        console.log("--- key words --- " + params);
         
        reqeustSearch(params)
    })
})
 

function reqeustSearch(jsonData) {  
    var xmlhttp = requestBC("search", "html", jsonData); 
    
    xmlhttp.onreadystatechange = function() {
  	  if (this.readyState == 4 && this.responseText != ""){
  		  var reJson = JSON.parse(this.responseText); 
  		  var reMode = reJson.mode;
  		  var reType = reJson.type;
  		  var reData = reJson.data;
  		  console.log(reJson);
		  console.log(reMode);
		  console.log(reType);
		  console.log(reData); 
		
  		  removeElements(document.getElementById("SearchResults"));
 				 
		  for (var i = 0; i < reData.length; i++) {
		    var bn = reData[i];
   			console.log("--- JSON ---" + bn.name); 
   			searchResultDone(bn); 
		  } 
  		  
  	  } else if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
        }
    };
     
//    
//    
//	
//    $.ajax({
//		url:'RequestBN', type:'POST', dataType:'json', data:{ Parameters : json }, 
//		success: function(jsonBNs){ 
//			var reJson = JSON.parse(JSON.stringify(jsonBNs));
//			var reMode = reJson.mode;
//			var reType = reJson.type;
//			var reData = reJson.data;
//			
//			removeElements(document.getElementById("SearchResults"));
////						 
////			for (var i = 0; i < reData.length; i++) {
////			    var bn = reData[i];
////	   			console.log("--- JSON ---" + bn.name); 
////	   			searchResultDone(bn); 
////			} 
//			
//			return Json(reData, JsonRequestBehavior.AllowGet);
//		}
//	}); 
}

function removeElements(elements) { 
	while (elements.firstChild) {
		elements.removeChild(elements.firstChild);
	}
}

function searchResultDone(bn) {
    console.log("--- create items in the list of results ---");
 
    
    
//	// Adding a paragraph to it
//	var paragraph = document.createElement("P");
//	var text = document.createTextNode("Another paragraph, yay! This one will be styled different from the rest since we styled the DIV we specifically created.");
//	paragraph.appendChild(text);
//	divElement.appendChild(paragraph);
// 
    
	// Creating a div element    
    //////////////////////////////////////////////////////////////////////////////////// 
	var divElement = document.createElement("Div");
	divElement.setAttribute('class', "col-md-4 col-sm-6"); 
	
	divElement.addEventListener("click", function(){  
	     
		var editor = ace.edit("editor"); 
	    var curBn = bn.script + editor.getValue(); 
	    editor.setValue(curBn); 
	     
	    document.getElementById("main").scrollIntoView();
	    
	});
	
	// Buttons //////////////////////////////////////////////////////////////////////////////////
//	var buttonEdit = document.createElement("Button");
//	var textForbuttonEdit = document.createTextNode("Edit"); 
//	buttonEdit.appendChild(textForbuttonEdit);
//  
//	buttonEdit.addEventListener("click", function(){
//	    alert("Hi!");
//	});
//	
//	divElement.appendChild(buttonEdit);
	      
	////////////////////////////////////////////////////////////////////////////////////
	var panel_default = document.createElement("Div"); 
	panel_default.setAttribute('class', "panel panel-default");  
	divElement.appendChild(panel_default);
	
	// Title ////////////////////////////////////////////////////////////////////////////////// 
	var panel_heading = document.createElement("Div"); 
	panel_heading.setAttribute('class', "panel-heading"); 
	
//	panel_heading.innerHTML= "<a href='#B' class='pull-right'>Edit</a> <a href='#A' class='pull-right'>Add/</a> " + "<h4>" + bn.title + "</h4>";
	panel_heading.innerHTML= "<h4>" + bn.title + "</h4>";
	 
	panel_default.appendChild(panel_heading);
	

	////////////////////////////////////////////////////////////////////////////////////
	var panel_thumbnail = document.createElement("Div"); 
	panel_thumbnail.setAttribute('class', "panel-thumbnail");  
	
	panel_default.appendChild(panel_thumbnail);
	 
	////////////////////////////////////////////////////////////////////////////////////
	var center = document.createElement("Center"); 
	
	panel_thumbnail.appendChild(center);
	
	// image //////////////////////////////////////////////////////////////////////////////////
	var img = document.createElement("Img");
	img.setAttribute('class', "img-responsiv"); 
	img.setAttribute('src', bn.image);
	img.setAttribute('align', "middle"); 
	img.setAttribute('height', "100%"); 
	img.setAttribute('width', "100%");
	img.setAttribute('object-fit', "contain");
	 
	center.appendChild(img);
	   
	// description //////////////////////////////////////////////////////////////////////////////////
	var panel_body = document.createElement("Div"); 
	panel_body.setAttribute('class', "panel-body");
	
	var str = bn.description;
	if(str.length > 60) str = str.substring(0, 60) + "...";
	else str = "&nbsp;";
	
	panel_body.innerHTML = "<hr>" + str;
	 
	panel_default.appendChild(panel_body);
	 
	////////////////////////////////////////////////////////////////////////////////////
	// Final update 
	document.getElementById("SearchResults").appendChild(divElement);
	
	console.log(divElement.innerHTML);
	
	document.getElementById("SearchMain").scrollIntoView();
}  
 

/*
 *  <div class="col-md-4 col-sm-6">
	 <div class="panel panel-default">
	   <div class="panel-heading">
	   		<a href="#B" class="pull-right">Edit</a>  
	   		<a href="#A" class="pull-right">Add/</a>
	   	<h4>Weather Model</h4></div> 
	   <div class="panel-thumbnail"><center><img src="data/img1.PNG" class="img-responsive" align="middle"></center></div> 
		<div class="panel-body"> 
		  <hr>
		  This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
		</div>
	 </div> 
	</div> 
*/
