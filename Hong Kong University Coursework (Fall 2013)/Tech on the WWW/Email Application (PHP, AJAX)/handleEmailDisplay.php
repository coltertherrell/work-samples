<?php
	mysql_connect("sophia.cs.hku.hk","jvitello","DKXQ0806") or die("Error 1! ".mysql_error());
	mysql_select_db("jvitello") or die("Error 2! ".mysql.error());
	$mailbox = $_GET['cur_disp'];
	$page = $_GET['page'];
	$query = "SELECT * FROM emails WHERE mailbox='$mailbox' ORDER BY emailID LIMIT $page,4";
	$result = mysql_query($query) or die('Error 3! '.mysql_error());
	
	if (mysql_num_rows($result) > 0){
		while($row = mysql_fetch_array($result)){
			print "
			<tr>
				<td><input type='checkbox' id=$row[0]></td>
				<td class='display_mail' onclick='openmail($row[0])' style='cursor:pointer;'><b>$row[2]</b></td>
				<td class='display_mail' onclick='openmail($row[0])' style='cursor:pointer;'>$row[1]</td> 
				<td class='display_mail' onclick='openmail($row[0])' style='cursor:pointer;'>$row[3]</td> 
			</tr><br>";
		}
	}
?>