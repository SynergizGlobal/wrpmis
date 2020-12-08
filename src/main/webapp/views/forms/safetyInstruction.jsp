<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Safety Instruction </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
 	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css"> 
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
   <!--  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	 -->	    
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

        .filevalue {
            display: block;
            margin-top: 10px;
        }

        .fw-45p {
            width: 45%;
            max-width: 45%;
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
                                <h6>Safety Instruction</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin" style="margin-top:20px;margin-bottom:20px">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="safety-instruction" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="fw-45p">Name</th>
                                                        <th class="fw-45p">Attachment</th>
                                                        <th class="no-sort">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input id="document_names0" names="document_name" type="text" class="validate"
                                                                placeholder="Name">
                                                        </td>
                                                        <td>
                                                            <div class="">
                                                                <input type="file" name="myfile" id="myFile0"
                                                                    onchange="getFileName(0)" style="display:none" />
                                                                <label for="myFile0" class="btn bg-m"><i
                                                                        class="fa fa-paperclip"></i></label>
                                                                <span id="fileVal0" class="filevalue">fileName</span>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td>
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
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;"
                                            class="btn waves-effect waves-light bg-m black-text">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s black-text"
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

 <!-- Page Loader -->
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


    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
   <!--  <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> -->
    <!-- <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> -->

	    <script>
        $(document).ready(function () {

        });
        rowNo = 1
        function addNewRow() {
            var text = '<tr>' + '<td><input id="name0" type="text" class="validate" placeholder = "Name" ></td > ' +
                '<td><div class=""><input type="file" name="myfile" id="myFile' + rowNo + '" onchange="getFileName(' + rowNo + ')" style="display:none" />' +
                ' <label for="myFile' + rowNo + '" class="btn bg-m"><i class="fa fa-paperclip"></i></label> <span id="fileVal' + rowNo + '" class="filevalue">fileName</span>' +
                '</div ></td ><td><a href="#" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>' + '</tr>';
            $('#safety-instruction').find('tr:last').prev().after(text);
            rowNo++;
        }

        function getFileName(rowNo) {
            var filename = $('#myFile' + rowNo)[0].files[0].name;
            $('#fileVal' + rowNo).html(filename);
        }
    </script>
</body>

</html>