<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body { padding: 20px; }
        div.mb-3 {
            width: 500px;
            margin-top: 25px;
            margin-bottom: 25px;
        }
        span.spanClass{
            background-color: lightslategray;
            color: white;
        }
        button.btn {
            background-color: dodgerblue;
        }
        input.form-control { background-color: blanchedalmond; }
    </style>
    <title>LSF Wochenplan Editor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
          crossorigin="anonymous">
</head>

<body>
    <h1>LSF Wochenplan Editor</h1>
    <hr width="410px">
    <div class="input-group mb-3" id="urlForm">
        <div class="input-group-prepend">
            <span class="input-group-text spanClass">LSF-Link</span>
        </div>
        <input value="https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100" type="text" id="urlInp" class="form-control">
    </div>
    <div class="input-group mb-3" id="semesterForm">
        <div class="input-group-prepend">
            <span class="input-group-text spanClass">Semester</span>
        </div>
        <input value="2023.2" type="text" id="semesterInp" class="form-control">
        <div class="buttonForm input-group-append">
            <button id="loadBtn" class="btn btn-primary">Plan laden</button>
        </div>
    </div>
    <#if loaded == "1">
        <h2>Test</h2>
    </#if>
    <script>
        const loadBtn = document.getElementById("loadBtn");
        const urlVal = document.getElementById("urlInp").value.replaceAll("&","<amp>");
        const semesterVal = document.getElementById("semesterInp").value;
        loadBtn.addEventListener("click", () => {
            window.location.href = "/?load=1&url=" + urlVal + "&semester="+ semesterVal;
        });
    </script>
</body>
</html>