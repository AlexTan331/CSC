<?php 
session_start();  //place session_start() before html 
?>

<!DOCTYPE html>
<html>
<body>
<?php
$page_title = 'Add a post';
include	('includes/header.html');
function putForm($ErrorInfo)
{
	echo "<form action='Add_Post.php' method = 'POST'>";
	echo "	<fieldset><legend>Adding post</legend>";
	echo 		"<p><input type='text' name='title' size='50' maxlength='50'></p>";
	echo 			"<font color='red'>".$ErrorInfo['title']."</font>";
	echo 		"<p><textarea name='body' rows='10' cols='50'></textarea>";
	echo 			"<font color='red'>".$ErrorInfo['body']."</font>";
	echo 		"<p><input type='submit' name='submit'></p>";
	echo "	</fieldset></form>";
}
$Errorlist=array('title' => "", 'body' => "");

if (!isset($_SESSION['user_id']))
{ 
	require	('includes/login_functions.inc.php');
	redirect_user();
}
else 
{
	if (!isset($_POST['submit']))
		putForm($Errorlist);
	else if (empty($_POST['title']))
	{
		$Errorlist['title'] = "Title is needed. Please enter a title";
		putForm($Errorlist);
	}
	else if (empty($_POST['body']))
	{
		$Errorlist['body'] = "Please enter a body for this post";
		putForm($Errorlist);
	}
	else if (!empty ($_POST['title']) && !empty ($_POST['body']))
	{
		$title =htmlspecialchars(strip_tags($_POST['title']));
		$body =htmlentities($_POST['body']);
		
		//connect to database;
		require('mysqli_connect.php');
		$q="INSERT INTO Post_Table(Title, BodyofPost, UserID, TimeofPost) VALUES (?, ?, ?, NOW())";
		$stmt = $dbc -> prepare($q);
		$stmt->bind_param('sss',$title,$body,$_SESSION['user_id']);
		$stmt->execute();
		$stmt->store_result();
		$stmt->fetch();
		echo "A new post has been successfully updated to the file!";
	}
	else 
		echo "Wrong information is entered!";

}

include('includes/footer.html');
?>



</body>
</html>