//
// This function is called from "lib/saveSvgAsPng.js"
// 
function sendImage (png) {
	  var edit_Title = document.getElementById("Edit_Title").value;  
      var edit_Authors = document.getElementById("Edit_Authors").value; 
      var edit_Links = document.getElementById("Edit_Links").value; 
      var edit_Keywords = document.getElementById("Edit_Keywords").value; 
      var edit_Description = document.getElementById("Edit_Description").value; 
      var edit_Reference = document.getElementById("Edit_Reference").value;  
      var editor = ace.edit("editor"); 
	  var curBn = editor.getValue();  
      var edit_Script = encodeURIComponent(curBn);
        
      var x = "{" + "\"title\" :" 	 +  "\"" + edit_Title 	+ "\""+
			      ", \"description\" :"  +  "\"" + edit_Description + "\""+
			      ", \"reference\" :"  +  "\"" + edit_Reference + "\""+
      			  ", \"authors\" :"  +  "\"" + edit_Authors + "\""+
      			  ", \"script\" :"  +  "\"" + edit_Script + "\""+
      			  ", \"links\" :" 	 +  "\"" + edit_Links 	+ "\""+
      			  ", \"keywords\" :" +  "\"" + edit_Keywords+ "\""+
      			  ", \"image\" :" 	 +  "\"" + png	+ "\""+"}"; 
      
      var xmlhttp = requestBC("register", "html", x); 
       
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
    		  
    		  document.getElementById("register_msg").innerHTML = reJson.data;
    		  
    	  } else if (this.readyState == 4 && this.status == 200) {
              alert(this.responseText);
          }
      };  
}

function resetRegisterModel() {
//	  document.getElementById("Edit_Title").value = "";
//      document.getElementById("Edit_Authors").value = "";
//      document.getElementById("Edit_Links").value = "";
//      document.getElementById("Edit_Keywords").value = "";
//      document.getElementById("Edit_Description").value = "";
//      document.getElementById("Edit_Reference").value  = "";
      document.getElementById("register_msg").innerHTML = "";
}

//search_start
$(function () {
    $('#register_start').submit(function (e) {
        e.preventDefault()  // prevent the form from 'submitting'
        console.log("--- register_start --- ");
        
        // var png = getSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, width:20, height:20, backgroundColor: "#FFFFFF"});
        var png = getSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
        
        //console.log(png);

    })
})
 