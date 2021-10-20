<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Progress Form</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">     
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
      <style>

        .table-inside .mdl-data-table td:first-of-type{
        	text-align:left !important;
        }
        .hiddendiv.common {
            width: 99vw !important;
        }
        .dataTables_filter label {
		    color: transparent;
		    font-size:0.1px;
		}

        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }

        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }

        .input-field .searchable_label {
            font-size: .9rem;
        }

        td,
        .date-holder {
            position: relative;
        }
		td:first-of-type,
		th:first-of-type{
			max-width:5rem;
		}
        .date-holder .datepicker~button {
            top: 30%;
            right: 0;
        }

        td .datepicker~button {
            top: 26px;
        }
 		/* date picker styling reset code starts here  */
        td .datepicker {
            font-size: 0.87rem !important;
        }

        td .datepicker-table thead tr,
        td .datepicker-table thead tr:hover,
        td .datepicker-table tbody tr,
        td .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        td .datepicker-table td:first-of-type,
        td .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        td .datepicker-table th,
        td .datepicker-table td {
            padding: 0;
            border: 0;
        }
        /* date picker styling reset code ends here  */
		.w-150{
			width:150px;
			min-width:150px;
			max-width:150px;
		}

		.w-250{
			width:250px;
			min-width:250px;
			max-width:250px;
		}
        @media only screen and (max-width: 700px) {
            .w-150,.w-250{
				width:auto;
				min-width:auto;
				max-width:auto;
				word-break:break-word;
			}
        }
         .error-msg label {
            color: red !important;
        }
        .errMsg label {
       		 color: red !important;
        }
        .errMsgCheck label {
       		 color: red !important;
        }
       
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
		.actualScopesError{
			display:block;
		}
		#checkErrMsg, #actualScopesError {
		   text-align: center;
		   display: block;
		   color: red;
		   padding-top: 10px;
		   font-weight: 600;
    }
    @media only screen and (max-width: 769px) {
	    .mobile_responsible_table >tbody > tr >td:nth-child(1) >p{
	    	display:inline-block;
	    }
	     .mdl-cell--6-col, .mdl-cell--6-col-tablet.mdl-cell--6-col-tablet,
	     div.dataTables_wrapper div.dataTables_filter label {
             width: 100% !important;
         }
        /*  select all holding code starts here */
         .mobile_responsible_table.different >thead{
         	display:block;
         	background-color:#007A7A;
         	padding-bottom:0;
         }        
         .mobile_responsible_table.different >thead tr> th:not(:first-of-type){
         	display:none;        	
         }   
         .mobileCheckbox::after,
         .mobileCheckbox::before{
         	content:'' !important;
         }
         .mobileCheckbox >p::after{
         	content:'' !important;
         } 
         .mobileCheckbox> p::before{
         	content:'Select All' !important;
         } 
         .mobileCheckbox> p > label{
         	vertical-align:middle;
         	margin-left:1rem;
         } 
       /*  select all holding code ends here */       
       	.nextLine{
       		white-space:break-spaces;
       		display:inline-block;
       		padding: 0 4px;
       		width: -webkit-fill-available;
       	}
       	.h-auto{
       		height:auto !important;
       	}    
       	td:first-of-type,
		th:first-of-type{
			max-width:inherit;
		}  	       	
    }
    @media only screen and (max-width: 768px){
		.dataTables_filter input,
         div.dataTables_wrapper div.dataTables_filter label input{
             width: 96% !important;
         }
	}
	@media only screen and (max-width: 576px){
		.dataTables_filter input,
         div.dataTables_wrapper div.dataTables_filter label input{
             width: 90% !important;
         }
	}
	.no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
	
    </style>
