<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录冰海客服系统</title>
    <link rel="stylesheet" href="../css/style.css">
    <script>
        <c:if test="${err != null}">
        alert("${err}");
        </c:if>
    </script>
</head>

<body>

<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<div class="cotn_principal">
    <div class="cont_centrar">
        <div class="cont_login">
            <div class="cont_info_log_sign_up">
                <div class="col_md_login">
                    <div class="cont_ba_opcitiy">
                        <h2>登录</h2>
                        <p>使用账户登录冰海客服系统.</p>
                        <button class="btn_login" onClick="cambiar_login()">登录</button>
                    </div>
                </div>
                <div class="col_md_sign_up">
                    <div class="cont_ba_opcitiy">
                        <h2>注册</h2>
                        <p>在冰海客服上注册一个新的账户.</p>
                        <button class="btn_sign_up" onClick="cambiar_sign_up()">注册</button>
                    </div>
                </div>
            </div>
            <div class="cont_back_info">
                <div class="cont_img_back_grey"><img src="../po.jpg" alt=""/></div>
            </div>

            <div class="cont_forms">
                <div class="cont_img_back_"><img src="../po.jpg" alt=""/></div>
                <form action="/auth/login.do" method="post">
                    <div class="cont_form_login"><a href="#" onClick="ocultar_login_sign_up()"><i
                            class="material-icons">
                        &#xE5C4;</i></a>
                        <h2>登录</h2>

                        <input type="text" name="email" placeholder="邮箱"/>
                        <input type="password" name="pass" placeholder="密码"/>
                        <button class="btn_login" onClick="javascript:this.form.submit()">登录</button>
                    </div>
                </form>
                <form action="/auth/reg.do" method="post">
                    <div class="cont_form_sign_up"><a href="#" onClick="ocultar_login_sign_up()"><i
                            class="material-icons">
                        &#xE5C4;</i></a>
                        <h2>注册</h2>
                        <input type="text" name="email" placeholder="邮箱"/>
                        <input type="text" name="name" placeholder="用户名"/>
                        <input type="password" name="pass" placeholder="密码"/>
                        <input type="password" name="confirm_pass" placeholder="重复密码"/>
                        <button class="btn_sign_up" onClick="javascript:this.form.submit()">注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="../js/index.js"></script>

</body>
</html>
