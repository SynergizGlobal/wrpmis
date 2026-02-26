<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Structure - Update Forms - PMIS</title>
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />

    <style>
         p a {
            color: blue;
        }
        .input-field .searchable_label{
        	font-size:0.85rem;
        }     
    	 .fw-400{
    	 	width:400px !important;
    	 	max-width:400px;
    	 }
    	 .fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
    	 .fw-200{
    	 	width:200px !important;
    	 	max-width:200px;
    	 }
         .dataTables_filter label::after{
         	content:'';
         }
         .right-btns1 .fa{
         	position:relative;
         	top:-35px;
         }
        
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
          .right-btns1 .fa+.fa{
         	right:-10px;
         }
         .row.no-mar{
         	margin-bottom:0;
         }

      @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
			}
			.mob-btn{
				padding:0 12px;
			}
			.hideCOl{
				display:none;
			} 
			.r-300{
				width:30vw !important;
        		max-width:30vw;
			}
			 .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	        .fw-111{
	        	width:30vw !important;
	        	min-width:30vw;
	        }
		}
		#datatable-structure_mob td > .btn.t-c{
			padding: 0 10px;
		}
		.fw-38w{
			width: 38vw !important;
		}
		.right-btns{ display:none;}

		.right-btns:last-of-type {
		  display:block;
		}
		.no-sort.sorting_asc:before,
