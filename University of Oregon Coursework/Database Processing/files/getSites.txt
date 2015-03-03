<?php

include('connectionData.txt');

$conn = mysqli_connect($server, $user, $pass, $dbname, $port)
or die('Error connecting to MySQL server.');

$dist = $_GET['dist'];
$temp = $_GET['temp'];
$depth = $_GET['depth'];

$query = "SELECT name, dist, depth, temp
FROM location 
WHERE dist < ? AND depth < ? AND temp > ?";

if ($conn->connect_errno) {
    echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
}

if (!($stmt = $conn->prepare($query))) {
    echo "Prepare failed: (" . $conn->errno . ") " . $conn->error;
}
else{


if (!($stmt->bind_param("iii",$dist,$depth,$temp))){
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->execute()) {
     echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->bind_result($name, $dist, $depth, $temp)) {
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}



while ($stmt->fetch()) {
	print "
			<tr>
				<td>$name</td>
				<td>$dist miles</td>
				<td>$depth ft.</td>
				<td>$temp degrees</td>
			</tr><br>";
}


}


mysqli_close($conn);

?>