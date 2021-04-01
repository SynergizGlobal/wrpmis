<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Source of Fund</c:if>
		 <c:if test="${action eq 'add'}"> Add Source of Fund</c:if>
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
                                <h6>
                                	<c:if test="${action eq 'edit'}">Update Source of Fund</c:if>
		 							<c:if test="${action eq 'add'}"> Add Source of Fund</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
	                          <c:if test="${action eq 'edit'}">				                
				                	<form action="<%=request.getContextPath() %>/update-funds" id="fundsForm" name="fundsForm" method="post"   enctype="multipart/form-data">
	                          </c:if>
				              <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-funds" id="fundsForm" name="fundsForm" method="post"  enctype="multipart/form-data">
							  </c:if>
							 <c:if test="${action eq 'add'}">	
                              <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <p class="searchable_label">Project <span class="required">*</span></p>
                                     <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                     </select>
                                     <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                              <!--   <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work</p>
                                   <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
                                   			onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
	                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div> -->
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                           </c:if>
                            <c:if test="${action eq 'edit'}">	
                              <div class="row" id="center" >
	                              <div class="col m2 hide-on-small-only">
	                              </div>
	                       		  <div class="col s12 m8 input-field">
										 <p class="searchable_label">Project <span class="required">*</span></p>
	                                     <input type="text" value="${fundDetails.project_id} - ${fundDetails.project_name}" readonly />
								  </div> 
								  <!-- <div class="col s12 m4 input-field"> 
									    <p class="searchable_label">Work</p>
	                                         	 	<input type="text" value="${fundDetails.work_id_fk} - ${fundDetails.work_name}" readonly />
	                              </div> -->
                              </div> 
                             </c:if>
                             <input type="hidden" name="funds_id" value="${fundDetails.funds_id }" />
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Source of Fund <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" name="source_of_funds_fk" id="source_of_funds_fk">
                                        <option value="">Select</option>
                                           <c:forEach var="obj" items="${sourceOfFundList}">
	                       						  <option value="${obj.source_of_funds_fk }" <c:if test="${fundDetails.source_of_funds_fk eq obj.source_of_funds_fk }">selected</c:if>>${obj.source_of_funds_fk }</option>
	                                       </c:forEach>
                                    </select>
                                    <span id="source_of_funds_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Sub Category Railway <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" name="sub_category_railway_id_fk" id="sub_category_railway_id_fk">
                                        <option value="">Select</option>
                                       		 <c:forEach var="obj" items="${railwaysList}">
	                       						  <option value="${obj.railway_id}" <c:if test="${fundDetails.sub_category_railway_id_fk eq obj.railway_id }">selected</c:if>>${obj.railway_id }<c:if test="${not empty obj.railway_name}"> - </c:if>${obj.railway_name}</option>
	                                         </c:forEach>
                                    </select>
                                    <span id="sub_category_railway_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="funding_date" type="text" class="validate datepicker" name="funding_date" value="${fundDetails.funding_date }">
                                    <label for="funding_date">Funding Date</label>
                                    <button type="button" id="funding_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="funding_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="fund_amount" min="0.01" step="0.01" type="number" class="validate" name="fund_amount" value="${fundDetails.fund_amount }">
                                    <label for="fund_amount"> Fund Amount </label>
                                    <span id="fund_amountError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="voucher_type" type="text" class="validate" name="voucher_type" value="${fundDetails.voucher_type }">
                                    <label for="voucher_type">Voucher Type </label>
                                    <span id="voucher_typeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="voucher_no" type="text" class="validate" name="voucher_no" value="${fundDetails.voucher_no }">
                                    <label for="voucher_no">Voucher No </label>
                                    <span id="voucher_noError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="bank_account" type="text" class="validate" name="bank_account" value="${fundDetails.bank_account }">
                                    <label for="bank_account">Bank Account </label>
                                    <span id="bank_accountError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="ledger_account" type="text" class="validate" name="ledger_account" value="${fundDetails.ledger_account }">
                                    <label for="ledger_account">Ledger Account </label>
                                    <span id="ledger_accountError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="narration" name="narration" class="materialize-textarea" data-length="1000">${fundDetails.narration }</textarea>
                                    <label for="narration">Narration </label>
                                    <span id="narrationError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" name="fundFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text" name="attachment"  value="${fundDetails.attachment }"  id="source_of_fund_attachment">
                                        </div>
                                    </div>
                                    
                                    <c:if test="${not empty fundDetails.attachment }">
                                  		<div>
                                  			<a  class="filevalue" href="<%=CommonConstants.FUND_FILES %>${fundDetails.attachment }" download>${fundDetails.attachment }</a>
                                  			<span onclick="removeMedia(this,'source_of_fund_attachment')" class="attachment-remove-btn">X</span>
                                  		</div>                                  			
                                	</c:if>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${fundDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                          <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateFunds();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                      </c:if>
										  <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addFunds();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
										  </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/source-of-funds" class="btn waves-effect waves-light bg-s"
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
<!--     <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
 -->    
     <script>
    /*    $(document).on('focus', '.datepicker',function(){
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
            $('#remarks,#narration').characterCounter();
          /*   $("#funding_date").datepicker(); */

            $('#funding_date_icon').click(function () {
                event.stopPropagation();
                $('#funding_date').click();
            });
            
            var projectId = "${fundDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
        });
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSourceOfFundForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${fundDetails.work_id_fk}";
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
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }
        
        function addFunds(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("fundsForm").submit();			
  	 	 }
        }
       function updateFunds(){
    	   if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("fundsForm").submit();	
       	}
       }	   
       var validator =	$('#fundsForm').validate({
			 errorClass: "my-error-class",
			   validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true															
	  			 	  },"source_of_funds_fk": {
	  			 		  required: true
	  			 	  },"sub_category_railway_id_fk": {
	  			 		  required: true
	  			 	  },"funding_date": {
	  			 		  required: false
	  			 	  },"fund_amount": {
	  			 		  required: false
	  			 	  },"voucher_type": {
	  			 		  required: false
	  			 	  },"voucher_no": {
	  			 		  required: false
	  			 	  },"bank_account": {
	  			 		  required: false
	  			 	  },"ledger_account": {
	  			 		  required: false
	  			 	  },"narration": {
	  			 		  required: false
	  			 	  },"remarks": {
	  			 		  required: false
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"source_of_funds_fk": {
	  			 		  required: 'Required'
	  			 	  },"sub_category_railway_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"funding_date": {
	  			 		  required: 'Required'
	  			 	  },"fund_amount": {
	  			 		required: 'Required'
	  			 	  },"voucher_type": {
	  			 		required: 'Required'
	  			 	  },"voucher_no": {
	  			 		required: 'Required'
	  			 	  },"bank_account": {
	  			 		required: 'Required'
	  			 	  },"ledger_account": {
	  			 		required: 'Required'
	  			 	  },"narration": {
	  			 		required: 'Required'
	  			 	  },"remarks": {
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
					 }else if(element.attr("id") == "source_of_funds_fk" ){
					     document.getElementById("source_of_funds_fkError").innerHTML="";
				 	     error.appendTo('#source_of_funds_fkError');
					 }else if(element.attr("id") == "sub_category_railway_id_fk" ){
					     document.getElementById("sub_category_railway_id_fkError").innerHTML="";
				 	     error.appendTo('#sub_category_railway_id_fkError');
					 }else if(element.attr("id") == "funding_date" ){
					     document.getElementById("funding_dateError").innerHTML="";
				 	     error.appendTo('#funding_dateError');
					 }else if(element.attr("id") == "fund_amount" ){
					     document.getElementById("fund_amountError").innerHTML="";
				 	     error.appendTo('#fund_amountError');
					 }else if(element.attr("id") == "voucher_type" ){
					     document.getElementById("voucher_typeError").innerHTML="";
				 	     error.appendTo('#voucher_typeError');
					 }else if(element.attr("id") == "voucher_no" ){
					     document.getElementById("voucher_noError").innerHTML="";
				 	     error.appendTo('#voucher_noError');
					 }else if(element.attr("id") == "bank_account" ){
					     document.getElementById("bank_accountError").innerHTML="";
				 	     error.appendTo('#bank_accountError');
					 }else if(element.attr("id") == "ledger_account" ){
					     document.getElementById("ledger_accountError").innerHTML="";
				 	     error.appendTo('#ledger_accountError');
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
	       function removeMedia(link,id){
	    	   	  $('#'+id).val('');
	    	   	  $(link).prev().text('');
	    	   	  $(link).css('display','none');
	    }  
    </script>
</body>

</html>