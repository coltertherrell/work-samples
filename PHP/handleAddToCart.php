<?php 
/*
Author: Sam Vitello
Uses sessions to store order information 
*/


	session_start();
	
	if (!isset($_SESSION['order'])){
		$_SESSION['order'] = array();
	}
	
	$orders = $_GET['order_amount'];

	for ($i = 0; $i < count($orders); ++$i){
		if ($orders[$i] > 0) {
			$temp = $_SESSION['items'][$i];
			if (isset($_SESSION['order'][$temp])){
				$_SESSION['order'][$temp] = $_SESSION['order'][$temp]+$orders[$i];
			}
			else{
				$_SESSION['order'][$temp] = $orders[$i];
			}
		}
		$count++;
	
	}
	
	/*foreach ($_SESSION['order'] as $key => $value){
		print "$key : $value"; 
	}*/
	
	
?>