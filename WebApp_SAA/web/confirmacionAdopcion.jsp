<%-- 
    Document   : confirmacionAdopcion
    Created on : May 6, 2014, 6:01:11 AM
    Author     : Yury
--%>

<%@page import="Entities.Mascota"%>
<%@page import="Facade.ControllerJPAMascota"%>
<jsp:include page="header.jsp"></jsp:include>

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>CONFIRMA QUE DESEA ADOPTAR ESTA MASCOTA?</h2>
        </header>
    </section>
    <div align="center"> 
        <form id="formLogin" name="login" action="solicitudController">

        <%
            ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
            Mascota mascota = controllerJPAMascota.getMascota(Integer.parseInt(request.getParameter("idmascota")));
        %>
        <section class="box">
            <a href="#" class="image image-full"><img src="images/pic08.jpg" alt="" /></a>
            <header>
                <h3><%= mascota.getNombre()%></h3>
                <span class="byline"><%=mascota.getEdad() + " años"%></span>
            </header>
            <p>
                <%
                    out.println(mascota.getIdTipoMascota().getIdTipoMascota() == 3 ? mascota.getOtroTipoMascota() : mascota.getIdTipoMascota().getNombre());
                    out.println(" raza ");
                    out.println(mascota.getIdRaza().getIdRaza() == 4 ? mascota.getOtraRaza() : mascota.getIdRaza().getNombre());
                %>
            </p>
            <footer class="actions">
                <input type="hidden" name="idmascota" value='<%=mascota.getIdMascota()%>' />
                <td><input type="submit" class="button alt" /></td>
            </footer>
        </section>
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
<footer>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/style-desktop.css" />
</footer>
</html>