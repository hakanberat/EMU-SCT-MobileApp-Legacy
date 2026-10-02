<?php
include_once("connect.php");

$response = array();

$result = mysql_query("INSERT INTO announcement (title,description,date,time,place) VALUES('".$_REQUEST['title']."','".$_REQUEST['description']."','".$_REQUEST['date']."','".$_REQUEST['time']."','".$_REQUEST['place']."' )");
    
if ($result) 
	$response["code"] = 1;
else 
	$response["code"] = 2;
		
echo json_encode($response);
?>