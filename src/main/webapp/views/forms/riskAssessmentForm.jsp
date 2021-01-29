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
     	 <c:if test="${action eq 'edit'}">Update Risk Assessment</c:if>
		 <c:if test="${action eq 'add'}"> Add Risk Assessment</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
        <style>
        textarea::placeholder {
            color: #444;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td {
            position: relative;
        }

        #riskReview {
            border: 1px solid #ddd;
        }

        #riskReview .datepicker~button {
            right:5px;
        } 

        #riskReview td .select2-container {
            width: 120px;
            max-width: 120px;
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

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
          .modal-header {
            text-align: center;
            background-color: #999999;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
            font-size: 24px;
        }

        .update-table {
            border: 1px solid #ddd;
        }

        table.update-table tbody td .datepicker~button {
            top: 15px !important;
            right: 1px;
        }

        table.update-table tbody td .input-field {
            margin-top: 0;
            margin-bottom: 0;
        }
         table.update-table tbody td .modal.datepicker-modal.open {
            width: 85% !important;
        }
        
        .fw-60 {
            max-width: 60px;
            width: 60px !important;
        }
        .fw-110{
        	 max-width: 100px;
            width: 100px !important;
        }        
        .fw-150 {
            max-width: 150px;
            width: 150px !important;
        }
        .fw-180 {
            max-width: 180px;
            width: 180px !important;
        }
       tbody .select2-container--default .select2-selection--single {
        	background-color:transparent;
        }
         .modal:not(.datepicker-modal){
        	max-height:90%;
        	width:62%;
        	min-height:75%;
        }
       
        .datepicker-table th, 
        .datepicker-table td,
        .mdl-data-table tbody td .datepicker-table th,
        .mdl-data-table tbody td .datepicker-table .datepicker-row td{
        	padding:0 !important;
        	height:44px !important;
        }
        .mdl-data-table tbody td .datepicker-table .datepicker-row{
        	bottom-border:none;
        }
        
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
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                               <h6> 	Action Taken Report </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
							<form action="<%=request.getContextPath() %>/update-risk" id="riskForm" name="riskForm" method="post" class="form-horizontal" role="form">
						
                        <div class="container container-no-margin">                          
                            	
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>                               
                                <div class="col s12 m4 input-field">
									<p class="searchable_label">Project :</p>
									<p>${riskDetails.project_id_fk}- ${riskDetails.project_name}</p>	
							    </div> 
                                <div class="col s12 m4 input-field"> 
								    <p class="searchable_label"> Work :</p>
									<p>${riskDetails.work_id_fk}- ${riskDetails.work_short_name}</p></div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                            <input  type="hidden"   name="risk_id_pk" value="${riskDetails.risk_id_pk }" />
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Sub Work :</p>
									<p>${riskDetails.work_id_fk}- ${riskDetails.work_short_name}</p>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <p class="searchable_label"> Risk ID :</p>
									 <p>${riskDetails.risk_id }-</p>
                                </div>                                
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Area :</p>
									<p>${riskDetails.area }-</p>
                                </div>
                                <div class="col s12 m4 input-field">
									<p class="searchable_label"> Sub Area :</p>
									<p>${riskDetails.sub_area_fk }-</p>                                   	
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Owner :</p>
									<p>${riskDetails.owner }-</p>
                                </div>
                                <div class="col s12 m4 input-field">
									<p class="searchable_label"> Responsible Person :</p>
									<p>${riskDetails.responsible_person }-</p>                                   	
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                       
                       <!--  </div> -->
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="row fixed-width"
										style="margin-bottom: 40px; margin-top: 20px">
										<div class="table-inside">
											<table id="riskReview" class="mdl-data-table update-table">
												<thead>
													<tr>
														<th style="width:25%">ATR Date</th>
														<th>Action Taken</th>
														<th class="fw-60">Action</th>
													</tr>
												</thead>
												<tbody id="riskRevisionBody">
													<c:choose>
														<c:when	test="${not empty rObj.riskActions && fn:length(rObj.riskActions) gt 0 }">
															<c:forEach var="aObj" items="${rObj.riskActions }"	varStatus="indexx">
																<tr id="actionRow${indexx.count }">
																	<td><input type="hidden"  id="rowCounts${indexx.count }"
																		name="rowCounts" class="hide" /> 
																		<input type="hidden" name="risk_action_ids"
																		id="risk_action_ids${indexx.count }"	value="${aObj.risk_action_id}" />
																		<div class="input-field">
																			<input id="atr_dates${indexx.count }"
																				name="atr_dates" type="text"
																				class="validate datepicker" placeholder="ATR  Date"
																				value="${ aObj.atr_date}">
																			<button type="button"
																				id="atr_date_icon${indexx.count }">
																				<i class="fa fa-calendar"></i>
																			</button>
																		</div></td>
																	<td><textarea
																			id="action_takens${indexx.count }"
																			name="action_takens" class="materialize-textarea"
																			placeholder="Action Taken" style="height: 44px;">${ aObj.action_taken}</textarea>
																	</td>
																	<td><a
																		onclick="removeactions('${indexx.count }');prevRow('${index.count }')"
																		class="btn waves-effect waves-light red t-c "> <i
																			class="fa fa-close"></i></a></td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow00${index.count }">
																<td><input type="hidden"
																	id="rowCounts0${index.count }" name="rowCounts"
																	value="1" class="hide" />
																	<div class="input-field">
																		<input id="atr_dates0${index.count }"
																			name="atr_dates" type="text"
																			class="validate datepicker" placeholder="ATR  Date">
																		<button type="button"
																			id="atr_date_icon00${index.count }">
																			<i class="fa fa-calendar"></i>
																		</button>
																	</div></td>
																<td><textarea id="action_takens0${index.count }"
																		name="action_takens" class="materialize-textarea"
																		placeholder="Action Taken" style="height: 44px;"></textarea>
																</td>
																<td><a
																	onclick="removeactions('0${index.count }');"
																	class="btn waves-effect waves-light red t-c "> <i
																		class="fa fa-close"></i></a></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
											<table class="mdl-data-table">
												<tbody id="actionBody">
													<tr>
														<td colspan="6" style="text-align: right;"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c "
															onclick="addRiskRow('${index.count }')"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (rObj.riskActions) && fn:length(rObj.riskActions) gt 0 }">
													<input type="hidden" id="trainNo" name="trainNo"
														value="${fn:length(rObj.riskActions) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="trainNo" name="trainNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                   <div class="center-align m-1">
										<button type="button" onclick="updateRisk();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
								</div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/risk" class="btn waves-effect waves-light bg-s black-text"
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
          /*   $("#dates0,#date_of_identification").datepicker();
            $('#date_icon0').click(function () {
                event.stopPropagation();
                $('#dates0').click();
            });
            $("#atr_date0").datepicker();
            $('#atr_date_icon0').click(function () {
                event.stopPropagation();
                $('#atr_dates0').click();
            });
            $('.modal').modal();
            $('#date_of_identification_icon').click(function () {
                event.stopPropagation();
                $('#date_of_identification').click();
            });
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter(); */
            var projectId = "${riskDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            getSubAreasList();
            getAreasList();
           
        });
        function getAreasList() {
        	$(".page-loader").show();
        	var area = $("#area").val();
        	var sub_area_fk = $("#sub_area_fk").val();
            if ($.trim(area) == "") {
            	$("#area option:not(:first)").remove();
            	var myParams = { sub_area_fk: sub_area_fk, area: area };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAreasList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                              	 var area = "${riskDetails.area}";
                              	 
      	                           if ($.trim(area) != '' && val.area == $.trim(area)) {
                                         $("#area").append('<option value="' + val.area + '" selected>' + $.trim(val.area) + '</option>');
                                     } else {
                                         $("#area").append('<option value="' + val.area + '">' + $.trim(val.area) + '</option>');
                                     }
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
     
        function getSubAreasList() {
        	$(".page-loader").show();
        	var area = $("#area").val();
        	var sub_area_fk = $("#sub_area_fk").val();
            if ($.trim(sub_area_fk) == "") {
            	$("#sub_area_fk option:not(:first)").remove();
            	var myParams = { sub_area_fk: sub_area_fk, area: area };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubAreasList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	
                            	 var subArea = "${riskDetails.sub_area_fk}";
                            	 
    	                           if ($.trim(subArea) != '' && val.sub_area_fk == $.trim(subArea)) {
                                       $("#sub_area_fk").append('<option value="' + val.sub_area_fk + '" selected>' + $.trim(val.sub_area_fk) + '</option>');
                                   } else {
                                       $("#sub_area_fk").append('<option value="' + val.sub_area_fk + '">' + $.trim(val.sub_area_fk) + '</option>');
                                   }
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
                                    var workId = "${riskDetails.work_id}";
                                    if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                    
                                    if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
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
    	//This function is used to get error message for all ajax calls
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
        
        
        
        function addRiskRow(revisionId) {
        	
            var rowNo = $("#rowNo").val();
            var riskNo = Number(rowNo)+1;
            var trainNo = $("#trainNo").val();
            var tNo = Number(trainNo)+1;
            var html = '<tr id="riskReviewRow' + riskNo + '">'+
			'<td><div class="input-field"><input id="atr_dates' + riskNo +'" name="atr_dates" type="text"  class="validate datepicker" placeholder="ATR  Date">'+
			'<button type="button" id="atr_date_icon' + riskNo + '"><i class="fa fa-calendar"></i></button></div></td>'+
			'<td><input type="hidden" id="rowCounts' + riskNo + '" name="rowCounts" class="hide" /><textarea id="action_takens' + riskNo +'"  name="action_takens" '+
			'class="materialize-textarea"  placeholder="Action Taken"style="height: 44px;"></textarea></td>'+
			'<td><a onclick="removeRiskRow(' + riskNo + '); prevRow('+tNo+')" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td></tr>';
		
			$('#riskRevisionBody').append(html);
            $("#rowNo").val(riskNo);
            $('#update_action'+riskNo).modal();
          	/*   $("#dates" + riskNo).datepicker();
            $('#reveiw_date_icon' + riskNo).click(function () {
                event.stopPropagation();
                $('#dates' + riskNo).click();
            }); 
            $("#atr_dates" + riskNo+tNo).datepicker();
            $('#atr_date_icon' + riskNo+tNo).click(function () {
                event.stopPropagation();
                $('#atr_dates' + riskNo+tNo).click();
            });
            $('.searchable').select2();*/		
        }
        
        function removeRiskRow(rowNo){
        	$("#riskReviewRow"+rowNo).remove();
        }
     
        var rowNumber = null;
        function showNo(a){
        	rowNumber = a.href.split("#")[1].split("_")[1].split('action')[1];        	
        	console.log($('#riskReviewRow'+rowNumber));
        }
        
        function prevRow(tNo){
       	 var id = $('#actionTableBody'+tNo+' tr .hide:last').attr('id');
       	 if(id == null){
         	id='s0';
         }
            var splt = id.split('s')[1];
            var c = $('#actionTableBody'+tNo+' tr').length;
            if(splt > 0){
            	var lastIndex = splt;
          	    var lastRow = $('#actionTableBody'+tNo+' #rowCounts'+lastIndex).removeAttr("disabled");
          	 $('#actionTableBody'+tNo+' #rowCounts'+lastIndex+':last').val(c)
            }else{
           	 $("#rowCounts0").removeAttr("disabled");
            }
       	
       }
      
        function updateRisk(){
       	 if(validator.form()){ // validation perform
   			$(".page-loader").show();
   			$('form input[name=atr_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=action_takens]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=owners]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=responsible_persons]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=prioritys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=probabilitys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=impacts]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=mitigation_plans]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			document.getElementById("riskForm").submit();	
      	}
        }
      
            
        var validator =	$('#riskForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"risk_id": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"risk_id": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_idError").innerHTML="";
				 		 error.appendTo('#project_idError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "risk_id" ){
						   document.getElementById("risk_idError").innerHTML="";
					 	   error.appendTo('#risk_idError');
					}else{
 					error.insertAfter(element);
			        } 
		   		},submitHandler:function(form){
			    	//form.submit();
			    }
			});   
	            
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