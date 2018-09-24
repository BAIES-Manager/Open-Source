/**
 * jquery JavsScript Library - http://jquery.com/downlaod/
 */

// Input related code goes here 
console.log("---- start ---- ");

function drawBN(myGraph) {
	console.log("--- draw ---" + myGraph);

	// Create and configure the renderer
	var render = new dagreD3.render();

	// Set up zoom support
	var svg = d3.select("svg");
	var inner = svg.select("g");
	// Remove previous all g elements.
	svg.selectAll("*").remove();
	inner = svg.append("g");

	// Setting Zoom 
	var zoom = d3.zoom().on("zoom", function() {
		inner.attr("transform", d3.event.transform);
	});
	svg.call(zoom);

	// Returning original position and scale. 
	svg.transition().duration(750).call(zoom.transform, d3.zoomIdentity);

	// Load dgraph from a graph dot script
	var dgraph;
	try {
		dgraph = graphlibDot.read(myGraph);
	} catch (e) {
		//throw e;
		return;
	}

	// Set margins, if not present
	if (!dgraph.graph().hasOwnProperty("marginx")
			&& !dgraph.graph().hasOwnProperty("marginy")) {
		dgraph.graph().marginx = 20;
		dgraph.graph().marginy = 20;
	}

	// Set transition 
	dgraph.graph().transition = function(selection) {
		return selection.transition().duration(500);
	};

	// Run the renderer. This is what draws the final graph.
	render(inner, dgraph);
	// d3.select("svg g").call(render, dgraph); // other approach.   
}


function requestBC(type, mode, data) {
	console.log("--- url --- " + window.location.href);   
	
	var jsonReq = createJsonRequest(type, mode, data); 
	var URLBC = window.location.href + "webapi/RequestBN/run";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST", URLBC, true);
	xmlhttp.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	xmlhttp.send(jsonReq);

	return xmlhttp;
}

// updateBN
$(function() {
	$('#report').submit(function(e) {
		e.preventDefault() // prevent the form from 'submitting'

		var url = e.target.action; // get the target
		var data = $(this).serialize(); // get form data 
		var convertedData = decodeURIComponent(data);
		var x = convertedData.replace(/\+/g, ' ');
		console.log("--- updateBN --- " + url);  
		
		// A = a1;B= b1; to DefineState(A, a1); DefineState(B, b1);
		var evidence = ""; 
		x = x.substr(3, x.length - 1);
		var fields = x.split(';'); 
		for (var i in fields) {
			var el = fields[i];
			if (el) {
				var ev = el.split('=');
				var rv = ev[0];
				var value = ev[1];
				evidence += "defineEvidence( " + rv + "," + value + "); ";
			}
		}

		var editor = ace.edit("editor"); 
		var sbn = encodeURIComponent(editor.getValue()+ evidence);
		var x = "{" + "\"script\" :"  +  "\"" + sbn + "\""+"}";  
		var xmlhttp = requestBC("draw", "html", x);
				 
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.responseText != "") {
				var htmlBN = this.responseText;
				console.log("--- draw BN ---");
				drawBN(htmlBN);

			} else if (this.readyState == 4 && this.status == 200) {
				alert(this.responseText);
			}
		};		
	})
})


function createBN() {
	var editor = ace.edit("editor"); 
	var sbn = encodeURIComponent(editor.getValue());  
	var x = "{" + "\"script\" :"  +  "\"" + sbn + "\""+"}";  
	var xmlhttp = requestBC("draw", "html", x);

	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.responseText != "") {
			var htmlBN = this.responseText;
			console.log("--- draw BN ---");
			drawBN(htmlBN);

		} else if (this.readyState == 4 && this.status == 200) {
			alert(this.responseText);
		}
	}; 
}

function createBNonViewer() {
	var myParam = location.search.split('myParam=')[1]
	console.log(myParam);

	console.log("--- create BN ---");
	$.ajax({
		//async: false,
		url : 'CreateBN',
		type : 'POST',
		dataType : 'text',
		data : {
			sbn_script : myParam
		},
		success : function(newBN) {
			console.log("--- init BN in createBN ---");
			drawBN(newBN);
		}
	});
}

function saveImage() {
	//the canvg call that takes the svg xml and converts it to a canvas
	canvg('canvas', $("svg").html());

	// the canvas calls to output a png
	var canvas = document.getElementById("canvas");
	var img = canvas.toDataURL("data/img/png");
}
