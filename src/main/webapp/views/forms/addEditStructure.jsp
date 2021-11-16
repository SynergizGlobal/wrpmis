<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Structure - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Structure - Update Forms - PMIS</c:if>
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
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" > 	
    <style>

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        #structure_details .select2-container{
        	min-width:300px;
        	max-width:300px;
        }
        #structure_details .select2-container--default .select2-selection--single {
        	background-color:transparent;
        }
         .fixed-width {
            width: 100%;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
         .bd-none{border:none !important;background: transparent}
		@media(max-width: 2200px){
		.table-add{position: absolute; width:52%;}
		.add-align{position: absolute;
   					 margin-top: -5.8em;
   					 margin-left: 26%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:36%;}
    	}
    	@media(max-width: 800px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;width:100%;}
    	}
        @media only screen and (max-width: 769px) {
            #structure_details .select2-container{
                min-width: inherit;
                max-width: inherit;
            }
            .disp-in-table{
            	display:inline-table !important;
            }
            .disp-in-table >.btn{
            	float:left;
            }   
            #structure_details .datepicker~button {
	            top: 0;
	            bottom:0;
	            right:26px;
	        }                    
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
    
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                 	 <c:if test="${action eq 'edit'}">Update Structure </c:if>
									 <c:if test="${action eq 'add'}"> Add Structure </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   		    <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-structure" id="structureForm" name="structureForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                         </c:if>
				             <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-structure" id="structureForm" name="structureForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							 </c:if>
                        <div class="container container-no-margin">
                        <input type="hidden" name= "structure_id" id="structure_id" value="${structuresListDetails.structure_id}" />
                        <c:if test="${action eq 'add'}">
                            <div class="row"> 
                                <div class="col s6 offset-m2 m4 input-field">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                               	  		 onchange="getWorksList(this.value);">
                                    		 <option value="" >Select</option>
                                      		 <c:forEach var="obj" items="${projectsList }">
                                         			 <option value="${obj.project_id_fk }">${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                      		 </c:forEach>
		                             </select>
                               		 <span id="project_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                   		  onchange="getContractsList(this.value);resetProject();">
                                   		  <option value="" >Select</option>
                                   		  	 <c:forEach var="obj" items="${worksList }">
	                                      	   		<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                      	 </c:forEach>
	                                  </select>
                                   		   
                                  <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             </div>
                             </c:if>
                             <c:if test="${action eq 'edit'}">	
                             <div class="row">                              
                                <div class="col s6 m4 input-field offset-m2">
                                    <input type="text"  id="project_id_fk" value="${structuresListDetails.project_id_fk}- ${structuresListDetails.project_name}" readonly />
                                    <input type="hidden" name="project_id_fk"  value="${structuresListDetails.project_id_fk}" />
									<label for="project_id_fk">Project <span class="required">*</span></label>     
							    </div> 
                                <div class="col s6 m4 input-field"> 
                                    <input type="text" id="work_id_fk"  value="${structuresListDetails.work_id_fk}- ${structuresListDetails.work_short_name}" readonly />
								    <input type="hidden" name="work_id_fk"  value="${structuresListDetails.work_id_fk}" />
								    <label for="work_id_fk">Work <span class="required">*</span></label>     
                                </div>
                            </div>
                             </c:if>
                            <div class="row">
                            	 
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Department</span></p>
                                    <select class="searchable validate-dropdown" name="department_fk" id="department_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${departmentsList }">
		                                     <option value="${obj.department_fk }" <c:if test="${structuresListDetails.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_name}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                <c:if test="${action eq 'edit'}">	
                                 <div class="col s6 m4 input-field "> 
                              	    <input type="text" id="contract_id_fk" value="${structuresListDetails.contract_id_fk} - ${structuresListDetails.contract_short_name}" readonly />
                                 	<input type="hidden" name="contract_id_fk"  value="${structuresListDetails.contract_id_fk}" />
                                 	<label for="contract_id_fk">Contract <span class="required">*</span></label>           
                              	    </div>
                                 </c:if>
                                <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Contract</p>
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
                          
                        <div class="row fixed-width" style="margin-bottom: 10px;margin-top:20px;">
                            <!-- <h5 class="center-align">Revision Details</h5> -->
                            <div class=" col s12 m8 offset-m2 table-inside">
                              <!-- <div class=""> -->
                                <table id="structure_details" class="mdl-data-table mobile_responsible_table">
                                    <thead>
                                        <tr>
                                            <th>Structure Type</th>
                                            <th>Structure Id</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="structureTableBody">
                                    <c:choose>
                                      	 <c:when test="${not empty structuresListDetails.structureList && fn:length(structuresListDetails.structureList) gt 0 }">
                                       	 <c:forEach var="dObj" items="${structuresListDetails.structureList }" varStatus="index"> 
                                        <tr id="structureRow${index.count }">
                                            <td data-head="Structure Type" class="input-field" >
                                                <select id="structure_type_fks${index.count }" name="structure_type_fks" class="validate-dropdown searchable">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${structuresList }">
		                                           			 <option value="${obj.structure_type }" <c:if test="${dObj.structure_type_fk eq obj.structure_type}">selected</c:if>>${obj.structure_type}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>
                                           <td data-head="Structure Id" class="input-field">
                                                    <input type="hidden" name= "ids" id="ids" value="${dObj.structure_id}" />
                                                    <input id="structure_id${index.count }" name="structures" type="text" class="validate"  placeholder="Structure Id" value="${dObj.structure }">
                                           </td>                                            
                                            <td class="mobile_btn_close">
                                                <a  onclick="removeStructureRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       <tr id="structureRow0">
                                            <td data-head="Structure Type" class="input-field">
                                                  <select id="structure_type_fks0" name="structure_type_fks" class="validate-dropdown searchable">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${structuresList }">
		                                           			 <option value="${obj.structure_type }">${obj.structure_type}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>                                            
                                           <td data-head="Structure Id" class="input-field"> 
                                                    <input id="structure_id0" name="structures" type="text" class="validate"
                                                        placeholder="Structure Id">
                                            </td>
                                            <td class="mobile_btn_close">
                                                <a  onclick="removeStructureRow('0');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
										
                                    	  </c:otherwise>
                                     	</c:choose>
                                     	 </tbody>
                                    </table>
                                    <table class="mdl-data-table table-add bd-none">
                                        <tbody id="documentBody">                                          
		                                    <tr class="bd-none">
		  										<td colspan="3" class="bd-none"> 
		  											<a type="button" class="btn waves-effect waves-light bg-m t-c add-align" onclick="addStructureRow()"> <i
		                                                            class="fa fa-plus"></i></a>
		                                        </td>
		                                    </tr>
                                        </tbody>
                                    </table>
  									<c:choose>
                                        <c:when test="${not empty (structuresListDetails.structureList) && fn:length(structuresListDetails.structureList) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(structuresListDetails.structureList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                                <!--  </div> -->
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m6 mt-brdr center-align">
                                    <div class="m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateDocument();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addDocument();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
											 </c:if>
                                    </div>
                                </div>
                               <div class="col s6 m6 mt-brdr center-align">
                                    <div class=" m-1">
                                          <a href="<%=request.getContextPath()%>/structure" class="btn waves-effect waves-light bg-s w-text">Cancel</a>
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    
        <script>
     /*    $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
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

            // commented code placed next script tag from here 


            $("#myFile").change(function () {
                filename1 = $('#myFile')[0].value;

                $('#fileVal').html(filename1);
                console.log(filename1)
                console.log($('#myFile')[0].localName)
            });
            
            var project_id_fk = "${structuresListDetails.project_id_fk}";
            if($.trim(project_id_fk) != ''){
            	getWorksList(project_id_fk);
            }
            var work_id_fk = "${structuresListDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });
       
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForStructureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var work_id_fk = "${structuresListDetails.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id_fk == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                        getContractsList("") 
                    }
                });
            }else{
            	
            	$(".page-loader").hide();
            }
        }
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            var project_id_fk = $("#project_id_fk").val();
            if ($.trim(work_id_fk) != "" || $.trim(project_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk, project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractListForStructureFrom",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var contract_id_fk = "${structuresListDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id_fk == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" selected>' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                }
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
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        
        function resetProject(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = $("#work_id_fk").val();
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3);    
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        function addDocument(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	  			$('form input[name=structure_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			document.getElementById("structureForm").submit();	
        	}
        }
        function updateDocument(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=structure_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			document.getElementById("structureForm").submit();	
        	}
        }
     
    function addStructureRow(){
      		
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
          
             var html = '<tr id="structureRow'+rNo+'">'
             			+'<td data-head="Structure Type" class="input-field"><select id="structure_type_fks'+rNo+'" name="structure_type_fks" class="validate-dropdown searchable">'
             			+'<option value="" >Select</option>'
			               <c:forEach var="obj" items="${structuresList }">
			              	+'<option value="${obj.structure_type }">${obj.structure_type}</option>'
			               </c:forEach>
           				+'</select></td>'
           				+'<td data-head="Structure Id" class="input-field">'
               			+'<input id="structure_id'+rNo+'" name="structures" type="text" class="validate" placeholder="Structure Id"> </td>'
               			+'<td class="mobile_btn_close"><a  onclick="removeStructureRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
       					+'</td></tr>';

    				 $('#structureTableBody').append(html);
    				 $("#rowNo").val(rNo);
    				 $('.searchable').select2();   				  
    				  
            } 
         
			function removeStructureRow(rowNo){
				$("#structureRow"+rowNo).remove();
			}

		    var validator =	$('#structureForm').validate({
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
		  			 	  },"department_fk": {
		  		 		    required: true
		  			 	  }
		  		 	},
		  		    messages: {
		  		 		 "project_id_fk": {
		  				 	required: 'This field is required',
		  			 	  },"work_id_fk": {
		  			 		required: ' This field is required'
		  			 	  },"contract_id_fk": {
		  		 			required: ' This field is required'
		  		 	  	  },"department_fk": {
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
						}else if(element.attr("id") == "department_fk" ){
							document.getElementById("department_fkError").innerHTML="";
						 	error.appendTo('#department_fkError');
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
	            $('[name="structure_type_fks"]').on('change', function() {
	    			$("select[name=structure_type_fks]").each(function(){
	    				var idNo = (this.id).replace('structure_type_fks',''); 
	    				if($.trim(this.value) == "" && $('#structure_id'+idNo).val() != ""){ 
	            			$('#structure_id'+idNo+'Error').text('Requried');
	    				}else{
	    					$('#structure_id'+idNo+'Error').text('');
	    				} 
	                });
	    			
	    		});
    </script>
</body>
</html>