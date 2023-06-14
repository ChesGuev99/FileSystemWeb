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

        var inputText = document.getElementById("textInput").value.trim();
        var firstWord = inputText.split(" ")[0];
        var otherWords = inputText.split(" ").slice(1);

        var requestData = {
          comando: firstWord,
          palabras: otherWords
        };

        fetch("${pageContext.request.contextPath}/Index", {
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
  </script>
</head>
<body>
  <textarea id="textArea" readonly>${output}
        ¡Bienvenido! Por favor digite sus datos.
  </textarea>
  <form id="form" method="post" action="${pageContext.request.contextPath}/processInput">
    <input type="text" id="textInput" name="input" placeholder="Ingrese sus instrucciones aquí" onkeypress="handleKeyPress(event)">
  </form>
</body>
</html>
