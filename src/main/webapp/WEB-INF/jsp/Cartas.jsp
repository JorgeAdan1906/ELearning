<%-- 
    Document   : Cartas
    Created on : 16-jul-2021, 17:11:08
    Author     : Karina Romero
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.dropbox.core.v2.sharing.ListSharedLinksResult"%>
<%@page import="Elearning.util.JavaDropBox"%>
<%@page import="Elearning.modelo.Curso"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.common.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.black.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.default.mobile.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cartas.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/newheader.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/kendo.all.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/header.js" defer></script>
        <script src="../content/shared/js/products.js"></script> 
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .body1 {
                font-family: 'Varela Round', sans-serif;
                justify-content: center;
                background:  #EAECE8;
            }
            h1{
                text-align: center;
                display: inline-block;
                position: relative;
                font-size: 35px;
                color: black;
                font-weight: bold;
            }

            h1::after,h1::before{

                content: '';
                position: absolute;
                width: 120px;
                height: 3px;
                background-color: #B15D28;
                top: 0.6em;

            }

            h1::before{
                left: -140px;

            }

            h1::after{
                right: -140px;

            }
            .footer-content h3{
                font-family: "Open Sans";
            }
            .k-card:hover{
                background-color: #B15D28;
                color: white;
                cursor: pointer;
                transform: scale(1.03);
                transition: all 1s ease;
            }
            #DivSCursos img{
                width: 50%;
            }
            /*propiedad responsive*/
            @media(max-width:820px){
                #DivSCursos img{
                    width: 80%;
                }
                h1::after,h1::before{
                    display: none;

                }
            }
        </style>
    </head>
    <body class="body1">
        <%
            String tipoUsuario = (String) session.getAttribute("tUsuario");
            if (tipoUsuario != null) {
                if (tipoUsuario.equals("Administrador")) {
                    response.sendRedirect("admin.html");
                }
            } else {
                response.sendRedirect("index.html");
            }
        %>
        <header>
            <nav class="navbar">
                <!--<div class="brand-title">Brand Name</div>-->
                <div class="logo">
                    <div>
                        <a href="bienvenida.html"><img src="${pageContext.request.contextPath}/resources/imagenes/B1SOFT-LOGO.gif"></a>
                    </div>
                </div>
                <a href="#" class="toggle-button">
                    <span class="bar"></span>
                    <span class="bar"></span>
                    <span class="bar"></span>
                </a>
                <div class="navbar-links">
                    <ul>
                        <li><a href="perfilsem.html">Mi perfil</a></li>
                        <li><a href="ProgressBar.html">Mis Cursos</a></li>
                        <!--<li><a href="#">Cerrar sesi�n</a></li>-->
                        <li><span><a onclick="cerrarSession()" class="cta">Cerrar sesi�n</a></span></li>
                    </ul>
                </div>
            </nav>
        </header>
        <br>
        <!-- Titulo de la P�gina -->
    <center><h1><c:out value="${categoria}"></c:out></h1></center>
        <div class="cards-container">
        <c:forEach items="${cursos}" var="curso"> 
            <div class="k-card">
                <a href="mediacursos.html?idCurso=${curso.idCurso}"><img class="k-card-image"  src="${curso.caratula}"/></a>
                <div class="k-card-body">
                    <center> <h3><c:out value="${curso.nombre}"></c:out></h3> </center> 
                    </div>
                </div>
        </c:forEach>
    </div>
    <center>
        <div id="DivSCursos">
            <img src="${pageContext.request.contextPath}/resources/imagenes/divScursos.png">
        </div>
    </center>
    <script>
        let DataForm = {};
        $(function () {
        <c:forEach var="curso" items="${cursos}">
            DataForm["id"] = "${curso.idCurso}";
        </c:forEach>

            if (DataForm.id != "undefined" && DataForm.id != null) {
                document.getElementById('DivSCursos').style.display = 'none';
            } else {
                document.getElementById('DivSCursos').style.display = 'block';
            }
        });
    </script>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <footer>
        <div class="footer-content">
            <h3>B1 SOFT LATINOAMERICA</h3>

            <ul class="socials">
                <li><a href="#"><i class="fab fa-facebook"></i></a>
                <li><a href="#"><i class="fab fa-twitter"></i></a>
                <li><a href="#"><i class="fab fa-google"></i></a>
                <li><a href="#"><i class="fab fa-youtube"></i></a>
                <li><a href="#"><i class="fab fa-linkedin"></i></a>
            </ul>
        </div>
        <div class="footer-bottom">
            <p>designed by <span>B1 SOFT</span></p>
        </div>
    </footer>
</body>
<script>
    function cerrarSession() {

        $(location).attr('href', "cerrarSession.html")

    }
</script>
</html>
