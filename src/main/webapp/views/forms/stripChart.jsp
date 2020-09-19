<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Strip Chart</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">


    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">

    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">

    <style>
        body {
            overflow-x: hidden;
        }

        .hiddendiv.common {
            width: 99vw !important;
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

        #dotgroup1 .dot-line {
            width: 30px;
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
            box-shadow: 0px 0px 10px 0.5px #f18905;
        }

        .dot.no-focus {
            opacity: 0.85;
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
            background-color: #f60;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
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
                                <h6>Strip Chart Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <div class="row">
                            <form action="<%=request.getContextPath()%>/update-stripchart" id="stripChartForm"
                                name="stripChartForm" enctype="multipart/form-data">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p>Project</p>
                                            <select class="searchable" id="project_id" name="project_id"
                                                onchange="getWorksList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${projectsList }">
                                                    <option value="${obj.project_id }" <c:if
                                                        test="${obj.project_id eq sessionScope.globalProjectId}">
                                                        selected</c:if>>${obj.project_id}<c:if
                                                            test="${not empty obj.project_name}"> - </c:if>
                                                        ${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m8 s12 input-field">
                                            <p>Work</p>
                                            <select class="searchable" id="work_id_fk" name="work_id_fk"
                                                onchange="getContractsList(this.value);">
                                                <option value="" selected>Select</option>
                                            </select>
                                        </div>
                                        <div class="col m12 s12 input-field">
                                            <p>Contract</p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable"
                                                onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                                <%-- <c:forEach var="obj" items="${contractsList }">
                                                	<option value="${obj.contract_id }" <c:if test="${obj.contract_id eq ''}">selected</c:if>>${obj.contract_name}</option>
                                                </c:forEach> --%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <!-- row 1  -->
                                        <div class="col m4 s12 input-field">
                                            <p>Structure</p>
                                            <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk"
                                                class="searchable" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${structuresList }">
                                                    <option value="${obj.strip_chart_structure }">
                                                        ${obj.strip_chart_structure}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Line</p>
                                            <select id="strip_chart_line_id_fk" name="strip_chart_line_id_fk"
                                                class="searchable" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${linesList }">
                                                    <option value="${obj.strip_chart_line }">${obj.strip_chart_line}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Section</p>
                                            <select id="strip_chart_section_id_fk" name="strip_chart_section_id_fk"
                                                class="searchable" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${sectionsList }">
                                                    <option value="${obj.strip_chart_section_id }">
                                                        ${obj.strip_chart_section_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row" style="margin-bottom: 20px;">
                                        <div class="col m12 s12" id="dotgroup1">
                                            <div class="dotgroup-scroll">
                                                <div id="dotgroup1_test">
                                                    <!-- <div class="horizontal-line"> </div> -->
                                                    <div class="dot-container">
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
                                                        <a href="#" class="dot completed no-focus"><span
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
                                                        <a href="#" class="dot completed no-focus"><span
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
                                                        <a href="#" class="dot completed no-focus"><span
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
                                                        <a href="#" class="dot completed no-focus"><span
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

                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p>Component</p>
                                            <select class="searchable" id="strip_chart_component_fk"
                                                name="strip_chart_component_fk">
                                                <option value="1">Option 1</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Component ID</p>
                                            <select class="searchable" id="strip_chart_component_id_fk"
                                                name="strip_chart_component_id_fk">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Activity</p>
                                            <select id="strip_chart_activity_id_fk" name="strip_chart_activity_id_fk"
                                                class="searchable" onchange="getStripChartDetails(this.value);">
                                                <option value="">Select</option>
                                                <%-- <c:forEach var="obj" items="${activitiesList }">
                                                	<option value="${obj.strip_chart_activity_id }" <c:if test="${obj.strip_chart_activity_id eq ''}">selected</c:if>>${obj.strip_chart_activity_name}</option>
                                                </c:forEach> --%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row" style="margin-bottom:30px;margin-top:20px">
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Planned Start <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m3 s6">
                                            <div>18/09/2020 </div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Planned Finish <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div>18/09/2021 </div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-bottom:30px">
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Scope <span class="right">:</span></div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div> scope</div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div class="primary-text">Completed <span class="right">:</span> </div>
                                        </div>
                                        <div class="col m3 s6 ">
                                            <div>19/09/2020</div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m6 s12 input-field">
                                            <input id="progress_date" type="text" class="validate datepicker">
                                            <label for="progress_date">Progress Date</label>
                                            <button type="button" id="progress_date_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <input id="progress" type="text" class="validate">
                                            <label for="progress">Progress for the Day</label>
                                            <span class="units">units</span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m6 s12">
                                            <div class="file-field input-field">
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col m6 s12">
                                            <div class="row">
                                                <!-- row 7 -->
                                                <div class="col s6 m6 input-field center-align">
                                                    <div style="margin-top: 8px;">Any Issue ?</div>

                                                </div>
                                                <div class="col s6 m6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="yes" />
                                                            <span>Yes</span>
                                                        </label>
                                                        <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="no" />
                                                            <span>No</span>
                                                        </label>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="issue_yes" style="display: none;">
                                        <div class="row">
                                            <h6 class="center-align" style="color:#2E58AD;font-weight:600">Issue Details
                                            </h6>
                                            <div class="col s12 m6 input-field" style="margin-top: 40px;">
                                                <select class="select">
                                                    <option value="0" selected>Select</option>
                                                    <option value="1">Category 1</option>
                                                    <option value="2">Category 2</option>
                                                    <option value="3">Category 3</option>
                                                </select>
                                                <label>Issue Category</label>
                                            </div>
                                            <div class="col s12 m6 input-field" style="padding-top: 14px;">

                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="high" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col s12 m12 input-field">
                                                <textarea id="issueDesc" class="materialize-textarea"
                                                    data-length="500"></textarea>
                                                <label for="issueDesc">Issue Description</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m12 s12 input-field">
                                            <textarea id="remarks" name="remarks" class="materialize-textarea"
                                                data-length="500"></textarea>
                                            <label for="remarks" class="">Remarks</label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="submit" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;">Update</button>
                                            </div>
                                        </div>
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="reset" class="btn waves-effect waves-light bg-s"
                                                    style="width: 100%;">Reset</button>
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



    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.select').formSelect();
            $('.searchable').select2();
            $("#planned_finish,#planned_start,#progress_date").datepicker();
            $('#planned_finish_icon').click(function () {
                event.stopPropagation();
                $('#planned_finish').click();
            });
            $('#planned_start_icon').click(function () {
                event.stopPropagation();
                $('#planned_start').click();
            });
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });
            $('#remarks').characterCounter();
            $('.sidenav').sidenav();
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter();
            $('input[name=issue]').change(function () {
                var radioval = $('input[name=issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
            // horizontal line width set dynammically
            $('#dotgroup1_test >.horizontal-line').offsetwidth = $('#dotgroup1_test').scrollWidth + 'px';

        });
    </script>

    <!-- <script>
        // code is not required, but to add many buttons I added this 
        var dotgroup = document.getElementById('dotgroup');

        for (let i = 0; i <= 27; i++) {
            let ele = document.createElement('a');
            ele.classList.add('dot');
            let it = document.createElement('span');
            it.classList.add('project');
            ele.appendChild(it);
            dotgroup.appendChild(ele);
        }
  </script> -->

    <script>
        $(document).ready(function () {
            // $('select').formSelect();
            $('.searchable').select2();
            // hide or display selects by clicking 
            $(".arr").click(function () {
                $('#toggle-selects').toggleClass("open");
                $('#hide-btn').toggleClass("fa-plus-circle");
                $('#hide-btn').toggleClass("fa-minus-circle");

            });
            // $(".datepicker").datepicker();
            $("#planned_finish,#planned_start,#progress_date").datepicker();
            $('#planned_finish_icon').click(function () {
                event.stopPropagation();
                $('#planned_finish').click();
            });
            $('#planned_start_icon').click(function () {
                event.stopPropagation();
                $('#planned_start').click();
            });
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });
            $('#remarks').characterCounter();
            $('.sidenav').sidenav();
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter();

            var globalProjectId = "${sessionScope.globalProjectId}";
            if ($.trim(globalProjectId) != '') {
                getWorksList(globalProjectId);
            }
        });


        //geting works list from database    
        function getWorksList(projectId) {
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var globalWorkId = "${sessionScope.globalWorkId}";
                                if ($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                    }
                });
            }
        }

        //geting contracts list    
        function getContractsList(workId) {
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(workId) != "") {
                var myParams = { work_id_fk: workId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                    }
                });
            }
        }

        //geting contracts list    
        function getComponentIdsList() {
            var html = "";
            var contractId = $("#contract_id_fk").val();
            var structureId = $("#strip_chart_structure_id_fk").val();
            var laneId = $("#strip_chart_line_id_fk").val();
            var sectionId = $("#strip_chart_section_id_fk").val();

            if ($.trim(contractId) != "") {
                var myParams = { contract_id_fk: contractId, strip_chart_structure_id_fk: structureId, strip_chart_line_id_fk: laneId, strip_chart_section_id_fk: sectionId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getComponentIdsList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var componentId = "'" + val.strip_chart_component_id + "'";
                                var componentName = "'" + val.strip_chart_component_name + "'";
                                alert(componentId);
                                html = html + '<a href="javascript:void(0);" class="dot" onclick="getStripChartActivitiesList(' + componentId + componentName + ');"><span class="project" data-project="' + val.strip_chart_component_id_name + '"></span></a>';
                            });
                        }
                        $("#dotgroup").html(html);
                    }
                });
            }
        }

        function getStripChartActivitiesList(componentId, componentName) {
            alert(componentId + " , " + componentName)
            $("#strip_chart_component_fk").val(componentName);
            $("#strip_chart_activity_id_fk option:not(:first)").remove();
            if ($.trim(componentId) != "") {
                var myParams = { strip_chart_component_id: componentId, };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStripChartActivitiesList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                $("#strip_chart_activity_id_fk").append('<option value="' + val.strip_chart_activity_id + '">' + $.trim(val.strip_chart_activity_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                    }
                });
            }
        }

        function getStripChartDetails(activitiId) {
            var componentId = $("#strip_chart_component_id_fk").val();
            if ($.trim(strip_chart_activity_id_fk) != "") {
                var myParams = { strip_chart_component_id_fk: componentId, strip_chart_activity_id_fk: activitiId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStripChartDetails",
                    data: myParams, cache: false,
                    success: function (data) {

                    }
                });
            }
        }

        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });

    </script>
</body>

</html>