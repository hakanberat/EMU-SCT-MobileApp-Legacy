<?php
include_once("connect.php");

$response = array();

$result = mysql_query("DELETE FROM contact WHERE id = '".$_REQUEST['id']."' ");


if ($result) 
	$response["code"] = 1;
else 
	$response["code"] = 2;
		
echo json_encode($response);
?>