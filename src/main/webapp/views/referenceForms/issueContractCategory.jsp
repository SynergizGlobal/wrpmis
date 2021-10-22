<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Issue Contract Category</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css"><!-- 
   
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Issue Contract Category</h6>
                        </div>
                    </span>
                    <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
					</c:if>
					<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
					</c:if>
                    <div class="">
                        <div class="row">
                            <div class="col m4 hide-on-small"></div>
                            <div class="col m4 s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Issue Contract Category</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="issue_contract_category_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Contract Category</th>
                                         <%--    <c:forEach var="tObj" items="${issueContractCategory.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach> --%>
                                            <th>Issue Category</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${issueContractCategory}" varStatus="index">
											<tr>
											<td>
											<input type="hidden" id="id${index.count}" name="id" value="${obj.id }" />
												<input type="hidden" id="contract_category_fk${index.count}" value="${obj.contract_category_fk }" class="findLengths"/>
												${obj.contract_category_fk }</td>
											<td>
											 	${obj.issue_category_fk }
											 	<input type="hidden" id="issue_category_fk${index.count}" value="${obj.issue_category_fk }" class="findLengths2"/>
											</td>
										<td class="last-column"><a href="#onlyUpdateModal" onclick="updateRow(${index.count})" class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-pencil" ></i></a><a onclick="deleteRow('${ obj.id }');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
									    </c:forEach>
 										
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
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
    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/add-issue-contract-category" id="addIssueContractCategoryForm" name="addIssueContractCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Issue Contract Category <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                     
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Contract Category</p>
                                <select  id="contract_category_fk_text" name="contract_category_fk" class="searchable validate-dropdown" onchange="doValidate(this.value,null)"> 
                                	<option value="">Select</option>
                                	 <c:forEach var="obj" items="${contractTypeDetails }">
			                                      <option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="contract_category_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6"> 
                            	<p class="searchable_label">Issue Category</p>
                                <select  id="issue_category_fk_text" name="issue_category_fk" class="searchable validate-dropdown" onchange="doValidate(null,this.value)">
                                	<option value="">Select</option>
                                	 <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="issue_category_fkError" class="error-msg" ></span>
                            </div>
                              <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		   </div>
                        </div>
                      
                        <div >
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn"
                                        class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                        
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/issue-contract-category"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-issue-contract-category" id=updateIssueContractCategoryForm name="updateIssueContractCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Issue Contract Category <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <input type="hidden" id="id" name="id"  />
                            <input type="hidden" id="no" name="no"  />
                            
                            <div class="input-field col s12 m6">
                                <input type="hidden" id="contract_caregory_old" name="contract" />
                                <p class="searchable_label">Contract Category</p>
                                <select class="searchable validate-dropdown" id="contract_category_text_update" name="contract_category_fk_new" onchange="doValidateUpdate(null,null)">
                                	<option value="">Select</option>
                                	<c:forEach var="obj" items="${contractTypeDetails }">
			                                      <option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="contract_category_text_updateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="hidden" id="issue_caregory_old" name="issue" />
                            	<p class="searchable_label">Issue Category</p>
                                <select class="searchable validate-dropdown" id="issue_category_text_update" name="issue_category_fk_new" onchange="doValidateUpdate(null,null)">
                                	<option value="">Select</option>
                                	  <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="issue_category_text_updateError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
                       		</div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/issue-contract-category"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>
   <!--  <div id="errorModal" class="modal">
        <div class="modal-content">
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%--     <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="idNo" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
        <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

            var table = $('#issue_contract_category_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false, 
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input');
                    self = this.api();
                    $clearButton = $(	'<i class="fa fa-close" title="Reset">')
                        .click(function() {		input.val(''); self.search(input.val()).draw(); 	});
                    $('.dataTables_filter > label').append(	$clearButton); 
                }
            });
        });
       
        var flag = false; 
        function doValidate(value,value1){
           var value = $('#contract_category_fk_text').val();
           var value1 = $('#issue_category_fk_text').val();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#DivError').text('');
     			   $('#bttn').prop('disabled', false); 
     			   flag = true;
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#contract_category_text_update').val();
           var value1 = $('#issue_category_text_update').val();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld2 = $('#issue_category_fk'+no).val();
           var valueOld = $('#contract_category_fk'+no).val();
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	  /*  var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   delete ek[s];
     	   delete ak[s1]; */
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			  if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			   $('#DivUpdateError').text('');
     			   $('#bttnUpdate').prop('disabled', false);
     			   updateFlag = true;
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
        
       
         $("#addIssueContractCategoryForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			 if(flag){
      				document.getElementById("addIssueContractCategoryForm").submit();	
      			 }
      			 $(".page-loader").hide();
      			 return false;
           }
       })
       
         $("#updateIssueContractCategoryForm").submit(function (e) {
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			 if(updateFlag){
      				document.getElementById("updateIssueContractCategoryForm").submit();	
      			 }
      			 $(".page-loader").hide();
      			 return false;
         }
     })
      
      
        var validator = $('#addIssueContractCategoryForm').validate({
	       	 ignore: ":hidden:not(.validate-dropdown)",
	       	 rules: {
	       		 "contract_category_fk": {
	   			 		  required: true
	       		 },
	   		    "issue_category_fk": {
	   				      required: true
	   			}
   			},messages: {
   		 		   "contract_category_fk": {
   			 		  required: 'Required'
   			 	  },
   			 	  "issue_category_fk": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "contract_category_fk_text" ){
   				     document.getElementById("contract_category_fkError").innerHTML="";
   			 	     error.appendTo('#contract_category_fkError');
   				 }else if(element.attr("id") == "issue_category_fk_text" ){
   				     document.getElementById("issue_category_fkError").innerHTML="";
   			 	     error.appendTo('#issue_category_fkError');
   				 }
   	        }
       });
       
       var validator1 =  $('#updateIssueContractCategoryForm').validate({
	       	 ignore: ":hidden:not(.validate-dropdown)",
	      	 rules: {
	      	   "contract_category_fk_new": {
	   	 		  required: true
	   			 },
	   		   "issue_category_fk_new": {
	   				      required: true
	   			}
   			},messages: {
   				   "contract_category_fk_new": {
   			 		  required: 'Required'
   			 	  },
   			 	  "issue_category_fk_new": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "issue_category_text_update" ){
   				     document.getElementById("issue_category_text_updateError").innerHTML="";
   			 	     error.appendTo('#issue_category_text_updateError');
   				 }else if(element.attr("id") == "contract_category_text_update" ){
   				     document.getElementById("contract_category_text_updateError").innerHTML="";
   			 	     error.appendTo('#contract_category_text_updateError');
   				 }
   	        }
      	
      });
       
       $('input').change(function(){
   	           if ($(this).val() != ""){
   	               $(this).valid();
   	           }
   	     });
       $('select').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
   

    function updateRow(no) {
    	 var issue_category = $('#issue_category_fk'+no).val();
         var contract_category = $('#contract_category_fk'+no).val();
         var id = $('#id'+no).val();
         $('#onlyUpdateModal').modal('open');
         $('#issue_category_text_update').val($.trim(issue_category))
         $('#contract_category_text_update').val($.trim(contract_category))
       
         $('#issue_category_old').val($.trim(issue_category));
         $('#contract_category_old').val($.trim(contract_category));
         var s  = $('#id').val($.trim(id))
         var s1  = $('#no').val($.trim(no))
         $('#onlyUpdateModal #issue_category_text_update').val($.trim(issue_category)).focus();
         $('#onlyUpdateModal #contract_category_text_update').val($.trim(contract_category)).focus();
         $('select[name^="issue_category_fk_new"] option[value="'+ $.trim(issue_category) +'"]').attr("selected","selected");
         $('select[name^="contract_category_fk_new"] option[value="'+ $.trim(contract_category) +'"]').attr("selected","selected");
	     $('.searchable').select2();
     }
	  
	  function deleteRow(val){
	  	$("#idNo").val(val);
	  	showCancelMessage();
	   }
	  	
	  
	  function showCancelMessage() {
	    	swal({
		            title: "Are you sure?",
		            text: "You will be able to change the status of record!",
		            type: "warning",
		            showCancelButton: true, 
		            confirmButtonColor: "#DD6B55",
		            confirmButtonText: "Yes, delete it!",
		            cancelButtonText: "No, cancel it!",
		            closeOnConfirm: false,
		            closeOnCancel: false
		        }, function (isConfirm) {
		            if (isConfirm) {
		               // swal("Deleted!", "Record has been deleted", "success");
		                $(".page-loader").show();
		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-issue-contract-category');
		    	    	$('#getForm').submit();
		           }else {
		                swal("Cancelled", "Record is safe :)", "error"); 
		            }
		        });
		    }
	</script>

</body>
</html>
