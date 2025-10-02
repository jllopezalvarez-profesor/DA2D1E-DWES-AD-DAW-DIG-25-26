<?php
session_start();

$errors = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $username = $_POST['username'] ?? '';
    $password = $_POST['password'] ?? '';

    // TODO: Validar que no están en blanco ninguno de los dos.

    // Validar usuario y contraseña
    if ($username == 'jl' && $password == 'la') {
        $_SESSION['username'] = $username;
    } else {
        array_push($errors, 'Usuario o contraseña incorrecta');
    }

    // var_dump($errors);
}

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h1>Login</h1>
    <?php if (!empty($errors)): ?>
        <ul>
            <?php foreach ($errors as $error): ?>
                <li><?= $error ?></li>
            <?php endforeach ?>
        </ul>

    <?php endif ?>
    <form method="post">
        <p><label>Usuario: <input type="text" name="username"></label></p>
        <p><label>Contraseña: <input type="password" name="password"></label></p>
        <p><button type="submit">Aceptar</button></p>
    </form>
</body>

</html>