.no-sort.sorting_asc:after{
    opacity:0 !important;
    content:'' !important;
}
tbody tr td:last-of-type,
thead tr th:last-of-type{
	max-width:100px;
	width:100px;
}
    .m-n1 {
        margin: -2rem auto 0;
    }

    .template-btn {
        text-shadow: 1px 1px 1px black;
    }

    @media only screen and (max-width: 767px) {
        .mob-mar {
            text-align: left;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    @media(max-width: 768px){
    	.dataTables_scrollBody tbody tr td:last-of-type, .no-sort{
    		max-width: 200px !important;
    	}
    	.w200{
    		width: 100px !important;
    	}
    }
    @media(max-width: 360px){
    	.w200{
    		width: 80px !important;
    	}
    }
    </style>
</head>
<body>

   <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="container-padding">
		
		<div class="row no-mar">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg"> 
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Structure</h6> -->
								<h6 class="mob-mar">Structure</h6>
								<div class="col s12 m12 right-align exportButton">
							    <div class="m-n1">
							        <a href="<%=request.getContextPath()%>/add-structure-form"
																	class="btn waves-effect waves-light bg-s t-c"> <strong><i
																		class="fa fa-plus-circle"></i> Add</strong></a>

							    </div>
							</div>
							</div>
						</span>
						<div class="row no-mar">
							<div class="col s12 m12">
								<div class="row">
									<div class="row clearfix">
									    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									        <c:if test="${not empty success }">
									            <div class="center-align m-1 close-message">${success}</div>
									        </c:if>
									        <c:if test="${not empty error }">
									            <div class="center-align m-1 close-message">${error}</div>
									        </c:if>
									    </div>
									</div>
									<div class="col s6 m4 l2 offset-l3 input-field">
										<p class="searchable_label">Project</p>
										<select id="project_id_fk" name="project_id_fk"
											  class="searchable" onchange="addInQueProject(this.value);getStructureList();">
											<option value="">Select</option>
											<%--  <c:forEach var="obj" items="${contractList}">
	                       						  <option value="${obj.contract_id }" <c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
	                                        </c:forEach> --%>
										</select>
									</div>
																
									<div class="col s12 m4 l2 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
										<div class="divider hide-on-med-and-up	"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col m12 s12">
									<table id="datatable-structure" class="mdl-data-table">
										<thead>
											<tr>
											    <th>Project </th>
												<th>Structures </th>
												<th class="no-sort">Action</th>
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

	<!-- update popup starts -->
	<div id="upload_template" class="modal">
		<div class="modal-content">
			<div class="center-align p-2 bg-m modal-title">
				<h6>Upload Structure</h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-structures"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="structureFile"
												name="structureFile" required="required">
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text">
										</div>
									</div>
								</div>
								<div class="col m2 hide-on-small-only"></div>
							</div>
						</div>
					</div>
					<div class="row no-mar">
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="submit" class="btn waves-effect waves-light bg-m"
									style="width: 100%;">Update</button>
							</div>
						</div>
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="button" class="btn waves-effect waves-light bg-s"
									style="width: 100%;" onclick="closeUploadStructureModal();">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>



	<div class="page-loader" style="display: none;">
		<div class="preloader-wrapper big active">
			<div class="spinner-layer spinner-blue-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
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
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
	 <form action="<%=request.getContextPath()%>/get-structure" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="structure_id" id="structure_id"/>
    </form>
    
  <form action="<%=request.getContextPath()%>/export-structure" name="exportStructureForm" id="exportStructureForm" target="_blank" method="post">	
        <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
        <input type="hidden" name="department_fk" id="exportDepartment_id_fk" />
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
	</form>
	
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
	
		var filtersMap = new Object();
		
		function openUploadStructureModal() {
			$("#structureFile").val('');
			$("#upload_template").modal('open');
		}

		function closeUploadStructureModal() {
			$("#structureFile").val('');
			$("#upload_template").modal('close');
		}

		$(document).ready(function() {
			$('.modal').modal();
			$('select:not(.searchable)').formSelect();
			$('.searchable').select2();
			
			var filters = window.localStorage.getItem("structureFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectListFilter(temp2[1]);
		        	  }
	        	  }
	          }
            }
            
			$('.close-message').delay(3000).fadeOut('slow');

		     $('#structure-table').DataTable({
	                columnDefs: [
	                    {
	                        targets: [0],
	                        className: 'mdl-data-table__cell--non-numeric',
	                        targets: 'no-sort', orderable: false,
	                    },
	                    //{ "width": "10px", "targets": [2] },
	                ],
	                "sScrollX": "100%",
	                "sScrollXInner": "100%",
	                "bScrollCollapse": true,
	                "bAutoWidth": true,
	                "ordering": false, //to stop sorting option                
	                fixedHeader: true, // to change the language of data table	          
	                initComplete: function () {
	                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	                }
	            });
			getStructureList();
			//getStructureUploadsList();
		});

		function clearFilter() {
			$("#project_id_fk").val('');

			$('.searchable').select2();
			window.localStorage.setItem("structureFilters",'');
			window.location.href = "<%=request.getContextPath()%>/structure";
			var table = $('#datatable-structure').DataTable();
    		table.draw( true );
			//getStructureUploadsList();
		}
        
		  function addInQueProject(project_id_fk){
	        	Object.keys(filtersMap).forEach(function (key) {
		   			if(key.match('project_id_fk')) delete filtersMap[key];
		   		});
	        	if($.trim(project_id_fk) != ''){
	       	    	filtersMap["project_id_fk"] = project_id_fk;
	        	}
	        }


    
        
		function getStructureList() {
			$(".page-loader-2").show();

			getProjectListFilter('');

			var project_id_fk = $("#project_id_fk").val();
			
			var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("structureFilters", filters);
   			});
        	 
						table = $('#datatable-structure').DataTable();
			
						table.destroy();
			
						
			
						var myParams = "&project_id_fk=" + project_id_fk;
			
						/***************************************************************************************************/
			
						$("#datatable-structure")
								.DataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sort" : "position",
											//bStateSave variable you can use to save state on client cookies: set value "true" 
											"bStateSave" : false,
											 stateSave: true,
											 "fnStateSave": function (oSettings, oData) {
								                    localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
								                },
								                "fnStateLoad": function (oSettings) {
								                    return JSON.parse(localStorage.getItem('MRVCDataTables'));
								                },
											//Default: Page display length
											"iDisplayLength" : 10,
											"iData" : {
												"start" : 52
											},
											//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
											"iDisplayStart" : 0,
											"fnDrawCallback" : function() {
												//Get page numer on client. Please note: number start from 0 So
												//for the first page you will see 0 second page 1 third page 2...
												//Un-comment below alert to see page number
												//alert("Current page number: "+this.fnPagingInfo().iPage);
											},
											//"sDom": 'l<"toolbar">frtip',
											"initComplete" : function() {
												$('.dataTables_filter input[type="search"]')
														.attr('placeholder', 'Search')
														.css({
															'width' : '350px ',
															'display' : 'inline-block'
														});
			
												var input = $('.dataTables_filter input')
														.unbind()
														.bind('keyup',function(e){
														    if (e.which == 13){
														    	self.search(input.val()).draw();
														    }
														}), self = this.api(), $searchButton = $(
														'<i class="fa fa-search" title="Go" >')
												//.text('Go')
												.click(function() {
													self.search(input.val()).draw();
												}), $clearButton = $(
														'<i class="fa fa-close" title="Reset">')
												//.text('X')
												.click(function() {
													input.val('');
													$searchButton.click();
												})
												$("div.right-btns1").remove();
												$('.dataTables_filter').append(
														'<div class="right-btns"></div>');
												$('.dataTables_filter div').append(
														$searchButton, $clearButton);
												
											},
											columnDefs : [ {
												"targets" : 'no-sort',
												"orderable" : false,
											},
											// {targets:[1,2,4,5], className: 'hideCOl'},
											{ targets: [0], className: 'no-sort'  },
											{ targets: [1], className: 'last-column'  },
											//{ targets: [0,3], className: 'fw-111'  }  
											],
											"sScrollX" : "100%",
											"ordering":false,
											"sScrollXInner" : "100%",
											"bScrollCollapse" : true, 
											"language" : {
												"info" : "_START_ - _END_ of _TOTAL_",
												paginate : {
													next : '<i class="fa fa-angle-right"></i>', // or '→'
													previous : '<i class="fa fa-angle-left"></i>' // or '←' 
												}
											},
											"bDestroy" : true,
											"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getStructureList?"+myParams,
													
						        "aoColumns": [
						        	
						        	 { "mData": function(data,type,row){
							            	if($.trim(data.project_id_fk) == ''){ return '-'; }else{ return data.project_id_fk; }
							            } },  				            
						            { "mData": function(data,type,row){
						            	var structureType = data.structure_type_fk;
										if(typeof structureType !== "undefined" && structureType != null
												&& structureType != "" && structureType.indexOf(",") > -1){
											structureType = structureType.replace(/,/g, "<br />");
						            	}
						            	if($.trim(data.structure_type_fk) == ''){ return '-'; }else{ return structureType; }
						            } },
						           /*  { "mData": function(data,type,row){
						            	if($.trim(data.department_fk) == ''){ return '-'; }else{ return data.department_name; }
						            } },
						            { "mData": function(data,type,row){
						            	if($.trim(data.department_fk) == ''){ return '-'; }else{ return data.department_name; }
						            } },
						         	{ "mData": function(data,type,row){
						         		var contract_short_name = '';
						         		if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) } 
						            	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk + contract_short_name; }
						            } }, */
						         	{ "mData": function(data,type,row){
						         		var structure_id = "'"+data.structure_id+"'";
					                    var actions = '<a href="javascript:void(0);"  onclick="getStructure('+structure_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
						            	return actions;
						            } }
						            
						        ]
						    });
		  $(".page-loader-2").hide();  		     
      	
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
	  	

      	
        function getProjectListFilter(project) {
	    	var project_id_fk = $("#project_id_fk").val();
         	$(".page-loader").show();
            if ($.trim(project_id_fk) == "") {
                $("#project_id_fk option:not(:first)").remove();
     		 	var myParams = {project_id_fk : project_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectsListFilterInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (project == val.project_id_fk)?'selected':'';
                            	$("#project_id_fk").append('<option value="' + val.project_id_fk + '" '+selectedFlag+'>' + val.project_id_fk +' - '+ $.trim(val.project_name) +'</option>');
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
        
 
        
	    function getStructure(structure_id) {
			$("#structure_id").val(structure_id);
			$("#getForm").submit();
		}
	
	    function exportStructure(){
	    	/* var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val(); */
	    	var project_id_fk = $("#project_id_fk").val();
	     	 
	     	/*  $("#exportContract_id_fk").val(contract_id_fk);
	     	 $("#exportDepartment_id_fk").val(department_fk); */
	     	 $("#exportProject_id_fk").val(project_id_fk);
	     	 $("#exportStructureForm ").submit();
	  	}
    
    </script> 
</body>
</html>