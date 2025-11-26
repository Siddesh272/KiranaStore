<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/index.css">
<title>Customer Registration - Kirana Store</title>

<script>
	function checkFormat() {
	    
	    let valid = true;

	    document.getElementById('nameError').textContent = '';
	    document.getElementById('emailError').textContent = '';
	    document.getElementById('passwordError').textContent = '';
	    document.getElementById('confirmPasswordError').textContent = '';
	    document.getElementById('addressError').textContent = '';
	    document.getElementById('contactNumberError').textContent = '';

	    var cname = document.getElementById('customerName').value;
	    if (!cname.match(/^[a-zA-Z\s]+$/) || cname.length === 0) {
	        document.getElementById('nameError').textContent = 'Customer Name must have alphabets only and must not be blank.';
	        valid = false;
	    }

	    var cemail = document.getElementById('email').value;
	    if (!cemail.includes('@') || cemail.length === 0) {
	        document.getElementById('emailError').textContent = 'Email id not valid and must not be blank.';
	        valid = false;
	    }


	    var cpassword = document.getElementById('password').value;
	    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{5,}$/;
	    if (!cpassword.match(passwordRegex) || cpassword.length === 0) {
	        document.getElementById('passwordError').textContent = 'Password must be at least 5 characters long and include an uppercase letter, a number, and a special character.';
	        valid = false;
	    }
	    
	    var confirmPassword = document.getElementById('confirm-password').value;
		if(cpassword !== confirmPassword){
	        document.getElementById('confirmPasswordError').textContent = 'Password does not match!'
			valid = false;
		}
			
	    
	    var caddress = document.getElementById('address').value;
	    if (caddress.length === 0) {
	        document.getElementById('addressError').textContent = 'Address must not be blank.';
	        valid = false;
	    }

	    var ccontactNumber = document.getElementById('contactNumber').value;
	    if (!ccontactNumber.match(/^\d+$/) || ccontactNumber.length !== 10) {
	        document.getElementById('contactNumberError').textContent = 'Contact number must be 10 digits long and must not contain alphabets.';
	        valid = false;
	    }
	    
	    if (valid) {
	        var customerId = Math.floor(Math.random() * 1000000);
	        document.getElementById('cId').value=customerId;
	        console.log(customerId);
	        alert("Your Customer ID is : " + customerId );
	        return true;
	    }
	    return false;
	};
	</script>
</head>
<body>
	
	<div class="container">
		<div class="form-container">
			<h2>Customer Registration</h2>
			<!-- Assuming checkFormat() function exists in a <script> block or is handled by the framework -->
			<form id="registrationForm" name="registrationForm" method="post" onsubmit="return checkFormat()" action="registrationSuccessful.jsp">
				<div class="form-group">
					<label for="customerName">Customer Name:</label> 
					<input type="text" id="customerName" name="customerName" maxlength="50">
					<div class="error" id="nameError"></div>
				</div>
				<div class="form-group">
					<label for="email">Email:</label> 
					<input type="text" id="email" name="email">
					<div class="error" id="emailError"></div>
				</div>
				<div class="form-group">
					<label for="password">Password:</label> 
					<input type="password" id="password" name="password" maxlength="30">
					<div class="error" id="passwordError"></div>
				</div>
				
				<div class="form-group">
					<label for="confirm-password">Confirm Password:</label> 
					<input type="password" id="confirm-password" name="confirm-password" maxlength="30">
					<div class="error" id="confirmPasswordError"></div>
				</div>
				
				<div class="form-group">
					<label for="address">Address:</label>
					<textarea id="address" name="address" maxlength="100"></textarea>
					<div class="error" id="addressError"></div>
				</div>
				<div class="form-group">
					<label for="contactNumber">Contact Number:</label> 
					<input type="text" id="contactNumber" name="contactNumber" maxlength="10">
					<div class="error" id="contactNumberError"></div>
				</div>
				<div class="form-group">
					<input type="submit" value="Register" name="Register">
				</div>
				<!-- Added type="password" to confirm-password for security -->
				<input type="hidden" name="customerId" id="cId">
			</form>
			<p>Already User? <a href="LoginPage.jsp">Login</a></p>
			<div id="successMessage" class="success"></div>
		</div>
	</div>
	

</body>
</html>