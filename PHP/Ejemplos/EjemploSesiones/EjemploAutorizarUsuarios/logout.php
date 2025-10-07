<?php
session_start();

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cierre de sesión</title>
</head>

<body>

    <h1>Cerrando sesión de <?= $_SESSION['username'] ?> </h1>
    <?php
    session_regenerate_id(true);

    session_destroy();

    ?>
    <p>Se ha cerrado la sesión de <?= $_SESSION['username'] ?></p>


</body>

</html>