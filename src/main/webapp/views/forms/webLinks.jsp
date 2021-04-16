<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Web Links - Admin - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <style>
        .fixed-width {
            width: 100%;
            margin: 0 !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        .mdl-data-table {
            border: 1px solid #ddd;
        }

        .fw-40p {
            width: 40%;
            max-width: 40%;
        }

        .fw-15p {
            width: 15%;
            max-width: 15%;
        }

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }
    </style>

</head>

<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Web Links</h6>
                            </div>
                        </span>
                        
                        <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
						
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-web-links" id="webLinksForm" name="webLinksForm" method="post">
                        <div class="container container-no-margin" style="margin-top:20px;margin-bottom:20px">
                            <div class="row">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="web-links-table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="fw-40p">Name</th>
                                                        <th class="fw-40p">Link</th>
                                                        <th class="fw-15p">Priority</th>
                                                        <th class="no-sort">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:choose>
                                                		<c:when test="${not empty webLinksList and fn:length(webLinksList) gt 0 }">
                                                			<c:forEach var="obj" items="${webLinksList }" varStatus="index">
                                                				<tr id="webLinkRow${index.count }">
				                                                	<td>
				                                                		<input type="hidden" id="id" name= "ids" value="${obj.id }"/>
				                                                		<input type="text" id="name${index.count }" name="names" class="validate" placeholder = "Name" value="${obj.name }" />
				                                                	</td >
													                <td><textarea id="link${index.count }" name="links" class="materialize-textarea validate" placeholder="Link" >${obj.link }</textarea></td>
													                <td><input type="number" id="priority${index.count }" name="prioritys" class="validate" placeholder="Priority" value="${obj.priority }" /> </td>
													                <td><a onclick="removeWebLink('${index.count }');removableWebLinkIds('${obj.id }');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td> 
												                </tr>
                                                			</c:forEach>
                                                		</c:when>
                                                		<c:otherwise>
                                                			<tr id="webLinkRow0">
			                                                	<td>
				                                                	<input type="hidden" id="id" name= "ids" value=""/>
				                                                	<input type="text" id="name0" name="names" class="validate" placeholder = "Name" />
			                                                	</td >
												                <td><textarea id="link0" name="links" class="materialize-textarea validate" placeholder="Link" ></textarea></td>
												                <td><input type="number" id="priority0" name="prioritys" class="validate" placeholder="Priority" /> </td>
												                <td><a onclick="removeWebLink('0');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td> 
											                </tr>
                                                		</c:otherwise>
                                                	</c:choose>
                                                	
                                                    <tr>
                                                        <td colspan="4" class="center-align">
                                                            <a href="javascript:void(0);" class="btn waves-effect waves-light bg-m t-c "
                                                                onclick="addNewWebLinkRow()">
                                                                <i class="fa fa-plus"></i></a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            
                                            <c:choose>
		                                        <c:when test="${not empty webLinksList and fn:length(webLinksList) gt 0 }">
		                                    		<input type="hidden" id="webLinkRowNo"  name="webLinkRowNo" value="${fn:length(webLinksList) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="webLinkRowNo"  name="webLinkRowNo" value="0" />
		                                     	</c:otherwise>
		                                    </c:choose> 
											<input type="hidden" id="removableWebLinkIds"  name="removableWebLinkIds" value="" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col m1 hide-on-small-only"></div>
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <button type="submit"   class="btn waves-effect waves-light bg-m">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/web-links" class="btn waves-effect waves-light bg-s ">Reset</a>
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

    <!-- footer  -->
<jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>

    <script>
	    function removeWebLink(rowNo){
			$("#webLinkRow"+rowNo).remove();
		}
    	function removableWebLinkIds(removableWebLinkId){
    		var ids = $("#removableWebLinkIds").val();
    		var removableWebLinkIds = '';
    		if($.trim(ids) != '' && $.trim(removableWebLinkId)){
    			removableWebLinkIds = ids + "," + removableWebLinkId;
    		}else{
    			removableWebLinkIds = removableWebLinkId;
    		}
    		
    		$("#removableWebLinkIds").val(removableWebLinkIds);
    	}
        function addNewWebLinkRow() {
        	var rowNo = $("#webLinkRowNo").val();
		    var rNo = Number(rowNo)+1;
		    
            var text = '<tr>' 
            	+ '<td>'
            	+'<input type="hidden" id="id' + rNo + '" name= "ids" value=""/>'
            	+'<input id="name' + rNo + '" name="names" type="text" class="validate" placeholder = "Name" />'
            	+'</td > '
            	+'<td><textarea id="link' + rNo + '" name="links" class="materialize-textarea validate" placeholder="Link" ></textarea></td>' 
                +'<td><input id="priority' + rNo + '" name="prioritys" type="number" class="validate" placeholder="Priority" /> </td>' 
                +'<td><a onclick="removeWebLink('+ rNo +');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>' 
                +'</tr>';
            $('#web-links-table tbody').find('tr:last').prev().after(text);
            $("#webLinkRowNo").val(rNo);
        }


    </script>

</body>

</html>