</head>
<body>
  <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Progress Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-pmis-progrss-form" id="progressForm" name="progressForm" method="post" >
                        <div class="row" style="margin-bottom: 0;">
                            <div class="col m10 s12 offset-m1">
                                <div class="row ">                                  
                                      <div class="col s6 m4 l2 input-field offset-l1">
                                          <p class="searchable_label">Project</p>
                                          <select name="project_id_fk" id="project_id_fk" onchange="getMileStoneList();" class="searchable validate-dropdown">
                                              <option value="">Select</option>                                                    
                                          </select>
                                      </div> 
                                       <div class="col s6 m4 l2 input-field">
                                          <p class="searchable_label">Work</p>
                                          <select name="work_id_fk" id="work_id_fk" onchange="getMileStoneList();" class="searchable validate-dropdown">
                                              <option value="">Select</option>                                                    
                                          </select>
                                      </div> 
                                      <div class="col s6 m4 l2 input-field">
                                          <p class="searchable_label">Milestone</p>
                                          <select name="milestone_fk" id="milestone_fk" onchange="getMileStoneList();" class="searchable validate-dropdown">
                                              <option value="">Select</option>                                                    
                                          </select>
                                      </div>          
                                         <div class="col s6 m4 l2 input-field">
                                          <p class="searchable_label">Contract</p>
                                          <select  name="contract_id_fk" id="contract_id_fk"  onchange="getMileStoneList();" class="searchable validate-dropdown">
                                              <option value="">Select</option>                                                    
                                          </select>
                                      </div>                                 
                                      <div class="col s12 m4 l3 input-field center-align">
                                          <button class="btn bg-m waves-effect waves-light t-c clear-filters "  onclick="clearFilter();">Clear Filters</button>
                                      </div>                                    
                                </div>
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12 center-align" style="margin-top: 10px;">
                                        <a class="btn waves-effect bg-m" id="activities" onclick="updateActual()">Finish
                                            Activities</a>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
								<span id="checkBoxError" class="error-msg" style="text-align:center"></span>
								
								<span  class="errMsg" id="checkErrMsg">select at least one check box </span>
							</div>
							</div>
							<span id="actualScopesError" class="error-msg" style="color:red"></span>
                                <div class="row fixed-width" style="margin-bottom: 30px;">
                                    <div class="table-inside">
                                        <table class="mdl-data-table mobile_responsible_table different" id="datatable-table">
                                            <thead>
                                                <tr>
                                                    <th class="mobileCheckbox">
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="select-all" 
                                                                    id="select-all" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </th>
                                                    <th class="w-250">Milestone</th>
                                                    <th class="w-250">Activity</th>
                                                    <th> Planned <br> Start Date </th>
                                                    <th> Planned <br> Finish Date </th>
                                                    <th> &nbsp; Actual <br> Start Date &nbsp; </th>
                                                    <th> Actual <br> Finish Date</th>
                                                    <th>Scope</th>
                                                    <th>Completed</th>
                                                    <th class="no-sort">
                                                    	Actual 
                                                    	<i class="fa fa-info-circle tooltipped" data-position="bottom" 
                                                    	   data-tooltip="Enter Actual Quantity for the day. Do not enter cumulative value."
                                                    	   style="font-size: 1.25rem; margin-left: 10px;"></i>
                                                    </th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody id="filerList">                                            	  
                                           <%--  <c:forEach items="${mileStoneList}" var="obj" varStatus="index">
                                                <tr>
                                                    <td>
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="activity_check" class="check"
                                                                    id="check_${index.count }" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </td>
                                                    <td>${obj.milestone_name}</td>
                                                    <td>${obj.activity_description}</td>
                                                    <td>${obj.planned_start}</td>
                                                    <td>${obj.planned_finish}</td>
                                                    <td>
                                                        <div class="date-holder">
                                                            <input id="start_date${index.count }" type="text"
                                                                class="validate datepicker" placeholder="Start Date"
                                                                value="${obj.actual_start}">
                                                            <button type="button" id="start_date_icon${index.count }" ><i
                                                                    class="fa fa-calendar"></i></button>
                                                    </td>
                                    </div>
                                    <td>

                                        <div class="date-holder">
                                            <input type="text" class="validate datepicker" 
                                                placeholder="Finish Date" value="${obj.actual_finish}">
                                            <button type="button" id="finish_date_icon${index.count }" ><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                    </td>
                                    <td><span id="scope1">${obj.total_scope}
                                    <input type="hidden"  name="totalScopes"  id="totalScopes${index.count }" value="${obj.total_scope}" >
                                    <input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(mileStoneList) }" /></span></td>
                                    
                                    <td><span id="completed1">${obj.completed}
                                    <input type="hidden"  name="completedScopes"  id="completedScopes${index.count }" value="${obj.completed}" ></span></td>
                                    <td class="input-field">
                                        <input type="text"  name="actualScopes"  id="actualScopes${index.count }"  readonly >
                                        
                                    </td>
                                    </tr>  
                                    </c:forEach>--%>
                                    </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m6 s12 offset-m3">                                    
                                    <div class="row">
                                        <!-- <div class="col s12 m4">
                                                <div class="center-align m-1">
                                                    <button type="button" class="btn waves-effect waves-light bg-m"
                                                        style="width: 100%;" onclick="updateActual()">Finish
                                                        Activities</button>
                                                </div>
                                            </div> -->
                                        <div class="col s6 m6 mt-brdr center-align ">
                                            <div class=" m-1">
                                                <button type="button" id="btn"  class="btn waves-effect waves-light bg-m"
                                                    >Update</button>
                                            </div>
                                        </div>
                                        <div class="col s6 m6 mt-brdr center-align">
                                            <div class=" m-1">
                                                <button type="reset" class="btn waves-effect waves-light bg-s"
                                                    >Reset</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
