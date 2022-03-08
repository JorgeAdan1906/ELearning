<%@page import="Elearning.modelo.Cuestionario"%>
<%@page import="java.util.TimerTask"%>
<%@page import="java.util.Timer"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quizz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/newheader.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/examen.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timer.css" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  </head>
  <style>
    .body1 {
      font-family: "Varela Round", sans-serif;
      justify-content: center;
      background: #eaece8;
    }
    h1 {
      text-align: center;
      display: inline-block;
      position: relative;
      font-size: 35px;
      color: black;
      font-weight: bold;
    }
    h1::after,h1::before {
      content: "";
      position: absolute;
      width: 120px;
      height: 3px;
      background-color: #b15d28;
      top: 0.6em;
    }

    h1::before {
      left: -140px;
    }

    h1::after {
      right: -140px;
    }
            /*propiedad responsive*/
        @media(max-width:820px){
            h1::after,h1::before{
                display:none;
            }
            .formulario{
                width: 80%;
                margin: auto;
                padding: 20px;
                border-radius: 10px;
                border:5px outset gray;
            }
            #botonresultado {
                width: 20%;
                height: 8%;
                background-color: #b15d28;
                font-size: 10px;
            }
            #botonrespuestas {
                width: 20%;
                height: 8%;
                background-color: #b15d28;
                font-size: 10px;
            }
        }
        
  </style>
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
                        <!--<li><a href="#">Cerrar sesión</a></li>-->
                        <li><span><a onclick="cerrarSession()" class="cta">Cerrar sesión</a></span></li>
                    </ul>
                </div>
            </nav>
        </header>
    <br>
    <center>
      <h1>${cuestionario.nombre}</h1>
      <br></br>
    </center>
     <br>
     
     <script>
        setInterval(myTimer, 780000);
        //let miTimeout;
         
        function myTimer() {
            //un minuto son 60000
            const miTimeout = setTimeout(myFunction, 780000);
            //clearTimeout(miTimeout);
        }
          
        function myFunction(){
            alert("Se acabo tu tiempo");
            document.getElementById('botonresultado').style.display = 'none';
            document.getElementById('tiempoEtiqueta').style.display = 'none';
            document.getElementById('timer').style.display = 'none';
            document.getElementById('timer').style.display = 'none';
            //clearTimeout(miTimeout);
            //document.getElementById('tiempoEtiqueta2').style.display = 'none';
            //AsignarCalificacion();
            //document.getElementById('botonrespuestas').style.display = 'block';
        }
        myTimer();
     </script>
     <div style="text-align: center">
       <p id="tiempoEtiqueta">Tiempo Total: 13 Min</p>  
      </div>
      <div id="timer" class="progress-bar">
	<span class="bar_timer">
            <span class="progress"></span>
	</span>
      </div>
    
    <br>
    <!--PARA CONTESTAR-->
    <div id="formulario" class="formulario">
      <div id="resultado"></div>
      <div id="test"></div>
      <br>
      <center> <button id="botonresultado" onclick="AsignarCalificacion()">Enviar</button> </center>
      <br>
      <center> <a id="botonresultado" onclick="ocultar()" style="display: none">Ver respuestas</a> </center>
      <script src="${pageContext.request.contextPath}/resources/js/examenpreview.js"></script>
    </div>
    <br>
    
    <!--RESPUESTAS-->
    <div id="respuestas" class="formulario" style="display: none" >
        <center><h2>Respuestas Correctas</h2></center>
      <br>
      <div id='tusResp' style="display: none">
          <h4>Tus Respuestas:</h4>
      </div>
      <div id="testResp">
          <c:forEach var="preg" items="${preguntas}">
              
              <h2 id='pregunta'>${preg.pregunta}</h2>
              <!--<p id='idP'></p>-->
              <label id='respA' style="color: #07D707">a) ${preg.respuestaA} (RESPUESTA CORRECTA)</label></label>
              <label id="respB">b) ${preg.respuestaB}</label>
              <label id='respC'>c) ${preg.respuestaC}</label>
        </c:forEach>
      </div>
      <br>
      <br>
      <center>
          <div id="btnConfirmar">
            <!--<a id="botonresultado" href="ActualizarMiCuestionario.html?idCurso=" + ${CursoID} + "&idUsuario=" + ${UsuarioID} + "&idCuestionario=" + ${cuestionario.idCuestionario} + " &calificacion="+ calificacion" &Evaluacion=1"">Regresar</a>-->
          </div>
          </center>
      <script src="${pageContext.request.contextPath}/resources/js/examenpreview.js"></script>
      <br>
      <br>
    </div>
   
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
    <script> 
        var x = [];
        var Quizz = [];
        
        function ocultar(x){
            console.log("ocultar");
            //alert("Se acabo tu tiempo")
            document.getElementById('botonresultado').style.display = 'none';
            document.getElementById('tiempoEtiqueta').style.display = 'none';
            document.getElementById('timer').style.display = 'none';
            document.getElementById('formulario').style.display = 'none';
            document.getElementById('respuestas').style.display = 'block';
            document.getElementById('tusResp').style.display = 'block';
            
            
            //console.log(x);
            //console.log(x.length);
            for(var i = 0; i<x.length; i++){
                //console.log("for x")
                //console.log(x[0][i]);
                console.log("Pregunta " + (i+1) + ": " +x[i] );
                if( x[i] === "a" ){
                    document.getElementById('tusResp').innerHTML += "<p style='color: #07D707; display: inline-block'>Pregunta " + (i+1) + ": " +x[i] + "</p>" ;
                } else if(x[i] === "b"){
                    document.getElementById('tusResp').innerHTML += "<p style='color: red; display: inline-block'>Pregunta " + (i+1) + ": " +x[i] + "</p>" ;
                }  else if(x[i] === "c"){
                    document.getElementById('tusResp').innerHTML += "<p style='color: red; display: inline-block'>Pregunta " + (i+1) + ": " +x[i] + "</p>" ;
                } else{
                    document.getElementById('tusResp').innerHTML += "<p style='color: red; display: inline-block'>Pregunta " + (i+1) + ": Sin responder</p>" ;
                }
                //document.getElementById('tusResp').innerHTML += "Pregunta " + (i+1) + ": " +x[0][i] ;
                document.getElementById('tusResp').innerHTML += "<br>";
            }
            document.getElementById('tusResp').innerHTML += "<br>" ;
            <c:forEach var="preg" items="${preguntas}">
                //for(var i = 0; i<=x.length; i++){
                  //console.log(x[i])
                  //document.getElementById('idP').innerHTML= "<p>${preg.idPregunta}</p>"; 
                  //document.getElementById('pregunta').innerHTML= "<p>${preg.pregunta}</p>"; 
                  //document.getElementById('respA').innerHTML= "<p>${preg.respuestaA} (RESPUESTA CORRECTA)</p>";
                  //document.getElementById('respB').innerHTML= "<p>${preg.respuestaB}</p>";
                  //document.getElementById('respC').innerHTML= "<p>${preg.respuestaC}</p>";
                  //document.getElementById('testResp').innerHTML = "<p>brenda</p>";
              //console.log("ola");
                //}
            </c:forEach>
            
        }
        
        
        <c:forEach var="preg" items="${preguntas}">
        Quizz.push({
        pregunta: "${preg.pregunta}",
        respuestas: {
        a: "${preg.respuestaA}",
        b: "${preg.respuestaB}",
        c: "${preg.respuestaC}"
                },
                respuestaCorrecta: "a"
            });
        </c:forEach>

        //console.log("arreglo Pregunta: " + Quizz[0].pregunta);
        pasarVariables(Quizz);
        var QuizzNum = Quizz.length;
        console.log("Hay:" + QuizzNum)
        //console.log(Quizz);
        mostrarTest();
        
        function AsignarCalificacion(){
            var Calificacion = 0;
            Calificacion = mostrarResultado();
            
            //Recoge localStorage de las respuestas seleccionadas en Array
            //localStorage.getItem("resp");
            
            //x.push(JSON.parse(localStorage.getItem("resp")));//console.log(x);
            //localStorage.setItem("x", JSON.stringify(x));
            //console.log(x);
            
            var x = [];
            x = getResp();
            console.log("resp jsp: " + x);
            var evaluacion;
            
            if(Calificacion >= 7)
                ScoreApro(Calificacion); 
                //ocultar();
            else 
                ScoreRepro(Calificacion);
                //ocultar();
            console.log("Calificacion: " + Calificacion);
            var botonConf = "<a id=\"botonresultado\" href=\"ActualizarMiCuestionario.html?idCurso=${CursoID}&idUsuario=${UsuarioID}&idCuestionario=${cuestionario.idCuestionario}&calificacion="+Calificacion+"&Evaluacion=1\" '>Confirmar</a>";
            document.getElementById('btnConfirmar').innerHTML = botonConf;
             
        }
        
        function ScoreApro(Calificacion){
            console.log("scoreAPro")
            swal.fire({
                title: "¡Buen trabajo!",
                text: "APROBADO - Calificación: " + Calificacion,
                icon: 'success',
                confirmButtonText: "Ver Respuestas Correctas",
                confirmButtonColor: '#203853'
            }).then((result) => {
                if(result.isConfirmed){
                    //document.location.href = "ActualizarMiCuestionario.html?idCurso=" + ${CursoID} + "&idUsuario=" + ${UsuarioID} + "&idCuestionario=" + ${cuestionario.idCuestionario} + "&Evaluacion=1"; 
                    
                }
            });
            //document.location.href = "ActualizarMiCuestionario.html?idCurso=" + ${CursoID} + "&idUsuario=" + ${UsuarioID} + "&idCuestionario=" + ${cuestionario.idCuestionario} + "&Evaluacion=1";     
            var x = [];
            x = getResp();
            //console.log("resp jsp: " + x);
            console.log(x);
            ocultar(x);
        }
        
        function ScoreRepro(Calificacion){
            console.log("scoreRepro");
            swal.fire({
                title: "¡Esfuerzate más!",
                text: "REPROBADO - Calificación: " + Calificacion,
                icon: 'warning',
                confirmButtonText: "Ver Respuestas Correctas",
                confirmButtonColor: '#203853'
            }).then(function () {
                //document.location.href = "mediacursos.html?idCurso=" + ${CursoID};
                //document.location.href = "respuestasExamen.html?idUsuario=" + ${UsuarioID} + "&idCuestionario=" + ${cuestionario.idCuestionario};
                //document.location.href = "examenuser.html?idCuestionario=" + ${cuestionario.idCuestionario} ;
                //document.location.href = "examenuser.html?idUsuario="+${UsuarioID}+"&idCuestionario="+${cuestionario.idCuestionario};
                
            });
            var x = [];
            x = getResp();
            //console.log("resp jsp: " + x);
            console.log(x);
            ocultar(x);
            //ocultar();
        }
    </script>
  </body>
  <script>
    function cerrarSession() {

        $(location).attr('href', "cerrarSession.html")

    }
</script>
</html>

