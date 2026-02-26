<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Land Acquisition Process - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Land Acquisition Process - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-responsive-table.css" >
   <style>
   /* * {
	    box-sizing: border-box;
	    font-family: Arial, Helvetica, sans-serif;
	}
	
	body {
	    background: #fff;
	    margin: 0;
	    padding: 0;
	}
	
	.container {
	    width: 96%;
	    margin: 10px auto;
	}
	
	.title {
	    text-align: center;
	    font-weight: bold;
	    font-size: 15px;
	    margin-bottom: 8px;
	}
	
	.project-info {
	    border-top: 1px solid #000;
	    border-bottom: 1px solid #000;
	    padding: 8px 0;
	}
	
	.project-info table {
	    width: 100%;
	    border-collapse: collapse;
	}
	
	.project-info td {
	    padding: 4px 6px;
	    font-size: 11px;
	}
	
	.project-info input {
	    width: 100%;
	    border: none;
	    border-bottom: 1px solid #888;
	    font-size: 11px;
	    padding: 2px;
	}
	
	.section-title {
	    text-align: center;
	    font-weight: bold;
	    margin: 12px 0 5px;
	    font-size: 13px;
	}
	
	.table-wrapper {
	    border: 2px dashed #999;
	    padding: 4px;
	    margin-bottom: 15px;
	}
	
	.land-table {
	    width: 100%;
	    border-collapse: collapse;
	    table-layout: fixed;
	}
	
	.land-table th {
	    background: #0d5a78;
	    color: #fff;
	    font-size: 11px;
	    height: 160px;
	    padding: 6px 3px;
	    writing-mode: vertical-rl;
	    transform: rotate(180deg);
	    vertical-align: bottom;
	    border-right: 1px solid #fff;
	}
	
	.land-table td {
	    border: 1px solid #ccc;
	    height: 28px;
	}
	
	.land-table td input {
	    width: 100%;
	    height: 100%;
	    border: none;
	    padding: 4px;
	    font-size: 11px;
	}
	
	.button-row {
	    text-align: center;
	    margin: 20px 0;
	}
	
	.btn {
	    background: #0d5a78;
	    color: #fff;
	    border: none;
	    padding: 8px 35px;
	    margin: 0 10px;
	    font-size: 13px;
	    cursor: pointer;
	} */
	
	.m0{margin:0;}
    .w0{width: 0;}
    .area{
    		display:none; 
		    clear:both; 
		    padding: 10px;
		}
        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .primary-text-bold {
            color: #EA6A2A !important;
            font-weight: 600
        }

        .input-field .prefix {
            color: #999;
        }

        .input-field .prefix.active {
            color: #EA6A2A;
        }
		input[type="number"]~.units {
		    position: absolute;
		    right: 15px;
		    top: 15px;
		    border: 0;
		    opacity: 0.7;
		}
        h6.primary-text-bold.center-align {
            margin: 20px auto;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
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
        .error-msg label{color:red!important;}
        
        .pt-5 {
		    padding-top: .3rem !important;
		}
		.wd-wrap{white-space: pre-line;
    				overflow: hidden;
    				text-overflow: ellipsis;
    				display: inline-block;
    				font-size: 11px !important;
    				line-height: normal;}
    	.w250px{width: 250px;}
    	.section-title {
		    text-align: center;
		    font-weight: bold;
		    margin: 12px 0 5px;
		    font-size: 13px;
		}
		.table-inside{
			overflow-y: auto;
		}
    	@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.5em;
   					 margin-left: 11%;}
   		.bd-none{border: none !important;}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:18%;}
    	}
    	@media(max-width: 820px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	}
    	.filevalue {
            display: block;
            margin-top: 10px;
			font-size: .9rem;
        }
        td.center-align{
        	text-align:center !important;
        }
        
        @media only screen and (max-width: 820px){
			.mt-sm-n1rem{
				margin-top:-1rem !important;
			}
			.pt-5 {
			    padding-top: 0 !important;
			}
			.filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
		}
		
		 @media only screen and (max-width: 568px){
			.container{
				width:100%;
			}
		}
		@media(max-width: 575px){
			.row .col{margin: 10px auto;}
			.mn6tbpx{margin: -6px auto;}
			.area{padding: 10px;}
			.input-field>label{font-size: 11px;line-height: 1;}
			#private_div .radio-lbl{margin-left:0 !important;}
		}
		 #compensation_unitsError{
	   		float:right;	
	    }
	   .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
		.pdr3em{
			padding-right: 3em !important;
		} 
		.pdr4em{
			padding-right: 4em !important;
		}
		.pdr5em{
			padding-right: 5em !important;
		}
		.w85{
			width: 85% !important;
		}
		.w80{
			width: 79% !important;
		}
		.w75{
			width: 75% !important;
		}
		.w70{
			width: 70% !important;
		}
         .datepicker-min-today ~button{
			    position: absolute;
			    right: 15px;
			    top: 15px;
			    border: 0;
			    opacity: 0.7;
			    cursor: pointer;
			    background-color: transparent;
		}	
		
		.modal-content label,
		.modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1.25rem; 
		    color: #9e9e9e;
		}
		.modal-content [type="radio"]:not(:checked)+span, [type="radio"]:checked+span{
			padding-left:25px;
		}
		
				.modal-content label, .modal-content [type="checkbox"]+span:not(.lever) {
				    font-size: 1rem;
				    color: #9e9e9e;
		}
		.datepicker-controls .select-month input {
			    width: 80px;
			}	
            td .datepicker-modal{
			word-break: normal;
		    word-wrap: normal;
		    white-space: normal;
		}
		.mdl-data-table tbody td .datepicker-calendar .datepicker-table thead tr>th {
		    text-align: center;
		    line-height: 100%;
		    padding: 0 !important;
		    height: auto;
		    vertical-align: baseline;
		    background-color: transparent;
		}
		.datepicker-table thead tr, .datepicker-table thead tr:hover, .datepicker-table tbody tr, .datepicker-table tbody tr:hover{
			height: auto;
		}
		.mdl-data-table tbody td .datepicker-calendar .datepicker-table thead tr>th{
			width: 0 !important;
			border-bottom: none;
		}
		.mdl-data-table td .datepicker-button{
			top: 25px;
		}
		.mdl-data-table td input{
			min-width: 110px;
		}
	</style>
