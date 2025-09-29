<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pide tu pizza</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<?php
include("Ejercicio10-common.php");

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

// Inicialmente no hay errores, incluido cuando sea un GET
$errors = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Validar parámetros, y acumular errores en array

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
}

?>


<body>
    <div class="container">
        <h1>Pide tu pizza</h1>


        <?php if (($_SERVER['REQUEST_METHOD'] == 'GET') || (!empty($errors))): ?>


            <?php if (!empty($errors)): ?>
                <p>Se han producido errores:</p>
                <ul>
                    <?php foreach ($errors as $error) {
                        echo "<li>$error</li>";
                    } ?>
                </ul>

            <?php endif ?>



            <form method="post" novalidate>
                <fieldset>
                    <legend>Configura tu pizza</legend>
                    <div class="row">
                        <div class="col-12 col-lg-4 form-group">
                            <label for="dough" class="form-label">Tipo de masa</label>
                            <select class="form-select" id="dough" name="dough" required>
                                <option value="" selected>Selecciona el tipo de masa...</option>
                                <?php foreach ($doughOptions as $value => $text): ?>
                                    <option value="<?= $value ?>" <?= ($value == $dough) ? 'selected' : '' ?>><?= $text ?></option>
                                <?php endforeach ?>
                            </select>
                        </div>
                        <div class="col-12 col-lg-4 form-group">
                            <label for="size" class="form-label">Tamaño</label>
                            <select class="form-select" id="size" name="size" required>
                                <option value="" selected>Selecciona el tamaño...</option>
                                <?php foreach ($sizeOptions as $value => $text): ?>
                                    <option value="<?= $value ?>" <?= ($value == $size) ? 'selected' : '' ?>><?= $text ?></option>
                                <?php endforeach ?>
                            </select>
                        </div>
                        <div class="col-12 col-lg-4 form-group">
                            <label for="base" class="form-label">Pizza base</label>
                            <select class="form-select" id="base" name="base" required>
                                <option value="" selected>Selecciona tu pizza base...</option>
                                <?php foreach ($baseOptions as $value => $text): ?>
                                    <option value="<?= $value ?>" <?= ($value == $base) ? 'selected' : '' ?>><?= $text ?></option>
                                <?php endforeach ?>
                            </select>
                        </div>
                    </div>
                    <fieldset class="col-12 form-group">
                        <legend>Ingredientes adicionales</legend>
                        <?php foreach ($optionalIngredientOptions as $value => $text): ?>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" value="<?= $value ?>"
                                    id="optional-ingredient-<?= $value ?>"
                                    name="optionalIngredients[]"
                                    <?= (in_array($value, $optionalIngredients, true)) ? 'checked' : '' ?>>
                                <label class="form-check-label" for="optional-ingredient-<?= $value ?>">
                                    <?= $text ?>
                                </label>
                            </div>
                        <?php endforeach ?>
                    </fieldset>
                </fieldset>
                <fieldset>
                    <legend>Datos de entrega</legend>
                    <div class="mb-3">
                        <label for="firstname" class="form-label">Nombre</label>
                        <input type="text" class="form-control" name="firstname" id="firstname" required>
                    </div>
                    <div class="mb-3">
                        <label for="lastname" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" name="lastname" id="lastname" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Dirección completa</label>
                        <input type="text" class="form-control" name="address" id="address" required>
                    </div>
                    <div class="mb-3">
                        <label for="telephone" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" name="telephone" id="telephone" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" class="form-control" name="email" id="email" placeholder="abc@mail.com" required>
                    </div>
                    <div class="mb-3">
                        <label for="comments" class="form-label">Observaciones</label>
                        <textarea class="form-control" name="comments" id="comments"></textarea>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Método de pago</legend>
                    <div class="mb-3">
                        <label for="paymentmethod" class="form-label">Tamaño</label>
                        <select class="form-select form-select-lg" name="paymentmethod" id="paymentmethod" required>
                            <option value="" selected>Selecciona la forma de pago</option>
                            <?php foreach ($paymentOptions as $value => $text): ?>
                                <option value="<?= $value ?>"><?= $text ?></option>
                            <?php endforeach ?>
                        </select>
                    </div>

                </fieldset>

                <p>
                    <button type="submit" class="btn btn-primary" name="submitAction" value="A">
                        Realizar
                        pedido (A)</button>
                    <button type="submit" class="btn btn-primary" name="submitAction" value="B">
                        Realizar
                        pedido (B)</button>
                </p>



            </form>
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