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
    <title>Risk Work HOD</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
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
            background-color: #577590;
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
                            <h6> Risk Work HOD</h6>
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
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Risk Work HOD</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                          
                            <div class="col m12 s12">
                                <table id="risk_work_hod_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work ID</th>
                                            <th>Hod User ID</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="obj" items="${workHODDetails}" varStatus="index">
											<tr>
											<td>
											 	<input type="hidden" id="risk_work_hod_id${index.count}" name="risk_work_hod_id" value="${obj.risk_work_hod_id }" />
												<input type="hidden" id="work${index.count}" value="${obj.work_id_fk }" />
												${obj.work_id_fk }<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</td>
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
		<form action="<%=request.getContextPath() %>/add-risk-work-hod" id="RiskWorkHODForm" name="RiskWorkHODForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Risk Work HOD <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
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
                                <p class="searchable_label">Hod User ID </p>
                                <select name="hod_user_id_fk" id="hod_user_id" class=" searchable
                                    validate-dropdown">
                                    <option value="">Select</option>
                                	<c:forEach var="obj" items="${HODDetails }">
 										<option value="${obj.hod_user_id_fk }">${obj.designation }</option>
 			                         </c:forEach>
	                             </select>
                                 <span id="hod_user_id_fkError" class="error-msg" ></span>
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
                    <div class="col m2 hide-on-small"></div>
                </div>
        </form>
    </div>

    <div id="onlyUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/update-risk-work-hod" id="RiskWorkHODUpdateForm" name="RiskWorkHODUpdateForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Update Risk Work HOD <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
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

           /*  // adding table data into table start
            var newArr = [
                ["Financial", "13"],
                ["Human Resource", "5"],
                ["Legal", "2"],
                ["Management", "1"],
                ["Material", "10"],
                ["Organizational", "4"],
                ["Political", "14"],
                ["Site Risk – (R&R)", "9"],
                ["Site Safety", "8"]
            ]
            function makeTableHTML(myArray) {
                var result = "";
                for (var i = 0; i < myArray.length; i++) {
                    result += "<tr>";
                    for (var j = 0; j < myArray[i].length; j++) {
                        result += "<td>" + myArray[i][j] + "</td>";
                    }
                    result += '<td class="last-column"> <a href="#" class="btn waves-effect waves-light bg-m t-c" onclick="updateRow(' + i + ')">' +
                        '<i class="fa fa-pencil"></i></a><a href="#addUpdateModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
                }
                return result;
            } */
            //$('#risk_work_hod_table tbody').append(makeTableHTML(newArr));
            // adding table data into table ends

            var table = $('#risk_work_hod_table').DataTable({
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
            var risk_work_hod_id = $('#risk_work_hod_id'+no).val();
            $('#onlyUpdateModal').modal('open');
            $('#update_work_id_text').val($.trim(work))
            $('#update_hod_user_id').val($.trim(user))
            $('#risk_work_hod_id').val($.trim(risk_work_hod_id))
            $('#onlyUpdateModal #update_work_id_text').val($.trim(work)).focus();
            $('#onlyUpdateModal #update_hod_user_id').val($.trim(user)).focus();
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
         	 if(validator.form()){ 
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
          			}
      			},messages: {
      		 		 "work_id_fk": {
      			 		  required: 'Required'
      			 	 }, "hod_user_id_fk": {
      			 		  required: 'Required'
      			 	  }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "update_work_id_text" ){
      				     document.getElementById("work_id_fkUpdateError").innerHTML="";
      			 	     error.appendTo('#work_id_fkUpdateError');
      			   }else if(element.attr("id") == "update_hod_user_id" ){
      			     document.getElementById("hod_user_id_fkUpdateError").innerHTML="";
      		 	     error.appendTo('#hod_user_id_fkUpdateError');
      		 	   }else if(element.attr("id") == "hod_user_id" ){
        			     document.getElementById("hod_user_id_fkError").innerHTML="";
          		 	     error.appendTo('#hod_user_id_fkError');
      		 	   }else if(element.attr("id") == "work_id_text" ){
        			     document.getElementById("work_id_fkError").innerHTML="";
          		 	     error.appendTo('#work_id_fkError');
          		   }
      	        }
          });

      var validato1r = $('#RiskWorkHODUpdateForm').validate({
          ignore: ":hidden:not(.validate-dropdown)",
        	 rules: {
        		 	"work_id_fk_new": {
    			 		  required: true
        			 },"hod_user_id_fk_new":{
    					  required: true
        			}
    			},messages: {
    		 		 "work_id_fk_new": {
    			 		  required: 'Required'
    			 	 }, "hod_user_id_fk_new": { 
    			 		  required: 'Required'
    			 	  }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "update_work_id_text" ){
    				     document.getElementById("work_id_fkUpdateError").innerHTML="";
    			 	     error.appendTo('#work_id_fkUpdateError');
    			   }else if(element.attr("id") == "update_hod_user_id" ){
    			     document.getElementById("hod_user_id_fkUpdateError").innerHTML="";
    		 	     error.appendTo('#hod_user_id_fkUpdateError');
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
      
    </script>

</body>

</html>