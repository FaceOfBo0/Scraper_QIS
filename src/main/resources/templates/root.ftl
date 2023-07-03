<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            padding: 20px;
        }
        #urlForm {
            width: 500px;
            margin-top: 25px;
            margin-bottom: 25px;
        }
        #answer {
            background: blanchedalmond;
        }
        #semesterLbl {
            margin-right: 10px;
        }
        #semesterForm {
            margin-bottom: 45px;
        }
    </style>
    <title>LSF Wochenplan Editor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
          crossorigin="anonymous">
</head>

<body>
    <h1>LSF Wochenplan Editor</h1>
    <hr width="420px">
    <form id="urlForm">
        <p>LSF-Link Veranstaltungen:</p>
        <input value="https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100" type="text" id="answer" class="form-control" aria-label="inputLbl">
    </form>
    <form id="semesterForm">
        <label for="semesters" id="semesterLbl">Semester: </label>
        <select name="semesters" id="semesters">
            <option value="WiSe 23/24">WiSe 23/24</option>
            <option value="SoSe 23">SoSe 23</option>
            <option value="WiSe 22/23">WiSe 22/23</option>
        </select>
    </form>
    <button id="loadBtn" class="btn btn-primary">Plan laden</button>
<script>
    const loadBtn = document.getElementById("loadBtn");
    loadBtn.addEventListener("click", (e) => {

    });
</script>
</body>
</html>