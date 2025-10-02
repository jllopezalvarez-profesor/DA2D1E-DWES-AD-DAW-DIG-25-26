<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fijar variable de sesión</title>
</head>

<body>
    <h1>Fijar variable de sesión</h1>
    <p>Esta página fija la variable de sesión 'usuario' con un valor concreto</p>
    <?php
    $_SESSION['usuario'] = 'jllopezalvarez';
    ?>
    <p>Valor fijado en la sesión: <?= $_SESSION['usuario'] ?></p>

</body>

</html>