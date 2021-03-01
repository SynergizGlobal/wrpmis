<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Progress Upload</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
     

    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
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
		
		.page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		
		.dataTables_filter label::after {
		    position: relative;
		    content: none;
		    color: #6C587B;
		    right: 15px;
		    font: normal normal normal 14px/1 FontAwesome;
		}
		.dataTables_filter label .fa::before {
		    position: relative;
		    color: #2E58AD;
		    right: 15px;
		    font: normal normal normal 14px/1 FontAwesome;
		}
		.fw-200{
			width:200px !important;
			max-width:200px;
		}
		label.error{color:red;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
    
    <div class="row">
        <div class="col s12 m12">
        	
        	<div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Upload Activities</h6>
                        </div>
                    </span>
                </div>
                
                <form action="<%=request.getContextPath() %>/upload-activities" id="uploadActivitiesForm" name="uploadActivitiesForm" method="post" enctype="multipart/form-data">
						<div class="container container-no-margin">
							<div class="row">		
								<div class="col s12">
									<c:if test="${not empty success }">					        
										<div class="m-1 close-message">	
										   ${success} 
										   <button type="button" onclick="closeMessage();"
											class="btn waves-effect waves-light bg-m t-c"
											style="width: 20%"><strong>Close </strong></button>
											<hr><br>
										</div>
										
									</c:if>
									
									<c:if test="${not empty error }">
										<div class="m-1 close-message">
										   ${error} 
											<button type="button" onclick="closeMessage();"
											class="btn waves-effect waves-light bg-m t-c"
											style="width: 20%"><strong>Close </strong></button>
											<hr><br>
										</div>
										
									</c:if>
								</div>											
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id_upload" name="work_id" onchange="getContracts(this.value);">
										<option value="">Select</option>
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id_fk_upload" name="contract_id_fk">
										<option value="">Select</option>	
									</select> 
									<span id="contract_id_fkError" class="error-msg"></span>
								</div>	
								<div class="col s12 m2 input-field">
									<p class="searchable_label">Structure Type</p>
									<select class="searchable validate-dropdown" id="structure_type_fk_upload" name="structure_type_fk">
										<option value="">Select</option>
									</select> 
									<span id="structure_type_fkError" class="error-msg"></span>
								</div>	
								
								<div class="col s12 m4 file-field input-field" style="padding-top: 18px;">
								      <div class="btn bg-m t-c">
								        <span>Upload File</span>
								        <input type="file" name="uploadFile" id="uploadFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
								      </div>
								      <div class="file-path-wrapper">
								        <input class="file-path validate" type="text">
								      </div>
								      <span id="uploadFileError" class="error-msg"></span>
                                </div>
												
							</div>
							<div class="row">	
								<div class="col s12 m6 input-field">
									<div class="center-align m-1">
										<!-- <button type="button" class="btn waves-effect waves-light bg-s t-c"	style="width: 100%" onclick="clearFilters();">Clear Filters</button> -->
										<p style="padding-top:1rem">Click <a href="/pmis/Activities_Template.xlsx" download>here</a> for the template</p>
									</div>
								</div>
								<div class="col s12 m6 input-field">
									<div class="center-align m-1">
										<button type="button" onclick="uploadActivities();"
											class="btn waves-effect waves-light bg-m t-c"
											style="width: 100%"><strong>Upload </strong></button>
									</div>
								</div>
							</div>

						</div>
					</form>
					
            </div>
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Activities</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8">
                                <div class="row">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="getActivitiesUploadFilesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Contract</p>
                                        <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getActivitiesUploadFilesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Structure Type</p>
                                        <select id="structure_type_fk" name="structure_type_fk" class="searchable" onchange="getActivitiesUploadFilesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>                                  
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                            Filters</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-activities" class="mdl-data-table">
                                    <thead>
                                        <tr>                                            
                                            <th>Work</th>
											<th>Contract</th>
											<th>Structure type</th>
											<th>Uploaded File</th>
											<th>Status</th>
											<th>Remarks</th>
											<th>Uploaded by </th>
											<th>Uploaded On</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
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
	
	<div class="page-loader-2" style="display: none;">
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
    
    
     <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script type="text/javascript">
    
	    $(document).ready(function () {
	    	$('.modal').modal();
	        $('select:not(.searchable)').formSelect();
	        $('.searchable').select2();
	        
	        getActivitiesUploadFilesList();
	        
	        getWorks();
	        getContracts('');
	        getStructureTypes();
	        
	        //$('.close-message').delay(5000).fadeOut('slow');
	    });
	    
	    function closeMessage() {
	    	$('.close-message').delay(500).fadeOut('slow');
		}
        
        
        function getWorks() {
         	$(".page-loader").show();
            $("#work_id_upload option:not(:first)").remove();
            var myParams = {};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksInActivitiesUpload",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var work_short_name = '';
                        	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
                            $("#work_id_upload").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + work_short_name +'</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			  $(".page-loader").hide();
   	          	  getErrorMessage(jqXHR, exception);
  	     	  }
            });
        }
        
        function getContracts(work_id) {
        	$(".page-loader").show();
            $("#contract_id_fk_upload option:not(:first)").remove();
            var myParams = {work_id : work_id};
     		$.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContractsInActivitiesUpload",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var contract_short_name = '';
                        	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
                           $("#contract_id_fk_upload").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + contract_short_name +'</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			  $(".page-loader").hide();
   	          	  getErrorMessage(jqXHR, exception);
  	     	  }
            });
        }
        
        
        function getStructureTypes() {
         	$(".page-loader").show();
            $("#structure_type_fk_upload option:not(:first)").remove();
            var myParams = {};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getStructureTypesInActivitiesUpload",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	$("#structure_type_fk_upload").append('<option value="' + val.structure_type + '">' + $.trim(val.structure_type) +'</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			  $(".page-loader").hide();
   	          	  getErrorMessage(jqXHR, exception);
  	     	  }
            });
        }
        
        
        function uploadActivities() {
        	if(validator.form()){
    			$(".page-loader").show();
    			$("#uploadActivitiesForm").submit();			
    	 	}
		}
        
        
        var validator = $('#uploadActivitiesForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "work_id": {
				 		required: false
				 	  },"contract_id_fk": {
				 		required: true
				 	  },"structure_type_fk": {
				 		required: true
			 	  	  },"uploadFile":{
			 	  		required: true
			 	  	  }
				 				
			 	},
			    messages: {
				     "work_id": {
			 			required: 'Required'
			 	  	 },"contract_id_fk": {
			 			required: 'Required'
			 	  	 },"structure_type_fk": {
  			 			required: 'Required'
  			 	  	 },"uploadFile":{
  			 	  		required: 'Required'
			 	  	 }
			 				      
		       },
			   errorPlacement:
			 	    function(error, element){
					  	if (element.attr("id") == "work_id_upload" ){
				 		     document.getElementById("work_idError").innerHTML="";
				 			 error.appendTo('#work_idError');
				 	    }else if (element.attr("id") == "contract_id_fk_upload" ){
				 		     document.getElementById("contract_id_fkError").innerHTML="";
				 			 error.appendTo('#contract_id_fkError');
				 	    }else if (element.attr("id") == "structure_type_fk_upload" ){
							 document.getElementById("structure_type_fkError").innerHTML="";
							 error.appendTo('#structure_type_fkError');
				        }else if (element.attr("id") == "uploadFile" ){
							 document.getElementById("uploadFileError").innerHTML="";
							 error.appendTo('#uploadFileError');
				        }
			  },invalidHandler: function (form, validator) {
                 var errors = validator.numberOfInvalids();
                 if (errors) {
                     var position = validator.errorList[0].element;
                     jQuery('html, body').animate({
                         scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                     }, 1000);
                 }
              },submitHandler: function(form) {
			    // do other things for a valid form
			    form.submit();
			    //return true;
			  }
		});
        
        /***************************************************************************************/
        
        
        function getActivitiesUploadFilesList(){
        	$(".page-loader-2").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
        	
        	getWorksListFilter();
        	getContractsListFilter();
        	getStructureTypesListFilter();
      		 
        	table = $('#datatable-activities').DataTable();	   		 
	   		table.destroy();	   		
	   		$.fn.dataTable.moment('DD-MMM-YYYY');
	   		table = $('#datatable-activities').DataTable({
	       		"bStateSave": true,
	       		fixedHeader: true,
	               "fnStateSave": function (oSettings, oData) {
	                   localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
	               },
	               "fnStateLoad": function (oSettings) {
	                   return JSON.parse(localStorage.getItem('MRVCDataTables'));
	               },
	               columnDefs: [
	                   {
	                       targets: [0, 1, 2],
	                       className: 'mdl-data-table__cell--non-numeric'
	                   },
	                   { orderable: false, 'aTargets': ['nosort'] }
	               ],
	               // "ScrollX": true,
	               //"scrollCollapse": true,
	               //"sScrollY": 400,
	               "sScrollX": "100%",
	               "sScrollXInner": "100%",
	               "bScrollCollapse": true,
	               initComplete: function () {
	                   $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	               }
	           }).rows().remove().draw();
	   		
	   		
	   		table.state.clear();
         	
   			
     		 
   		    /***************************************************************************************************/   
   		    $.ajax({url : "<%=request.getContextPath()%>/ajax/getActivitiesUploadFilesList",type:"POST",data:myParams,
   		    	success : function(data){    				
	    			if(data != null && data != '' && data.length > 0){    					
	             		$.each(data,function(key,val){
	             			var rowArray = [];    	                 
	    
	                    	var workShortName = '';
	                        if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
	                        
	                        var contractShortName = '';
	                        if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
	                        
							var filePath = "";
	                        
	                        if($.trim(val.uploaded_file) != ''){
	                        	filePath = '<a href="<%=CommonConstants2.ACTIVITIES_UPLOAD_FILES%>'+ val.uploaded_file +'">'+val.uploaded_file + '</a>';
	                        }
	                       
	                       	rowArray.push($.trim(val.work_id) + workName);
	                       	rowArray.push($.trim(val.contract_id) + contractShortName);
	                       	rowArray.push($.trim(val.structure_type_fk));
	                    	rowArray.push(filePath);
	                       	rowArray.push($.trim(val.status));
	                       	rowArray.push($.trim(val.remarks));
	                       	rowArray.push($.trim(val.uploaded_by_user_id_fk));
	                    	rowArray.push($.trim(val.uploaded_on));
	                       	
	                        table.row.add(rowArray).draw( true );
	                        		                       
	    				});
	             		
	             		$(".page-loader-2").hide();
	    			}else{
	    				$(".page-loader-2").hide();
	    			}
	    			
	    		},error: function (jqXHR, exception) {
	    			$(".page-loader-2").hide();
	             	getErrorMessage(jqXHR, exception);
	         	}
	    	});
   		    
   		    $(".page-loader-2").hide();  		     
         	
        }
        
      	
    	function clearFilters() {
            $('#work_id_fk').val("");
            $('#contract_id_fk').val("");
            $('#structure_type_fk').val("");
            $('.searchable').select2();
            getActivitiesUploadFilesList();
        }
      	
      	
        function getWorksListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
  	       
         	$(".page-loader").show();

            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var work_short_name = '';
                            	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
 	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + work_short_name +'</option>');
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
        
        function getContractsListFilter() {
       		var work_id_fk = $("#work_id_fk").val();
           	var contract_id_fk = $("#contract_id_fk").val();
           	var structure_type_fk = $("#structure_type_fk").val();
           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
  	       
         	$(".page-loader").show();

            if ($.trim(contract_id_fk) == "") {
                $("#contract_id_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + contract_short_name +'</option>');
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
        
        
        function getStructureTypesListFilter() {
       		var work_id_fk = $("#work_id_fk").val();
           	var contract_id_fk = $("#contract_id_fk").val();
           	var structure_type_fk = $("#structure_type_fk").val();
           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
      	       
         	$(".page-loader").show();

            if ($.trim(structure_type_fk) == "") {
                $("#structure_type_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStructureTypesListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#structure_type_fk").append('<option value="' + val.structure_type_fk + '">' + $.trim(val.structure_type_fk) +'</option>');
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
        
	    //This function is used to get error message for all ajax calls
	    function getErrorMessage(jqXHR, exception) {
	       	    var msg = '';
	       	    if (jqXHR.status === 0) {
	       	        msg = 'Not connect.\n Verify Network.';
	       	    } else if (jqXHR.status == 404) {
	       	        msg = 'Requested page not found. [404]';
	       	    } else if (jqXHR.status == 500) {
	       	        msg = 'Internal Server Error [500].';
	       	    } else if (exception === 'parsererror') {
	       	        msg = 'Requested JSON parse failed.';
	       	    } else if (exception === 'timeout') {
	       	        msg = 'Time out error.';
	       	    } else if (exception === 'abort') {
	       	        msg = 'Ajax request aborted.';
	       	    } else {
	       	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
	       	    }
	       	    console.log(msg);
	    }
        
    </script>
<body>

</body>
</html>