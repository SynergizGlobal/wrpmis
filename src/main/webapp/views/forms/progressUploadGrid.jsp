<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Progress Upload</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
     

    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
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
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		
		.page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		
		.dataTables_filter label::after {
		    position: relative;
		    content: none;
		    color: #6C587B;
		    right: 15px;
		    font: normal normal normal 14px/1 FontAwesome;
		}
		.dataTables_filter label .fa::before {
		    position: relative;
		    color: #2E58AD;
		    right: 15px;
		    font: normal normal normal 14px/1 FontAwesome;
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
                            <h6>Progress Upload</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4">
                                <div class="m-1 l-align">
                                    <a href="javascript:void(0);" onclick="openUploadModal();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="/pmis/Activitiess.xlsx" download>here</a> for the template</p>
                                </div>
                            </div>
                            <div class="col s12 m4">
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportActivities();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m10">
                                <div class="row">
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="getActivitiesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Contract</p>
                                        <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getActivitiesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Structure</p>
                                        <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk" class="searchable" onchange="getActivitiesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Component ID</p>
                                        <select id="strip_chart_component_id" name="strip_chart_component_id" class="searchable" onchange="getActivitiesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Component</p>
                                        <select id="strip_chart_component" name="strip_chart_component" class="searchable" onchange="getActivitiesList();">
                                            <option value="">Select</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                            Filters</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col m1 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-activities" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract</th>
                                            <th>Structure</th>
                                            <th>Component <br>ID</th>
                                            <th>Component</th>
                                            <th>Activity</th>
                                            <th>Planned <br> Start</th>
                                            <th>Planned <br> Finish</th>
                                            <th>Scope</th>
                                            <th>Completed</th>
                                            <th>Weightage</th>
                                            <!-- <th class="no-sort">Action</th> -->
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
    
    <!-- update popup starts -->
    <div id="upload_template" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Users</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-designs" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="uploadFile" name="uploadFile" required="required">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="submit" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUploadModal();">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
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
	
	
	
        
    <form action="<%=request.getContextPath() %>/export-activities" name="exportActivitiesForm" id="exportActivitiesForm" target="_blank" method="post">	
       <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
       <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
       <input type="hidden" name="strip_chart_structure_id_fk" id="exportStrip_chart_structure_id_fk" />
       <input type="hidden" name="strip_chart_component_id" id="exportStrip_chart_component_id" />
       <input type="hidden" name="strip_chart_component" id="exportStrip_chart_component" />
	</form>
    
    
     <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script type="text/javascript">
     
	    function  openUploadModal() {
	 		$("#uploadFile").val('');
	     	$("#upload_template").modal('open');
	 	}
	
	 	function  closeUploadModal() {
	 		$("#uploadFile").val('');
	     	$("#upload_template").modal('close');
	 	}
        $(document).ready(function () {
        	$('.modal').modal();
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#datatable-activities').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [9] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                // paging: false,
                initComplete: function () {
                	$('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '100%!important', 'display': 'inline-block' });
	                   /*  $('select[name="datatable-activities_length"]').addClass('browser-default table-length');
	                	$('select[name="datatable-activities_length"]').parent().after($('select[name="datatable-activities_length"]'));
	                	$('.dataTables_length .select-wrapper').remove(); */
   	    	        
	   	    	  /*   var input = $('.dataTables_filter input').unbind(),
	   	            self = this.api(),
	   	            $searchButton = $('<button class="btn-small bg-m t-c">')
	   	                       .text('Go')
	   	                       .click(function() {
	   	                          self.search(input.val()).draw();
	   	                       }),
	   	            $clearButton = $('<button class="btn-small bg-m t-c" style="margin-left:10px">')
	   	                       .text('X')
	   	                       .click(function() {
	   	                          input.val('');
	   	                          $searchButton.click(); 
	   	                       }) 
	   	                    $('.dataTables_filter').append('<div class="center-align"></div>');
		   	          $('.dataTables_filter div').append($searchButton, $clearButton); */
                }
            });
            
            getActivitiesList();
        });
        function clearFilters() {
            $('#work_id_fk').val("");
            $('#contract_id_fk').val("");
            $('#strip_chart_structure_id_fk').val("");
            $('#strip_chart_component_id').val("");
            $('#strip_chart_component').val("");
            $('.searchable').select2();
            getActivitiesList();
        }
        
        
        function getActivitiesList(){
        	$(".page-loader-2").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
        	
        	getWorksListFilter();
        	getContractsListFilter();
        	getStructuresListFilter();
        	getComponentIdsListFilter();
        	getComponentsListFilter();
        	
        	
        	table = $('#datatable-activities').DataTable();
      		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
         	
   			var myParams = "work_id_fk="+ work_id_fk+"&contract_id_fk="+ contract_id_fk+"&strip_chart_structure_id_fk="+ strip_chart_structure_id_fk+"&strip_chart_component_id="+ strip_chart_component_id+"&strip_chart_component="+ strip_chart_component ;
     		 
   		    /***************************************************************************************************/   
   		        
   		        $("#datatable-activities").DataTable( {
   				        "bProcessing": true,
   				        "bServerSide": true,
   				        "sort": "position",
   				        //bStateSave variable you can use to save state on client cookies: set value "true" 
   				        "bStateSave": false,
   				        //Default: Page display length
   				        "iDisplayLength": 10,
   				        "iData":{"start":52},
   				        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
   				        "iDisplayStart": 0,
   				        "fnDrawCallback": function () {
   				            //Get page numer on client. Please note: number start from 0 So
   				            //for the first page you will see 0 second page 1 third page 2...
   				            //Un-comment below alert to see page number
   				        	//alert("Current page number: "+this.fnPagingInfo().iPage);
   				        },   				        
   				        //"sDom": 'l<"toolbar">frtip',
	   	                "initComplete": function () {
	   	                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px ', 'display': 'inline-block' });
	   	                   /*  $('select[name="datatable-activities_length"]').addClass('browser-default table-length');
	   	                	$('select[name="datatable-activities_length"]').parent().after($('select[name="datatable-activities_length"]'));
	   	                	$('.dataTables_length .select-wrapper').remove(); */
		   	    	        
			   	    	   /*  var input = $('.dataTables_filter input').unbind(),
			   	            self = this.api(),
			   	            $searchButton = $('<button class="btn-small bg-m t-c">')
			   	                       .text('Go')
			   	                       .click(function() {
			   	                          self.search(input.val()).draw();
			   	                       }),
			   	            $clearButton = $('<button class="btn-small bg-m t-c" style="margin-left:10px">')
			   	                       .text('X')
			   	                       .click(function() {
			   	                          input.val('');
			   	                          $searchButton.click(); 
			   	                       }) 
			   	                    $('.dataTables_filter').append('<div class="center-align"></div>');
				   	          $('.dataTables_filter div').append($searchButton, $clearButton); */
				   	          
	   	                   var input = $('.dataTables_filter input').unbind(),
			   	            self = this.api(),
			   	            $searchButton = $('<i class="fa fa-search">')
			   	                       //.text('Go')
			   	                       .click(function() {			   	                    	 
			   	                          self.search(input.val()).draw();
			   	                       })			   	        
				   	          $('.dataTables_filter label').append($searchButton);	
	   	                },
	   	             columnDefs: [
	                     {
	                         "targets": 'no-sort',
	                         "orderable": false,
	                     }
	                 ],
		   	            "sScrollX": "100%",
		                "sScrollXInner": "100%",
		                "bScrollCollapse": true,
		                "language": {
		                	 "info": "_START_ - _END_ of _TOTAL_",
		                	 paginate: {
		                		 next: '<i class="fa fa-angle-right"></i>', // or '→'
		                		 previous: '<i class="fa fa-angle-left"></i>' // or '←' 
		                	 }
		                },
   			            "bDestroy": true,
   				        "sAjaxSource": "<%=request.getContextPath()%>/ajax/getActivitiesList?"+myParams,
   				        "aoColumns": [
   				            { "mData": function(data,type,row){
   				            	var contract_short_name = '';
   		                        if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) }    	
   		                     	if($.trim(data.contract_id) == ''){ return '-'; }else{ return data.contract_id + contract_short_name; }
   	            			} },   				            
   				            { "mData": function(data,type,row){
   				            	if($.trim(data.strip_chart_structure) == ''){ return '-'; }else{ return data.strip_chart_structure; }
   				            } },
   				         	{ "mData": function(data,type,row){
				            	if($.trim(data.strip_chart_component_id_name) == ''){ return '-'; }else{ return data.strip_chart_component_id_name; }
				            } },
				            { "mData": function(data,type,row){
   				            	if($.trim(data.strip_chart_component) == ''){ return '-'; }else{ return data.strip_chart_component; }
   				            } },
   				         	{ "mData": function(data,type,row){
				            	if($.trim(data.strip_chart_activity_name) == ''){ return '-'; }else{ return data.strip_chart_activity_name; }
				            } },
				            { "mData": function(data,type,row){
   				            	if($.trim(data.planned_start) == ''){ return '-'; }else{ return data.planned_start; }
   				            } },
   				         	{ "mData": function(data,type,row){
				            	if($.trim(data.planned_finish) == ''){ return '-'; }else{ return data.planned_finish; }
				            } },
				            { "mData": function(data,type,row){
   				            	if($.trim(data.scope) == ''){ return '-'; }else{ return data.scope; }
   				            } },
   				         	{ "mData": function(data,type,row){
				            	if($.trim(data.completed) == ''){ return '-'; }else{ return data.completed; }
				            } },
				            { "mData": function(data,type,row){
   				            	if($.trim(data.weight) == ''){ return '-'; }else{ return data.weight; }
   				            } }
				            
   				        ]
   				    } );
   		    
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
      	
      	
        function getWorksListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
  	       
         	$(".page-loader").show();

            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
                var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, strip_chart_structure_id_fk : strip_chart_structure_id_fk, strip_chart_component_id : strip_chart_component_id, strip_chart_component : strip_chart_component };
         		$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var work_short_name = '';
                            	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
 	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + work_short_name +'</option>');
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
        
        function getContractsListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
  	       
         	$(".page-loader").show();

            if ($.trim(contract_id_fk) == "") {
                $("#contract_id_fk option:not(:first)").remove();
                var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, strip_chart_structure_id_fk : strip_chart_structure_id_fk, strip_chart_component_id : strip_chart_component_id, strip_chart_component : strip_chart_component };
         		$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) } 
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + contract_short_name +'</option>');
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
        
        
        function getStructuresListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
  	       
         	$(".page-loader").show();

            if ($.trim(strip_chart_structure_id_fk) == "") {
                $("#strip_chart_structure_id_fk option:not(:first)").remove();
                var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, strip_chart_structure_id_fk : strip_chart_structure_id_fk, strip_chart_component_id : strip_chart_component_id, strip_chart_component : strip_chart_component };
         		$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStructureListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure + '">' + $.trim(val.strip_chart_structure) +'</option>');
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
        
        
        function getComponentIdsListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
  	       
         	$(".page-loader").show();

            if ($.trim(strip_chart_component_id) == "") {
                $("#strip_chart_component_id option:not(:first)").remove();
                var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, strip_chart_structure_id_fk : strip_chart_structure_id_fk, strip_chart_component_id : strip_chart_component_id, strip_chart_component : strip_chart_component };
         		$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getComponentIdsListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#strip_chart_component_id").append('<option value="' + val.strip_chart_component_id + '">' + $.trim(val.strip_chart_component_id_name) +'</option>');
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
        
        function getComponentsListFilter() {
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
  	       
         	$(".page-loader").show();

            if ($.trim(strip_chart_component) == "") {
                $("#strip_chart_component option:not(:first)").remove();
                var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, strip_chart_structure_id_fk : strip_chart_structure_id_fk, strip_chart_component_id : strip_chart_component_id, strip_chart_component : strip_chart_component };
         		$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getComponentsListFilterInActivitiesUpload",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#strip_chart_component").append('<option value="' + val.strip_chart_component + '">' + $.trim(val.strip_chart_component) +'</option>');
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
        
        
        function exportActivities(){
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
        	var strip_chart_component_id = $("#strip_chart_component_id").val();
        	var strip_chart_component = $("#strip_chart_component").val();
	     	 
	     	 $("#exportWork_id_fk").val(work_id_fk);
	     	 $("#exportContract_id_fk").val(contract_id_fk);
	     	 $("#exportStrip_chart_structure_id_fk").val(strip_chart_structure_id_fk);
	     	 $("#exportStrip_chart_component_id").val(strip_chart_component_id);
	     	 $("#exportStrip_chart_component").val(strip_chart_component);
	     	 $("#exportActivitiesForm ").submit();
	  	}
        
    </script>
<body>

</body>
</html>