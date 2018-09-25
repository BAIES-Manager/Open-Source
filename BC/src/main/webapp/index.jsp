<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Bayes Cloud</title>


<!--         <script type="text/javascript" src="assets/libs/jquery/jquery-1.10.2.min.js"></script> -->
<!--         <script type="text/javascript" src="lib/jquery-3.3.1.min.js"></script> -->
<!-- 		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>  -->
<!-- 		<script	src="https://dagrejs.github.io/project/graphlib-dot/v0.6.3/graphlib-dot.js"></script> -->
<!-- 		<script src="https://d3js.org/d3.v5.js"></script> -->

<script src="lib/jquery-3.3.1.js"></script>
<script src="lib/d3.v5.js"></script>
<script src="lib/graphlib-dot.js"></script>
<script src="lib/dagre-d3.js"></script>

<!-- 		<script src="https://d3js.org/d3.v4.js"></script> -->
<!-- 		<script src="https://dagrejs.github.io/project/graphlib-dot/v0.6.3/graphlib-dot.js"></script> -->
<!-- 		<script src="https://dagrejs.github.io/project/dagre-d3/latest/dagre-d3.js"></script> -->

<script src="lib/bootstrap.js"></script>
<script src="lib/saveSvgAsPng.js"></script>
<script src="lib/dropzone.js"></script>
<script src="lib/ace/src-min-noconflict/ace.js" type="text/javascript"	charset="utf-8"></script>

<script src="jslib/main.js" type="text/javascript"></script>
<script src="jslib/main_util.js" type="text/javascript"></script>
<script src="jslib/main_image.js" type="text/javascript"></script>
<script src="jslib/main_search.js" type="text/javascript"></script>
<script src="jslib/main_register.js" type="text/javascript"></script>
<script src="jslib/main_dataupload.js" type="text/javascript"></script>
<script src="jslib/main_machine_learning.js" type="text/javascript"></script>

<link href="lib/theme.css" rel="stylesheet">
<link href="lib/dropzone.css" rel="stylesheet">
<link href="lib/bootstrap.css" rel="stylesheet">

<!-- 		This is for saving image -->
<style type="text/css">
.blendCircle {
	mix-blend-mode: multiply;
}
</style>

<!--  		end 					 -->

<style>
.link {
	fill: none;
	stroke: #666;
	stroke-width: 1.5px;
}

#licensing {
	fill: green;
}

.link.licensing {
	stroke: green;
}

.link.resolved {
	stroke-dasharray: 0, 2 1;
}

circle {
	fill: #ccc;
	stroke: #333;
	stroke-width: 1.5px;
}

rect {
	fill: #ccc;
	stroke: #333;
	stroke-width: 1.5px;
}

text {
	font: 10px sans-serif;
	pointer-events: none;
	text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, 0 -1px 0 #fff, -1px 0 0 #fff;
}

svg {
	border: 1px solid #999;
	overflow: hidden;
}

.node {
	white-space: nowrap;
}

.node rect, .node circle, .node ellipse {
	stroke: #333;
	fill: #fff;
	stroke-width: 1.5px;
}

.cluster rect {
	stroke: #333;
	fill: #000;
	fill-opacity: 0.1;
	stroke-width: 1.5px;
}

.edgePath path.path {
	stroke: #333;
	stroke-width: 1.5px;
	fill: none;
}

h1, h2 {
	color: #333;
}

textarea {
	width: 800px;
}

label {
	margin-top: 1em;
	display: block;
}

.error {
	color: red;
}
</style>


<style type="text/css" media="screen">
#editor {body { overflow:hidden;
	
}

#editor {
	margin: 0;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}
</style>

