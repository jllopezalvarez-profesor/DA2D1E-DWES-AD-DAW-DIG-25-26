<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 3</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<body>
    <?php
    $inicio = $_GET['inicio'];
    $fin = $_GET['fin'];
    ?>
    <ul>
        <li>isset($_GET['inicio']): <?php var_dump(isset($_GET['inicio'])) ?></li>
        <li>$inicio: <?php var_dump($inicio) ?></li>
        <li>isset($inicio): <?php var_dump(isset($inicio)) ?></li>
        <li>empty($inicio): <?php var_dump(empty($inicio)) ?></li>
    </ul>

    <h1>Tablas de multiplicar del <?= $inicio ?> al <?= $fin ?></h1>

    <?php
    $inicio = $_GET['inicio'] ?? '';
    $inicio = filter_var($inicio, FILTER_VALIDATE_INT);
    $fin = $_GET['fin'] ?? '';
    $fin = filter_var($fin, FILTER_VALIDATE_INT);
    if ($inicio === false || $fin === false) {

        echo "<p>Error, alguno de los dos números no se ha recibido o no es un número entero.</p>";
    } elseif ($inicio > $fin) {
        echo " <p>Los números tienen que estar ordenados</p>";
    } else { ?>

        <p> Ok, seguimos...</p>


        <table class="table table-striped">
            <tr>
                <th scope="col">cabecera</th>
                <?php for ($numero = $inicio; $numero <= $fin; $numero++): ?>
                    <th scope="col"><?= $numero  ?> </td>
                    <?php endfor ?>
            </tr>
            <?php for ($operando = 0; $operando <= 10; $operando++): ?>
                <tr>
                    <th scope="row"><?= $operando ?></th>
                    <?php for ($numero = $inicio; $numero <= $fin; $numero++): ?>
                        <td><?= ($numero * $operando) ?> </td>
                    <?php endfor ?>
                </tr>
            <?php endfor ?>

        </table>
    <?php
    } ?>


</body>

</html>