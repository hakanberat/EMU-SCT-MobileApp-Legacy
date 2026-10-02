<?php
include_once("connect.php");
$sqlString = "select * from admin";
$rs = mysql_query($sqlString);

if($rs){
   while($objRs = mysql_fetch_assoc($rs)){
      $output[] = $objRs;
   }
   echo json_encode($output);
}

mysql_close();

?>