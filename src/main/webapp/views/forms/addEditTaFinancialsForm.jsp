<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>
     	 <c:if test="${action eq 'edit'}">Update TA Financial - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}">Add TA Financial - Update Forms - PMIS</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/finance.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" > 
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        input[type="month"] {
            height: 35px;
            background-color: transparent;
            border: 0;
            outline: 0;
            border-bottom: 1px solid #aaa;
            font-size: 1rem;
        }

        input[type=month]:not(.browser-default):focus:not([readonly]) {
            border-bottom: 1px solid #F99934;
            box-shadow: 0 1px 0 0 #F99934;
        }

        #financialFormTable .input-field .prefix {
            top: 1.55rem;
        }

        .fixed-width {
            width: 100%;
            margin-left:auto !important;
            margin-right:auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
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
		label.my-error-class{
			position: relative;
    		font-size: 0.85rem;
		}
		.m-b-2{
			margin-bottom:2rem;
		}
		.row.no-mar{
			margin-bottom:0
		}
		@media only screen and (max-width: 768px){
			input[type="month"]{
				width: -webkit-fill-available;
				height:40px;
				box-shadow:inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
			}
		}
    </style>
</head>

<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                 <c:if test="${action eq 'edit'}">Update TA Financial</c:if>
								 <c:if test="${action eq 'add'}">Add TA Financial</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
	                   	 <c:if test="${action eq 'edit'}">				                
					           <form action="<%=request.getContextPath() %>/update-ta-financials" id="taFinancialForm" name="taFinancialForm" method="post" class="form-horizontal" role="form" >
		                 </c:if>
					     <c:if test="${action eq 'add'}">				                
					           <form action="<%=request.getContextPath() %>/add-ta-financials" id="taFinancialForm" name="taFinancialForm" method="post" class="form-horizontal" role="form">
						</c:if>
                        <div class="container container-no-margin">
                         <c:if test="${action eq 'add'}">	
                            <div class="row" 0>
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${worksList }">
                                            <option value="${obj.work_id_fk }" >${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                        </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Contract <span class="required">*</span></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown"  onchange="resetWorksList();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                            <option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }" >${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                        </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            </c:if>
                        </div>
                         <div>
                       		<c:if test="${action eq 'edit'}">	
                       		 <div class="row no-mar" >
                       		  <div class="col s6 m4 input-field offset-m2">
                                    <input type="text" name="work_id_fk" id="work_id_fk" value="${taFinancialDetails.work_id_fk}- ${taFinancialDetails.work_short_name}" readonly />
                                    <label for="work_id_fk"> Work <span class="required">*</span></label>
							  </div> 
							  <div class="col s6 m4 input-field"> 
                                    <input type="text" value="${taFinancialDetails.contract_id_fk}-${taFinancialDetails.contract_short_name}" readonly />
                                    <input type="hidden" name="contract_id_fk" id="contract_id_fk" value="${taFinancialDetails.contract_id_fk}" readonly />      
                                    <label for="contract_id_fk"> Contract <span class="required">*</span></label>           	 	
                              </div>
                        	  </div> 
                           </c:if>
                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m8 offset-m2">
                                <div class="row fixed-width">
                                    <h5 class="center-align">TA Financial Details</h5>
                                    <div class="table-inside">
                                        <table id="financialFormTable" class="mdl-data-table mobile_responsible_table">
                                            <thead>
                                                <tr>
                                                    <th>Month </th>
                                                    <th>Planned Invoicing </th>
                                                    <th>Actual Invoicing </th>
                                                    <th>Payment Received </th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="financialTableBody">
                                             <c:choose>
		                                      	 <c:when test="${not empty taFinancialDetails.taFinancials && fn:length(taFinancialDetails.taFinancials) gt 0 }">
		                                       	 <c:forEach var="sObj" items="${taFinancialDetails.taFinancials }" varStatus="index"> 
                                       	 
	                                                <tr id="financialRow${index.count }">
	                                                    <td data-head="Month" class="input-field"> <input type="hidden" name= "IDs" id="IDs${index.count }" value="${sObj.ID }"/>
	                                                    <input id="months${index.count }" name="months" type="month" class="validate" value="${sObj.month }"
	                                                            placeholder="Select Month">
	                                                        <span id="month${index.count }sgst_tdsError" class="error-msg"></span>
	                                                    </td>
	                                                    <td data-head="Planned Invoicing" class="input-field">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="planned_invoice${index.count }" type="number" step="0.01" value="${sObj.planned }"
	                                                            min="0.01" class="validate" name="planneds"
	                                                            placeholder="Planned Invoicing">
	                                                        <span id="planned_invoice${index.count }Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td data-head="Actual Invoicing" class="input-field">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="actual_invoices${index.count }" type="number" step="0.01" min="0.01" value="${sObj.actual }"
	                                                            class="validate" name="actuals"
	                                                            placeholder="Actual Invoicing">
	                                                        <span id="actual_invoice${index.count }Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td data-head="Payment Received" class="input-field">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="payment_receiveds${index.count }" type="number" step="0.01" value="${sObj.payment_received }"
	                                                            min="0.01" class="validate" name="payment_receiveds"
	                                                            placeholder="Payment Received">
	                                                        <span id="payment_received${index.count }Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td class="mobile_btn_close">
	                                                        <a onclick="removeFinancial('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
	                                                                class="fa fa-close"></i></a>
	                                                    </td>
	                                                </tr>
	                                               </c:forEach>
                                      			 </c:when>
                                       			<c:otherwise>
                                       				 <tr id="financialRow0">
	                                                    <td data-head="Month" class="input-field"> <input id="months0" name="months" type="month" class="validate"
	                                                            placeholder="Select Month">
	                                                        <span id="month0sgst_tdsError" class="error-msg"></span>
	                                                    </td>
	                                                    <td data-head="Planned Invoicing" class="input-field">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="planned_invoices0" type="number" step="0.01"
	                                                            min="0.01" class="validate" name="planneds"
	                                                            placeholder="Planned Invoicing">
	                                                        <span id="planned_invoice0Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td class="input-field" data-head="Actual Invoicing">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="actual_invoices0" type="number" step="0.01" min="0.01"
	                                                            class="validate" name="actuals"
	                                                            placeholder="Actual Invoicing">
	                                                        <span id="actual_invoice0Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td class="input-field" data-head="Payment Received">
	                                                        <i class="material-icons prefix center-align">₹</i>
	                                                        <input id="payment_receiveds0" type="number" step="0.01"
	                                                            min="0.01" class="validate" name="payment_receiveds"
	                                                            placeholder="Payment Received">
	                                                        <span id="payment_received0Error" class="error-msg"></span>
	                                                    </td>
	                                                    <td class="mobile_btn_close">
	                                                        <a onclick="removeFinancial('0');" class="btn waves-effect waves-light red t-c "> <i
	                                                                class="fa fa-close"></i></a>
	                                                    </td>
	                                                </tr>
	                                               </c:otherwise>
                                     			 </c:choose>
                                            </tbody>
	                                        </table>
											<table class="mdl-data-table">
		                                        <tbody id="safetyBody">                                          
					                                    <tr>
					  										 <td colspan="5" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c" onclick="addFinancialRow()"> <i
					                                                            class="fa fa-plus"></i></a>
					                                    </tr>
		                                        </tbody>
                                   		   </table>
                                   		   <c:choose>
		                                        <c:when test="${not empty taFinancials && fn:length(taFinancialDetails.taFinancials) gt 0 }">
		                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(taFinancialDetails.taFinancials) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
		                                     	</c:otherwise>
                                      	 </c:choose> 
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 mt-brdr">                                  
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateTAFinancial();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addTAFinancial();"  class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/ta-financials" class="btn waves-effect waves-light bg-s">Cancel</a>
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
 <jsp:include page="../layout/footer.jsp"></jsp:include>
    <!-- footer  -->

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            // $('#textarea1,#textarea2,#textarea3').characterCounter();
            $("#funding_date").datepicker();

            $('#funding_date_icon').click(function () {
                event.stopPropagation();
                $('#funding_date').click();
            });


        });
        
        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForFinancialsForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                                $("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
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
        
        function resetWorksList(){
        	$(".page-loader").show();        	
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){        			
       		
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
       			//workId = workId.substring(3, work_id.length);
       			projectId = workId.substring(0, 3);
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForFinancialsForm",
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
        
        function addFinancialRow() {
        	var rowNo = $("#rowNo").val();
            var No = Number(rowNo)+1;
             
            var html = ' <tr id="financialRow' + No + '"> <input type="hidden" name= "IDs" id="IDs'+No+'" />'+
            '<td data-head="Month" class="input-field"> <input id="months' + No + '"  name="months" type="month" class="validate" placeholder="Select Month">' +
                '<span id="month' + No + 'Error" class="error-msg"></span></td >'+
                '<td data-head="Planned Invoicing" class="input-field"> <i class="material-icons prefix center-align">₹</i>' +
                '<input id="planned_invoices' + No + '" type="number" step="0.01" min="0.01" class="validate" name="planneds" placeholder="Planned Invoicing">' +
                '<span id="planned_invoice' + No + 'Error" class="error-msg"></span> </td>'+
                '<td data-head="Actual Invoicing" class="input-field"><i class="material-icons prefix center-align">₹</i><input id="actual_invoices' + No + '" type="number" step="0.01" min="0.01" class="validate" name="actuals" placeholder="Actual Invoicing">' +
                '<span id="actual_invoice' + No + 'Error" class="error-msg"></span> </td> <td data-head="Payment Received" class="input-field"><i class="material-icons prefix center-align">₹</i>' +
                '<input id="payment_receiveds' + No + '" type="number" step="0.01" min="0.01" class="validate" name="payment_receiveds" placeholder="Payment Received">' +
                '<span id="payment_received' + No + 'Error" class="error-msg"></span></td>'+
                '<td class="mobile_btn_close"> <a onclick="removeFinancial('+ No +');" class="btn waves-effect waves-light red t-c "> ' +
                '<i class="fa fa-close"></i></a></td></tr>';
                $('#financialTableBody').append(html);
				$("#rowNo").val(rNo);
        }
        
        function removeFinancial(rowNo){
        	$("#financialRow"+rowNo).remove();
        }
        
        function addTAFinancial(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=months]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=planneds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=actuals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=payment_receiveds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("taFinancialForm").submit();	
        	}
        }
        
        function updateTAFinancial(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=months]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=planneds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=actuals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=payment_receiveds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("taFinancialForm").submit();	
        	}
        }
        
        var validator =	$('#taFinancialForm').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  			 	  "work_id_fk": {
	  			 		required: true
	  			 	  },"contract_id_fk": {
	  		 		    required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"contract_id_fk": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if(element.attr("id") == "work_id_fk" ){
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