<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
    <title>Test</title>
    
 	<script type="text/javascript" src="assets/libs/jquery/jquery-1.10.2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script> 
	<script src="https://d3js.org/d3.v4.js"></script>
	<script	src="https://dagrejs.github.io/project/graphlib-dot/v0.6.3/graphlib-dot.js"></script>
	<script src="BNScripts/lib/dagre-d3.js"></script> 
	<script src="BNScripts/lib/jquery-1.10.2.js"></script>  
	
	<script src="jslib/main.js" type="text/javascript" ></script>
	<script src="jslib/main_util.js" type="text/javascript" ></script>
      
    <style>
			.link {  fill: none;  stroke: #666;  stroke-width: 1.5px;}
			#licensing {  fill: green;}
			.link.licensing {  stroke: green;}
			.link.resolved {  stroke-dasharray: 0,2 1;}
			circle {  fill: #ccc;  stroke: #333;  stroke-width: 1.5px;}
			rect {  fill: #ccc;  stroke: #333;  stroke-width: 1.5px;}
			text {  font: 10px sans-serif;  pointer-events: none;  text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, 0 -1px 0 #fff, -1px 0 0 #fff;}
			svg { border: 1px solid #999; overflow: hidden;BN_Pane_for_Jupyter.html }
			.node { white-space: nowrap; }
			.node rect, .node circle, .node ellipse { stroke: #333; fill: #fff; stroke-width: 1.5px; }
			.cluster rect { stroke: #333; fill: #000; fill-opacity: 0.1; stroke-width: 1.5px; }
			.edgePath path.path { stroke: #333; stroke-width: 1.5px; fill: none; } 
			h1, h2 { color: #333; }
			textarea { width: 800px; }
			label { margin-top: 1em; display: block; }
			.error { color: red;  }
			
	</style> 
	 
	
</head>
 
<body > 
	<svg width=auto height=700>
<!-- 	<svg width=800 height=500> -->
  		<g/>
	</svg>  

	<script>
		createBNonViewer();
    </script> 
    
</body>
</html>