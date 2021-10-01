<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 
    	<%-- <c:if test="${action eq 'edit'}">Edit Contract Resources - Update Forms - PMIS</c:if> --%>
		Add Contract Resources - Update Forms - PMIS
	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <style>
	   .no-mar .row {
	       margin-bottom: 0;
	   }
	
	   .fixed-width {
	       width: 100%;
	       margin-left: auto !important;
	       margin-right: auto !important;
	       margin-bottom: 20px;
	   }
	
	   .fixed-width .table-inside {
	       width: 100%;
	       overflow: auto;
	   }
	   .error-msg,.error{
	    	color:red !important;
	   }
	   td {
	       position: relative;
	   }
	
	   #resourceFormTableBody .select2-container {
	       max-width: 125px;
	   }
	
	   .select2-container--default .select2-selection--single {
	       background-color: transparent;
	   }
	
	   @media screen and (max-width:769px) {
	       #resourceFormTableBody .select2-container {
	           max-width: inherit;
	       }
	   }
	</style>
</head>
<body>

	<!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header  ends-->
	
	<div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6> Add Contract Resources</h6>
                            </div>
                        </span>
                    </div>
                    	<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="<%=request.getContextPath() %>/add-contract-resource" id="resourceForm" name="resourceForm" method="post">
                            <div class="row ">
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <p class="searchable_label">Project</p>
                                    <select class="searchable" id="project_id_fk" name="project_id_fk" onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l6 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable" id="work_id_fk" name="work_id_fk" onchange="getContractsList(this.value);resetProjectsDropdowns();">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row ">
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <p class="searchable_label">Contract <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l6 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker">
                                    <label for="date" class="fs-sm-8rem">Deployment Date <span class="required">*</span></label> 
                                    <button type="button" id="date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                            </div>
                      		<input type="hidden" name="user_id" id="user_id" />
                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s12 m8 l12 offset-m2">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">Resource Details</h5>
                                        <div class="table-inside">
                                            <table id="resourceFormTable"
                                                class="mdl-data-table mobile_responsible_table">
                                                <thead>
                                                    <tr>
                                                        <th>Resource Type</th>
                                                        <th>Resource Name</th>
                                                        <th>Quantity </th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="resourceFormTableBody">
													  <tr id="resourceFormTableRow0">
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_types0"
                                                                name="resource_types">
                                                                <option value="" >Select</option>  
														          <c:forEach var="obj" items="${resourceTypeList }">
						                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
						                                          </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_names0" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names" value="Excavator">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field">
                                                        <input id="quantitys0" type="number" name="quantitys" min="1" step="1" class="validate" placeholder="Qty" value="0" ></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="javascript:void(0);" onclick="removeResource('0');" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr id="ResourceRow1">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types1" name="resource_types" class="select searchable">
														 <option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names1" name="resource_names" type="text" class="validate" placeholder="Name" value="JCB"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys1" name="quantitys" type="number" value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('1');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow2">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types2" name="resource_types" class="select searchable">
														<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names2" name="resource_names" type="text" class="validate" placeholder="Name" value="Dumper"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys2" name="quantitys" type="number" value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('2');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow3">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types3" name="resource_types" class="select searchable">
														<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names3" name="resource_names" type="text" class="validate" placeholder="Name" value="Grader"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys3" name="quantitys" type="number" value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('3');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow4">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types4" name="resource_types" class="select searchable">
														<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names4" name="resource_names" type="text" class="validate" placeholder="Name" value="Water Tanker Bowser"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys4" name="quantitys" type="number" value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('4');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow5">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types5" name="resource_types" class="select searchable">
														<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names5" name="resource_names" type="text" class="validate" placeholder="Name" value="Semi Skilled"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys5" name="quantitys" type="number" value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('5');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow6">
												<td class="input-field" data-head="Resource Type" >
													<select id="resource_types6" name="resource_types" class="select searchable">
														<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names6" name="resource_names" type="text" class="validate" placeholder="Name" value="Highly Skilled"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys6" name="quantitys" type="number"  value="0" class="validate" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('6');"><i class="fa fa-close"></i></a></td>
											</tr>
											<tr id="ResourceRow7">
												<td class="input-field" data-head="Resource Type" >
												<select id="resource_types7" name="resource_types"class="select searchable">
													<option value="" >Select</option>  
												          <c:forEach var="obj" items="${resourceTypeList }">
				                                      	    <option value= "${obj.resource_type}" >${obj.resource_type}</option>
				                                          </c:forEach>
													</select>
												</td>
												<td class="input-field" data-head="Resource Name"><input id="resource_names7" name="resource_names" type="text"  class="validate" placeholder="Name" value="Unskilled"></td>
												<td class="input-field" data-head="Quantity"><input id="quantitys7" name="quantitys" type="number" class="validate" value="0" placeholder="Qty" min="0"></td>
												<td class="mobile_btn_close"><a href="javascript:void(0);" class="btn tab waves-effect waves-light red t-c" onclick="removeResource('7');"><i class="fa fa-close"></i></a></td>
											</tr>
                                                </tbody>
                                            </table>
                                            <table class="mdl-data-table">
                                                <tbody>
                                                    <tr>
                                                        <td colspan="4" style="text-align:center;"><a href="javascript:void(0);" onclick="addResource();" class="btn tab waves-effect waves-light bg-m t-c "> 
                                                        <i class="fa fa-plus"></i></a></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <input type="hidden" id="rowNo" name="rowNo" value="7" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div></div>
							<div class="row">
								<div class="col s6 m4 l6 mt-brdr offset-m2 center-align">
									<div class=" m-1">
								  	 <button type="button" onclick="submitResource();" class="btn waves-effect waves-light bg-m">Submit</button>
									</div>
							</div>
							<div class="col s6 m4 l6 mt-brdr center-align">
								<div class=" m-1">
									<a href="<%=request.getContextPath()%>/contract-resource-form"
										class="btn waves-effect waves-light bg-s" >Reset</a>
								</div>
							</div>
							 
						</div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
    
    <!-- footer  starts-->
     <jsp:include page="../layout/footer.jsp"></jsp:include>
    <!-- footer  ends-->
    
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
       /*  let date_pickers = document.querySelectorAll('.datepicker');
        $.each(date_pickers, function () {
            var dt = this.value.split(/[^0-9]/);
            this.value = "";
            var options = { format: 'dd-mm-yyyy', autoClose: true };
            if (dt.length > 1) {
                options.setDefaultDate = true,
                    options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
            }
            M.Datepicker.init(this, options);
        }); */
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();     
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
        });
        var user_id  = '${sessionScope.USER_ID}';
		$('#user_id').val(user_id);
		function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForContractResourceForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForContractResourceForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
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
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        
        function resetProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
       		var work_id_fk = $("#work_id_fk").val();
       		if($.trim(work_id_fk) != ''){  
            	projectId = work_id_fk.substring(0, 3);    
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		$(".page-loader").hide();
        	
        }
        
        function addResource() {
            // var rowNo = $("#rowNo").val();
            var rowNo = $("#resourceFormTableBody tr").length;
            var rNo = Number(rowNo) + 1;
            var html = '<tr id="resourceFormTableRow' + rNo + '"><td data-head="Resource Type" class="input-field"><select class="searchable" id="resource_types' + rNo + '" name="resource_types">' +
			            '<option value="">select</option>'+
					       <c:forEach var="obj" items="${resourceTypeList }">
                                   	    '<option value= "${obj.resource_type}" >${obj.resource_type}</option>'+
                           </c:forEach>
			            '</select></td><td data-head="Resource Name" class="input-field"> <input id="resource_names' + rNo + '" type="text" class="validate" placeholder="Resource Name" name="resource_names" >' +
			            '</td> <td data-head="Quantity" class="input-field"><input id="quantitys' + rNo + '" type="number" min="1" step="1" class="validate" placeholder="Qty" name="quantitys" value="0"></td> <td> <a href="#" onclick="removeResource(' + rNo + ');" class="btn waves-effect waves-light red t-c ">' +
			            '<i class="fa fa-close"></i></a></td></tr>';
            $("#resourceFormTableBody").append(html);
            $("#rowNo").val(rNo);
            $('.searchable').select2();
        }
        
        
        function removeResource(rowNo){
        	$("#resourceFormTableRow"+rowNo).remove();
        }
        function submitResource(){
    		if(validator.form()){
    			var flag = true;
            	$('select[name=resource_types').each(function(){
            		var num = (this.id).replace('resource_types','');
            		
            		var resource_type = $("#resource_types"+num).val();
     				var resource_name = $("#resource_names"+num).val(); 
         	 		var quantity = $("#quantitys"+num).val();
         	 		if($.trim(resource_type) == '' || $.trim(resource_name) == '' || $.trim(quantity) == ''){
         	 			flag = false;
         	 		}
         	 	});
           	 	if(flag){
    	       	 	$(".page-loader").show();
    	       	 	document.getElementById("resourceForm").submit();
            	}else{
            		swal("All Fields required");
            	}						
    	 	}
    	}
        
        var validator = $('#resourceForm').validate({
       	 ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		   "contract_id_fk": {
     			 	 required: true
     			   },"structure_fk": {
     			 	 required: false
     			   },"date": {
     			 	 required: true,
     			 	 dateFormat : true
     			   }
         	 },messages: {
         		   "contract_id_fk": {
         				required: 'Requried'
    			   },"structure_fk": {
    					required: 'Requried'
    			   },"date": {
   		 	   		required: 'Requried'
   		 	   }
         	 },errorPlacement:function(error, element){
   	         if (element.attr("id") == "contract_id_fk" ){
   			     document.getElementById("contract_id_fkError").innerHTML="";
   	 			 error.appendTo('#contract_id_fkError');
   	 	     }else if (element.attr("id") == "structure_fk" ){
   			     document.getElementById("structure_fkError").innerHTML="";
   	 			 error.appendTo('#structure_fkError');
   	 	     }else if (element.attr("id") == "date" ){
   			     document.getElementById("dateError").innerHTML="";
   	 			 error.appendTo('#dateError');
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
     		    	//form.submit();
     		 }
   	});
   	$.validator.addMethod("dateFormat", function(value, element) {
   		return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
   	}, "Date format (DD-MM-YYYY)");
   	
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