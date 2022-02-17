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
    <title>Department</title>
    <%--<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <%--<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css"> --%>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Department</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Department</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="department_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Department</th>
                                            <th>Department Name</th>
                                            <th>Department code</th>
                                            <c:forEach var="tObj" items="${departmentDetails.tablesList}" >
                                            	 <c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } </th>
                                            	</c:forEach>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${departmentDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="departmentId${indexs.count}" value="${obj.department }" class="findLengths"/>
												${obj.department }
											</td>
											<td>
												<input type="hidden" id="departmentName${indexs.count}" value="${obj.department_name }" class="findLengths1"/>
												${obj.department_name }
											</td>
											<td>
												<input type="hidden" id="departmentCode${indexs.count}" value="${obj.contract_id_code }" class="findLengths2"/>
												${obj.contract_id_code }
											</td>
											<c:forEach var="tObj" items="${departmentDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${departmentDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.department eq obj.department }"> 
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
										 	<c:forEach var="oSbj"  items="${departmentDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.department eq obj.department }"> 
												      	<a onclick="deleteRow('${ oSbj.department }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-department" id="addDepartmentForm" name="addDepartmentForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Department <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="department_id" type="text" name="department" class="validate" onkeyup="doValidate(this.value,null,null)">
                                <label for="department_id">Department id</label>
                                <span id="departmentError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="department_name" type="text" name="department_name" class="validate" onkeyup="doValidate(null,this.value,null)">
                                <label for="department_name">Department Name</label>
                                <span id="department_nameError" class="error-msg" ></span>
                            </div>
                            </div><div class="row">
                            <div class="input-field col s12 m6">
                                <input id="department_code" type="text" name="contract_id_code" class="validate" onkeyup="doValidate(null,null,this.value)">
                                <label for="department_code">Department code</label>
                                <span id="contract_id_codeError" class="error-msg" ></span>
                            </div>
                          
                        </div>
                         <div class="row">
                          <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		</div>
                       	</div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn" class="btn waves-effect waves-light bg-m">Add</button>
                                </div>
                            </div>
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/department"
									  class="btn waves-effect waves-light bg-s modal-action modal-close " style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
    
    <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-department" id=updateDepartmentForm name="updateDepartmentForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Department <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="department_new" type="text" name="department_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <input id="department_old" type="hidden" name="department_old"  >
                                <label for="department_id">Department id</label>
                                <span id="department_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="department_name_new" type="text" name="department_name_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <label for="department_name_new">Department Name</label>
                                <input id="department_name_old" type="hidden" name="department_name_old"  >
                                <span id=department_name_newError class="error-msg" ></span>
                            </div>
                            </div>
                            <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="department_code_new" type="text" name="department_code_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <input id="department_code_old" type="hidden" name="department_code_old"  >
                                <label for="contract_id_code_new">Department code</label>
                                <span id="department_code_newError" class="error-msg" ></span>
                            </div>
                            
                        </div>
                         <div class="row">
                         <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
                       		</div>
                       	</div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/department"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
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
    	<input type="hidden" name="department" id="department" />
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
          
            var table = $('#department_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [8] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging:false,
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
        function doValidate(value,value1,value2){
           var value = $('#department_id').val();
           var value1 = $('#department_name').val();
           var value2 = $('#department_code').val();
           value = value.trim();
           value1 = value1.trim();
           value2 = value2.trim();
           var print_value = value;	
     	   var print_value2 = value1;
     	   var print_value3 = value2;
           var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
     	   var bk = $('.findLengths2').map((_,el) => el.value).get();
           var s = Object.keys(ek).find(key => ek[key] === value);
           if(value != null){ value = value.toLowerCase();}
           if(value1 != null){ value1 = value1.toLowerCase();}
           if(value2 != null){ value2 = value2.toLowerCase();}
         
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	  
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			 var findVal3 = bk[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     			if(findVal3 != null){ findVal3 = findVal3.toLowerCase(); }
     			
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null) && (findVal3 == value2 && value2 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' & '+'" '+print_value3+' "'+' alreday exists').css('color', 'red');
   				   $('#departmentError').text('');
   				   $('#department_nameError').text('');
   				   $('#contract_id_codeError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#departmentError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#department_nameError').text('');}else{ $('#department_nameError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal3 != value2 ){$('#contract_id_codeError').text('');}else{$('#contract_id_codeError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else if(findVal2 == value1 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#department_nameError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#departmentError').text('');}else{ $('#departmentError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal3 != value2 ){$('#contract_id_codeError').text('');}else{ $('#contract_id_codeError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else if(findVal3 == value2 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#contract_id_codeError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#department_nameError').text('');}else{ $('#department_nameError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal != value ){$('#departmentError').text('');}else{ $('#departmentError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else{
        			   $('#DivError').text('');
        			   $('#departmentError').text('');
        			   $('#department_nameError').text('');
        			   $('#contract_id_codeError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  }
     		
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1,value2){
           var value = $('#department_new').val();
           var value1 = $('#department_name_new').val();
           var value2 = $('#department_code_new').val();
           value = value.trim();
           value1 = value1.trim();
           value2 = value2.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var print_value3 = value2;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#department_old').val()
           var valueOld2 = $('#department_name_old').val()
           var valueOld3 = $('#department_code_old').val()
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
     	   var bk = $('.findLengths2').map((_,el) => el.value).get();
     	   value = value.toLowerCase();
     	   value1 = value1.toLowerCase();
     	   value2 = value2.toLowerCase();
     	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   var s2 = Object.keys(bk).find(key => bk[key] === valueOld3);
     	   delete ek[s];
     	   delete ak[s1];
     	   delete bk[s2];
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			  var findVal3 = bk[count];
  			 if(findVal != null){ findVal = findVal.toLowerCase();}
  			 if(findVal2 != null){ findVal2 = findVal2.toLowerCase();}
  			 if(findVal3 != null){ findVal3 = findVal3.toLowerCase();}
   		     if((findVal == value && value != null) && (findVal2 == value1 && value1 != null) && (findVal3 == value2 && value2 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' & '+'" '+print_value3+' "'+' alreday exists').css('color', 'red');
  				   $('#department_newError').text('');
 			   	   $('#department_name_newError').text('');
 			   	   $('#department_code_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
      				 $('#bttnUpdate').prop('disabled', true);
      				 $('#DivUpdateError').text('');
      				 $('#department_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
      				 if(findVal2 != value1 ){$('#department_nameError').text('');}else{ $('#department_nameError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal3 != value2 ){$('#department_code_newError').text('');}else{ $('#department_code_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
     				 
      				 updateFlag = false; 
      				 $('#bttnUpdate').prop('disabled', true);
      				 return false;
     			 }else if(findVal2 == value1 ){
     				 $('#DivUpdateError').text('');
     				 $('#department_name_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#department_newError').text('');}else{ $('#department_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal3 != value2 ){$('#department_code_newError').text('');}else{ $('#department_code_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
     				 updateFlag = false;
     				 $('#bttnUpdate').prop('disabled', true);
     				 return false;
     			  }else if(findVal3 == value2 ){
     				 $('#DivUpdateError').text('');
     				 $('#department_code_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#department_name_newError').text('');}else{ $('#department_name_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 if(findVal != value ){$('#department_newError').text('');}else{ $('#department_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 updateFlag = false;
     				$('#bttnUpdate').prop('disabled', true);
     				return false;
     			  }else{
       			       $('#DivUpdateError').text('');
       			       $('#department_newError').text('');
       			   	   $('#department_name_newError').text('');
       			   	   $('#department_code_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   }
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#department_newError').text('');
   		 $('#department_name_newError').text('');
   		 $('#department_code_newError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
      
    
    $("#addDepartmentForm").submit(function (e) {
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			if(flag){
  				document.getElementById("addDepartmentForm").submit();	
  			 }
  			 $(".page-loader").hide();
  			 return false;
          }
      })
      
    $("#updateDepartmentForm").submit(function (e) {
       	 if(validator1.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			if(updateFlag){
  				document.getElementById("updateDepartmentForm").submit();	
  			 }
  			 $(".page-loader").hide();
  			 return false;
          }
      })
      
     
      var validator =  $('#addDepartmentForm').validate({
      	 rules: {
      		 "department": {
			 		  required: true
      		 },"department_name": {
			 		  required: true
      		 },"contract_id_code": {
			 		  required: true
      		 }
			},messages: {
		 		   "department": {
			 		  required: 'Required'
			 	  },"department_name": {
			 		  required: 'Required'
			 	  },"contract_id_code": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "department_id" ){
				     document.getElementById("departmentError").innerHTML="";
			 	     error.appendTo('#departmentError');
				 }else if(element.attr("id") == "department_name" ){
				     document.getElementById("department_nameError").innerHTML="";
			 	     error.appendTo('#department_nameError');
				 }else if(element.attr("id") == "department_code" ){
				     document.getElementById("contract_id_codeError").innerHTML="";
			 	     error.appendTo('#contract_id_codeError');
				 }
	        }
      	
      });
      
      var validator1 =  $('#updateDepartmentForm').validate({
       	 rules: {
       		 "department_new": {
 			 		  required: true
       		 },"department_name_new": {
 			 		  required: true
       		 },"department_code_new": {
 			 		  required: true
       		 }
 			},messages: {
 		 		   "department_new": {
 			 		  required: 'Required'
 			 	  },"department_name_new": {
 			 		  required: 'Required'
 			 	  },"department_code_new": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "department_new" ){
 				     document.getElementById("department_newError").innerHTML="";
 			 	     error.appendTo('#department_newError');
 				 }else if(element.attr("id") == "department_name_new" ){
 				     document.getElementById("department_name_newError").innerHTML="";
 			 	     error.appendTo('#department_name_newError');
 				 }else if(element.attr("id") == "department_code_new" ){
 				     document.getElementById("department_code_newError").innerHTML="";
 			 	     error.appendTo('#department_code_newError');
 				 }
 	        }
       	
       });
      
      $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	     });
      
      function updateRow(no) {
          var departmentName = $('#departmentName'+no).val();
          var department = $('#departmentId'+no).val();
          var departmentCode = $('#departmentCode'+no).val();
          $('#department_name_old').val($.trim(departmentName))
          $('#department_old').val($.trim(department))
          $('#department_code_old').val($.trim(departmentCode))
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #department_new').val($.trim(department)).focus();
          $('#onlyUpdateModal #department_name_new').val($.trim(departmentName)).focus();
          $('#onlyUpdateModal #department_code_new').val($.trim(departmentCode)).focus();
      }
      
      function deleteRow(val){
      	$("#department").val(val);
      	showCancelMessage();
 	    }
      	
      
      function showCancelMessage() {
      	swal({
 	            title: "Are you sure?",
 	            text: "You will be changing the status of the record!",
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
 	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-department');
 	    	    	$('#getForm').submit();
 	           }else {
 	                swal("Cancelled", "Record is safe :)", "error");
 	            }
 	        });
 	    }
      
    </script>

</body>

</html>