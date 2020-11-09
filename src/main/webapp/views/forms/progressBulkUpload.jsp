<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Progress Bulk Update</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
     <style>
        /* body {
            overflow-x: hidden;
        } */

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
        }

        .dot.active {
            box-shadow: 0px 0px 14px 6px #444, 0px 0px 25px 1px #777;
            border: 1px solid black !important;
        }

        .dot.active .project {
            font-weight: bold;
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
            background-color: #FD7E29;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
        }

        .page-loader {
            background: #332e2ec2 !important;
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .preloader-wrapper {
            top: 45% !important;
            left: 47% !important;
        }

        .error-msg label {
            color: red !important;
        }

        .input-field .searchable_label {
            font-size: .85rem;
        }
        .fixed-width {
            width: 100%;
            overflow: auto;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before{
            border: 2px solid #fff;
        }
        thead th input[type="checkbox"]:checked+span:not(.lever):before{
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
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
                                <h6>Progress Bulk Update Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                            <form id="stripChartForm" name="stripChartForm" method="post" enctype="multipart/form-data">
                    <div class="container container-no-margin">
                        <div class="row">                          
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Project</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                        <div class="col m8 s12 input-field">
                                            <p class="searchable_label">Work</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                        <div class="col m12 s12 input-field">
                                            <p class="searchable_label">Contract</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>

                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Structure</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Line</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Section</p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Option 1</option>
                                                <option value="2">Option 2</option>
                                                <option value="3">Option 3</option>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <div class="row" style="margin-bottom: 20px;"
                                        id="component_circles_row">
                                        <div class="col m12 s12" id="dotgroup1">
                                            <div class="dotgroup-scroll">
                                                <div id="component_circles" style="padding: 10px;">
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
                                                        <a href="#" class="dot not-started active"><span
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
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div> 

                                    <div class="row">                                     
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Component ID</p>
                                            <select class="searchable validate-dropdown" id="strip_chart_component_id"
                                                name="strip_chart_component_id">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_component_idError" class="error-msg"></span>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Component</p>
                                            <select class="searchable validate-dropdown" id="strip_chart_component"
                                                name="strip_chart_component">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_componentError" class="error-msg"></span>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Activity</p>
                                            <select id="strip_chart_activity_id" name="strip_chart_activity_id"
                                                class="searchable validate-dropdown"
                                                onchange="getStripChartDetails(this.value);">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_activity_idError" class="error-msg"></span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m2 hide-on-small-only"></div>
                                        <div class="col m8 s12 center-align" style="margin-bottom: 30px;margin-top: 10px;">
                                            <a class="btn waves-effect bg-m" onclick="updateActual()">Finish Activities</a>
                                        </div>
                                        <div class="col m2 hide-on-small-only"></div>
                                    </div>
</div>
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="select-all" id="select-all"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </th>
                                                        <th>Component <br>ID</th>
                                                        <th>Component</th>
                                                        <th>Activity</th>
                                                        <th>Planned <br> Start</th>
                                                        <th>Planned <br> Finish</th>
                                                        <!-- <th>A S</th>
                                                        <th>A F</th> -->
                                                        <th>Scope</th>
                                                        <th>Completed</th>
                                                        <th>Actual</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="activity_check" id="check_1"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </td>
                                                        <td>1</td>
                                                        <td>2</td>
                                                        <td>3</td>
                                                        <td>3</td>
                                                        <!-- <td>4</td>
                                                        <td>5</td> -->
                                                        <td>6</td>
                                                        <td><span id="scope1">10</span></td>
                                                        <td><span id="completed1">7</span></td>                                                       
                                                        <td class="input-field">
                                                            <input type="text" id="actual1" readonly>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="activity_check" id="check_2"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </td>
                                                        <td>1</td>
                                                        <td>2</td>
                                                        <td>3</td>
                                                        <td>3</td>
                                                        <!-- <td>4</td>
                                                        <td>5</td> -->
                                                        <td>6</td>
                                                        <td><span id="scope2">70</span></td>
                                                        <td><span id="completed2">27</span></td>                                                       
                                                        <td class="input-field">
                                                            <input type="text" id="actual2" readonly>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="activity_check" id="check_3"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </td>
                                                        <td>1</td>
                                                        <td>2</td>
                                                        <td>3</td>
                                                        <td>3</td>
                                                        <!-- <td>4</td>
                                                        <td>5</td> -->
                                                        <td>6</td>
                                                        <td><span id="scope3">12</span></td>
                                                        <td><span id="completed3">3</span></td>                                                       
                                                        <td class="input-field">
                                                            <input type="text" id="actual3" readonly>
                                                        </td>
                                                    </tr>
                                                  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
								<div class="container container-no-margin" >
                                    <div class="row">
                                        <div class="col m12 s12 input-field">
                                            <textarea id="remarks" name="remarks" class="materialize-textarea"
                                                data-length="500"></textarea>
                                            <label for="remarks" class="">Remarks</label>
                                        </div>
                                    </div>
                                    <input type="hidden" id="strip_chart_id" name="strip_chart_id" />
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="button" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;" >Update</button>
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
                        </div>
                            </form>
                    </div>
                    <!-- form ends  -->
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
    
      <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>    
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });

            $('#progress_date').datepicker({
                maxDate: new Date(),
                format: 'dd-mm-yyyy',
                //perform click event on done button
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            });

            $('#remarks').characterCounter();

        });
        // update actual function for single value with out ids
       /*  function updateActual(){
            $('input[name="activity_check"]').each(function(){
                if($(this).prop('checked')){  
                    var scope_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().prev().children().text());
                    var completed_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().children().text());
                    var remaining=scope_val-completed_val;
                    var actual_val=$(this).parent().closest('tr').find('td:last-of-type').children().val(remaining);
                }
            })           
        } */

        // update actual function for single value with ids
        function updateActual(){
            $('input[name="activity_check"]').each(function(){
                if($(this).prop('checked')){    
                    let no=$(this).attr('id').split("_")[1];
                    let remaining=parseInt($('#scope'+no).text())- parseInt($('#completed'+no).text());
                    $('#actual'+no).val(remaining);
                }
            })           
        }
     
// select or deselect all checkboxes 
$('#select-all').change(function() {
    var _this = this;
    $('input[name="activity_check"]').each(function() { 
    if ($(_this).is(':checked')) {
        $(this).prop('checked', true);
    } else {
        $(this).prop('checked', false);
    }
    });
});

    </script>
</body>
</html>