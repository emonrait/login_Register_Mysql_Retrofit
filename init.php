<?php

$host = "localhost";
$user = "root";
$pass = "";
$db = "userdb";

$con = mysqli_connect($host,$user,$pass, $db);

if($con){
   echo "ok";
}else{
  echo "no";
}

 ?>