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
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	
	<style>
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
		}
		.active >.over-sub-menu:after{
			content:"\f077";
		}
		
		
		
		
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
		 	.over-sub-menu:after{
		 		right:.5rem;
		 	}
		 	.ad-i:before{
				vertical-align: sub;
			}
		 	.collapsible-header i{
		 		margin-right:.5rem;
		 		vertical-align: inherit;
		 		width:1rem;
		 	}
		 	/* .hideText .material-icons,
			.hideText .fas,
			.hideText .fa,
			.hideText .ad-i{
				margin-right:.5rem;
				margin-left:.3rem;
			} */
		 }
	</style>
	
</head>
<body style="background-color:#fff;">
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<!-- model 1 which closes entire navigation -->
	
	<div class="" style="margin-top:2rem;">
	    <div class="row">
	        <div class="col s12 m2 " id="menu-item-holder">
	            <!-- <ul class="collapsible m-0">
	                <li class="active"> -->
	                    <!-- <div class="collapsible-header secondModel" onclick="toggleMenu()"><i class="fa fa-bars"></i></div> -->
	                    <div class=" main-menu-collapse">
	                        <ul class="collapsible main-menu"> <!-- id="secondModel"> -->
							<c:forEach var="form" items="${overviewDashboardForms }" varStatus="index">
								<c:if test="${not empty form.formsSubMenu}">
											<li><div class="collapsible-header over-sub-menu" >
											
												<a href="#">
														<%-- <i class="${form.icon }"></i> --%><span class="showHide" id="${form.name }">${form.name }</span>
												</a>												

											</div>
											 <div class="collapsible-body special-padding">
			                                    <ul class="collapsible">
			                                    <c:forEach var="subList" items="${form.formsSubMenu }">
			                                        <li>
			                                            <div class="collapsible-header" >			                                            
															<a href="#">
																	<%-- <i class="${subList.icon }"></i> --%><span class="showHide" id="${subList.name }">${subList.name }</span>
															</a>			                                           
			                                            
			                                            </div>
			                                        </li>
			                                        </c:forEach>
			                                    </ul>
		                                    </div>											
											 </li>
								</c:if>	
								<c:if test="${empty form.formsSubMenu}">
										<c:if test="${not empty form.link_url}">
											<li><div class="collapsible-header" >
											
												<a href="#">
													<%-- <i class="${form.icon }"></i> --%><span class="showHide" id="${form.name }">${form.name }</span>
											</a>											
											
											</div>
										</c:if> 
								</c:if>                           
	                            </c:forEach>
	                        </ul>
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
	<script>
		$(document).ready(function(){
		    $('.collapsible').collapsible();
		    $('.searchable').select2();
		    
		    $(".collapsible-header > a > span").on("click", function () {
                var pagename = $(this).attr("id");               
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