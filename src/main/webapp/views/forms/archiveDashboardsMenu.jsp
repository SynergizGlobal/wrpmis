<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		.ui-state-default{border: 3px solid #4498d3dd;background: #fff;}
		.bd-bl{
			border: 3px solid #4498d3dd;
			padding:.5em .5em .5em .7em;
			border-radius: 10px;
			display:block;
			}
		.ui-state-hover, .ui-icon{display:none;background: #fff;}
		.ui-accordion .ui-accordion-content{
			padding: 0em 0em 0 2.2em;
			height: auto !important;
		}
		.ui-accordion-content p{
			margin: 5px 0; 
		}
		.ui-accordion-content p a{
			margin: 5px 0; 
		}
		.ds-none{display:none !important;}
		.non-active{background: #fff;color: #000 !important;}
		.ui-state-active a:visited{
		color: #000;
		background-color: rgb(227, 242, 253) !important;
		}
		.ui-state-active{
			background-color: transparent;
		}
		.active {
		  background-color: rgb(227, 242, 253) !important;
		  color: #000 !important;
		}
		
		.ui-state-active a, .ui-state-active a:link, .ui-state-active a:visited {
		   color:#000!important;
		}

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
		.collapsible-header{
			text-align:left !important;
		}
		.secondModel .fa{
			margin:0;
		}
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
		 .disabled {
		    pointer-events:none;
		    /* opacity:0.4!important; */
		 }
		 
	</style>
	
</head>
<body style="background-color:#fff;">
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<!-- model 1 which closes entire navigation -->
	
	<div class="" style="margin-top:2rem;">
	    <div class="row">
	        <div class="col s12 m2" id="menu-item-holder">
 	             <div class=" main-menu-collapse" id="ar2">
	             	<div id="accordion1">
					</div>	
                 </div> 
 	             <div class=" main-menu-collapse" id="ar3">
	             	<div id="accordion2">
					</div>	
                 </div>                                   
	        </div>
	    	<div class="col s12 m10" id="tableau-item-holder" >	    	 	
				<iframe id="dashboardOpen" name="dashboardOpen" frameborder="1" marginheight="0" marginwidth="0" title="data visualization" allowtransparency="true" allowfullscreen="true" class="timeline_body" src="" ></iframe>
	    	</div>
	    	<div class="col m2 s12" id="filter-item-holder" style="display:none;">

	    	</div>
		</div>

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
  <script type="text/javascript" src="http://10.203.10.158:8000/javascripts/api/tableau-2.min.js"></script>
  <!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  
  <script type="text/javascript">

	    $(document).ready(function(){	

		    $.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftNavArchiveModules",
				type:"POST",
				cache: false,async:false,
				success : function(data){   
					$('#accordion1').append(getDataModules(data));
					
					var header = document.getElementById("accordion1");
					var btns = header.getElementsByClassName("bg-a");
					for (var i = 0; i < btns.length; i++) {
					  btns[i].addEventListener("click", function() {
					  var current = document.getElementsByClassName("active");
					  current[0].className = current[0].className.replace(" active", "");
					  this.className += " active";
					  });
					}
					$( "#accordion1" ).accordion({ header: "h3", collapsible: false, active: false });
				}
	        });
		    
		    
		    $.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftNavArchiveWorks",
				type:"POST",
				cache: false,async:false,
				success : function(data){  
					$('#accordion2').append(getDataWorks(data));
					
					var header = document.getElementById("accordion2");
					var btns = header.getElementsByClassName("bg-a");
					for (var i = 0; i < btns.length; i++) {
					  btns[i].addEventListener("click", function() {
					  var current = document.getElementsByClassName("active");
					  current[0].className = current[0].className.replace(" active", "");
					  this.className += " active";
					  });
					}
					$( "#accordion2" ).accordion({ header: "h3", collapsible: false, active: false });
				}
	        });	
		    $(".ui-accordion-content").hide();
		    
		    
	});

	    
    function getDataModules(data)
    {
    	var html = '<h3 class="bg-a" id="" parent_id="" onclick="openModulesMenu();"><a href="javascript:void(0);">Modules</a></h3>';

		var flag = 0;

		html = html + '<div id="idnShowModules"> <p>';
    	$.each( data, function( index, value ){
			html = html + '<a href="archive-'+value.tableauUrl+'" class="bd-bl bg-a" id="'+value.tableauDashboardId+'">'+value.tableauDashboardName+'</a>';
    	});
    	html = html + '</p> </div>';
    	return html;
    }	    
	    
	    
    function getDataWorks(data)
    {
    	
		var html = '<h3 class="bg-a" id="" parent_id="" onclick="openWorksMenu();"><a href="javascript:void(0);">Works</a></h3>';

		var flag = 0;  
		
		console.log(data);

		html = html + '<div id="idnShowWorks" onclick="openChildWorksMenu();"> <p>';

    	$.each( data, function( index, value ){
			html = html + '<a href="#" class="bd-bl bg-a" id="'+value.tableauDashboardId+'" onclick="openChildWorksSubMenu('+index+','+value.tableauDashboardId+');">'+value.tableauDashboardName+'</a>';
			if(value.tableauSubList != "" && value.tableauSubList != null && value.tableauSubList != 'undefined'){
				html = html + '<div style="margin: 0 0 0 2em;display:none;" id="submenu'+index+'" class="submenucls"> <p>';
				$.each( value.tableauSubList, function( index2, value2 ){
					
					html = html + '<a href="archive-'+value2.tableauUrl+'/'+value2.work_id_fk+'" class="bd-bl bg-a" id="'+value2.tableauDashboardId+'">'+value2.tableauDashboardName+'</a>';
	
				});
				html = html + '</p> </div>';
			
			}
    	});
		html = html + '</p> </div>';

    	return html;
    }
    
    function openModulesMenu()
    {
    		if($("#idnShowModules").is(":visible"))
    		{
    			$("#idnShowModules").hide();
    		}
    		else
   			{
    			$("#idnShowModules").show();
    			$("#idnShowWorks").hide();
    			$("#ui-id-1").addClass("active");
    			$("#ui-id-2").removeClass("active");
    			
   			}
    }
    
    
    function openWorksMenu()
    {
		if($("#idnShowWorks").is(":visible"))
		{
			$("#idnShowWorks").hide();
			$("#idnShowChildWorks").hide();
		}
		else
			{
			$("#idnShowWorks").show();
			$("#idnShowModules").hide();
			$("#ui-id-1").removeClass("active");
			$("#ui-id-2").addClass("active");			
			
			}    	
    }   
    
    function openChildWorksSubMenu(t,id)
    {
    	$(".submenucls").hide();
		if($("#submenu"+t).is(":visible"))
		{
			$("#submenu"+t).hide();
			$("#"+id).removeClass("active");
		}
		else
		{
			
			$("#submenu"+t).show();
			$("#"+id).addClass("active");
			
		}  	
    }  

 </script>
</body>
</html>