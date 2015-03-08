<%-- 
    Document   : pop-ups
    Created on : 08-mar-2015, 18:02:26
    Author     : andresfelipegarciaduran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>

        <div id="modal_login" class="popupContainer" style="display:none;">
            <header class="popupHeader">
                <span class="header_title">Login</span>
                <span class="modal_close"><i class="fa fa-times"></i></span>
            </header>

            <section class="popupBody">
                <!-- Social Login -->
                <div class="social_login">
                    <h6>Bienvenido</h6>
                    <br/>
                    <div class="action_btns">
                        <div class="one_half"><a href="#" id="login_form" class="btn">Login</a></div>
                        <div class="one_half last"><a href="#" id="register_form" class="btn">Sign up</a></div>
                    </div>
                </div>

                <!-- Username & Password Login form -->
                <div class="user_login">
                    <form id="contact-form" class='contact-form'  action="Login" method="post">
                        <div class="contact-form-loader"></div>
                        <fieldset>
                            <label class="email">
                                <input id="email_login" type="email" name="email" placeholder="e-mail@dominio.com" value=""
                                       data-constraints="@Required" />
                            </label>
                            <label class="password">
                                <input id="password_login" type="password" name="password" placeholder="password" value=""
                                       data-constraints="@Required" />
                            </label>
                            <div class="action_btns">
                                <div class="one_half"><a href="#" class="btn back_btn"><i class="fa fa-angle-double-left"></i> Back</a></div>
                                <div class="one_half last"><a href="#" class="btn btn_red" onclick="$(this).closest('form').submit()">Login</a></div>
                            </div>
                        </fieldset>
                    </form>
                </div>

                <!-- Register Form -->
                <div class="user_register">
                    <form id="contact-form" class='contact-form'  action="Register" method="post">
                        <div class="contact-form-loader"></div>
                        <fieldset>
                            <label class="text">
                                <input id="fname_register" type="text" name="fname" placeholder="Nombres" value=""
                                       data-constraints="@Required" />
                            </label>
                            <label class="text">
                                <input id="flast_register" type="text" name="flast" placeholder="Apellidos" value=""
                                       data-constraints="@Required" />
                            </label>
                            <label class="email">
                                <input id="email_register" type="email" name="email" placeholder="e-mail@dominio.com" value=""
                                       data-constraints="@Required" />
                            </label>
                            <label class="password">
                                <input id="password_register" type="password" name="password" placeholder="password" value=""
                                       data-constraints="@Required" />
                            </label>
                            <br/>
                            <div class="action_btns">
                                <div class="one_half"><a href="#" class="btn back_btn"><i class="fa fa-angle-double-left"></i> Back</a></div>
                                <div class="one_half last"><a href="#" class="btn btn_red" onclick="$(this).closest('form').submit()">Register</a></div>
                            </div>
                        </fieldset>
                    </form>
                </div>

            </section>
        </div>

    </body>
</html>
