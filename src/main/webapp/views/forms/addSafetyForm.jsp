<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Safety</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/mrvc/resources/css/select2.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/safety.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	
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
                                <h6>Add Safety</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/add-safety" id="safetyForm" name="safetyForm" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Project </label></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" >${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Work </label></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Contract </label></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Department </label></p>
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                            <option value="${obj.department_fk }" >${obj.department_fk}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Category </label></p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyCategoryList }">
                                            <option value="${obj.category }" >${obj.category}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="category_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Impact </label></p>
                                    <select class="searchable validate-dropdown" id="impact_fk" name="impact_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyImpactList }">
                                            <option value="${obj.impact }" >${obj.impact}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="impact_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Root Cause </label></p>
                                    <select class="searchable validate-dropdown" id="root_cause_fk" name="root_cause_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyRootCauseList }">
                                            <option value="${obj.root_cause }" >${obj.root_cause}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="root_cause_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Status </label></p>
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyStatusList }">
                                            <option value="${obj.status }" >${obj.status}</option>
                                        </c:forEach>
                                    </select>                                    
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Committee formed </label></p>
                                    <select id="committee_formed_fk" name="committee_formed_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
                                    <span id="committee_formed_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field ">
                                    <div class="row">
                                        <div class="col s12 m4 input-field ">
                                            <input id="title" name="title" type="text" class="validate">
		                                    <label for="title">Title</label>
		                                    <span id="titleError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m8 input-field">
                                            <input id="description" name="description" type="text" class="validate">
		                                    <label for="description">Description </label>
		                                    <span id="descriptionError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker">
                                    <label for="date"> Date</label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" name="location" type="text" class="validate">
                                    <label for="location">Location </label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="validate">
                                    <label for="reported_by">Reported By </label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="responsible_person" name="responsible_person" type="text" class="validate">
                                    <label for="responsible_person">Responsible Person </label>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="closure_date" name="closure_date" type="text" class="validate datepicker">
                                    <label for="closure_date">Closure Date</label>
                                    <button type="button" id="closure_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="closure_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="lti_hours" name="lti_hours" type="text" class="validate">
                                    <label for="lti_hours">LTI Hours</label>
                                    <span id="lti_hoursError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="equipment_impact" name="equipment_impact" type="text" class="validate">
                                    <label for="equipment_impact"> Equipment Impact </label>
                                    <span id="equipment_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="people_impact" name="people_impact" type="text" class="validate">
                                    <label for="people_impact">People Impact</label>
                                    <span id="people_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="work_impact" name="work_impact" type="text" class="validate">
                                    <label for="work_impact"> Work Impact </label>
                                    <span id="work_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="compensation" name="compensation" type="text" class="validate">
                                    <label for="compensation"> Compensation </label>
                                    <span id="compensationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="investigation_completed" name="investigation_completed" type="text" class="validate datepicker">
                                    <label for="investigation_completed">Investigation Completion Date</label>
                                    <button type="button" id="investigation_completed_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="investigation_completedError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="payment_date" name="payment_date" type="text" class="validate datepicker">
                                    <label for="payment_date">Payment Date</label>
                                    <button type="button" id="payment_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="payment_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <input id="corrective_measure_short_term" name="corrective_measure_short_term" type="text" class="validate">
                                    <label for="corrective_measure_short_term">Corrective Measure (Short Term) </label>
                                    <span id="corrective_measure_short_termError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="corrective_measure_long_term" name="corrective_measure_long_term" type="text" class="validate">
                                    <label for="corrective_measure_long_term">Corrective Measure (Long Term) </label>
                                    <span id="corrective_measure_long_termError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>                          

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="safetyFile" name="safetyFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="addSafety()" style="width: 100%;"
                                            class="btn waves-effect waves-light bg-m black-text">Add </button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="reset" class="btn waves-effect waves-light bg-s black-text"
                                            style="width:100%">Cancel</button>
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
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script>
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
            
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            
            $('#date').datepicker({                   
	   	    	
	   	    	format:'dd-mm-yyyy',
	   	    	//perform click event on done button
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
   	        });
            
            $('#closure_date_icon').click(function () {
                event.stopPropagation();
                $('#closure_date').click();
            });
            
            $('#closure_date').datepicker({                   
  	    	    
  	    	    format:'dd-mm-yyyy',
  	    	    //perform click event on done button
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#investigation_completed_icon').click(function () {
                event.stopPropagation();
                $('#investigation_completed').click();
            });
            
            $('#investigation_completed').datepicker({                   
  	    	    
  	    	    format:'dd-mm-yyyy',
  	    	    //perform click event on done button
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#payment_date_icon').click(function () {
                event.stopPropagation();
                $('#payment_date').click();
            });
            
            $('#payment_date').datepicker({                   
  	    	    
  	    	    format:'dd-mm-yyyy',
  	    	    //perform click event on done button
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
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
                                var globalWorkId = "${sessionScope.globalWorkId}";
                                if ($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('select').select2();
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
                    url: "<%=request.getContextPath()%>/ajax/getContract",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                            });
                        }
                        $('select').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        function addSafety(){
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
    			document.getElementById("safetyForm").submit();			
    	 	}
    	}
    	
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#safetyForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"department_fk": {
    				 		required: true
    				 	  },"category_fk": {
    				 		required: false
    				 	  },"impact_fk": {
    				 		required: false
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"root_cause_fk": {
    			 		    required: false,
    			 	   	  },"title": {
    				 		required: true
    				 	  },"description": {
    			 		    required: false
    			 	   	  },"date": {
    				 		required: true
    				 	  },"location": {
    				 		required: false
    				 	  },"latitude": {
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"responsible_person":{
    				 		 required: false
    				 	  },"closure_date": {
    			 		    required: false,
    			 	   	  },"committee_formed_fk": {
    				 		required: false
    				 	  },"lti_hours": {
    			 		    required: false
    			 	   	  },"equipment_impact": {
    				 		required: false
    				 	  },"people_impact": {
    				 		required: false
    				 	  },"work_impact":{
    				 		 required: false
    				 	  },"compensation": {
    			 		    required: false,
    			 	   	  },"investigation_completed": {
    				 		required: false
    				 	  },"payment_date": {
    			 		    required: false
    			 	   	  },"corrective_measure_short_term": {
    				 		required: false
    				 	  },"corrective_measure_long_term":{
    				 		 required: false
    				 	  },"remarks":{
    				 		 required: false
    				 	  }
    				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 },"department_fk": {
    			 			required: 'Required'
    			 	  	 },"category_fk": {
    			 			required: 'Required'
    			 	  	 },"impact_fk": {
    			 			required: 'Required'
    			 	  	 },"status_fk": {
    			 			required: 'Required'
    			 	  	 },"root_cause_fk": {
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
	   				 	  },"closure_date": {
	   			 		    required: 'Required',
	   			 	   	  },"committee_formed_fk": {
	   				 		required: 'Required'
	   				 	  },"lti_hours": {
	   			 		    required: 'Required'
	   			 	   	  },"equipment_impact": {
	   				 		required: 'Required'
	   				 	  },"people_impact": {
	   				 		required: 'Required'
    				 	  },"work_impact":{
    				 		 required: 'Required'
    				 	  },"compensation": {
    				 		 required: 'Required'
    			 	   	  },"investigation_completed": {
    			 	   		required: 'Required'
    				 	  },"payment_date": {
    				 		 required: 'Required'
    			 	   	  },"corrective_measure_short_term": {
    			 	   		required: 'Required'
    				 	  },"corrective_measure_long_term":{
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
    			 	    }else if (element.attr("id") == "department_fk" ){
    			 		     document.getElementById("department_fkError").innerHTML="";
    			 			 error.appendTo('#department_fkError');
    			 	    }else if (element.attr("id") == "category_fk" ){
    			 		     document.getElementById("category_fkError").innerHTML="";
    			 			 error.appendTo('#category_fkError');
    			 	    }else if (element.attr("id") == "impact_fk" ){
    			 		     document.getElementById("impact_fkError").innerHTML="";
    			 			 error.appendTo('#impact_fkError');
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
	  			 	    }else if (element.attr("id") == "closure_date" ){
	  			 		     document.getElementById("closure_dateError").innerHTML="";
	  			 			 error.appendTo('#closure_dateError');
	  			 	    }else if (element.attr("id") == "committee_formed_fk" ){
	  			 		     document.getElementById("committee_formed_fkError").innerHTML="";
	  			 			 error.appendTo('#committee_formed_fkError');
	  			 	    }else if (element.attr("name") == "lti_hours" ){
	  			 		     document.getElementById("lti_hoursError").innerHTML="";
	  			 			 error.appendTo('#lti_hoursError');
	  			 	    }else if (element.attr("id") == "equipment_impact" ){
	  			 		     document.getElementById("equipment_impactError").innerHTML="";
	  			 			 error.appendTo('#equipment_impactError');
	  			 	    }else if (element.attr("id") == "people_impact" ){
	  			 		     document.getElementById("people_impactError").innerHTML="";
	  			 			 error.appendTo('#people_impactError');
	  			 	    }else if (element.attr("id") == "work_impact" ){
	  			 		     document.getElementById("work_impactError").innerHTML="";
	  			 			 error.appendTo('#work_impactError');
	  			 	    }else if (element.attr("id") == "compensation" ){
	  			 		     document.getElementById("compensationError").innerHTML="";
	  			 			 error.appendTo('#compensationError');
	  			 	    }else if (element.attr("id") == "investigation_completed" ){
	  			 		     document.getElementById("investigation_completedError").innerHTML="";
	  			 			 error.appendTo('#investigation_completedError');
	  			 	    }else if (element.attr("id") == "payment_date" ){
	  			 		     document.getElementById("payment_dateError").innerHTML="";
	  			 			 error.appendTo('#payment_dateError');
	  			 	    }else if (element.attr("name") == "corrective_measure_short_term" ){
	  			 		     document.getElementById("corrective_measure_short_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_short_termError');
	  			 	    }else if (element.attr("id") == "corrective_measure_long_term" ){
	  			 		     document.getElementById("corrective_measure_long_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_long_termError');
	  			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }
    			 },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
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