</head>

<body>

    <!-- header  starts-->
      <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->


 <div class="container-padding">
    <div class="col s12 m12">
        <div class="card">
            <div class="card-content">

                <div class="center-align">
                    <span class="card-title headbg">
                        <div class="center-align p-2 bg-m m-b-2">
                            <h6>Add Land Acquisition Process</h6>
                        </div>
                    </span>
                </div>

                <div class="container container-no-margin">

<form action="<%=request.getContextPath() %>/add-land-acquisition-process"
      id="landAcquisitionForm"
      method="post"
      enctype="multipart/form-data">


    <!-- ================= PROJECT ================= -->
<div class="row">
    <div class="col s6 m4 l4 input-field offset-m2">
        <p class="searchable_label">
            Project Name <span class="required">*</span>
        </p>

        <select id="projectId" name="projectId"
                class="searchable validate-dropdown" required>

            <option value="">Select</option>

            <c:forEach var="obj" items="${projectsList}">
                <option value="${obj.project_id}"
                    <c:if test="${LADetails != null && LADetails.projectId == obj.project_id}">
                        selected
                    </c:if>>
                    ${obj.project_id}
                    <c:if test="${not empty obj.project_name}"> - </c:if>
                    ${obj.project_name}
                </option>
            </c:forEach>

        </select>

        <span class="error-msg"></span>
    </div>
</div>


    <!-- ================= DECLARATION ================= -->
    <h6 class="center-align primary-text">Declaration of Special Railway Project</h6>

<div class="row">

<!-- ================= Submission of Proposal to GM ================= -->
<c:set var="formattedSubmissionOfProposalToGM" value="" />
<c:if test="${not empty LADetails.submissionOfProposalToGM}">
    
    <c:choose>
        <!-- If format is yyyy-MM-dd -->
        <c:when test="${fn:length(LADetails.submissionOfProposalToGM) == 10 
                      && fn:substring(LADetails.submissionOfProposalToGM,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.submissionOfProposalToGM}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate1"/>
            <fmt:formatDate value="${tempDate1}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedSubmissionOfProposalToGM"/>
        </c:when>

        <!-- If already dd-MM-yyyy -->
        <c:otherwise>
            <c:set var="formattedSubmissionOfProposalToGM" 
                   value="${LADetails.submissionOfProposalToGM}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="submissionOfProposalToGM"
           name="submissionOfProposalToGM"
           type="text"
           class="validate datepicker"
           value="${formattedSubmissionOfProposalToGM}" />
    <label for="submissionOfProposalToGM"
           class="${not empty formattedSubmissionOfProposalToGM ? 'active' : ''}">
        Submission of Proposal to GM
    </label>
    <button type="button"
            id="private_submission_proposal_gm_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>


<!-- ================= Approval of GM ================= -->
<c:set var="formattedApprovalOfGM" value="" />
<c:if test="${not empty LADetails.approvalOfGM}">
    <c:choose>
        <c:when test="${fn:length(LADetails.approvalOfGM) == 10 
                      && fn:substring(LADetails.approvalOfGM,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.approvalOfGM}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate2"/>
            <fmt:formatDate value="${tempDate2}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedApprovalOfGM"/>
        </c:when>
        <c:otherwise>
            <c:set var="formattedApprovalOfGM" 
                   value="${LADetails.approvalOfGM}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="approvalOfGM"
           name="approvalOfGM"
           type="text"
           class="datepicker"
           value="${formattedApprovalOfGM}" />
    <label for="approvalOfGM"
           class="${not empty formattedApprovalOfGM ? 'active' : ''}">
        Approval of GM
    </label>
    <button type="button"
            id="private_approval_gm_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>


