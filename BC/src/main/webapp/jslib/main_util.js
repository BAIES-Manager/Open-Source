   
//
// str = "type = 12 & data = abc"
// to json
//
function str2json(params) {  
	params = 	"{\"" + 
				params
			    .replace( /\?/gi, "" )
			    .replace( /\&/gi, "\",\"" )
			    .replace( /\=/gi, "\":\"" ) +
			    "\"}";
	return params
}
  
function createJsonRequest(mode, type, jsonData) {
	var json = "";
	json = 	"{" + 
	"\"mode\":" + "\"" +  mode + "\"," +
	"\"type\":" + "\"" +  type + "\"," +
	"\"data\":" + jsonData + 
	"}";
	
	return json
}

function IsJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}