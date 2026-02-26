<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="./layout/header.jsp"></jsp:include>

<style>
/* Body & Homepage */
.body-content { padding: 20px; background-color: #ffeedd; font-family: Arial, sans-serif; }
.homepage { max-width: 1200px; margin: 0 auto; }

/* Header Logos & Name */
.homepage-heading {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
    margin-bottom: 30px;
    position: relative;
}
.homepage-heading img { width: 60px; height: 60px; }
.homepage-heading h1 { font-size: 28px; font-weight: bold; }

/* Dashboard Cards */
.dashboard-cards {
    display: flex; 
    justify-content: center; 
    flex-wrap: wrap; 
    gap: 20px; 
    margin-bottom: 60px;
}
.dashboard-cards .card { padding: 20px; border-radius: 15px; box-shadow: 0px 0px 3px 3px rgba(231, 151, 114, 0.7); width: 200px; text-align: center; display: flex; flex-direction: column; gap: 10px; align-items: center; justify-content: center;background-color: #ffd4b2; }

/* Project Type Menu */
.homepage-menu {
    display: flex;
    flex-wrap: wrap;
    gap: 15px;
    justify-content: center;
    margin-bottom: 30px;
}
.homepage-menu a {
    background-color: #fff;
    border-radius: 12px;
    padding: 16px 24px;
    text-align: center;
    font-weight: bold;
    text-decoration: none;
    color: #000;
    box-shadow: 0 2px 8px rgba(0,0,0,0.2);
    min-width: 200px;
}

/* Project List Container */
#projectListContainer { display: none; flex-direction: column; gap: 20px; }

/* Heading + Back Button */
.project-type-heading-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    margin-bottom: 20px;
}
.project-type-heading-wrapper h2 {
    font-size: 28px;
    font-weight: bold;
    text-align: center;
    line-height: 1.1;
    margin-bottom: 10px;
}
.back-button {
    position: absolute;
    left: 0;
    background-color: #EA6A2A;
    color: #fff;
    border: none;
    border-radius: 30px;
    padding: 8px 16px;
    cursor: pointer;
    display: none;
    font-size: 16px;
}
.back-button:hover{
	background-color: #2AAAEA;
}

/* Project Cards */
.project-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    justify-content: center;
    flex: 1 0 40%;
    flex-direction: column;
}
.project-card {
    background-color: white;
    border-radius: 12px;
    padding: 12px;
    font-weight: bold;
    width: 200px;
    text-align: center;
    cursor: pointer;
    box-shadow: 0 4px 10px rgba(0,0,0,0.15);
    transition: 0.3s ease-in-out;
}
.project-card:hover{
	background-color: #D58D54;
	color: #fff;
}
.project-card.active {
    background-color: #ccc;
    color: #000;
}
.project-table-section{
	flex: 1 0 55%;
}

/* Stats Table */
.project-stats table {
    margin: 0 auto;
    border-collapse: collapse;
}
.project-stats td {
    border: 1px solid #e99772;
    padding: 6px 12px;
}
.inner-project-cards{
	display: flex;
	gap: 30px;
	flex-wrap: wrap;
	align-items:center;	
}
.table-action{
	display: flex;
	justify-content: center;
	align-items:center;
	margin-top: 15px;
	display: none;
}
.table-action a:hover{
	background-color: #2AAAEA;
}
.box-content{
	display: flex;
	flex-direction: column;
	border: 1px solid #000;
	padding: 0 10px;
}
.box-inner-line{
	display: flex;
	border-bottom : 3px solid #EA6A2A;
	padding: 25px
}
.box-left{
	flex: 0 0 48%;
}
.box-inner-line:nth-last-child(1){
	border-bottom: 0;
}
.projectInnerTable thead tr{
	background-color: #EA6A2A;
	color: #fff;
}
.projectInnerTable thead tr th, .projectInnerTable tr td{
	padding: 10px;
	border-bottom: 2px solid #e99772;
}

.header-inline {
    display: flex;
    align-items: center;
    gap: 10px;
}

.header-text {
    font-size: 22px;
    font-weight: 600;
    white-space: nowrap;
}

.header-icon {
    height: 32px;
}


</style>

