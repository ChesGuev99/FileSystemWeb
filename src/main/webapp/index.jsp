<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <title>Consola</title>
  <style>
    body {
      background-color: #999999;
    }
    
    #textInput {
      font-family: Consolas;
      font-size: 12px;
      width: 100%;
      height: 50px;
      box-sizing: border-box;
    }
    
    #textArea {
      font-family: Consolas;
      font-size: 12px;
      width: 100%;
      height: 480px;
      box-sizing: border-box;
      resize: none;
    }
  </style>
  <script>
    var accumulatedText = "${output}";

    function handleKeyPress(event) {
      if (event.keyCode === 13) {
        event.preventDefault();
        //window.location.href = "${pageContext.request.contextPath}/Login";
        var urlParams = new URLSearchParams(window.location.search);
        var username = urlParams.get('username');
        var memory = urlParams.get("memory");
        
        var inputText = document.getElementById("textInput").value.trim();
        var firstWord = inputText.split(" ")[0];
        var otherWords = inputText.split(" ").slice(1);
        document.getElementById("textInput").value = "";
        var requestData = {
          user: username,
          memory: memory,
          command: firstWord,
          parameters: otherWords
        };

        fetch("${pageContext.request.contextPath}/ProcessCommand", {
            method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(requestData)
        })
          .then(response => response.text())
          .then(data => {
            var newText = accumulatedText + "\n" + inputText + "\n" + data;
            document.getElementById("textArea").value = newText;
            accumulatedText = newText;
          })
          .catch(error => {
            var newText = accumulatedText + "\n" + inputText + "\n" + error;
            document.getElementById("textArea").value = newText;
            accumulatedText = newText;
          });
      }
    }

    // Función para cerrar sesión
    function logout() {
      window.location.href = "${pageContext.request.contextPath}/Login";
    }
  </script>
</head>
<body>
  <textarea id="textArea" readonly>${output}
        ¡Bienvenido! Por favor digite sus datos.
  </textarea>

  <span id="usernameLabel" class="text-dark"></span>

  <form id="form" method="post">
    <input type="text" id="textInput" name="input" placeholder="Ingrese sus instrucciones aquí" onkeypress="handleKeyPress(event)">
    <button type="button" class="btn btn-danger mt-3" onclick="logout()">Cerrar sesión</button>
  </form>

  <script>
    // Obtiene el nombre de usuario del parámetro de la URL
    var urlParams = new URLSearchParams(window.location.search);
    var username = urlParams.get('username');

    // Muestra el nombre de usuario en la etiqueta correspondiente
    var usernameLabel = document.getElementById("usernameLabel");
    usernameLabel.textContent = "Bienvenido, " + username;
  </script>
</body>
</html>
