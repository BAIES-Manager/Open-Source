   
function startMachineLearning () {
	  var editor = ace.edit("editor"); 
      var curBn = editor.getValue(); 
    
	  var uploaded_file_name = document.getElementById("uploaded_file_name").innerHTML;  
      var edit_Script = encodeURIComponent(curBn);
        
      var x = "{" + "\"script\" :"  +  "\"" + edit_Script + "\""+
      			  	", \"uploaded_file_name\" :" 	 +  "\"" + uploaded_file_name 	+ "\""+ 
      			  	"}"; 
       
      console.log(x);
       
       $.ajax({
		//async: false,
		url: 'MachineLearningBN',
		type: 'POST',
//		processData: false,
		dataType: 'text',	
		data: { BNinfoAndData : x }, 
		success: function(learnedSBN){
			console.log(learnedSBN);   
			var editor = ace.edit("editor");  
		    editor.setValue(learnedSBN); 
		     
		}
	});  
       
}

//function resetRegisterModel() { 
//      document.getElementById("register_msg").innerHTML = "";
//}

//search_start
$(function () {
    $('#machine_learning_start').submit(function (e) {
        e.preventDefault()  // prevent the form from 'submitting'
        console.log("--- start Machine Learning --- ");
        
        startMachineLearning ();
    })
})
 