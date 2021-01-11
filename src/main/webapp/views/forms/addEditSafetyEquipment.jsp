<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   	     <c:if test="${action eq 'edit'}">Update Safety Equipment </c:if>
		 <c:if test="${action eq 'add'}"> Add Safety Equipment </c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
 	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
     
     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">		
    
    <style>
         .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        #example4 {
            border: 1px solid #ddd;
        }

        #example4 .datepicker~button {
            top: 26px;
        }

        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .filevalue {
            display: block;
            margin-top: 10px;
        }
         /* .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;} */
		.my-error-class {
   			 color:red;
		}
		.input-field .searchable_label {
            font-size: 0.85rem;
        }
		.my-valid-class {
   			 color:green;
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
	                             <c:if test="${action eq 'edit'}">Update Safety Equipment</c:if>
								 <c:if test="${action eq 'add'}"> Add Safety Equipment</c:if>
							  </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
	                       	 <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-safety-equipment" id="safetyEquipmentForm" name="safetyEquipmentForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                         </c:if>
				             <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-safety-equipment" id="safetyEquipmentForm" name="safetyEquipmentForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							 </c:if>
							<div class="container container-no-margin">
                                <c:if test="${action eq 'add'}">	
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div class="col s12 m8 input-field"> 
                                    <div class="row">-->
                                        <div class="col s12 m4 input-field">
                                            <p class="searchable_label"> Project </p>
		                                           <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
		                                 	  		 onchange="getWorksList(this.value);">
		                                      		  <option value="" >Select</option>
		                                        		 <c:forEach var="obj" items="${projectsList }">
		                                           			 <option value="${obj.project_id_fk }" <c:if test="${safetyEquipmentDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
		                                        		 </c:forEach>
		                                          </select>
                                   			 <span id="project_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <p class="searchable_label"> Work </p>
	                                           <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
	                                      		  onchange="getContractsList(this.value);">
	                                      		  <option value="">Select</option>
	                                      		  <c:forEach var="obj" items="${worksList }">
	                                      	   			<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                      		  </c:forEach>
	                                   		 	</select>
                                   		   
                                     		 <span id="work_id_fkError" class="error-msg" ></span>
                                        </div>
                               			<div class="col m2 hide-on-small-only"></div>
                               			</div>
                                     <div class="row">
                                        <div class="col m2 hide-on-small-only"></div>
                                        <div class="col s12 m8 input-field">                                          
                                            <p> <label>Contract </p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
                                       			 <option value="">Select</option>
                                       			  <c:forEach var="obj" items="${contractsList }">
                                      	    		<option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                        		 </c:forEach>
                                  			 </select>                                  			
                                   			 <span id="contract_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m2 hide-on-small-only"></div>
                                    </div>
                                </div>
                                 </c:if>
                                <!-- <div class="col m2 hide-on-small-only"></div>
                            </div> -->
                            <div>
                       		 <c:if test="${action eq 'edit'}">	
                       	 <div class="row">
                       	      <div class="col m2 hide-on-small-only">	</div>
                       		  <div class="col s12 m4 input-field">
									<p class="searchable_label"> Project </p>
                                      <input type="text" name="project_id" id="project_id" value="${safetyEquipmentDetails.project_id}- ${safetyEquipmentDetails.project_name}" readonly />
							  </div> 
							  <div class="col s12 m4 input-field"> 
								    <p class="searchable_label"> Work </p>
                                  	 	<input type="text"  value="${safetyEquipmentDetails.work_id}- ${safetyEquipmentDetails.work_name}" readonly />
                                  	 	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${safetyEquipmentDetails.work_id}" readonly />
                              </div>
                               <div class="col m2 hide-on-small-only">     </div>
                          </div> 
                           <div class="row">
                       	      <div class="col m2 hide-on-small-only">	</div>
                       	      <div class="col s12 m8 input-field">
                     				<p class="searchable_label">Contract </p>        
                              	    <input type="text"  value="${safetyEquipmentDetails.contract_id_fk}- ${safetyEquipmentDetails.contract_name}"   readonly />                         
                             		<input type="hidden" name="contract_id_fk" id="contract_id_fk" value="${safetyEquipmentDetails.contract_id_fk}" readonly />
                             </div>
                             <div class="col m2 hide-on-small-only">	</div>
                           </div>
                             </c:if>
                            </div>
<%--                             <input type="hidden" name= "safety_equipment_id" id="safety_equipment_id" value="${safetyEquipmentDetails.safety_equipment_id}" />--%>                       
      						</div>
      						<div class="row fixed-width" style="margin-bottom: 40px;">
                                <h5 class="center-align">Equipment Details</h5>
                                <div class="table-inside">
                                    <table id="example4" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Equipment No</th>
                                                <th>Equipment Details</th>
                                                <th> Validity of <br>Equipment</th>
                                                <th>Inspecting <br> Official</th>
                                                <th>Last <br> Inspection</th>
                                                <th>Next Inspection <br> Due</th>
                                                <th>Remarks </th>
                                                <th> Attachment</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                      <tbody id="safetyTableBody">
                                     
                                       <c:choose>
                                      	 <c:when test="${not empty safetyEquipmentDetails.safetyEquipments && fn:length(safetyEquipmentDetails.safetyEquipments) gt 0 }">
                                       	 <c:forEach var="sObj" items="${safetyEquipmentDetails.safetyEquipments }" varStatus="index"> 
                                       	 
                                            <tr id="safetyRow${index.count }">
                                                <td>
                                                	<input type="hidden" name= "safety_equipment_ids" id="safety_equipment_ids${index.count }" value="${sObj.safety_equipment_id}" />
                                                    <input id="safety_equipment_numbers${index.count }" name="safety_equipment_numbers" type="text" class="validate" value="${sObj.safety_equipment_number }"
                                                        placeholder="Equipment No">
                                                </td>
                                                <td>
                                                    <input id="safety_equipment_details${index.count }" name="safety_equipment_details" type="text" class="validate" value="${sObj.safety_equipment_detail }"
                                                        placeholder="Equipment Details">
                                                </td>
                                                <td>
                                                    <input id="validity_dates${index.count }" name="validity_dates" type="text" class="validate datepicker" value="${sObj.validity_date }"
                                                        placeholder="Validity of Equipment">
                                                    <button type="button" id="validity${index.count }_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                 <td>
                                                    <input id="inspecting_officials${index.count }" name="inspecting_officials" type="text" class="validate"
                                                        placeholder="Inspecting Official" value="${sObj.inspecting_official }">                                                  
                                                </td>
                                                <td>
                                                    <input id="last_inspection_dates${index.count }" name="last_inspection_dates" type="text" class="validate datepicker"
                                                        placeholder="Last Inspection" value="${sObj.last_inspection_date }">
                                                    <button type="button" id="last_inspection_dates${index.count }_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="next_inspection_dues${index.count }" name="next_inspection_dues" type="text" class="validate datepicker"
                                                        placeholder="Next Inspection Due" value="${sObj.next_inspection_due }">
                                                    <button type="button" id="next_inspection_dues${index.count }_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarkss${index.count }" name="remarkss" type="text" class="validate" value="${sObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <div class="">
                                                        <input type="file" name="SafetyEquipmentFile" id="SafetyEquipmentFile${index.count }" onchange="getFileName('${index.count }')"   
                                                            style="display:none" />
                                                        <label for="SafetyEquipmentFile${index.count }" class="btn bg-m"><i
                                                                class="fa fa-paperclip"></i></label>
                                                        <a id="fileVal${index.count }" class="filevalue" href="<%=CommonConstants.SAFETY_EQUIPMENT_FILES %>${sObj.attachment }" download>${sObj.attachment }</a>
                                                    </div>
                                                    <input type="hidden" id="safetyEquipmentFileNames${index.count }" name="safetyEquipmentFileNames" value="${sObj.attachment }">
                                                </td>
                                                <td>
                                                    <a onclick="removeSafety('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                          </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       	 <tr id="safetyRow0">
                                                <td>
                                                    <input type="hidden" name= "safety_equipment_ids" id="safety_equipment_ids0" />
                                                
                                                    <input id="safety_equipment_numbers0" name="safety_equipment_numbers" type="text" class="validate"  
                                                        placeholder="Equipment No">
                                                </td>
                                                <td>
                                                    <input id="safety_equipment_details0" name="safety_equipment_details" type="text" class="validate" 
                                                        placeholder="Equipment Details">
                                                </td>
                                                <td>
                                                    <input id="validity_dates0" name="validity_dates" type="text" class="validate datepicker" 
                                                        placeholder="Validity of Equipment">
                                                    <button type="button" id="validity_1_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                 <td>
                                                    <input id="inspecting_officials0" name="inspecting_officials" type="text" class="validate"
                                                        placeholder="Inspecting Official">                                                  
                                                </td>
                                                <td>
                                                    <input id="last_inspection_dates0" name="last_inspection_dates" type="text" class="validate datepicker"
                                                        placeholder="Last Inspection">
                                                    <button type="button" id="last_inspection_dates0_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="next_inspection_dues0" name="next_inspection_dues" type="text" class="validate datepicker"
                                                        placeholder="Next Inspection Due">
                                                    <button type="button" id="next_inspection_dues0_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarkss0" name="remarkss" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <div class="">
                                                        <input type="file" name="SafetyEquipmentFile" id="SafetyEquipmentFile0"   
                                                            style="display:none" />
                                                           <input name="SafetyEquipmentFile" id="SafetyEquipmentFiles0" type="hidden"  onchange="getFileName('0')"/>
                                                        <label for="SafetyEquipmentFile0" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
                                                        <span id="fileVal0" class="filevalue" ></span>
                                                    </div>
                                                    <input type="hidden" id="safetyEquipmentFileNames0" name="safetyEquipmentFileNames">
                                                </td>
                                                <td>
                                                    <a onclick="removeSafety('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
	                                         <script type="text/javascript">
			                                                $("#validity_dates0").datepicker({
			                                                 	 format:'dd-mm-yyyy',
			                                                     onSelect: function () {
			                                          	    	     $('.confirmation-btns .datepicker-done').click();
			                                          	    	  }
			                                                 });
			                                                $("#SafetyEquipmentFile0").change(function () {
				                                                filename1 = $('#SafetyEquipmentFile0')[0].files[0].name;
				                                                $('#fileVal0').html(filename1);
				                                                console.log(filename1)
				                                            });
			                                                
		                                      </script>
                                    	  </c:otherwise>
                                     	</c:choose>
                                        </tbody>
                                    </table>
                                     <table class="mdl-data-table">
                                        <tbody id="safetyBody">                                          
			                                    <tr>
			  										 <td colspan="9" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-s t-c " onclick="addSafetyRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
  									<c:choose>
                                        <c:when test="${not empty safetyEquipmentDetails.safetyEquipments && fn:length(safetyEquipmentDetails.safetyEquipments) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(safetyEquipmentDetails.safetyEquipments) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                                </div>
                            </div>
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                     <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateSafetyEquipment();" style="width: 100%;" class="btn waves-effect waves-light bg-m black-text">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addSafetyEquipment();" style="width: 100%;" class="btn waves-effect waves-light bg-m black-text">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                          <a href="<%=request.getContextPath()%>/safety-equipment" class="btn waves-effect waves-light bg-s black-text"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                    </div>
                        </form>
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
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <!-- <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> -->


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
           // $("#validity_1").datepicker();
            $('#validity_1_icon').click(function () {
                event.stopPropagation();
                $('#validity_1').click();
            });
            
           
            var projectId = "${safetyEquipmentDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            var work_id_fk = "${safetyEquipmentDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });

        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyEquipmentForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var work_id_fk = "${safetyEquipmentDetails.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id_fk == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
        
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForSafetyEquipmentForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var contract_id_fk = "${safetyEquipmentDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id_fk == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" selected>' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
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
        
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){  
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
            	projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyEquipmentForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
                $('.searchable').select2();
            }
       		
        }
        
        
        function addSafetyRow(){
      		
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
          
             var html = '<tr id="safetyRow'+rNo+'">'
    				   +'<td>'
    				   +' <input type="hidden" name= "safety_equipment_ids" id="safety_equipment_ids'+rNo+'" />'
             		   +'<input id="safety_equipment_numbers'+rNo+'" name="safety_equipment_numbers" type="text" class="validate" placeholder="Equipment No"></td>'
    				   +'<td> <input id="safety_equipment_details'+rNo+'" name="safety_equipment_details" type="text" class="validate" placeholder="Equipment Details"></td>'
    				   +'<td><input id="validity_dates'+rNo+'" name="validity_dates" type="text" class="validate datepicker" placeholder="Validity of Equipment"><button type="button" id="validity_1_icon" class="white"><i class="fa fa-calendar"></i></button></td>'
    				   +'<td><input id="inspecting_officials'+rNo+'" name="inspecting_officials" type="text" class="validate" placeholder="Inspecting Official"></td>'
    				   +'<td><input id="last_inspection_dates'+rNo+'" name="last_inspection_dates" type="text" class="validate datepicker" placeholder="Last Inspection"><button type="button" id="last_inspection_dates'+rNo+'_icon" class="white"><i class="fa fa-calendar"></i></button></td>'
    				   +'<td><input id="next_inspection_dues'+rNo+'" name="next_inspection_dues" type="text" class="validate datepicker" placeholder="Next Inspection Due"><button type="button" id="next_inspection_dues'+rNo+'_icon" class="white"><i class="fa fa-calendar"></i></button> </td>'
    				   +'<td><input id="remarkss'+rNo+'" name="remarkss" type="text" class="validate" placeholder="Remarks"></td>'
    			   	   +'<td><div class=""> <input type="file" name="SafetyEquipmentFile" id="SafetyEquipmentFile'+rNo+'" style="display:none" onchange="getFileName('+rNo+')" /> '
    			   	   +'<label for="SafetyEquipmentFile'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
    			   	   +'<input type="hidden" id="safetyEquipmentFileNames'+rNo+'" name="safetyEquipmentFileNames">'
                       +'<span id="fileVal'+rNo+'" class="filevalue" ></span> </div>'
                       +'</td>'
    				   +'<td> <a onclick="removeSafety('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';

    				 $('#safetyTableBody').append(html);
    				 $("#rowNo").val(rNo);
    				 $('.searchable').select2();
    				  $("#validity_dates"+rNo).datepicker({
                      	 format:'dd-mm-yyyy',
                          onSelect: function () {
               	    	     $('.confirmation-btns .datepicker-done').click();
               	    	  }
                      });
    				  
         } 
   
        
        function removeSafety(rowNo){
        	//alert("#revisionRow"+rowNo);
        	$("#safetyRow"+rowNo).remove();
        }
        
        function getFileName(rowNo){
			var filename = $('#SafetyEquipmentFile'+rowNo)[0].files[0].name;
		    $('#fileVal'+rowNo).html(filename);
		}
        
        function addSafetyEquipment(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=safety_equipment_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safety_equipment_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safety_equipment_details]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=validity_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safetyEquipmentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("safetyEquipmentForm").submit();	
        	}
        }
        function updateSafetyEquipment(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=safety_equipment_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safety_equipment_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safety_equipment_details]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=validity_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safetyEquipmentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("safetyEquipmentForm").submit();	
        	}
        }
        
        var validator =	$('#safetyEquipmentForm').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"contract_id_fk": {
	  		 		    required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"contract_id_fk": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id" ){
						 document.getElementById("project_idError").innerHTML="";
				 		 error.appendTo('#project_idError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "contract_id_fk" ){
						document.getElementById("contract_id_fkError").innerHTML="";
					 	error.appendTo('#contract_id_fkError');
					}else{
	 					error.insertAfter(element);
			        } 
		   		},invalidHandler: function (form, validator) {
                    var errors = validator.numberOfInvalids();
                    if (errors) {
                        var position = validator.errorList[0].element;
                        jQuery('html, body').animate({
                            scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                        }, 1000);
                    }
                },submitHandler:function(form){
			    	form.submit();
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