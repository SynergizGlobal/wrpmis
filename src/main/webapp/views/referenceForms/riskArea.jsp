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
    <title>Risk Area</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">
    <style>
        input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		input[type=number] {
		  -moz-appearance: textfield;
		}
        .last-column {
            word-break: break-all;
            white-space: inherit;
        }

        .error {
            color: red;
        }
     
		.dataTables_length{
		    text-align: center;
		}
		th{    		
    		text-transform: capitalize;    		
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
                            <h6>  Area</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add  Area</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="risk_area_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Area</th>
                                            <th> Short Name </th>
                                            <th>Item No</th>
                                            <c:forEach var="tObj" items="${riskAreaDetails.tablesList}" >
                                            	<%--  <th>${tObj.tName } <br>(count)</th> --%>
                                            	 <c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } <br>(count)</th>
                                            	</c:forEach>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${riskAreaDetails.dList1}" varStatus="indexs">
											<tr>
												<td>
													<input type="hidden" id="areaId${indexs.count}" value="${obj.area }" class="findLengths" />
													${obj.area }
												</td>
												<td>
													<input type="hidden" id="shortNameId${indexs.count}" value="${obj.area_short_name }" class="findLengths2" /> 
													${obj.area_short_name }
												</td>
												<td>
													<input type="hidden" id="item_noId${indexs.count}" value="${obj.item_no }"  class="findLengths1"/>
													${obj.item_no }
												</td>
												<c:forEach var="tObj" items="${riskAreaDetails.tablesList}" varStatus="index">
													<td>
														<c:forEach var="cObj" items="${riskAreaDetails.countList}" >
															<c:choose> 
														    <c:when test="${tObj.tName eq cObj.tName }"> 
														    		<c:choose>  
																	    <c:when test="${cObj.area eq obj.area }"> 
																	      	 ( ${cObj.count } )   
																	    </c:when>  
																	    <c:otherwise>  
																	    </c:otherwise>   
																</c:choose>
															</c:when>
															<c:otherwise> 
														   </c:otherwise>
													</c:choose>
													</c:forEach>
													</td>
                                            </c:forEach>
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${riskAreaDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.area eq obj.area }"> 
												      	<a onclick="deleteRow('${ oSbj.area }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-risk-area" id="riskAreaForm" name="riskAreaForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add  Area <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="risk_area_text" type="text" name="area" class="validate"  onkeyup="doValidate(this.value,null,null)">
                                <label for="risk_area_text"> Area</label>
                                <span id="areaError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="risk_short_name_text" type="text" name="area_short_name" class="validate"  onkeyup="doValidate(null,this.value,null)">
                                <label for="risk_short_name_text"> Short Name</label>
                                <span id="shortNameError" class="error-msg" ></span>
                            </div>
                            </div><div class="row">
                            <div class="input-field col s12 m6">
                                <input id="item_no" type="number" name="item_no" min="1" class="validate"  onkeyup="doValidate(null,null,this.value)">
                                <label for="item_no">Item No</label>
                                <span id="item_noError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		</div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addRiskArea()" class="btn waves-effect waves-light bg-m " id="bttn">Add </button>
                                </div>
                            </div>
                      
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-area"
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
		 <form action="<%=request.getContextPath() %>/update-risk-area" id="updateRiskAreaForm" name="updateRiskAreaForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update  Area <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                         <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new"> Area</label>
                                <span id="value_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="area_short_name_new" type="text" name="area_short_name_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <input id="short_value_old" type="hidden" name="short_value_old"  >
                                <label for="area_short_name_new"> Short Name</label>
                                <span id="short_value_newError" class="error-msg" ></span>
                            </div>
                            </div><div class="row">
                            <div class="input-field col s12 m6">
                                <input id="item_no_new" name="item_no_new" type="text" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                                <label for="item_no_new">Item No</label>
                                 <input id="item_no_old" type="hidden" name="item_no_old"  >
                                <span id="item_no_newError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
                       		</div>
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
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/risk-area"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
    <!-- <div id="errorModal" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h6>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="area" id="area" />
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

            var table = $('#risk_area_table').DataTable({
            	"order": [],
                columnDefs: [
                    {
                        targets: [1],
                        //className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [3] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "scrollX": true,
                "paging":false,
                "info":false,
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
           var value = $('#risk_area_text').val();
           var value1 = $('#item_no').val();
           var value2 = $('#risk_short_name_text').val();
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
   				   $('#areaError').text('');
   				   $('#item_noError').text('');
   				   $('#shortNameError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#areaError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#item_noError').text('');}else{ $('#item_noError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
    				 if(findVal3 != value2 ){$('#shortNameError').text('');}else{ $('#shortNameError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
    				 
     				 flag = false;
      			     return false;
     			  }else if(findVal2 == value1 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#item_noError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#areaError').text('');}else{ $('#areaError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
    				 if(findVal3 != value2 ){$('#shortNameError').text('');}else{ $('#shortNameError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
    				 
     				 flag = false;
      			     return false;
     			 }else if(findVal3 == value2 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#shortNameError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#areaError').text('');}else{ $('#areaError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				if(findVal2 != value1 ){$('#item_noError').text('');}else{ $('#item_noError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
    				 
     				 flag = false;
      			     return false;
     			  }else{
        			   $('#DivError').text('');
        			   $('#areaError').text('');
        			   $('#item_noError').text('');
        			   $('#shortNameError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  }
     		
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1,value2){
           var value = $('#value_new').val();
           var value1 = $('#item_no_new').val();
           var value2 = $('#area_short_name_new').val();
           value = value.trim();
           value1 = value1.trim();
           value2 = value2.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var print_value3 = value2;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#value_old').val()
           var valueOld2 = $('#item_no_old').val()
           var valueOld3 = $('#short_value_old').val()
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
  				   $('#value_newError').text('');
 			   	   $('#item_no_newError').text('');
 			   	   $('#short_value_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
      				 $('#bttnUpdate').prop('disabled', true);
      				 $('#DivUpdateError').text('');
      				 $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
      				 if(findVal2 != value1 ){$('#item_no_newError').text('');}else{ $('#item_no_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
    				 if(findVal3 != value2 ){$('#short_value_newError').text('');}else{ $('#short_value_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
    				 
      				 updateFlag = false; 
      				 $('#bttnUpdate').prop('disabled', true);
      				 return false;
     			 }else if(findVal2 == value1 ){
     				 $('#DivUpdateError').text('');
     				 $('#item_no_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#value_newError').text('');}else{ $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
    				 if(findVal3 != value2 ){$('#short_value_newError').text('');}else{ $('#short_value_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');}
    				
     				 updateFlag = false;
     				 $('#bttnUpdate').prop('disabled', true);
     				 return false;
     			 }else if(findVal3 == value2 ){
     				 $('#DivUpdateError').text('');
     				 $('#item_no_newError').text('" '+print_value3+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#value_newError').text('');}else{ $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
    				 if(findVal2 != value1 ){$('#item_no_newError').text('');}else{ $('#item_no_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 
     				 updateFlag = false;
     				 $('#bttnUpdate').prop('disabled', true);
     				 return false;
     			  }else{
       			       $('#DivUpdateError').text('');
       			       $('#department_newError').text('');
       			   	   $('#item_no_newError').text('');
       			       $('#short_value_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   }
     		   }
     		   
     		   count++; 
     	   }
        }
     function addRiskArea(){
        	 if(validator.form()){ 
    			$(".page-loader").show();
    			$("#addUpdateModal").modal();
    			document.getElementById("riskAreaForm").submit();	
         }
     }
     
     function updateRiskArea(){
    	 if(validator1.form()){ 
			$(".page-loader").show();
			$("#onlyUpdateModal").modal();
			document.getElementById("updateRiskAreaForm").submit();	
     }
 	}
     var validator = $('#riskAreaForm').validate({
     	 rules: {
     		 "area": {
 			 		  required: true
     		 },"item_no": {
 			 		  required: true
     		 },"area_short_name": {
 			 		  required: true
     		 }
 			},messages: {
 		 		   "area": {
 			 		  required: 'Required'
 			 	  },"item_no": {
 			 		  required: 'Required'
 			 	  },"area_short_name": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "risk_area_text" ){
 				     document.getElementById("areaError").innerHTML="";
 			 	     error.appendTo('#areaError');
 				 }else if(element.attr("id") == "risk_short_name_text" ){
 				     document.getElementById("shortNameError").innerHTML="";
 			 	     error.appendTo('#shortNameError');
 				 }else if(element.attr("id") == "item_no" ){
 				     document.getElementById("item_noError").innerHTML="";
 			 	     error.appendTo('#item_noError');
 				 }
 	        }
     	
     });
     
     var validator1 = $('#updateRiskAreaForm').validate({
    	 rules: {
    		 	"value_new": {
			 		  required: true
    			 },"item_no_new":{
					  required: true
    			}, "area_short_name_new":{
    				required:true
    			}
			},messages: {
		 		 "value_new": {
			 		  required: 'Required'
			 	 }, "item_no_new": {
			 		  required: 'Required'
			 	 }, "area_short_name_new":{
	    				required: 'Required'
	    		 }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "value_new" ){
				     document.getElementById("value_newError").innerHTML="";
			 	     error.appendTo('#value_newError');
			   }else if(element.attr("id") == "item_no_new" ){
			     document.getElementById("item_no_newError").innerHTML="";
		 	     error.appendTo('#item_no_newError');
		  }
	        }
    });
  
  $('input').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
	   });


  function updateRow(no) {
      var area = $('#areaId'+no).val();
      var shortName = $('#shortNameId'+no).val();
      var item_no = $('#item_noId'+no).val();
      $('#value_old').val($.trim(area))
      $('#short_value_old').val($.trim(shortName))
      $('#item_no_old').val($.trim(item_no))
      $('#onlyUpdateModal').modal('open');
      $('#onlyUpdateModal #value_new').val($.trim(area)).focus();
      $('#onlyUpdateModal #area_short_name_new').val($.trim(shortName)).focus();
      $('#onlyUpdateModal #item_no_new').val($.trim(item_no)).focus();
  }
  
  function deleteRow(val){
  	$("#area").val(val);
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
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk-area');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
</script>

</body>


</html>