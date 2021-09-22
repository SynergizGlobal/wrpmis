<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
    
    <!-- <link rel="stylesheet" href="/pmis/resources/css/work.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <style>
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
          .input-field .searchable_label{
      		font-size:0.85rem;
        } 
        .fw-15vw{
        	width:12vw;
        	min-width:12vw;
        }
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
        .fw-111{
        	width:30vw;
        	min-width:30vw;
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
     </style>
</head>
<body>
    <!-- header  starts-->
<%--  <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->

	<div class="row">
		<div class="col s12 m12  hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Work</h6>
						</div>
					</span>

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

					<div class="">
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
									<a href="add-work-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Work</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportWork();"
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
								<h6 class="hide-on-med-and-down">Update Work</h6>
								<h6 class="hide-on-large-only">Work</h6>
							</div>
						</span>
						<div class="row no-mar" >
						<div class="col s12 hide-on-large-only mb-md-2 center-align">
						    <a href="add-work-form"
						    class="btn waves-effect waves-light bg-s t-c"> <strong><i
						        class="fa fa-plus-circle"></i> Add Work</strong></a>
						</div>
							<div class="col m12 s12 ">
								<div class="row" style="margin-bottom: 0;">
									<div class="col s6 m2 input-field offset-m5 offset-s3">
										<p class="searchable_label center-small">Project</p>
										<select class="searchable" name="project_id_fk"
											id="project_id_fk" onchange="addInQueProject(this.value);getWorksList();">
											<option value="">Select</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col m12 s12">
										<table id="datatable-works" class="mdl-data-table">
											<thead>
												<tr>
													<th class="no-sort">Project</th>
													<th>Work ID</th>
													<th>Work Name</th>
													<th>Sanctioned Year</th>
													<th>Railway Agency</th>
													<th>Executed By</th>
													<th>Status</th>
													<th class="no-sort">Action</th>
												</tr>
											</thead>
											<tbody id="workTbale">
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



    <!-- footer  -->
<%--  <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

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
    	<input type="hidden" name="work_id" id="work_id" />
    </form>
    
	<form action="<%=request.getContextPath()%>/mobileappwebview/export-work" name="exportWorkForm" id="exportWorkForm" target="_blank" method="post">	
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
        
	</form>
    <script>
   	    var filtersMap = new Object();
    
        $(document).ready(function () {
             $('.sidenav').sidenav();
             $('select:not(.searchable)').formSelect();
             $('.searchable').select2(); 
             $('.modal').modal();
             $('.tooltipped').tooltip();
             $(".datepicker").datepicker();
             var filters = window.localStorage.getItem("workFilters");
             
             if($.trim(filters) != '' && $.trim(filters) != null){
           	  var temp = filters.split('^'); 
           	  for(var i=0;i< temp.length;i++){
     	        	  if($.trim(temp[i]) != '' ){
     	        		  var temp2 = temp[i].split('=');
     		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
     		        		  getProjectsFilterList(temp2[1]);
     		        	  }
     	        	  }
     	          }
               }
            // $('#textarea1').characterCounter();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
           
            getWorksList();
        });
        
        function addInQueProject(project_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('project_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(project_id_fk) != ''){
       	    	filtersMap["project_id_fk"] = project_id_fk;
        	}
        }
        function getWorksList(){
        	$(".page-loader-2").show();
        	
         	getProjectsFilterList('');
         	
        	var project_id_fk = $("#project_id_fk").val();

        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("workFilters", filters);
    			});
        	table = $('#datatable-works').DataTable();
   		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-works').DataTable({
        		"bStateSave": true,
        		fixedHeader: true,
                "fnStateSave": function (oSettings, oData) {
                    localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
                },
                "fnStateLoad": function (oSettings) {
                    return JSON.parse(localStorage.getItem('MRVCDataTables'));
                },
                columnDefs: [
                   
                    { orderable: false, 'aTargets': ['no-sort'] },
                    {
                        targets: [0,3,4,5,6],
                        className: 'hideCOl'
                    },{ targets: [1,2], className: 'fw-111'  },{ targets: [0,1, 2, 3, 5, 6], className: 'fw-15vw'  },
                ], 
                // "ScrollX": true,
                "sScrollX": "100%",
                "ordering":false,
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
		                    var input = $('.dataTables_filter input')
							.unbind(), self = this.api(), $searchButton = $(
							'<i class="fa fa-search" title="Go">')
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
                }
            }).rows().remove().draw();
    		
    		table.state.clear();		
    	 	var myParams = {project_id_fk : project_id_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/mobileappwebview/ajax/getWorksList",
    			type:"POST",
    			data:myParams, cache: false,async:false,
    			success : function(data){      				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var work_id = "'"+val.work_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getWork('+work_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn"><i class="fa fa-pencil"></i></a>'
                  	 	var rowArray = [];    	                 
                       	
                    	var project_name = '';
                        if ($.trim(val.project_name) != '') { project_name =  $.trim(val.project_name) }
                        
                       	rowArray.push( project_name);
                       	rowArray.push($.trim(val.work_id));
                       	rowArray.push($.trim(val.work_short_name));
                       	rowArray.push($.trim(val.sanctioned_year_fk));
                       	rowArray.push($.trim(val.railway));
                       	rowArray.push($.trim(val.executed_by));
                       	rowArray.push($.trim(val.work_status_fk));
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
        
        
        function getProjectsFilterList(project) {
        	$(".page-loader").show();
            var project_id_fk = $("#project_id_fk").val();
    		if ($.trim(project_id_fk) == "") {
            	$("#project_id_fk option:not(:first)").remove();
            	var myParams = { project_id_fk: project_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getProjectsFilterListInWork",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var projectName = '';
                                if ($.trim(val.project_name) != '') { projectName =  $.trim(val.project_name) }
                                var selectedFlag = (project == val.project_id_fk)?'selected':'';
    	                        $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>'  + projectName +'</option>');
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
        
    	<%-- function getWorksList() {
    		$(".page-loader-2").show();

         	table = $('#datatable-works').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');

    		var myParams = "";

    		/***************************************************************************************************/

    		$("#datatable-works")
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
    							"sAjaxSource" : "	/ajax/get-works?"+myParams,
    		        "aoColumns": [
    		            { "mData": function(data,type,row){
    		            	var project_name= "";
    		            	if($.trim(data.project_name) != ''){project_name = '-'+ $.trim(data.project_name)}
    		            	if($.trim(data.project_id_fk) == ''){ return '-'; }else{ return data.project_id_fk + project_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.work_id) == ''){ return '-'; }else{ return data.work_id; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.work_short_name) == ''){ return '-'; }else{ return data.work_short_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.sanctioned_year_fk) == ''){ return '-'; }else{ return data.sanctioned_year_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.railway) == ''){ return '-'; }else{ return data.railway; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.executed_by) == ''){ return '-'; }else{ return data.executed_by; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.remarks) == ''){ return '-'; }else{ return data.remarks; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		         		var work_id = "'"+data.work_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getWork('+work_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	    
    	  $(".page-loader-2").hide();  		     
      	
     } --%>
        function getWork(work_id){
	    	$("#work_id").val(work_id);
	    	//$("#executed_by_id_fk").val(executed_by_id_fk);

	    	//$("#railway_id_fk").val(railway_id_fk);
	    	
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/mobileappwebview/get-work');
	    	$('#getForm').submit();
	    }
        function deleteWork(work_id){       
        	$("#work_id").val(work_id);
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
	                //swal("Deleted!", "Record has been deleted", "success");
	                $('#getForm').attr('action', '<%=request.getContextPath()%>/mobileappwebview/deleteRow');
	    	    	$('#getForm').submit();
	            } else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
        
        function exportWork(){
        	 var project_id_fk = $("#project_id_fk").val();
         	 
        	 $("#exportProject_id_fk").val(project_id_fk);
         	 $("#exportWorkForm").submit();
      	}
    
    </script>

</body>

</html>