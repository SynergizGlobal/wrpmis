<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forms</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        th:last-child,
        td:last-child {
            text-align: center !important;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
  		 .fw-111{
	        	width:11vw; 
	        	min-width:11vw;
	     }
	     div.dataTables_wrapper div.dataTables_info {
		    white-space: break-spaces;
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
	        	width:20vw;
	        	min-width:20vw;
	        }
	         .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	         .last-column .btn+.btn {
	            margin-left: 10px;
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
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Forms</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4 center-align" style="margin-bottom:20px;">
                                 <a href="<%=request.getContextPath()%>/add-form-access" class="btn waves-effect waves-light bg-s t-c">
                                     <strong><i class="fa fa-plus-circle"></i> Add Form</strong></a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s6 m3 l2 input-field offset-m2 offset-l3">
                                <p class="searchable_label">Select Module</p>
                                <select id="module_name_fk" name="module_name_fk" class="searchable" onchange="addInQueModule(this.value);getFormsList();">
                                    <option value="">Select</option>
                                </select>
                            </div>
                            <div class="col s6 m3 l2 input-field">
                                <p class="searchable_label">Select Status</p>
                               <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk" onchange="addInQueStatus(this.value);getFormsList();">
                                    <option value="" >Select</option>
                                </select>
                            </div>
                            <div class="col s12 m3 l2 mob-center">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 10px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="data-table-forms" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="no-sort">Module</th>
                                            <th>Form</th>
                                            <th>Folder</th>
                                            <th>Web URL</th>
                                            <th>Mobile URL</th>
                                            <th>Priority</th>
                                            <th>Status</th>
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

    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="form_id" id="form_id" />
    </form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script>
    	var filtersMap = new Object();
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.close-message').delay(3000).fadeOut('slow');
            var filters = window.localStorage.getItem("formFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^');  
        	  for(var i=0;i< temp.length;i++){
 	        	  if($.trim(temp[i]) != '' ){
 	        		  var temp2 = temp[i].split('=');
 		        	  if($.trim(temp2[0]) == 'module_name_fk' ){
 		        		 getModulesFilterList(temp2[1]);
 		        	  }else if($.trim(temp2[0]) == 'soft_delete_status_fk'){
 		        		 getStatusFilterList(temp2[1]);
 		        	  }
 	        	  }
 	          }
            }
         	getFormsList(); 
        });

        function clearFilters() {
        	 $('#module_name_fk').val("");
             $('#soft_delete_status_fk').val("");
             $('.searchable').select2();
             getFormsList();

         	window.localStorage.setItem("formFilters",'');
         	window.location.href= "<%=request.getContextPath()%>/forms";
        }
        function addInQueModule(module_name_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('module_name_fk')) delete filtersMap[key];
       		});
        	if($.trim(module_name_fk) != ''){
       	    	filtersMap["module_name_fk"] = module_name_fk;
        	}
        }
      
        function addInQueStatus(soft_delete_status_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('soft_delete_status_fk')) delete filtersMap[key];
       		});
        	if($.trim(soft_delete_status_fk) != ''){
       	    	filtersMap["soft_delete_status_fk"] = soft_delete_status_fk;
        	}
        }
        function getFormsList(){
        	$(".page-loader-2").show();
        	var module_name_fk = $("#module_name_fk").val();
        	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
        	getModulesFilterList('');
         	getStatusFilterList('');
         	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("formFilters", filters);
    		});

        	table = $('#data-table-forms').DataTable();
  	   		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#data-table-forms').DataTable({
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
                        targets: [7],
                        className: 'last-column'
                    },
                    {targets: [2,3,4,5,6], className: 'hideCOl'},
                    {targets: [0,1,2,3,4,5,6,7], className: 'fw-111'},
                    {targets: [0,1,7], className: 'fw-111'},
                    { orderable: false, 'aTargets': ['no-sort'] }
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                "ordering":false,
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
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
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton);
                }
            }).rows().remove().draw();
    		
    		table.state.clear();	
    	 	var myParams = {module_name_fk : module_name_fk,soft_delete_status_fk : soft_delete_status_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-forms-list",type:"POST",data:myParams,success : function(data){    
    	 		
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var form_id = "'"+val.form_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getAccessForm('+form_id+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-pencil"></i></a>';
                        			  
                      	
                        if($.trim(val.web_form_url) != ''){
                        	actions = actions +'<a href="<%=request.getContextPath()%>/'+val.web_form_url+'" target="_blank" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-share"></i></a>';
                        }			  
                        var rowArray = [];    	                 
                        
                        rowArray.push(val.module_name_fk);
                        rowArray.push(val.form_name); 
                       	rowArray.push(val.folder_name);
                       	rowArray.push(val.web_form_url);
                       	rowArray.push(val.mobile_form_url);
                       	rowArray.push(val.priority);
                       	rowArray.push(val.soft_delete_status_fk);
                       
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
        
        function getModulesFilterList(module) {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(module_name_fk) == "") {
            	$("#module_name_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getModulesFilterListInForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (module == val.module_name_fk)?'selected':'';
    	                           $("#module_name_fk").append('<option value="' + val.module_name_fk + '"'+selectedFlag+'>' + $.trim(val.module_name_fk) +'</option>');
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
        
        function getStatusFilterList(status) {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(soft_delete_status_fk) == "") {
            	$("#soft_delete_status_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (status == val.soft_delete_status_fk)?'selected':'';
    	                           $("#soft_delete_status_fk").append('<option value="' + val.soft_delete_status_fk + '"'+selectedFlag+'>' + $.trim(val.soft_delete_status_fk) +'</option>');
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
        
        function getAccessForm(form_id){
        	$("#form_id").val(form_id);
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-form');
        	$('#getForm').submit();
        }
    </script>
</body>

</html>