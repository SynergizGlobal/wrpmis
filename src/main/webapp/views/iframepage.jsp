<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="./layout/header.jsp"></jsp:include>

<style>
  .iframe-container {
    margin: 30px auto;
    width: 95%;
    height: 700px;
    border: 2px solid #ccc;
    border-radius: 10px;
    overflow: hidden;
  }
  iframe {
    width: 100%;
    height: 100%;
    border: none;
  }
  .dashboard-heading {
    text-align: center;
    margin-top: 20px;
    font-size: 24px;
    font-weight: bold;
  }
</style>

<div class="body-content">
  <div class="dashboard-heading">
    Dashboard: <c:out value="${param.module}" />
  </div>
  <div class="iframe-container">
    <iframe id="dashboardIframe"></iframe>
  </div>
</div>

<jsp:include page="./layout/footer.jsp"></jsp:include>

<script>
  const dashboardUrls = {
    "Fortnight Meeting": "http://203.153.40.44:8000/views/MonthwiseProgress/Dashboard1?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Drawing Status": "http://203.153.40.44:8000/views/HinderanceTable/IssueOpen?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Project Performance Appraisal": "http://203.153.40.44:8000/views/DailyProgress/DailyProgressoverview?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Timeline Schedule module": "http://203.153.40.44:8000/views/RollingStock-BidProcess/PrototypeRakeApprovalO?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "BG & Insurance Dashboard": "http://203.153.40.44:8000/views/MUTPIIIDisbursmentvsActual/AIIBTargetVsActual?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Usage Analysis Dashboard": "http://203.153.40.44:8000/views/ContractsStatus/ContractStatus?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Contract Status": "http://203.153.40.44:8000/views/TenderFNDashboard/TenderStatus?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Contractwise Physical Progress": "http://203.153.40.44:8000/views/Train_sets_delievery/Dashboard1?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Component Wise Progress": "http://203.153.40.44:8000/views/RollingStock-BidProcess/DeliveryofSeriesRakesO?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Progress Table": "http://203.153.40.44:8000/views/Structure_wise_shortfall/StructurewiseShortfall?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "DPR Updation & Validation Status": "http://203.153.40.44:8000/views/RollingStock-BidProcess/BidProcessO?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "TDC Projections": "http://203.153.40.44:8000/views/RollingStock-BidProcess/1_DesignPrototypeRakeDeliveryO?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Issues": "http://203.153.40.44:8000/views/UtilityShiftingStripchartDashboard/UtilityShiftingStripChart?:showAppBanner=false&:display_count=n&:showVizHome=n",
    "Structure wise Shortfall": "http://203.153.40.44:8000/views/Structure_wise_shortfall/StructurewiseShortfall?:showAppBanner=false&:display_count=n&:showVizHome=n"
  };

  const moduleParam = decodeURIComponent(new URLSearchParams(window.location.search).get("module"));
  const iframe = document.getElementById("dashboardIframe");

  if (moduleParam && dashboardUrls[moduleParam]) {
    iframe.src = dashboardUrls[moduleParam];
  } else {
    iframe.outerHTML = "<p style='text-align:center; color:red; font-weight:bold;'>Dashboard not found for module: " + moduleParam + "</p>";
  }
</script>
