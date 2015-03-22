<%-- 
    Document   : publishs-comments
    Created on : 21-mar-2015, 8:44:50
    Author     : andresfelipegarciaduran
--%>

<%@page import="edu.lab.modelo.Comentario"%>
<%@page import="java.util.List"%>
<%@page import="edu.lab.modelo.Publicacion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="edu.lab.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Commments --->
<%
    Usuario usuario = (Usuario) session.getAttribute("user-data");
    List<Publicacion> publish = (List<Publicacion>) request.getAttribute("publish-data");
%>


<%
    for (Publicacion p : publish) {

%>
<div id="entry-author-info">
    <div id="author-description">
        <img class="avatar alignleft" src="images/content/avatar.gif" alt="">
        <h1><%= p.getTittle()%></h1>
        <span class="tdate"><%= new SimpleDateFormat("MMMM d, yyyy hh:mm:ss a", Locale.ENGLISH).format(p.getDatecreated())%></span>
        <p> <%= p.getDescription()%> </p>                        
    </div>
    <div class="clear"></div>
</div>

<div id="comment">
    <h3> <% out.print(publish.size());%> Comments</h3>
    <ol class="commentlist">
        <div class="box-comment">
            <div class="comment-body">
                <div class="comment-arrow"></div>
                <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div><br/>
                <cite class="fn"><%= usuario.getNombre()%></cite>
                <div id="current-date">
                    <span class="tdate"><%= new SimpleDateFormat("MMMM d, yyyy hh:mm:ss a", Locale.ENGLISH).format(Calendar.getInstance().getTime())%></span>
                </div><br/>
                <form action="#" method="post">
                    <textarea data-autoresize placeholder="Comment here" rows="2" name="comment"></textarea>
                    <input class="btn-box-comment" style="background: none;" type="submit" value=" Comment">
                </form>
            </div>
        </div>

        <%
            for (Comentario c : p.getComment()) {
        %>
        <li>
            <div class="comment-body">
                <div class="comment-arrow"></div>
                <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                <cite class="fn"><%= c.getEmail().getNombre()%></cite>
                <span class="tdate"><%= new SimpleDateFormat("MMMM d, yyyy hh:mm:ss a", Locale.ENGLISH).format(c.getDatecreated())%> &nbsp;/&nbsp; </span> <span class="reply"><a href="#">Reply</a></span>
                <div class="commenttext">
                    <p><%= c.getCommentspublish()%> </p>
                </div>
            </div>
            <%
                if (c.getComment() != null) {
            %>
            <ol>
                <li>
                    <div class="comment-body">
                        <div class="comment-arrow"></div>
                        <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                        <cite class="fn"><a href="#">Wayne Rooney</a></cite>
                        <span class="tdate">August 17, 2010 7:15 am &nbsp;/&nbsp;</span> <span class="reply"><a href="#">Reply</a></span>
                        <div class="commenttext">
                            <p>Nulla lobortis facilisis eros vitae mollis. Morbi consectetur, tortor ut feugiat rhoncus, nunc augue placerat massa, sit amet laoreet est libero quis nisl. Integer cursus sodales sem eu dapibus. Morbi lobortis eleifend lectus sit amet porttitor. Nam tincidunt congue laoreet.</p>
                        </div>
                    </div>
                </li>
            </ol>
            <%
                }
            %>
        </li>
        <%
            }
        %>
    </ol>
</div>

<%    }
%>
