<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contractor - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png"> 
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contractor.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
   	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
   	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
   	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
   <style>
   
   		 .column-reverse{
            display: flex;
            flex-direction: row-reverse;
            align-items: normal;
        }
        button.accordion {
          width: 100%;
          background-color: transparent;
          border: none;
          outline: none;
          text-align: left;
          padding: 15px 20px;
          font-size: 14px;
          color: #333;
          cursor: pointer;
          transition: background-color 0.2s linear;
      }

      

      button.accordion:hover,
      button.accordion.is-open {
          background-color: #ddd;
      }

      .accordion-content {
          background-color: white;
          border-left: 1px solid whitesmoke;
          border-right: 1px solid whitesmoke;
          padding: 0 20px;
          max-height: 0;
          overflow: hidden;
          transition: max-height 0.2s ease-in-out;
      }
      .gal-image{
        width: 200px;
        height: 180px;
      }
      #myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal{top: 10%;

}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  z-index: 1003 !important;
}

/* Caption of Modal Image */
/*#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}*/

/* Add Animation */


/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
/*breadcrumbs*/
ul.breadcrumb li {
  display: inline;
  font-size: 18px;
}
ul.breadcrumb li+li:before {
  padding: 8px;
  color: black;
  content: "/\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
@media(max-width: 575px){
.column-reverse{flex-direction: column;}
}
   </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Gallery</h6>
                        </div>
                    </span>
                    <div class="">

                        <!-- <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar" style="margin-bottom: 0;">
                            
                        </div>

                        <div class="row  column-reverse">
                            <div class="col m2 s12">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m12 input-field">
                                        <p class="searchable_label">Select Module </p>
                                        <select id="work_id_fk" class="searchable">
                                            <option value="" disabled selected>Select Module Name</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m12 input-field">
                                        <p class="searchable_label">Select Dashboard Type</p>
                                        <select id="dept_fk" class="searchable">
                                            <option value="" disabled selected>Select Dashboard</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m12 input-field">
                                        <p class="searchable_label">Select Status</p>
                                        <select id="contractor_fk" class="searchable">
                                            <option value="" disabled selected>Select Contractor</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m12">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 20px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m10 s12">
                                <div class="photos">
                                    <ul>
                                        <div class="row">
                                            <li class="col l4 m4 s12">
                                                <center>
                                                    <a href="#modal1" class="modal-trigger">
                                                        <img src="/pmis/resources/images/mrvclogo.png" alt="image" class="gal-image myImages" id="myImg">
                                                    </a>
                                                </center>
                                                <button class="accordion">description: <span class="right">Date: 08/03/2021</span></button>
                                                <div class="accordion-content">
                                                    <p>
                                                      Whether you need a wordpress website, a shopify site, or a custom fullstack application
                                                  </p>
                                              </div>
                                          </li>
                                       </div>

						          </ul>
						      </div>
                              <!--   <div id="myModal" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="img01">
              <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Designs</h6>
            </div>

  <div id="caption">path</div>
</div> -->
<!-- update popup starts -->
<div id="modal1" class="modal">
    <div class="modal-content">
        <div class="center-align p-2 bg-m modal-title">
            <h6>Image</h6>
        </div>
            <!-- <ul class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li><a href="#">Pictures</a></li>
              <li><a href="#">Summer 15</a></li>
              <li>Italy</li>
          </ul> -->
          <img class="modal-content" id="img01">
          
      </div>
  </div>
</div>
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
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
   	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>  

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contractor_id" id="contractor_id" />
    </form>
     
    <form action="<%=request.getContextPath() %>/export-contractor" name="exportContractorForm" id="exportContractorForm" target="_blank" method="post">	
        <input type="hidden" name="contractor_id" id="exportContractor_id" />
	</form>
	
    <script type="text/javascript">
        $(".selectrow1").click(function(){
            $(".row1").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow2").click(function(){
            $(".row2").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow3").click(function(){
            $(".row3").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn1").click(function(){
            $(".column1").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn2").click(function(){
            $(".column2").prop("checked",$(this).prop("checked"));
        });
    </script>
    <script>
        $(document).ready(function () {
            $('.modal').modal();
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            $('#example').DataTable({
                columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric',
                    targets: 'no-sort', orderable: false,
                },
                { "width": "20px", "targets": [7] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                ordering: false,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
                    .unbind(),
                    self = this.api(),
                    $searchButton = $('<i class="fa fa-search" title="Go" id="save_post">')
                    .click(function () { self.search(input.val()).draw(); }),
                    $clearButton = $('<i class="fa fa-close" title="Reset">')
                    .click(function () {
                        input.val('');
                        $searchButton.click();
                    })
                    $('.dataTables_filter').append(
                        '<div class="right-btns"></div>');
                    $('.dataTables_filter div').append(
                        $searchButton, $clearButton);
                }
            });

            $('.clear-filters').click(function () {
                $('#hod_fk').val("");
                $('#module_fk').val("");
            });
        });

        //accordion script
        const accordionBtns = document.querySelectorAll(".accordion");

        accordionBtns.forEach((accordion) => {
          accordion.onclick = function () {
            this.classList.toggle("is-open");

            let content = this.nextElementSibling;
            console.log(content);

            if (content.style.maxHeight) {
      //this is if the accordion is open
      content.style.maxHeight = null;
  } else {
      //if the accordion is currently closed
      content.style.maxHeight = content.scrollHeight + "px";
      console.log(content.style.maxHeight);
  }
};
});

        //modal images
        // create references to the modal...
        var modal = document.getElementById('modal1');
// to all images -- note I'm using a class!
var images = document.getElementsByClassName('myImages');
// the image in the modal
var modalImg = document.getElementById("img01");
// and the caption in the modal
var captionText = document.getElementById("caption");

// Go through all of the images with our custom class
for (var i = 0; i < images.length; i++) {
  var img = images[i];
  // and attach our click listener for this image.
  img.onclick = function(evt) {
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}
}

var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
  modal.style.display = "none";
}
</script>
</body>

</html>