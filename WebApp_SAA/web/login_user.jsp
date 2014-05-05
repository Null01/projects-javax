<%-- 
    Document   : login_user
    Created on : May 5, 2014, 9:54:39 PM
    Author     : Yury
--%>

<jsp:include page="header.jsp"></jsp:include>

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>BIENVENIDO POR FAVOR IDENTIFIQUESE.</h2>
        </header>
    </section>
    <div align="center"> 
        <form id="formLogin" name="login" action="loginUserController">
            <table border="1" style="width:500px" >
                <tbody>
                    <tr>
                        <td><h2>USUARIO: </h2></td>
                        <td><input type="text" name="user"class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>CONTRASEÑA: </h2></td>
                        <td><input type="password" name="password" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><a href="registro.jsp" class="button alt">Registro</a></td>
                        <td><input type="submit" class="button alt" /></td>
                    </tr>
                    <tr>
                    <% if (request.getAttribute("mensaje") != null) {%>
                    <td> <%=request.getAttribute("mensaje")%> </td>
                    <%}%>
                </tr>
            </tbody>
        </table>

    </form>
</div> 

<jsp:include page="footer.jsp"></jsp:include>