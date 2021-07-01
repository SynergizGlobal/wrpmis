<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PMIS Contract Alerts - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
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
         .fw-all{
         		width:12vw !important;
        		max-width:12vw;
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
	        .break{
	        	text-align:center;
	        	width:30vw;
	        	min-width:30vw;
			    margin-left: auto;
			    margin-right: auto;
	        }
	        .break a{
			 word-break: break-all;
	        }
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
                            <h6>PMIS Contract Alerts</h6>
                        </div>
                    </span>
                    <div class="">
                        <!-- <div class="row plr-1">
                            <div class="col s12 m4">
                                 <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div>
                            </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="Notifications.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Notifications</strong></a>
                                </div>
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar">
                            <form action="">
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label">HOD</p>
                                    <select id="hod" name="hod" class="searchable" onchange="addInQueHOD(this.value);getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="addInQueWork(this.value);getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label">Contract</p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="addInQueContract(this.value);getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label">Contractor</p>
                                    <select id="contractor_id_fk" name="contractor_id_fk" class="searchable" onchange="addInQueContractor(this.value);getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label">Alert Type</p>
                                    <select id="alert_type_fk" name="alert_type_fk" class="searchable" onchange="addInQueType(this.value);getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 l2 mob-center">
                                    <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 12px;width: 100%;" onclick="clearFilters()">Clear
                                        Filters</button>
                                </div>
                            </form>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
	                                <table id="notifications-table" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th>HOD </th>
	                                            <th>Work </th>
	                                            <th>Contract </th>
	                                            <th>Contractor </th>
	                                            <th>Alert Type </th>
	                                            <th>Alert Level</th>
	                                            <th>Reason</th>
	                                            <th>Action Taken</th>
	                                            <th class="no-sort">&nbsp;</th>
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
    
    <!-- Modal Structure -->
   <div id="remarksModal" class="modal">
       <div class="modal-content">
           <h5 class="modal-header"> Add Action Taken <span
                   class="right modal-action modal-close"><span
                       class="material-icons">close</span></span></h5>
           <form action="<%=request.getContextPath() %>/add-alert-remarks" method="post">
           	   <input type="hidden" id="alert_id" name="alert_id" />
               <div class="row no-mar">
                   <div class="input-field col s12 m10 offset-m1">
                       <textarea id="remarks" name="remarks"
                           class="materialize-textarea"
                           data-length="500" maxlength="500" required="required"></textarea>
                       <label for="remarks">Action Taken</label>
                   </div>
               </div>
               <div class="row no-mar">
                   <div class="col s6 m5 offset-m1">
                       <div class="center-align m-1">
                           <button type="submit" style="width: 100%;"
                               class="btn waves-effect waves-light bg-m">ADD</button>
                       </div>
                   </div>
                   <div class="col s6 m5">
                       <div class="center-align m-1">
                           <button type="button" style="width: 100%;"
                               class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                       </div>
                   </div>
               </div>
           </form>
       </div>
   </div>
   
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

    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
	
    <script>
    	var filtersMap = new Object();
	    var email_id = '${email_id}';
	    var user_role_name = '${user_role_name}';
	    var user_id = '${user_id}';
    
	    $(document).ready(function(){
			var successMessage = '${success}';
			var errorMessage = '${error}';
			if(successMessage){
				swal("Success!", successMessage);
			}
			if(errorMessage){
				swal("Failed!", errorMessage, "error");
			}
			
			$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();
            $('.materialize-textarea').characterCounter();
            
            var filters = window.localStorage.getItem("alertsFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'hod' ){
		        		  getHODFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractsFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contractor_id_fk'){
		        		  getContractorsFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'alert_type_fk'){
		        		  getAlertTypesFilterList(temp2[1]);
		        	  }
	        	  }
	          }
            }
            
            getAlerts();
	            
		});
	    
        function clearFilters() {
        	
            $('#hod').val("");
            $('#work_id_fk').val("");
            $('#contract_id_fk').val("");
            $('#contractor_id_fk').val("");
            $('#alert_type_fk').val("");
            $('.searchable').select2();
            window.localStorage.setItem("alertsFilters",'');
            window.location.href="<%=request.getContextPath()%>/get-alerts"
            //getAlerts();
        }
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
        
        function addInQueContract(contract_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('contract_id_fk')) delete filtersMap[key];
	   		});
        	if($.trim(contract_id_fk) != ''){
       	    	filtersMap["contract_id_fk"] = contract_id_fk;
        	}
        }
        
        function addInQueContractor(contractor_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('contractor_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(contractor_id_fk) != ''){
            	filtersMap["contractor_id_fk"] = contractor_id_fk;
	      	}
        }
        
        function addInQueType(alert_type_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('alert_type_fk')) delete filtersMap[key];
	   		});
        	if($.trim(alert_type_fk) != ''){
       	    	filtersMap["alert_type_fk"] = alert_type_fk;
        	}
        }
        
        
        function getAlerts() {
    		$(".page-loader-2").show();

    		var alert_id_from_tableau = '${alert_id}';

        	getContractsFilterList('');
			getHODFilterList('');
			getContractorsFilterList('');
			getWorkFilterList('');
			getAlertTypesFilterList('');

			var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();

        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("alertsFilters", filters);
   			});
        	
       	
       	    $('#webView').css({'display':'block'});
       		table = $('#notifications-table').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');

    		var myParams =  "hod="
    				+ hod + "&work_id_fk="+ work_id_fk+ "&contractor_id_fk="+ contractor_id_fk+ "&contract_id_fk="+ contract_id_fk+ "&alert_type_fk="+ alert_type_fk;

    		/***************************************************************************************************/

    		$("#notifications-table")
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
    							},{targets: [0,1,2,3,6,7], className: 'hideCOl'},
    							{targets: [4,5], className: 'fw-111'},{targets: [8], className: 'break'},
    							{targets: [0,1,2,3,4,5,6,7], className: 'fw-all'},],
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
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getAlerts?"+myParams,
    		        "aoColumns": [
    		        	{ "mData": function(data,type,row){
     		            	if($.trim(data.hod) == ''){ return '-'; }else{ return data.hod; }
     		            } },
      		            { "mData": function(data,type,row){
      		            	var work_short_name = '';
                             if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                             if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
      		            } },
      		         	{ "mData": function(data,type,row){
      		         		 var contractName = '';
                             if ($.trim(data.contract_short_name) != '') { contractName = ' - ' + $.trim(data.contract_short_name) }
                             if($.trim(data.contract_id) == ''){ return '-'; }else{ return data.contract_id + contractName ; }
      		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.contractor_name) == ''){ return '-'; }else{ return data.contractor_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.alert_type_fk) == ''){ return '-'; }else{ return data.alert_type_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.alert_level) == ''){ return '-'; }else{ return data.alert_level; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.alert_value) == ''){ return '-'; }else{ return data.alert_value; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.remarks) == ''){ return '-'; }else{ return data.remarks; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		         		var alert_id = "'"+data.alert_id+"'";
    	         			var remarks = "'"+data.remarks+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="addAlertRemarks('+alert_id+','+remarks+');" class="btn waves-effect waves-light bg-m t-c modal-trigger mob-btn break">Action Taken</a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
	    	    
	    	  $(".page-loader-2").hide();  		     
     }

        
		function getAlerts1(){
			$(".page-loader-2").show();
			
			var alert_id_from_tableau = '${alert_id}';

        	getContractsFilterList('');
			getHODFilterList('');
			getContractorsFilterList('');
			getWorkFilterList('');
			getAlertTypesFilterList('');

			var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();

        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("alertsFilters", filters);
   			});
         	
         	table = $('#notifications-table').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#notifications-table').DataTable({
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
                //"scrollCollapse": true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            }).rows().remove().draw();
    		
    		
    		table.state.clear();		
    	 
    		var myParams = {user_id : user_id,email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getAlerts",
    			data:myParams,cache: false,async:false,
    			type:'POST',
    			dataType: 'json',
    			success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var alert_id = "'"+val.alert_id+"'";
    	         			var remarks = "'"+val.remarks+"'";
    	                    var actions = '<a href="javascript:void(0);" onclick="addAlertRemarks('+alert_id+','+remarks+');" class="btn waves-effect waves-light bg-m t-c modal-trigger">Action Taken</a>';    	                   	
    	                   	var rowArray = [];    	                 
    	                   	
    	                	var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            
                            var contractName = '';
                            if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
                            
                            rowArray.push($.trim(val.hod));
    	                   	rowArray.push($.trim(val.work_id_fk) + workName);
    	                   	rowArray.push($.trim(val.contract_id) + contractName);
    	                   	rowArray.push($.trim(val.contractor_name));
    	                   	rowArray.push($.trim(val.alert_type_fk));
    	                   	rowArray.push($.trim(val.alert_level));
    	                   	rowArray.push($.trim(val.alert_value));
    	                   	rowArray.push($.trim(val.remarks));
    	                   	rowArray.push($.trim(actions));
    	                   	
    	                    table.row.add(rowArray).draw( true );
    	                    
    	                    
    	                    if($.trim(alert_id_from_tableau) == $.trim(val.alert_id) ){
    	                    	addAlertRemarks(val.alert_id,val.remarks);
    	                    }
    	                    		                       
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
        
	   	function getContractsFilterList(contract){
   	    	$(".page-loader").show();
   	    	var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
   	    	
   	        if ($.trim(contract_id_fk) == "") {   	        	
   	        	$("#contract_id_fk option:not(:first)").remove();
   	    	 	var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
   	            $.ajax({
   	                url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInAlerts",
   	                data: myParams,type:"POST", cache: false,async: false,
   	                success: function (data) {   	                	
   	                    if (data.length > 0) {
   	                        $.each(data, function (i, val) {
   	                        	var selectedFlag = (contract == val.contract_id)?'selected':'';
   		                        $("#contract_id_fk").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id) +" - "+ $.trim(val.contract_short_name) +'</option>');
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
        
        function getHODFilterList(hod_designation){
        	$(".page-loader").show();
        	var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
   	    	
   	        if ($.trim(hod) == "") {
   	        	$("#hod option:not(:first)").remove();
   	    	 	var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
   	            $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getHODFilterListInAlerts",
                    data: myParams,type:"POST", cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (hod_designation == val.hod)?'selected':'';
    	                           $("#hod").append('<option value="' + val.hod + '"'+selectedFlag+'>' + $.trim(val.hod)  + '</option>');
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
       
        function getContractorsFilterList(contractor){
        	$(".page-loader").show();
        	var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
   	    	
   	        if ($.trim(contractor_id_fk) == "") {
   	        	$("#contractor_id_fk option:not(:first)").remove();
   	    	 	var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
   	            $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractorsFilterListInAlerts",
                    data: myParams,type:"POST", cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (contractor == val.contractor_id_fk)?'selected':'';
    	                           $("#contractor_id_fk").append('<option value="' + val.contractor_id_fk + '"'+selectedFlag+'>' + $.trim(val.contractor_id_fk) +" - "+ $.trim(val.contractor_name) +'</option>');
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
    	 
    	 function getWorkFilterList(work){
    	 	$(".page-loader").show();
    	 	var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
   	    	
   	        if ($.trim(work_id_fk) == "") {
   	        	$("#work_id_fk option:not(:first)").remove();
   	    	 	var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
   	            $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInAlerts",
                    data: myParams,type:"POST", cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var selectedFlag = (work == val.work_id_fk)?'selected':'';
                            	 $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) +" - "+ $.trim(val.work_short_name) +'</option>');
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
    	 
    	function getAlertTypesFilterList(type){
     	 	$(".page-loader").show();
     	 	var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
   	    	
   	        if ($.trim(alert_type_fk) == "") {
   	        	$("#alert_type_fk option:not(:first)").remove();
   	    	 	var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
   	            $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAlertTypesFilterListInAlerts",
                    data: myParams,type:"POST", cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var selectedFlag = (type == val.alert_type_fk)?'selected':'';
                            	 $("#alert_type_fk").append('<option value="' + val.alert_type_fk + '"'+selectedFlag+'>' + $.trim(val.alert_type_fk) +'</option>');
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
        
        function addAlertRemarks(alert_id,remarks){   
        	$("#remarksModal").modal("open");
        	$("#remarks").val('');
        	$("#alert_id").val(alert_id);
        	if($.trim(remarks) != '' && $.trim(remarks) != 'null'){
        		$("#remarks").val(remarks);
        		$("#remarks").show().focus()
        	}        	
        	
        }
    </script>

</body>

</html>