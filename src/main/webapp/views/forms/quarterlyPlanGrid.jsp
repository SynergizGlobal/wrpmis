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
    									<a href="#"	class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
										<a href="#" onclick="exportFortnight();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
										<a href="#" onclick="openUploadFortnightModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-upload"></i> Upload</strong></a>											
    								</div>
    							</div>
                        </div>
                    </span>
                    <div class="">

                        <!-- <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m11 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Work </p>
                                        <select id="work_id_fk" class="searchable">
                                            <option value="" disabled selected>Select Work</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Items</p>
                                        <select id="items" class="searchable">
                                            <option value="" disabled selected>Select Items</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
	                                    <p>Critical Items</p>
	                                    <p>
	                                        <label>
	                                            <input class="with-gap" name="critical_items" id="critical_items1" type="radio" value="Yes" />
	                                            <span>Yes</span>
	                                        </label>
	                                        <label>
	                                            <input class="with-gap" name="critical_items" id="critical_items2" type="radio" value="No" />
	                                            <span>No</span>
	                                        </label>
	                                    </p>
                                	</div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Period</p>
                                        <select id="contractor_fk" class="searchable">
                                            <option value="" disabled selected>Select Period</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m1">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;">Clear Filters</button>
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
                                            <th class="w10px">S.No</th>
                                            <th class="w20em">Items</th>
                                            <th class="pdla">Critical (Y/N)</th>
                                            <th class="pdla">Scope of Work</th>
                                            <th class="pdla">TDC</th>
                                            <th class="pdla">Cumulative Progress</th>
                                            <th class="no-sort w10px">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>

                                            <td>&nbsp;</td>
                                            <td>Items</td>
                                            <td>Category</td>
                                            <td>Yes</td>
                                            <td>TDC</td>
                                            <td>50%</td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                        <a href="#modal1"
                                                    class="btn waves-effect waves-light bg-m t-c modal-trigger"><i
                                                        class="fa fa-eye"></i> </a>
                                               <!--  <a href="#" class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-share"></i></a> -->
                                            </td>

                                        </tr>
                                        <tr>

                                            <td>&nbsp;</td>
                                            <td>Items</td>
                                            <td>Category</td>
                                            <td>Yes</td>
                                            <td>TDC</td>
                                            <td>50%</td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                        <a href="#modal1"
                                                    class="btn waves-effect waves-light bg-m t-c modal-trigger"><i
                                                        class="fa fa-eye"></i> </a>
                                               <!--  <a href="#" class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-share"></i></a> -->
                                            </td>

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
    $(document).ready(function(){
        $('.modal').modal();
      });
    $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
        $('.notification.dropdown-trigger').dropdown({
            coverTrigger: false,
            closeOnClick: false,
        });
        $('#app_com_table').DataTable({
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric',
                    targets: 'no-sort', orderable: false,
                },
                {
                    targets: [2,3,4,5,6,7],
                    className: 'w10',
                },
                {
                    targets: [1],
                    className: 'w20em',
                },
                { "width": "20px", "targets": [7] },
            ], "scrollCollapse": true,
            fixedHeader: true,
            "sScrollY": 400,
            ordering: false,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                var input = $('.dataTables_filter input')
                    .unbind(),
                    self = this.api(),
                    $searchButton = $('<i class="fa fa-search" title="Go" id="save_post">')
                        .click(function () { self.search(input.val()).draw(); }),
                    $clearButton = $('<i class="fa fa-close" title="Reset">')
                        .click(function () {
                            input.val('');
                            $searchButton.click();
                        })
                $('.dataTables_filter').append(
                    '<div class="right-btns"></div>');
                $('.dataTables_filter div').append(
                    $searchButton, $clearButton);
            }
        });

        $('.clear-filters').click(function () {
            $('#hod_fk').val("");
            $('#module_fk').val("");
        });
    });
