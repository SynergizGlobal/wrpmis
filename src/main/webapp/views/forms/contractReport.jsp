<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract Reports - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contract.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
      <style>
        p a {
            color: blue;
            text-decoration: none;
        }
      
		/* .input-field .searchable_label {
		    margin-top: -10px !important;
		} */		
		.error-msg label{color:red!important;}
		.label-for-report{
			text-align:left ;
			line-height: 50px;
		}
		.error-msg{color:red!important;}
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
                            <h6 id="rptName">Contract Reports </h6>
                        </div>
                    </span>
                    <div class="">                    	
                        <div class="row no-mar">
                            <div class="col m8 s12 offset-m2">
                            	<form id="contractReportForm" name="contractReportForm" method="post">
	                                <div class="row no-mar">	
	                                    <div class="col s6 m3 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select id="hod_designation" class="searchable validate-dropdown" name="hod_designations" onchange="addInQueHOD();getResetFiltersList();"  multiple="multiple" >
	                                        </select>
	                                        <span id="hod_designationError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All</option>
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m2 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contractor</p>
	                                        <select id="contractor_id_fk" name="contractor_id_fk" onchange="addInQueContractor(this.value);getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All </option>
	                                        </select>
	                                        <span id="contractor_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m2 input-field" id="CSdiv" style="display:none;">
	                                        <p class="searchable_label" style="text-align:left">Contract Status</p>
	                                        <select id="status" name="status" class="searchable validate-dropdown" onchange="addInQueContractStatus(this.value);getStatusofWorkItems();getResetFiltersList();">
	                                            <option value="">All</option>
	                                            <option value="Open">Open</option>
	                                            <option value="Closed">Closed</option>
	                                        </select>
	                                        <span id="contract_status_fkError" class="error-msg" ></span>
	                                    </div>	                                    
	                                    <div class="col s6 m2 input-field">
	                                        <p class="searchable_label" style="text-align:left">Status of Work</p>
	                                        <select id="contract_status_fk" name="contract_status_fk" onchange="addInQueStatusOfWork(this.value);getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All </option>
	                                        </select>
	                                        <span id="contract_status_fkError" class="error-msg" ></span>
	                                    </div>	                                    
	                                </div>  
	                                <div class="row" id="nextRow" style="display:none;">
	                                    <div class="col s6 m3 input-field" id="dateDiv">
	                                        <input id="date" name="date" type="text" class="validate datepicker"> <label for="date" class="fs-sm-8rem"> Validity Expiry By Date</label>
											<button type="button" id="date_icon" ><i class="fa fa-calendar"></i></button>
											<span id="dateError" class="error-msg"></span>
	                                    </div>
	                                    <div class="col s6 m3 input-field" id="contractDiv">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select id="contract_id" name="contract_id" onchange="addInQueContract(this.value);getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="contract_idError" class="error-msg" ></span>
	                                    </div>
	                                </div>  
			                        <div class="row">
	                                    <div class="col s7 m6 input-field" style="text-align:right;">
	                                        <button type="button" class="btn btn-primary t-c" id="btnGenerateReport"
	                                            onclick="generateContractReport();"> Generate Report</button>
	                                    </div>			                        
			                            <div class="col s4 m6 input-field" style="text-align:left;">
												<button type="button" class="btn waves-effect waves-light bg-s t-c" onclick="clearFilters();">Reset</button>
			
			                                <!--  <button class="btn waves-effect waves-light bg-s t-c"
			                                     style="margin-top: 6px; font-weight: 600;"
			                                     onclick="clearFilters();"> Clear Filters</button> -->
			                             </div>
			                        </div>	                                                            
                                </form> 
                            </div>
                        </div>

