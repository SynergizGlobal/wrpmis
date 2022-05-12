<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WBS Tree - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png"> 
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contractor.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
   	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
   	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
   	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
   	<link href="/pmis/resources/css/app_2.min.css" rel="stylesheet">
   	<link href="/pmis/resources/css/app_1.min.css" rel="stylesheet">
   	
   	<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link href="/pmis/resources/vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
		<link href="/pmis/resources/vendors/bower_components/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet">
		 
		<link href="/pmis/resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">
		<link href="/pmis/resources/css1/material-design-iconic-font.css" rel="stylesheet">
		<link href='/pmis/resources/css1/font-awesome.css' rel='stylesheet'>
		<link href="/pmis/resources/css1/app_1.min.css" rel="stylesheet">
		<link href="/pmis/resources/css1/app_2.min.css" rel="stylesheet">
		<link href="/pmis/resources/css1/style.css" rel="stylesheet">
		<link href="/pmis/resources/css1/chosen.css" rel="stylesheet"> -->
   <style>
   		/*  #imageFiles{
   		 	display: flex;
		    flex-wrap: nowrap;
		    justify-content: center;
		    vertical-align: middle;
   		 } */
   		 .select2-results__option{
   		 	font-size: 13px;
   		 }
   		 .des h5{
				font-size: 18px !important;
			}
   		 .removed{
   		 	margin-left: 17em !important;
   		 }
   		 [type="checkbox"]:not(:checked), [type="checkbox"]:checked{
   		 	opacity: 1;
    		pointer-events: initial;
    		-webkit-transform: scale(1.5);
    		margin: 12px 307px;
   		 }
   		 .br-bl{border: 2px solid #4498d3dd;
   		 	padding:10px !important;
   		 	border-radius: 10px;
   		 }
   		 .bg-m, .bg-m:hover{background-color: #4498d3dd;}
   		 .img-li:hover {
    		box-shadow: 0 14px 28px rgb(0 0 0 / 15%), 0 10px 10px rgb(0 0 0 / 0%);
		}
		.swal-overlay {
		background-color: rgba(63,255,106,0.69);
		}

	    .w31{width: 31% !important;}
	    .mt10px{margin-top: 10px;padding: 10px;}
   		 .cw{color:#fff;
   		 	height: 4em;
			    display: flex;
			    flex-wrap: nowrap;
			    align-content: center;
			    justify-content: center;
			    align-items: center;
   		 }
   		 .align-center{
   		 	display: flex;
    		align-items: center;
   		 }
   		 .column-reverse{
            display: flex;
            flex-direction: row-reverse;
            align-items: normal;
        }




input[type=checkbox] {
    display: block;
      -webkit-appearance:checkbox;
    
} 


/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}

@media(max-width: 1920px){
	.removed{
		margin-left: 27em !important;
	}
}
@media(max-width: 1560px){
	.removed{
		margin-left: 20em !important;
	}
}
@media(max-width: 1366px){
	.removed{
		margin-left: 17.5em !important;
	}
}
@media(max-width: 1024px){
	.gal-img{
		height: 180px;
   		max-width: 15em;
	}
	.w31{
		width: 45% !important;
	}
	.removed{
		margin-left: 20em !important;
	}
}
@media(max-width: 820px){
.column-reverse{flex-direction: column;}
.w31{width: 47% !important;}
.modal{padding-left: 0;}
.modal{width: 90%;}
.removed{margin-left: 21em !important;}
}
@media (min-width: 768px){
	p {
	    font-size: 12px !important;
	}
	.select2-container--default .select2-selection--single .select2-selection__rendered{
		font-size: 14px !important;
	}
}
@media(max-width: 575px){
	.w31{width: 95% !important;}
	.cw{height: 6em;}
	.ta-right{text-align:right;}
	.tree-body p{
		display: flex !important;
	}
	.des{
		margin-top: 15px;
	}
	
	.m-text-center{
		text-align: center !important;
	}
}
@media(max-width: 360px){
	.removed{margin-left: 17.5em !important;}
}
 .btn {
       padding-left: 6px;
       padding-right: 6px;
     }
     
     .editMode{
         background-color: rgb(219 219 219 / 69%);
	}