<!-- ================= Draft Letter to CE/Con ================= -->
<c:set var="formattedDraftLetterToConForApprovalRP" value="" />
<c:if test="${not empty LADetails.draftLetterToConForApprovalRP}">
    <c:choose>
        <c:when test="${fn:length(LADetails.draftLetterToConForApprovalRP) == 10 
                      && fn:substring(LADetails.draftLetterToConForApprovalRP,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.draftLetterToConForApprovalRP}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate3"/>
            <fmt:formatDate value="${tempDate3}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedDraftLetterToConForApprovalRP"/>
        </c:when>
        <c:otherwise>
            <c:set var="formattedDraftLetterToConForApprovalRP" 
                   value="${LADetails.draftLetterToConForApprovalRP}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="draftLetterToConForApprovalRP"
           name="draftLetterToConForApprovalRP"
           type="text"
           class="datepicker"
           value="${formattedDraftLetterToConForApprovalRP}" />
    <label for="draftLetterToConForApprovalRP"
           class="${not empty formattedDraftLetterToConForApprovalRP ? 'active' : ''}">
        Draft Letter to CE/Con
    </label>
    <button type="button"
            id="private_draft_letter_ce_con_approval_dec_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>


<!-- ================= Date of Approval of Construction ================= -->
<c:set var="formattedDateOfApprovalOfConstructionRP" value="" />
<c:if test="${not empty LADetails.dateOfApprovalOfConstructionRP}">
    <c:choose>
        <c:when test="${fn:length(LADetails.dateOfApprovalOfConstructionRP) == 10 
                      && fn:substring(LADetails.dateOfApprovalOfConstructionRP,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.dateOfApprovalOfConstructionRP}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate4"/>
            <fmt:formatDate value="${tempDate4}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedDateOfApprovalOfConstructionRP"/>
        </c:when>
        <c:otherwise>
            <c:set var="formattedDateOfApprovalOfConstructionRP" 
                   value="${LADetails.dateOfApprovalOfConstructionRP}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfApprovalOfConstructionRP"
           name="dateOfApprovalOfConstructionRP"
           type="text"
           class="datepicker"
           value="${formattedDateOfApprovalOfConstructionRP}" />
    <label for="dateOfApprovalOfConstructionRP"
           class="${not empty formattedDateOfApprovalOfConstructionRP ? 'active' : ''}">
        Date of Approval of Construction
    </label>
    <button type="button"
            id="private_date_approval_ce_construction_dec_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>


<!-- ================= Uploading of Gazette Notification ================= -->
<c:set var="formattedDateOfUploadingOfGazetteNotificationRP" value="" />
<c:if test="${not empty LADetails.dateOfUploadingOfGazetteNotificationRP}">
    <c:choose>
        <c:when test="${fn:length(LADetails.dateOfUploadingOfGazetteNotificationRP) == 10 
                      && fn:substring(LADetails.dateOfUploadingOfGazetteNotificationRP,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.dateOfUploadingOfGazetteNotificationRP}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate5"/>
            <fmt:formatDate value="${tempDate5}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedDateOfUploadingOfGazetteNotificationRP"/>
        </c:when>
        <c:otherwise>
            <c:set var="formattedDateOfUploadingOfGazetteNotificationRP" 
                   value="${LADetails.dateOfUploadingOfGazetteNotificationRP}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfUploadingOfGazetteNotificationRP"
           name="dateOfUploadingOfGazetteNotificationRP"
           type="text"
           class="datepicker"
           value="${formattedDateOfUploadingOfGazetteNotificationRP}" />
    <label for="dateOfUploadingOfGazetteNotificationRP"
           class="${not empty formattedDateOfUploadingOfGazetteNotificationRP ? 'active' : ''}">
        Uploading of Gazette Notification
    </label>
    <button type="button"
            id="private_date_uploading_gazette_notification_dec_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>


<!-- ================= Publication in Gazette ================= -->
<c:set var="formattedPublicationInGazetteRP" value="" />
<c:if test="${not empty LADetails.publicationInGazetteRP}">
    <c:choose>
        <c:when test="${fn:length(LADetails.publicationInGazetteRP) == 10 
                      && fn:substring(LADetails.publicationInGazetteRP,4,5) == '-'}">
            <fmt:parseDate value="${LADetails.publicationInGazetteRP}" 
                           pattern="yyyy-MM-dd" 
                           var="tempDate6"/>
            <fmt:formatDate value="${tempDate6}" 
                            pattern="dd-MM-yyyy" 
                            var="formattedPublicationInGazetteRP"/>
        </c:when>
        <c:otherwise>
            <c:set var="formattedPublicationInGazetteRP" 
                   value="${LADetails.publicationInGazetteRP}" />
        </c:otherwise>
    </c:choose>
</c:if>

<div class="col s6 m4 l4 input-field">
    <input id="publicationInGazetteRP"
           name="publicationInGazetteRP"
           type="text"
           class="datepicker"
           value="${formattedPublicationInGazetteRP}" />
    <label for="publicationInGazetteRP"
           class="${not empty formattedPublicationInGazetteRP ? 'active' : ''}">
        Publication in Gazette
    </label>
    <button type="button"
            id="private_publication_gazette_dec_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>

