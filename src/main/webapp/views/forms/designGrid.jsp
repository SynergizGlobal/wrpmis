<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design & Drawing</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>
        p a {
            color: blue;
        }
         td{
       		 word-break: break-word;
    		 word-wrap: break-word;
   			 white-space: initial;
    	 }
    	 td:last-child{
    	 	word-break:inherit;
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
    </style>
</head>
<body>

   <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
         
         
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Design & Drawing</h6>
                        </div>
                    </span>
                    <div class="">         
                    	<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>             
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4 l-align">
                                <div class="m-1 ">
                                   <a href="javascript:void(0);" onclick="openUploadDesignsModal();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="/pmis/Designs.xlsx" download>here</a> for the template</p>
                                </div>
                            </div>

                            <div class="col s12 m4 c-align">
                                <div class="m-1 ">
                                    <a href="<%=request.getContextPath() %>/add-design-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Design & Drawing</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportDesign();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col s12 m12">
                                <div class="row">
                                    <div class="col s12 m2 input-field">
                                     <p> <label>Contract </label></p>
                                        <select id="contract_id_fk" name="contract_id_fk" onchange="getDesignList();" class="searchable">
                                            <option value="">Select</option>
                                            <c:forEach var="obj" items="${contractList}">
	                       						  <option value="${obj.contract_id }" <c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
	                                        </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p> <label>Department </label></p>
                                        <select id="department_id_fk" name="department_id_fk" onchange="getDesignList();" class="searchable">
                                            <option value="">Select</option>
                                           		 <c:forEach var="obj" items="${departmentList}">
	                       						   <option value="${obj.department_id_fk }" <c:if test="${param.department_id_fk eq obj.department_id_fk }">selected</c:if>>${obj.department_id_fk}<c:if test="${not empty obj.department_name}"> - </c:if>${obj.department_name}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p> <label>HOD </label></p>
                                        <select id="hod" name="hod" onchange="getDesignList();" class="searchable">
                                            <option value="">Select</option>
                                           		<c:forEach var="obj" items="${hodList}">
	                       						  <option value="${obj.designation }" <c:if test="${param.hod eq obj.designation }">selected</c:if>>${obj.designation}</option>
	                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p> <label>Structure </label></p>
                                        <select id="structure_type_fk" name="structure_type_fk" onchange="getDesignList();" class="searchable">
                                            <option value="">Select</option>
                                           		 <c:forEach var="obj" items="${structureTypeList}">
	                       						   <option value="${obj.structure_type_fk }" <c:if test="${param.structure_type_fk eq obj.structure_type_fk }">selected</c:if>>${obj.structure_type_fk}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                       <p> <label>Drawing Type </label></p>
                                        <select id="drawing_type_fk" name="drawing_type_fk" onchange="getDesignList();" class="searchable">
                                            <option value="">Select </option>
                                           		  <c:forEach var="obj" items="${drawingTypeList}">
	                       						    <option value="${obj.drawing_type_fk }" <c:if test="${param.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
	                                              </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 18px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-design" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract </th>
                                            <th>Title</th>
                                            <th>Structure </th>
                                            <th>Drawing Type </th>
                                            <th>Contractor <br>Drawing No </th>
                                            <th>MRVC <br>Drawing No </th>
                                            <th>Division <br>Drawing No</th>
                                            <th>HQ Drawing No </th>                                           
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <!--  <tr>
                                           
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="design&drawing.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>
                                        </tr> -->
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
                <h6>Upload Users</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-designs" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="designFile" name="designFile" required="required">
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
                                    style="width: 100%;" onclick="closeUploadDesignsModal();">Cancel</button>
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
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 

    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
	 <form action="<%=request.getContextPath()%>/get-design" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="design_id" id="design_id"/>
    </form>
    
  <form action="<%=request.getContextPath() %>/export-design" name="exportDesignForm" id="exportDesignForm" target="_blank" method="post">	
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
        <input type="hidden" name="department_id_fk" id="exportDepartment_id_fk" />
        <input type="hidden" name="hod" id="exportHod" />
        <input type="hidden" name="structure_type_fk" id="exportStructure_type_fk" />
        <input type="hidden" name="drawing_type_fk" id="exportDrawing_type_fk" />
	</form>
	
     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	<script>
	
	function  openUploadDesignsModal() {
		$("#designFile").val('');
    	$("#upload_template").modal('open');
	}

	function  closeUploadDesignsModal() {
		$("#designFile").val('');
    	$("#upload_template").modal('close');
	}
	
	 $(document).ready(function () {
		 $('.modal').modal();
		 $('select:not(.searchable)').formSelect();
         $('.searchable').select2();
	       	var table = $('#datatable-design').DataTable({
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
	                { orderable: false, 'aTargets': ['nosort'] },
	               // { "width": "100px", "targets": [1] }, to get 100px width in title column
	            ],
	            "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
	            initComplete: function () {
	                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	            }
	        });
	    	table.state.clear(); 
			
	    	
	    	$('.close-message').delay(3000).fadeOut('slow');
	    	
	    	getDesignList();
	 });
	 
	 function clearFilter(){
     	$("#contract_id_fk").val('');
     	$("#department_id_fk").val('');
     	$("#hod").val('');
     	$("#structure_type_fk").val('');
     	$("#drawing_type_fk").val('');
     	$('.searchable').select2();
     	getDesignList();
     }

	 function getDesignList(){
	    	$(".page-loader").show();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_id_fk = $("#department_id_fk").val();
	    	var hod = $("#hod").val();
	    	var structure_type_fk = $("#structure_type_fk").val();
	    	var drawing_type_fk = $("#drawing_type_fk").val();


	     	
	     	table = $('#datatable-design').DataTable();
			 
			table.destroy();
			
			$.fn.dataTable.moment('DD-MMM-YYYY');
			table = $('#datatable-design').DataTable({
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
	                { orderable: false, 'aTargets': ['nosort'] },
	                // { "width": "100px", "targets": [1] }, to get 100px width in title column
	            ],	            
	            "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
	            initComplete: function () {
	                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	            }
	        }).rows().remove().draw();
			
			
			table.state.clear();		
		 
		 	var myParams = {contract_id_fk : contract_id_fk, department_id_fk : department_id_fk, hod : hod,structure_type_fk : structure_type_fk,drawing_type_fk : drawing_type_fk};
			$.ajax({url : "<%=request.getContextPath()%>/ajax/getDesigns",type:"POST",data:myParams,success : function(data){    				
					if(data != null && data != '' && data.length > 0){    					
		         		$.each(data,function(key,val){
		         			var design_id = "'"+val.design_id+"'";
		                    var actions = '<a href="javascript:void(0);"  onclick="getDesign('+design_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';    	                   	
		                   	var rowArray = [];    	                 
		                   	
		                	/* var workName = '';
	                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) } */
	                        
		                   	rowArray.push($.trim(val.contract_id_fk));
		                   	rowArray.push($.trim(val.drawing_title));
		                   	rowArray.push($.trim(val.structure_type_fk));
		                   	rowArray.push($.trim(val.drawing_type_fk));
		                   	rowArray.push($.trim(val.contractor_drawing_no));
		                   	rowArray.push($.trim(val.mrvc_drawing_no));
		                   	rowArray.push($.trim(val.division_drawing_no));
		                	rowArray.push($.trim(val.hq_drawing_no));
		                   	rowArray.push($.trim(actions));   	                   	
		                   	
		                    table.row.add(rowArray).draw( true );
		                    		                       
						});
		         		
		         		$(".page-loader").hide();
					}else{
						$(".page-loader").hide();
					}
					
				},error: function (jqXHR, exception) {
					$(".page-loader").hide();
		         	getErrorMessage(jqXHR, exception);
		     }});
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
	    function getDesign(design_id) {
			$("#design_id").val(design_id);
			$("#getForm").submit();
		}
	
	     function exportDesign(){
     	 var contract_id_fk  = $("#contract_id_fk ").val();
     	 var department_id_fk  = $("#department_id_fk ").val();
     	 var hod  = $("#hod ").val();
     	 var structure_type_fk  = $("#structure_type_fk ").val();
     	 var drawing_type_fk  = $("#drawing_type_fk ").val();
     	 
     	 $("#exportContract_id_fk").val(contract_id_fk);
     	 $("#exportDepartment_id_fk").val(department_id_fk);
     	 $("#exportHod").val(hod);
     	 $("#exportStructure_type_fk").val(structure_type_fk);
     	 $("#exportDrawing_type_fk").val(drawing_type_fk);
     	 $("#exportDesignForm ").submit();
  	}
    
    </script>
</body>
</html>