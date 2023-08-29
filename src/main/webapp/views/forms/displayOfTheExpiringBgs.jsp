<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display of the expiring BG's - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contract.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>        
        .input-field .searchable_label{
        	font-size:0.85rem;
        }
    	 .fw-250{
    	 	width:250px !important;
    	 	max-width:250px;
    	 }
    	 .fw-150{
    	 	width:150px !important;
    	 	max-width:150px;
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
         .no-wrap{
         	white-space:nowrap;
         }
         @media only screen and (max-width: 1366px) and (min-width:1023px){ 
         	tbody td{
         		padding:12px 10px !important;
         	}
         }
           @media only screen and (max-width: 820px){ 
			
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
			.fw-2{
				width:45vw !important;
        		max-width:45vw;  
        	}
        	.fw-1{
				width:30vw !important;
        		max-width:30vw; 
        	} 
        	.cw-m{width: 8rem;}
			
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
		.page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}		
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		
	.m-n1 {
        margin: -2rem auto 0;
    }


.sorting_asc:after, .sorting_desc:after {
  content: "" !important;
}

.sorting_asc:before, .sorting_desc:before {
  content: "" !important;
}


    @media only screen and (max-width: 820px) {
        .mob-mar {
            text-align: left;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    
  select {
    display: block !important;
}  
    
    </style>
</head>

<body>
 <jsp:include page="../layout/header.jsp"></jsp:include>
	<div class="row">
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6 class="mob-mar">List of Contracts Closer to BG Expiry Date</h6>	
							</div>
						</span>
					</div>

					<div class="row">
						<div class="col m12 s12">

							<table id="datatable-contract" class="mdl-data-table">
								<thead>
									<tr>
										<th>S. No.</th>
										<th class="no-sort fw-150">Contract ID</th>
										<th class="no-sort fw-250">Contract Short Name</th>
										<th class="no-sort fw-250">Contractor Name</th>
										<th class="no-sort fw-250">BG Type</th>
										<th class="no-sort">Issuing Bank</th>
										<th class="no-sort fw-250">BG Number</th>
										<th class="no-sort fw-150">Amount(INR)</th>
										<th class="no-sort fw-150">Expiry Date</th>
										<th class="no-sort">Download Letter</th>
										<th class="no-sort fw-250">Status</th>
										<th>Update</th>
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

		<div class="page-loader-2" style="display: none;">
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

		<!-- footer  -->
 	<jsp:include page="../layout/footer.jsp"></jsp:include>
 	

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	
    <script>

    getContractList();
    
    var pageNo = window.localStorage.getItem("contractPageNo");
    
    function getContractList(){
    	$(".page-loader-2").show();
     	table = $('#datatable-contract').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-contract').DataTable({
			"bStateSave": true,  
     		fixedHeader: true,
           
         	//Default: Page display length
				"iDisplayLength" : 10,
				"iData" : {
					"start" : 52
				},"iDisplayStart" : 0,
				"drawCallback" : function() {
					var info = table.page.info();
					window.localStorage.setItem("contractPageNo", info.page);
				},
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric'
                },
                { targets:[3,4,5,6,7], className: 'hideCOl'},
                { targets:[1], className: 'cw-m'},
                { orderable: false, targets: [0,1,2,3,4,5,6,7,8] }
            ],
            // "ScrollX": true,
            //"scrollCollapse": true,
            "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
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
						}), self = this.api(), $searchButton = $('<i class="fa fa-search" title="Go" >')
					.click(function() {
						self.search(input.val()).draw();
					}), 
					$clearButton = $('<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton); 					
				}
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
		$.ajax({url : "<%=request.getContextPath()%>/ajax/generate-bg-contractual-letters",type:"POST",success : function(data){  
				if(data != null && data != '' && data.length > 0){
					var iteration=1;
	         		$.each(data,function(key,val){
	         			var actions = '<a href="javascript:void(0);"  class="btn waves-effect waves-light bg-m t-c" title="Refresh"><i class="fa fa-refresh"></i></a>'; 
	         			var actionsDownload = '<a href="javascript:void(0);"  onClick=getContractDownload("'+val.contract_id+'"); class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';                     	

	                   	var rowArray = []; 
	                   	
	                   	var StatusArray='<select class="searchable" name="status" id="status"><option>Not Submitted</option><option>Submitted</option></select>';
                        var table11="<tr><td>"+iteration+"</td><td>"+val.contract_id+"</td><td>"+val.contract_short_name+"</td><td>"+val.contractor_name+"</td><td>"+val.bg_type_fk+"</td><td>"+val.issuing_bank+"</td><td>"+val.bg_number+"</td><td>"+val.bg_value+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+StatusArray+"</td><td>"+actions+"</td></tr>";
	                   	rowArray.push($.trim(iteration));
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_short_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.bg_type_fk));
	                   	rowArray.push($.trim(val.issuing_bank));
	                   	rowArray.push($.trim(val.bg_number));
	                   	rowArray.push($.trim(val.bg_value));
	                   	rowArray.push($.trim(val.bg_valid_upto));
	                   	rowArray.push($.trim(actionsDownload));
	                   	rowArray.push(StatusArray);
	                   	rowArray.push(actions);   	                   	
	                   	
	                    table.row.add(rowArray).draw( true );
	                    
	                    iteration++;
					});
	         		if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	                var oTable = $('#datatable-contract').dataTable();
	                oTable.fnPageChange( pageNo );
	         		$(".page-loader-2").hide();
				}else{
					$(".page-loader-2").hide();
				}
				
			}});
    } 
    
    
    function getContractDownload(contract_id)
    {
	 	var myParams = {contract_id : contract_id};
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/get-contract-download",
            data: myParams, cache: false,async: false,
            success: function (data) {

            }
        });    	
    }

    
    var queue = 1;

	
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