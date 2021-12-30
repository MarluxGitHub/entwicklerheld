<?php

declare(strict_types=1);

function fizzbuzz(int $number)
{
    $response = "";
    $response .= ($number % 3 === 0) ? "fizz" : "";
    $response .= ($number % 5 === 0) ? "buzz" : "";

    return $response ?: $number;
}
