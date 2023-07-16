<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Training - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Training - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    
    
     <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
   
    <style>
        #session-table .datepicker~button {
            top: 26px;
        }
         #session-table {
            padding-left: 0px;;
        }

        #session-table input[type="text"]::-webkit-input-placeholder,
        #session-table input[type="text"]:-ms-input-placeholder,
        #session-table input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }
  
        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
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
        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
           
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        /* font size increase  */
        .input-field .searchable_label {
            font-size: 0.9rem;
        }
		input[type="text"].timepicker{
			font-size:1rem;
		}		
        .timepicker~button {
            position: absolute;
            /* right: 15px; */
            right: 0;
            /* top: 32px; */
            top: 12px;
            border: 0;
            opacity: 0.7;
            cursor: pointer;
            background-color: transparent;
        }
        
        .attendees-column{     
        	padding: 0.43rem .65rem !important;
        }
        .attendees-column >.btn{
        	padding:0 10px;
        }

        .timepicker~button .fa {
            font-size: 1.3rem;
            color: #333;
        }

        /* modal header styling  */
        .modal-header {
            text-align: center;
            background-color: #007a7a;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .table-inside tbody td p {
            margin-bottom: 0;
            margin-top: 10px;
        }

        input[type="checkbox"]+label:before {
            border: 2px solid indigo;
            background: transparent;
        }

        input[type="checkbox"]:checked+label:before {
            border: 2px solid transparent;
            border-bottom: 2px solid indigo;
            border-right: 2px solid indigo;
            background: transparent;
        }

        .materialize-textarea::placeholder {
            color: #777;
        }
        .modal .select2-container {
        	width:110px !important;
    		max-width: 160px;
    		min-width:160px; 
        }
        .modal .attendee-dropdown >.select2-container{
        	min-width: 250px;
   			max-width: 300px;
        }
        .select2-container--default .select2-selection--single{
        	background-color:transparent;
        }
        .modal{
        	max-height:90%;
        	width:68%;
        }
        .modal-content .row.fixed-width,.row.fixed-width{
        	margin:0;
        }
        
        .select2-container.select2-container--default.select2-container--open{
        	z-index:1034;
        }
        .row.no-mar{
        	margin-bottom:0;
        }
     
        .fw-110{
            max-width: 110px;
		    width: 110px;
		    min-width: 110px;
    	}
    	.fw-400{
        	width: 400px;
    		max-width: 400px;
    	}
     	.filevalue {
            display: block;
            margin-top: 5px;
            /* max-width:250px; */
        }
        .error-msg label{color:red!important;}   
       
        .input-field>textarea+label:not(.label-icon).active{
        	margin-top:0;
        }
        .input-field>label:not(.label-icon).active{
        	margin-top:5px;
        }
        .modal-content .mdl-data-table thead tr:hover{
        	background-color:#007a7a;
        }
        .mt-brdr{
			margin-top: 20px;
		    border-top: 1px solid #777;
		    border-bottom: 1px solid #777;
		}
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
		
		.pos-rel{
			position:relative;
		}
		.disp-init{
			display:initial;
		}
		.fw-220{
			width:220px ;
			min-width:220px;
		}
		@media only screen and (min-width: 993px){	
			div[id^=session-update-modal].modal.open{
				width:90%;
			}			
		}		
		.date-holder{
		    color: white;
		    margin-top: 2rem;
		    font-size: 2rem;
		}
		.date-holder >.date-text{
		    font-size: 2.5rem;
		    margin-top: 2rem;
		}
		@media only screen and (max-width: 768px){
			.date-holder{
			    margin-top: 1rem;
			    font-size: 1rem;
			    margin-bottom:2rem;
			    text-align:center;
			}
			.date-holder >.date-text{
			    font-size: 1.5rem;
			    margin-top: 0;		
			    margin-left:1rem;        
			}
			.date-holder >.date-text,
			.date-holder >.year-text{
			    display:inline-block; 
			}
			
			.modal.datepicker-modal > .modal-content,
			.modal.timepicker-modal > .modal-content{
			    padding:0 !important;  
			}
			.modal:not(.datepicker-modal){
	        	max-height:80%;
	        	width:85%;
	        }
	        .modal.datepicker-modal {
	        	width:76%;
	        	max-width:78%;
	        }
	        .pos-rel{
	        	width:100%;	        
	        }
	        .modal .select2-container{
	        	max-width:inherit;
	        }	        
	        .modal:not(.datepicker-modal).modal-header{
	        	margin: -24px -12px 20px !important;
    			font-size: 1.4rem;
	        }
	        .modal:not(.datepicker-modal) .modal-content{
	        	padding:24px 12px;
	        }
	        .modal .attendee-dropdown >.select2-container{
	        	min-width: auto;
	   			max-width: inherit;
	        }
	        .mobile_responsible_table .input-field input.timepicker{
	        	width:100% ;
	        }
	        .timepicker~button{
	        	right:-10px;
	        }
			td.cell-disp-inb .file-path-wrapper {
			    visibility: hidden;
			    width: 2px;
			    margin-bottom: 0;
			}
			.pos-rel{
				white-space:inherit !important;
			}
		}
			@media only screen and (max-width: 769px) and (min-width: 500px){ 
			input.timepicker~button{
	        	right:25px;
	        }	
	        .mobile_responsible_table .input-field input.timepicker{
	        	width:90% ;
	        }	 
		}
		.mw-150{
			width: 150px !important;
		    max-width: 150px !important;
		}
		@media only screen and (min-width:769px){
			.py-0{
				padding:auto 0;
			}
		}
		#msg-text{
			text-align: left;
			padding: 4px;
		}
		@media only screen and (max-width:450px){
			.timepicker-digital-display{
				min-height:150px;
			}
			.date-holder{
			    font-size: 2rem;
			    margin-bottom:3rem;
			}
			.date-holder >.date-text{
			    font-size: 2.5rem;     
			}
		}
		
		div[class^=autocomplete]{
			position: absolute;
		    z-index: 2;
		    background-color: #fff;
		    width: min(200%,180px);
		    border:1px solid #eee;
		    padding:.1rem;
		    overflow:auto;
		    max-height: 200px;
		    box-shadow:-2px -2px 5px #efefef;
		}
		.autoList{
			padding:.1rem .3rem;
			cursor:pointer;
			border-bottom:1px solid #ececec;
		}
		.autoList:hover{
			background-color:rgba(0,0,0,.05);
		}
    </style>
