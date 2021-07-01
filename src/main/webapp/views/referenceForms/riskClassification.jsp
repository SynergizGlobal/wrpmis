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
    <title>Risk Classification</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
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
		.mdl-data-table td.last-column {
		    text-align: left ;
		}
        .modal-header {
            text-align: center;
            background-color: #999999;
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

       /*  .mdl-data-table thead tr, .mdl-data-table tfoot tr {
		    background-color: #999999 !important;
		} */
			input[type=number]:not(.browser-default):focus:not([readonly]),
		input[type=text]:not(.browser-default):focus:not([readonly]),
		input[type=search]:not(.browser-default):focus:not([readonly]),
		textarea.materialize-textarea:focus:not([readonly])   {
		    border-bottom: 1px solid #999999 !important;
		    box-shadow: 0 1px 0 0 #999999 !important;
		}
		.input-field input[type=text]:not(.browser-default).validate+label::after,
		.input-field input[type=text]:not(.browser-default):focus:not([readonly])+label ,
		.input-field input[type=number]:not(.browser-default).validate+label::after,
		.input-field input[type=number]:not(.browser-default):focus:not([readonly])+label ,
		.input-field textarea.materialize-textarea:focus:not([readonly])+label       {
		    color: #999999  !important;
		}

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }
		.error-msg label{color:red!important;}
    </style>
</head>

<body>

    <!-- header  starts-->