.centered {
			    text-align: center;
			    font-size: 0;
			    padding-bottom: 10px;
			}
			.centered > div {
			    float: none;
			    display: inline-block;
			    text-align: left;
			    font-size: 13px;
				border: 1px solid #000;
			}
			
			
			/* Extra: Just to see columns...*/
			 /* .row { 
			    border: 1px solid black;
			 } */ 
			
			.tree-body {
				padding: 0px;
			}
			.tree-body p{ 
				margin: 0px;
			    color: #fff;
			    display: inherit;
			    padding: 2px 10px;
			} 
			.Finished{ 
			    background: green;
			} 
			.pending {
				background: #f27935;
			}
			.notstarted {
				background: blue;
			}
			.inprogress {
				background: orange;
			}
			.tree-body strong{ 
				padding: 0px 10px;
			} 
			.section-content {
				/* margin-bottom: 10px;
			    padding-bottom: 10px; */
			    border-bottom: 0.2px solid #9c9696 !important;
			    background-color: #fff !important;
			}
			.multiple-columns .tree-body {
				margin-right: 15px;
				cursor: pointer;
			}
			.section-content .col-md-4 {
				width: auto;
			}
			.section-content .col-sm-4 {
				width: auto;
			}
			.tree-body {
				margin: 8px 0px;
			}
			
			div .active-node{
			   /*  box-shadow: 3px 3px 3px rgba(0,0,0,0.7); */
			    border: 2px solid #000;
			}
			
			div .active-node:after { 
				border-left: 10px solid transparent;
			    border-right: 10px solid transparent;
			    content: "";
			    border-top: 14px solid #444242;
			    position: absolute;
			    bottom: -14px;
			    width: 0px;
			    left: 50%;
			    margin-left: -10px;
		    }
		    .row-bg {
		    	background: #f5f5f5;
		    }
		    .card{
		    	background-color: #e3e7ea !important; 
		    }
		    .container-fluid{
		    		  padding-top: 1.5em;
		    }
   </style>
</head>

<body>
    <!-- header included -->
    <%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>

	<div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6><span id="work_short_name"></span>${work.work_short_name } - WBS Tree</h6>
                            <!-- <div class="col s12 m12 right-align">
                             <div class="m-n1">
                            	<a href="javascript:void(0);" onclick="exportImages();" style="right: 282px;top: 65px;position: absolute;"
												class="btn waves-effect waves-light bg-s t-c m-d-none download"> <strong><i
													class="fa fa-cloud-download"></i> Download</strong></a>
									 <a href="javascript:void(0);" class="btn waves-effect waves-light bg-s t-c m-d-none back" style="display: none;float: left;top: 27px;"
										  onclick="editImages();"> <i class="fa fa-cloud-download" aria-hidden="true"></i>Back</a>
									  <a href="javascript:void(0);" class="btn waves-effect waves-light bg-s t-c m-d-none back" style="display: none; top: 65px; right: 372px;position: absolute;"
									   onClick="selectAll();"> <i class="fa fa-cloud-download" aria-hidden="true" ></i><span id="selecAllToggle">select all</span></a>				
										 			
							</div>
							
                        	</div> -->
                        	      
                        </div>
                     
                    </span>
               
                    <div class="">
                        <div class="row no-mar" style="margin-bottom: 0;">
                        </div>
                        <div class="row  column-reverse">
                            <div class="col l3 m6 s12 offset-m3">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s8 m12 input-field br-bl offset-s2">
                                        <p class="searchable_label"> Contract </p>
                                        <select id="contract_id" name="contract_id" class="searchable" onchange="getP6DataList();" data-placeholder="Select">
                                          <option value="" ></option> 
                                          <c:forEach var="obj" items="${contractsList}">
												<option value="${obj.contract_id }">${obj.contract_id } - ${obj.contract_short_name }</option>
											</c:forEach> 
                                        </select>
                                    </div>
                                 
                                    <div class="col s12 m8 offset-m2 l8 offset-l2 m-text-center">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"  onclick="clearFilter();" 
                                        style="margin-top:20px;width: 100%;">Clear Filters</button>
                                    </div> 
                                </div>
                            </div>
                            <div class="col m12 s12">
                               <div id="container">    
