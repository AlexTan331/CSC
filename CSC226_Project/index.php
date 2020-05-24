<?php
session_start();
?>
<!DOCTYPE html>
<html>
<body>
<?php
function Post_table() {
	require "mysqli_connect.php";
	if (mysqli_connect_errno()) {
    echo 'Error: Could not connect to database.  Please try again later.';
    exit;
	}
	
	if (isset($_GET['page']))
	  $CurrentPage = $_GET['page'];
	else
	  $CurrentPage = 1;
	$query = "SELECT count(PostID)
			FROM Post_Table";
	$stmt = $dbc -> prepare($query);
	$stmt->execute();
	$stmt->store_result();
    $stmt->bind_result($totrecords);
	$stmt->fetch();

	$stmt->free_result();

	$recordsperpage = 5;
	$totalpages = ceil($totrecords / $recordsperpage);
	$offset = ($CurrentPage - 1) * $recordsperpage;
	
	$query = "SELECT Post_Table.Title, Post_Table.BodyOfPost, Users.NickName, Post_Table.TimeofPost FROM Users, Post_Table WHERE Users.UserID = Post_Table.UserID ORDER BY Post_Table.TimeofPost DESC LIMIT ?,?";
	$stmt =$dbc->prepare($query);    //prevent SQL Injection
	$stmt ->bind_param('ss',$offset, $recordsperpage);
	$stmt ->execute();
	$stmt ->store_result();
	$stmt ->bind_result($Title,$BodyOfPost,$NickName,$TimeofPost);
	
	while ($stmt->fetch())
	{
?>	
		<div style="background-color:rgba(180, 210, 224, 0.288);color:black;padding:20px;  font-size: 100%;">
<?php
		echo "<p><strong>".$Title."</strong><br>";
		echo "Username: ".$NickName."<br>";
		echo "Post Content: ".$BodyOfPost."<br>";
		echo "DateofPost: ".$TimeofPost."</p>";   //implement time format later
?>
		</div>
<?php
	}
	
	$stmt->free_result();
	$dbc ->close();

	$bar = "";
	$prevpage =0;
	$nextpage =0;
	echo '<p style ="text-align: right;">total pages: '.$totalpages.'</p>';
	if ($totalpages > 1)
	{ 
		for($page = 1; $page <= $totalpages; $page++)
		{
			if ($page == $CurrentPage)            
			{
				$bar .= " $page ";
			} else 
			{
				$bar .= "<a href=\"index.php?page=$page\"> $page </a>";
			}
		}
		
		if ( $CurrentPage == 1 )
		{
			$nextpage= $CurrentPage +1; 
			$bar .="<a href=\"index.php?page=$nextpage\"> Next </a>";
		}
		else if (  $totalpages > $CurrentPage  && $CurrentPage > 1)
		{
			$prevpage =$CurrentPage -1;
			$nextpage =$CurrentPage +1;
			$bar = "<a href=\"index.php?page=$prevpage\"> Prev </a>".$bar."<a href=\"index.php?page=$nextpage\"> Next </a>";
		}
		else if ($CurrentPage == $totalpages)
		{
			$prevpage =$CurrentPage -1;
			$bar ="<a href=\"index.php?page=$prevpage\"> Prev </a>.$bar";
		}
		echo '<p style ="text-align: right; "><b>'.$bar.'</b></p>';
	} 
   
}

$page_title = 'Welcome to this Site!';
include('includes/header.html');

?>

<div class="page-header"><h1>Post Content</h1></div>

<?php
Post_table();
include('includes/footer.html');
?>

</body>
</html>