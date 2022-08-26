<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quarterly plan</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
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
         .d-none{
         	display: none;
         }
         .w10{
         	width: 10em !important;
         	white-space: break-spaces;
         }
         .w20em{
         	width: 20em !important;
         }
         .w10px{
         	width: 10px !important;
         }
         .pdla{
         	padding-left: 8px !important;
         }

      @media only screen and (max-width: 820px){ 
			
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
	        .fw-111{
	        	width:30vw !important;
	        	min-width:30vw;
	        }
		}
		#datatable-randr_mob td > .btn.t-c{
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
	.m-n1 {
   		 margin: -2rem auto 0;
	}
	.template-btn{
		text-shadow:1px 1px 1px black;
	}
	@media only screen and (max-width: 820px){
		.mob-mar{
			text-align: center;
		    margin-top: -1rem;
		    margin-bottom: 2.2rem;
		}
		.exportButton .btn{
			padding-left: 6px;
	   		padding-right: 6px;
		}
	}
	
	thead th{
		text-transform: capitalize;
	}
	 .select2-container--default .select2-selection--multiple .select2-selection__choice__display{
			white-space: pre-wrap;
			word-break: break-word;
		}
		.select2-container--default .select2-selection--multiple .select2-selection__choice{
			display: inherit;
		}
		/*.select2-selection__rendered li{
			display: block;
			float: left;
		} */
			#app_com_table {
		counter-reset: serial-number;  /* Set the serial number counter to 0 */
		}
		#app_com_table td:first-child:before {
		counter-increment: serial-number;  /* Increment the serial number counter */
		content: counter(serial-number);  /* Display the counter */
		}
		#app_com_table .datepicker-table td:first-child:before {
		    content: none !important;
		}
	@media(max-width: 575px){
		.fw-200{
			width: 120px !important;
    		max-width: 75px;
		}
		.mdl-data-table thead tr th {
		    vertical-align: middle;
		    text-align: center;
		    word-break: break-word;
		    white-space: initial;
		}
		.fw-10{
			width: 120px !important;
    		max-width: 110px;
		}
	}
	.card-content .line {
	    display: flex;
	    padding: 1px;
	    justify-content: space-between;
	    border-bottom: 1px solid #eee;
	}
	.card-content .line>.alignleft {
	    float: left;
	    width: 58.33333%;
	    width: 100%;
	    text-align: left;
	}
	.w150 {
	    width: 150% !important;
	}
	.card-content .line>.aligncenter {
	    float: left;
	    width: 3.33333%;
	    text-align: center;
	}
	.pdlr20px {
	    padding: 0 20px;
	}
	.modal{
		top: 16% !important;
	}
	.modal .modal-footer{
		text-align: center;
	}
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
                            <h6> Quarterly plan</h6>
                            <div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="add-quarterly-plan"	class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
										<a href="#" onclick="exportFortnight();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i></strong></a>
										<a href="#" onclick="openUploadFortnightModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-upload"></i> Upload</strong></a>											
    								</div>
    							</div>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m11 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Work </p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getFortnightQuarterlyPlanList();" class="searchable">
											<option value="">Select</option>

										</select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Items</p>
                                        <select id="item" class="searchable" name="item">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                	<div class="col s12 m2 input-field">
	                                    <p>Criticality</p>
	                                    <p>
	                                        <label>
	                                            <input class="with-gap" name="criticality" id="critical1" type="radio" value="Yes" onchange="getFortnightQuarterlyPlanList();" />
	                                            <span>Yes</span>
	                                        </label>
	                                        <label>
	                                            <input class="with-gap" name="criticality" id="critical2" type="radio" value="No" onchange="getFortnightQuarterlyPlanList();" />
	                                            <span>No</span>
	                                        </label>
	                                    </p>
                                	</div>
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Period<span class="required">*</span></p>
										<select id="period" name="period"
											onchange="addInQuePeriod(this.value);getFortnightQuarterlyPlanList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
                                    <div class="col s12 m1">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">

                                <div class="">
                                	<table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index">
                                    <thead>
                                        <tr>
                                            <th class="w10px">S.No. </th>
                                            <th class="w20em">Items</th>
                                            <th class="pdla">Critical (Y/N)</th>
                                            <th class="pdla">Scope of Work</th>
                                            <th class="pdla">TDC</th>
                                            <th class="no-sort w10px">Action</th>
                                        </tr>
                                    </thead>
                                </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div id="modal1" class="modal modal-fixed-footer">
