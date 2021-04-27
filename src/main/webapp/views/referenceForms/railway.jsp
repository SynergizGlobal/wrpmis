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
    <title>Railway</title>
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
                            <h6> Railway</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Railway</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="railway_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Railway ID</th>
                                            <th>Railway Name</th>
                                            <c:forEach var="tObj" items="${railwayDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${railwayDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="railway_id${indexs.count}" value="${obj.railway_id }" /  class="findLengths">
												${obj.railway_id }</td>
											<td>
												<input type="hidden" id="railway_name${indexs.count}" value="${obj.railway_name }" class="findLengths2" />
												${obj.railway_name }</td>
											<c:forEach var="tObj" items="${railwayDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${railwayDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.railway_id eq obj.railway_id }"> 
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
										 	<c:forEach var="oSbj"  items="${railwayDetails.dList}" varStatus="indexx"> 
												<c:choose>  
												    <c:when test="${oSbj.railway_id eq obj.railway_id }"> 
												      	<a onclick="deleteRow('${ oSbj.railway_id }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-railway" id="addRailwayForm" name="addRailwayForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Railway <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="railway_id" type="text" name="railway_id" class="validate"  onkeyup="doValidate(this.value,null)">
                                <label for="railway_id">Railway id</label>
                                <span id="railway_idError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="railway_name" type="text" name="railway_name" class="validate"  onkeyup="doValidate(null,this.value)">
                                <label for="railway_name">Railway Name</label>
                                <span id="railway_nameError" class="error-msg" ></span>
                            </div>
                             <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		</div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn"  class="btn waves-effect waves-light bg-m">Add</button>
                                </div>
                            </div>
                        
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/railway"
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
		 <form action="<%=request.getContextPath() %>/update-railway" id=updateRailwayForm name="id=updateRailwayForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Railway <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="railway_id">Railway id</label>
                                <span id="railway_idUpdateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="railway_name_new" type="text" name="railway_name_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="railway_name_old" type="hidden" name="railway_name_old"  >
                                <label for="railway_name">Railway Name</label>
								<span id="value_newError" class="error-msg" ></span>           
		                    </div>
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
                                        <a href="<%=request.getContextPath()%>/railway"
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
    	<input type="hidden" name="railway_id_val" id="railway_id_val" />
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
          
            var table = $('#railway_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [6] },
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
        var flag = false; 
        function doValidate(value,value1){
           var value = $('#railway_id').val();
           var value1 = $('#railway_name').val();
           value = value.trim();
           value1 = value1.trim();
           var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
           var s = Object.keys(ek).find(key => ek[key] === value);
           if(value != null){ value = value.toLowerCase();}
           if(value1 != null){ value1 = value1.toLowerCase();}
         
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	  
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
   				   $('#railway_nameError').text('');
   				   $('#railway_idError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#railway_idError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
     				 flag = false;
      			     return false;
     			  }else if(findVal2 == value1 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#railway_nameError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 flag = false;
      			     return false;
     			  }else{
        			   $('#DivError').text('');
        			   $('#railway_idError').text('');
        			   $('#railway_nameError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  }
     		
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#value_new').val();
           var value1 = $('#railway_name_new').val();
           value = value.trim();
           value1 = value1.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#value_old').val()
           var valueOld2 = $('#railway_name_old').val()
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
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
  				   $('#railway_idUpdateError').text('');
 			   	   $('#value_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
      				 $('#bttnUpdate').prop('disabled', true);
      				 $('#DivUpdateError').text('');
      				 $('#railway_idUpdateError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
      				 updateFlag = false; 
       			     return false;
     			 }else if(findVal2 == value1 ){
     				 $('#bttnUpdate').prop('disabled', true);
     				 $('#DivUpdateError').text('');
     				 $('#value_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 flag = false;
      			     return false;
     			  } else{
       			       $('#DivUpdateError').text('');
       			       $('#railway_idUpdateError').text('');
       			   	   $('#value_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   }
       			    
     			
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#railway_idUpdateError').text('');
   		 $('#value_newError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
      
       
     $("#addRailwayForm").submit(function (e) {
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			if(flag){
      				document.getElementById("addRailwayForm").submit();	
      			 }
      			 $(".page-loader").hide();
      			 return false;
          }
      })
     $("#updateRailwayForm").submit(function (e) {
      	 if(validator1.form()){ 
  			$(".page-loader").show();
  			$("#addUpdateModal").modal();
  			if(updateFlag){
  				document.getElementById("updateRailwayForm").submit();	
  			 }
  			 $(".page-loader").hide();
  			 return false;
         }
     })
      var validator = $('#railwayForm').validate({
      	 rules: {
      		 "railway_id": {
  			 		  required: true
      		 },"railway_name": {
  			 		  required: true
      		 }
  			},messages: {
  		 		   "railway_id": {
  			 		  required: 'Required'
  			 	  },"railway_name": {
  			 		  required: 'Required'
  			 	  }
  	        },errorPlacement:function(error, element){
  	        	 if(element.attr("id") == "railway_id" ){
  				     document.getElementById("railway_idError").innerHTML="";
  			 	     error.appendTo('#railway_idError');
  				 }else  if(element.attr("id") == "railway_name" ){
  				     document.getElementById("railway_nameError").innerHTML="";
  			 	     error.appendTo('#railway_nameError');
  				 }
  	        }
      });
      
      var validator1 =  $('#updateRailwayForm').validate({
       	 rules: {
       		 "railway_name_new": {
 			 		  required: true
       		 }, "value_new": {
 			 		  required: true
       		 }
 			},messages: {
 		 		   "railway_name_new": {
 			 		  required: 'Required'
 			 	  },"value_new": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "railway_name_new" ){
 				     document.getElementById("value_newError").innerHTML="";
 			 	     error.appendTo('#value_newError');
 				 }else  if(element.attr("id") == "value_new" ){
  				     document.getElementById("railway_idUpdateError").innerHTML="";
  			 	     error.appendTo('#railway_idUpdateError');
  				 }
 	        }
       	
       });
        
        $('input').change(function(){
  	           if ($(this).val() != ""){
  	               $(this).valid();
  	           }
  	     });

        function updateRow(no) {
    	      var railway_id = $('#railway_id'+no).val();
    	      var railway_name = $('#railway_name'+no).val();
    	      $('#value_old').val($.trim(railway_id))
    	      $('#railway_name_old').val($.trim(railway_name))
    	      $('#onlyUpdateModal').modal('open');
    	      $('#onlyUpdateModal #value_new').val($.trim(railway_id)).focus();
    	      $('#onlyUpdateModal #railway_name_new').val($.trim(railway_name)).focus();
    	  }
    	  
    	  function deleteRow(val){
    	  	$("#railway_id_val").val(val);
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
    		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-railway');
    		    	    	$('#getForm').submit();
    		           }else {
    		                swal("Cancelled", "Record is safe :)", "error");
    		            }
    		        });
    		    }
    	</script>

</body>
</html>
