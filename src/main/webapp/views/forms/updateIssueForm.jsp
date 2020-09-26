<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Issues</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/issues.css">
	
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
        .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}		
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		.error-msg label{color:red!important;}
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

 <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6> Update Issues</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="<%=request.getContextPath() %>/update-issue" id="issueForm" name="issueForm" method="post" enctype="multipart/form-data">
                        	<input id="issue_id" name="issue_id" type="hidden" value="${issue.issue_id }">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" <c:if test="${issue.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>
                                    <label> Project ID </label>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" selected>Select</option>
                                    </select>
                                    <label> Work ID </label>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <label>Contract ID</label>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="activity_id_fk" name="activity_id_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${activityList }">
                                            <option value="${obj.activity_id_fk }" <c:if test="${issue.activity_id_fk eq obj.activity_id_fk}">selected</c:if>>${obj.activity_name}</option>
                                        </c:forEach>
                                    </select>
                                    <label> Activity ID </label>
                                    <span id="activity_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <!-- <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea> -->
                                    <label for="">Issue ID : <input id="issue_id" name="issue_id" type="text" value="${issue.issue_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                            <option value="${obj.department_fk }" <c:if test="${issue.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_fk}</option>
                                        </c:forEach>
                                    </select>
                                    <label>Department </label>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesCategoryList }">
                                            <option value="${obj.category }" <c:if test="${issue.category_fk eq obj.category}">selected</c:if>>${obj.category}</option>
                                        </c:forEach>
                                    </select>
                                    <label>Issue Category </label>
                                    <span id="category_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                   <select class="searchable validate-dropdown" id="priority_fk" name="priority_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesPriorityList }">
                                            <option value="${obj.priority }" <c:if test="${issue.priority_fk eq obj.priority}">selected</c:if>>${obj.priority}</option>
                                        </c:forEach>
                                    </select>
                                    <label>Issue Priority </label>
                                    <span id="priority_fkError" class="error-msg" ></span>

                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesStatusList }">
                                            <option value="${obj.status }" <c:if test="${issue.status_fk eq obj.status}">selected</c:if>>${obj.status}</option>
                                        </c:forEach>
                                    </select>                                    
                                    <label>Issue Status </label>
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="title" name="title" type="text" class="validate" value="${issue.title }">
                                    <label for="title">Title</label>
                                    <span id="titleError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="description" name="description" type="text" class="validate" value="${issue.description }">
                                    <label for="description">Description </label>
                                    <span id="descriptionError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker" value="${issue.date }">
                                    <label for="date"> Date</label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" name="location" type="text" class="validate" value="${issue.location }">
                                    <label for="location">Location </label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${issue.latitude }">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${issue.longitude }">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="validate" value="${issue.reported_by }">
                                    <label for="reported_by">Reported By </label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="responsible_person" name="responsible_person" type="text" class="validate" value="${issue.responsible_person }">
                                    <label for="responsible_person">Responsible Person </label>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="zonal_rly" name="zonal_rly" type="text" class="validate" value="${issue.zonal_rly }">
                                    <label for="zonal_rly"> Zonal Rly</label>
                                    <span id="zonal_rlyError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="corrective_measure" name="corrective_measure" class="materialize-textarea" data-length="1000">${issue.corrective_measure }</textarea>
                                    <label for="corrective_measure">Corrective Measure</label>
                                    <span id="corrective_measureError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="resolved_date" name="resolved_date" type="text" class="validate datepicker" value="${issue.resolved_date }">
                                    <label for="resolved_date"> Resolved Date</label>
                                    <button type="button" id="resolved_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="resolved_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="escalated_to" name="escalated_to" type="text" class="validate" value="${issue.escalated_to }">
                                    <label for="escalated_to">Escalated To </label>
                                    <span id="escalated_toError" class="error-msg" ></span>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${issue.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="issueFile" name="issueFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row no-mar">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="updateIssue();" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">Update
                                        </button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/issues" class="btn waves-effect waves-light bg-s black-text"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
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
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script>
        $(document).ready(function () {
            $('select').formSelect();
            $('#corrective_measure').characterCounter();
            $('#remarks').characterCounter();
            
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            
            $('#date').datepicker({                   
	   	    	maxDate: new Date(),
	   	    	format:'dd-mm-yyyy',
	   	    	//perform click event on done button
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
   	        });
            
            $('#resolved_date_icon').click(function () {
                event.stopPropagation();
                $('#resolved_date').click();
            });
            
            $('#resolved_date').datepicker({                   
  	    	    maxDate: new Date(),
  	    	    format:'dd-mm-yyyy',
  	    	    //perform click event on done button
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            var project_id_fk = "${issue.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${issue.work_id_fk}";
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            }
            
        });
        
      //geting works list from database    
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var work_id_fk = "${issue.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('select').formSelect();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }

        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${issue.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                }
                            });
                        }
                        $('select').formSelect();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function updateIssue(){
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
    			document.getElementById("issueForm").submit();			
    	 	}
    	}
    	
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#issueForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"activity_id_fk": {
    				 		required: true
    				 	  },"department_fk": {
    				 		required: true
    				 	  },"category_fk": {
    				 		required: true
    				 	  },"priority_fk": {
    				 		required: true
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"title": {
    				 		required: true
    				 	  },"description": {
    			 		    required: true
    			 	   	  },"date": {
    				 		required: true
    				 	  },"location": {
    				 		required: true
    				 	  },"latitude": {
    				 		required: true
    				 	  },"longitude": {
    				 		required: true
    				 	  },"reported_by": {
    				 		required: true
    				 	  },"responsible_person":{
    				 		 required: true
    				 	  },"corrective_measure": {
    			 		    required: true,
    			 	   	  },"resolved_date": {
    				 		required: true
    				 	  },"escalated_to": {
    			 		    required: true
    			 	   	  },"zonal_rly": {
    				 		required: true
    				 	  },"remarks":{
    				 		 required: true
    				 	  }
    				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 },"activity_id_fk": {
    			 			required: 'Required'
    			 	  	 },"department_fk": {
    			 			required: 'Required'
    			 	  	 },"category_fk": {
    			 			required: 'Required'
    			 	  	 },"priority_fk": {
    			 			required: 'Required'
    			 	  	 },"status_fk": {
    			 			required: 'Required'
    			 	  	 },"title": {
    			 			required: 'Required'
    			 	  	 },"description": {
    			 			required: 'Required'
    			 	  	 },"date": {
    			 			required: 'Required'
    			 	  	 },"location": {
     				 		required: 'Required'
	   				 	  },"latitude": {
	   				 		required: 'Required'
	   				 	  },"longitude": {
	   				 		required: 'Required'
	   				 	  },"reported_by": {
	   				 		required: 'Required'
	   				 	  },"responsible_person":{
	   				 		 required: 'Required'
	   				 	  },"corrective_measure": {
	   			 		    required: 'Required',
	   			 	   	  },"resolved_date": {
	   				 		required: 'Required'
	   				 	  },"escalated_to": {
	   			 		    required: 'Required'
	   			 	   	  },"zonal_rly": {
	   				 		required: 'Required'
	   				 	  },"remarks":{
    			 	  		required: 'Required'
    				 	  }
    			 				      
    		    },
    			  errorPlacement:
    			 	function(error, element){
	    				if (element.attr("id") == "project_id_fk" ){
	 			 		     document.getElementById("project_id_fkError").innerHTML="";
	 			 			 error.appendTo('#project_id_fkError');
	 			 	    }else if (element.attr("id") == "work_id_fk" ){
    			 		     document.getElementById("work_id_fkError").innerHTML="";
    			 			 error.appendTo('#work_id_fkError');
    			 	    }else if (element.attr("id") == "contract_id_fk" ){
    			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#contract_id_fkError');
    			 	    }else if (element.attr("id") == "activity_id_fk" ){
    			 		     document.getElementById("activity_id_fkError").innerHTML="";
    			 			 error.appendTo('#activity_id_fkError');
    			 	    }else if (element.attr("id") == "department_fk" ){
    			 		     document.getElementById("department_fkError").innerHTML="";
    			 			 error.appendTo('#department_fkError');
    			 	    }else if (element.attr("id") == "category_fk" ){
    			 		     document.getElementById("category_fkError").innerHTML="";
    			 			 error.appendTo('#category_fkError');
    			 	    }else if (element.attr("id") == "priority_fk" ){
    			 		     document.getElementById("priority_fkError").innerHTML="";
    			 			 error.appendTo('#priority_fkError');
    			 	    }else if (element.attr("id") == "status_fk" ){
    			 		     document.getElementById("status_fkError").innerHTML="";
    			 			 error.appendTo('#status_fkError');
    			 	    }else if (element.attr("id") == "title" ){
    			 		     document.getElementById("titleError").innerHTML="";
    			 			 error.appendTo('#titleError');
    			 	    }else if (element.attr("name") == "description" ){
    			 		     document.getElementById("descriptionError").innerHTML="";
    			 			 error.appendTo('#descriptionError');
    			 	    }else if (element.attr("id") == "date" ){
    			 		     document.getElementById("dateError").innerHTML="";
    			 			 error.appendTo('#dateError');
    			 	    }else if (element.attr("id") == "location" ){
  			 	    	     document.getElementById("locationError").innerHTML="";
  			 			     error.appendTo('#locationError');
	  			 	    }else if (element.attr("id") == "latitude" ){
	  			 		     document.getElementById("latitudeError").innerHTML="";
	  			 			 error.appendTo('#latitudeError');
	  			 	    }else if (element.attr("id") == "longitude" ){
	  			 		     document.getElementById("longitudeError").innerHTML="";
	  			 			 error.appendTo('#longitudeError');
	  			 	    }else if (element.attr("id") == "reported_by" ){
	  			 		     document.getElementById("reported_byError").innerHTML="";
	  			 			 error.appendTo('#reported_byError');
	  			 	    }else if (element.attr("id") == "responsible_person" ){
	  			 		     document.getElementById("responsible_personError").innerHTML="";
	  			 			 error.appendTo('#responsible_personError');
	  			 	    }else if (element.attr("id") == "corrective_measure" ){
	  			 		     document.getElementById("corrective_measureError").innerHTML="";
	  			 			 error.appendTo('#corrective_measureError');
	  			 	    }else if (element.attr("id") == "resolved_date" ){
	  			 		     document.getElementById("resolved_dateError").innerHTML="";
	  			 			 error.appendTo('#resolved_dateError');
	  			 	    }else if (element.attr("name") == "escalated_to" ){
	  			 		     document.getElementById("escalated_toError").innerHTML="";
	  			 			 error.appendTo('#escalated_toError');
	  			 	    }else if (element.attr("id") == "zonal_rly" ){
	  			 		     document.getElementById("zonal_rlyError").innerHTML="";
	  			 			 error.appendTo('#zonal_rlyError');
	  			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }
    			 },submitHandler: function(form) {
    			    // do other things for a valid form
    			    //form.submit();
    			    //return true;
    			  }
    		});
    	    $.validator.addMethod("dateFormat",
        	    function(value, element) {
        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
        	    	//return dtRegex.test(value);
        	    },
        	    //"Date format (Aug 02,2020)"
        	    "Date format (DD-MM-YYYY)"
        	);
            
            
            $('select').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	});
            
            $('input').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	});
            
    </script>
</body>
</html>