<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Overview Dashboard - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">	
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">	
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	
	<style>
		.ui-icon, .ui-widget-content .ui-icon{
			display:none;
		}
		.ui-state-active .ui-icon, .ui-button:active .ui-icon{
			display:none;
		}
		.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr, .ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl{
			border-radius: 10px;
		}
		.ui-widget-content{
			border: none;
		}
		.ui-state-default{border: 3px solid #4498d3dd;}
		.bd-bl{
			border: 3px solid #4498d3dd;
			padding:.5em .5em .5em .7em;
			border-radius: 10px;
			display:block;
			}
		.ui-state-hover, .ui-icon{display:none;}
		.ui-accordion .ui-accordion-content{
			padding: 0em 0em 0 2.2em;
		}
		.ui-accordion-content p{
			margin: 5px 0; 
		}
		.ui-accordion-content p a{
			margin: 5px 0; 
		}
		.ds-none{display:none !important;}
		.non-active{background: #f6f6f6 !important;color: #000 !important;}
		.non-active:hover{background: #ededed !important;}
		/* .ad-i{
			font-size: 1.1rem !important;
			margin-left: .3rem;
		}
		.ad-i:before{
			vertical-align: sub;
		} */
		.main-menu-collapse{
			padding:0;
		}
		.main-menu-collapse .collapsible,.m-0{
			margin:0;
		}
		.main-menu-collapse .special-padding{	
			padding:0;		
			padding-left:max(1.5rem,20px);
			position:relative;
		}
		.collapsible{
			border:none !important;
			box-shadow:none;
		}
		.main-menu li .collapsible-header{
			/* background-color:#DAEAFB;
			background-color:#E4EFFC;
			background-color:#A3C9F5; */
			
			/* background-color:#deebf7;
			border:none; */
			
			background-color:transparent;
			border:2px solid #177dc5;
			border-radius:10px;
			margin-top:1px;
			margin-bottom:1px;
			padding:.35rem .5rem;
		}
		.main-menu li .collapsible-header a{
			color:#000;
			display:block;
			width:100%;
		}
		.main-menu li .collapsible-body{
			/* background-color:#FDDAD9; */
			
			/* background-color:#EDF4FD;			
			border:none; */
			
			background-color:transparent;
			/* border:2px solid #177dc5;
			border-radius:20px; */
		}
		
.collapsible-body {
    border-bottom: 0px solid #ddd;
    box-sizing: border-box;
}		
		.special-padding .collapsible li:not(.active) .collapsible-header{
			background-color:transparent;
		}
/*				
		.over-sub-menu{		    
		    position:relative;		
		}
 		.over-sub-menu:after{
			content:"\f078";
			font: normal normal normal 14px/1 FontAwesome;
			font-size: .7rem;
		    text-rendering: auto;
		    -webkit-font-smoothing: antialiased;
		    position:absolute;
		    right:1rem;
		    top:38%;
		} */
/* 		.active >.over-sub-menu:after{
			content:"\f077";
		} */
		
		
		
		
		/* .secondModel{
			/* box-shadow :0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important; */
			justify-content:flex-end;
		} */
		.collapsible-header{
			text-align:left !important;
		}
		.secondModel .fa{
			margin:0;
		}
		/* .showHide{
			display:inline-block;			
		} */
		/* .hideText .showHide,
		.hideText .over-sub-menu:after{
			display:none;
		}
		.hideText{
			display:inline-block;
		} */
		/* .hideText .material-icons,
		.hideText .fas,
		.hideText .fa,
		.hideText .ad-i{
			margin-right:0;
			margin-left:0;
		} */
		.timeline_body {
		    display: block;
		    margin: 0 auto;
		    /*border: none;*/
		    width: 100%;
		    height: 560px;
		    height:85vh;
		}
		@media only screen and (max-width:678px){
			/* #secondModel {
				margin-bottom:.5rem;
			} */
			.main-menu li{
				display:inline-block;
				margin-inline:auto;
			}
			/* .ad-i:before{
				vertical-align: baseline;
			} */
		}
	</style>
    <style>
		 .main-menu li .collapsible-header,
		 .filterHolder{
		    border: 3px solid #4498d3dd;
		    margin-top:2px;
		 }
		 .timeline_body{
		 	border:3px solid #4498d3dd;
		 	border-radius:14px;
		 }
		 .filterHolder{
		 	max-width:100%;
		 	border-radius:10px;
		 	padding: .3rem .6rem;
		 }
		 .filterHolder .select2.select2-container{
		 	max-width:100% !important;
		 }
		 .filterHolder label{
		 	margin-top:-.4rem;
		 	display:block;
		 }
		 .clearHolder{
		 	padding: .3rem .6rem;
		 	text-align:center;
		 	margin-top:.6rem;
		 }
		 .clearHolder .btn{
		 	background-color: #4498d3;
		    color: #000;
		    border-radius: 20px;
		 }
		 
		 @media only screen and (max-width:576px){
		 	.main-menu:not(.hideText) li .collapsible-header{
		 		padding-right:2rem;
		 	}

		 	.ad-i:before{
				vertical-align: sub;
			}
		 	.collapsible-header i{
		 		margin-right:.5rem;
		 		vertical-align: inherit;
		 		width:1rem;
		 	}
		 
		 }
		 
		 
	</style>
	
</head>
<body style="background-color:#fff;">
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<style>
		.brand-logo img{
		    width: 3rem! important;
		}
		 #CurrentDate{
		 	left:3.4rem !important;
		 	font-size:.75rem !important;
		}
	</style>	
	<!-- model 1 which closes entire navigation -->
	
	<div class="" style="margin-top:2rem;">
	    <div class="row">
	        <div class="col s12 m2 " id="menu-item-holder">
	            <!-- <ul class="collapsible m-0">
	                <li class="active"> -->
	                    <!-- <div class="collapsible-header secondModel" onclick="toggleMenu()"><i class="fa fa-bars"></i></div> -->
	                    <div class=" main-menu-collapse" id="nestable">
	                    	<div id="accordion">
	                    		<h3 class="non-active"><a href="#">Section 1</a></h3>
	                    		<div class="ds-none">
	                    			<p></p>
	                    		</div>
								  <h3><a href="#">Section 2</a></h3>
								  <div>
								    <p>
								    <a href="#" class="bd-bl">link1</a>
								    <a href="#" class="bd-bl">link2</a>
								    </p>
								  </div>
								  <h3><a href="#">Section 3</a></h3>
								  <div>
								    <p>
								    <a href="#" class="bd-bl">link1</a>
								    <a href="#" class="bd-bl">link2</a>
								    </p>
								  </div>
								  <h3><a href="#">Section 4</a></h3>
								  <div>
								    <p>
								    <a href="#" class="bd-bl">link1</a>
								    <a href="#" class="bd-bl">link2</a>
								    </p>
								  </div>
	                    	</div>
	                    </div>
	               <!--  </li>
	            </ul> -->
	
	        </div>
	    	<div class="col s12 m10" id="tableau-item-holder" >	    	 	
			<iframe id="dashboardOpen" name="dashboardOpen" frameborder="1" marginheight="0" marginwidth="0" title="data visualization" allowtransparency="true" allowfullscreen="true" class="timeline_body" src="" ></iframe>
	    	</div>
	    	<div class="col m2 s12" id="filter-item-holder" style="display:none;">
		    	<div class="filterHolder">
		    		<label>Work</label>
		    		<select class="searchable" name="work_id_fk" id="work_id_fk">
		    			<option>select</option>
		    			<option>thane diva</option>
		    			<option>fob</option>
		    			<option>wr fob</option>
		    			<option>cr fob</option>
		    		</select>
		    	</div>
		    	<div class="filterHolder">
		    		<label>Project</label>
		    		<select class="searchable" name="project_id_fk" id="project_id_fk">
		    			<option>select</option>
		    			<option>thane diva</option>
		    			<option>fob</option>
		    			<option>wr fob</option>
		    			<option>cr fob</option>
		    		</select>
		    	</div>
		    	<div class="filterHolder">
		    		<label>HOD</label>
		    		<select class="searchable" name="hod_id_fk" id="hod_id_fk">
		    			<option>select</option>
		    			<option>thane diva</option>
		    			<option>fob</option>
		    			<option>wr fob</option>
		    			<option>cr fob</option>
		    		</select>
		    	</div>
		    	<div class="filterHolder">
		    		<label>Dy HOD</label>
		    		<select class="searchable" name="dyhod_id_fk" id="dyhod_id_fk">
		    			<option>select</option>
		    			<option>thane diva</option>
		    			<option>fob</option>
		    			<option>wr fob</option>
		    			<option>cr fob</option>
		    		</select>
		    	</div>
		    	<div class="clearHolder">
		    		<button class="btn waves-effect waves-light t-c" onclick="clearFilter();">Clear Filters</button>
		    	</div>
	    </div>
	</div>

<!-- model 2 which shows icons on navigation -->
	
	<!-- <div class="container">
	    <div class="row">
	        <div class="col s12 m4">
	                <div>
	                    <div class="collapsible-header 2ndModel" onclick="toggleMenu()"><i class="fa fa-bars"></i>Logo</div>
	                    <div class=" main-menu-collapse">
	                        <ul class="collapsible main-menu" id="2ndModel">
	                            <li>
	                                <div class="collapsible-header over-sub-menu"><i class="material-icons">filter_drama</i> 
	                                	<span class="showHide">First</span>
	                                </div>
	                                <div class="collapsible-body special-padding">
	                                    <ul class="collapsible">
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i>
	                                            	<span class="showHide">Second</span>
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header over-sub-menu"><i
	                                                    class="material-icons">place</i><span class="showHide">Second</span></div>
	                                            <div class="collapsible-body special-padding">
	                                                <ul class="collapsible">
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body">
	
	                                                        </div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                </ul>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i><span class="showHide">Second</span>
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">place</i><span class="showHide">Second</span></div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Third</span></div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                        </ul>
	                    </div>
	                </div>	
	        </div>
	        <div class="col s12 m8">
	            <p> content goes here </p>
	        </div>
	    </div>
	</div> -->
	
	
		<div class="page-loader" style="display: none;">
		<div class="preloader-wrapper big active">
			<div class="spinner-layer spinner-blue-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer included -->
 	<jsp:include page="../layout/footer.jsp"></jsp:include> 

  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script type="text/javascript" src="https://infoviz.syntrackpro.com/javascripts/api/tableau-2.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

  
	<script>
	 $( function() {
		    $( "#accordion" ).accordion({ header: "h3", collapsible: false, active: false });
		  } );
	$(".brand-logo.fs").html('<img src="/pmis/resources/images/mrvclogo.png" alt="Logo"> <span class="brand-text">MRVC PMIS</span><span id="CurrentDate"></span>');  			
	document.getElementById("CurrentDate").innerHTML=todayDate;
	
	
	$.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftNavNodes",
		type:"POST",
		cache: false,async:false,
		success : function(data)
		{    
			//$('#nestable').append(getData(data)); 
		}
	});	
	
	
	function getData(Data)
	{
		var html= '<ul class="collapsible main-menu">';
		
		$.each( Data, function( index, value ){
			var nameStr=value.name;
				nameStr=nameStr.replaceAll("&","_");
				nameStr=nameStr.replaceAll(" ","--");
			 html=html+'<li><div class="collapsible-header over-sub-menu" id="'+nameStr+'">';
			html=html+'<a href="#" id='+value.id+'><span class="showHide" id="'+nameStr+'">'+value.name+'</span></a></div>';
			if(value.formsSubMenu!="" && value.formsSubMenu!=null && value.formsSubMenu!=undefined)
				{
					html= html+'<div class="collapsible-body special-padding">';
					html=html+''+getData(value.formsSubMenu);
					html=html+'</div>';
				}
			html=html+'</li>'; 
		});
		html=html+'</ol>';
	    return html;	
	}	
	
	
	  function onLoadPage(pageName)
	  {
		  
          $('.collapsible-header').css("background-color", "#ffffff");
          $('.collapsible-header#'+pageName+'').css("background-color", "#e3f2fd");
		  
	                var pagename = pageName;
               
	             	var bool = false;
	             	 $.ajax({
	             		url: "<%=request.getContextPath()%>/ajax/GetURL?tableauDashboardName="+pagename,
	                   type: 'POST',
	                   async: true,
	                   dataType: 'json',
	                   success: function (data) 
	                   {
	                	   $("#dashboardOpen").attr("src",data.tableauUrl);
	                   },error: function(xhr){
	                       alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
	                   }
	  });
	  }
	
		$(document).ready(function(){
		    $('.collapsible').collapsible();
		    $('.searchable').select2();
		    
		    onLoadPage("Project Overview");
		    
		    $(".collapsible-header").on("click", function () {
		    	
		    	var pagename = $(this).attr("id");
				onLoadPage(pagename);
            });		    
		    
		  });
		
		/*  function toggleMenu(){
				$('#secondModel,.secondModel').toggleClass('hideText');
				//$('#tableau-item-holder').toggleClass('m8 m11');
				$('#menu-item-holder').toggleClass('m2 m1');
				
				$('#tableau-item-holder').toggleClass('m8 m10');
				$('#filter-item-holder').toggleClass('m2 m1');
			} */
	        function clearFilter(){
		        	$("#work_id_fk").val("");
		        	$("#project_id_fk").val("");
		        	$("#hod_id_fk").val("");
		        	$("#dyhod_id_fk").val("");
		        	
		        	$('#work_id_fk option:eq(0)').prop('selected',true);
		        	$('#project_id_fk option:eq(0)').prop('selected',true);
		        	$('#hod_id_fk option:eq(0)').prop('selected',true);
		        	$('#dyhod_id_fk option:eq(0)').prop('selected',true);
		        	$(".searchable").select2();
		        }

	</script>
	</body>
	</html>