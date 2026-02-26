<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Payment Details</title>
    <style>
    * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Verdana, sans-serif;
}

body {
    background-color: #f8f9fa;
}

header {
    background-color: #007b7f;
    color: white;
    text-align: center;
    padding: 15px;
    font-size: 14px;
}

.container {
    width: 100%;
    height: calc(100vh - 60px); /* Adjust 60px based on your header's height */
    background: white;
    padding: 30px;
    margin: 0 auto;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    overflow: auto; /* Prevents content overflow */
}

.form-group {
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
}

.form-group label {
    /* font-weight: bold; */
    margin-bottom: 5px;
}

.radio-group {
    display: flex;
    align-items: center;
    gap: 20px; /* Adjust the spacing between Yes and No */
    margin-top: 5px;
    flex-direction: row; /* Ensures horizontal alignment */
}

.radio-group label {
    display: flex;
    align-items: center;
    gap: 5px; /* Reduce the gap between the radio button and text */
    margin: 0;
    white-space: nowrap; /* Prevents wrapping */
}

.radio-group input {
    margin: 0;
    width: 16px; /* Adjust if needed */
    height: 16px;
}


.button-group {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
    margin-top: -50px;
    margin-right: 100px !important;
}

.btn {
    padding: 10px 15px;
    border: none;
    color: white;
    cursor: pointer;
    border-radius: 5px;
}

.btn:hover {
    opacity: 0.8;
}

.btn {
    background-color: #007bff;
}

.draft {
    background-color: #6c757d;
}

.highlight {
    color: #ff9900;
}

/* Grid Layout for Three Fields in One Row */
/* .row {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 15px;
    margin-bottom: 15px;
}

.row-four {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;
    margin-bottom: 15px;
} */

/* Form Row Layout */
.row, .row-four {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* Keeping all rows consistent */
    gap: 15px;
    margin-bottom: 15px;
}

.form-group input, 
.form-group select {
    padding: 5px 0px;
    width: 90%;
    border: none;
    border-bottom: 2px solid #ccc;
    border-radius: 5px;
    outline: none;
    background: transparent;
}

.form-group input:focus,
.form-group select:focus {
    border-bottom: 2px solid #4CAF50;
    /* Highlight color on focus */
}

/* Required field indicator */
.required {
    color: red;
}
    
    </style>
</head>

<body>
    <header>
        <h2>Add Payment Details</h2>
    </header>
    <div class="container">
        <form id="paymentForm">
            <!-- First Row (Three Fields in One Line) -->
            <div class="row">
                <div class="form-group">
                    <label>Contract No.<span class="required"> *</span></label>
                    <select id="contractNo" class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="0" ></option>
                    </select>
                </div>

                <div class="form-group">
                    <label>GeM Invoice No.<span class="required"> *</span></label>
                    <select id="invoiceNo" class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Bill No<span class="required"> *</span></label>
                    <input type="text" id="billNo">
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label>Vendor Code</label>
                    <input type="text">
                </div>

                <div class="form-group">
                    <label>Vendor Name</label>
                    <input type="text">
                </div>

                <div class="form-group">
                    <label>Payment Status</label>
                    <select id="status" class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="0">select Status</option>
                        <option>Pending</option>
                        <option>Success</option>
                        <option>Failed</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label>Amount Paid to Seller<span class="required"> *</span></label>
                    <input type="number" id="billAmountPaid">
                </div>

                <div class="form-group">
                    <label>Transaction/Payment Date</label>
                    <input type="date" id="transactionDate">
                </div>

                <div class="form-group">
                    <label>Payment Mode</label>
                    <select id="paymentBy" class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="0">Select Payment By </option>
                        <option value="RTGS">RTGS</option>
                        <option value="NEFT">NEFT</option>
                        <option value="Cheque">Cheque</option>
                        <option value="Other">Other</option>
                        <option value="Internet Banking">Internet Banking</option>
                    </select>
                </div>
            </div>

            <div class="row-four">
                <div class="form-group">
                    <label class="highlight">Cheque No.</label>
                    <input type="text" id="chequeNumber">
                </div>

                <div class="form-group">
                    <label class="highlight">Bank Transaction No.</label>
                    <input type="text" id="bankTransactionNo">
                </div>

                <div class="form-group">
                    <label class="highlight">Demand Draft No.</label>
                    <input type="text" id="demandDraftNo">
                </div>

                <div class="form-group">
                    <label>Bank Name</label>
                    <input type="text" id="bankName">
                </div>
                <div class="form-group">
                     <label>Invoice Number</label>
                    <input type="text" id="invoiceNumber">               
                </div>
                 <div class="form-group">
                     <label>Transaction ID</label>
                    <input type="text" id="transactionId">               
                </div>               
                
                
                
            </div>

            <div class="row">
                <div class="form-group">
                    <label>Deduction Need to Impose?</label>
                    <div class="radio-group">
                        <label><input type="radio" name="deduction" value="yes"> Yes</label>
                        <label><input type="radio" name="deduction" value="no"> No</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="highlight">Deduction Type</label>
                    <select id="deductionType" lass="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="LD">LD</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="highlight">Deduction Amount</label>
                    <input type="number" id="deductedAmount">
                </div>
            </div>

            <div class="row-four">
                <div class="form-group">
                    <label>Sanction No.</label>
                    <input type="text" id="sanctions">
                </div>

                <div class="form-group">
                    <label>Sanction Date</label>
                    <input type="date" id="sanctionDate">
                </div>

                <div class="form-group">
                    <label>Do you want to return bill to buyer?</label>
                    <div class="radio-group">
                        <label><input type="radio" name="return_bill" value="yes"> Yes</label>
                        <label><input type="radio" name="return_bill" value="no"> No</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="highlight">Return Reason</label>
                    <input type="text" id="returnReason">
                </div>
            </div>

            <div class="button-group">
                <button type="submit" class="btn">Submit</button>
            </div>
        </form>
    </div>
    
    
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
    
    
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>

