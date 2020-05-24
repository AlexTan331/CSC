<?php # Script 9.5 - register.php #2
// This script performs an INSERT query to add a record to the users table.
session_start();
$page_title = 'Register';
include('includes/header.html');
// Check for form submission:
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	require('mysqli_connect.php'); // Connect to the db.
	$errors = []; // Initialize an error array.
	if (empty($_POST['nickname'])) {
		$errors[] = 'You forgot to enter your first name.';
	} 
	else
	{
		$NickName = trim($_POST['nickname']);
	}
	// Check for an email address:
	if (empty($_POST['email'])) {
		$errors[] = 'You forgot to enter your email address.';
	} 
	else
	{
		$email = trim($_POST['email']);
	}
	// Check for a password and match against the confirmed password:
	if (!empty($_POST['pass1'])) {
		if ($_POST['pass1'] != $_POST['pass2']) {
			$errors[] = 'Your password did not match the confirmed password.';
		} 
		else
		{$pass= trim($_POST['pass1']);
		}
	}
	 else {
		$errors[] = 'You forgot to enter your password.';
	}
	
	if (empty($errors)) { // If everything's OK.
		// Register the user in the database...
		// Here is the prepared query!
		//prepared queries can be made out of any INSERT, UPDATE, DELETE, SELECT
		//define the query making placeholders for the variables that you use
		//note: no quotes around the string
		$q = "INSERT INTO Users (Password, EmailAddress, NickName) VALUES (SHA2(?,512), ? , ?)";
		//prepare the statement in mysql, assigning the results to a php variables
		//so the query is parsed but not executed
		$stmt = mysqli_prepare($dbc,$q);
		
		//bind php variables to the placeholders in the query, i.e. say what the ? in the query refer to
		mysqli_stmt_bind_param($stmt,'sss',$pass,$email,$NickName);
		//execute the statement
		mysqli_stmt_execute($stmt);
		if (mysqli_stmt_affected_rows($stmt)==1) { // If it ran OK.
			// Print a message:
			echo '<h1>Thank you!</h1>
		<p>You are now registered. <br></p>';
		} else { // If it did not run OK.
			// Public message:
			echo '<h1>System Error</h1>
			<p class="error">You could not be registered due to a system error. We apologize for any inconvenience.</p>';
			// Debugging message:
			echo '<p>' . mysqli_stmt_error($stmt) . '<br><br>Query: ' . $q . '</p>';
		} // End of if ($r) IF.
		mysqli_close($dbc); // Close the database connection.
		// Include the footer and quit the script:
		include('includes/footer.html');
		exit();
	} else { // Report the errors.
		echo '<h1>Error!</h1>
		<p class="error">The following error(s) occurred:<br>';
		foreach ($errors as $msg) { // Print each error.
			echo " - $msg<br>\n";
		}
		echo '</p><p>Please try again.</p><p><br></p>';
	} // End of if (empty($errors)) IF.
	mysqli_close($dbc); // Close the database connection.
} // End of the main Submit conditional.
?>
<h1>Register</h1>
<form action="register.php" method="post">
	<p>Nickname: <input type="text" name="nickname" size="20" maxlength="20" value="<?php if (isset($_POST['nickname'])) echo $_POST['nickname']; ?>"></p>
	<p>Email Address: <input type="email" name="email" size="50" maxlength="50" value="<?php if (isset($_POST['email'])) echo $_POST['email']; ?>" > </p>
	<p>Password: <input type="password" name="pass1" size="50" maxlength="50" value="<?php if (isset($_POST['pass1'])) echo $_POST['pass1']; ?>" ></p>
	<p>Confirm Password: <input type="password" name="pass2" size="50" maxlength="50" value="<?php if (isset($_POST['pass2'])) echo $_POST['pass2']; ?>" ></p>
	<p><input type="submit" name="submit" value="Register"></p>
</form>
<?php include('includes/footer.html'); ?>