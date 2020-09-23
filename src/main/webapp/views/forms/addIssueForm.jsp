<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Issues</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/issues.css">
	
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

 <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6> Add / Edit Issues</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="#">
                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label> Project ID </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label> Work ID </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">issue 1</option>
                                        <option value="2">issue 2</option>
                                        <option value="3">issue 3</option>
                                    </select>
                                    <label>Contract ID</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">issue 1</option>
                                        <option value="2">issue 2</option>
                                        <option value="3">issue 3</option>
                                    </select>
                                    <label>Activity ID</label>
                                </div>
                            </div>

                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>


                                <div class="col s12 m4 input-field">
                                    <!-- <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea> -->
                                    <label for="">Issue ID :</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Priority 1</option>
                                        <option value="2">Priority 2</option>
                                        <option value="3">Priority 3</option>
                                    </select>
                                    <label>Department </label>

                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Priority 1</option>
                                        <option value="2">Priority 2</option>
                                        <option value="3">Priority 3</option>
                                    </select>
                                    <label>Issue Category </label>

                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                    </select>
                                    <label>Issue Priority </label>

                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                    </select>
                                    <label>Issue Status </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <!-- <select> -->
                                    <input id="title" type="text" class="validate">
                                    <label for="title">Title</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="description" type="text" class="validate">
                                    <label for="description">Description </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row ">
                                <!-- row 3 -->
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <input id="date" type="text" class="validate datepicker">
                                    <label for="date"> Date</label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" type="text" class="validate">
                                    <label for="location">Location </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" type="text" class="validate">
                                    <label for="latitude">Latitude
                                    </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" type="text" class="validate">
                                    <label for="longitude">Longitude </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <!-- <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Priority 1</option>
                                        <option value="2">Priority 2</option>
                                        <option value="3">Priority 3</option>
                                    </select>
                                    <label>Reported By                                    </label> -->
                                    <input id="reported_by" type="text" class="validate">
                                    <label for="reported_by">Reported By </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <!-- <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                    </select>
                                    <label>Responsible Person                                    </label> -->
                                    <input id="responsible_person" type="text" class="validate">
                                    <label for="responsible_person">Responsible Person </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="zonal_rly" type="text" class="validate">
                                    <label for="zonal_rly"> Zonal Rly</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Corrective Measure</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="resolved_date" type="text" class="validate datepicker">
                                    <label for="resolved_date"> Resolved Date</label>
                                    <button type="button" id="resolved_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="escalated_to" type="text" class="validate">
                                    <label for="escalated_to">Escalated To </label>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea2" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea2">Remarks</label>
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

                            <div class="row no-mar">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="submit" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">Add / Edit
                                        </button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button" class="btn waves-effect waves-light bg-s"
                                            style="width: 100%;">Cancel
                                        </button>
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

   
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.sidenav').sidenav();
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();

            $("#date,#resolved_date").datepicker();
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            $('#resolved_date_icon').click(function () {
                event.stopPropagation();
                $('#resolved_date').click();
            });
        });
    </script>
</body>
</html>