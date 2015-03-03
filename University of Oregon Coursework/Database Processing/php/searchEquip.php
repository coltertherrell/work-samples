<?php

include('connectionData.txt');

$conn = mysqli_connect($server, $user, $pass, $dbname, $port)
or die('Error connecting to MySQL server.');

$type = $_GET['type'];
$dt_in = $_GET['date_in'];
$dt_out = $_GET['date_out'];

$query = "SELECT equipment.num, equipment.type, equipment.size, equipment.condition, rnt.dateout, CONCAT('$',et.cost)
FROM equipment_type et JOIN equipment USING (type)
LEFT JOIN 
	(SELECT eq_num as num, dateout, dateret
	FROM rental
	GROUP BY num) rnt USING (num)
WHERE type=? AND ((rnt.dateout > DATE(?) OR (rnt.dateret < DATE(?))) OR rnt.dateout IS NULL);";

if ($conn->connect_errno) {
    echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
}

if (!($stmt = $conn->prepare($query))) {
    echo "Prepare failed: (" . $conn->errno . ") " . $conn->error;
}
else{


if (!($stmt->bind_param("sss",$type,$dt_in,$dt_out))){
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->execute()) {
     echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->bind_result($num, $type, $size, $cond,$dt,$cost)) {
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}



while ($stmt->fetch()) {
	print "
			<tr>
				<td>$num</td>
				<td>$type</td>
				<td>$size</td>
				<td>$cond</td>
				<td>$dt</td>
				<td>$cost</td>
			</tr><br>";
}


}


mysqli_close($conn);

?>