<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R&R Agency Grid</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
    </style>
</head>

<body>

    <!-- header  starts-->

    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> R&R Agency</h6>
                        </div>
                    </span>
                    <div class="">

                        <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath()%>/add-rr-bses" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div> </div>
 <!--
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
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
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">work </p>
                                        <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="addInQueWork(this.value);getRRList();">
                                            <option value="" >Select </option>
                                          
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">MRVC - HOD</p>
                                        <select id="hod" name="hod" class="searchable" onchange="addInQueHOD(this.value);getRRList();">
                                            <option value="">Select</option>
                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">

                                <table id="rr-bses" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>work</th>
                                            <th>MRVC - HOD</th>
                                            <th>MRVC Responsible Person</th>
                                            <th>BSES Agency Name</th>
                                            <th>Responsible Person From Agency</th>                                           
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

    <!-- footer  -->
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
    	var filtersMap = new Object();
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
	  	
	 }

    </script>

</body>

</html>