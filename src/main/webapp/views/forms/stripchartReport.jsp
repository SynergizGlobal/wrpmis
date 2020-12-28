<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/searchable-dropdown.css">
	<style>
		.input-field .searchable_label {
			font-size:0.9rem;
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
								<h6>StripChart Report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form>
						<div class="container container-no-margin">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m4 input-field">
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk">
										<option value="">Select</option>
									</select> <span id="project_idError" class="error-msg"></span>
								</div>
								<div class="col s12 m4 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" >
										<option value="">Select</option>
									</select> <span id="work_id_fkError" class="error-msg"></span>
								</div>
								<div class="col m2 hide-on-small-only"></div>
							</div>

							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m4 input-field">
									<input id="date" name="date" type="text" class="validate datepicker"> <label for="date">Date</label>
									<button type="button" id="date_icon" class="white"><i class="fa fa-calendar"></i></button>
								</div>
								<div class="col s12 m4 input-field">
									<div class="center-align m-1">
										<a href="#"
											class="btn waves-effect waves-light bg-m t-c"
											style="width: 100%">Generate Report</a>
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

	<div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<p>reports goes here </p>
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
	<script>
      $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        
        </script>
</body>
</html>