</head>
<body>

	<!-- *************************************************************************************	-->
	<!-- 									First Bar											-->

	<div class="navbar navbar-fixed-top header">
		<div class="col-md-12">

			<!-- BAR: SEARCH   -->
			<div class="navbar-header">
				<a class="navbar-brand">Bayes Cloud</a>
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse1">
					<i class="glyphicon glyphicon-search"></i>
				</button>
			</div>

			<div class="collapse navbar-collapse" id="navbar-collapse1">

				<form class="navbar-form pull-left" id="search_start"
					action="{{route('search_start')}}" method="post">
					<div class="input-group" style="max-width: 370px;">
						<input type="text" class="form-control" placeholder="Search"
							name="srch-term" id="srch-term">
						<div class="input-group-btn">
							<button class="btn btn-default btn-primary" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>

				<form class="navbar-form pull-right">
					<button type="button" href="#Registration"
						onclick="resetRegisterModel()" data-toggle="modal"
						class="btn btn-primary btn-round btn-md">Registration</button>
					<button type="button" onclick="saveImage()"
						class="btn btn-primary btn-round btn-md">Image</button>
					<button type="button" href="#DataUploadModal"
						onclick="dataUpload()" data-toggle="modal"
						class="btn btn-primary btn-round btn-md">Machine Learning</button>

				</form>
			</div>
		</div>
	</div>

	<!-- *************************************************************************************	-->
	<!-- 									Second BAR 											-->
	<div class="navbar navbar-default" id="subnav">
		<div class="col-md-12">
			<div class="navbar-header navbar-right">

				<form class="navbar-form pull-right" id="report"
					action="{{route('report')}}" method="post">
					<div class="input-group" style="width: 100%;">
						<input type="text" name="EV" value="EbolaVirusDisease = has;"
							class="form-control">

						<div class="input-group-btn">
							<button class="btn btn-primary btn-round btn-md" type="submit">Reasoning</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>


	<!-- Editing Pane -->

	<!-- MAIN "navbar navbar-fixed-top header" -->
	<!-- 	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="main" > -->

	<!-- <div class="container"> -->

	<div class=" " id="main">

		<div class="row">

			<!-- Edit Pane -->
			<div class="col-md-12">
				<!-- Message Pane -->
				<div class="alert alert-info alert-dismissable">
					<strong>Ready to design!</strong> Now you can develop models.
				</div>

				<!-- Graph Pane -->
				<div id="parent"
					style="padding: 0px, auto; width: auto; height: 720px; border: none;">
					<div style="float: left; width: 70%;">
						<svg width=100% height=720px
							style="border-color: #D3D3D3; background-color: #f0f8ff;">
	  							<g />
						  	</svg>
					</div>


					<!-- Creating Script BN -->
					<div style="float: right; width: 30%; height: 720;">
						<form id="createMyBN" style="border: 1px solid #D3D3D3">
							<div id="editor" style="width: 485px; height: 720px;"
								onKeyUp="createBN();">// 
// Keyboard Shortcuts: Ctrl+Alt+H 
// 

defineNode(EbolaVirusDisease, Description); 
{
	defineState(Discrete, has, not); 
	p( EbolaVirusDisease ) = 
		{ has: 0.1; not: 0.9;} 
} 

defineNode(Fever, Description); 
{
	defineState(Continuous); 
	p(Fever | EbolaVirusDisease) =
        if (EbolaVirusDisease == has) 
            { NormalDist(103, 1.4); } 
        else if (EbolaVirusDisease == not) 
            { NormalDist(98.6, 0.31); } 
}</div>
						</form>
					</div>
				</div>
			</div>

			<!--/row-->


			<!-- Search Results   -->
			<hr>
			<br>
			<div class="clearfix"></div>
			<div id="SearchMain">

				<div class="row" id="SearchResults">
<!-- 					<div class="col-md-12" id="SearchResults"> -->
<!-- 						<h2>Search Results</h2> -->
<!-- 					</div> -->
					<!--Search Result Items -->
					
					
					
					 <div class="col-md-4 col-sm-6">
	 <div class="panel panel-default">
	   <div class="panel-heading">
	   		<a href="#B" class="pull-right">Edit</a>  
	   		<a href="#A" class="pull-right">Add/</a>
	   	<h4>Weather Model</h4></div> 
	   <div class="panel-thumbnail"><center><img src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-responsive" align="middle">
	   	</center></div> 
		<div class="panel-body"> 
		  <hr>
		  This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
			This supplies graphical forecasts from numerical weather models.
		</div>
	 </div> 
	</div> 
				 <div class="col-md-4 col-sm-6">	
					
