<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FortnightPlan - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
	 <style>
     	.fw-400{
     		max-width:400px;
     		width:400px;
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
         .mt-md-18{
         	margin-top:18px;
         }
          .fw-15vw{
        	width:50px !important;
        	min-width:50px;
	      }
	     .fw-12vw{
	        	width:15vw !important;
	        	min-width:15vw !important;
	      }
	     .fw-10vw{
        		width:8vw !important;
	        	min-width:8vw !important;
	      } 
         @media only screen and (max-width: 769px){ 
		.mdl-data-table thead tr th{
			text-align:left !important;
		}		
		.dataTables_scrollBody tbody tr td:last-of-type,
		.no-sort{
			padding:3px !important;
			max-width: 35px;
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
        	width:30vw;
        	min-width:30vw;
        }
        .fw-12vw {
	  	     width:15vw !important;
        	min-width:15vw !important;
		}
       .mdl-data-table__cell--non-numeric.mdl-data-table__cell--non-numeric {
		    text-align: center;
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

    .template-btn {
        text-shadow: 1px 1px 1px black;
    }

    @media only screen and (max-width: 767px) {
        .mob-mar {
            text-align: center;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    @media(max-width: 820px){
    	.m-d-none{display:none;}
    	.mb2em{margin-bottom: 2em;}
    }
    
.dataTables_scrollBody
{
 overflow-x:hidden !important;
 overflow-y:auto !important;
 width:100%;
}
    
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
		<c:if test="${(sessionScope.USER_TYPE eq 'DyHOD') or (sessionScope.USER_TYPE eq 'HOD') or (sessionScope.USER_ROLE_CODE eq 'IT')}">
		</c:if>
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Budget</h6> -->
								<h6 class="mob-mar">Fortnightly Plan</h6>
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="<%=request.getContextPath()%>/add-fortnightly-plan"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
										<a href="javascript:void(0);" onclick="exportBudget();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
    								</div>
    							</div>								
							</div>
						</span>
						
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>

						<div class="row no-mar">
							<div class="col s12 l8 offset-l2 m9 offset-m2">
								<div class="row no-mar">
									
									<div class="col s12 m2 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getFortnightlyPlanList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Category</p>
										<select id="category" name="category" onchange="addInQueCategory(this.value);getFortnightlyPlanList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
                                	<div class="col s12 m2 input-field">
	                                    <p>Critical</p>
	                                    <p>
	                                        <label>
	                                            <input class="with-gap" name="critical" id="critical" type="radio" value="Yes" onchange="getFortnightlyPlanList();" />
	                                            <span>Yes</span>
	                                        </label>
	                                        <label>
	                                            <input class="with-gap" name="critical" id="critical" type="radio" value="No" onchange="getFortnightlyPlanList();" />
	                                            <span>No</span>
	                                        </label>
	                                    </p>
                                	</div>									
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Period</p>
										<select id="period" name="period"
											onchange="addInQuePeriod(this.value);getFortnightlyPlanList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s12 m2 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
									</div>									
								</div>

								</div>
							</div>
						</div>
						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-fortnightplan" class="mdl-data-table">
										<thead>
											<tr>
												<th class="w10px">S.No</th>
												<th class="pdla">Category</th>
												<th class="w20em">Contract</th>
												<th class="pdla">Structure ID</th>
												<th class="pdla">Critical Item</th>
												<th class="pdla">Cum Planned<br> Last Fortnight</th>
												<th class="pdla">Cum Actual<br> Last Fortnight</th>
												<th class="pdla">Plan for<br> Current Fortnight</th>
												<th class="pdla">Actual<br> progress</th>
												<th class="no-sort w10px">Update</th>
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

	<form action="<%=request.getContextPath()%>/get-FortnightPlan" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="fortnightly_plan_id" id="fortnightly_plan_id"/>
    </form>
  
  
	<form action="<%=request.getContextPath() %>/export-fortnightplans" name="exportFortnightplanForm" id="exportFortnightplanForm" target="_blank" method="post">	
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="period" id="exportperiod" />
         <input type="hidden" name="category" id="exportcategory" />
	</form>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 

	<script>
	
		var filtersMap = new Object();
		var pageNo = window.localStorage.getItem("fortnightplanPageNo");
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("fortnightPlanFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'period' ){
  		        		getPeriodFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
  		        		getWorksFilterList(temp2[1]);
  	        		  }else if($.trim(temp2[0]) == 'category'){
  	        			getCategoryFilterList(temp2[1]);
		        	  }
  	        	  }
  	            }
              }
    		
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	 getFortnightlyPlanList();
        	 if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
             var oTable = $('#datatable-fortnightplan').dataTable();
             oTable.fnPageChange( pageNo );
        });

        function clearFilter(){
        	$("#work_id_fk").val('');
        	$("#period").val('');
        	$("#category").val('');
        	$(".searchable").select2();
        	window.localStorage.setItem("fortnightPlanFilters",'');
        	window.location.href="<%=request.getContextPath()%>/FortnightPlan"
        	//getFortnightlyPlanList();
        	var table = $('#datatable-fortnightplan').DataTable();
        	table.draw( true );
        	
        }
        
        function addInQueWork(work_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('work_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
	      	}
        }
        
        function addInQuePeriod(period){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('period')) delete filtersMap[key];
	   		});
        	if($.trim(period) != ''){
       	    	filtersMap["period"] = period;
        	}
        }
        function addInQueCategory(category){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('category')) delete filtersMap[key];
	   		});
        	if($.trim(category) != ''){
       	    	filtersMap["category"] = category;
        	}
        }

        
        function getFortnightlyPlanList(){
        	$(".page-loader").show();
        	
        	getPeriodFilterList('');
        	getWorksFilterList('');
        	getCategoryFilterList('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var period = $("#period").val();
        	var category = $("#category").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("fortnightPlanFilters", filters);
   			});
       		table = $('#datatable-fortnightplan').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-fortnightplan').DataTable({
         		"bStateSave": true,  
         		fixedHeader: true,
               
             	//Default: Page display length
 				"iDisplayLength" : 10,
 				"iData" : {
 					"start" : 52
 				},"iDisplayStart" : 0,
 				"drawCallback" : function() {
 					var info = table.page.info();
 					window.localStorage.setItem("fortnightplanPageNo", info.page);
 				},
                columnDefs: [ 
                	{targets: [2,3], className: 'hideCOl'},
                    { targets: [ 1,2,4], className: 'fw-12vw'  },
                    { targets: [ 5,6,7], className: 'fw-10vw'  },
                    { targets: [0,1], className: 'fw-111'  },
                    { "width": "20px", "targets": [0] },
                    { orderable: false, 'aTargets': ['no-sort'] } 
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                 "ordering":false,
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
							}), self = this.api(), $searchButton = $('<i class="fa fa-search" title="Go" >')
  					.click(function() {
  						self.search(input.val()).draw();
  					}), 
  					$clearButton = $('<i class="fa fa-close" title="Reset">')
  					.click(function() {
  						input.val('');
  						$searchButton.click();
  					})
  					$('.dataTables_filter').append( '<div class="right-btns"></div>');
  					$('.dataTables_filter div').append( $searchButton, $clearButton); 					
  				}
            }).rows().remove().draw();
    		table.state.clear();		
    	 
    	 	var myParams = {work_id_fk : work_id_fk, period : period, category:category,critical:critical};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getFortnightPlanList",
	    			type:"POST",
	    			data:myParams,cache: false,async:false,
	    			success : function(data)
	    			{    	
    					if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var fortnightly_plan_id = val.fortnightly_plan_id;
    	         			var cname="";
    	         			if ($.trim(val.contract_short_name) != '') { cname =  "Yes" } 
    	         			
    	                    var actions = '<a href="javascript:void(0);"  onclick=getFortnightPlan('+fortnightly_plan_id+',"'+cname+'"); class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
    	                   	var rowArray = [];    	                  
    	                   	var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) } 
    	                   	
                            rowArray.push($.trim(key+1));
    	                   	rowArray.push($.trim(val.category));
    	                   	rowArray.push($.trim(val.contract_short_name));
    	                   	rowArray.push($.trim(val.structure));
    	                   	rowArray.push($.trim(val.structure_type_fk));
    	                   	rowArray.push($.trim(val.cum_planned_last_st));
    	                   	rowArray.push($.trim(val.cum_actual_last_st));
    	                   	rowArray.push($.trim(val.planned_current_st));
    	                   	rowArray.push($.trim(val.planned_current_st));
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
        
        function getPeriodFilterList(period){
        	$(".page-loader").show();
    	 	var work_id_fk = $("#work_id_fk").val();
    	 	var period = $("#period").val(); 
    	 	var category = $("#category").val();
    	    if ($.trim(period) == "") {
    	    	$("#period option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,period : period,category : category};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getPeriodsListFilterInFortnight",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                       if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                            		var selectedFlag = (period == val.period)?'selected':'';
    	                           $("#period").append('<option value="' + val.period + '"'+selectedFlag+'>' + $.trim(val.period) + '</option>');
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
        
        function getWorksFilterList(work_id){
    	 	$(".page-loader").show();
    	 	var period = $("#period").val();
    	 	var work_id_fk = $("#work_id_fk").val();
    	 	var category = $("#category").val();
    	    if ($.trim(work_id_fk) == "") {
    	    	$("#work_id_fk option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,period : period,category : category};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInFortnight",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                    	if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                            	 var work_short_name = '';
                                 if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) }
                                 var selectedFlag = (work_id == val.work_id_fk)?'selected':'';
    	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + work_short_name + '</option>');
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
        
        
        function getCategoryFilterList(category){
    	 	$(".page-loader").show();
    		var work_id_fk = $("#work_id_fk").val();
    	 	var period = $("#period").val();
    	 	var category = $("#category").val();
    	    if ($.trim(category) == "") {
    	    	$("#category option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,category : category,period : period};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getCategoryListFilterInFortnight",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                    	if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                                 if ($.trim(val.category) != '') { category = ' - ' + $.trim(val.category) }
                                 var selectedFlag = (category == val.category)?'selected':'';
    	                         $("#category").append('<option value="' + val.category + '"'+selectedFlag+'>'+$.trim(val.category)+'</option>');
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
        
        
        function getFortnightPlan(fortnightly_plan_id,cname) {
    		$("#fortnightly_plan_id").val(fortnightly_plan_id);
    			if(cname=="")
    			{
    				window.location.href="/pmis/updateFortnighlytPlanManual/"+fortnightly_plan_id;
    			}
    			else
    			{
    				window.location.href="/pmis/get-FortnightPlan/"+fortnightly_plan_id;
    			}
    	}

    </script>
</body>
</html>