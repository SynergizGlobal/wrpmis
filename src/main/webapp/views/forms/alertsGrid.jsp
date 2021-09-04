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
   <!--  <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
   
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
         /* .fw-all{
         		width:12vw !important;
        		max-width:12vw;
         } */
         /* .fw-100{
        	width:100px !important;
        	max-width:100px;
        }
         .fw-150{
        	width:150px !important;
        	max-width:150px;
        }
		.fw-200{
        	width:200px !important;
        	max-width:200px;
        }
		.fw-250{
        	width:250px !important;
        	max-width:250px;
        } */
           @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			.mob-btn{
				padding:4px 12px;
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
	        	width:26vw;
    			min-width:26vw;
			    margin-left: auto;
			    margin-right: auto;
			    
	        }
	        .break a{
			   word-break: break-word;
			   height: auto;
			   white-space: normal;
			   line-height: inherit;
	        }
	        .mob-center{
	        	text-align:center;
	        }
		}
		
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		
		/* new code for modal and its contents starts  */
		.row.no-mar{
			margin-bottom:0;
		}
		.radioClass,
		#confirmBox input[type="radio"],
		#cvDocBox input[type="radio"] {
			opacity:2;
			position: inherit;
		}		
		#confirmBox ,#cvDocBox,#noAtrDiv {
			text-align:center;
			font-size: 1.25rem;
		}		
		.modal-content label,
		.modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1.25rem; 
		    color: #9e9e9e;
		}
		.modal-content [type="radio"]:not(:checked)+span, [type="radio"]:checked+span{
			padding-left:25px;
		}
		/* new code for modal and its contents ends  */
		.btn-holder .btn+.btn{
			margin-left:20px;
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
	                                            <th>Alert Type &nbsp; </th>
	                                            <th>Alert Level</th>
	                                            <th>Reason</th>
	                                            <th>Action Taken</th>
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
    
    <!-- Modal Structure -->
   <div id="remarksModal" class="modal">
       <div class="modal-content">
           <h5 class="modal-header"> Action Taken 
	           <span class="right modal-action modal-close">
	           <span class="material-icons">close</span></span>
           </h5>
           <form action="<%=request.getContextPath() %>/add-alert-remarks" method="post" id="remarksForm" name="remarksForm">
           	   <input type="hidden" id="alert_id" name="alert_id" />
           	   <input type="hidden" id="alert_type_fk" name="alert_type_fk" />
               <div class="row">
                   <div class="input-field col s12 m10 offset-m1">
                       <textarea id="remarks" name="remarks"
                           class="materialize-textarea"
                           data-length="1000" maxlength="1000"></textarea>
                       <label for="remarks">Action Taken</label>
                   </div>
               </div>
               <div class="row no-mar" id="amendment_not_required_in_contract_Div" style="display: block;">
                   <div class="input-field col s12 m10 offset-m1">
                       <p  class="center-align"> <label> <input type="checkbox" id="amendment_not_required_in_contract" name="amendment_not_required_in_contract" value="Yes"/> 
                       		<!-- <span> Amendment not required in contract, Stop sending this alert in mail and notifications</span>  -->
                       		<span>No Action Required</span>
                       </label>
                       </p>
                   </div>
                   
                   <div class="row no-mar" id="actionRadioBtns"  style="display:none;">
                   	<div class="col s12 m6 offset-m3">
                   		<div class="row no-mar">
                   			<div class="col l8 m12">
                   				<p>Is the work physically completed?</p>
                   			</div>
                   			<div class="col l4 m12 right-align mob-center">                   				
						      <label>
						        <input id="isPhysicallyCompletedYes" name="isPhysicallyCompleted" class="with-gap" type="radio" value="yes" />
						        <span>Yes</span>
						      </label>					    
						      <label>
						        <input id="isPhysicallyCompletedNo" name="isPhysicallyCompleted" class="with-gap" type="radio" value="no" />
						        <span>No</span>
						      </label>							    
                   			</div>
                   		</div>
                   		
                   		<div class="row no-mar" id="contractPeriodRadio" style="display:none;">
                   			<div class="col l8 m12">
                   				<p>Does it require DOC extension? </p>
                   			</div>
                   			<div class="col l4 m12 right-align mob-center">                   				
						      <label>
						        <input id="contractPeriodRadioYes" name="contractPeriodRadio" class="with-gap" type="radio" value="yes"/>
						        <span>Yes</span>
						      </label>					    
						      <label>
						        <input id="contractPeriodRadioNo" name="contractPeriodRadio" class="with-gap" type="radio" value="no"/>
						        <span>No</span>
						      </label>							    
                   			</div>
                   		</div>                		
					   
					   <div class="row no-mar" id="contractValueRadio" style="display:none;"> 
                   			<div class="col m12 l8">
                   				<p>Does it require CV variation? </p>
                   			</div>
                   			<div class="col l4 m12 right-align mob-center">                   				
						      <label>
						        <input  id="contractValueRadioYes" name="contractValueRadio" class="with-gap" type="radio" value="yes" />
						        <span>Yes</span>
						      </label>					    
						      <label>
						        <input id="contractValueRadioNo" name="contractValueRadio" class="with-gap" type="radio" value="no" />
						        <span>No</span>
						      </label>							    
                   			</div>
                   		</div> 
                   	</div>
                   </div>
               </div>        
                           
               <div class="row no-mar">
	               <div class="input-field col s12 m10 offset-m1">
	               		<p class="my-error-class" id="messageError"></p>	
	               </div>
               </div>
				
                <div class="row no-mar col s12 m12 center-align btn-holder" >
                           <button type="button" onclick="addRemarks();" style="width: auto;" id="btnRmks"
                               class="btn waves-effect waves-light bg-m">Submit</button>
                           <button type="button" onclick="addStpAlert();" style="width: auto;display:none;" id="btnStpAlert"
                               class="btn waves-effect waves-light bg-m">Stop Alert</button>
                  
                           <button type="button" style="text-align:right;width: auto;"
                               class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                  
                           <button type="button" onclick="getContractForm('bgDetails');" style="width: auto; display:none;" id="btnBG"
                               class="btn waves-effect waves-light bg-m">UPDATE BG DETAILS</button>
                 
                           <button  type="button" onclick="getContractForm('insuranceDetails');" style="width: auto;display:none;" id="btnInsurance"
                               class="btn waves-effect waves-light bg-m">UPDATE INSURANCE DETAILS</button>
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
    
    <form action="<%=request.getContextPath() %>/get-contract" id="contracUpdateForm" name="contracUpdateForm" method="post" >
    	<input type="hidden" name="contract_id" id="contract_id" />
    	<input type="hidden" name="tab_name" id="tab_name" />
    </form>
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
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
	    
	    function getContractForm(tab_name) {
			$("#tab_name").val(tab_name);
			$("#contracUpdateForm").submit();
		}
    
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
    				+ encodeURIComponent(hod) + "&work_id_fk="+ work_id_fk+ "&contractor_id_fk="+ contractor_id_fk+ "&contract_id_fk="+ contract_id_fk+ "&alert_type_fk="+ alert_type_fk;

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
    							/* {targets: [0,1,2,3,4,5,6,7], className: 'fw-all'}, */{ targets: [0], className: 'no-sort fw-100'},
    							{targets: [1,2,3], className: 'fw-200'},{targets: [6,7], className: 'fw-250'}
    							],
    							"sScrollX" : "100%",
    							"ordering":false,
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
    		         		var alert_level = "'"+data.alert_level+"'";
    		         		var alert_type_fk = "'"+data.alert_type_fk+"'";
    		         		var alert_id = "'"+data.alert_id+"'";
    	         			var remarks = "'"+data.remarks+"'";
    	         			var contract_id = "'"+data.contract_id+"'";
    	         			var amendment_not_required_in_contract = "'"+data.amendment_not_required_in_contract+"'";
    	                    var actions = '-';    	                    
    	                    //if("IT" !== '${sessionScope.USER_ROLE_CODE}'){
    	                    	actions = '<a href="javascript:void(0);"  onclick="addAlertRemarks('+alert_id+','+alert_level+','+alert_type_fk+','+remarks+','+amendment_not_required_in_contract+','+contract_id+');" class="btn waves-effect waves-light bg-m t-c modal-trigger mob-btn">Action Taken</a>';
    	                    //}
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
	    	    
	    	  $(".page-loader-2").hide();  		     
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
        
        function addAlertRemarks(alert_id,alert_level,alert_type_fk,remarks,amendment_not_required_in_contract,contract_id){
    		
    		$("#btnStpAlert").hide();
    		
        	$("#remarksModal").modal("open"); 
        	$("#messageError").html('');
        	$("#remarks").val('');
        	$("#amendment_not_required_in_contract").prop("checked", false);
        	$("#alert_id").val(alert_id);
        	$("#alert_type_fk").val(alert_type_fk);
       		
        	$('#contractValueRadioYes').prop('checked',false);
        	$('#contractValueRadioNo').prop('checked',false);
        	$('#contractPeriodRadioYes').prop('checked',false);
        	$('#contractPeriodRadioNo').prop('checked',false);
        	$('#isPhysicallyCompletedYes').prop('checked',false);
        	$('#isPhysicallyCompletedNo').prop('checked',false); 
        	
        	$('#actionRadioBtns').hide();
       		$('#contractValueRadio').hide();
        	$('#contractPeriodRadio').hide();        	
        	
        	if(alert_level=="Overdue" && (alert_type_fk=="Contract Period" || alert_type_fk=="Contract Value" )){
        		$("#amendment_not_required_in_contract_Div").show();
        		$("#btnBG").hide(); 
        		$("#btnInsurance").hide(); 
        	}else{
        		$("#amendment_not_required_in_contract_Div").hide(); 
        		$("#btnBG").hide(); 
        		$("#btnInsurance").hide(); 
        		$("#contract_id").val(contract_id);
        		if(alert_type_fk=="Bank Guarantee"){
        			$("#btnBG").show(); 
        		}else if(alert_type_fk=="Insurance"){
        			$("#btnInsurance").show(); 
        		}
        	} 
        	if($.trim(remarks) != '' && $.trim(remarks) != 'null'){
        		$("#remarks").val(remarks);
        		$("#remarks").show().focus();
        	} 
        	if($.trim(amendment_not_required_in_contract) != '' && $.trim(amendment_not_required_in_contract) != 'null' && 
        			$.trim(amendment_not_required_in_contract) == 'Yes'){
        		$("#amendment_not_required_in_contract").prop("checked", true);
        		$('#actionRadioBtns').show();
        		$('#isPhysicallyCompletedYes').prop('checked',true);
                if($("#alert_type_fk").val()=="Contract Value"){
            		$('#contractValueRadio').show();
            		$('#contractValueRadioNo').prop('checked',true);            		
           		}else if($("#alert_type_fk").val()=="Contract Period"){
           			$('#contractPeriodRadio').show();
           			$('#contractPeriodRadioNo').prop('checked',true);
            	}          
        	}   
             stopAlertShowHide();        	
        }
        
        $('#amendment_not_required_in_contract').change(function() {
        	$('#contractValueRadio').hide();
        	$('#contractPeriodRadio').hide();
        	$('#contractValueRadioYes').prop('checked',false);
        	$('#contractValueRadioNo').prop('checked',false);
        	$('#contractPeriodRadioYes').prop('checked',false);
        	$('#contractPeriodRadioNo').prop('checked',false);
        	$('#isPhysicallyCompletedYes').prop('checked',false);
        	$('#isPhysicallyCompletedNo').prop('checked',false);           	
            if(this.checked) {
               $('#actionRadioBtns').show();
               if($("#alert_type_fk").val()=="Contract Value"){
           			$('#contractValueRadio').show();
          		}else if($("#alert_type_fk").val()=="Contract Period"){
          			$('#contractPeriodRadio').show();
           		}
            } else{
            	$('#actionRadioBtns').hide();
            	$("#btnStpAlert").hide();
                $("#btnRmks").show();
            } 
        });
        
        
        $('#isPhysicallyCompletedYes,#isPhysicallyCompletedNo,#contractValueRadioNo,#contractValueRadioYes,#contractPeriodRadioNo,#contractPeriodRadioYes').change(function() {
        	stopAlertShowHide();
        });

        function stopAlertShowHide(){
        	if($('#isPhysicallyCompletedYes').prop('checked') ){
               if ( $('#contractValueRadioNo').prop('checked') || $('#contractPeriodRadioNo').prop('checked') ){
              		$("#btnStpAlert").show();
                  	$("#btnRmks").hide();
	             } else{
	 	            	$("#btnStpAlert").hide();
	 	                $("#btnRmks").show();
	             }
        	}else{
	            	$("#btnStpAlert").hide();
 	                $("#btnRmks").show();
             }
        }
        $('#btnStpAlert').hide();
        
        function addRemarks(){
        	$("#amendment_not_required_in_contract").prop("checked", false);
        	var remarks = $("#remarks").val();
        	var amendment_not_required_in_contract = $("#amendment_not_required_in_contract").prop('checked');
        	//alert(remarks +" : "+amendment_not_required_in_contract);
        	
        	var alert_type_fk = $("#alert_type_fk").val();
        	
        	if($.trim(remarks) != '' || amendment_not_required_in_contract == true){
        		$("#messageError").html('');
        		$(".page-loader").show();
        		document.getElementById("remarksForm").submit();
        	}else if((alert_type_fk == 'Contract Period' || alert_type_fk == 'Contract Value') ){
        		$("#messageError").html('Required any one field');
        	}else{
        		$("#messageError").html('Remarks required');
        	}
        }
        
        function addStpAlert(){
        	var remarks = $("#remarks").val();
        	var amendment_not_required_in_contract = $("#amendment_not_required_in_contract").prop('checked');
        	
        	var alert_type_fk = $("#alert_type_fk").val();
        	
        	if($.trim(remarks) != '' || amendment_not_required_in_contract == true){
        		$("#messageError").html('');
        		$(".page-loader").show();
        		document.getElementById("remarksForm").submit();
        	}
        }       
        
        
        
        $('textarea').change(function(){
            if ($(this).val() != ""){
            	$("#messageError").html('');
            }
        });
        $('textarea').keyup(function(){
            if ($(this).val() != ""){
            	$("#messageError").html('');
            }
        });
        
        $('input[type=checkbox]').change(function(){
            if ($(this).val() != ""){
            	$("#messageError").html('');
            }
        });
        
        var validator = $('#remarksForm').validate({
   		 	errorClass: "my-error-class",
   		   	validClass: "my-valid-class",
		   	ignore: ":hidden:not(.validate-dropdown)",
	   	   	rules: {
		   	   	"remarks":{
			 		 required: function(element) {
							   		return !$("#amendment_not_required_in_contract").val();
							   }
			 	 },"amendment_not_required_in_contract": {
	   			 	 required: function(element) {
							   		return !$("#remarks").val();
							   }
	   			  }
	   		 				
	   	 	},
	   	    messages: {
	   			 "remarks":{
	  	 	  		required: 'Required any one field'
			 	  },"amendment_not_required_in_contract": {
   		 			required: 'Required any one field'
   		 	  	  }
	   	 				      
	        },
	   	    errorPlacement:
		   	 	function(error, element){
		   			if (element.attr("id") == "amendment_not_required_in_contract" ){
	   		 		     document.getElementById("messageError").innerHTML="";
	   		 			 error.appendTo('#messageError');
		   		 	}else if (element.attr("id") == "remarks" ){
		  	 		     document.getElementById("messageError").innerHTML="";
			 			 error.appendTo('#messageError');
			 		}
		   	 },invalidHandler: function (form, validator) {
		         var errors = validator.numberOfInvalids();
		         if (errors) {
		             var position = validator.errorList[0].element;
		             jQuery('html, body').animate({
		                 scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
		             }, 1000);
		         }
		     },submitHandler: function(form) {
		   	    // do other things for a valid form
		   	    //form.submit();
		   	    //return true;
		   	  }
		});
    </script>

</body>

</html>