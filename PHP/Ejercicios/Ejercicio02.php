<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 2 - Tabla de multiplicar como columna</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<body>
    <h1>Ejercicio 2 - Tabla de multiplicar como columna</h1>

    <?php $numero = 8 ?>

    <h2>Tabla de multiplicar del número <?= $numero ?></h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Operación</th>
            </tr>
        </thead>
        <?php for ($otroNumero = 0; $otroNumero <= 10; $otroNumero++): ?>
            <tr>
                <td> <?php echo $numero ?> x <?php echo $otroNumero ?> = <?php echo $numero * $otroNumero ?></td>
            </tr>
        <?php endfor ?>
    </table>








</body>

</html>