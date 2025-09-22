<!DOCTYPE html>
<html lang="es">
<?php

// Crear un array vacío para los errores
$errors = [];

$month = $_GET['m'] ?? '';
$month = filter_var($month, FILTER_VALIDATE_INT, ["options" => ["min_range" => 1, "max_range" => 12]]);
if ($month === false) {
    array_push($errors, "El mes tiene que ser un número entre 1 y 12 ambos incluidos.");
}

$year = $_GET['y'] ?? '';
$year = filter_var($year, FILTER_VALIDATE_INT, ["options" => ["min_range" => 2020, "max_range" => 2030]]);
if ($year === false) {
    array_push($errors, "El año tiene que estar entre el 2020 y el 2030 ambos incluidos");
}



$pageTitle = '';
if (empty($errors)) {
    $pageTitle = "Calendario de $month/$year";
} else {
    $pageTitle = "Calendario - Se han producido errores";
}


?>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?= $pageTitle ?></title>
    <style>
        table {
            border-collapse: collapse;
        }

        td {
            border: 1px solid red;
        }
    </style>
</head>

<body>
    <?php if (empty($errors)) { ?>
        <h1> Calendario de <?= "$month/$year" ?></h1>
        <?php
        $firstDayOfMonth = DateTime::createFromFormat("Y-m-d", "$year-$month-1");
        $numberOfDaysInMonth = $firstDayOfMonth->format("t");
        $startWeekDay = $firstDayOfMonth->format("w");
        ?>


        <table>
            <tr>
                <?php
                $currentDay = 1;
                $daysOfCurrentWeek = 0;

                while ($daysOfCurrentWeek <  $startWeekDay - 1) {
                    echo "<td></td>";
                    $daysOfCurrentWeek++;
                }

                while ($currentDay <= $numberOfDaysInMonth) {
                    echo "<td>$currentDay</td>";
                    $currentDay++;
                    $daysOfCurrentWeek++;
                    if ($daysOfCurrentWeek >= 7) {
                        echo "</tr><tr>";
                        $daysOfCurrentWeek = 0;
                    }
                }
                if ($daysOfCurrentWeek > 0)
                    while ($daysOfCurrentWeek++ < 7) {
                        echo "<td></td>";
                    }


                ?>
            </tr>

        </table>
    <?php
    } else {
    ?>
        <h1> Calendario - Con errores</h1>
        <p>Se han producido <?= sizeof($errors) ?> errores:</p>
        <ul>
            <?php foreach ($errors as $error): ?>
                <li><?= $error ?></li>
            <?php endforeach ?>
        </ul>

    <?php
    }
    ?>



</body>

</html>