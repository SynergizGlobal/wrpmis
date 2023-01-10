<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update New WorkModuleUserAccess</c:if>
		 <c:if test="${action eq 'add'}"> Add New WorkModuleUserAccess</c:if>
    </title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<!-- <link rel="stylesheet" href="/pmis/resources/css/fob.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a {
            color: blue
        }
    	     
        #WorkModuleUserAccessTable2 td.input-field .prefix,
        #WorkModuleUserAccessTable1 td.input-field .prefix {
            top: 1.5rem;
        }
          .input-field .searchable_label{
            font-size: .85rem;
            text-align: left;
        }
        .fixed-width {
            width: 100%;
            margin-left:auto !important;
            margin-right:auto !important;
        }
	.mdl-data-table td, .mdl-data-table th{padding: 0 5px 12px;}
	@media(max-width: 820px){
			.mdl-data-table td, .mdl-data-table th{
				padding: 0 35px 12px;
			}
		}
		@media(max-width: 575px){
			.mdl-data-table td, .mdl-data-table th{
				padding: 0 18px 12px;
			}
		}
	@media only screen and (max-width: 820px)
	.mobile_responsible_table>tbody tr td.mobile_btn_close a {
	    float: right;
	    margin-right: -25px;

  		.filevalue {
            display: block;
            margin-top: 10px;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .select2-container--default .select2-selection--single {
    		background-color: transparent;
    	}
    	.input-field .prefix{
			top:25%;
		}
		@media only screen and (min-width:820px){
			.mdl-data-table td.mobile_btn_close{
				padding-right:inherit;
			}
		}
		
        @media only screen and (max-width: 600px) {
            .input-field .prefix~input {
                min-width: 80px;
            }
        }
        @media only screen and (max-width: 820px) {
             td.cell-disp-inb div.file-path-wrapper {
			    visibility: hidden;
			    width: 2px;
			    margin-bottom: 0;
			}
        }       

        .table-inside td span {
            text-align: center;
            display: block;
        }
           .primary-text {
            color: #118AB2;
        }
            
		.error-msg label{color:red!important;}
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
		
		.my-error-class{
		color:red!important; 
		}
				
		.input-field>label:not(.label-icon).active {
		    -webkit-transform: translateY(-24px) scale(0.8);
		    transform: translateY(-24px) scale(0.8);
		}
		
		
		@media only screen and (min-width: 820px)  {
			#WorkModuleUserAccessTableBody tr td .select2-container{
	        	width:140px !important;
	        	max-width:140px;
	        }          
		}
		
		td.input-field .select2-container--default{
			display:inline-block;
		}
		.mdl-data-table td:not(.mobile_btn_add) {
			vertical-align: baseline;
			padding: 0 1px 12px;
		}
		label.my-error-class{
			transform: none;
		    position: relative;
		    font-size: .85rem;
		    word-break: break-all;
		    color: red !important;
		}
		
		.my-error-class
		{
		color: red !important;
		}
		
		
	select {
    display: block !important;
}

		.modal{
			max-height:90%;
			top:5% !important;
		}
		
				.modal-content .mdl-data-table thead tr:hover{
					background-color:#007a7a;
		}
		
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 30%;
  width: 50%; /* Full width */
  height: 50%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  /*background-color: rgba(0,0,0,0.4);*/ /* Black w/ opacity */
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 40%; /* Could be more or less, depending on screen size */
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
                                 <h6>Work Module User Access </h6>
                            </div>
                        </span>
                    </div>
		                
		           <form action="<%=request.getContextPath() %>/add-new-workmoduleuseraccess" id="WorkModuleUserAccessForm" name="WorkModuleUserAccessForm" method="post"   enctype="multipart/form-data">
                        
                    <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="getWorksList(this.value);" style="display:block;">
                                        <option value="" disabled>Select</option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="WorksListForWorkWiseUserAccess();" style="display:block;">
                                        <option value="" disabled>Select</option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk} - ${obj.work_short_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>                                
                            </div>

                             </div>
                    
						<div class="row fixed-width">
							<h5 class="center-align">Module Access</h5>
							<div class="table-inside">
					 
								<table id="WorkModuleUserAccessFormTable" class="mdl-data-table mobile_responsible_table">
									<thead>
										<tr>
											<th>Work</th>
											<th>Execution</th>
                                          	<c:forEach var="obj" items="${modulesList }">
                                      	   		<th>${obj.module_name_fk}</th>
                                         	</c:forEach>											
										</tr>
									
									</thead>
 									<tbody id="WorkModuleUserAccessTableBody">
                                        <%--<c:forEach var="obj" items="${worksList }">
                                    	   		<tr>
                                    	   			<td style="width:30%">${obj.work_id_fk} - ${obj.work_short_name}</td>
	                                            	<c:forEach var="obj1" items="${modulesList }">
	                                      	   			<td style="width:12%">
								                            <select class="searchable validate-dropdown" id="${obj.work_id_fk}_${obj1.module_name_fk}" name="executive_user_id_fk" multiple="multiple" >
								                                <option >Select</option>			
										                                    <c:forEach var="obj2" items="${usersDetails}">
																				<option value="${obj2.user_id }" 
																					<c:forEach var="tempobj" items="${contractObj.responsiblePeopleLists}">
																			 			<c:if test="${tempobj.responsible_people_id_fk eq obj2.user_id }">selected</c:if>
										                                          	</c:forEach>>${obj2.designation }-${obj2.user_name}</option>
																			</c:forEach>										 			 		                             	
								                            </select>                                      	   			
	                                      	   			
	                                      	   			</td>
	                                         		</c:forEach>                                  	   		
                                    	   		</tr>
                                       	</c:forEach>--%>
                                       	
                                       		
									</tbody> 
								</table>
								<p id="fyError" style="color:red;"></p>
							</div>
						</div>
					<div class="container container-no-margin">                           

                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                   <div class="center-align m-1">
						                       <button type="button" onclick="addWorkModuleUserAccess();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add / Update Access</button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/work-module-user-access" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        
                    </div>
                    <!-- form ends  -->
                    </form>
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
	
  <div id="Modalremove" class="container container-no-margin" style="width:100%;display:none;">
       <div>
           <h5 class="modal-header"> Add Access 
	           <span class="right modal-action modal-close" onClick="rmvModal();">
	           <span class="material-icons">close</span></span>
           </h5>
               <div class="row no-mar" id="amendment_not_required_in_contract_Div" style="display: block;">
                   <div class="col s12 m12 input-field" style="text-align:center;">
	                   <table id="tblExecutionUserAccess">
	                   		<thead>
	                   			<tr>
									<th>Contract Short Name</th>
									<th>P6 Updates</th>
									<th>Activity Update Form</th>
									<th>Validation Form</th>
									<th>Structure Type</th>
								</tr>
	                   		</thead>
	                   		<tbody>
	                   		
	                   		<tr>
	                   			<td style="width:20%">
										<select id="contract_id_fk0" name="contract_id_fk" onchange="getStructureTypesListFilter(0);addContracts(0);" class="searchable" >
											<option value="">Select</option>
										</select>
								</td>
								<td style="width:20%">
									<select id="p6_updates_users0" name="p6_updates_users"  class="searchable" multiple>
									<option value="">Select</option>
	                                    <c:forEach var="obj" items="${usersDetails}" >
	                                  		  <option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
	                                  	</c:forEach>
	                                 </select>								
								</td>
								<td style="width:20%">
									<select id="activity_updateform_users0" name="activity_updateform_users"  class="searchable" multiple>
									<option value="">Select</option>
	                                    <c:forEach var="obj" items="${usersDetails}" >
	                                  		  <option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
	                                  	</c:forEach>
	                                 </select>								
								</td>
								<td style="width:20%">
									<select id="validation_form_users0" name="validation_form_users"  class="searchable" multiple>
									<option value="">Select</option>
	                                    <c:forEach var="obj" items="${usersDetails}" >
	                                  		  <option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
	                                  	</c:forEach>
	                                 </select>									
								</td>
								<td style="width:20%">	
	                                        <select id="structure_type_fk0" name="structure_type_fk" class="searchable" class="searchable"  onChange="getAllSelected(this);" multiple>
	                                            <option value="">Select All</option>
	                                        </select>
								</td>	                   		
	                   		</tr>
	                   		<input type="hidden" id="structureContractRowNo"  name="structureContractRowNo" value="0" />
	                   		
	                   		</tbody>
	                   </table>
                  
		            	<div id="errorUserAccess" style="color:red;"></div>    
		            	<div style="text-align:center;">                 
							<a class="btn waves-effect waves-light bg-m t-c"  onclick="addUserAccessContractRow()"> <i class="fa fa-plus"></i></a>                   
                   		</div>
                   		<br>
                   		<br>	
                   		<div> 		            	               
							<button type="button" name="remove" id="remove" class="btn btn-primary t-c" onClick="addUserAccess();">Update</button>
							<button type="button" name="cancel" id="cancel" class="btn waves-effect waves-light bg-s t-c" onClick="rmvModal();">Cancel</button>
						</div>
						<br><br><br><br>
                   </div>
               </div>        
       </div>
   </div>	
	 
    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

 
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
         $('.modal').modal();
         $('.collapsible').collapsible();
         $("#project_id_fk").val('P04').trigger('change');
        getSelectedWorkUser();
    	getContractsFilterList(0);
   		getStructureTypesListFilter(0);

     });
     
     var workidsArray=new Array();
     var modulesArray=new Array();
     var workidmodulesArray=new Array();
     
     function getContractsFilterList(rowNo){
 	 	$(".page-loader").show();
 		var work_id_fk = $("#work_id_fk").val();
 	 	var work_status_fk = $("#work_status_fk").val();
 	 	var contract_id_fk = $("#contract_id_fk").val();
 	 	var structure_type_fk = $("#structure_type_fk").val();
 	 	
 	    if ($.trim(contract_id_fk) == "") {
 	    	$("#contract_id_fk option:not(:first)").remove();
 	    	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,work_status_fk : work_status_fk,structure_type_fk : structure_type_fk};
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInStructure",
                 data: myParams, cache: false,async: false,
                 success: function (data) {
                 	if(data != null && data != '' && data.length > 0){  
                         $.each(data, function (i, val) {
                         	 var contract_short_name = '';
                              if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
 	                         $("#contract_id_fk"+rowNo).append('<option value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + contract_short_name + '</option>');
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 },error: function (jqXHR, exception) {
  	   			      $(".page-loader").hide();
 	   	          	  getErrorMessage(jqXHR, exception);
 	   	     	  }
             });
         }else{
         	  $(".page-loader").hide();
         }
 	 }
     
     
		function rmvModal()
		{
			$("#Modalremove").modal('close');
		} 
		
		function getAllSelected(t)
		{
	
			if($(t).val()=="")
				{
	   				$(t).select2('destroy').find('option').prop('selected', 'selected').end().select2();
				}
		}
     
     function addUserAccessContractRow(){
       	 var rowNo = $("#structureContractRowNo").val();
    		 var rNo = Number(rowNo)+1;
    		 var no = 0;
    		 
    				 
    		 var html = '<tr id="departmentRow'+rNo+'">'
    			   +'<td data-head="Department" class="input-field">'+
    		         '<select  class="searchable validate-dropdown" name="contracts_id_fk" id="contract_id_fk'+rNo+'" onchange="getStructureTypesListFilter('+rNo+');addContracts('+rNo+');"> '
             		 +'<option value="">Select</option>'+
             '</select>'+
             '</td>'
    			   +'<td data-head="Select Executives" class="input-field h-auto">'+
              '<select  class="searchable validate-dropdown"  name="p6_updates_users" id="p6_updates_users'+rNo+'"  multiple="multiple">'+
              '<option value="" disabled="disabled">Select</option>'+
              <c:forEach var="obj" items="${usersDetails}">
    		  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
              </c:forEach> 
            '</select><td data-head="Select Executives" class="input-field h-auto">'+
    	    	              '<select  class="searchable validate-dropdown"  name="activity_updateform_users" id="activity_updateform_users'+rNo+'"   multiple="multiple">'+
    	    	              '<option value="" disabled="disabled">Select</option>'+
    	    	              <c:forEach var="obj" items="${usersDetails}">
    	    	    		  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
    	    	              </c:forEach> 
    	    	            '</select>'+
	    			   		'</td>'
	    	    			   +'<td data-head="Select Executives" class="input-field h-auto">'+
	    	    	              '<select  class="searchable validate-dropdown"  name="validation_form_users" id="validation_form_users'+rNo+'"   multiple="multiple">'+
	    	    	              '<option value="" disabled="disabled">Select</option>'+
	    	    	              <c:forEach var="obj" items="${usersDetails}">
	    	    	    		  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
	    	    	              </c:forEach> 
	    	    	            '</select>'+
    	    			   		'</td>'
    	    	    			   +'<td data-head="Select Executives" class="input-field h-auto">'+
    	    	    	              '<select  class="searchable validate-dropdown"  name="structure_type_fk" multiple onChange="getAllSelected(this);" id="structure_type_fk'+rNo+'">'+
    	    	    	              '<option value="">Select All</option>'+
    	    	    	            '</select>'+
    	    	    	    			   		'</td>'     	    	    	    			   		
    	    	    	    			   		
    			   +'<td class="mobile_btn_close"> <a onclick="removeStructureContractResponsible('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    			   +'</tr>';
    		
    			 $('#tblExecutionUserAccess tbody').append(html); 
    			 
    		    	getContractsFilterList(rNo);
    		   		getStructureTypesListFilter(rNo);
    		   		
    			 $("#structureContractRowNo").val(rNo);
    			  $('.searchable:not(.units)').select2();
    			 

       } 
     
     
     function removeStructureContractResponsible(rowNo){
     	$("#departmentRow"+rowNo).remove();
     }       
     
     function addUserAccess()
     {
    	 	var accLength=$("#tblExecutionUserAccess tbody tr").length;
     
			for(var i1=0;i1<accLength;i1++)
			{
					if($("#contract_id_fk"+i1).val()=="")
					{
						$("#errorUserAccess").html("Contract Short Name	 is mandotory in row "+(i1+1));
						return false;
					}
					else
					{
						$("#errorUserAccess").html("");
					}
					
					if($("#p6_updates_users"+i1).val()=="" && $("#activity_updateform_users"+i1).val()=="" && $("#validation_form_users"+i1).val()=="")
					{
						$("#errorUserAccess").html("Please select Executives in P6 Updates	/ Activity Update Form	/ Validation Form in row  "+(i1+1));
						return false;
					}
					else
					{
						$("#errorUserAccess").html("");
					}
						
			}  
			
			var concatText="";
			
			for(var i1=0;i1<accLength;i1++)
			{
					
			
			   		var p6value="No";
					if($("#p6_updates_users"+i1).val()!="")
					{
						p6value=$("#p6_updates_users"+i1).val();
					}
				
			   		var auusers="No";
					if($("#activity_updateform_users"+i1).val()!="")
					{
						auusers=$("#activity_updateform_users"+i1).val();
					}
					
			   		var vfusers="No";
					if($("#validation_form_users"+i1).val()!="")
					{
						vfusers=$("#validation_form_users"+i1).val();
					}
					
			   		var stype="No";
					if($("#structure_type_fk"+i1).val()!="")
					{
						stype=$("#structure_type_fk"+i1).val();
					}					
					
					if($("#contract_id_fk"+i1).val()!="" && $("#contract_id_fk"+i1).val()!=undefined)
					{
						var workmoduleValue=$("#contract_id_fk"+i1).val()+'__'+p6value+'__'+auusers+'__'+vfusers+'__'+stype;
						concatText=concatText+''+workmoduleValue+'###';
					}

			}				

	   	    var myParams3 = { work_id_fk: $("#work_id_fk").val(),module_name_fk:concatText};
   	    	$.ajax({
  	             url: "<%=request.getContextPath()%>/ajax/addUserAccessforExecutionContracts",
  	             data: myParams3, cache: false,
  	             success: function (data1) 
  	             {
  	 				alert("Updated successfully");
  	 				window.location.reload();    	             
  	             }
  	         });  	
     }
     
     var contractArray=new Array();
     function addContracts(rowNo)
     {
 	 		var accLength=$("#tblExecutionUserAccess tbody tr").length;
     
    	     for(var i=0;i<accLength;i++)
    		 {
		    	 if(contractArray.indexOf($("#contract_id_fk"+i).val())==-1)
		    	 {
		    	 	contractArray.push($("#contract_id_fk"+i).val());
		    	 }

    		 }

/*     	     if(rowNo!=0)
    	    	 {
		   	     	 if(contractArray.indexOf($("#contract_id_fk"+rowNo).val())!=-1)
		   	    	 {
						$("#errorUserAccess").html("Contract already exist");
						$("#contract_id_fk"+rowNo).val("");
						return false;	   	    	 
		   	    	 }
		   	     	 else
		     		 {
		     			$("#errorUserAccess").html("");
		     		 }
    	    	 } */
    	    	
     }
     
     
     function getStructureTypesListFilter(rowNo) {
    		var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk"+rowNo).val();
        	var structure_type_fk = $("#structure_type_fk"+rowNo).val();
        	
        	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
   	       
      	$(".page-loader").show();

         if ($.trim(structure_type_fk) == "") {
             $("#structure_type_fk"+rowNo+" option:not(:first)").remove();
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getStructureTypeListForFilter",
                 data: myParams, cache: false,async: false,
                 success: function (data) {
                     if (data.length > 0) {
                    
                         $.each(data, function (i, val) {
                         	$("#structure_type_fk"+rowNo).append('<option value="' + val.structure_type + '">' + $.trim(val.structure_type) +'</option>');
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 },error: function (jqXHR, exception) {
  	   			  $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
             });
         }else{
         	  $(".page-loader").hide();
         }
     }   
     function getSelectedWorkUser()
     {
    	     var trRowsLength=$('#WorkModuleUserAccessTableBody > tr').length;
    	     var tdColumnsLength=$('#WorkModuleUserAccessTableBody > tr:eq(0) > td').length;
    	     
    	     var myParams2 = { project_id_fk: document.getElementById("project_id_fk").value,work_id_fk: document.getElementById("work_id_fk").value };
   		 
 	    	$.ajax({
 	             url: "<%=request.getContextPath()%>/ajax/getWorkModuleWiseUsers",
 	            data: myParams2,cache: false,
 	             success: function (data1) {
 	                 if (data1.length > 0) 
 	                 {
		 	           		$.each(data1, function (i1, val1) {
		 	           		var SRM=val1.module_name_fk.split(" ").join("");
		 	           		
		 	           		SRM=SRM.replace(/&/g, "and");

		 	       		 				if(workidsArray.indexOf(val1.work_id_fk)==-1)
		 	       		 				{
		 	       		 					workidsArray.push(val1.work_id_fk);
		 	       		 				}
		 	       		 				
		 	       		 				if(modulesArray.indexOf(SRM)==-1)
		 	       		 				{
		 	       		 					modulesArray.push(SRM);
		 	       		 				} 
		 	       		 				
		 	       		 				workidmodulesArray.push(val1.work_id_fk+'@@@'+SRM+'@@@'+val1.user_id);
		 	           				
		 	           		
		 	           		});
		 	           			for(var i=0;i<workidsArray.length;i++)
		 	           			{
		 	           			
			 	           			for(var i1=0;i1<modulesArray.length;i1++)
			 	           			{
			 	           			    var mopval=workidsArray[i]+'_'+modulesArray[i1];
			 	           			   
			 	           					for(var i2=0;i2<workidmodulesArray.length;i2++)
			 	           			    	{
			 	           			           var splitStr=workidmodulesArray[i2];
			 	           			           var splitStr1=splitStr.toString();
			 	           			       	   var splitStr2=splitStr1.split('@@@');
			 	           			       	   	   if(workidsArray[i]==splitStr2[0] && modulesArray[i1]==splitStr2[1])
			 	           			       		   {
			 	           			       		   		var URT=splitStr2[2].split(',');
			 	           			       	    		$("#"+mopval).val(URT).trigger('change');
			 	           			       		   }
			 	           			    	}
			 	           			}		 	           			
		 	           			}
		 	       		 		
 	                 }
 	             }
 	         });     	     
     

     }
     
     
   
     
     
     function getWorksList(projectId) {
     	$(".page-loader").show();
         $("#work_id_fk option:not(:first)").remove();
         $("#contract_id_fk option:not(:first)").remove();
         if ($.trim(projectId) != "") {
             var myParams = { project_id_fk: projectId };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getWorkListForDesignForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                             var workName = '';
                             if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                             var workId = "${designDetails.work_id_fk}";
                             if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                 $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                             } else {
                                 $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                             }
                         });
                         $("#work_id_fk").val('P04W01').trigger('change');
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide();
                 }
             });
         }else{
         	$(".page-loader").hide();
         }
     } 
     
     function openPopup()
     {
    	 //$("#Modalremove").modal('open');
     
    	 $("#Modalremove").show();
    	 getSelectedExecutionContracts();
     }
     
     function getSelectedExecutionContracts()
     {
	     var myParams2 = { project_id_fk: document.getElementById("project_id_fk").value,work_id_fk: document.getElementById("work_id_fk").value };
   		 
	    	$.ajax({
	             url: "<%=request.getContextPath()%>/ajax/getSelectedExecutionContracts",
	            data: myParams2,cache: false,
	             success: function (data1) {
	                 if (data1.length > 0) 
	                 {
		 	           		$.each(data1, function (i1, val1) {
		 	           			
		 	           			    if(i1>0)
		 	           				{
		 	           			    	addUserAccessContractRow();
		 	           				}
		 	           			
 	           						$("#contract_id_fk"+i1).val(val1.contract_id_fk).trigger('change');
 	           						if(val1.structure_type_fk!="")
           							{
           								$("#structure_type_fk"+i1).val(val1.structure_type_fk).trigger('change');
           							}
 	           						
	 	           					var users=val1.form1_users;
	 	           					if(users!="")
 	           						{
	           			       		   	var URT=users.split(','); 	  
		 	           					$("#p6_updates_users"+i1).val(URT).trigger('change');	
 	           						}

	 	           					var users1=val1.form2_users;
	 	           					if(users1!="")
 	           						{	 	           					
	           			       		   	var URT1=users1.split(','); 	  
		 	           					$("#activity_updateform_users"+i1).val(URT1).trigger('change');	
 	           						}

	 	           					var users2=val1.form3_users;
	 	           					if(users2!="")
 	           						{
	           			       		   	var URT2=users2.split(','); 	  
		 	           					$("#validation_form_users"+i1).val(URT2).trigger('change');	
 	           						}
	 	           				
		 	           		});
		 	       		 		
	                 }
	             }
	         });    	 
     }
     
     function WorksListForWorkWiseUserAccess()
     {
     
    	 $("#WorkModuleUserAccessFormTable tbody tr").remove();

                
             		var html="<tr>";
  		 			html=html+'<td style="width:10%">'+$("#work_id_fk option:selected").text()+'</td><td style="width:10%"><button type="button" name="btnAdd" id="btnAdd" class="btn waves-effect waves-light bg-m" onClick="openPopup();">Add</button></td>';
  		 				for(var i1=0;i1<7;i1++)
  		 				{
 							var SRM=document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
 							SRM = SRM.replace(/&amp;/g, "and");
 							
 							if(document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i1+1].innerHTML=="Contracts")
 								{
  		 						html=html+'<td style="width:10%">'+
  		 						'<select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_'+SRM+'" name="executive_user_id_fk" multiple="multiple" >'+
  		 						'<option >Select</option>'+
                                    <c:forEach var="obj" items="${usersDetails}" >
                                  		  +'<option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>'
                                  	</c:forEach>											 			 		                             	
                                  	+'</select></td>';  
 								}
 							else
 								{
  		 							html=html+'<td style="width:10%">'+
	  		 						'<select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_'+SRM+'" name="executive_user_id_fk" multiple="multiple" >'+
	  		 						'<option >Select</option>'+
	                                    <c:forEach var="obj" items="${moduleUsersDetails}" >
	                                  		  +'<option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>'
	                                  	</c:forEach>											 			 		                             	
	                                  	+'</select></td>';  								
 								}
  		 				}
  		 				html=html+'</tr>';
         			$("#WorkModuleUserAccessFormTable tbody").append(html);  

					for(var i1=0;i1<7;i1++)
					{
						var SRM=document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
						SRM = SRM.replace(/&amp;/g, "and");
					
							var mopval=$("#work_id_fk").val()+'_'+SRM;
							$("#"+mopval).select2();
						
		   			}
		  		  
		      		getSelectedWorkUser();

		   
     }
     
     function addWorkModuleUserAccess()
     {
    	 var allModulesArray=new Array();
			for(var i1=0;i1<7;i1++)
			{
				
				var SRM=document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
				SRM = SRM.replace(/&amp;/g, "and");				
			
				
	 					if(allModulesArray.indexOf(SRM)==-1)
		 				{
	 						allModulesArray.push(SRM);
		 				}
   			}  	 
    	 
     
    	 			var concatText="";
     

    			
        			for(var i1=0;i1<allModulesArray.length;i1++)
        			{
        			   

								var workmoduleValue=$("#work_id_fk").val()+'_'+allModulesArray[i1];
								if($("#"+workmoduleValue).val()!=null && $("#"+workmoduleValue).val()!="" && $("#"+workmoduleValue).val()!=undefined)
									{
  			       	   					concatText=concatText+allModulesArray[i1]+"___"+$("#"+workmoduleValue).val()+'###';
									}

        			       		   
        			    	
        			}	
        			
 				
   	   			var myParams3 = { work_id_fk: $("#work_id_fk").val(),module_name_fk:concatText};
	       		   
     	    	$.ajax({
     	             url: "<%=request.getContextPath()%>/ajax/addWorkModuleUserAccess",
     	             data: myParams3, cache: false,
     	             success: function (data1) 
     	             {
     	 				alert("Updated successfully");
     	 				window.location.reload();    	             
     	             }
     	         }); 				

     }
     
     

     
     function removeErrorMsg(){
		 $('#bank_name_text1Error').text('');
		 $('#bttnUpdate').prop('disabled', false);
		 updateFlag = true;
		}
 
     
    </script>

</body>
    <style>
	select {
    	display: block !important;
	}
</style>
<script>
$('.searchable').select2();
</script>
</html>
