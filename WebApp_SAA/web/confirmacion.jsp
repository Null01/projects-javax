<%-- 
    Document   : confirmacion
    Created on : May 6, 2014, 6:34:30 AM
    Author     : Yury
--%>

<jsp:include page="header.jsp"></jsp:include>

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>CONFIRMACIÓN</h2>
        </header>
    </section>
    <div align="center"> 
        <p><%=request.getAttribute("mensaje")%></p>
        <a href="index.jsp" class='button fa fa-file-text'>Aceptar</a>
</div> 

<jsp:include page="footer.jsp"></jsp:include>