<div class="body-content">
    <div class="homepage">

        <!-- Header -->
        <div class="homepage-heading">
        	<button onclick="goBackToTypeMenu()" class="back-button">Back</button>
            <img src="/wrpmis/resources/images/wr-logo-small.png" alt="logo" id="main-hidden">
            <h1 id="mainHeaderTitle">Western Railways</h1> 
            <img src="/wrpmis/resources/images/world-map.svg" alt="logo" id="main-hidden2">
        </div>
<c:set var="seenProjects" value="|" />
<c:set var="totalLength" value="0" />
<c:set var="totalCommissionedLength" value="0" />
<c:set var="projectCount" value="0" />

<c:forEach var="p" items="${projects}">
    <c:set var="pid" value="|${p.project_id}|" />

    <c:if test="${!fn:contains(seenProjects, pid)}">

        <!-- mark project as seen -->
        <c:set var="seenProjects" value="${seenProjects}${pid}" />

        <!-- project count comes from DB -->
        <c:set var="projectCount" value="${p.project_count}" />

        <!-- add length once per project -->
        <c:set var="totalLength"
               value="${totalLength + (p.length != null ? p.length : 0)}" />

        <!-- add commissioned length once per project -->
        <c:set var="totalCommissionedLength"
               value="${totalCommissionedLength + (p.commissioned_length != null ? p.commissioned_length : 0)}" />

    </c:if>
</c:forEach>

        <!-- Dashboard cards -->
        <div class="dashboard-cards" id="dashboardCards">
<div class="card">
    <div style="font-size:28px; font-weight:bold;">
        ${projectCount}
    </div>
    <div>Projects</div>
</div>

<div class="card">
    <div id="totalLengthVal" style="font-size:28px; font-weight:bold;">
        0.00 km
    </div>
    <div>Length</div>
</div>
<div class="card">
    <div id="totalCommissionedVal" style="font-size:28px; font-weight:bold;">
        0.00 km
    </div>
    <div>Commissioned Length</div>
</div>
        </div>
        

        <!-- Project type menu -->
        <div id="projectTypeContainer" class="homepage-menu">
            <c:forEach var="projectType" items="${projectTypes}">
                <a href="javascript:void(0)" onclick="showProjectsByType('${projectType.project_type_id}','${projectType.project_type_name}')">
                    ${projectType.project_type_name}
                </a>
            </c:forEach>
        </div>

        <!-- Project List Container -->
        <div id="projectListContainer">
            <!-- Heading + Back Button -->

            
             <div class="inner-project-cards"> 
             	<!-- Project Cards -->
	            <div id="projectGrid" class="project-grid"></div>
	
	            <!-- Stats Table -->
	            <div class="project-table-section">
	                <div class="project-type-heading-wrapper">
              		  <h2 id="tableHeading">Overall</h2>
            		</div>
            		<div id="projectStats" class="project-stats"></div>
	            </div>

             	
             </div>
        </div>

        <!-- Hidden form for project overview -->
        <form action="<%=request.getContextPath()%>/project-overview" id="projectOverviewForm" method="post">
            <input type="hidden" id="project_type_id_overview" name="project_type_id">
        </form>

    </div>
</div>

<jsp:include page="./layout/footer.jsp"></jsp:include>

<script>
// JS logic unchanged

const defaultHeaderTitle = "Western Railways";

function setHeader(title) {
    document.getElementById("mainHeaderTitle").innerText = title;
}

var allProjects = [];
var allProjectsProjectTypes = [];

var projectStats = ${projectStatsJson != null ? projectStatsJson : '{}'}; // JSON from backend

let selectedProjectId = null;
let currentProjectTypeProjects = [];
let currentProjectTypeName = '';


<c:forEach var="proj" items="${projects}">
allProjects.push({
    structure_type     : '${fn:escapeXml(proj.structure_type)}',
    project_id         : '${fn:escapeXml(proj.project_id)}',
    project_name       : '${fn:escapeXml(proj.project_name)}',
    project_type_id    : '${fn:escapeXml(proj.project_type_id)}',
    project_type_name  : '${fn:escapeXml(proj.project_type_name)}',
    scope              : '${fn:escapeXml(proj.scope)}',
    completed          : '${fn:escapeXml(proj.completed)}',
});
</c:forEach>


