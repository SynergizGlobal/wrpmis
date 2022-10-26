<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--     <title>PMIS Report - Safety Incidents</title> -->
    <title>Safety Reports - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
      <style>            
		.desk-right{
			text-align:right;
		}
        @media only screen and (max-width: 769px) {         
            .mob-center{
            	text-align:center;
            }
        }       
		.error-msg label{color:red!important;}

    #googoose-footer {
        margin-right: 1in;
        position: absolute;
        width: 100%;
        text-align: right;
    }
    #googoose-header {
        width: 100%;
        text-align: center;
    }
#hello-canvas
{
display:none;
}  	

body{
font-family:Calibri;
font-size:13px;
}	
		
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Safety Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m8 s12 offset-m2">
                            	<form action="<%=request.getContextPath() %>/generate-safety-report" id="reportForm" name="reportForm" method="post">
	                                <div class="row no-mar">
	                                	<div class="col s6 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" onchange="addInQueHOD(this.value);getSafetyReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getContractsList2InSafetyReport(this.value);getSafetyReport();">
	                                            <option value="">Select </option>
	                                          
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);resetWorksDropdowns();getSafetyReport();">
	                                            <option value="">Select </option>
	                                               <c:forEach var="obj" items="${contractsList }">
                                                    <option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }"> ${obj.contract_id_fk } - ${obj.contract_short_name }</option>
                                                </c:forEach> 
	                                        </select>
	                                        <span id="contract_id_fkError" class="error-msg" ></span>
	                                    </div>
									    <div class="col s6 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Status</p>
	                                        <select class="searchable validate-dropdown" id="status_fk" name="status_fk" onchange="addInQueStatus(this.value);getSafetyReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="contract_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                </div>
	                                
				    			<div class="row">	                                	
	                                <div class="col s7 m4 l3 input-field center-align offset-l3 offset-m2">
	                                    <button type="button" class="btn bg-m waves-effe ct waves-light t-c clear-filters" style="margin-top: 6px;min-width:160px%; font-weight: 600;" onclick="generateReport();">Generate Report</button>
	                                </div>
	                                <div class="col s5 m4 l3 input-field left-align ">
	                                    <button class="btn bg-s waves-effect waves-light t-c" type="button" style="margin-top: 6px; font-weight: 600; min-width:120px" onclick="clearFilter()">Reset</button>
	                                </div>                                
	                             </div>	
								<div class='googoose-wrapper' style="display:none;">
								    <div class='googoose' style="padding-bottom:0px;text-align:center;display:none;" id="logoDiv">
								       <img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55">
								    </div>
		                              <div class="col m8 s12 offset-m2" style="text-align:center;font-weight:bold;font-family:Calibri;">
		                             	PMIS Report - Safety Incidents
		                             </div>
		                             <div class="col m8 s12 offset-m2" style="text-align:center;font-weight:bold;font-family:Calibri;display:none;" id="ovenDiv">
		                             	LIST OF OPEN SAFETY INCIDENTS
		                             </div>
		                             <div id="appendSummaryData" style="font-family:Calibri;">
		                             
		                             </div> 
									

		                             <div id="appendSummaryClosedData" style="font-family:Calibri;">
		                             
		                             </div> 		                             
		                             
		                      </div>                                                                                       
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
 <canvas id='hello-canvas'></canvas>
               
            </div>
        </div>
    </div>
         
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
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/gh/aadel112/googoose@master/jquery.googoose.js"></script>
    
    <script>
    
	   var today = new Date();
	   var dd = today.getDate();

	   var mm = today.getMonth()+1; 
	   var yyyy = today.getFullYear();
	   if(dd<10) 
	   {
	       dd='0'+dd;
	   } 

	   if(mm<10) 
	   {
	       mm='0'+mm;
	   } 
	   today = dd+'/'+mm+'/'+yyyy;
	   
   
  	    var filtersMap = new Object();
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
        
        
        $(document).ready(function(){
        	$('.searchable').select2();
        	
       	
            var filters = window.localStorage.getItem("safetyReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'hod_user_id_fk' ){
    		        		  getHODListInSafetyReport(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksListInSafetyReport(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
    		        		  getContractsListInSafetyReport(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'status_fk'){
    		        		  getStatusListInSafetyReport(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            getSafetyReport();
        });
        function addInQueHOD(hod_user_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('hod_user_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(hod_user_id_fk) != ''){
       	    	filtersMap["hod_user_id_fk"] = hod_user_id_fk;
        	}
        }
        
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        } 
        function addInQueContract(contract_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('contract_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(contract_id_fk) != ''){
       	    	filtersMap["contract_id_fk"] = contract_id_fk;
        	}
        }
        
        function addInQueStatus(status_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('status_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(status_fk) != ''){
            	filtersMap["status_fk"] = status_fk;
          	}
        }
        function clearFilter(){
    		$('#work_id_fk').val('');
    		$('#contract_id_fk').val('');
    		$('#hod_user_id_fk').val('');
    		$('#status_fk').val('');
    		$('.searchable').select2();
    		window.localStorage.setItem("safetyReportFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/safety-report";
    	}
        
        function getSafetyReport(){
        	
        	getWorksListInSafetyReport("");
        	getContractsListInSafetyReport("");
        	getHODListInSafetyReport("");
        	getStatusListInSafetyReport("");
        	
        	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("safetyReportFilters", filters);
    			});
        	
        	
        }
        function getStatusListInSafetyReport(status) {
        	$(".page-loader").show();
        	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	if ($.trim(status_fk) == "") {
	           	$("#status_fk option:not(:first)").remove();
	           	var myParams = {contract_id_fk : contract_id_fk, hod_user_id_fk : hod_user_id_fk, work_id_fk : work_id_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getStatusListInSafetyReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                        	 var selectedFlag = (status == val.status_fk)?'selected':'';
	   	                         $("#status_fk").append('<option value="' + val.status_fk + '"'+selectedFlag+'>' + $.trim(val.status_fk) +'</option>');
	                           });
	                       }
	                       $('.searchable').select2();
	                       $(".page-loader").hide();
	                   },error: function (jqXHR, exception) {
	    	   			  $(".page-loader").hide();
	   	   	          	  getErrorMessage(jqXHR, exception);
	   	   	     	  }
	            });
        	}else{
            	  $(".page-loader").hide();
            }
        }
        function getWorksListInSafetyReport(work) {
        	$(".page-loader").show();
        	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	if ($.trim(work_id_fk) == "") {
	           	$("#work_id_fk option:not(:first)").remove();
	        	var myParams = {contract_id_fk : contract_id_fk, hod_user_id_fk : hod_user_id_fk, status_fk : status_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getWorksListInSafetyReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                           	 var workShortName = '';
	                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
	   	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
	                           });
	                       }
	                       $('.searchable').select2();
	                       $(".page-loader").hide();
	                   },error: function (jqXHR, exception) {
	    	   			  $(".page-loader").hide();
	   	   	          	  getErrorMessage(jqXHR, exception);
	   	   	     	  }
	            });
        	}else{
          	  $(".page-loader").hide();
          }
        }
        
        function getContractsList2InSafetyReport(work_id_fk){
       		$(".page-loader").show();
       		$("#contract_id_fk option:not(:first)").remove();
       		/* $("#status_fk option:not(:first)").remove();
       		$("#hod_user_id_fk option:not(:first)").remove(); */
           	var myParams = {work_id_fk : work_id_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractsListInSafetyReport",
                   data: myParams, cache: false,async: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var contractShortName = '';
                               if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
							   $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" value="' + $.trim(val.contract_id_fk) + '">' + $.trim(val.contract_id_fk) + contractShortName +'</option>');
                           });
                       }
                      
                       $('.searchable').select2(); 
                       $(".page-loader").hide();
                   },error: function (jqXHR, exception) {
    	   			  $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
   	   	     	  }
            });
        }
        function getContractsListInSafetyReport(contract){
        	$(".page-loader").show();
           
           	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	if ($.trim(contract_id_fk) == "") {
        		$("#contract_id_fk option:not(:first)").remove();
	           	var myParams = {work_id_fk : work_id_fk, hod_user_id_fk : hod_user_id_fk, status_fk : status_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getContractsListInSafetyReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                        	   var contractShortName = '';
	                               if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
	                               var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
								   $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" value="' + $.trim(val.contract_id_fk) + '"'+selectedFlag+'>' + $.trim(val.contract_id_fk) + contractShortName +'</option>');
	                           });
	                       }
	                       $('.searchable').select2();
	                       $(".page-loader").hide();
	                   },error: function (jqXHR, exception) {
	    	   			  $(".page-loader").hide();
	   	   	          	  getErrorMessage(jqXHR, exception);
	   	   	     	  }
	            });
        	}else{
            	  $(".page-loader").hide();
            }
        }
        function resetWorksDropdowns(){
        	$(".page-loader").show();        	
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){  
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       			
       		}
       		$(".page-loader").hide();
        }
        
        function getHODListInSafetyReport(hod_user_id){
        	$(".page-loader").show();
        	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	if ($.trim(hod_user_id_fk) == "") {
	           	$("#hod_user_id_fk option:not(:first)").remove();
	           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getHODListInSafetyReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                        	   var selectedFlag = (hod_user_id == val.hod_user_id_fk)?'selected':'';
	                        	   $("#hod_user_id_fk").append('<option value="' + $.trim(val.hod_user_id_fk) + '"'+selectedFlag+'>' + $.trim(val.designation) +'</option>');
	                           });
	                       }
	                       $('.searchable').select2();
	                       $(".page-loader").hide();
	                   },error: function (jqXHR, exception) {
	    	   			  $(".page-loader").hide();
	   	   	          	  getErrorMessage(jqXHR, exception);
	   	   	     	  }
	            });
        	}else{
          	  $(".page-loader").hide();
          }
        }
        
        var projectIdsArray=new Array();
        var projectWorkIdsArray=new Array();
        var workIdsArray=new Array();
        var RowsArray=new Array();
        
        
        
        var projectIdsCArray=new Array();
        var projectWorkIdsCArray=new Array();
        var workIdsCArray=new Array();
        var RowsCArray=new Array();       
  
        function generateReport() {
        	projectIdsArray=[];
        	projectWorkIdsArray=[];
        	
        	projectIdsCArray=[];
        	projectWorkIdsCArray=[];       	
        	
        	//$(".page-loader").show();
        	var work_id_fk = $('#work_id_fk').val();
        	var contract_id_fk = $('#contract_id_fk').val();
        	var hod_user_id_fk = $('#hod_user_id_fk').val();
        	var status_fk = $('#status_fk').val();
        	var mcl=0;
        	
	           	var myParams = {hod_user_id_fk:hod_user_id_fk,work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/generateSafetyReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
 	                           var html="<table style='width:100%' class='table table-striped table-bordered'>";
	                           $.each(data, function (i1, val1) {
	                        	   if(val1.status_fk=="Open")
	                        		   {
	                        		   mcl=1;
	                        		   	 $("#logoDiv").show();
	    	                    	  	 $("#ovenDiv").show();
			                        	   var hh=0;
			                       	   	   if(projectIdsArray.indexOf(val1.project_id_fk)==-1)
			                       		   {	hh=1;
					                       		html=html+"<tr><td style='text-align:left;'>"+(i1+1)+":Project Name:"+val1.project_id_fk+" - "+val1.project_name+"<span>"+today+"</span></td></tr>";
					                       		projectIdsArray.push(val1.project_id_fk);
			                       		   }
			                       	   	   	   var lengthRows=0;
				                       	   	   var klm=0;
				 	                           $.each(data, function (i, val) {
					                       	   	   if(val.project_id_fk==val1.project_id_fk && RowsArray.indexOf(val.work_id_fk)==-1 && val.status_fk=="Open")
					                       		   {
					                       	   			RowsArray.push(val.work_id_fk);
					                       	   			lengthRows++;
					                       		   }
					                       	   	   
				 	                           });
				 	                           $.each(data, function (i, val) {
					                       	   	   if(val.project_id_fk==val1.project_id_fk && workIdsArray.indexOf(val.work_id_fk)==-1 && val.status_fk=="Open")
					                       		   {
					                       	   				if(klm>0)
					                       	   				{
					                       	   					RowsArray=[];
					                       	   					lengthRows=0;
									 	                           $.each(data, function (i6, val6) {
										                       	   	   if(val6.project_id_fk==val1.project_id_fk && RowsArray.indexOf(val6.work_id_fk)==-1 && val6.status_fk=="Open" && val.work_id_fk==val6.work_id_fk)
										                       		   {
										                       	   			RowsArray.push(val6.work_id_fk);
										                       	   			lengthRows++;
										                       		   }
										                       	   	   
									 	                           });					                       	   					
					                       	   				}
					                       	   			workIdsArray.push(val.work_id_fk);
							                       		html=html+"<tr><td style='text-align:left;'>"+(i1+1)+"."+(klm+1)+".Work Name:"+val.work_id_fk+" - "+val.work_short_name+"</td></tr>";
							                       		html=html+"<tr><td style='text-align:left;'>No. of Open Safety Incidents:"+lengthRows+"</td></tr>";
							                       		html=html+"<tr><td style='text-align:left;'>";
								                       		html=html+"<table style='width:100%' class='table table-striped table-bordered'>";
								                       			html=html+"<tr style='background-color:#ecf2ff;'>";
								                       	   			html=html+"<td style='width:5%;'>S No</td>";
								                       	   			html=html+"<td style='width:10%;'>Safety ID</td>";
								                       	   			html=html+"<td style='width:15%'>Name of Contract</td>";
								                       	   			html=html+"<td style='width:10%'>Contractor</td>";
								                       				html=html+"<td style='width:10%'>HOD</td>";
								                       				html=html+"<td style='width:10%'>Date / Location</td>";
								                       				html=html+"<td style='width:10%'>Description</td>";
								                       				html=html+"<td style='width:10%'>Impact / Category</td>";
								                       				html=html+"<td style='width:10%'>Root Cause</td>";
								                       				html=html+"<td style='width:10%'>Committee (Y/N)</td>";
								                       				html=html+"<td style='width:10%'>Incident Status</td>";
								                       				html=html+"<td style='width:10%'>Short Term Corrective Measure</td>";
								                       				html=html+"<td style='width:10%'>Long Term Corrective Measure</td>";
								                       			html=html+"</tr>";
								                       			var fgyu=0;
								                       			$.each(data, function (i2, val2) {
								                       					if(val2.project_id_fk==val.project_id_fk && val2.work_id_fk==val.work_id_fk && val2.status_fk=="Open")
								                       					{
								                       						var conName=""; 
								                       						if(val2.contractor_name!=null)
								                       							{
								                       								conName=val2.contractor_name;
								                       							}								                       						
											                       			html=html+"<tr style='background-color:#ffffff;'>";
											                       				html=html+"<td>"+(fgyu+1)+"</td>";
											                       				html=html+"<td>"+val2.safety_seq_id+"</td>";
											                       				html=html+"<td>"+val2.contract_id_fk+"/ \n "+val2.contract_short_name+"</td>";
											                       				html=html+"<td>"+conName+"</td>";
											                       				html=html+"<td>"+val2.designation+"</td>";
											                       				html=html+"<td>"+val2.date+"\n "+val2.location+"</td>";
											                       				html=html+"<td>"+val2.description+"</td>";
											                       				html=html+"<td>"+val2.impact_fk+"/ "+val2.category_fk+"</td>";
											                       				html=html+"<td>"+val2.root_cause_fk+"</td>";
											                       				html=html+"<td>"+val2.committee_required_fk+"</td>";
											                       				html=html+"<td>"+val2.status_fk+"</td>";
											                       				html=html+"<td>"+val2.corrective_measure_short_term+"</td>";
											                       				var lttext="";
											                       					if(val2.corrective_measure_long_term!=null)
											                       					{
											                       						lttext=val2.corrective_measure_long_term;
											                       					}
											                       				html=html+"<td>"+lttext+"</td>";
										                       				html=html+"</tr>";
										                       				fgyu++;
								                       					}
								                       			});
								                       		html=html+"</table>";
							                       		html=html+"</td></tr>";
							                       		klm++;
					                       		   }
					                           }); 	
				 	                          lengthRows=0;
			                       	   	 
	                        		   }
		                       });
	                           html=html+"</table>";
	                           $("#appendSummaryData").html(html);
	                           if(status_fk!="Open")
	                           {
	                           var htmlC="";
                           	   if(mcl>0)
                        	   {
                        	    	htmlC=htmlC+"<div class='googoose break'></div><div style='text-align:center;'><img src='/pmis/resources/images/mrvclogo.png' alt='Logo' width='70' height='55'></div><br><br>";						    
                        	   }
                           	   htmlC=htmlC+'<div class="col m8 s12 offset-m2" style="text-align:center;font-weight:bold;font-family:Calibri;" id="closedDiv">LIST OF CLOSED SAFETY INCIDENTS</div>';
	                           htmlC=htmlC+"<table style='width:100%' class='table table-striped table-bordered'>";
                        	   if(data.length>0)
                        	   {
	                           $.each(data, function (i1, val1) {

	                        	   if(val1.status_fk=="Closed")
	                        		   {
		                        		   $("#logoDiv").show();
			                        	   var hh=0;
			                       	   	   if(projectIdsCArray.indexOf(val1.project_id_fk)==-1)
			                       		   {	hh=1;
			                       				htmlC=htmlC+"<tr><td style='text-align:left;'>"+(i1+1)+":Project Name:"+val1.project_id_fk+" - "+val1.project_name+"<span>"+today+"</span></td></tr>";
					                       		projectIdsCArray.push(val1.project_id_fk);
			                       		   }

			                       	   	var lengthRows=0;
			                       	   	   var klm=0;
			 	                           $.each(data, function (i, val) {
				                       	   	   if(val.project_id_fk==val1.project_id_fk && RowsArray.indexOf(val.work_id_fk)==-1 && val.status_fk=="Closed")
				                       		   {
				                       	   			RowsArray.push(val.work_id_fk);
				                       	   			lengthRows++;
				                       		   }
				                       	   	   
			 	                           });
			 	                           $.each(data, function (i, val) {
				                       	   	   if(val.project_id_fk==val1.project_id_fk && workIdsCArray.indexOf(val.work_id_fk)==-1 && val.status_fk=="Closed")
				                       		   {
				                       	   				if(klm>0)
				                       	   				{
				                       	   					RowsArray=[];
				                       	   					lengthRows=0;
								 	                           $.each(data, function (i6, val6) {
									                       	   	   if(val6.project_id_fk==val1.project_id_fk && RowsArray.indexOf(val6.work_id_fk)==-1 && val6.status_fk=="Closed" && val.work_id_fk==val6.work_id_fk)
									                       		   {
									                       	   			RowsArray.push(val6.work_id_fk);
									                       	   			lengthRows++;
									                       		   }
									                       	   	   
								 	                           });					                       	   					
				                       	   				}
					                       	   			workIdsCArray.push(val.work_id_fk);
					                       	   			htmlC=htmlC+"<tr><td style='text-align:left;'>"+(i1+1)+"."+(klm+1)+".Work Name:"+val.work_id_fk+" - "+val.work_short_name+"</td></tr>";
					                       	   			htmlC=htmlC+"<tr><td style='text-align:left;'>No. of Open Safety Incidents:"+lengthRows+"</td></tr>";
					                       	   			htmlC=htmlC+"<tr><td style='text-align:left;'>";
					                       	   			htmlC=htmlC+"<table style='width:100%' class='table table-striped table-bordered'>";
					                       	   			htmlC=htmlC+"<tr style='background-color:#ecf2ff;'>";
					                       	   			htmlC=htmlC+"<td style='width:5%;'>S No</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Safety ID</td>";
					                       	   			htmlC=htmlC+"<td style='width:15%;'>Name of Contract</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Contractor</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>HOD</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Date / Location</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Description</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Impact / Category</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Root Cause</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Committee (Y/N)</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Incident Status</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Short Term Corrective Measure</td>";
					                       	   			htmlC=htmlC+"<td style='width:10%;'>Long Term Corrective Measure</td>";
					                       	   			htmlC=htmlC+"</tr>";
								                       			var fgyu=0;
								                       			$.each(data, function (i2, val2) {
								                       					if(val2.project_id_fk==val.project_id_fk && val2.work_id_fk==val.work_id_fk && val2.status_fk=="Closed")
								                       					{
								                       						var conName=""; 
								                       						if(val2.contractor_name!=null)
								                       							{
								                       								conName=val2.contractor_name;
								                       							}
								                       						
								                       							htmlC=htmlC+"<tr style='background-color:#ffffff;'>";
								                       							htmlC=htmlC+"<td>"+(fgyu+1)+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.safety_seq_id+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.contract_id_fk+"/ \n "+val2.contract_short_name+"</td>";
								                       							htmlC=htmlC+"<td>"+conName+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.designation+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.date+"\n "+val2.location+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.description+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.impact_fk+"/ "+val2.category_fk+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.root_cause_fk+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.committee_required_fk+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.status_fk+"</td>";
								                       							htmlC=htmlC+"<td>"+val2.corrective_measure_short_term+"</td>";
											                       				var lttext="";
										                       					if(val2.corrective_measure_long_term!=null)
										                       					{
										                       						lttext=val2.corrective_measure_long_term;
										                       					}
										                       					htmlC=htmlC+"<td>"+lttext+"</td>";
								                       							htmlC=htmlC+"</tr>";
										                       				fgyu++;
								                       					}
								                       			});
								                       			htmlC=htmlC+"</table>";
								                       			htmlC=htmlC+"</td></tr>";
							                       		klm++;
					                       		   }
					                           }); 	
			 	                          lengthRows=0;
	                        		   }
	                        	   
		                       });
                        	   }
	                           htmlC=htmlC+"</table>";
	                           $("#appendSummaryClosedData").html(htmlC);
	                           }

 	                   		$(".googoose-wrapper").show();
	                		$(".page-loader-2").show();

	                    	 var canvas = document.getElementById("hello-canvas");
	                    	    var ctx = canvas.getContext("2d");
	                    	    function r(ctx, x, y, w, h, c) {
	                    	      ctx.beginPath();
	                    	      ctx.rect(x, y, w, h);
	                    	      ctx.strokeStyle = c;
	                    	      ctx.stroke();
	                    	    }
	                    	    r(ctx, 0, 0, 32, 32, "black");
	                    	    r(ctx, 4, 4, 16, 16, "red");
	                    	    r(ctx, 8, 8, 16, 16, "green");
	                    	    r(ctx, 12, 12, 16, 16, "blue");


	                    	    var o = {
	                    	        download: 0,
	                    	        filename: 'SafetyReport.doc',
	                    	    	  	  margins: '0.25in',
	                    	    	  	  size: '11in 11.0in',
	                    	    	  	headermargin: '.4in',
	                    	    };
	                    	    $(document).googoose(o);    
	                    	    $(".page-loader-2").hide();
	                    	    $(".googoose-wrapper").hide(); 
	                    	    window.location.reload();
	                           
	                       }
	                       $('.searchable').select2();
	                       $(".page-loader").hide();
	                   },error: function (jqXHR, exception) {
	    	   			  $(".page-loader").hide();
	   	   	          	  getErrorMessage(jqXHR, exception);
	   	   	     	  }
	            });
        	
		}
        
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "work_id_fk": {
	  			 		required: false
	  			 	  },"contract_id_fk": {
	  			 		required: false
	  			 	  },"hod_user_id_fk": {
	  			 		required: false
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "work_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"contract_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"hod_user_id_fk": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "work_id_fk" ){
						 document.getElementById("work_id_fkError").innerHTML="";
				 		 error.appendTo('#work_id_fkError');
					} else if(element.attr("id") == "contract_id_fk" ){
						   document.getElementById("contract_id_fkError").innerHTML="";
					 	   error.appendTo('#contract_id_fkError');
					} else if(element.attr("id") == "hod_user_id_fk" ){
						   document.getElementById("hod_user_id_fkError").innerHTML="";
					 	   error.appendTo('#hod_user_id_fkError');
					} else{
	 					error.insertAfter(element);
			        }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        

    </script>

</body>

</html>