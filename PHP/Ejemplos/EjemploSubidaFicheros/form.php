<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejemplo de subida de ficheros</title>
</head>

<body>
    <h1>Ejemplo de subida de ficheros</h1>
    <form method="post" action="process-file.php" enctype="multipart/form-data">
        <p><label>Info: <input type="text" name="info"></label></p>

        <p><label>Documento: <input type="file" name="document" accept="application/pdf"></label></p>




        <p><button type="submit">Enviar fichero</button></p>
    </form>
</body>

</html>