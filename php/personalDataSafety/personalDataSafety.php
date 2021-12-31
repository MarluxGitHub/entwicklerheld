<?php
declare(strict_types=1);
include("user_data.php");

function get_personal_data(string $user_id): array {
    // You Implementation should go here
    $userData = get_user_by_id($user_id);

    if(!has_strong_authetication($user_id)) {
        $userData = anonymise($userData);
    }


    return $userData;
}

function anonymise(array $userData): array {
    $userData['street'] = substr( $userData['street'], 0, 3) . str_repeat("*", strlen( $userData['street']) - 3) ;
    $userData['zip'] = substr( $userData['zip'], 0, 3) . str_repeat("*", strlen( $userData['zip']) - 3) ;
    $userData['city'] = str_repeat("*", strlen( $userData['city']) - 3) . substr( $userData['city'], -3);

    $atPos = strpos($userData['email'], "@");
    $userData['email'] = str_repeat("*", $atPos) . substr($userData['email'], $atPos);
    $userData['phone'] = str_repeat("*", strlen( $userData['phone']) - 3) . substr( $userData['phone'], -3);
    $userData['bank account'] = substr( $userData['bank account'], 0, 2) . str_repeat("*", strlen( $userData['bank account']) - 5) . substr( $userData['bank account'], -3);

    $userData['reference account']['iban'] = substr($userData['reference account']['iban'], 0, 3) . str_repeat("*", strlen($userData['reference account']['iban']) - 6) . substr($userData['reference account']['iban'], -3);
    $userData['reference account']['bic'] = str_repeat("*", strlen( $userData['reference account']['bic']) - 3) . substr( $userData['reference account']['bic'], -3);
    $userData['reference account']['institution'] = substr($userData['reference account']['institution'], 0, 3) . str_repeat("*", strlen($userData['reference account']['institution']) - 6) . substr($userData['reference account']['institution'], -3);

    return $userData;
}

?>