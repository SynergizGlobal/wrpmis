<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Source of Fund - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a {
            color: blue
        }     
        .input-field .searchable_label {
            font-size: 0.85rem;
        }  
    	 .fw-400{
    	 	width:400px !important;
    	 	max-width:400px;
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
         .row.no-mar{
         	margin-bottom:0;
         }
         .mdl-data-table tr td:last-of-type, 
         .mdl-data-table tr th:last-of-type{
         	padding-right:5px !important;
         }
         @media only screen and (min-width: 768px) and (max-width: 1024px){			
			/* .mb-md-2{
				margin-bottom:0;
			} */
			.fw-111{
	        	width:30vw;
	        	min-width:30vw;
	        }
		}
		
         /*  @media only screen and (max-width: 768px){
			.dataTables_filter label input {
			    width: 100% !important;
			}
			.mb-md-2{
				margin-bottom:2rem;
			}
		} */
		 @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
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
	        .fw-35{
	        	width:35vw !important;
	        	min-width:30vw;
	        }
	        .fw-25{
	        	width:25vw !important;
	        	min-width:30vw;
	        }
		}
		.right-btns{
			height:1px;
		}
		
		 @media only screen and (min-width: 480px) and (max-width: 839px){
		    .mdl-cell--6-col, .mdl-cell--6-col-tablet.mdl-cell--6-col-tablet{
		        width:100%;
		    }
		    .mdl-grid .dataTables_length{
		        width: 100%;
		        text-align: center;
		    }
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


	<div class="row">
		<div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Source of Fund</h6>
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
							<div class="col s12 m4 h">
								<!--  <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
							</div>

							<div class="col s12 m4 .hide-on-med-and-up">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/add-fund-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Source of Fund</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportFunds();"
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
								<h6 class="hide-on-med-and-down">Update Source of Fund</h6>
								<h6 class="hide-on-large-only">Source of Fund</h6>									
							</div>
						</span>
						<div class="row no-mar" >
							<div class="col s12 hide-on-large-only mb-md-2 center-align">
								<a href="<%=request.getContextPath()%>/add-fund-form"
								class="btn waves-effect waves-light bg-s t-c" > <strong><i
								class="fa fa-plus-circle"></i> Add Source of Fund</strong></a>
							</div>					
							<div class="col l6 s12 offset-l3 m8 offset-m2">
								<div class="row no-mar" >
									<!-- <div class="col s12 m3 input-field">
                                       <p class="searchable_label">Work</p>
                                         <select id="work_id_fk" name="work_id_fk" onchange="getFundList();" class="searchable">
                                            <option value="" >Select</option>
                                        </select>
                                    </div> -->
                                    	
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Source of Fund</p>
										<select id="source_of_funds_fk" name="source_of_funds_fk"
											onchange="addInQueSOF(this.value);getFundList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Railway</p>
										<select id="sub_category_railway_id_fk"
											name="sub_category_railway_id_fk" onchange="addInQueRailway(this.value);getFundList();"
											class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s12 m4 input-field center-align">
										<button class="btn bg-m waves-effect waves-light t-c clear-filters "
											style="margin-top: 3px;" onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-fund" class="mdl-data-table">
									<thead>
										<tr>
											<th class="fw-400">Project</th>
											<th>Source of Fund</th>
											<th>Railway</th>
											<th>Funding Date</th>
											<th>Fund Amount</th>
											<th>Ledger Account</th>
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

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="funds_id" id="funds_id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-funds" name="exportFundsForm" id="exportFundsForm" target="_blank" method="post">	
       
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="source_of_funds_fk" id="exportSource_of_funds_fk" />
         <input type="hidden" name="sub_category_railway_id_fk" id="exportSub_category_railway_id_fk" />
	</form>
    
    <script>
    var filtersMap = new Object();
    
    $(document).ready(function () {
  	   $('select:not(.searchable)').formSelect();
         $('.searchable').select2();
         
         var filters = window.localStorage.getItem("sourceOfFundFilters");
         
         if($.trim(filters) != '' && $.trim(filters) != null){
       	  var temp = filters.split('^'); 
       	  for(var i=0;i< temp.length;i++){
 	        	  if($.trim(temp[i]) != '' ){
 	        		  var temp2 = temp[i].split('=');
 		        	  if($.trim(temp2[0]) == 'source_of_funds_fk' ){
 		        		 getSOFFilterList(temp2[1]);
 		        	  }else if($.trim(temp2[0]) == 'sub_category_railway_id_fk'){
 		        		 getRailwaysFilterList(temp2[1]);
 		        	  }
 	        	  }
 	          }
           }
         
     	var table = $('#datatable-fund').DataTable({
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
                  targets: [0],
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
  	
  	getFundList();
  	 if(window.matchMedia("(max-width: 769px)").matches){
	    	$('tbody.web').removeAttr('id');
	        $('#mobView').css({'display':'block'});
	      	
	    } else{
	    	$('#webView').css({'display':'block'});
	    }
  });
  
    function clearFilter(){
    	
    	$("#work_id_fk").val("");
    	$("#source_of_funds_fk").val("");
    	$("#sub_category_railway_id_fk").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("sourceOfFundFilters",'');
    	window.location.href = "<%=request.getContextPath()%>/source-of-funds";
    	//getFundList();
    }
    
    
    function addInQueSOF(source_of_funds_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('source_of_funds_fk')) delete filtersMap[key];
   		});
    	if($.trim(source_of_funds_fk) != ''){
   	    	filtersMap["source_of_funds_fk"] = source_of_funds_fk;
    	}
    }
    
    function addInQueRailway(sub_category_railway_id_fk){
      	Object.keys(filtersMap).forEach(function (key) {
	   		if(key.match('sub_category_railway_id_fk')) delete filtersMap[key];
   	   	});
      	if($.trim(sub_category_railway_id_fk) != ''){
        	filtersMap["sub_category_railway_id_fk"] = sub_category_railway_id_fk;
      	}
    }
    
    
    function getFundList() {
		$(".page-loader-2").show();
		getSOFFilterList('');
    	getRailwaysFilterList('');
    	getWorksFilterList('');
    	
    	var work_id_fk = $("#work_id_fk").val();
    	var source_of_funds_fk = $("#source_of_funds_fk").val();
    	var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
    	
    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("sourceOfFundFilters", filters);
			});
    	
    		table = $('#datatable-fund').DataTable();
			table.destroy();
	
			$.fn.dataTable.moment('DD-MMM-YYYY');
	
			var myParams =  "source_of_funds_fk="+ source_of_funds_fk+ "&sub_category_railway_id_fk="+ sub_category_railway_id_fk;
	
			/***************************************************************************************************/
	
			$("#datatable-fund")
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
								} ,{ targets: [1,2,3,4], className: 'hideCOl'  },{ targets: [0], className: 'fw-25'  },{ targets: [5], className: 'fw-35'  }],
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
								"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-funds?"+myParams,
			       	 "aoColumns": [
	  		            { "mData": function(data,type,row){
	  		            	 var project_name = '';
	                         if ($.trim(data.project_name) != '') { project_name = ' - ' + $.trim(data.project_name) }    	
	                         if($.trim(data.project_id_fk) == ''){ return '-'; }else{ return data.project_id_fk +project_name; }
	  		            } },
	  		         	{ "mData": function(data,type,row){
			            	if($.trim(data.source_of_funds_fk) == ''){ return '-'; }else{ return data.source_of_funds_fk; }
			            } },
	  		         	{ "mData": function(data,type,row){
	  		         	 	 var railwayName = '';
	  		         	 	 if ($.trim(data.railwayName) != '') { railwayName = ' - ' + $.trim(data.railway_name) } 
	                         if($.trim(data.sub_category_railway_id_fk) == ''){ return '-'; }else{ return data.sub_category_railway_id_fk +railwayName; }
	  		            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.funding_date) == ''){ return '-'; }else{ return data.funding_date; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.fund_amount) == ''){ return '-'; }else{ return data.fund_amount; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.ledger_account) == ''){ return '-'; }else{ return data.ledger_account; }
			            } },
			         	{ "mData": function(data,type,row){
			         		var funds_id = "'"+data.funds_id+"'";
		                    var actions = '<a href="javascript:void(0);"  onclick="getFunds('+funds_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
			            	return actions;
			            } }
			            
			        ]
			    });
	  $(".page-loader-2").hide();  		     
  	
 }

    
    function getFundList1(){
    	$(".page-loader").show();
    	
    	getSOFFilterList('');
    	getRailwaysFilterList('');
    	getWorksFilterList('');
    	
    	var work_id_fk = $("#work_id_fk").val();
    	var source_of_funds_fk = $("#source_of_funds_fk").val();
    	var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
    	
    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("sourceOfFundFilters", filters);
			});
    	
    	table = $('#datatable-fund').DataTable();
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-fund').DataTable({
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
	 	var myParams = {work_id_fk : work_id_fk, source_of_funds_fk : source_of_funds_fk, sub_category_railway_id_fk : sub_category_railway_id_fk};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-funds",
	 		type:"POST",
			data:myParams, cache: false,async:false,
			success : function(data){       				
			if(data != null && data != '' && data.length > 0){    					
         		$.each(data,function(key,val){
         			var funds_id = "'"+val.funds_id+"'";
                    var actions = '<a href="javascript:void(0);"  onclick="getFunds('+funds_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
/*                     			  +'<a onclick="deleteFunds('+funds_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
 */                   	var rowArray = [];    	                 
                   	
                	var workName = '';
                    if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                    var railwayName = '';
                    if ($.trim(val.railway_name) != '') { railwayName = ' - ' + $.trim(val.railway_name) }
                    
                   	//rowArray.push($.trim(val.work_id_fk) + workName);
                   	rowArray.push($.trim(val.project_id) + " - " + val.project_name);
                   	rowArray.push($.trim(val.source_of_funds_fk));
                   	rowArray.push($.trim(val.sub_category_railway_id_fk) + railwayName);
                   	rowArray.push($.trim(val.funding_date));
                   	rowArray.push($.trim(val.fund_amount));
                   	rowArray.push($.trim(val.ledger_account));
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
    
    function getSOFFilterList(sof){
    	$(".page-loader").show();
    	var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
    	var source_of_funds_fk = $("#source_of_funds_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
        if ($.trim(source_of_funds_fk) == "") {
        	$("#source_of_funds_fk option:not(:first)").remove();
        	var myParams = { work_id_fk: work_id_fk,sub_category_railway_id_fk : sub_category_railway_id_fk,source_of_funds_fk : source_of_funds_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getSOFFilterListInFunds",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (sof == val.source_of_funds_fk)?'selected':'';
                        	$("#source_of_funds_fk").append('<option value="' + val.source_of_funds_fk + '"'+selectedFlag+'>' + $.trim(val.source_of_funds_fk) +'</option>');
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
    
    function getRailwaysFilterList(railway){
    	$(".page-loader").show();
    	var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
    	var source_of_funds_fk = $("#source_of_funds_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	if ($.trim(sub_category_railway_id_fk) == "") {
        	$("#sub_category_railway_id_fk option:not(:first)").remove();
        	var myParams = { work_id_fk: work_id_fk,sub_category_railway_id_fk : sub_category_railway_id_fk,source_of_funds_fk : source_of_funds_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getRailwaysFilterListInFunds",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var railwayName = '';
                            if ($.trim(val.railway_name) != '') { railwayName = ' - ' + $.trim(val.railway_name) }
                            var selectedFlag = (railway == val.sub_category_railway_id_fk)?'selected':'';
                        	$("#sub_category_railway_id_fk").append('<option value="' + val.sub_category_railway_id_fk + '"'+selectedFlag+'>' + $.trim(val.sub_category_railway_id_fk) + railwayName +'</option>');
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
    	
    function getWorksFilterList(work){
	 	$(".page-loader").show();
	 	var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
    	var source_of_funds_fk = $("#source_of_funds_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
        	var myParams = { work_id_fk: work_id_fk,sub_category_railway_id_fk : sub_category_railway_id_fk,source_of_funds_fk : source_of_funds_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInFunds",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
    
    function getFunds(funds_id){
    	$("#funds_id").val(funds_id);
    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-funds');
    	$('#getForm').submit();
    	
    }
    
    function exportFunds(){
      	 var work_id_fk = $("#work_id_fk").val();
         var source_of_funds_fk = $("#source_of_funds_fk").val();
      	 var sub_category_railway_id_fk = $("#sub_category_railway_id_fk").val();
      	 $("#exportWork_id_fk").val(work_id_fk);
      	 $("#exportSource_of_funds_fk").val(source_of_funds_fk);
      	 $("#exportSub_category_railway_id_fk").val(sub_category_railway_id_fk);
      	 $("#exportFundsForm").submit();
   	}
    
    function deleteFunds(funds_id){
    	$("#funds_id").val(funds_id);
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
            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-funds');
    	    	$('#getForm').submit();
           }else {
                swal("Cancelled", "Record is safe :)", "error");
            }
        });
    }
    </script>

</body>

</html>