</div>



    <!-- ================= NOMINATION ================= -->
    <h6 class="center-align primary-text">Nomination of Competent Authority</h6>

<div class="row">
<fmt:parseDate value="${LADetails.dateOfProposalToDCForNomination}" 
               pattern="yyyy-MM-dd" 
               var="parsedDateOfProposalToDCForNomination"/>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfProposalToDCForNomination"
           name="dateOfProposalToDCForNomination"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedDateOfProposalToDCForNomination}' pattern='dd-MM-yyyy'/>"/>

    <label for="dateOfProposalToDCForNomination"
           class="${not empty LADetails.dateOfProposalToDCForNomination ? 'active' : ''}">
        Proposal to DC
    </label>

    <button type="button"
            id="private_date_proposal_dc_nomination_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>

<fmt:parseDate value="${LADetails.dateOfNominationOfCompetentAuthority}" 
               pattern="yyyy-MM-dd" 
               var="parsedDateOfNominationOfCompetentAuthority"/>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfNominationOfCompetentAuthority"
           name="dateOfNominationOfCompetentAuthority"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedDateOfNominationOfCompetentAuthority}' pattern='dd-MM-yyyy'/>"/>

    <label for="dateOfNominationOfCompetentAuthority"
           class="${not empty LADetails.dateOfNominationOfCompetentAuthority ? 'active' : ''}">
        Nomination of Competent Authority
    </label>

    <button type="button"
            id="private_date_nomination_competent_authority_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>

<fmt:parseDate value="${LADetails.draftLetterToConForApprovalCA}" 
               pattern="yyyy-MM-dd" 
               var="parsedDraftLetterToConForApprovalCA"/>

<div class="col s6 m4 l4 input-field">
    <input id="draftLetterToConForApprovalCA"
           name="draftLetterToConForApprovalCA"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedDraftLetterToConForApprovalCA}' pattern='dd-MM-yyyy'/>"/>

    <label for="draftLetterToConForApprovalCA"
           class="${not empty LADetails.draftLetterToConForApprovalCA ? 'active' : ''}">
        Draft Letter to CE/Con
    </label>

    <button type="button"
            id="private_draft_letter_ce_con_approval_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>
<fmt:parseDate value="${LADetails.dateOfApprovalOfConstructionCA}" 
               pattern="yyyy-MM-dd" 
               var="parsedDateOfApprovalOfConstructionCA"/>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfApprovalOfConstructionCA"
           name="dateOfApprovalOfConstructionCA"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedDateOfApprovalOfConstructionCA}' pattern='dd-MM-yyyy'/>"/>

    <label for="dateOfApprovalOfConstructionCA"
           class="${not empty LADetails.dateOfApprovalOfConstructionCA ? 'active' : ''}">
        Approval of Construction
    </label>

    <button type="button"
            id="private_date_approval_ce_construction_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>

<fmt:parseDate value="${LADetails.dateOfUploadingOfGazetteNotificationCA}" 
               pattern="yyyy-MM-dd" 
               var="parsedDateOfUploadingOfGazetteNotificationCA"/>

<div class="col s6 m4 l4 input-field">
    <input id="dateOfUploadingOfGazetteNotificationCA"
           name="dateOfUploadingOfGazetteNotificationCA"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedDateOfUploadingOfGazetteNotificationCA}' pattern='dd-MM-yyyy'/>"/>

    <label for="dateOfUploadingOfGazetteNotificationCA"
           class="${not empty LADetails.dateOfUploadingOfGazetteNotificationCA ? 'active' : ''}">
        Uploading of Gazette Notification
    </label>

    <button type="button"
            id="private_date_uploading_gazette_notification_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>
<fmt:parseDate value="${LADetails.publicationInGazetteCA}" 
               pattern="yyyy-MM-dd" 
               var="parsedPublicationInGazetteCA"/>

<div class="col s6 m4 l4 input-field">
    <input id="publicationInGazetteCA"
           name="publicationInGazetteCA"
           type="text"
           class="datepicker"
           value="<fmt:formatDate value='${parsedPublicationInGazetteCA}' pattern='dd-MM-yyyy'/>"/>

    <label for="publicationInGazetteCA"
           class="${not empty LADetails.publicationInGazetteCA ? 'active' : ''}">
        Publication in Gazette
    </label>

    <button type="button"
            id="private_publication_gazette_nom_icon"
            class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</div>
</div>
<input type="hidden" id="id" name="id"
       value="<c:out value='${LADetails != null ? LADetails.id : ""}'/>"/>
       <input type="hidden" id="laId" name="laId"
       value="<c:out value='${LADetails != null ? LADetails.laId : ""}'/>"/>
<input type="hidden" id="privateLandsJson" name="privateLandsJson">
<input type="hidden" id="governmentLandsJson" name="governmentLandsJson">
<input type="hidden" id="forestLandsJson" name="forestLandsJson">
<br><br>
    </div>