</head>

<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<!-- card  -->
	
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m m-b-2">
								<h6> Training Report </h6>
							</div>
						</span>
					</div>
					<!-- form start-->
						<!-- form start-->
						
						
				<form action="<%=request.getContextPath() %>/generate-training-report" id="trainingForm" name="trainingForm" method="get" class="form-horizontal" role="form">
						
				<div class="row">
    				<div class="col m12 s12">        
						<div class="row fixed-width">
							<div class="col s12 m12 l12 table-inside" style=" width: 100%; ">
								<table id="session-table" class="mdl-data-table mobile_responsible_table"style="text-align:center;">
									<thead>
										<tr>
											
											<th>Work <span class="required">*</span></th> 
											<th>Start Date<span class="required">*</span></th>
											<th>End Date<span class="required">*</span></th>
											
										</tr>
									</thead>
									<tbody >
										<c:choose>
											<c:when
												test="${not empty trainingDetails.trainingSessions && fn:length(trainingDetails.trainingSessions) gt 0 }">
												 
											</c:when>
											<c:otherwise>
											<tr id="trainingRow0">
											
											
											<td data-head="Work" class="input-field" >
											<select class="searchable validate-dropdown" id="work_short_name" name="work_short_name">
											<option value="" selected>Select</option>
											<c:forEach var="obj" items="${worklist}">
											<option value="${obj.work_short_name}" ${trainingDetails.work_short_name eq obj.work_short_name ? 'selected' : ''} name="work_short_name">${obj.work_short_name}</option>
											</c:forEach>
											</select>
											</td>
											
											
											
											<td data-head="Start Time" class="input-field">
							<input id="start_time${index.count }" name="start_time" type="text" class="validate datepicker" value="${iObj.created_date }" placeholder="Start Date">
							<button type="button" id="created_date${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
							
											
													
							<td data-head="End Time" class="input-field">
							<input id="end_time${index.count }" name="end_time" type="text" class="validate datepicker" value="${iObj.created_date }" placeholder="End Date">
							<button type="button" id="created_date${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>	
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
							</table>
								<div style="display: flex; justify-content: center;">
									<div class="m-1">
										<button type="button" onclick="executeActions();" class="btn waves-effect waves-light bg-m" style="min-width: 90px;">Generate Report</button>
									</div>
									<div class="m-1">
										<a href="<%=request.getContextPath()%>/home" class="btn waves-effect waves-light bg-s">Cancel</a>
									</div>
								</div>
						</div>
					 </div>
				</div>
					<br><br>
							
					</div>
				 </form>
					<!-- form ends  -->
			  </div>

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
	
	<script src="/pmis/resources/js/ReportDatePicker.js"></script>
	
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	
	<script>
	$(document).ready(function() {
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
    });
	 $("[data-length]").each(function(i,val){
     	$('#'+val.id).characterCounter();;
     });
	
	function autocomplete(inp, arr) {
		  /*the autocomplete function takes two arguments,
		  the text field element and an array of possible autocompleted values:*/
		  var currentFocus;
		  /*execute a function when someone writes in the text field:*/
		  inp.addEventListener("input", function(e) {
		      var a, b, i, val = this.value;
		      /*close any already open lists of autocompleted values*/
		      closeAllLists();
		      if (!val) { return false;}
		      currentFocus = -1;
		      /*create a DIV element that will contain the items (values):*/
		      a = document.createElement("DIV");
		      a.setAttribute("id", this.id + "autocomplete-list");
		      a.setAttribute("class", "autocomplete-items");
		      /*append the DIV element as a child of the autocomplete container:*/
		      this.parentNode.appendChild(a);
		      /*for each item in the array...*/
		      for (i = 0; i < arr.length; i++) {
		        /*check if the item starts with the same letters as the text field value:*/
		        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
		          /*create a DIV element for each matching element:*/
		          b = document.createElement("DIV");
		          b.classList.add('autoList');
		          /*make the matching letters bold:*/
		          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
		          b.innerHTML += arr[i].substr(val.length);
		          /*insert a input field that will hold the current array item's value:*/
		          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
		          /*execute a function when someone clicks on the item value (DIV element):*/
		          b.addEventListener("click", function(e) {
		              /*insert the value for the autocomplete text field:*/
		              inp.value = this.getElementsByTagName("input")[0].value;
		              /*close the list of autocompleted values,
		              (or any other open lists of autocompleted values:*/
		              closeAllLists();
		          });
		          a.appendChild(b);
		        }
		      }
		  });
		  /*execute a function presses a key on the keyboard:*/
		  inp.addEventListener("keydown", function(e) {
		      var x = document.getElementById(this.id + "autocomplete-list");
		      if (x) x = x.getElementsByTagName("div");
		      if (e.keyCode == 40) {
		        /*If the arrow DOWN key is pressed,
		        increase the currentFocus variable:*/
		        currentFocus++;
		        /*and and make the current item more visible:*/
		        addActive(x);
		      } else if (e.keyCode == 38) { //up
		        /*If the arrow UP key is pressed,
		        decrease the currentFocus variable:*/
		        currentFocus--;
		        /*and and make the current item more visible:*/
		        addActive(x);
		      } else if (e.keyCode == 13) {
		        /*If the ENTER key is pressed, prevent the form from being submitted,*/
		        e.preventDefault();
		        if (currentFocus > -1) {
		          /*and simulate a click on the "active" item:*/
		          if (x) x[currentFocus].click();
		        }
		      }
		  });
		  function addActive(x) {
		    /*a function to classify an item as "active":*/
		    if (!x) return false;
		    /*start by removing the "active" class on all items:*/
		    removeActive(x);
		    if (currentFocus >= x.length) currentFocus = 0;
		    if (currentFocus < 0) currentFocus = (x.length - 1);
		    /*add class "autocomplete-active":*/
		    x[currentFocus].classList.add("autocomplete-active");
		  }
		  function removeActive(x) {
		    /*a function to remove the "active" class from all autocomplete items:*/
		    for (var i = 0; i < x.length; i++) {
		      x[i].classList.remove("autocomplete-active");
		    }
		  }
		  function closeAllLists(elmnt) {
		    /*close all autocomplete lists in the document,
		    except the one passed as an argument:*/
		    var x = document.getElementsByClassName("autocomplete-items");
		    for (var i = 0; i < x.length; i++) {
		      if (elmnt != x[i] && elmnt != inp) {
		        x[i].parentNode.removeChild(x[i]);
		      }
		    }
		  }
		  /*execute a function when someone clicks in the document:*/
		  document.addEventListener("click", function (e) {
		      closeAllLists(e.target);
		  });
		}
  
		$(document).ready(function () {
		    $('select:not(.searchable)').formSelect();
		    $('.searchable').select2();
		    $('.modal').modal();
		    $('#remarks').characterCounter();
		    MaterialDateTimePicker.create($("#start_time"), { format: 'HH:mm' });
		    MaterialDateTimePicker.create($("#end_time"), { format: 'HH:mm' });

		    $('#start_time_icon0').click(function () {
		        MaterialDateTimePicker.create($("#start_time"), { format: 'HH:mm' });
		    });
		    $('#end_time_icon0').click(function () {
		        MaterialDateTimePicker.create($("#end_time"), { format: 'HH:mm' });
		    });

		    $('input[name=is_there_issue]').change(function () {
		        var radioval = $('input[name=is_there_issue]:checked').val();
		        if (radioval == 'yes') {
		            $('#issue_yes').css("display", "block");
		        }
		        else if (radioval == 'no') {
		            $('#issue_yes').css("display", "none");
		        }
		    });
		    callTimePicker(dateTimesInits);
		});

		function callTimePicker() {
		    for ( var i = 1; i <= dateTimesInits; i++ ) {
		        MaterialDateTimePicker.create($("#start_times"+i), { format: 'HH:mm' });
		        MaterialDateTimePicker.create($("#end_times"+i), { format: 'HH:mm' });
		        $('#start_time_icon'+i).click(function () {
		            MaterialDateTimePicker.create($("#start_times"+i), { format: 'HH:mm' });
		        });
		        $('#end_time_icon'+i).click(function () {
		            MaterialDateTimePicker.create($("#end_times"+i), { format: 'HH:mm' });
		        });
		    }
		}
 
        var selectedvalueArray=new Array();
       
   
   
   
     	function executeActions() {
    	    addTraining();
    	    setTimeout(generateReport, 3000); // Wait for 5000 milliseconds (5 seconds) before executing generateReport()
    	  }

     	  function addTraining(){
     	    	if(validator.form()){ // validation perform
     				$(".page-loader").show();
     				$('form select[name=work_short_name]').each(function () {if ($.trim(this.value) != '') {$(this).val(this.value.split(",").join("~$~"));}});
     				$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
     				$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
     				document.getElementById("trainingForm").submit();
     	    	}
     	 }
    	  function generateReport() {
    		  // Redirect to /training-report
      	    window.location.href = "/pmis/training-report";
    	  }
     
     var validator = $('#trainingForm').validate({
		 ignore: ":hidden:not(.validate-dropdown)",
  		    rules: {
  		 		  "training_type_fk": {
  			 		required: true
  			 	  },"training_category_fk": {
  			 		required: true
  			 	  },"period_fk": {
  		 		    required: true
  			 	  },"title": {
  		 		    required: true
  			 	  },"description": {
  		 		    required: true
  			 	  },"designation": {
  		 		    required: false
  			 	  },"faculty_name": {
  		 		    required: true
  			 	  },"mobile_nos": {
  			 		  required: false,
	  				  number: true,
	  				  minlength: 10,
	  				  maxlength: 10
  			 	  },"session_nos":{
  			 		required: true
  			 	  },"start_times":{
  			 		required: true
  			 	  },"end_times":{
  			 		required: true
  			 	  },"num_participants":{
  			 		required: true
  			 	  },"num_absentees":{
  			 		required: true
  			 	  },"remarkss":{
  			 		required: false
  			 	  },"session_nos":{
  			 		required: true
  			 	  }
  			 	  
  		 	},
  		    messages: {
  		 		 "training_type_fk": {
  				 	required: 'This field is required',
  			 	  },"training_category_fk": {
  			 		required: ' This field is required'
  			 	  },"status_fk": {
  		 			required: ' This field is required'
  		 	  	 },"title": {
  		 			required: ' This field is required'
  		 	  	 },"description": {
  		 			required: ' This field is required'
  		 	  	 },"designation": {
  		 			required: ' This field is required'
  		 	  	 },"faculty_name": {
  		 			required: ' This field is required'
  		 	  	 },"mobile_nos": {
  		 			required: "Enter your mobile no",
  		 			minlength : "please enter valid number",
  		 			minlength : "please enter valid number"
  		 	  	 },"session_nos": {
  		 			required: ' This field is required'
  		 	  	 },"start_times": {
  		 			required: ' This field is required'
  		 	  	 },"end_times": {
  		 			required: ' This field is required'
  		 	  	 },"num_participants": {
  		 			required: ' This field is required'
  		 	  	 },"num_absentees": {
  		 			required: ' This field is required'
  		 	  	 }
	   		},
	   		errorPlacement:function(error, element){
	   		 	if (element.attr("id") == "training_type_fk" ){
					 document.getElementById("training_typeError").innerHTML="";
			 		 error.appendTo('#training_typeError');
			    }else if(element.attr("id") == "training_category_fk" ){
					   document.getElementById("training_category_fkError").innerHTML="";
				 	   error.appendTo('#training_category_fkError');
			    }else if(element.attr("id") == "status_fk" ){
						document.getElementById("status_fkError").innerHTML="";
					 	error.appendTo('#status_fkError');
			    }else if(element.attr("id") == "title" ){
			 		 document.getElementById("titleError").innerHTML="";
	 				 error.appendTo('#titleError');
				}else if(element.attr("id") == "description" ){
			 		 document.getElementById("descriptionError").innerHTML="";
	 				 error.appendTo('#descriptionError');
				}else if(element.attr("id") == "designation" ){
			 		 document.getElementById("designationError").innerHTML="";
	 				 error.appendTo('#designationError');
				}else if(element.attr("id") == "faculty_name" ){
			 		 document.getElementById("faculty_nameError").innerHTML="";
	 				 error.appendTo('#faculty_nameError');
				}else{
	 					error.insertAfter(element);
			       } 
	   		},invalidHandler: function (form, validator) {
		         var errors = validator.numberOfInvalids();
		         if (errors) {
		             var position = validator.errorList[0].element;
		             jQuery('html, body').animate({
		                 scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
		             }, 1000);
		         }validateAttendees();
		     },submitHandler:function(form){
		    	form.submit();
		    }
		});   

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

         function resetModal(a){
        	 var modalId=$(a).closest('.modal.open').attr('id');
        	 $('#'+modalId+' input[type="text"]:not(".no-reset")').val('');
        	 $('#'+modalId+' input[type="number"]:not(".no-reset")').val('');
        	 $('#'+modalId+' input[name="required_fks"]:not(".no-reset")').val('No');
        	 $('#'+modalId+' input[name="participated_fks"]:not(".no-reset")').val('No');
        	 $('#'+modalId+' input[type="checkbox"]:not(".no-reset")').prop('checked',false);
        	 $('#'+modalId+' select:not(".no-reset")').val('');
        	 $('.searchable').select2();
         }
         var rowNumber = 1;
         function validateAttendees(rowNo){
        	 rowNumber = rowNo;
     		var flag = true;
     		$(".department_fks").each(function(){
     			var idNo = (this.id).replace('new_department_fks','');
     			var new_department_fks = $("#new_department_fks"+idNo).val();
     			if($.trim(new_department_fks) == "" ){
 					$('#new_department_fksError'+idNo).text('Requried');
 					$('#new_department_fksError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		
     		$(".hod_user_id_fks").each(function(){
     			var idNo = (this.id).replace('new_hod_user_id_fks','');
     			var new_hod_user_id_fks = $("#new_department_fks"+idNo).val();
     			if($.trim(new_hod_user_id_fks) == "" ){
 					$('#new_hod_user_id_fkError'+idNo).text('Requried');
 					$('#new_hod_user_id_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".attendees").each(function(){
     			var idNo = (this.id).replace('new_attendees','');
     			var new_attendees = $("#new_attendees"+idNo).val();
     			if($.trim(new_attendees) == "" ){
 					$('#new_attendeesError'+idNo).text('Requried');
 					$('#new_attendeesError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".trainee_designations").each(function(){
     			var idNo = (this.id).replace('new_trainee_designations','');
     			var new_trainee_designations = $("#new_trainee_designations"+idNo).val();
     			if($.trim(new_trainee_designations) == "" ){
 					$('#new_trainee_designationsError'+idNo).text('Requried');
 					$('#new_trainee_designationsError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".email").each(function(){
     			var idNo = (this.id).replace('email','');
     			var email = $("#email"+idNo).val();
     			if($.trim(email) == "" ){
 					$('#emailError'+idNo).text('Requried');
 					$('#emailError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".new_mobile_nos").each(function(){
     			var idNo = (this.id).replace('new_mobile_nos','');
     			var new_mobile_nos = $("#new_mobile_nos"+idNo).val();
     			if($.trim(new_mobile_nos) == "" ){
 					$('#mobile_nosError'+idNo).text('Requried');
 					$('#mobile_nosError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".required_fks").each(function(){
     			var idNo = (this.id).replace('new_required_fks','');
     			var reqCheck =  $("#new_required_fks"+idNo+":checked")
     			if(reqCheck.length > 0){
 					$('#new_required_fkError'+idNo).text('');
 				}else{
 					$('#new_required_fkError'+idNo).text('Requried');
 					$('#new_required_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     	/* 	$(".participated_fks").each(function(){
     			var idNo = (this.id).replace('new_participated_fks','');
     			var participatedCheck = $("#new_participated_fks"+idNo+":checked");
     			if(participatedCheck.length > 0){
 					$('#new_participated_fkError'+idNo).text('');
     			}else{
     				$('#new_participated_fkError'+idNo).text('Requried');
 					$('#new_participated_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
     			}
 				}); */
     			var len = $('#newAttendeesTableBody'+rowNo+' tr').length;
     			$(".department_fks").each(function(){
     				
         			var idNo = (this.id).replace('new_department_fks','');
         			var new_department_fks = $("#new_department_fks"+idNo).val();
         			var new_hod_user_id_fks = $("#new_department_fks"+idNo).val();
         			var new_attendees = $("#new_attendees"+idNo).val();
         			var new_trainee_designations = $("#new_trainee_designations"+idNo).val();
         			var new_mobile_nos = $("#new_mobile_nos"+idNo).val();
         			var reqCheck =  $("#new_required_fks"+idNo+":checked");
         			//var participatedCheck = $("#new_participated_fks"+idNo+":checked");
         			var email = $("#email"+idNo).val();
         			if($.trim(new_department_fks) == "" && $.trim(new_hod_user_id_fks) == "" && $.trim(new_attendees) == "" && $.trim(new_trainee_designations) == "" 
         				&& $.trim(new_mobile_nos) == "" && $.trim(email) == "" && (reqCheck.length == 0)){
     					$('#new_department_fksError'+idNo).text('');
     					$('#new_hod_user_id_fkError'+idNo).text('');
     					$('#new_attendeesError'+idNo).text('');
     					$('#new_trainee_designationsError'+idNo).text('');
     					$('#emailError'+idNo).text('');
     					$('#mobile_nosError'+idNo).text('');
     					$('#new_required_fkError'+idNo).text('');
     					//$('#new_participated_fkError'+idNo).text('');
     				}
         		});
     			//$('#newAttendeesTableBody'+rowNo+' .error-msg').each(function(index,value){
     				a = [];
     				$('#newAttendeesTableBody'+rowNo+' .error-msg').each(function(){a.push(this.innerHTML)});
     				console.log(a)
     				var found = a.includes('Requried');
     				if (!found){
     					flag = true;
     				}
     			//})
     			if(!flag){
     				 //$('#session-update-modal'+rowNo).modal();
     				//$('#session-update-modal'+rowNo).modal({dismissible:false});
     				$('#session-update-modal'+rowNo).modal('open');
     			}else{
     				$('#session-update-modal'+rowNo).modal('close');
     			}
     			return flag;
     		};
            
         $('.attendees').keyup(function(key, element){
 			$(".attendees").each(function(){
 				var idNo = (this.id).replace('new_attendees',''); 
       		if($.trim(this.value) != ""){ 
       			$('#new_attendeesError'+idNo).text('');
 			}
           });
 		});
      $('.department_fks').change(function(key, element){
			$(".department_fks").each(function(){
				var idNo = (this.id).replace('new_department_fks',''); 
      		if($.trim(this.value) != ""){ 
      			$('#new_department_fksError'+idNo).text('');
			}
          });
		});
      $('.required_fks').change(function(key, element){
			$(".required_fks").each(function(){
				var idNo = (this.id).replace('new_required_fks',''); 
				var new_required_fks = $("#new_required_fks"+idNo+":checked");
     			if($("#new_required_fks"+idNo).is(":checked")){
       			$('#new_required_fkError'+idNo).text('');
			}
           });
		}); 
     /*  $('.participated_fks').change(function(key, element){
			$(".participated_fks").each(function(){
				var idNo = (this.id).replace('new_participated_fks',''); 
				var participatedCheck = $("#new_participated_fks"+idNo+":checked");
     			if($("#new_participated_fks"+idNo).is(":checked")){
        			$('#new_participated_fkError'+idNo).text('');
				}
            });
		});   */
      $('.new_mobile_nos').keyup(function(key, element){
			$("input[name=mobile_nos]").each(function(){
				var idNo = (this.id).replace('new_mobile_nos',''); 
      		if($.trim(this.value) != "" ){ 
      			$('#mobile_nosError'+idNo).text('');
			}
          });
			
		});
      $('.email').keyup(function(key, element){
			$("input[name=emails]").each(function(){
				var idNo = (this.id).replace('email',''); 
      		if($.trim(this.value) != "" ){ 
       			$('#emailError'+idNo).text('');
			}
           }); 
		});
      $('.trainee_designations').keyup(function(key, element){
			$("input[name=trainee_designations]").each(function(){
				var idNo = (this.id).replace('new_trainee_designations',''); 
        		if($.trim(this.value) != "" ){ 
        			$('#new_trainee_designationsError'+idNo).text('');
				}
            });
		});
      $('.hod_user_id_fks').change(function(key, element){
 			$(".hod_user_id_fks").each(function(){
 				var idNo = (this.id).replace('new_hod_user_id_fks',''); 
         		if($.trim(this.value) != ""){ 
         			$('#new_hod_user_id_fkError'+idNo).text('');
 					$('#new_department_fksError'+idNo).text('');
 				}
             });
 		});
      
      document.addEventListener('DOMContentLoaded', function() {
    	    var elems = document.querySelectorAll('.datepicker');
    	    var options = {
    	        format: 'yyyy-mm-dd',
    	        autoClose: true
    	    };
    	    var instances = M.Datepicker.init(elems, options);
    	});
     
     

    
    

 </script>
</body>

