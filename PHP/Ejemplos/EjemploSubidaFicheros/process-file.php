<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Procesamiento de ficheros subidos</title>
</head>

<body>
    <h1>Procesamiento de ficheros subidos</h1>

    <p>Datos recibidos en $_POST:</p>
    <p><?= var_dump($_POST) ?></p>


    <p>Datos recibidos en $_FILES:</p>
    <p><?= var_dump($_FILES) ?></p>


    <p>¿Qué hay en $_FILES['algo'], que no existe (no hay un input file con nombre "algo")?
        <?= var_dump($_FILES['algo']) ?></p>

    <p>¿Se ha subido correctamente? <?= (($_FILES['document']['error'] != UPLOAD_ERR_OK) ? 'NO' : 'SI') ?></p>

    <p>
        <?php
        // Directorio de destino
        // $destDir = __DIR__; // Ok, en local... en el servidor ya veríamos
        $destDir = '/etc/netplan'; // Fallaría, a no ser que se ejecute php -S con usuario ROOT


        // Mover el fichero al directorio actual
        if (move_uploaded_file($_FILES['document']['tmp_name'], $destDir . '/' . 'fichero.pdf')) {
            echo 'Movido correctamente';
        } else {
            echo 'No se ha podido mover el fichero.';
        }

        ?>
    </p>

    <p><button type="button" onclick="history.back()">Volver</button></p>
    <p><a href="#" onclick="event.preventDefault();history.back()">Volver (con enlace - v1) </p>
    <p><a href="javascript:history.back()">Volver (con enlace - v2) </p>

</body>

</html>