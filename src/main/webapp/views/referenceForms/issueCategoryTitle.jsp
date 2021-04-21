<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Issue Category Title</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        p a {
            color: blue;
        }

        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #007A7A;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .last-column {
            word-break: break-all;
            white-space: inherit;
        }

        .error {
            color: red;
        }

        .searchable_label {
            margin-bottom: 0;
        }

        .select2-container {
            z-index: 1073;
        }

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }
    </style>
</head>

<body>

    <!-- header  starts-->

    <!-- header ends  -->


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Issue Category Title</h6>
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
                        <div class="row">
                            <div class="col m4 hide-on-small"></div>
                            <div class="col m4 s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Issue Category Title</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                          
                            <div class="col m12 s12">
                                <table id="issue_category_title_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Issue Category</th>
                                            <th>Short Description</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="obj" items="${workHODDetails}" varStatus="index">
											<tr>
											<td>
											 	<input type="hidden" id="risk_work_hod_id${index.count}" name="risk_work_hod_id" value="${obj.risk_work_hod_id }" />
												<input type="hidden" id="work${index.count}" value="${obj.issue_category_fk }" />
												${obj.issue_category_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</td>
											<td>
											 	${obj.short_description }
											 	<input type="hidden" id="short_description${index.count}" value="${obj.short_description }" />
											</td>
											<td>
												<input type="hidden" id="user${index.count}" value="${obj.hod_user_id_fk }" />
												${obj.designation }</td>
										<td class="last-column"><a href="#onlyUpdateModal" onclick="updateRow(${index.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger "> <i class="fa fa-pencil" ></i></a><a onclick="deleteRow('${ obj.risk_work_hod_id }');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
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
                <h5 class="modal-header">Add Issue Category Title <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <!-- <input id="issue_category_text" type="text" class="validate">
                                <label for="issue_category_text">Issue Category</label> -->
                                <p class="searchable_label">Issue Category </p>
                                <select name="issue_category_fk" id="issue_category_text" class="searchable validate-dropdown">
                                    <option value="">Select</option>
	                                 <c:forEach var="obj" items="${workDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="issue_category_fkError" class="error-msg" ></span>
                            </div>                            
                            <div class="input-field col s12 m6">
                                <!-- <input name="short_description" id="short_description" type="text"> -->
                                <textarea name="short_description" id="short_description" class="materialize-textarea"></textarea>
                                <label for="short_description">Short Description</label>
                                 <span id="short_descriptionError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" class="btn waves-effect waves-light bg-m " onclick="addRiskWorkHOD();">Add</button>
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
		<form action="<%=request.getContextPath() %>/update-issue-category-title" id="IssueCategoryTitleForm" name="IssueCategoryTitleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Update Issue Category Title <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                        	<input type="hidden" id="risk_work_hod_id" name="risk_work_hod_id"  />
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Issue Category </p>
                                <select name="issue_category_new" id="update_issue_category_text"
                                    class="searchable validate-dropdown">
                                    <option value="">Select</option>
	                                <c:forEach var="obj" items="${workDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="issue_category_fkUpdateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="short_description_new" id="short_description_new" type="text">
                                <label for="short_description_new">Short Description</label>
                                 <span id="short_description_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" class="btn waves-effect waves-light bg-m " onclick="updateRiskWorkHOD();">Update</button>
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
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div>
    <!-- footer  -->

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="risk_work_hod_id" id="risk_work_hod" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
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
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });

        function updateRow(no) {
            var work = $('#work'+no).val();
            var user = $('#user'+no).val();
            var short_description = $('#short_description'+no).val();
            var risk_work_hod_id = $('#risk_work_hod_id'+no).val();
            $('#onlyUpdateModal').modal('open');
            $('#update_issue_category_text').val($.trim(work))
            $('#update_hod_user_id').val($.trim(user))
            $('#short_description_new').val($.trim(short_description))
            $('#risk_work_hod_id').val($.trim(risk_work_hod_id))
            $('#onlyUpdateModal #update_issue_category_text').val($.trim(work)).focus();
            $('#onlyUpdateModal #update_hod_user_id').val($.trim(user)).focus();
            $('#onlyUpdateModal #short_description_new').val($.trim(short_description)).focus();
            $('select[name^="issue_category_fk"] option[value="'+ $.trim(work) +'"]').attr("selected","selected");
            $('select[name^="hod_user_id_fk"] option[value="'+ user +'"]').attr("selected","selected");
  	    	$('.searchable').select2();
        }
        function addRiskWorkHOD(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("IssueCategoryTitle").submit();	
         	}
      }
        function updateRiskWorkHOD(){
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			document.getElementById("IssueCategoryTitleForm").submit();	
         	}
      }
        
      var validator = $('#IssueCategoryTitle').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"issue_category_fk": {
      			 		  required: true
          			 },"hod_user_id_fk":{
      					  required: true
          			},"short_description":{
          				  required: true
          			}
      			},messages: {
      		 		 "issue_category_fk": {
      			 		  required: 'Required'
      			 	 }, "hod_user_id_fk": {
      			 		  required: 'Required'
      			 	 }, "short_description": {
      			 		  required: 'Required'
      			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "short_description" ){
      				     document.getElementById("short_descriptionError").innerHTML="";
      			 	     error.appendTo('#short_descriptionError');
      			   }else if(element.attr("id") == "hod_user_id" ){
        			     document.getElementById("hod_user_id_fkError").innerHTML="";
          		 	     error.appendTo('#hod_user_id_fkError');
      		 	   }else if(element.attr("id") == "issue_category_text" ){
        			     document.getElementById("issue_category_fkError").innerHTML="";
          		 	     error.appendTo('#issue_category_fkError');
          		   }
      	        }
          });

      var validator1 = $('#IssueCategoryTitleForm').validate({
          ignore: ":hidden:not(.validate-dropdown)",
        	 rules: {
        		 	"issue_category_new": {
    			 		  required: true
        			 },"hod_user_id_fk_new":{
    					  required: true
        			},"short_description_new":{
   					  	  required: true
       			}
    			},messages: {
    		 		 "issue_category_new": {
    			 		  required: 'Required'
    			 	 }, "hod_user_id_fk_new": { 
    			 		  required: 'Required'
    			 	 }, "short_description_new": { 
   			 		  required: 'Required'
   			 	  }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "update_issue_category_text" ){
    				     document.getElementById("issue_category_fkUpdateError").innerHTML="";
    			 	     error.appendTo('#issue_category_fkUpdateError');
    			   }else if(element.attr("id") == "update_hod_user_id" ){
    			     document.getElementById("hod_user_id_fkUpdateError").innerHTML="";
    		 	     error.appendTo('#hod_user_id_fkUpdateError');
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
       
       function deleteRow(id){
   	  	  $("#risk_work_hod").val(id);
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