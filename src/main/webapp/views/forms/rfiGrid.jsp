<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RFI Management</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="../nginx-1.9.9/html/wrpmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
         .input-field .searchable_label{
        	font-size:0.85rem;
        }   
    	 .fw-400{
    	 	width:400px !important;
    	 	max-width:400px;
    	 }
    	 .fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
    	 .fw-200{
    	 	width:200px !important;
    	 	max-width:200px;
    	 }
         .dataTables_filter label::after{
         	content:'';
         }
         .right-btns1 .fa{
         	position:relative;
         	top:-35px;
         }
        
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
          .right-btns1 .fa+.fa{
         	right:-10px;
         }
         .row.no-mar{
         	margin-bottom:0;
         }
         .d-none{
         	display: none;
         }
         .modal-wrapper {
		    align-items: center;
		    background-color: rgba(100, 100, 100, 0.5);
		    bottom: 0;
		    display: flex;
		    flex-wrap: wrap;
		    height: 100vh;
		    justify-content: center;
		    left: 0;
		    opacity: 0;
		    position: fixed;
		    right: 0;
		    transition: all 0.2s ease-in-out;
		    visibility: hidden;
		    width: 100%;
		    z-index: 1000;
		}
		.modal{
			max-height: 100%;
		}
		.modal.modal-fixed-footer .modal-content{
		height: auto;
		}
		.modal.modal-fixed-footer{
			height: -webkit-fill-available;
		}
		.modal-wrapper.visible {
		    opacity: 1;
		    visibility: visible;
		}
		
		.modal-window {
		    background-color: white;
		    border-radius: 5px;
		    box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		   /*  padding: 30px; */
		    transform: scale(0);
		    transition: 0.2s ease-in-out all;
		    min-width: 70%;
		    max-width: 80%;
		    max-height: 80%;
		    overflow-x: scroll;
		}
		
		.modal-wrapper.visible .modal-window {
		    transform: scale(1);
		}
		
		.modal-header {
		    align-items: center;
		    border-bottom: 2px solid black;
		    display: flex;
		    justify-content: space-between;
		    margin-bottom: 20px;
		    padding-bottom: 20px;
		   position: sticky;
		    width: 100%;
		    top: 0em;
    		margin: 0px 0px 10px !important;
		}
		
		.modal-content{
			padding: 30px !important;
		}
		.modal-footer{
			float: right;
		}
		.modal-footer .close-modal-button{
			color: #000;
			background-color: #ccc;
		}
		.modal-footer .close-modal-button:hover{
			color: #fff;
		}
		.close-modal-button {
		    border: none;
		    background-color: transparent;
		    color: #fff;
		    cursor: pointer;
		    font-size: 1rem;
		    padding: 0.2em;
		}
		
		.close-modal-button:hover {
		    color: black;
		}
		
		.modal-trigger a {
		    color: rgb(10, 47, 255);
		    cursor: pointer;
		    text-decoration: underline;
		}
		.modal.modal-fixed-footer .modal-content{
			overflow-y: auto;
			position: relative;
			z-index: -1;
		    min-height: 70%;
		}
		.modal.modal-fixed-footer .modal-footer{
			top: 100%;
			position: inherit;
		}
		.m0{
			margin: 0;
		}
		.w40em{
			width: 40em;
		}
		.select2-dropdown{
			z-index:1000;
		}
		.con-center{
	    	display: flex;
		    vertical-align: middle;
		    align-items: center;
    	}
    	.con-center div:not(:first-of-type) {
		    margin-left: auto;
		}
    	.con-center.p-2 {
		    margin-top: 1.5rem;
		    margin-bottom: 1.5rem;
		    float: none;
		}
		.legends {
            padding: 2px;
        }

        .box,
        .description {
            display: inline-block;
            margin-left: 3px;
            margin-right: 3px;
            vertical-align: middle;
        }

        .box {
            width: 20px;
            height: 20px;
            border-radius:50%;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        .box.apv,
        .apv {
            background-color: green;
        }

        .box.pen,
        .pen {
            background-color: yellow;
        }

        .box.ns,
        .ns {
            background-color: #fff;
        }
        
        .box.rjc,
        .rjc {
            background-color: red;
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
			.r-300{
				width:30vw !important;
        		max-width:30vw;
			}
			 .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	        .fw-111{
	        	width:30vw !important;
	        	min-width:30vw;
	        }
		}
		#datatable-randr_mob td > .btn.t-c{
			padding: 0 10px;
		}
		.fw-38w{
			width: 38vw !important;
		}
		.right-btns{ display:none;}

		.right-btns:last-of-type {
		  display:block;
		}
		.no-sort.sorting_asc:before,
