<?php

declare(strict_types=1);

function calculate_easter_sunday(int $year): DateTime
{

    $G = $year % 19;
    $C = (int)($year / 100);
    $H = (int)($C - (int)($C / 4) - (int)((8 * $C + 13) / 25) + 19 * $G + 15) % 30;
    $I = (int)$H - (int)($H / 28) * (1 - (int)($H / 28) * (int)(29 / ($H + 1)) * ((int)(21 - $G) / 11));
    $J = ($year + (int)($year / 4) + $I + 2 - $C + (int)($C / 4)) % 7;
    $L = $I - $J;
    $m = 3 + (int)(($L + 40) / 44);
    $d = (int)($L + 28 - 31 * ((int)($m / 4)));

    $dateTime = new DateTime();
    $dateTime->setDate($year, $m, $d);

    return $dateTime;
}

function calculate_easter_sunday(int $year): array
{
    $sunday = calculate_easter_days($year);

    $friday = clone $sunday;
    $monday = clone $sunday;

    $friday->modify('-2 day');
    $monday->modify('+1 day');

    return [$friday, $sunday, $monday];
}
