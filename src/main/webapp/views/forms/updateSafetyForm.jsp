<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Safety Incident</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	 
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	 
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
        .hidden{
        	display:none;
        }
         .input-field .searchable_label{
        	font-size:0.85rem;
        } 
        .mti-5 p{
        	margin-top:5px;
        }       
		.error-msg label{color:red!important;}
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
		.m-b-2{
			margin-bottom:2rem;
		}
		.mt-brdr{
			margin-top: 20px;
		    border-top: 1px solid #777;
		    border-bottom: 1px solid #777;
		}
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Update Safety Incident</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-safety" id="safetyForm" name="safetyForm" method="post" enctype="multipart/form-data">
                        	<div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <%--  <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" <c:if test="${safety.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select> --%>
                                    <input type="text"  value ="${safety.project_id_fk } - ${safety.project_name}" readonly id="project-text"/>
                                    <label for="project-text">Project <span class="required">*</span></label>
                                    <input type="hidden" name="project_id_fk" id="project_id_fk" value ="${safety.project_id_fk }" />
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                  <!--   <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" selected>Select</option>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span> -->
                                    <input type="text"  value ="${safety.work_id_fk } - ${safety.work_short_name}" readonly id="work-text"/>
                                    <label for="work-text">Work <span class="required">*</span></label>
                                    <input type="hidden" name="work_id_fk" id="work_id_fk" value ="${safety.work_id_fk }" />
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <!-- <input type="text" id="user_id"> -->
                                    <label for="user_id" >Safety ID : <input id="safety_id" name="safety_id" type="text" value="${safety.safety_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                    <br><br>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <!-- <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span> -->
                                    <input type="text"  value ="${safety.contract_id_fk } - ${safety.contract_short_name}" readonly id="contract-text"/>
                                    <label for="contract-text">Contract <span class="required">*</span></label>
                                    <input type="hidden" name="contract_id_fk" id="contract_id_fk" value ="${safety.contract_id_fk }" />
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div> 
                                
                                <div class="col s12 m4 input-field">
                                	<p class="searchable_label"> HOD <span class="required">*</span></p>
                                    <select id="hod_user_id_fk" name="hod_user_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${hodList }">
                                      	   <option value= "${ obj.hod_user_id_fk}" <c:if test="${safety.hod_user_id_fk eq obj.hod_user_id_fk}">selected</c:if>>${obj.designation}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="hod_user_id_fkError" class="error-msg" ></span>
                                </div>
                                
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                     <p class="searchable_label"> Department <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                        	<c:if test="${obj.department_fk eq 'Elec' or obj.department_fk eq 'Engg' or obj.department_fk eq 'S&T'}">
                                            	<option value="${obj.department_fk }" <c:if test="${safety.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_name}</option>
                                        	</c:if>
                                        </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p class="searchable_label"> Category <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk" onchange="setTitle(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyCategoryList }">
                                            <option value="${obj.category }" <c:if test="${safety.category_fk eq obj.category}">selected</c:if>>${obj.category}</option>
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
                                <p class="searchable_label"> Impact <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="impact_fk" name="impact_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyImpactList }">
                                            <option value="${obj.impact }" <c:if test="${safety.impact_fk eq obj.impact}">selected</c:if>>${obj.impact}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="impact_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">    
                                <p class="searchable_label"> Root Cause <span class="required">*</span></p>                            
                                    <select class="searchable validate-dropdown" id="root_cause_fk" name="root_cause_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyRootCauseList }">
                                            <option value="${obj.root_cause }" <c:if test="${safety.root_cause_fk eq obj.root_cause}">selected</c:if>>${obj.root_cause}</option>
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
                                <p class="searchable_label"> Status <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyStatusList }">
                                            <option value="${obj.status }" <c:if test="${safety.status_fk eq obj.status}">selected</c:if>>${obj.status}</option>
                                        </c:forEach>
                                    </select>                                    
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m2 input-field mti-5">
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_required" name="committee_required" <c:if test="${safety.committee_required_fk eq 'Yes'}">checked</c:if>/>
									        <span>Committee Required</span>
									      </label>
									      <input type="hidden" id="committee_required_fk" name="committee_required_fk" value="No"/>
								    </p>
							    </div>
							    <div class="col s12 m2 hidden input-field mti-5" id="committee_formed_div" >
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_formed" name="committee_formed" <c:if test="${safety.committee_formed_fk eq 'Yes'}">checked</c:if>/>
									        <span>Committee Formed</span>
									      </label>
									      
								    </p>
								    <input type="hidden" id="committee_formed_fk" name="committee_formed_fk" value="No"/>
							    </div>
                                <%-- <div class="col s12 m4 input-field">
                                 <p class="searchable_label"> Committee formed </p>
                                    <select id="committee_formed_fk" name="committee_formed_fk" class="searchable">
                                        <option value="">Select</option>
                                        <option value="Yes" <c:if test="${safety.committee_formed_fk eq 'Yes'}">selected</c:if>>Yes</option>
                                        <option value="No" <c:if test="${safety.committee_formed_fk eq 'No'}">selected</c:if>>No</option>
                                    </select>
                                    <span id="committee_formed_fkError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field ">                                   
                                      <textarea id="title" name="title" class="materialize-textarea validate" data-length="100">${safety.title }</textarea>
	                                  <label for="title">Short Description <span class="required">*</span></label>
	                                  <span id="titleError" class="error-msg" ></span>
                                 </div>
                                 <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                      <div class="col s12 m8 input-field">
										<textarea id="description" name="description" class="materialize-textarea validate" data-length="1000">${safety.description }</textarea>                                            
	                                    <label for="description">Full Description<span class="required">*</span></label>
	                                    <span id="descriptionError" class="error-msg" ></span>
                                      </div>                                    
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker" value="${safety.date }">
                                    <label for="date"> Date <span class="required">*</span></label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" name="location" type="text" class="validate" value="${safety.location }">
                                    <label for="location">Location/Station/KM<span class="required">*</span></label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${safety.latitude }">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${safety.longitude }">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="validate" value="${safety.reported_by }">
                                    <label for="reported_by">Reported By</label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="responsible_person" name="responsible_person" type="text" class="validate" value="${safety.responsible_person }">
                                    <label for="responsible_person">Person Responsible in MRVC</label>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="closure_date" name="closure_date" type="text" class="validate datepicker" value="${safety.closure_date }">
                                    <label for="closure_date">Closure Date</label>
                                    <button type="button" id="closure_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="closure_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="lti_hours" name="lti_hours" type="text" class="validate" value="${safety.lti_hours }">
                                    <label for="lti_hours">Loss Time Injury (LTI) Hours</label>
                                    <span id="lti_hoursError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="equipment_impact" name="equipment_impact" type="text" class="validate" value="${safety.equipment_impact }">
                                    <label for="equipment_impact"> Equipment Impact </label>
                                    <span id="equipment_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="people_impact" name="people_impact" type="text" class="validate" value="${safety.people_impact }">
                                    <label for="people_impact">People Impact</label>
                                    <span id="people_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="work_impact" name="work_impact" type="text" class="validate" value="${safety.work_impact }">
                                    <label for="work_impact"> Work Impact </label>
                                    <span id="work_impactError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m4 input-field ">
                                    <input id="investigation_completed" name="investigation_completed" type="text" class="validate datepicker" value="${safety.investigation_completed }">
                                    <label for="investigation_completed">Investigation Completion Date</label>
                                    <button type="button" id="investigation_completed_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="investigation_completedError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                               
                                <div class="col s12 m4 input-field">
                                    <input id="payment_date" name="payment_date" type="text" class="validate datepicker" value="${safety.payment_date }">
                                    <label for="payment_date">Payment Date</label>
                                    <button type="button" id="payment_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="payment_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <i class="material-icons prefix center-align">₹</i>
                                    <input id="compensation" name="compensation" type="number" min="0.01" step="0.01" class="validate" value="${safety.compensation }">
                                    <label for="compensation"> Compensation </label>
                                    <span id="compensationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="corrective_measure_short_term" name="corrective_measure_short_term" class="materialize-textarea validate" data-length="1000">${safety.corrective_measure_short_term }</textarea>  
                                    <label for="corrective_measure_short_term">Corrective Measure (Short Term) </label>
                                    <span id="corrective_measure_short_termError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                               		<textarea id="corrective_measure_long_term" name="corrective_measure_long_term" class="materialize-textarea validate" data-length="1000">${safety.corrective_measure_long_term }</textarea>  
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
                                            <input class="file-path validate" type="text" name="attachment" value="${safety.attachment }" id="safetyFileWrappeer">
                                        </div>
                                    </div>
                                    <c:if test="${not empty safety.attachment }">
                                       	<a href="<%=CommonConstants2.SAFETY_FILES %>${safety.attachment }" class="filevalue" download>${safety.attachment }</a>
                                       	<span onclick="removeMedia(this,'safetyFileWrappeer')" class="attachment-remove-btn">X</span>	
                                   	</c:if>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${safety.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="updateSafety()"
                                            class="btn waves-effect waves-light bg-m">Update </button>
                                    </div>
                                </div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/safety" class="btn waves-effect waves-light bg-s">Cancel</a>
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
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script>
	/* $(document).on('focus', '.datepicker',function(){
        $(this).datepicker({
        	format:'dd-mm-yyyy',
   	    	onSelect: function () {
   	    	   $('.confirmation-btns .datepicker-done').click();
   	    	}
        })
    }); */
    let date_pickers = document.querySelectorAll('.datepicker');
    $.each(date_pickers, function(){
    	var dt = this.value.split(/[^0-9]/);
    	this.value = ""; 
    	var options = {format: 'dd-mm-yyyy'};
    	if(dt.length > 1){
    		options.setDefaultDate = true,
    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
    	}
    	M.Datepicker.init(this, options);
    });

        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
            
            $('#date_icon').click(function (event) {
                event.stopPropagation();
                $('#date').click();
            });
                      
            $('#closure_date_icon').click(function (event) {
                event.stopPropagation();
                $('#closure_date').click();
            });
                                  
            $('#investigation_completed_icon').click(function (event) {
                event.stopPropagation();
                $('#investigation_completed').click();
            });
            
  	        $('#payment_date_icon').click(function (event) {
                event.stopPropagation();
                $('#payment_date').click();
            });             	   
            
            var project_id_fk = "${safety.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${safety.work_id_fk}";
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            }
            
            $('#committee_required').change(function(){
                if(this.checked){
                    $('#committee_formed_div').removeClass('hidden');
                }else{
                    $('#committee_formed_div').addClass('hidden');
                    $("#committee_formed_fk").val("No");
                    $('#committee_formed').prop('checked', false);
                }
            });
            
            
            $("#committee_required").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_required_fk").val("Yes");
	            }else{
	            	$("#committee_required_fk").val("No");
	            }
	        });
	        
	        $("#committee_formed").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_formed_fk").val("Yes");
	            }else{
	            	$("#committee_formed_fk").val("No");
	            }
	        });
	        
	        var rValue = "${safety.committee_required_fk}";
	        var fValue = "${safety.committee_formed_fk}";
	        
	        if(rValue == 'Yes'){
	        	$("#committee_required_fk").val("Yes");
	        	$('#committee_formed_div').removeClass('hidden');
	        }else{
	        	$("#committee_required_fk").val("No");
	        	$('#committee_formed_div').addClass('hidden');
	        }
	        
	        if(fValue == 'Yes'){
	        	$("#committee_formed_fk").val("Yes");
	        }else{
	        	$("#committee_formed_fk").val("No");
	        }
            
        });
        
        function setTitle(category){
        	$("#title").val(category).focus();
        }
        
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
                                var work_id_fk = "${safety.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
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
                    url: "<%=request.getContextPath()%>/ajax/getContracts",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${safety.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function updateSafety(){
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
    				 	  },"hod_user_id_fk": {
    				 		required: true
    				 	  },"department_fk": {
    				 		required: true
    				 	  },"category_fk": {
    				 		required: true
    				 	  },"impact_fk": {
    				 		required: true
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"root_cause_fk": {
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
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"responsible_person":{
    				 		 required: false
    				 	  },"closure_date": {
    			 		    required: false,
    			 		   dateBefore1 : "#date",
    				 		statusCheck1: true
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
    				 		required: false,
     			 		    dateBefore3 : "#date",
    				 		statusCheck2: true
    				 	  },"payment_date": {
    			 		    required: false,
     			 		    dateBefore2 : "#date",
    				 		statusCheck3: true
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
    			 	  	 },"hod_user_id_fk": {
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
    			 	    }else if (element.attr("id") == "hod_user_id_fk" ){
	   			 	    	 document.getElementById("hod_user_id_fkError").innerHTML="";
				 			 error.appendTo('#hod_user_id_fkError');
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
    			 	    }else if (element.attr("id") == "root_cause_fk" ){
	   			 		     document.getElementById("root_cause_fkError").innerHTML="";
				 			 error.appendTo('#root_cause_fkError');
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
    	
    	    $.validator.addMethod("dateBefore1", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	            
	        }, "Closure date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore2", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Payment Date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore3", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Investigation Completion date must be after Date");
    	
    	    $.validator.addMethod("statusCheck1", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#closure_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck2", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#investigation_completed").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck3", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#payment_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    		
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
            function removeMedia(link,id){
             	  $('#'+id).val('');
             	  $(link).prev().text('');
             	  $(link).css('display','none');
            }
    </script>
</body>
</html>