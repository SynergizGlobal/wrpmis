<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Documents Category</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
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
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">
    <style>
		.select2-container.select2-container--default select2-container--open,
		.select2-container{
			z-index:1093 !important;
		}
		.select2-container--default .select2-selection--single{
			background-color:transparent;
		}
    </style>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Web Documents Category</h6>
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
                            <div class="col s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Web Documents Category</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="web_documents_category_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>type_fk</th>
                                            <th>Category</th>
                                             <c:forEach var="tObj" items="${webDocumentsCategoryDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${webDocumentsCategoryDetails.dList1}" varStatus="indexs">
											<tr>
											<td>
												<input type="hidden" id="id${indexs.count}" value="${obj.id }" />
												${obj.id }</td>
											<td>
												<input type="hidden" id="type_fkId${indexs.count}" value="${obj.type_fk }" class="findLengths"/>
												${obj.type_fk }</td>
											<td>
												<input type="hidden" id="categoryId${indexs.count}" value="${obj.category }" class="findLengths2"/>
												${obj.category }</td>
											<c:forEach var="tObj" items="${webDocumentsCategoryDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${webDocumentsCategoryDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.category eq obj.id }"> 
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
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${webDocumentsCategoryDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.id eq obj.id }"> 
												      	<a onclick="deleteRow('${ oSbj.id }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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

    <!-- Add Modal Training -->
    <div id="addUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/add-web-documents-category" id="webDocumentsCategoryForm" name="webDocumentsCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Web Documents Category <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                        	 <div class="col s12 m6 input-field">
                                 <p class="searchable_label">Risk Area</p>
                                 <select class="searchable validate-dropdown" name="type_fk" id="type_fk" onchange="doValidate(this.value,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${documentType }">
		                                      <option value="${obj.type_fk }">${obj.type_fk }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="type_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="category_text" name="category" type="text" class="validate" onkeyup="doValidate(null,this.value)">
                                <label for="category_text">Web Documents Category</label>
                                <span id="categoryError" class="error-msg" ></span>
                            </div>
                            
                        </div>
                          <div class="row no-mar">
	                             <div  style="text-align:center">
	                        		 <span id="DivError" class="error-msg" ></span> 
	                         	 </div>
                          </div>
                        <div class="row">
                            <div class="col s12 m6">
                               <div class="center-align m-1">
										<button  id="bttn" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
								</div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/web-documents-category"
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
		 <form action="<%=request.getContextPath() %>/update-web-documents-category" id=updateWebDocumentsCategoryForm name="updateWebDocumentsCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Web Documents Category <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="col s12 m6 input-field">
                         <input type="hidden" id="no" name="no"  />
                                 <p class="searchable_label">Risk Area</p>
                                 <input type="hidden" id="type_fk_old" name="type_fk_old"  />
                                 <select class="searchable validate-dropdown" name="type_fk_new" id="type_fk_new" onchange="doValidateUpdate(null,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${documentType }">
		                                      <option value="${obj.type_fk }">${obj.type_fk }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="risk_area_fkError" class="error-msg" ></span>
                         </div> 
                         <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Web Documents Category</label>
                                <span id="value_newError" class="error-msg" ></span>
                         </div>
	                        
                        </div>
                         <div class="row">
		                         <div  style="text-align:center">
		                        		 <span id="DivUpdateError" class="error-msg" ></span> 
		                    		 </div>
	                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/web-documents-category"
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
    <!-- 
       <div id="errorModal" class="modal">
           <div class="modal-content">
               <h5 class="modal-header ">Error <span class="right modal-action modal-close"><span
                           class="material-icons">close</span></span></h5>
               <div class="row center-align" style="margin-bottom: 0;">
                   <p style="color:red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
               </div>
           </div>
    	</div> -->
   
    <!-- footer  -->
