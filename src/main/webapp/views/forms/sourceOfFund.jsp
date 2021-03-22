<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Source of Fund</title>
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
        .input-field .searchable_label {
            font-size: 0.85rem;
        }  
    	 .fw-400{
    	 	width:400px !important;
    	 	max-width:400px;
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
							<div class="col s12 m4">
								<!--  <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
							</div>

							<div class="col s12 m4">
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
								<h6>Update Source of Fund</h6>
							</div>
						</span>
						<div class="row no-mar" style="margin-bottom: 0;">
							<div class="col m3 hide-on-small-only"></div>
							<div class="col m6 s12">
								<div class="row" style="margin-bottom: 0;">
									<!-- <div class="col s12 m3 input-field">
                                       <p class="searchable_label">Work</p>
                                         <select id="work_id_fk" name="work_id_fk" onchange="getFundList();" class="searchable">
                                            <option value="" >Select</option>
                                        </select>
                                    </div> -->
									<div class="col s12 m4 input-field">
										<p class="searchable_label">Source of Fund</p>
										<select id="source_of_funds_fk" name="source_of_funds_fk"
											onchange="addInQueSOF(this.value);getFundList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s12 m4 input-field">
										<p class="searchable_label">Railway</p>
										<select id="sub_category_railway_id_fk"
											name="sub_category_railway_id_fk" onchange="addInQueRailway(this.value);getFundList();"
											class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s12 m4 input-field">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="margin-top: 3px; width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
							<div class="col m3 hide-on-small-only"></div>
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

										<!--    <tr>

                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="finance.html"
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
  	
  	getFundList();
  });
  
    function clearFilter(){
    	
    	$("#work_id_fk").val("");
    	$("#source_of_funds_fk").val("");
    	$("#sub_category_railway_id_fk").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("sourceOfFundFilters",'');
    	getFundList();
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
    
    function getFundList(){
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