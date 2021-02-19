<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Data Gathering </c:if>
		 <c:if test="${action eq 'add'}"> Add Data Gathering </c:if>
	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
     
        .input-field .searchable_label {
            font-size: 0.9rem;
        }
      
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                               		 <c:if test="${action eq 'edit'}">Update Data Gathering </c:if>
		 							 <c:if test="${action eq 'add'}"> Add Data Gathering </c:if>
                               </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
	                            <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-data-gathering" id="dataGatherigForm" name="dataGatherigForm" method="post" class="form-horizontal" role="form" >
	                            </c:if>
				                <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-data-gathering" id="dataGatherigForm" name="dataGatherigForm" method="post" class="form-horizontal" role="form" >
							    </c:if>
						
						<%--   <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Project Priority</p>
                                    <select class="searchable validate-dropdown" name="project_priority_fk" id="project_priority_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${priorityList }">
		                                     <option value="${obj.project_priority_fk }" <c:if test="${dataGatheringDetails.project_priority_fk eq obj.project_priority_fk}">selected</c:if>>${obj.project_priority_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="project_priority_fkError" class="error-msg" ></span>
                                </div>   --%>                             
                                <!-- <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work Name</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div> -->
                               <!--  <div class="col m2 hide-on-small-only"></div>
                            </div> -->
                            <input type="hidden" name="id" id="id" value="${dataGatheringDetails.id }" />
							<c:if test="${action eq 'add'}">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m4 input-field">
									<p class="searchable_label">Project <span class="required">*</span></p>
									<select class="searchable validate-dropdown" name="project_id_fk"
										id="project_id_fk"  onchange="getWorksList(this.value);">
										<option value="">Select</option>
											<c:forEach var="obj" items="${projectsList }">
		                                         <option value="${obj.project_id_fk }" >${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
		                                    </c:forEach>
									</select> <span id="project_id_fkError" class="error-msg"></span>
								</div>
								<div class="col s12 m4 input-field">
									<p class="searchable_label">Work <span class="required">*</span></p>
									<select class="searchable validate-dropdown" name="work_id_fk"
										id="work_id_fk" onchange="getContractsList(this.value);">
										<option value="">Select</option>
										 	<c:forEach var="obj" items="${formWorksList }">
	                                      	   	<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                        </c:forEach>
									</select> <span id="work_id_fkError" class="error-msg"></span>
								</div>
								<div class="col m2 hide-on-small-only"></div>
						</div>
						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m8 input-field">
								<p class="searchable_label">Contract <span class="required">*</span></p>
								<select class="searchable validate-dropdown" name="contract_id_fk"
									id="contract_id_fk"  onchange="resetWorksAndProjectsDropdowns();">
									<option value="">Select</option>
										 <c:forEach var="obj" items="${contractsList }">
                                      	    <option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
								</select> <span id="contract_id_fkError" class="error-msg"></span>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>
                      <!--   <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                   <p class="searchable_label">Data Gathering Work</p>
                                   <select class="searchable validate-dropdown" name="DGwork_id_fk" id="DGwork_id_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
		                                     <option value="${obj.work_id_fk }" <c:if test="${dataGatheringDetails.work_id_fk eq obj.work_id_fk}">selected</c:if>>${obj.work_id_fk} - ${obj.work_short_name}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>                                   
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                          </div> -->
                       </c:if>
					   <c:if test="${action eq 'edit'}">
						 <div class="row">
							  <div class="col m2 hide-on-small-only"></div>
							   <div class="col s12 m4 input-field">
                                    <input type="text"  value="${dataGatheringDetails.project_id_fk}- ${dataGatheringDetails.project_name}" readonly />
									<label for="project_id_fk">Project <span class="required">*</span></label>
							  </div> 
                               <div class="col s12 m4 input-field"> 
                                  <input type="text"  value="${dataGatheringDetails.work_id_fk}- ${dataGatheringDetails.work_short_name}" readonly />
							      <label for="work_id_fk">Work <span class="required">*</span></label>
                               </div>
                               <div class="col m2 hide-on-small-only"></div>
	                        </div>
	                        <div class="row">
							  <div class="col m2 hide-on-small-only"></div>
	                             <div class="col s12 m8 input-field"> 
                              	    <input type="text"  value="${dataGatheringDetails.contract_id_fk} - ${dataGatheringDetails.contract_short_name}" readonly />
                                 	<label for="contract_id_fk">Contract <span class="required">*</span></label>     
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                          <!--  <div class="row">
							  <div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m8 input-field"> 
									    <p class="searchable_label">Data Gatherinng Work </p>
	                                    <input type="text"  value="${dataGatheringDetails.DGwork_id_fk}- ${dataGatheringDetails.DGwork_name}" readonly />
	                            </div>
	                            <div class="col m2 hide-on-small-only"></div>
	                       </div> -->
						</c:if>
							                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="target_date" name="target_date" type="text" class="validate datepicker" value="${dataGatheringDetails.target_date }">
                                    <label for="target_date">Target Date</label>
                                    <button type="button" id="target_date_icon" class="white"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                              <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Status</p>
                                    <select class="searchable validate-dropdown" name="status_fk" id="status_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${statusList }">
		                                     <option value="${obj.status_fk }" <c:if test="${dataGatheringDetails.status_fk eq obj.status_fk}">selected</c:if>>${obj.status_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                   <div class="col s12 m4 input-field">
                                    <input id="start_date" name="start_date" type="text" class="validate datepicker" value="${dataGatheringDetails.start_date }">
                                    <label for="start_date">Start Date</label>
                                    <button type="button" id="start_date_icon" class="white"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="finish_date" name="finish_date" type="text" class="validate datepicker" value="${dataGatheringDetails.finish_date }">
                                    <label for="finish_date">Finish Date</label>
                                    <button type="button" id="finish_date_icon" class="white"><i
                                            class="fa fa-calendar"></i></button>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <!-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> -->
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="description" name="description" class="materialize-textarea" data-length="1000">${dataGatheringDetails.description }</textarea>
                                    <label for="description">Description</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${dataGatheringDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateDataGatherigForm();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addDataGatherigForm();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                               <div class="col s12 m4">
                                    <div class="center-align m-1">
                                          <a href="<%=request.getContextPath()%>/data-gathering" class="btn waves-effect waves-light bg-s white-text"
                                            style="width:100%">Cancel</a>
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

 <!-- Page Loader -->
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


        <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    

     <script>
     $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    });
     $(document).ready(function () {
         $('select:not(.searchable)').formSelect();
         $('.searchable').select2();
         $('#remarks').characterCounter();
        // $("#target_date,#start_date,#finish_date").datepicker();
         $('#target_date_icon').click(function () {
             event.stopPropagation();
             $('#target_date').click();
         });
         $('#start_date_icon').click(function () {
             event.stopPropagation();
             $('#start_date').click();
         });
         $('#finish_date_icon').click(function () {
             event.stopPropagation();
             $('#finish_date').click();
         });
         
         var project_id_fk = "${dataGatheringDetails.project_id_fk}";
         if($.trim(project_id_fk) != ''){
         	getWorksList(project_id_fk);
         }
         var work_id_fk = "${dataGatheringDetails.work_id_fk}";
         if($.trim(work_id_fk) != ''){
         	getContractsList(work_id_fk);
         }

     });
     
     //geting works list from database 
     function getWorksList(projectId) {
     	$(".page-loader").show();
         $("#work_id_fk option:not(:first)").remove();
         $("#contract_id_fk option:not(:first)").remove();

         if ($.trim(projectId) != "") {
             var myParams = { project_id_fk: projectId };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getWorkListForDataGatheringForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                             var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = '  - ' + $.trim(val.work_short_name) }
                             $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workShortName) + '</option>');
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 }
             });
         }else{
         	$(".page-loader").hide();
         }
     }
     //geting contracts list    
     function getContractsList(work_id_fk) {
     	$(".page-loader").show();
         $("#contract_id_fk option:not(:first)").remove();
         
         if ($.trim(work_id_fk) != "") {
             var myParams = { work_id_fk: work_id_fk };
             $.ajax({
             	url: "<%=request.getContextPath()%>/ajax/getContractsListForDataGatheringForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                         	var contract_short_name = '';
                             if ($.trim(val.contract_short_name) != '') { contract_short_name = '  - ' + $.trim(val.contract_short_name) }
                             $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 }
             });
         }else{
         	$(".page-loader").hide();
         }
     }
     
     function resetWorksAndProjectsDropdowns(){
     	$(".page-loader").show();        	
     	var projectId = '';
     	var workId = ''
    		var contract_id_fk = $("#contract_id_fk").val();
    		if($.trim(contract_id_fk) != ''){        			
    			
         	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
         	projectId = workId.substring(0, 3);    
    			//workId = workId.substring(3, work_id.length);
    			$("#project_id_fk").val(projectId);
    			$("#project_id_fk").select2();
    		}
    		
    		if ($.trim(projectId) != "") {
    			$("#work_id_fk option:not(:first)").remove();
             var myParams = { project_id_fk: projectId };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getWorkListForDataGatheringForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                             var workName = '';
                             if ($.trim(val.work_name) != '') { workName = '  - ' + $.trim(val.work_name) }
                             if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                 $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                             } else {
                                 $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                             }
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 }
             });
             $('.searchable').select2();
         }
    		
     }
     function addDataGatherigForm(){
     	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	  			document.getElementById("dataGatherigForm").submit();	
     	}
     }
     
     function updateDataGatherigForm(){
      	if(validator.form()){ // validation perform
 	        	$(".page-loader").show();	    		
 	  			document.getElementById("dataGatherigForm").submit();	
      	}
      }
     
     var validator =	$('#dataGatherigForm').validate({
		 errorClass: "my-error-class",
		 validClass: "my-valid-class",
		 ignore: ":hidden:not(.validate-dropdown)",
  		    rules: {
  		 		  "project_priority_fk": {
  			 		required: false
  			 	  },"work_id_fk": {
  			 		required: true
  			 	  },"project_id_fk": {
  			 		required: true
  			 	  },"contract_id_fk": {
  			 		required: true
  			 	  },"status_fk": {
  		 		    required: false
  			 	  }
  		 	},
  		    messages: {
  		 		 "project_priority_fk": {
  				 	required: 'This field is required',
  			 	  },"work_id_fk": {
  			 		required: ' This field is required'
  			 	  },"project_id_fk": {
  			 		required: ' This field is required'
  			 	  },"contract_id_fk": {
  			 		required: ' This field is required'
  			 	  },"status_fk": {
  		 			required: ' This field is required'
  		 	  	  }
	   		},
	   		errorPlacement:function(error, element){
	   		 	if (element.attr("id") == "project_priority_fk" ){
					 document.getElementById("project_priority_fkError").innerHTML="";
			 		 error.appendTo('#project_priority_fkError');
				}else if(element.attr("id") == "work_id_fk" ){
				   document.getElementById("work_id_fkError").innerHTML="";
			 	   error.appendTo('#work_id_fkError');
				}else if(element.attr("id") == "project_id_fk" ){
					   document.getElementById("project_id_fkError").innerHTML="";
				 	   error.appendTo('#project_id_fkError');
				}else if(element.attr("id") == "status_fk" ){
					document.getElementById("status_fkError").innerHTML="";
				 	error.appendTo('#status_fkError');
				}else if(element.attr("id") == "contract_id_fk" ){
					   document.getElementById("contract_id_fkError").innerHTML="";
				 	   error.appendTo('#contract_id_fkError');
				}else{
 					error.insertAfter(element);
		        } 
	   		},invalidHandler: function (form, validator) {
                var errors = validator.numberOfInvalids();
                if (errors) {
                    var position = validator.errorList[0].element;
                    jQuery('html, body').animate({
                        scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                    }, 1000);
                }
            },submitHandler:function(form){
		    	form.submit();
		    }
		});   
	 
	 $.validator.addMethod("dateFormat",
        	    function(value, element) {
        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
        	    	//return dtRegex.test(value);
        	    },
        	    //"Date format (Aug 02,2020)"
        	    "Date format (DD-MM-YYYY)"
        	);
            
            
            $('select').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	});
            
            $('input').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	});
 </script>


</body>
</html>