<div id="body">
            
            
        
            
		<section id=""> 
		  <section id="content">
		    <div class="container">       
		      <div class="row">
		      	<div class="col-sm-12 text-center">
          			<div class="des">
	          			<h5 style="display: inline-block;"><span class="label label-default" style="background-color:#1f77b4;">Planned</span></h5>
	      				<h5 style="display: inline-block;"><span class="label label-default" style="background-color:#2ca02c;">Actual</span></h5>
	      				<h5 style="display: inline-block;"><span class="label label-default" style="background-color:#ff0000;">Variance</span></h5>
	      				<h5 style="display: inline-block;"><span class="label label-default" style="background-color:#f27935;">Weightage</span></h5>    
      				</div>
      				<div style="clear: both;"></div>
        		</div>
		        <div class="col-sm-12">        
		          <div class="card tree-body"> 
		          
		                   <form id="treeForm" name="treeForm" method="post">
		                   <div class="section-content" id="level4">
								<div class="container-fluid">
									<div class="row centered  multiple-columns "  id="level4Div">
									<%-- 	<c:forEach var="obj" items="${level4List }" varStatus="index">
											<div class="col-sm-4 col-md-4 tree-body" id="level-active1${index.count }" onClick="getNextLevelData('${obj.wbs_4_name }','3','','${index.count }')">
												<p <c:choose><c:when test="${obj.planned > obj.actual }">style="background-color:red; color:white;" 
												</c:when><c:otherwise>style="background-color:white; color:black;" </c:otherwise></c:choose> >${obj.wbs_4_name }</p>
												<p style="background: #1f77b4;">${obj.planned } %</p><p style="background: #2ca02c;">${obj.actual } %</p><p style="background-color: #f27935;">${obj.weightage }</p>
											</div>
										</c:forEach> --%> 
									</div>
								</div>
							</div>		
							
							<div class="section-content row-bg" >
								<div class="container-fluid levels" id="level3" style="display: none;">
									<div class="row centered  multiple-columns " id="level3Div">
									<%-- 	<c:forEach var="obj" items="${contractor }"> 
											<div class="col-sm-4 col-md-4 tree-body active-node">
												<p style="background-color: ${obj.backgroundColor };color:${obj.color };">${obj.levelName }</p><p style="background: #1f77b4;">${obj.plannedProgress } %</p><p style="background: #2ca02c;">${obj.actualProgress } %</p>
											</div>
										</c:forEach> --%>
									</div>
								</div>
							</div>								
							
							<div class="section-content">	
								<div class="container-fluid levels" id="level2" style="display: none;">
									<div class="row centered  multiple-columns"  id="level2Div">
									</div>
								</div>
							</div>
							
								<div class="section-content">	
								<div class="container-fluid levels" id="level1" style="display: none;">
									<div class="row centered  multiple-columns"  id="level1Div">
									</div>
								</div>
							</div>
							
								<div class="section-content">	
								<div class="container-fluid levels" id="level0" style="display: none;">
									<div class="row centered  multiple-columns"  id="level0Div">
									</div>
								</div>
							</div> 
					
							</form>
							
								
							<div id="level4"></div>	
							<div id="level5"></div>	
							<div id="level6"></div>	
							<div id="level7"></div>	
							<div id="level8"></div>	
							
					<!-- 		<div class="loading" style="display: none;">
					    		<div style="position: fixed; left: 0; top: 0; z-index: 202; background-color: rgba(0,0,0,0.5); width: 100%;Height: 100%;">
									<i class="fa fa-spinner fa-spin" style="font-size:48px;color:red; position:fixed; margin-top: -24px%;margin-left: -24px;top:50%;left: 50%;"></i>
								</div>
							</div>  -->                  
		                   
		            
		          </div>         
		        </div> 
		      </div>
		    </div>
		  </section>
		</section>


		<%-- <div class="footercontainer">
			<footer id=""> <jsp:include page="footer.jsp"></jsp:include> </footer> 
		</div>	 --%>

</div>
</div>
						<!-- update popup starts -->
						<div>
						
						</div>
						<div id="modal" class="modal">
						    <div class="modal-content">
						        <h4><div class="cw p-2 bg-m modal-title" id="modalhead"></div></h4>
						          <img class="modal-content" id="img01" src="">
						          
						      </div>
						  </div>
				</div>
