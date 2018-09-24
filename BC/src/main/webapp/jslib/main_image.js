//$(function () {
//    $('#saveImage').submit(function (e) {
//        e.preventDefault()  // prevent the form from 'submitting' 
//        saveSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
//    	 
//    })
//})
//  
//
//$("saveImage").click(function(){
//	   saveSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
//  	 
//});

//$(document).ready(function(){
//    $("saveImage").click(function(){
//    	  saveSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
//    	  	 
//    });
//});

//$(document).ready(function(){
//    $("button").click(function(){ 
//    	 saveSvgAsPng(document.getElementsByTagName("svg")[0], "plot.png", {scale: 1, backgroundColor: "#FFFFFF"});
//    });
//});

function saveImage () {
	 saveSvgAsPng(document.getElementsByTagName("svg")[0], "image.png", {scale: 1, backgroundColor: "#FFFFFF"});
}