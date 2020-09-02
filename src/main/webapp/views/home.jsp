<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
   <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
   
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/mrvc/resources/css/font-awesome-v.4.7.css">

   
    <link rel="stylesheet" href="/mrvc/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css">
    
   
   
    <style>
        .input-field .select2-container--default {
            width: 100% !important;
        }

        /* searchable dropdown styling */
        .select2-container--default .select2-selection--single {
            background-color: lightgrey;
            border: 1px solid #aaa;
            height: 32px;
            /* border-radius: 2px; */
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        /* datatable styling starts here*/
        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        /* datatable stylings ends here*/
        
        
          h4 {
		    font-size: 2.28rem;
		    line-height: 110%;
		    margin: 0 0 .912rem 0;
		}
		
		.card .card-content {
		    padding: 5px 24px;
		    border-radius: 0 0 2px 2px;
		}
.no-mar{
	margin-bottom:0;
}
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/newHeader.jsp"></jsp:include>

  <div class="row no-mar">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content" style="height: 660px;">
                    <span class="card-title ">
                        <div class="center-align p-2">
                            <h4> Welcome to MRVC PMIS </h4>
                            <img src="/mrvc/resources/images/mrvclogo.png" alt="lgo" style="width: 10%;">
                        </div>
                    </span>

                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row" style="display:none">

                                <!-- project ,work , module dropdowns from database starts -->
                                <div class="col s12 m4 input-field">
                                    <select class="searchable"  id="projectId" name="projectId" onchange="getWorksListByProject(this.value);" >
                                        <option value="" selected>Select Project</option>
                                        <c:forEach var="obj" items="${projects}">
									   		<option value="${obj.projectId}" <c:if test="${sessionScope.globalProjectId eq obj.projectId}">selected</c:if>>${obj.projectId} ${obj.projectName}</option>
									    </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable" id="workId" name="workId" onchange="setGlobalVariableFromHome(this.value)" >
                                        <option value="" selected>Select Work</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable" id="moduleId" name="moduleId" onchange="getWorkModuleStatus('',this.value);" >
                                        <option value="" selected>Select Module</option>
                                         <c:forEach var="obj" items="${modules}">
									   		<option value="${obj.moduleId}">${obj.moduleName}</option>
									    </c:forEach>
                                    </select>
                                </div>
                       <!-- project ,work , module dropdowns from database ends -->
                                
                            </div>

                             <div class="row" style="margin: 0px;display:none">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
                                    <div class="center-align ">
                                      <!-- selected status shows here -->
                                        <h5> Status </h5>
                                        <p id="workModuleStatus"></p>
                                    </div>
                                    <div style="margin-bottom: 200px;"> </div>
                                </div>
                            </div>
                        </form>
                        
                            <div class="row" style="margin: 0px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
                                    <div class="center-align ">
                                      <!-- selected status shows here -->
                                        <h5> User Details </h5>
                                        <table class="table-striped">
                                        <tr>
                                         <th>ID</th>
                                         <th>Name</th>
                                         <th>Email</th>
                                        </tr>
                                        <c:forEach var="user" items="${userDetailsList}">
                                        <tr>
                                        <td>${user.userId}</td>
                                        <td>${user.userName}</td>
                                        <td>${user.emailId}</td>
                                        
                                        
                                        </tr>
                                        </c:forEach>
                                        
                                         
                                         
                                         
                                        </table>
                                    </div>
                                    <div style="margin-bottom: 200px;"> </div>
                                </div>
                            </div>
                        
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    


  <!-- footer included -->
  <jsp:include page="./layout/newFooter.jsp"></jsp:include>
    		
  <script src="/mrvc/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/mrvc/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/mrvc/resources/js/select2.min.js"></script>
  <script src="/mrvc/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/mrvc/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).ready(function () {
            //$('.select').formSelect();
            //searchable dropdown intialization
            $('#projectId').select2();
            $('#workId').select2();
            $('#moduleId').select2();
                      
            var projectId = "${sessionScope.globalProjectId}";
            
            if($.trim(projectId) != ''){
            	getWorksListByProject(projectId);
            }
            
        });
        
        //get projects based on work selected from database
        function getWorksListByProject(projectId){
    		$("#workId option:not(:first)").remove();
    		if($.trim(projectId) != ""){
    			var myParams = {projectId : projectId}; 
    			$.ajax({
    				url:"<%=request.getContextPath()%>/ajax/getWorksListByProject",
    				data:myParams,cache: false,
    				success:function(data){
    					if(data.length > 0){
    						 $.each(data, function(i,val) {
    				            var globalWorkId = "${sessionScope.globalWorkId}";
    							if($.trim(globalWorkId) != '' && val.workId == $.trim(globalWorkId)){
    								$("#workId").append('<option value="'+val.workId+'" selected>'+val.workId+' - '+val.workName+'</option>');
    							}else{
    								$("#workId").append('<option value="'+val.workId+'">'+val.workId+' - '+val.workName+'</option>');
    							}
    				         });		
    		 			}	
    					$("#workId").select2();
    				}	 
    			});
    		}else{
    			$("#workId").select2();
    		}
    	}
    	//getting global variables setting from database
        function setGlobalVariableFromHome(workId){
        	var projectId = $("#projectId").val();
    		var workName = $("#workId option:selected").text();;
    		$("#globalProjectId").val(projectId);
    		$("#globalWorkId").val(workId);
    		$("#globalWorkName").val(workName);
    		$("#setGlobalVariablesForm").submit();
        }
        
    	//getting work & module from dropdowns
       function getWorkModuleStatus(workid,moduleId){
        	var workModuleStatus = "";
        	var workId = "";
        	var moduleId = "";
        	if($.trim(workId) == "" ){
        		workId = $("#workId").val();
        	}
        	if($.trim(moduleId) == "" ){
        		moduleId = $("#moduleId").val();
        	}
    		if($.trim(workId) != "" && $.trim(moduleId) != ''){
    			var myParams = {moduleId : moduleId,workId:workId}; 
    			$.ajax({
    				url:"<%=request.getContextPath()%>/ajax/getWorkModuleStatus",
    				data:myParams,cache: false,
    				success:function(data){
    					workModuleStatus = "";
    					if($.trim(data) != ''){
    						workModuleStatus = data.workModuleStatus;	
    		 			} else {
    		 				workModuleStatus = "";
    		 			}
						$("#workModuleStatus").html(workModuleStatus); 
    				}	 
    			});
    		}else{
    			$("#workModuleStatus").html(workModuleStatus); 
    		}
    	} 
        
        
    </script>
</body>

</html>