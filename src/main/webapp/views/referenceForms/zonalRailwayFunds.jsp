<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zonal Railway Funds</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
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
		/* .mdl-data-table td.last-column {
		    text-align: left ;
		}
        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #2E58AD;
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
        } */

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
                            <h6> Zonal Railway Funds</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row">
                            <div class="col m4 hide-on-small"></div>
                            <div class="col m4 s12 center-align">
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal" >
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Zonal Railway Funds</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="zonal_railway_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Zonal Railway Funds</th>
                                             <c:forEach var="tObj" items="${zonalRailwayDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${zonalRailwayDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="zonal_railway_fundsId${indexs.count}" value="${obj.zonal_railway_funds }" />
												${obj.zonal_railway_funds }</td>
											<c:forEach var="tObj" items="${zonalRailwayDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${zonalRailwayDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.zonal_railway_funds eq obj.zonal_railway_funds }"> 
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
										 	<c:forEach var="oSbj"  items="${zonalRailwayDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.zonal_railway_funds eq obj.zonal_railway_funds }"> 
												      	<a onclick="deleteRow('${ oSbj.zonal_railway_funds }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	  <%-- <input name="bg_type" value="${oSbj.bg_type}"/> --%>
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


    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
       	<form action="<%=request.getContextPath() %>/add-zonal-railway-funds" id="zonalRailwayFundsForm" name="zonalRailwayFundsForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h4 class="modal-header">Add Zonal Railway Funds <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h4>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="zonal_railway_type_text" name="zonal_railway_funds" type="text" class="validate">
                                <label for="zonal_railway_type_text">Zonal Railway Funds</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addZonalRailwayFunds()"
                                        class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/zonal-railway-funds"
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
		 <form action="<%=request.getContextPath() %>/update-zonal-railway-funds" id=updateZonalRailwayFundsForm name="updateZonalRailwayFundsForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Zonal Railway Funds <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="input-field col s12 m12">
                                <input id="value_new" type="text" name="value_new" class="validate">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Zonal Railway Funds</label>
                                <span id="value_newError" class="error-msg" ></span>
                         </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateZonalRailwayFunds()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/zonal-railway-funds"
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

    
    <!-- footer  -->

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="zonal_railway_funds" id="zonal_railway_funds" />
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
/* 
            // adding table data into table start
            var arr = ['MMRDA', 'MRVC\'s Surlus Fund', 'MUTP'];
            var table_text = '';
            $.each(arr, function (i, val) {
                table_text = table_text + ' <tr><td>' + val + '</td>' + '<td class="last-column"> <a href="#" class="btn waves-effect waves-light bg-m t-c" onclick="updateRow(' + (i + 1) + ')">' +
                    '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
            });
            $('#zonal_railway_type_table tbody').append(table_text); */
            // adding table data into table ends

            var table = $('#zonal_railway_type_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'nosort', orderable: false,
                        className: "last-column", targets: [2],
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });

        function addZonalRailwayFunds(){
           	 if(validator.form()){ 
       			$(".page-loader").show();
       			$("#addUpdateModal").modal();
       			document.getElementById("zonalRailwayFundsForm").submit();	
           	}
        }
        function updateZonalRailwayFunds(){
        	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			document.getElementById("updateZonalRailwayFundsForm").submit();	
         }
     }
        var validator =	$('#zonalRailwayFundsForm').validate({
       	 rules: {
       		 "zonal_railway_funds": {
			 		  required: true
			 	  }
       	},messages: {
	 		   "zonal_railway_funds": {
		 		  required: 'Required'
		 	  }
        },errorPlacement:function(error, element){
        	 if(element.attr("id") == "training_type_text" ){
			     document.getElementById("training_typeError").innerHTML="";
		 	     error.appendTo('#training_typeError');
			 }
        }
      });
      var validator1 = $('#updateZonalRailwayFundsForm').validate({
          	 rules: {
          		 	"value_new": {
      			 		  required: true
          			 }
      			},messages: {
      		 		 "value_new": {
      			 		  required: 'Required'
      			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "value_new" ){
      				     document.getElementById("value_newError").innerHTML="";
      			 	     error.appendTo('#value_newError');
      			   }
      	        }
          });
        
        $('input').change(function(){
      	           if ($(this).val() != ""){
      	               $(this).valid();
      	           }
      	   });


        function updateRow(no) {
            var zonal_railway_funds = $('#zonal_railway_fundsId'+no).val();
            $('#value_old').val($.trim(zonal_railway_funds))
            $('#onlyUpdateModal').modal('open');
            $('#onlyUpdateModal #value_new').val($.trim(zonal_railway_funds)).focus();
        }
        
        function deleteRow(val){
        	$("#zonal_railway_funds").val(val);
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
      	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-zonal-railway-funds');
      	    	    	$('#getForm').submit();
      	           }else {
      	                swal("Cancelled", "Record is safe :)", "error");
      	            }
      	        });
      	    }
       
      </script>

      </body>


   </html>