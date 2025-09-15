<html>
<head>
<title>Primera página</title>
</head>
<body>
<h1>Esto es la primera prueba, en versión alternativa</h1>
<?php

echo "<p>";

echo "Hola, esto se ha generado en PHP, pero es otra página";

echo "</p><p>";

echo "Este texto es un segundo echo";

echo '</p>'

?>

<p>Fecha y hora actuales: <?= date(DATE_RFC2822) ?></p>


<?php echo "<p>Este texto es con echo no abreviado</p>"?>
<p>
<?= "Este otro texto se ha hecho con un echo abreviado" ?>
</p>
</body>
</html>