<!-- ================= PRIVATE LAND ================= -->
<h6 class="center-align primary-text">Private Land</h6>
<div class="table-inside">
<table class="mdl-data-table mobile_responsible_table">
<thead>
<tr>
<th>District</th><th>Taluka</th><th>Village</th><th>Collector</th>
<th>Area (ha)</th><th>Chainage From</th><th>Chainage To</th>
<th>No. of Khasras</th><th>Proposal Submission</th><th>20A</th><th>20E</th>
<th>JM</th><th>20F</th><th>Grievance</th><th>Action</th>
</tr>
</thead>
<tbody id="privateTbody">
<c:if test="${not empty LADetails.privateLands}">
    <c:forEach var="pl" items="${LADetails.privateLands}" varStatus="status">
        <tr>
			<td><input type="text" name="privateLands[${status.index}].district" value="${pl.district}" /></td>
			<td><input type="text" name="privateLands[${status.index}].taluka" value="${pl.taluka}" /></td>
			<td><input type="text" name="privateLands[${status.index}].village" value="${pl.village}" /></td>
			<td><input type="text" name="privateLands[${status.index}].collector" value="${pl.collector}" /></td>
			
			<td><input type="text" name="privateLands[${status.index}].areaRequired" value="${pl.areaRequired}" /></td>
			<td><input type="text" name="privateLands[${status.index}].chainageFrom" value="${pl.chainageFrom}" /></td>
			<td><input type="text" name="privateLands[${status.index}].chainageTo" value="${pl.chainageTo}" /></td>
			
			<td><input type="text" name="privateLands[${status.index}].surveyNumbers" value="${pl.surveyNumbers}" /></td>

<td>
<fmt:parseDate value="${pl.submissionOfProposal}" pattern="yyyy-MM-dd" var="parsedDate"/>

<input type="text" class="datepicker"
    name="privateLands[${status.index}].submissionOfProposal"
    value="<fmt:formatDate value='${parsedDate}' pattern='dd-MM-yyyy'/>" />
</td>


<td>
    <fmt:parseDate value="${pl.notificationUnder20A}" pattern="yyyy-MM-dd" var="parsed20A"/>
    <input type="text" class="datepicker"
        name="privateLands[${status.index}].notificationUnder20A"
        value="<fmt:formatDate value='${parsed20A}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${pl.notificationUnder20E}" pattern="yyyy-MM-dd" var="parsed20E"/>
    <input type="text" class="datepicker"
        name="privateLands[${status.index}].notificationUnder20E"
        value="<fmt:formatDate value='${parsed20E}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${pl.jmMeasurement}" pattern="yyyy-MM-dd" var="parsedJM"/>
    <input type="text" class="datepicker"
        name="privateLands[${status.index}].jmMeasurement"
        value="<fmt:formatDate value='${parsedJM}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${pl.acquisitionUnder20F}" pattern="yyyy-MM-dd" var="parsed20F"/>
    <input type="text" class="datepicker"
        name="privateLands[${status.index}].acquisitionUnder20F"
        value="<fmt:formatDate value='${parsed20F}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <input type="text"
        name="privateLands[${status.index}].grievanceSurveyNos"
        value="${pl.grievanceSurveyNos}" />
</td>			<td>                <button type="button" class="btn red" onclick="removeRow(this,'privateTbody')">
                    <i class="fa fa-close"></i>
                </button></td>
        </tr>
    </c:forEach>
</c:if>
</tbody>

</table>
</div>
    <div class="section-title">
		    <button type="button" class="btn center-align primary-text" onclick="addPrivateRow()">+</button>
		</div>

<!-- ================= GOVERNMENT LAND ================= -->
<h6 class="center-align primary-text">Government Land</h6>
<div class="table-inside">
<table class="mdl-data-table mobile_responsible_table">
<thead>
<tr>
<th>District</th><th>Taluka</th><th>Village</th><th>Collector</th>
<th>Agency</th><th>Area (ha)</th><th>From</th><th>To</th>
<th>No. of Khasras</th><th>Proposal Submission</th><th>Payment</th>
<th>Amount</th><th>NOC</th><th>Valuation</th>
<th>Paid</th><th>Date</th><th>Transfer</th><th>Action</th>
</tr>
</thead>
<tbody id="governmentTbody">
<c:if test="${not empty LADetails.governmentLands}">
    <c:forEach var="gl" items="${LADetails.governmentLands}" varStatus="status">
        <tr>
<td><input type="text" name="governmentLands[${status.index}].district" value="${gl.district}" /></td>
<td><input type="text" name="governmentLands[${status.index}].taluka" value="${gl.taluka}" /></td>
<td><input type="text" name="governmentLands[${status.index}].village" value="${gl.village}" /></td>
<td><input type="text" name="governmentLands[${status.index}].collector" value="${gl.collector}" /></td>
<td><input type="text" name="governmentLands[${status.index}].agency" value="${gl.agency}" /></td>

<td><input type="text" name="governmentLands[${status.index}].areaRequired" value="${gl.areaRequired}" /></td>
<td><input type="text" name="governmentLands[${status.index}].chainageFrom" value="${gl.chainageFrom}" /></td>
<td><input type="text" name="governmentLands[${status.index}].chainageTo" value="${gl.chainageTo}" /></td>

