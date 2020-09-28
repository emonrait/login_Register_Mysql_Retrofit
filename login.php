<?php

$email=$_POST ["email"];
$password=$_POST ["password"];

require 'init.php';

if($con){
	$sql ="select * from user_info where email='$email' and password='$password'";
	$result=mysqli_query($con,$sql);
	
	if(mysqli_num_rows($result)>0){
		$row=mysqli_fetch_assoc($result);
		$status="ok";
		$result_code="1";
		$name=$row['name'];
		$email=$row['email'];
		$mobile=$row['mobile'];
		$address=$row['address'];
		echo json_encode(array('status'=>$status,'result_code'=>$result_code,'name'=>$name,'email'=>$email,'mobile'=>$mobile,'address'=>$address));
	}else{
		
			$status="ok";
			$result_code="0";
			echo json_encode(array('status'=>$status,'result_code'=>$result_code));
			
		
		
	}
}else{
	$status="failed";
	echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
	
}
mysqli_close($con);


?>