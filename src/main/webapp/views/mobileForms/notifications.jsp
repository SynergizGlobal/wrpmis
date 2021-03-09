<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PMIS Notifications</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/mobile-notifications.css">
	<style>
		.modal-header {
			text-align: center;
			background-color: #2E58AD;
			color: #fff;
			margin: -24px -24px 20px !important;
			padding: 1rem;
		}
		.row.no-mar{
			margin-bottom:0;
		}
		.m-b-2{
			margin-bottom:1rem;
		}
	</style>

</head>

<body>


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-2">
                            <h5>Notifications</h5>
                        </div>
                    </span>
                        <div class="row no-mar">                            
                   <div >
                  <!-- Notification dropdown body starts -->
                      <div>
                          <input type="text"  name="srch-term" id="srch-term" class=" searching empty"placeholder="&#xF002; Search Alerts...">
                      </div>
                      
                       <ul class="notifications_group" style="margin-top: 4px;" id="notificationList">
                       <!-- list of Notifications starts -->
                       <c:forEach var="obj" items="${alerts }">
                          <li class="head-item">${obj.key}</li>
                          <c:if test="${obj.key eq '3rd Alert'}">
                          	<c:set var="bgClass" value="type-3"></c:set>
                          </c:if>
                          <c:if test="${obj.key eq '2nd Alert'}">
                          	<c:set var="bgClass" value="type-2"></c:set>
                          </c:if>
                          <c:if test="${obj.key eq '1st Alert'}">
                          	<c:set var="bgClass" value="type-1"></c:set>
                          </c:if>
                          <c:forEach var="aObj" items="${obj.value}">
                          	  
                          	  <c:if test="${aObj.alert_type_fk eq 'Bank Guarantee'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>account_balance</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Insurance'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>security</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>access_time</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
	                          	<c:set var="bgIcon" value="<i class='fa fa-money'></i>"></c:set>
	                          </c:if>
                          	  
                          	  <li class="item ${bgClass }">
	                              <a href="#">
	                              	<span class="icon">
	                              	 	<!-- <i class="material-icons">access_time</i> -->
	                              	 	${bgIcon }
	                              	 	<span class="icon-text">${aObj.alert_type_fk }</span>
	                              	 </span>                                   
	                                 <div>Work : ${aObj.work_short_name }</div>
	                                 <div>Contract : ${aObj.contract_short_name }</div>
	                                 <div>Contractor : ${aObj.contractor_name }</div>
	                                 <div>Reason : ${aObj.alert_value }</div>
	                              </a>
	                          </li>
	                         
                          </c:forEach>
                      </c:forEach>
                      
                      <!-- 	removable code starts here -->
                      <!-- <li class="head-item">3rd Alert</li>
                      <li class="item type-3">
                          <a href="#">
                          	<span class="icon">
                          	 	<i class="material-icons">access_time</i>
                          	 	<i class="material-icons">security</i>
                          	 	<span class="icon-text">Insurance</span>
                          	 </span>                                   
                             <div>Work : 5th and 6th Line Thane-Diva</div>
                             <div>Contract : OHE &amp; associated works with SP at Mumbra for prov of 5th &amp; 6th lines betw TNA-DW</div>
                             <div>Contractor : Enrich R.D. Infraprojects Pvt Ltd</div>
                             <div>Reason : CAR 111161822120224000 Valid upto 13-Mar-2021</div>
                          </a>
                      </li>
                      <li class="item type-3">
	                              <a href="#">
	                              	<span class="icon">
	                              	 	<i class="material-icons">access_time</i>
	                              	 	<i class="fa fa-money"></i>
	                              	 	<span class="icon-text">Contract Value</span>
	                              	 </span>                                   
	                                 <div>Work : 5th and 6th Line Thane-Diva</div>
	                                 <div>Contract : General Electrical service works for associated works of provision of 5th and 6 th line between Than</div>
	                                 <div>Contractor : Jagdish Engineering Services</div>
	                                 <div>Reason : Cumulative expenditure : 70262497, Awarded Cost : 22257327.91</div>
	                              </a>
	                          </li>
	                          <li class="head-item">1st Alert</li>
	                          <li class="item type-1">
	                              <a href="#">
	                              	<span class="icon">
	                              	 	<i class="material-icons">access_time</i>
	                              	 	<i class="material-icons">access_time</i>
	                              	 	<span class="icon-text">Contract Period</span>
	                              	 </span>                                   
	                                 <div>Work : Trespass Control on Mid-Section</div>
	                                 <div>Contract : TPC  FOB OHE works</div>
	                                 <div>Contractor : Leena Electro Mechanical Pvt. Ltd</div>
	                                 <div>Reason : Contract revised date : 31-Mar-2021</div>
	                              </a>
	                          </li>
	                          <li class="item type-1">
	                              <a href="#">
	                              	<span class="icon">
	                              	 	<i class="material-icons">access_time</i>
	                              	 	<i class="material-icons">account_balance</i>
	                              	 	<span class="icon-text">Bank Guarantee</span>
	                              	 </span>                                   
	                                 <div>Work : Trespass Control on Mid-Section</div>
	                                 <div>Contract : Inspection of Fabrication of FOB girders in TPC FOB</div>
	                                 <div>Contractor : M/s. CEIL, Mumbai</div>
	                                 <div>Reason : Performance Guarantee OGT0001190035274 valid upto 31-Mar-2021</div>
	                              </a>
	                          </li> -->
	                          
	                       <!-- 	removable code ends here -->
                      </ul>
                      
                      <!-- Notification dropdown body ends -->
                </div>
                        </div>
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
	
	
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
<!--     <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script> -->
	
  <script>
	  $("#srch-term").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#notificationList li.item").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		});
		
	  $('.searching').on('keyup', function () {
	      var input = $(this);
	      if (input.val().length === 0) {
	          input.addClass('empty');
	      } else {
	          input.removeClass('empty');
	      }
	  });
  </script>

</body>

</html>