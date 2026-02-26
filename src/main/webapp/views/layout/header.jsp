<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants2" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="icon" href="/wrpmis/resources/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="/wrpmis/resources/images/favicon.ico" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dashboard</title>
<link rel="preload"  as="style" href="/wrpmis/resources/css/style.css" onload="this.onload=null;this.rel='stylesheet'">
<link rel="preload"  as="style" href="/wrpmis/resources/css/navigation.css" onload="this.onload=null;this.rel='stylesheet'">
<link rel="preload" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght@400&display=swap" as="style">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer">
 
 
  <!-- Load Google Fonts with font-display swap -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght@400&display=swap" rel="stylesheet">
<noscript>
<link rel="stylesheet" href="/wrpmis/resources/css/navigation.css">
<link rel="stylesheet" href="/wrpmis/resources/css/style.css">
</noscript>

 <style>
    body {
      background-color: #FFEFE2;
    }
    .menu-item a {
      color: #fff;
    }
    .homepage-heading {
      padding: 40px 0;
      gap: 40px;
    }
    .homepage-menu {
      display: flex;
      flex-wrap: wrap;
      align-items: center;
      justify-content: center;
      gap: 40px;
      align-items: stretch;
    }
    .homepage-menu a {
      background-color: #fff;
      color: #000;
      font-weight: 600;
      box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
      padding: 15px 40px;
      border-radius: 10px;
      flex: 0 0 20%;
      display: flex;
      align-items: center;
      text-align: center;
      justify-content: center;
      transition: 0.3s ease-in-out;
    }
    .homepage-menu a:hover {
      background-color: #D58D54;
      color: #fff;
    }
    @media(max-width: 630px) {
      .heading-name h1 {
        margin: 0;
      }
      .homepage-menu a {
        padding: 15px 10px;
        flex: 0 0 35%;
      }
      .homepage-menu {
        justify-content: space-between;
        gap: 30px;
        padding: 0 5px;
      }
      .heading-logo img {
        width: 40px;
        height: 40px;
      }
    }
    
      .page-title-bar h5 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
        display: inline-block;
    }

   

    .action-buttons .btn {
        margin-left: 10px;
    }

    .data-table-wrapper {
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
    }

    .success, .error {
        margin: 10px 0;
        padding: 10px;
        border-radius: 4px;
        text-align: center;
        font-weight: bold;
    }

    .success {
        background-color: #d4edda;
        color: #155724;
    }

    .error {
        background-color: #f8d7da;
        color: #721c24;
    }

    table.mdl-data-table {
        width: 100%;
        border-collapse: collapse;
    }

    table.mdl-data-table th,
    table.mdl-data-table td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table.mdl-data-table th {
        background: #e56717;
        color: white;
    }

    table.mdl-data-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .last-column {
        text-align: center;
    }

    .mob-btn i {
        font-size: 16px;
    }

    .container-padding {
        padding: 30px;
    }
  .btn-primary {
    color: #fff;
    background-color: #EA6A2A;
}  

.btn-primary:hover {
    background-color: #0B5073;
}
     .material-symbols-outlined{
     	font-size: 24px;
     }
     
  .material-symbols-outlined{
     	font-size: 24px;
     	vertical-align: middle;
     }
 
img {
	  display: block;
	  max-width: 100%;
	  height: auto;
	}   
  </style>
</head>
<body>
<div class="page-container">
  <nav>
    <div style="color: white; font-weight: bold;">
      <img src="/wrpmis/resources/images/wr-logo-small.png" alt="logo" width="50" height="50">
    </div>
    <div class="menu" id="menu">
      <div class="menu-item">
        <a href="<%=request.getContextPath()%>/home"><i class="fa-solid fa-house"></i> Home</a>
      </div>

      <!-- Dynamic Modules Menu -->
      <div class="menu-item">
        <div class="d-flex align-center"><span class="material-symbols-outlined">dashboard</span> Modules</div>
