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
    $inicio = 4;
    $fin = 7;
    ?>

    <h1>Tablas de multiplicar del <?= $inicio ?> al <?= $fin ?></h1>


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


</body>

</html>