.no-sort.sorting_asc:after{
    opacity:0 !important;
    content:'' !important;
}
	.m-n1 {
   		 margin: -2rem auto 0;
	}
	.template-btn{
		text-shadow:1px 1px 1px black;
	}
	@media only screen and (max-width: 820px){
		.mob-mar{
			text-align: center;
		    margin-top: -1rem;
		    margin-bottom: 2.2rem;
		}
		.exportButton .btn{
			padding-left: 6px;
	   		padding-right: 6px;
		}
	}
	
	thead th{
		text-transform: capitalize;
	}
	@media(max-width: 575px){
		.fw-200{
			width: 120px !important;
    		max-width: 75px;
		}
		.mdl-data-table thead tr th {
		    vertical-align: middle;
		    text-align: center;
		    word-break: break-word;
		    white-space: initial;
		}
		.fw-10{
			width: 120px !important;
    		max-width: 110px;
		}
	}
	.ds-b{
		display: block;
		margin: 10px;
    	width: 5em;
	}
 .progressContainer {
align-content: center;
	 position: relative;
	 width: auto;
	 height: auto;
	 margin: 0 auto;
	 overflow: hidden;
	 padding: 2rem;
	 color: #fff;
	 background: #5d5cb0;
}
 .progress {
	 position: relative;
	 padding: 0 1rem 0 3.5rem;
	 margin: 2rem 0 0;
	 list-style: none;
    height: initial;
    width: initial;
    background-color: transparent;
    border-radius: initial;
    overflow: initial;
}
 .progress__item {
	 position: relative;
	 min-height: 75px;
	 counter-increment: list;
	 padding-left: 2.5rem;
   justify-content:center;
   align-content: center;
   position: middle;
}
 .progress__item:before {
	 content: "";
	 position: absolute;
	 left: 0.65rem;
    top: 25px;
    height: 70%;
    width: 1px;
    border-left: 4px solid #ccc;
}
 .progress__item:after {
	 
	 position: absolute;
	 top: 0;
	 left: -0.5rem;
	 width: 36px;
	 height: 36px;
	 border-radius: 50%;
	 background: transparent;
	 color: #000;
	 font-weight: 400;
	 font-size: 13px;
	 line-height: 2rem;
	 text-align: center;
	 border: 4px solid #ccc;
}
 .progress__item:first-child:after {
	 content: '\f073';
	 font-family: 'FontAwesome';
}
 .progress__item:nth-child(2):after {
	 content: '\f274';
	 font-family: 'FontAwesome';
}
 .progress__item:last-child:after {
	 content: '\f164';
	 font-family: 'FontAwesome';
	 
}
 .progress__item:last-child:before {
	 
	 border: none;
}
 .progress__item.progress__item--active:after {
	 background: green;
	 color: #fff;
	 font-weight: bold;
}
 .progress__title {
	 padding: 0.4rem 0 0.5rem;
	 margin: 0;
	 font-size: 13px;
	 font-weight: bold;
}
 .progress__info {
 	font-size: 1.5rem;	 
}
 .title {
	 padding: 0.4rem 0 0rem;
	 margin: 0;
	 font-size: 13px;
	 font-weight: bold;
}
 .info {
 	font-size: 1.4rem;	 
}
 .info a{
 	color: blue;
 	text-decoration: underline;	 
}
 @keyframes gradient {
	 0% {
		 background-position: 0% 0%;
	}
	 50% {
		 background-position: 100% 100%;
	}
	 100% {
		 background-position: 0% 0%;
	}
}
	.red {
    background-color: red;
}

