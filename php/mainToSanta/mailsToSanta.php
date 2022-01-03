<?php

declare(strict_types=1);
include("holidays.php");

function calculated_time_to_process(DateTime $time_of_arrival): DateTime
{


    $time_of_arrival = calculateBusinessHours($time_of_arrival);
    $time_of_arrival = calculateWorkDays($time_of_arrival);
    $time_of_arrival = calculateHolidays($time_of_arrival);

    // your implementation should go here
    return $time_of_arrival;
}

function calculateBusinessHours(DateTime $time_of_arrival): DateTime
{
    $hours = intval($time_of_arrival->format('H'));

    if ($hours < 8 || $hours > 15) {
        $time_of_arrival = $time_of_arrival->setTime(8, 0);
    }

    if ($hours > 15) {
        $time_of_arrival = $time_of_arrival->modify('+1 day');
    }

    return $time_of_arrival;
}

function calculateWorkDays(DateTime $time_of_arrival): Datetime
{
    $weekDay = intval($time_of_arrival->format('N'));
    if ($weekDay > 5) {
        $time_of_arrival = $time_of_arrival->modify('+' . 8 - $weekDay . ' day')->setTime(8, 0);
    }

    return $time_of_arrival;
}

function calculateHolidays(DateTime $time_of_arrival): Datetime
{
    global $HOLIDAY_LIST;

    foreach ($HOLIDAY_LIST as $holiday) {
        $day = intval($time_of_arrival->format('d'));
        $month = intval($time_of_arrival->format('m'));
        $year = intval($time_of_arrival->format('Y'));

        if (
            $month >= $holiday['start']['month']
            && $month <= $holiday['end']['month']
            && $day >= $holiday['start']['day']
            && $day <= $holiday['end']['day']
        ) {
            $time_of_arrival = $time_of_arrival->setDate($year, $holiday['end']['month'], $holiday['end']['day'])->setTime(8, 0);
            $time_of_arrival = $time_of_arrival->modify('+1 day');
        }
    }

    return $time_of_arrival;
}
