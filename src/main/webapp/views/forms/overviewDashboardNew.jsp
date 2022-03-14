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
	             <div class=" main-menu-collapse">
	             	<div id="accordion">
	                 <!-- <h3 class="non-active bg-a"><a href="#">Section 1</a></h3>
	                 	  <div class="ds-none">
	                 		<p></p>
	                 	  </div>
						  <h3 class="bg-a"><a href="#">Section 2</a></h3>
						  <div>
						    <p>
						    <a href="#" class="bd-bl bg-a">link1</a>
						    <a href="#" class="bd-bl bg-a">link2</a>
						    </p>
						  </div>
						  <h3 class="bg-a"><a href="#">Section 3</a></h3>
						  <div>
						    <p>
						    <a href="#" class="bd-bl bg-a">link1</a>
						    <a href="#" class="bd-bl bg-a">link2</a>
						    </p>
						  </div>
						  <h3 class="bg-a"><a href="#">Section 4</a></h3>
						  <div>
						    <p>
						    <a href="#" class="bd-bl bg-a">link1</a>
						    <a href="#" class="bd-bl bg-a">link2</a>
						    </p>
						  </div> -->
					  </div>
               </div>
	        </div>
	    	<div class="col s12 m10" id="tableau-item-holder" >	    	 	
				<iframe id="dashboardOpen" name="dashboardOpen" frameborder="1" marginheight="0" marginwidth="0" title="data visualization" allowtransparency="true" allowfullscreen="true" class="timeline_body" src="" ></iframe>
	    	</div>
	    	<div class="col m2 s12" id="filter-item-holder" style="display:none;">
		    	<!-- <div class="clearHolder">
		    		<button class="btn waves-effect waves-light t-c" onclick="clearFilter();">Clear Filters</button>
		    	</div> -->
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
  <script type="text/javascript" src="https://infoviz.syntrackpro.com/javascripts/api/tableau-2.min.js"></script>
  <!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  
  <script type="text/javascript">
	   /*  var header = document.getElementById("accordion");
		var btns = header.getElementsByClassName("bg-a");
		for (var i = 0; i < btns.length; i++) {
		  btns[i].addEventListener("click", function() {
		  var current = document.getElementsByClassName("active");
		  current[0].className = current[0].className.replace(" active", "");
		  this.className += " active";
		  });
		}
		$( function() {
			  $( "#accordion" ).accordion({ header: "h3", collapsible: false, active: false });
	    }); */

	    var requestedDashboardId = '';
	    $(document).ready(function(){	 
		    var overview_work_id = '${work_id}';
		    requestedDashboardId = '${dashboardId}';
		    $.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftNav",
				type:"POST",
				data:{work_id : overview_work_id},
				cache: false,async:false,
				success : function(data){   
					$('#accordion').append(getData(data));
					
					var header = document.getElementById("accordion");
					var btns = header.getElementsByClassName("bg-a");
					for (var i = 0; i < btns.length; i++) {
					  btns[i].addEventListener("click", function() {
					  var current = document.getElementsByClassName("active");
					  current[0].className = current[0].className.replace(" active", "");
					  this.className += " active";
					  });
					}
					$( "#accordion" ).accordion({ header: "h3", collapsible: false, active: false });
					
					$.each( data, function( index, value ){
						if(index == 0 && $.trim(requestedDashboardId) == ''){
							requestedDashboardId = value.dashboard_id;
						}
					});
				}
	        });
		   
		    if($.trim(requestedDashboardId) != ''){
				var tempParentId = $('.bg-a#'+requestedDashboardId).attr("parent_id");
				if($.trim(tempParentId) != '' && tempParentId != 'undefined'){
					$('.bg-a#'+tempParentId).trigger("click");
					requestedDashboardId = '${dashboardId}';
					openDashboard(requestedDashboardId);
				}else{
					$('.bg-a#'+requestedDashboardId).trigger("click");
				}
			}
		    $('.searchable').select2();
	});
	    
    function getData(data){
    	var html= '';
		var flag = 0;
		var tempDashboardId = '';
		$.each( data, function( index, value ){
			var parentDashboardId = value.dashboard_id;
			var liDisabled = '';
			var notAvailable = '';
			/* var liDisabled = 'disabled';
			var notAvailable = 'NA';
			if(($.trim(value.work_exists_or_not) != '' && value.work_exists_or_not > 0) || value.dashboard_name == 'Project Overview'){
				liDisabled = '';
				notAvailable = '';
			} */
			if(flag == 0 && $.trim(notAvailable) == '' && $.trim(parentDashboardId) != ''){
				tempDashboardId = parentDashboardId;
				flag = flag + 1;
			}
			
			html = html+'<h3 class="bg-a" id="'+parentDashboardId+'" parent_id="" onclick="openDashboard('+value.dashboard_id+');"><a href="javascript:void(0);">'+value.dashboard_name+'</a></h3>';
			if(value.formsSubMenu!="" && value.formsSubMenu!=null && value.formsSubMenu != 'undefined'){
				html = html + '<div> <p>';
				$.each( value.formsSubMenu, function( subMenuindex, subMenuvalue ){
					var dashboardId = subMenuvalue.dashboard_id;
					var liDisabled = '';
					var notAvailable = '';
					/* var liDisabled = 'disabled';
					var notAvailable = 'NA';
					if($.trim(subMenuvalue.work_exists_or_not) != '' && subMenuvalue.work_exists_or_not > 0){
						liDisabled = '';
						notAvailable = '';
					} */
					if(flag == 0 && $.trim(notAvailable) == '' && $.trim(dashboardId) != ''){
						tempDashboardId = dashboardId;
						flag = flag + 1;
					}
			    	
					html = html + '<a href="javascript:openDashboard('+subMenuvalue.dashboard_id+');"" class="bd-bl bg-a" id="'+dashboardId+'" parent_id="'+parentDashboardId+'">'+subMenuvalue.dashboard_name+'</a>';
				});
				html = html + '</p></div> ';
			}else{
				html = html+'<div class="ds-none"> <p></p> </div>';
			}
		});
	    return html;	
	}	
	  
	function openDashboard(dashboardId){
		  $(".page-loader").show();
		  $(".bg-a").removeClass("active");
		  $("#"+dashboardId).addClass("active");
		  $(".bg-a").removeClass("disabled");
		  $("#"+dashboardId).addClass("disabled");
		  requestedDashboardId = dashboardId;
          var params = "";
          var show_left_menu = '';
          var filterIds = "";
      	  
      	  $.ajax({
      		url: "<%=request.getContextPath()%>/ajax/getFilters",
            type: 'POST',
            data:{dashboard_id : dashboardId,work_id : '${work_id}'},
            async: false,
            dataType: 'json',
            success: function (data){
         	   if(data.length){
         		   /* if($.trim(show_left_menu) == 'Yes'){
	         		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m8");
	         		   $("#menu-item-holder").show();
	         	   }else{
	         		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m10");
	         		   $("#menu-item-holder").hide();
	         	   } */
         		   $("#filter-item-holder").show();
         		   
         		   $.each( data, function( index, value ){
         			   var filter_column = value.filter_column_name;
         			   if($.trim(value.filter_column_id) != ''){
         				  filter_column = value.filter_column_id;
         			   }     				  
         			   if($.trim(filterIds) != ''){
         				  filterIds = filterIds +","+ filter_column;
         			   }else{
         				  filterIds = filter_column;
         			   }
         		   });
         		   
         		   filterIds = "'"+ filterIds + "'";
         		   
         		   var dashboardIdTemp = "'"+ dashboardId + "'";
         		   
         		   var filters = "";
         		   $.each( data, function( index, value ){
         			   var filter_column = value.filter_column_name;
        			   if($.trim(value.filter_column_id) != ''){
        				  filter_column = value.filter_column_id;
        			   } 
        			   
        			   var filter_label_name = "'"+ value.filter_label_name + "'";
        			   var filter_id = "'"+ value.filter_id + "'";
        			   var filter_column_name = "'"+ filter_column + "'";
        			   
         			   filters = filters + '<div class="filterHolder">'
					         			+ '<label>'+value.filter_label_name+'</label>'
					         			+ '<select class="searchable" filter_id='+value.filter_id+' name="'+filter_column+'" id="'+filter_column+'" onchange="getSelectedOption(this.value,'+filter_label_name+','+filterIds+','+dashboardIdTemp+','+filter_id+','+filter_column_name+');">'
					         			+ '<option value="">All</option>'
					         			$.each( value.filter, function( index2, value2 ){
					         				var filter_option_id = value2.filter_option_value;
					         				if($.trim(value2.filter_option_id) != ''){
					         					filter_option_id = value2.filter_option_id;
					         				}
					         				var selectedFlag = "";
					         				if(index == 0 && index2 == 0){
					         					selectedFlag = 'selected';
					         				}
					         				filters = filters + '<option value="'+filter_option_id+'" '+selectedFlag+'>'+value2.filter_option_value+'</option>'
					         			});
					         			filters = filters + '</select>'
					         			+ '</div>';	
         		   });
         		   
         		  filters = filters + '<div class="clearHolder">'
         		 						+ '<button class="btn waves-effect waves-light t-c" onclick="clearFilter('+filterIds+','+dashboardIdTemp+');">Clear Filters</button>'
         								+ '</div>'
         		   
         		   $("#filter-item-holder").html(filters);
         		   $('.searchable').select2();
         	   }else{
        		   //$("#tableau-item-holder").removeClass("m8");
         		   //$("#tableau-item-holder").addClass("m10");
         		   
         		   /* if($.trim(show_left_menu) == 'Yes'){
	         		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m10");
	         		   $("#menu-item-holder").show();
	         	   }else{
	         		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m12");
	         		   $("#menu-item-holder").hide();
	         	   } */
         		   $("#filter-item-holder").hide();
       		       $("#filter-item-holder").html("");
         	   }
         	   $(".page-loader").hide();
            },error: function(xhr){
            	$(".page-loader").hide();
                alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
               
            }
     	 });
      	
      	
      	 if($.trim(filterIds) != '' ){ 
      		 filterIds = filterIds.replace(/['"]+/g, '');
	      	 var ids = filterIds.split(",");
			 for(var  i=0;i<ids.length;i++){
				 var id = ids[i];
				 var val = $("#"+id).val();
				 var param = id+"="+val;
				 if($.trim(val) != ''){
					 if($.trim(params) != ''){
					 	params = params +"&"+ param;
				   	 }else{
					   params = param;
				     }
				 }
			 }
      	 }
      	 
      	 $(".page-loader").show();
		 $.ajax({
	      		url: "<%=request.getContextPath()%>/ajax/getDashboardURL",
	            type: 'POST',
	            data:{dashboard_id : dashboardId,work_id : '${work_id}',params : encodeURIComponent(params)},
	            async: false,
	            dataType: 'json',
	            success: function (data){
	            	var dashboard_url = data.dashboard_url;
	            	if($.trim(dashboard_url) == 'structure-gallery-page'){
	            		dashboard_url = "<%=request.getContextPath()%>/"+dashboard_url+"/${work_id}";
	            	}
	         	    $("#dashboardOpen").attr("src",dashboard_url);
	         	    show_left_menu = data.show_left_menu;
	         	    $(".page-loader").hide();
	            },error: function(xhr){
	            	$(".page-loader").hide();
	                alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
	            }
	     });
		 
		 if($.trim(show_left_menu) == 'Yes' && $.trim(filterIds) != ''){
	   		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m8");
	   		   $("#menu-item-holder").show();
	   	 }else if($.trim(show_left_menu) == 'Yes'){
	   		   $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m10");
	   		   $("#menu-item-holder").show();
	   		   $("#filter-item-holder").hide();
		       $("#filter-item-holder").html("");
	   	 }else{
   		      $("#tableau-item-holder").removeClass("m10 m8 m12").addClass("m12");
 		      $("#menu-item-holder").hide();
 		      $("#filter-item-holder").hide();
		      $("#filter-item-holder").html("");
 	   	 }
		 $(".page-loader").hide();
		 getFilteredOptions(filterIds,dashboardId);
	 }
	
	 function getSelectedOption(selectedValue,filter_label_name,filterIds,dashboardId,filter_id,filter_column_name){
		 //if(filter_label_name == 'Work'){
			 /*if($.trim(requestedDashboardId) != ''){
				 dashboardId = requestedDashboardId;
			 } */
			 <%--  window.location.href = "<%=request.getContextPath()%>/work-overview-dashboard/"+selectedValue+"/"+dashboardId; --%>
		 // }
			 
	     getFilteredOptions(filterIds,dashboardId);
			 
		 var params = "";
		 var ids = filterIds.split(",");
		 for(var  i=0;i<ids.length;i++){
			 var id = ids[i];
			 var val = $("#"+id).val();
			 var param = id+"="+val;
			 if($.trim(val) != ''){
				 if($.trim(params) != ''){
				 	params = params +"&"+ param;
			   	 }else{
				   params = param;
			     }
			 }
		 }
		 
		 $.ajax({
	      		url: "<%=request.getContextPath()%>/ajax/getDashboardURL",
	            type: 'POST',
	            data:{dashboard_id : dashboardId,work_id : '${work_id}',params : encodeURIComponent(params)},
	            async: true,
	            dataType: 'json',
	            success: function (data){
	         	    $("#dashboardOpen").attr("src",data.dashboard_url);
	            },error: function(xhr){
	                alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
	            }
	     });
	 }
	 
	 function getFilteredOptions(filterIds,dashboardId){
		 var params = "";
		 var ids = filterIds.split(",");
		 for(var  i=0;i<ids.length;i++){
			 var id = ids[i];
			 var val = $("#"+id).val();
			 var param = id+"='"+val+"'";
			 if($.trim(val) != ''){
				 if($.trim(params) != ''){
				 	params = params +" AND "+ param;
			   	 }else{
				   params = param;
			     }
			 }
		 }
		 for(var  i=0;i<ids.length;i++){
			 var id = ids[i];
			 var val = $("#"+id).val();
			 
			 var filter_id_temp = $('#'+id).attr("filter_id");
			 
			 if($.trim(val) == ''){
				 $("#"+id+" option:not(:first)").remove();
				 $.ajax({
			      		url: "<%=request.getContextPath()%>/ajax/getFilteredOptions",
			            type: 'POST',
			            data:{dashboard_id : dashboardId,work_id : '${work_id}',filter_id : filter_id_temp,params : encodeURIComponent(params)},
			            async: false,
			            dataType: 'json',
			            success: function (data){
			         	   if(data.length){
			         		   $.each( data, function( index, value ){
			         			  var filterOptions = value.filter;
			         			  $.each( value.filter, function( index2, value2 ){
				         			  	var filter_option_id = value2.filter_option_value;
				         				if($.trim(value2.filter_option_id) != ''){
				         					filter_option_id = value2.filter_option_id;
				         				}
				         				var length = filterOptions.length;
				         				var selectedFlag = "";
				         				if($.trim(length) != '' && length == 1){
				         					selectedFlag = 'selected';
				         				}
				         				$("#"+id).append('<option value="'+filter_option_id+'" '+selectedFlag+'>'+value2.filter_option_value+'</option>');
			                      });
			         		  });
			         		   $('.searchable').select2();
			         	   }
			         	   $(".page-loader").hide();
			            },error: function(xhr){
			            	$(".page-loader").hide();
			            }
			     });
			 }
		 }
	 }
		
	
     function clearFilter(filterIds,dashboardId){
    	 /* var ids = filterIds.split(",");
		 for(var  i=0;i<ids.length;i++){
			 var id = ids[i];
			 $("#"+id).val('');
		 }		 
       	 $(".searchable").select2();
       	 
       	 getSelectedOption('','',filterIds,dashboardId); */
       	 
       	openDashboard(dashboardId);
     }

 </script>
</body>
</html>