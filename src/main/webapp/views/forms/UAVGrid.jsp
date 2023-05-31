<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UAV - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/UAV.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 1024px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 1024px)" href="/pmis/resources/css/mobile-grid-template.css" />
    
    <style>
        p a {
            color: blue
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
        @media only screen and (max-width: 1024px){ 
			
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
			.fw-300{width:30vw !important;
        	max-width:30vw; }
			
			}
		.no-sort.sorting_asc:before,
		.no-sort.sorting_asc:after{
		    opacity:0 !important;
		    content:'' !important;
		}
	.m-n1 {
        margin: -2rem auto 0;
    }

    @media only screen and (max-width: 1024px) {
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
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update UAV</h6> -->
								<h6 class="mob-mar">UAV</h6>
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="<%=request.getContextPath()%>/add-uav"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
    								</div>
    							</div>								
							</div>
						</span>
						<div class="row no-mar" style="margin-bottom: 0;">
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
							<div class="col m8 s12 offset-m2 l6 offset-l3">
								<div class="row no-mar">
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getUAVList();getSurveyDatesFilterList('');" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Survey Date</p>
										<select id="survey_date" name="survey_date"
											onchange="addInQueSurvey_Date(this.value);getUAVList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>									
									<div class="col s12 m4 center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="margin-top: 10px; width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
							<div class="col m3 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-UAV" class="mdl-data-table">
										<thead>
											<tr>
												<th class='fw-300'>Work</th>
												<th>Survey Date</th>
												<th>Video</th>
												<th>Last Update</th>
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
    
    
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-UAV" name="exportUAVForm" id="exportUAVForm" target="_blank" method="post">	
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="survey_date" id="exportSurvey_date" />
	</form>

    <script>
    
    var filtersMap = new Object();
    
	
	var monthShortCode=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var datePickerSelectAddClass = function () {
        var self = this;
        setTimeout(function () {
            var selector = self.el;
            if (!selector) {
                selector = ".datepicker"
            }
            $(selector).siblings(".datepicker-modal")
                .find(".select-dropdown.dropdown-trigger")
                .each((index, item) => {
                    var dateDropdownID = $(item).attr("data-target");
                    var dropdownUL = $('#' + dateDropdownID);
                    dropdownUL.children("li").on("click", () => {
                        datePickerSelectAddClass();
                    });
                    dropdownUL.addClass("datepicker-dropdown-year-month")
                });
        }, 500);
    };
    
    $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
        var filters = window.localStorage.getItem("p6Filters");
          
        if($.trim(filters) != '' && $.trim(filters) != null){
    	  var temp = filters.split('^'); 
    	  for(var i=0;i< temp.length;i++){
        	  if($.trim(temp[i]) != '' ){
        		  var temp2 = temp[i].split('=');
	        	   if($.trim(temp2[0]) == 'work_id_fk'){
	        		   getWorksFilterList(temp2[1]);
	        	  }
	        	  else if($.trim(temp2[0]) == 'survey_date'){
	        		  getSurveyDatesFilterList(temp2[1]);
	        	  }	        	   
        	  }
          }
        }
     	$('.close-message').delay(3000).fadeOut('slow');
     	
     	getUAVList();
        var today = new Date();
        
        $('#survey_date').datepicker({
	    	    format:'dd-mm-yyyy',
	    	    //endDate: "today",
           // maxDate: today,
            autoClose:true,
	    	  	onOpen: datePickerSelectAddClass
	        });

        $('#survey_date_icon').click(function () {
            event.stopPropagation();
            $('#survey_date').click();
        });

       
    });   
    

 
    var today = new Date();
    
    $('#survey_date').datepicker({
  	    format:'dd-mm-yyyy',
  	    //endDate: "today",
       // maxDate: today,
        autoClose:true,
  	  	onOpen: datePickerSelectAddClass
      });

    $('#survey_date_icon').click(function () {
        event.stopPropagation();
        $('#survey_date').click();
    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#survey_date").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("UAVFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/uav";
    	//getUAVList(); 
    	var table = $('#datatable-UAV').DataTable();
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
    function addInQueSurvey_Date(survey_date)
    {
      	Object.keys(filtersMap).forEach(function (key) {
	   		if(key.match('survey_date')) delete filtersMap[key];
   	   	});
      	if($.trim(survey_date) != ''){
        	filtersMap["survey_date"] = survey_date;
      	}   	
    }
    
    var queue = 1;
    function getUAVList() {
		$(".page-loader-2").show();

		getWorksFilterList('');
		getSurveyDatesFilterList('');
     	
    	var work_id_fk = $("#work_id_fk").val();
    	var survey_date = $("#survey_date").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("UAVFilters", filters);
			});
    	   	table = $('#datatable-UAV').DataTable();
    		table.destroy();
			var i = 0;
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		var rowLen = 0;
    		var myParams =  "work_id_fk="
    				+ work_id_fk + "&survey_date="+ survey_date;
    		

    		/***************************************************************************************************/

    		$("#datatable-UAV")
    				.DataTable(
    						{
    							"bProcessing" : true,
    							"bServerSide" : true,
    							"sort" : "position",
    							"bStateSave" : false,
    							 stateSave: true,
    							 "fnStateSave": function (oSettings, oData) {
    							 	localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
    							},
    							 "fnStateLoad": function (oSettings) {
    								return JSON.parse(localStorage.getItem('MRVCDataTables'));
    							 },
    							"iDisplayLength" : 10,
    							"iData" : {
    								"start" : 52
    							},
    							"iDisplayStart" : 0,
    							"fnDrawCallback" : function() {
    							},
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
    										'<i class="fa fa-search" title="Go" >')
    								.click(function() {
    									self.search(input.val()).draw();
    								}), $clearButton = $(
    										'<i class="fa fa-close" title="Reset">')
    								.click(function() {
    									input.val('');
    									$searchButton.click();
    								})
    								$('.dataTables_filter').append(
    										'<div class="right-btns"></div>');
    								$('.dataTables_filter div').append(
    										$searchButton, $clearButton);
    								rowLen = $('#datatable-UAV tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#datatable-UAV').dataTable().api().draw(); 
    									getUAVList();
    									queue++;
    							    } 

    							}
    							,
    							columnDefs : [ {
    								"targets" : 'no-sort',
    								"orderable" : false,
    							},{targets:[2,3],
    			                       className: 'hideCOl td-align-right'},{ targets: [0], className: 'no-sort'  }
    			                       ,{ targets: [1], className: 'td-align-center'  }
    			                ],
    							"sScrollX" : "100%",
    							"sScrollXInner" : "100%",
    							"ordering":false,
    							"bScrollCollapse" : true,
    							"language" : {
    								"info" : "_START_ - _END_ of _TOTAL_",
    								paginate : {
    									next : '<i class="fa fa-angle-right"></i>', 
    									previous : '<i class="fa fa-angle-left"></i>'  
    								}
    							},
    							
    							"bDestroy" : true,
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-uav?"+myParams,
    		        "aoColumns": [
    		        	
      		            { "mData": function(data,type,row){
      		            	var work_short_name = '';
                             if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                             if($.trim(data.work_id) == ''){ return '-'; }else{ return data.work_id +work_short_name; }
      		            } },
      		         	{ "mData": function(data,type,row){
                             if($.trim(data.survey_date) == ''){ return '-'; }else{ return data.survey_date ; }
      		            } },
      		       
    		            { "mData": function(data,type,row){ 
    		            	var file='<a href="DRONE_SURVEY/'+data.video_file_name+'" target="_new">'+data.video_file_name+'</a>';
    		            	if($.trim(data.video_file_name) == ''){ return '-'; }else{ return file; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.upload_date) == ''){ return '-'; }else{ return data.upload_date; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		         		var UAV_id = "'"+data.id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getUAV('+data.id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	
    	
	  $(".page-loader-2").hide();  		     
  	
 }

    
    function getSurveyDatesFilterList(survey_date) {
    	$(".page-loader").show();
        var survey_date = $("#survey_date").val();
        var work_id_fk = $("#work_id_fk").val();
       
        	$("#survey_date option:not(:first)").remove();
        	var myParams = { survey_date : survey_date,work_id_fk: work_id_fk };
        	
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getSurveyDatesFilterList",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var survey_dateSelected = '';
                             if ($.trim(val.survey_date) != '') { survey_dateSelected = $.trim(val.survey_date) }
                             var selectedFlag = (survey_date == val.survey_date)?'selected':'';
	                         $("#survey_date").append('<option value="' + val.survey_date + '"'+selectedFlag+'>' + $.trim(val.survey_date) +'</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
            });

    }
    

    
    function getWorksFilterList(work) {
    	$(".page-loader").show();
        var survey_date = $("#survey_date").val();
        var work_id_fk = $("#work_id_fk").val();
        if ($.trim(work_id_fk) == "") {
        	$("#work_id_fk option:not(:first)").remove();
        	var myParams = { survey_date : survey_date,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInUAV",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
    

    
    function getUAV(id){
    	
    	window.location.href="/pmis/get-uav/"+id;
    }
    function deleteUAV(id){
    	$("#id").val(id);
    	showCancelMessage();
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
    
    
  	
    function showCancelMessage() {
    	swal({
            title: "Are you sure?",
            text: "You will be changing the status of the record!",
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
            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-UAV');
    	    	$('#getForm').submit();
           }else {
                swal("Cancelled", "Record is safe :)", "error");
            }
        });
    }
    
    function exportUAV(){
     var work_id_fk = $("#work_id_fk").val();
   	 var survey_date = $("#survey_date").val();
   	 $("#exportWork_id_fk").val(work_id_fk);
   	 $("#exportSurvey_date").val(survey_date);
   	 $("#exportUAVForm").submit();
	}
    
    
    </script>

</body>

</html>