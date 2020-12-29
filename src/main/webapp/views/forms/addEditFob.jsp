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
		<c:if test="${action eq 'edit'}">Update FOB</c:if>
		<c:if test="${action eq 'add'}"> Add FOB</c:if>
	</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	 	
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	 
	<link rel="stylesheet" href="/pmis/resources/css/fob.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">		
	 <style>
        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
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
		/*table with fixed header & height start */
		.max-h{
			max-height:400px;
			height:auto;
			overflow:auto;			
		}	
		.max-h tr{
			position:relative;
		}
		.max-h thead th{
			position:sticky;
			top:0;
			z-index:1;
			background-color:#003049;
		}
		.input-field>label.small{
			font-size: 0.88rem !important;
		}
		/*table with fixed header & height ends */
		
		.preloader-wrapper{top: 45%!important;left:47%!important;}
        .error-msg label{color:red!important;}
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

  <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-commissioning_date headbg card-title">
                            <div class="center-align p-2 bg-m">
                                <h6>
                                	<c:if test="${action eq 'edit'}">Update FOB</c:if>
									<c:if test="${action eq 'add'}"> Add FOB</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                          <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-fob" id="fobForm" name="fobForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-fob" id="fobForm" name="fobForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Project </label></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" <c:if test="${obj.project_id eq fob.project_id_fk}">selected</c:if> >${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
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

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p> <label> Contract </label></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <c:if test="${empty fob.fob_id }">
	                                <div class="col s12 m4 input-field">
	                                    <input id="fob_id" name="fob_id" type="text" class="validate" style="margin-top: 5px;">
	                                     <label for="fob_name">FOB ID</label>
	                                    <span id="fob_idError" class="error-msg" ></span>
	                                </div>
                                </c:if>
                                <c:if test="${not empty fob.fob_id }">
	                                <div class="col s12 m4 input-field">
	                                    <label style="margin-top:10px"> FOB ID : <input id="fob_id" name="fob_id" type="text" value="${fob.fob_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
	                                    <span id="fob_idError" class="error-msg" ></span>
	                                </div>
                                </c:if>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="fob_name" name="fob_name" type="text" class="validate" value="${fob.fob_name }" style="margin-top: 5px;">
                                    <label for="fob_name">FOB Name</label>
                                    <span id="fob_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                   <p> <label for="work_status_fk">Work Status</label></p>
                                    <select id="work_status_fk" name="work_status_fk"  class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${generalStatusList }">
                                            <option value="${obj }" <c:if test="${obj eq fob.work_status_fk}">selected</c:if> >${obj}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="work_status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
	                                <div class="row">
	                                	  <div class="col s12 m4 input-field">
                                    <input id="target_date" name="target_date" type="text" class="validate datepicker" value="${fob.target_date }">
                                    <label for="target_date">Target Date </label>
                                    <button type="button" id="target_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="target_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>   
                                    <input id="estimated_cost" name="estimated_cost" type="number" class="validate" value="${fob.estimated_cost }">
                                    <label for="estimated_cost">Estimated Cost (in Cr)</label>
                                    <span id="estimated_costError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>   
                                    <input id="last_sanctioned_cost" name="last_sanctioned_cost" type="number" class="validate" value="${fob.last_sanctioned_cost }">
                                    <label for="last_sanctioned_cost" class="small">Last Sanctioned Cost (in Cr)</label>
                                    <span id="last_sanctioned_costError" class="error-msg" ></span>
                                </div>                                
	                                </div>
                                </div>
                              <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="construction_start_date" name="construction_start_date" type="text" class="validate datepicker" value="${fob.construction_start_date }">
                                    <label for="construction_start_date">Construction Start Date </label>
                                    <button type="button" id="construction_start_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="construction_start_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="commissioning_date" name="commissioning_date" type="text" class="validate datepicker" value="${fob.commissioning_date }">
                                    <label for="commissioning_date">Commissioning Date </label>
                                    <button type="button" id="commissioning_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="commissioning_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker" value="${fob.actual_completion_date }">
                                    <label for="actual_completion_date">Actual Completion Date </label>
                                    <button type="button" id="actual_completion_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="actual_completion_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>   
                                    <input id="completion_cost" name="completion_cost" type="text" class="validate" value="${fob.completion_cost }">
                                    <label for="completion_cost">Completion Cost (in Cr)</label>
                                    <span id="completion_costError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${fob.latitude }">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${fob.longitude }">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <div class="row fixed-width">
                                <h5 class="center-align">FOB Details</h5>
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 max-h">
                                    <table id="fobDetailsTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>FOB Detail </th>
                                                <th>Value </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="fobDetailsTableBody">
                                        	<c:choose>
                                        		<c:when test="${not empty fob.fobDetails && fn:length(fob.fobDetails) gt 0 }">
                                        			<c:forEach var="dObj" items="${fob.fobDetails }" varStatus="index">                                        	
			                                           <tr id="fobDetailsRow${index.count }">                                            	
			                                               <td>
			                                                    <input id="fob_detail_names${index.count }" name="fob_detail_names" type="text" class="validate" value="${dObj.detail_name }"
			                                                        placeholder="Detail name">
			                                                </td>
			                                                <td>
			                                                    <input id="fob_detail_values${index.count }" name="fob_detail_values" type="text" class="validate" value="${dObj.value }"
			                                                        placeholder="Value">
			                                                </td>
			                                                <td>
			                                                    <a href="javascript:void(0);" class="btn waves-effect waves-light red t-c " onclick="removeFOBDetails('${index.count }');"> <i class="fa fa-close"></i></a>
			                                                </td>
			                                            </tr>
		                                            </c:forEach> 
                                        		</c:when>
                                        		<c:otherwise>
	                                        		<tr id="fobDetailsRow0">   
	                                        			<td><input id="fob_detail_names0" name="fob_detail_names" type="text" class="validate" placeholder="Detail name">
		                                                </td>
		                                                <td><input id="fob_detail_values0" name="fob_detail_values" type="text" class="validate" placeholder="Value">
		                                                </td>
		                                                <td><a href="javascript:void(0);" class="btn waves-effect waves-light red t-c " onclick="removeFOBDetails('0');"> <i class="fa fa-close"></i></a>
		                                                </td>
	                                                </tr>
                                        		</c:otherwise>
                                        	</c:choose>                                                                                       
                                            <!-- <tr>
                                                <td></td>
                                                <td></td>
                                                <td><a href="#" class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-plus"></i></a> </td>
											</tr> -->
                                        </tbody>
                                    </table>
                                    
                                    <table class="mdl-data-table">
                                        <tbody>                                          
                                            <tr>
                                                <td colspan="3" style="text-align: right;"><a href="javascript:void(0);" onclick="addFOBDetails()"class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-plus"></i></a> </td>
											</tr>
                                        </tbody>
                                    </table>
                                    <c:choose>
                                        <c:when test="${not empty fob.fobDetails && fn:length(fob.fobDetails) gt 0 }">
                                            <input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(fob.fobDetails)}" />
                                        </c:when>
                                        <c:otherwise>
                                        	<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                        </c:otherwise>
                                    </c:choose>  
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
 							<!-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attach Image</span>
                                            <input type="file" id="fobFile" name="fobFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> -->
                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
	                            <div class="col m8 s12">
	                               <div class="file-field input-field">
	                                   <div class="btn bg-m">
	                                   	<c:if test="${not empty fob.attachment }">
	                                       <span>Change Image</span>
	                                       </c:if>
	                                       <c:if test="${empty fob.attachment }">
	                                       <span>Attach Image</span>
	                                       </c:if>
	                                       <input type="file" id="fobFile" name="fobFile" accept="image/*" onchange="readURL(this);">
	                                   </div>
	                                   <div class="file-path-wrapper">
	                                       <input class="file-path validate" type="text" name="attachment" value="${fob.attachment}">
	                                       <img style="height: 20%;width: 20%;<c:if test="${empty fob.attachment }">display:none;</c:if>" id="fobImagePreview" src="<%=CommonConstants2.FOB_FILES %>${fob.attachment }" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" alt="FOB Image" />
	                                   </div>
	                               </div>
	                            </div>
	                            <div class="col m2 hide-on-small-only"></div>
                            </div>
                                
                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${fob.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateFOB();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addFOB();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/fob" class="btn waves-effect waves-light bg-s" style="width:100%">Cancel</a>
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
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
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
        $('#remarks').characterCounter();
        
        $('#date_of_approval_icon').click(function () {
            event.stopPropagation();
            $('#date_of_approval').click();
        });
                     
        $('#construction_start_date_icon').click(function () {
            event.stopPropagation();
            $('#construction_start_date').click();
        });
        
              
        $('#actual_completion_date_icon').click(function () {
            event.stopPropagation();
            $('#actual_completion_date').click();
        });
                            
        $('#commissioning_date_icon').click(function () {
            event.stopPropagation();
            $('#commissioning_date').click();
        });
               
        $('#target_date_icon').click(function () {
            event.stopPropagation();
            $('#target_date').click();
        });
                       
        var project_id_fk = "${fob.project_id_fk}";
        if ($.trim(project_id_fk) != '') {
            getWorksList(project_id_fk);
        }
        
        var work_id_fk = "${fob.work_id_fk}";
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
                            var work_id_fk = "${fob.work_id_fk }";
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
                            var contract_id_fk = "${fob.contract_id_fk }";
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
    
    function addFOB(){
		if(validator.form()){ // validation perform
			$(".page-loader").show();
			$('form input[name=fob_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			$('form input[name=fob_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			document.getElementById("fobForm").submit();			
	 	}
	}
	
    function updateFOB(){
  		if(validator.form()){ // validation perform
  			$(".page-loader").show();	    		
  			$('form input[name=fob_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			$('form input[name=fob_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			document.getElementById("fobForm").submit();			
	 	}
	}
	
	//Wait for the DOM to be ready
	
	// to validate apartment form inputs thruogh jquery.
	   
	var validator = $('#fobForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "fob_id": {
				 		required: true
			 	  	  },"project_id_fk": {
   				 		required: true
   				 	  },"work_id_fk": {
				 		required: true
				 	  },"contract_id_fk": {
				 		required: true
				 	  },"fob_name": {
				 		required: true
				 	  },"work_status_fk": {
				 		required: true
				 	  },"target_date": {
				 		required: true,
   				 		dateBefore1:"#construction_start_date"
				 	  },"estimated_cost": {
				 		required: false
				 	  },"last_sanctioned_cost": {
			 		    required: false,
			 	   	  },"construction_start_date": {
			 		    required: false
			 	   	  },"commissioning_date": {
				 		required: false,
   				 		dateBefore2:"#construction_start_date"
				 	  },"actual_completion_date": {
			 		    required: false,
   				 		dateBefore3:"#commissioning_date"
			 	   	  },"completion_cost": {
				 		required: false
				 	  },"latitude": {
				 		required: false
				 	  },"longitude": {
				 		required: false
				 	  },"remarks":{
				 		 required: false
				 	  }
				 				
			 	},
			   messages: {
				     "fob_id": {
  			 			required: 'Required'
  			 	  	 },"project_id_fk": {
   			 			required: 'Required'
   			 	  	 },"work_id_fk": {
			 			required: 'Required'
			 	  	 },"contract_id_fk": {
			 			required: 'Required'
			 	  	 },"fob_name": {
			 			required: 'Required'
			 	  	 },"work_status_fk": {
			 			required: 'Required'
			 	  	 },"target_date": {
			 			required: 'Required'
			 	  	 },"estimated_cost": {
			 			required: 'Required'
			 	  	 },"last_sanctioned_cost": {
			 	  		required: 'Required'
			 	   	 },"construction_start_date": {
			 			required: 'Required'
			 	  	 },"commissioning_date": {
			 			required: 'Required'
			 	  	 },"actual_completion_date": {
			 			required: 'Required'
			 	  	 },"completion_cost": {
			 			required: 'Required'
			 	  	 },"latitude": {
 				 		required: 'Required'
   				 	  },"longitude": {
   				 		required: 'Required'
   				 	  },"remarks":{
			 	  		required: 'Required'
				 	  }
			 				      
		    },
			  errorPlacement:
			 	function(error, element){
				    if (element.attr("id") == "fob_id" ){
			 		     document.getElementById("fob_idError").innerHTML="";
			 			 error.appendTo('#fob_idError');
			 	    }else if (element.attr("id") == "project_id_fk" ){
 			 		     document.getElementById("project_id_fkError").innerHTML="";
 			 			 error.appendTo('#project_id_fkError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "fob_name" ){
			 		     document.getElementById("fob_nameError").innerHTML="";
			 			 error.appendTo('#fob_nameError');
			 	    }else if (element.attr("id") == "work_status_fk" ){
			 		     document.getElementById("work_status_fkError").innerHTML="";
			 			 error.appendTo('#work_status_fkError');
			 	    }else if (element.attr("id") == "target_date" ){
			 		     document.getElementById("target_dateError").innerHTML="";
			 			 error.appendTo('#target_dateError');
			 	    }else if (element.attr("id") == "estimated_cost" ){
			 		     document.getElementById("estimated_costError").innerHTML="";
			 			 error.appendTo('#estimated_costError');
			 	    }else if (element.attr("id") == "construction_start_date" ){
			 		     document.getElementById("construction_start_dateError").innerHTML="";
			 			 error.appendTo('#construction_start_dateError');
			 	    }else if (element.attr("id") == "estimated_cost" ){
			 		     document.getElementById("estimated_costError").innerHTML="";
			 			 error.appendTo('#estimated_costError');
			 	    }else if (element.attr("id") == "last_sanctioned_cost" ){
			 		     document.getElementById("last_sanctioned_costError").innerHTML="";
			 			 error.appendTo('#last_sanctioned_costError');
			 	    }else if (element.attr("id") == "commissioning_date" ){
			 		     document.getElementById("commissioning_dateError").innerHTML="";
			 			 error.appendTo('#commissioning_dateError');
			 	    }else if (element.attr("name") == "actual_completion_date" ){
			 		     document.getElementById("actual_completion_dateError").innerHTML="";
			 			 error.appendTo('#actual_completion_dateError');
			 	    }else if (element.attr("id") == "completion_cost" ){
			 		     document.getElementById("completion_costError").innerHTML="";
			 			 error.appendTo('#completion_costError');
			 	    }else if (element.attr("id") == "latitude" ){
			 	    	     document.getElementById("latitudeError").innerHTML="";
			 			     error.appendTo('#latitudeError');
  			 	    }else if (element.attr("id") == "longitude" ){
  			 		     document.getElementById("longitudeError").innerHTML="";
  			 			 error.appendTo('#longitudeError');
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
	    
	    
	    $.validator.addMethod("dateBefore1", function(value, element) {
            var fromDateString = $('#construction_start_date').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            	//return Date.parse(fromDate) < Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Target Date must be after Construction Start Date");
	    
	    $.validator.addMethod("dateBefore2", function(value, element) {
            var fromDateString = $('#construction_start_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            	//return Date.parse(fromDate) < Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Commissioning Date must be after Construction Start Date");
	    
	    $.validator.addMethod("dateBefore3", function(value, element) {
            var fromDateString = $('#commissioning_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            	//return Date.parse(fromDate) < Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Actual Completion Date must be after Commissioning Date");
        
        
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
        
        
        
        function addFOBDetails(){      		
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="fobDetailsRow'+rNo+'">'
    		   		  +'<td><input  type="text" class="validate" id="fob_detail_names'+rNo+'" name="fob_detail_names" placeholder="Detail Name"></td>'
    				   +'<td><input  type="text" class="validate" id="fob_detail_values'+rNo+'" name="fob_detail_values" placeholder="Value"></td>'
    			   	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeFOBDetails('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
    			 
			 $('#fobDetailsTableBody').append(html);
			 $("#rowNo").val(rNo);
        }
        
		 function removeFOBDetails(rowNo){
        	$("#fobDetailsRow"+rowNo).remove();
         }
		 
		 function readURL(input) {
	            if (input.files && input.files[0]) {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    $('#fobImagePreview').attr('src', e.target.result)
	                        //.width(150)
	                        //.height(200);
	                };
	                reader.readAsDataURL(input.files[0]);
	                $('#fobImagePreview').show();
	            }
	     }
    </script>
	
</body>
</html>