<?php
include_once("connect.php");

$response = array();

$result = mysql_query("UPDATE announcement SET title = '".$_REQUEST['title']."', description = '".$_REQUEST['description']."', date = '".$_REQUEST['date']."', time = '".$_REQUEST['time']."', place = '".$_REQUEST['place']."' WHERE id = '".$_REQUEST['id']."'");




if ($result) 
	$response["code"] = 1;
else 
	$response["code"] = 2;
		
echo json_encode($response);
?>