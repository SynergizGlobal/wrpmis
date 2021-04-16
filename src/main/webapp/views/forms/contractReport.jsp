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
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
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
       
        td {
        	word-break: break-word;
    		white-space: initial;
		}
		.fw-120{
        	width:120px !important;
        	max-width:120px;
        }
		.fw-200{
        	width:200px;
        	max-width:200px;
        }
        .fw-250{
        	width:250px;
        	max-width:250px;
        }
.col.input-field p.searchable_label {
    margin-top: -10px !important;
}		
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
                            <h6>Contract Reports </h6>
                        </div>
                    </span>
                    <div class="">                    	
                        <div class="row no-mar">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
                            	<form id="contractReportForm" name="contractReportForm" method="post">
	                                <div class="row">
	                                    <div class="col s12 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select id="hod_designation" name="hod_designation" onchange="getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All </option>
	                                        </select>
	                                        <span id="hod_designationError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s12 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select id="work_id_fk" name="work_id_fk" onchange="getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All</option>
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s12 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contractor</p>
	                                        <select id="contractor_id_fk" name="contractor_id_fk" onchange="getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All </option>
	                                        </select>
	                                        <span id="contractor_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                </div>  
	                                <div class="row">
	                                    <div class="col s12 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract Status</p>
	                                        <select id="contract_status_fk" name="contract_status_fk" onchange="getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">All </option>
	                                        </select>
	                                        <span id="contract_status_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s12 m4 input-field">
	                                        <input id="date" name="date" type="text" class="validate datepicker"> <label for="date"> Date</label>
											<button type="button" id="date_icon" class="white"><i class="fa fa-calendar"></i></button>
											<span id="dateError" class="error-msg"></span>
	                                    </div>
	                                    <div class="col s12 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select id="contract_id" name="contract_id" onchange="getResetFiltersList();" class="searchable validate-dropdown">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="contract_idError" class="error-msg" ></span>
	                                    </div>
	                                </div>                              
                                </form> 
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m8 hide-on-small-only"></div>
                            <div class="col s12 m2 input-field">
                                 <button class="btn waves-effect waves-light bg-s t-c"
                                     style="margin-top: 6px; font-weight: 600;"
                                     onclick="clearFilters();"> Clear Filters</button>
                             </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
	                                <div class="row">	                                  	
	                                    <div class="col s12 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateContractReport();"> Contracts List</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateBGReport();"> BG Report</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateInsurancceReport();"> Insurance Report</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateContractDetailReport();"> Contract Detail Report</button>
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
        $(document).ready(function(){
        	$('.searchable').select2();
        	getResetFiltersList();
        	
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
            
        });
        
        function clearFilters(){
        	$("#contractor_id_fk").val('');
        	$("#work_id_fk").val('');
        	$("#hod_designation").val('');
        	$("#contract_status_fk").val('');
        	$("#contract_id").val('');
        	$("#date").val('');
        	$('.searchable').select2();
        	getResetFiltersList();
        }
        function getResetFiltersList(){
        	getContractorsFilterList();
        	getWorkFilterList();
        	getDesignationFilterList();        	
        	getContractStatusFilterList();        	  
        	getContractListFilter();
        }
        
        function getDesignationFilterList(){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designation = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(hod_designation) == "") {
            	$("#hod_designation option:not(:first)").remove();
        	 	var myParams = {hod_designation : hod_designation,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getHODListInContractReport",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#hod_designation").append('<option value="' + val.designation + '">' + $.trim(val.designation)  + '</option>');
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
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designation = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
        	 	var myParams = {hod_designation : hod_designation,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getWorksListInContractReport",
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
   	 
        function getContractorsFilterList(){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designation = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contractor_id_fk) == "") {
            	$("#contractor_id_fk option:not(:first)").remove();
        	 	var myParams = {hod_designation : hod_designation,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractorsListInContractReport",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           var contractor_name = '';
    	                           if ($.trim(val.contractor_name) != '') { contractor_name = ' - ' + $.trim(val.contractor_name) }
    	                           $("#contractor_id_fk").append('<option value="' + val.contractor_id_fk + '">' + $.trim(val.contractor_id_fk)  + contractor_name +'</option>');
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
        
        function getContractStatusFilterList(){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designation = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contract_status_fk) == "") {
            	$("#contract_status_fk option:not(:first)").remove();
        	 	var myParams = {hod_designation : hod_designation,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractStatusListInContractReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   /* if($.trim(val.contract_status_fk) == 'In Progress'){
                        		   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '" selected>' + $.trim(val.contract_status_fk) +'</option>');
                        	   }else{    
                        		   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '">' + $.trim(val.contract_status_fk) +'</option>');
                               } */
                        	   $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '">' + $.trim(val.contract_status_fk) +'</option>');
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
        
        function getContractListFilter(){
        	$(".page-loader").show();
        	var contractor_id_fk = $("#contractor_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var hod_designation = $("#hod_designation").val();
        	var contract_status_fk = $("#contract_status_fk").val();
        	var contract_id = $("#contract_id").val();
            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
        	 	var myParams = {hod_designation : hod_designation,contractor_id_fk : contractor_id_fk, work_id_fk : work_id_fk,contract_status_fk : contract_status_fk,contract_id : contract_id};
                $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractListInContractReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var contractName = '';
	                           if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
                        	   $("#contract_id").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + contractName+'</option>');
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
        	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-report");
        	$("#contractReportForm").submit();
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
        
        
    </script>

</body>

</html>