<div class="panel panel-default">
<div class="panel-heading"><h4>1</h4></div>
<div class="panel-thumbnail"> 
<center><img src="data:image/png;base64,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" class="img-responsive" align="middle">
</center></div><div class="panel-body"><hr>&nbsp;</div></div>					
					
		</div> 			
					
					
					
					
					
					
					
				</div>
			</div>

			<!--End of Web -->

			<hr>

			<div class="row">
				<br>

				<div class="clearfix"></div>

				<hr>
				<div class="col-md-12 text-center">
					<a href="http://baies.org" target="_ext">http://baies.org</a> <br>
					<a>Bayes Cloud by BAIES 2018</a>
					
				</div>
				<hr>
			</div>
		</div>
		
		<!--/main-->

		<!--message modal-->
		<div id="messageModal" class="modal fade" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h2 class="text-center">About</h2>
					</div>

					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">OK</button>
					</div>
				</div>
			</div>
		</div>
 
		<!--Data Upload modal-->
		<div id="DataUploadModal" class="modal fade" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h2 class="text-center">Upload a CSV Data File</h2>
					</div>

					<form id="my-awesome-dropzone" action="UploadData" class="dropzone"
						method="POST"></form>

					<div id="uploaded_file_name" class="panel-body"></div>

					<form class="form col-md-12 center-block"
						id="machine_learning_start"
						action="{{route('machine_learning_start')}}" method="post">
						<div class="modal-footer">
							<button class="btn btn-primary btn-lg btn-block" type="submit">Start
								Machine Learning</button>
						</div>
					</form>
				</div>
			</div>
		</div>
 
		<!--Registration modal-->
		<div id="Registration" class="modal fade" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h2 class="text-center">
							<img src="data/img/BN.PNG"> <br>Model Registration
						</h2>
					</div>

					<div class="modal-body">
						<form class="form col-md-12 center-block" id="register_start"
							action="{{route('register_start')}}" method="post" charset=UTF-8>

							<div class="form-group">
								<input type="text" class="form-control input-lg" id="Edit_Title"
									placeholder="Title (e.g., Human Model)">
							</div>

							<div class="form-group">
								<input type="text" class="form-control input-lg"
									id="Edit_Description"
									placeholder="Description (e.g., A BN for human activity)">
							</div>

							<div class="form-group">
								<input type="text" class="form-control input-lg" id="Edit_Links"
									placeholder="Links (e.g., http://a.com; http://b.com)">
							</div>

							<div class="form-group">
								<input type="text" class="form-control input-lg"
									id="Edit_Reference"
									placeholder="Reference (e.g., Author. (20**). Title. Journal.;...">
							</div>

							<div class="form-group">
								<input type="text" class="form-control input-lg"
									id="Edit_Authors" placeholder="Authors (e.g., John; Maria)">
							</div>

							<div class="form-group">
								<input type="text" class="form-control input-lg"
									id="Edit_Keywords"
									placeholder="Keywords (e.g., Human; Activity)">
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
							</div>

							<!-- Message Pane -->
							<br>
							<div class="alert alert-info alert-dismissable" id="register_msg">
							</div>


							<div class="modal-footer">
								<div class="col-md-12">
									<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
								</div>
							</div>
						</form>

					</div>
				</div>

			</div>
		</div>
	</div>
 
	<!--login modal-->
	<div id="loginModal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h2 class="text-center">
						<img
							src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100"
							class="img-circle"><br>Login
					</h2>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block">
						<div class="form-group">
							<input type="text" class="form-control input-lg"
								placeholder="Email">
						</div>
						<div class="form-group">
							<input type="password" class="form-control input-lg"
								placeholder="Password">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-lg btn-block">Sign In</button>
							<span class="pull-right"><a href="#">Register</a></span><span><a
								href="#">Need help?</a></span>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div class="col-md-12">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
 
	<!--about modal-->
	<div id="aboutModal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h2 class="text-center">About</h2>
				</div>
				<div class="modal-body">
					<div class="col-md-12 text-center" style="line-height: 1.8;">
						<b>Bayes Cloud - Collective Intelligence Multi-Model
							Integration Platform</b>
					</div>
					<div></div>
					<div class="col-md-12 text-left" style="line-height: 1.8;">Bayes
						Cloud provides three main functions:</div>
					<div class="col-md-12 text-left" style="line-height: 1.8;">(1)
						Collaborative causal modeling</div>
					<div class="col-md-12 text-left" style="line-height: 1.8;">(2)
						Causal model integration</div>
					<div class="col-md-12 text-left" style="line-height: 1.8;">(3)
						Causal model reasoning and learning</div>
					<div class="col-md-12 text-left" style="line-height: 1.8;">
						The three functions are supported by Subject-Matter Experts and/or
						Artificial Intelligence, with uncertainty treatment.</div>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">OK</button>
				</div>
			</div>
		</div>
	</div>
 
	<!-- *********************************** -->
	<!--    		End scripts 			 -->
	<script src="lib/my_ace_control.js" type="text/javascript"
		charset="utf-8"></script>

</body>
</html>
