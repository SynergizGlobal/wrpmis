<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>P6 Data - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/p6data.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
     	.text-primary p a:not(.btn) {
            color: blue;
        }
        #existing,
        #baseline{
        	padding:4rem 15px 15px;
        	margin-bottom:10px; 
        	box-shadow:0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%);         	 	
        }
        #existing p,
        #baseline p{
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
		.tabs .tab a:focus, 
		.tabs .tab a:focus.active {
		 	background-color:transparent;
		}
		.select2-container--default .select2-selection--single {
		 	background-color:transparent;
		}
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
		.head{
			text-transform: uppercase;
			/* background-color:#f0f8ff; */
			background-color:#dadada;
			text-align:center;
			font-size: large;
			font-weight: bold;
			border-bottom: 1px solid  #000;
			padding:8px 5px !important;
		}
		.head.existing{
			background-color:#d5ebff;
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
							<div class="col s12 l6 offset-l3  m8 offset-m2">								
			                  <!--   <ul class="tabs">
			                        <li class="tab col s6" style="background-color:#f0f8ff"><a class="active" href="#existing">Update Existing</a></li>
			                        <li class="tab col s6" style="background-color:#fafafa"><a  href="#baseline">Add Baseline</a></li>
			                    </ul> -->
			                    <div class="tab col s12 head" >Add Baseline</div>   
				                  <div class="" id="baseline" style="background-color:#fafafa">
				                   <!--  <div style="margin-top:20px"> -->
				                        <form action="<%=request.getContextPath() %>/upload-p6-data" name="p6UploadFrom" id="p6UploadFrom" method="post" enctype="multipart/form-data">
				                            <div class="row">
				                                <div class="col s12 m6 input-field">
				                                    <p  class="searchable_label"> Contract <span class="required">*</span></p>
				                                     <select id="contract_id_fkUpload" name="contract_id_fk"  class="searchable validate-dropdown" onchange="getFobList(this.value,'fob_id_fkUpload','fobDropDownUpload');">
				                                            <option value="" >Select</option>
				                                            <c:forEach var="obj" items="${contractsList}">
				                       						  <option value="${obj.contract_id }" >${obj.contract_id }<c:if test="${not empty obj.contract_name }"> - ${obj.contract_name }</c:if></option>
				                                             </c:forEach>
				                                     </select>
				                                     <span id="contract_id_fkUploadError" class="error-msg" ></span>
				                                </div>
				                                <div class="col s12 m6 input-field" id="fobDropDownUpload" style="display: none;">
				                                    <p  class="searchable_label">FOB <span class="required">*</span></p>
				                                     <select id="fob_id_fkUpload" name="fob_id_fk"  class="browser-default searchable">
				                                            <option value="" >Select</option>
				                                     </select>
				                                     <span id="fob_id_fkUploadError" class="error-msg" ></span>
				                                </div>
				                            </div>
				                            <div class="row">
				                                <div class="col s12 m6 input-field">
				                                    <input id="data_dateUpload" type="text" name="data_date" class="validate datepicker">
				                                    <label for="data_dateUpload"> Data Date <span class="required">*</span></label>
				                                    <button type="button" id="data_dateUpload_icon"><i class="fa fa-calendar"></i></button>
				                                    <span id="data_dateUploadError" class="error-msg" ></span>
				                                </div>
				                                <div class="col s12 m6">
				                                    <div class="file-field input-field">
				                                        <div class="btn btn-outline">
				                                            <span>Upload P6 Export File <span class="required">*</span></span>
				                                            <input type="file" name="p6dataFile" id="p6dataFileUpload">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>
				                                         <span id="uploadFileError" class="error-msg"></span>
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
				                   <!--  </div> -->
				                        <div class="row center-align">
				                            <div class="col m12 text-primary">
				                                <p><strong>Note :</strong> Please make sure the uploading
				                                    P6 data file will be in
				                                    the given format. Click <a href="/pmis/P6BaselineFile.xlsx" download>here</a> for
				                                    the file format</p>
				                            </div>
				
				                        </div>
				                   
				                </div>
				                
				                <div class="tab col s12 head existing" >Update Existing</div>   
			                    <div class="" id="existing" style="background-color:#f0f8ff">
				                   <!--  <div style="margin-top:20px"> -->
				                        <form action="<%=request.getContextPath() %>/update-p6-activities" name="p6UpdateFrom" id="p6UpdateFrom" method="post" enctype="multipart/form-data">
				                            <div class="row">
				                                <div class="col s12 m6 input-field">
				                                    <p  class="searchable_label">Contract <span class="required">*</span></p>
				                                     <select id="contract_id_fkUpdate" name="contract_id_fk"  class="searchable validate-dropdown" onchange="getFobList(this.value,'fob_id_fkUpdate','fobDropDownUpdate');">
				                                            <option value="" >Select</option>
				                                            <c:forEach var="obj" items="${contractsList}">
				                       						  <option value="${obj.contract_id }">${obj.contract_id }<c:if test="${not empty obj.contract_name }"> - ${obj.contract_name }</c:if></option>
				                                             </c:forEach>				                                             
				                                     </select>
				                                     <span id="contract_id_fkUpdateError" class="error-msg" ></span>
				                                </div>
				                                <div class="col s12 m6 input-field" id="fobDropDownUpdate" style="display: none;">
				                                   <p class="searchable_label"> FOB <span class="required">*</span></p>
				                                   <select id="fob_id_fkUpdate" name="fob_id_fk"  class="browser-default searchable">
				                                        <option value="">Select</option>
				                                   </select>
				                                   <span id="fob_id_fkUpdateError" class="error-msg" ></span>
				                                </div>
				                            </div>
				                            <div class="row">
				                                <div class="col s12 m6 input-field">
				                                    <input id="data_dateUpdate" type="text" name="data_date" class="validate datepicker">
				                                    <label for="data_dateUpdate"> Data Date <span class="required">*</span></label>
				                                    <button type="button" id="data_dateUpdate_icon"><i class="fa fa-calendar"></i></button>
				                                    <span id="data_dateUpdateError" class="error-msg" ></span>
				                                </div>
				                                <div class="col s12 m6">
				                                    <div class="file-field input-field">
				                                        <div class="btn btn-outline">
				                                            <span>Upload P6 Export File <span class="required">*</span></span>
				                                            <input type="file" name="p6dataFile" id="p6dataFileUpdate">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>
				                                        <span id="updateFileError" class="error-msg"></span>
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
				                   <!--  </div> -->
				                        <div class="row center-align">
				                            <div class="col m12 text-primary">
				                                <p><strong>Note :</strong> Please make sure the uploading
				                                    P6 data file will be in
				                                    the given format. Click <a href="/pmis/P6UpdateFile.xlsx" download>here</a> for
				                                    the file format</p>
				                            </div>
				
				                        </div>
				                </div>
							</div>
						</div>
                </div>
             
            </div>
			
			<c:if test="${USER_ROLE_CODE eq 'IT' }">
                <div class="card ">
                    <div class="card-content ">
                        <span class="card-title">
                            <h6 class="mar-top center-align">P6 DATA HISTORY</h6>
                        </span>
                        
                        <div class="row no-mar">
                            <div class="col m12 s12 ">
                                <div class="row no-mar">
                                    <div class="col s6 m4 l2 offset-l1 input-field">
                                      <p class="searchable_label">Contract</p>
                                        <select id="contract_id" name="contract_id" onchange="getP6ActivityDataList();" class="searchable">
                                            <option value="">Select</option>
                                            <%-- <c:forEach var="obj" items="${contractsListFilter }">
                                            	<option value="${obj.contract_id }">${obj.contract_name }</option>
                                            </c:forEach> --%>	                                           
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l2 input-field">
                                      <p class="searchable_label">FOB </p>
                                        <select id="fob_id" name="fob_id" onchange="getP6ActivityDataList();" class="searchable">
                                            <option value="">Select</option>	
                                            <%-- <c:forEach var="obj" items="${fobListFilter }">
                                            	<option value="${obj.fob_id }">${obj.fob_name }</option>
                                            </c:forEach>  --%>                                           
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l2 input-field">
                                    	<p class="searchable_label">Data Type</p>
                                        <select id="upload_type" name="upload_type" onchange="getP6ActivityDataList();" class="searchable">
                                            <option value="">Select</option>	
                                            <%-- <c:forEach var="obj" items="${uploadTypes }">
                                            	<option value="${obj.upload_type }">${obj.upload_type }</option>
                                            </c:forEach> --%> 	                                        
                                        </select>
                                    </div>              
                                    <div class="col s6 m4 l2 input-field">
                                    	<p class="searchable_label">Status</p>
                                        <select id="status_fk" name="status_fk" onchange="getP6ActivityDataList();" class="searchable">
                                            <option value="">Select</option>	
                                            <%-- <c:forEach var="obj" items="${statusList }">
                                            	<option value="${obj.soft_delete_status_fk }">${obj.soft_delete_status_fk }</option>
                                            </c:forEach> --%>	                                        
                                        </select>
                                    </div>                       
                                    <div class="col s12 m4 l2 mob-center">
                                        <button class="btn bg-m waves-effect waves-light t-c"
                                            style="margin-top: 12px;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                   
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-p6-data" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract ID</th>
                                            <th>FOB ID</th>
                                            <th>Data Type</th>
                                            <th>Data Date </th>
                                            <th>Status</th>
                                            <th>Uploaded File</th>
                                            <th>Uploaded By</th>
                                            <th>Uploaded Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <%-- <c:forEach var="obj" items="${activityDataList }">
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
                                       </c:forEach> --%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
             </c:if>
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
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 	
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	
    <script>
	    
        $(document).ready(function () {
        	$('#fob_id_fkUpdate').formSelect();
        	$('#fob_id_fkUpload').formSelect();
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
          
            $('.tabs').tabs();
            var today = new Date();
            $('#data_dateUpdate').datepicker({
  	    	    format:'dd-mm-yyyy',
	  	    	endDate: "today",
	            maxDate: today,
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
            
            $('#data_dateUpload').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    endDate: "today",
	            maxDate: today,
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
            
            $('#data_dateUpdate_icon').click(function () {
                event.stopPropagation();
                $('#data_dateUpdate').click();
            });
            $('#data_dateUpload_icon').click(function () {
                event.stopPropagation();
                $('#data_dateUpload').click();
            });

            getP6ActivityDataList();
        });
        
        function getFobList(contract_id_fk,fob_id_attr,fobHideShowId) {
        	$(".page-loader").show();
            $("#"+fob_id_attr+" option:not(:first)").remove();

            if ($.trim(contract_id_fk) != "") {
                var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getFobListInP6",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var fobName = '';
                                if ($.trim(val.fob_name) != '') { fobName = ' - ' + $.trim(val.fob_name) }
                                $("#"+fob_id_attr).append('<option value="' + val.fob_id + '">' + $.trim(val.fob_id)  + fobName +'</option>');
                            });
                            $("#"+fobHideShowId).show();
                        }else{
                        	$("#"+fobHideShowId).hide();
                        }                     
                        $('#'+fob_id_attr).formSelect();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
      	   				$(".page-loader").hide();
    	   	         	getErrorMessage(jqXHR, exception);
    	   	     	}
                });
            }else{
            	$("#"+fobHideShowId).hide();
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
	  			 	  },"p6dataFile": {
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
	  		 	  	  },"p6dataFile": {
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
					} else if(element.attr("id") == "p6dataFileUpload" ){
						document.getElementById("uploadFileError").innerHTML="";
					 	error.appendTo('#uploadFileError');
					} else{
		 				error.insertAfter(element);
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
	  			 	  },"p6dataFile": {
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
	  		 	  	  },"p6dataFile": {
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
					} else if(element.attr("id") == "p6dataFileUpdate" ){
						document.getElementById("updateFileError").innerHTML="";
					 	error.appendTo('#updateFileError');
					} else{
		 				error.insertAfter(element);
				    } 
		   		},submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        
       $('.validate-dropdown').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
       $('.browser-default').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
       $('input').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
       
       
       function clearFilter(){
	       	$("#contract_id").val("");
	       	$("#fob_id").val("");
	       	$("#upload_type").val("");
	       	$("#status_fk").val("");        	
	       	$(".searchable").select2();
	       	getP6ActivityDataList();
       }
           
       function getP6ActivityDataList(){
	       	$(".page-loader").show();
	       	var contract_id_fk = $("#contract_id").val();
	       	var fob_id_fk = $("#fob_id").val();
	       	var upload_type = $("#upload_type").val();
	       	var status_fk = $("#status_fk").val();
	       	
	       	getContractsListFilter();
	       	getFobListFilter();
	       	getUploadTypesFilter();
	       	getStatusListFilter();
	        	
	        table = $('#datatable-p6-data').DataTable();	   		 
	   		table.destroy();	   		
	   		$.fn.dataTable.moment('DD-MMM-YYYY');
	   		table = $('#datatable-p6-data').DataTable({
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
	                	   targets:[2,3,4,5,6],
	                       className: 'hideCOl'
	                   },
	                   
	                   { orderable: false, 'aTargets': ['nosort'] },
	                   
	               ],
	               // "ScrollX": true,
	               //"scrollCollapse": true,
	               //"sScrollY": 400,
	               "sScrollX": "100%",
	               "sScrollXInner": "100%",
	               "bScrollCollapse": true,
	               initComplete: function () {
	                   $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	               }
	           }).rows().remove().draw();

	   		table.state.clear();		
	   	 
	   	 	var myParams = {contract_id_fk : contract_id_fk, fob_id_fk : fob_id_fk, upload_type : upload_type, status_fk : status_fk };
	   		$.ajax({url : "<%=request.getContextPath()%>/ajax/getP6ActivityData",type:"POST",data:myParams,success : function(data){    				
	   				if(data != null && data != '' && data.length > 0){    					
	   	         		$.each(data,function(key,val){
	   	         			var rowArray = [];    	                  
	   	                   	
	   	                   	var contract_name = '';
	                        if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
	                        
	                        var filePath = "";
	                        
	                        if($.trim(val.p6_file_path) != ''){
	                        	filePath = '<a href="<%=CommonConstants2.P6_FILES%>'+ val.p6_file_path +'">'+val.p6_file_path + '</a>';
	                        }
	   	                   	
	   	                   	rowArray.push($.trim(val.contract_id_fk) + contract_name);
	   	                    rowArray.push($.trim(val.fob_id_fk));
	   	                   	rowArray.push($.trim(val.upload_type));
	   	                   	rowArray.push($.trim(val.data_date));
	   	                   	rowArray.push($.trim(val.soft_delete_status_fk));
	   	                    rowArray.push(filePath);	   	                   	
	   	                   	rowArray.push($.trim(val.uploaded_by_user_id_fk));
	   	                   	rowArray.push($.trim(val.uploaded_date));
	   	                   	
	   	                    table.row.add(rowArray).draw( true );
	   	                    		                       
	   					});
	   	         		
	   	         		$(".page-loader").hide();
	   				}else{
	   					$(".page-loader").hide();
	   				}
	   				
	   			},error: function (jqXHR, exception) {
	   				$(".page-loader").hide();
	   	         	getErrorMessage(jqXHR, exception);
	   	     }});
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
     	
       function getContractsListFilter() {
    	   var contract_id_fk = $("#contract_id").val();
	       var fob_id_fk = $("#fob_id").val();
	       var upload_type = $("#upload_type").val();
	       var status_fk = $("#status_fk").val();
	      
       	   $(".page-loader").show();

           if ($.trim(contract_id_fk) == "") {
               $("#contract_id option:not(:first)").remove();
               var myParams = { contract_id: contract_id_fk,fob_id : fob_id_fk,upload_type : upload_type,status_fk : status_fk };
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInP6",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
	                           var contract_name = '';
	                           if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
	                           $("#contract_id").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id)  + contract_name +'</option>');
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
     	
       function getFobListFilter() {
    	   var contract_id_fk = $("#contract_id").val();
	       var fob_id_fk = $("#fob_id").val();
	       var upload_type = $("#upload_type").val();
	       var status_fk = $("#status_fk").val();
	       
       	   $(".page-loader").show();

           if ($.trim(fob_id_fk) == "") {
               $("#fob_id option:not(:first)").remove();
               var myParams = { contract_id: contract_id_fk,fob_id : fob_id_fk,upload_type : upload_type,status_fk : status_fk };
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getFobListFilterInP6",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                           		var fobName = '';
                               	if ($.trim(val.fob_name) != '') { fobName = ' - ' + $.trim(val.fob_name) }
                              	$("#fob_id").append('<option value="' + val.fob_id + '">' + $.trim(val.fob_id)  + fobName +'</option>');
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
       
       function getUploadTypesFilter() {
    	   var contract_id_fk = $("#contract_id").val();
	       var fob_id_fk = $("#fob_id").val();
	       var upload_type = $("#upload_type").val();
	       var status_fk = $("#status_fk").val();
	       
       	   $(".page-loader").show();

           if ($.trim(upload_type) == "") {
               $("#upload_type option:not(:first)").remove();
               var myParams = { contract_id: contract_id_fk,fob_id : fob_id_fk,upload_type : upload_type,status_fk : status_fk };
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getUploadTypesFilterInP6",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
	                           	$("#upload_type").append('<option value="' + val.upload_type + '">' + $.trim(val.upload_type) +'</option>');
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
       
       function getStatusListFilter() {
    	   var contract_id_fk = $("#contract_id").val();
	       var fob_id_fk = $("#fob_id").val();
	       var upload_type = $("#upload_type").val();
	       var status_fk = $("#status_fk").val();
	       
       	   $(".page-loader").show();

           if ($.trim(status_fk) == "") {
               $("#status_fk option:not(:first)").remove();
               var myParams = { contract_id: contract_id_fk,fob_id : fob_id_fk,upload_type : upload_type,status_fk : status_fk };
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getStatusListFilterInP6",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                           		$("#status_fk").append('<option value="' + val.soft_delete_status_fk + '">' + $.trim(val.soft_delete_status_fk) +'</option>');
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
       
       
        
    </script>
</body>
</html>