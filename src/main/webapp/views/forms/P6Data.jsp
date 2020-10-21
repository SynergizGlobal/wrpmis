<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>P6 Data</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/p6data.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>
     	.text-primary p a:not(.btn) {
            color: blue;
        }
        #existing p,#baseline p{
        	 margin-bottom: 0;
        }
        
        .my-error-class {
			color: red;
		}
		.input-field .searchable_label{
			font-size:.85rem !important;
		}
		
		.my-valid-class {
			color: green;
		}		
		 .tabs .tab a:focus, .tabs .tab a:focus.active {
		 	background-color:transparent;
		 }
		 .select2-container--default .select2-selection--single {
		 	background-color:transparent;
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
		.error-msg label{color:red!important;}
		
		.card-content #existing p,
		.card-content #baseline p{
			color:#004346;
		}
		.card-content #existing .file-field .btn,
		.card-content #baseline .file-field .btn{
			background-color:transparent;
		}
		.card-content #existing .input-field input[type=text]:not(.browser-default),
		.card-content #existing input[type=text]:not(.browser-default).validate+label,
		.card-content #existing .datepicker~button .fa,
		.card-content #baseline .input-field input[type=text]:not(.browser-default),
		.card-content #baseline input[type=text]:not(.browser-default).validate+label,
		.card-content #baseline .datepicker~button .fa{
			color:#004346 !important;
		}
		.card-content #existing .select2-container--default .select2-selection--single .select2-selection__rendered,
		.card-content #existing .input-field>.datepicker ~ label:not(.label-icon).active ,
		.card-content #baseline .select2-container--default .select2-selection--single .select2-selection__rendered,
		.card-content #baseline .input-field>.datepicker ~ label:not(.label-icon).active{
			color:#004346;
			background-color:transparent;
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
                            <h6> P6 Data </h6>
                        </div>
                    </span>
                    <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>     
						<div class="row">
							<div class="col m3 hide-on-small-only"></div>
							<div class="col m6 s12">								
			                    <ul class="tabs">
			                        <li class="tab col s6" style="background-color:#f0f8ff"><a class="active" href="#existing">Update Existing</a></li>
			                        <li class="tab col s6" style="background-color:#fafafa"><a  href="#baseline">Add Baseline</a></li>
			                    </ul>
			                      
                <div class="" id="existing" style="padding:15px; background-color:#f0f8ff">
                    <div style="margin-top:20px">
                        <form action="<%=request.getContextPath() %>/update-p6-activities" name="p6UpdateFrom" id="p6UpdateFrom" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col s12 m6 input-field">
                                    <p  class="searchable_label">Contract</p>
                                     <select id="contract_id_fkUpdate" name="contract_id_fk"  class="searchable validate-dropdown" onchange="getFobList(this.value,'fob_id_fkUpdate');">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${contractsList}">
                       						  <option value="${obj.contract_id_fk }" >${obj.contract_id_fk }</option>
                                             </c:forEach>
                                     </select>
                                     <span id="contract_id_fkUpdateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6 input-field">
                                   <p class="searchable_label"> FOB</p>
                                   <select id="fob_id_fkUpdate" name="fob_id_fk"  class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                   </select>
                                   <span id="fob_id_fkUpdateError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m6 input-field">
                                    <input id="data_dateUpdate" type="text" name="data_date" class="validate datepicker">
                                    <label for="data_dateUpdate"> Data Date</label>
                                    <button type="button" id="data_dateUpdate_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="data_dateUpdateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6">
                                    <div class="file-field input-field">
                                        <div class="btn btn-outline">
                                            <span>Upload P6 Export File</span>
                                            <input type="file" name="p6dataFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 center-align">
                                    <div style="display: inline-block;">
                                        <button type="button" class="btn waves-effect waves-light bg-m f-w-b" onclick="uploadP6Update();">
                                            Update Activities
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="container-no-margin">
                        <div class="row center-align">
                            <div class="col m12 text-primary">
                                <p style="margin-bottom: 20px;"><strong>Note :</strong> Please make sure the uploading
                                    P6 data file will be in
                                    the given format. Click <a href="/pmis/P6UpdateFile.xlsx" download>here</a> for
                                    the file format</p>
                            </div>

                        </div>
                    </div>
                </div>
                
 				<div class="" id="baseline" style="padding:15px;background-color:#fafafa">
                    <div style="margin-top:20px">
                        <form action="<%=request.getContextPath() %>/upload-p6-data" name="p6UploadFrom" id="p6UploadFrom" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col s12 m6 input-field">
                                    <p  class="searchable_label"> Contract</p>
                                     <select id="contract_id_fkUpload" name="contract_id_fk"  class="searchable validate-dropdown" onchange="getFobList(this.value,'fob_id_fkUpload');">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${contractsList}">
                       						  <option value="${obj.contract_id_fk }" >${obj.contract_id_fk }</option>
                                             </c:forEach>
                                     </select>
                                     <span id="contract_id_fkUploadError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6 input-field">
                                    <p  class="searchable_label">FOB</p>
                                     <select id="fob_id_fkUpload" name="fob_id_fk"  class="searchable validate-dropdown">
                                            <option value="" >Select</option>
                                     </select>
                                     <span id="fob_id_fkUploadError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m6 input-field">
                                    <input id="data_dateUpload" type="text" name="data_date" class="validate datepicker">
                                    <label for="data_dateUpload"> Data Date</label>
                                    <button type="button" id="data_dateUpload_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="data_dateUploadError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6">
                                    <div class="file-field input-field">
                                        <div class="btn btn-outline">
                                            <span>Upload P6 Export File</span>
                                            <input type="file" name="p6dataFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 center-align">
                                    <div style="display: inline-block;">
                                        <!-- <input type="submit" value="" > -->
                                        <button type="button" class="btn waves-effect waves-light bg-m f-w-b" onclick="uploadP6Baseline();">
                                            Upload Activities
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="container-no-margin">
                        <div class="row center-align">
                            <div class="col m12 text-primary">
                                <p style="margin-bottom: 20px;"><strong>Note :</strong> Please make sure the uploading
                                    P6 data file will be in
                                    the given format. Click <a href="/pmis/P6BaselineFile.xlsx" download>here</a> for
                                    the file format</p>
                            </div>

                        </div>
                    </div>
                </div>
			                    
							</div>
						</div>
                </div>
             
            </div>
        </div>
