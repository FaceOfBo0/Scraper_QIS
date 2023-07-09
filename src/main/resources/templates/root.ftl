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
            background-color: #33475b;
            color: whitesmoke;
            border-color: #33475b;
        }
        input.form-control {
            border-color: #33475b;
            color: whitesmoke;
            background-color: #2B2A33;
        }
    </style>
    <title>LSF Wochenplan Editor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
</head>

<body style="background-color: #2B2A33; color: whitesmoke">
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
            <button id="loadBtn" class="btn btn-primary" style="color: whitesmoke">Plan laden</button>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
            integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
            crossorigin="anonymous"></script>
</body>
</html>