$(function(){
		
		$(document).on('click', '.remove', function() {
			var trIndex = $(this).closest("tr").index();
				if(trIndex>0) {
				$(this).closest("tr").remove();
			} else {
				
			}
		});
	});
    	//multiple modal end
    	 
    	/* $(function(){
    		  $(".mdl-data-table tr").each(function(){
    		    var col_val = $(this).find("td:eq(1)").text();
    		    if (col_val == "CONFIRMED"){
    		      $(this).addClass('selected');  //the selected class colors the row green//
    		    } else {
    		      $(this).addClass('bad');
    		    }
    		  });
    		}); */
 
    <%-- 	var filtersMap = new Object();
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            getRRList();
        });
		var filters = window.localStorage.getItem("RandRBSESFilters");
	    if($.trim(filters) != '' && $.trim(filters) != null){
	      	  var temp = filters.split('^'); 
	      	  for(var i=0;i< temp.length;i++){
		        	  if($.trim(temp[i]) != '' ){
		        		  var temp2 = temp[i].split('=');
			        	  if($.trim(temp2[0]) == 'work_id_fk' ){
			        		  getWorksFilterList(temp2[1]);
			        	  }else if($.trim(temp2[0]) == 'hod'){
			        		  getHODFilterList(temp2[1]);
			        	  }
		        	  }
		          }
	          }
        $('.clear-filters').click(function () {
            $('#hod').val("");
            $('#work_id_fk').val("");
            window.localStorage.setItem("RandRBSESFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/rr-bses";
        });
        
        function addInQueHOD(hod){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('hod')) delete filtersMap[key];
	   	   	});
	      	if($.trim(hod) != ''){
	        	filtersMap["hod"] = hod;
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
	    function getHODFilterList(hodVal) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	        if ($.trim(hod) == "") { 
	        	$("#hod option:not(:first)").remove();
	        	var myParams = { hod: hod,work_id_fk : work_id_fk};
	        	$.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getHodFilterListInRRBSES",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (hodVal == val.hod)?'selected':'';
		                         $("#hod").append('<option value="' + val.hod + '"'+selectedFlag+'>' + $.trim(val.designation)+"-" + $.trim(val.user_name)  +'</option>');
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
	    
	    function getWorksFilterList(work) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	        if ($.trim(work_id_fk) == "") {
	        	$("#work_id_fk option:not(:first)").remove();
	        	var myParams = { hod: hod,work_id_fk : work_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorkFilterListInRRBSES",
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
	    
	    function getRandR(rrbses_id){
	    	$("#rrbses_id").val(rrbses_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-rr-bses');
	    	$('#getForm').submit();
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
	    
	    var queue = 1;
	    function getRRList() {
			$(".page-loader-2").show();

			getWorksFilterList('');
			getHODFilterList('');
	     	
	     	
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	    	
	    	var filters = '';
	    	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
	    		filters = filters + key +"="+filtersMap[key] + "^";
	    		window.localStorage.setItem("RandRBSESFilters", filters);
				});
	    	   	table = $('#rr-bses').DataTable();
	    		table.destroy();
				var i = 0;
	    		$.fn.dataTable.moment('DD-MMM-YYYY');
	    		var rowLen = 0;
	    		var myParams =  "work_id_fk="
	    				+ work_id_fk + "&hod="+ hod;

	    		/***************************************************************************************************/
	    		$("#rr-bses").DataTable({
	    			
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
	    										'<i class="fa fa-search" title="Go" >')
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
	    								rowLen = $('#rr-bses tbody tr:visible').length
	    								if(rowLen <= 1 &&  queue == 1){									
	    									$('#rr-bses').dataTable().api().draw(); 
	    									getRRList();
	    									queue++;
	    							    } 
	    							}
	    							,
	    							columnDefs : [ {
	    								"targets" : 'no-sort',
	    								"orderable" : false,
	    							}],
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
	    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/rr-bses?"+myParams,
	    		        "aoColumns": [
	    		        	
	      		            { "mData": function(data,type,row){
	                             if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_short_name; }
	      		            } },
	      		         	{ "mData": function(data,type,row){
	                             if($.trim(data.hod) == ''){ return '-'; }else{ return data.designation +"-"+ data.user_name ; }
	      		            } },
	      		       
	    		            { "mData": function(data,type,row){ 
	    		            	if($.trim(data.mrvc_responsible_person) == ''){ return '-'; }else{return data.res_designation +"-"+ data.res_user_name ; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		            	if($.trim(data.bses_agency_name) == ''){ return '-'; }else{ return data.bses_agency_name; }
	    		            } },
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.agency_responsible_person) == ''){ return '-'; }else{ return data.agency_responsible_person; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		         		var rrbses_id = "'"+data.rrbses_id+"'";
	    	                    var actions = '<a href="javascript:void(0);"  onclick="getRandR('+rrbses_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
	    		            	return actions;
	    		            } }
	    		            
	    		        ]
	    		    });
	    	
	    	
		  $(".page-loader-2").hide();  		     
	  	
	 } --%>

    </script>

</body>

</html>