</div>

        <div class="row">
            <div class="col s12 m12">
                <div class="card ">
                    <div class="card-content ">
                        <span class="card-title">
                            <h6 class="mar-top center-align">P6 DATA HISTORY</h6>
                        </span>
                        
                        <div class="row" style="margin-bottom: 0;">
                            <div class="col m12 s12 ">
                                <div class="row" style="margin-bottom: 0;">
                                 <div class="col m1 hide-on-small-only"></div>
                                    <div class="col s12 m2 input-field">
                                      <p class="searchable_label">Contract</p>
                                        <select id="contract_id" name="contract_id" onchange="" class="searchable">
                                            <option value="">Select Contract ID</option>	                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                      <p class="searchable_label">FOB </p>
                                        <select id="fob_id" name="fob_id" onchange="" class="searchable">
                                            <option value="">Select FOB ID</option>	                                            
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Data Type</p>
                                        <select id="data_type" name="data_type" onchange="" class="searchable">
                                            <option value="">Select Data Type</option>		                                        
                                        </select>
                                    </div>              
                                     <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Status</p>
                                        <select id="status" name="status" onchange="" class="searchable">
                                            <option value="">Select Contractor</option>		                                        
                                        </select>
                                    </div>                       
                                    <div class="col s12 m2">
                                        <button class="btn bg-m waves-effect waves-light t-c"
                                            style="margin-top: 20px; width:100%" onclick="clearFilter();">Clear Filters</button>
                                    </div>
									<div class="col m1 hide-on-small-only"></div>
                                    
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="p6datatable" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract ID</th>
                                            <th>FOB ID</th>
                                            <th>Data Type</th>
                                            <th>Data Date </th>
                                            <th>Status</th>
                                            <th>P6 File</th>
                                            <th>Created By</th>
                                            <th>Created Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                     <c:forEach var="obj" items="${activityDataList }">
                                        <tr>
                                           <td>${ obj.contract_id_fk }</td>
                                            <td>${ obj.fob_id_fk }</td>
                                            <td>${ obj.upload_type }</td>
                                            <td>${ obj.data_date }</td>
                                            <td>${ obj.soft_delete_status_fk }</td>
                                            <td><c:if test="${not empty obj.p6_file_path }"><a href="<%=CommonConstants2.P6_FILES%>${ obj.p6_file_path }">${ obj.p6_file_path }</a> </c:if></td>
                                            <td>${ obj.uploaded_by_user_id_fk }</td>
                                            <td>${ obj.uploaded_date }</td>
                                        </tr>
                                        </c:forEach>
                                       <!--  <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Update</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr> -->
                                    </tbody>
                                </table>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	
    <script>
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
          
            $('.tabs').tabs();
            
            $('#data_dateUpdate').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#data_dateUpload').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#data_dateUpdate_icon').click(function () {
                event.stopPropagation();
                $('#data_dateUpdate').click();
            });
            $('#data_dateUpload_icon').click(function () {
                event.stopPropagation();
                $('#data_dateUpload').click();
            });
            $('#p6datatable').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    }
                ], 
                //"scrollCollapse": true,
                fixedHeader: true,
               // "sScrollY": 400,
               "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
        
        function getFobList(contract_id_fk,fob_id_attr) {
        	$(".page-loader").show();
            $("#"+fob_id_attr+" option:not(:first)").remove();

            if ($.trim(contract_id_fk) != "") {
                var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/get-fob-list",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                               $("#"+fob_id_attr).append('<option value="' + val.fob_id_fk + '">' + $.trim(val.fob_id_fk)  + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        

	    function uploadP6Baseline() {
	    	if(validatorUpload.form()){ // validation perform
				$(".page-loader").show();	    		
				document.getElementById("p6UploadFrom").submit();
	    	}
		}
	    
	    function  uploadP6Update() {
	    	if(validatorUpdate.form()){ // validation perform
				$(".page-loader").show();	    		
				document.getElementById("p6UpdateFrom").submit();
	    	}
		}
        
        var validatorUpload =	$('#p6UploadFrom').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "contract_id_fk": {
	  			 		required: true
	  			 	  },"fob_id_fk": {
	  			 		required: true
	  			 	  },"data_date": {
	  		 		    required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "contract_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"fob_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"data_date": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "contract_id_fkUpload" ){
						document.getElementById("contract_id_fkUploadError").innerHTML="";
				 		error.appendTo('#contract_id_fkUploadError');
					} else if(element.attr("id") == "fob_id_fkUpload" ){
					    document.getElementById("fob_id_fkUploadError").innerHTML="";
				 	    error.appendTo('#fob_id_fkUploadError');
					} else if(element.attr("id") == "data_dateUpload" ){
						document.getElementById("data_dateUploadError").innerHTML="";
					 	error.appendTo('#data_dateUploadError');
					} else{
		 				error.insertAfter(element);
				    } 
		   		},invalidHandler: function (form, validator) {
                    var errors = validator.numberOfInvalids();
                    if (errors) {
                        var position = validator.errorList[0].element;
                        jQuery('html, body').animate({
                            scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                        }, 1000);
                    }
                },submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        
        var validatorUpdate =	$('#p6UpdateFrom').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "contract_id_fk": {
	  			 		required: true
	  			 	  },"fob_id_fk": {
	  			 		required: true
	  			 	  },"data_date": {
	  		 		    required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "contract_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"fob_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"data_date": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "contract_id_fkUpdate" ){
						document.getElementById("contract_id_fkUpdateError").innerHTML="";
				 		error.appendTo('#contract_id_fkUpdateError');
					} else if(element.attr("id") == "fob_id_fkUpdate" ){
					    document.getElementById("fob_id_fkUpdateError").innerHTML="";
				 	    error.appendTo('#fob_id_fkUpdateError');
					} else if(element.attr("id") == "data_dateUpdate" ){
						document.getElementById("data_dateUpdateError").innerHTML="";
					 	error.appendTo('#data_dateUpdateError');
					} else{
		 				error.insertAfter(element);
				    } 
		   		},invalidHandler: function (form, validator) {
                   var errors = validator.numberOfInvalids();
                   if (errors) {
                       var position = validator.errorList[0].element;
                       jQuery('html, body').animate({
                           scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                       }, 1000);
                   }
               },submitHandler:function(form){
			    	form.submit();
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
        
    </script>
</body>
</html>