<div class="modal-content">
                <h5 class="modal-header bg-m">View Quarterly Plan <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span class="material-icons">close</span></span></h5>
                <div class="row">
                 <div class="card-content">
                 	<div class="line">
	                     <p class="alignleft">Work</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Work Name</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Period</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Time</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Structure</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Structure Type</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Item</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Item Name</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Criticality</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Yes or No</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">TDC</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Date Calendar</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Scope Of Work</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Text</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Cumulative Progress</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">50%</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Fortnight</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Based On Period</p>
	                 </div>
	                 <div class="line">
	                     <p class="alignleft">Activity</p>
	                     <p class="aligncenter">:</p>
	                     <p class="alignleft pdlr20px">Text</p>
	                 </div>
                 </div>
                        
                   
                </div>

            </div>
    <div class="modal-footer">
      <a href="#!" class="btn waves-effect waves-light bg-s modal-action modal-close center-align">Close</a>
    </div>
  </div>


    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
 <form action="<%=request.getContextPath()%>/get-rr-bses" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="rrbses_id" id="rrbses_id"/>
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
	var filtersMap = new Object();

	function closeUploadUSModal() {
		$("#fortnightlyPlanFile").val('');
		$("#upload_template").modal('close');
	}		
	
	var pageNo = window.localStorage.getItem("fortnightplanPageNo");
    $(document).ready(function () {
    	$('.modal').modal();
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
	        		  }else if($.trim(temp2[0]) == 'item'){
	        			getitemFilterList(temp2[1]);
	        	  }
	        	  }
	            }
          }
		
    	 getFortnightQuarterlyPlanList();
    });

    function clearFilter(){
    	$("#work_id_fk").val('');
    	$("#period").val('');
    	$("#item").val('');
    	$(".searchable").select2();
    	window.localStorage.setItem("fortnightPlanFilters",'');
    	window.location.href="<%=request.getContextPath()%>/FortnightQuarterlyPlan"
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
    function addInQueitem(item){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('item')) delete filtersMap[key];
   		});
    	if($.trim(item) != ''){
   	    	filtersMap["item"] = item;
    	}
    }

    
    function getFortnightQuarterlyPlanList(){
    	$(".page-loader").show();
    	
    	getPeriodFilterList('');
    	getWorksFilterList('');
    	getitemFilterList('');
    	
    	var work_id_fk = $("#work_id_fk").val();
    	var period = $("#period").val();
    	var item = $("#item").val();
    	var critical = "";
    	
	 		if($("#critical1").prop('checked') )
	 		{
	 			critical="Yes";
 		}else if($("#critical2").prop('checked') )
 		{
 			critical="No";
 		}        	
    	
    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("fortnightPlanFilters", filters);
			});
	
    	table = $('#app_com_table').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#app_com_table').DataTable({
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
    	
	 	var myParams = {work_id_fk : work_id_fk, period : period, item:item,critical:critical};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getFortnightQuarterlyPlanList",
    			type:"POST",
    			data:myParams,cache: false,async:false,
    			success : function(data)
    			{    	
					if(data != null && data != '' && data.length > 0){   
						var i=0;
	         		$.each(data,function(key,val){
	         			var fortnight_quarterly_plan_id = val.fortnight_quarterly_plan_id;
	         			var cname="";
	         			
	                    var actions = '<a href="javascript:void(0);"  onclick=getFortnightPlan('+fortnight_quarterly_plan_id+'); class="btn waves-effect waves-light bg-m t-c mob-btn" title="View"><i class="fa fa-eye"></i></a>';    	                   	
	                   	var rowArray = [];    	                  
                        
	                   			rowArray.push($.trim(key+1));
        	                   	rowArray.push($.trim(val.item));
        	                   	rowArray.push($.trim(val.criticality));
        	                   	rowArray.push($.trim(val.scope_of_work_quarterly));
        	                   	rowArray.push($.trim(val.tdc_calendar));
        	                   	rowArray.push($.trim(actions));                               	
	                   	
	                    table.row.add(rowArray).draw( true );
	                    i++;
	                    		                       
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
    
	function openUploadFortnightModal() {
		$("#fortnightlyPlanFile").val('');
		$("#upload_template").modal('open');
	}        
    
    function getPeriodFilterList(period){
    	$(".page-loader").show();
	 	var work_id_fk = $("#work_id_fk").val();
	 	var period = $("#period").val(); 
	 	var item = $("#item").val();
	    if ($.trim(period) == "") {
	    	$("#period option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,item : item};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getPeriodsListFilterInQuarterlyFortnight",
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
	 	var item = $("#item").val();
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,item : item};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInQuarterlyFortnight",
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
    
    
    function getitemFilterList(item){
	 	$(".page-loader").show();
		var work_id_fk = $("#work_id_fk").val();
	 	var period = $("#period").val();
	 	var item = $("#item").val();
	    if ($.trim(item) == "") {
	    	$("#item option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,item : item,period : period};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getItemListFilterInQuarterlyFortnight",
                data: myParams, cache: false,async: false,
                success: function (data) {
                	if(data != null && data != '' && data.length > 0){  
                        $.each(data, function (i, val) {
                             if ($.trim(val.item) != '') { item = ' - ' + $.trim(val.item) }
                             var selectedFlag = (item == val.item)?'selected':'';
	                         $("#item").append('<option value="' + val.item + '"'+selectedFlag+'>'+$.trim(val.item)+'</option>');
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
    
    
    function getFortnightPlan(fortnightly_plan_id,cname,data_id) {
		$("#fortnightly_plan_id").val(fortnightly_plan_id);
		window.location.href="/pmis/add-quarterly-plan/"+fortnightly_plan_id;

	}
    
    function exportFortnight(){
    	var work_id_fk = $("#work_id_fk").val();
    	var period = $("#period").val();
    	var item = $("#item").val();
    	var critical = "";
    	
	 		if($("#critical1").prop('checked') )
	 		{
	 			critical="Yes";
 		}else if($("#critical2").prop('checked') )
 		{
 			critical="No";
 		} 
	 		
      	 $("#exportWork_id_fk").val(work_id_fk);
      	 $("#exportPeriod").val(period);
      	 $("#exportitem").val(item);
      	 $("#exportCritical").val(critical);          	 
      	 $("#exportFortnightplanForm").submit();
   	}  	
	
	
	
    </script>

</body>

</html>