<div class="dropdown">
    <div class="menu-item"><a href="/wrpmis/InfoViz/fortnight-meeting">Fortnight Meeting</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/drawing-status">Drawing Status</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/project-performance-appraisal">Project Performance Appraisal</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/timeline-schedule-module">Timeline Schedule Module</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/bg-&amp;-insurance-dashboard">BG &amp; Insurance Dashboard</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/usage-analysis-dashboard">Usage Analysis Dashboard</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/contractwise-physical-progress">Contractwise Physical Progress</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/component-wise-progress">Component Wise Progress</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/progress-table">Progress Table</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/contract-status">Contract Status</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/hindrance">Issues</a></div> <!-- No matching URL found, you can update this -->
    <div class="menu-item"><a href="/wrpmis/InfoViz/structure-wise-short-fall">Structure wise Shortfall</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/dpr-updation-&amp;-validation-status">DPR Updation &amp; Validation Status</a></div>
    <div class="menu-item"><a href="/wrpmis/InfoViz/tdc-projections">TDC Projections</a></div>
</div>      </div>

    <!-- Works -->
<div class="menu-item"> 
  <div class="d-flex align-center">
    <span class="material-symbols-outlined">dashboard</span> Works
  </div>
  
  <div class="dropdown">
	<c:forEach var="type" items="${projectTypes}">
	  <div class="menu-item">
	    <div class="d-flex justify-space-between w-100">
	      <span class="title-case">${type.project_type_name}</span>
	      <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fff">
	        <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
	      </svg>
	    </div>
	
	    <div class="sub-dropdown">
	      <c:forEach var="proj" items="${projectTypeswithProject}">
	        <c:if test="${proj.project_type_id == type.project_type_id}">
	          <div class="menu-item">
	            <a class="title-case" href="javascript:getProjectOverview('${proj.project_id}')">${proj.project_name}</a>
	          </div>
	        </c:if>
	      </c:forEach>
	    </div>
	  </div>
	</c:forEach>
  </div>
</div>

<div class="menu-item">
<span class="material-symbols-outlined">
edit_document
</span>
  Update Forms
  <div class="dropdown">

    <!-- Projects -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Projects</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/project">Add Project</a></div>
      </div>
    </div>

    <!-- Works -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Works</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/structure">Add Structure</a></div>
        <div class="menu-item"><a href="/wrpmis/structure-form">Update Structure</a></div>
        <div class="menu-item">
          <div class="d-flex justify-space-between w-100">
            <span>Technical Assistance</span>
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
              <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
            </svg>
          </div>
          <div class="sub-dropdown">
            <div class="menu-item"><a href="/wrpmis/deliverables">Deliverables</a></div>
          </div>
        </div>
      </div>
    </div>

    <!-- Contracts/Tenders -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Contracts/Tenders</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/contract">Contract</a></div>
        <div class="menu-item"><a href="/wrpmis/contractor">Contractor</a></div>
      </div>
    </div>

    <!-- Execution & Monitoring -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Execution & Monitoring</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/p6-new-data">Structure P6 Updates</a></div>
        <div class="menu-item"><a href="/wrpmis/new-activities-update">New Activities Update</a></div>
        <div class="menu-item"><a href="/wrpmis/modify-actuals">Modify Actuals</a></div>
      </div>
    </div>



    <!-- Direct Links -->
    <div class="menu-item"><a href="/wrpmis/design">Design & Drawing</a></div>
    <div class="menu-item"><a href="/wrpmis/issues">Issues</a></div>
    
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Land Acquisition</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" width="24px" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/land-acquisition-process-grid">Land Acquisition process</a></div>
        <div class="menu-item"><a href="/wrpmis/land-acquisition">Add Land Details</a></div>
      </div>
    </div>    
    
    
    
    <div class="menu-item"><a href="/wrpmis/utilityshifting">Utility Shifting</a></div>
    <div class="menu-item"><a href="/wrpmis/progress-approval-page">Validate Data</a></div>

    <div class="menu-item"><a href="/wrpmis/get-alerts">Alerts</a></div>
  </div>
</div>




