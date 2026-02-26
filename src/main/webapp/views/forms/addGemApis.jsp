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
                    <select class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                        <option>001</option>
                        <option>002</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>GeM Invoice No.<span class="required"> *</span></label>
                    <select class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                        <option>INV-001</option>
                        <option>INV-002</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Bill No<span class="required"> *</span></label>
                    <input type="text">
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
                    <select class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                        <option>Pending</option>
                        <option>Success</option>
                        <option>Failed</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label>Amount Paid to Seller<span class="required"> *</span></label>
                    <input type="number">
                </div>

                <div class="form-group">
                    <label>Transaction/Payment Date</label>
                    <input type="date">
                </div>

                <div class="form-group">
                    <label>Payment Mode</label>
                    <select class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                        <option>RTGS</option>
                        <option>NEFT</option>
                        <option>Cheque</option>
                        <option>Other</option>
                    </select>
                </div>
            </div>

            <div class="row-four">
                <div class="form-group">
                    <label class="highlight">Cheque No.</label>
                    <input type="number">
                </div>

                <div class="form-group">
                    <label class="highlight">Bank Transaction No.</label>
                    <input type="number">
                </div>

                <div class="form-group">
                    <label class="highlight">Demand Draft No.</label>
                    <input type="text">
                </div>

                <div class="form-group">
                    <label>Bank Name</label>
                    <input type="text">
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
                    <select class="searchable validate-dropdown no-z select2-hidden-accessible">
                        <option value="select" hidden> </option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="highlight">Deduction Amount</label>
                    <input type="number">
                </div>
            </div>

            <div class="row-four">
                <div class="form-group">
                    <label>Sanction No.</label>
                    <input type="number">
                </div>

                <div class="form-group">
                    <label>Sanction Date</label>
                    <input type="date">
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
                    <input type="text">
                </div>
            </div>

            <div class="button-group">
                <button type="submit" class="btn">Submit</button>
                <button type="button" class="btn draft">Save as Draft</button>
            </div>
        </form>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>

<script>
$(document).ready(function () {
    $.ajax({
        url: "http://203.153.40.44:8080/gemapi/api/get-bill-details",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "user": "DEMO",
            "method": "getbills",
            "from_date": "2020-01-01",
            "to_date": "2025-03-26"
        }),
        success: function (response) {

        	const encryptedData = response.data;
        	const rawKey = "qcNv4GRqj40WdqhY"; // Plain-text key (not Base64)

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

        	// Optional: parse JSON
        	try {
        	    const jsonData = JSON.parse(decryptedText);
        	    console.log(JSON.stringify(jsonData, null, 2));
        	} catch (err) {
        	    console.error("JSON parsing failed:", err);
        	}

        },
        error: function (xhr, status, error) {
            console.error("AJAX error:", error);
            alert("Error: " + xhr.status + " - " + xhr.statusText);
        }
    });
});
</script>
</body>
</html>
