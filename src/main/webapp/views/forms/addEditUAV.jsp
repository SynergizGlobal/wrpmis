<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UAV - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/p6data.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
    	.bt-sh{
    		font-size: 12px;
    		padding: 0 5px;
    	}
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
	  @media only screen and (max-width: 820px){
	  	.cw-m{width: 150px !important;}	 		
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
	 .no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
	 @media(max-width: 575px){
		.cw-m{width: 95px !important;}
	}
    </style>
    
<style>
.action-icon{padding:5px;cursor:pointer;color:#fff}
.table{font-size:11px;}
.table>tbody>tr>td{padding: 2px 6px;vertical-align: middle;border:none;}
#main-container{padding: 0px 20px 40px; width: 50%;margin:auto;}
#upload-status-container{display:none;}
#upload-header{height:35px;width:100%;background-color: #323254;color: #fff;padding: 8px;border-top-left-radius: 10px;border-top-right-radius: 10px;}
#progress-bar-container{padding:20px;max-height:260px;overflow-y:auto;border:1px solid #323254;}
::-webkit-scrollbar {background-color: #fff; width: 8px; height: 8px;}
::-webkit-scrollbar-thumb {background-color: #C0C0C0; border-radius: 10px;}
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
                            <h6> UAV </h6>
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
							<div class="col s12 l12 m12 ">								
			                    <div class="col s12 m12 l12">
				                  <div class="" id="baseline">
				                   <!--  <div style="margin-top:20px"> -->
				                        <form action="<%=request.getContextPath() %>/upload-uavmp4" name="uavForm" id="uavForm" method="post" enctype="multipart/form-data">
				                            <div class="row">
			                               <div class="col s6 m4 l4 input-field offset-m2">
			                                    <p class="searchable_label">Project <span class="required">*</span></p>
			                                     <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
			                                   		 onchange="getWorksList(this.value);">
			                                         <option value="" >Select</option>
			                                         <c:forEach var="obj" items="${projectsList }">
			                                      	   <option value= "${ obj.project_id_fk}" <c:if test="${uavDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
			                                         </c:forEach>
			                                     </select>
			                                     <span id="project_id_fkError" class="error-msg" ></span>
			                                </div>
			                                <div class="col s6 m4 l4 input-field">
			                                    <p class="searchable_label">Work <span class="required">*</span></p>
			                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="resetProjectsDropdowns(this.value);">
			                                        <option value="" >Select</option>
			                                        <c:forEach var="obj" items="${worksList }">
			                                      	   <option value= "${ obj.work_id_fk}" <c:if test="${uavDetails.work_id_fk eq obj.work_id_fk}">selected</c:if>>${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
			                                         </c:forEach>
			                                    </select>
			                                     <span id="work_id_fkError" class="error-msg" ></span>
			                                </div>
				                                <div class="col s12 m4 input-field">
				                                    <input id="survey_dateUpload" type="text" name="survey_date" class="validate datepicker" value="${uavDetails.survey_date}">
				                                    <label for="survey_dateUpload"> Survey Date <span class="required">*</span></label>
				                                    <button type="button" id="survey_dateUpload_icon"><i class="fa fa-calendar"></i></button>
				                                    <span id="survey_dateUploadError" class="error-msg" ></span>
				                                </div>
				                            </div>
				                            <div class="row">
				                            
				                                <div class="col s6 m4 l4 input-field offset-m2">
					                                    <p class="searchable_label">From Station <span class="required">*</span></p>
					                                    <select class="searchable validate-dropdown" id="from_station" name="from_station" onchange="checkStationsValidation(this.value,'From');">
					                                        <option value="" >Select</option>
					                                        <c:forEach var="obj" items="${stationList }">
					                                      	   <option value= "${ obj.station_id}" <c:if test="${uavDetails.from_station eq obj.station_id}">selected</c:if>>${obj.station_name}</option>
					                                         </c:forEach>
					                                    </select>
					                                    <span id="from_stationError" class="error-msg" ></span>
				                                </div>
				                                <div class="col s6 m4 l4 input-field offset-m2">
				                                    <p class="searchable_label">To Station <span class="required">*</span></p>
				                                    <select class="searchable validate-dropdown" id="to_station" name="to_station" onchange="checkStationsValidation(this.value,'To');">
				                                        <option value="" >Select</option>
				                                        <c:forEach var="obj" items="${stationList }">
				                                      	   <option value= "${ obj.station_id}" <c:if test="${uavDetails.to_station eq obj.station_id}">selected</c:if>>${obj.station_name}</option>
				                                         </c:forEach>
				                                    </select>
				                                    <span id="to_stationError" class="error-msg" ></span>
				                                </div>					                            
				                                <div class="col s6 m4 l4 input-field offset-m2">
				                                    <div class="file-field">
				                                        <div class="btn btn-outline bt-sh">
				                                            <span>Upload Video </span>
				                                            <input type="file" name="mp4FileUpload" id="mp4FileUpload"  onchange="return mp4fileValidation();" accept="video/*" value="${uavDetails.video_file_name}" /> 
				                                            <input type="hidden" name="filename" id="filename" value="${uavDetails.video_file_name}">
				                                            <input type="hidden" name="id" id="hdnId" value="${uavDetails.id}">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>
        <div id="upload-status-container">
            <div id="upload-header">
                <span id="upload-header-text"></span>
                <i class="action-icon fa fa-window-minimize pull-right" onclick="showHide(this)" title="minimize"></i>
            </div>
            <div id="progress-bar-container">
                <table class="table">
                    <tbody></tbody>
                </table>
            </div>
        </div>				                                        
				                                         <span id="uploadFileError" class="error-msg"></span>
				                                    </div>
				                                </div>
			                                				                                
				                            </div>

				                            <br>
				                            <div class="row">
					                            	<div class="col s12 m4"></div>
					                            	<div class="col s12 m4"></div>
				                            		<div class="col s12 m4" style="font-size: 1rem;padding-top:0px;" id="mp4Error">Mp4 Format only
				                            		</div>
				                            </div>
				                            <div class="row">

				                            <div class="col s12 m4"></div>
				                             <div class="col s12 m4"></div>
				                            <div class="col s12 m4" id="attachFile">
				                            
				                             				                                          
				                            </div> 				                            				                                         
				                            
				                            </div>				                            
				                            <div class="row">
				                            		<div class="col s12 m12 required" style="font-size: 1rem;" id="errorUploadFile">
				                            		</div>
				                            </div>	
        			                            
				                            			                            
				                            <div class="row">
				                                <div class="col s12 center-align">
				                                    <div style="display: inline-block;">
				                                        <!-- <input type="submit" value="" > -->
				                                        <button type="button" class="btn waves-effect waves-light bg-m f-w-b" onclick="uploadUAV();">
				                                            Update
				                                        </button>
				                                        <button type="reset" onClick="window.location.reload(); clearFilters();" class="btn waves-effect waves-light bg-s f-w-b">Reset</button>			                                        
				                                    </div>
				
				                                </div>
				                            </div>
				                        </form>
				                   
				                </div>
				                
				                
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
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 	
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
		
    <script>
    	var filtersMap = new Object();
    	
    	var totalFileCount, fileUploadCount, fileSize;
    	
    	
    	 
        /* start uploading files */
        function startUploading() {
            var files = document.getElementById('files').files;
            if(files.length==0){
                alert("Please choose at least one file and try.");
                return;
            }
            fileUploadCount=0;
            prepareProgressBarUI(files);
             
            // upload through ajax call     
            uploadFile();
        }
         
        /* This method will be called to prepare progress-bar UI */
        function prepareProgressBarUI(files){
            totalFileCount = files.length;
            var $tbody=$("#progress-bar-container").find("tbody");
            $tbody.empty();
            $("#upload-header-text").html("1 of "+totalFileCount+" file(s) is uploading");
            for(var i=0;i<totalFileCount;i++){
                var fsize=parseFileSize(files[i].size);
                var fname=files[i].name;
                var bar='<tr id="progress-bar-'+i+'"><td style="width:75%"><div class="filename">'+fname+'</div>'
                +'<div class="progress"><div class="progress-bar progress-bar-striped active" style="width:0%"></div></div></td>'
                +'<td  style="width:25%"><span class="size-loaded"></span> '+fsize+' <span class="percent-loaded"></span></td></tr>';
                $tbody.append(bar);
            }
            $("#upload-status-container").show();
        }
         
        /* parse the file size in kb/mb/gb */
        function parseFileSize(size){
            var precision=1;
            var factor = Math.pow(10, precision);
            size = Math.round(size / 1024); //size in KB
            if(size < 1000){
                return size+" KB";
            }else{
                size = Number.parseFloat(size / 1024); //size in MB
                if(size < 1000){
                    return (Math.round(size * factor) / factor) + " MB";
                }else{
                    size = Number.parseFloat(size / 1024); //size in GB
                    return (Math.round(size * factor) / factor) + " GB";
                }
            }
            return 0;
        }
     
        /* one by one file will be uploaded to the server by ajax call*/
        function uploadFile() {
            var file = document.getElementById('mp4FileUpload').files[fileUploadCount];
            fileSize = file.size;
            var xhr = new XMLHttpRequest();
            var fd = new FormData();
            fd.append("multipartFile", file);
            xhr.upload.addEventListener("progress", onUploadProgress, false);
            xhr.addEventListener("load", onUploadComplete, false);
            xhr.addEventListener("error", onUploadError, false);
            xhr.open("POST", "UploadServlet");
            xhr.send(fd);
             
        }
     
        /* This function will continueously update the progress bar */
        function onUploadProgress(e) {
            if (e.lengthComputable) {
                var percentComplete = parseInt((e.loaded) * 100 / fileSize);
                var pbar = $('#progress-bar-'+fileUploadCount);
                var bar=pbar.find(".progress-bar");
                var sLoaded=pbar.find(".size-loaded");
                var pLoaded=pbar.find(".percent-loaded");
                bar.css("width",percentComplete + '%');
                sLoaded.html(parseFileSize(e.loaded)+ " / ");
                pLoaded.html("("+percentComplete+ "%)");
            } else {
                alert('unable to compute');
            }
        }
     
        /* This function will call when upload is completed */
        function onUploadComplete(e, error) {
            var pbar = $('#progress-bar-'+fileUploadCount);
            if(error){
                pbar.find(".progress-bar").removeClass("active").addClass("progress-bar-danger");
            }else{
                pbar.find(".progress-bar").removeClass("active");
                pbar.find(".size-loaded").html('<i class="fa fa-check text-success"></i> ');
            }
            fileUploadCount++;
            if (fileUploadCount < totalFileCount) {
                //ajax call if more files are there 
                uploadFile();
                $("#upload-header-text").html((fileUploadCount+1)+" of "+totalFileCount+" file(s) is uploading");
            } else {
                $("#upload-header-text").html("File(s) uploaded successfully!");
            }
        }
     
        /* This function will call when an error come while uploading */
        function onUploadError(e) {
            console.error("Something went wrong!");
            onUploadComplete(e,true);
        }
         
        function showHide(ele){
            if($(ele).hasClass('fa-window-minimize')){
                $(ele).removeClass('fa-window-minimize').addClass('fa-window-restore').attr("title","restore");
                $("#progress-bar-container").slideUp();
            }else{
                $(ele).addClass('fa-window-minimize').removeClass('fa-window-restore').attr("title","minimize");
                $("#progress-bar-container").slideDown();
            }
        }   	
    	
    	function checkStationsValidation(selectedValue,Station)
    	{
    			if(Station=="From")
    			{
    					if($("#to_station").val()!="" && selectedValue==$("#to_station").val())
    					{
    						$("#from_stationError").html("From Station should not same as To Station");
    						$("#from_station").val("");
    						$("#from_station #select2-to_station-container").html("Select");
    						
    					}
    					else
    						{
    							$("#from_stationError").html("");
    						}
    			}
    			if(Station=="To")
    			{
					if($("#from_station").val()!="" && selectedValue==$("#from_station").val())
					{
						$("#to_stationError").html("From Station should not same as To Station");
						$("#to_station").val("");
						$("#to_station #select2-to_station-container").html("Select");
					} 
					else
					{
						$("#to_stationError").html("");
					}
    			}    			
    	}
    	
    	
    	
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
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("p6Filters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	   if($.trim(temp2[0]) == 'project_id_fk'){
		        		   getProjectsListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id'){
		        		  getWorksList(temp2[1]);
		        	  }
	        	  }
	          }
            }
            $('.tabs').tabs();
            var today = new Date();
            
            $('#survey_dateUpload').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    //endDate: "today",
	           // maxDate: today,
	            autoClose:true,
  	    	  	onOpen: datePickerSelectAddClass
  	        });

            $('#survey_dateUpload_icon').click(function () {
                event.stopPropagation();
                $('#survey_dateUpload').click();
            });
			
            
          	if("${uavDetails.video_file_name}"!="")
          	{
          		$('#attachFile').html('<a href="/pmis/DRONE_SURVEY/${uavDetails.video_file_name} " class="filevalue" download=""><i class="fa fa-arrow-down"></i></a>');
          	}
           
        });
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }
        
        function mp4fileValidation()
        {
        	$("#errorUploadFile").html("");
        	var fileInput =
                document.getElementById('mp4FileUpload');
             
            var filePath = fileInput.value;
            document.getElementById('filename').value=$("#mp4FileUpload").val().split('/').pop().split('\\').pop();
         
            // Allowing file type
            var allowedExtensions =
                    /(\.mp4)$/i;
             
            if (!allowedExtensions.exec(filePath)) {
                alert('Invalid file type-File type is Mp4 only');
                fileInput.value = '';
                return false;
            }  
            
            var files = document.getElementById('mp4FileUpload').files;
            if(files.length==0){
                alert("Please choose at least one file and try.");
                return;
            }
            fileUploadCount=0;
            prepareProgressBarUI(files);
             
            // upload through ajax call     
            uploadFile();           
        }
        
        function checkDataAvailable()
        {

        	var bool = false;
           	 $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/checkDataAvailable",
                 data: {id:$("#hdnId").val(),work_id_fk:$("#work_id_fk").val(),survey_date:$("#survey_dateUpload").val().split("-").reverse().join("-"),from_station:$("#from_station").val(),to_station:$("#to_station").val()},type: 'POST',
                 async: false,
                 dataType: 'json',
                 success: function (data) 
                 {
                	 if (data == true) {
                         bool = true;
                     }
                 }
             });
           	return trueOrFalse(bool);
        }	
        
        function trueOrFalse(bool){
            return bool;
    	}       

	    function uploadUAV() {
	    	

	    	if(validatorUpload.form())
	    	{ 
	    		if(checkDataAvailable()==false)
	    		{
			    		if($("#mp4FileUpload").val()!="")
			   			{
			    			$("#errorUploadFile").html("");
			   			}
			    		else
			   			{
			    				if("${uavDetails.video_file_name}"=="")
			    				{
					   				$("#errorUploadFile").html("Please upload MP4 file.");
					   				return false;
			    				}
			   			}
			    		
			    		$('#mp4FileUpload').get(0).type = 'text';
			    		
						$(".page-loader").show();
						document.getElementById("uavForm").submit();
	    		}
	    		else
	    		{
	   				$("#from_stationError").html("Data already exists with work id, survey date, from station & to station");
	   				return false;	    			
	    		}
	    	}
		}
        
        
       $('.validate-dropdown').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });

       $('input').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
       
       
       var validatorUpload =	$('#uavForm').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
		  			 	required: true
		  			  },"work_id_fk": {
	  			 		required: true
	  			 	  },"survey_date": {
	  		 		    required: true
	  			 	  },"from_station": {
	  			 		required: true
	  			 	  },"to_station": {
	  		 		    required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
		  				 required: 'This field is required',
		  			  },"work_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"survey_date": {
	  		 			required: ' This field is required'
	  		 	  	  },"from_station": {
	  				 	required: 'This field is required',
	  			 	  },"to_station": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						document.getElementById("project_id_fkError").innerHTML="";
				 		error.appendTo('#project_id_fkError');
					}else if (element.attr("id") == "work_id_fk" ){
						document.getElementById("work_id_fkError").innerHTML="";
				 		error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "survey_dateUpload" ){
						document.getElementById("survey_dateUploadError").innerHTML="";
					 	error.appendTo('#survey_dateUploadError');
					}else if(element.attr("id") == "from_station" ){
						document.getElementById("from_stationError").innerHTML="";
					 	error.appendTo('#from_stationError');
					}else if(element.attr("id") == "to_station" ){
						document.getElementById("to_stationError").innerHTML="";
					 	error.appendTo('#to_stationError');
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
   	

       function getWorksList(projectId) {
       	$(".page-loader").show();
           $("#work_id_fk option:not(:first)").remove();

           if ($.trim(projectId) != "") {
               var myParams = { project_id_fk: projectId };
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getWorkListForExpenditureForm",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                               var workName = '';
                               if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                               $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
       

        function getProjectsList(project) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id = $("#work_id").val();
        	
            if ($.trim(project_id_fk) == "") {
            	$("#project_id_fk option:not(:first)").remove();
            	var myParams = {};
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsList",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (project == val.project_id_fk)?'selected':'';
	                            $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk) +'-'+ $.trim(val.project_name) + '</option>');
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