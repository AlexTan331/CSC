<!DOCTYPE html>
<html>
<body>
<?php
$UserAccount=array("joeDoe" => "goProgramPHP!", "junk0" => "isthismypassword",
				   "busyBee" => "pass20word", "computerGal" => "pass30word",
				   "compGuy" => "mypass2309", "expertComp" => "60your78",
				   "smartone" => "78y68pal", "johndoe" => "pass4567",
				   "johndoe3" => "pass2100", "johndoe67" => "pass2099");

function putForm($ErrorInfo) 
{
echo	"<form action='LoginwithOneFile.php' method='post' target='_blank'>";
echo 	"<fieldset>";
echo 	"<legend>Log in your account</legend>";
echo	  "Username: <br />";
echo	  "<input type='text' name='username'>"; 
echo    "<font color='red'>".$ErrorInfo['username']."</font>";
echo	  "<br />";
echo	  "Password: <br />";
echo	  "<input type='password' name='pswd'>";
echo 	"<font color='red'>".$ErrorInfo['pswd']."</font>";
echo	  "<br />";
echo      "<input type='submit' name='Login'>";
echo 	"</fieldset>";
echo	"</form>";
} 

$Errorlist=array('username' => "", 'pswd' => "");
if (!isset($_POST['Login']))	
{
	putForm($Errorlist);
} 
else if ($_POST['username'] == Null) 
{
	$Errorlist['username'] = "Enter proper username";
	putForm($Errorlist);
}
else if ($_POST['pswd'] == Null)
{
	$Errorlist['pswd'] = "Enter proper password";
	putForm($Errorlist);
}

else if (array_key_exists(trim($_POST['username']),$UserAccount) && trim($_POST['pswd']) === $UserAccount[trim($_POST['username'])])
{
	echo "Welcome ".htmlspecialchars(trim($_POST['username']))." -- You have successfully logged in!";
} 	

//alternative way of checking correct username and password
/* 			//check for existence of key in the array						check for existence of value in the array
else if (array_key_exists(trim($_POST['username']), $UserAccount) && in_array(trim($_POST['pswd']), $UserAccount) && 
		//check if key and value are associative
		trim($_POST['username']) === array_search(trim($_POST['pswd']), $UserAccount)){

		echo "Welcome ".htmlspecialchars(trim($_POST['username']))." -- You have successfully logged in!";
	}
 */

else 
		echo "Sorry, wrong information has been entered!";



?>
</body>
</html>