</div>
</div>
</div>
</div>
</div>
</div>

	<!-- Page Loader -->
	<div class="page-loader" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
	
    <!-- footer included -->
   <%--  <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
   	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>  

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contractor_id" id="contractor_id" />
    </form>
     
    <form action="<%=request.getContextPath() %>/export-contractor" name="exportContractorForm" id="exportContractorForm" target="_blank" method="post">	
        <input type="hidden" name="contractor_id" id="exportContractor_id" />
	</form>

	 <!-- <script src="/airport/resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="/airport/resources/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <script src="/airport/resources/vendors/bower_components/Waves/dist/waves.min.js"></script>

        Placeholder for IE9
        [if IE 9 ]>
            <script src="/airport/resources/vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]

        <script src="/airport/resources/js/app.min.js"></script>
        <script type="text/javascript" src="/airport/resources/js/jquery.validate.min.js"></script>
		
		<script src="/pmis/resources/vendors/sparklines/jquery.sparkline.min.js"></script>
		<script src="/pmis/resources/vendors/bower_components/moment/min/moment.min.js"></script>
		<script src="/pmis/resources/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
		<script src="/pmis/resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
		<script src="/pmis/resources/js1/custom.js"></script>
		<script src='/pmis/resources/js1/jquery.peity.js'></script>    
		<script src='/pmis/resources/js1/holder.min.js'></script> -->

	<script>
    
    $(document).ready(function () {
        $('#contract_id').select2();
        $('.notification.dropdown-trigger').dropdown({
            coverTrigger: false,
            closeOnClick: false,
        });
        getP6DataList();
    });
  
	/* $(document).on('click.bs.dropdown.data-api', '.dropdown', function (e) { e.stopPropagation() });    
    $(".data-attributes span").peity("donut"); */
	// This function is used to get error message for all ajax calls
	function getErrorMessage(jqXHR, exception) {
		    var msg = '';
		    if (jqXHR.status === 0) {
		        msg = 'Not connect.\n Verify Network.';
		    } else if (jqXHR.status == 404) {
		        msg = 'Requested page not found. [404]';
		    } else if (jqXHR.status == 500) {
		        msg = 'Internal Server Error [500].';
		    } else if (exception === 'parsererror') {
		        msg = 'Requested JSON parse failed.';
		    } else if (exception === 'timeout') {
		        msg = 'Time out error.';
		    } else if (exception === 'abort') {
		        msg = 'Ajax request aborted.';
		    } else {
		        msg = 'Uncaught Error.\n' + jqXHR.responseText;
		    }
		    console.log(msg);
	 }

	 function getNextLevel(id,name,levelStage,key){
		
		 var stageNo = Number(levelStage)+1;
		 var myParams = {levelId : id, stageNo : stageNo};
		 
		 for(var i=0;i<100;i++){
			 $("#level-active"+levelStage+""+i).removeClass("active-node");
		 }
		 
		 $(".loading").show();
		 
		 if(Number(levelStage) <= 7){
		 
			 $.ajax({
				url:"<%=request.getContextPath()%>/getWbsActivities",
				data:myParams,
				type:"POST",
				dataType:"json",
				success:function(data){
					
					for(var i=Number(levelStage)+1;i<=8;i++){
						 $("#level"+i).html('');
					}
					
					if(data != null && data != '' && data.length > 0){
						
						$("#level-active"+levelStage+""+key).addClass("active-node");
						
						var html = "";
						
						if(stageNo%2 == 0){
							html = html + '<div class="section-content row-bg">';									
						}else{
							html = html + '<div class="section-content">';
						}
						
						html = html + '<div class="container-fluid">'
   	            		+ '<div class="row centered  multiple-columns">'
	   	            	$.each(data,function(key,val){
	   	            		var lId = "'"+val.levelId+"'";
	   	            		var lName = "'"+val.levelName+"'";
	   	            		html = html + '<div class="col-sm-4 col-md-4 tree-body"  id="level-active'+stageNo+key+'" onclick="getNextLevel('+lId+','+lName+','+stageNo+','+key+');">'
											+'<p style="background-color: '+val.backgroundColor+';color:'+val.color+'">'+val.levelName+'</p><p style="background: #1f77b4;">'+val.plannedProgress+' %</p><p style="background: #2ca02c;">'+val.actualProgress+' %</p><p style="background-color: #f27935;">'+val.weightage+'</p>'
										+'</div>'
	   	                });
   	            		
   	            		html = html +  '</div>'
						+ '</div>'
						+ '</div>'
						
						$("#level"+stageNo).html(html);
						
   	            		$(".loading").hide();
	   				}else{
	   					$(".loading").hide();
	   				}
	   				
	   			},error: function (jqXHR, exception) {
	   				$(".loading").hide();
	   	            getErrorMessage(jqXHR, exception);
	   	        }});
		 
		 }else{
			 $(".loading").hide();
		 }
		 
	  }
  
        $(".selectrow1").click(function(){
            $(".row1").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow2").click(function(){
            $(".row2").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow3").click(function(){
            $(".row3").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn1").click(function(){
            $(".column1").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn2").click(function(){
            $(".column2").prop("checked",$(this).prop("checked"));
        });
 

