<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FOB</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">
	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/fob.css">
	
	 <style>
       p a {
            color: blue;
       }
       td{
	        word-break: break-word;
	    	word-wrap: break-word;
	   		white-space: initial;
     	}
     	td:last-child,td:last-of-type{
     		white-space:inherit;
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
                            <h6> FOB</h6>
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
                            <div class="col s12 m4">                               
                            </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-fob-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add FOB</strong></a>
                                </div>
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportFOB();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>                   
						<div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m6 s12 center-align">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m4 input-field">
                                       <select id="contract_id_fk" name="contract_id_fk" onchange="getFOBList();">
                                            <option value="" >Select Contract ID</option>
                                            <c:forEach var="obj" items="${contracts }">
		                                    	<option value="${obj.contract_id }" <c:if test="${param.contract_id_fk eq obj.contract_id }">selected</c:if>>${obj.contract_id }<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
		                                    </c:forEach>
                                        </select>
                                        <label>Select Contract</label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                       <select id="work_status_fk" name="work_status_fk" onchange="getFOBList();">
                                            <option value="" >Select Work Status</option>     
                                            <c:forEach var="obj" items="${generalStatusList }">
	                                            <option value="${obj }" <c:if test="${obj eq fob.work_status_fk}">selected</c:if> >${obj}</option>
	                                        </c:forEach>                                      
                                        </select>
                                        <label>Select Work Status</label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 8px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
                        </div>
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-fob" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work </th>
                                            <th>Contract </th>
                                            <th>FOB ID </th>
                                            <th>FOB Name </th>                                          
                                            <th>Work Status </th>                                           
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>                                         
                                            <td></td>
                                            <td></td>                                            
                                            <td class="last-column"> <a href="fob.jsp"
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

	<form action="<%=request.getContextPath()%>/get-fob" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="fob_id" id="fob_id"/>
    </form>
  
  
	<form action="<%=request.getContextPath() %>/export-fobs" name="exportFOBForm" id="exportFOBForm" target="_blank" method="post">	
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
        <input type="hidden" name="work_status_fk" id="exportWork_status_fk" />
	</form>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script> 
	<script src=" //cdn.datatables.net/plug-ins/1.10.12/sorting/datetime-moment.js"></script> 
	<script>
        $(document).ready(function () {
        	$('select').formSelect();
           	var table = $('#datatable-fob').DataTable({
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
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        	table.state.clear(); 
    		
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	getFOBList();
        });
        
        function clearFilter(){
        	$("#contract_id_fk").val('');
        	$("#work_status_fk").val('');
        	getFOBList();
        }
        
        function getFOBList(){
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var work_status_fk = $("#work_status_fk").val();
         	
         	table = $('#datatable-fob').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-fob').DataTable({
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
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            }).rows().remove().draw();
    		
    		
    		table.state.clear();		
    	 
    	 	var myParams = {contract_id_fk : contract_id_fk, work_status_fk : work_status_fk};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getFOBList",type:"POST",data:myParams,success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var fob_id = "'"+val.fob_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getFOB('+fob_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit">Edit</a>';    	                   	
    	                   	var rowArray = [];    	                  
    	                   	
    	                   	var workName = '';
                            if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                            var contract_name = '';
                            if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
    	                   	
    	                   	
    	                   	rowArray.push($.trim(val.work_id_fk) + workName);
    	                   	rowArray.push($.trim(val.contract_id_fk) + contract_name);
    	                   	rowArray.push($.trim(val.fob_id));
    	                   	rowArray.push($.trim(val.fob_name));
    	                   	rowArray.push($.trim(val.work_status_fk));
    	                   	
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
        
        
        function getFOB(fob_id) {
    		$("#fob_id").val(fob_id);
    		$("#getForm").submit();
    	}
        
        function exportFOB(){
          	 var contract_id_fk = $("#contract_id_fk").val();
          	 var work_status_fk = $("#work_status_fk").val();
          	 
          	 $("#exportContract_id_fk").val(contract_id_fk);
          	 $("#exportWork_status_fk").val(work_status_fk);
          	 $("#exportFOBForm").submit();
       	}


    </script>
</body>
</html>