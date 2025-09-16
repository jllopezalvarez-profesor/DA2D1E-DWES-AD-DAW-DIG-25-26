<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 1 - Tabla de multiplicar como lista</title>
</head>

<body>
    <h1>Ejercicio 1 - Tabla de multiplicar como lista</h1>

    <?php $numero = 7 ?>

    <h2>Generado con echo en un for</h2>
    <ul>
        <?php
        for ($otroNumero = 0; $otroNumero <= 10; $otroNumero++) {
            $producto = $numero * $otroNumero;
            echo "<li>{$numero} x {$otroNumero} = {$producto}</li>";
            // echo "<li>" . $numero . " x " . $otroNumero . " = " . $producto . "</li>";
        }
        ?>
    </ul>

    <h2>Generado con html en un for</h2>
    <ul>
        <?php
        for ($otroNumero = 0; $otroNumero <= 10; $otroNumero++) { ?>
            <li><?php echo $numero ?> x
                <?php echo $otroNumero ?> =
                <?php echo $numero * $otroNumero ?></li>
        <?php
        }
        ?>
    </ul>

    <h2>Generado con html en un for con "dos puntos"</h2>
    <ul>
        <?php for ($otroNumero = 0; $otroNumero <= 10; $otroNumero++): ?>
            <li><?php echo $numero ?> x <?php echo $otroNumero ?> = <?php echo $numero * $otroNumero ?></li>
        <?php endfor ?>
    </ul>








    <ul>
        <li>n x m = producto</li>
    </ul>


</body>

</html>