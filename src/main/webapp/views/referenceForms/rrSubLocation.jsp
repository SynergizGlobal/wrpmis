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
    <title>R & R Sub Location</title>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Sub Location</h6>
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
                        <div class="row no-mar">
                            <div class="col m12 s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Sub Location</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="rr_sublocation_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                        	<th>ID</th>
                                            <th>Location</th>
                                            <th>Sub Location</th>
                                            <c:forEach var="tObj" items="${rrSubLocationDetails.tablesList}" >
                                            	 <th>${tObj.captiliszedTableName }</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${rrSubLocationDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="id${indexs.count}" value="${obj.id }" />
												${obj.id }</td>
											<td>
												<input type="hidden" id="rr_location_fkId${indexs.count}" value="${obj.rr_location_fk }" /> 
												${obj.rr_location_fk }</td>
											<td>
												<input type="hidden" id="rr_sub_locationId${indexs.count}" value="${obj.rr_sub_location }" /> 
												${obj.rr_sub_location }</td>
											<c:forEach var="tObj" items="${rrSubLocationDetails.tablesList}" varStatus="index">
												 
												<td><c:forEach var="cObj" items="${rrSubLocationDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.rr_location eq obj.id }"> 
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
										 	<c:forEach var="oSbj"  items="${rrSubLocationDetails.dList}" varStatus="indexx"> 
												 
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
		 <form action="<%=request.getContextPath() %>/add-rr-sub-location" id="addRrSubLocationForm" name="addRrSubLocationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add  Sub Location <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <!-- <input id="rr_location_fk" name="rr_location_fk" type="text" class="validate"> -->
                                <p class="searchable_label"> Location</p>
                                <select name="rr_location_fk" id="rr_location_fk" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${RrLocationsList }">
		                                      <option value="${obj.rr_location }">${obj.rr_location }</option>
		                              </c:forEach>
                                </select>
                                <span id="rr_location_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="rr_sub_location" name="rr_sub_location" type="text" class="validate">
                                <label for="rr_sub_location"> Sub Location </label>
                                <span id="rr_sub_locationError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addRrSubLocation()" class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  
                                         <a href="<%=request.getContextPath()%>/rr-sub-location"
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
		 <form action="<%=request.getContextPath() %>/update-rr-sub-location" id=updateRrSubLocationForm name="updateRrSubLocationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update  Status <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                       <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="ids" name="id" type="hidden" class="validate"> 
                                <p class="searchable_label"> Location</p>
                                <input id="rr_location_fk_old" type="hidden" name="rr_location_fk_old"  >
                                <select name="rr_location_fk_new" id="rr_location_fk_new" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${RrLocationsList }">
		                                      <option value="${obj.rr_location }">${obj.rr_location }</option>
		                              </c:forEach>
                                </select>
                                <span id="rr_location_fk_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="rr_sub_location_new" name="rr_sub_location_new" type="text" class="validate">
                                <input id="rr_sub_location_old" type="hidden" name="rr_sub_location_old"  >
                                <label for="rr_sub_location"> Sub Location </label>
                                <span id="rr_sub_location_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateRrSubLocation()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/rr-sub-location"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>

    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
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
            $('#remarks').characterCounter();
            // adding table data into table start
          /*   
            // adding table data into table ends */

            var table = $('#rr_sublocation_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                  //  { "width": "10px", "targets": [4] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                fixedColumns:   {
                    right: 1
                },
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

        function addRrSubLocation(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("addRrSubLocationForm").submit();	
          }
       }
        
        function updateRrSubLocation(){
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			document.getElementById("updateRrSubLocationForm").submit();	
         }
      }
      
       var validator = $('#addRrSubLocationForm').validate({
        ignore: ":hidden:not(.validate-dropdown)",
      	 rules: {
      		 	"rr_location_fk": {
  			 		  required: true
      			 },"rr_sub_location": {
  			 		  required: true
      			 }
  			},messages: {
  		 		 "rr_location_fk": {
  			 		  required: 'Required'
  			 	 }, "rr_sub_location": {
  			 		  required: 'Required'
  			 	 }
  	        },errorPlacement:function(error, element){
  	        	 if(element.attr("id") == "rr_location_fk" ){
  				     document.getElementById("rr_location_fkError").innerHTML="";
  			 	     error.appendTo('#rr_location_fkError');
  			   }else if(element.attr("id") == "rr_sub_location" ){
  				     document.getElementById("rr_sub_locationError").innerHTML="";
  			 	     error.appendTo('#rr_sub_locationError');
  			   }
  	        }
       });
      
       var validator1 = $('#updateRrSubLocationForm').validate({
           ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 	"rr_location_fk_new": {
     			 		  required: true
         			 },"rr_sub_location_new": {
     			 		  required: true
         			 }
     			},messages: {
     		 		 "rr_location_fk_new": {
     			 		  required: 'Required'
     			 	 }, "rr_sub_location_new": {
     			 		  required: 'Required'
     			 	 }
     	        },errorPlacement:function(error, element){
     	        	 if(element.attr("id") == "rr_location_fk_new" ){
     				     document.getElementById("rr_location_fk_newError").innerHTML="";
     			 	     error.appendTo('#rr_location_fk_newError');
     			   }else if(element.attr("id") == "rr_sub_location_new" ){
     				     document.getElementById("rr_sub_location_newError").innerHTML="";
     			 	     error.appendTo('#rr_sub_location_newError');
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
          var rr_sub_location = $('#rr_sub_locationId'+no).val();
          var rr_location_fk = $('#rr_location_fkId'+no).val();
          $('#rr_sub_location_old').val($.trim(rr_sub_location));
          $('#rr_location_fk_old').val($.trim(rr_location_fk));
          $('#ids').val($.trim(id));
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #rr_sub_location_new').val($.trim(rr_sub_location)).focus();
          $('#onlyUpdateModal #rr_location_fk_new').val($.trim(rr_location_fk)).focus(); 
          $('select[name^="rr_location_fk_new"] option[value="'+ rr_location_fk +'"]').attr("selected","selected");
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
    	                $(".page-loader").show();
    	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-rr-sub-location');
    	    	    	$('#getForm').submit();
    	           }else {
    	                swal("Cancelled", "Record is safe :)", "error");
    	            }
    	        });
    	    }
    </script>

    </body>


</html>