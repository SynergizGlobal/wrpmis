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
     	 Risk Action Taken Report
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
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
        .input-field textarea{
        	border:none;
        	border-bottom:1px solid #9e9e9e;
        	border-radius:0;
        	height: 45px;
        }
        .input-field>textarea+label {
		    margin-left: 0;
		    padding-left: 0;
		    background-color: transparent;
		}
	    .error-msg label{color:red!important;}   
		textarea[readonly]{
			color:#999;
		}
		.center-align.m-1 button.bg-m.waves-effect, 
		.center-align.m-1 button.bg-s.waves-effect{
			width:inherit;
		}
		.mt-brdr{
			margin-top: 20px;
		    border-top: 1px solid #777;
		    border-bottom: 1px solid #777;
		}
		.error-msg{color:red!important;text-align: left;}
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
                            <div class="center-align p-2 bg-m m-b-2">
                               <h6>Action Taken Report </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
					<form action="<%=request.getContextPath() %>/update-risk-assessment" id="riskForm" name="riskForm" method="post" class="form-horizontal" role="form">
						
                        <div class="container container-no-margin">                          
                            	
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>                               
                                <div class="col s12 m4 input-field">
									<p class="searchable_label">Project :</p>
									<p>${risk.project_id_fk}- ${risk.project_name}</p>	
							    </div> 
                                <div class="col s12 m4 input-field"> 
								    <p class="searchable_label"> Work :</p>
									<p>${risk.work_id_fk}- ${risk.work_short_name}</p></div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work :</p>
									<p>${risk.sub_work}</p>
									<input type="hidden"  name="sub_work" value="${risk.sub_work }" /> 
                                </div>
                                <div class="col s12 m4 input-field">
                                     <p class="searchable_label"> Assessment Date :</p>
									 <p>${risk.assessment_date }</p>
                                </div>                               
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Area :</p>
									<p>${risk.area }</p>
                                </div>
                                <div class="col s12 m4 input-field">
									<p class="searchable_label"> Sub Area :</p>
									<p>${risk.sub_area_fk }</p>                                   	
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Owner :</p>
									<p>${risk.owner }</p>
									<input type="hidden"  name="owner_user_id" value="${risk.owner_user_id }" />
                                </div>
                                <div class="col s12 m4 input-field">
									<p class="searchable_label"> Responsible Person :</p>
									<p>${risk.responsible_person }</p>       
									<input type="hidden"  name="responsible_user_id" value="${risk.responsible_user_id }" />                            	
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>                               
                                <div class="col s12 m4 input-field">
									<p class="searchable_label">Priority :</p>
									<p>${risk.priority_fk}</p>	
							    </div>                                 
                            </div>
                            <div class="row">
                            	<div class="col s12 m8 input-field offset-m2"> 
								    <%-- <p class="searchable_label"> Mitigation Plan :</p>
									<p>${risk.mitigation_plan}</p> --%>
									<textarea id="mitigation_plan" name="mitigation_plan" class="materialize-textarea" data-length="1000" >${risk.mitigation_plan}</textarea>
									<label for="mitigation_plan">Mitigation Plan :</label>
								</div>
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
													<!-- 	<th style="width:25%">Assessment Date</th> -->
														<th style="width:25%">ATR Date</th>
														<th>Action Taken</th>
														<th class="fw-60">Action</th>
													</tr>
												</thead>
												<tbody id="riskRevisionBody">
													<c:choose>
														<c:when	test="${not empty risk.riskActions && fn:length(risk.riskActions) gt 0 }">
															<c:forEach var="aObj" items="${risk.riskActions }"	varStatus="index">
																<tr id="actionRow${index.count }">																	
					                                                <%-- <td>
					                                                    <select id="assessment_dates${index.count }" name="assessment_dates" class="select searchable">
					                                                        <option value="" selected>Select</option>
					                                                          <c:forEach var="obj" items="${assessmentDates }">
					                                      					   <option value= "${obj.risk_revision_id}" <c:if test="${aObj.risk_revision_id_fk eq obj.risk_revision_id}">selected</c:if>>${ obj.assessment_date}</option>
					                                        				  </c:forEach>
					                                                    </select>
					                                                </td> --%>
																	<td>
											 							<div class="input-field">
																			<input id="atr_dates${index.count }"
																				name="atr_dates" type="text"
																				class="validate datepicker" placeholder="ATR  Date"
																				value="${aObj.atr_date}"/>
																			<button type="button"
																				id="atr_date_icon${index.count }">
																				<i class="fa fa-calendar"></i>
																			</button>
																			<p id="atr_dates${index.count }Error" class="error-msg" ></p>
																		</div>
																		
																		
																	</td>
																	<td>
																		<textarea 
																			id="action_takens${index.count }"
																			name="action_takens" class="materialize-textarea"
																			placeholder="Action Taken" style="height: 44px;">${aObj.action_taken}</textarea>
																			<p id="action_takens${index.count }Error" class="error-msg" ></p>
																	</td>
																	<td>
																	<%-- <a onclick="removeActions('${index.count }');" class="btn waves-effect waves-light red t-c "> 
																	<i class="fa fa-close"></i></a> --%>
																	<a onclick="removeActions('${index.count }');" style="font-size: 20px;" class="btn red"> 
																	<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<%-- <td>
				                                                    <select id="assessment_dates0" name="assessment_dates" class="select searchable">
				                                                        <option value="" selected>Select</option>
				                                                          <c:forEach var="obj" items="${assessmentDates }">
				                                      					   <option value= "${obj.risk_revision_id}" >${obj.assessment_date}</option>
				                                        				  </c:forEach>
				                                                    </select>
				                                                </td> --%>
																<td><input type="hidden"
																	id="rowCounts0" name="rowCounts"
																	value="1" class="hide" />
																	<div class="input-field">
																		<input id="atr_dates0"
																			name="atr_dates" type="text"
																			class="validate datepicker" placeholder="ATR  Date">
																		<button type="button"
																			id="atr_date_icon0">
																			<i class="fa fa-calendar"></i>
																		</button>
																		<p id="atr_dates0Error" class="error-msg" ></p>
																	</div>
																</td>
																<td><textarea id="action_takens0"
																		name="action_takens" class="materialize-textarea"
																		placeholder="Action Taken" style="height: 44px;"></textarea>
																		<p id="action_takens0Error" class="error-msg" ></p>
																</td>
																<td>
																	<!-- <a onclick="removeActions('0');"
																		class="btn waves-effect waves-light red t-c "> <i
																		class="fa fa-close"></i></a> -->
																		
																		<a onclick="removeActions('0');" style="font-size: 20px;" class="btn red"> 
																	<i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
											<table class="mdl-data-table">
												<tbody>
													<tr>
														<td colspan="6" ><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c "
															onclick="addRiskRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (risk.riskActions) && fn:length(risk.riskActions) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo"
														value="${fn:length(risk.riskActions) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							
							<input  type="hidden"   name="risk_id_pk" value="${risk.risk_id_pk }" />
							<input  type="hidden"   name="risk_revision_id" value="${risk.risk_revision_id }" />
							<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 mt-brdr">
                                
                                    <!-- <div class="center-align m-1">
										<button type="button" onclick="updateRisk();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
									</div> -->
									
									<div class="center-align m-1">
                                    	<c:if test="${risk.readonlyForm eq false }">
                                        	<button type="button" onclick="updateRisk();" class="btn waves-effect waves-light bg-m">Update</button>
                                        </c:if>
                                        <c:if test="${risk.readonlyForm eq true }">
                                        	<a style="color:red;line-height: 36px;">Not Authorized to Edit</a>
                                        </c:if>
                                    </div>
                                    
                                </div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/risk-atr-update" class="btn waves-effect waves-light bg-s"
                                            >Cancel</a>
                                           <!--  <a href="javascript:void(0);" onclick="closeTab();" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>  -->
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
 	<script type="text/javascript">
 	
 	   var USER_DESIGNATION = '${sessionScope.USER_DESIGNATION}';
 	   
 	   /* $(document).on('focus', '.datepicker',function(){
           $(this).datepicker({
         	format:'dd-mm-yyyy',
    	    	onSelect: function () {
    	    	   $('.confirmation-btns .datepicker-done').click();
    	    	}
           })
        });  */
        
        var year = '';
       	var month = '';
       	var day = '';
       	
        var assessment_date = '${risk.assessment_date }';
        if($.trim(assessment_date) != '' ){
        	var sDate = assessment_date.split("-");
        	year = sDate[2];
        	month = sDate[1];
        	day = sDate[0];
        }
        
        var minDate = new Date(year,month-1,day);
        
        var dates_arr = [];
       	$("input[name=atr_dates]").each(function(){
        	dates_arr.push(this.value);
        });
        
        let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy',
	    			autoClose:true,
	    			minDate:minDate,
	    			maxDate: new Date(),
	    			onDraw : function() {disableDates("ondraw")},
	    			onOpen : function() {disableDates("onopen")},
	    			disableDayFn: function(date){
		   	    		var string = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
		   	    		var ex_dates = [];
		   	    		for (var i = 0; i < dates_arr.length; i++) {
			            	 var year = '';
			                 var month = '';
			                 var day = '';
			                 if($.trim(dates_arr[i]) != '' ){
			                 	var sDate = dates_arr[i].split("-");
			                 	year = sDate[2];
			                 	month = sDate[1]-1;
			                 	day = sDate[0];
			                 	var fullDisabledDate = new Date(year,month,day),
			                    getDisabledMonth    = fullDisabledDate.getMonth(),
			                    getDisabledDay      = fullDisabledDate.getDate(),
			                    getDisabledYear     = fullDisabledDate.getFullYear();
			                 	var d = fullDisabledDate.getDate() + "-" + (fullDisabledDate.getMonth() + 1) + "-" + fullDisabledDate.getFullYear();
			                 	ex_dates.push(d);
			                 }
			            }
		   	    		
		   	    		for (var i = 0; i < ex_dates.length; i++) {
			   	    		if(ex_dates[i] == string){
			   	    			return true;
			   	    		}
		   	    		}
		   	    		
			   	        //return [ ex_dates.indexOf(string) == 1 ]
			   	    }
	    		};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            
            if('${risk.readonlyForm}' == 'true'){
	            $("#riskForm :input").attr("disabled", true);
	            //$("#riskForm :textarea").attr("disabled", true);	            
	            $("#riskForm select").prop("disabled", true);	            
            }
           if(USER_DESIGNATION == '${risk.owner}'){
        	   $("#mitigation_plan").prop("readonly", false); 
           	   $('#mitigation_plan').characterCounter();            
           } else{
        	   $("#mitigation_plan").prop("readonly", true); 
           }
            
        });
        
        function closeTab(){
        	 window.top.close();
        }
        
        function disableDates(msg) {        	
        	var dates_arr = [];
        	$("input[name=atr_dates]").each(function(){
            	dates_arr.push(this.value);
            });
        	
            for (var i = 0; i < dates_arr.length; i++) {
            	 var year = '';
                 var month = '';
                 var day = '';
                 if($.trim(dates_arr[i]) != '' ){
                 	var sDate = dates_arr[i].split("-");
                 	year = sDate[2];
                 	month = sDate[1]-1;
                 	day = sDate[0];
                 	var fullDisabledDate = new Date(year,month,day),
                    getDisabledMonth    = fullDisabledDate.getMonth(),
                    getDisabledDay      = fullDisabledDate.getDate(),
                    getDisabledYear     = fullDisabledDate.getFullYear();
                    $('.datepicker-modal.open').find('button.datepicker-day-button[data-year="'+getDisabledYear+'"][data-month="'+getDisabledMonth+'"][data-day="'+getDisabledDay+'"]').parent('td').addClass('is-disabled');
                 }
            }
        }
        var idNo = "";
        function addRiskRow() {   
        	var dates_arr = [];
        	$("input[name=atr_dates]").each(function(){
            	dates_arr.push(this.value);
            });
        	//alert(dates_arr);
        	/*******************************************************************/
        	
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="actionRow' + rNo + '">'
            /*    +'<td> <div>'
			   +'<select  name="assessment_dates" id="assessment_dates'+rNo+'" class="select searchable">'	   			
			   +'<option value="" >Select</option>'
			   <c:forEach var="obj" items="${assessmentDates }">
				  +' <option value= "${obj.risk_revision_id}">${obj.assessment_date}</option>'
			  </c:forEach>
			+'</select></div></td>' */
			+'<td><div class="input-field"><input id="atr_dates' + rNo +'" name="atr_dates" type="text"  class="validate datepicker" placeholder="ATR  Date">'
			+'<button type="button" id="atr_date_icon' + rNo + '"><i class="fa fa-calendar"></i></button>'
			+'</div><p id="atr_dates' + rNo + 'Error" class="error-msg" ></p></td>'
			+'<td><textarea id="action_takens' + rNo +'"  name="action_takens" '
			+'class="materialize-textarea"  placeholder="Action Taken"style="height: 44px;"></textarea><p id="action_takens' + rNo + 'Error" class="error-msg" ></p></td>'
			+'<td><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td></tr>';
		
			$('#riskRevisionBody').append(html);
            $("#rowNo").val(rNo);
          	idNo = $("#rowNo").val();
            //$("#atr_dates" + rNo).datepicker();
            
            $('#atr_dates' + rNo).datepicker({
            	minDate: minDate,
            	maxDate: new Date(),
	        	format:'dd-mm-yyyy',
	        	autoClose:true,
	   	    	onDraw : function() {disableDates("ondraw")},
	   	        onOpen : function() {disableDates("onopen")},
	   	        onSelect:function(date){
	   	        	var dates_arr = [];
	   	        	$("input[name=atr_dates]").each(function(){
	   	            	dates_arr.push(this.value);
	   	            });
	   	        	//console.log(dates_arr.length)
	   	            for (var i = 0; i < dates_arr.length; i++) {
	   	            	 var year = '';
	   	                 var month = '';
	   	                 var day = '';
	   	                 if($.trim(dates_arr[i]) != '' ){
	   	                 	var sDate = dates_arr[i].split("-");
	   	                 	year = sDate[2];
	   	                 	month = sDate[1]-1;
	   	                 	day = sDate[0];
	   	                 	var fullDisabledDate = new Date(year,month,day),
	   	                    getDisabledMonth    = fullDisabledDate.getMonth(),
	   	                    getDisabledDay      = fullDisabledDate.getDate(),
	   	                    getDisabledYear     = fullDisabledDate.getFullYear();
	   	                 	//console.log(date.getDate() +" - "+date.getMonth()+" - "+date.getFullYear());
	 	   	        		//console.log(fullDisabledDate.getDate() +" - "+fullDisabledDate.getMonth()+" - "+fullDisabledDate.getFullYear());
	 	   	        	
	   	                    if(date.getDate() == fullDisabledDate.getDate() && date.getMonth() == fullDisabledDate.getMonth() && date.getFullYear() == fullDisabledDate.getFullYear()){
	   	                    	//$('.page-loader').show();
	   	                    	//setTimeout(function(){$('#atr_dates' + rNo).val('');$('.page-loader').hide();}, 1000);
	   	                    }
	   	                 }
	   	            }
	   	        },
	   	     	disableDayFn: function(date){
	   	    		var string = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
	   	    		//alert(dates_arr.indexOf(string) == -1);
	   	    		var ex_dates = [];
	   	    		for (var i = 0; i < dates_arr.length; i++) {
		            	 var year = '';
		                 var month = '';
		                 var day = '';
		                 if($.trim(dates_arr[i]) != '' ){
		                 	var sDate = dates_arr[i].split("-");
		                 	year = sDate[2];
		                 	month = sDate[1]-1;
		                 	day = sDate[0];
		                 	var fullDisabledDate = new Date(year,month,day),
		                    getDisabledMonth    = fullDisabledDate.getMonth(),
		                    getDisabledDay      = fullDisabledDate.getDate(),
		                    getDisabledYear     = fullDisabledDate.getFullYear();
		                 	var d = fullDisabledDate.getDate() + "-" + (fullDisabledDate.getMonth() + 1) + "-" + fullDisabledDate.getFullYear();
		                 	ex_dates.push(d);
		                 }
		            }
	   	    		
	   	    		for (var i = 0; i < ex_dates.length; i++) {
		   	    		if(ex_dates[i] == string){
		   	    			//console.log(ex_dates[i] + " : " +string);
		   	    			return true;
		   	    		}
	   	    		}
	   	    		
		   	        //return [ ex_dates.indexOf(string) == 1 ]
		   	    }
	        }); 
            
            $('#atr_date_icon' + rNo).click(function () {
                event.stopPropagation();
                $('#atr_dates' + rNo).click();
            });
            
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        }
        
        function removeActions(rowNo){
        	$("#actionRow"+rowNo).remove();
        }
      
        function updateRisk(){	       	
   			$(".page-loader").show();
   			var flag = validateRisk();
   			if(flag){
   				$('form input[name=atr_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   		   		$('form input[name=action_takens]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   	   			$("#riskForm").submit();
   			}
   			$(".page-loader").hide();
   			
        }
        
        function validateRisk(){
        	var flag = true;
        	$("input[name=atr_dates]").each(function(){
        		//console.log((this.id).replace('atr_dates',''));
        		var idNo = (this.id).replace('atr_dates','');
        		var action_taken = $("#action_takens"+idNo).val();
        		if($.trim(this.value) == "" && $.trim(action_taken) != ""){
					$('#'+this.id+'Error').text('Requried');
					flag = false;
				}else if($.trim(this.value) != "" && $.trim(action_taken) == ""){
					$('#action_takens'+idNo+'Error').text('Requried');
					flag = false;
				}
            });
        	return flag;
        }
        
	    $('input[name=atr_dates]').change(function(key, element){
	    	$("input[name=atr_dates]").each(function(){
        		if($.trim(this.value) == ""){
					$('#'+this.id+'Error').text('Requried');
				}else{
					$('#'+this.id+'Error').text('');
				}
            });
        	$("textarea[name=action_takens]").each(function(){
        		if($.trim(this.value) == ""){
					$('#'+this.id+'Error').text('Requried');
				}else{
					$('#'+this.id+'Error').text('');
				}
            });
		});
		$('textarea[name=action_takens]').change(function(){
			$("input[name=atr_dates]").each(function(){
        		if($.trim(this.value) == ""){
					$('#'+this.id+'Error').text('Requried');
				}else{
					$('#'+this.id+'Error').text('');
				}
            });
        	$("textarea[name=action_takens]").each(function(){
        		if($.trim(this.value) == ""){
					$('#'+this.id+'Error').text('Requried');
				}else{
					$('#'+this.id+'Error').text('');
				}
            });
		
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