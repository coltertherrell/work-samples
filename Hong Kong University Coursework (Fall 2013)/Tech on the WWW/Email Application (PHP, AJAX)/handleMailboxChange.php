<?php
mysql_connect("sophia.cs.hku.hk","jvitello","DKXQ0806") or die("Error 1! ".mysql_error());
mysql_select_db("jvitello") or die("Error 2! ".mysql.error());
$box = $_GET['box'];
$id1 = $_GET['id1'];
$id2 = $_GET['id2'];
$id3 = $_GET['id3'];
$id4 = $_GET['id4'];

if ($box == 'forever'){
	$query = "DELETE FROM emails WHERE emailID='$id1' OR emailID='$id2' OR emailID='$id3' OR emailID='$id4'";
	mysql_query($query) or die('Error 3! '.mysql_error());
	}
else{
	$query = "UPDATE  emails SET mailbox='$box' WHERE emailID='$id1' OR emailID='$id2' OR emailID='$id3' OR emailID='$id4'";
	mysql_query($query) or die('Error 3! '.mysql_error());
}
?>