<%--   <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
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
        	 $('select:not(.searchable)').formSelect();
             $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            /* $('.modal .searchable').select2({
            	 dropdownParent: $('.Modal');
            }) */
            // adding table data into table start
          
            // adding table data into table ends

            var table = $('#web_documents_category_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        className: "last-column", targets: [4], 
                    },
                    { "width": "20px", "targets": [4] },
                ],
                "scrollCollapse": true,
                paging: false,
                fixedHeader: true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                fixedColumns:   {
                    right: 1
                },
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
        var flag = false; 
        function doValidate(value,value1){
           var value = $('#type_fk').val();
           var value1 = $('#category_text').val();
           var print_value = value;	
     	   var print_value2 = value1;	
           var value = value.trim();
           var value1 = value1.trim();
           value = value.toLowerCase();
           value1 = value1.toLowerCase();
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#DivError').text('');
     			   $('#bttn').prop('disabled', false); 
     			   flag = true;
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#type_fk_new').val();
           var value1 = $('#value_new').val();
           value = value.trim();
           value1 = value1.trim();
         
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld = $('#type_fk_old'+no).val();
           var valueOld2 = $('#value_old').val();
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	  /*  var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	  
     	   delete ek[s];
     	   delete ak[s1]; */
     	   value = value.toLowerCase();
           value1 = value1.toLowerCase();
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			  if(findVal != null){ findVal = findVal.toLowerCase(); }
 			  if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
  			  if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			   $('#DivUpdateError').text('');
     			   $('#bttnUpdate').prop('disabled', false);
     			   updateFlag = true;
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
      
        $("#webDocumentsCategoryForm").submit(function (e) {
           	 if(validator.form()){ 
       			$(".page-loader").show();
       			$("#addUpdateModal").modal();
	       		 if(flag){
	    				document.getElementById("webDocumentsCategoryForm").submit();	
	    			 }
	    			 $(".page-loader").hide();
	    			 return false;
           	}
        })
        $("#updateWebDocumentsCategoryForm").submit(function (e) {
        	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			 if(updateFlag){
        				document.getElementById("updateWebDocumentsCategoryForm").submit();	
        			 }
        			 $(".page-loader").hide();
        			 return false;
         }
     })
        var validator =	$('#webDocumentsCategoryForm').validate({
         ignore: ":hidden:not(.validate-dropdown)",
       	 rules: { 
       		 "category": {
			 		  required: true
			 	  }
        	,"type_fk": {
			 		  required: true
			 	  }
       	},messages: {
	 		   "category": {
		 		  required: 'Required'
		 	  },
		 	  "type_fk": {
		 		  required: 'Required'
		 	  }
        },errorPlacement:function(error, element){
        	 if(element.attr("id") == "category_text" ){
			     document.getElementById("categoryError").innerHTML="";
		 	     error.appendTo('#categoryError');
			 }else if(element.attr("id") == "type_fk" ){
			     document.getElementById("type_fkError").innerHTML="";
		 	     error.appendTo('#type_fkError');
			 }
        }
      });
      var validator1 = $('#updateWebDocumentsCategoryForm').validate({
    	     ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"value_new": {
      			 		  required: true
          			 },"type_fk_new": {
      			 		  required: true
          			 }
      			},messages: {
      		 		 "value_new": {
      			 		  required: 'Required'
      			 	 },"type_fk_new": {
      			 		  required: 'Required'
      			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "value_new" ){
      				     document.getElementById("value_newError").innerHTML="";
      			 	     error.appendTo('#value_newError');
      			   }else if(element.attr("id") == "type_fk_new" ){
      				     document.getElementById("type_fk_newError").innerHTML="";
      			 	     error.appendTo('#type_fk_newError');
      			   }
      	        }
          });
        
        $('input').change(function(){
      	           if ($(this).val() != ""){
      	               $(this).valid();
      	           }
      	   });


        function updateRow(no) {
            var type_fk = $('#type_fkId'+no).val();
            var category = $('#categoryId'+no).val();
            var id = $('#id'+no).val();
            $('#value_old').val($.trim(category))
            $('#type_fk_old').val($.trim(type_fk))
            $('#onlyUpdateModal').modal('open');
            $('#onlyUpdateModal #type_fk_new').val($.trim(type_fk)).focus();
            $('#onlyUpdateModal #value_new').val($.trim(category)).focus();
            $('select[name^="type_fk_new"] option[value="'+ type_fk_new +'"]').attr("selected","selected");
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
      	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-web-documents-category');
      	    	    	$('#getForm').submit();
      	           }else {
      	                swal("Cancelled", "Record is safe :)", "error");
      	            }
      	        });
      	    }
      </script>

      </body>


   </html>