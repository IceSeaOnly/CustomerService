<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录冰海客服系统</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        <c:if test="${err != null}">
        alert("${err}");
        </c:if>
    </script>
</head>

<body>
<div align="center">
    <h1>appKey:${user.appKey}</h1>
    <h1>secret:${user.secret}</h1>
    <h1>api说明</h1>
    <img style="width: 1159px;height: 439px" src="http://image.binghai.site/data/f_54597662.png">
    <img style="width: 1159px;height: 372px" src="http://image.binghai.site/data/f_33195976.png">
    <h1>系统回调说明</h1>
    <img style="width: 430px;height: 240px" src="http://image.binghai.site/data/f_75538496.png">
</div>

</body>
</html>