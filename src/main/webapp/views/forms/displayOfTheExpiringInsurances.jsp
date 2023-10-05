<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display of the expiring Insurance's - PMIS</title>
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
    	 
      	 .fw-10{
    	 	width:100px !important;
    	 	max-width:100px;
    	 }  
     	 .fw-320{
    	 	width:320px !important;
    	 	max-width:320px;
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
								<h6 class="mob-mar">List of Contracts Closer to Insurance Expiry Date</h6>	
							</div>
						</span>
					</div>
					<div id="docx" style="display:none;">
					<div class="WordSection1">




<html>
<head>
    <style type="text/css">
    @page Section1 {
        margin:0.75in 0.75in 0.75in 0.75in;
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
        
        <span style="text-align:left;"><img src="https://myfiles.space/user_files/173095_15c158b78d540b9a/173095_custom_files/img169346494752.png" width="95" height="69.774"></span>
        <span style="text-align:right;"><img src="https://myfiles.space/user_files/173095_15c158b78d540b9a/173095_custom_files/img1693464948.png" width="235" height="69.1176"></span>
        </p>

      </div>
      &nbsp;
    </td>

    <td>
      <div style='mso-element:footer' id=f1>
        <p class=headerFooter>
       <span style="font-size: 14px; line-height: 115%; font-family: 'Century Gothic',sans-serif; color: black;"><br><img style="width: 767px; height: 60.9686px;" src="https://myfiles.space/user_files/173095_15c158b78d540b9a/173095_custom_files/img169346494879.png" width="767" height="60.9686"></span>
<span style='color:#005686'>Page</span>
                <span style='color:#005686;mso-no-proof:yes'>1</span>
                <span style='color:#005686'> </span>
                <span style='font-size:10.0pt;color:#005686'>
                    <o:p></o:p>
                </span>       
       </p>
       
      </div>
      &nbsp;
</td></tr></table>

<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">Letter No. MRVC/ACCTS/EXP/Insurance/<u id="currentyeartext">2020-21</u></span></strong><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></strong><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">Date:&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;&shy;<span id="insurancedate2months"></span>&nbsp;</span></strong></p>
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">DY.CPM-III MRVC</span></strong></p>
<table style="border-collapse: collapse; border: none;">
<tbody>
<tr>
<td style="width: 49.65pt; padding: 0cm 5.4pt; vertical-align: top;">
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 200%;"><strong><span style="font-size: 14px; line-height: 200%; font-family: 'Arial',sans-serif; color: black;">SUB:</span></strong></p>
</td>
<td style="width: 448.6pt; padding: 0cm 5.4pt; vertical-align: top;">
<p style="margin: 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; margin-top: 7.2pt; line-height: 115%;"><strong><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">Extension of Insurances against CA/ PO. No. MRVC/W/109/CA of <u id="insuranceContractorName">M/S M VENKATA RAO INFRA PROJECTS PVT LTD</u></span></strong></p>
</td>
</tr>
</tbody>
</table>
<p><span style="height:30px;">&nbsp;</span></p>
<p style="margin: 6pt 9.7pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; text-indent: 50.4pt; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">The validity of the following Insurances received from </span><strong><u><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;" id="insuranceContractorName1">VENKATA RAO INFRA PROJECTS PVT</span></u></strong><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">expires in <strong><u id="insuranceExpiryDate">02-Sep-23</u></strong>. You are requested to arrange for extension of&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">Insurance before the expiry date in case the contract is not completed in all respects.&nbsp;The contractor is required to provide insurance cover from the start date to the end date of the Defect Liability Period.&nbsp;</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">In case the contract has been completed satisfactorily in all respects, advice to this effect must be sent to this office before <span id="insuranceExpiryDate1">02-Sep-23</span>.&nbsp;</span></p>

<p>

<table id="appendData" class="mdl-data-table no-footer dataTable" border="1" cellspacing="2" cellpadding="2" style="font-family:arial;font-size:10.5px;width:100%" >
	<thead>
	<tr><th>Insurance Type</th><th>Insurance Number</th><th>Insurance Value (INR)</th><th>Agency</th><th>Valid Upto</th></tr></thead>
	<tbody></tbody>
</table>

</p>

<p style="margin: 6pt 9.7pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: justify; text-indent: 50.4pt; line-height: 115%;"><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif; color: black;">If this office does not receive the extension/reply within the stipulated date mentioned above, MRVC will take necessary action as per the clause of Contract Agreement</span><span style="font-size: 14px; line-height: 115%; font-family: 'Arial',sans-serif;">&nbsp;</span></p>

<p><span style="height:30px;">&nbsp;</span></p>

<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif; text-align: right;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;">FACAO(MRVC)</span></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;">Copy to:</span></strong></p>
<p style="margin: 6pt 32.4pt .0001pt 0cm; font-size: 15px; font-family: 'Calibri',sans-serif;"><strong><u><span style="font-size: 14px; font-family: 'Arial',sans-serif; color: black;" id="insuranceContractorAddress">VENKATA RAO INFRA PROJECTS PVT LTD,&nbsp;B-212, Western Edge-II, Behind Metro Mall, Borivali(E), Mumbai- 400066</span></u></strong></p>
<br clear=all style='mso-special-character:line-break; page-break-before:always'>



</div></body>
</html>
</div></div>

					<div class="row">
						<div class="col m12 s12">
						<form id="contractReportForm" name="contractReportForm" method="post">
							<table id="datatable-contract" class="mdl-data-table">
								<thead>
									<tr>
										<th class="no-sort fw-10">S. No.</th>
										<th class="no-sort fw-150">Contract ID</th>
										<th class="no-sort fw-320">Contract Short Name</th>
										<th class="no-sort fw-320">Contractor Name</th>
										<th class="no-sort fw-320">Insurance Type</th>
										<th class="no-sort fw-320">Insurance No.</th>
										<th class="no-sort fw-150">No. of Insurances</th>
										<th class="no-sort fw-150">Expiry Month</th>
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
    
    function export2Word( element,row,contract_id,insurance_number ) {

    	
		$.ajax({url : "<%=request.getContextPath()%>/ajax/get-contract-insurance-details",type:"POST",data: {contract_id:contract_id,insurance_number:insurance_number},success : function(data){  
			if(data != null && data != '' && data.length > 0)
			{
				$.each(data,function(key,val){
					$("#currentyeartext").html(getCurrentFinancialYear());
					
					var SplitStr=val.insurance_valid_upto;
					SplitStr=SplitStr.toString();
					var SplitStrwith=SplitStr.split("-");
					
					var dt = new Date(val.insurance_valid_upto);
					
					
					var subdate=addDate(dt, -2, 'months');
					$("#insurancedate2months").html(convert(subdate));
					$("#insuranceNumber").html(val.insurance_number);
					$("#insuranceDate").html(val.insurance_date);
					$("#insuranceAmount").html(val.insurance_value);
					$("#insuranceContractorName").html(val.contractor_name);
					$("#insuranceContractorName1").html(val.contractor_name);
					$("#insuranceExpiryDate").html(val.insurance_valid_upto);
					$("#insuranceExpiryDate1").html(val.insurance_valid_upto);
					$("#insuranceContractorAddress").html(val.contractor_name+', '+val.address);
					$("#insuranceBankAddress").html(val.issuing_bank);
					$("#insuranceDateonExpiry").html(val.insurance_date);
					$("#insuranceExpiryDate2").html(val.insurance_valid_upto);
					$("#insuranceAmountNew").html(val.insurance_value);
					
					var htmlData="<tr><td>"+val.insurance_type_fk+"</td><td>"+val.insurance_number+"</td><td>"+val.insurance_value+"</td><td>"+val.issuing_agency+"</td><td>"+val.insurance_valid_upto+"</td></tr>";
					
					$("#appendData tbody").append(htmlData);
					
					
				
				
				});
				
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
		    	   link.download = 'Insurance Expiry Letter_'+contract_id; // default name without extension 
		    	   document.body.appendChild(link);
		    	   if (navigator.msSaveOrOpenBlob ) navigator.msSaveOrOpenBlob( blob, 'Insurance_Expiry_Letter.doc'); // IE10-11
		    	       else link.click();  // other browsers
		    	   document.body.removeChild(link);	
		    	       window.location.reload();
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
	 
		$.ajax({url : "<%=request.getContextPath()%>/ajax/generate-insurance-contractual-letters",type:"POST",success : function(data){  
				if(data != null && data != '' && data.length > 0){
					var iteration=1;
	         		$.each(data,function(key,val){
	         			var actionsDownload ="";
	         			if(new Date(val.insurance_valid_upto)<new Date())
         				{
         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.insurance_number+'") class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
         				}
	         			else
         				{
         			 		actionsDownload = '<a href="javascript:void(0);"  onclick=export2Word(window.docx,'+iteration+',"'+val.contract_id+'","'+val.insurance_number+'") class="btn waves-effect waves-light bg-s t-c" title="Download"><i class="fa fa-download"></i></a>';    
         				}

	                   	var rowArray = []; 
	                   	
	                   	var StatusArray="";
                   		if(val.insurance_letter_status=="Submitted")
                   		{
                   			StatusArray='<select class="searchable" disabled name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.insurance_number+'");>';
                   		}
                   		else
               			{
                   			StatusArray='<select class="searchable" name="letter_status" id="letter_status'+iteration+'" onChange=updateLetterStatus("'+val.contract_id+'",this.value,"'+val.insurance_number+'");>';
               			}
	                   	
	                   	
	                   	if(val.insurance_letter_status=="Not Submitted")
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted" selected>Not Submitted</option><option value="Submitted">Submitted</option>';
                   		}
	                   	else if(val.insurance_letter_status=="Submitted")
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted" selected>Submitted</option>';
                   		}
	                   	else
                   		{
	                   		StatusArray=StatusArray+'<option value="Not Submitted">Not Submitted</option><option value="Submitted">Submitted</option>';
                   		}
	                   	
	                   	var insurancelink="<a href='/pmis/get-contract/"+val.contract_id+"'>"+val.insurance_number+"</a>";
	                   	
	                   	insurancelink=replaceAll(insurancelink, ',',',<br>');
	                   	
	                   	StatusArray=StatusArray+'</select>';
                        var table11="<tr><td>"+iteration+"</td><td>"+val.contract_id+"</td><td>"+val.contract_short_name+"</td><td>"+val.contractor_name+"</td><td>"+val.insurance_type_fk+"</td><td>"+val.insurance_count+"</td><td>"+val.insurance_number+"</td><td>"+val.valid_upto+"</td><td>"+StatusArray+"</td></tr>";
	                   	rowArray.push($.trim(iteration));
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_short_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.insurance_type_fk));
	                   	rowArray.push($.trim(insurancelink));
	                   	rowArray.push($.trim(val.insurance_count));
	                   	rowArray.push($.trim(val.insurance_valid_upto));
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
    
    function replaceAll(str, find, replace) {
  	  return str.replace(new RegExp(find, 'g'), replace);
  }   
    
    function updateLetterStatus(contractid,letter_status,insurance_number)
    {
	 	var myParams = {contract_id : contractid,letter_status:letter_status,insurance_number:insurance_number};
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/UpdateInsuranceLetterStatus",
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