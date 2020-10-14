<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work Contract Module Status </title>
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
            color: blue
        }
  		td {
            word-break: break-word;
            word-wrap: break-word;
            white-space: initial;
        }
        .input-field .searchable_label {
            font-size: 0.8rem;
        }
        td:last-child,
        td:last-of-type {
            white-space: inherit;
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
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Work Contract Module Status</h6>
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
                            <div class="col m4 hide-on-small-only"> </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-work-status-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Work Contract Module
                                            Status</strong></a>
                                </div>
                            </div>
                            <div class="col m4 hide-on-small-only"> </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Project</p>
                                        <select class="searchable" name="project_id_fk" id="project_id_fk" onchange="getWorkContractList();">
                                            <option value="" selected>Select Project</option>
                                            	<c:forEach var="obj" items="${projectsList}">
	                       						  <option value="${obj.project_id }" <c:if test="${param.project_id_fk eq obj.project_id }">selected</c:if>>${obj.project_id }<c:if test="${not empty obj.project_name}"> - </c:if>${obj.project_name}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Work</p>
                                       <select id="work_id_fk" name="work_id_fk" onchange="getWorkContractList();" class="searchable">
                                            <option value="" >Select Work Name</option>
	                                            <c:forEach var="obj" items="${workList}">
	                       						  <option value="${obj.work_id }" <c:if test="${param.work_id_fk eq obj.work_id }">selected</c:if>>${obj.work_id }<c:if test="${not empty obj.work_name}"> - </c:if>${obj.work_name}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Contract</p>
                                         <select id="contract_id_fk" name="contract_id_fk" onchange="getWorkContractList();" class="searchable">
                                            <option value="" >Select Contarct Id</option>
	                                            <c:forEach var="obj" items="${contractsList}">
	                       						  <option value="${obj.contract_id_fk }" <c:if test="${param.contract_id_fk eq obj.contract_id_fk }">selected</c:if>>${obj.contract_id_fk }<c:if test="${not empty obj.contract_name}"> - </c:if>${obj.contract_name}</option>
	                                             </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-workcontract" class="mdl-data-table">
                                    <thead >
                                        <tr>
                                            <th>Project</th>
                                            <th>Work</th>
                                            <th>Contract</th>
                                            <th>Date</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <!--  <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="work_contract_module.html"
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="project_id_fk" id="tempProject_id_fk" />
    	<input type="hidden" name="work_id_fk" id="tempWork_id_fk" />
    	<input type="hidden" name="contract_id_fk" id="tempContract_id_fk" />
    </form>
    
  <script>
  $(document).ready(function () {
	   $('select:not(.searchable)').formSelect();
       $('.searchable').select2();
   	var table = $('#datatable-workcontract').DataTable({
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
	
	getWorkContractList();
});

   function clearFilter(){
   	$("#project_id_fk").val("");
   	$("#work_id_fk").val("");
   	$("#contract_id_fk").val("");
   	$('.searchable').select2();
   	getWorkContractList();
   }
   
   function getWorkContractList(){
   	$(".page-loader").show();
   	var project_id_fk = $("#project_id_fk").val();
   	var work_id_fk = $("#work_id_fk").val();
   	var contract_id_fk = $("#contract_id_fk").val();
   	table = $('#datatable-workcontract').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-workcontract').DataTable({
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
           "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
           initComplete: function () {
               $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
           }
       }).rows().remove().draw();
		
		table.state.clear();		
	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/getWorkContractModule",type:"POST",data:myParams,success : function(data){    				
			if(data != null && data != '' && data.length > 0){    					
        		$.each(data,function(key,val){
        			var project_id_fk = val.project_id_fk;
        			var work_id_fk = val.work_id_fk;
        			var contract_id_fk = val.contract_id_fk;
        			var ids ="'"+ project_id_fk+"','"+work_id_fk+"','"+contract_id_fk+"'";
                   var actions = '<a href="javascript:void(0);"  onclick="getWorkStatus('+ids+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
/*                     			  +'<a onclick="deleteBudget('+budget_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
*/                   	var rowArray = [];    	                 
                  	
               	var workName = '';
                   if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
               	var projectName = '';
                if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
            	var contractName = '';
                if ($.trim(val.contract_name) != '') { contractName = ' - ' + $.trim(val.contract_name) }
                   
                 	rowArray.push($.trim(val.project_id_fk)+ projectName);
                  	rowArray.push($.trim(val.work_id_fk) + workName);
                  	rowArray.push($.trim(val.contract_id_fk)+ contractName);
                  	rowArray.push($.trim(val.month));
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

   function getWorkStatus(project_id_fk,work_id_fk,contract_id_fk){
   	$("#tempProject_id_fk").val(project_id_fk);
 	$("#tempWork_id_fk").val(work_id_fk);
 	$("#tempContract_id_fk").val(contract_id_fk);
   	
   	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-work-status');
   	$('#getForm').submit();
   }
    </script>


</body>

</html>