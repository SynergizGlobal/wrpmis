<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Utility Shifting - Update Forms - PMIS</title>
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
    	 	width:200px !important;
    	 	max-width:200px;
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
          @media only screen and (max-width: 769px){ 
			
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


    @media only screen and (max-width: 767px) {
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

                <div class="card">
                <div class="card-content">
                     <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <!-- <h6 class="hide-on-med-and-down">Update Incident</h6> -->
							<h6 class="mob-mar">Utility Shifting</h6>
							<div class="col s12 m12 right-align exportButton">
							    <div class="m-n1">
							    	 <a href="<%=request.getContextPath()%>/add-utility-shifting" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add</strong></a>
<!--                                      <a href="javascript:void(0);" onclick="exportUtilityShifting();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export</strong></a> -->
                                 </div>
                            </div>
                        </div>
                    </span>
                       						<div class="row no-mar">
							<div class="col s12 m12">
								<div class="row">
									<div class="col s12 hide-on-large-only mb-md-2 center-align">
									    <a href="<%=request.getContextPath()%>/add-utility-shifting"
									        class="btn waves-effect waves-light bg-s t-c"> <strong><i
									            class="fa fa-plus-circle"></i> Add Utility Shifting</strong></a>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getUtilityShiftingList();" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Location</p>
										<select id="location_name" name="location_name" onchange="addInQueLocation(this.value);getUtilityShiftingList();" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Category</p>
										<select id="utility_category_fk" name="utility_category_fk" onchange="addInQueUtilityCategory(this.value);getUtilityShiftingList();" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Utility Type</p>
										<select id="utility_type_fk" name="utility_type_fk" onchange="addInQueUtilityType(this.value);getUtilityShiftingList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Status</p>
										<select id="shifting_status_fk" name="shifting_status_fk" onchange="addInQueUtilityStatus(this.value);getUtilityShiftingList();" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s12 m4 l2 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
										<div class="divider hide-on-med-and-up	"></div>
									</div>
								</div>
							</div>
						</div>
                        
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-us" class="mdl-data-table">
										<thead>
											<tr>
												<th class="fw-200">ID</th>
												<th>Description</th>
												<th>Utility type</th>
												<th>Category</th>
												<th>Owner</th>
												<th>Execution agency</th>
												<th>Status</th>
												<th class="no-sort">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>												
												<td></td>
												<td></td>
												<td></td>
											</tr>
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
	
	<form action="<%=request.getContextPath()%>/get-utility-shifting" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="id" id="id"/>
    </form>
  
  
	<form action="<%=request.getContextPath()%>/export-utility-shifting" name="exportUtilityShiftingForm" id="exportUtilityShiftingForm" target="_blank" method="post">	
        <input type="hidden" name="shifting_status_fk" id="exportShifting_status_fk" /> 
        <input type="hidden" name="utility_type_fk" id="exportUtility_type_fk" />
        <input type="hidden" name="utility_category_fk" id="exportUtility_category_fk" />
        <input type="hidden" name="location_name" id="exportLocation_name" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
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
        	
        	var filters = window.localStorage.getItem("USFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
        		  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorksListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'location_name'){
		        		  getLocationListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'utility_category_fk'){
		        		  getUtilityCategoryListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'utility_type_fk'){
		        		  getUtilityTypeListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'shifting_status_fk'){
		        		  getUtilityStatusListFilter(temp2[1]);
		        	  }
	        	  }
	          }
            }        	
        	getUtilityShiftingList();        	
        });
        
        
        function getWorksListFilter(work) {
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,location_name : location_name, utility_category_fk : utility_category_fk, utility_type_fk : utility_type_fk, shifting_status_fk : shifting_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilter",
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
        
        
        function getLocationListFilter(location_name) {
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(location_name) == "") {
                $("#location_name option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,location_name : location_name, utility_category_fk : utility_category_fk, utility_type_fk : utility_type_fk, shifting_status_fk : shifting_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getLocationListFilter",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var location_name = '';
                            	if ($.trim(val.location_name) != '') { location_name = ' - ' + $.trim(val.location_name) } 
                            	var selectedFlag = (location_name == val.location_name)?'selected':'';
 	                            $("#location_name").append('<option value="' + val.location_name + '" '+selectedFlag+'>' + $.trim(val.location_name)+'</option>');
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
        
        
        function getUtilityCategoryListFilter(utility_category_fk) {
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(utility_category_fk) == "") {
                $("#utility_category_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,location_name : location_name, utility_category_fk : utility_category_fk, utility_type_fk : utility_type_fk, shifting_status_fk : shifting_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getUtilityCategoryListFilter",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var utility_category_fk = '';
                            	if ($.trim(val.utility_category_fk) != '') { utility_category_fk = ' - ' + $.trim(val.utility_category_fk) } 
                            	var selectedFlag = (utility_category_fk == val.utility_category_fk)?'selected':'';
 	                            $("#utility_category_fk").append('<option value="' + val.utility_category_fk + '" '+selectedFlag+'>' + $.trim(val.utility_category_fk) +'</option>');
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
        

        function getUtilityTypeListFilter(utility_type_fk) {
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(utility_type_fk) == "") {
                $("#utility_type_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,location_name : location_name, utility_category_fk : utility_category_fk, utility_type_fk : utility_type_fk, shifting_status_fk : shifting_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getUtilityTypeListFilter",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var utility_type_fk = '';
                            	if ($.trim(val.utility_type_fk) != '') { utility_type_fk = ' - ' + $.trim(val.utility_type_fk) } 
                            	var selectedFlag = (utility_type_fk == val.utility_type_fk)?'selected':'';
 	                            $("#utility_type_fk").append('<option value="' + val.utility_type_fk + '" '+selectedFlag+'>' + $.trim(val.utility_type_fk) +'</option>');
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
        
        
        function getUtilityStatusListFilter(shifting_status_fk) {
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
         	$(".page-loader").show();

            if ($.trim(shifting_status_fk) == "") {
                $("#shifting_status_fk option:not(:first)").remove();
         		var myParams = {work_id_fk : work_id_fk,location_name : location_name, utility_category_fk : utility_category_fk, utility_type_fk : utility_type_fk, shifting_status_fk : shifting_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStatusListFilter",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var shifting_status_fk = '';
                            	if ($.trim(val.shifting_status_fk) != '') { shifting_status_fk = ' - ' + $.trim(val.shifting_status_fk) } 
                            	var selectedFlag = (shifting_status_fk == val.shifting_status_fk)?'selected':'';
 	                            $("#shifting_status_fk").append('<option value="' + val.shifting_status_fk + '" '+selectedFlag+'>' + $.trim(val.shifting_status_fk)+'</option>');
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
        
        
        
        
        function addInQueWork(work_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('work_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
	      	}
        }
        
        function addInQueLocation(location_name){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('location_name')) delete filtersMap[key];
	   		});
        	if($.trim(location_name) != ''){
       	    	filtersMap["location_name"] = location_name;
        	}
        }
        
        function addInQueUtilityType(utility_type_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('utility_type_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(utility_type_fk) != ''){
            	filtersMap["utility_type_fk"] = utility_type_fk;
	      	}
        }
        
        function addInQueUtilityCategory(utility_category_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('utility_category_fk')) delete filtersMap[key];
	   		});
        	if($.trim(utility_category_fk) != ''){
       	    	filtersMap["utility_category_fk"] = utility_category_fk;
        	}
        }
        
        function addInQueUtilityStatus(shifting_status_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('shifting_status_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(shifting_status_fk) != ''){
            	filtersMap["shifting_status_fk"] = shifting_status_fk;
	      	}
        }        
        
        function clearFilter(){
			$("#work_id_fk").val('');
			$("#location_name").val('');
			$("#utility_category_fk").val('');
			$("#utility_type_fk").val('');
			$("#shifting_status_fk").val('');
        	
        	//window.localStorage.clear();
        	window.localStorage.setItem("USFilters",'');
        	window.location.href = "<%=request.getContextPath()%>/utilityshifting";
        	var table = $('#datatable-us').DataTable();
        	table.draw( true );
        	//getUtilityShiftingList();
        }
        
        
 
           
        var queue = 1;
        function getUtilityShiftingList() {
    		$(".page-loader-2").show();

    		getWorksListFilter('');
        	getLocationListFilter('');
        	//getDepartmentsListFilter('');
        	getUtilityCategoryListFilter('');
        	getUtilityTypeListFilter('');
        	getUtilityStatusListFilter('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("USFilters", filters);
   			});
       		$('#webView').css({'display':'block'});
         	table = $('#datatable-us').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		var rowLen = 0;
    		var myParams =  "work_id_fk="
				+ work_id_fk + "&location_name="+ location_name+ "&utility_type_fk="+ utility_type_fk+ "&utility_category_fk="+ utility_category_fk+ "&shifting_status_fk="+ shifting_status_fk;

    		/***************************************************************************************************/

    		$("#datatable-us")
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
    								rowLen = $('#datatable-us tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#datatable-us').dataTable().api().draw(); 
    									getUtilityShiftingList();
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
    							},{targets: [2,3,4,5,6], className: 'hideCOl'},{ targets: [0], className: 'no-sort'  } ],
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
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getUtilityShiftingList?"+myParams,
    		        "aoColumns": [
      		         	{ "mData": function(data,type,row){
      		         		 var contractName = '';
      		         		if($.trim(data.utility_shifting_id) == ''){ return '-'; }else{ return data.utility_shifting_id; }
      		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.utility_description) == ''){ return '-'; }else{ return data.utility_description; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.utility_type_fk) == ''){ return '-'; }else{ return data.utility_type_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.utility_category_fk) == ''){ return '-'; }else{ return data.utility_category_fk; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.owner_name) == ''){ return '-'; }else{ return data.owner_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.execution_agency_fk) == ''){ return '-'; }else{ return data.execution_agency_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.shifting_status_fk) == ''){ return '-'; }else{ return data.shifting_status_fk; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		         		var id = "'"+data.id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getUtilityShifting('+id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	  $(".page-loader-2").hide();  		     
      	
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
      	
       
        
        function getUtilityShifting(id) {
    		$("#id").val(id);
    		$("#getForm").submit();
    	}
        
        function exportUtilityShifting(){
        	var work_id_fk = $("#work_id_fk").val();
        	var location_name = $("#location_name").val();
        	var utility_category_fk = $("#utility_category_fk").val();
        	var utility_type_fk = $("#utility_type_fk").val();
        	var shifting_status_fk = $("#shifting_status_fk").val();
          	 
          	$("#exportWork_id_fk").val(work_id_fk);
          	$("#exportLocation_name").val(location_name);
          	$("#exportUtility_category_fk").val(utility_category_fk);
          	$("#exportUtility_type_fk").val(utility_type_fk);
          	$("#exportShifting_status_fk").val(shifting_status_fk);
          	$("#exportUtilityShiftingForm").submit();
       	}
    </script>
</body>
</html>