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
    <title>Land Acquisition Sub Category</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
            margin-bottom: 0;
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
        }

        .select2-container.select2-container--default.select2-container--open {
            z-index: 1004;
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
                            <h5> Land Acquisition Sub Category</h5>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Land Acquisition Sub Category</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="la_subcategory_table" class="mdl-data-table">
                                    <thead>
                                        <tr><th>ID</th>
                                            <th>LA Category</th>
                                            <th>LA Sub Category</th>
                                            <c:forEach var="tObj" items="${landAcquisitionSubCategoryDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${landAcquisitionSubCategoryDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="id${indexs.count}" value="${obj.id }" />
												${obj.id }</td>
											<td>
												<input type="hidden" id="la_category_fkId${indexs.count}" value="${obj.la_category_fk }" /> 
												${obj.la_category_fk }</td>
											<td>
												<input type="hidden" id="la_sub_categoryId${indexs.count}" value="${obj.la_sub_category }" /> 
												${obj.la_sub_category }</td>
											<c:forEach var="tObj" items="${landAcquisitionSubCategoryDetails.tablesList}" varStatus="index">
												 
												<td><c:forEach var="cObj" items="${landAcquisitionSubCategoryDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.la_category eq obj.id }"> 
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
										 	<c:forEach var="oSbj"  items="${landAcquisitionSubCategoryDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.id eq obj.id }"> 
												      	<a onclick="deleteRow('${ oSbj.id }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-la-sub-category" id="addLaSubCategoryForm" name="addLaSubCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Land Acquisition Sub Category <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <!-- <input id="la_category_fk" name="la_category_fk" type="text" class="validate"> -->
                                <p class="searchable_label">Land Acquisition Category</p>
                                <select name="la_category_fk" id="la_category_fk" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${LACategorysList }">
		                                      <option value="${obj.la_category }">${obj.la_category }</option>
		                              </c:forEach>
                                </select>
                                <span id="la_category_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="la_sub_category" name="la_sub_category" type="text" class="validate">
                                <label for="la_sub_category">Land Acquisition Sub Category </label>
                                <span id="la_sub_categoryError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addLASubCategory()" class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  
                                         <a href="<%=request.getContextPath()%>/la-sub-category"
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
		 <form action="<%=request.getContextPath() %>/update-la-sub-category" id=updateLaSubCategoryForm name="updateLaSubCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Land Acquisition Status <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="ids" name="id" type="hidden" class="validate"> 
                                <p class="searchable_label">Land Acquisition Category</p>
                                <input id="la_category_fk_old" type="hidden" name="la_category_fk_old"  >
                                <select name="la_category_fk_new" id="la_category_fk_new" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${LACategorysList }">
		                                      <option value="${obj.la_category }">${obj.la_category }</option>
		                              </c:forEach>
                                </select>
                                <span id="la_category_fk_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="la_sub_category_new" name="la_sub_category_new" type="text" class="validate">
                                <input id="la_sub_category_old" type="hidden" name="la_sub_category_old"  >
                                <label for="la_sub_category">Land Acquisition Sub Category </label>
                                <span id="la_sub_category_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateLASubCategory()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/la-sub-category"
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
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
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
            $('#remarks').characterCounter();
            // adding table data into table start
          /*   var newArr = [
                ["28", "Amrutlal Bhikabhai Harsora", "Private"],
                ["29", "Allana Cold Storage Divisional Allana Oil Mil", "Private"],
                ["30", "Kalwa Cold Storage No. 4 Allana House, Kalwa,", "Private"],
                ["31", "MIDC", "Government"],
                ["32", "CIDCO", "Government"],
                ["33", "Mafatlal", "Private"],
                ["34", "State Government", "Government"],
                ["35", "Farm", "Private"],
                ["36", "Railway", "Railway"],
                ["37", "Agricultural Land", "Private"],
                ["38", "Khajan", "Government"],
                ["39", "Akari Pad", "Government"],
                ["40", "Private Forest", "Forest"],
                ["41", "Non Agricultural land", "Private"],
                ["42", "Milk Commisioner", "Government"],
                ["43", "Kandal Forest Area", "Government"],
                ["44", "Khadi", "Government"],
                ["45", "Nala", "Government"],
                ["46", "State Government", "Forest"],
                ["47", "River", "Government"],
                ["48", "Reserve Forest", "Forest"],
                ["49", "Revenue", "Government"],
                ["50", "MSRDC", "Government"],
                ["51", "Panchayat", "Government"],
                ["52", "NMMC", "Government"],
                ["53", "Forest", "Forest"],
                ["54", "Non Agricultural land", "Government"]
            ]
            function makeTableHTML(myArray) {
                var result = "";
                for (var i = 0; i < myArray.length; i++) {
                    result += "<tr>";
                    for (var j = 0; j < myArray[i].length; j++) {
                        result += "<td>" + myArray[i][j] + "</td>";
                    }
                    result += '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light modal-trigger bg-m t-c">' +
                        '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
                }
                return result;
            }
            $('#la_subcategory_table tbody').append(makeTableHTML(newArr));
            // adding table data into table ends */

            var table = $('#la_subcategory_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [3] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                // paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });

        function addLASubCategory(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("addLaSubCategoryForm").submit();	
          }
       }
        
        function updateLASubCategory(){
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			document.getElementById("updateLaSubCategoryForm").submit();	
         }
      }
      
       var validator = $('#addLaSubCategoryForm').validate({
        ignore: ":hidden:not(.validate-dropdown)",
      	 rules: {
      		 	"la_category_fk": {
  			 		  required: true
      			 },"la_sub_category": {
  			 		  required: true
      			 }
  			},messages: {
  		 		 "la_category_fk": {
  			 		  required: 'Required'
  			 	 }, "la_sub_category": {
  			 		  required: 'Required'
  			 	 }
  	        },errorPlacement:function(error, element){
  	        	 if(element.attr("id") == "la_category_fk" ){
  				     document.getElementById("la_category_fkError").innerHTML="";
  			 	     error.appendTo('#la_category_fkError');
  			   }else if(element.attr("id") == "la_sub_category" ){
  				     document.getElementById("la_sub_categoryError").innerHTML="";
  			 	     error.appendTo('#la_sub_categoryError');
  			   }
  	        }
       });
      
       var validator1 = $('#updateLaSubCategoryForm').validate({
           ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 	"la_category_fk_new": {
     			 		  required: true
         			 },"la_sub_category_new": {
     			 		  required: true
         			 }
     			},messages: {
     		 		 "la_category_fk_new": {
     			 		  required: 'Required'
     			 	 }, "la_sub_category_new": {
     			 		  required: 'Required'
     			 	 }
     	        },errorPlacement:function(error, element){
     	        	 if(element.attr("id") == "la_category_fk_new" ){
     				     document.getElementById("la_category_fk_newError").innerHTML="";
     			 	     error.appendTo('#la_category_fk_newError');
     			   }else if(element.attr("id") == "la_sub_category_new" ){
     				     document.getElementById("la_sub_category_newError").innerHTML="";
     			 	     error.appendTo('#la_sub_category_newError');
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
    	  var id = $('#id'+no).val();
          var la_sub_category = $('#la_sub_categoryId'+no).val();
          var la_category_fk = $('#la_category_fkId'+no).val();
          $('#la_sub_category_old').val($.trim(la_sub_category));
          $('#la_category_fk_old').val($.trim(la_category_fk));
          $('#ids').val($.trim(id));
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #la_sub_category_new').val($.trim(la_sub_category)).focus();
          $('#onlyUpdateModal #la_category_fk_new').val($.trim(la_category_fk)).focus(); 
          $('select[name^="la_category_fk_new"] option[value="'+ la_category_fk +'"]').attr("selected","selected");
          $('.searchable').select2();
      }
      
      function deleteRow(val){
      	$("#id").val(val);
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
    	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-la-sub-category');
    	    	    	$('#getForm').submit();
    	           }else {
    	                swal("Cancelled", "Record is safe :)", "error");
    	            }
    	        });
    	    }
    </script>

    </body>


</html>