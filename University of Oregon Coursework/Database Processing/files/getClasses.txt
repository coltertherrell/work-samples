<?php

include('connectionData.txt');

$conn = mysqli_connect($server, $user, $pass, $dbname, $port)
or die('Error connecting to MySQL server.');

$class = $_GET['class'];

$query = "SELECT cert, CONCAT(MONTH(date),'-',DAY(date), '-',YEAR(date)) as date, CONCAT('$',cost) as cost, UCASE(loc_name) as location
FROM class WHERE cert = ?";

if ($conn->connect_errno) {
    echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
}

if (!($stmt = $conn->prepare($query))) {
    echo "Prepare failed: (" . $conn->errno . ") " . $conn->error;
}
else{


if (!($stmt->bind_param("s",$class))){
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->execute()) {
     echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->bind_result($cert, $date, $cost, $loc)) {
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}



while ($stmt->fetch()) {
	print "
			<tr>
				<td>$cert</td>
				<td>$date</td>
				<td>$loc</td>
				<td>$cost</td>
			</tr><br>";
}


}


mysqli_close($conn);

?>