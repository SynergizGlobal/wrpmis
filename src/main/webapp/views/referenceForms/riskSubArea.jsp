<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Risk Sub Area</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
            margin-bottom: 1px;
        }
        .select2-container--default .select2-selection--single{
        	background-color:transparent;
        }
		.mdl-data-table td.last-column {
		    text-align: left ;
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

        .mdl-data-table td {
            white-space: break-spaces;
        }

        .last-column {
            word-break: break-all;
            white-space: inherit !important;
        }
		/* .mdl-data-table thead tr, .mdl-data-table tfoot tr {
		    background-color: #999999 !important;
		} */
		.select2-container.select2-container--default.select2-container--open{
			z-index:1034;
		}
		/* input[type=number]:not(.browser-default):focus:not([readonly]),
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
		} */
        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }

		input[type=number]:not(.browser-default):focus:not([readonly]),
		input[type=text]:not(.browser-default):focus:not([readonly]),
		input[type=search]:not(.browser-default):focus:not([readonly]),
		textarea.materialize-textarea:focus:not([readonly])   {
		    border-bottom: 1px solid #999999 !important;
		    box-shadow: 0 1px 0 0 #999999 !important;
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
                            <h6> Risk Sub Area</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Risk Sub Area</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="risk_sub_area_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Risk Area</th>
                                            <th>Sub Area</th>
                                            <th>Item No</th>
                                            <c:forEach var="tObj" items="${riskAreaDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${riskAreaDetails.dList1}" varStatus="indexs">
											<tr><td>
												${obj.risk_area_fk }
											</td>
											<input type="hidden" id="risk_area_fk${indexs.count}" value="${obj.risk_area_fk }" name="risk_area_fk" /> 
											<input type="hidden" id="sub_area${indexs.count}" value="${obj.sub_area }" />
											<input type="hidden" id="item_no${indexs.count}" value="${obj.item_no }" />
											<td>
												${obj.sub_area }</td>
											<td>
												
												${obj.item_no }</td>
											<c:forEach var="tObj" items="${riskAreaDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${riskAreaDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.sub_area eq obj.sub_area }"> 
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
										 	<c:forEach var="oSbj"  items="${riskAreaDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.sub_area eq obj.sub_area }"> 
												      	<a onclick="deleteRow('${indexs.count}');"  class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-risk-sub-area" id="riskSubAreaForm" name="riskSubAreaForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Risk Sub Area <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                           <!--  <div class="input-field col s12 m6">
                                <input id="risk_area" type="text" class="validate">
                                <label for="risk_area">Risk Area</label>
                            </div> -->
                              <div class="col s12 m6 input-field">
                                 <p class="searchable_label">Risk Area</p>
                                 <select class="searchable validate-dropdown" name="risk_area_fk" id="area">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${riskAreaList }">
		                                      <option value="${obj.area }">${obj.area }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="risk_area_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <div class="input-field col s12 m6">
                                <input id="sub_area" type="text" name="sub_area" class="validate">
                                <label for="sub_area">Sub Area</label>
                                <span id="sub_areaError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="item_no" type="number" min="1" name="item_no" class="validate">
                                <label for="item_no">Item No</label>
                                <span id="item_noError" class="error-msg" ></span>
                            </div>

                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addRiskSubArea()" class="btn waves-effect waves-light bg-m ">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-sub-area"
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
		 <form action="<%=request.getContextPath() %>/update-risk-sub-area" id="updateRiskSubAreaForm" name="updateRiskSubAreaForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Risk Area <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        
                       <div class="row no-mar">
                           <!--  <div class="input-field col s12 m6">
                                <input id="risk_area" type="text" class="validate">
                                <label for="risk_area">Risk Area</label>
                            </div> -->
                              <div class="col s12 m6 input-field">
                                 <p class="searchable_label">Risk Area</p>
                                 <select class="searchable validate-dropdown" name="risk_area_fk_new" id="risk_area_fk_new">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${riskAreaList }">
		                                      <option value="${obj.area }" id="${obj.area }" >${obj.area }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="risk_area_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Sub Area</label>
                                <span id="sub_areaError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="item_no_new" type="number" min="1" name="item_no_new" class="validate">
                                <label for="item_no_new">Item No</label>
                                <span id="item_no_newError" class="error-msg" ></span>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateRiskAreaType()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-sub-area"
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
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="sub_area" id="sub_areas" />
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
        	 $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

            var table = $('#risk_sub_area_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                       /*  className: 'mdl-data-table__cell--non-numeric', */
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [4] },
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
        function addRiskSubArea(){
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			document.getElementById("riskSubAreaForm").submit();	
           }
        }
       
        var validator = $('#riskSubAreaForm').validate({
         ignore: ":hidden:not(.validate-dropdown)",
       	 rules: {
       		 	"sub_area": {
   			 		  required: true
       			 },"risk_area_fk": {
   			 		  required: true
       			 },"item_no": {
   			 		  required: true
       			 }
   			},messages: {
   		 		 "sub_area": {
   			 		  required: 'Required'
   			 	 }, "risk_area_fk": {
   			 		  required: 'Required'
   			 	 }, "item_no": {
   			 		  required: 'Required'
   			 	 }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "sub_area" ){
   				     document.getElementById("sub_areaError").innerHTML="";
   			 	     error.appendTo('#sub_areaError');
   			   }else if(element.attr("id") == "area" ){
   				     document.getElementById("risk_area_fkError").innerHTML="";
   			 	     error.appendTo('#risk_area_fkError');
   			   }else if(element.attr("id") == "item_no" ){
   				     document.getElementById("item_noError").innerHTML="";
   			 	     error.appendTo('#item_noError');
   			   }
   	        }
        });
       
        var validator1 = $('#updateRiskSubAreaForm').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"value_new": {
      			 		  required: true
          			 },"item_no_new":{
      					  required: true
          			}, "risk_area_fk_new": {
    			 		  required: 'true'
     			 	 }
      			},messages: {
      		 		 "value_new": {
      			 		  required: 'Required'
      			 	 }, "item_no_new": {
      			 		  required: 'Required'
      			 	  }, "risk_area_fk_new": {
      			 		  required: 'Required'
       			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "value_new" ){
      				     document.getElementById("value_newError").innerHTML="";
      			 	     error.appendTo('#value_newError');
      			   }else if(element.attr("id") == "item_no_new" ){
      			     document.getElementById("item_no_newError").innerHTML="";
      		 	     error.appendTo('#item_no_newError');
      		 	   }else if(element.attr("id") == "risk_area_fk_new" ){
      			     document.getElementById("risk_area_fk_newError").innerHTML="";
      		 	     error.appendTo('#risk_area_fk_newError');
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
      
       $("#risk_sub_area_table td:not(:last-child)").each(function(){ 
    	    $(this).text($.trim($(this).text()));
    	})
       
   function updateRow(no) {
    	   	  var risk_area_fk = $('#risk_area_fk'+no).val();
      	      var sub_area = $('#sub_area'+no).val();
      	      var item_no = $('#item_no'+no).val();
      	     
      	      $('#value_old').val($.trim(sub_area))
      	      $('#onlyUpdateModal').modal('open');
      	      $('#onlyUpdateModal #value_new').val($.trim(sub_area)).focus();
      	      $('#onlyUpdateModal #item_no_new').val($.trim(item_no)).focus();
      	      $('#onlyUpdateModal #risk_area_fk_new').val($.trim(risk_area_fk)).focus();
      	    	//$('#risk_area_fk_new option:contains(' + risk_area_fk + ')').attr('selected', 'selected');
      	    	$('select[name^="risk_area_fk_new"] option[value="'+ risk_area_fk +'"]').attr("selected","selected");
      	    	$('.searchable').select2();
      	  }
      	  
      	  function deleteRow(count){
      		  var id = $("#sub_area"+count).val();
      		  console.log(id);
      	  	$("#sub_areas").val(id);
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
      		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk-sub-area');
      		    	    	$('#getForm').submit();
      		           }else {
      		                swal("Cancelled", "Record is safe :)", "error");
      		            }
      		        });
      		    }
    </script>

</body>

</html>