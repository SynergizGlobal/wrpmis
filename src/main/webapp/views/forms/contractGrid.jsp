<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <!-- <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
<!--     <link rel="stylesheet" href="/pmis/resources/css/header-footer.css"> -->
    <!-- <link rel="stylesheet" href="/pmis/resources/css/update_page.css"> -->
    <style>
        .row.no-mar {
            margin-bottom: 0;
        }

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
    <!-- header ends  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Contract</h6>
                        </div>
                    </span>
                             
			<div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
			</div>
			</div>
                    <div class="">

                        <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                                <!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="add-contract-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportContract();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                           <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m3 input-field">
                                      <p><label>Work Name</label></p>
                                        <select id="work_id_fk" name="work_id_fk" onchange="getContractList();" class="searchable">
                                            <option value="">Select Work Name</option>
	                                            <c:forEach var="obj" items="${workList}">
	                       						  <option value="${obj.work_id }" <c:if test="${param.work_id eq obj.work_id }">selected</c:if>>${obj.work_id }<c:if test="${not empty obj.work_name}"> - </c:if>${obj.work_name}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                      <p><label>Department</label></p>
                                        <select id="department_fk" name="department_fk" onchange="getContractList();" class="searchable">
                                            <option value="">Select Department</option>
	                                             <c:forEach var="obj" items="${departmentList}">
	                       						  <option value="${obj.department_fk }" <c:if test="${param.department_fk eq obj.department_fk }">selected</c:if>>${obj.department_fk}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                    <p><label>Contractor</label></p>
                                        <select id="contractor_id_fk" name="contractor_id_fk" onchange="getContractList();" class="searchable">
                                            <option value="">Select Contractor</option>
		                                          <c:forEach var="obj" items="${contractor}">
		                       						 <option value="${obj.contractor_id_fk }" <c:if test="${param.contractor_id_fk eq obj.contractor_id_fk }">selected</c:if>>${obj.contractor_id_fk }<c:if test="${not empty obj.contractor_name}"> - </c:if>${obj.contractor_name}</option>
		                                           </c:forEach>
                                        </select>
                                    </div>
                                    
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c"
                                            style="margin-top: 30px;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">

                                <table id="datatable-contract" class="mdl-data-table">
                                    <thead >
                                        <tr>
                                            <th>Work</th>
                                            <th>Contract ID</th>
                                            <th>Contract Name </th>
                                            <th>Contractor Name </th>
                                            <th>Department </th>
                                            <th>HOD </th>
                                            <th>Dy HOD </th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
 										 <%--  <c:forEach var="obj" items="${contractList }">
                                        <tr>

                                            <td>${ obj.work_name }</td>
                                            <td >${ obj.contract_id }</td>
                                            <td >${ obj.contract_name }</td>
                                            <td>${ obj.contractor_name }</td>
                                            <td>${ obj.department_fk }</td>
                                            <td>${ obj.hod_user_id_fk }</td>
                                            <td>${ obj.dy_hod_user_id_fk }</td>
                                            <td class="last-column"> <a href="contract.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>

                                        </tr>
                                        </c:forEach>  --%>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
	<form action="<%=request.getContextPath()%>/get-contract" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="contract_id" id="contract_id"/>
    </form>
    <form action="<%=request.getContextPath() %>/export-contract" name="exportContractForm" id="exportContractForm" target="_blank" method="post">	
        <input type="hidden" name="contractor_id_fk" id="exportContractor_id_fk" />
        <input type="hidden" name="department_fk" id="exportDepartment_fk" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
	</form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	
    <script>
    $(document).ready(function () {
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
       	var table = $('#datatable-contract').DataTable({
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
            "scrollCollapse": true,
            //"sScrollY": 400,
            "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        });
    	table.state.clear(); 
		
    	
    	$('.close-message').delay(3000).fadeOut('slow');
    	
    	getContractList();
    });
    
    
    function clearFilter(){
    	$("#contractor_id_fk").val("");
    	$("#department_fk").val("");
    	$("#work_id_fk").val("");
    	$('.searchable').select2();
    	getContractList();
    }
    function getContractList(){
    	$(".page-loader").show();
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var department_fk = $("#department_fk").val();
    	var work_id_fk = $("#work_id_fk").val();


     	
     	table = $('#datatable-contract').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-contract').DataTable({
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
            "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
	 	var myParams = {contractor_id_fk : contractor_id_fk, department_fk : department_fk, work_id_fk : work_id_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getContract",type:"POST",data:myParams,success : function(data){    				
				if(data != null && data != '' && data.length > 0){    					
	         		$.each(data,function(key,val){
	         			var contract_id = "'"+val.contract_id+"'";
	                    var actions = '<a href="javascript:void(0);"  onclick="getContract('+contract_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
	                   	var rowArray = [];    	                 
	                   	
	                	var workName = '';
                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                        
	                   	rowArray.push($.trim(val.work_id_fk) + workName);
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.department_name));
	                   	rowArray.push($.trim(val.designation));
	                   	rowArray.push($.trim(val.dy_hod_user_id_fk));
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

    function getContract(contract_id) {
		$("#contract_id").val(contract_id);
		$("#getForm").submit();
	}
    
    function exportContract(){
     	 var contractor_id_fk = $("#contractor_id_fk").val();
     	 var department_fk = $("#department_fk").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 
     	 $("#exportContractor_id_fk").val(contractor_id_fk);
     	 $("#exportDepartment_fk").val(department_fk);
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportContractForm").submit();
  	}
    
    </script>

</body>

</html>