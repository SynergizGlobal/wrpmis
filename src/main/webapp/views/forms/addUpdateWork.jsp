<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Work</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/work.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <!-- <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> -->
    <style>
        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }
        .fixed-width {
            width: 100%;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		#newButton{
			position: relative;
			float: right;
			right: 24px;
			top: 5px;
		}
    </style>
</head>
<body>
    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                            	 <c:if test="${action eq 'add'}">	
                               			 <h6>Add Work</h6>
                               	 </c:if>
                               	 <c:if test="${action eq 'edit'}">	
                               			 <h6>Edit Work</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                	<c:if test="${not empty success }">
						<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							${success }
						</div>
					</c:if>
					<c:if test="${not empty error }">
						<div class="alert alert-danger alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							${error }
						</div>
					</c:if>
				</div>
			</div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                    	 <c:if test="${action eq 'edit'}">				                
			                	<form action="updateWork-form" id="workForm" name="workForm" method="post" class="form-horizontal" role="form">
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="addWork" id="workForm" name="workForm" method="post" class="form-horizontal" role="form">
						  </c:if>
                      
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown"  name ="project_id_fk" id="project_id_fk"  >
                                   		 <c:if test="${action eq 'add'}">	
                                          	 <option value="${workDeatils.project_id_fk}" selected hidden>select</option>
                                          </c:if>
                                          <c:forEach var="obj" items="${projectsList}">
                       						  <option value="${obj.project_id }"<c:if test="${workDeatils.project_id_fk eq obj.project_id }">selected</c:if>>${obj.project_id}</option>
                                            </c:forEach>
                                    </select>
                                    <label>Project ID</label>
                                      <span id="project_id_fkError"></span>
                                </div>
                                
                                <div class="col s12 m4 input-field">
                               		 <c:if test="${action eq 'edit'}">				                
                                   		  <input id="work_id" type="text" class="form-control" name="work_id" value="${workDeatils.work_id }" readonly >  
                                     	  <label>Work ID :</label>
                                	 </c:if>
                                 </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="work_name" type="text" class="validate" name="work_name" value="${workDeatils.work_name }">
                                    <label for="work_name">Work Name</label>
                                     <span id="work_nameError"></span>
                                </div>
								 <div class="col m2 hide-on-small-only"></div>
                            </div>

                        <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row"> 
                                        <div class="col s12 m4 input-field">
                                            <input id="sanctioned_year" type="text" class="validate datepicker" name="sanctioned_year" value="${workDeatils.sanctioned_year }">
                                            <label for="sanctioned_year">Sanctioned Year</label>
                                             <span id="sanctioned_yearError"></span>
                                            <button type="button" id="sanctioned_year_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                         <div class="col s12 m4 input-field">
                                            <input id="sanctioned_estimated_cost" type="number" class="validate" name="sanctioned_estimated_cost" value="${workDeatils.sanctioned_estimated_cost }" min="1">
                                            <label for="sanctioned_estimated_cost">Sanctioned Estimated Cost</label>
                                             <span id="sanctioned_estimated_costError"></span>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="completeion_period_months" type="text" class="validate" name="completeion_period_months" value="${workDeatils.completeion_period_months }">
                                            <label for="completeion_period_months">Completion Period </label>
                                            <span id="completeion_period_monthsError"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="sanctioned_completion_cost" type="number" class="validate" name="sanctioned_completion_cost" value="${workDeatils.sanctioned_completion_cost }" min="1">
                                    <label for="sanctioned_completion_cost"> Sanctioned Completion Cost </label>
                                     <span id="sanctioned_completion_costError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="anticipated_cost" type="number" class="validate" name="anticipated_cost" value="${workDeatils.anticipated_cost }" min="1">
                                    <label for="anticipated_cost">Anticipated cost</label>
                                      <span id="anticipated_costError"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="year_of_completion" type="text" class="validate datepicker" name="year_of_completion" value="${workDeatils.year_of_completion }">
                                    <label for="year_of_completion">Year of Completion </label>
                                    <span id="year_of_completionError"></span>
                                    <button type="button" id="year_completed_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completion_cost" type="number" class="validate" name="completion_cost" value="${workDeatils.completion_cost }" min="1">
                                    <label for="completion_cost">Completion cost</label>
                                    <span id="completion_costError"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> 
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                  <select  class="searchable validate-dropdown" name="railway_id_fk" id="railway_id_fk" size='1' >
                                  		 <option value="" >select</option>
                                                <c:forEach var="obj" items="${railwaysList}">
  													 <option value="${obj.railway_id }" <c:if test="${workDeatils.railway_id_fk eq obj.railway_id}">selected</c:if>>${obj.railway_name}</option>
                                                </c:forEach>
                                  </select>
                                    <label>Railway Agency</label>
                                      <span id="railway_id_fkError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                  <select  class="searchable validate-dropdown" name="executed_by_id_fk" id="executed_by_id_fk"  size='1'>
                                   <option value="" >select</option>
                                            <c:forEach var="obj" items="${excecuteList}">
                    					  			 <option value="${obj.executed_by_id_fk }"<c:if test="${workDeatils.executed_by_id_fk eq obj.executed_by_id_fk}">selected</c:if>> ${obj.executed_by_id_fk}</option>
                                            </c:forEach>
                                  </select>
                                    <label>Executed By </label>
                                     <span id="executed_by_id_fkError"></span>
                                </div>
                            </div> 
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="revisionsTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Financial Year </th>
                                                <th>PB Item No </th>
                                                <th>Latest Revised Cost </th>
                                                <th>Year of Revision </th>
                                                <th>Revision No </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead> 
                                        <tbody id="revisionsTableBody">
                                           <tr id="revisionRow0">                                            	
                                                <td> 
                              					 <select  name="financial_years"  id="financial_years0"  class="selectDropdown">
                                   					 <option value="" >select</option>
                                         			  <c:forEach var="obj" items="${yearList}">
                    					  				 <option value="${obj.financial_year }"<c:if test="${workDeatils.financial_year eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
                                          			  </c:forEach>
                               					  </select>
                                                </td>
                                                <td>
                                                    <input id="pink_book_item_numbers0" name="pink_book_item_numbers" type="text" class="validate" value="${workDeatils.pink_book_item_number }" 
                                                        placeholder="PB Item Number">
                                                </td>
                                                <td>
                                                    <input id="latest_revised_costs0" name="latest_revised_costs" type="number" class="validate" value="${workDeatils.latest_revised_cost }"
                                                        placeholder="Latest Revised Cost">
                                                </td>
                                                <td>
                                                   <select id="year_of_revisions0" name ="year_of_revisions" >
                                                           <option value="" selected>select</option>
                                                        <c:forEach var="obj" items="${yearList}">
                    					  				  <option  value="${obj.financial_year }"<c:if test="${workDeatils.year_of_revision eq obj.financial_year}">selected</c:if>> ${obj.financial_year}</option>
                                          			    </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="revision_numbers0" name="revision_numbers" type="text" class="validate" value="${workDeatils.revision_number }"
                                                        placeholder="Revision Number">
                                                </td>
                                                <td>
                                                    <input id="remarkss0" name="remarkss" type="text" class="validate" value="${workDeatils.wys_renarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a class="btn waves-effect waves-light red t-c " onclick="removeRevision('0');"> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
  									 <a type="button" id="newButton"class="btn waves-effect waves-light bg-s t-c "  onclick="addRevisionRow()"> <i
                                                            class="fa fa-plus"></i></a>
                                    <input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="workFile" name="workFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" class="materialize-textarea" data-length="1000" name="remarks">${workDeatils.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                      <c:if test="${action eq 'edit'}">
                                       <button type="button" onclick="addWork();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
                                      </c:if>
                                    <c:if test="${action eq 'add'}">
                                        <button type="button" onclick="addWork();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 


    <script src="/pmis/resources/js/jQuery.min.js"></script>
    <script src="/pmis/resources/js/materialize.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script> 


    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.modal').modal();
            $('#remarks').characterCounter();
            // $(".datepicker").datepicker();
            $("#year_of_completion").datepicker({
                format: 'yyyy',
                selectYears: true,
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            });
           /*  $("#financial_year").datepicker({
                format: 'yyyy',
                selectYears: true,
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            });
            $("#year_revision").datepicker({
                format: 'yyyy',
                selectYears: true,
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            }); */
            $('#sanctioned_year').datepicker({
                format: 'yyyy',
                selectYears: true,
                selectMonths: true,
                selectDays: false,
                onSelect: function (arg) {
                    var selectedYear = parseInt(arg.getFullYear());
                    var selectedMonth = parseInt(arg.getMonth() + 1);
                    var year = (selectedMonth >= 4) ? selectedYear + '-' + (selectedYear + 1) : (selectedYear - 1) + '-' + selectedYear;
                    console.log('sanctioned_year : ' + year);
                    $('.confirmation-btns .datepicker-done').click();
                }
            });
            $('#sanctioned_year_icon').click(function () {
                event.stopPropagation();
                $('#sanctioned_year').click();
            });
            $('#year_completed_icon').click(function () {
                event.stopPropagation();
                $('#year_completed').click();
            });
          /*   $('#financial_year_icon').click(function () {
                event.stopPropagation();
                $('#financial_year').click();
            });
            $('#year_revision_icon').click(function () {
                event.stopPropagation();
                $('#year_revision').click();
            }); */
            /* $('#revisionsTable').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    }
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            }); */
        });
        
  //*********************VALIDATION FOR WORK ADD/EDIT FORMS*************************************      
        
        function addWork(){
    		if(validator.form()){ // validation perform
    			document.getElementById("workForm").submit();			
    	 	}
    	}
        
        var validator = $('#workForm').validate({
				
		        	 errorClass: "my-error-class",
				   validClass: "my-valid-class",
				   ignore: ":hidden:not(.validate-dropdown)",
		  		    rules: {
		  		 		  "project_id_fk": {
		  		 			required: true
		  			 	  },"work_name": {
		  			 		required: true
		  			 	  },"sanctioned_estimated_cost": {
		  			 		required: true
		  			 	  },"completeion_period_months": {
		  		 		    required: true
		  		 	   	  },"sanctioned_year": {
		  			 		required: true
		  			 	  },"sanctioned_completion_cost": {
		  			 		required: true
		  			 	  }	,"anticipated_cost": {
		  			 		required: true
		  			 	  }	,"year_of_completion": {
		  			 		required: true
		  			 	  }	,"completion_cost": {
		  			 		required: true
		  			 	  }	,"railway_id_fk": {
		  			 		required: true
		  			 	  }	,"executed_by_id_fk": {
		  			 		required: true
		  			 	  }	,"remarks": {
		  			 		required: true
		  			 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_id_fk": {
		  		 			required: 'This Field Required'
		  		 	  	 },"work_name": {
		  				 	required: 'This Field Required',
		  			 	  },"sanctioned_estimated_cost": {
		  			 		required: ' This Field Required'
		  			 	  },"completeion_period_months": {
		  		 			required: ' This Field Required'
		  		 	  	 },"sanctioned_year": {
		  		 			required: ' This Field Required'
		  		 	  	 },"sanctioned_completion_cost": {
		  		 			required: ' This Field Required' 
		  		 	  	 },"anticipated_cost": {
		  		 			required: '  This Field Required'
		  		 	  	 },"year_of_completion": {
		  		 			required: ' This Field Required  '
		  		 	  	 },"completion_cost": {
		  		 			required: ' This Field Required'
		  		 	  	 },"railway_id_fk": {
		  		 			required: ' This Field Required'
		  		 	  	 },"executed_by_id_fk": {
		  		 			required: 'This Field Required'
		  		 		},"remarks": {
		  		 			required: 'This Field Required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	if (element.attr("id") == "work_name" ){
		  		 		     document.getElementById("work_nameError").innerHTML="";
		  		 		  error.appendTo('#work_nameError');
			   			 }else if (element.attr("id") == "sanctioned_estimated_cost" ){
		  		 		     document.getElementById("sanctioned_estimated_costError").innerHTML="";
		  		 			 error.appendTo('#sanctioned_estimated_costError');
		  		 	   		 }else if (element.attr("id") == "project_id_fk" ){
		  						document.getElementById("project_id_fkError").innerHTML="";
				  		 		 error.appendTo('#project_id_fkError');
				  		 	   	 }else if (element.attr("id") == "completeion_period_months" ){
					  		 		 document.getElementById("completeion_period_monthsError").innerHTML="";
					  		 		 error.appendTo('#completeion_period_monthsError');
					  		 	     }else if (element.attr("id") == "sanctioned_year" ){
							  		 	 document.getElementById("sanctioned_yearError").innerHTML="";
							  		 	 error.appendTo('#sanctioned_yearError');
							  		     }else if (element.attr("id") == "sanctioned_completion_cost" ){
									  		  document.getElementById("sanctioned_completion_costError").innerHTML="";
									  		  error.appendTo('#sanctioned_completion_costError');
									  		 }else if (element.attr("id") == "anticipated_cost" ){
											  	  document.getElementById("anticipated_costError").innerHTML="";
											  	  error.appendTo('#anticipated_costError');
											  	 }else if (element.attr("id") == "year_of_completion" ){
													  document.getElementById("year_of_completionError").innerHTML="";
													  error.appendTo('#year_of_completionError');
													 }else if (element.attr("id") == "completion_cost" ){
														  document.getElementById("completion_costError").innerHTML="";
														  error.appendTo('#completion_costError');
														 }else if (element.attr("id") == "railway_id_fk" ){
															  document.getElementById("railway_id_fkError").innerHTML="";
															  error.appendTo('#railway_id_fkError');
															 }else if (element.attr("id") == "executed_by_id_fk" ){
																  document.getElementById("executed_by_id_fkError").innerHTML="";
																  error.appendTo('#executed_by_id_fkError');
																 }else if (element.attr("id") == "remarks" ){
																	  document.getElementById("remarksError").innerHTML="";
																	  error.appendTo('#remarksError');
																	 }else{
															            	error.insertAfter(element);
															            } 
			   		 	
			   		},submitHandler:function(form){
				    	form.submit();
				    }
				}); 
        //**********************ADD NEW ROW TO REVISION TABLE *************************************************************************************
       	 
  	  function addRevisionRow(){
  		
        var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var total = 0;
       /*   $('select[name="financial_years"]').each(function(){
        	if($(this).val() != ''){
                $('.selectDropdown').not(this).find('option[value="'+$(this).val()+'"]').remove();
        	} 
        });
         */
        var className = '.selectDropdown';
       
  		var html = '<tr id="revisionRow'+rNo+'"><td> <div>'
   			+'<select  name="financial_years" id="financial_years'+rNo+'"  class="selectDropdown" >'	   			
   			html = html	+'<option value=" " >select</option>'
   		
   			$('.selectDropdown').each(function(){
   				<c:forEach var="obj" items="${yearList}">
   					if($(this).val()== "${obj.financial_year }"){
	                     $('.selectDropdown').$(this).find('option[value="'+$(this).val()+'"]').remove();
   					}else {
   	   					html = html	 +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
   					}
			    </c:forEach>
   			});
   		html = html		+'</select></div></td>'
		   +'<td><input  type="text" class="validate" id="pink_book_item_numbers'+rNo+'" name="pink_book_item_numbers" placeholder="PB Item Number"></td>'
		   +'<td><input  type="number" class="validate" id="latest_revised_costs'+rNo+'" name="latest_revised_costs" placeholder="Latest Revised Cost"></td>'
		   +'<td> <div>'
		   +'<select class="test" id="year_of_revisions'+rNo+'" name="year_of_revisions" >'
		   +'<option value=" " selected>select</option>'
		   <c:forEach var="obj" items="${yearList}">
			 +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
		    </c:forEach>
				+'</select></div></td>'
		   +'<td><input  type="text" class="validate" id="revision_numbers'+rNo+'" name="revision_numbers" placeholder="Revision Number"></td>'
		   +'<td><input  type="text" class="validate" id="remarkss'+rNo+'" name="remarkss" placeholder="Remarks"></td>'
	   	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	 
		 $('#revisionsTableBody').append(html);
		 $("#rowNo").val(rNo);
		 $('select').formSelect();
		 
		 //******************* Revision table Validation***************************************

		/* var className = '.selectDropdown';
		  $(className).focus(function () {
		                  oldValue = this.value;
		                  oldText = $(this).find('option:selected').text();
		              })
		                $(className).change(function () {
		                  var newSelectedValue = $(this).val();
		                  if (newSelectedValue != "") {
		                      $('.selectDropdown').not(this).find('option[value="'+newSelectedValue+'"]').remove();
		                  }
		                  if ($(className).not(this).find('option[value="'+this.value+'"]').length == 0) { // NOT EXIST
		                      $(className).not(this).append('<option value='+this.value+'>' + $(this).find('option:selected').text() + '</option>');
		                  }
		                  $(this).blur();
		              }); */
     }
  	
   	/*  $('#newButton').on('click', function () {
         $('tbody').append(html);
         $('select').formSelect();
        // $('#clone').clone(true);
        
     }); */
   /* 	$(document).on('click', '#remove', function() {
        $(this).parents('tr').remove();
    }); */
    
    function removeRevision(rowNo){
    	$("#revisionRow"+rowNo).remove();
    }
  
   
     
    </script>
</body>

</html>