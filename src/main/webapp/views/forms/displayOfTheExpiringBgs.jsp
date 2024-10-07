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
 
 .datepicker-select
 {
     display: none !important;
 
 }  

@page WordSection1{
         mso-page-orientation: landscape;
         size: 841.95pt 595.35pt; /* EU A4 */
         /* size:11.0in 8.5in; */ /* US Letter */
     }
     div.WordSection1 {
         page: WordSection1;
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
					<div id="docx" style="display:none;">
					<div class="WordSection1">




<html>
<head>
    <style type="text/css">
    @page Section1 {
        margin:0.15in 0.25in 0.25in 0.35in;
        size:670.95pt 595.35pt;
        mso-page-orientation:Portrait;
        mso-header-margin:0.5in;
        mso-header: h1;
        mso-footer-margin:0.5in;
        mso-footer: f1;
    }

    div.Section1 {page:Section1;}

    p.headerFooter { margin:0in; text-align: center; }
    </style>
</head>
<body><div class=Section1>

<table style='margin-left:50in;'><tr style='height:1pt;mso-height-rule:exactly'>
    <td>
      <div style='mso-element:header' id=h1>
        <p class=headerFooter>
        
        <span style="text-align:left;"><img src="https://i.postimg.cc/prFXBmn6/img169346494752.png" width="95" height="69.774"></span>
        <span style="text-align:right;"><img src="https://i.postimg.cc/VNgSS0j9/img1693464948.png" width="235" height="69.1176"></span>
        </p>

      </div>
      &nbsp;
    </td>

    <td>
      <div style='mso-element:footer' id=f1>
        <p class=headerFooter>
       <span style="font-size: 14px; line-height: 115%; font-family: 'Century Gothic',sans-serif; color: black;"><br><img style="width: 767px; height: 60.9686px;" src="https://i.postimg.cc/0QMbtk6h/img169346494879.png" width="767" height="60.9686"></span></p>
      </div>
      &nbsp;
</td></tr></table>

<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">Letter No. MRVC/ACCTS/EXP/BG/<u id="currentyeartext">2020-21</u></span></strong><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></strong><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">Date:&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;<span id="bgdate2months"></span>&nbsp;</span></strong></p>
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;" id="hodContract">DY.CPM-III MRVC</span></strong></p>
<table style="border-collapse: collapse; border: none;">
<tbody>
<tr>
<td style="width: 49.65pt; padding: 0cm 5.4pt; vertical-align: top;">
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">SUB:</span></strong></p>
</td>
<td style="width: 448.6pt; padding: 0cm 5.4pt; vertical-align: top;">
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 115%;"><strong><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">Extension of BG No <u id="bgNumber">IBG103814</u>&nbsp; &nbsp; &nbsp; &nbsp;Dt: <u id="bgDate">03-Mar-21</u> for Rs <u id="bgAmount">3,00,00,000.00</u> against CA/ PO. No. <u id="bgLoaNumber">MRVC/W/109/CA</u> of <u id="bgContractorName">M/S M VENKATA RAO INFRA PROJECTS PVT LTD</u></span></strong></p>
</td>
</tr>
</tbody>
</table>
<p style="margin: 6pt 9.7pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; text-indent: 50.4pt; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">The validity of the subject&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">Bank Guarantee received from </span><strong><u><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;" id="bgContractorName1">VENKATA RAO INFRA PROJECTS PVT</span></u></strong><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">expires on <strong><u id="bgExpiryDate">02-Sep-23</u></strong>. You are requested to arrange for extension of&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">BG before the expiry date in case the contract is not completed in all respects.&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">In case the contract has been completed satisfactorily in all respects, advice to this effect must be sent to this office before <span id="bgExpiryDate1">02-Sep-23</span>.&nbsp;</span></p>
<p style="margin: 6pt 9.7pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; text-indent: 50.4pt; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">If this office does not receive the extension/reply within the stipulated date before the expiry date of BG, the issuing bank should be advised by this office to deposit the amount due to MRVC under guarantee. A copy of this letter is being endorsed to the firm's bank for taking necessary action.</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif;">&nbsp;</span></p>
<br><br>
<p><span style="height:30px;">&nbsp;</span></p>

<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: right;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;">FACAO(MRVC)</span></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;">Copy to:</span></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><u><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;" id="bgContractorAddress">VENKATA RAO INFRA PROJECTS PVT LTD,&nbsp;B-212, Western Edge-II, Behind Metro Mall, Borivali(E), Mumbai- 400066</span></u></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">Contractor is advised to take speedy steps for extension of the above BG [or submit the completion report to enable this office to release the said BG].</span></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;">Copy to:</span></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;" id="bgBankAddress">The Manager, <u>HDFC BANK LTD,1<sup>st</sup> Floor, Metropolis II, Opp. Vintage Hospital, St.Inez Panjim, GOA-403001</span></u></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">The above BG confirmed vide your L.N <u id="bgDateonExpiry">03-03-2021&nbsp;</u>as issued by you, expires on <u id="bgExpiryDate2">02-Sep-23</u>. If the firm fails to extend the same unconditionally before the expiry date, these offices claim for Rs. <span id="bgAmountNew">3,00,000</span> is deemed to be lodged on your bank which may please be noted.</span></p>


</div></body>
</html>
</div></div>





					<div class="row">
					
							<div class="col m12 l12s12">
									<div class="col s12 m2 input-field"></div>
							
	                                <div class="col s12 m4 input-field">
	                                    <input id="date_of_start" name="date_of_start" type="text" class="validate datepicker">
	                                    <label for="date_of_start">Start Date</label>
	                                     <span id="date_of_startError" class="error-msg" ></span>
	                                    <button type="button" id="date_of_start_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="date_of_end" name="date_of_end" type="text" class="validate datepicker">
	                                    <label for="date_of_end">End Date</label>
	                                     <span id="date_of_endError" class="error-msg" ></span>
	                                    <button type="button" id="date_of_end_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s12 m2 input-field"></div>
						</div>					
						<div class="col m12 s12">
						<form id="contractReportForm" name="contractReportForm" method="post">
							<table id="datatable-contract" class="mdl-data-table">
								<thead>
									<tr>
										<th>S. No.</th>
										<th class="no-sort fw-150">Contract ID</th>
										<th class="no-sort fw-250">Contract Short Name</th>
										<th class="no-sort fw-250">Contractor Name</th>
										<th class="no-sort fw-250">BG Type</th>
										<th class="no-sort fw-250">Issuing Bank</th>
										<th class="no-sort fw-250">BG Number</th>
										<th class="no-sort fw-150">Amount(INR)</th>
										<th class="no-sort fw-150">Expiry Date</th>
										<th class="no-sort">Download Letter</th>
										<th class="no-sort fw-250">Status</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
							</form>
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
    
    $(".datepicker-select").hide();
    
    $(document).on('focus', '.datepicker',function(){
        $(this).datepicker({
        	format:'dd-mm-yyyy',
   	    	onSelect: function () {
   	    	   $('.confirmation-btns .datepicker-done').click();
   	    	}
        })
    });  
    
 
    $('#date_of_start').change(function() {
        var date = $(this).val();
        getContractListWithDatesFilter(date,$('#date_of_end').val());
    });
    
    $('#date_of_end').change(function() {
        var date = $(this).val();
        getContractListWithDatesFilter($('#date_of_start').val(),date);
    });   
    
    function getCurrentFinancialYear() {
        var financial_year = "";
        var today = new Date();
        if ((today.getMonth() + 1) <= 3) {
            financial_year = (today.getFullYear() - 1) + "-" + today.getFullYear();
        } else {
            financial_year = today.getFullYear() + "-" + (today.getFullYear() + 1)% 100;
        }
        return financial_year;
    }
    
    function addDate(dt, amount, dateType) {
    	  switch (dateType) {
    	    case 'days':
    	      return dt.setDate(dt.getDate() + amount) && dt;
    	    case 'weeks':
    	      return dt.setDate(dt.getDate() + (7 * amount)) && dt;
    	    case 'months':
    	      return dt.setMonth(dt.getMonth() + amount) && dt;
    	    case 'years':
    	      return dt.setFullYear( dt.getFullYear() + amount) && dt;
    	  }
    	}
    function convert(str) {
    	  var date = new Date(str),
    	    mnth = ("0" + (date.getMonth() + 1)).slice(-2),
    	    day = ("0" + date.getDate()).slice(-2);
    	  return [date.getFullYear(), mnth, day].join("-");
    	}

    getContractList();
    
    var pageNo = window.localStorage.getItem("contractPageNo");
    
    function export2Word( element,row,contract_id,bg_number ) {

    	
		$.ajax({url : "<%=request.getContextPath()%>/ajax/get-contract-bg-details",type:"POST",data: {contract_id:contract_id,bg_number:bg_number},success : function(data){  
			if(data != null && data != '' && data.length > 0)
			{
				$.each(data,function(key,val){
					$("#currentyeartext").html(getCurrentFinancialYear());
					
					var SplitStr=val.bg_valid_upto;
					SplitStr=SplitStr.toString();
					var SplitStrwith=SplitStr.split("-");
					
					var dt = new Date(val.bg_valid_upto);
					
					
					var subdate=addDate(dt, -2, 'months');
					$("#bgdate2months").html(convert(subdate));
					$("#bgNumber").html(val.bg_number);
					$("#bgDate").html(val.bg_date);
					$("#bgAmount").html(val.bg_value);
					$("#bgContractorName").html(val.contractor_name);
					$("#bgLoaNumber").html(val.loa_letter_number);
					$("#bgContractorName1").html(val.contractor_name);
					$("#bgExpiryDate").html(val.bg_valid_upto);
					$("#bgExpiryDate1").html(val.bg_valid_upto);
					$("#bgContractorAddress").html(val.contractor_name+', '+val.address);
					$("#bgBankAddress").html(val.issuing_bank);
					$("#bgDateonExpiry").html(val.bg_date);
					$("#bgExpiryDate2").html(val.bg_valid_upto);
					$("#bgAmountNew").html(val.bg_value);
					$("#hodContract").html(val.hod_user_id_fk+' MRVC');
					
					
					
					 var html, link, blob, url, css;
			    	   
			    	   css = (
			    	     '<style>' +
			    	     '@page WordSection1{size: 670.95pt 595.35pt;mso-page-orientation: Portrait;}' +
			    	     'div.WordSection1 {page: WordSection1;}' +
			    	     '</style>'
			    	   );
			    	   
			    	   html = element.innerHTML;
			    	   blob = new Blob(['\ufeff', css + html], {
			    	     type: 'application/msword'
			    	   });
			    	   url = URL.createObjectURL(blob);
			    	   link = document.createElement('A');
			    	   link.href = url;
			    	   link.download = 'BG Expiry Letter_'+val.bg_number;  // default name without extension 
			    	   document.body.appendChild(link);
			    	   if (navigator.msSaveOrOpenBlob ) navigator.msSaveOrOpenBlob( blob, 'BG_Expiry_Letter.doc'); // IE10-11
			    	       else link.click();  // other browsers
			    	   document.body.removeChild(link);					
				
				});
			}
         		
			
		}});   	

    	
    	  
    	 }
    	 
    	 
    
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
	         			var actionsDownload ="";
	         			var color="";
	         			if(new Date(val.bg_valid_upto)<new Date())
         				{
	         				color="red";
         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.bg_number+'") class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
         				}
	         			else
         				{
         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.bg_number+'") class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
         				}

	                   	var rowArray = []; 
	                   	
	                   	var StatusArray="";
                   		if(val.bg_letter_status=="Submitted")
                   		{
                   			StatusArray='<select class="searchable" disabled name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.bg_number+'");>';
                   		}
                   		else
               			{
                   			StatusArray='<select class="searchable" name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.bg_number+'");>';
               			}
	                   	
	                   	
	                   	if(val.bg_letter_status=="Not Submitted")
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted" selected>Not Submitted</option><option value="Submitted">Submitted</option>';
                   		}
	                   	else if(val.bg_letter_status=="Submitted")
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted" selected>Submitted</option>';
                   		}
	                   	else
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted">Submitted</option>';
                   		}
	                   	
	                   	var bglink="<a href='/pmis/get-contract/"+val.contract_id+"'>"+val.bg_number+"</a>";
	                   	
	                   	StatusArray=StatusArray+'</select>';
                        var table11="<tr><td>"+iteration+"</td><td>"+val.contract_id+"</td><td>"+val.contract_short_name+"</td><td>"+val.contractor_name+"</td><td>"+val.bg_type_fk+"</td><td>"+val.issuing_bank+"</td><td>"+val.bg_number+"</td><td>"+val.bg_value+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+StatusArray+"</td></tr>";
	                   	rowArray.push($.trim(iteration));
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_short_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.bg_type_fk));
	                   	rowArray.push($.trim(val.issuing_bank));
	                   	rowArray.push($.trim(bglink));
	                   	rowArray.push($.trim(val.bg_value));
	                   	rowArray.push($.trim('<span style="color:'+color+';">'+val.bg_valid_upto+'</span>'));
	                   	rowArray.push($.trim(actionsDownload));
	                   	rowArray.push(StatusArray);
	                   	
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
    
    
    function getContractListWithDatesFilter(start,end){
    	if(start!="" && end!="")
    		{
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
				
				
				start=start.split("-").reverse().join("-");
				end=end.split("-").reverse().join("-");
			 	var myParams = {date_of_start : start,bg_date:end};
		        
			 
				$.ajax({url : "<%=request.getContextPath()%>/ajax/generate-bg-contractual-letters",type:"POST",data: myParams, cache: false,async: false,success : function(data){  
						if(data != null && data != '' && data.length > 0){
							var iteration=1;
			         		$.each(data,function(key,val){
			         			var actionsDownload ="";
			         			var color="";
			         			if(new Date(val.bg_valid_upto)<new Date())
		         				{
			         				color="red";
		         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.bg_number+'") disabled class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
		         				}
			         			else
		         				{
		         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.bg_number+'") class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
		         				}
		
			                   	var rowArray = []; 
			                   	
			                   	var StatusArray="";
		                   		if(val.bg_letter_status=="Submitted")
		                   		{
		                   			StatusArray='<select class="searchable" disabled name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.bg_number+'");>';
		                   		}
		                   		else
		               			{
		                   			StatusArray='<select class="searchable" name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.bg_number+'");>';
		               			}
			                   	
			                   	
			                   	if(val.bg_letter_status=="Not Submitted")
		                   		{
			                   		StatusArray=StatusArray+'<option value="Not Submitted" selected>Not Submitted</option><option value="Submitted">Submitted</option>';
		                   		}
			                   	else if(val.bg_letter_status=="Submitted")
		                   		{
			                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted" selected>Submitted</option>';
		                   		}
			                   	else
		                   		{
			                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted">Submitted</option>';
		                   		}
			                   	
			                   	var bglink="<a href='/pmis/get-contract/"+val.contract_id+"'>"+val.bg_number+"</a>";
			                   	
			                   	StatusArray=StatusArray+'</select>';
		                        var table11="<tr><td>"+iteration+"</td><td>"+val.contract_id+"</td><td>"+val.contract_short_name+"</td><td>"+val.contractor_name+"</td><td>"+val.bg_type_fk+"</td><td>"+val.issuing_bank+"</td><td>"+val.bg_number+"</td><td>"+val.bg_value+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+val.valid_upto+"</td><td>"+StatusArray+"</td></tr>";
			                   	rowArray.push($.trim(iteration));
			                   	rowArray.push($.trim(val.contract_id));
			                   	rowArray.push($.trim(val.contract_short_name));
			                   	rowArray.push($.trim(val.contractor_name));
			                   	rowArray.push($.trim(val.bg_type_fk));
			                   	rowArray.push($.trim(val.issuing_bank));
			                   	rowArray.push($.trim(bglink));
			                   	rowArray.push($.trim(val.bg_value));
			                   	rowArray.push($.trim('<span style="color:'+color+';">'+val.bg_valid_upto+'</span>'));
			                   	rowArray.push($.trim(actionsDownload));
			                   	rowArray.push(StatusArray);
			                   	
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
    }   
    
    
    function updateLetterStatus(contractid,letter_status,bg_number)
    {
	 	var myParams = {contract_id : contractid,letter_status:letter_status,bg_number:bg_number};
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/UpdateLetterStatus",
            data: myParams, cache: false,async: false,
            success: function (data) {

            }
        });    	
    }
    
    
    function getContractDownload(contract_id)
    {
    	$("#contractReportForm").attr("action","<%=request.getContextPath()%>/generate-contract-download/"+contract_id);
    	$("#contractReportForm").submit();
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