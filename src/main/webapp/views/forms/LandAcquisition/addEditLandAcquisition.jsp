<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add / Edit Land Acquisition</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/la.css">
</head>

<body>
  <!-- header including -->
  <jsp:include page="../../layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
  <!-- card starting -->
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Land Acquisition</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="drawing_no" type="text" class="validate">
                                    <label for="drawing_no">Drawing Number</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="map_no" type="text" class="validate">
                                    <label for="map_no">Map Number</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="plot_no" type="text" class="validate">
                                    <label for="plot_no">Plot Number</label>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <input id="area" type="text" class="validate">
                                    <label for="area">Area</label>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Sqm</option>
                                        <option value="2">Sqft</option>
                                        <option value="3">Sqkm</option>
                                    </select>
                                    <label>Units</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 3 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" type="text" class="validate">
                                    <label for="location">Location Name</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="sub_location" type="text" class="validate">
                                    <label for="sub_location">Sub Location Name</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" type="text" class="validate">
                                    <label for="longitude">Longitude</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" type="text" class="validate">
                                    <label for="latitude">Latitude</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 5 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="chainage_from" type="text" class="validate">
                                    <label for="chainage_from">Chainage From</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="chainage_to" type="text" class="validate">
                                    <label for="chainage_to">Chainage To</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                    </select>
                                    <label>Category</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                    </select>
                                    <label>Sub Category</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Pending</option>
                                        <option value="2">Progressing</option>
                                        <option value="3">Not Started</option>
                                        <option value="4">Completed</option>
                                    </select>
                                    <label>Survey Status</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Pending</option>
                                        <option value="2">Progressing</option>
                                        <option value="3">Not Started</option>
                                        <option value="4">Completed</option>
                                    </select>
                                    <label>Valuation Status</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 8 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Pending</option>
                                        <option value="2">Progressing</option>
                                        <option value="3">Not Started</option>
                                        <option value="4">Completed</option>
                                    </select>
                                    <label>Approval Status</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Pending</option>
                                        <option value="2">Progressing</option>
                                        <option value="3">Not Started</option>
                                        <option value="4">Completed</option>
                                    </select>
                                    <label>Acquisition Status</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 9 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Pending</option>
                                        <option value="2">Progressing</option>
                                        <option value="3">Not Started</option>
                                        <option value="4">Completed</option>
                                    </select>
                                    <label>Payment Status</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="payment" type="text" class="validate">
                                    <label for="payment">Payment Amount</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Remarks</label>
                                </div>
                            </div>
<!-- update and back buttons starts -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="submit"
                                            class="btn waves-effect waves-light bg-m"> Create / Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button"class="btn waves-effect waves-light bg-s">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
<!-- update and back buttons ends -->
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>
            </div>
<!-- card ending -->            
        </div>
    </div>


  <!-- footer including -->
  <jsp:include page="../../layout/footer.jsp"></jsp:include>
  
  <script>
//material components initialization
    $(document).ready(function () {
    	$('select').formSelect();
    	$('#textarea1').characterCounter();
    });
  </script>
</body>

</html>