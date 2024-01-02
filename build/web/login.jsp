<%-- 
    Document   : login_page
    Created on : 31 de dez. de 2023, 14:28:56
    Author     : Heraldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="page_login/css/style.css" type="text/css">
        
        <title>Otica Nova Login </title>
    </head>
    <body>
        <main id="container">
            <form id="login_form" action="gerenciarLogin" method="POST">
                <div id="form_header">
                    <h1>Login</h1>
                    <i id="mode_icon" class="fa-solid fa-moon"></i>
                </div>

                <div id="social_midia">
                    <a href="#"> <img src="page_login/imgs/facebook.png" alt="facebook-logo"></a>
                    <a href="#"> <img src="page_login/imgs/github.png" alt="github-logo"></a>
                    <a href="#"> <img src="page_login/imgs/google.png" alt="google-logo"></a>
                </div>

                <div id="input">
                    <div class="input-box">
                        <label for="name"> Usuário
                            <div class="input-field">
                                <i class="fa-solid fa-user"></i>
                                <input type="text" 
                                       id="name" 
                                       name="login">
                            </div>
                        </label>
                    </div>

                    <div class="input-box">
                        <label for="password"> Password
                            <div class="input-field">
                                <i class="fa-solid fa-key"></i>
                                <input type="password" 
                                       id="password" 
                                       name="senha"
                                       value="">
                            </div>
                        </label>
                        <div id="forgot_password">
                            <a href="#">Forgot yours password?</a>
                        </div>
                    </div>
                </div>

                <button type="submit" id="login-button">
                    Login
                </button>
            </form>   
            <script type="text/javascript" src="page_login/js/script.js"></script>  

        </main>  
    </body>
</html>
