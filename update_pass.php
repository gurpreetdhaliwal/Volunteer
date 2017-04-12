<?php

$data=file_get_contents('php://input');
$data_decode=json_decode($data,true);

$email=$data_decode['e'];
$newpassword=$data_decode['n'];


$connection=mysqli_connect('localhost','root','');

mysqli_select_db($connection, 'volunteer');



$result = mysqli_query($connection,"update ngo1 set password = '$newpassword' where email = '$email'");

if($result){
$response['key']="done";
echo json_encode($response);
}

else {
	$response['key']="not done";
echo json_encode($response);

}
	
	
?>

