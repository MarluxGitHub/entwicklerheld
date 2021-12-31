<?php
declare(strict_types=1);

function climbingStairs(int $numberOfStairs) {
    if($numberOfStairs <= 0) return 0;

    $dp = [0,1,2];

    for($i = 3; $i <= $numberOfStairs; $i++) {
        $dp[$i] = $dp[$i-1] + $dp[$i-2];
    }

    return $dp[$numberOfStairs];
}