<!--                         </div> -->

<!--                 </div> -->
                </form>
            </div>
        </div>
        <!-- form ends  -->
    </div>

    <!-- Page Loader starts-->
    <div class="page-loader" style="display: none;">
        <div class="preloader-wrapper big active">
            <div class="spinner-layer spinner-blue-only">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
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
    
    <!-- Page Loader ends-->
    
 <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	
    <script>
	   /*  $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
        $(document).ready(function () {
        	$(".errMsg").hide();
        	$(".errMsgCheck").hide();
            $('.searchable').select2();
           // $('#start_date,#finish_date').datepicker();
          /*   $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
            $('#finish_date_icon').click(function () {
                event.stopPropagation();
                $('#finish_date').click();
            }); */
            $('.tooltipped').tooltip();
             $('#datatable-table').DataTable({
            	//"sPaginationType": "full_numbers",
            	 "paging": false,
                columnDefs: [
                    {
                    	'targets': [0, 1, 2],
                        'searchable':false,
                        'orderable':false,
                        'className': 'mdl-data-table__cell--non-numeric',
                       
                        // targets: [0, 1, 2],
                        // className: 'mdl-data-table__cell--non-numeric',
                       
                    },
                    { "width": "10px", "targets": [6] },
                ],
                'order': [1, 'asc'],
                "ScrollX": true,
                "ordering":false,
                "scrollCollapse": true,
                "sScrollY": 400,
                
                 //paging: true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
             getMileStoneList();
        });
        function clearFilter(){
        	$(".page-loader").show();
            $("#contract_id_fk").val("");
            $("#milestone_fk").val("");
            $("#project_id_fk").val("");
            $("#work_id_fk").val("");
        	$('.searchable').select2();
        	getMileStoneList();
        }

        function getMileStoneFilterList() {
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var milestone_fk = $("#milestone_fk").val();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
    		if ($.trim(milestone_fk) == "") {
            	$("#milestone_fk option:not(:first)").remove();
         	    var myParams = {project_id_fk: project_id_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, milestone_fk : milestone_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getMileStonesFilterListInMilestone",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#milestone_fk").append('<option value="' + val.milestone_fk + '">' + $.trim(val.milestone_name)   +'</option>');
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
        
        function getContractFilterList() {
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var milestone_fk = $("#milestone_fk").val();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
    		if ($.trim(contract_id_fk) == "") {
            	$("#contract_id_fk option:not(:first)").remove();
         	    var myParams = {project_id_fk: project_id_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, milestone_fk : milestone_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInPMISStripChart",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contractShortName = '';
                            	 if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
    	                           $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' +$.trim(val.contract_id_fk)+ contractShortName   +'</option>');
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
        
        function getProjectsFilterList() {
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var milestone_fk = $("#milestone_fk").val();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
    		if ($.trim(project_id_fk) == "") {
            	$("#project_id_fk option:not(:first)").remove();
         	    var myParams = {project_id_fk: project_id_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, milestone_fk : milestone_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInPMISStripChart",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var projectName = '';
                            	 if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
    	                           $("#project_id_fk").append('<option value="' + val.project_id_fk + '">' +$.trim(val.project_id_fk)+ projectName   +'</option>');
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
        
        function getWorksFilterList() {
        	$(".page-loader").show();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var milestone_fk = $("#milestone_fk").val();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
    		if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
         	    var myParams = {project_id_fk: project_id_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, milestone_fk : milestone_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInPMISStripChart",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var workShortName = '';
                            	 if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
    	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' +$.trim(val.work_id_fk)+ workShortName   +'</option>');
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
        
       function getMileStoneList(){
       	 $(".page-loader-2").show();
       	 var html = '';
       	 var s =0;
       	 getMileStoneFilterList();
       	 getContractFilterList();
       	 getProjectsFilterList();
       	 getWorksFilterList();
       	 $("#filerList").html('');
       	 $("#select-all").prop('checked', false);
       	var project_id_fk = $("#project_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
       	 var contract_id_fk = $("#contract_id_fk").val();
       	 var milestone_fk = $("#milestone_fk").val();
       	 table = $('#datatable-table').DataTable();
		 
		 table.destroy();
		
		 $.fn.dataTable.moment('DD-MMM-YYYY');
		 table = $('#datatable-table').DataTable({
    		"bStateSave": true,
    		fixedHeader: true,
            "fnStateSave": function (oSettings, oData) {
                localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
            },
            "fnStateLoad": function (oSettings) {
                return JSON.parse(localStorage.getItem('MRVCDataTables'));
            },
            //"sPaginationType": "full_numbers",
             "paging": false,
            ccolumnDefs: [
                {
                	'targets': [0, 1, 2],
                    'searchable':false,
                    'orderable':false,
                    'className': 'mdl-data-table__cell--non-numeric',
                                       
                },              
                { "width": "10px", "targets": [6] },                
            ],
            'order': [1, 'asc'],
            // "ScrollX": true,
            "sScrollX": "100%",
            "ordering":false,
             "sScrollXInner": "100%",
             "bScrollCollapse": true,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                var input = $('.dataTables_filter input')
				.unbind(), self = this.api(), 
				$searchButton = $('<i class="fa fa-search" title="Go" id="save_post">')
				.click(function() {
					self.search(input.val()).draw();
				}), $clearButton = $('<i class="fa fa-close" title="Reset">')
				.click(function() {
					input.val('');
					$searchButton.click();
				})
				$('.dataTables_filter').append('<div class="right-btns"></div>');
				$('.dataTables_filter div').append($searchButton, $clearButton);
        	}
        }).rows().remove().draw();
		
		table.state.clear();		
  	     var myParams = {project_id_fk: project_id_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, milestone_fk : milestone_fk };
  	     $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getMileStoneFilterList",
            data: myParams, cache: false,
            success: function (data) {
  	            	
  	                if (data.length > 0) {
  	                    $.each(data, function (i, val) {
	  	                    var num = i;
	  	                  	var rowArray = []; 
	  	                  	
	  	                    var checkBox = 		        '<tr id="row'+num+'"><td><p><label><input type="checkbox" class="check" name="activity_check" id="check_'+num+'"/><span><input type="hidden" name="ids"  id="ids'+num+'"  value="' + $.trim(val.id) + '" /></span></label></p></td>';
	                    	var milestone_name = 	    '<td data-head="Milestone" class="input-field"><span class="nextLine">' + $.trim(val.milestone_name) + '</span></td>';
	                    	var activity_description =	'<td data-head="Activity" class="input-field"><span class="nextLine">' + $.trim(val.activity_description) + '</span></td>';
	                    	var planned_start =			'<td data-head="Planned Start Date" class="input-field"><span class="nextLine">' + $.trim(val.planned_start) + '</span></td>';
	                    	var planned_finish = 		'<td data-head="Planned Finish Date" class="input-field"><span class="nextLine">' + $.trim(val.planned_finish) + '</span></td>';
	                    	var actual_starts =			'<td data-head="Actual Start Date " class="input-field"> <input id="actual_starts'+num+'" name="actual_starts" type="text" class="validate datepicker" placeholder="Start Date" value="' + $.trim(val.actual_start) + '">'
                            								+'<button type="button" id="actual_starts'+num+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>';
                           	var actual_finishs = 		'<td data-head="Actual Finish Date" class="input-field"> <input id="actual_finishs'+num+'" name="actual_finishs" type="text" class="validate datepicker" placeholder="Finish Date" value="' + $.trim(val.actual_finish) + '">'
                          						  		    +'<button type="button" id="actual_finishs'+num+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>';
                          	var totalScope = 			'<td data-head="Scope" class="input-field"><span class="nextLine">' + $.trim(val.total_scope) + '</span>'
          	 											+'<input type="hidden" name="totalScopes"  id="totalScopes'+num+'"  value="' + $.trim(val.total_scope) + '" />';
          	 				var completed = 			'<td data-head="Completed" class="input-field"><span class="nextLine">' + $.trim(val.completed) + '</span>'
          	 											+'<input type="hidden" name="completedScopes" class="completed" id="completedScopes'+num+'"  value="' + $.trim(val.completed) + '" />';
          	 				var actual = 				'<td data-head="Actual" class="input-field"><input type="number" class="count" min="0" name="actualScopes" id="actualScopes'+num+'"  ><span id="actualScopesError'+num+'" name="actualScopesError" class=" actualScopesError" style="color:red"></span></td></tr>';
                   			
          	 				rowArray.push(checkBox);
                   			rowArray.push(milestone_name);
                   			rowArray.push(activity_description);
                   			rowArray.push(planned_start);
                   			rowArray.push(planned_finish);
                   			rowArray.push(actual_starts);
                   			rowArray.push(actual_finishs);
                   			rowArray.push(totalScope);
                   			rowArray.push(completed);
                   			rowArray.push(actual);
                   			//rowArray.push(icon);
  	                    			 
                    			 
                    		//$("#filerList").append(html);
                    		 table.row.add(rowArray).draw( true );
                    		 
                    		 //adding data-head attribute for all tds in a row 
                    		 table.rows().every(function(){
                    			 var tr_node=this.node();
                    			 $(tr_node).find('td:nth-child(1)').attr('data-head','Check to Select').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(2)').attr('data-head','Milestone').addClass('input-field h-auto');
                    			 $(tr_node).find('td:nth-child(3)').attr('data-head','Activity').addClass('input-field h-auto');
                    			 $(tr_node).find('td:nth-child(4)').attr('data-head','Planned Start Date').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(5)').attr('data-head','Planned Finish Date').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(6)').attr('data-head','Actual Start Date').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(7)').attr('data-head','Actual Finish Date').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(8)').attr('data-head','Scope').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(9)').attr('data-head','Completed').addClass('input-field');
                    			 $(tr_node).find('td:nth-child(10)').attr('data-head','Actual').addClass('input-field');
                    		});
                    		 
                    	 	$("#check_"+num).change(function() {
                    	 		//alert("#actualScopes"+num)
                    	 		$("#actualScopes"+num).val('');
                    	 	})
                    	 	$("#select-all").change(function() {
                    	 		if($("#check_"+num).is(':unchecked')){
                    	 			$("#actualScopes"+num).val('');
                    	 		}
                    	 	})
                    	 	//var noOfBoxes = document.getElementsByClassName("check")
                    	 	  /*   $('#datatable-table').on('click', function () {
                    	 	    	$("#check_"+num).change(function() {
                            	 		$("#actualScopes"+num).val('');
                            	 	})
                    	 	    });
                    	 	 */
                    	 	 
                    	 	/* $("#btn").on('click', function(){
                    	 		var ans = $("#actualScopes"+num).val();
                    	 		if($("#check_"+num).is(':checked') && ans == ""){
                    	 			$("#actualScopes"+num).focus();
                    	 	        $(".errMsg").show();
                    	 	        return false;
                    	 		}
                    	 	}) */
                    	 		$("#select-all").on('change', function(){
		                    	 	if( $("#select-all").prop('checked') ){
	                    	 				 $('#actualScopes'+num).prop('readonly', false);
	                    	 				$(".errMsg").hide();
	                    	 		}else{
	                    	 			 $('#actualScopes'+num).prop('readonly', true);
	                    	 			 $('#actualScopesError'+num).html("");
	                    	 		}
	                    	 	});
                    	 		document.getElementById('check_'+num).onchange = function() {
                    	 		if($("#check_"+num).prop('checked')){
                    	 			 $('#actualScopes'+num).prop('readonly', false);
                    	 		}else{
                    	 			 $('#actualScopes'+num).prop('readonly', true);
                    	 		}
                    	 	  
                    	 	};
                    	 	
                    	 	$("#activities").on('click', function(){
                    	 		var ans = $("#actualScopes"+num).val();
                    	 		if($("#check_"+num).is(':checked') && ans != ""){
                    	 			$("#actualScopes"+num).focus();
                    	 	        $(".error-msg").hide();
                    	 	        
                    	 		}
                    	 		/* if ($("#progressForm input:checkbox:checked").length == 0){
                    	 			alert('check any box ')
                    	 		} */
                    	 	})
                    	 	
                    	 	$('#actualScopes'+num).on('keyup', function(){
                    	 		var actual = parseFloat($("#totalScopes"+num).val() - $("#completedScopes"+num).val())
                    	 		
                    	 		if(actual < $('#actualScopes'+num).val() || $('#actualScopes'+num).val() < 0){
                    	 			$("#actualScopes"+num).val('');
                    	 			$('#actualScopesError'+num).html("< or = '"+actual+"'");
                    	 		}
                    	 		else{
                    	 			$('#actualScopesError'+num).html("");
                    	 			document.getElementById("actualScopesError").innerHTML = ""; 
                    	 		}
                    	 	})
                    	 	
                    	 	if( $("#check_"+num).prop('checked') ){
                    	 		$('#actualScopesError').html("");
                    	 		
                    	 	}
                    	 	/* $("#activities").on('click', function(){
                    	 		if($(".check").prop('checked') == true){
                    	 			$(".errMsgCheck").hide();
                    	 		}
                    	 		if($("#check_"+num).prop('checked') == false){
                    	 			$(".errMsgCheck").show();
                    	 		}
                    	 	}) */
                    	 	$(function(){
	                    	 	$("#btn").on('click', function(){
	                    	 		
	                   	 			 checked = $("input[type=checkbox]:checked").length;
	                   	              if(!checked) {
	                   	            	  document.getElementById("actualScopesError").innerHTML = "select atleast one checkbox"; 
	                   	            	
	                   	             }else{
	                   	            	 var v =$('#actualScopes'+num).val();
	                   	            	
	                   	            	  var field = $(this).parent().children('.count');
	                   	            	  var count = 0;

	                   	            	  $('.count').each(function() {
	                   	            	    if ($(this).val()) {
	                   	            	      count++;
	                   	            	    }
	                   	            	  });

		                   	           
	                        	 		 if(typeof v != 'undefined' && v != ""){ 
	                        	 			  
	                        	 			 if(count == checked){
	 	                        	 			updateProgress();
	                        	 			 }
	                        	 		 }else{
	                        	 			document.getElementById("actualScopesError").innerHTML = "Click on finish activities"; 
	                        	 			var elmnt = document.getElementById("actualScopesError");
	                        	 			elmnt.scrollIntoView();
	                        	 		 }
	                   	             }
	                    	 	})
	                    	 	$("#check_"+num).on('change', function(){
                    	 			if($("#check_"+num).is(':checked')){
                    	 			  document.getElementById("actualScopesError").innerHTML = ""; 
                    	 			  $(".errMsg").hide();
	                    	 	    }
	                    	   })
                    	  })
  	                  });
  	               }
  	               $(".page-loader-2").hide();
  	            }
  	        });
       } 
       
        $('#datatable-table').on('click', function () {
        $("input[type='checkbox'].check").change(function(){
   		    var a = $("input[type='checkbox'].check");
   		    if(a.length == a.filter(":checked").length){
   		    	$("#select-all").prop('checked', true);
   		    }
   		    else {
  		       	    $("#select-all").prop('checked', false);
  		   		 }
   			});
        });
        
   	 	 $('#select-all').on('click', function(){
   	 	   var rows = table.rows({ 'search': 'applied' }).nodes();
   	 	   $('input[type="checkbox"]', rows).prop('checked', this.checked);
   	 	}); 
        
    	var checkedOrNot = false;
        // update actual function for single value with ids
       function updateActual(){
          $('input[name="activity_check"]').each(function(){
             if($(this).prop('checked')){ 
            	 checkedOrNot = true;
                 let no = $(this).attr('id').split("_")[1];
                 //alert($('#totalScopes'+no).val())
                 var scope = $('#totalScopes'+no).val();
                 var completed = $('#completedScopes'+no).val();
                 
                 if($.trim(scope) != '' && $.trim(completed) != '' ){
                	 let remaining = parseFloat(scope)- parseFloat(completed);
                     $('#actualScopes'+no).val(remaining);
                 }
                 
             }
          
         }) 
         if(checkedOrNot == false){
        	 $('#checkErrMsg').show();
         }
     } 
    	 	
    
        // select or deselect all checkboxes 
        $('#select-all').change(function () {
            var _this = this;
            $('input[name="activity_check"]').each(function () {
                if ($(_this).is(':checked')) {
                    $(this).prop('checked', true);
                } else {
                    $(this).prop('checked', false);
                }
            });
        });

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
        
    
        //update button functionality
        function updateProgress(){
             
              	if(validator.form()){ // validation perform
         	        	$(".page-loader").show();	    		
         	   			document.getElementById("progressForm").submit();	
              	}
              
        }
        
        
        var validator = $('#progressForm').validate({
       	 ignore: ":hidden:not(.validate-dropdown)",
       	 rules: {
       		   "contract_id_fk": {
   		 		required: false
   		 	  },"actualScopes": {
 		 		 required: false,
 				 min:0
		 	  }
       	 },
               messages: {
                    "contract_id_fk": {
   		 			required: 'Select contract'
   		 	  	 },"actualScopes": {
   		 			required: 'click on finish activities'
   		 	  	 }
       	 },errorPlacement:function(error, element){
     		 	        if (element.attr("id") == "contract_id_fk" ){
	   			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
	   			 			 error.appendTo('#contract_id_fkError');
	   			 	    }else if (element.attr("name") == "actualScopes" ){
	   			 	    	 document.getElementById("errMsg").innerHTML="";
	   			 			 error.appendTo('#errMsg');
		   			 	    }
       	 },submitHandler:function(form){
   		    	//form.submit();
   		    }
        });
        
        $('select').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
        $('input').click(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
    </script>

</body>
</html>