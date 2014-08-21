<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!Javascript script to display date modified found on the web: http://www.dynamicdrive.com/forums/archive/index.php/t-9510.html>


<html>
	<head>
		<meta http-equiv="Content-Type" Content="text/html; Charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="mystyle.css">
		<title> File Sharing Box </title>
		
		<script type="text/javascript">
			function getLastMod(){
				var myFrm = document.getElementById("myIframe");
				var lastModif = new Date(myFrm.contentWindow.document.lastModified);
				document.getElementById('LastModified').innerHTML = "File Last Modified On: " + lastModif.toLocaleString();
			}
		</script>
	
	</head>
  
	<body>
		<div class="header"></div>
		<div class="divider"></div>
		<div id="menu">
			<ul>
				<li> <A HREF="index.html">Home </A> </li>
				<li> <A HREF="files.php">Files </A> </li>
				<li> <A HREF="media.html">Media </A> </li>
			</ul>	
		</div>
		
		<div class="divider"></div>
		
		<div id="content">
			<div>
				<h1>Download Files</h1>
				<div class="divider"></div>
			</div>
			<table width=900 align="center" border=1 id="layered">
				
				<thead>
					<th> File </th>
					<th> Date Modified </th>
				</thead>
				
				<ul>
					<tr>
						<td>
							<li>
								<A HREF="pictures/dim_sum.jpg">Dim Sum</A>
							</li>
						</td>
						<td>
							<p>
							<?php
							$last_mod = filemtime("pictures/dim_sum.jpg");
							$to_print = date("j/m/y H:i", $last_mod);
							echo $to_print";
							?>
							</p>
							
						</td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/beer_olympics.jpg">Beer Olympics</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/boat.jpg">Boat Ride To Cheung Chau</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/city_streets.jpg">City Streets at Night</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/hku">Hong Kong University</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/josh_game.jpg">Gaming Arcade</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/parfait.jpg">Giant Parfait</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/sophia_sep_26.jpg">Fun at LKF</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/sticker.jpg">Sticker</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/sunset1.jpg">Sunset Lee Hysan</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/sunset2.jpg">Sunset at the Beach</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="pictures/sunset3.jpg">Sunset in Cheung Chau</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="videos/hku_www.mov">Week of Welcome Party</A>
							</li>
						</td>
						<td> </td>
					</tr>
					
					<tr>
						<td>
							<li>
								<A HREF="videos/lee_hysan_orientation.mov">Lee Hysan Hall Orientation</A>
							</li>
						</td>
						<td> </td>
					</tr>
				</ul>				
				
			</table>
		</div>
		
		<div id="footer">
		All Files are Original Content By Sam Vitello
		</div>
		
	</body>
</html>