<td><input type="text" name="governmentLands[${status.index}].surveyNo" value="${gl.surveyNo}" /></td>
<td>
    <fmt:parseDate value="${gl.proposalSubmission}" pattern="yyyy-MM-dd" var="parsedProposal"/>
    <input type="text" class="datepicker"
        name="governmentLands[${status.index}].proposalSubmission"
        value="<fmt:formatDate value='${parsedProposal}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${gl.letterOfPayment}" pattern="yyyy-MM-dd" var="parsedLetter"/>
    <input type="text" class="datepicker"
        name="governmentLands[${status.index}].letterOfPayment"
        value="<fmt:formatDate value='${parsedLetter}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>
<td><input type="text" name="governmentLands[${status.index}].amountDemanded" value="${gl.amountDemanded}" /></td>
<td><input type="text" name="governmentLands[${status.index}].noc" value="${gl.noc}" /></td>
<td>
    <fmt:parseDate value="${gl.valuationDate}" pattern="yyyy-MM-dd" var="parsedValuation"/>
    <input type="text" class="datepicker"
        name="governmentLands[${status.index}].valuationDate"
        value="<fmt:formatDate value='${parsedValuation}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>
<td><input type="text" name="governmentLands[${status.index}].payment" value="${gl.payment}" /></td>
<td>
    <fmt:parseDate value="${gl.paymentDate}" pattern="yyyy-MM-dd" var="parsedPayment"/>
    <input type="text" class="datepicker"
        name="governmentLands[${status.index}].paymentDate"
        value="<fmt:formatDate value='${parsedPayment}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${gl.landTransfer}" pattern="yyyy-MM-dd" var="parsedTransfer"/>
    <input type="text" class="datepicker"
        name="governmentLands[${status.index}].landTransfer"
        value="<fmt:formatDate value='${parsedTransfer}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>
<td> <button type="button" class="btn red" onclick="removeRow(this,'governmentTbody')">
		                    <i class="fa fa-close"></i>
                </button></td>

        </tr>
    </c:forEach>
</c:if>
</tbody>

</table>
</div>
    <div class="section-title">
		    <button type="button" class="btn center-align primary-text" onclick="addGovernmentRow()">+</button>
		</div>


<!-- ================= FOREST LAND ================= -->
<h6 class="center-align primary-text">Forest Land</h6>
<div class="table-inside">
<table class="mdl-data-table mobile_responsible_table">
<thead>
<tr>
<th>District</th><th>Taluka</th><th>Village</th><th>Collector</th>
<th>Area (ha)</th><th>From</th><th>To</th>
<th>Proposal Submission</th><th>Verification</th>
<th>Clearance</th><th>Valuation</th>
<th>Payment</th><th>Date</th><th>Possession</th><th>Action</th>
</tr>
</thead>
<tbody id="forestTbody">
<c:if test="${not empty LADetails.forestLands}">
    <c:forEach var="fl" items="${LADetails.forestLands}" varStatus="status">
        <tr>
			<td><input type="text" name="forestLands[${status.index}].district" value="${fl.district}" /></td>
			<td><input type="text" name="forestLands[${status.index}].taluka" value="${fl.taluka}" /></td>
			<td><input type="text" name="forestLands[${status.index}].village" value="${fl.village}" /></td>
			<td><input type="text" name="forestLands[${status.index}].collector" value="${fl.collector}" /></td>
			
			<td><input type="text" name="forestLands[${status.index}].areaRequired" value="${fl.areaRequired}" /></td>
			<td><input type="text" name="forestLands[${status.index}].chainageFrom" value="${fl.chainageFrom}" /></td>
			<td><input type="text" name="forestLands[${status.index}].chainageTo" value="${fl.chainageTo}" /></td>
			
			<td><input type="text" class="datepicker" name="forestLands[${status.index}].submissionOfProposal" value="${fl.submissionOfProposal}" /><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
			<td><input type="text" class="datepicker" name="forestLands[${status.index}].physicalVerification" value="${fl.physicalVerification}" /><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
			<td><input type="text" class="datepicker" name="forestLands[${status.index}].firstStageClearance" value="${fl.firstStageClearance}" /><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
			
			<td><input type="text" name="forestLands[${status.index}].valuation" value="${fl.valuation}" /></td>
			<td><input type="text" name="forestLands[${status.index}].payment" value="${fl.payment}" /></td>
<td>
    <fmt:parseDate value="${fl.paymentDate}" pattern="yyyy-MM-dd" var="parsedForestPayment"/>
    <input type="text" class="datepicker"
        name="forestLands[${status.index}].paymentDate"
        value="<fmt:formatDate value='${parsedForestPayment}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>

<td>
    <fmt:parseDate value="${fl.possession}" pattern="yyyy-MM-dd" var="parsedPossession"/>
    <input type="text" class="datepicker"
        name="forestLands[${status.index}].possession"
        value="<fmt:formatDate value='${parsedPossession}' pattern='dd-MM-yyyy'/>" />
    <button type="button" class="datepicker-button">
        <i class="fa fa-calendar"></i>
    </button>
