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
    <title>R & R Executives</title>
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
                            <h6>Executives</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Executives</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="rr_executives_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work</th>
                                            <th>Executives</th>
                                            <c:forEach var="tObj" items="${subLocationDetails.tablesList}" >
                                            	 <th>${tObj.captiliszedTableName }</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${subLocationDetails.dList1}" varStatus="indexs">
											<tr>
											<td>
												<input type="hidden" id="rr_location_fkId${indexs.count}" value="${obj.rr_location_fk }" /> 
												${obj.rr_location_fk }</td>
											<td>
												<input type="hidden" id="rr_sub_locationId${indexs.count}" value="${obj.rr_sub_location }" /> 
												${obj.rr_sub_location }</td>
											<c:forEach var="tObj" items="${subLocationDetails.tablesList}" varStatus="index">
												 
												<td><c:forEach var="cObj" items="${subLocationDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.rr_sub_location eq obj.rr_sub_location }"> 
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
										 	<c:forEach var="oSbj"  items="${subLocationDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.rr_sub_location eq obj.rr_sub_location }"> 
												      	<a onclick="deleteRow('${ oSbj.rr_sub_location }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-rr-executives" id="addRrExecutivesForm" name="addRrExecutivesForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add  Executives <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col s12">
                        <div class="row no-mar">
                             <div class="table-inside">
					            <table id="addExecutivesTable" class="mdl-data-table mobile_responsible_table" >
					                <thead>
					                    <tr>
					                        <th>Work</th>
											<th>Responsible Executives </th>
											<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:10%;">Action</th></c:if>
					                    </tr>
					                </thead>
					                
								    <tbody id="addExecutivesBody">									               
					                    <tr id="AexecutiveRow0">
					                        <td data-head="Work" class="input-field">
					                             <select class="searchable validate-dropdown dept" name="work_id_fks" id="work_id_fk0" >
					                                	<option value="" >Select</option>  																         
					                              </select>
					                              <span id="workError0" class="my-error"></span>
					                        </td>
					                        <td data-head="Responsible Executives" class="input-field h-auto">
					                        <input type="hidden"  id="responsible_executives_id_fk0" name="responsible_executives_id_fks" />
					                            <select class="searchable validate-dropdown" name="excecutives"
					                                id="responsible_executives_id_fks0" multiple="multiple" >
					                                <option value="" >Select</option>														 			 		                             	
					                            </select>
					                            <span id="executiveError0" class="my-error"></span>
					                        </td>
			                				<c:choose>
		                                        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}" >										                        
							                        <td class="mobile_btn_close">
							                            <a onclick="deleteAExecutiveRow('0');"
							                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
							                        </td>
									            </c:when>   
					                       </c:choose>									                        
					                    </tr>
					             
					                </tbody>								                

								</table>
					            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
					            <table  class="mdl-data-table">
                                       <tbody>                                          
                                           <tr >
                                  				<td colspan="3"><a class="btn waves-effect waves-light bg-m "  onclick="addAExecutiveRow()"> <i class="fa fa-plus"></i></a></td>
                                            </tr>
                                       </tbody>
                                   </table> 
                                   </c:if>
                                                  
					        </div>
                        </div>
                        <div class="row">
                        	<div class="col s12 m8 offset-m2">
                        		<div class="row">
                        			 <div class="col s12 m6">
		                                <div class="center-align m-1">
		                                    <button style="width: 100%;" onclick="addRrExecutives()" class="btn waves-effect waves-light bg-m">Add </button>
		                                </div>
		                            </div>
		                            <div class="col s12 m6">
		                                <div class="center-align m-1">
		                                  
		                                         <a href="<%=request.getContextPath()%>/rr-executives"
											     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
		                                </div>
		                            </div>
                        		</div>
                        	</div>
                           
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
    
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-rr-executives" id=updateRrExecutivesForm name="updateRrExecutivesForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update  Status <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m12 s12">
                        <div class="table-inside">
					            <table id="updateExecutivesTable" class="mdl-data-table mobile_responsible_table" >
					                <thead>
					                    <tr>
					                        <th>Work</th>
											<th>Responsible Executives </th>
											<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:10%;">Action</th></c:if>
					                    </tr>
					                </thead>
					                
								    <tbody id="updateExecutivesBody">									               
					                    <tr id="UexecutiveRow0">
					                        <td data-head="Work" class="input-field">
					                             <select class="searchable validate-dropdown dept" name="work_id_fks" id="u_work_id_fk0" >
					                                	<option value="" >Select</option>  																         
					                              </select>
					                              <span id="u_workError0" class="my-error"></span>
					                        </td>
					                        <td data-head="Responsible Executives" class="input-field h-auto">
					                        <input type="hidden"  id="u_responsible_executives_id_fk0" name="responsible_executives_id_fks" />
					                            <select class="searchable validate-dropdown" name="excecutives"
					                                id="u_responsible_executives_id_fks0" multiple="multiple" >
					                                <option value="" >Select</option>														 			 		                             	
					                            </select>
					                            <span id="u_executiveError0" class="my-error"></span>
					                        </td>
			                				<c:choose>
		                                        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}" >										                        
							                        <td class="mobile_btn_close">
							                            <a onclick="deleteUExecutiveRow('0');"
							                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
							                        </td>
									            </c:when>   
					                       </c:choose>									                        
					                    </tr>
					             
					                </tbody>								                

								</table>
					            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
					            <table  class="mdl-data-table">
                                       <tbody>                                          
                                           <tr >
                                  				<td colspan="3"><a class="btn waves-effect waves-light bg-m "  onclick="addUExecutiveRow()"> <i class="fa fa-plus"></i></a></td>
                                            </tr>
                                       </tbody>
                                   </table> 
                                   </c:if>
                                                  
					        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateRrExecutives()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/rr-executives"
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
    	<input type="hidden" name="rr_sub_location" id="id" />
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

            var table = $('#rr_executives_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
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

        function addRrExecutives(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("addRrExecutivesForm").submit();	
          }
       }
        
        function updateRrExecutives(){
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			document.getElementById("updateRrExecutivesForm").submit();	
         }
      }
      
    /*    var validator = $('#addRrExecutivesForm').validate({
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
      
       var validator1 = $('#updateRrExecutivesForm').validate({
           ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 	"rr_location_fk_new": {
     			 		  required: true
         			 },"value_new": {
     			 		  required: true
         			 }
     			},messages: {
     		 		 "rr_location_fk_new": {
     			 		  required: 'Required'
     			 	 }, "value_new": {
     			 		  required: 'Required'
     			 	 }
     	        },errorPlacement:function(error, element){
     	        	 if(element.attr("id") == "rr_location_fk_new" ){
     				     document.getElementById("rr_location_fk_newError").innerHTML="";
     			 	     error.appendTo('#rr_location_fk_newError');
     			   }else if(element.attr("id") == "value_new" ){
     				     document.getElementById("value_newError").innerHTML="";
     			 	     error.appendTo('#value_newError');
     			   }
     	        }
          }); */
       
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
          $('#value_old').val($.trim(rr_sub_location));
          $('#rr_location_fk_old').val($.trim(rr_location_fk));
          $('#ids').val($.trim(id));
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #value_new').val($.trim(rr_sub_location)).focus();
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
    	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-rr-executives');
    	    	    	$('#getForm').submit();
    	           }else {
    	                swal("Cancelled", "Record is safe :)", "error");
    	            }
    	        });
    	    }
          
      function addAExecutiveRow(){
    	   	 var rowNo = $("#addExecutivesBody tr:last-of-type").attr('id').split('executiveRow')[1];
    			 var rNo = Number(rowNo)+1;
    			 var no = 0;    			 
    					 
    			 var html = '<tr id="AexecutiveRow'+rNo+'">'
    				   +'<td data-head="Department" class="input-field">'+
    				   <c:choose>
    			        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">                                 
    			         '<select  class="searchable validate-dropdown" name="work_id_fk" id="work_id_fk'+rNo+'"> '
    	         		 +'<option value="">Select</option>'+
    	                 <c:forEach var="obj" items="${contractsList}">
    				 		'<option workId="${obj.work_id_fk }" value="${obj.work_id_fk }" >${obj.contract_short_name }</option>'+
    	                 </c:forEach>
		    	         '</select>'+
		    	         </c:when>
		    	         <c:otherwise>
		    	         '<select  class="searchable validate-dropdown" name="work_id_fk" id="work_id_fk'+rNo+'" >'+
		    	         		 '<option value="">Select</option>'+
		    	                 <c:forEach var="obj" items="${contractsList}">
		    				 		'<option workId="${obj.work_id_fk }" value="${obj.work_id_fk }">${obj.contract_short_name }</option>'
		    	                 </c:forEach>
		    	         +'</select>' +                               
		    	         </c:otherwise>
		    	         </c:choose>			   
		    	         '</td>'
    				   +'<td data-head="Responsible Executives" class="input-field h-auto">'+    				   
	    		    <c:choose>
	    	         <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   
	    	         ' <input type="hidden"  id="responsible_executives_id_fk'+rNo+'" name="responsible_executives_id_fks" />'+
	    	          '<select  class="searchable validate-dropdown"  name="excecutives" id="responsible_executives_id_fks'+rNo+'"  multiple="multiple">'+
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	          <c:forEach var="obj" items="${responsiblePeopleList}">
	    			  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	        '</select>'+
	    	         </c:when>
	    	         <c:otherwise>
	    	         ' <input type="hidden"  id="responsible_executives_id_fk'+rNo+'" name="responsible_executives_id_fks" />'+
	    			 '<select  class="searchable validate-dropdown"  name="excecutives" id="responsible_executives_id_fks'+rNo+'" multiple="multiple" >'+
	    	                                          
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	           <c:forEach var="obj" items="${responsiblePeopleList}">
	    			  			 '<option value="${obj.user_id }" > ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	         '</select>'+  
	    	         
	    	          </c:otherwise>
	    	         </c:choose>   			   
    				   
    				   		'</td>'
    				   +'<td class="mobile_btn_close"> <a onclick="deleteAExecutiveRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    				   +'</tr>';
    			
    				 $('#addExecutivesBody').append(html); 
    				// $("#structureContractRowNo").val(rNo);
    				 $('.searchable:not(.units)').select2();  				 

    	   }    
    	        	    
   	    function deleteAExecutiveRow(rowNo){
   	    	$("#AexecutiveRow"+rowNo).remove();
   	    }
      function updateUExecutiveRow(){
    	   	 var rowNo = $("#updateExecutivesBody tr:last-of-type").attr('id').split('executiveRow')[1];
    			 var rNo = Number(rowNo)+1;
    			 var no = 0;    			 
    					 
    			 var html = '<tr id="UexecutiveRow'+rNo+'">'
    				   +'<td data-head="Department" class="input-field">'+
    				   <c:choose>
    			        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">                                 
    			         '<select  class="searchable validate-dropdown" name="work_id_fk" id="u_work_id_fk'+rNo+'"> '
    	         		 +'<option value="">Select</option>'+
    	                 <c:forEach var="obj" items="${contractsList}">
    				 		'<option workId="${obj.work_id_fk }" value="${obj.work_id_fk }" >${obj.contract_short_name }</option>'+
    	                 </c:forEach>
		    	         '</select>'+
		    	         </c:when>
		    	         <c:otherwise>
		    	         '<select  class="searchable validate-dropdown" name="work_id_fk" id="u_work_id_fk'+rNo+'" >'+
		    	         		 '<option value="">Select</option>'+
		    	                 <c:forEach var="obj" items="${contractsList}">
		    				 		'<option workId="${obj.work_id_fk }" value="${obj.work_id_fk }">${obj.contract_short_name }</option>'
		    	                 </c:forEach>
		    	         +'</select>' +                               
		    	         </c:otherwise>
		    	         </c:choose>			   
		    	         '</td>'
    				   +'<td data-head="Responsible Executives" class="input-field h-auto">'+    				   
	    		    <c:choose>
	    	         <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   
	    	         ' <input type="hidden"  id="u_responsible_executives_id_fk'+rNo+'" name="responsible_executives_id_fks" />'+
	    	          '<select  class="searchable validate-dropdown"  name="excecutives" id="u_responsible_executives_id_fks'+rNo+'"  multiple="multiple">'+
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	          <c:forEach var="obj" items="${responsiblePeopleList}">
	    			  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	        '</select>'+
	    	         </c:when>
	    	         <c:otherwise>
	    	         ' <input type="hidden"  id="u_responsible_executives_id_fk'+rNo+'" name="responsible_executives_id_fks" />'+
	    			 '<select  class="searchable validate-dropdown"  name="excecutives" id="u_responsible_executives_id_fks'+rNo+'" multiple="multiple" >'+
	    	                                          
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	           <c:forEach var="obj" items="${responsiblePeopleList}">
	    			  			 '<option value="${obj.user_id }" > ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	         '</select>'+  
	    	         
	    	          </c:otherwise>
	    	         </c:choose>   			   
    				   
    				   		'</td>'
    				   +'<td class="mobile_btn_close"> <a onclick="deleteUExecutiveRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    				   +'</tr>';
    			
    				 $('#updateExecutivesBody').append(html); 
    				// $("#structureContractRowNo").val(rNo);
    				 $('.searchable:not(.units)').select2();  				 

    	   }    
    	        	    
   	    function deleteUExecutiveRow(rowNo){
   	    	$("#UexecutiveRow"+rowNo).remove();
   	    }
    </script>

    </body>


</html>