<div class="dropdown">
  <c:forEach var="form" items="${forms}">
  
    <!-- Case: Has Submenu -->
    <c:if test="${not empty form.formsSubMenu}">
      <div class="menu-item">
        <div class="d-flex justify-space-between w-100">
          <span>${form.formName}</span>
          <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fff">
            <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z" />
          </svg>
        </div>

        <div class="sub-dropdown">
          <c:forEach var="sub" items="${form.formsSubMenu}">
          
            <!-- Submenu Level 2 -->
            <c:if test="${empty sub.formsSubMenuLevel2}">
              <div class="menu-item">
                <a href="${pageContext.request.contextPath}/${sub.webFormUrl}">${sub.formName}</a>
              </div>
            </c:if>

            <!-- Submenu Level 3 -->
            <c:if test="${not empty sub.formsSubMenuLevel2}">
              <div class="menu-item">
                <div class="d-flex justify-space-between w-100">
                  <span>${sub.formName}</span>
                  <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fff">
                    <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z" />
                  </svg>
                </div>
                <div class="sub-dropdown">
                  <c:forEach var="sub2" items="${sub.formsSubMenuLevel2}">
                    <div class="menu-item">
                      <a href="${pageContext.request.contextPath}/${sub2.webFormUrl}">${sub2.formName}</a>
                    </div>
                  </c:forEach>
                </div>
              </div>
            </c:if>

          </c:forEach>
        </div>
      </div>
    </c:if>

    <!-- Case: No Submenu -->
    <c:if test="${empty form.formsSubMenu && not empty form.webFormUrl}">
      <div class="menu-item">
        <a href="${pageContext.request.contextPath}/${form.webFormUrl}">${form.formName}</a>
      </div>
    </c:if>

  </c:forEach>
</div>

    <!-- Reports -->
<div class="menu-item">
<span class="material-symbols-outlined">
assignment
</span>
  Reports
  <div class="dropdown">

    <!-- Contracts -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Contracts</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/contract-report/2">Contract Detail</a></div>
        <div class="menu-item"><a href="/wrpmis/contractorslist">List of Contractors</a></div>
        <div class="menu-item"><a href="/wrpmis/contract-report/7">List of Contracts</a></div>
        <div class="menu-item"><a href="/wrpmis/contract-report/8">BG/Insurance Report</a></div>
        <div class="menu-item"><a href="/wrpmis/contract-report/9">Date of Completion Report</a></div>
        <div class="menu-item"><a href="/wrpmis/bg-contractual-letters">BG Contractual Letters</a></div>
        <div class="menu-item"><a href="/wrpmis/insurance-contractual-letters">Insurance Contractual Letters</a></div>
        <div class="menu-item"><a href="/wrpmis/doc-contractual-letters">Date of Completion Letters</a></div>
      </div>
    </div>

    <div class="menu-item"><a href="/wrpmis/activities-export-report">Contract-wise Activities</a></div>

    <!-- Progress Report -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Progress Report</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/tpc-status-report">TPC</a></div>
        <div class="menu-item"><a href="/wrpmis/mcdo-progress-report">Network Expansion Works</a></div>
        <div class="menu-item"><a href="/wrpmis/station-improvements-report">Station Improvements Progress Report</a></div>
      </div>
    </div>

    <!-- Issues -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Issues</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/issues-report">Pending Issues Report</a></div>
        <div class="menu-item"><a href="/wrpmis/generate-issues-summary-report">Issues Summary Report</a></div>
        <div class="menu-item"><a href="/wrpmis/issue-details-report">Issue Details Report</a></div>
      </div>
    </div>





    <div class="menu-item"><a href="/wrpmis/la-report">Land Acquisition</a></div>

    <!-- Utility Shifting -->
    <div class="menu-item">
      <div class="d-flex justify-space-between w-100">
        <span>Utility Shifting</span>
        <svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" fill="#fff">
          <path d="M504-480 320-664l56-56 240 240-240 240-56-56 184-184Z"/>
        </svg>
      </div>
      <div class="sub-dropdown">
        <div class="menu-item"><a href="/wrpmis/utility-report">Utility Report</a></div>
      </div>
    </div>

  </div>
</div>


<div class="menu-item">
<span class="material-symbols-outlined">description</span>
  Documents
  <div class="dropdown">
    <div class="menu-item"><a href="/wrpmis/web-documents/codes-&amp;-manuals">Codes &amp; Manuals</a></div>
    <div class="menu-item"><a href="/wrpmis/web-documents/faq">FAQ</a></div>
    <div class="menu-item"><a href="/wrpmis/web-documents/policies-&amp;-circulars">Policies &amp; Circulars</a></div>
  </div>
</div>
<div class="menu-item">
<span class="material-symbols-outlined">
link
</span>
  Quick Links
  <div class="dropdown">
    <div class="menu-item">
      <a href="http://www.shramikkalyan.indianrailways.gov.in/" target="_blank">Shramik Kalyan Portal</a>
    </div>
    <div class="menu-item">
      <a href="https://return.shramsuvidha.gov.in/user/login" target="_blank">Shram Suvidha</a>
    </div>
  </div>
