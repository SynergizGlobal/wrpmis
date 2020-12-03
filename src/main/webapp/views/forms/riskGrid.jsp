<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risks</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }

        .last-column a+a {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .tabs .tab a:not(.active) {
            color: #999999;
        }

        .tabs .tab a:focus.active {
            background-color: #e5e5e5;
        }

        .tabs .tab a.active {
            color: #444;
            background-color: #dadada;
            border-bottom: 1px solid #444;
        }

        .tabs .indicator {
            background-color: #888;
        }

        .btn.disabled,
        .btn.disabled * {
            color: #999 !important;
        }

        @media only screen and (max-width: 700px) {
            .mt-md-54 {
                margin-top: inherit;
            }
        }
         .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		 .page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}			
		.preloader-wrapper{top: 45%!important;left:47%!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row" style="margin-bottom:0;">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risks</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4 l-align">
                                <div class="m-1">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Risk Data</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 c-align">
                                <div class="m-1 ">
                                    <a  href="<%=request.getContextPath() %>/add-risk-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Risks</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1">
                                    <div class="row">
                                        <div class="col s12 m6 input-field" style="margin:0">
                                            <select id="work_id_fk" name="work_id_fk" onchange="getRiskList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                           </select>
                                        </div>
                                        <div class="col s12 m6">
                                            <a href="#" class="btn waves-effect waves-light bg-s t-c disabled"
                                                id="downloadWork">
                                                <strong><i class="fa fa-cloud-download"></i> Download Work
                                                    Data</strong></a>
                                        </div>
                                    </div>
                                    <!-- <div class="col s12 m6 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select class="searchable" id="work_fk">
                                            <option value="" disabled selected>Select </option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Download Work Data</strong></a> -->
                                </div>
                            </div>
                        </div>

                        <!--if  model 2 -->
                        <div class="row no-mar">
                            <div class="col m1 hide-on-small-only"></div>
                            <!-- <div class="col m10 s12"> -->

                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Area</p>
                                  <select id="area" name="area" onchange="getRiskList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Classification</p>
                                 <select id="classification" name="classification" onchange="getRiskList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Priority </p>
                               <select id="priority" name="priority" onchange="getRiskList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Responsible Person </p>
                                 <select id="responsible_person" name="responsible_person" onchange="getRiskList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                 </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 6px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                            <!-- </div> -->
                            <div class="col m1 hide-on-small-only"></div>

                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-risk" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work</th>
                                            <th>Risk Id</th>
                                            <th>Area</th>
                                            <th>Sub Area</th>
                                            <th>Owner</th>
                                            <th>Responsible Person</th>
                                            <th>Priority</th>
                                            <th>Classification</th>
                                            <!-- <th>Description </th> -->
                                            <!-- <th>Category</th> -->
                                            <!-- <th>Mitigation Plan</th>
                                            <th>Target Date <br>of Mitigation</th>
                                            <th>Mitigated <br>On</th> -->
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <!--  <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c"><i
                                                        class="fa fa-pencil"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c"><i
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

 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risk Analysis Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
                                <div class="row">
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label" style="text-align:left">Work</p>
                                        <select class="searchable" id="report_work">
                                            <option value="select" disabled selected>Select </option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Assessment Date</p>
                                        <select class="searchable" id="report_date">
                                            <option value="" disabled selected>Select </option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>

                                    <div class="col s12 m4 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 6px;width: 100%; font-weight: 600;"
                                            onclick="generateReport()">Generate
                                            Report</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col m2 hide-on-small-only"></div>
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
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="risk_id" id="risk_id" />
    	<input type="hidden" name="work_id_fk" id="work_id_fk" />
    </form>
      <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.tabs').tabs();
            $('#datatable-risk').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [8] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                // paging: false,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });

            $("#work_fk").change(function () {
                if ($("#work_fk").val() == 'select') {
                    $("#downloadWork").addClass('disabled');
                }
                else {
                    $("#downloadWork").removeClass('disabled');
                }
            });
            
            $("#work_id_fk").change(function () {
                if ($("#work_id_fk").val() == 'select') {
                    $("#downloadWork").addClass('disabled');
                }
                else {
                    $("#downloadWork").removeClass('disabled');
                }
            });
            getRiskList();
        });
        function clearFilters() {
            $('#work_id_fk').val('');
            $('#area').val('');
            $('#classification').val('');
            $('#priority').val('');
            $('#responsible_person').val('');
            getRiskList();
            $("#downloadWork").addClass('disabled');
            $('.searchable').select2();
        }
        
        
        function getRiskList(){
        	$(".page-loader-2").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
        	getWorksFilterList();
         	getAreasFilterList();
         	getPrioritiesFilterList();
         	getClassificationsFilterList();
         	getResponsiblePersonsFilterList();
        	table = $('#datatable-risk').DataTable();
    		table.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-risk').DataTable({
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
    		var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/get-risk-list",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var risk_id = "'"+val.risk_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getRisk('+ risk_id +');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
    /*                     			  +'<a onclick="deleteRIsk('+risk_id_pk+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
     */                   	var rowArray = [];    	                 
    
                    	var workName = '';
                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                        
                       	rowArray.push($.trim(val.work_id_fk) + workName);
                       	rowArray.push($.trim(val.risk_id));
                       	rowArray.push($.trim(val.area));
                       	rowArray.push($.trim(val.sub_area));
                       	rowArray.push($.trim(val.owner));
                       	rowArray.push($.trim(val.responsible_person));
                       	rowArray.push($.trim(val.priority));
                    	rowArray.push($.trim(val.classification));
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
        
        
        function getWorksFilterList() {
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
            	            	var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person}
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInRisk",
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
        
        function getAreasFilterList() {
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
            if ($.trim(area) == "") {
            	$("#area option:not(:first)").remove();
            	var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person}
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAreasFilterListInRisk",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#area").append('<option value="' + val.area + '">' + $.trim(val.area)   +'</option>');
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
        
        function getPrioritiesFilterList() {
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
            if ($.trim(priority) == "") {
            	$("#priority option:not(:first)").remove();
                      	var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person}
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getPrioritiesFilterListInRisk",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#priority").append('<option value="' + val.priority + '">' + $.trim(val.priority)   +'</option>');
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
        
        
        function getClassificationsFilterList() {
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
            if ($.trim(classification) == "") {
            	$("#classification option:not(:first)").remove();
            	var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person}
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getClassificationsFilterListInRisk",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#classification").append('<option value="' + val.classification + '">' + $.trim(val.classification)   +'</option>');
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
        
        function getResponsiblePersonsFilterList() {
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var area = $("#area").val();
        	var priority = $("#priority").val();
        	var classification = $("#classification").val();
        	var responsible_person = $("#responsible_person").val();
            if ($.trim(responsible_person) == "") {
            	$("#responsible_person option:not(:first)").remove();
            	  	var myParams = {work_id_fk : work_id_fk,area : area,priority : priority,classification : classification,responsible_person : responsible_person}
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getResponsiblePersonsFilterListInRisk",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#responsible_person").append('<option value="' + val.responsible_person + '">' + $.trim(val.responsible_person)   +'</option>');
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
        
        function getRisk(risk_id){
        	$("#risk_id").val(risk_id);
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-risk');
        	$('#getForm').submit();
        }
        function deleteRisk(risk_id_pk){
        	$("#risk_id_pk").val(risk_id_pk);
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
                	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk');
        	    	$('#getForm').submit();
               }else {
                    swal("Cancelled", "Record is safe :)", "error");
                }
            });
        }
        

    </script>

</body>

</html>