    
// Dropzone configure 

$(function(){
	Dropzone.options.myAwesomeDropzone = {
    maxFilesize: 1,
    addRemoveLinks: true,
    createImageThumbnails: false,
    dictResponseError: 'Server not Configured',
    acceptedFiles: ".csv",
    
    init:function(){
      var self = this;
      
      // config
      self.options.addRemoveLinks = true;
      self.options.dictRemoveFile = "Delete";
      
      //New file added
      self.on("addedfile", function (file) {
        console.log('new file added ', file);
      });
      
      // Send file starts
      self.on("sending", function (file) {
        console.log('upload started', file);
        $('.meter').show();
      });
      
      // File upload Progress
      self.on("totaluploadprogress", function (progress) {
        console.log("progress ", progress);
        $('.roller').width(progress + '%');
      });
       
      self.on("success", function (File) {
    	  console.log('success');
      });
      
      self.on("complete", function (File) { 
        console.log('complete'); 
        document.getElementById("uploaded_file_name").innerHTML = File.name;
        
      });
      
      // On removing file
      self.on("removedfile", function (file) {
        console.log(file);
      });
    }
  };
})
//
//function dataUpload() { 
//	document.getElementById("register_msg").innerHTML = "";
//}
// 

//
////search_start
//$(function () {
//  $('#dataUpload').submit(function (e) {
//      e.preventDefault()  // prevent the form from 'submitting'
//      console.log("--- register_start --- ");
//     
//        
//      var png = getSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
//  
//        
//  })
//})