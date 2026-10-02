<?php
include_once("connect.php");

$response = array();

$result = mysql_query("INSERT INTO contact (name,email,msg) VALUES('".$_REQUEST['name']."','".$_REQUEST['email']."','".$_REQUEST['msg']."' )");
    
if ($result) 
	$response["code"] = 1;
else 
	$response["code"] = 2;
		
echo json_encode($response);
?>