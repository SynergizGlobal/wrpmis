<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tableau infoviz</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/pmis/resources/css/activity.css">
  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
  <style type="text/css">
  	.error{color:red;}
  	.timeline_body {
	    display: block;
	    margin: 0 auto;
	    border: none;
	    /*width: 1231px;*/
	    width: 100%;
	    height: 560px;
	}
	.card .card-content {
	    padding: 0px;
	    border-radius: 0 0 2px 2px;
	}
  </style>
</head>

<body>
  <!-- header  -->
  <jsp:include page="./layout/newHeader.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                   
                    	 <div class="row">
                              <div class="col s12 m12 input-field">
                              		<input type="hidden" id="resizeWidth" placeholder="Width">
								    <input type="hidden" id="resizeHeight" placeholder="Height">
								    <%-- <div class="col s12 m4 input-field"></div>
								    <div class="col s12 m4 input-field">
								    <div class="buttons" style="padding: 10px;">
								        <select id="changeWork" name="changeWork" onchange="workFilter(this.value);">
								        	<option value="">Work ID</option>
									        <c:forEach var="obj" items="${works}">
										   		<option value="${obj.workId}">${obj.workId} - ${obj.workName}</option>
										    </c:forEach>
								        </select>
								        
								        
								    </div>
								    </div>
								    <div class="col s12 m4 input-field">
								    	<button type="button" id="clearFilter" class="btn" style="background-color: #ffc898;">Clear filter</button>
								    </div> --%>
								    
								    <!-- <div class="col s12 m4 input-field">
								    	<button type="button" class="btn" style="background-color: #ffc898;float: right;" onClick="vizResize();">Resize</button>
								    </div> -->
								    
                              		<!-- <div class="buttons" style="padding: 10px;">
									  <button class="apply_RegionFilter btn" style="background-color: #a8dba2; ">1-MEL</button>
									  <button class="apply_RegionFilter btn" style="background-color: #f5a8a3; ">24-MM</button>
									  <button class="apply_RegionFilter btn" style="background-color: #afc8e3; ">3-CYR-N</button>
									  <button class="apply_RegionFilter btn" style="background-color: #d0bde0; ">4-GTR-S</button>
									  <button class="apply_RegionFilter btn" style="background-color: #ffc898; ">6-BCL-S</button>
									  <button class="apply_RegionFilter btn" style="background-color: #cdb2ac; ">AKEC</button>
									  <button class="selectAll_RegionFilter btn btn-outline">All</button>
									  
									  
									</div> -->
                              
                                 	<iframe frameborder="0" marginheight="0" marginwidth="0" title="data visualization" allowtransparency="true" allowfullscreen="true" class="timeline_body" src="${url.tableauUrl}" ></iframe>
			                		<%-- <c:if test="${not empty url.tableauUrl and fn:toLowerCase(url.tableauUrl) ne 'no'}">
		              					<div id="vizContainer" class="tableauViz timeline_body"></div>
			                		</c:if>
			                		<c:if test="${empty url.tableauUrl or fn:toLowerCase(url.tableauUrl) eq 'no'}">
		              					<div style="text-align: center;margin-top:100px;">No Dash board available right now.</div>
			                		</c:if> --%>
			                		
                              </div>
                          </div>
                  
                </div>
            </div>
        </div>
    </div>


  <!-- footer  -->
  <jsp:include page="./layout/newFooter.jsp"></jsp:include>
  
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script type="text/javascript" src="https://infoviz.syntrackpro.com/javascripts/api/tableau-2.min.js"></script>
  
  <script type="text/javascript">
     $(document).ready(function(){		
    	 
    	//$("#changeWork").formSelect(); 
    	
    	var globalWorkId = "${sessionScope.globalWorkId}";
    	
		//initViz(globalWorkId);	
		/* setTimeout(function() {
			vizResize();
        }, 5000);  */
	 });
     var viz,workbook,activeSheet;
     function initViz(globalWorkId) {
          var containerDiv = document.getElementById("vizContainer"),
          url = "${url.tableauUrl}",
          options = {
        	  "work_id":globalWorkId,
       		  hideTabs: true,    
       		  hideToolbar: false,  
       		  width: "1231px",
       		  height: "560px",
       		  onFirstInteractive: function () { 
       		     workbook = viz.getWorkbook();
	 			 activeSheet = workbook.getActiveSheet(); 
       		  }
          };
          viz = new tableauSoftware.Viz(containerDiv, url, options);      
          
          workFilter(globalWorkId);
      }
	  
	  function vizResize() {
          /* var width = document.getElementById("resizeWidth").value;
          var height = document.getElementById("resizeHeight").value;
		  viz.setFrameSize(parseInt(width, 10), parseInt(height, 10)); */
			
		  var w = $("#vizContainer").width();
		  var h = $( document ).height();	
			
		  $(".timeline_body").width(w);
		  $(".timeline_body").height(h);
			
		  $("#resizeWidth").val(w);
		  $("#resizeHeight").val(h-200);			
			
		  var width = $("#resizeWidth").val();
		  var height = $("#resizeHeight").val();
		  var sheet = viz.getWorkbook().getActiveSheet();

		  sheet.changeSizeAsync({"behavior": "EXACTLY", "maxSize": { "height": height, "width": width }})
		       .then(viz.setFrameSize(parseInt(width, 10), parseInt(height, 10)));
		  
		  //viz.setFrameSize(parseInt(width, 10), parseInt(height, 10));
     }
	  
	  
	  $("#clearFilter").click(function() {
          viz.revertAllAsync();
      });
	  
	  function workFilter(work) {
          
		  if($.trim(work) == ''){
        	  viz.revertAllAsync();
          }else{
	          //workbook = viz.getWorkbook().activateSheetAsync("Sheet 13 (2)")
	          var sheet = viz.getWorkbook().getActiveSheet();
	          
	          if(sheet.getSheetType() == "worksheet"){
	        	  sheet.applyFilterAsync("work_id", work,"REPLACE");
	          }else{
	        	  worksheetArray  = sheet.getWorksheets();
	        	  for(var i = 0 ; i < worksheetArray.length  ; i++){
	        		  //alert(worksheetArray[i].getName());
	        		  worksheetArray[i].applyFilterAsync("work_id", work,tableauSoftware.FilterUpdateType.REPLACE);
	        	  }
	          }
          }
      } 
	  
 </script>
</body>

</html>