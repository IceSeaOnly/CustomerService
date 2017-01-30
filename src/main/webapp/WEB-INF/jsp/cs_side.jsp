<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>客服系统</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="shortcut icon" href="/favicon.ico"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/chat_style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/jquery.mobile.flatui.css"/>
    <link rel="stylesheet" href="http://g.alicdn.com/msui/sm/0.6.2/css/sm.min.css"/>
    <link rel="stylesheet" href="http://g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css"/>
    <script>
        function submit() {

            var text = $("#content_input").val();
            $("#content_input").val('');
            if (text != "") {
                $("#talks").append('<li class="even"> <a class="user" href="#"><img class="img-responsive avatar_" src="http://image.binghai.site/data/f_84387849.png" alt="" /><span class="user-name">我</span></a>         <div class="reply-content-box"><span class="reply-time">刚刚</span><div class="reply-content pr"><span class="arrow">&nbsp;</span>' + text + '</div></div> </li> ');
            }
            $.closeModal();
            scroll_toEnd();

            submit_msg(text);

        }
        function submit_msg(t_msg) {
            $.post("/ajax/submit_msg.do",
                    {
                        cid:${cid},
                        uid:${uid},
                        token:'${token}',
                        ckey:'${ckey}',
                        msg: t_msg
                    },
                    function (data, status) {
                        if(data != "true")
                            $.alert(data);
                    });
        }
        function scroll_toEnd() {
            var H = $("#talks").get(0).offsetHeight;
            //alert(H);
            try {
                $("#talks_cnts").scrollTop(H);
            } catch (e) {
                alert(e.message)
            }
        }
        function getMsgList() {
            $.getJSON("/ajax/getMsg.do?cid=${cid}&uid=${uid}&ckey=${ckey}&token=${token}", function (data) {
                console.log(data);
                if (data.result == false) {
                    if(data.reason == "endingservice"){
                        $("#btn_to_submit").hide();
                        clearInterval(query_msg_list);
                        $.alert("${not empty conversation.rendTime?'服务已结束':'服务已结束，请填写服务结语'}");
                    }else if(data.reason == "serviceended"){
                        $("#btn_to_end").hide();
                        $("#btn_to_submit").hide();
                        clearInterval(query_msg_list);
                        $.alert("服务已结束，感谢使用");
                    }else{
                        $.alert(data.reason);
                    }
                } else {
                    var nHtml = '';
                    $.each(data.msgs, function (i, item) {
                        if (item.lR == 0) {
                            nHtml += '<li class="odd"> <a class="user" href="#"><img class="img-responsive avatar_" src="http://image.binghai.site/data/f_63936085.png" alt="" /><span class="user-name">客户</span></a>         <div class="reply-content-box"><span class="reply-time">' + item.timetext + '</span><div class="reply-content pr"><span class="arrow">&nbsp;</span>' + item.msg + '</div></div> </li> ';
                        } else {
                            nHtml += '<li class="even"> <a class="user" href="#"><img class="img-responsive avatar_" src="http://image.binghai.site/data/f_84387849.png" alt="" /><span class="user-name">我</span></a>         <div class="reply-content-box"><span class="reply-time">' + item.timetext + '</span><div class="reply-content pr"><span class="arrow">&nbsp;</span>' + item.msg + '</div></div> </li> ';
                        }
                    });
                    try{
                        $("#talks").html(nHtml);
                    }catch (e){alert(e.message);}

                }
            });
            scroll_toEnd();
        }
    </script>
</head>
<body>
<div class="page-group">
    <div class="page page-current" id="main_page">
        <!-- 你的html代码 -->
        <header class="bar bar-nav">
            <h1 class="title" id="icesea_t">客服端</h1>
        </header>
        <nav class="bar bar-tab">
            <c:if test="${empty conversation.rendTime}">
                <a id="btn_to_end" class="tab-item active" href="javascript:$.popup('.popup-endchat');"> <span class="icon icon-emoji"></span> <span
                        class="tab-label">结束服务</span> </a>
            </c:if>
            <c:if test="${conversation.status < 2}">
                <a id="btn_to_submit" class="tab-item active" href="javascript:$.popup('.popup-input');"> <span class="icon icon-edit"></span>
                    <span class="tab-label">回复客户</span> </a>
            </c:if>
            <c:if test="${not empty conversation.rendTime}">
                <a class="tab-item active"> <span class="icon icon-emoji"></span> <span
                        class="tab-label">结束已服务</span> </a>
            </c:if>
        </nav>
        <!-- 这里是页面内容区 begin-->
        <div class="content" id="talks_cnts">
            <div data-role="content" class="container" role="main">
                <ul class="content-reply-box mg10" id="talks">
                    <c:forEach items="${msgs}" var="msg">
                        <c:if test="${msg.LR == 0}">
                            <li class="odd"><a class="user" href="#"><img class="img-responsive avatar_"
                                                                          src="http://image.binghai.site/data/f_63936085.png"
                                                                          alt=""/><span class="user-name">客户</span></a>
                                <div class="reply-content-box">
                                    <span class="reply-time">${msg.timetext}</span>
                                    <div class="reply-content pr">
                                        <span class="arrow">&nbsp;</span> ${msg.msg}
                                    </div>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${msg.LR == 1}">
                            <li class="even"><a class="user" href="#"><img class="img-responsive avatar_"
                                                                           src="http://image.binghai.site/data/f_84387849.png"
                                                                           alt=""/><span class="user-name">我</span></a>
                                <div class="reply-content-box">
                                    <span class="reply-time">${msg.timetext}</span>
                                    <div class="reply-content pr">
                                        <span class="arrow">&nbsp;</span> ${msg.msg}
                                    </div>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!-- 这里是页面内容区 end-->
        <!-- 你的html代码 -->
    </div>
</div>

<!-- insert Popup -->
<div class="popup popup-input">
    <div class="content-block">
        <div class="list-block">
            <ul>
                <li class="align-top">
                    <div class="item-content">
                        <div class="item-inner">
                            <div class="item-input">
                                <textarea id="content_input"></textarea>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <p><a href="javascript:submit()" class="button button-big button-fill button-success">回复消息</a></p>
        <p><a href="javascript:$.closeModal();" class="button button-big">算了</a></p>
    </div>
</div>
<!-- end Popup -->
<div class="popup popup-endchat">
    <form action="/service/rend.do" method="post" id="end_form">
        <input name="uid" value="${uid}" type="hidden"/>
        <input name="cid" value="${cid}" type="hidden"/>
        <input name="ckey" value="${ckey}" type="hidden"/>
        <input name="token" value="${token}" type="hidden"/>
        <div class="content-block">
            <div class="list-block">
                <ul>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-input">
                                    <textarea placeholder="添加服务结语" name="endText"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <p><a href="javascript:$('#end_form').submit()" class="button button-big button-fill button-success">结束服务</a></p>
        </div>
    </form>
</div>

<script type="text/javascript" src="http://g.alicdn.com/sj/lib/zepto/zepto.min.js" charset="utf-8"></script>
<script type="text/javascript" src="http://g.alicdn.com/msui/sm/0.6.2/js/sm.min.js" charset="utf-8"></script>
<script type="text/javascript" src="http://g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js" charset="utf-8"></script>
<script>
    $.init();
    try {
        var query_msg_list = setInterval(getMsgList, 6000);
    } catch (e) {
        $.toast(e.message);
    }
    scroll_toEnd();
</script>
</body>
</html>
