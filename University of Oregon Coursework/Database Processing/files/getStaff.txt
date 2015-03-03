<?php

include('connectionData.txt');

$conn = mysqli_connect($server, $user, $pass, $dbname, $port)
or die('Error connecting to MySQL server.');

$cert = $_GET['staff'];


$query = "SELECT person.fname, person.lname, UCASE(cert)
FROM instructor JOIN person ON person.pnum = instructor.pnum
WHERE instructor.cert = ?;";

if ($conn->connect_errno) {
    echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
}

if (!($stmt = $conn->prepare($query))) {
    echo "Prepare failed: (" . $conn->errno . ") " . $conn->error;
}
else{


if (!($stmt->bind_param("s",$cert))){
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->execute()) {
     echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
}


if (!$stmt->bind_result($fname, $lname, $cert)) {
	echo "Bind Failed: (" . $stmt->errno . ") " . $stmt->error;
}



while ($stmt->fetch()) {
	print "
			<tr>
				<td>$fname $lname</td>
				<td>$cert</td>
				
			</tr><br>";
}


}


mysqli_close($conn);

?>