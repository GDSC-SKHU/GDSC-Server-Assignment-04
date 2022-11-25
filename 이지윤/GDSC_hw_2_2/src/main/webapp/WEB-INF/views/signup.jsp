<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>희망 도서</title>
    <style>
        .btn {
            padding: 0.4em 1em;
            border: 1px solid gray;
            border-radius: 0.5em;
            background: linear-gradient(#fff, #ddd);
            text-decoration: none;
            color: black;
            display: inline-block;
        }

        button {
            margin: 5px 0 20px 20px;
        }

        div.container {
            width: 600px;
            margin: 50px auto;
            font-size: 10pt;
        }

        form {
            width: 600px;
            margin: auto;
            box-shadow: 0 0 4px lightgray, 2px 2px 4px gray;
            overflow: auto;
        }

        div.want {
            font-size: 20pt;
            padding: 10px;
            background-color: #eee;
        }

        table td {
            padding: 4px;
        }

    </style>
</head>
<body>
<div class="container">
    <form:form method="post" modelAttribute="hope">
        <div class="want">도서 등록</div>
        <table>
            <tr>
                <td>책 명:</td>
                <td><form:input path="title"/></td>
            </tr>
            <tr>
                <td>저 자:</td>
                <td><form:input path="author"/></td>
            </tr>

            <tr>
                <td>출판사:</td>
                <td><form:input path="publisher"/></td>
            </tr>
        </table>
        <hr>
        <div>
            <button type="submit" class="btn">등록</button>
            <a href="list" class="btn">취소</a>
        </div>
    </form:form>
</div>
</body>
</html>