</div>

<div class="menu-item">
<span class="material-symbols-outlined">
article_person
</span>
  Admin
  <div class="dropdown">

    <div class="menu-item"><a href="/wrpmis/left-menu">Overview Dashboards</a></div>


    <div class="menu-item"><a href="/wrpmis/users">Users</a></div>
    <div class="menu-item"><a href="/wrpmis/access-dashboards">Dashboards</a></div>
    <div class="menu-item"><a href="/wrpmis/access-forms">Forms</a></div>
    <div class="menu-item"><a href="/wrpmis/access-reports">Reports</a></div>
    <div class="menu-item"><a href="/wrpmis/web-links">Web Links</a></div>
    <div class="menu-item"><a href="/wrpmis/template-upload">Template Upload</a></div>
    <div class="menu-item"><a href="/wrpmis/user-manuals">WR PMIS Manuals</a></div>
    <div class="menu-item"><a href="/wrpmis/reference-forms">Reference Forms</a></div>
    <div class="menu-item"><a href="/wrpmis/delete-activities">Delete Activities</a></div>
    <div class="menu-item"><a href="/wrpmis/run_planned_normal_distribution_day">Refresh S-Curve</a></div>
  </div>
</div>

  </div>

      <div class="notf-profile">
  	<div class="bell-icon">
  		<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fff"><path d="M160-200v-80h80v-280q0-83 50-147.5T420-792v-28q0-25 17.5-42.5T480-880q25 0 42.5 17.5T540-820v28q80 20 130 84.5T720-560v280h80v80H160Zm320-300Zm0 420q-33 0-56.5-23.5T400-160h160q0 33-23.5 56.5T480-80ZM320-280h320v-280q0-66-47-113t-113-47q-66 0-113 47t-47 113v280Z"/></svg>

  		<div class="notf-tab">
  			<ul>
  				<li>notification for testing. message appeared</li>
  			</ul>
  		</div>
  	</div>
  	<div class="message-icon">
  		<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fff"><path d="M80-80v-720q0-33 23.5-56.5T160-880h640q33 0 56.5 23.5T880-800v480q0 33-23.5 56.5T800-240H240L80-80Zm126-240h594v-480H160v525l46-45Zm-46 0v-480 480Z"/></svg>

  		<div class="notf-tab">
  			<ul>
  				<li>notification for testing. message appeared</li>
  				<li>notification-2 for testing. message appeared</li>
  				<li>notification-3 for testing. message appeared</li>
  			</ul>
  		</div>
  	</div>
  	<div class="profile-name">
  		 <p>${USER_NAME}</p>

  		<div class="notf-tab">
  			<div class="profile-options">
						<a href="<%=request.getContextPath()%>/profile">Profile</a>
						<a href="<%=request.getContextPath()%>/reset-password">Reset
								password</a>
						<a href="<%=request.getContextPath()%>/logout">Logout</a>
  			</div>
  		</div>
  	</div>
  </div>

    <div class="menu-toggle" onclick="toggleMenu()">
      <div class="bar"></div><div class="bar"></div><div class="bar"></div>
    </div>
  </nav>
    <div class="body-content">
  
  <script>
  document.addEventListener("DOMContentLoaded", function () {
    const isWRPMIS = window.location.pathname.includes("/wrpmis/");
    
    if (isWRPMIS) {
      // Hide select elements named work_id or work_id_fk
      document.querySelectorAll("select[name='work_id'], select[name='work_id_fk']").forEach(el => {
        el.closest('.form-group')?.style.setProperty('display', 'none', 'important'); // If inside form group
        el.style.display = 'none'; // fallback
      });

      // Optional: hide label too if it's not grouped
      document.querySelectorAll("label[for='work_id'], label[for='work_id_fk']").forEach(label => {
        label.style.display = 'none';
      });
    }
  });
  
	function getProjectOverview(project_id){
		//window.location.href='work-overview-dashboard/'+project_id;
		window.location.href = '/wrpmis/work-overview-dashboard/'+project_id;
  	}
	
	
	  function toTitleCase(str) {
	    return str.toLowerCase().replace(/\b\w/g, c => c.toUpperCase());
	  }

	  document.addEventListener("DOMContentLoaded", function () {
	    document.querySelectorAll('.title-case').forEach(function (el) {
	      el.textContent = toTitleCase(el.textContent);
	    });
	  });
</script>
  
