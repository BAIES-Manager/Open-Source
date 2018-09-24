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
					<div class="col-md-12" id="SearchResults">
						<h2>Search Results</h2>
					</div>
					<!--Search Result Items -->
				</div>
			</div>

			<!--End of Web -->

			<hr>

			<div class="row">
				<br>

				<div class="clearfix"></div>

				<hr>
				<div class="col-md-12 text-center">
					<p>Bayes Cloud by BAIES 2018</p>
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
