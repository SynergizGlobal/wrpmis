<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Category</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
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
		.mdl-data-table td.last-column {
		    text-align: left ;
		}
        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #a2bbb2;
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
		    background-color: #a2bbb2 !important;
		}
		.mdl-button--raised.mdl-button--colored {
		    background-color: #a2bbb2 !important;
		}
		 input[type=number]:not(.browser-default):focus:not([readonly]),
		input[type=text]:not(.browser-default):focus:not([readonly]),
		input[type=search]:not(.browser-default):focus:not([readonly]),
		textarea.materialize-textarea:focus:not([readonly])   {
		    border-bottom: 1px solid #a2bbb2 !important;
		    box-shadow: 0 1px 0 0 #a2bbb2 !important;
		}
		.input-field input[type=text]:not(.browser-default).validate+label::after,
		.input-field input[type=text]:not(.browser-default):focus:not([readonly])+label ,
		.input-field input[type=number]:not(.browser-default).validate+label::after,
		.input-field input[type=number]:not(.browser-default):focus:not([readonly])+label ,
		.input-field textarea.materialize-textarea:focus:not([readonly])+label       {
		    color: #a2bbb2  !important;
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
                            <h6> Safety Category</h6>
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
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c black-text"
                                    href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Safety Category</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="safety_category_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Safety Category</th>
                                            <th>Short Description </th>
                                            <c:forEach var="tObj" items="${safetyCategoryDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${safetyCategoryDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="categoryId${indexs.count}" value="${obj.category }" />
												${obj.category }</td>
											<td>
												<input type="hidden" id="short_descriptionId${indexs.count}" value="${obj.short_description }" />
												${obj.short_description }</td>
											<c:forEach var="tObj" items="${safetyCategoryDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${safetyCategoryDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.category eq obj.category }"> 
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
										 	<c:forEach var="oSbj"  items="${safetyCategoryDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.category eq obj.category }"> 
												      	<a onclick="deleteRow('${ oSbj.category }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-safety-category" id="safetyCategoryForm" name="safetyCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header black-text">Add Safety Category <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="safety_category_text" type="text" name="category" class="validate">
                                <label for="safety_category_text">Safety Category</label>
                                <span id="categoryError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="short_description_text" type="text" name="short_description" class="validate">
                                <label for="short_description_text">Safety Description</label>
                                <span id="short_descriptionError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addSafetyCategory()"
                                        class="btn waves-effect waves-light bg-m black-text">Add </button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/safety-category"
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
		 <form action="<%=request.getContextPath() %>/update-safety-category" id="updateSafetyCategoryForm" name="updateSafetyCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Safety Category <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                         <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Safety Category</label>
                                <span id="value_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="short_description_new" name="short_description_new" type="text" class="validate">
                                <label for="short_description_new">Safety Description</label>
                                <span id="short_description_newError" class="error-msg" ></span>
                            </div>
                        </div>
                      
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateSafetyCategoryType()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/safety-category"
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
               <h5 class="modal-header black-text">Error <span class="right modal-action modal-close"><span
                           class="material-icons">close</span></span></h5>
               <div class="row center-align" style="margin-bottom: 0;">
                   <p style="color:red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
               </div>
           </div>
    	</div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="category" id="category" />
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

            // adding table data into table start
          /*   var arr = ['Fatal', 'Near Misses', 'Serious'];
            var table_text = '';
            $.each(arr, function (i, val) {
                table_text = table_text + ' <tr><td>' + val + '</td>' + '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light bg-m t-c modal-trigger" >' +
                    '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-m t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
            });
            $('#safety_category_table tbody').append(table_text); */
            // adding table data into table ends

            
            var table = $('#safety_category_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [3] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging:false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });
     function addSafetyCategory(){
        	 if(validator.form()){ 
    			$(".page-loader").show();
    			$("#addUpdateModal").modal();
    			document.getElementById("safetyCategoryForm").submit();	
         }
     }
     
     function updateSafetyCategory(){
    	 if(validator1.form()){ 
			$(".page-loader").show();
			$("#onlyUpdateModal").modal();
			document.getElementById("updateSafetyCategoryForm").submit();	
     }
 }
     
     var validator = $('#safetyCategoryForm').validate({
     	 rules: {
     			 "category": {
 			 		  required: true
     			 },"short_description": {
 			 		  required: true
     		 	}
 			},messages: {
 		 		   "category": {
 			 		  required: 'Required'
 			 	  },"short_description": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "safety_category_text" ){
 				     document.getElementById("categoryError").innerHTML="";
 			 	     error.appendTo('#categoryError');
 				 }else  if(element.attr("id") == "short_description_text" ){
 				     document.getElementById("short_descriptionError").innerHTML="";
 			 	     error.appendTo('#short_descriptionError');
 				 }
 	        }
     });
     
     var validator1 = $('#updateSafetyCategoryForm').validate({
    	 rules: {
    		 	"value_new": {
			 		  required: true
    			 },"short_description_new":{
					  required: true
    			}
			},messages: {
		 		 "value_new": {
			 		  required: 'Required'
			 	 }, "short_description_new": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "value_new" ){
				     document.getElementById("value_newError").innerHTML="";
			 	     error.appendTo('#value_newError');
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


  function updateRow(no) {
      var category = $('#categoryId'+no).val();
      var short_description = $('#short_descriptionId'+no).val();
      $('#value_old').val($.trim(category))
      $('#onlyUpdateModal').modal('open');
      $('#onlyUpdateModal #value_new').val($.trim(category)).focus();
      $('#onlyUpdateModal #short_description_new').val($.trim(short_description)).focus();
  }
  
  function deleteRow(val){
  	$("#category").val(val);
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
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-safety-category');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
</script>

</body>


</html>