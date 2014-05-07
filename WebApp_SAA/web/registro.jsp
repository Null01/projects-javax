<%-- 
    Document   : registro
    Created on : May 6, 2014, 12:47:46 AM
    Author     : Yury
--%>

<jsp:include page="header.jsp"></jsp:include>

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>INGRESE SUS DATOS</h2>
        </header>
    </section>
    <div align="center"> 
        <form id="formLogin" name="login" action="registerController">
            <table border="1" style="width:500px" >
                <tbody>
                    <tr>
                        <td><h2>Nombres: </h2></td>
                        <td><input type="text" name="nombres" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Apellidos: </h2></td>
                        <td><input type="text" name="apellidos" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Email: </h2></td>
                        <td><input type="email" name="email" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Contraseña: </h2></td>
                        <td><input type="password" name="password" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Confirmar contraseña: </h2></td>
                        <td><input type="password" name="confirmacionPassword" class="rounded_corners" required /></td>
                    </tr>

                    <tr>
                        <td><h2>Dirección </h2></td>
                        <td><input type="text" name="direccion" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Teléfono 1: </h2></td>
                        <td><input type="tel" name="telefono1" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                        <td><h2>Teléfono 2: </h2></td>
                        <td><input type="tel" name="telefono2" class="rounded_corners" required /></td>
                    </tr>
                    <tr>
                    <% if (request.getAttribute("mensaje") != null) {%>
                    <td> <%=request.getAttribute("mensaje")%> </td>
                    <%}%>
                </tr>
            </tbody>
        </table>
        <td><input type="submit" class="button alt" /></td>
    </form>
</div> 

</div>
</div>
<!-- Footer Wrapper -->
<div id="footer-wrapper">

    <!-- Footer -->
    <section id="footer" class="container">
    </section>    
</div>

</body>
</html>