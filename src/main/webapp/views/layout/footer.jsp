</div> <!-- closes page-container -->

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Hidden field to store current context path -->
<input type="hidden" id="contextPath" value="${context_path}"> <!-- this comes from your controller -->

<div class="footer">
    
    <div class="ft-copyright">
        © <span id="currentYear"></span> @ WR 
        <img src="/wrpmis/resources/images/wr-logo.png" alt="logo" width="25" height="25">
    </div>

    <div class="designed-name">
        Designed & Developed by 
        <img src="/wrpmis/resources/images/syg-logo.png" alt="SYG Logo">
    </div>

    <div class="server-type">
        <!-- Dropdown only shown if JS sets it -->
        <span style="width: 15%;" id="envDropdownContainer">
            <select class="browser-default fm-none" id="envSelect" onchange="switchEnvironment(this.value)">
                <option value="/wrpmis/">Production</option>
                <option value="/wrpmis_qa/">Testing</option>
            </select>
        </span>
    </div>
</div>

<!-- Form for environment switch -->
<form id="switchEnv" name="switchEnv" method="post">
    <input type="hidden" name="user_id" value="${sessionScope.user.user_id}" />
    <input type="hidden" name="password" value="${sessionScope.user.decrypted_password}" />
    <input type="hidden" name="current_url" id="current_url" />
</form>

<script>
/* ---------- Set Current Year ---------- */
document.getElementById("currentYear").innerHTML = new Date().getFullYear();

/* ---------- Show Environment Dropdown only if test env is enabled ---------- */
document.addEventListener("DOMContentLoaded", function () {

    var envSelect = document.getElementById("envSelect");

    // If test environment is enabled, show dropdown
    var isTestEnv = "${sessionScope.IS_TEST_ENV_ENABLED}" === "true";
    if (!isTestEnv) {
        document.getElementById("envDropdownContainer").style.display = "none";
    } else {
        // select option based on current URL path
        if (envSelect) {
            var currentPath = window.location.pathname; // e.g., /wrpmis_qa/dashboard/home
            for (var i = 0; i < envSelect.options.length; i++) {
                if (currentPath.startsWith(envSelect.options[i].value)) {
                    envSelect.options[i].selected = true;
                    break;
                }
            }
        }
    }

    /* ---------- Auto-adjust pmis-textarea heights ---------- */
    document.querySelectorAll('.pmis-textarea:not(.textarea-no-height)').forEach(function (item) {
        item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';

        item.addEventListener('keyup', function () {
            item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
        });
        item.addEventListener('change', function () {
            item.style.height = 0;
            item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
        });
    });
});


/* ---------- Switch Environment Function ---------- */
function switchEnvironment(val) {
    var current_url = $(location).attr('href');
    current_url = current_url.replace("/wrpmis/", val);
    current_url = current_url.replace("/wrpmis_qa/", val);
    $("#current_url").val(current_url);

    $("#switchEnv").attr("action",
        $(location).attr('protocol') + "//" +
        $(location).attr('host') + val + "login"
    );
    $("#switchEnv").submit();
}
</script>

</body>
</html>
