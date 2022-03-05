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
		/* .active >.over-sub-menu:after{
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
		 /* .over-sub-menu:after{
		 		right:.5rem;
		 	} */
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
		 
		 .disabled {
		    pointer-events:none;
		    opacity:0.4!important;
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
	            <!-- <ul class="collapsible m-0">
	                <li class="active"> -->
	                    <!-- <div class="collapsible-header secondModel" onclick="toggleMenu()"><i class="fa fa-bars"></i></div> -->
	                    <div class=" main-menu-collapse" id="nestable"></div>
	               <!--  </li>
	            </ul> -->
	        </div>
	    	<div class="col s12 m10" id="tableau-item-holder" >	    	 	
				<iframe id="dashboardOpen" name="dashboardOpen" frameborder="1" marginheight="0" marginwidth="0" title="data visualization" allowtransparency="true" allowfullscreen="true" class="timeline_body" src="" ></iframe>
	    	</div>
	    	<div class="col m2 s12" id="filter-item-holder" style="display:none;">
		    	<div class="clearHolder">
		    		<button class="btn waves-effect waves-light t-c" onclick="clearFilter();">Clear Filters</button>
		    	</div>
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

  
  <script type="text/javascript">
	
   $(document).ready(function(){	 
	   var overview_work_id = '${work_id}';
	   $.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftNav",
			type:"POST",
			data:{work_id : overview_work_id},
			cache: false,async:false,
			success : function(data){   
				
				var html= '<ul class="collapsible main-menu">';
				
				var flag = 0;
				var tempDashboardId = '';
				$.each( data, function( index, value ){
					var nameStr = value.dashboard_name;
					nameStr = nameStr.replaceAll("&","_");
					nameStr = nameStr.replaceAll(" ","--");
					
					var parentDashboardId = value.dashboard_id;
					var liDisabled = 'disabled';
					var notAvailable = 'NA';
					if(($.trim(value.work_exists_or_not) != '' && value.work_exists_or_not > 0) || value.dashboard_name == 'Project Overview'){
						liDisabled = '';
						notAvailable = '';
					}
					if(flag == 0 && $.trim(notAvailable) == '' && $.trim(parentDashboardId) != ''){
						tempDashboardId = parentDashboardId;
						flag = flag + 1;
					}
					
					html = html + '<li class="'+liDisabled+'"><div class="collapsible-header over-sub-menu" id="'+parentDashboardId+'" >';
					html = html + '<a href="javascript:void(0);" id='+nameStr+value.dashboard_id+'>'
					+'<span class="showHide" id="'+nameStr+'">'+value.dashboard_name+'</span>'
					+'<span style="float:right;color: red;">'+notAvailable+'</span>'
					+'</a>'
					+'</div>';
					if(value.formsSubMenu != "" && value.formsSubMenu != null && value.formsSubMenu != 'undefined'){
							html = html + '<div class="collapsible-body special-padding">';
							//html = html + '' + getData(value.formsSubMenu);
							
							html= html +'<ul class="collapsible main-menu">';
							
							$.each( value.formsSubMenu, function( subMenuindex, subMenuvalue ){
								nameStr = subMenuvalue.dashboard_name;
								nameStr = nameStr.replaceAll("&","_");
								nameStr = nameStr.replaceAll(" ","--");
								
								var dashboardId = subMenuvalue.dashboard_id;
								var liDisabled = 'disabled';
								var notAvailable = 'NA';
								if($.trim(subMenuvalue.work_exists_or_not) != '' && subMenuvalue.work_exists_or_not > 0){
									liDisabled = '';
									notAvailable = '';
								}
								if(flag == 0 && $.trim(notAvailable) == '' && $.trim(dashboardId) != ''){
									tempDashboardId = dashboardId;
									flag = flag + 1;
								}
								
								html = html + '<li class="'+liDisabled+'"><div class="collapsible-header over-sub-menu" id="'+dashboardId+'" parent_id="'+parentDashboardId+'">';
								html = html + '<a href="javascript:void(0);" id='+nameStr+subMenuvalue.dashboard_id+'>'
								+'<span class="showHide" id="'+nameStr+'">'+subMenuvalue.dashboard_name+'</span>'
								+'<span style="float:right;color: red;">'+notAvailable+'</span>'
								+'</a>'
								+'</div>';
								html = html + '</li>';
							});
							
							html = html + '</ul>';
							
							
							html = html + '</div>';
					}
					html = html + '</li>'; 
				});
				html = html + '</ul>';
				
				$('#nestable').html(html);		
				
				$('.collapsible').collapsible();
			    $('.searchable').select2();
			    
			    var requestedDashboardId = '${dashboardId}';
			    if($.trim(requestedDashboardId) != ''){
			    	tempDashboardId = requestedDashboardId;
			    }
				if($.trim(tempDashboardId) != ''){
					
					onLoadPage(tempDashboardId);
					var tempParentId = $('.collapsible-header#'+tempDashboardId).attr("parent_id");
					if($.trim(tempParentId) != ''){
						tempDashboardId = tempParentId;
					}
					
					$('.collapsible-header#'+tempDashboardId).trigger("click");
				}
			}
	    });
	    
	    
	    
	    $(".collapsible-header").on("click", function () {
	    	var dashboardId = $(this).attr("id");
			onLoadPage(dashboardId);
        });		
	    
	    $('.collapsible').collapsible();
	    $('.searchable').select2();
	});
	
	
	function getData(data){
		var html= '<ul class="collapsible main-menu">';
		
		$.each( data, function( index, value ){
			var nameStr = value.dashboard_name;
			nameStr = nameStr.replaceAll("&","_");
			nameStr = nameStr.replaceAll(" ","--");
			
			var dashboardId = value.dashboard_id;
			var liDisabled = 'disabled';
			var notAvailable = 'NA';
			if($.trim(value.work_exists_or_not) != '' && value.work_exists_or_not > 0){
				liDisabled = '';
				notAvailable = '';
			}
			html = html + '<li class="'+liDisabled+'"><div class="collapsible-header over-sub-menu" id="'+dashboardId+'">';
			html = html + '<a href="javascript:void(0);" id='+nameStr+value.dashboard_id+'>'
			+'<span class="showHide" id="'+nameStr+'">'+value.dashboard_name+'</span>'
			+'<span style="float:right;color: red;">'+notAvailable+'</span>'
			+'</a>'
			+'</div>';
			if(value.formsSubMenu != "" && value.formsSubMenu != null && value.formsSubMenu != 'undefined'){
					html = html + '<div class="collapsible-body special-padding">';
					html = html + '' + getData(value.formsSubMenu);
					html = html + '</div>';
			}
			html = html + '</li>'; 
		});
		html = html + '</ul>';
	    return html;	
	}	
	
	
	function onLoadPage(dashboardId){
          $('.collapsible-header').css("background-color", "#ffffff");
          $('.collapsible-header#'+dashboardId+'').css("background-color", "#e3f2fd");
          var params = "";
          var show_left_menu = '';
          var filterIds = "";
      	  <%-- $.ajax({
	      		url: "<%=request.getContextPath()%>/ajax/getDashboardURL?dashboardId="+dashboardId+"&params="+params,
	            type: 'POST',
	            async: false,
	            dataType: 'json',
	            success: function (data){
	         	    $("#dashboardOpen").attr("src",data.dashboard_url);
	         	    show_left_menu = data.show_left_menu;
	            },error: function(xhr){
	                alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
	            }
	      }); --%>
      	  
      	  $.ajax({
      		url: "<%=request.getContextPath()%>/ajax/getFilters?dashboardId="+dashboardId,
            type: 'POST',
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
        			   
         			   filters = filters + '<div class="filterHolder">'
					         			+ '<label>'+value.filter_label_name+'</label>'
					         			+ '<select class="searchable" name="'+filter_column+'" id="'+filter_column+'" onchange="getSelectedOption(this.value,'+filter_label_name+','+filterIds+','+dashboardIdTemp+');">'
					         			+ '<option value="">All</option>'
					         			$.each( value.filter, function( index2, value2 ){
					         				var filter_option_id = value2.filter_option_value;
					         				if($.trim(value2.filter_option_id) != ''){
					         					filter_option_id = value2.filter_option_id;
					         				}
					         				var overview_work_id = '${work_id}';
					         				var selectedFlag = "";
					         				if($.trim(overview_work_id) == $.trim(filter_option_id)){
					         					selectedFlag = 'selected';
					         				}
					         				filters = filters + '<option value="'+filter_option_id+'" '+selectedFlag+'>'+value2.filter_option_value+'</option>'
					         			});
					         			filters = filters + '</select>'
					         			+ '</div>';	
         		   });
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
            },error: function(xhr){
               alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
            }
     	 });
      	
      	
      	 if($.trim(filterIds) != '' ){ 
      		 filterIds = filterIds.replace(/['"]+/g, '');
	      	 var ids = filterIds.split(",");
			 for(var  i=0;i<ids.length;i++){
				 var id = ids[i];
				 var val = $("#"+id).val();
				 var param = id+"~"+val;
				 if($.trim(val) != ''){
					 if($.trim(params) != ''){
					 	params = params +"$"+ param;
				   	 }else{
					   params = param;
				     }
				 }
			 }
      	 }
      	
		 $.ajax({
	      		url: "<%=request.getContextPath()%>/ajax/getDashboardURL",
	            type: 'POST',
	            data:{dashboard_id : dashboardId,work_id : '${work_id}',params : encodeURIComponent(params)},
	            async: false,
	            dataType: 'json',
	            success: function (data){
	         	    $("#dashboardOpen").attr("src",data.dashboard_url);
	         	    show_left_menu = data.show_left_menu;
	            },error: function(xhr){
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
		 
	 }
	
	 function getSelectedOption(selectedValue,filter_label_name,filterIds,dashboardId){
		 if(filter_label_name == 'Work'){
			 window.location.href = "<%=request.getContextPath()%>/work-overview-dashboard/"+selectedValue+"/${dashboardId}";
		 }		 
		 var params = "";
		 var ids = filterIds.split(",");
		 for(var  i=0;i<ids.length;i++){
			 var id = ids[i];
			 var val = $("#"+id).val();
			 var param = id+"~"+val;
			 if($.trim(val) != ''){
				 if($.trim(params) != ''){
				 	params = params +"$"+ param;
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
		
     /*function toggleMenu(){
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