<c:forEach var="proj1" items="${projectTypeswithProject}">
allProjectsProjectTypes.push({
    project_id        : '${fn:escapeXml(proj1.project_id)}',
    project_name      : '${fn:escapeXml(proj1.project_name)}',
    project_type_id   : '${fn:escapeXml(proj1.project_type_id)}',
    project_type_name : '${fn:escapeXml(proj1.project_type_name)}',
    length                : ${proj1.proposed_length != null ? proj1.proposed_length : 0},
    commissioned_length   : ${proj1.commissioned_length != null ? proj1.commissioned_length : 0}
    
});
</c:forEach>

console.log(allProjectsProjectTypes);


const seenProjects = new Set();
let totalLength = 0;

allProjects.forEach(p => {
    if (!seenProjects.has(p.projectId)) {
        seenProjects.add(p.projectId);
        totalLength += Number(p.length);
    }
});

let totalLength1 = 0;
let totalCommissionedLength1 = 0;

allProjectsProjectTypes.forEach(p => {
    totalLength1 += Number(p.length || 0);
    totalCommissionedLength1 += Number(p.commissioned_length || 0);
});



document.getElementById("totalLengthVal").innerText =totalLength1.toFixed(2) + " km";
document.getElementById("totalCommissionedVal").innerText =totalCommissionedLength1.toFixed(2) + " km";


function renderStats(projectArray, heading = 'All Projects') {

	const ORDER = [
	    'earthwork cutting',
	    'earthwork filling',
	    'blanketing',
	    'ballast',
	    'laying of sleepers',
	    'major bridge',
	    'minor bridge',
	    'rob',
	    'rub'
	];



	// build rows using ORDER.forEach(...)

    const grouped = {};

    projectArray.forEach(p => {
        if (!grouped[p.structure_type]) {
            grouped[p.structure_type] = {
                scope: 0,
                completed: 0,
                unit: getUnit(p.scope) || getUnit(p.completed)
            };
        }

        grouped[p.structure_type].scope += getNumber(p.scope);
        grouped[p.structure_type].completed += getNumber(p.completed);
    });

    document.getElementById('tableHeading').innerText =
        heading + ' Status of Major Items';

    let rows = '';

 const sortedTypes = Object.keys(grouped).sort((a, b) => {
     const ia = ORDER.indexOf(a.toLowerCase());
     const ib = ORDER.indexOf(b.toLowerCase());

     // unknown items go to bottom
     if (ia === -1 && ib === -1) return a.localeCompare(b);
     if (ia === -1) return 1;
     if (ib === -1) return -1;

     return ia - ib;
 });

 sortedTypes.forEach(type => {
     const g = grouped[type];
     rows +=
         '<tr>' +
             '<td>' + type + '</td>' +
             '<td>' + g.scope.toFixed(2) + ' ' + g.unit + '</td>' +
             '<td>' + g.completed.toFixed(2) + ' ' + g.unit + '</td>' +
         '</tr>';
 });


    // Render table
    document.getElementById('projectStats').innerHTML =
        '<table class="projectInnerTable">' +
            '<thead>' +
                '<tr>' +
                    '<th>Item</th>' +
                    '<th>Scope</th>' +
                    '<th>Completed</th>' +
                '</tr>' +
            '</thead>' +
            '<tbody>' +
                rows +
            '</tbody>' +
        '</table>'+
        '<div class="table-action"><a id="dashboardLink" href="#" class="btn btn-primary">View Dashboard</a></div>';

}


renderStats(allProjects, 'All Projects');

function getNumber(val) {
    if (!val) return 0;
    return parseFloat(val.replace(/[^0-9.]/g, '')) || 0;
}

function getUnit(val) {
    if (!val) return '';
    return val.replace(/[0-9.\s]/g, '');
}



