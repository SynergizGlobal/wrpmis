<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Work</title>
        <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/work.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <!-- <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> -->
    <style>
        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
    </style>
</head>

<body>

    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Work</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                    	
			              
			               
                      
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select multiple >
                                    <c:if test="${action eq 'add'}">	
                                           <option value="${workDeatils.project_id_fk} " selected>select</option>
                                           </c:if>
                                                <c:forEach var="obj" items="${projectsList }">
                                             
                                                    <option value="${obj.project_id } "  
                                                    <c:if test="${workDeatils.project_id_fk eq obj.project_id }">selected</c:if> >
                                                       ${obj.project_id}</option>
                                                </c:forEach>
                                    </select>
                                    <label>Project ID</label>
                                </div>
                                
                                <div class="col s12 m4 input-field">
                                 <div class="form-line upload-form">
                                <c:if test="${action eq 'edit'}">				                
			                	<form action="updateWork-form" id="workForm" name="workForm" method="post" class="form-horizontal" role="form">
                                     <input id="work_id" type="text" class="form-control" name="work_id" value="${workDeatils.work_id }" readonly >   </c:if>
                                      <c:if test="${action eq 'add'}">				                
			                	<form action="addWork-form" id="workForm" name="workForm" method="post" class="form-horizontal" role="form">
							 <input id="work_id" type="text" class="validate" name="work_id" value="${workDeatils.work_id }"  >
							</c:if>
                                    
                                </div><label>Work ID :</label></div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="work_name" type="text" class="validate" name="work_name" value="${workDeatils.work_name }">
                                    <label for="work_name">Work Name</label>
                                </div>


                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                        <!--     <div class="row">
                               
                              <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <input id="sactioned_year" type="text" class="validate datepicker" name="sanctioned_year" value="${workDeatils.sanctioned_year }">
                                            <label for="sactioned_year">Sanctioned Year</label>
                                            <button type="button" id="sactioned_year_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="sactioned_estimation" type="number" class="validate" name="sanctioned_estimated_cost" value="${workDeatils.sanctioned_estimated_cost }">
                                            <label for="sactioned_estimation">Sanctioned Estimated Cost</label>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="completion_period" type="text" class="validate" name="completeion_period_months" value="${workDeatils.completeion_period_months }">
                                            <label for="completion_period">Completion Period </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="saction_completed_cost" type="number" class="validate" name="sanctioned_completion_cost" value="${workDeatils.sanctioned_completion_cost }" >
                                    <label for="saction_completed_cost"> Sanctioned Completion Cost </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="anticipated_cost" type="number" class="validate" name="anticipated_cost" value="${workDeatils.anticipated_cost }">
                                    <label for="anticipated_cost">Anticipated cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="year_completed" type="text" class="validate datepicker" name="year_of_completion" value="${workDeatils.year_of_completion }">
                                    <label for="year_completed">Year of Completion </label>
                                    <button type="button" id="year_completed_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completed_cost" type="number" class="validate" name="completion_cost" value="${workDeatils.completion_cost }">
                                    <label for="completed_cost">Completion cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>-->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                  <select multiple >
                                    <c:if test="${action eq 'add'}">	
                                           <option value="${workDeatils.ailway_id_fk} " selected>select</option>
                                           </c:if>
                                                <c:forEach var="obj" items="${railwaysList }">
                                             
                                                    <option value="${obj.railway_id } "  
                                                    <c:if test="${workDeatils.railway_id_fk eq obj.railway_id }">selected</c:if> >
                                                       ${obj.railway_name}</option>
                                                </c:forEach>
                                    </select>
                                    <label>Railway Agency</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                 <select multiple >
                                    <c:if test="${action eq 'add'}">	
                                           <option value="${workDeatils.ailway_id_fk} " selected>select</option>
                                           </c:if>
                                                <c:forEach var="obj" items="${railwaysList }">
                                             
                                                    <option value="${obj.railway_id } "  
                                                    <c:if test="${workDeatils.railway_id_fk eq obj.railway_id }">selected</c:if> >
                                                       ${obj.railway_name}</option>
                                                </c:forEach>
                                    </select>
                                    <label>Executed By </label>
                                </div>
                            </div> -->
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="example3" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Financial Year </th>
                                                <th>PB Item No </th>
                                                <th>Latest Revised Cost </th>
                                                <th>Year of Revision </th>
                                                <th>Revision No </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td> <select>
                                                        <option value="0" selected>Financial Year</option>
                                                        <option value="1">Agency 1</option>
                                                        <option value="2">Agency 2</option>
                                                        <option value="3">Agency 3</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="completed_cost0" type="text" class="validate"
                                                        placeholder="PB Item Number">
                                                </td>
                                                <td>
                                                    <input id="completed_cost1" type="number" class="validate"
                                                        placeholder="Latest Revised Cost">
                                                </td>
                                                <td>
                                                    <select>
                                                        <option value="0" selected>Year of Revision</option>
                                                        <option value="1">Agency 1</option>
                                                        <option value="2">Agency 2</option>
                                                        <option value="3">Agency 3</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="completed_cost2" type="text" class="validate"
                                                        placeholder="Revision Number">
                                                </td>
                                                <td>
                                                    <input id="completed_cost3" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-s t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col m8 s12">
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
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Remarks</label>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="submit" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>


    <script src="/pmis/resources/js/jQuery.min.js"></script>
    <script src="/pmis/resources/js/materialize.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.sidenav').sidenav();
            $('.modal').modal();
            $('#textarea1,#textarea2,#textarea3').characterCounter();
            // $(".datepicker").datepicker();
            $("#year_completed").datepicker({
                format: 'yyyy',
                selectYears: true,
            });
            $("#financial_year").datepicker({
                format: 'yyyy',
                selectYears: true,
            });
            $("#year_revision").datepicker({
                format: 'yyyy',
                selectYears: true,
            });
            $('#sactioned_year').datepicker({
                format: 'yyyy',
                selectYears: true,
                selectMonths: true,
                selectDays: false,
                onSelect: function (arg) {
                    var selectedYear = parseInt(arg.getFullYear());
                    var selectedMonth = parseInt(arg.getMonth() + 1);
                    var year = (selectedMonth >= 4) ? selectedYear + '-' + (selectedYear + 1) : (selectedYear - 1) + '-' + selectedYear;
                    console.log('sactioned_year : ' + year);

                }
            });
            $('#sactioned_year_icon').click(function () {
                event.stopPropagation();
                $('#sactioned_year').click();
            });
            $('#year_completed_icon').click(function () {
                event.stopPropagation();
                $('#year_completed').click();
            });
            $('#financial_year_icon').click(function () {
                event.stopPropagation();
                $('#financial_year').click();
            });
            $('#year_revision_icon').click(function () {
                event.stopPropagation();
                $('#year_revision').click();
            });
            $('#example,#example1').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    }
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
    </script>
</body>

</html>