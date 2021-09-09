<%-- 
    Document   : nuevocurso
    Created on : 16-jul-2021, 17:29:14
    Author     : Karina Romero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nuevo Curso</title>
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.common.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.black.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kendo.default.mobile.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nuevocurso.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/newheader.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/kendo.all.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/header.js" defer></script>
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .body1 {
                font-family: 'Varela Round', sans-serif;
                justify-content: center;
                background:  #EAECE8;
            }
            #Titulo {
                color: black;
                text-align: center;
                font-size: 30px;
            }

            .k-button{
                width: 40%;
                margin-bottom: 0;
                background: #B15D28;
                color: black;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }
            .k-button:hover{
                background-color: white;
                color: black;
                filter: brightness(1.05);
            }
            #simple-input{
                padding: 5px;
            }
        </style>
    </head>
    <body class="body1">
        <!-- Navigation-->
        <header>
            <nav class="navbar">
                <!--<div class="brand-title">Brand Name</div>-->
                <div class="logo">
                    <div>
                        <a href="admin.html"><img src="${pageContext.request.contextPath}/resources/imagenes/B1SOFT-LOGO.gif"></a>
                    </div>
                </div>
                <a href="#" class="toggle-button">
                    <span class="bar"></span>
                    <span class="bar"></span>
                    <span class="bar"></span>
                </a>
                <div class="navbar-links">
                    <ul>
                        <li><a href="perfiladmin.html">Mi perfil</a></li>
                        <li><a href="nuevoadmin.html">Administradores</a></li>
                        <li><a href="nuevosemillero.html">Semilleros</a></li>
                        <li><a href="listadodecursos.html">Cursos</a></li>
                        <li><a href="nuevocurso.html">Agregar nuevo curso</a></li>
                        <!--<li><a href="#">Cerrar sesión</a></li>-->
                        <li><span><a onclick="cerrarSession()" class="cta">Cerrar sesión</a></span></li>
                    </ul>
                </div>
            </nav>
        </header>
        <br>

        <div id="container">
            <div id="Titulo">
                <h3>Agregar nuevo curso</h3>
            </div>
            <br>
            <form:form id="form" action="crearCurso.html" method="POST" enctype="multipart/form-data">   
                <div id="example">
                    <div class="demo-section k-content">
                        <ul class="fieldlist">
                            <center><img  src="${pageContext.request.contextPath}/resources/imagenes/curso.png" width="220px" height="160px" ></center>
                            <br>
                            <li>
                                <input id="simple-input" type="text" placeholder="Nombre" class="k-textbox" style="width: 100%;" name="nombre" />
                            </li>
                            <li>
                                <input id="simple-input" type="text" placeholder="Descripción" class="k-textbox" style="width: 100%;" name="descripcion"  />
                            </li>
                            <li>
                                <select  name="categoria" id="simple-input" class="k-textbox" style="width: 100%;">
                                    <option disabled selected value="c">Selecciona una categoria</option>
                                    <option>Back-End</option>
                                    <option>Front-End</option>
                                    <option>Bases de Datos</option>
                                    <option>Redes</option>
                                    <option>Seguridad en redes</option>   
                                    <option>Otro</option>
                                </select>
                            </li>
                            <li>
                                <h3 style="color: white;"> Selecciona la caratula del curso: </h3> 
                                <input id="simple-input" type="file" name="caratula" class="k-textbox" style="width: 100%;" />

                            </li>
                            <li>
                            <center><button class="k-button" type="submit">Guardar curso</button>&nbsp;</center> 
                            </li>
                            <br>
                            <br>
                            <br>

                        </ul>
                        <style>
                            .demo-section  {
                                background-color: rgba(255, 255, 255, 0);
                            }

                            .fieldlist {

                                width: 90%;
                                height: 560px;
                                background:  #203853;
                                padding: 50px;
                                margin: auto;
                                margin-top: 30px;
                                border-radius: 10px;

                                color: rgb(42, 35, 49);
                                box-shadow: 7px 13px 37px #000;

                            }

                            .fieldlist li {
                                list-style: none;
                                padding-bottom: 3em;

                            }

                            .fieldlist label {
                                display: block;
                                padding-bottom: 1em;
                                font-weight: bold;
                                text-transform: uppercase;
                                font-size: 20px;
                                color:black;
                            }

                        </style>
                    </div>
                </div>
            </div>
        </form:form>        
        <!--Footer-->
        <br>
        <br>
        <br>
        <br>
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
        <p>2021. <span>B1 SOFT</span></p>
    </div>
</footer>
    </body>
    <script>
        function cerrarSession() {

            $(location).attr('href', "cerrarSession.html")

        }
    </script>
</html>
