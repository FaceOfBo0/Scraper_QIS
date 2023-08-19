<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body { padding: 20px; }
        table {
            border-collapse: collapse; /* This ensures that adjacent borders are combined into a single border */
            border: 1px solid black; /* The border style and color */
        }
        th, td {
            border: 1px solid black; /* The border style and color for table header and data cells */
            padding: 8px; /* Optional padding for cells to make the table visually appealing */
        }
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

<table>
    <tr>
        <th>Tag</th>
        <th>Zeit</th>
        <th>Veranstaltung</th>
        <th>Lehrende</th>
        <th>Raum</th>
    </tr>
    <#list lecturesList as lecture>
        <tr>
            <td>${lecture.getDay()}</td>
            <td>${lecture.getTime()}</td>
            <td>${lecture.getTitle()}</td>
            <td>${lecture.getLecturers()}</td>
            <td>${lecture.getRoom()}</td>
        </tr>
    </#list>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
</body>
</html>