</td>			
			<td>                                <button type="button" class="btn red" onclick="removeRow(this,'forestTbody')">
		                    <i class="fa fa-close"></i>
                </button></td>

        </tr>
    </c:forEach>
</c:if>
</tbody>

</table>
</div>
    <div class="section-title">
			    <button type="button" class="btn center-align primary-text" onclick="addForestRow()">+</button>
			</div>
		</div>

                </div>
            </div>
        </div>
    </div>
</div>


    <!-- ================= SUBMIT ================= -->
    <div class="row">
        <div class="col s6 m4 l6 center-align offset-m2">
            <button type="submit"
                    class="btn waves-effect waves-light bg-m"
                    style="min-width:90px" onClick="submitLandForm();">
                Add
            </button>
        </div>

        <div class="col s6 m4 l6 center-align">
            <a href="<%=request.getContextPath()%>/land-acquisition-process-grid"
               class="btn waves-effect waves-light bg-s">
                Cancel
            </a>
        </div>
    </div>

</form>

    <!-- footer  -->
  <jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/wrpmis/resources/js/datepickerDepedency.js"></script>
	<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
	<script src="/wrpmis/resources/js/select2.min.js"></script>
	<script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/wrpmis/resources/js/datetimepicker.js"></script>
	<script type="text/javascript">
	
	console.log("${not empty LADetails.privateLands}");
	
	function submitLandForm() {

	    let privateLands = getTableData("privateTbody", "privateLands");
	    let governmentLands = getTableData("governmentTbody", "governmentLands");
	    let forestLands = getTableData("forestTbody", "forestLands");

	    if (
	        privateLands.length === 0 &&
	        governmentLands.length === 0 &&
	        forestLands.length === 0
	    ) {
	        M.toast({
	            html: "Please enter at least one land detail (Private / Government / Forest)",
	            classes: "red"
	        });
	        return false;
	    }

	    document.getElementById("privateLandsJson").value = JSON.stringify(privateLands);
	    document.getElementById("governmentLandsJson").value = JSON.stringify(governmentLands);
	    document.getElementById("forestLandsJson").value = JSON.stringify(forestLands);

	    document.getElementById("landAcquisitionForm").submit();
	}




	function reindexRows(tbodyId) {
	    const rows = document.querySelectorAll(`#${tbodyId} tr`);
	    rows.forEach((row, index) => {
	        row.querySelectorAll("input, select, textarea").forEach(input => {
	            const oldName = input.getAttribute("name");
	            if (oldName) {
	                const newName = oldName.replace(/\[\d+\]/, `[${index}]`);
	                input.setAttribute("name", newName);
	            }
	        });
	    });
	}
	
	
	function getTableData(tbodyId, listName) {

	    let data = [];

	    $("#" + tbodyId + " tr").each(function () {

	        let rowData = {};
	        let hasValue = false;

	        $(this).find("input, select, textarea").each(function () {
	            let name = $(this).attr("name");
	            let value = $(this).val();

	            if (value && value.trim() !== "") {
	                hasValue = true;
	            }

	            if (name) {
	                let field = name.substring(name.lastIndexOf(".") + 1);
	                rowData[field] = value;
	            }
	        });

	        // ✅ PUSH ONLY IF ROW HAS DATA
	        if (hasValue) {
	            data.push(rowData);
	        }
	    });

	    return data;
	}



	
	
	
	
    // Convert server-side projectsList to a JS array
    var projectsList = [
        <c:forEach var="obj" items="${projectsList}" varStatus="status">
            {
                id: "${obj.project_id}",
                name: "${fn:escapeXml(obj.project_name)}"
            }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    // Alert if projectsList is empty
    if(projectsList.length === 0){
        alert("Projects list is empty!");
    } else {
        console.log("Projects loaded:", projectsList);
    }
</script>
<script>

$(document).ready(function () {
    $('select:not(.searchable)').formSelect();
    
    $(".datepicker").datepicker({
        dateFormat: "dd-mm-yy"
    });   
    
    $('.searchable').select2();
    
    $("#projectId").on("change", function () {

        const projectId = $(this).val();

        if (!projectId || projectId === "0") {
            projectExists = false;
            return;
        }

        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/check-project-exists",
            type: "GET",
            data: { projectId: projectId },
            success: function (exists) {
                if (exists === true || exists === "true") {
                    projectExists = true;
                    alert("Record already exists for this project");
                    $("#projectId").val("").trigger("change"); // reset
                } else {
                    projectExists = false;
                }
            }
        });
    });
});


let privateRowCount =
    document.querySelectorAll("#privateTbody tr").length;

let governmentRowCount =
    document.querySelectorAll("#governmentTbody tr").length;

let forestRowCount =
    document.querySelectorAll("#forestTbody tr").length;

