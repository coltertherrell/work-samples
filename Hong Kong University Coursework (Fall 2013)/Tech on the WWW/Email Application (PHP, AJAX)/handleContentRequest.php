<?php
mysql_connect("sophia.cs.hku.hk","jvitello","DKXQ0806") or die("Error 1! ".mysql_error());
mysql_select_db("jvitello") or die("Error 2! ".mysql.error());
$id = $_GET['id'];
$query = "SELECT * FROM emails WHERE emailID='$id'";
$result = mysql_query($query) or die('Error 3! '.mysql_error());

while($row = mysql_fetch_array($result)){
	print "<p align='top'>$row[4]</p>";
}
?>