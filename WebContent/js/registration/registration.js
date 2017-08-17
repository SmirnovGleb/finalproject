function validateForm() {
var checkPass1 = document.forms["myForm"]["password1"].value;
var checkPass2 = document.forms["myForm"]["password2"].value;
	if (checkPass1!=checkPass2) {
		alert("passwords don't match");
  		return false;
	}
}