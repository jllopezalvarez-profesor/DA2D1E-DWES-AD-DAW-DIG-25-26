<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pide tu pizza</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<?php
include("Ejercicio09-common.php");
?>

<body>
    <div class="container">
        <h1>Pide tu pizza</h1>
        <form method="post" action="Ejercicio09.php" novalidate>
            <fieldset>
                <legend>Configura tu pizza</legend>
                <div class="row">
                    <div class="col-12 col-lg-4 form-group">
                        <label for="dough" class="form-label">Tipo de masa</label>
                        <select class="form-select" id="dough" name="dough" required>
                            <option value="" selected>Selecciona el tipo de masa...</option>
                            <?php foreach ($doughOptions as $value => $text): ?>
                                <option value="<?= $value ?>"><?= $text ?></option>
                            <?php endforeach ?>
                        </select>
                    </div>
                    <div class="col-12 col-lg-4 form-group">
                        <label for="size" class="form-label">Tamaño</label>
                        <select class="form-select" id="size" name="size" required>
                            <option value="" selected>Selecciona el tamaño...</option>
                            <?php foreach ($sizeOptions as $value => $text): ?>
                                <option value="<?= $value ?>"><?= $text ?></option>
                            <?php endforeach ?>
                        </select>
                    </div>
                    <div class="col-12 col-lg-4 form-group">
                        <label for="base" class="form-label">Pizza base</label>
                        <select class="form-select" id="base" name="base" required>
                            <option value="" selected>Selecciona tu pizza base...</option>
                            <?php foreach ($baseOptions as $value => $text): ?>
                                <option value="<?= $value ?>"><?= $text ?></option>
                            <?php endforeach ?>
                        </select>
                    </div>
                </div>
                <fieldset class="col-12 form-group">
                    <legend>Ingredientes adicionales</legend>
                    <?php foreach ($optionalIngredientOptions as $value => $text): ?>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" value="<?= $value ?>" id="optional-ingredient-<?= $value ?>" name="optionalIngredients[]">
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

    </div>
</body>

</html>