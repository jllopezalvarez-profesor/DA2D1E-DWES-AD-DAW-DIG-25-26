<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado del pedido de pizza</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<?php
include("Ejercicio09-common.php");

// Obtener distintos parámetros
$dough = $_POST['dough'] ?? '';
$size = $_POST['size'] ?? '';
$base = $_POST['base'] ?? '';
$optionalIngredients = $_POST['optionalIngredients'] ?? [];
$firstName = filter_input(INPUT_POST, 'firstname', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$lastName = filter_input(INPUT_POST, 'lastname', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$address = filter_input(INPUT_POST, 'address', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$telephone = filter_input(INPUT_POST, 'telephone', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$comments = filter_input(INPUT_POST, 'comments', FILTER_SANITIZE_FULL_SPECIAL_CHARS) ?? '';
$paymentMethod = $_POST['paymentmethod'] ?? '';


// Validar parámetros, y acumular errores en array
$errors = [];

if (!array_key_exists($dough, $doughOptions)) {
    array_push($errors, "El tipo de masa elegido ($dough) no es válido.");
}

if (!array_key_exists($size, $sizeOptions)) {
    array_push($errors, "El tamaño elegido ({$size}) no es válido.");
}

if (!array_key_exists($base, $baseOptions)) {
    array_push($errors, "El tamaño elegido ($base) no es válido.");
}

foreach ($optionalIngredients as $optionalIngredient) {
    if (!array_key_exists($optionalIngredient, $optionalIngredientOptions)) {
        array_push($errors, "El ingrediente opcional '$optionalIngredient' no es válido.");
    }
}

if (empty($firstName)) {
    array_push($errors, "No se ha introducido el nombre del cliente.");
}

if (empty($lastName)) {
    array_push($errors, "No se ha introducido el apellido del cliente.");
}

if (empty($address)) {
    array_push($errors, "No se ha introducido la dirección del cliente.");
}

if (empty($telephone)) {
    array_push($errors, "No se ha introducido el teléfono del cliente.");
}

if (empty($email)) {
    array_push($errors, "No se ha introducido el email del cliente.");
} else {
    $validatedEmail = filter_var($email, FILTER_VALIDATE_EMAIL);
    if ($validatedEmail === false) {
        array_push($errors, "El correo electrónico introducido ($email) no es válido.");
    }
}

// Los comentarios son opcionales y no se validan

if (!array_key_exists($paymentMethod, $paymentOptions)) {
    array_push($errors, "El método de pago elegido ($paymentMethod) no es válido.");
}


?>

<body>
    <div class="container">
        <h1>Pide tu pizza</h1>

        <?php if (!empty($errors)): ?>
            <p>Se han producido errores:</p>
            <ul>
                <?php foreach ($errors as $error) {
                    echo "<li>$error</li>";
                } ?>
            </ul>

        <?php else: ?>

            <p>Los datos de tu pedido:</p>
            <ul>
                <li>Tipo de masa: <?= $doughOptions[$dough] ?> </li>
                <li>Tamaño: <?= $sizeOptions[$size] ?> </li>
                <li>Pizza base: <?= $baseOptions[$base] ?> </li>
                <?php if (empty($optionalIngredients)): ?>
                    <li>Sin ingredientes adicionales</li>
                <?php else: ?>
                    <li>
                        Ingredientes adicionales:
                        <ul>
                            <?php foreach ($optionalIngredients as $optionalIngredient): ?>
                                <li><?= $optionalIngredientOptions[$optionalIngredient] ?></li>
                            <?php endforeach ?>
                        </ul>
                    </li>
                <?php endif ?>
                <li>Nombre y apellidos: <?= $firstName ?> <?= $lastName ?></li>
                <li>Dirección: <?= $address ?></li>
                <li>Teléfono: <?= $telephone ?></li>
                <li>Correo electrónico: <?= $email ?></li>
                <li>Método de pago: <?= $paymentOptions[$paymentMethod] ?></li>
                <li>Comentarios: <?= $comments ?></li>
            </ul>
        <?php endif ?>
    </div>
</body>

</html>