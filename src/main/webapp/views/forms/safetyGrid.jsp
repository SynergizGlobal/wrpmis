<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Safety Incidents</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/safety.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	
	 <style>
        p a {
            color: blue;
        }
     .input-field .searchable_label{
        	font-size:0.85rem;
        } 
          td,th{
     	box-sizing:content-box !important;
     }
   		.fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
    	 .fw-110{
    	 	width:110px !important;
    	 	max-width:110px;
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
                            <h6> Safety Incidents</h6>
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
                                <!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath()%>/add-safety-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Safety Incidents</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportSafety();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                    
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 s12 input-field">
                            	<p class="searchable_label">Work</p>
                                 <select id="work_id_fk" name="work_id_fk" onchange="getSafetyList();" class="searchable">
                                     <option value="" >Select</option>
                                     
                                 </select>  
                            </div>
                            <div class="col s12 m2 input-field">
                            	<p class="searchable_label">Contract</p>
                                 <select id="contract_id_fk" name="contract_id_fk" onchange="getSafetyList();" class="searchable">
                                     <option value="" >Select</option>
                                     <%-- <c:forEach var="obj" items="${contracts }">
		                               	<option value="${obj.contract_id }" <c:if test="${param.contract_id_fk eq obj.contract_id }">selected</c:if>>${obj.contract_id }<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
		                             </c:forEach> --%>
                                 </select>                                
                            </div>
                            <div class="col s12 m2 input-field">
                            <p class="searchable_label">Department</p>
                                <select id="department_fk" name="department_fk" onchange="getSafetyList();" class="searchable">
                                     <option value="" >Select</option>
                                     <%-- <c:forEach var="obj" items="${departments }">
		                               	<option value="${obj.department }" <c:if test="${param.department_fk eq obj.department }">selected</c:if>>${obj.department_fk }<c:if test="${not empty obj.department_name}"> - </c:if> ${obj.department_name }</option>
		                             </c:forEach> --%>
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                            <p class="searchable_label">Category</p>
                                 <select id="category_fk" name="category_fk" onchange="getSafetyList();" class="searchable">
                                     <option value="" >Select</option>
                                     <%-- <c:forEach var="obj" items="${categorys }">
		                               	<option value="${obj.category_fk }" <c:if test="${param.category_fk eq obj.category_fk }">selected</c:if>>${obj.category_fk }</option>
		                             </c:forEach> --%>
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                            <p class="searchable_label">Status</p>
                                 <select id="status_fk" name="status_fk" onchange="getSafetyList();" class="searchable">
                                     <option value="" >Select</option>
                                     <%-- <c:forEach var="obj" items="${statuses }">
		                               	<option value="${obj.status_fk }" <c:if test="${param.status_fk eq obj.status_fk }">selected</c:if>>${obj.status_fk }</option>
		                             </c:forEach> --%>
                                 </select>
                            </div>
                            <div class="col s12 m2">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters black-text"
                                    style="margin-top: 20px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col m12 s12">

                                <table id="datatable-safety" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th class="fw-300">Contract</th>
                                            <th class="fw-110">Short Description </th>
                                            <th>Location </th>
                                            <th>Responsible Person <br> in MRVC</th>
                                            <th>Department </th>
                                            <th>Category </th>                                           
                                            <th>Status </th>                                           
                                            <th class="no-sort">Action</th>
                                            <!-- <th>Project ID</th> -->
<!--                                             <th>Work</th> -->
<!--                                             <th>Date </th> -->
<!--                                             <th> Reported <br> By </th> -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>                                           
                                            <td class="last-column"> <a href="safety_new.html"
                                                    class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>                                           
                                            <td class="last-column"> <a href="safety_new.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c "><i
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
	
	<form action="<%=request.getContextPath()%>/get-safety" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="safety_id" id="safety_id"/>
    </form>
  
  
	<form action="<%=request.getContextPath() %>/export-safety" name="exportSafetyForm" id="exportSafetyForm" target="_blank" method="post">	
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
        <input type="hidden" name="department_fk" id="exportDepartment_fk" />
        <input type="hidden" name="category_fk" id="exportCategory_fk" />
        <input type="hidden" name="status_fk" id="exportStatus_fk" />
	</form>


	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>	
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	<script>
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           	var table = $('#datatable-safety').DataTable({
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
            });
        	table.state.clear(); 
    		
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	getSafetyList();
        });
        
        
        function clearFilter(){
        	$("#contract_id_fk").val("");
        	$("#department_fk").val("");
        	$("#category_fk").val("");
        	$("#status_fk").val("");
        	getSafetyList();
        }
            
        function getSafetyList(){
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	
        	getContractsListFilter();
        	getDepartmentsListFilter();
        	getCategoryListFilter();
        	getStatusListFilter();
        	
         	table = $('#datatable-safety').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-safety').DataTable({
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
    	 
    		var myParams = {contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk };
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getSafetyList",type:"POST",data:myParams,success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var safety_id = "'"+val.safety_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getSafety('+safety_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
    	                   	var rowArray = [];    	                 
    	                   	
    	                	var workName = '';
                            if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                            var contract_name = '';
                            if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                            
    	                   	rowArray.push($.trim(val.safety_id));
    	                   	/* rowArray.push($.trim(val.project_id_fk)); */
    	                   	/* rowArray.push($.trim(val.work_id_fk) + workName); */
    	                   	rowArray.push($.trim(val.contract_id_fk) + contract_name);
    	                   	rowArray.push($.trim(val.title));
    	                   	/* rowArray.push($.trim(val.date)); */
    	                   	rowArray.push($.trim(val.location));
    	                   	/* rowArray.push($.trim(val.reported_by)); */
    	                   	rowArray.push($.trim(val.responsible_person));
    	                   	rowArray.push($.trim(val.department_fk));
    	                   	rowArray.push($.trim(val.category_fk));
    	                   	rowArray.push($.trim(val.status_fk));
    	                   	
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
      	
      	
        function getContractsListFilter() {
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(contract_id_fk) == "") {
                 $("#contract_id_fk option:not(:first)").remove();
                 var myParams = {contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInSafety",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + contract_short_name +'</option>');
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
      	
       
        
        function getDepartmentsListFilter() {
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(department_fk) == "") {
                 $("#department_fk option:not(:first)").remove();
                 var myParams = {contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDepartmentsListFilterInSafety",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#department_fk").append('<option value="' + val.department_fk + '">' + $.trim(val.department_name) +'</option>');
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
        
        function getCategoryListFilter() {
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(category_fk) == "") {
                 $("#category_fk option:not(:first)").remove();
                 var myParams = {contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk };
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getCategoryListFilterInSafety",
                     data: myParams, cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                           	 	$("#category_fk").append('<option value="' + val.category_fk + '">' + $.trim(val.category_fk) +'</option>');
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
        
        function getStatusListFilter() {
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(status_fk) == "") {
                 $("#status_fk option:not(:first)").remove();
                 var myParams = {contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk };
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getStatusListFilterInSafety",
                     data: myParams, cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                           	 	$("#status_fk").append('<option value="' + val.status_fk + '">' + $.trim(val.status_fk) +'</option>');
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
        
        
        function getSafety(safety_id) {
    		$("#safety_id").val(safety_id);
    		$("#getForm").submit();
    	}
        
        function exportSafety(){
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
          	 
          	$("#exportContract_id_fk").val(contract_id_fk);
          	$("#exportDepartment_fk").val(department_fk);
          	$("#exportCategory_fk").val(category_fk);
          	$("#exportStatus_fk").val(status_fk);
          	$("#exportSafetyForm").submit();
       	}
    </script>
</body>
</html>