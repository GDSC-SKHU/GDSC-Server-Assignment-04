<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>bookList</title>
    <style>

        h1 {
            text-align: center;
        }

        .container {
            width: 600px;
            margin: 50px auto;
            padding: 50px;
            background-color: snow;
            border-radius: 5px;
        }

        .image {
            margin-left: 40px;
        }

        img {
            width: 150px;
            height: 200px;
            display: inline-block;
            padding: 10px;
        }

        img:hover {
            box-shadow: 4px 4px 2px gray;
        }

        .btn {
            padding: 0.4em 1em;
            border: 1px solid gray;
            border-radius: 0.5em;
            background: linear-gradient(#fff, #ddd);
            text-decoration: none;
            color: black;
            display: inline-block;
            float: right;
            margin-right: 20px;
            margin-top: 10px;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>📚DD._.Library📚</h1>
    <div class="image">
        <c:forEach var="library" items="${ libraries }">
            <tr data-url="detail?id=${ library.id }">
                <td><img src="${ library.id }.jpeg" alt="${ library.title }" onclick="redirect(${ library.id })"></td>
            </tr>
        </c:forEach>
    </div>
    <a href="signup" class="btn">희망도서</a>
    <a href="signList" class="btn">희망도서목록</a>
    <script>
        function redirect(i) {
            location.href = "detail?id=" + i;
        }
    </script>
</div>
</body>
</html>