<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Land Acquisition - Update Forms - PMIS</title>
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child {
            white-space: inherit;
        }
        
        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
        .fs-350 {
		    width: 350px;
		    max-width: 350px;
		}
		 .dataTables_filter label::after{
         	content:'';
         }
          .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
         .fw-115{
         		width:217px !important;
        		max-width:217px;
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
			.mb-md-2{
				margin-bottom:2rem;
			}
			/* .dataTables_filter label input {
			    width: 100% !important;
			} */
			.r-300{
				width:30vw !important;
        		max-width:30vw;
			}
			/*  .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        } */
	        .fw-111{
	        	width:30vw;
	        	min-width:30vw; 
	        }
		}
		
		@media only screen and (min-width: 576px) and (max-width: 839px){
			.fs-md-74rem{
			    font-size: .74rem;
			}
			 .fw-111{
	        	width:30vw !important;
	        	min-width:30vw;
	        }
		}
		.fw-last{
				width:116px !important;
	        	min-width:116px;
		}
		.no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
	    .m-n1 {
        margin: -2rem auto 0;
    }

    .template-btn {
        text-shadow: 1px 1px 1px black;
    }

    @media only screen and (max-width: 767px) {
        .mob-mar {
            text-align: left;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    @media(max-width: 375px){
    	.fw-111{
    		min-width: 22vw;
    	}
    	.fw-last{
    		min-width: 85px;
    	}
    }
    </style>
</head>

<body>

    <!-- header  starts-->
      <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="container-padding">
        
       <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <!-- <h6 class="hide-on-med-and-down">Update Land Acquisition</h6> -->
							<h6 class="mob-mar">Land Acquisition Process</h6>
							<div class="col s12 m12 right-align exportButton">
    							<div class="m-n1">
    								 <a href="<%=request.getContextPath() %>/land-acquisition-process" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add </strong></a>   
                                     <a href="javascript:void(0);" onclick="exportLA();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>                                     
    							</div>
    						</div>	
                        </div>
                    </span>          
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
                        <div class="row no-mar" >
                            <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label">Select Project</p>
                                    <select id="project_id_fk" name="project_id_fk"  class="searchable validate-dropdown" onchange="getLandAcquisitionList();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                            </div>
                            
<!--                              <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label">Select LA File Type</p>
<select id="la_file_type" class="searchable" onchange="getLandAcquisitionList();">
<option value="">Select</option>
<option value="PRIVATE">Private</option>
<option value="GOVERNMENT">Government</option>
<option value="FOREST">Forest</option>
</select>	
                            </div>    -->                        
                            
                     

                            <div class="col s12 m4 l2 center-align input-field">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 10px;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="land-acquisition-datatable" class="mdl-data-table">
<thead> <tr> <th>LA ID</th> <th>Project ID </th><th>Project Name </th>   <th>Submission to GM</th> <th>Approval of GM</th> <th>Draft Letter RP</th> <th>Date Approval RP</th> <th>Date Upload Gazette RP</th> <th>Publication Gazette RP</th> <th>Date Proposal to DC</th> <th>Date Nomination CA</th> <th>Draft Letter CA</th> <th>Date Approval CA</th> <th>Date Upload Gazette CA</th> <th>Publication Gazette CA</th> <th>Last Updated</th> <th class="no-sort">Action</th> </tr> </thead>
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
<!-- update popup starts -->
	<div id="upload_template" class="modal">
		<div class="modal-content">
			<div class="center-align p-2 bg-m modal-title headbg">
				<h6>Upload Land Acquisition Data </h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-la"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="laUploadFile"
												name="laUploadFile" required="required">
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text">
										</div>
									</div>
								</div>
								<div class="col m2 hide-on-small-only"></div>
							</div>
						</div>
					</div>
					<div class="row no-mar">
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="submit" class="btn waves-effect waves-light bg-m"
									style="width: 100%;">Update</button>
							</div>
						</div>
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="button" class="btn waves-effect waves-light bg-s"
									style="width: 100%;" onclick="closeUploadLAModal();">Cancel</button>
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
    <!-- footer  -->
  <jsp:include page="../layout/footer.jsp"></jsp:include>
  
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
    
	
	<form name="getForm" action="<%=request.getContextPath()%>/land-acquisition-process"  id="getForm" method="post">
    	<input type="hidden" name="la_id" id="la_id" />
    </form>
    <script>
    

        $(document).ready(function () {

            

            
            
        	  $('.modal').modal();
        	  $('select:not(.searchable)').formSelect();
              $('.searchable').select2();
              $('.close-message').delay(3000).fadeOut('slow')
              
            
              
      	      getLandAcquisitionList();
      	      
      	  	 if(window.matchMedia("(max-width: 769px)").matches){
      		    	$('tbody.web').removeAttr('id');
      		        $('#mobView').css({'display':'block'});
      		      	
      		    } else{
      		    	$('#webView').css({'display':'block'});
      		    }
        });
        
        
        function exportLA() {
            var project_id   = $("#project_id_fk").val();
            var la_file_type = $("#la_file_type").val();

            // Construct URL for download with query parameters
            var url = "<%=request.getContextPath()%>/export-land-acquisition?project_id=" 
                      + encodeURIComponent(project_id) 
                      + "&la_file_type=" + encodeURIComponent(la_file_type);

            // Trigger download
            window.location.href = url;
        }
       

        function clearFilters() {
            $('.searchable').select2();
            window.localStorage.setItem("laFilters",'');
            window.location.href = "<%=request.getContextPath()%>/land-acquisition-process-grid";
            var table = $('#land-acquisition-datatablet').DataTable();
        	table.draw( true );
            //getLandAcquisitionList();
        }


        var queue = 1;
        function getLandAcquisitionList() {

            $(".page-loader-2").show();

            var project_id   = $("#project_id_fk").val();
            var la_file_type = $("#la_file_type").val();

            if ($.fn.DataTable.isDataTable('#land-acquisition-datatable')) {
                $('#land-acquisition-datatable').DataTable().destroy();
            }

            $('#land-acquisition-datatable').DataTable({

                processing: true,
                serverSide: true,
                searching: true,
                ordering: false,
                lengthMenu: [10, 25, 50, 100],
                pageLength: 10,
                scrollX: true,
                autoWidth: false,

                ajax: {
                    url: "<%=request.getContextPath()%>/ajax/get-land-acquisition-process",
                    type: "GET",
                    data: function(d) {
                        d.project_id   = $("#project_id_fk").val();
                        d.la_file_type = $("#la_file_type").val();
                        d.draw         = d.draw;
                        d.start        = d.start;
                        d.length       = d.length;
                        d.searchValue       = d.search.value; // server-side search
                    },
                    error: function () {
                        $(".page-loader-2").hide();
                        alert("Error loading data");
                    }
                },

                columns: [
                    { data: "laId", width: "220px" },
                    { data: "projectId" },
                    { data: "projectName" },

                    { data: "submissionOfProposalToGM", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "approvalOfGM", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "draftLetterToConForApprovalRP", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfApprovalOfConstructionRP", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfUploadingOfGazetteNotificationRP", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "publicationInGazetteRP", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfProposalToDCForNomination", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfNominationOfCompetentAuthority", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "draftLetterToConForApprovalCA", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfApprovalOfConstructionCA", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "dateOfUploadingOfGazetteNotificationCA", render: function(d){ return formatDateDDMMYYYY(d); } },
                    { data: "publicationInGazetteCA", render: function(d){ return formatDateDDMMYYYY(d); } },

                    {
                        data: "modifiedDate",
                        render: function(d){
                            return formatDateDDMMYYYY(d);
                        }
                    },

                    {
                        data: "id",
                        orderable: false,
                        width: "80px",
                        render: function (id) {
                            return '<a class="btn bg-m" ' +
                                   'href="<%=request.getContextPath()%>/land-acquisition-process/' + id + '">' +
                                   '<i class="fa fa-pencil"></i>' +
                                   '</a>';
                        }
                    }
                ],

                drawCallback: function () {
                    $(".page-loader-2").hide();
                }
            });
        }
        
        
        function formatDateDDMMYYYY(dateString) {
            if (!dateString) return "-";

            var date = new Date(dateString);
            if (isNaN(date)) return dateString;

            var day   = ("0" + date.getDate()).slice(-2);
            var month = ("0" + (date.getMonth() + 1)).slice(-2);
            var year  = date.getFullYear();

            return day + "-" + month + "-" + year;
        }       


        
        function exportLA() {
            // Get current filters
            var project_id   = $("#project_id_fk").val();
            var la_file_type = $("#la_file_type").val();

            // Fetch data from server (all filtered rows)
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/get-land-acquisition-process",
                type: "GET",
                data: {
                    project_id: project_id,
                    la_file_type: la_file_type,
                    start: 0,
                    length: 10000 // fetch all rows
                },
                success: function(response) {
                    var data = response.data; // array of objects

                    if (!data || data.length === 0) {
                        alert("No data to export");
                        return;
                    }

                    // Prepare worksheet data with header row
                    var headers = [];
                    $('#land-acquisition-datatable thead th').each(function() {
                        // Exclude action column if you want
                        if (!$(this).hasClass('no-sort')) {
                            headers.push($(this).text().trim());
                        }
                    });

                    var ws_data = [];
                    ws_data.push(headers); // first row = header

                    data.forEach(function(row) {
                        var rowData = [];
                        // map columns in the same order as table headers
                        rowData.push(row.laId);
                        rowData.push(row.projectId);
                        rowData.push(row.projectName);
                        rowData.push(row.status);
                        rowData.push(row.laFileType);
                        rowData.push(row.submissionOfProposalToGM);
                        rowData.push(row.approvalOfGM);
                        rowData.push(row.draftLetterToConForApprovalRP);
                        rowData.push(row.dateOfApprovalOfConstructionRP);
                        rowData.push(row.dateOfUploadingOfGazetteNotificationRP);
                        rowData.push(row.publicationInGazetteRP);
                        rowData.push(row.dateOfProposalToDCForNomination);
                        rowData.push(row.dateOfNominationOfCompetentAuthority);
                        rowData.push(row.draftLetterToConForApprovalCA);
                        rowData.push(row.dateOfApprovalOfConstructionCA);
                        rowData.push(row.dateOfUploadingOfGazetteNotificationCA);
                        rowData.push(row.publicationInGazetteCA);
                        rowData.push(row.modifiedDate);
                        ws_data.push(rowData);
                    });

                    // Create worksheet
                    var ws = XLSX.utils.aoa_to_sheet(ws_data);

                    // Apply green background to header row
                    var range = XLSX.utils.decode_range(ws['!ref']);
                    for (var C = range.s.c; C <= range.e.c; ++C) {
                        var cell_address = XLSX.utils.encode_cell({r:0, c:C});
                        if(!ws[cell_address]) continue;
                        ws[cell_address].s = {
                            fill: { fgColor: { rgb: "00FF00" } }, // green background
                            font: { bold: true }
                        };
                    }

                    // Create workbook and add worksheet
                    var wb = XLSX.utils.book_new();
                    XLSX.utils.book_append_sheet(wb, ws, "Land Acquisition");

                    // Export Excel
                    XLSX.writeFile(wb, "Land_Acquisition.xlsx");
                },
                error: function() {
                    alert("Failed to fetch data for export");
                }
            });
        }
        	



    </script>
</body>

</html>