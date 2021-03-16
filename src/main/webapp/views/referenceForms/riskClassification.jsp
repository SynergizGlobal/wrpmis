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

        .mdl-data-table thead tr, .mdl-data-table tfoot tr {
		    background-color: #999999 !important;
		}
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
         .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
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
                            <h5> Risk Classification</h5>
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
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Risk Classification</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
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
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${riskClassificationList}">
											<tr><td>${obj.risk_classification_id }</td>
											<td>${obj.classification }</td>
											<td>${obj.minimum }</td>
											<td>${obj.maximum }</td>
											<td class="last-column"><a href="#errorModal" class="btn waves-effect waves-light bg-m t-c modal-trigger "> <i class="fa fa-pencil" ></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
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

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

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
      
      $('input').change(function(){
  	           if ($(this).val() != ""){
  	               $(this).valid();
  	           }
  	   });

    </script>

</body>

</html>