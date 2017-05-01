<?php
$data =file_get_contents('php://input');

$data_decode = json_decode($data, true);
  
$ngo_name=$data_decode['n'];

$date=$data_decode['d']; 
$message=$data_decode['m'];
$volunteer_id=$data_decode['v'];



$connection =mysqli_connect('localhost', 'root','');

mysqli_select_db($connection,'volunteer');



$result = mysqli_query($connection,"insert into feedback(ngo_name,  date, message, volunteer_id)values ('$ngo_name' ,  '$date', '$message','$volunteer_id ' )");

if($result){
$response['key']="1";
echo json_encode($response);
}

else{
	$response['key']="0";
    echo json_encode($response);
}

	
	

?>