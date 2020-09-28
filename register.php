<?php

$name=$_POST ["name"];
$email=$_POST ["email"];
$mobile=$_POST ["mobile"];
$address=$_POST ["address"];
$password=$_POST ["password"];

require 'init.php';

if($con){
	$sql ="select * from user_info where email='$email'";
	$result=mysqli_query($con,$sql);
	
	if(mysqli_num_rows($result)>0){
		$status="Exist";
		$result_code="0";
		echo json_encode(array('status'=>$status,'result_code'=>$result_code));
	}else{
		$sql ="insert into user_info (name,email,mobile,address,password) values ('$name','$email','$mobile','$address','$password')";
		if(mysqli_query($con,$sql)){
			$status="ok";
			$result_code="1";
			echo json_encode(array('status'=>$status,'result_code'=>$result_code));
			
		}else{
			$status="failed";
			echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
			
		}
		
	}
}else{
	$status="failed";
	echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
	
}
mysqli_close($con);


?>