.yellow {
    background-color: yellow;
}
.green {
    background-color: green;
}
.white {
    background-color: white;
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
                            <h6> RFI Management</h6>
                        </div>
                    </span>
                    <div class="">

                        <!-- <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar" style="margin-bottom: 20px;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m10 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Status</p>
                                        <select id="status" class="searchable">
                                            <option value="" disabled selected>Select Status</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">From Date</p>
                                        <select id="from_date" class="searchable">
                                            <option value="" disabled selected>Select Date</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">To Date</p>
                                        <select id="to_date" class="searchable">
                                            <option value="" disabled selected>Select Date</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>
						<div class="row no-mar">
						<div class="col m12 l7 offset-l2 s12">
						 	<fieldset class="p-2 con-center fs16rm">
						 		<div class="">
                                       <span class="box ns"></span>
                                       <span class="description">Not Started</span>
                                   </div>
                                   <div class="">
                                       <span class="box pen"></span>
                                       <span class="description">Pending</span>
                                   </div>
                                   <div class="">
                                       <span class="box apv"></span>
                                       <span class="description">Approved</span>
                                   </div>
                                   <div class="">
                                       <span class="box rjc"></span>
                                       <span class="description">Rejected</span>
                                   </div> 
                                                                     
                              </fieldset>
						</div>
					</div>
                        <div class="row">
                            <div class="col m12 s12">

                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="w40em">Description</th>
                                            <th class="w40em">Report</th>
                                            <th class="w40em">Responsible</th>
                                            <th class="w40em">Work Description</th>
                                            <th class="w40em">Remarks</th>                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>
                                        
											<td>not started</td>
                                            <td>
                                            <h4><i class="fa fa-database" aria-hidden="true"></i> Panvel Karjat</h4>
                                            <p><i class="fa fa-empire" aria-hidden="true"></i> Authority</p>
                                            <h5 class="m0"> Western Railway</h5>
                                            <h5><i class="fa fa-user" aria-hidden="true"></i> Bhushan Satish Chaudary</h5>
                                            <p><i class="fa fa-calendar" aria-hidden="true"></i> Uploaded On</p>
                                            <h5 class="m0"> 30/06/2022</h5>
                                           
                                            </td>
                                            <td>
                                            	 <ul class="progress">
												    <li class="progress__item">
												      <p class="progress__title">Submission On</p>
												      <p class="progress__info">N/A</p>
												    </li>
												    <li class="progress__item">
												      <p class="progress__title">Inspection On</p>
												      <p class="progress__info">N/A</p>
												    </li>
												    <li class="progress__item">
												      <p class="progress__title">Approved On</p>
												      <p class="progress__info">N/A</p>
												     </li>
												  </ul>
                                            </td>
                                            <td>
                                            	<p class="title">Contact No</p>
                                            	<p class="info">Unknown</p>
                                            	<p class="title">Authority Engineer</p>
                                            	<p class="info">MRVC</p>
                                            	<p class="title">EPC Contractor</p>
                                            	<p class="info"></p>
                                            </td>
                                            <td>
                                            	<p class="title">Type</p>
                                            	<p class="info">BHS</p>
                                            	<p class="title">Work Description</p>
                                            	<p class="info"><a href="#"> Blasting</a></p>
                                            	<p class="title">Chainage</p>
                                            	<p class="info">0-0</p>
                                            </td>
                                            <td>
                                            	<p class="title">Length</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Slide</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Remarks</p>
                                            	<p class="info">N/A</p>
                                            </td>
                                            
                                            <td class="last-column">
                                            	<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b">
                                            		<i class="fa fa-pencil"></i> </a>
                                            		<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal4">
                                            		<i class="fa fa-upload" aria-hidden="true" data-modal-id="modal4"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal1">
                                            		<i class="fa fa-picture-o" aria-hidden="true"  data-modal-id="modal1"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal3">
                                            		<i class="fa fa-check-circle-o" aria-hidden="true" data-modal-id="modal3"></i> </a>
                                            </td>

                                        </tr>
                                         <tr>
                                        
											<td>pending</td>
                                            <td>
                                            <h4><i class="fa fa-database" aria-hidden="true"></i> Panvel Karjat</h4>
                                            <p><i class="fa fa-empire" aria-hidden="true"></i> Authority</p>
                                            <h5 class="m0"> Westren Railway</h5>
                                            <h5><i class="fa fa-user" aria-hidden="true"></i> Bhushan Satish Chaudary</h5>
                                            <p><i class="fa fa-calendar" aria-hidden="true"></i> Uploaded On</p>
                                            <h5 class="m0"> 30/06/2022</h5>
                                           
                                            </td>
                                            <td>
                                            	 <ul class="progress">
												    <li class="progress__item  progress__item--active">
												      <p class="progress__title">Submission On</p>
												      <p class="progress__info">20/06/2022</p>
												    </li>
												    <li class="progress__item progress__item--active">
												      <p class="progress__title">Inspection On</p>
												      <p class="progress__info">25/06/2022</p>
												    </li>
												    <li class="progress__item">
												      <p class="progress__title">Approved On</p>
												      <p class="progress__info">N/A</p>
												     </li>
												  </ul>
                                            </td>
                                            <td>
                                            	<p class="title">Contact No</p>
                                            	<p class="info">Unknown</p>
                                            	<p class="title">Authority Engineer</p>
                                            	<p class="info">MRVC</p>
                                            	<p class="title">EPC Contractor</p>
                                            	<p class="info"></p>
                                            </td>
                                            <td>
                                            	<p class="title">Type</p>
                                            	<p class="info">BHS</p>
                                            	<p class="title">Work Description</p>
                                            	<p class="info"><a href="#"> Blasting</a></p>
                                            	<p class="title">Chainage</p>
                                            	<p class="info">0-0</p>
                                            </td>
                                            <td>
                                            	<p class="title">Length</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Slide</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Remarks</p>
                                            	<p class="info">N/A</p>
                                            </td>
                                            
                                            <td class="last-column">
                                            	<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b">
                                            		<i class="fa fa-pencil"></i> </a>
                                            		<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal4">
                                            		<i class="fa fa-upload" aria-hidden="true" data-modal-id="modal4"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal1">
                                            		<i class="fa fa-picture-o" aria-hidden="true"  data-modal-id="modal1"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal3">
                                            		<i class="fa fa-check-circle-o" aria-hidden="true" data-modal-id="modal3"></i> </a>
                                            </td>

                                        </tr>
                                        <tr>
                                        
											<td>approved</td>
                                            <td>
                                            <h4><i class="fa fa-database" aria-hidden="true"></i> Virar Dahanu</h4>
                                            <p><i class="fa fa-empire" aria-hidden="true"></i> Authority</p>
                                            <h5 class="m0"> Westren Railway</h5>
                                            <h5><i class="fa fa-user" aria-hidden="true"></i> Bhushan Satish Chaudary</h5>
                                            <p><i class="fa fa-calendar" aria-hidden="true"></i> Uploaded On</p>
                                            <h5 class="m0"> 30/06/2022</h5>
                                           
                                            </td>
                                            <td>
                                            	 <ul class="progress">
												    <li class="progress__item  progress__item--active">
												      <p class="progress__title">Submission On</p>
												      <p class="progress__info">20/06/2022</p>
												    </li>
												    <li class="progress__item progress__item--active">
												      <p class="progress__title">Inspection On</p>
												      <p class="progress__info">25/06/2022</p>
												    </li>
												    <li class="progress__item progress__item--active">
												      <p class="progress__title">Approved On</p>
												      <p class="progress__info">01/07/2022</p>
												     </li>
												  </ul>
                                            </td>
                                            <td>
                                            	<p class="title">Contact No</p>
                                            	<p class="info">Unknown</p>
                                            	<p class="title">Authority Engineer</p>
                                            	<p class="info">MRVC</p>
                                            	<p class="title">EPC Contractor</p>
                                            	<p class="info"></p>
                                            </td>
                                            <td>
                                            	<p class="title">Type</p>
                                            	<p class="info">BHS</p>
                                            	<p class="title">Work Description</p>
                                            	<p class="info"><a href="#"> Blasting</a></p>
                                            	<p class="title">Chainage</p>
                                            	<p class="info">0-0</p>
                                            </td>
                                            <td>
                                            	<p class="title">Length</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Slide</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Remarks</p>
                                            	<p class="info">N/A</p>
                                            </td>
                                            
                                            <td class="last-column">
                                            	<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b">
                                            		<i class="fa fa-pencil"></i> </a>
                                            		<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal4">
                                            		<i class="fa fa-upload" aria-hidden="true" data-modal-id="modal4"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal1">
                                            		<i class="fa fa-picture-o" aria-hidden="true"  data-modal-id="modal1"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal3">
                                            		<i class="fa fa-check-circle-o" aria-hidden="true" data-modal-id="modal3"></i> </a>
                                            </td>

                                        </tr>
                                        <tr>
                                        
											<td>rejected</td>
                                            <td>
                                            <h4><i class="fa fa-database" aria-hidden="true"></i> Virar Dahanu</h4>
                                            <p><i class="fa fa-empire" aria-hidden="true"></i> Authority</p>
                                            <h5 class="m0"> Westren Railway</h5>
                                            <h5><i class="fa fa-user" aria-hidden="true"></i> Bhushan Satish Chaudary</h5>
                                            <p><i class="fa fa-calendar" aria-hidden="true"></i> Uploaded On</p>
                                            <h5 class="m0"> 30/06/2022</h5>
                                           
                                            </td>
                                            <td>
                                            	 <ul class="progress">
												    <li class="progress__item  progress__item--active">
												      <p class="progress__title">Submission On</p>
												      <p class="progress__info">20/06/2022</p>
												    </li>
												    <li class="progress__item progress__item--active">
												      <p class="progress__title">Inspection On</p>
												      <p class="progress__info">25/06/2022</p>
												    </li>
												    <li class="progress__item">
												      <p class="progress__title">Approved On</p>
												      <p class="progress__info">N/A</p>
												     </li>
												  </ul>
                                            </td>
                                            <td>
                                            	<p class="title">Contact No</p>
                                            	<p class="info">Unknown</p>
                                            	<p class="title">Authority Engineer</p>
                                            	<p class="info">MRVC</p>
                                            	<p class="title">EPC Contractor</p>
                                            	<p class="info"></p>
                                            </td>
                                            <td>
                                            	<p class="title">Type</p>
                                            	<p class="info">BHS</p>
                                            	<p class="title">Work Description</p>
                                            	<p class="info"><a href="#"> Blasting</a></p>
                                            	<p class="title">Chainage</p>
                                            	<p class="info">0-0</p>
                                            </td>
                                            <td>
                                            	<p class="title">Length</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Slide</p>
                                            	<p class="info">0</p>
                                            	<p class="title">Remarks</p>
                                            	<p class="info">N/A</p>
                                            </td>
                                            
                                            <td class="last-column">
                                            	<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b">
                                            		<i class="fa fa-pencil"></i> </a>
                                            		<a href="#" class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal4">
                                            		<i class="fa fa-upload" aria-hidden="true" data-modal-id="modal4"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal1">
                                            		<i class="fa fa-picture-o" aria-hidden="true"  data-modal-id="modal1"></i> </a>
                                            		<a class="btn waves-effect waves-light bg-m t-c ds-b modal-trigger" data-modal-id="modal3">
                                            		<i class="fa fa-check-circle-o" aria-hidden="true" data-modal-id="modal3"></i> </a>
                                            </td>

                                        </tr>
                                        

                                    </tbody>

                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div class="modal-wrapper modal modal-fixed-footer" id="modal1">
		  <section class="modal-window">
		    <header class="modal-header">
		      <h3>Title goes here...</h3>
		      <button
		        type="button"
		        class="close-modal-button"
		        aria-label="Close modal window"
		      >
		        X
		      </button>
		    </header>
		    <div class="modal-content">
		    	<a href="#" class="modal-trigger" data-modal-id="modal2">
				    <img src="https://media.istockphoto.com/photos/delivering-quality-construction-for-a-quality-lifestyle-picture-id1297780288?b=1&k=20&m=1297780288&s=170667a&w=0&h=NDdDs9BBGULLwYUDUt1AjIOroHuwmFY9N6ZEDAYV7sE=" style="width: 400px; height: 264px;margin: 20px 0" data-modal-id="modal2">
				</a>
				    
				<a href="#" class="modal-trigger" data-modal-id="modal2">
				    <img src="https://media.istockphoto.com/photos/worker-at-construction-site-is-fixing-the-form-for-the-beam-picture-id897664288?k=20&m=897664288&s=612x612&w=0&h=kFFtfcVOXrz8dnUdpmarcT1V-KwETy0gyN-4YuNLRuE=" style="width: 400px; height: 264px;margin: 20px 0" data-modal-id="modal2">
				</a>
				
				<a href="#" class="modal-trigger" data-modal-id="modal2">
				    <img src="https://images.livemint.com/img/2021/02/07/1600x900/90079T99_1604246739390_1604246747884_1612718188073.jpg" style="width: 400px; height: 264px;margin: 20px 0" data-modal-id="modal2">
				</a>
				<a href="#" class="modal-trigger" data-modal-id="modal2">
				    <img src="https://images.livemint.com/img/2021/02/07/1600x900/90079T99_1604246739390_1604246747884_1612718188073.jpg" style="width: 400px; height: 264px;margin: 20px 0" data-modal-id="modal2">
				</a>
			    <!-- <p>
			      Now open
			      <a href="#" class="modal-trigger" data-modal-id="modal2">another modal</a
			      >!
			    </p> -->
		    </div>
		    <div class="modal-footer">
			        <button type="button" class="btn btn-secondary close-modal-button">Close</button>
			        <button type="button" class="btn btn-primary">Save changes</button>
			      </div>
		  </section>
		</div>
		<div class="modal-wrapper modal modal-fixed-footer" id="modal2">
		  <section class="modal-window">
		    <header class="modal-header">
		      <h3>Image Name</h3>
		      <button
		        type="button"
		        class="close-modal-button"
		        aria-label="Close modal window"
		      >
		        X
		      </button>
		    </header>
		    <div class="modal-content">
		    	<img src="" class="imagepreview" style="width: 70%;" >
		    </div>
		    <div class="modal-footer">
			        <button type="button" class="btn btn-secondary close-modal-button">Close</button>
			        <button type="button" class="btn btn-primary">Save changes</button>
			      </div>
		  </section>
		  
		</div>
		<div class="modal-wrapper modal modal-fixed-footer" id="modal3">
		  <section class="modal-window">
		    <header class="modal-header">
		      <h3>Status</h3>
		      <button
		        type="button"
		        class="close-modal-button"
		        aria-label="Close modal window"
		      >
		        X
		      </button>
		    </header>
		    	<div class="modal-content">
		    		<form>
		    		<div class="row">
		    			<div class="col s12 m6 input-field">
		                     <p class="searchable_label">MRVC - HOD</p>
		                     <select id="prog_status" class="searchable">
		                         <option value="" disabled selected>Select Dashboard</option>
		                         <option value="not started">Not Started</option>
		                         <option value="pending">Pending</option>
		                         <option value="approved">Approved</option>
		                         <option value="rejected">Rejected</option>
		                     </select>
		                 </div>
		                 		<div class="col s6 m6 input-field">
		                             <input id="remarks" maxlength="1000" data-length="1000" name="remarks" type="text" class="validate w80 pdr4em" value="">
		                             <label for="remarks">Remarks</label>
		                             <span id="remarksError" class="error-msg" ></span>
		                         </div>
		    		</div>
		    	</form>
		    	</div>
		    	<div class="modal-footer">
			        <button type="button" class="btn btn-secondary close-modal-button">Close</button>
			        <button type="button" class="btn btn-primary">Save changes</button>
			      </div>
		  </section>
		</div>
		<div class="modal-wrapper modal modal-fixed-footer" id="modal4">
		  <section class="modal-window">
		    <header class="modal-header">
		      <h3>Status</h3>
		      <button
		        type="button"
		        class="close-modal-button"
		        aria-label="Close modal window"
		      >
		        X
		      </button>
		    </header>
		    	<div class="modal-content">
		    		<form>
		    		<!-- <div class="col s12 m3 input-field">
                     <p class="searchable_label">MRVC - HOD</p>
                     <select id="prog_status" class="searchable">
                         <option value="" disabled selected>Select Dashboard</option>
                         <option value="not started">Not Started</option>
                         <option value="pending">Pending</option>
                         <option value="approved">Approved</option>
                         <option value="rejected">Rejected</option>
                     </select>
                 </div> -->
		    	</form>
		    	</div>
		    	<div class="modal-footer">
			        <button type="button" class="btn btn-secondary close-modal-button">Close</button>
			        <button type="button" class="btn btn-primary">Save changes</button>
			      </div>
		  </section>
		</div>
    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
 <form action="<%=request.getContextPath()%>/get-rr-bses" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="rrbses_id" id="rrbses_id"/>
    </form>
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
   
    
 // Stack of modals
    let currentlyOpenModals = [];

    const noModalsOpen = () => !currentlyOpenModals.length;

    const openModal = modalId => {
      const modalWrapper = document.getElementById(modalId);
      const body = document.querySelector("body");
      modalWrapper.classList.add("visible");
      currentlyOpenModals.push(modalWrapper);
      if (modalWrapper.classList.contains("visible")) {
          // Disable scroll
          body.style.overflow = "hidden";
      } else {
          // Enable scroll
          body.style.overflow = "auto";
      }
    };
    
    // By definition, it's always the topmost modal that will be closed first
    const body = document.querySelector("body");
    const closeTopmostModal = () => {
      if (noModalsOpen()) {
    	  body.style.overflow = "auto";
        return;
      }

      const modalWrapper = currentlyOpenModals[currentlyOpenModals.length - 1];
      modalWrapper.classList.remove("visible");
      currentlyOpenModals.pop();
    };

    const modalTriggers = document.querySelectorAll(".modal-trigger");
    modalTriggers.forEach(modalTrigger => {
      modalTrigger.addEventListener("click", clickEvent => {
        const trigger = clickEvent.target;
        const modalId = trigger.getAttribute("data-modal-id");
        openModal(modalId);
      });
    });

    // Otherwise, clicking the content of a modal will propagate the click to the modal wrapper,
    // and that will close the entire thing. That's not what we want!
    document.querySelectorAll(".modal-window").forEach(modal => {
      modal.addEventListener("click", clickEvent => {
        clickEvent.stopPropagation();
      });
    });

    const modalWrappers = document.querySelectorAll(".modal-wrapper");
    modalWrappers.forEach(modalWrapper => {
      modalWrapper.addEventListener("click", () => {
        closeTopmostModal();
        body.style.overflow = "auto";
      });
    });

    document.querySelectorAll(".close-modal-button").forEach(closeModalButton => {
      closeModalButton.addEventListener("click", () => {
        closeTopmostModal();
        body.style.overflow = "auto";
      });
    });

    document.body.addEventListener("keyup", keyEvent => {
      if (keyEvent.key === "Escape") {
        closeTopmostModal();
        body.style.overflow = "auto";
      }
    });
    //double modal end 
     $(function() {
		$('.modal-trigger').on('click', function() {
			$('.imagepreview').attr('src', $(this).find('img').attr('src'));
			   
		});		
});
    //color change function
    var rows = document.getElementById("example").getElementsByTagName("tbody")
    [0].getElementsByTagName("tr");

      // loops through each row
      for (i = 0; i < rows.length; i++) {cells = rows[i].getElementsByTagName('td');
              if (cells[0].innerHTML == 'rejected')
                  rows[i].className = "red";

              if (cells[0].innerHTML == 'pending')
                  rows[i].className = "yellow";
              
              if (cells[0].innerHTML == 'approved')
                  rows[i].className = "green";
              
              if (cells[0].innerHTML == 'Not Started')
                  rows[i].className = "White";
   } 
      $('#example tbody tr > *:nth-child(1)').hide();
    /* $("select").change(function(){
    	$("option:selected",this).text().trim().toLowerCase() == "Pending" ? $(this).closest("#example tr").css("background-color","orange"):
    	$("option:selected",this).text().trim().toLowerCase() == "approved" ? $(this).closest("#example tr").css("background-color","green"):
    	$("option:selected",this).text().trim().toLowerCase() == "rejected" ? $(this).closest("#example tr").css("background-color","red") : $(this).closest(".mdl-data-table tr").css("background-color","green")

    	});  */
    $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
        $('.notification.dropdown-trigger').dropdown({
            coverTrigger: false,
            closeOnClick: false,
        });
        $('#example').DataTable({
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric',
                    targets: 'no-sort', orderable: false,
                },
                { "width": "20px", "targets": [7] },
            ], "scrollCollapse": true,
            fixedHeader: true,
            "sScrollY": 400,
            ordering: false,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                var input = $('.dataTables_filter input')
                    .unbind(),
                    self = this.api(),
                    $searchButton = $('<i class="fa fa-search" title="Go" id="save_post">')
                        .click(function () { self.search(input.val()).draw(); }),
                    $clearButton = $('<i class="fa fa-close" title="Reset">')
                        .click(function () {
                            input.val('');
                            $searchButton.click();
                        })
                $('.dataTables_filter').append(
                    '<div class="right-btns"></div>');
                $('.dataTables_filter div').append(
                    $searchButton, $clearButton);
            }
        });

        $('.clear-filters').click(function () {
            $('#hod_fk').val("");
            $('#module_fk').val("");
        });
    });
    	//multiple modal end
    	 
    	/* $(function(){
    		  $(".mdl-data-table tr").each(function(){
    		    var col_val = $(this).find("td:eq(1)").text();
    		    if (col_val == "CONFIRMED"){
    		      $(this).addClass('selected');  //the selected class colors the row green//
    		    } else {
    		      $(this).addClass('bad');
    		    }
    		  });
    		}); */
 
    <%-- 	var filtersMap = new Object();
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            getRRList();
        });
		var filters = window.localStorage.getItem("RandRBSESFilters");
	    if($.trim(filters) != '' && $.trim(filters) != null){
	      	  var temp = filters.split('^'); 
	      	  for(var i=0;i< temp.length;i++){
		        	  if($.trim(temp[i]) != '' ){
		        		  var temp2 = temp[i].split('=');
			        	  if($.trim(temp2[0]) == 'work_id_fk' ){
			        		  getWorksFilterList(temp2[1]);
			        	  }else if($.trim(temp2[0]) == 'hod'){
			        		  getHODFilterList(temp2[1]);
			        	  }
		        	  }
		          }
	          }
        $('.clear-filters').click(function () {
            $('#hod').val("");
            $('#work_id_fk').val("");
            window.localStorage.setItem("RandRBSESFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/rr-bses";
        });
        
        function addInQueHOD(hod){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('hod')) delete filtersMap[key];
	   	   	});
	      	if($.trim(hod) != ''){
	        	filtersMap["hod"] = hod;
	      	}
	    }
	    function addInQueWork(work_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('work_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(work_id_fk) != ''){
	        	filtersMap["work_id_fk"] = work_id_fk;
	      	}
	    }
	    function getHODFilterList(hodVal) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	        if ($.trim(hod) == "") { 
	        	$("#hod option:not(:first)").remove();
	        	var myParams = { hod: hod,work_id_fk : work_id_fk};
	        	$.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getHodFilterListInRRBSES",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (hodVal == val.hod)?'selected':'';
		                         $("#hod").append('<option value="' + val.hod + '"'+selectedFlag+'>' + $.trim(val.designation)+"-" + $.trim(val.user_name)  +'</option>');
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
	    
	    function getWorksFilterList(work) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	        if ($.trim(work_id_fk) == "") {
	        	$("#work_id_fk option:not(:first)").remove();
	        	var myParams = { hod: hod,work_id_fk : work_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorkFilterListInRRBSES",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var workShortName = '';
	                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
		                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
	    
	    function getRandR(rrbses_id){
	    	$("#rrbses_id").val(rrbses_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-rr-bses');
	    	$('#getForm').submit();
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
	    
	    var queue = 1;
	    function getRRList() {
			$(".page-loader-2").show();

			getWorksFilterList('');
			getHODFilterList('');
	     	
	     	
	    	var work_id_fk = $("#work_id_fk").val();
	    	var hod = $("#hod").val();
	    	
	    	var filters = '';
	    	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
	    		filters = filters + key +"="+filtersMap[key] + "^";
	    		window.localStorage.setItem("RandRBSESFilters", filters);
				});
	    	   	table = $('#rr-bses').DataTable();
	    		table.destroy();
				var i = 0;
	    		$.fn.dataTable.moment('DD-MMM-YYYY');
	    		var rowLen = 0;
	    		var myParams =  "work_id_fk="
	    				+ work_id_fk + "&hod="+ hod;

	    		/***************************************************************************************************/
	    		$("#rr-bses").DataTable({
	    			
	    							"bProcessing" : true,
	    							"bServerSide" : true,
	    							"sort" : "position",
	    							//bStateSave variable you can use to save state on client cookies: set value "true" 
	    							"bStateSave" : false,
	    							 stateSave: true,
	    							 "fnStateSave": function (oSettings, oData) {
	    							 	localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
	    							},
	    							 "fnStateLoad": function (oSettings) {
	    								return JSON.parse(localStorage.getItem('MRVCDataTables'));
	    							 },
	    							//Default: Page display length
	    							"iDisplayLength" : 10,
	    							"iData" : {
	    								"start" : 52
	    							},
	    							//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	    							"iDisplayStart" : 0,
	    							"fnDrawCallback" : function() {
	    								//Get page numer on client. Please note: number start from 0 So
	    								//for the first page you will see 0 second page 1 third page 2...
	    								//Un-comment below alert to see page number
	    								//alert("Current page number: "+this.fnPagingInfo().iPage);
	    							},
	    							//"sDom": 'l<"toolbar">frtip',
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
	    										}), self = this.api(), $searchButton = $(
	    										'<i class="fa fa-search" title="Go" >')
	    								//.text('Go')
	    								.click(function() {
	    									self.search(input.val()).draw();
	    								}), $clearButton = $(
	    										'<i class="fa fa-close" title="Reset">')
	    								//.text('X')
	    								.click(function() {
	    									input.val('');
	    									$searchButton.click();
	    								})
	    								$('.dataTables_filter').append(
	    										'<div class="right-btns"></div>');
	    								$('.dataTables_filter div').append(
	    										$searchButton, $clearButton);
	    								rowLen = $('#rr-bses tbody tr:visible').length
	    								if(rowLen <= 1 &&  queue == 1){									
	    									$('#rr-bses').dataTable().api().draw(); 
	    									getRRList();
	    									queue++;
	    							    } 
	    							}
	    							,
	    							columnDefs : [ {
	    								"targets" : 'no-sort',
	    								"orderable" : false,
	    							}],
	    							"sScrollX" : "100%",
	    							"sScrollXInner" : "100%",
	    							"ordering":false,
	    							"bScrollCollapse" : true,
	    							"language" : {
	    								"info" : "_START_ - _END_ of _TOTAL_",
	    								paginate : {
	    									next : '<i class="fa fa-angle-right"></i>', 
	    									previous : '<i class="fa fa-angle-left"></i>'  
	    								}
	    							},
	    							
	    							"bDestroy" : true,
	    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/rr-bses?"+myParams,
	    		        "aoColumns": [
	    		        	
	      		            { "mData": function(data,type,row){
	                             if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_short_name; }
	      		            } },
	      		         	{ "mData": function(data,type,row){
	                             if($.trim(data.hod) == ''){ return '-'; }else{ return data.designation +"-"+ data.user_name ; }
	      		            } },
	      		       
	    		            { "mData": function(data,type,row){ 
	    		            	if($.trim(data.mrvc_responsible_person) == ''){ return '-'; }else{return data.res_designation +"-"+ data.res_user_name ; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		            	if($.trim(data.bses_agency_name) == ''){ return '-'; }else{ return data.bses_agency_name; }
	    		            } },
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.agency_responsible_person) == ''){ return '-'; }else{ return data.agency_responsible_person; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		         		var rrbses_id = "'"+data.rrbses_id+"'";
	    	                    var actions = '<a href="javascript:void(0);"  onclick="getRandR('+rrbses_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
	    		            	return actions;
	    		            } }
	    		            
	    		        ]
	    		    });
	    	
	    	
		  $(".page-loader-2").hide();  		     
	  	
	 } --%>

    </script>

</body>

</html>