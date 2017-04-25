<?php

$data=file_get_contents('php://input');
$data_decode=json_decode($data,true);

$email=$data_decode['email_key'];

$connection=mysqli_connect('localhost','root','');

mysqli_select_db($connection, 'volunteer');



$result = mysqli_query($connection,"select * from ngo1 where email = '$email' ");

$output[] = mysqli_fetch_assoc($result);

$response['key'] = $output ;

echo json_encode($response);


?>

