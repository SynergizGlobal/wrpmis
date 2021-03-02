<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alerts</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
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
                            <h5>Alerts</h5>
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
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">HOD</p>
                                    <select id="hod" name="hod" class="searchable" onchange="getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Contract</p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Contractor</p>
                                    <select id="contractor_id_fk" name="contractor_id_fk" class="searchable" onchange="getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Alert Type</p>
                                    <select id="alert_type_fk" name="alert_type_fk" class="searchable" onchange="getAlerts();">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m2">
                                    <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
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
                                            <th>Remarks</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<%-- <c:forEach var="obj" items="${alerts }">
	                                        <tr>
	                                            <td>${obj.hod }</td>
	                                            <td>${obj.work_short_name }</td>
	                                            <td>${obj.contract_short_name }</td>
	                                            <td>${obj.contractor_name }</td>
	                                            <td>${obj.alert_type_fk }</td>
	                                            <td>${obj.alert_level }</td>
	                                            <td>${obj.alert_value }</td>
	                                            <td>${obj.remarks }</td>
	                                            <td class="last-column">
	                                                <a href="javascript:void(0);" onclick="addAlertRemarks('${obj.alert_id }');" class="btn waves-effect waves-light bg-m t-c modal-trigger">Remarks</a>
	                                            </td>
	                                        </tr>
                                        </c:forEach> --%>
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
           <h5 class="modal-header"> Add Remarks <span
                   class="right modal-action modal-close"><span
                       class="material-icons">close</span></span></h5>
           <form action="<%=request.getContextPath() %>/add-alert-remarks" method="post">
           	   <input type="hidden" id="alert_id" name="alert_id" />
               <div class="row no-mar">
                   <div class="col m1 hide-on-small-only"></div>
                   <div class="input-field col s12 m10">
                       <textarea id="remarks" name="remarks"
                           class="materialize-textarea"
                           data-length="500" maxlength="500" required="required"></textarea>
                       <label for="remarks">Remarks</label>
                   </div>
                   <div class="col m1 hide-on-small-only"></div>
               </div>
               <div class="row no-mar">
                   <div class="col m1 hide-on-small-only"></div>
                   <div class="col s12 m5">
                       <div class="center-align m-1">
                           <button type="submit" style="width: 100%;"
                               class="btn waves-effect waves-light bg-m">Update</button>
                       </div>
                   </div>
                   <div class="col s12 m5">
                       <div class="center-align m-1">
                           <button type="button" style="width: 100%;"
                               class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                       </div>
                   </div>
                   <div class="col m1 hide-on-small-only"></div>
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
    
	    var email_id = '${email_id}';
	    var user_role_name = '${user_role_name}';
    
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
            
            getAlerts();
	            
		});
	    
        function clearFilters() {
        	
            $('#hod').val("");
            $('#work_id_fk').val("");
            $('#contract_id_fk').val("");
            $('#contractor_id_fk').val("");
            $('#alert_type_fk').val("");
            $('.searchable').select2();
            
            getAlerts();
        }
        
		function getAlerts(){
			$(".page-loader-2").show();
			var hod = $("#hod").val();
   	    	var work_id_fk = $("#work_id_fk").val();
   	    	var contractor_id_fk = $("#contractor_id_fk").val();
   	    	var contract_id_fk = $("#contract_id_fk").val();
   	    	var alert_type_fk = $("#alert_type_fk").val();
        	
        	getContractsFilterList();
			getHODFilterList();
			getContractorsFilterList();
			getWorkFilterList();
			getAlertTypesFilterList();

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
    	 
    		var myParams = {email_id : email_id,user_role_name : user_role_name,hod : hod,work_id_fk : work_id_fk,contractor_id_fk : contractor_id_fk, contract_id_fk : contract_id_fk, alert_type_fk : alert_type_fk};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getAlerts",
    			data:myParams,
    			type:'POST',
    			dataType: 'json',
    			success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var alert_id = "'"+val.alert_id+"'";
    	         			var remarks = "'"+val.remarks+"'";
    	                    var actions = '<a href="javascript:void(0);" onclick="addAlertRemarks('+alert_id+','+remarks+');" class="btn waves-effect waves-light bg-m t-c modal-trigger">Remarks</a>';    	                   	
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
        
	   	function getContractsFilterList(){
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
   	                data: myParams,type:"POST", cache: false,
   	                success: function (data) {   	                	
   	                    if (data.length > 0) {
   	                        $.each(data, function (i, val) {
   		                        $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) +" - "+ $.trim(val.contract_short_name) +'</option>');
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
        
        function getHODFilterList(){
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
                    data: myParams,type:"POST", cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#hod").append('<option value="' + val.hod + '">' + $.trim(val.hod)  + '</option>');
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
       
        function getContractorsFilterList(){
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
                    data: myParams,type:"POST", cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#contractor_id_fk").append('<option value="' + val.contractor_id_fk + '">' + $.trim(val.contractor_id_fk) +" - "+ $.trim(val.contractor_name) +'</option>');
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
    	 
    	 function getWorkFilterList(){
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
                    data: myParams,type:"POST", cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) +" - "+ $.trim(val.work_short_name) +'</option>');
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
    	 
    	function getAlertTypesFilterList(){
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
                    data: myParams,type:"POST", cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 $("#alert_type_fk").append('<option value="' + val.alert_type_fk + '">' + $.trim(val.alert_type_fk) +'</option>');
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