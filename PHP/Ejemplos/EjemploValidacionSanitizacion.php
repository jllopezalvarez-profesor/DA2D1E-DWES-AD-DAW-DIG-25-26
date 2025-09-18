<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejemplos de validación y sanitización</title>
</head>

<body>
    <h1>Sanitización de salida con htmlspecialchars</h1>
    <?php
    $variableConHtml = "Esto es un texto que no debería llevar un <p> dentro."
    ?>
    <h2>Párrafo escrito sin sanitización: </h2>
    <p>
        <?= $variableConHtml ?>
    </p>
    <h2>Párrafo escrito con sanitización: </h2>
    <p>
        <?= htmlspecialchars($variableConHtml) ?>
    </p>


    <?php
    $edad = filter_input(INPUT_POST, 'edad', FILTER_VALIDATE_INT, ["options" => ["min_range" => 10, "max_range" => 30]]);
    if ($edad === false) {
        echo "La edad no es correcta";
    } else {
        echo "<p>Edad introducida: $edad</p>";
    }

    ?>

</body>

</html>