<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Risk Classification</h6>
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
                            <div class="col m12 s12 center-align">
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Risk Classification</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="risk_classification_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Risk Id</th>
                                            <th>Classification</th>
                                            <th>Minimum</th>
                                            <th>Maximum</th>
                                            <c:forEach var="tObj" items="${riskClassificationDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
									   <c:forEach var="obj" items="${riskClassificationDetails.dList1}" varStatus="indexs">
											<tr><td>
											<input type="hidden" id="risk_classification_id${indexs.count}" value="${obj.risk_classification_id }" />
											${obj.risk_classification_id }</td>
											<td>
											<input type="hidden" id="classificationId${indexs.count}" value="${obj.classification }" />
											${obj.classification }</td>
											<td>
											<input type="hidden" id="minimumId${indexs.count}" value="${obj.minimum }" />
											${obj.minimum }</td>
											<td>
											<input type="hidden" id="maximumId${indexs.count}" value="${obj.maximum }" />
											${obj.maximum }</td>
											<c:forEach var="tObj" items="${riskClassificationDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${riskClassificationDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.classification eq obj.classification }"> 
																      	 ( ${cObj.count } )   
																    </c:when>  
																    <c:otherwise>  
																    </c:otherwise>   
															</c:choose>
														</c:when>
														<c:otherwise> 
													   </c:otherwise>
												</c:choose>
												</c:forEach></td>
                                            </c:forEach>
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${riskClassificationDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.classification eq obj.classification }"> 
												      	<a onclick="deleteRow('${ oSbj.risk_classification_id }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	</a>
												    </c:when>  
												    <c:otherwise>  
												    </c:otherwise>   
												</c:choose>  
												
 											 </c:forEach>
 											</td></tr>										  
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
		 <form action="<%=request.getContextPath() %>/add-risk-classification" id="riskClassificationForm" name="riskClassificationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Risk Classification <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="risk_classification" type="text" name="classification" class="validate">
                                <label for="risk_classification">Risk Classification</label>
                                <span id="classificationError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="risk_minimum" type="number" name="minimum" min="1" class="validate" step="1">
                                <label for="risk_minimum">Risk Minimum</label>
                                <span id="minimumError" class="error-msg" ></span>
                           </div>
                        </div>
                        <div class="row">
                          
                            <div class="input-field col s12 m6">
                                <input id="risk_maximum" type="number" name="maximum" min="1" class="validate" step="1">
                                <label for="risk_maximum">Risk Maximum</label>
                                 <span id="maximumError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addClassification()" class="btn waves-effect waves-light bg-m ">Add</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-classification"
									  class="btn waves-effect waves-light bg-s modal-action modal-close black-text" style="width: 100%">Cancel</a>
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
		 <form action="<%=request.getContextPath() %>/update-risk-classification" id="updateRiskClassificationForm" name="updateRiskClassificationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Update Risk Classification <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Risk Classification</label>
                                <span id="value_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="risk_minimum_new" type="number" name="risk_minimum_new" min="1" class="validate" step="1">
                                <label for="risk_minimum_new">Risk Minimum</label>
                                <span id="risk_minimum_newError" class="error-msg" ></span>
                           </div>
                        </div>
                        <div class="row">
                          
                            <div class="input-field col s12 m6">
                                <input id="risk_maximum_new" type="number" name="risk_maximum_new" min="1" class="validate" step="1">
                                <label for="risk_maximum_new">Risk Maximum</label>
                                 <span id="risk_maximum_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateClassification()" class="btn waves-effect waves-light bg-m ">Update</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-classification"
									  class="btn waves-effect waves-light bg-s modal-action modal-close black-text" style="width: 100%">Cancel</a>
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
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="risk_classification_id" id="risk_classification_id" />
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

            var table = $('#risk_classification_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [4] },
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

      function addClassification(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("riskClassificationForm").submit();	
          }
      }
      
      function updateClassification(){
      	 if(validator1.form()){ 
  			$(".page-loader").show();
  			$("#onlyUpdateModal").modal();
  			document.getElementById("riskClassificationForm").submit();	
       }
   }
      
      
      var validator =	$('#riskClassificationForm').validate({
      	 rules: {
      		 "classification": {
  			 		  required: true
      		 },"minimum": {
  			 		  required: true
      		 },"maximum": {
  			 		  required: true
      		 }
  			},messages: {
  		 		   "classification": {
  			 		  required: 'Required'
  			 	  },"minimum": {
  			 		  required: 'Required'
  			 	  },"maximum": {
  			 		  required: 'Required'
  			 	  }
  	        },errorPlacement:function(error, element){
  	        	 if(element.attr("id") == "risk_classification" ){
  				     document.getElementById("classificationError").innerHTML="";
  			 	     error.appendTo('#classificationError');
  				 }else if(element.attr("id") == "risk_minimum" ){
  				     document.getElementById("minimumError").innerHTML="";
  			 	     error.appendTo('#minimumError');
  				 }else if(element.attr("id") == "risk_maximum" ){
  				     document.getElementById("maximumError").innerHTML="";
  			 	     error.appendTo('#maximumError');
  				 }
  	        }
      	
      });
      
      var validator1 = $('#updateRiskClassificationForm').validate({
       	 rules: {
       		 	"value_new": {
   			 		  required: true
       			 },"risk_minimum_new": {
 			 		  required: true
          		 },"risk_maximum_new": {
      			 		  required: true
          		 }
   			},messages: {
   		 		 "value_new": {
   			 		  required: 'Required'
   			 	 },"risk_minimum_new": {
 			 		  required: 'Required'
 			 	  },"risk_maximum_new": {
 			 		  required: 'Required'
 			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "value_new" ){
   				     document.getElementById("value_newError").innerHTML="";
   			 	     error.appendTo('#value_newError');
   			   }else if(element.attr("id") == "risk_minimum_new" ){
  				     document.getElementById("risk_minimum_newError").innerHTML="";
  			 	     error.appendTo('#risk_minimum_newError');
  			   }else if(element.attr("id") == "risk_maximum_new" ){
  				     document.getElementById("risk_maximum_newError").innerHTML="";
  			 	     error.appendTo('#risk_maximum_newError');
  			   }
   	        }
       });
     
     $('input').change(function(){
   	           if ($(this).val() != ""){
   	               $(this).valid();
   	           }
   	   });


     function updateRow(no) {
         var classification = $('#classificationId'+no).val();
         var minimum = $('#minimumId'+no).val();
         var maximum = $('#maximumId'+no).val();
         $('#value_old').val($.trim(classification))
        
         $('#onlyUpdateModal').modal('open');
         $('#onlyUpdateModal #value_new').val($.trim(classification)).focus();
         $('#onlyUpdateModal #risk_minimum_new').val($.trim(minimum)).focus();
         $('#onlyUpdateModal #risk_maximum_new').val($.trim(maximum)).focus();
     }
     
     function deleteRow(val){
     	$("#risk_classification_id").val(val);
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
   	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk-classification');
   	    	    	$('#getForm').submit();
   	           }else {
   	                swal("Cancelled", "Record is safe :)", "error");
   	            }
   	        });
   	    }
   </script>

   </body>


</html>