// ================= PRIVATE LAND =================
function addPrivateRow() {
    const i = privateRowCount++;
    document.getElementById("privateTbody").insertAdjacentHTML("beforeend", `
        <tr>
            <td><input type="text" name="privateLands[${i}].district"></td>
            <td><input type="text" name="privateLands[${i}].taluka"></td>
            <td><input type="text" name="privateLands[${i}].village"></td>
            <td><input type="text" name="privateLands[${i}].collector"></td>
            <td><input type="text" name="privateLands[${i}].areaRequired"></td>
            <td><input type="text" name="privateLands[${i}].chainageFrom"></td>
            <td><input type="text" name="privateLands[${i}].chainageTo"></td>
            <td><input type="text" name="privateLands[${i}].surveyNumbers"></td>
            <td><input type="text" class="datepicker" name="privateLands[${i}].submissionOfProposal"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="privateLands[${i}].notificationUnder20A"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="privateLands[${i}].notificationUnder20E"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="privateLands[${i}].jmMeasurement"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="privateLands[${i}].acquisitionUnder20F"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" name="privateLands[${i}].grievanceSurveyNos"></td>
            <td class="center">
                <button type="button" class="btn red" onclick="removeRow(this,'privateTbody')">
                    <i class="fa fa-close"></i>
                </button>
            </td>
        </tr>
    `);
}

// Call once on page load
document.addEventListener("DOMContentLoaded", function() {

    const hasPrivateLands = ${not empty LADetails.privateLands};
    const hasGovernmentLands = ${not empty LADetails.governmentLands};
    const hasForestLands = ${not empty LADetails.forestLands};
	
	
    if (!hasPrivateLands) {
        addPrivateRow();
    }

    if (!hasGovernmentLands) {
        addGovernmentRow();
    }

    if (!hasForestLands) {
        addForestRow();
    }
});

// ================= GOVERNMENT LAND =================
function addGovernmentRow() {
    const i = governmentRowCount++;
    document.getElementById("governmentTbody").insertAdjacentHTML("beforeend", `
        <tr>
            <td><input type="text" name="governmentLands[${i}].district"></td>
            <td><input type="text" name="governmentLands[${i}].taluka"></td>
            <td><input type="text" name="governmentLands[${i}].village"></td>
            <td><input type="text" name="governmentLands[${i}].collector"></td>
            <td><input type="text" name="governmentLands[${i}].agency"></td>
            <td><input type="text" name="governmentLands[${i}].areaRequired"></td>
            <td><input type="text" name="governmentLands[${i}].chainageFrom"></td>
            <td><input type="text" name="governmentLands[${i}].chainageTo"></td>
            <td><input type="text" name="governmentLands[${i}].surveyNo"></td>
            <td><input type="text" class="datepicker" name="governmentLands[${i}].proposalSubmission"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="governmentLands[${i}].letterOfPayment"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" name="governmentLands[${i}].amountDemanded"></td>
            <td><input type="text" name="governmentLands[${i}].noc"></td>
            <td><input type="text" class="datepicker" name="governmentLands[${i}].valuationDate"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" name="governmentLands[${i}].payment"></td>
            <td><input type="text" class="datepicker" name="governmentLands[${i}].paymentDate"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="governmentLands[${i}].landTransfer"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td class="center">
                <button type="button" class="btn red" onclick="removeRow(this,'governmentTbody')">
                    <i class="fa fa-close"></i>
                </button>
            </td>
        </tr>
    `);
}

// ================= FOREST LAND =================
function addForestRow() {
    const i = forestRowCount++;
    document.getElementById("forestTbody").insertAdjacentHTML("beforeend", `
        <tr>
            <td><input type="text" name="forestLands[${i}].district"></td>
            <td><input type="text" name="forestLands[${i}].taluka"></td>
            <td><input type="text" name="forestLands[${i}].village"></td>
            <td><input type="text" name="forestLands[${i}].collector"></td>
            <td><input type="text" name="forestLands[${i}].areaRequired"></td>
            <td><input type="text" name="forestLands[${i}].chainageFrom"></td>
            <td><input type="text" name="forestLands[${i}].chainageTo"></td>
            <td><input type="text" class="datepicker" name="forestLands[${i}].submissionOfProposal"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="forestLands[${i}].physicalVerification"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="forestLands[${i}].firstStageClearance"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" name="forestLands[${i}].valuation"></td>
            <td><input type="text" name="forestLands[${i}].payment"></td>
            <td><input type="text" class="datepicker" name="forestLands[${i}].paymentDate"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td><input type="text" class="datepicker" name="forestLands[${i}].possession"><button type="button" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>
            <td class="center">
                <button type="button" class="btn red" onclick="removeRow(this,'forestTbody')">
                    <i class="fa fa-close"></i>
                </button>
            </td>
        </tr>
    `);
}


function removeRow(btn, tbodyId) {
    const row = btn.closest("tr");
    row.remove();
    reindexRows(tbodyId);
}
function reindexTable(tbodyId) {
    const rows = document.querySelectorAll(`#${tbodyId} tr`);

    rows.forEach((row, index) => {
        row.querySelectorAll("input").forEach(input => {
            input.name = input.name.replace(/\[\d+\]/, `[${index}]`);
        });
    });
}




</script>



</body>
</html>