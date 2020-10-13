<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
       	<c:if test="${action eq 'edit'}">Update Work Contract Module Status </c:if>
		<c:if test="${action eq 'add'}"> Add Work Contract Module Status </c:if>
     </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }
        .input-field .searchable_label {
            font-size: 0.8rem;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        .table-inside td span {
            text-align: left;
            display: block;
        }

        .primary-text {
            color: #2E58AD;
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
                            <div class="center-align p-2 bg-m">
                                <h6>
                                <c:if test="${action eq 'edit'}">Update Work Contract Module Status </c:if>
								<c:if test="${action eq 'add'}"> Add Work Contract Module Status </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                         <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-work-status" id="workStatusForm" name="workStatusForm" method="post" class="form-horizontal" role="form" >
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-workstatus" id="workStatusForm" name="workStatusForm" method="post" class="form-horizontal" role="form">
						  </c:if>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Project</p>
                                     <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${workStatusDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work</p>
                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" selected>Select</option>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Contract</p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" type="text" class="validate datepicker" name="month" value="${workStatusDetails.month }"> 
                                    <label for="date"> Date </label>
                                    <button id="date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="container" style="margin-bottom: 30px;">
                                <div class="row">
                                    <div class="col m1 hide-on-small-only"></div>
                                    <div class="col m10 s12">
                                        <div class="row fixed-width">
                                            <h5 class="center-align">Module Status</h5>
                                            <div class="table-inside">
                                                <table id="example4" class="mdl-data-table">
                                                    <thead>
                                                        <tr>
                                                            <th>Module </th>
                                                            <th>Status </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    
                                                      <c:choose>
                                      				 <c:when test="${not empty safetyDetails.safetyEquipments && fn:length(safetyDetails.safetyEquipments) gt 0 }">
                                                     <c:forEach var="sObj" items="${workStatusDetails.workContractStatus }"> 
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_contract">Contract</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status1" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${sObj.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_design">Design</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status2" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${sObj.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_execution_monitoring">Execution
                                                                    Monitoring</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status3" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_finance">Finance</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status4" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_issues">Issues</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status5" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_land_acquisition">Land Aquisition</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status6" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_randr"   
                                                                >R & R</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status7" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_risk"   
                                                                >Risk</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status8" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_safety">Safety</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status9" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_training">Training</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status10" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_training"   
                                                                >Unmanned
                                                                    Aerial Vehicle</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status11" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_us"   
                                                                >Utility
                                                                    Shifting</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status12" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_work"   
                                                                >Work</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status13" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month } </textarea>
                                                            </td>
                                                        </tr>
                                                         </c:forEach>
                                                          </c:when>
                                     			  	<c:otherwise>
                                     			  	 <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_contract">Contract</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status1" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${sObj.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_design">Design</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status2" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${sObj.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_execution_monitoring">Execution
                                                                    Monitoring</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status3" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_finance">Finance</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status4" class="materialize-textarea"  name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_issues">Issues</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status5" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_land_acquisition">Land Aquisition</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status6" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_randr"   
                                                                >R & R</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status7" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_risk"   
                                                                >Risk</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status8" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_safety">Safety</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status9" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" 
                                                                    id="table_training">Training</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status10" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_training"   
                                                                >Unmanned
                                                                    Aerial Vehicle</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status11" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_us"   
                                                                >Utility
                                                                    Shifting</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status12" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month }</textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_work"   
                                                                >Work</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status13" class="materialize-textarea" name="status_as_on_months"
                                                                    data-length="1000">${workStatusDetails.status_as_on_month } </textarea>
                                                            </td>
                                                        </tr>
                                                        </c:otherwise>
                                     				</c:choose>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                       		 <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateWorkContractStatus();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addWorkContractStatus();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
  <script>
		$(document).on('focus', '.datepicker',function(){
		      $(this).datepicker({
		      	format:'dd-mm-yyyy',
		 	    	onSelect: function () {
		 	    	   $('.confirmation-btns .datepicker-done').click();
		 	    	}
		      })
		 });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#textarea1').characterCounter();
           

            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            var project_id_fk = "${workStatusDetails.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${workStatusDetails.work_id_fk}";
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
                                var work_id_fk = "${workStatusDetails.work_id_fk }";
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
                	url: "<%=request.getContextPath()%>/ajax/getContract",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${workStatusDetails.contract_id_fk }";
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
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        function addWorkContractStatus(){
        	$(".page-loader").show();	    		
   			document.getElementById("workStatusForm").submit();	
        }
        
        function updateWorkContractStatus(){
        	$(".page-loader").show();	    		
   			document.getElementById("workStatusForm").submit();	
        }
    </script>

</body>

</html>