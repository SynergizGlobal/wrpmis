<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Reference Forms - Admin - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-form.css">
    <style>
        .hidden {
            display: none;
        }
        .row.no-mar{
        	margin-bottom:0;
        	box-shadow:1px 1px 10px 1px #777;
        }
        .position-sticky{
        	position:sticky;
        }
        .top-2rem{
        	top:2rem;
        }
       .theme-change {
		    position: fixed;
		    z-index: 2;
		    right: -20px;
		    top: 60px;
		    border: 0;
		    cursor: pointer;
		    font-size: 1.5rem;
		    transition: all 1s ease-in-out;
		}
        .module-holder {
            margin-top: .5rem;
            background-color:#f2f2f2;
        }
        iframe {
            box-sizing: border-box;
            /* make the border size be included in the height */
            display: block;
            /* make them block to fix white space margin */
            width: 100%;
            min-height:600px;
            height:auto;
        }
    </style>
</head>

<body>
<jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row no-mar position-sticky top-2rem">
        <div class="col s12">
            <div class="module-holder" id="module-holder">
              <c:forEach var="obj" items="${referenceForms}" varStatus="index">
              	 <%-- <c:set var = "classVal" value = "${fn:toLowerCase(obj.module_fk)}" /> --%>
               <div class="module default">
		           <!--  <div class="collapsible "> -->
	                    <div class="" onclick="showIframes('${obj.form_url }'); getReferencePagesList('${obj.module_fk }','${index.count }');">
	                    <input type="hidden" name="form_url" id="form_url${index.count }" value="${obj.form_url }"/>
	                        <!-- <div class="collapsible-header-icon">Default</div> -->
	                        ${obj.module_fk }  
	                    </div>
		            <!-- </div> -->
        		</div>  
                                          	
      		   </c:forEach>
                <!-- <div class="module">
            <ul class="collapsible default">
                <li>
                    <div class="collapsible-header">
                        <div class="collapsible-header-icon">Default</div>
                        First
                    </div>
                    <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                </li>
            </ul>
        </div> --> 
            </div>
        </div>

    </div>

    <div class="row" id="frame-holder" style="margin-bottom:50px">
    
	   <%--  <c:forEach var="obj" items="${referenceForms}" > --%>
	    	<%-- <div class="col s12 m6">
            	<iframe src="<%=request.getContextPath() %>/${obj.form_url }" title="" id="pageLoader" name="pageLoader" width="100%"  class="hidden frameportions" ></iframe>
        	</div>  --%>                            	
	   <%--  </c:forEach> --%>
        <!-- <div class="col s12 m6">
            <iframe src="training_status.html" title="" id="pageLoader" name="pageLoader" width="100%" height="600px"
                class="hidden frameportions"></iframe>
        </div>
        <div class="col s12 m6">
            <iframe src="rr_status.html" title="" id="pageLoader1" name="pageLoader1" width="100%" height="600px"
                class="hidden frameportions"></iframe>
        </div>
        <div class="col s12 m6">
            <iframe src="la_status.html" title="" id="pageLoader2" name="pageLoader2" width="100%" height="600px"
                class="hidden frameportions"></iframe>
        </div> -->
    </div>


    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script>
        // pages of individual groups as arrays
        var project_arr = ['rr_status.html', 'la_status.html'];
        var work_arr = ['training_status.html'];
        var contract_arr = ['contract-type', 'training-type', 'deliverable-type'];

        // documment on load
      /*   $(document).ready(function () {
            // show all groups
            var groups = ['default', 'project', 'work', 'contract', 'contractor'];
            var collapse_text = '';

            // dynamically generate groups
            $.each(groups, function (i, val) {
                collapse_text = collapse_text + '<div class="module"> <ul class="collapsible ' + val + '"> <li>' +
                    '<div class="collapsible-header" onclick="showIframes(' + val + '_arr)">' + val + '</div></div>'
            });
            $('#module-holder').append(collapse_text);
        });
 */
        // generate and show frames of a single group
        function showIframes(name) {
          /*   var frames_text = '';
            $.each(name, function (i, val) {
                frames_text = frames_text + '<div class="col s12 m6"> <iframe src="http://localhost/pmis/' + val + '" title="" id="pageLoader' + i + '" name="pageLoader' + i + '" width="100%" ' +
                    'class="hidden frameportions"  style="height:100%" onload="resizeIframe(this)"></iframe> </div>';
            });
            $('#frame-holder').html(' ');
            $('#frame-holder').html(frames_text);
 */
            // showing the frames
           // $('.frameportions').removeClass('hidden');
        }
       
        function getReferencePagesList(module_fk,count) {
        	//$(".page-loader").show();
        	$('div #frame').remove();
        	var form_url = "";
            if ($.trim(module_fk) != "") {
            	var myParams = { module_fk: module_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getReferencePagesList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                        	var i = data.length;
                            $.each(data, function (i, val) {
                            	var path = val.form_url;
    	                           $("#frame-holder").append('<div class="col s12 m6" id="frame"><iframe src="'+ path +'" title="" width="100%" id="" name="pageLoader" class=" frameportions" onload="resizeIframe(this,600)"></iframe></div>');
                            });
                        }
                       
                      //  $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			     // $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);  
    	   	     	  }
                });
                module_fk = "";
            }else{
            	 // $(".page-loader").hide();
            }
        } 

        function resizeIframe(obj,a) {
        	//obj.style.height=a;
        	//console.log(	$(obj).height(	$(obj).find('body')	)	)
        	var b=$(obj).find('html body');
        	console.log();
            //console.log('a '+a)
           // obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
        } 
      //This function is used to get error message for all ajax calls
        function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
        
    </script>
</body>

</html>