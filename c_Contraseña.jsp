<%-- 
    Document   : c_Contraseña
    Created on : 14/06/2021, 05:51:35 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>n_contraseña</title>
        <link rel="stylesheet" href="css/kendo.common.min.css" />
        <link rel="stylesheet" href="css/kendo.black.min.css" />
        <link rel="stylesheet" href="css/kendo.default.mobile.min.css" />
        <script src="js/jquery.min.js"></script> 
        <script src="js/kendo.all.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    </head>
    <body>

        <div id= "logo"> 
            <img src="imagenes\logo_transparent1.png" width="30%" style="margin-left: 5%;" />
        </div>
        <div id="text">
            <h1>Recuperacion de la cuenta</h1>
            <h2>Bienvenido.</h2>
        </div>

        <!-- formulario para iniciar sesion -->
        <div class="form">
            <!-- contenedor (fondo en blanco del login) -->
            <form id="login" action=""></form>
        </div>
        <style>
            body {
                background-color: #2471A3;
            }
            #logo {
                width: 50%;
                height: 50%;
                float: left;
            }  
            .form {
                font: bold 1em sans-serif;
                background: #fff;
                width: 30%;
                height: 100%;
                margin-top: 12%;
                margin-right: 35%;
                border-radius: 2%;
                border: 1px solid #707070;
                padding: 15px;
                float: right;  
            }
            .btn {
                width: 50%;
                margin-bottom: 5px;
                font-size: 22px;
                background:#3acace;
                cursor: pointer;
            }
            #text {
                font: bold sans-serif;
                color: white;
                margin-top: 5%;
                margin-right: 20%;
                float: right;
            }
        </style>

        <script>
            //formulario index
            $(document).ready(function () {
                $("#login").kendoForm({
                    orientation: "vertical",
                    items: [
                        {
                            field: "password",
                            label: "Contraseña:",
                            validation: {required: true},
                            editor: function (container, options) {
                                container.append($("<input type='password' class='k-textbox k-valid' id='Password' name='Password' title='Password' required='required' autocomplete='off' aria-labelledby='Password-form-label' data-bind='value:Password' aria-describedby='Password-form-hint'>"));
                            }
                        },
                        {
                            field: "passwordconfirm",
                            label: "Confirma tu contraseña:",
                            validation: {required: true},
                            editor: function (container, options) {
                                container.append($("<input type='password' class='k-textbox k-valid' id='Password' name='Password' title='Password' required='required' autocomplete='off' aria-labelledby='Password-form-label' data-bind='value:Password' aria-describedby='Password-form-hint'>"));
                            }
                        }

                    ],
                    // "evita que aparezcan los botones de kendo"
                    buttonsTemplate: "",
                    //btn iniciar sesion
                    buttonsTemplate: "<input type = 'submit' class = 'btn' value = 'Finalizar'/>",

                });
            });
        </script>

    </body>
</html>
