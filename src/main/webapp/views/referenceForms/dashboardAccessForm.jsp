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
		Dashboard Access Form
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/DashboardAccessForm.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" > 
    <style>
        p a {
            color: blue
        }
    	     
        #DashboardAccessFormTable2 td.input-field .prefix,
        #DashboardAccessFormTable1 td.input-field .prefix {
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
			#DashboardAccessFormTableBody tr td .select2-container{
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
                                 <h6>Dashboard Access Form </h6>
                            </div>
                        </span>
                    </div>
		                
		           <form action="<%=request.getContextPath() %>/add-new-dashboardaccessform" id="DashboardAccessFormForm" name="DashboardAccessFormForm" method="post"   enctype="multipart/form-data">
                        
                    <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="getWorksList(this.value);" style="display:block;">
                                        <option value="">Select</option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="WorksListForWorkWiseUserAccess();" style="display:block;">
                                        <option value="">Select</option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk} - ${obj.work_short_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>                                
                            </div>

                             </div>
                    
						<div class="row fixed-width">
							<h5 class="center-align">Work Access</h5>
							<div class="table-inside">
					 
								<table id="DashboardAccessFormFormTable" class="mdl-data-table mobile_responsible_table">
									<thead>
										<tr>
											<th>S. No.</th>
											<th style="width:30%">Work Name</th>
											<th>User Role</th>
											<th>User Type</th>
											<th>User</th>
										</tr>
									
									</thead>
									<tbody id="DashboardAccessFormTableBody">
<%-- 									<c:set var="count" value="1" />
									
                                        <c:forEach var="obj" items="${worksList }">
                                    	   		<tr>
                                    	   			<td>${count}</td>
                                    	   			<td>${obj.work_id_fk} - ${obj.work_short_name}</td>
														<td class="input-field">
                                                           <select name="user_role_access" class="searchable" multiple>
                                                               <option value="">Select</option>
                                                               <c:forEach var="obj" items="${user_roles }">
	                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
	                                                        </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td class="input-field">
                                                           <select name="user_type_access" class="searchable" multiple>
                                                               <option value="">Select</option>
	                                                        <c:forEach var="obj" items="${user_types }">
	                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
	                                                        </c:forEach>
                                                           </select>
                                                       </td>
													<td class="input-field">
                                                          <select name="user_access" class="searchable" multiple>
                                                              <option value="" >Select</option>
                                                        <c:forEach var="obj" items="${users }">
                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
                                                        </c:forEach>
                                                          </select>
                                                   </td>	                                      	   				                                      	   			
                                    	   		</tr>
                                    	   	<c:set var="count" value="${count+1}" />  
                                       	</c:forEach> --%>
									</tbody>
								</table>
								<p id="fyError" style="color:red;"></p>
							</div>
						</div>
					<div class="container container-no-margin">                           

                            <div class="row">
                                <div class="col s12 m12 l12 mt-brdr">
                                   <div class="center-align m-1">
						                       <button type="button" onclick="updateWorkAccess();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Update</button>
                                    </div>
                                </div>
                            </div>
                        
                    </div>
						<div class="row fixed-width">
							<h5 class="center-align">Overview Dashboard</h5>
							<div class="table-inside">
					 
								<table id="OverviewDashboardAccessFormFormTable" class="mdl-data-table mobile_responsible_table">
									<thead>
										<tr>
											<th>S. No.</th>
											<th style="width:30%">Parent Dashboard</th>
											<th>User Role</th>
											<th>User Type</th>
											<th>User</th>
										</tr>
									
									</thead>
									<tbody id="OverviewDashboardAccessFormTableBody">
<%-- 									<c:set var="countM" value="1" />
                                        <c:forEach var="obj" items="${modulesList }">
                                    	   		<tr>
                                    	   			<td>${countM}</td>
                                    	   			<td>${obj.module_name_fk}</td>
														<td class="input-field">
                                                           <select name="user_role_access" class="searchable" multiple>
                                                               <option value="">Select</option>
                                                               <c:forEach var="obj" items="${user_roles }">
	                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
	                                                        </c:forEach>
                                                           </select>
                                                       </td>
                                                       <td class="input-field">
                                                           <select name="user_type_access" class="searchable" multiple>
                                                               <option value="">Select</option>
	                                                        <c:forEach var="obj" items="${user_types }">
	                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
	                                                        </c:forEach>
                                                           </select>
                                                       </td>
													<td class="input-field">
                                                          <select name="user_access" class="searchable" multiple>
                                                              <option value="" >Select</option>
                                                        <c:forEach var="obj" items="${users }">
                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
                                                        </c:forEach>
                                                          </select>
                                                   </td>	                                      	   				                                      	   			
                                    	   		</tr>
                                    	   		<c:set var="countM" value="${countM+1}" />  
                                       	</c:forEach> --%>
									</tbody>
								</table>
								<p id="fyError" style="color:red;"></p>
							</div>
						</div>


					<div class="container container-no-margin">                           

                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                   <div class="center-align m-1">
						                       <button type="button" onclick="addDashboardAccessForm();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Update</button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/dashboard-access-form" class="btn waves-effect waves-light bg-s">Cancel</a>
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
         $('.searchable').select2();
         $('.modal').modal( );
         $("#project_id_fk").val('P04').trigger('change');
      
     });
     
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
     
     var workidsArray=new Array();
     var modulesArray=new Array();
     var workidmodulesArray=new Array();
     var uroleArray=new Array();
     var utypeArray=new Array();
     var uArray=new Array();
     
     function getSelectedWorkUser()
     {
    	     var trRowsLength=$('#DashboardAccessFormTableBody > tr').length;
    	     var trOverviewRowsLength=$('#OverviewDashboardAccessFormTableBody > tr').length;
    	     var tdColumnsLength=$('#DashboardAccessFormTableBody > tr:eq(0) > td').length;
    	     
    	     var myParams2 = { project_id_fk: document.getElementById("project_id_fk").value,work_id_fk: document.getElementById("work_id_fk").value };
   		 
 	    	$.ajax({
 	             url: "<%=request.getContextPath()%>/ajax/getDashboardUserAccess",
 	            data: myParams2,cache: false,
 	             success: function (data1) {
 	             
 	                 if (data1.length > 0) 
 	                 {
		 	           		$.each(data1, function (i1, val1) {

		 	           					if(val1.access_type=="user")
		 	           					{
		 	           						var workmoduleValue=$("#work_id_fk").val()+'_User';
		 	           						
		 	           						for(var u=0;u<trOverviewRowsLength;u++)
		 	           							{
					 	           						var OverviewValue=$("#work_id_fk").val()+'_OverviewUser_'+u+'';
					 	           						
			 	           			       		   		var URT=val1.access_value.split(',');
			 	           			       	    		$("#"+workmoduleValue).val(URT).trigger('change');	
			 	           			       	    		$("#"+OverviewValue).val(URT).trigger('change');
			 	           			       	    		
				 	           			       	    	$("#"+OverviewValue+' option').each(function() {
						 	           			       	     if ($(this).is(':selected')) 
						 	           			       	    	 {
						 	           			       	    	 
						 	           			       	    	 }
						 	           			       	     else
						 	           			       	    	 {
						 	           			       	  			uroleArray.push($(this).val());
						 	           			       	    	 }
						 	           			       	 });
			 	           			       	    	
			 	           			       	    		for(var k=0;k<uroleArray.length;k++)
			 	           			       	    		{
			 	           			       	    			$("#"+OverviewValue+" option[value='" + uroleArray[k] + "']").hide();
			 	           			       	    		}
		 	           							}

		 	           					}
		 	           					if(val1.access_type=="user_role")
		 	           					{
		 	           						var workmoduleValue=$("#work_id_fk").val()+'_UserRole';
		 	           						for(var u=0;u<trOverviewRowsLength;u++)
	 	           							{
			 	           						var OverviewValue=$("#work_id_fk").val()+'_OverviewUserRole_'+u+'';
			 	           					
			 	           						var URT=val1.access_value.split(',');
	 	           			       	    		$("#"+workmoduleValue).val(URT).trigger('change');
	 	           			       	    		$("#"+OverviewValue).val(URT).trigger('change');
	 	           			       	    		
	 	 	           			       	    	$("#"+OverviewValue+' option').each(function() {
	 			 	           			       	     if ($(this).is(':selected')) 
	 			 	           			       	    	 {
	 			 	           			       	    	 
	 			 	           			       	    	 }
	 			 	           			       	     else
	 			 	           			       	    	 {
	 			 	           			       	  			utypeArray.push($(this).val());
	 			 	           			       	    	 }
	 			 	           			       	 });
	 	 	           			       	    	
	 	           			       	    		for(var k=0;k<utypeArray.length;k++)
	 	           			       	    		{
	 	           			       	    			$("#"+OverviewValue+" option[value='" + utypeArray[k] + "']").prop("disabled",true);
	 	           			       	    		}
	 	           							}

		 	           					}
		 	           					if(val1.access_type=="user_type")
		 	           					{
		 	           						var workmoduleValue=$("#work_id_fk").val()+'_UserType';
		 	           						for(var u=0;u<trOverviewRowsLength;u++)
	 	           							{
			 	           						var OverviewValue=$("#work_id_fk").val()+'_OverviewUserType_'+u+'';
			 	           					
			 	           						var URT=val1.access_value.split(',');
	 	           			       	    		$("#"+workmoduleValue).val(URT).trigger('change');	
	 	           			       	    		$("#"+OverviewValue).val(URT).trigger('change');
	
	 	 	           			       	    	$("#"+OverviewValue+' option').each(function() {
	 			 	           			       	     if ($(this).is(':selected')) 
	 			 	           			       	    	 {
	 			 	           			       	    	 
	 			 	           			       	    	 }
	 			 	           			       	     else
	 			 	           			       	    	 {
	 			 	           			       	  			uArray.push($(this).val());
	 			 	           			       	    	 }
	 			 	           			       	 });	           			       	    		
	 	           			       	    		for(var k=0;k<uArray.length;k++)
	 	           			       	    		{
	 	           			       	    			$("#"+OverviewValue+" option[value='" + uArray[k] + "']").hide();
	 	           			       	    		}
	 	           							}
		 	           					}		 	           					
		 	           		
		 	           		});
		 	           		
		 	           		
		 	           		
		 	       		 		
 	                 }
 	             }
 	         });     	     
     

     }   
     
     
     function WorksListForWorkWiseUserAccess()
     {
     
    	 $("#DashboardAccessFormFormTable tbody tr").remove();
    	 $("#OverviewDashboardAccessFormFormTable tbody tr").remove();

                
             		var html="<tr><td>1</td>";
  		 			html=html+'<td style="width:30%">'+$("#work_id_fk option:selected").text()+'</td>';

 							

  		 							html=html+'<td>'+
	  		 						'<select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_UserRole" name="user_role_access" multiple="multiple" onChange="getUserRoleAccess();">'+
	  		 						'<option >Select</option>'+
	                                    <c:forEach var="obj" items="${user_roles}" >
	                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
	                                  	</c:forEach>											 			 		                             	
	                                  	+'</select></td><td><select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_UserType" name="user_type_access" multiple="multiple" onChange="getUserTypeAccess();">'+
	  		 						'<option >Select</option>'+
	                                    <c:forEach var="obj" items="${user_types}" >
	                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
	                                  	</c:forEach>											 			 		                             	
	                                  	+'</select></td><td><select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_User" name="user_access" multiple="multiple" onChange="getUserAccess();">'+
	  		 						'<option >Select</option>'+
	                                    <c:forEach var="obj" items="${users}" >
	                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
	                                  	</c:forEach>											 			 		                             	
	                                  	+'</select></td>';  								
 								
  		 				
  		 				html=html+'</tr>';
  		 				
  	         			$("#DashboardAccessFormFormTable tbody").append(html);
  	         			
  	         			var modulesArray=new Array();
                          <c:forEach var="obj" items="${dashboardNames}" >
                          	modulesArray.push("${obj.access_value_name}");
                    	</c:forEach> 
                    	

  		 				for(var y=0;y<modulesArray.length;y++)
  		 					{
  		 				
		  	             		var htmlOverview="<tr>";
		  	             		htmlOverview=htmlOverview+'<td>'+(y+1)+'</td><td style="width:30%">'+modulesArray[y]+'</td>';
		
		  	 							
		
		  	             		htmlOverview=htmlOverview+'<td>'+
		  		  		 						'<select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_OverviewUserRole_'+y+'" name="user_role_access" multiple="multiple" >'+
		  		  		 						'<option >Select</option>'+
		  		                                    <c:forEach var="obj" items="${user_roles}" >
		  		                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
		  		                                  	</c:forEach>											 			 		                             	
		  		                                  	+'</select></td><td><select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_OverviewUserType_'+y+'" name="user_type_access" multiple="multiple" >'+
		  		  		 						'<option >Select</option>'+
		  		                                    <c:forEach var="obj" items="${user_types}" >
		  		                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
		  		                                  	</c:forEach>											 			 		                             	
		  		                                  	+'</select></td><td><select class="searchable validate-dropdown" id="'+$("#work_id_fk").val()+'_OverviewUser_'+y+'" name="user_access" multiple="multiple" >'+
		  		  		 						'<option >Select</option>'+
		  		                                    <c:forEach var="obj" items="${users}" >
		  		                                  		  +'<option value= "${obj.access_value_id}">${obj.access_value_id }</option>'
		  		                                  	</c:forEach>											 			 		                             	
		  		                                  	+'</select></td>';  								
		  	 								
		  	  		 				
		  		                                  htmlOverview=htmlOverview+'</tr>';
		  		                     			$("#OverviewDashboardAccessFormFormTable tbody").append(htmlOverview);

  		 					}
  	  		 				
  	  		 				
  		 				

					for(var i1=0;i1<3;i1++)
					{
						var SRM=document.getElementById("DashboardAccessFormFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
						SRM = SRM.replace(/&amp;/g, "and");
					
							var mopval=$("#work_id_fk").val()+'_'+SRM;
							$("#"+mopval).select2();
						
		   			}
		  		  
		      		getSelectedWorkUser();

		   
     }
     
     function updateWorkAccess()
     {
    	 var allModulesArray=new Array();
			for(var i1=0;i1<3;i1++)
			{
				
				var SRM=document.getElementById("DashboardAccessFormFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
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
     			
				
	   			var myParams3 = { work_id_fk: $("#work_id_fk").val(),access_value:concatText};
	   			
	       		   
   	    	$.ajax({
  	             url: "<%=request.getContextPath()%>/ajax/updateWorkAccess",
  	             data: myParams3, cache: false,
  	             success: function (data1) 
  	             {
  	 				alert("Updated successfully");
  	 				window.location.reload();    	             
  	             }
  	         });    	 
     }
     
     
     
     function getUserRoleAccess(roles)
     {
    	 
     }
     
     
     function getUserTypeAccess(types)
     {
    	 
     }
     
     
     function getUserAccess(users)
     {
    	 
     }     
     
     
     function addDashboardAccessForm()
     {
    	 var allModulesArray=new Array();
			for(var i1=0;i1<3;i1++)
			{
				
				var SRM=document.getElementById("DashboardAccessFormFormTable").rows[0].cells[i1+1].innerHTML.split(" ").join("");
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
        			
 				
   	   			var myParams3 = { work_id_fk: $("#work_id_fk").val(),access_value:concatText};
   	   			
	       		   
      	    	$.ajax({
     	             url: "<%=request.getContextPath()%>/ajax/addDashboardUserAccess",
     	             data: myParams3, cache: false,
     	             success: function (data1) 
     	             {
     	 				alert("Updated successfully");
     	 				window.location.reload();    	             
     	             }
     	         }); 				
 
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
