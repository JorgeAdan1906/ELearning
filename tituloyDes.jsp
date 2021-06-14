<%-- 
    Document   : tituloyDes
    Created on : 14/06/2021, 06:02:38 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="css/kendo.common.min.css" />
        <link rel="stylesheet" href="css/kendo.black.min.css" />
        <link rel="stylesheet" href="css/kendo.default.mobile.min.css" />
        <link rel="stylesheet" href="css/styleadd.css" />
        <link rel="stylesheet" href="css/styleListadoCurso.css" />
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/kendo.all.min.js"></script>
        <title>Nombre y Descripcion</title>
    </head>
    <body>
        <header>
            <nav>

                <img src="imagenes\logo_transparent1.png" height="100%" style="margin-left: 5%;" />
                <div class="box">
                    <input type="text" name="search" placeholder="Buscar cursos" class="src" autocomplete="off">
                </div>
                <div id="perfil">
                    <div class="demo-section k-content">
                        <ul id="panelbar-images"></ul>
                    </div>
                    <script>
                        $("#panelbar-images").kendoPanelBar({
                            dataUrlField: "link",
                            dataSource: [
                                {
                                    text: "USUARIO", imageUrl: "../content/shared/icons/sports/baseball.png",
                                    items: [
                                        {text: "Mis cursos", link: "cursos.html"},
                                        {text: "Mi perfil", link: "misdatosadm.html"},
                                        {text: "Salir", link: "index.html"},
                                    ]
                                },
                            ]
                        });
                    </script>
                </div>
            </nav>
        </header>
        <!-- MODAL AGREGAR ARCHIVOS -->
        <div id="EditDialog1"></div>
        <div id="dialogUsuarios1"></div>
        <div id="example1"></div>
        <button id="undo" style="display:none" class="k-button hide-on-narrow">Agregar Modulo</button>

        <div id="window">
            <!--ACA VA TODO LO DE AGREGAR LOS MODULOS AL BAR-->
            <form>
                <label for="nombre">Nombre</label>
                <br>
                <input type="text" value="Item" id="appendText" class="k-textbox">
                <br><br>
                <label for="nombre">Descripcion</label>
                <br>
                <input type="textArea" name="descripcion">
            </form>
            <br>
            <button class="appendItem k-button">Guardar</button>
        </div>

        <br><br><br><br><br><br><br><br><br><br><br><br>

        <div class="demo-section k-content">
            <ul id="panelbar">
                <li class="k-state-active">

                </li>
            </ul>
        </div>

        <br>
        <div class="botones">
            <button class="removeItem k-button">Eliminar</button>

            <button class="editItem k-button">Modificar</button>

            <button class="addItem k-button">Agregar Archivos</button>

        </div>
        <!--KENDO DE LA VENTANA MODULO-->
        <script>
            $(document).ready(function () {
                var myWindow = $("#window"),
                        undo = $("#undo");

                undo.click(function () {
                    myWindow.data("kendoWindow").open();
                    undo.fadeOut();
                });

                function onClose() {
                    undo.fadeIn();
                }

                myWindow.kendoWindow({
                    width: "600px",
                    title: "Agrega Nuevo Modulo",
                    visible: false,
                    actions: [
                        "Pin",
                        "Minimize",
                        "Maximize",
                        "Close"
                    ],
                    close: onClose
                }).data("kendoWindow").center().close();
            });
        </script>

        <!--KENDO DE ITEMS-->

        <script>
            $(document).ready(function () {
                var getItem = function (target) {
                    var itemIndexes = target.val().split(/[.,]/),
                            rootItem = panelBar.element.children("li").eq(itemIndexes[0]);

                    return itemIndexes.length > 1 ?
                            rootItem.find(".k-group > .k-item").eq(itemIndexes[1]) :
                            rootItem;
                },
                        select = function (e) {
                            if (e.type != "keypress" || kendo.keys.ENTER == e.keyCode)
                                panelBar.select(getItem($("#selectIndex")));
                        },
                        append = function (e) {
                            if (e.type != "keypress" || kendo.keys.ENTER == e.keyCode)
                                panelBar.append({
                                    text: $("#appendText").val()
                                }, panelBar.select());
                        };

                $(".selectItem").click(select);
                $("#selectIndex").keypress(select);
                $(".appendItem").click(append);
                $("#appendText").keypress(append);
                $(".toggleItem").click(function (e) {
                    var item = panelBar.select();
                    panelBar.enable(item, item.hasClass("k-state-disabled"));
                });

                $(".triggerItem").click(function (e) {
                    var item = panelBar.select();

                    if (item.hasClass("k-state-active")) {
                        panelBar.collapse(item);
                    } else {
                        panelBar.expand(item);
                    }
                });

                $(".removeItem").click(function (e) {
                    panelBar.remove(panelBar.select());
                });

            });

            var panelBar = $("#panelbar").kendoPanelBar().data("kendoPanelBar");

        </script>


        <!--MODAL AGREGAR ARCHIVO-->
        <Script>
            function onOpenformulario1() {
                var dialog = $('#EditDialog1');
                dialog.empty();
                dialog.append("<form id='exampleform1'</form>");
                var form = $('#exampleform1')
                form.kendoForm({
                    layout: "grid",
                    grid: {
                        cols: 1,
                        gutter: 20
                    },
                    buttonsTemplate: "",
                    items: [
                        {
                            type: "group",
                            label: "URL (PDF, Video, Test)",
                            layout: "grid",
                            grid: {cols: 1, gutter: 10},

                            items: [
                                {
                                    field: "Nombre",
                                    label: "Nombre:",
                                    validation: {required: true}
                                },
                                {
                                    field: "URL",
                                    label: "URL del archivo:",
                                    validation: {required: true}
                                },
                            ],
                        },
                    ],
                });

                dialog.kendoDialog({
                    width: "450px",
                    title: "AÑADIR ARCHIVOS",
                    modal: true,
                    animation: {
                        open: {
                            effects: "fade:in"
                        },
                        close: {
                            effects: "fade:out"
                        }
                    },
                    actions: [

                        {text: 'Guardar'}]
                });
                dialog.data("kendoDialog").open();
            }

            function onOpenAdministrador() {
                $("#dialogUsuarios1").html("<div><form id='form1' novalidate></form></div>" +
                        "<div id='gridUsuarios1'></div>");
                $("#form1").kendoForm({
                    orientation: "vertical",
                    layout: "grid",
                    grid: {cols: 2, gutter: 10},
                    buttonsTemplate: ""
                });
            }
            $(document).ready(function () {

                $('.addItem').click(function () {
                    onOpenformulario1();
                });

                $('#dialogformulario1').kendoDialog({
                    title: "Grid de ejemplo",
                    width: "1300px",
                    height: "600px",
                    modal: true,
                    open: onOpenformulario1,
                    visible: false,
                    closable: true
                });
            });
        </Script>

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
        <div class='footer-wrapper'>
            <div style='padding-top:15px;padding-bottom:10px; '>
                <center>

                    <a href='https://www.google.com.mx/' target='_blank'><img HSPACE='10'
                                                                              src='https://img.icons8.com/android/24/000000/facebook.png' title='' />
                        <a href='https://www.google.com.mx/' target='_blank'><img HSPACE='10'
                                                                                  src='https://img.icons8.com/android/24/000000/facebook.png' title='' /></a>
                        <a href='https://www.google.com.mx/' target='_blank'><img HSPACE='10'
                                                                                  src='https://img.icons8.com/android/24/000000/facebook.png' title='' /></a>
                        <a href='https://www.google.com.mx/' target='_blank'><img HSPACE='10'
                                                                                  src=' https://img.icons8.com/metro/26/000000/youtube.png' title='' /></a>
                    </a>
                </center>
            </div> Nombre de tu Blog&#169;2017 Todos los derechos reservados
        </div>
    </body>
</html>
