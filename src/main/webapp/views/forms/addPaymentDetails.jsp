<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Project - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Project - Update Forms - PMIS</c:if>    
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    
	<style>
		body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

.container {
    max-width: 900px;
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    margin: auto;
}

h2 {
    text-align: center;
    color: #005f5f;
}

.form-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 15px;
    margin-bottom: 20px;
}

.form-group {
    display: flex;
    flex-direction: column;
}

.form-group label {
    font-weight: bold;
    margin-bottom: 5px;
}

.form-group input,
.form-group select {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.radio-group {
    display: flex;
    align-items: center;
    gap: 10px;
}

.radio-group input {
    margin: 0;
}

.button-group {
    text-align: center;
}

.button-group button {
    padding: 10px 15px;
    margin: 5px;
    border: none;
    cursor: pointer;
    font-size: 16px;
    border-radius: 5px;
}

.submit {
    background-color: #00796b;
    color: white;
}

.draft {
    background-color: #37474f;
    color: white;
}

/* Responsive Design */
@media (max-width: 768px) {
    .form-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

@media (max-width: 500px) {
    .form-grid {
        grid-template-columns: 1fr;
    }
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
                            <div class="center-align p-2 bg-m m-b-2">
                               		<h6>Add Payment Details</h6>

                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
			              
			                	        <form>
            <div class="form-grid">
                <div class="form-group">
                    <label for="contract_no">Contract No *</label>
                    <select id="contract_no"></select>
                </div>
                <div class="form-group">
                    <label for="gem_invoice">GeM Invoice No *</label>
                    <select id="gem_invoice"></select>
                </div>
                <div class="form-group">
                    <label for="bill_no">Bill No *</label>
                    <input type="text" id="bill_no" readonly>
                </div>
                
                <div class="form-group">
                    <label for="vendor_code">Vendor Code</label>
                    <input type="text" id="vendor_code" readonly>
                </div>
                <div class="form-group">
                    <label for="vendor_name">Vendor Name</label>
                    <input type="text" id="vendor_name" readonly>
                </div>
                <div class="form-group">
                    <label for="payment_status">Payment Status</label>
                    <select id="payment_status">
                        <option>Pending</option>
                        <option>Success</option>
                        <option>Failed</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="amount_paid">Amount Paid to Seller *</label>
                    <input type="text" id="amount_paid" readonly>
                </div>
                <div class="form-group">
                    <label for="payment_date">Transaction/Payment Date</label>
                    <input type="date" id="payment_date">
                </div>
                <div class="form-group">
                    <label for="payment_mode">Payment Mode</label>
                    <select id="payment_mode">
                        <option>RTGS</option>
                        <option>NEFT</option>
                        <option>Cheque</option>
                        <option>Other</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="cheque_no">Cheque No.</label>
                    <input type="number" id="cheque_no">
                </div>
                <div class="form-group">
                    <label for="bank_txn">Bank Transaction No.</label>
                    <input type="number" id="bank_txn">
                </div>
                <div class="form-group">
                    <label for="demand_draft">Demand Draft No.</label>
                    <input type="text" id="demand_draft">
                </div>

                <div class="form-group">
                    <label for="bank_name">Bank Name</label>
                    <input type="text" id="bank_name">
                </div>
                <div class="form-group">
                    <label>Deduction Need to Impose?</label>
                    <div class="radio-group">
                        <input type="radio" id="deduction_yes" name="deduction" value="yes">
                        <label for="deduction_yes">Yes</label>
                        <input type="radio" id="deduction_no" name="deduction" value="no" checked>
                        <label for="deduction_no">No</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="deduction_type">Deduction Type</label>
                    <select id="deduction_type"></select>
                </div>

                <div class="form-group">
                    <label for="sanction_no">Sanction No.</label>
                    <input type="number" id="sanction_no">
                </div>
                <div class="form-group">
                    <label for="sanction_date">Sanction Date</label>
                    <input type="date" id="sanction_date">
                </div>
                <div class="form-group">
                    <label for="deduction_amount">Deduction Amount</label>
                    <input type="number" id="deduction_amount">
                </div>

                <div class="form-group">
                    <label>Return Bill to Buyer?</label>
                    <div class="radio-group">
                        <input type="radio" id="return_yes" name="return_bill" value="yes">
                        <label for="return_yes">Yes</label>
                        <input type="radio" id="return_no" name="return_bill" value="no" checked>
                        <label for="return_no">No</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="return_reason">Return Reason</label>
                    <input type="text" id="return_reason">
                </div>
            </div>

            <div class="button-group">
                <button type="submit" class="submit">Submit</button>
                <button type="button" class="draft">Save as Draft</button>
            </div>
        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>


  	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
    	var authData = {
    		    user: "DEMO",
    		    pass: "f90903562fe1ff60154f46c12b8d8a68",
    		    method: "login"
    		};

    		$.ajax({
    		    url: "https://api.gemorion.org/erp/webs/",
    		    type: "POST",
    		    contentType: "application/json",
    		    dataType: "json",
    		    data: JSON.stringify(authData),
    		    success: function(response) {
    		        // Assuming the token is returned in response.token
    		        var token = response.token;
    		        alert("Authentication successful. Token: " + token);
    		    },
    		    error: function(xhr, status, error) {
    		        console.error("Authentication failed:", error);
    		    }
    		});
    });

    // Function to fetch order summary
    function fetchOrderSummary(token) {
        $.ajax({
            url: "https://api.gemorion.org/erp/webs/order-summary",
            type: "GET", // or "POST" depending on the API specification
            contentType: "application/json",
            dataType: "json",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                alert("Order Summary:", response);
                // Process the order summary data as needed
            },
            error: function(xhr, status, error) {
                console.error("Failed to fetch order summary:", error);
            }
        });
    }
</script>   	            	        
    
</body>

</html>