function showProjectsByType(typeId, typeName) {

    currentProjectTypeProjects = allProjects.filter(p => p.project_type_id === typeId);
    currentProjectTypeName = typeName;
    selectedProjectId = null;

    document.getElementById('projectTypeContainer').style.display = 'none';
    document.getElementById('projectListContainer').style.display = 'flex';
    document.getElementById('dashboardCards').style.display = 'none';
    document.querySelector(".back-button").style.display = "block";
    document.querySelector("#main-hidden").style.display = "none";
    document.querySelector("#main-hidden2").style.display = "none";

    document.getElementById("mainHeaderTitle").innerHTML =
        '<div style="display:flex;align-items:center;gap:10px;white-space:nowrap;">' +
            '<img src="/wrpmis/resources/images/wr-logo-small.png" alt="logo">' +
            '<span style="font-size:22px;font-weight:600;">' +
                typeName + ' - Western Railways' +
            '</span>' +
            '<img src="/wrpmis/resources/images/world-map.svg" alt="logo">' +
        '</div>';

    renderStats(currentProjectTypeProjects, "Overall");
    document.getElementById("tableHeading").innerText =
        "Overall Status of Major Items";

    let html = '';
    const uniqueProjects = new Map();

    allProjectsProjectTypes.forEach(p => {
        if (String(p.project_type_id) === String(typeId)) {
            if (!uniqueProjects.has(p.project_id)) {
                uniqueProjects.set(p.project_id, p);
            }
        }
    });


    uniqueProjects.forEach(p => {
        html +=
            '<div class="project-card" data-id="' + p.project_id + '" ' +
            'onclick="handleProjectClick(this, \'' + p.project_id + '\', \'' +
            p.project_name.replace(/\'/g, "\\'") + '\')">' +
                p.project_name +
            '</div>';
    });

    document.getElementById('projectGrid').innerHTML = html;


    document.querySelector(".table-action").style.display = "none";
}



function handleProjectClick(card, projectId, projectName) {

    if (selectedProjectId === projectId) {

        document.querySelectorAll(".project-card")
            .forEach(c => c.classList.remove("active"));

        selectedProjectId = null;

        document.getElementById("tableHeading").innerText =
            "Overall Status of Major Items";

        renderStats(currentProjectTypeProjects, "Overall");

        const spn = document.getElementById("spnHide");
        if (spn) spn.style.display = "flex";

        document.querySelector(".table-action").style.display = "none";
        return;
    }

    // New project selection
    selectedProjectId = projectId;

    document.querySelectorAll(".project-card")
        .forEach(c => c.classList.remove("active"));
    card.classList.add("active");

    const projectRows = currentProjectTypeProjects.filter(p =>
        String(p.project_id).trim() === String(projectId).trim()
    );

    if (projectRows.length === 0) {
        console.warn("Project data not found:", projectId);

        // Clear table
        document.getElementById('projectStats').style.display="block";

        // Reset heading
        document.getElementById("tableHeading").innerText =
            projectName + " - No data available";
        
        document.getElementById('projectStats').innerHTML ='<div class="table-action" style="padding-left:150px;"><a id="dashboardLink" href="#" class="btn btn-primary">View Dashboard</a></div>';

        document.getElementById("dashboardLink").href ="/wrpmis/work-overview-dashboard/" + projectId;       

        // Hide dashboard button
        const action = document.querySelector(".table-action");
        if (action) action.style.display = "block";

        // Hide spn if needed
        const spn = document.getElementById("spnHide");
        if (spn) spn.style.display = "none";

        return;
    }


    document.getElementById("tableHeading").innerText =
        projectName + " Status of Major Items";

    renderStats(projectRows, projectName);

    const spn = document.getElementById("spnHide");
    if (spn) spn.style.display = "none";

    document.querySelector(".table-action").style.display = "flex";
    document.getElementById("dashboardLink").href =
        "/wrpmis/work-overview-dashboard/" + projectId;
}




function activateProjectCard(cardElement, projectId) {
    document.querySelectorAll(".project-card").forEach(c => c.classList.remove("active"));
    cardElement.classList.add("active");

    document.querySelector(".table-action").style.display = "flex";

    document.getElementById("dashboardLink").href =
        "/wrpmis/work-overview-dashboard/" + projectId;
}


function goBackToTypeMenu() {
    document.getElementById('projectTypeContainer').style.display = 'flex';
    document.getElementById('projectListContainer').style.display = 'none';
    document.getElementById('dashboardCards').style.display = 'flex';
    document.querySelector(".back-button").style.display = "none";
    document.querySelector("#main-hidden").style.display="block";
    document.querySelector("#main-hidden2").style.display="block";
    document.getElementById("mainHeaderTitle").innerText = defaultHeaderTitle;
    //document.getElementById("mainHeaderTitle").innerText = "Western Railways";
    selectedProjectId = null;
}
</script>
