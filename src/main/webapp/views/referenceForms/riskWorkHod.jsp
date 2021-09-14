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
    <title>Add Risk Subwork & Responsible HOD</title>
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
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <style>
        .error {
            color: red;
        }

        .searchable_label {
            margin-bottom: 0;
        }

        .select2-container {
            z-index: 1073;
        }

        .dataTables_length{
		    text-align: center;
		}
		th{    		
    		text-transform: capitalize;    		
		}
		@media (min-width: 480px) and (max-width: 839px){
		    .mdl-cell--6-col, .mdl-cell--6-col-tablet.mdl-cell--6-col-tablet {
		        width: 100%;
		        text-align:center;
		    }
		    div.dataTables_wrapper div.dataTables_filter{
		            text-align:center;
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
                            <h6> Subwork & Responsible HOD</h6>
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
                            <div class="col m12 s12 center-align">
                                <a style="height: auto;" class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Subwork & Responsible HOD</a>
                            </div>
                        </div>
                        <div class="row no-mar">                          
                            <div class="col m12 s12">
	                            <div class="fixed-width">
	   								<div class="table-inside">
		                                <table id="risk_work_hod_table" class="mdl-data-table table-responsive">
		                                    <thead>
		                                        <tr>
		                                            <th>Work ID</th>
		                                            <th>Sub Work</th>
		                                            <th>Short Name</th>
		                                            <th>HOD</th>
		                                            <th>Work <br>Completed</th>
		                                            <th>Priority</th>
		                                            <th class="no-sort">Action</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                    	<c:forEach var="obj" items="${workHODDetails}" varStatus="index">
													<tr>
													<td>
													 	<input type="hidden" id="risk_work_hod_id${index.count}" name="risk_work_hod_id" value="${obj.risk_work_hod_id }" />
														<input type="hidden" id="work${index.count}" value="${obj.work_id_fk }" />
														${obj.work_id_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }
													</td>
													<td>
													 	${obj.sub_work }
													 	<input type="hidden" id="sub_work${index.count}" value="${obj.sub_work }" />
													</td>
													<td>
													 	${obj.short_name }
													 	<input type="hidden" id="short_name${index.count}" value="${obj.short_name }" />
													</td>
													<td>
														<input type="hidden" id="user${index.count}" value="${obj.hod_user_id_fk }" />
														${obj.designation }
													</td>
													<td> 
														<p class="center-align">
													      <label>
													        <input type="checkbox" id="risk_work_completed${index.count}" name="risk_work_completed" disabled="disabled"  value="${obj.risk_work_completed }"  <c:if test="${obj.risk_work_completed eq 'Yes'}">checked</c:if> />
													        <span></span>
													      </label>
													    </p>
													</td>
													<td>
														<input type="hidden" id="priority${index.count}" value="${obj.priority }" />
														${obj.priority }
													</td>
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
        </div>

    </div>


    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/add-risk-work-hod" id="RiskWorkHODForm" name="RiskWorkHODForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Risk Work HOD <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row no-mar">
                    <div class="col m8 s12 offset-m2">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <!-- <input id="work_id_text" type="text" class="validate">
                                <label for="work_id_text">Work ID</label> -->
                                <p class="searchable_label">Work ID </p>
                                <select name="work_id_fk" id="work_id_text" class="searchable validate-dropdown">
                                    <option value="">Select</option>
	                                 <c:forEach var="obj" items="${workDetails }">
			                                      <option value="${obj.work_id_fk }">${obj.work_id_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="work_id_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <!-- <input id="hod_user_id" type="text" class="validate">
                                <label for="hod_user_id">Hod User ID</label> -->
                                <p class="searchable_label">HOD User ID </p>
                                <select name="hod_user_id_fk" id="hod_user_id" class=" searchable
                                    validate-dropdown">
                                    <option value="">Select</option>
                                	<c:forEach var="obj" items="${HODDetails }">
 										<option value="${obj.hod_user_id_fk }">${obj.designation }</option>
 			                         </c:forEach>
	                             </select>
                                 <span id="hod_user_id_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m12">
                                <input name="sub_work" id="sub_work" type="text">
                                <label for="sub_work">Sub Work</label>
                                 <span id="sub_workError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="short_name" id="short_name" type="text">
                                <label for="short_name">Short Name</label>
                                 <span id="short_nameError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="priority" id="priority" type="number">
                                <label for="priority">Priority</label>
                                 <span id="priorityError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row no-mar">
                        	<div class="input-field col s6 m6">
                               <p class="center-align">
							      Risk Work Completed ?
							    </p>
                            </div>
                        	<div class="input-field col s6 m6">
                               <p class="center-align">
							      <label>
							        <input type="checkbox"  class="risk_work_completed" id="risk_work_completed" name="risk_work_completed" value="No"/>
							        <span></span>
							      </label>
							    </p>
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
                                        <a href="<%=request.getContextPath()%>/risk-work-hod"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
    </div>

    <div id="onlyUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/update-risk-work-hod" id="RiskWorkHODUpdateForm" name="RiskWorkHODUpdateForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Update Risk Work HOD <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row no-mar">
                    <div class="col m8 s12 offset-m2">
                        <div class="row">
                        	<input type="hidden" id="risk_work_hod_id" name="risk_work_hod_id"  />
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Work ID </p>
                                <select name="work_id_fk_new" id="update_work_id_text"
                                    class="searchable validate-dropdown">
                                    <option value="">Select</option>
	                                <c:forEach var="obj" items="${workDetails }">
			                                      <option value="${obj.work_id_fk }">${obj.work_id_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="work_id_fkUpdateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Hod User ID </p>
                                <select name="hod_user_id_fk_new" id="update_hod_user_id" class=" searchable
                                    validate-dropdown">
                                    <option value="">Select</option>
                               	    <c:forEach var="obj" items="${HODDetails }">
			                                      <option value="${obj.hod_user_id_fk }">${obj.designation }</option>
			                         </c:forEach>
	                             </select>
                                 <span id="hod_user_id_fkUpdateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m12">
                                <input name="sub_work_new" id="sub_work_new" type="text">
                                <label for="sub_work_new">Sub Work</label>
                                 <span id="sub_work_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="short_name" id="short_name_new" type="text">
                                <label for="short_name_new">Short Name</label>
                                 <span id="short_name_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="priority" id="priority_new" type="number">
                                <label for="priority_new">Priority</label>
                                 <span id="priority_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row no-mar">
                        	<div class="input-field col s6 m6">
                               <p class="center-align">
							      Risk Work Completed ?
							    </p>
                            </div>
                        	<div class="input-field col s6 m6">
                               <p class="center-align">
							      <label>
							        <input type="checkbox" class="risk_work_completed" id="risk_work_completed_new" name="risk_work_completed_new" value="No"  />
							        <span></span>
							      </label>
							    </p>
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
                                    <a href="<%=request.getContextPath()%>/risk-work-hod"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
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

            var table = $('#risk_work_hod_table').DataTable({
            	"order":[],
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
                    var input = $('.dataTables_filter input');
                    self = this.api();
                    $clearButton = $(	'<i class="fa fa-close" title="Reset">')
                        .click(function() {		input.val(''); self.search(input.val()).draw(); 	});
                    $('.dataTables_filter > label').append(	$clearButton); 
                }
            });
        });
        $("input").change(function(){
        	
        	
        })
        function updateRow(no) {
            var work = $('#work'+no).val();
            var user = $('#user'+no).val();
            var sub_work = $('#sub_work'+no).val();            
            var risk_work_hod_id = $('#risk_work_hod_id'+no).val();
            var risk_work_completed = $('#risk_work_completed'+no).val();
            var short_name = $('#short_name'+no).val();
            var priority = $('#priority'+no).val();
            $('#onlyUpdateModal').modal('open');
            $('#update_work_id_text').val($.trim(work));
            $('#update_hod_user_id').val($.trim(user));
            $('#sub_work_new').val($.trim(sub_work));
            $('#short_name_new').val($.trim(short_name)).focus();
            $('#priority_new').val($.trim(priority)).focus();
            if(risk_work_completed == "Yes")
            {
            	$('.risk_work_completed').prop('checked', true);$("#risk_work_completed_new").val("Yes");
            }
            else
            {
            	$('.risk_work_completed').prop('checked', false);$("#risk_work_completed_new").val("No");
            }
            //$('#risk_work_completed_new').val($.trim(sub_work))
            $('#risk_work_hod_id').val($.trim(risk_work_hod_id))
            $('#onlyUpdateModal #update_work_id_text').val($.trim(work)).focus();
            $('#onlyUpdateModal #update_hod_user_id').val($.trim(user)).focus();
            $('#onlyUpdateModal #sub_work_new').val($.trim(sub_work)).focus();
            
            $('select[name^="work_id_fk"] option[value="'+ $.trim(work) +'"]').attr("selected","selected");
            $('select[name^="hod_user_id_fk"] option[value="'+ user +'"]').attr("selected","selected");
  	    	$('.searchable').select2();
        }
        function addRiskWorkHOD(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("RiskWorkHODForm").submit();	
         	}
      }
        function updateRiskWorkHOD(){
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			document.getElementById("RiskWorkHODUpdateForm").submit();	
         	}
      }
        
      var validator = $('#RiskWorkHODForm').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"work_id_fk": {
      			 		  required: true
          			 },"hod_user_id_fk":{
      					  required: true
          			},"sub_work":{
          				  required: true
          			},"short_name":{
          				  required: false
          			},"priority":{
          				  required: true
          			}
      			},messages: {
      		 		 "work_id_fk": {
      			 		  required: 'Required'
      			 	 }, "hod_user_id_fk": {
      			 		  required: 'Required'
      			 	 }, "sub_work": {
      			 		  required: 'Required'
      			 	 },"short_name":{
         				  required: 'Required'
           			 },"priority":{
         				  required: 'Required'
           			 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "sub_work" ){
      				     document.getElementById("sub_workError").innerHTML="";
      			 	     error.appendTo('#sub_workError');
      			   }else if(element.attr("id") == "hod_user_id" ){
        			     document.getElementById("hod_user_id_fkError").innerHTML="";
          		 	     error.appendTo('#hod_user_id_fkError');
      		 	   }else if(element.attr("id") == "work_id_text" ){
        			     document.getElementById("work_id_fkError").innerHTML="";
          		 	     error.appendTo('#work_id_fkError');
          		   }else if(element.attr("id") == "short_name" ){
	      			     document.getElementById("short_nameError").innerHTML="";
	      		 	     error.appendTo('#short_nameError');
	      		   }else if(element.attr("id") == "priority" ){
	      			     document.getElementById("priorityError").innerHTML="";
	      		 	     error.appendTo('#priorityError');
	      		   }
      	        }
          });

      var validator1 = $('#RiskWorkHODUpdateForm').validate({
          ignore: ":hidden:not(.validate-dropdown)",
        	 rules: {
        		 	"work_id_fk_new": {
    			 		  required: true
        			 },"hod_user_id_fk_new":{
    					  required: true
        			},"sub_work_new":{
   					  	  required: true
       				},"short_name":{
       				      required: false
        			},"priority":{
          				  required: true
          			}
    			},messages: {
    		 		 "work_id_fk_new": {
    			 		required: 'Required'
    			 	 },"hod_user_id_fk_new": { 
    			 		required: 'Required'
    			 	 },"sub_work_new": { 
   			 		  	required: 'Required'
   			 	  	 },"short_name":{
     				  	required: 'Required'
         			 },"priority":{
     				  	required: 'Required'
         			 }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "update_work_id_text" ){
    				     document.getElementById("work_id_fkUpdateError").innerHTML="";
    			 	     error.appendTo('#work_id_fkUpdateError');
    			   }else if(element.attr("id") == "update_hod_user_id" ){
	    			     document.getElementById("hod_user_id_fkUpdateError").innerHTML="";
	    		 	     error.appendTo('#hod_user_id_fkUpdateError');
    		 	   }else if(element.attr("id") == "sub_work_new" ){
	    			     document.getElementById("sub_work_newError").innerHTML="";
	    		 	     error.appendTo('#sub_work_newError');
    		 	   }else if(element.attr("id") == "short_name_new" ){
	      			     document.getElementById("short_nameError").innerHTML="";
	      		 	     error.appendTo('#short_name_newError');
	      		   }else if(element.attr("id") == "priority_new" ){
	      			     document.getElementById("priority_newError").innerHTML="";
	      		 	     error.appendTo('#priority_newError');
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
   		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk-work-hod');
   		    	    	$('#getForm').submit();
   		           }else {
   		                swal("Cancelled", "Record is safe :)", "error");
   		            }
   		        });
   		    }
   	  
    	  $("input[type='checkbox']").click(function() {
    		  
    		  if(this.checked){
        		  $(".risk_work_completed").val("Yes");
        	  }else{
        		  $(".risk_work_completed").val("No");
        	  }
    	  })
    </script>

</body>

</html>