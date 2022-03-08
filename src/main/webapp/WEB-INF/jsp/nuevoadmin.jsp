<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/newheader.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="CRUD dinamico con HTMLS, CSS and JS">
        <link href="https://fonts.googleapis.com/css?family=Quicksand:600&display=swap" rel="stylesheet">
        <title>Nuevos admin</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/resources/js/header.js" defer></script>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <style>
            /*Estilo header*/
            .body1 {
                font-family: 'Varela Round', sans-serif;
                justify-content: center;
                background:  #EAECE8;
            }

            form{
                width: 820px;
                margin: auto;
                background: #203853;
                padding: 20px 20px;
                box-sizing: border-box;
                margin-top: 10px;
                border-radius: 15px;
                border: none;
                column-count: 2;
                column-gap: 60px;
                column-rule: solid 2px #B15D28;
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

            input{
                width: 93%;
                margin-bottom: 10px;
                padding: 5px;
                text-decoration: none;
                border-radius: 5px;
                box-sizing: border-box;
                font-family: 'Varela Round', sans-serif;
                font-weight: 900;
                font-size: 18px;
                border: none;
                background: #181716;
                color: white;
                outline: none;
            }

            select{
                margin-bottom: 20px;
                border-radius: 5px;
                width: 93%;
                padding: 5px;
                outline: none;
                font-weight: 900;
                font-family: 'Varela Round', sans-serif;
                font-size: 18px;
                background: #181716;
                color:white;
                border: none;
            }
            
            input[type="submit"]{
                width: 50%;
                margin-bottom: 0;
                background: #B15D28;
                color: rgb(36, 33, 33);
                border-radius: 5px;
                border: none;
                cursor: pointer;
                font-family: "Font Awesome 5 Free"; 
                font-weight: 1000;
            }
            
            input[type="submit"]:hover{
                cursor: pointer;
                background-color: #f8f9fc;
            }
            
            .btnCR{
                width: 80%;
                margin-bottom: 0;
                background: #B15D28;
                color: rgb(36, 33, 33);
                border-radius: 5px;
                border: none;
                cursor: pointer;
                font-family: "Font Awesome 5 Free"; 
                font-weight: 1000;
            }
            
            .btnCR:hover{
                cursor: pointer;
                background-color: #f8f9fc;
            }

            table{
                background-color: #203853;
                width: 90%;
                text-align: center;
                color: white;
                border-collapse: collapse;
            }
            
            th, td{
                padding: 10px;
            }
            
            tr {
                border: #B15D28 2px solid;
            }

            thead{
                background-color:  #203853;
                border-bottom: solid 5px #B15D28;
                color: white;
            }

            .Editarr{
                background-color: teal;
            }
            
            #nom, #apeP, #apeM, #correo, #cont, #gen, #RFC{
                background-color: #272727;
                color: white;
            }
            
            h1 {
                color: black;
                font-weight: bold;
            }
            
            .btnbtn-success{
                width: 40px;
                height: 40px;
                background: #28A845;
                text-align: center;
                viewBox: 0 0 16 16;
                cursor: pointer;
                border:none;
                border-radius: 5px;
                color: white;
            }
            
            .btnbtn-success:hover {
                background: #218837;
            }
            
            .btnbtn-danger{
                width: 40px;
                height: 40px;
                background: #DD3545;
                text-align: center;
                viewBox: 0 0 16 16;
                cursor: pointer;
                border:none;
                border-radius: 5px;
                color: white;
            }
            
            .btnbtn-danger:hover {
               background: #C82333;
            }
            
            /*propiedad responsive*/
            @media(max-width:820px){
                form{
                    width: 90%;
                }
                table {
                    display: block;
                    overflow-x: auto;
                }
                h1::after,h1::before{
                    display: none;
                } 
            }
        </style>
    </head>
    <!-- Navigation-->
    <header>
        <nav class="navbar">
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
                    <li><span><a onclick="cerrarSession()" class="cta">Cerrar sesión</a></span></li>
                </ul>
            </div>
        </nav>
    </header>
    <body class="body1">
        <%
            String tipoUsuario = (String) session.getAttribute("tUsuario");
            if (tipoUsuario != null) {
                if (tipoUsuario.equals("Semillero")) {
                    response.sendRedirect("bienvenida.html");
                }
            } else {
                response.sendRedirect("index.html");
            }
        %>
                    
        <br>

        <center><h1>Administradores</h1></center>
        
        <br>
        <center>
            <form id="FormAdmin" method="POST" action="addAdministrador.html">
                <input type="text" id="nom" placeholder="Nombre" required name="nombre">
                <input type="text" id="apeP" placeholder="Apellido paterno" required name="aPaterno">
                <input type="text" id="apeM" placeholder="Apellido materno" required name="aMaterno">
                <select  name="genero" id="gen">
                    <option disabled selected value="g">Género:</option>
                    <option value="Femenino">Femenino</option>
                    <option value="Masculino">Masculino</option>
                </select>
                <input type="text" id="RFC" placeholder=" RFC" required name="rfc">
                <input type="text" id="corre" placeholder="Correo" required name="email">
                <input type="password" id="con" placeholder="Contraseña" required name="contrasena">
                <div id="IdAdmin" style="display:none;">
                    <input type="text" id="adminId" placeholder="Id" name="idUsuario">
                </div>
                <br>
                <br>
                <center>
                    <div id="DivBtnGuardar">
                        <div style="width: 100%">
                            <input class="submit" type="submit" onclick="alertGuardar()" value="Crear Administrador">
                        </div>  
                    </div>
                    <div id="DivBtnActualizar">
                        <div style="float: left; width: 50%">
                            <input class="submit" type="submit" onclick="alertActualizar()" value="Guardar">
                        </div>
                        <div style="float: right; width: 50%">
                            <input class="btnCR" type="button" onclick="cancelActualizar()" value="Cancelar">
                        </div>   
                    </div>
                </center>
            </form>
        </center>
        
        <br>
        <br>
        
        <center>
            <div class="tablita">
                <table class="tabla" id="tabla">
                    <thead>
                        <tr> 
                            <th>Nombre</th> 
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Genero</th>
                            <th>RFC</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <c:forEach items="${administradores}" var="admin">
                        <tr>
                            <td><c:out value="${admin.nombre}"></c:out></td>
                            <td><c:out value="${admin.aPaterno}"></c:out></td>
                            <td><c:out value="${admin.aMaterno}"></c:out></td>
                            <td><c:out value="${admin.genero}"></c:out></td>
                            <td><c:out value="${admin.rfc}"></c:out></td>
                            <td><c:out value="${admin.email}"></c:out></td>
                            <td><c:out value="${admin.contrasena}"></c:out></td>
                            <td>
                                <button type="button"  class="btnbtn-danger" onclick="return confirm(${admin.idUsuario});">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash " viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                                </button>
                                <button type="button" class="btnbtn-success" onClick="Editarr(this,${admin.idUsuario})" >
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-upload" viewBox="0 0 16 16">
                                        <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                                        <path d="M7.646 1.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 2.707V11.5a.5.5 0 0 1-1 0V2.707L5.354 4.854a.5.5 0 1 1-.708-.708l3-3z"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </center>

        <br/>
        
        <script>
            var Fila = null;
    
            document.getElementById('DivBtnActualizar').style.display = 'none';
            
            function Vaciar() {
                document.getElementById("nom").value = "";
                document.getElementById("apeP").value = "";
                document.getElementById("apeM").value = "";
                document.getElementById("corre").value = "";
                document.getElementById("con").value = "";
                document.getElementById("gen").value = 'g';
                document.getElementById("RFC").value = "";
                
                document.getElementById("nom").focus();
            }
            
            function Editarr(td, id) {
                Fila = td.parentElement.parentElement;
                document.getElementById("nom").value = Fila.cells[0].innerHTML;
                document.getElementById("apeP").value = Fila.cells[1].innerHTML;
                document.getElementById("apeM").value = Fila.cells[2].innerHTML;
                document.getElementById("gen").value = Fila.cells[3].innerHTML;
                document.getElementById("RFC").value = Fila.cells[4].innerHTML;
                document.getElementById("corre").value = Fila.cells[5].innerHTML;
                document.getElementById("con").value = Fila.cells[6].innerHTML;
                document.getElementById("adminId").value = id;
                
                document.getElementById('DivBtnGuardar').style.display = 'none';
                document.getElementById('DivBtnActualizar').style.display = 'block';
            }
            
            function alertGuardar() {
                document.querySelector('#FormAdmin').addEventListener('submit', function (e) {

                    var form = this;
                    e.preventDefault();

                    swal.fire({
                        title: "¿Desea crear un nuevo Administrador?",
                        text: "",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonText: "Sí, crear",
                        confirmButtonColor: '#203853',
                        cancelButtonColor: '#B15D28',
                        cancelButtonText: "Cancelar"
                    })
                    .then(function (isConfirm) {
                        if (isConfirm.value) {
                            swal.fire({
                                title: "El Administrador se creo correctamente",
                                text: "",
                                icon: 'success',
                                iconColor: '#203853',
                                confirmButtonColor: '#B15D28'
                            })
                            .then(function () {
                                document.getElementById("adminId").value = "";
                                form.submit();
                            });
                        } else {
                            swal.fire({
                                title: "No se creo el Administrador",
                                text: "",
                                icon: 'error',
                                iconColor: '#B15D28',
                                confirmButtonColor: '#203853'
                            })
                            .then(function () {
                                Vaciar();
                                document.getElementById("nom").focus();
                            });
                        }
                    });
                });
            }
            
            function alertActualizar() {
                document.querySelector('#FormAdmin').addEventListener('submit', function (e) {

                    var form = this;
                    e.preventDefault();

                    swal.fire({
                        title: "¿Desea Actualizar los datos del Administrador?",
                        text: "",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonText: "Sí, Actualizar",
                        confirmButtonColor: '#203853',
                        cancelButtonColor: '#B15D28',
                        cancelButtonText: "Cancelar"
                    })
                    .then(function (isConfirm) {
                        if (isConfirm.value) {
                            swal.fire({
                                title: "El Administrador se actualizo correctamente",
                                text: "",
                                icon: 'success',
                                iconColor: '#203853',
                                confirmButtonColor: '#B15D28'
                            })
                            .then(function () {
                                form.submit();
                            });
                        } else {
                            swal.fire({
                                title: "No se actualizo el Administrador",
                                text: "",
                                icon: 'error',
                                iconColor: '#B15D28',
                                confirmButtonColor: '#203853'
                            })
                            .then(function () {
                                document.getElementById('DivBtnGuardar').style.display = 'block';
                                document.getElementById('DivBtnActualizar').style.display = 'none';
                                
                                Vaciar();
                                document.getElementById("tabla").focus();
                            });
                        }
                    });
                });
            }
            
            function cancelActualizar() {
                document.getElementById('DivBtnGuardar').style.display = 'block';
                document.getElementById('DivBtnActualizar').style.display = 'none';
                
                Vaciar();
                document.getElementById("tabla").focus();
            }
            
            function confirm(id) {
                if(id == 1){
                    swal.fire({
                        title: '¡VAYA!',
                        text: 'No se puede eliminar este Administrador',
                        icon: 'error',
                        iconColor: '#B15D28',
                        confirmButtonColor: '#203853'
                    });
                } else{
                    swal.fire({
                        title: '¿Estas seguro de eliminar el Administrador?',
                        text: "Si se elimina no se podra revertir",
                        icon: 'warning',
                        iconColor: '#B15D28',
                        showCancelButton: true,
                        confirmButtonText: 'Eliminar',
                        confirmButtonColor: '#203853',
                        cancelButtonColor: '#B15D28',
                        cancelButtonText: 'Cancelar',
                        reverseButtons: true
                    }).then(function (result) {
                        if (result.value) {
                            swal.fire({
                                title: '¡Eliminado!',
                                text: 'Se elimino el Administrador',
                                icon: 'success',
                                iconColor: '#203853',
                                confirmButtonColor: '#B15D28'
                            }).then(function () {
                                document.location.href = "eliminarAdmin.html?idUsuario=" + id;
                            });
                        }else {
                            swal.fire({
                                title: '¡Cancelado!',
                                text: 'No se elimino ningun Administrador',
                                icon: 'error',
                                iconColor: '#B15D28',
                                confirmButtonColor: '#203853'
                            });
                        }
                    });
                }
            }
        </script>

        <br/>
        <br/>
    </body>
    
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <!--Footer-->
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
    
    <script>
        function cerrarSession() {
            $(location).attr('href', "cerrarSession.html")
        }
    </script>
</html>