<script>
var storeObject=[];
$(document).ready(function () {
    $.ajax({
        url: "http://203.153.40.44:8080/gemapiprod/api/get-bill-details",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "user": "MRVC",
            "method": "getbills",
            "from_date": "2020-01-01",
            "to_date": "2025-03-26"
        }),
        success: function (response) {

        	const encryptedData = response.data;
        	const rawKey = "Y1*AW91pAn1U$ss4"; // Plain-text key (not Base64)

        	// Convert plain key to WordArray using UTF-8
        	const key = CryptoJS.enc.Utf8.parse(rawKey);

        	// Parse the Base64 encrypted data into a WordArray
        	const ciphertext = CryptoJS.enc.Base64.parse(encryptedData);

        	// Decrypt using AES in ECB mode with PKCS7 padding
        	const decrypted = CryptoJS.AES.decrypt({ ciphertext: ciphertext }, key, {
        	    mode: CryptoJS.mode.ECB,
        	    padding: CryptoJS.pad.Pkcs7
        	});

        	// Convert decrypted WordArray to UTF-8 string
        	const decryptedText = decrypted.toString(CryptoJS.enc.Utf8);
        	console.log(decryptedText);
        	$(".page-loader").show();

        	try {
        	    const billData = JSON.parse(decryptedText);
        	    storeObject=billData;

        	    if (billData.data && Array.isArray(billData.data)) {
        	        const $dropdown = $('#contractNo');
        	        $dropdown.empty(); // clear existing options
        	        $dropdown.append('<option value="">-- Select Contract Number --</option>');

        	        billData.data.forEach(item => {
        	            if (item.supplyOrderNo) {
        	                $dropdown.append('<option value="' + item.supplyOrderNo + '">' + item.supplyOrderNo + '</option>');
        	            }
        	        });
        	        $(".page-loader").hide();
        	    } else {
        	        console.error("billData.data is not an array or is missing:", billData);
        	    }

        	} catch (err) {
        	    console.error("JSON parsing failed:", err);
        	}



        },
        error: function (xhr, status, error) {
            console.error("AJAX error:", error);
            alert("Error: " + xhr.status + " - " + xhr.statusText);
        }
    });
    
    $('#contractNo').on('change', function () {
        const selectedContract = $(this).val();
        const $invoiceDropdown = $('#invoiceNo');
        $invoiceDropdown.empty();
        $invoiceDropdown.append('<option value="">-- Select Invoice Number --</option>');

        if (selectedContract && storeObject.data && Array.isArray(storeObject.data)) {
            const filteredInvoices = storeObject.data.filter(item => item.supplyOrderNo === selectedContract);

            const uniqueInvoices = new Set();
            filteredInvoices.forEach(item => {
                if (item.gemInvoiceNo && !uniqueInvoices.has(item.gemInvoiceNo)) {
                    uniqueInvoices.add(item.gemInvoiceNo);
                    $invoiceDropdown.append('<option value="' + item.gemInvoiceNo + '">' + item.gemInvoiceNo + '</option>');
                }
            });
        }
    });
    
    $('#invoiceNo').on('change', function () {
        const selectedContract = $('#contractNo').val();
        const selectedInvoice = $(this).val();

        if (selectedContract && selectedInvoice && storeObject.data && Array.isArray(storeObject.data)) {
            const matched = storeObject.data.find(item =>
                item.supplyOrderNo === selectedContract && item.gemInvoiceNo === selectedInvoice
            );

            if (matched) {
                // Populate fields - assuming your form fields are in order
                $('input[type="text"]').eq(0).val(matched.billNo || '');             // Bill No
                $('input[type="text"]').eq(1).val(matched.vendorCode || '');         // Vendor Code
                $('input[type="text"]').eq(2).val(matched.vendorName || '');         // Vendor Name
                $('select').eq(2).val(matched.paymentStatus || '');                  // Payment Status
                $('input[type="number"]').eq(0).val(matched.amountPaid || '');       // Amount Paid
                $('input[type="date"]').eq(0).val(matched.invoiceDate || '');        // Payment Date
                $('select').eq(3).val(matched.paymentMode || '');                    // Payment Mode

                $('input[type="number"]').eq(1).val(matched.chequeNo || '');         // Cheque No
                $('input[type="number"]').eq(2).val(matched.bankTxnNo || '');        // Bank Txn No
                $('input[type="text"]').eq(3).val(matched.ddNo || '');               // Demand Draft No
                $('input[type="text"]').eq(4).val(matched.bankName || '');           // Bank Name
                
                $("#invoiceNumber").val(matched.invoiceNo);
                $("#transactionId").val(matched.transactionId);
                
                
                if (matched.deduction === 'yes') {
                    $('input[name="deduction"][value="yes"]').prop('checked', true);
                } else {
                    $('input[name="deduction"][value="no"]').prop('checked', true);
                }

                $('select').eq(4).val(matched.deductionType || '');
                $('input[type="number"]').eq(3).val(matched.deductionAmount || '');

                $('input[type="number"]').eq(4).val(matched.sanctionNo || '');
                $('input[type="date"]').eq(1).val(matched.sanctionDate || '');

                if (matched.returnBill === 'yes') {
                    $('input[name="return_bill"][value="yes"]').prop('checked', true);
                } else {
                    $('input[name="return_bill"][value="no"]').prop('checked', true);
                }

                $('input[type="text"]').eq(5).val(matched.returnReason || '');
            }
        }
    });
   
    document.getElementById("paymentForm").addEventListener("submit", function (e) {
    	  e.preventDefault();

    	  const requestBody = {
    	   // transactionID: document.getElementById("transactionID").value,
    	    transactionID:document.getElementById("transactionId").value,
    	    status: document.getElementById("status").value,
    	    paymentBy: document.getElementById("paymentBy").value,
    	    contractNo: document.getElementById("contractNo").value,
    	    gemInvoiceNo: document.getElementById("invoiceNo").value,
    	    invoiceNo: document.getElementById("invoiceNumber").value,
    	    billNo: document.getElementById("billNo").value,
    	    billAmountPaid: document.getElementById("billAmountPaid").value,
    	    transactionDate: document.getElementById("transactionDate").value,
    	    deductedAmount: document.getElementById("deductedAmount").value,
    	    deductionType: document.getElementById("deductionType").value,
    	    bankName: document.getElementById("bankName").value,
    	    chequeNumber: document.getElementById("chequeNumber").value,
    	    bankTransactionNo: document.getElementById("bankTransactionNo").value,
    	    demandDraftNo: document.getElementById("demandDraftNo").value,
    	    sanctions: document.getElementById("sanctions").value,
    	    sanctionDate: document.getElementById("sanctionDate").value
    	  };

    	  fetch("http://203.153.40.44:8080/gemapiprod/api/payment-status", {
    	    method: "POST",
    	    headers: {
    	      "Content-Type": "application/json"
    	    },
    	    body: JSON.stringify(requestBody)
    	  })
    	    .then(response => response.json())
    	    .then(data => {
    	      console.log("API Response:", data);
    	      alert("Payment status submitted successfully.");
    	      document.getElementById("paymentForm").reset();
    	      
    	    })
    	    .catch(error => {
    	      console.error("Error submitting payment status:", error);
    	      alert("There was an error submitting the payment.");
    	    });
    	});
});
</script>
</body>
</html>
