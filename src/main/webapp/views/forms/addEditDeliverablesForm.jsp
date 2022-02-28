<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		  <c:if test="${action eq 'edit'}">Update Deliverable - Update Forms - PMIS</c:if>
		  <c:if test="${action eq 'add'}">Add Deliverable - Update Forms - PMIS</c:if>
	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
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
		.col.input-field>textarea.materialize-textarea{
		   margin-bottom: 2px;
		}
		.file-field input[type=file]{
			box-shadow:none;
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                               		 <c:if test="${action eq 'edit'}">Update Deliverable </c:if>
		  							 <c:if test="${action eq 'add'}"> Add Deliverable </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-deliverables" id="deliverablesForm" name="deliverablesForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                 </c:if>
				     <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-deliverables" id="deliverablesForm" name="deliverablesForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
				     </c:if>
                     <div class="container container-no-margin">
                        <input type="hidden" name="id" id="id"  value="${deliverablesDetails.id }"/>
                        <c:if test="${action eq 'add'}">
	                         <div class="row">
	                                <div class="col s6 m4 l4 input-field">
	                                    <p class="searchable_label"> Project <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
	                               	  		 onchange="getWorksList(this.value);">
	                                    		 <option value="" >Select</option>
	                                      		 <c:forEach var="obj" items="${projectsList }">
	                                         			 <option value="${obj.project_id_fk }" <c:if test="${deliverablesDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
	                                      		 </c:forEach>
			                             </select>
	                               		 <span id="project_idError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 l4 input-field">
	                                    <p class="searchable_label"> Work <span class="required">*</span></p>
	                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
	                                   		  onchange="getContractsList(this.value);">
	                                   		  <option value="">Select</option>
	                                   		  <c:forEach var="obj" items="${worksList }">
                                      	   		<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                               </c:forEach>
		                                  </select>
	                                   		   
	                                  <span id="work_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <c:if test="${action eq 'add'}">	
	                                 <div class="col s12 m4 l4 input-field">
	                                    <p class="searchable_label">Contract <span class="required">*</span></p>
	                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
	                                       	<option value="">Select</option>
	                                       	 <c:forEach var="obj" items="${contractsList }">
                                      	  			 <option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                        	 </c:forEach>
	                                  	</select>
	                                   	<span id="contract_id_fkError" class="error-msg" ></span>
	                                 </div> 
                              	 </c:if>
	                              </div>
                              </c:if>
                              <c:if test="${action eq 'edit'}">	
	                              <div class="row">                              
	                                <div class="col s6 m4 input-field">
	                                    <input type="text"  id="project_id_fk" value="${deliverablesDetails.project_id_fk}- ${deliverablesDetails.project_name}" readonly />
	                                    <input type="hidden" name="project_id_fk"  value="${deliverablesDetails.project_id_fk}"  />
										<label for="project_id_fk">Project <span class="required">*</span></label>
								    </div> 
	                                <div class="col s6 m4 input-field"> 
	                                   <input type="text"  id="work_id_fk" value="${deliverablesDetails.work_id_fk}- ${deliverablesDetails.work_name}" readonly />
	                                     <input type="hidden" name="work_id_fk"  value="${deliverablesDetails.work_id_fk}"  />
									    <label for="work_id_fk">Work <span class="required">*</span></label>
	                                </div>
	                                <div class="col s12 m4 input-field"> 
	                              	    <input type="text"  id="contract_id_fk" value="${deliverablesDetails.contract_id_fk} - ${deliverablesDetails.contract_name}" readonly />
	                              	     <input type="hidden" name="contract_id_fk"  value="${deliverablesDetails.contract_id_fk}"  />
	                                 	<label for="contract_id_fk">Contract <span class="required">*</span></label>     
                              	    </div>
	                             </div>
                              </c:if>
                              <%-- <div class="row">
                                <c:if test="${action eq 'edit'}">	
                                 	
                                 </c:if>
                             	 
                                 
                            </div> --%>
                            <div class="row">
                            <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Deliverable Type <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" name="deliverable_type_fk" id="deliverable_type_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${deliverablesTypeList }">
		                                     <option value="${obj.deliverable_type_fk }" <c:if test="${deliverablesDetails.deliverable_type_fk eq obj.deliverable_type_fk}">selected</c:if>>${obj.deliverable_type_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="deliverable_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Priority </p>
                                    <select class="searchable validate-dropdown" name="project_priority_fk" id="project_priority_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${priorityList }">
		                                     <option value="${obj.project_priority_fk }" <c:if test="${deliverablesDetails.project_priority_fk eq obj.project_priority_fk}">selected</c:if>>${obj.project_priority_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="project_priority_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Status </p>
                                    <select class="searchable validate-dropdown" name="status_fk" id="status_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${statusList }">
		                                     <option value="${obj.status_fk }" <c:if test="${deliverablesDetails.status_fk eq obj.status_fk}">selected</c:if>>${obj.status_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <textarea id="deliverable_description" name="deliverable_description" class="pmis-textarea"
                                        data-length="1000">${deliverablesDetails.deliverable_description }</textarea>
                                    <label for="deliverable_description">Deliverable Description</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="target_date" name="target_date" type="text" class="validate datepicker" value="${deliverablesDetails.target_date }">
                                    <label for="target_date">Target Date</label>
                                    <button type="button" id="target_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="start_date" name="start_date" type="text" class="validate datepicker" value="${deliverablesDetails.start_date }">
                                    <label for="start_date">Start Date</label>
                                    <button type="button" id="start_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <input id="finish_date" name="finish_date" type="text" class="validate datepicker" value="${deliverablesDetails.finish_date }">
                                    <label for="finish_date">Finish Date</label>
                                    <button type="button" id="finish_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                
                            </div>
						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col m12 s12">
							
							 <c:if test="${action eq 'add'}">
			                            <div id="selectedFilesInput">
			                                    	<div class="file-field input-field" id="deliverableFilesDiv1" >
				                                        <div class="btn bg-m t-c">
				                                            <span>Attach Files</span>
				                                            <input type="file" id="deliverableFiles1" name="deliverableFiles"   onchange="selectFile('1')">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>                                       
				                                    </div>
												</div>
			                                    <div id="selectedFiles">
												</div>
									  </c:if>	
									  <c:if test="${action eq 'edit'}">
													<c:set var="existingDeliverableFilesLength" value="${fn:length(deliverablesDetails.deliverableFilesList )}"></c:set>
													<c:if test="${fn:length(deliverablesDetails.deliverableFilesList ) gt 0}">
														<c:set var="existingDeliverableFilesLength" value="${fn:length(deliverablesDetails.deliverableFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="deliverableFilesDiv${existingDeliverableFilesLength }" >
					                                        <div class="btn bg-m t-c">
					                                            <span>Attach Files</span>
					                                            <input type="file" id="deliverableFiles${existingDeliverableFilesLength }" name="deliverableFiles"  onchange="selectFile('${existingDeliverableFilesLength }')">
					                                        </div>
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    
				                                    <div id="selectedFiles">
				                                    	<c:forEach var="obj" items="${deliverablesDetails.deliverableFilesList }" varStatus="index">
															 <div id="deliverableFileNames${index.count }">
																<a href="<%=CommonConstants.DELIVERABLES_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
																<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
																<input type="hidden" name="deliverableFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" ></div>
														</c:forEach>
													</div>
				                             </c:if>	
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>
						<div class="row">
                                <div class="col s12 m12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${deliverablesDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m6 mt-brdr">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateDeliverablesFrom();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addDeliverablesFrom();" class="btn waves-effect waves-light bg-m" style="min-width:90px;">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m6 mt-brdr">
                                    <div class="center-align m-1">
                                          <a href="<%=request.getContextPath()%>/deliverables" class="btn waves-effect waves-light bg-s white-text">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
      <script>
   /*    $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
	    function selectFile(no){
		    files = $("#deliverableFiles"+no)[0].files;
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=deliverableFileNames'+no+'>'
				 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
				 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
				 + '</div>'
				 + '<div style="clear:both;"></div>';
		    }
		    $("#selectedFiles").append(html);
		    
		    $('#deliverableFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			moreFiles(fileIndex);
		}
		
		function moreFiles(no){
			var html = "";
			html =  html + '<div class="file-field input-field" id="deliverableFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c">'
			+ '<span>Attach Files</span>'
			+ '<input type="file" id="deliverableFiles'+no+'" name="deliverableFiles"  onchange="selectFile('+no+')">'
			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFile(no){			
	     	$('#deliverableFilesDiv'+no).remove();
	     	$('#deliverableFileNames'+no).remove();
	    } 

	   /*   let date_pickers = document.querySelectorAll('.datepicker');
        $.each(date_pickers, function(){
        	var dt = this.value.split(/[^0-9]/);
        	this.value = ""; 
        	var options = {format: 'dd-mm-yyyy',autoClose:true};
        	if(dt.length > 1){
        		options.setDefaultDate = true,
        		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
        	}
        	M.Datepicker.init(this, options);
        }); */
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           // $("#target_date,#start_date,#finish_date").datepicker();
            $('#remarks').characterCounter();
         /*    $('#target_date_icon').click(function () {
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
            }); */
            var project_id_fk = "${deliverablesDetails.project_id_fk}";
            if($.trim(project_id_fk) != ''){
            	getWorksList(project_id_fk);
            }
            var work_id_fk = "${deliverablesDetails.work_id_fk}";
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDeliverablesForm",
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
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForDeliverablesForm",
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDeliverablesForm",
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
        
        function addDeliverablesFrom(){
        	if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	    		
   	  			document.getElementById("deliverablesForm").submit();	
        	}
        }
        
        function updateDeliverablesFrom(){
         	if(validator.form()){ // validation perform
    	        	$(".page-loader").show();	    		
    	  			document.getElementById("deliverablesForm").submit();	
         	}
         }
        
        
        var validator =	$('#deliverablesForm').validate({	
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"contract_id_fk": {
	  		 		    required: true
	  			 	  },"deliverable_type_fk": {
	  		 		    required: true
	  			 	  },"project_priority_fk": {
	  		 		    required: false
	  			 	  },"status_fk": {
	  		 		    required: false
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"contract_id_fk": {
	  		 			required: ' This field is required'
	  		 	  	  },"deliverable_type_fk": {
	  		 			required: ' This field is required'
	  		 	  	  },"project_priority_fk": {
	  		 			required: ' This field is required'
	  		 	  	  },"status_fk": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_idError").innerHTML="";
				 		 error.appendTo('#project_idError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "contract_id_fk" ){
						document.getElementById("contract_id_fkError").innerHTML="";
					 	error.appendTo('#contract_id_fkError');
					}else if(element.attr("id") == "deliverable_type_fk" ){
						document.getElementById("deliverable_type_fkError").innerHTML="";
					 	error.appendTo('#deliverable_type_fkError');
					}else if(element.attr("id") == "project_priority_fk" ){
						document.getElementById("project_priority_fkError").innerHTML="";
					 	error.appendTo('#project_priority_fkError');
					}else if(element.attr("id") == "status_fk" ){
						document.getElementById("status_fkError").innerHTML="";
					 	error.appendTo('#status_fkError');
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
	            function removeMedia(link,id){
	          	  $('#'+id).val('');
	          	  $(link).prev().text('');
	          	  $(link).css('display','none');
	            } 
    </script>
</body>
</html>