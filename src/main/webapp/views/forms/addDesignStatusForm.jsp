<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design Status Update Form - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
         .hidden{
        	display:none;
        }
         .input-field .searchable_label{
        	font-size:0.85rem;
        } 
        .mti-5 p{
        	margin-top:5px;
        }
        .pb-10{
        	padding-bottom:10px;
        }
        .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
        @media only screen and (max-width:364px){
			.fs-sm-67rem {
			    font-size: .656rem !important;
			}
		}
		.error-msg label{color:red!important;}
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
		.input-field >textarea.materialize-textarea{
			margin-bottom:2px;
		}

.datepicker~button ,
		 .datepicker-max-today~button{
		    bottom: 1.5rem;
		} 
		
.datepicker-max~button {
    position: absolute;
    right: 15px;
    top: 15px;
    border: 0;
    opacity: 0.7;
    cursor: pointer;
    background-color: transparent;
     bottom: 1.5rem;
}	

		.page-loader-4 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
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
                                <h6>Design Status Update Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-design-status-form" id="updateDesignStatusForm" name="updateDesignStatusForm" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" >${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                	<p class="searchable_label"> Contract <span class="required">*</span></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();p6ActivitiesData();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" hod_user_id="${obj.hod_user_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
							<br><br>
							<div class="row">
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
								<div class="col m12 s12">
									<table id="designStatusTable" class="mdl-data-table" style="width:100%">
										<thead>
											<tr>
												<th class="no-sort fw-100">Structure</th>
												<th class="no-sort fw-100">Component</th>
												<th>Element</th>
												<th>Activity</th>
												<th class="fw-250"> Scope</th>
												<th>Target Date</th>
												<th>Actual Date</th>
												<th class="fw-180"> Remarks </th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
							  </div>
							</div>
							<br><br>
                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="updateDesignStatusFn()"
                                            class="btn waves-effect waves-light bg-m" style="min-width:90px">Update</button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/update-design-status" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
			<div class="page-loader-4" style="display: none;">
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
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script>

        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        
        
    	var monthShortCode=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	    var datePickerSelectAddClass = function () {
	        var self = this;
	        setTimeout(function () {
	            var selector = self.el;
	            if (!selector) {
	                selector = ".datepicker"
	            }
	            $(selector).siblings(".datepicker-modal")
	                .find(".select-dropdown.dropdown-trigger")
	                .each((index, item) => {
	                    var dateDropdownID = $(item).attr("data-target");
	                    var dropdownUL = $('#' + dateDropdownID);
	                    dropdownUL.children("li").on("click", () => {
	                        datePickerSelectAddClass();
	                    });
	                    dropdownUL.addClass("datepicker-dropdown-year-month")
	                });
	        }, 500);
	    };
	    $(document).on('focus', '.datepicker-max', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split('-');
			    this.value = "";
			    var options = {
			    	//maxDate: new Date(),
			    	format: 'dd-mmm-yy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {			    	
			        var year=(dt[2] < 80)?Number(dt[2])+2000:Number(dt[2])+1900;
			        var month=monthShortCode.indexOf(dt[1]);
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(year, month, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
	    
		 
		 $(document).on('focus', '.datepicker-max-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 });       
            
        function setTitle(category){
        	var short_description = $("#category_fk").find('option:selected').attr("name");
        	$("#title").val(short_description).focus();
        }
        
      function getWorksList(projectId) {
        	$(".page-loader-4").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workShortName = '';
                                if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workShortName) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader-4").hide();
                    }
                });
            }else{
            	$(".page-loader-4").hide();
            }
        }

        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader-4").show();
            $("#contract_id_fk option:not(:first)").remove();
            
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                                $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" hod_user_id="'+val.hod_user_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader-4").hide();
                    }
                });
            }else{
            	$(".page-loader-4").hide();
            }
        }
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader-4").show();        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){        			
       			
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
            	projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader-4").hide();
                    }
                });
                $('.searchable').select2();
            }       		
        }        
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        function updateDesignStatusFn(){
    		if(validator.form()){ // validation perform
    			$(".page-loader-4").show();
    			var compensation = $('#compensation').val();
	  			if(compensation == ""){
	  				$('#compensation_units').val("");
	  			}
    			document.getElementById("updateDesignStatusForm").submit();			
    	 	}
    	}
    	
    	
    	   
    	    var validator = $('#updateDesignStatusForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  }
   				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 }   			 				      
    		    }
    		});

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
            
            function p6ActivitiesData()
            {
            	$("#designStatusTable tbody").empty();
            	$(".page-loader-4").show();
            	 var myParams = { contract_id_fk: $("#contract_id_fk").val() };
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getP6ActivitiesData",
                     data: myParams, cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                            	 var rmks=val.remarks;
                            	 
                            	 if(val.remarks==null)
                            		 {
                            		 	rmks="";
                            		 }
                            	 
								var trdate=val.actual_date;
                            	 
                            	 if(val.actual_date==null )
                            		 {
                            		 trdate="";
                            		 }                           	 
                            	 
                            	 var html="<tr><td><input type='hidden' value='"+val.p6_activity_id+"' name='p6activityids' readonly> <input type='text' value='"+val.structure+"' name='structures' readonly></td><td><input type='text' name='components' value='"+val.component+"' readonly></td><td><input type='text' name='elements' value='"+val.element+"' readonly></td><td><input type='text' name='activities' value='"+val.activity+"' readonly></td><td><input type='text' name='scopes' value='"+val.scope+"' readonly></td><td><input type='text' name='target_dates' value='"+val.target_date+"' readonly></td><td><input type='text' class='validate datepicker-max' name='actual_dates' value='"+trdate+"'></td><td><input maxlength='100' data-length='100' value='"+rmks+"' class='validate w85 pdr5em' type='text' name='designremarks' id='designremarks"+i+"'></td></tr>";
                            	 
                            	 $("#designStatusTable tbody").append(html);

                             });
                         }
                         $('.searchable').select2();
                         $(".page-loader-4").hide();
                     }
                 });            	
            }
            
    </script>
</body>
</html>