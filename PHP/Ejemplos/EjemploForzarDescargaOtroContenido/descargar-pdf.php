<?php

header('Content-Description: Ejemplo de descarga de fichero');    // Descripción
header('Content-Type: application/pdf');        // Tipo de contenido
// Indicar que es un adjunto y nombre del fichero a descargar
header('Content-Disposition: attachment; filename="datos.pdf"');
// TODO: Escribir el fichero en la salida

echo "Hola";
