<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Activities Progress Report - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/searchable-dropdown.css">
	<style>
		.input-field .searchable_label {
			font-size:0.9rem;
		}
		.error-msg label{
			color:red!important;
		}	
		.input-field .center-align.m-1 button.bg-m, 
		.input-field .center-align.m-1 button.bg-s{
			width:inherit !important;
		}
	</style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m m-b-2">
								<h6>Activities Progress Report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form action="<%=request.getContextPath() %>/generate-activities-dpr-report" id="activitiesReportForm" name="activitiesReportForm" method="post" target="_blank">
						<div class="container container-no-margin">
							<div class="row">
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id" name="project_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>										<option>1</option>
									</select> 
									<span id="project_idError" class="error-msg"></span>
								</div>							
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id" name="work_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	<option>1</option>
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id" name="contract_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="contract_idError" class="error-msg"></span>
								</div>	
											
							</div>
							<div class="row">	
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Contractor</p>
									<select class="searchable validate-dropdown" id="contractor_id" name="contractor_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="contractor_idError" class="error-msg"></span>
								</div>
								<div class="col s12 m3 input-field">
									<p class="searchable_label">HOD</p>
									<select class="searchable validate-dropdown" id="hod" name="hod" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="hod_idError" class="error-msg"></span>
								</div>
								<div class="col s12 m3 input-field">
									<p class="searchable_label">Dy HOD</p>
									<select class="searchable validate-dropdown" id="dyhod" name="dyhod" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="dyhod_idError" class="error-msg"></span>
								</div>
							</div>
							
							<div class="row">
							    <div class="col s12 m3 input-field" >
							        <div id="fob_id_fk_div" style="display: none;">
										<p class="searchable_label">FOB</p>
										<select class="searchable validate-dropdown" id="fob_id_fk" name="fob_id_fk" onchange="resetFilterDropDowns();">
											<option value="">Select</option>	
										</select> 
										<span id="fob_id_fkError" class="error-msg"></span>
									</div>
								</div>			
								<div class="col s12 m3 input-field">
									<input id="from_date" name="from_date" type="text" class="validate datepicker"> <label for="from_date"> From Date <span class="required">*</span></label>
									<button type="button" id="from_date_icon" class="white"><i class="fa fa-calendar"></i></button>
									<span id="from_dateError" class="error-msg"></span>
								</div>
								<div class="col s12 m3 input-field" id="to_date_holder" style="display:none;">
									<input id="to_date" name="to_date" type="text" class="validate datepicker"> <label for="to_date"> To Date</label>
									<button type="button" id="to_date_icon" class="white"><i class="fa fa-calendar"></i></button>
									<span id="to_dateError" class="error-msg"></span>
								</div>	
							</div>									
							
							<div class="row">	
								<div class="col s12 m5 input-field">
									<div class="center-align m-1">
										<button type="button" class="btn waves-effect waves-light bg-s t-c" onclick="clearFilters();">Clear Filters</button>
									</div>
								</div>
								<div class="col s12 m4 input-field">
									<div class=" m-1">
										<button type="submit"
											class="btn waves-effect waves-light bg-m t-c"><strong>Generate Report </strong></button>
									</div>
								</div>
							</div>

						</div>
					</form>
					<!-- form ends  -->
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<p>reports goes here </p>
				</div>
			</div>
		</div>
	</div> -->



	<!-- Page Loader -->
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
	<script>
 /*      $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
	  var today = new Date();
      let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy',endDate: "today", maxDate: today};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            resetFilterDropDowns();           
        });
        $('#from_date').change(function(){
        	if($('#from_date').val()==''){        		
        	} else{
        		$('#to_date_holder').css("display", "block");
        	}
        });
        
        function clearFilters(){
        	$("#project_id option:not(:first)").remove();
        	$("#work_id option:not(:first)").remove();
        	$("#contract_id option:not(:first)").remove();
        	$("#fob_id_fk option:not(:first)").remove();
        	$("#contractor_id option:not(:first)").remove();
        	$("#hod option:not(:first)").remove();
        	$("#dyhod option:not(:first)").remove();
        	
        	$("#project_id").val('');
        	$("#work_id").val('');
        	$("#contract_id").val('');
        	$("#fob_id_fk").val('');
        	$("#contractor_id").val('');
        	$("#hod").val('');
        	$("#dyhod").val('');        	

            $("#from_date").val('').focus();
            $("#to_date").val('').focus();
            
        	resetFilterDropDowns();
        }
        
        
        function resetFilterDropDowns(){        	
        	getProjectsList();
        	getWorksList();
            getContractsList();
            getFobList();
            getContractorsList();
            getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        }
        
        
        function getProjectsList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(project_id) == "") {
            	$("#project_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            $("#project_id").append('<option value="' + val.project_id + '">' + $.trim(val.project_id) +'-'+ $.trim(val.project_name) + '</option>');
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
        
        function getWorksList(project_id) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(work_id) == "") {
            	$("#work_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            var workName = '';
	                            if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
	                            $("#work_id").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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

        //geting contracts list    
        function getContractsList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id, fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var contract_name = '';
	                            if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
	                            $("#contract_id").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
        
        function getFobList() {
        	$(".page-loader").show();           
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
        	//alert(contract_id +" "+ fob_id_fk);
            if ($.trim(contract_id) != "" && $.trim(fob_id_fk) == "") {
            	$("#fob_id_fk option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk :fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getFobFilterListInActivitiesReport",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var fobName = '';
                                if ($.trim(val.fob_name) != '') { fobName = ' - ' + $.trim(val.fob_name) }
                                $("#fob_id_fk").append('<option value="' + val.fob_id + '">' + $.trim(val.fob_id)  + fobName +'</option>');
                            });
                            $("#fob_id_fk_div").show();
                        }else{
                        	$("#fob_id_fk_div").hide();
                        }                     
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
      	   				$(".page-loader").hide();
    	   	         	getErrorMessage(jqXHR, exception);
    	   	     	}
                });
            }else if ($.trim(contract_id) != "" && $.trim(fob_id_fk) != "") {
            	$(".page-loader").hide();
            }else{
            	$(".page-loader").hide();
            	$("#fob_id_fk_div").hide();
            }
        }
        
        function getContractorsList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(contractor_id) == "") {
            	$("#contractor_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractorsFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	$("#contractor_id").append('<option value="' + val.contractor_id + '">' + $.trim(val.contractor_name) + '</option>');
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
        
        function getHodList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(hod) == "") {
            	$("#hod option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getHodFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	$("#hod").append('<option value="' + val.user_id + '">' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
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
        
        function getDyhodList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(dyhod) == "") {
            	$("#dyhod option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getDyhodFilterListInActivitiesReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	$("#dyhod").append('<option value="' + val.user_id + '">' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
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
        
        var validator = $('#activitiesReportForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "work_id": {
				 		required: false
				 	  },"contract_id": {
				 		required: false
				 	  },"from_date": {
				 		required: true
			 	  	  },"to_date":{
	               		required:false,
	               		greaterFrom: "#from_date"
	               	  }
				 				
			 	},
			   messages: {
				     "work_id": {
			 			required: 'Required'
			 	  	 },"contract_id": {
			 			required: 'Required'
			 	  	 },"from_date": {
  			 			required: 'Required'
  			 	  	 },"to_date":{
  		             	required:'Required'
  		              }
			 				      
		    },
			  errorPlacement:
			 	function(error, element){
				    if (element.attr("id") == "from_date" ){
			 		     document.getElementById("from_dateError").innerHTML="";
			 			 error.appendTo('#from_dateError');
			 	    }else if (element.attr("id") == "work_id" ){
			 		     document.getElementById("work_idError").innerHTML="";
			 			 error.appendTo('#work_idError');
			 	    }else if (element.attr("id") == "to_date" ){
						 document.getElementById("to_dateError").innerHTML="";
						 error.appendTo('#to_dateError');
			        }else if (element.attr("id") == "contract_id" ){
			 	    	 document.getElementById("contract_idError").innerHTML="";
			 			 error.appendTo('#contract_idError');
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
			    form.submit();
			    //return true;
			  }
		});
        
        $.validator.addMethod("greaterFrom", function(value, element) {
            var fromDateString = $('#from_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
			if($.trim(value) != ''){
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	            
	            return Date.parse(fromDate) < Date.parse(toDate);
			}else if($.trim(value) == ''){
				return true;
			}
        }, "To date must be after From date");
        
        $('select').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
        
        $('input').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
    </script>
</body>
</html>