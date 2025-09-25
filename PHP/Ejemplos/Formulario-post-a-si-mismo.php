<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<?php
$info = $_POST['info'] ?? '';
$accept = $_POST['accept'] ?? '';
$accept = filter_var($accept, FILTER_VALIDATE_BOOL);
$errors = [];
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (empty($info)) {
        $errors[] = 'No has indicado información';
    } elseif (strlen($info) < 10) {
        $errors[] = 'Al menos hay que escribir 10 carcteres';
    }
}

?>

<body>

    <h1>Demo de formulario que se envía a si mismo</h1>

    <?php if (($_SERVER['REQUEST_METHOD'] == 'GET') || (!empty($errors))): ?>
        <!-- Si hay errores los muestro -->
        <?php if (!empty($errors)): ?>
            <ul>
                <?php foreach ($errors as $error): ?>
                    <li><?= $error ?></li>
                <?php endforeach ?>
            </ul>
        <?php endif ?>


        <!-- No uso "action" para que se envíe a si mismo-->
        <form method="post">
            <p><label>Info: <input type="text" name="info" value="<?= $info ?>"></label></p>

            <p> <label> <input type="checkbox" name="accept" value="true" <?= $accept ? 'checked' : '' ?>>
                    Acepto condiciones</label></p>

            <p>

                <button type="submit">Enviar</button>

        </form>
    <?php endif ?>

    <?php if (($_SERVER['REQUEST_METHOD'] == 'POST') && (empty($errors))): ?>
        <p>Valor recibido: <?= $info ?></p>
        <p>¿Ha aceptado? <?= $accept ? 'SI' : 'NO' ?>
        <p>
            <a href="Formulario.php">Volver al formulario</a>

        <?php endif ?>


</body>

</html>