<!--                         <div class="row no-mar">
                            <div class="col m8 s12 offset-m2">
	                                <div class="row">	                                  	
	                                    <div class="col s6 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateContractReport();"> Contracts List</button>
	                                    </div>
	                                    <div class="col s6 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateBGReport();"> BG Report</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field center-align">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateInsurancceReport();"> Insurance Report</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field center-align">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateContractDetailReport();"> Contract Detail Report</button>
	                                    </div>
	                                </div>     
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div> 
 -->                                             
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
    <script>
    var filtersMap = new Object();
    var idNo = "";
        $(document).ready(function(){
        	$('.searchable').select2();
        	
        	
        	$('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            
            $('#date').datepicker({                   
  	    	  //maxDate: new Date(),
  	    	  format:'dd-mm-yyyy',
  	    	  //perform click event on done button
  	    	  onSelect: function () {
  	    	     $('.confirmation-btns .datepicker-done').click();
  	    	  }
  	        });
            
          
            	var ReportNo = getUrlVars()["id"];
            	idNo = ReportNo;
				if(ReportNo==1)
				{
					$("#rptName").html("List of Contracts");
					$("#nextRow").hide();
					$("#CSdiv").show();
					
					
				}
				else if(ReportNo==2)
				{
					$("#rptName").html("Contract Detail Report");
					$("#nextRow").show();
					$("#dateDiv").hide();
					$("#contractDiv").show();
					$("#CSdiv").hide();

				}
				else if(ReportNo==3)
				{
					$("#rptName").html("DOC Report");
					$("#nextRow").show();
					$('#toremove').hide();
					$("#dateDiv").show();
					$("#contractDiv").hide();
					$("#CSdiv").hide();

				}
				else if(ReportNo==4)
				{
					$("#rptName").html("BG Report");
					$("#nextRow").show();
					$("#dateDiv").show();
					$("#contractDiv").hide();
					$("#CSdiv").hide();

				}
				else if(ReportNo==5)
				{
					$("#rptName").html("Insurance Report");
					$("#nextRow").show();
					$("#dateDiv").show();
					$("#contractDiv").hide();
					$("#CSdiv").hide();

				}
				else if(ReportNo==6)
				{
					$("#rptName").html("DOC, BG & Insurance Report");
					$("#nextRow").show();
					$("#dateDiv").show();
					$("#contractDiv").hide();
					$("#CSdiv").hide();

				}
				 var filters = window.localStorage.getItem("contarctReportFilters"+idNo);
	             
	             if($.trim(filters) != '' && $.trim(filters) != null){
	           	  var temp = filters.split('^'); 
	           	  for(var i=0;i< temp.length;i++){
	     	        	  if($.trim(temp[i]) != '' ){
	     	        		  var temp2 = temp[i].split('=');
	     		        	   if($.trim(temp2[0]) == 'work_id_fk'){
	     		        		  getWorkFilterList(temp2[1]);
	     		        	  }else if($.trim(temp2[0]) == 'contract_id'){
	     		        		 getContractListFilter(temp2[1]);
	     		        	  }else if($.trim(temp2[0]) == 'contractor_id_fk'){
	     		        		 getContractorsFilterList(temp2[1]);
	     		        	  }else if($.trim(temp2[0]) == 'hod_designation'){
	     		        		 getDesignationFilterList(temp2[1]);
	     		        	  }else if($.trim(temp2[0]) == 'status'){
	     		        		 getContractStatusFilterList(temp2[1]);
	     		        	  }else if($.trim(temp2[0]) == 'contract_status_fk'){
	     		        		 //getContractStatusFilterList(temp2[1]);
	     		        		 getStatusofWorkItems(temp2[1]);
	     		        	  }
	     	        	  }
	     	          }
	               }
	        	getResetFiltersList();
	        	
        });
        function getUrlVars() {
            var vars = {};
            var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
                vars[key] = value;
            });
            return vars;
        }
      
        
        
        function getStatusofWorkItems(statusVal)
        {
          if($("#status").val()!="")
       	  {
        	   var status=$("#status").val();
        	   
            	$("#contract_status_fk option:not(:first)").remove();
        	 	var myParams = {status : status};
       		   
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getStatusofWorkItems",
                   type:"post",
	          	   traditional: true, 
                   data: myParams, cache: false,async: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var selectedFlag = (statusVal == val.contract_status_fk)?'selected':'';
                        	   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '"'+selectedFlag+'>' + $.trim(val.contract_status_fk) +'</option>');
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
        }
        
        
        function clearFilters(){
        	$("#contractor_id_fk").val('');
        	$("#work_id_fk").val('');
        	$("#hod_designation").val('');
        	$("#contract_status_fk").val('');
        	$("#contract_id").val('');
        	$("#status").val('');
        	$("#date").val('');
        	$('.searchable').select2();
        	 window.localStorage.setItem("contarctReportFilters"+idNo,'');
        	getResetFiltersList();
        }
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        } 
       
        function addInQueContract(contract_id){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('contract_id')) delete filtersMap[key];
       		});
        	if($.trim(contract_id) != ''){
       	    	filtersMap["contract_id"] = contract_id;
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
        
        function addInQueStatusOfWork(status){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('status')) delete filtersMap[key];
       	   	});
          	if($.trim(status) != ''){
            	filtersMap["status"] = status;
          	}
        }
        function addInQueContractStatus(contract_status_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('contract_status_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(contract_status_fk) != ''){
            	filtersMap["contract_status_fk"] = contract_status_fk;
          	}
        }
        function addInQueHOD(){
        	var hod_designation = $("#hod_designation").val();
        	hod_designation = hod_designation.toString();
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('hod_designation')) delete filtersMap[key];
       	   	});
          	if($.trim(hod_designation) != ''){
            	filtersMap["hod_designation"] = hod_designation;
          	}
        }
        function getResetFiltersList(){
        	getContractorsFilterList("");
        	getWorkFilterList("");
        	getDesignationFilterList("");        	
        	getContractStatusFilterList("");        	  
        	getContractListFilter("");
        	//getStatusofWorkItems("");
        	
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
        	var status = $("#status").val()
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("contarctReportFilters"+idNo, filters);
    			});
        	
        }
        
        function getDesignationFilterList(designation){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(hod_designations) == "") {
            	$("#hod_designation option").remove();
        	 	var myParams = {hod_designations : hod_designations,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getHODListInContractReport",
                    type:"post",
	          		traditional: true, 
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) 
                        {
                        	//$("#hod_designation").append('<option  name="hod_designations" value="">All</option>');
                            $.each(data, function (i, val) {
                            	
                            	if(designation != null ){
   		                       	 var designationArr = designation.split(',');
   		                         if(designationArr.length > 0){
   		                       	  for(var j=0;j< designationArr.length;j++){
   		                       		 var selectedFlag = (designationArr[j] == val.designation)?'selected':'';
   		                              $("#hod_designation").append('<option value="' + val.designation + '"'+selectedFlag+'>' + $.trim(val.designation)  + '</option>');
   		                       	  }
   		                         }
   		                    } else{
 	                           $("#hod_designation").append('<option  name="hod_designations" value="' + val.designation + '">' + $.trim(val.designation)  + '</option>');
   		                    }
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
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
        	 	var myParams = {hod_designations : hod_designations,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getWorksListInContractReport",
	                   type:"post",
	          		   traditional: true, 
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
   	 
        function getContractorsFilterList(contractor){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contractor_id_fk) == "") {
            	$("#contractor_id_fk option:not(:first)").remove();
        	 	var myParams = {hod_designations : hod_designations,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractorsListInContractReport",
                    type:"post",
	          		traditional: true, 
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           var contractor_name = '';
    	                           if ($.trim(val.contractor_name) != '') { contractor_name = ' - ' + $.trim(val.contractor_name) }
    	                           var selectedFlag = (contractor == val.contractor_id_fk)?'selected':'';
    	                           $("#contractor_id_fk").append('<option value="' + val.contractor_id_fk + '"'+selectedFlag+'>' + $.trim(val.contractor_id_fk)  + contractor_name +'</option>');
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
        
        function getContractStatusFilterList(contractStatus){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contract_status_fk) == "") {
            	$("#contract_status_fk option:not(:first)").remove();
        	 	var myParams = {hod_designations : hod_designations,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractStatusListInContractReport",
                   type:"post",
	          	   traditional: true, 
                   data: myParams, cache: false,async: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   /* if($.trim(val.contract_status_fk) == 'In Progress'){
                        		   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '" selected>' + $.trim(val.contract_status_fk) +'</option>');
                        	   }else{    
                        		   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '">' + $.trim(val.contract_status_fk) +'</option>');
                               } */
                               var selectedFlag = (contractStatus == val.contract_status_fk)?'selected':'';
                        	   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '"'+selectedFlag+'>' + $.trim(val.contract_status_fk) +'</option>');
                               var name = document.querySelector("#rptName").textContent;
                               if(name === 'DOC Report' ||name === 'BG Report' ||name === 'Insurance Report' ||name === 'DOC, BG & Insurance Report'){
                            	   $("#contract_status_fk option[value='Closed']").remove();
                               } 
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
        
        function getContractListFilter(contract){
        	//debugger
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designations = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
        	 	var myParams = {hod_designations : hod_designations,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};

                $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractListInContractReport",
                   type:"post",
	          	   traditional: true, 
                   data: myParams, cache: false,async: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var contractName = '';
	                           if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
	                           var selectedFlag = (contract == val.contract_id)?'selected':'';
                        	   $("#contract_id").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id) + contractName+'</option>');
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
        
        function generateContractReport() {
        	//$(".page-loader").show();
        	if(getUrlVars()["id"]==1)
        		{
        			$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-report/"+getUrlVars()["id"]);
        		}
        	    else if(getUrlVars()["id"]==2)
	    		{
        	    	var contract_id = $("#contract_id").val();
                	if($.trim(contract_id) != '')
                	{
    	            	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-detail-report/"+getUrlVars()["id"]);
                    	$("#contractReportForm").submit();
                	}
                	else
                	{
                		var errorMessage = "Please select contract";
                		$("#contract_idError").html(errorMessage);
                		//swal("Required!", errorMessage, "error");
                	}       	    	
	    		}  
        	    else if(getUrlVars()["id"]==3)
	    		{
	            	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-doc-report/"+getUrlVars()["id"]);
	    		} 
        	    else if(getUrlVars()["id"]==4)
	    		{
	            	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-bg-report/"+getUrlVars()["id"]);
	    		} 
        	    else if(getUrlVars()["id"]==5)
	    		{
	            	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-insurance-report/"+getUrlVars()["id"]);
	    		} 
        	    else if(getUrlVars()["id"]==6)
	    		{
	            	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-doc-bg-insurance-report/"+getUrlVars()["id"]);
	    		}  
        	if(getUrlVars()["id"]!=2)
        		{
        			$("#contractReportForm").submit();
        		}
		}
        function generateBGReport() {
        	//$(".page-loader").show();
        	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-bg-report");
        	$("#contractReportForm").submit();
		}
        function generateInsurancceReport() {
        	//$(".page-loader").show();
        	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-insurance-report");
        	$("#contractReportForm").submit();
		}
        
        function generateContractDetailReport() {
        	//$(".page-loader").show();
        	var contract_id = $("#contract_id").val();
        	if($.trim(contract_id) != ''){
        		$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-detail-report");
            	$("#contractReportForm").submit();
        	}else{
        		var errorMessage = "Please select contract";
        		$("#contract_idError").html(errorMessage);
        		//swal("Required!", errorMessage, "error");
        	}
        	
		}
        
        $('#contract_id').change(function(){
    	    if ($(this).val() != ""){
    	    	$("#contract_idError").html('');
    	    }
    	});
        
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
        
    </script>

</body>

</html>