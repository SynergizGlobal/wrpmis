<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Progress Upload - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
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
		.fw-150{
			width:150px !important;
			max-width:150px;
		}
		.fw-200{
			width:200px !important;
			max-width:200px;
		}
		.fw-250{
			width:250px !important;
			max-width:250px;
		}
		.fw-300{
			width:300px !important;
			max-width:300px;
		}
		td.p-10i{
			padding:10px !important;
		}
		label.error{color:red;}
		@media only screen and (max-width: 769px){
			.w-sm-auto {
			    width: auto !important;
			}
			th.fw-33p{
				width:33% !important;		
			}
		}
.no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
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
               <!--  </div> -->
                
                <form action="<%=request.getContextPath() %>/demo/upload-activities" id="uploadActivitiesForm" name="uploadActivitiesForm" method="post" enctype="multipart/form-data">
						<div class="container container-no-margin">
							<div class="row">
								<div class="col s12">
									<c:if test="${not empty success }">	
											        
										<div class="m-1 close-message">	
											   <!-- <span style="color:Green;" id="StatusMessage">Activities Uploaded Successfully...</span>	 -->
											   <p>
												   ${success} 
												   <a onclick="closeMessage();" style="font-size: 20px;color:black;"><strong>X </strong></a>
											   </p>
												<hr><br>
										</div>
										
									</c:if>
									
									<c:if test="${not empty error }">
										<div class="m-1 close-message">
										    <p style="color:red;">${error}
												<a onclick="closeMessage();" style="font-size: 20px;color:black;"><strong>X </strong></a>
											 </p>
											<hr><br>
										</div>
										
									</c:if>
								</div>											
								<div class="col s6 m4 l4 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id_upload" name="work_id" onchange="getContracts(this.value);">
										<option value="">Select</option>
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s6 m4 l4 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id_fk_upload" name="contract_id_fk">
										<option value="">Select</option>	
									</select> 
									<span id="contract_id_fkError" class="error-msg"></span>
								</div>	
									
								
								<div class="col s12 m4 l4 file-field input-field" >
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
								<div class="col s12 m4 offset-m2 offset-l1">
									<div class="center-align m-1">
										<!-- <button type="button" class="btn waves-effect waves-light bg-s t-c"	style="width: 100%" onclick="clearFilters();">Clear Filters</button> -->
										<p style="padding-top:1rem">Click <a href="/pmis/Activities_Template_demo.xlsx" download>here</a> for the template</p>
									</div>
								</div>
								<div class="col s12 m4 input-field center-align">
									<div class=" m-1">
										<button type="button" onclick="uploadActivities();" style="width:100%;"
											class="btn waves-effect waves-light bg-m t-c w-sm-auto"><strong>Upload </strong></button>
									</div>
								</div>
							</div>

						</div>
					</form>
					
            </div>
            </div>
            
            <c:if test="${USER_ROLE_CODE eq 'IT' }">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Activities</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m10 l8 offset-l2 offset-m1">
                                <div class="row">
                                    <div class="col s6 m3 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="addInQueWork(this.value);getActivitiesUploadFilesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s6 m3 input-field">
                                        <p class="searchable_label">Contract</p>
                                        <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="addInQueContract(this.value);getActivitiesUploadFilesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s6 m3 input-field">
                                        <p class="searchable_label">Structure Type</p>
                                        <select id="structure_type_fk" name="structure_type_fk" class="searchable" onchange="addInQueStructure(this.value);getActivitiesUploadFilesList();" >
                                            <option value="">Select</option>
                                        </select>
                                    </div>                                  
                                    <div class="col s6 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 12px;width: 100%;" onclick="clearFilters()">Clear
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
                                            <th class="fw-33p no-sort fw-150">Work</th>
											<th class="fw-33p fw-250">Contract</th>
											<th>Structure type</th>
											<th class="fw-33p fw-200">Uploaded File</th>
											<th>Status</th>
											<th class="fw-250">Remarks</th>
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
            </c:if>
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
        var filtersMap = new Object();
	    var pageNo = window.localStorage.getItem("activitiesPageNo");
	    $(document).ready(function () {
	    	$('.modal').modal();
	        $('select:not(.searchable)').formSelect();
	        $('.searchable').select2();
   			var filters = window.localStorage.getItem("activitiesUploadFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'work_id_fk' ){
  		        		getWorksListFilter(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
  		        		getContractsListFilter(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'structure_type_fk'){
  		        		getStructureTypesListFilter(temp2[1]);
  		        	  }
  	        	  }
  	            }
              }
	        getActivitiesUploadFilesList();
	        getWorks();
	        getContracts('');	        
            
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
                url: "<%=request.getContextPath()%>/ajax/demo/getWorksInActivitiesUpload",
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
                url: "<%=request.getContextPath()%>/ajax/demo/getContractsInActivitiesUpload",
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
        
        function uploadActivities(){
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
				 	  },"uploadFile":{
			 	  		required: true
			 	  	  }
				 				
			 	},
			    messages: {
				     "work_id": {
			 			required: 'Required'
			 	  	 },"contract_id_fk": {
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
        
        $('#work_id_upload,#contract_id_fk_upload').change(function(){
            if ($(this).val() != ""){
                $(this).valid();
            }
        });
        /***************************************************************************************/
          function addInQueWork(work_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('work_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
	      	}
        }
        
          function addInQueContract(contract_id_fk){
  	      	Object.keys(filtersMap).forEach(function (key) {
  		   		if(key.match('contract_id_fk')) delete filtersMap[key];
  	   	   	});
  	      	if($.trim(contract_id_fk) != ''){
              	filtersMap["contract_id_fk"] = contract_id_fk;
  	      	}
          }
          
          function addInQueStructure(structure_type_fk){
  	      	Object.keys(filtersMap).forEach(function (key) {
  		   		if(key.match('structure_type_fk')) delete filtersMap[key];
  	   	   	});
  	      	if($.trim(structure_type_fk) != ''){
              	filtersMap["structure_type_fk"] = structure_type_fk;
  	      	}
          }
        
        function getActivitiesUploadFilesList(){
        	$(".page-loader-2").show();
        	getWorksListFilter('');
        	getContractsListFilter('');
        	getStructureTypesListFilter('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
        	
        
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("activitiesUploadFilters", filters);
   			});
        	
        	table = $('#datatable-activities').DataTable();	   		 
	   		table.destroy();	   		
	   		$.fn.dataTable.moment('DD-MMM-YYYY');
	   		table = $('#datatable-activities').DataTable({
	   			"order": [],
      		   "bStateSave": true,  
      		   fixedHeader: true,
            
            	//Default: Page display length
				"iDisplayLength" : 10,
				"iData" : {
					"start" : 52
				},"iDisplayStart" : 0,
				"drawCallback" : function() {
					var info = table.page.info();
					window.localStorage.setItem("activitiesPageNo", info.page);
				},
	               columnDefs: [
	            	   {
	                        targets: [2,4,5,6,7],
	                        className: 'hideCOl'
	                    },
	                    {
	                        targets: [0],
	                        className: 'p-10i'
	                    },
	                   { orderable: false, 'aTargets': ['no-sort'] }
	               ],
	               // "ScrollX": true,
	               //"scrollCollapse": true,
	               //"sScrollY": 400,
	               "sScrollX": "100%",
	               "ordering":false,
	               "sScrollXInner": "100%",
	               "bScrollCollapse": true,
	               initComplete: function () {
	                   $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	                   var input = $('.dataTables_filter input')
						.unbind()
						.bind('keyup',function(e){
						    if (e.which == 13){
						    	self.search(input.val()).draw();
						    }
						}), self = this.api(), $searchButton = $(
						'<i class="fa fa-search" title="Go">')
						.click(function() {
							self.search(input.val()).draw();
						}), $clearButton = $(
								'<i class="fa fa-close" title="Reset">')
						.click(function() {
							input.val('');
							$searchButton.click();
						})
						$('.dataTables_filter').append('<div class="right-btns"></div>');
						$('.dataTables_filter div').append( $searchButton, $clearButton);
	               }
	           }).rows().remove().draw();
	   		
	   		
	   		table.state.clear();
         	
   			
     		 
   		    /***************************************************************************************************/   
   		    $.ajax({url : "<%=request.getContextPath()%>/ajax/demo/getActivitiesUploadFilesList",type:"POST",data:myParams,
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
	             		if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	                    var oTable = $('#datatable-activities').dataTable();
	                    oTable.fnPageChange( pageNo );
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
            window.localStorage.setItem("activitiesUploadFilters",'');
        	window.location.href="<%=request.getContextPath()%>/demo/activities-upload"
        	var table = $('#datatable-activities').DataTable();
        	table.draw( true );
        }
      	
      	
        function getWorksListFilter(work) {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
  	       
         	$(".page-loader").show();

            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/demo/getWorksListFilterInActivitiesUpload",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var work_short_name = '';
                            	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
                            	var selectedFlag = (work == val.work_id)?'selected':'';
 	                            $("#work_id_fk").append('<option value="' + val.work_id + '"'+selectedFlag+'>' + $.trim(val.work_id) + work_short_name +'</option>');
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
        
        function getContractsListFilter(contract) {
       		var work_id_fk = $("#work_id_fk").val();
           	var contract_id_fk = $("#contract_id_fk").val();
           	var structure_type_fk = $("#structure_type_fk").val();
           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
  	       
         	$(".page-loader").show();

            if ($.trim(contract_id_fk) == "") {
                $("#contract_id_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/demo/getContractsListFilterInActivitiesUpload",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
                            	var selectedFlag = (contract == val.contract_id)?'selected':'';
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id) + contract_short_name +'</option>');
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

        function getStructureTypesListFilter(fob) {
       		var work_id_fk = $("#work_id_fk").val();
           	var contract_id_fk = $("#contract_id_fk").val();
           	var structure_type_fk = $("#structure_type_fk").val();
           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,structure_type_fk : structure_type_fk} ;
      	       
         	$(".page-loader").show();

            if ($.trim(structure_type_fk) == "") {
                $("#structure_type_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/demo/getStructureTypesListFilterInActivitiesUpload",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (fob == val.structure_type_fk)?'selected':'';
                            	$("#structure_type_fk").append('<option value="' + val.structure_type_fk + '"'+selectedFlag+'>' + $.trim(val.structure_type_fk) +'</option>');
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