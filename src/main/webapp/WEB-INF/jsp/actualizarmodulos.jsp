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
        <title>Actualizar modulos</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/resources/js/header.js" defer></script>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            /*Estilo header*/
            .body1 {
                font-family: 'Varela Round', sans-serif;
                justify-content: center;
                background:  #EAECE8;
            }

            form{
                width: 450px;
                margin: auto;
                background: #203853;
                padding: 20px 20px;
                box-sizing: border-box;
                margin-top: 20px;
                border-radius: 15px;
                border: none;
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
                width: 100%;
                margin-bottom: 5px;
                padding: 7px;
                text-decoration: none;
                border: none;
                box-sizing: border-box;
                font-family: 'Varela Round', sans-serif;
                font-weight: 1000;
                font-size: 18px;
                border: none;
                background: #181716;
                color: white;
            }

            input[type="file"]{
                width: 100%;
                height: 40px;
                margin-bottom: 0;
                background: #181716;
                color: white;
            }

            input[type="submit"]{
                padding: 7px 25px;
                width: 50%;
                height: 20%;
                margin-bottom: 0;
                background: #B15D28;
                color:rgb(36, 33, 33);
                font-family: 'Varela Round', sans-serif;
                font-size: 14px;
                border-radius: 5px;
                border: none;
                cursor: pointer;
            }
            .btnCS{
                padding: 7px 23px;
                width: 35%;
                height: 20%;
                background-color: #B15D28;
                font-size: 14px;
                color: rgb(36, 33, 33);
                border-radius: 5px;
                border: none;
                cursor: pointer;
                text-decoration: none;
                transition: all 0.3s ease 0s;
            }

            .btnCR{
                padding: 7px 23px;
                width: 50%;
                height: 20%;
                background-color: #B15D28;
                font-size: 14px;
                color: rgb(36, 33, 33);
                border-radius: 5px;
                border: none;
                cursor: pointer;
                text-decoration: none;
                transition: all 0.3s ease 0s;
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
            .opciones{
                width: 200px;
            }

            .Editarr{
                background-color: teal;
            }
            #nom, #des, #arch{
                background-color: #272727;
                color: white;
            }
            #nomAc, #desAc, #archAc{
                background-color: #272727;
                color: white;
            }
            #progress_bar{
                margin-left: 8%;
                margin-bottom: 10px;
                padding: 3px;
                border: 1px solid #000;
                font-size: 12px;
                clear: both;
                opacity: 0;
                -moz-transition: opacity 1s linear;
                -o-transition: opacity 1s linear;
                -webkit-transition: opacity 1s linear;
            }
            #progress_bar.loading{
                opacity: 1.0;
            }
            #progress_bar .percent {
                /*background-color: #99ccff;*/
                border: 1px solid #000;
                height: auto;
                width: 0;
            }
            .btncancelar{
                width: 20%;
                margin-bottom: 0;
                background: white;
                color: black;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                margin-left: 8%;
                font-size: 14px;
            }
            #DivSCursos img{
                width: 50%;
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
            
            .btnbtn-warning{
                width: 40px;
                height: 40px;
                background: #FFC107;
                text-align: center;
                viewBox: 0 0 16 16;
                cursor: pointer;
                border:none;
                border-radius: 5px;
            }
             .btnbtn-warning:hover {
               background: #E5AB00;
            }
            /*propiedad responsive*/
            @media(max-width:820px){
                form{
                    width: 80%;

                }
                table {
                    display: block;
                    overflow-x: auto;
                }
                .opciones{
                    width: 100px;
                }
                h1::after,h1::before{
                    display: none;

                }
                .btncancelar{
                    width: 20%;
                    margin-bottom: 0;
                    background: white;
                    color: black;
                    border-radius: 5px;
                    border: none;
                    cursor: pointer;
                    margin-left: 8%;
                    font-size: 10px;
                }
                #progress_bar{
                    width:60%
                }
                #DivSCursos img{
                    width: 80%;
                }

            }
        </style>
    </head>
    <body class="body1">
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
                        <!--<li><a href="#">Cerrar sesi�n</a></li>-->
                        <li><span><a onclick="cerrarSession()" class="cta">Cerrar sesi�n</a></span></li>
                    </ul>
                </div>
            </nav>
        </header>
        <br>
    <center><h1>Actualizar curso</h1></center>
    <div id="DivAgregar" class="caja">
        <form id="form" action="anadirModulos.html" method="POST" enctype="multipart/form-data">
            <center>
                <label for="nom"></label> <input type="text" name="titulo" id="nom" placeholder="Nombre" required>
                <br>
                <label for="des"></label> <input type="text" name="descripcion" id="des" placeholder="Descripcion" required>
                <br>
                <label for="arch"></label> <input type="file" id="arch" name="url" accept=".mp4,.avi,.wmv" required>
                <!--<div id="DividAg" style="display:none;">
                <!--<div id="DividAg">
                    <label for="curid"></label> <input type="text" id="curid" placeholder="Id" name="curid">
                </div>-->
            </center>
            <br>
            <div id="CargaProgress">
                <div id="progress_bar" style="background-color:white;width:80%;border-radius: 3px;">
                    <div class="percent" style="background-color:#B15D28;height:15px;">0%</div>
                </div>
                <input type="button" class="btncancelar" onclick="abortRead();" value="Cancelar">
            </div>
            <center>
                <br>
                <input class="submit" type="submit" onclick="alertAgregar()" value="Agregar">
                <br>
                <br>
                <a class="btnCS" href="anadirNuevosarchivos.html?CursoE=${idCurso}">Sube Archivos a tu Curso</a>
            </center>
        </form>
    </div>
    <div id="DivActualizar" class="caja">
        <form id="formActualizar" action="ActualizarModulo.html?VistaA=2" method="POST" enctype="multipart/form-data">
            <center>
                <label for="nom"></label> <input type="text" name="titulo" id="nomAc" placeholder="Nombre" required>
                <br> 
                <label for="des"></label> <input type="text" name="descripcion" id="desAc" placeholder="Descripcion" required>
                <br>
                <label for="arc"></label> <input type="file" id="archAc" name="url" accept=".mp4,.avi,.wmv">
            </center>
            <br>
            <center>
                <div id="Divid" style="display:none;">
                    <!--<div id="Divid">-->
                    <label for="moduid"></label> <input type="text" id="moduid" placeholder="Id" name="moduid">
                </div>
                <br>
                <input class="submit" type="submit" onclick="alertActualizar()" value="Actualizar">
                <br>
                <br>
                <input class="btnCR" type="button" onclick="cancelActualizar()" value="Cancelar">
            </center>
        </form>
    </div>
    <br>
    <center> 
        <div id="Divtablita" class="tablita">
            <table class="tabla" id="tabla">
                <thead>
                    <tr> 
                        <th>Nombre</th> 
                        <th>Descripci�n</th>
                        <th>Archivos</th>
                        <th class="opciones">Opciones</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <br>
        <br>
        <div id="DivSCursos">
            <img src="${pageContext.request.contextPath}/resources/imagenes/divSmodulos.png">
        </div>
    </center>
    <br/>
    <script>
        var Fila = null;
        let DataForm = {};
        //Variables a utilizar en ProgressBar
        var reader;
        var progress = document.querySelector('.percent');
        //Escucha la entrada de archivos del Input File
        document.getElementById('arch').addEventListener('change', handleFileSelect, false);

        $(function () {
            document.getElementById('DivActualizar').style.display = 'none';
            document.getElementById('CargaProgress').style.display = 'none';
        <c:forEach var="modu" items="${modulosAc}">
            DataForm["id"] = "${modu.idModulo}";
            DataForm["nom"] = "${modu.titulo}";
            DataForm["des"] = "${modu.descripcion}";
            DataForm["url"] = "${modu.url}";
            InsertarDatos(DataForm);
        </c:forEach>

            if (DataForm.id != "undefined" && DataForm.id != null) {
                document.getElementById('Divtablita').style.display = 'block';
                document.getElementById('DivSCursos').style.display = 'none';
            } else {
                document.getElementById('Divtablita').style.display = 'none';
                document.getElementById('DivSCursos').style.display = 'block';
            }

            console.log("ID: ", DataForm.id);
            console.log("Nombre: ", DataForm.nom);
        });

        function alertVideo(url) {
            //https://dl.dropboxusercontent.com/s/8c69iw8s5bppw07/org.springframework.web.multipart.commons.CommonsMultipartFile%4019663056_Video.mp4?dl=0
            //console.log("URL: ",url);
            swal.fire({
                width: '80%',
                background: '#00000000',
                showConfirmButton: false,
                html: '<video controls src="' + url + '" width="100%" height="98%"></video>'
                        /*customClass: {
                         container: 'sweet_containerImportant',
                         tittle: 'sweet_containerImportant'
                         }*/
            });
        }
        function handleFileSelect(evt) {
            document.getElementById('CargaProgress').style.display = 'block';
            progress.style.width = '0%';
            progress.textContent = '0%';

            reader = new FileReader();
            reader.onerror = errorHandler;
            reader.onprogress = updateProgress;
            reader.onabort = function (e) {
                //alert('Carga de archivo cancelada');
                Swal.fire({
                    title: '�Cancelado!',
                    text: 'Carga de archivo cancelada',
                    icon: 'warning',
                    iconColor: '#B15D28',
                    confirmButtonColor: '#203853'
                });
                document.getElementById("arch").value = null;
                document.getElementById('CargaProgress').style.display = 'none';
            };
            reader.onloadstart = function (e) {
                document.getElementById('progress_bar').className = 'loading';
            };
            reader.onload = function (e) {
                progress.style.width = '100%';
                progress.textContent = '100%';

                setTimeout("document.getElementById('progress_bar').className='';document.getElementById('CargaProgress').style.display = 'none';", 2000);
            };

            reader.readAsBinaryString(evt.target.files[0]);
        }

        function updateProgress(evt) {
            // evt es un ProgressEvent
            if (evt.lengthComputable) {
                var percentLoaded = Math.round((evt.loaded / evt.total) * 100);
                if (percentLoaded < 100) {
                    progress.style.width = percentLoaded + '%';
                    progress.textContent = percentLoaded + '%';
                }
            }
        }

        function abortRead() {
            reader.abort();
        }

        function errorHandler(evt) {
            switch (evt.target.error.code) {
                case evt.target.error.NOT_FOUND_ERR:
                    alert('Archivo no encontrado!');
                    break;
                case evt.target.error.NOT_READABLE_ERR:
                    alert('No se puede leer el archivo');
                    break;
                case evt.target.error.ABORT_ERR:
                    break;
                default:
                    alert('Ha ocurrido un error al leer el archivo');
            }
            ;
        }

        function cancelActualizar() {
            document.getElementById('DivActualizar').style.display = 'none';
            document.getElementById('DivAgregar').style.display = 'block';
        }

        function alertActualizar() {
            document.querySelector('#formActualizar').addEventListener('submit', function (e) {

                var form = this;
                e.preventDefault();

                swal.fire({
                    title: "�Desea Actualizar el Modulo?",
                    text: "",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: "S�, Actualizar",
                    confirmButtonColor: '#203853',
                    cancelButtonColor: '#B15D28',
                    cancelButtonText: "Cancelar"
                })
                        .then(function (isConfirm) {
                            if (isConfirm.value) {
                                swal.fire({
                                    title: "El Modulo se actualizo correctamente",
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
                                    title: "No se actualizo ningun Modulo",
                                    text: "",
                                    icon: 'error',
                                    iconColor: '#B15D28',
                                    confirmButtonColor: '#203853'
                                })
                                        .then(function () {
                                            document.getElementById('DivActualizar').style.display = 'none';
                                            document.getElementById('DivAgregar').style.display = 'block';
                                        });
                            }
                        });
            });
        }

        function alertAgregar() {
            document.querySelector('#form').addEventListener('submit', function (e) {

                var form = this;
                e.preventDefault();

                swal.fire({
                    title: "�Desea Agregar un nuevo Modulo?",
                    text: "",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: "S�, Agregar",
                    confirmButtonColor: '#203853',
                    cancelButtonColor: '#B15D28',
                    cancelButtonText: "Cancelar"
                })
                        .then(function (isConfirm) {
                            if (isConfirm.value) {
                                swal.fire({
                                    title: "El Modulo se agrego correctamente",
                                    text: "",
                                    icon: "success",
                                    iconColor: '#203853',
                                    confirmButtonColor: '#B15D28'
                                })
                                        .then(function () {
                                            form.submit();
                                        });
                            } else {
                                swal.fire({
                                    title: "No se agrego ningun Modulo",
                                    text: "",
                                    icon: "error",
                                    iconColor: '#B15D28',
                                    confirmButtonColor: '#203853'
                                });
                            }
                        });
            });
        }

        function Leer() {
            let DataForm = {};
            DataForm["nom"] = document.getElementById("nom").value;
            DataForm["des"] = document.getElementById("des").value;
            DataForm["arch"] = document.getElementById("arch").value;
            return DataForm;
        }

        function InsertarDatos(data) {
            let table = document.getElementById("tabla").getElementsByTagName('tbody')[0];
            let Fila = table.insertRow(table.length);
            columna1 = Fila.insertCell(0).innerHTML = data.nom;
            columna2 = Fila.insertCell(1).innerHTML = data.des;
            columna3 = Fila.insertCell(2).innerHTML = `<button onClick="alertVideo('` + data.url + `')"><video src="` + data.url + `" width="240" height="140"></video></button>`;
            columna3 = Fila.insertCell(3).innerHTML = `
                                                            <button type="button" class="btnbtn-danger" onClick="Borrarr(this,` + data.id + `)">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash " viewBox="0 0 16 16">
                                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                                </svg>
                                                            </button>
                    
                                                            <button type="button" class="btnbtn-success" onClick="Editarr(this,` + data.id + `)" >
                                                                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-upload" viewBox="0 0 16 16">
                                                                    <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                                                                    <path d="M7.646 1.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 2.707V11.5a.5.5 0 0 1-1 0V2.707L5.354 4.854a.5.5 0 1 1-.708-.708l3-3z"/>
                                                                  </svg>
                                                            </button>
        
                                                        <button type="button" class="btnbtn-warning" onClick="a�adirCuestionario(` + data.id + `)">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                            </svg>
                                                        </button>        
                                                            `;
        }

        function Vaciar() {
            document.getElementById("nom").value = "";
            document.getElementById("des").value = "";
            document.getElementById("arch").value = "";
            Fila = null;
        }

        function Editarr(td, id) {
            document.getElementById('DivActualizar').style.display = 'block';
            document.getElementById('DivAgregar').style.display = 'none';

            Fila = td.parentElement.parentElement;
            document.getElementById("nomAc").value = Fila.cells[0].innerHTML;
            document.getElementById("desAc").value = Fila.cells[1].innerHTML;
            document.getElementById("moduid").value = id;
            document.getElementById("nomAc").focus();
        }

        function Actualizar(DataForm) {
            Fila.cells[0].innerHTML = DataForm.nom;
            Fila.cells[1].innerHTML = DataForm.des;
            Fila.cells[2].innerHTML = DataForm.arch;
            document.getElementById("nom").focus();
        }

        function Borrarr(td, id) {
            swal.fire({
                title: "�Desea eliminar el Modulo?",
                text: "Al realizar esta operacion no se podra revertir",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: "S�, eliminar",
                confirmButtonColor: '#203853',
                cancelButtonColor: '#B15D28',
                cancelButtonText: "Cancelar"
            })
                    .then((willDelete) => {
                        if (willDelete.value) {
                            swal.fire({
                                title: "El Modulo se elimino correctamente",
                                text: "",
                                icon: "success",
                                iconColor: '#203853',
                                confirmButtonColor: '#B15D28'
                            }).then(function () {
                                row = td.parentElement.parentElement;
                                document.getElementById("tabla").deleteRow(row.rowIndex);
                                document.location.href = "borrarModulo.html?ModuloE=" + id + "&VistaB=2";
                            });
                        } else {
                            swal.fire({
                                title: "No se elimino ningun Modulo",
                                text: "",
                                icon: "error",
                                iconColor: '#B15D28',
                                confirmButtonColor: '#203853'
                            });
                        }
                    });
        }

        function a�adirCuestionario(id) {
           document.location.href = "cuestionario.html?Modulo=" + id;
        }
        
    </script>


    <br/>
    <br/>
</div>

<!--Footer-->
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<footer>
    <div class="footer-content">
        <h3>B1 SOFT</h3>

        <ul class="socials">
            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
            <li><a href="#"><i class="fa fa-youtube"></i></a></li>
            <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
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