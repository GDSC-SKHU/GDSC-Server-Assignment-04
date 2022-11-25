<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>상세보기</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>

        div.container {
            width: 600px;
            margin: 50px auto;
        }

        table {
            width: 600px;
            height: 300px;
            border-collapse: collapse;
        }

        table td:nth-child(1) {
            background-color: #eee;
            width: 100px;
            text-align: center;
        }

        td {
            padding: 5px;
            border: 1px solid lightgray;
        }

        .back {
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
<div class="container"><h1> 🔎 책 들여다보기 </h1>
    <table>
        <tr>
            <td>책 명</td>
            <td>${ library.title }</td>
        </tr>
        <tr>
            <td>저자</td>
            <td>${ library.author }</td>
        </tr>
        <tr>
            <td>출판사</td>
            <td>${ library.publisher }</td>
        </tr>
        <tr>
            <td>줄거리</td>
            <td>${ library.summary }</td>
        </tr>
        <tr>
            <td>위치</td>
            <td>${ library.location }</td>
        </tr>
    </table>

    <a href="javascript:window.history.back()"><button class="back">목록으로</button></a>
</div>
</body>
</html>