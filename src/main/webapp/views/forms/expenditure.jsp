<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expenditure</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />     
     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue
        }
    	 
    	 .fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
    	  .fw-250{
    	 	width:250px !important;
    	 	max-width:250px;
    	 }
    	  td,th{
        	box-sizing:content-box !important;
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
							<h6>Expenditure</h6>
						</div>
					</span>
					<div class="">
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
						<div class="row plr-1 center-align">
							<div class="col s12 m4">
								<div class="m-1 l-align">
									<a href="javascript:void(0);"
										onclick="openUploadExpendituresModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
									<p style="padding-top: 1rem">
										Click <a href="/pmis/Expenditure_Template.xlsx" download>here</a>
										for the template
									</p>
								</div>
							</div>

							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/add-expenditure-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Expenditure</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportExpenditure();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export Data</strong></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6>Update Expenditure</h6>
							</div>
						</span>
						<div class="row no-mar">
							<div class="col s12 m2 input-field">
								<p class="searchable_label">Work</p>
								<select id="work_id_fk" name="work_id_fk"
									onchange="addInQueWork(this.value);getExpenditureList();" class="searchable">
									<option value="">Select</option>
									<%--  <c:forEach var="obj" items="${worksList}">
                 						  <option value="${obj.work_id_fk }" <c:if test="${param.work_id_fk eq obj.work_id_fk }">selected</c:if>>${obj.work_id_fk }<c:if test="${not empty obj.work_name}"> - </c:if>${obj.work_name}</option>
                                      </c:forEach> --%>
								</select>
							</div>
							<div class="col s12 m2 input-field">
								<p class="searchable_label">Contract</p>
								<select id="contract_id_fk" name="contract_id_fk"
									onchange="addInQueContract(this.value);getExpenditureList();" class="searchable">
									<option value="">Select</option>
									<%--  <c:forEach var="obj" items="${contractsList}">
                 						  <option value="${obj.contract_id_fk }" <c:if test="${param.contract_id_fk eq obj.contract_id_fk }">selected</c:if>>${obj.contract_id_fk }<c:if test="${not empty obj.contract_name}"> - </c:if>${obj.contract_name}</option>
                                      </c:forEach> --%>
								</select>
							</div>
							<div class="col s12 m2 input-field">
								<p class="searchable_label">Ledger Account</p>
								<select id="ledger_account" name="ledger_account"
									onchange="addInQueLedger(this.value);getExpenditureList();" class="searchable">
									<option value="">Select</option>
									<%--   <c:forEach var="obj" items="${ledgerAccountsList}">
                 						  <option value="${obj.ledger_account }" <c:if test="${param.ledger_account eq obj.ledger_account }"></c:if>>${obj.ledger_account }</option>
                                      </c:forEach> --%>
								</select>
							</div>
							<div class="col s12 m2 input-field">
								<p class="searchable_label">Contractor Name</p>
								<select id="contractor_name" name="contractor_name"
									onchange="addInQueContractor(this.value);getExpenditureList();" class="searchable">
									<option value="">Select</option>
									<%--  <c:forEach var="obj" items="${contractorNamesList}">
                 						  <option value="${obj.contractor_name }" <c:if test="${param.contractor_name eq obj.contractor_name }">selected</c:if>>${obj.contractor_name }</option>
                                      </c:forEach> --%>
								</select>
							</div>
							<div class="col s12 m2 input-field">
								<p class="searchable_label">Voucher Type</p>
								<select id="voucher_type" name="voucher_type"
									onchange="addInQueVoucher(this.value);getExpenditureList();" class="searchable">
									<option value="">Select</option>
									<%-- <c:forEach var="obj" items="${voucherTypesList}">
                 						  <option value="${obj.voucher_type }" <c:if test="${param.voucher_type eq obj.voucher_type }">selected</c:if>>${obj.voucher_type }</option>
                                      </c:forEach> --%>
								</select>
							</div>
							<div class="col s12 m2 input-field">
								<button
									class="btn bg-m waves-effect waves-light t-c clear-filters"
									style="margin-top: 8px; width: 100%;" onclick="clearFilter();">Clear
									Filters</button>
							</div>
						</div>
					<!-- </div>
				</div> -->

				<div class="row">
					<div class="col m12 s12">
						<table id="datatable-expenditure" class="mdl-data-table">
							<thead>
								<tr>
									<th class="fw-300">Work</th>
									<th class="fw-250">Contract</th>
									<th>Ledger Account</th>
									<th>Contractor <br> Name
									</th>
									<th>Date</th>
									<th>Vocher Type</th>
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
                                    <td></td>
                                    <td class="last-column"> <a href="expenditure.html"
                                            class="btn waves-effect waves-light bg-m t-c "><i class="fa fa-pencil"></i>
                                        </a>
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
	<div id="upload_template" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m headbg modal-title">
                <h6>Upload Expenditures</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-expenditures" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="expenditureFile" name="expenditureFile" required="required">
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
                                    style="width: 100%;" onclick="closeUploadExpendituresModal();">Cancel</button>
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
    	<input type="hidden" name="expenditure_id" id="expenditure_id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-expenditure" name="exportExpenditureForm" id="exportExpenditureForm" target="_blank" method="post">	
       
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
         <input type="hidden" name="ledger_account" id="exportLedger_account" />
         <input type="hidden" name="contractor_name" id="exportContractor_name" />
         <input type="hidden" name="voucher_type" id="exportVoucher_type" />
	</form>
    
    <script>
    var filtersMap = new Object();
    
    function  openUploadExpendituresModal() {
		$("#expenditureFile").val('');
    	$("#upload_template").modal('open');
	}

	function  closeUploadExpendituresModal() {
		$("#expenditureFile").val('');
    	$("#upload_template").modal('close');
	}
	
    $(document).ready(function () {
   	    $('.modal').modal();
 	    $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
        
        var filters = window.localStorage.getItem("expenditureFilters");
        
        if($.trim(filters) != '' && $.trim(filters) != null){
    	  var temp = filters.split('^'); 
    	  for(var i=0;i< temp.length;i++){
        	  if($.trim(temp[i]) != '' ){
        		  var temp2 = temp[i].split('=');
	        	  if($.trim(temp2[0]) == 'ledger_account' ){
	        		  getLedgerAccountsFilterList(temp2[1]);
	        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
	        		  getWorksFilterList(temp2[1]);
	        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
	        		  getContractsFilterList(temp2[1]);
	        	  }else if($.trim(temp2[0]) == 'contractor_name'){
	        		  getContractorNamesFilterList(temp2[1]);
	        	  }else if($.trim(temp2[0]) == 'voucher_type'){
	        		  getVoucherTypesFilterList(temp2[1]);
	        	  }
        	  }
          }
        }
        
 		
  	
  		$('.close-message').delay(3000).fadeOut('slow');
  	
  		getExpenditureList();
  });
  

    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#contract_id_fk").val("");
    	$("#ledger_account").val("");
    	$("#contractor_name").val("");
    	$("#voucher_type").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("expenditureFilters",'');
    	getExpenditureList();
    }
    
    function addInQueLedger(ledger_account){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('ledger_account')) delete filtersMap[key];
   		});
    	if($.trim(ledger_account) != ''){
   	    	filtersMap["ledger_account"] = ledger_account;
    	}
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
    
    function addInQueContractor(contractor_name){
      	Object.keys(filtersMap).forEach(function (key) {
	   		if(key.match('contractor_name')) delete filtersMap[key];
   	   	});
      	if($.trim(contractor_name) != ''){
        	filtersMap["contractor_name"] = contractor_name;
      	}
    }
    
    function addInQueVoucher(voucher_type){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('voucher_type')) delete filtersMap[key];
   		});
    	if($.trim(voucher_type) != ''){
   	    	filtersMap["voucher_type"] = voucher_type;
    	}
    }
    
    function getExpenditureList() {
		$(".page-loader-2").show();

		getWorksFilterList('');
    	getContractsFilterList(''); 
    	getLedgerAccountsFilterList('');
    	getContractorNamesFilterList(''); 
    	getVoucherTypesFilterList('');
    	
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
    	
    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("expenditureFilters", filters);
			});
     	
    	table = $('#datatable-expenditure').DataTable();

		table.destroy();

		$.fn.dataTable.moment('DD-MMM-YYYY');

		var myParams = "work_id_fk=" + work_id_fk + "&contract_id_fk="
				+ contract_id_fk + "&ledger_account=" + ledger_account
				+ "&contractor_name=" + contractor_name+ "&voucher_type=" + voucher_type;

		/***************************************************************************************************/

		$("#datatable-expenditure")
				.DataTable(
						{
							"bProcessing" : true,
							"bServerSide" : true,
							"sort" : "position",
							//bStateSave variable you can use to save state on client cookies: set value "true" 
							"bStateSave" : false,
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
										.unbind(), self = this.api(), $searchButton = $(
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
							} ],
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
							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-expenditure?"+myParams,
		        "aoColumns": [
		            { "mData": function(data,type,row){
		            	var work_short_name = '';
                        if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                     	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
        			} },   				            
		            { "mData": function(data,type,row){
		            	var contract_short_name = '';
                        if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) } 
		            	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk +contract_short_name; }
		            } },
		         	{ "mData": function(data,type,row){
		            	if($.trim(data.ledger_account) == ''){ return '-'; }else{ return data.ledger_account; }
		            } },
		            { "mData": function(data,type,row){
		            	if($.trim(data.contractor_name) == ''){ return '-'; }else{ return data.contractor_name; }
		            } },
		         	{ "mData": function(data,type,row){
		            	if($.trim(data.date) == ''){ return '-'; }else{ return data.date; }
		            } },
		            { "mData": function(data,type,row){
		            	if($.trim(data.voucher_type) == ''){ return '-'; }else{ return data.voucher_type; }
		            } },
		         	{ "mData": function(data,type,row){
		         		var expenditure_id = "'"+data.expenditure_id+"'";
	                    var actions = '<a href="javascript:void(0);"  onclick="getExpenditure('+expenditure_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
		            	return actions;
		            } }
		            
		        ]
		    });
	    
	  $(".page-loader-2").hide();  		     
  	
 }
    
    function getExpenditureList1(){
    	
    	$(".page-loader-2").show();
    	getWorksFilterList('');
    	getContractsFilterList(''); 
    	getLedgerAccountsFilterList('');
    	getContractorNamesFilterList(''); 
    	getVoucherTypesFilterList('');
    	
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
    	
    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("expenditureFilters", filters);
			});
     	
    	table = $('#datatable-expenditure').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-expenditure').DataTable({
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
	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-expenditure",
	 		type:"POST",
			data:myParams, cache: false,async:true,
			success : function(data){  
			if(data != null && data != '' && data.length > 0){    					
         		$.each(data,function(key,val){
         			var expenditure_id = "'"+val.expenditure_id+"'";
                    var actions = '<a href="javascript:void(0);"  onclick="getExpenditure('+expenditure_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
                    			  //+'<a onclick="deleteExpenditure('+expenditure_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
                  	var rowArray = [];    	                 
                   	
                	var workName = '';
                    if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                    var contractName = '';
                    if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
                    
                   	rowArray.push($.trim(val.work_id_fk) + workName);
                   	rowArray.push($.trim(val.contract_id_fk) + contractName);
                   	rowArray.push($.trim(val.ledger_account));
                   	rowArray.push($.trim(val.contractor_name));
                   	rowArray.push($.trim(val.date));
                   	rowArray.push($.trim(val.voucher_type));
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
    
    function getWorksFilterList(work) {
    	$(".page-loader").show();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
        if ($.trim(work_id_fk) == "") {
        	$("#work_id_fk option:not(:first)").remove();
    	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInExpenditure",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '" '+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
    
    function getContractsFilterList(contract) {
    	$(".page-loader").show();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
        if ($.trim(contract_id_fk) == "") {
        	$("#contract_id_fk option:not(:first)").remove();
    	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInExpenditure",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var contractShortName = '';
                             if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
                             var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
	                         $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '" '+selectedFlag+'>' + $.trim(val.contract_id_fk)   + contractShortName +'</option>');
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
    
    function getLedgerAccountsFilterList(ledger) {
    	$(".page-loader").show();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
        if ($.trim(ledger_account) == "") {
        	$("#ledger_account option:not(:first)").remove();
    	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getLedgerAccountsFilterListInExpenditure",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var selectedFlag = (ledger == val.ledger_account)?'selected':'';
	                         $("#ledger_account").append('<option value="' + val.ledger_account + '" '+selectedFlag+'>' + $.trim(val.ledger_account) +'</option>');
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
    
    function getContractorNamesFilterList(contractorName) {
    	$(".page-loader").show();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
        if ($.trim(contractor_name) == "") {
        	$("#contractor_name option:not(:first)").remove();
    	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContractorNamesFilterListInExpenditure",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (contractorName == val.contractor_name)?'selected':'';
	                        $("#contractor_name").append('<option value="' + val.contractor_name + '"'+selectedFlag+'>' + $.trim(val.contractor_name) +'</option>');
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
    
    function getVoucherTypesFilterList(type) {
    	$(".page-loader").show();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
        if ($.trim(voucher_type) == "") {
        	$("#voucher_type option:not(:first)").remove();
    	 	var myParams = { work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, ledger_account : ledger_account, contractor_name : contractor_name, voucher_type : voucher_type};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getVoucherTypesFilterListInExpenditure",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	   var selectedFlag = (type == val.voucher_type)?'selected':'';
	                           $("#voucher_type").append('<option value="' + val.voucher_type + '"'+selectedFlag+'>' + $.trim(val.voucher_type) +'</option>');
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
    
    function exportExpenditure(){
      	
        var work_id_fk = $("#work_id_fk").val();
        var contract_id_fk = $("#contract_id_fk").val();
    	var ledger_account = $("#ledger_account").val();
    	var contractor_name = $("#contractor_name").val();
    	var voucher_type = $("#voucher_type").val();
      	
      	 $("#exportWork_id_fk").val(work_id_fk);
      	 $("#exportContract_id_fk").val(contract_id_fk);
    	 $("#exportLedger_account").val(ledger_account);
    	 $("#exportContractor_name").val(contractor_name);
    	 $("#exportVoucher_type").val(voucher_type);
      	 $("#exportExpenditureForm").submit();
   	}
    
    
    
    function getExpenditure(expenditure_id){
    	$("#expenditure_id").val(expenditure_id);
    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-expenditure');
    	$('#getForm').submit();
    }
    
    function deleteExpenditure(expenditure_id){
    	$("#expenditure_id").val(expenditure_id);
    	showCancelMessage();
    }
    function showCancelMessage() {
    	swal({
            title: "Are you sure?",
            text: "You will be able to change the status of record!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel it!",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function (isConfirm) {
            if (isConfirm) {
               // swal("Deleted!", "Record has been deleted", "success");
            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-expenditure');
    	    	$('#getForm').submit();
           }else {
                swal("Cancelled", "Record is safe :)", "error");
            }
        });
    }
    
    
    
    
    </script>

</body>

</html>