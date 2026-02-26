<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design and Drawing Report - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" >
      <style>   
		.error-msg label{color:red!important;}
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
                            <h6>Design and Drawing Report</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m12 s12 offset-m2">
                            	<form action="<%=request.getContextPath() %>/generate-design-drawing-report" id="reportForm" name="reportForm" method="post">
	                                <div class="row no-mar">
	                                    
	
	                                    <div class="col s12 m4 input-field mob-center">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;" onclick="generateReport()">Generate
	                                            Report</button>
	                                    </div>
	                                </div>
                                
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
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

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
    var filtersMap = new Object();
    var workid = "";
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
        	getHodListInDesignReport('');
        	   var filters = window.localStorage.getItem("designReportFilters");
               
               if($.trim(filters) != '' && $.trim(filters) != null){
             	  var temp = filters.split('^'); 
             	  for(var i=0;i< temp.length;i++){
       	        	  if($.trim(temp[i]) != '' ){
       	        		  var temp2 = temp[i].split('=');
       		        	  if($.trim(temp2[0]) == 'hod' ){
       		        		getHodListInDesignReport(temp2[1]);
       		        	  }
       	        	  }
       	          }
                 }
               getDesignReport();
              
        });
        
        function addInQueHOD(hod){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('hod')) delete filtersMap[key];
       		});
        	if($.trim(hod) != ''){
       	    	filtersMap["hod"] = hod;
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
        
        function getDesignReport(){
        	//getHodListInDesignReport('');
        	 workid = $('#work_id_fk').val()
        	var work_id_fk = $('#work_id_fk').val();
        	var hod = $('#hod').val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("designReportFilters", filters);
    			});
        	
        }
 
      
        function getHodListInDesignReport(hodval){
        	$(".page-loader").show(); 
        	var work_id_fk = $('#work_id_fk').val();
        	var hod = $('#hod').val();
        	if( workid != work_id_fk){
        		hod = "";
        	}
        	if ($.trim(hod) == "" ) {
	           	$("#hod option:not(:first)").remove();
	           	var myParams = {work_id_fk : work_id_fk}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getHodListInDesignReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                        	   var selectedFlag = (hodval == val.hod)?'selected':'';
	                        	   $("#hod").append('<option value="' + $.trim(val.hod) + '"'+selectedFlag+'>' + $.trim(val.hod) +'</option>');
	                        	   i = data.length+1;
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
        
        function generateReport() {
        	//$(".page-loader").show();
        	$("#reportForm").submit();
		}
        
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "work_id_fk": {
	  			 		required: false
	  			 	  },"hod": {
	  			 		required: false
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "work_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"hod": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "work_id_fk" ){
						 document.getElementById("work_id_fkError").innerHTML="";
				 		 error.appendTo('#work_id_fkError');
					} else if(element.attr("id") == "hod" ){
						   document.getElementById("hodError").innerHTML="";
					 	   error.appendTo('#hodError');
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