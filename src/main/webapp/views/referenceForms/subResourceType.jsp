<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Sub Resource Type</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <style>
		.dataTables_length{
		    text-align: center;
		}		
		
		@media (min-width: 480px) and (max-width: 839px){
		    .mdl-cell--6-col, .mdl-cell--6-col-tablet.mdl-cell--6-col-tablet {
		        width: 100%;
		        text-align:center;
		    }
		    div.dataTables_wrapper div.dataTables_filter{
		            text-align:center;
		    }
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
                            <h6> Sub Resource Type</h6>
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
                                <a style="height: auto;" class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Sub Resource Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="sub_resource_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Resource Type</th>
                                            <th>Sub Resource Type</th>
                                            <c:forEach var="tObj" items="${subResourceTypeDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${subResourceTypeDetails.dList1}" varStatus="indexs">
											<tr><td>
												${obj.resource_type_fk } 
											</td>
											<input type="hidden" id="resource_type_fk${indexs.count}" value="${obj.resource_type_fk }" name="resource_type_fk"   class="findLengths"/> 
											<input type="hidden" id="sub_resource_type${indexs.count}" value="${obj.sub_resource_type }" class="findLengths1"/>
											<input type="hidden" id="id${indexs.count}" value="${obj.id }" \/>
											<td>
												${obj.sub_resource_type }</td>
									
											<c:forEach var="tObj" items="${subResourceTypeDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${subResourceTypeDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.sub_resource_type eq obj.sub_resource_type }"> 
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
										 	<c:forEach var="oSbj"  items="${subResourceTypeDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.sub_resource_type eq obj.sub_resource_type }"> 
												      	<a onclick="deleteRow('${indexs.count}');"  class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-sub-resource-type" id="subResourceType" name="subResourceType" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Sub Resource Type <span class="right modal-action modal-close"><span
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
                                 <p class="searchable_label">Resource Type</p>
                                 <select class="searchable validate-dropdown" name="resource_type_fk" id="area" onchange="doValidate(this.value,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${resourceTypeList }">
		                                      <option value="${obj.resource_type }">${obj.resource_type }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="resource_type_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <div class="input-field col s12 m6">
                                <input id="sub_resource_type" type="text" name="sub_resource_type" class="validate" onkeyup="doValidate(null,this.value)">
                                <label for="sub_resource_type">Sub Resource Type</label>
                                <span id="sub_resource_typeError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center;" id="DivError"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addSubResourceType()" class="btn waves-effect waves-light bg-m " id="bttn">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/sub-resource-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close " style="width: 100%">Cancel</a>
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
		 <form action="<%=request.getContextPath() %>/update-sub-resource-type" id="updateSubResourceTypeForm" name="updateSubResourceTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Sub Resource Type <span class="right modal-action modal-close"><span
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
                                 <p class="searchable_label">Resource Type</p>
                                 <select class="searchable validate-dropdown" name="resource_type_fk_new" id="resource_type_fk_new" onchange="doValidateUpdate(null,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${resourceTypeList }">
		                                      <option value="${obj.resource_type }" id="${obj.resource_type }" >${obj.resource_type }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="resource_type_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Sub Resource Type</label>
                                <span id="sub_resource_typeError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center;" id="DivUpdateError"></div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateRiskAreaType()" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close "
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/sub-resource-type"
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
    	<input type="hidden" name="id" id="sub_resource_types" />
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

            var table = $('#sub_resource_type_table').DataTable({
            	"order": [],
                columnDefs: [
                    {
                        targets: [0],
                       /*  className: 'mdl-data-table__cell--non-numeric', */
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                paging:false,
                "sScrollXInner": "100%",
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
           var value = $('#area').val();
           var value1 = $('#sub_resource_type').val();
           value = value.trim();
           value1 = value1.trim();
           var print_value = value;	
     	   var print_value2 = value1;
           var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
           var s = Object.keys(ek).find(key => ek[key] === value);
           if(value != null){ value = value.toLowerCase();}
           if(value1 != null){ value1 = value1.toLowerCase();}
         
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	  
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     			
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
   				   $('#resource_type_fkError').text('');
   				   $('#sub_resource_typeError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
        			   $('#DivError').text('');
        			   $('#resource_type_fkError').text('');
        			   $('#sub_resource_typeError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  
     		   }
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#resource_type_fk_new').val();
           var value1 = $('#value_new').val();
           value = value.trim();
           value1 = value1.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#department_old').val()
           var valueOld2 = $('#department_name_old').val()
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
     	   value = value.toLowerCase();
     	   value1 = value1.toLowerCase();
     	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   delete ek[s];
     	   delete ak[s1];
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			 if(findVal != null){ findVal = findVal.toLowerCase();}
  			 if(findVal2 != null){ findVal2 = findVal2.toLowerCase();}
   		     if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
  				   $('#resource_type_fkError').text('');
 			   	   $('#value_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			 
       			       $('#DivUpdateError').text('');
       			       $('#resource_type_fkError').text('');
       			   	   $('#value_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function addSubResourceType(){
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			document.getElementById("subResourceType").submit();	
           }
        }
       
        var validator = $('#subResourceType').validate({
         ignore: ":hidden:not(.validate-dropdown)",
       	 rules: {
       		 	"sub_resource_type": {
   			 		  required: true
       			 },"resource_type_fk": {
   			 		  required: true
       			 }
   			},messages: {
   		 		 "sub_resource_type": {
   			 		  required: 'Required'
   			 	 }, "resource_type_fk": {
   			 		  required: 'Required'
   			 	 }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "sub_resource_type" ){
   				     document.getElementById("sub_resource_typeError").innerHTML="";
   			 	     error.appendTo('#sub_resource_typeError');
   			   }else if(element.attr("id") == "area" ){
   				     document.getElementById("resource_type_fkError").innerHTML="";
   			 	     error.appendTo('#resource_type_fkError');
   			   }
   	        }
        });
       
        var validator1 = $('#updateSubResourceTypeForm').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"value_new": {
      			 		  required: true
          			 }, "resource_type_fk_new": {
    			 		  required: 'true'
     			 	 }
      			},messages: {
      		 		 "value_new": {
      			 		  required: 'Required'
      			 	 }, "resource_type_fk_new": {
      			 		  required: 'Required'
       			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "value_new" ){
      				     document.getElementById("value_newError").innerHTML="";
      			 	     error.appendTo('#value_newError');
      			   }else if(element.attr("id") == "resource_type_fk_new" ){
      			     document.getElementById("resource_type_fkError").innerHTML="";
      		 	     error.appendTo('#resource_type_fkError');
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
      
       $("#sub_resource_type_table td:not(:last-child)").each(function(){ 
    	    $(this).text($.trim($(this).text()));
    	})
       
   function updateRow(no) {
    	   	  var resource_type_fk = $('#resource_type_fk'+no).val();
      	      var sub_resource_type = $('#sub_resource_type'+no).val();
      	      var item_no = $('#item_no'+no).val();
      	     
      	      $('#value_old').val($.trim(sub_resource_type))
      	      $('#onlyUpdateModal').modal('open');
      	      $('#onlyUpdateModal #value_new').val($.trim(sub_resource_type)).focus();
      	      $('#onlyUpdateModal #item_no_new').val($.trim(item_no)).focus();
      	      $('#onlyUpdateModal #resource_type_fk_new').val($.trim(resource_type_fk)).focus();
      	    	//$('#resource_type_fk_new option:contains(' + resource_type_fk + ')').attr('selected', 'selected');
      	    	$('select[name^="resource_type_fk_new"] option[value="'+ resource_type_fk +'"]').attr("selected","selected");
      	    	$('.searchable').select2();
      	  }
      	  
      	  function deleteRow(count){
      		  var id = $("#id"+count).val();
      		  console.log(id);
      	  	$("#sub_resource_types").val(id);
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
      		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-sub-resource-type');
      		    	    	$('#getForm').submit();
      		           }else {
      		                swal("Cancelled", "Record is safe :)", "error");
      		            }
      		        });
      		    }
    </script>

</body>

</html>