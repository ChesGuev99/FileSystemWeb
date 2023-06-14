<%-- 
    Document   : login
    Created on : Jun 14, 2023, 11:59:21 AM
    Author     : anagu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            .btn-color{
                background-color: #0e1c36;
                color: #fff;
            }

            .profile-image-pic{
                height: 200px;
                width: 200px;
                object-fit: cover;
            }

            .cardbody-color{
                background-color: #ebf2fa;
            }

            a{
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <h2 class="text-center text-dark mt-5">Ingresa tu Usuario</h2>
                    <div class="card my-5">
                        <form id="loginForm" class="card-body cardbody-color p-lg-5">

                            <div class="text-center">
                                <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                                     width="200px" alt="profile">
                            </div>

                            <div class="mb-3">
                                <input type="text" class="form-control" id="Username" aria-describedby="emailHelp"
                                       placeholder="User Name">
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="password" placeholder="password">
                            </div>
                            <div class="text-center"><button type="button" class="btn btn-color px-5 mb-5 w-100" onclick="login()">Iniciar Sesión</button></div>
                            <div id="emailHelp" class="form-text text-center mb-5 text-dark">¿No eres usuario?
                                <a href="#" class="text-dark fw-bold"> Crea una cuenta</a>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->

        <script>
            function login() {
                // Perform any necessary validation or processing

                // Get the form element
                
                var form = document.getElementById("loginForm");
                console.log("Si esta llegando al login :v");
                // Redirect to the desired page
                window.location.href = "${pageContext.request.contextPath}/Index";
            }
        </script>


        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>


