
function requestBN() {  
//	var myParam = location.search.split('myParam=')[1]
	var params = location.search;
  	console.log(params);
	
	params = 	"{\"" + 
			    params
			    .replace( /\?/gi, "" )
			    .replace( /\&/gi, "\",\"" )
			    .replace( /\=/gi, "\":\"" ) +
			    "\"}";

	params = JSON.parse( params );
	
    console.log("--- Reqeust ---");
	$.ajax({
		//async: false,
		url: 'ReqeustBN',
		type: 'POST',
		//dataType: 'text',
		dataType: 'json',
		data: { parameters : params }, 
		success: function(newBN){
			console.log("--- init BN in createBN ---");
			//initBN(newBN); 
		}
	});
}  