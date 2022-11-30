<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>희망도서조회</title>
    <style>

        div.container {
            width: 600px;
            margin: 50px auto;
        }

        table {
            width: 600px;
            height: 300px;
            border-collapse: collapse;
            text-align: center;
        }

        th {
            background-color: #eee;
            width: 100px;
            height: 30px;
            text-align: center;
            border-collapse: collapse;
            border: 1px solid lightgray;
        }

        td {
            padding: 5px;
            border: 1px solid lightgray;
        }

        .btn {
            float: right;
            margin-top: 20px;
            padding: 0.4em 1em;
            border: 1px solid gray;
            border-radius: 0.5em;
            background: linear-gradient(#fff, #ddd);
            text-decoration: none;
            color: black;
            display: inline-block;

        }

    </style>
</head>
<body>
<div class="container">
    <h1>🌟희망 도서 목록 🌟</h1>
    <table class="table table-bordered table-condensed">
        <thead>
        <tr>
            <th>제목</th>
            <th>저자</th>
            <th>출판사</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hope" items="${ hopes }">
            <tr>
                <td>${ hope.title }</td>
                <td>${ hope.author}</td>
                <td>${ hope.publisher }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="list"/>
    <butoon class="btn">목록으로</butoon>
</div>
</body>
</html>
