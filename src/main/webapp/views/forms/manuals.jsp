<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Manuals</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<style>		
		.center-align.p-2.bg-m h5{
			font-size:20px;
		}
		.t-t-i {
			text-transform: inherit;
			font-size:1rem;
			font-weight:500;
		}
		.modal-header {
		    text-align: center;
		    background-color: #2E58AD;
		    color: #fff;
		    margin: -24px -24px 20px !important;
		    padding: 1rem;
		}
		.preview-modal{
			max-height: 80%;
    		min-height: 60%;
		}
		.main-card>.card-content{
			min-height:85vh;
		}
	
	 /* change icon & bg based on the active state starts */
	  	li.active>.collapsible-header{
			background-color:#DAE8F9;
		}
		li.active>.collapsible-body{
			background-color:#ECF8FD;
		}
        .collapsible-header>.close {
            display: none;
        }

        .collapsible-header>.open {
            display: inline-block;
        }

        .active>.collapsible-header .open {
            display: none;
        }

        .active>.collapsible-header .close {
            display: inline-block;
        }
	/* change icon & bg based on the active state ends  */
		.card-action.flex {
			display: flex;
			justify-content: space-between;
			padding: 8px 24px;
		}
		
		.card-action.flex a {
			margin-right: 0 !important;
			font-size: 2rem;			
			cursor:pointer;
			color:#2E58AD !important;
		}
		.card .card-title {
    		font-size: 1.2rem;
    	}
		
		.collapsible-body .row {
			margin-bottom: 0;
		}
		
		.collapsible-body .card-content img {
			width: 100px;
			height: 100px;
		}
		
		.collapsible-body .card-file {
			width: 230px;
		}
		
		.card-file .card-title, 
		.card-file .card-content .card-title,
		.collapsible-body .card-content .card-title {
			margin-bottom: 0;
		}
		
		.files-collection {
			display: flex;
			justify-content: space-between;
			flex-wrap: wrap;
		}
		
		.input-field .prefix.right-side {
			right: 10px;
		}
		
		.input-field .prefix.right-side ~label,
		.input-field .prefix.right-side	~input {
			margin-left: 0;
			width: 100%;
		}
		
		.input-field .prefix.right-side ~input {
			background-color: #fafafa;
			width: calc(100% - 25px);
			border-radius: 20px;
			padding-left: 25px;
		}
		
		@media only screen and (max-width: 600px) {
			.files-collection {
				justify-content: center;
			}
			.collapsible-body .card-file {
				margin-right: auto;
				margin-left: auto;
			}
			.input-field .prefix.right-side ~input {
				padding-left: 15px;
				width: calc(100% - 15px);
			}
		}
		
	</style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12">
			<div class="card main-card">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m">
								<h5>Manuals</h5>
							</div>
						</span>
					</div>

					<div class="container" style="margin-top: 30px;">
						<div class="row" style="margin-top: 30px">
							<div class="input-field col m6 s12">
								<i class="material-icons prefix right-side"
									onclick="alert('search')">search</i> <input id="icon_telephone"
									type="text" class="validate autocomplete"
									placeholder="Search ...">
							</div>
							<div class="col m6 s12">
								<form action="#">
									<div class="file-field input-field">
										<div class="btn t-t-i bg-m">
											<span>Upload File</span> <input type="file">
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text">
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<ul class="collapsible">
								<li>
									<div class="collapsible-header">
										<i class="fa fa-folder open"></i> <i class="fa fa-folder-open close"></i>Contractor
									</div>
									<div class="collapsible-body">
										<div class="row">
											<div class="col s6 m3">
												<div class="card">
													<div class="card-content center-align">
														<img src="/pmis/resources/images/document.svg"> <span
															class="card-title">Contractor 1</span>
													</div>
													<div class="card-action flex">
														<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
															href="#"><i class="fa fa-download"></i></a>
													</div>
												</div>
											</div>
											<div class="col s6 m3">
												<div class="card">
													<div class="card-content center-align">
														<img src="/pmis/resources/images/document.svg"> <span
															class="card-title">Contractor 2</span>
													</div>
													<div class="card-action flex">
														<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
															href="#"><i class="fa fa-download"></i></a>
													</div>
												</div>
											</div>
											<div class="col s6 m3">
												<div class="card">
													<div class="card-content center-align">
														<img src="/pmis/resources/images/document.svg"> <span
															class="card-title">Contractor 3</span>
													</div>
													<div class="card-action flex">
														<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
															href="#"><i class="fa fa-download"></i></a>
													</div>
												</div>
											</div>
											<div class="col s6 m3">
												<div class="card">
													<div class="card-content center-align">
														<img src="/pmis/resources/images/document.svg"> <span
															class="card-title">Contractor 4</span>
													</div>
													<div class="card-action flex">
														<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
															href="#"><i class="fa fa-download"></i></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
								<li>
									<div class="collapsible-header">
										<i class="fa fa-folder open"></i> <i class="fa fa-folder-open close"></i> Contracts
									</div>
									<div class="collapsible-body">
										<div class="files-collection">

											<div class="card card-file">
												<div class="card-content center-align">
													<img src="/pmis/resources/images/document.svg"> <span
														class="card-title">Contractor 1</span>
												</div>
												<div class="card-action flex">
													<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
														href="#"><i class="fa fa-download"></i></a>
												</div>
											</div>
											<div class="card card-file">
												<div class="card-content center-align">
													<img src="/pmis/resources/images/document.svg"> <span
														class="card-title">Contractor 2</span>
												</div>
												<div class="card-action flex">
													<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
														href="#"><i class="fa fa-download"></i></a>
												</div>
											</div>
											<div class="card card-file">
												<div class="card-content center-align">
													<img src="/pmis/resources/images/document.svg"> <span
														class="card-title">Contractor 3</span>
												</div>
												<div class="card-action flex">
													<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
														href="#"><i class="fa fa-download"></i></a>
												</div>
											</div>
											<div class="card card-file">
												<div class="card-content center-align">
													<img src="/pmis/resources/images/document.svg"> <span
														class="card-title">Contractor 4</span>
												</div>
												<div class="card-action flex">
													<a href="#modal-test" class="modal-trigger"><i class="fa fa-eye"></i></a> <a
														href="#"><i class="fa fa-download"></i></a>
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>

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

  <!-- Modal Structure -->
  <div id="modal-test" class="modal preview-modal">
    <div class="modal-content">
      <h5 class="modal-header">File Preview <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
      <div class="center-align" style="margin-top:150px">Preview of File Goes Here....</div>
    </div>
  </div>

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script>
	    $(document).ready(function(){
	      $('.collapsible').collapsible();
	      $('.modal').modal({
	    	  dismissible:false,
	      });
	      $('input.autocomplete').autocomplete({
              data: {
                  "MUTP-I": null,
                  "MUTP-II": null,
                  "MUTP-IIA": null,
                  "MUTP-IIB": null,
                  "MUTP-III": null,
              },
          });
	    });
    </script>
</body>
</html>