//
//   +--------------------------------------------------------+    nodeChart
//   |                         title                          | 
//   +--------------------------------------------------------+
//   |  barLabel1     		 |=========== prob1               |
//   |  barLabel2            |====================== prob2    |
//   |                       |                                |
//   |                       |                                |
//   +--------------------------------------------------------+
//
//

var nodeChartW = 200;
var nodeChartX = -nodeChartW/2;
var bartW = nodeChartW/2;
var nodeChartTop = 30;
var barH = 30;

function updateNodeChart(node, force)
{ 	  
	var textY = 15;
	var textX = 15;
	
	var color = d3.scale.category20();
	var radius = d3.scale.sqrt().range([0, 6]); 

	function getY(d, i)	{
		return - (nodeChartTop + barH * node.data()[i].probs.length)/2;
	}
	
	function getH(d, i)	{
		return nodeChartTop +  barH * node.data()[i].probs.length;
	}
	
	//create a node frame
	var nodeFrame = node.selectAll(".nodeFrame");
	nodeFrame = nodeFrame.data(function (d) {return d.symbol;});
	nodeFrame
	    .attr("x", nodeChartX)
		.attr("y", getY)
		.attr("width", nodeChartW)
		.attr("height", getH)
	.enter()
		.append("rect") 
			.attr("class", "nodeFrame")
			.style("fill", "white")
			.style("stroke", "black")
			.style("stroke-width","1")
			.attr("x", nodeChartX)
			.attr("y", getY)
			.attr("width", nodeChartW) 
			.attr("height", getH);
	nodeFrame.exit().remove();
 
	//create top Box for the frame
	var topBox = node.selectAll(".topBox");
	topBox = topBox.data(function (d) {return d.symbol;});
	topBox.attr("x", nodeChartX)
		.attr("y", getY) 
	.enter()
		.append("rect") 
			.attr("class", "topBox")
			.style("fill", "white")
			.style("stroke", "black")
			.style("stroke-width","1")
			.attr("x", nodeChartX)
			.attr("y", getY)
			.attr("width", nodeChartW)
			.attr("height", nodeChartTop);  
	topBox.exit().remove();
	 
	//add text
	var title = node.selectAll(".title");
	title = title.data(function (d) {return d.symbol;});
	title
		.attr("x", 0)
		.attr("y",  function (d, i) { return  getY(d, i) + nodeChartTop/2;})
		.attr("dx", ".35em") 
		.attr("dy", ".35em")
		.style("text-anchor", "middle")
		.text(function(d) { return d; })
	.enter()
		.append("text")
			.attr("class", "title")
			.attr("x", 0)
			.attr("y",  function (d, i) { return  getY(d, i) + nodeChartTop/2;})
			.attr("dx", ".35em")
			.attr("dy", ".35em")
			.style("text-anchor", "middle")
			.text(function(d) {return d; });
	title.exit().remove();
	
 	//States Box
	var stateBoxs = node.selectAll(".stateBoxs");
	stateBoxs = stateBoxs.data(function (d, i) { return node.data()[i].states;}) 
	stateBoxs.style("fill", "white")
	 		 .style("stroke", "black")
		 	 .style("opacity","0.5")
		 	 .attr("width", bartW)
			 .attr("height", barH)
			 .attr("x", nodeChartX) 
			 .attr("y", function (d, i) { return nodeChartTop + getY(d, i) + i*barH;})
	.enter()
		.append("rect") 
			 .attr("class", function(d) { return "stateBoxs"; })
	   		 .style("fill", "white")
	 		 .style("stroke", "black")
	 		 .style("opacity","0.5")
 			 .attr("width", bartW)
 			 .attr("height", barH)
 			 .attr("x", nodeChartX)
 			 .attr("y", function (d, i) { return nodeChartTop + getY(d, i) + i*barH;})
 			 .on('click', function(d,i) {
	 				//debugger;
	 				d3.select(this.parentNode).selectAll(".stateBoxs").style("fill", "white");
	 				var nodeData = d3.select(this.parentNode).datum();
	 				nodeData.ev = d;
	 				d3.select(this)
					.style("fill", "gray");
	 			});
	stateBoxs.exit().remove();
	 
	
 	//States or Bar Labels  
	var states = node.selectAll(".states");
	states = states.data(function (d, i) { return node.data()[i].states;}) 
	states.attr("x", nodeChartX)
		  .attr("y", function (d, i) { return nodeChartTop + textY + getY(d, i) + i*barH;}) 
		  .attr("dx", ".35em")
		  .attr("dy", ".35em")
		  .text(function(d) { return d; })	
	.enter()
	    .append("text")
		  .attr("class", function(d) { return "states"; })
		  .style("fill", "black")
	      .attr("x", nodeChartX)
		  .attr("y", function (d, i) { return nodeChartTop + textY + getY(d, i) + i*barH;}) 
		  .attr("dx", ".35em")
		  .attr("dy", ".35em")
		  .text(function(d) { return d; });
	states.exit().remove();
	 
    
	//create gradient
	var svg = d3.select("svg"); 
	var minY = 0;
	var maxY = bartW;
	var gradient = svg
	    .append("linearGradient")
	    .attr("x1", minY)
	    .attr("x2", maxY)
	    .attr("y1", "0")
	    .attr("y2", "0")
	    .attr("id", "gradient")
	    .attr("gradientUnits", "userSpaceOnUse")
	    
	gradient
	    .append("stop")
	    .attr("offset", "0")
	    .attr("stop-color", "#FFFFFF")
	    
	gradient
	    .append("stop")
	    .attr("offset", "0.99")
	    .attr("stop-color", "#007A00") 
	    
	
	//Probability Bar 
	var probBars = node.selectAll(".probBars");
	probBars = probBars.data(function (d, i) { return node.data()[i].probs;}) 
	probBars.attr("width", function (d) { return bartW*d;})
			.attr("height", barH)
			.attr("x", 0)
			.attr("y", function (d, i) { return nodeChartTop + getY(d, i) + i*barH;})
	.enter()
	    .append("rect") 
			.attr("class", function(d) { return "probBars"; })
			 .style("fill", "url(#gradient)")
			.style("stroke", "black")
	 	//	.style("opacity","0.5")
 			.attr("width", function (d) { return bartW*d;})
 			.attr("height", barH)
 			.attr("x", 0)
 			.attr("y", function (d, i) { return nodeChartTop + getY(d, i) + i*barH;});
 	probBars.exit().remove();
	
	//Probability Text
	var probLabels = node.selectAll(".probLabels");
	probLabels = probLabels.data(function (d, i) { return node.data()[i].probs;}) 
	probLabels.attr("x", function(d) { return textX; })
			  .attr("y", function (d, i) { return nodeChartTop + textY + getY(d, i) + i*barH;}) 
			  .attr("dx", ".35em")
			  .attr("dy", ".35em")
			  //.style("text-anchor", "middle")
			  .text(function(d) { return  d3.format(".4f")(d); })
	.enter()
	    .append("text")
			  .attr("class", function(d) { return "probLabels"; })
			  .style("fill", "black")
			  .attr("x", function(d) { return textX;}) 
			  .attr("y", function (d, i) { return nodeChartTop + textY + getY(d, i) + i*barH;}) 
		      .attr("dx", ".35em")
		      .attr("dy", ".35em")
			  //.style("text-anchor", "middle")
			  .text(function(d) { return d3.format(".4f")(d); });
 	probLabels.exit().remove();
	
  
}