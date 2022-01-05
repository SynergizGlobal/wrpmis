<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Issue Category Title</title>
    <%--<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <%--<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
   <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">--%>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Category Title</h6>
                        </div>
                    </span>
                    <div class="">
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
                        <div class="row no-mar">
                            <div class="col s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Category Title</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                          
                            <div class="col m12 s12">
                                <table id="issue_category_title_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Category</th>
                                            <th>Short Description</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="obj" items="${issueCategoryTitleDetails}" varStatus="index">
											<tr>
											<td>
											 	<input type="hidden" id="id${index.count}" name="id" value="${obj.id }" />
												<input type="hidden" id="issue_category_fk${index.count}" value="${obj.issue_category_fk }" class="findLengths"/>
												${obj.issue_category_fk }</td>
											<td>
											 	${obj.short_description }
											 	<input type="hidden" id="short_description${index.count}" value="${obj.short_description }" class="findLengths2"/>
											</td>
												
										<td class="last-column"><a href="#onlyUpdateModal" onclick="updateRow(${index.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger "> <i class="fa fa-pencil" ></i></a><a onclick="deleteRow('${ obj.id }');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
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


    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/add-issue-category-title" id="IssueCategoryTitle" name="IssueCategoryTitle" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add Category Title <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <!-- <input id="issue_category_text" type="text" class="validate">
                                <label for="issue_category_text">Issue Category</label> -->
                                <p class="searchable_label">Category </p>
                                <select name="issue_category_fk" id="issue_category_text" class="searchable validate-dropdown" onchange="doValidate(this.value,null)">
                                    <option value="">Select</option>
	                                 <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="issue_category_fkError" class="error-msg" ></span>
                            </div>                            
                            <div class="input-field col s12 m6">
                                 <input name="short_description" id="short_description" type="text" onkeyup="doValidate(null,this.value)"> 
                                <!-- <textarea name="short_description" id="short_description" class="materialize-textarea"></textarea> -->
                                <label for="short_description">Short Description</label>
                                 <span id="short_descriptionError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                          </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" class="btn waves-effect waves-light bg-m " id="bttn">Add</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/issue-category-title"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                                </div>
                            </div>
                        </div>
                          
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>
        </form>
    </div>

    <div id="onlyUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/update-issue-category-title" id="updateIssueCategoryTitleForm" name="updateIssueCategoryTitleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Update Category Title <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                        	<input type="hidden" id="id" name="id"  />
                        	<input type="hidden" id="no" name="no"  />
                            <div class="input-field col s12 m6">
                            	<input name="update_issue_category_update" id="update_issue_category_update" type="hidden">
                                <p class="searchable_label">Category </p>
                                <select  id="update_issue_category_text" name="issue_category_fk_new" onchange="doValidateUpdate(null,null)"
                                    class="searchable validate-dropdown">
                                    <option value="">Select</option>
	                                 <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
	                             </select> 
                                 <span id="issue_category_fkUpdateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="short_description_new" id="short_description_new" type="text" onkeyup="doValidateUpdate(null,null)">
                                <input name="short_description_new_fk" id="short_description_new_fk" type="hidden">
                                <label for="short_description_new">Short Description</label>
                                 <span id="short_description_newError" class="error-msg" ></span>
                            </div>
                             <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
                    		 </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" class="btn waves-effect waves-light bg-m " id="bttnUpdate">Update</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/issue-category-title"
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

    <div id="errorModal" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h6>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div>
    <!-- footer  -->

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="idNo" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

            var table = $('#issue_category_title_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                paging: false,
                "sScrollXInner": "100%",
                fixedColumns:   {
                    right: 1
                },
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
           var value = $('#issue_category_text').val();
           var value1 = $('#short_description').val();
           var print_value = value;	
     	   var print_value2 = value1;	
           var value = value.trim();
           var value1 = value1.trim();
           value = value.toLowerCase();
           value1 = value1.toLowerCase();
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
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
           var value = $('#update_issue_category_text').val();
           var value1 = $('#short_description_new').val();
           value = value.trim();
           value1 = value1.trim();
         
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld2 = $('#update_issue_category_update'+no).val();
           var valueOld = $('#short_description_new_fk'+no).val();
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	  /*  var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	  
     	   delete ek[s];
     	   delete ak[s1]; */
     	   value = value.toLowerCase();
           value1 = value1.toLowerCase();
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			  if(findVal != null){ findVal = findVal.toLowerCase(); }
 			  if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
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
      
        $("#IssueCategoryTitle").submit(function (e) {
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("IssueCategoryTitle").submit();	
     			 if(flag){
       				document.getElementById("IssueCategoryTitle").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
         	}
      })
        $("#updateIssueCategoryTitleForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			document.getElementById("updateIssueCategoryTitleForm").submit();
     			 if(updateFlag){
       				document.getElementById("updateIssueCategoryTitleForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
         	}
      })
        
    
        var validator = $('#IssueCategoryTitle').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"issue_category_fk": {
      			 		  required: true
          		 	},"short_description":{
          				  required: true
          			}
      			},messages: {
      		 		 "issue_category_fk": {
      			 		  required: 'Required'
      			 	 }, "short_description": {
      			 		  required: 'Required'
      			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "short_description" ){
      				     document.getElementById("short_descriptionError").innerHTML="";
      			 	     error.appendTo('#short_descriptionError');
      			   }else if(element.attr("id") == "issue_category_text" ){
        			     document.getElementById("issue_category_fkError").innerHTML="";
          		 	     error.appendTo('#issue_category_fkError');
          		   }
      	        }
          });

      var validator1 = $('#updateIssueCategoryTitleForm').validate({
          ignore: ":hidden:not(.validate-dropdown)",
        	 rules: {
        		 	"issue_category_fk_new": {
    			 		  required: true
        			 },"short_description_new":{
   					  	  required: true
       			}
    			},messages: {
    		 		 "issue_category_fk_new": {
    			 		  required: 'Required'
    			 	 }, "short_description_new": { 
   			 		  required: 'Required'
   			 	  }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "update_issue_category_text" ){
    				     document.getElementById("issue_category_fkUpdateError").innerHTML="";
    			 	     error.appendTo('#issue_category_fkUpdateError');
    			   }else if(element.attr("id") == "short_description_new" ){
    			     document.getElementById("short_description_newError").innerHTML="";
    		 	     error.appendTo('#short_description_newError');
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
            var short_descriptions = $('#short_description'+no).val();
            var id = $('#id'+no).val();
            $('#onlyUpdateModal').modal('open');
            $('#update_issue_category_text').val($.trim(issue_category))
            $('#short_description_new').val($.trim(short_descriptions))
            var s  = $('#id').val($.trim(id))
            $('#onlyUpdateModal #update_issue_category_text').val($.trim(issue_category)).focus();
            $('#onlyUpdateModal #short_description_new').val($.trim(short_descriptions)).focus();
            $('select[name^="issue_category_fk_new"] option[value="'+ $.trim(issue_category) +'"]').attr("selected","selected");
   	     $('.searchable').select2();
        }
     
       function deleteRow(id){
   	  	  $("#idNo").val(id);
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
   		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-issue-category-title');
   		    	    	$('#getForm').submit();
   		           }else {
   		                swal("Cancelled", "Record is safe :)", "error");
   		            }
   		        });
   		    }
      
    </script>

</body>

</html>