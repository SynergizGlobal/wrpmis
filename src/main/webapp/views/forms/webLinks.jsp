<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Links</title>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <style>
        .fixed-width {
            width: 100%;
            margin: 0 !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        .mdl-data-table {
            border: 1px solid #ddd;
        }

        .fw-40p {
            width: 40%;
            max-width: 40%;
        }

        .fw-15p {
            width: 15%;
            max-width: 15%;
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
                                <h6>Web Links</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin" style="margin-top:20px;margin-bottom:20px">
                            <div class="row">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="web-links-table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="fw-40p">Name</th>
                                                        <th class="fw-40p">Link</th>
                                                        <th class="fw-15p">Priority</th>
                                                        <th class="no-sort">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td colspan="4" class="center-align">
                                                            <a href="#" class="btn waves-effect waves-light bg-m t-c "
                                                                onclick="addNewRow()">
                                                                <i class="fa fa-plus"></i></a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col m1 hide-on-small-only"></div>
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;"
                                            class="btn waves-effect waves-light bg-m">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s "
                                            style="width:100%">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

    <!-- footer  -->
<jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>

    <script>
        rowNo = 0;
        // adding table data into table start
        var newArr = [
            ["Railway Board", "https://www.indianrailways.gov.in/railwayboard/", "1"],
            ["RDSO", "https://rdso.indianrailways.gov.in/", "2"],
            ["CR", "https://cr.indianrailways.gov.in/", "3"],
            ["WR", "https://wr.indianrailways.gov.in/", "4"],
            ["IRICEN", "http://www.iricen.gov.in/iricen/Home", "5"],
            ["RDSO's vendor directory", "https://rdso.indianrailways.gov.in/view_section.jsp?lang=0&id=0", "6"],
            ["RDSO's Bridges & Structures directorate", "http://10.100.2.4/drawing/frmLink.aspx  ", "7"],
            ["Raliway's Shramik Kalyan Portal", "http://www.shramikkalyan.indianrailways.gov.in/", "8"],
            ["MRVC ", "http://www.mrvc.indianrailways.gov.in/", "9"],
            ["Ministry of labour & employment", "https://labour.gov.in/", "10"],
            ["Shram suvidha unified portal for labour law compliance", "https://shramsuvidha.gov.in/home", "11"],
        ]
        function makeTableHTML(myArray) {
            var result = "";
            for (var i = 0; i < myArray.length; i++) {
                result += '<tr><td>  <input id="name' + rowNo + '" type="text" class="validate" placeholder = "Name" name="names" value="' + myArray[i][0] + '"/></td>' +
                    '<td><textarea id="link' + rowNo + '" class="materialize-textarea validate" placeholder="Link" name="links" >' + myArray[i][1] + '</textarea>  </td>' +
                    '<td><input id="priority' + rowNo + '" type="number" class="validate" placeholder="Priority" name="priorities" value="' + myArray[i][2] + '"/> </td>' +
                    '<td> <a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td></tr>';
                rowNo++;
            }
            return result;
        }
        $('#web-links-table tbody tr:last').before(makeTableHTML(newArr));
        // adding table data into table ends


        function addNewRow() {
            var text = '<tr>' + '<td><input id="name' + rowNo + '" type="text" class="validate" placeholder = "Name" name="names" /></td > ' +
                '<td><textarea id="link' + rowNo + '" class="materialize-textarea validate" placeholder="Link" name="links" ></textarea></td>' +
                '<td><input id="priority' + rowNo + '" type="number" class="validate" placeholder="Priority" name="priorities" /> </td>' +
                '<td><a href="#" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>' + '</tr>';
            $('#web-links-table tbody').find('tr:last').prev().after(text);
            rowNo++;
        }


    </script>

</body>

</html>