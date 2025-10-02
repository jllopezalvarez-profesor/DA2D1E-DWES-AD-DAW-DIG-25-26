<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejemplo de acceso a sesiones sin sesión</title>
</head>

<body>
    <h1>Ejemplo de acceso a sesiones sin sesión</h1>
    <p>Valor de la variable de sesión "usuario":<?= $_SESSION['usuario'] ?></p>
</body>

</html>