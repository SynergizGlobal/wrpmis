<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activity Progress Form</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
     
     
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">

    <style>
        body {
            overflow-x: hidden;
        }

        .hiddendiv.common {
            width: 99vw !important;
        }
        .input-field .searchable_label{
        	font-size: 0.85rem;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }

        .primary-text {
            color: #2E58AD;
            font-weight: 500;
        }

        /* dots related styling  */

        /* horizontal line*/

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }

        /* selects toggle class */

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        #dotgroup1 .dotgroup-scroll {
            width: 100%;
            max-width: 100%;
            height: 100px;
            padding-top: 30px;
            overflow-x: auto;
            white-space: nowrap;

        }

        #dotgroup1 .horizontal-line {
            border: 1px solid #777;
            position: relative;
            bottom: -19px;
            height: 8px;
            box-shadow: 0 0 3px inset #555;
        }

        #dotgroup1 .dot-container {
            position: relative;
            display: inline-block;
        }
		.dot-container{
			min-width:55px;
		}		  
		 #component_circles .dot-container:first-of-type a{
            margin-left: -10px;
        }
        #dotgroup1 .dot-line {
            /* width: 30px; */
            width: inherit;
            min-width:30px;
            border: 2px solid #777;
            position: absolute;
            top: 14px;
            left: -17px;
            z-index: 0;
        }

        #dotgroup1 .dot {
            height: 30px;
            width: 30px;
            z-index: 1;
            background-color: #bbb;
            color: #333;
            border-radius: 50%;
            border: 1px solid #bbb;
            display: inline-block;
            position: relative;
            margin: 0 12px;           
        }
		.dot.active{
		 box-shadow: 0px 0px 14px 6px #444,0px 0px 25px 1px #777;
		     border: 1px solid black !important;		 
		 }
		 .dot.active .project{
		 font-weight:bold;
		 }

        #dotgroup1 .project::before {
            content: none;
        }

        #dotgroup1 a .project.odd {
            position: relative;
            top: 30px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 a .project.even {
            position: relative;
            bottom: 20px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 .dot.not-started {
            background-color: #fff;
        }

        #dotgroup1 .dot.in-progress {
            background-color: #FFFF00;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }

/* 
        #dotgroup1 .dot.delayed {
            background-color: #f00;
        } */
        
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
		.page-loader-3 {
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
    </style>
  <style>
       
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

        .box.not-started {
            background-color: #fff;
        }

        .box.in-progress {
            background-color: #FFFF00;
        }

        .box.completed {
            background-color: #05a705;
        }
         p.priokind{
        	font-weight:600;
        	margin-top: 10px !important;     
        }

       /*  .box.delayed {
            background-color: #f00;
        } */

        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Activity Progress Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <div class="row">
                        	<c:if test="${not empty success}">
						        <div class="center-align m-1 close-message">	
								   ${success}
								</div>
								</c:if>
							<c:if test="${not empty error }">
								<div class="center-align m-1 close-message">
								   ${error}
								</div>
							</c:if>
                            <form id="activitiesChartForm" name="activitiesChartForm" method="post" enctype="multipart/form-data">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Project <span class="required">*</span></p>
                                            <select class="searchable validate-dropdown" id="project_id" name="project_id"
                                                onchange="getActivitiesWorksList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${projectsList }">
                                                    <option value="${obj.project_id }" <c:if test="${obj.project_id eq activitiesData.project_id }">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="project_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m8 s12 input-field">
                                           <p class="searchable_label">Work <span class="required">*</span></p>
                                            <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                                onchange="getActivitiesContractsList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id }" <c:if test="${obj.work_id eq activitiesData.work_id }">selected</c:if>>${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="work_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m12 s12 input-field">
                                           <p class="searchable_label">Contract <span class="required">*</span></p>
                                            <!-- <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown"
                                                onchange="getComponentIdsList('1');getActivitiesStructures(); getActivitiesLines(); getActivitiesSections();"> -->
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown"
                                                onchange="resetWorksAndProjectsDropdowns();getActivitiesStructures(); getActivitiesLines(); getActivitiesSections();">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${contractsList }">
                                                	<option name="${obj.work_id_fk }" value="${obj.contract_id }" <c:if test="${obj.contract_id eq activitiesData.contract_id }">selected</c:if>>${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if>${obj.contract_short_name}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="contract_id_fkError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <!-- row 1  -->
                                        <div class="col m4 s12 input-field">
                                           <p class="searchable_label">Structure <span class="required">*</span></p>
                                            <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_structure_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m4 s12 input-field" id="strip_chart_line_id_fkDiv" style="display: none;">
                                           <p class="searchable_label">Line</p>
                                            <select id="strip_chart_line_id_fk" name="strip_chart_line_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field" id="strip_chart_section_id_fkDiv" style="display: none;">
                                           <p class="searchable_label">Section</p>
                                            <select id="strip_chart_section_id_fk" name="strip_chart_section_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row" style="margin-bottom: 20px;display:none;" id="component_circles_row">
                                        <div class="col m12 s12" id="dotgroup1">
                                            <div class="dotgroup-scroll">
                                                <div id="component_circles" style="padding: 10px;">
                                                    <!-- <div class="dot-container">
                                                        <a href="javascript:void(0);" class="dot"
                                                            style="margin-left: 0;">
                                                            <span class="project odd">P2P4P2P4</span></a>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>  -->

                                                </div>
                                            </div>
                                        </div>
                                    </div>

 									<div class="row legends" id="legends" style="display:none;">
                                        <div class="col m4 s3 center-align">
                                            <span class="box not-started"></span>
                                            <span class="description">Not Started</span>
                                        </div>
                                        <div class="col m4 s3  center-align">
                                            <span class="box in-progress"></span>
                                            <span class="description">In Progress</span>
                                        </div>
                                        <div class="col m4 s3  center-align">
                                            <span class="box completed"></span>
                                            <span class="description">Completed</span>
                                        </div>
                                        <!-- <div class="col m3 s6 center-align">
                                            <span class="box delayed"></span>
                                            <span class="description">Delayed</span>
                                        </div> -->
                                    </div>
                                    <div class="row">
                                    	
                                    	<div class="col m4 s12 input-field">
                                           <p class="searchable_label">Component ID <span class="required">*</span></p>
                                            <select class="searchable validate-dropdown" id="strip_chart_component_id" name="strip_chart_component_id" onchange="getComponentAndActivitiesList(this.value);">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_component_idError" class="error-msg" ></span>
                                        </div>
                                        
                                        <div class="col m4 s12 input-field">
                                        	<p class="searchable_label">Component</p>
                                            <input id="strip_chart_component" name="strip_chart_component" type="text" style="height: 2.75rem;" readonly="readonly">
                                            <!-- <p>Component</p>
                                            <select class="searchable validate-dropdown" id="strip_chart_component" name="strip_chart_component">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_componentError" class="error-msg" ></span> -->
                                        </div>
                                        
                                        <div class="col m4 s12 input-field">
                                           <p class="searchable_label">Activity <span class="required">*</span></p>
                                            <select id="strip_chart_activity_id" name="strip_chart_activity_id"
                                                class="searchable validate-dropdown" onchange="getActivitiesDetails(this.value);">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_activity_idError" class="error-msg" ></span>
                                        </div>
                                    </div>

                                    <div class="row" style="margin-bottom:30px;margin-top:20px">
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Planned Start <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m3 s6">
                                            <div id="plannedStart"> </div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Planned Finish <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div id="plannedFinish"> </div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-bottom:30px">
                                        <div class="col m2 s6 ">
                                            <div class="primary-text">Scope <span class="right">:</span></div>
                                        </div>
                                        <div class="col m2 s6 ">
                                            <div style="opacity: 0.8;"><input type="text" id="totalScope" name="scope" readonly="readonly" style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"/><span style="width:40%;" class="unit_fk"></span></div>
                                        </div>
                                        <div class="col m2 s6 ">
                                            <div class="primary-text">Completed <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m2 s6 ">
                                            <div style="opacity: 0.8;"><input type="text" id="completed" name="completed" readonly="readonly" style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;" /><span style="width:40%;" class="unit_fk"></span></div>
                                        </div>
                                        <div class="col m2 s6 ">
                                            <div class="primary-text">Remaining <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m2 s6 ">
                                            <div style="opacity: 0.8;"><input type="text" id="remaining" name="remaining" readonly="readonly" style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;" /><span style="width:40%;" class="unit_fk"></span></div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m6 s12 input-field">
                                            <input id="progress_date" name="progress_date" type="text" class="validate datepicker">
                                            <label for="progress_date">Progress Date <span class="required">*</span></label>
                                            <button type="button" id="progress_date_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                            <span id="progress_dateError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <input id="progress" name="progress" type="text" class="validate">
                                            <label for="progress">Progress for the Day <span class="required">*</span></label>
                                            <span class="units unit_fk"></span>
                                            <span id="progressError" class="error-msg" ></span>
                                        </div>
                                    </div>

                                    <div class="row">                
                                        <div class="col s6 m6 input-field center-align">
                                            <p class="priokind">Any Issue ?</p>  
                                        </div>
                                        <div class="col s6 m6 input-field">
                                            <p class="radiogroup"
                                                style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="is_there_issue" type="radio"
                                                        value="yes" />
                                                    <span>Yes</span>
                                                </label>
                                                <label>
                                                    <input class="with-gap" name="is_there_issue" type="radio"
                                                        value="no" Checked/>
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>                                  
                                    </div>
                                           
                                    <div id="issue_yes" style="display: none;">
                                        <div class="row">
                                            <h6 class="center-align" style="color:#2E58AD;font-weight:600">Issue Details   </h6>
                                            <!-- <div class="col s12 m6 input-field" style="margin-top: 40px;"> -->
                                            <div class="col s12 m6 input-field" style="margin-top: 23px;">
                                            	<p>Issue Category</p>
                                                <select class="browser-default searchable" id="issue_category_id" name="issue_category_id">
                                                    <option value="" selected>Select</option>
                                                </select>
                                                <!-- <label>Issue Category</label> -->
                                                <span id="issue_category_idError" class="error-msg" ></span>
                                            </div>
                                            <div class="col s12 m6 input-field" style="padding-top: 4px;">

                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="Low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="Medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="High" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                                <span id="issue_priority_idError" class="error-msg" ></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12 m12 input-field">
                                                <textarea id="issue_description" name="issue_description" class="materialize-textarea"
                                                    data-length="500"></textarea>
                                                <label for="issue_description">Issue Description</label>
                                                <span id="issue_descriptionError" class="error-msg" ></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                       <!--  <div class="col m12 s12" id="issue_yes_attachment" style="display: none;"> -->
                                            <div class="col m12 s12 file-field input-field" >
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file" id="stripChartFile" name="stripChartFile">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text">
                                                </div>
                                            </div>
                                        </div>
                                      <div class="row">
                                        <div class="col m12 s12 input-field">
                                            <textarea id="remarks" name="remarks" class="materialize-textarea"
                                                data-length="500">${activitiesData.remarks}</textarea>
                                            <label for="remarks" class="">Issue/Action Taken/Remark </label>
                                        </div>
                                    </div>
                                    </div>
                                   
                                    <input type="hidden" id="activity_id" name="activity_id" value="${activitiesData.activity_id}" />
                                    
                                    <input type="hidden" id="strip_chart_component_id_name" name="strip_chart_component_id_name" />
                                    <input type="hidden" id="strip_chart_line" name="strip_chart_line" />
                                    <input type="hidden" id="strip_chart_section_name" name="strip_chart_section_name" />
                                    <input type="hidden" id="strip_chart_activity_name" name="strip_chart_activity_name" />
            
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="button" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;"  onclick="saveProgress();">Update</button>
                                            </div>
                                        </div>
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <a class="btn waves-effect waves-light bg-s"
                                                    style="width: 100%;" href="<%=request.getContextPath() %>/activity-progress">Cancel</a>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

	<!-- Page Loader starts-->
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
	
	<div class="page-loader-3" style="display: none;">
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
<!-- Page Loader ends-->

    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    

    <script>
        $(document).ready(function () {
            $('#issue_category_id').formSelect();
            $('.searchable').select2();
            // hide or display selects by clicking 
            $(".arr").click(function () {
                $('#toggle-selects').toggleClass("open");
                $('#hide-btn').toggleClass("fa-plus-circle");
                $('#hide-btn').toggleClass("fa-minus-circle");

            });
            
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });
            
            $('#progress_date').datepicker({                   
  	    	  maxDate: new Date(),
  	    	  format:'dd-mm-yyyy',
  	    	  //perform click event on done button
  	    	  onSelect: function () {
  	    	     $('.confirmation-btns .datepicker-done').click();
  	    	  }
  	        });
            
            $('#issue_description').characterCounter();
  	      	$('#remarks').characterCounter();
  	     
	  	    $('input[name=is_there_issue]').change(function () {
	              var radioval = $('input[name=is_there_issue]:checked').val();
	              if (radioval == 'yes') {											
	                  $('#issue_yes').css("display", "block");
	                  getIssuesCategoryList();
	              } else if (radioval == 'no') {
	                  $('#issue_yes').css("display", "none");
	                  $("#issue_category_id option:not(:first)").remove();
	                  $('#issue_category_id').formSelect();
	              }
	        });
  	      

            var project_id = "${activitiesData.project_id}";
            if ($.trim(project_id) != '') {
            	$("#project_id").val(project_id);
            	$("#project_id").select2();
            	getActivitiesWorksList(project_id);
            }
           
        });


        //geting works list from database    
        function getActivitiesWorksList(projectId) { 
        	$(".page-loader").show();
        	$("#contract_id_fk option:not(:first)").remove();    	
            $("#work_id_fk option:not(:first)").remove();
            
            $("#strip_chart_structure_id_fk option:not(:first)").remove();
            $("#strip_chart_line_id_fk option:not(:first)").remove();
            $("#strip_chart_section_id_fk option:not(:first)").remove();
        	
        	$('.searchable').select2();
        	
        	clearComponentCircle();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                    	var id1 = "";
                        var id2 = "${activitiesData.work_id}";
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                if ($.trim(id2) != '' && val.work_id == $.trim(id2)) {
                                	id1 = val.work_id;
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                        
                        if ($.trim(id1) != '' && $.trim(id2) != '') {
                        	getActivitiesContractsList(id2);
                        }
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }

        //geting contracts list    
        function getActivitiesContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            
            $("#strip_chart_structure_id_fk option:not(:first)").remove();
            $("#strip_chart_line_id_fk option:not(:first)").remove();
            $("#strip_chart_section_id_fk option:not(:first)").remove();
        	$('.searchable').select2();
        	clearComponentCircle();
        	
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesContractsList",
                    data: myParams, cache: false,
                    success: function (data) {
                    	var id1 = "";
                    	var id2 = "${activitiesData.contract_id}";                        
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_short_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                                if ($.trim(id2) != '' && val.contract_id == $.trim(id2)) {
                                	id1 = val.contract_id;
                                    $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_short_name) + '</option>');
                                } else {
                                    $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_short_name) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                        
                        if ($.trim(id1) != '' && $.trim(id2) != '') {
                        	getActivitiesStructures(id2);
                        }
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();
        	
        	clearComponentCircle();
        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){        			
       			workId = $("#contract_id_fk").find('option:selected').attr("name");
       			projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length);
       			$("#project_id").val(projectId);
       			$("#project_id").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
                $('.searchable').select2();
            }
       		
        }
        
        function clearComponentCircle(){        	
        	$("#strip_chart_component").attr("readonly", false); 
        	$("#strip_chart_component").val('');
        	$("#strip_chart_component").attr("readonly", true);
        	
        	$("#strip_chart_component_id option:not(:first)").remove();
        	$("#strip_chart_activity_id option:not(:first)").remove();
        	
        	$('.searchable').select2();
        	
            $("#component_circles").html('');
            $("#component_circles_row").hide();
            $("#legends").hide();            
            $("#plannedStart").html("");
        	$("#plannedFinish").html("");
        	$("#totalScope").val('');
        	$("#completed").val('');
        	$("#remaining").val('');
        	$(".unit_fk").html("");
        	$("#activity_id").val("");        	
        }
        
        function getActivitiesStructures() {
        	$(".page-loader-2").show();
        	var contract_id_fk = $("#contract_id_fk").val();
            $("#strip_chart_structure_id_fk option:not(:first)").remove();
            if ($.trim(contract_id_fk) != "") {
            	var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesStructures",
                    data: myParams, cache: false,
                    success: function (data) {
                    	var id1 = "";
                    	var id2 = "${activitiesData.strip_chart_structure_id}";
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_structure_id_fk == $.trim(id2)) {
	                            	id1 = val.strip_chart_structure_id_fk;
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            } else {
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '">' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader-2").hide();
                        
                        
                        if ($.trim(id1) != '' && $.trim(id2) != '') {
                        	getComponentIdsList(id2);
                        }
                    }
                });
            }else{
            	$(".page-loader-2").hide();
            }
        }
        
        function getActivitiesLines() {
        	var contract_id_fk = $("#contract_id_fk").val();
            $("#strip_chart_line_id_fk option:not(:first)").remove();
            if ($.trim(contract_id_fk) != "") {
            	var myParams = { contract_id_fk: contract_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesLines",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                        	$("#strip_chart_line_id_fkDiv").show();
                            $.each(data, function (i, val) {
                                $("#strip_chart_line_id_fk").append('<option value="' + val.strip_chart_line_id_fk + '">' + $.trim(val.strip_chart_line_id_fk) + '</option>');
                            });
                        }else{
                        	$("#strip_chart_line_id_fkDiv").hide();
                        }
                        $('.searchable').select2();
                    }
                });
            }
        }
        
        function getActivitiesSections() {
        	var contract_id_fk = $("#contract_id_fk").val();
            $("#strip_chart_section_id_fk option:not(:first)").remove();
            if ($.trim(contract_id_fk) != "") {
            	var myParams = { contract_id_fk: contract_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesSections",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                        	$("#strip_chart_section_id_fkDiv").show();
                            $.each(data, function (i, val) {
                                $("#strip_chart_section_id_fk").append('<option value="' + val.strip_chart_section_id_fk + '">' + $.trim(val.strip_chart_section_name) + '</option>');
                            });
                        }else{
                        	$("#strip_chart_section_id_fkDiv").hide();
                        }
                        $('.searchable').select2();
                    }
                });
            }
        }

        //geting contracts list    
        function getComponentIdsList() {   
        	$(".page-loader-3").show();
        	
        	clearComponentCircle();
            
            var contract_id_fk = $("#contract_id_fk").val();
            var structureId = $("#strip_chart_structure_id_fk").val();
            var laneId = $("#strip_chart_line_id_fk").val();
            var sectionId = $("#strip_chart_section_id_fk").val();
            var myParams = { contract_id_fk: contract_id_fk, strip_chart_structure_id_fk: structureId, strip_chart_line_id_fk: laneId, strip_chart_section_id_fk: sectionId };
            var html = '';

            if ($.trim(contract_id_fk) != "" && $.trim(structureId) != "" ) {                
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivityComponentIdsList",
                    data: myParams, cache: false,
                    success: function (data) {
                    	var id1 = "";
                    	var id2 = "${activitiesData.strip_chart_component_id}";
                        var strip_chart_component = "${activitiesData.strip_chart_component}";
                        
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var componentIdAndName = "'" + val.strip_chart_component_id + "','" +val.strip_chart_component+ "'";
                                var className = "odd";
                                if(i%2 == 0){
                                	className = "even";
                                }
                                
                                var pointerEvent = "";
                                if(val.component_id_color == "completed"){
                                	pointerEvent = "pointer-events: none;";
                                	html = html + '<div class="dot-container" id="dd'+val.strip_chart_component_id+'" >'
                                    + '<a href="javascript:void(0);" id="'+val.strip_chart_component_id+'" style="'+pointerEvent+'" onclick="getActivitiesActivitiesList('+componentIdAndName+');" class="dot '+val.component_id_color+'" >'
                                    + '<span class="project '+className+'">'+val.strip_chart_component_id+'</span></a>';
                                   // if(i != 0){
                                    	html = html + '<span class="dot-line"></span>';
                                    //}
                                    html = html + '</div>';
                                	
                                	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '" disabled>' + $.trim(val.strip_chart_component_id) + '</option>');
                                } else {                
                                	
                                	html = html + '<div class="dot-container" id="dd'+val.strip_chart_component_id+'">'
                                    + '<a href="javascript:void(0);" id="'+val.strip_chart_component_id+'" style="'+pointerEvent+'" onclick="getActivitiesActivitiesList('+componentIdAndName+');" class="dot '+val.component_id_color+'" >'
                                    + '<span class="project '+className+'">'+val.strip_chart_component_id+'</span></a>';
                                   // if(i != 0){ 
                                    	html = html + '<span class="dot-line"></span>';
                                   // }
                                    html = html + '</div>';
                                	
                                	if ($.trim(id2) != '' && val.strip_chart_component_id == $.trim(id2)) {
                                		id1 = val.strip_chart_component_id;
    	                            	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '" selected>' + $.trim(val.strip_chart_component_id) + '</option>');
    	                            } else {
    	                            	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '">' + $.trim(val.strip_chart_component_id) + '</option>');
    	                            }
                                }                                
                            });
                            
                            $('.searchable').select2();
                        }
                        $("#component_circles").html(html);
                        $("#component_circles_row").show();
                        $('#legends').show();
                      //dynamic width based on content
                        $.each($('.dot-container'), function (i, val) {
                        	$(val).css("width",$(val).find('a').children().width());
                        });
                        
                        $(".page-loader-3").hide();
                        
                        
                        if ($.trim(id1) != '' && $.trim(id2) != '') {
                        	getActivitiesActivitiesList(id2,strip_chart_component);
                        }
                    }
                });
            }else{
            	$(".page-loader-3").hide();
            	$("#component_circles").html(html);
                $("#component_circles_row").hide();
                $('#legends').hide();
            }
            
            $("#plannedStart").html("");
        	$("#plannedFinish").html("");
        	var scope = "";
        	var completed = "";
        	var remaining = "";
        	$("#totalScope").val(scope);
        	$("#completed").val(completed);
        	$("#remaining").val(remaining);
        	$(".unit_fk").html("");
        	$("#activity_id").val("");
        }

        function getActivitiesActivitiesList(componentId,componentName) {
        	
        	$( ".dot" ).removeClass( "active" );
        	$( "#"+componentName ).addClass( "active" );
        	
        	/* $("#strip_chart_component option:not(:first)").remove();
        	$("#strip_chart_component").append('<option value="' + componentName + '" selected>' + $.trim(componentName) + '</option>');
        	$('.searchable').select2(); */
        	
        	$("#strip_chart_component").attr("readonly", false); 
        	$("#strip_chart_component").val(componentName);
        	$("#strip_chart_component").attr("readonly", true); 
        	
        	$("#strip_chart_component_id").val(componentId);
        	$(".page-loader").show();
            $("#strip_chart_activity_id option:not(:first)").remove();
            
            var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
            var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
            var strip_chart_section_id_fk = $("#strip_chart_section_id_fk").val();
            
            if ($.trim(componentId) != "") {
                var myParams = { strip_chart_component_id: componentId,strip_chart_component : componentName,
                		strip_chart_line_id_fk : strip_chart_line_id_fk,strip_chart_structure_id_fk : strip_chart_structure_id_fk,
                		strip_chart_section_id_fk : strip_chart_section_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesActivitiesList",
                    data: myParams, cache: false,
                    success: function (data) {
                    	var id1 = "";
                    	var id2 = "${activitiesData.strip_chart_activity_id}";
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_activity_id == $.trim(id2)) {
	                            	id1 = val.strip_chart_activity_id;
	                                $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '" selected>' + $.trim(val.strip_chart_activity_name) + '</option>');
	                            } else {
	                                $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '">' + $.trim(val.strip_chart_activity_name) + '</option>');
	                            }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();                        
                        
                        if ($.trim(id1) != '' && $.trim(id2) != '') {
                        	getActivitiesDetails(id2);
                        }
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
            
            $("#plannedStart").html("");
        	$("#plannedFinish").html("");
        	var scope = "";
        	var completed = "";
        	var remaining = "";
        	$("#totalScope").val(scope);
        	$("#completed").val(completed);
        	$("#remaining").val(remaining);
        	$(".unit_fk").html("");
        	$("#activity_id").val("");
        }
        
        function getComponentAndActivitiesList(componentId){
        	$( ".dot" ).removeClass( "active" );
        	$( "#"+componentId ).addClass( "active" );
        	
        	var $scroller = $('.dotgroup-scroll');
            var childs=$scroller.children().children().length;
            var indexing=$(".dot-container").index($("#dd"+componentId));
           	var scrollTo=Math.round((indexing*($scroller[0].scrollWidth/childs))-childs);           
            $scroller.animate({'scrollLeft': scrollTo}, 1000);  
                        
        	var componentName = $("#strip_chart_component_id").find('option:selected').attr("name");
        	
        	/* $("#strip_chart_component option:not(:first)").remove();
        	$("#strip_chart_component").append('<option value="' + componentName + '" selected>' + $.trim(componentName) + '</option>');
        	$('.searchable').select2(); */
        	$("#strip_chart_component").attr("readonly", false); 
        	$("#strip_chart_component").val(componentName);
        	$("#strip_chart_component").attr("readonly", true);
        	
        	$(".page-loader").show();
            $("#strip_chart_activity_id option:not(:first)").remove();
            
            var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
            var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
            var strip_chart_section_id_fk = $("#strip_chart_section_id_fk").val();
            
            if ($.trim(componentId) != "") {
                var myParams = { strip_chart_component_id: componentId,strip_chart_component : componentName,
                		strip_chart_line_id_fk : strip_chart_line_id_fk,strip_chart_structure_id_fk : strip_chart_structure_id_fk,
                		strip_chart_section_id_fk : strip_chart_section_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesActivitiesList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '">' + $.trim(val.strip_chart_activity_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
            
            $("#plannedStart").html("");
        	$("#plannedFinish").html("");
        	var scope = "";
        	var completed = "";
        	var remaining = "";
        	$("#totalScope").val(scope);
        	$("#completed").val(completed);
        	$("#remaining").val(remaining);
        	$(".unit_fk").html("");
        	$("#activity_id").val("");
        }
        
        
        function getIssuesCategoryList() {
        	$("#issue_category_id option:not(:first)").remove();
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getIssuesCategoryListForActivities",
                cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            $("#issue_category_id").append('<option value="' + val.category + '">' + $.trim(val.category) + '</option>');
                        });
                    }
                    $('#issue_category_id').formSelect();
                }
            });
        }

        function getActivitiesDetails(activitiId) {
        	$(".page-loader").show();
        	
        	$("#plannedStart").html("");
        	$("#plannedFinish").html("");
        	var scope = "";
        	var completed = "";
        	var remaining = "";
        	$("#totalScope").val(scope);
        	$("#completed").val(completed);
        	$("#remaining").val(remaining);
        	$(".unit_fk").html("");
        	$("#activity_id").val("");
        	
            var componentId = $("#strip_chart_component_id").val();
            var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
            var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
            var strip_chart_section_id_fk = $("#strip_chart_section_id_fk").val();
            
            var component_name = "";
            if($.trim(componentId) != ''){
            	component_name = $("#strip_chart_component_id option:selected").text();
            }
            var line_name = "";
            if($.trim(strip_chart_line_id_fk) != ''){
            	line_name = $("#strip_chart_line_id_fk option:selected").text();
            }
            var section_name = "";
            if($.trim(strip_chart_section_id_fk) != ''){
            	section_name = $("#strip_chart_section_id_fk option:selected").text();
            }
            var activity_name = "";
            if($.trim(activitiId) != ''){
            	activity_name = $("#strip_chart_activity_id option:selected").text();
            }
            
            $("#strip_chart_component_id_name").val(component_name);
            $("#strip_chart_line").val(line_name);
            $("#strip_chart_section_name").val(section_name);
            $("#strip_chart_activity_name").val(activity_name);
            
            if ($.trim(strip_chart_activity_id) != "") {
                var myParams = { strip_chart_component_id: componentId, strip_chart_activity_id: activitiId , 
                		strip_chart_line_id_fk : strip_chart_line_id_fk,strip_chart_structure_id_fk : strip_chart_structure_id_fk,
                		strip_chart_section_id_fk : strip_chart_section_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getActivitiesDetails",
                    data: myParams, cache: false,
                    success: function (data) {
                    	$("#plannedStart").html(data.planned_start);
                    	$("#plannedFinish").html(data.planned_finish);
                    	var scope = 0;
                    	if($.trim(data.scope) != ''){
                    		scope = $.trim(data.scope);
                    	}
                    	var completed = 0;
                    	if($.trim(data.completed) != ''){
                    		completed = $.trim(data.completed);
                    	}
                    	var remaining = 0;
                    	if($.trim(data.remaining) != ''){
                    		remaining = $.trim(data.remaining);
                    	}
                    	$("#totalScope").val(scope);
                    	$("#completed").val(completed);
                    	$("#remaining").val(remaining);
                    	
                    	$(".unit_fk").html(data.unit_fk);
                    	
                    	$("#activity_id").val(data.activity_id);
                    	
                    	$(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }

        /* $('form').on('reset', function () {
            $(".searchable").trigger("change");
        }); */
        
        function saveProgress(){
    		if(validator.form()){ // validation perform
    			 $(".page-loader").show();
    			document.getElementById("activitiesChartForm").action = "<%=request.getContextPath() %>/update-activity-progress";
    			document.getElementById("activitiesChartForm").submit();			
    	 	}
    	}
    	    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#activitiesChartForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"strip_chart_structure_id_fk": {
    				 		required: true
    				 	  },"strip_chart_component": {
    				 		required: false
    				 	  },"strip_chart_component_id": {
    				 		required: true
    				 	  },"strip_chart_activity_id": {
    				 		required: true
    				 	  },"progress_date": {
    				 		required: true
    				 	  },"remaining":{
    				 		 required: true
    				 	  },"progress": {
    			 		    required: true,
    			 		    number:true,
    			 		    progressLessThanEquals:"#remaining"
    			 	   	  },"issue_category_id": {
    				 		required: true
    				 	  },"issue_priority_id": {
    			 		    required: true
    			 	   	  },"issue_description": {
    				 		required: true
    				 	  },"remarks":{
    				 		 required: false
    				 	  }
    				 				
    			 	},
    			   messages: {
	    				 "project_id": {
	   			 			required: 'Select work'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Select work'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Select contract'
    			 	  	 },"strip_chart_structure_id_fk": {
    			 	  		required: 'Select Structure'
   				 	  	 },"strip_chart_component": {
    			 			required: 'Select component'
    			 	  	 },"strip_chart_component_id": {
    			 			required: 'Select component id'
    			 	  	 },"strip_chart_activity_id": {
    			 			required: 'Select Activity'
    			 	  	 },"progress_date": {
    			 			required: 'Select date'
    			 	  	 },"progress": {
    			 			required: 'Enter Progress completed on this date',
    			 			number:'Enter numbers only'
    			 	  	 },"issue_category_id": {
    			 			required: 'Select category'
    			 	  	 },"issue_priority_id": {
    			 			required: 'Select priority'
    			 	  	 },"issue_description": {
    			 			required: 'Enter description'
    			 	  	 },"remarks":{
    			 	  		required: 'Enter BOQ completed'
    				 	  }
    			 				      
    		    },
    			  errorPlacement:
    			 	function(error, element){
	    				if (element.attr("id") == "project_id" ){
	 			 		     document.getElementById("project_idError").innerHTML="";
	 			 			 error.appendTo('#project_idError');
	 			 	    }else if (element.attr("id") == "work_id_fk" ){
    			 		     document.getElementById("work_id_fkError").innerHTML="";
    			 			 error.appendTo('#work_id_fkError');
    			 	    }else if (element.attr("id") == "contract_id_fk" ){
    			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#contract_id_fkError');
    			 	    }else if (element.attr("id") == "strip_chart_structure_id_fk" ){
	   			 	    	 document.getElementById("strip_chart_structure_id_fkError").innerHTML="";
				 			 error.appendTo('#strip_chart_structure_id_fkError');
			 	    	}else if (element.attr("id") == "strip_chart_component" ){
    			 		     document.getElementById("strip_chart_componentError").innerHTML="";
    			 			 error.appendTo('#strip_chart_componentError');
    			 	    }else if (element.attr("id") == "strip_chart_component_id" ){
    			 		     document.getElementById("strip_chart_component_idError").innerHTML="";
    			 			 error.appendTo('#strip_chart_component_idError');
    			 	    }else if (element.attr("id") == "strip_chart_activity_id" ){
    			 		     document.getElementById("strip_chart_activity_idError").innerHTML="";
    			 			 error.appendTo('#strip_chart_activity_idError');
    			 	    }else if (element.attr("id") == "progress_date" ){
    			 		     document.getElementById("progress_dateError").innerHTML="";
    			 			 error.appendTo('#progress_dateError');
    			 	    }else if (element.attr("id") == "progress" ){
    			 		     document.getElementById("progressError").innerHTML="";
    			 			 error.appendTo('#progressError');
    			 	    }else if (element.attr("id") == "issue_category_id" ){
    			 		     document.getElementById("issue_category_idError").innerHTML="";
    			 			 error.appendTo('#issue_category_idError');
    			 	    }else if (element.attr("name") == "issue_priority_id" ){
    			 		     document.getElementById("issue_priority_idError").innerHTML="";
    			 			 error.appendTo('#issue_priority_idError');
    			 	    }else if (element.attr("id") == "issue_description" ){
    			 		     document.getElementById("issue_descriptionError").innerHTML="";
    			 			 error.appendTo('#issue_descriptionError');
    			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }
    			 },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
                     }
                 },submitHandler: function(form) {
    			    // do other things for a valid form
    			    //form.submit();
    			    //return true;
    			  }
    	});
    	
    	
    	    $.validator.addMethod('progressLessThanEquals', function(value, element, param) {
    	        if (this.optional(element)) return true;
    	        var i = parseFloat(value);
    	        var j = parseFloat($(param).val());
    	        return i <= j;
    	    }, "Progress completed on this date should be less than or equal to Progress remaining");
    	    

    	    $.validator.addMethod("dateFormat",
        	    function(value, element) {
        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
        	    	//return dtRegex.test(value);
        	    },
        	    //"Date format (Aug 02,2020)"
        	    "Date format (DD-MM-YYYY)"
        	);
            
            
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