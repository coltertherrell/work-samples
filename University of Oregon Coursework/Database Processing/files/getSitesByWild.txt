<?php

include('connectionData.txt');

$conn = mysqli_connect($server, $user, $pass, $dbname, $port)
or die('Error connecting to MySQL server.');

$wild = $_GET['wild'];

$query = "SELECT location.name, location.dist, location.depth, location.temp
FROM location JOIN wildlife ON wildlife.loc = location.name
WHERE wildlife.wild_name = ?";

if ($conn->connect_errno) {
    echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
}

if (!($stmt = $conn->prepare($query))) {
    echo "Prepare failed: (" . $conn->errno . ") " . $conn->error;
}
else{


if (!($stmt->bind_param("s",$wild))){
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
				<td>$dist</td>
				<td>$depth</td>
				<td>$temp</td>
			</tr><br>";
}


}


mysqli_close($conn);

?>