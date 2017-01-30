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
    <h3>appKey:${user.appKey}</h3>
    <h3>secret:${user.secret}</h3>
    <span style="color:green">请求根路径:http://cservice.nanayun.cn/manage/</span><p>
    <h1 style="color:red">api说明</h1>
    <img style="width: 1159px;height: 439px" src="http://image.binghai.site/data/f_54597662.png">
    <img style="width: 1163px;height: 376px" src="http://image.binghai.site/data/f_10486066.png">
    <img style="width: 1164px;height: 113px" src="http://image.binghai.site/data/f_33170789.png">
    <h1 style="color:red">系统回调说明</h1>
    <img style="width: 430px;height: 240px" src="http://image.binghai.site/data/f_75538496.png">
    <h1 style="color:red">客服端链接拼接方法</h1>
    <h1 style="color:green">http://cservice.nanayun.cn/service/enterChat.do?ckey={会话秘钥}&uid={客服id}&cid={会话id}</h1>
    <h1 style="color:red">用户端链接拼接方法</h1>
    <h1 style="color:green">http://cservice.nanayun.cn/service/enterChat.do?ckey={会话秘钥}&uid={用户id}&cid={会话id}</h1>
	<!-- 多说评论框 start -->
	<div class="ds-thread" data-thread-key="1041414957" data-title="登录冰海客服系统" data-url="http://cservice.nanayun.cn/auth/main.do"></div>
<!-- 多说评论框 end -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"cservice"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
<!-- 多说公共JS代码 end -->
</div>

</body>
</html>