<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload & Downnload Risks</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>

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
      
		.error-msg label{color:red!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row" style="margin-bottom:0;">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risks</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty updateSuccess }">
					      
							<div class="center-align m-1 close-message">	
							   ${updateSuccess}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
                        <div class="row plr-1">
                            <div class="col s12 m4 l-align">
                                <div class="m-1">
                                    <a href="javascript:void(0);" onclick="openUploadRiskModal();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Risk Data</strong></a>
                                        <!-- <p style="padding-top:1rem"> Click <a href="/pmis/Risk_Template.xlsx" download>here</a> for the template</p> -->
                                </div>
                            </div>

                            <div class="col s12 m2 c-align">
                               <!--  <div class="m-1 ">
                                    <a  href="<%=request.getContextPath() %>/add-risk-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Risks</strong></a>
                                </div> -->
                            </div>

                            <div class="col s12 m6 r-align">
                                <div class="m-1">
                                	<form action="<%=request.getContextPath()%>/export-risks" method="post">
	                                    <div class="row">
	                                        <div class="col s12 m4 input-field" style="margin-top:0">
	                                        	<p class="searchable_label left-align">Work</p>
	                                            <select id="work_id_fk" name="work_id_fk" onchange="getAssessmentDates(this.value);"  class="searchable" required="required">
	                                            	<option value="" >Select</option>	                                           
	                                            </select>
	                                        </div>
	                                        <div class="col s12 m4 input-field" style="margin-top:0">
				                                <p class="searchable_label left-align">Assessment Date</p>
				                                  <select id="assessment_date" name="assessment_date" class="searchable">
				                                  		<option value="" >Select </option>	                                           
				                                 </select>
				                            </div>
	                                        <div class="col s12 m4">
	                                            <button type="submit" class="btn waves-effect waves-light bg-s t-c disabled" id="downloadWork" style="margin-top:5px">
	                                            	<strong><i class="fa fa-cloud-download"></i> Download Risk Data</strong>
	                                            </button>
	                                        </div>
	                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!--if  model 2 -->
                    
                    </div>
                </div>
            </div>
        </div>
    </div>

 
    
     <!-- update popup starts -->
    <div id="upload_template" class="modal">
        <div class="modal-content headbg">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Risks</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-risk" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="riskFile" name="riskFile" required="required">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                        <span id="riskFileError" class="error-msg"></span>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="submit"  class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUploadRiskModal();">Cancel</button>
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
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="risk_id_pk" id="risk_id_pk" />
    	<input type="hidden" name="work_id_fk" id="work_id_fk" />
    </form>
      <script>
      
      $(document).ready(function () {
    	  $(".modal").modal();
          $('select:not(.searchable)').formSelect();
          $('.searchable').select2();
          $('.tabs').tabs();
          $('#datatable-risk').DataTable({
              columnDefs: [
                  {
                      targets: [0, 1, 2],
                      className: 'mdl-data-table__cell--non-numeric',
                      targets: 'no-sort', orderable: false,
                  },
                  { "width": "10px", "targets": [8] },
              ],
              "ScrollX": true,
              "scrollCollapse": true,
              "sScrollY": 400,
              // paging: false,
              initComplete: function () {
                  $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
              }
          }); 
          
          $("#work_id_fk").change(function () {
              if ($("#work_id_fk").val() == '') {
                  $("#downloadWork").addClass('disabled');
              } else {
                  $("#downloadWork").removeClass('disabled');
              }
          });
          getWorksFilterList();
      });
      
	    function  openUploadRiskModal() { 
	  		$("#riskFile").val('');
	      	$("#upload_template").modal('open');
	  	}
	
	  	function  closeUploadRiskModal() {
	  		$("#riskFile").val('');
	      	$("#upload_template").modal('close');
	  	}
	  	
	  	 function riskFileSubmit(){
        	/* var flag = $("#riskUploadForm").valid();  
        	if(flag){
        		$(".page-loader").show();
            	$("#riskUploadForm").submit();
        	}      	 */
        }
        
        var validator = $('#riskUploadForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "riskFile":{
				   		required: true
				   	  }	
			 	},
			   messages: {
    				 "riskFile":{
    					 required: 'Required'
   				   	 }	      
		       },
			  	errorPlacement:
			 	function(error, element){
    				if (element.attr("id") == "riskFile" ){
  			 		     document.getElementById("riskFileError").innerHTML="";
  			 			 error.appendTo('#riskFileError');
  			 	    }
			   },submitHandler: function(form) {
			    // do other things for a valid form
			    //form.submit();
			    //return true;
			  }
		});
  	
       
        function getWorksFilterList() {
       		$(".page-loader").show();
           	$("#work_id_fk option:not(:first)").remove();
       		var myParams = {};
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInRisk",
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
            
        }
        
     
        function getAssessmentDates(work_id_fk) {
        	$(".page-loader").show();
            if ($.trim(work_id_fk) != "") {
            	$("#assessment_date option:not(:first)").remove();
        		var myParams = {work_id_fk : work_id_fk};
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAssessmentDatesFilterListInRisk",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#assessment_date").append('<option value="' + val.assessment_date + '">' + $.trim(val.assessment_date)   +'</option>');
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
        
        
      
    </script>

</body>

</html>