function clearFilter(){
	
	$("#contract_id").val("");
	$('.searchable').select2();
	window.location.href= "<%=request.getContextPath()%>/wbs-tree/${work_id}";

}


function showCancelMessage() {
	swal({
        title: "Action Requried!",
        text: "Atleast Select one Check Box to Download!",
        type: "info",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Okay"
    });
}
var colorVal = '';
var wbs_4_name = '';
var wbs_3_name = '';
var wbs_2_name = '';
function getP6DataList(){
	$(".page-loader").show();
	var contract_id = $('#contract_id').val();
	$('#level4Div').empty();
	colorVal = 260;
	var work_id = '${work_id}';
	 	var myParams = {contract_id : contract_id, work_id : work_id};
       $.ajax({
           url: "<%=request.getContextPath()%>/ajax/getWbslevelsList",
           data: myParams, cache: false,async: false,
           success: function (data) {
               if (data.length > 0) {
                   $.each(data, function (i, val) {  
                	   $('.levels').hide();
                	   var style = 'style="background-color:white; color:black;"'
                	   var actual =  parseFloat(val.actual) /val.weightage;
                	   var planned = parseFloat(val.planned) /val.weightage;
                	   if(isNaN(actual)) {
              	  		 actual = 0.00;
	              	  	}
	              	  	if(isNaN(planned)) {
	              	  		 planned = 0.00;
	              	  	}
                	   actual =   actual.toFixed(2);
                	  	planned =   planned.toFixed(2);
                	  	var level4 = "'"+val.wbs_4_name+"'";
                	  	 if(parseFloat(planned) >= parseFloat(actual)){
                    			style = 'style="background-color:red; color:white;"'
                    	   }
                        var htmlText = '<div class="col-sm-4 col-md-4 tree-body point " id="level-active33'+i+'" onClick="getNextLevelData(\'' + val.wbs_4_name + '\',\'' + 3 + '\',\'' + contract_id + '\',\'' +i + '\');setLevel4val('+level4 +','+i+')">'+
											'<p '+style+'>'+val.wbs_4_name+'</p>'+
											'<p style="background: #1f77b4;">'+planned+' %</p><p style="background: #2ca02c;">'+actual+' %</p><p style="background-color: #f27935;">'+val.weightage+'</p>'+
										'</div>';
                        $("#level4Div").append(htmlText).hide();
                        wbs_3_name = '';
                        wbs_2_name = '';
                        
                   }); 
               }else{
            	    $('.levels').hide();
            	    var htmlText = '<div class="col-sm-4 col-md-4 tree-body ">  <p style="color:black;padding-top: 0.5em">No Records Found! </p</div>'
            		$("#level4Div").append(htmlText);
               }
               $("#level4Div").show('slow');
               $('.searchable').select2();
               $(".page-loader").hide();
           },error: function (jqXHR, exception) {
   			      $(".page-loader").hide();
  	          	  getErrorMessage(jqXHR, exception);
  	     	  }
       });
	
}

