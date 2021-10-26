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
    	@media (hover: hover) and (pointer: fine){
			.theme-change:hover {
			    transform: translateX(-20px);
			}		
		}
		.theme-change .fa-moon-o {
		    color: white;
		}
    </style>
</head>

<body>
<jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row no-mar position-sticky top-2rem">
        <div class="col s12">
            <div class="module-holder" id="module-holder">
              <c:forEach var="obj" items="${referenceForms}" varStatus="index">
               <div class="module default" selectActive='${obj.module_fk }'>
                    <div class="" onclick="getReferencePagesList('${obj.module_fk }');">
                    <input type="hidden" name="form_url" id="form_url${index.count }" value="${obj.form_url }"/>
                        <!-- <div class="collapsible-header-icon">Default</div> -->
                        ${obj.module_fk }  
                    </div>
        		</div>                                           	
      		   </c:forEach>
            </div>
        </div>

    </div>

    <div class="row" id="frame-holder" style="padding-bottom:.5rem">
    
	   <%--  <c:forEach var="obj" items="${referenceForms}" > --%>
	    	<%-- <div class="col s12 m6">
            	<iframe src="<%=request.getContextPath() %>/${obj.form_url }" title="" id="pageLoader" name="pageLoader" width="100%"  class="hidden frameportions" ></iframe>
        	</div>  --%>                           	
	   
    </div>


    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script>
	    
        function getReferencePagesList(module_fk) {
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
                            	console.log(val);
                            	console.log(path);
    	                           $("#frame-holder").append('<div class="col s12 m6" id="frame"><iframe src="'+ path +'" title="" width="100%" id="" name="pageLoader" class=" frameportions" onload="resizeIframe(this,600)"></iframe></div>');
                            });
                        }
                       
                      //  $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			     // $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);  
    	   	     	  }
                });
                /* active class addition code */
                $.each($('.module'), function( index, value ) {
                  $(value).removeClass('active');
                });
                $('[selectActive="'+module_fk+'"]').addClass('active');

                /* local storage setting code */
                localStorage.setItem("module_name", module_fk);
                module_fk = "";               
            }else{
            	 // $(".page-loader").hide();
            }
        } 
        $(document).ready(function () {
        	/* get the items from localstorage module */
        	if(localStorage.getItem("module_name")){
		        getReferencePagesList(localStorage.getItem("module_name"));    	    		
        	}else{      		
	        	getReferencePagesList('Contracts');    	
        	}
        });
        function resizeIframe(obj,a) {
        	//obj.style.height=a;
        	var b=$(obj).find('html body');
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