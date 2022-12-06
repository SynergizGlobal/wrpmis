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
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/WorkModuleUserAccess.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" > 
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
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="WorksListForWorkWiseUserAccess();" style="display:block;">
                                        <option value="">Select</option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk} - ${obj.project_name}</option>
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
                                          	<c:forEach var="obj" items="${modulesList }">
                                      	   		<th>${obj.module_name_fk}</th>
                                         	</c:forEach>											
										</tr>
									
									</thead>
									<tbody id="WorkModuleUserAccessTableBody">
                                        <c:forEach var="obj" items="${worksList }">
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
                                       	</c:forEach>
                                       	
                                       		
									</tbody>
								</table>
								<p id="fyError" style="color:red;"></p>
							</div>
						</div>
					<div class="container container-no-margin">                           

                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                   <div class="center-align m-1">
						                       <button type="button" onclick="addWorkModuleUserAccess();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/new-workModuleUserAccess" class="btn waves-effect waves-light bg-s">Cancel</a>
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
         getSelectedWorkUser();
     });
     
     function getSelectedWorkUser()
     {
    	     var trRowsLength=$('#WorkModuleUserAccessFormTable > tbody').length;
     
    	 	 for(var i=1;i<trRowsLength;i++)
    		 {
    		 	alert(document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i+1].innerHTML);
    		 }
     }
     
     function WorksListForWorkWiseUserAccess()
     {
    	 $("#WorkModuleUserAccessFormTable tbody tr").remove();
     
    	 var myParams2 = { project_id_fk: document.getElementById("project_id_fk").value };
		  $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getWorksListForWorkWiseUserAccess",
            data: myParams2, cache: false,
            success: function (data) 
            {
                if (data.length > 0) 
                {
                	$.each(data, function (i, val) {
                
                		var html="<tr>";
     		 			html=html+'<td>'+val.work_id_fk+' - '+val.work_short_name+'</td>';
     		 				for(var i1=0;i1<7;i1++)
     		 				{
     		 						html=html+'<td>'+
     		 						
     		 						'<select class="searchable validate-dropdown" id="'+val.work_id_fk+'_'+document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i+1].innerHTML+'" name="executive_user_id_fk" multiple="multiple" >'+
     		 						'<option >Select</option>'+
                                       <c:forEach var="obj" items="${usersDetails}" >
                                     		  +'<option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>'
                                     	</c:forEach>											 			 		                             	
                                     	+'</select></td>';    
     		 				}
     		 				html=html+'</tr>';
 		         			$("#WorkModuleUserAccessFormTable tbody").append(html);  
 		         		  
                	});
                }
            }
		  });
     }
     
     
     function getProjectWiseModuleUserAccess()
     {
    	 var html="";
    	 
	    	$.ajax({
	             url: "<%=request.getContextPath()%>/ajax/getProjectWiseModuleUserAccess",
	             type: 'GET',
	             cache: false,
	             success: function (data1) {
	                 if (data1.length > 0) 
	                 {
              		 		html=html+'<select class="searchable validate-dropdown" name="executive_user_id_fk"  multiple="multiple">';
          	                	 html=html+'<option value="0">Select</option>';
         	                	 $.each(data1, function (i1, val1) {
             	                	 html=html+'<option value= "'+val.user_id+'">'+val.designation+'-'+val.user_name+'</option>';
         	                     });          	                	 
      	                	 html=html+'</select>';
      	                	
	                 }
	             }
	         }); 

        return html;








<%--     	 $("#WorkModuleUserAccessFormTable tbody tr").remove();
 		  var myParams2 = { project_id_fk: document.getElementById("project_id_fk").value };
		  $.ajax({
             url: "<%=request.getContextPath()%>/ajax/getWorksListForWorkWiseUserAccess",
             data: myParams2, cache: false,
             success: function (data) {
                 if (data.length > 0) {
                	 $.each(data, function (i, val) {
             	    	
                		 var html="<tr>";
                		 
                		 html=html+'<td>'+val.work_id_fk+' - '+val.work_short_name+'</td>';
                		 
                		 var myParams3 = { work_id_fk: val.work_id_fk,module_id_fk:document.getElementById("WorkModuleUserAccessFormTable").rows[0].cells[i+1].innerHTML};
                		 
             	    	$.ajax({
             	             url: "<%=request.getContextPath()%>/ajax/getWorkModuleWiseUsers",
             	             data: myParams3, cache: false,
             	             success: function (data1) {
             	                 if (data1.length > 0) {
             	                	 var html1='<select class="searchable validate-dropdown" name="executive_user_id_fk"  multiple="multiple">';
             	                	 $.each(data1, function (i1, val1) {
                 	                	 html1=html1+'<option value="0">Select</option>';
             	                     });
             	                	 html1=html1+'</select>';
             	                	 html=html1;
             	                	$("#WorkModuleUserAccessFormTable tbody").append(html);
             	                 }
             	             }
             	         }); 
             	    	
             	    	html=html+'</tr>';
             	    	
             	    	          	    	
             	    	
                     });
                 }
             }
         }); --%>      	 
     }
     
     

     
     function removeErrorMsg(){
		 $('#bank_name_text1Error').text('');
		 $('#bttnUpdate').prop('disabled', false);
		 updateFlag = true;
		}
     
     $("#bankNameForm").submit(function (e) {
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			if(flag){
				document.getElementById("bankNameForm").submit();	
			 }
			 $(".page-loader").hide();
			 return false;
        }
     })
     
     $("#bankNameForm1").submit(function (e) {
       	 if(validator1.form()){ 
   			$(".page-loader").show();
   			$("#onlyUpdateModal").modal();
   			if(updateFlag){
 				document.getElementById("bankNameForm1").submit();	
 			 }
 			 $(".page-loader").hide();
 			 return false;
        }
     })
    
     var validator = $('#bankNameForm').validate({
    	 rules: {
    		 	"bank_name": {
			 		  required: true 
    			},"bank_name_new": {
			 		  required: true 
    			}
			},messages: {
		 		 "bank_name": {
			 		  required: 'Required'
			 	 },"bank_name_new": {
			 		  required: 'Required'
			 	 }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "bank_name_text" ){
				     document.getElementById("bank_nameError").innerHTML="";
			 	     error.appendTo('#bank_nameError');
			   }else if(element.attr("id") == "bank_name_text1" ){
				     document.getElementById("bank_name_text1Error").innerHTML="";
			 	     error.appendTo('#bank_name_text1Error');
			   }
	        }
     });
     
     var validator1 = $('#bankNameForm1').validate({
    	 rules: {
    		 	"bank_name_new": {
			 		  required: true 
    			}
			},messages: {
		 		"bank_name_new": {
			 		  required: 'Required'
			 	 }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "bank_name_text1" ){
				     document.getElementById("bank_name_text1Error").innerHTML="";
			 	     error.appendTo('#bank_name_text1Error');
			   }
	        }
     });
    
     $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	  });

     function updateRow(no) {
         var currentVal = $('#value'+no).val();
         $('#bank_name_old_text').val($.trim(currentVal)) 
         $('#onlyUpdateModal').modal('open');
         $('#onlyUpdateModal #bank_name_text1').val($.trim(currentVal)).focus();
     }
     
     function deleteRow(val){
     	$("#bank_name").val(val);
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
	            	$(".page-loader").show();
	               // swal("Deleted!", "Record has been deleted", "success");
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-bank-name');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
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
