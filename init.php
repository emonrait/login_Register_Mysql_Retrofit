<?php

$host = "localhost";
$user = "id14990066_wp_16203683479fbd324504db3e9e64c865";
$pass = "Emon@9834955";
$db = "id14990066_wp_16203683479fbd324504db3e9e64c865";

$con = mysqli_connect($host,$user,$pass, $db);

if($con){
   echo "Database Connect Successfull";
}else{
  echo "Database Connect Unsuccessfull";
}

 ?>