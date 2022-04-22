<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Safety Incidents - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
	
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
    	 .fw-200{
    	 	width:245px !important;
    	 	max-width:245px;
    	 }
    	 .fw-150{
    	 	width:150px !important;
    	 	max-width:150px;
    	 }
    	 .fw-37vw{
    	 	width:37vw !important;
    	 	max-width:38vw;
    	 
    	 }
       .input-field>.datepicker ~ label:not(.label-icon).active {
		    background-color: transparent !important;
		}
		 .dataTables_filter label::after{
         	content:'';
         }
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
          @media only screen and (max-width: 820px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			td.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			td:not(:last-of-type),
			th:not(:last-of-type){
				width:30vw;
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
	         .mdl-data-table__cell--non-numeric.mdl-data-table__cell--non-numeric, .mdl-data-table td {
			    text-align: left;
			}
	    
		}
		.no-sort.sorting_asc:before,
.no-sort.sorting_asc:after{
    opacity:0 !important;
    content:'' !important;
}
    .m-n1 {
        margin: -2rem auto 0;
    }


    @media only screen and (max-width: 820px) {
        .mob-mar {
            text-align: left;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

   <div class="row">
        <div class="col s12 m12 ">
           <%--  <div class="card hide-on-med-and-down">
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
                                        <strong><i class="fa fa-plus-circle"></i> Add Incident</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportSafety();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>                    
                    </div>
                 </div>
              </div> --%>
                    
                <div class="card">
                <div class="card-content">
                     <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <!-- <h6 class="hide-on-med-and-down">Update Incident</h6> -->
							<h6 class="mob-mar">Incident</h6>
							<div class="col s12 m12 right-align exportButton">
							    <div class="m-n1">
							    	 <a href="<%=request.getContextPath()%>/add-safety-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add</strong></a>
                                     <a href="javascript:void(0);" onclick="exportSafety();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export</strong></a>
                                 </div>
                            </div>
                        </div>
                    </span>
                        <div class="row no-mar">                        	
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
                        	 <div class="col m12 s12 l10">
                        		<div class="row">                        			
                        			  <div class="col m4 s6 l2 input-field">
		                            	<p class="searchable_label">Work</p>
		                                 <select id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>		                                     
		                                 </select>  
                            		 </div>
		                            <div class="col s6 m4 l2 input-field">
		                            	<p class="searchable_label">Contract</p>
		                                 <select id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>
		                                     <%-- <c:forEach var="obj" items="${contracts }">
				                               	<option value="${obj.contract_id }" <c:if test="${param.contract_id_fk eq obj.contract_id }">selected</c:if>>${obj.contract_id }<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
				                             </c:forEach> --%>
		                                 </select>                                
		                            </div>
		                            <div class="col s6 m4 l2 input-field">
		                            <p class="searchable_label">HOD</p>
		                                <select id="hod_user_id_fk" name="hod_user_id_fk" onchange="addInQueHOD(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>
		                                 </select>
		                            </div>
		                            <div class="col s6 m4 l2 input-field">
		                            <p class="searchable_label">Department</p>
		                                <select id="department_fk" name="department_fk" onchange="addInQueDepartment(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>
		                                     <%-- <c:forEach var="obj" items="${departments }">
				                               	<option value="${obj.department }" <c:if test="${param.department_fk eq obj.department }">selected</c:if>>${obj.department_fk }<c:if test="${not empty obj.department_name}"> - </c:if> ${obj.department_name }</option>
				                             </c:forEach> --%>
		                                 </select>
		                            </div>
		                            <div class="col s6 m4 l2 input-field">
		                            <p class="searchable_label">Category</p>
		                                 <select id="category_fk" name="category_fk" onchange="addInQueCategory(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>
		                                     <%-- <c:forEach var="obj" items="${categorys }">
				                               	<option value="${obj.category_fk }" <c:if test="${param.category_fk eq obj.category_fk }">selected</c:if>>${obj.category_fk }</option>
				                             </c:forEach> --%>
		                                 </select>
		                            </div>
		                            <div class="col s6 m4 l2 input-field">
		                            <p class="searchable_label">Status</p>
		                                 <select id="status_fk" name="status_fk" onchange="addInQueStatus(this.value);getSafetyList();" class="searchable">
		                                     <option value="" >Select</option>
		                                     <%-- <c:forEach var="obj" items="${statuses }">
				                               	<option value="${obj.status_fk }" <c:if test="${param.status_fk eq obj.status_fk }">selected</c:if>>${obj.status_fk }</option>
				                             </c:forEach> --%>
		                                 </select>
		                            </div>
                               </div>
                            </div> 
                            <div class="col s12 m4 offset-m4 l2 center-align">
                                <button class="btn bg-s waves-effect waves-light t-c clear-filters "
                                    style="margin-top: 10px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-safety" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <!-- <th>ID</th> -->
                                            <th class="fw-300">Contract</th>
                                            <th class="fw-200">Short Description </th>
                                            <th>Location </th>
                                            <th>Person Responsible<br> in MRVC</th>
                                            <th>Department </th>
                                            <th>Category </th>                                           
                                            <th>Status </th>
                                            <th>Last Update </th>                                           
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
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="hod_user_id_fk" id="exportHod_user_id_fk" />
	</form>


	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>	
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 

	<script>
		var filtersMap = new Object();
	
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           
        	
			$('.close-message').delay(10000).fadeOut('slow');
        	
        	var filters = window.localStorage.getItem("safetyFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorksListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractsListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getDepartmentsListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'category_fk'){
		        		  getCategoryListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'status_fk'){
		        		  getStatusListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'hod_user_id_fk' ){
		        		  getHODListInSafetyFilter(temp2[1]);
		        	  } 
	        	  }
	          }
            }        	
        	getSafetyList();        	
        });
        
        
        function clearFilter(){
        	$("#work_id_fk").val("");
        	$("#contract_id_fk").val("");
        	$("#department_fk").val("");
        	$("#category_fk").val("");
        	$("#status_fk").val("");
        	$("#hod_user_id_fk").val("");
        	
        	//window.localStorage.clear();
        	window.localStorage.setItem("safetyFilters",'');
        	window.location.href = "<%=request.getContextPath()%>/safety";
        	var table = $('#datatable-safety').DataTable();
        	table.draw( true );
        	//getSafetyList();
        }
        
        
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
        
        function addInQueDepartment(department_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('department_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(department_fk) != ''){
            	filtersMap["department_fk"] = department_fk;
	      	}
        }
        
        function addInQueCategory(category_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('category_fk')) delete filtersMap[key];
	   		});
        	if($.trim(category_fk) != ''){
       	    	filtersMap["category_fk"] = category_fk;
        	}
        }
        
        function addInQueStatus(status_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('status_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(status_fk) != ''){
            	filtersMap["status_fk"] = status_fk;
	      	}
        }
        
        function addInQueHOD(hod){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('hod_user_id_fk')) delete filtersMap[key];
	   		});
        	if($.trim(hod) != ''){
       	    	filtersMap["hod_user_id_fk"] = hod;
        	}
        }
           
        var queue = 1;
        function getSafetyList() {
    		$(".page-loader-2").show();

    		getWorksListFilter('');
        	getContractsListFilter('');
        	getDepartmentsListFilter('');
        	getCategoryListFilter('');
        	getStatusListFilter('');
        	getHODListInSafetyFilter('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("safetyFilters", filters);
   			});
       		$('#webView').css({'display':'block'});
         	table = $('#datatable-safety').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		var rowLen = 0;
    		var myParams =  "work_id_fk="
    				+ work_id_fk + "&contract_id_fk="+ contract_id_fk+ "&department_fk="+ encodeURIComponent(department_fk)+ "&category_fk="+ category_fk+ "&status_fk="+ status_fk+ "&hod_user_id_fk="+ hod_user_id_fk;

    		/***************************************************************************************************/

    		$("#datatable-safety")
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
    										'<i class="fa fa-search" title="Go">')
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
    								$('.dataTables_filter').append(
    										'<div class="right-btns"></div>');
    								$('.dataTables_filter div').append(
    										$searchButton, $clearButton);
    								rowLen = $('#datatable-safety tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#datatable-safety').dataTable().api().draw(); 
    									getSafetyList();
    									queue++;
    							    }
    								/* var input = $('.dataTables_filter input').unbind(),
    								self = this.api(),
    								$searchButton = $('<i class="fa fa-search">')
    								           //.text('Go')
    								           .click(function() {			   	                    	 
    								              self.search(input.val()).draw();
    								           })			   	        
    								  $('.dataTables_filter label').append($searchButton); */
    							},
    							columnDefs : [ {
    								"targets" : 'no-sort',
    								"orderable" : false,
    							},{targets: [2,3,4,5,6,7], className: 'hideCOl'},{ targets: [0], className: 'no-sort'  } ],
    							"ordering":false,
    							"sScrollX" : "100%",
    							"sScrollXInner" : "100%",
    							"bScrollCollapse" : true,
    							"language" : {
    								"info" : "_START_ - _END_ of _TOTAL_",
    								paginate : {
    									next : '<i class="fa fa-angle-right"></i>', 
    									previous : '<i class="fa fa-angle-left"></i>'  
    								}
    							},
    							"bDestroy" : true,
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getSafetyList?"+myParams,
    		        "aoColumns": [
      		         	{ "mData": function(data,type,row){
      		         		 var contractName = '';
                             if ($.trim(data.contract_short_name) != '') { contractName = ' - ' + $.trim(data.contract_short_name) }
                             if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk + contractName ; }
      		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.title) == ''){ return '-'; }else{ return data.title; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.location) == ''){ return '-'; }else{ return data.location; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.responsible_person) == ''){ return '-'; }else{ return data.responsible_person; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.department_name) == ''){ return '-'; }else{ return data.department_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.category_fk) == ''){ return '-'; }else{ return data.category_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.status_fk) == ''){ return '-'; }else{ return data.status_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.modified_date) == ''){ return '-'; }else{ return data.modified_date; }
    		            } },    		            
    		         	{ "mData": function(data,type,row){
    		         		var safety_id = "'"+data.safety_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getSafety('+safety_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	  $(".page-loader-2").hide();  		     
      	
       }

        function getSafetyList1(){
        	$(".page-loader-2").show();
        	
        	getWorksListFilter('');
        	getContractsListFilter('');
        	getDepartmentsListFilter('');
        	getCategoryListFilter('');
        	getStatusListFilter('');
        	getHODListInSafetyFilter('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("safetyFilters", filters);
   			});
        	
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
    	 
    		var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk, category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getSafetyList",
    			type:"POST",
    			data:myParams, cache: false,async:false,
    			success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var safety_id = "'"+val.safety_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getSafety('+safety_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
    	                   	var rowArray = [];    	                 
    	                   	
    	                	var workName = '';
                            if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                            var contract_short_name = '';
                            if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                            
    	                   	/* rowArray.push($.trim(val.safety_id)); */
    	                   	/* rowArray.push($.trim(val.project_id_fk)); */
    	                   	/* rowArray.push($.trim(val.work_id_fk) + workName); */
    	                   	rowArray.push($.trim(val.contract_id_fk) + contract_short_name);
    	                   	rowArray.push($.trim(val.title));
    	                   	/* rowArray.push($.trim(val.date)); */
    	                   	rowArray.push($.trim(val.location));
    	                   	/* rowArray.push($.trim(val.reported_by)); */
    	                   	rowArray.push($.trim(val.responsible_person));
    	                   	rowArray.push($.trim(val.department_name));
    	                   	rowArray.push($.trim(val.category_fk));
    	                   	rowArray.push($.trim(val.status_fk));
    	                   	
    	                   	rowArray.push($.trim(actions));   	                   	
    	                   	
    	                    table.row.add(rowArray).draw( true );
    	                    		                       
    					});
    	         		
    	         		$(".page-loader-2").hide();
    				}else{
    					$(".page-loader-2").hide();
    				}
    				
    			},error: function (jqXHR, exception) {
    				$(".page-loader-2").hide();
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
      	
        function getWorksListFilter(work) {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInSafety",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var work_short_name = '';
                            	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
                            	var selectedFlag = (work == val.work_id_fk)?'selected':'';
 	                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '" '+selectedFlag+'>' + $.trim(val.work_id_fk) + work_short_name +'</option>');
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
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(contract_id_fk) == "") {
                $("#contract_id_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInSafety",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
                            	var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '" '+selectedFlag+'>' + $.trim(val.contract_id_fk) + contract_short_name +'</option>');
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
      	
       
        
        function getDepartmentsListFilter(department) {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(department_fk) == "") {
                $("#department_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDepartmentsListFilterInSafety",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (department == val.department_fk)?'selected':'';
                            	$("#department_fk").append('<option value="' + val.department_fk + '" '+selectedFlag+'>' + $.trim(val.department_name) +'</option>');
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
        
        function getCategoryListFilter(category) {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(category_fk) == "") {
                 $("#category_fk option:not(:first)").remove();
         		 var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getCategoryListFilterInSafety",
                     data: myParams, cache: false,async:false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                            	 var selectedFlag = (category == val.category_fk)?'selected':'';
                           	 	 $("#category_fk").append('<option value="' + val.category_fk + '" '+selectedFlag+'>' + $.trim(val.category_fk) +'</option>');
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
        
        function getStatusListFilter(status) {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(status_fk) == "") {
                 $("#status_fk option:not(:first)").remove();
         		 var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getStatusListFilterInSafety",
                     data: myParams, cache: false,async:false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                            	 var selectedFlag = (status == val.status_fk)?'selected':'';
                           	 	 $("#status_fk").append('<option value="' + val.status_fk + '" '+selectedFlag+'>' + $.trim(val.status_fk) +'</option>');
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
        
        function getHODListInSafetyFilter(hod_user_id){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var category_fk = $("#category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
           	
           	if ($.trim(hod_user_id_fk) == "") {
           		$("#hod_user_id_fk option:not(:first)").remove();
	           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : encodeURIComponent(department_fk), category_fk : category_fk, status_fk : status_fk,hod_user_id_fk : hod_user_id_fk };
	            $.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getHODListFilterInSafety",
	                   data: myParams, cache: false,async:false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                        	    var selectedFlag = (hod_user_id == val.hod_user_id_fk)?'selected':'';
	                             	if($.trim(selectedFlag) == ''){
	                             		var user_id  = '${sessionScope.USER_ID}';
	                                	selectedFlag = (user_id == val.hod_user_id_fk)?'selected':'';
	                             	}
	                        	   $("#hod_user_id_fk").append('<option value="' + $.trim(val.hod_user_id_fk) + '" '+selectedFlag+'>' + $.trim(val.designation) +'</option>');
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
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
          	 
          	$("#exportContract_id_fk").val(contract_id_fk);
          	$("#exportWork_id_fk").val(work_id_fk);
          	$("#exportDepartment_fk").val(department_fk);
          	$("#exportCategory_fk").val(category_fk);
          	$("#exportStatus_fk").val(status_fk);
          	$("#exportHod_user_id_fk").val(hod_user_id_fk);
          	$("#exportSafetyForm").submit();
       	}
    </script>
</body>
</html>