var levl3 = '';
var levl2 = '';
function setLevel4val(level4,key){
	wbs_4_name = level4;
	 $(".point").removeClass("active-node");
	 $(".point").css({ boxShadow : "rgb(103 103 202 / 41%) 0px 0px 0px 0px" }); 
	 $("#level-active33"+key).addClass("active-node");
	 $("#level-active33"+key).css({ boxShadow : "rgb(103 103 202 / 41%) 4px 4px 5px 4px" }); 
	console.log(wbs_4_name );
}
function getNextLevelData(levelName,levelNo,contract,key){
	  var stageNo = Number(levelNo)+1;
	  var work_id = '${work_id}';
	  $("#level"+levelNo+"Div").empty();
	  var prevLevel = Number(levelNo)+1;
	  var nxtLvl = Number(levelNo)-1;
	  $(".point"+(stageNo+1)).removeClass("active-node");
	  $(".point"+(stageNo+1)).css({ boxShadow : "rgb(103 103 202 / 41%) 0px 0px 0px 0px" });
	  if(stageNo != 0){
		  $("#level-active"+(stageNo+1)+key).addClass("active-node");
		  $("#level-active"+(stageNo+1)+key).css({ boxShadow : "rgb(103 103 202 / 41%) 4px 4px 5px 4px" }); 
	  }
	  if(prevLevel == 4){
		  colorVal = 260;
		  var myParams = {wbs_4_name : levelName, contract_id : contract, levelNo : levelNo , work_id : work_id};
		  $('.levels').hide();
	  }else if(prevLevel == 3){
		  colorVal = 250;
		  levl3 = levelName;
		  var myParams = {wbs_3_name : levelName,wbs_4_name : wbs_4_name, contract_id : contract, levelNo : levelNo, work_id : work_id};
		  $("#level"+nxtLvl+"Div").empty();
	  }else if(prevLevel == 2){
		  colorVal = 240;
		  levl2 = levelName;
		  var myParams = {wbs_2_name : levelName,wbs_3_name : levl3,wbs_4_name : wbs_4_name, contract_id : contract, levelNo : levelNo, work_id : work_id};
		  $("#level0Div").empty();
	  }else if(prevLevel == 1){
		  colorVal = 240;
		  var myParams = {wbs_1_name : levelName,wbs_2_name : levl2,wbs_3_name : levl3,wbs_4_name : wbs_4_name, contract_id : contract, levelNo : levelNo, work_id : work_id};
	  }
	  $('#level'+levelNo).css("display", "block");
       $.ajax({
           url: "<%=request.getContextPath()%>/ajax/getWbslevelsList",
           data: myParams, cache: false,async: false,
           success: function (data) {
               if (data.length > 0) {
                   $.each(data, function (i, val) {
                	   if(levelNo == 0){
                		   var lastLvlHtml =  '<div class="col-sm-4 col-md-4 tree-body lastPoint">  <p style="background-color:red; color:white;padding-top: 0.5em">Activity Name - '+val.activity_name+'</p</div>'
                  	    	 $("#level0Div").append(lastLvlHtml).hide().show('slow');;
                  	    	 $(".lastPoint").css({ boxShadow : "rgb(103 103 202 / 41%) 4px 4px 5px 4px" });
                	   }else{
                	   var style = 'style="background-color:white; color:black;"'
                		   var levelName = '';
               		  if(levelNo == 3){
               			 
           				  levelName = val.wbs_3_name; wbs_3_name = val.wbs_3_name;
           			  }else if(levelNo == 2){
           				  levelName = val.wbs_2_name;  wbs_2_name = val.wbs_2_name;
           			  }else if(levelNo == 1){
           				  levelName = val.wbs_1_name; wbs_3_name = val.wbs_3_name; wbs_2_name = val.wbs_2_name;
           			  }
               		
               		 console.log(wbs_3_name );
                     console.log(wbs_2_name );
               		 var actual =  parseFloat(val.actual) /val.weightage;
              	     var planned = parseFloat(val.planned) /val.weightage;
              	   if(isNaN(actual)) {
          	  		 actual = 0.00;
	          	  	}
	          	  	if(isNaN(planned)) {
	          	  		 planned = 0.00;
	          	  	}
	              	actual =   actual.toFixed(2);
	           	  	planned =   planned.toFixed(2);
                   	   if(parseFloat(planned) >= parseFloat(actual)){
                   			style = 'style="background-color:red; color:white;"'
                   	   }
                        var htmlText = '<div class="col-sm-4 col-md-4 tree-body point'+stageNo+'" id="level-active'+stageNo+i+'" onClick="getNextLevelData(\'' + levelName + '\',\'' +nxtLvl+ '\',\'' + contract + '\',\'' +i + '\');">'+
											'<p '+style+'>'+levelName+'</p>'+
											'<p style="background: #1f77b4;">'+planned+' %</p><p style="background: #2ca02c;">'+actual+' %</p><p style="background-color: #f27935;">'+val.weightage+'</p>'+
										'</div>';
                        $("#level"+levelNo+"Div").append(htmlText);
                	   }   
                   }); 
                
               }else{
            	   var htmlText = '<div class="col-sm-4 col-md-4 tree-body ">  <p style="color:black;padding-top: 0.5em">No Records Found! </p</div>'
            	    	 $("#level"+levelNo+"Div").append(htmlText);
            	  
               }
               $("#level"+levelNo+"Div").hide().show('slow');;
               $('.searchable').select2();
               $(".page-loader").hide();
           },error: function (jqXHR, exception) {
   			      $(".page-loader").hide();
  	          	  getErrorMessage(jqXHR, exception);
  	     	  }
       });
       colorVal = colorVal - 10;
       $("#level"+levelNo).css({ backgroundColor : "rgb("+colorVal+","+colorVal+","+colorVal+")" });

       wbs_3_name = '';
       wbs_2_name = '';
}
</script>
</body>

</html>