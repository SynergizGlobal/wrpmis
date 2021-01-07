<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<title>
 		 <c:if test="${action eq 'edit'}">Update Expenditure</c:if>
		 <c:if test="${action eq 'add'}"> Add Expenditure</c:if>
 	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
          
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue
        }
        .input-field .searchable_label {
            font-size: 0.85rem;
        }        
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
                                <h6>
                                 <c:if test="${action eq 'edit'}">Update Expenditure</c:if>
								 <c:if test="${action eq 'add'}"> Add Expenditure</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
				                	<form action="<%=request.getContextPath() %>/update-expenditure" id="expenditureForm" name="expenditureForm" method="post" >
	                          </c:if>
				              <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-expenditure" id="expenditureForm" name="expenditureForm" method="post" >
							  </c:if>
							<c:if test="${action eq 'add'}">	
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Project</p>
                                     <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                   		 onchange="getWorksList(this.value);">
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                     </select>
                                     <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_name}"> - </c:if> ${obj.work_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Contract</p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option contractorName="${obj.contractor_name }" workId="${obj.work_id_fk }" value= "${ obj.contract_id}">${obj.contract_id}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" readonly="readonly">
                                    <label for="contractor_name">Contractor Name</label>
                                    <span id="contractor_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                           </c:if>
                           <c:if test="${action eq 'edit'}">	
	                       	 	<div class="row">
		                       	 	  <div class="col m2 hide-on-small-only"></div>
		                       		  <div class="col s12 m4 input-field">
											<p><label> Project </label></p>
		                                    <input type="text" value="${expenditureDetails.project_id_fk} - ${expenditureDetails.project_name}" readonly />
									  </div> 
									  <div class="col s12 m4 input-field"> 
										    <p><label> Work </label></p>
		                                    <input type="text" value="${expenditureDetails.work_id_fk} - ${expenditureDetails.work_name}" readonly />
		                              </div>
	                             </div> 
	                             <div class="row">
		                       	 	  <div class="col m2 hide-on-small-only"></div>
		                       		  <div class="col s12 m4 input-field">
											<p><label>Contract </label></p>        
	                              			<input type="text" value="${expenditureDetails.contract_id_fk} - ${expenditureDetails.contract_name}" readonly /><br><br>
									  </div> 
									  <div class="col s12 m4 input-field"> 
										    <p><label>Contractor name </label></p>
		                                    <input type="text" id="contractor_name" name="contractor_name" value="${expenditureDetails.contractor_name}" readonly />
		                              </div>
		                              <div class="col m2 hide-on-small-only"></div>
	                             </div>	                            
                            </c:if>
                            <%-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="ledger_account" name="ledger_account" class="materialize-textarea">${expenditureDetails.ledger_account }</textarea>
                                    <label for="ledger_account">Ledger Account</label>
                                    <span id="ledger_accountError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <%-- <div class="col s12 m4 input-field">
                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" value="${expenditureDetails.contractor_name }">
                                    <label for="contractor_name">Contractor Name</label>
                                    <span id="contractor_nameError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m4 input-field">
                                    <textarea id="ledger_account" name="ledger_account" class="materialize-textarea">${expenditureDetails.ledger_account }</textarea>
                                    <label for="ledger_account">Ledger Account</label>
                                    <span id="ledger_accountError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" type="text" name="date" class="validate datepicker" value="${expenditureDetails.date }">
                                    <label for="date">Date</label>
                                    <span id="dateError" class="error-msg" ></span>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Voucher Type</p>
                                    <select class="searchable validate-dropdown" id="voucher_type" name="voucher_type">
                                         <option value="" >Select Voucher Type</option>
                                         <c:forEach var="obj" items="${voucherList }">
                                      	   <option value= "${ obj.financial_year}" <c:if test="${expenditureDetails.voucher_type eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
                                         </c:forEach>
                                     </select>
                                     <span id="voucher_typeError" class="error-msg" ></span>
                                </div>
                                <input type="hidden" name="expenditure_id" value="${expenditureDetails.expenditure_id }" />
                                <div class="col s12 m4 input-field">
                                    <input id="voucher_no" type="text" class="validate" name="voucher_no" value="${expenditureDetails.voucher_no }">
                                    <label for="voucher_no">Voucher No </label>
                                    <span id="voucher_noError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="narration" class="materialize-textarea" name="narration">${expenditureDetails.narration }</textarea>
                                    <label for="narration">Narration</label>
                                    <span id="narrationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="net_paid" type="text" class="validate" name="net_paid" value="${expenditureDetails.net_paid }">
                                    <label for="net_paid"> Net Paid </label>
                                    <span id="net_paidError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="gross_work_done" type="text" class="validate" name="gross_work_done" value="${expenditureDetails.gross_work_done }"> 
                                    <label for="gross_work_done"> Gross Work Done </label>
                                    <span id="gross_work_doneError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="sd_payable" type="text" class="validate" name="sd_payable" value="${expenditureDetails.sd_payable }">
                                    <label for="sd_payable">SD Payable</label>
                                    <span id="sd_payableError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="contractor_income_tax" type="text" class="validate" name="contractor_income_tax" value="${expenditureDetails.contractor_income_tax }">
                                    <label for="contractor_income_tax">Contractor Income Tax</label>
                                    <span id="contractor_income_taxError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m8">
									<div class="row">
										<div class="col s12 m4 input-field">
											<i class="material-icons prefix center-align">₹</i> <input
												id="cgst_tds" type="text" class="validate" name="cgst_tds"
												value="${expenditureDetails.cgst_tds }"> <label
												for="cgst_tds">CGST TDS</label> <span id="cgst_tdsError"
												class="error-msg"></span>
										</div>
										<div class="col s12 m4 input-field">
											<i class="material-icons prefix center-align">₹</i> <input
												id="sgst_tds" type="text" class="validate" name="sgst_tds"
												value="${expenditureDetails.sgst_tds }"> <label
												for="sgst_tds">SGST TDS</label> <span id="sgst_tdsError"
												class="error-msg"></span>
										</div>
										<div class="col s12 m4 input-field">
											<i class="material-icons prefix center-align">₹</i> <input
												id="igst_tds" type="text" class="validate" name="igst_tds"
												value="${expenditureDetails.igst_tds }"> <label
												for="igst_tds">IGST TDS</label> <span id="igst_tdsError"
												class="error-msg"></span>
										</div>
									</div>
								</div>
							<div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="mob_advance" type="text" class="validate" name="mob_advance" value="${expenditureDetails.mob_advance }">
                                    <label for="mob_advance">Mobilization Advance</label>
                                    <span id="mob_advanceError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="interest_on_mob_adv" type="text" class="validate" name="interest_on_mob_adv" value="${expenditureDetails.interest_on_mob_adv }">
                                    <label for="interest_on_mob_adv">Interest on Mobilization
                                        Advance</label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="vat_wct" type="text" class="validate" name="vat_wct" value="${expenditureDetails.vat_wct }">
                                    <label for="vat_wct">VAT WCT</label>
                                    <span id="vat_wctError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="amount_withheld" type="text" class="validate" name="amount_withheld" value="${expenditureDetails.amount_withheld }">
                                    <label for="amount_withheld">Amount WithHeld</label>
                                    <span id="amount_withheldError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${expenditureDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateExpenditure();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addExpenditure();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                       <a href="<%=request.getContextPath()%>/expenditure" class="btn waves-effect waves-light bg-s"
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
            $('#remarks').characterCounter();
           /*  $("#date").datepicker(); */

            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            var projectId = "${expenditureDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            var work_id_fk = "${expenditureDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                $("#contract_id_fk").append('<option contractorName="'+val.contractor_name +'" workId="'+val.work_id_fk +'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
       			var contractor_name = $("#contract_id_fk").find('option:selected').attr("contractorName");
       			$("#contractor_name").attr("readonly", false); 
            	$("#contractor_name").val(contractor_name).focus();
            	$("#contractor_name").attr("readonly", true);
            	
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
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
                $('.searchable').select2();
            }
       		
        }
        
        function addExpenditure(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("expenditureForm").submit();			
  	 	 	}
        }
       function updateExpenditure(){
    	    if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("expenditureForm").submit();	
       		}
       }	   
       
       
       var validator =	$('#expenditureForm').validate({
			 errorClass: "my-error-class",
			   validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true															
	  			 	  },"contract_id_fk": {
	  			 		  required: true
	  			 	  },"date": {
	  			 		  required: true
	  			 	  },"contractor_name": {
	  			 		  required: true
	  			 	  },"voucher_type": {
	  			 		  required: true
	  			 	  },"voucher_no": {
	  			 		  required: false
	  			 	  },"net_paid": {
	  			 		  required: false
	  			 	  },"ledger_account": {
	  			 		  required: false
	  			 	  },"narration": {
	  			 		  required: false
	  			 	  },"remarks": {
	  			 		  required: false
	  			 	  },"gross_work_done": {
	  			 		  required: false
	  			 	  },"sd_payable": {
	  			 		  required: false
	  			 	  },"contractor_income_tax": {
	  			 		  required: false
	  			 	  },"cgst_tds": {
	  			 		  required: false
	  			 	  },"sgst_tds": {
	  			 		  required: false
	  			 	  },"vat_wct": {
	  			 		  required: false
	  			 	  },"mob_advance": {
	  			 		  required: false
	  			 	  },"interest_on_mob_adv1": {
	  			 		  required: false
	  			 	  },"amount_withheld": {
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
	  			 	  },"date": {
	  			 		  required: 'Required'
	  			 	  },"contractor_name": {
	  			 		required: 'Required'
	  			 	  },"voucher_type": {
	  			 		required: 'Required'
	  			 	  },"voucher_no": {
	  			 		required: 'Required'
	  			 	  },"ledger_account": {
	  			 		required: 'Required'
	  			 	  },"narration": {
	  			 		required: 'Required'
	  			 	  },"remarks": {
	  			 		required: 'Required'
	  			 	  },"net_paid": {
	  			 		required: 'Required'
	  			 	  },"gross_work_done": {
	  			 		required: 'Required'
	  			 	  },"sd_payable": {
	  			 		required: 'Required'
	  			 	  },"contractor_income_tax": {
	  			 		required: 'Required'
	  			 	  },"cgst_tds": {
	  			 		required: 'Required'
	  			 	  },"sgst_tds": {
	  			 		required: 'Required'
	  			 	  },"vat_wct": {
	  			 		required: 'Required'
	  			 	  },"mob_advance": {
	  			 		required: 'Required'
	  			 	  },"interest_on_mob_adv1": {
	  			 		required: 'Required'
	  			 	  },"amount_withheld": {
	  			 		required: 'Required'
	  			 	  }		
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "project_id_fk" ){
					     document.getElementById("project_id_fkError").innerHTML="";
				 	     error.appendTo('#project_id_fkError');
					 }else if(element.attr("id") == "work_id_fk" ){
					     document.getElementById("work_id_fkError").innerHTML="";
				 	     error.appendTo('#work_id_fkError');
					 }else if(element.attr("id") == "contract_id_fk" ){
					     document.getElementById("contract_id_fkError").innerHTML="";
				 	     error.appendTo('#contract_id_fkError');
					 }else if(element.attr("id") == "ledger_account" ){
					     document.getElementById("ledger_accountError").innerHTML="";
				 	     error.appendTo('#ledger_accountError');
					 }else if(element.attr("id") == "date" ){
					     document.getElementById("dateError").innerHTML="";
				 	     error.appendTo('#dateError');
					 }else if(element.attr("id") == "contractor_name" ){
					     document.getElementById("contractor_nameError").innerHTML="";
				 	     error.appendTo('#contractor_nameError');
					 }else if(element.attr("id") == "voucher_type" ){
					     document.getElementById("voucher_typeError").innerHTML="";
				 	     error.appendTo('#voucher_typeError');
					 }else if(element.attr("id") == "voucher_no" ){
					     document.getElementById("voucher_noError").innerHTML="";
				 	     error.appendTo('#voucher_noError');
					 }else if(element.attr("id") == "net_paid" ){
					     document.getElementById("net_paidError").innerHTML="";
				 	     error.appendTo('#net_paidError');
					 }else if(element.attr("id") == "gross_work_done" ){
					     document.getElementById("gross_work_doneError").innerHTML="";
				 	     error.appendTo('#gross_work_doneError');
					 }else if(element.attr("id") == "sd_payable" ){
					     document.getElementById("sd_payableError").innerHTML="";
				 	     error.appendTo('#sd_payableError');
					 }else if(element.attr("id") == "bank_account" ){
					     document.getElementById("bank_accountError").innerHTML="";
				 	     error.appendTo('#bank_accountError');
					 }else if(element.attr("id") == "contractor_income_tax" ){
					     document.getElementById("contractor_income_taxError").innerHTML="";
				 	     error.appendTo('#contractor_income_taxError');
					 }else if(element.attr("id") == "narration" ){
					     document.getElementById("narrationError").innerHTML="";
				 	     error.appendTo('#narrationError');
					 }else if(element.attr("id") == "remarks" ){
					     document.getElementById("remarksError").innerHTML="";
				 	     error.appendTo('#remarksError');
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