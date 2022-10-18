async function userSubmitHandler(event) {
	event.preventDefault();
	let objUser = {id: null};
	objUser.name = document.getElementById("txtName").value;
	objUser.email = document.getElementById("txtEmail").value;
	objUser.phoneNumber = document.getElementById("txtPhone").value;
	objUser.password = document.getElementById("txtPassword").value;
	let retypePassword = document.getElementById("txtRetypePassword").value;
	
	if(objUser.password !== retypePassword) {
		document.getElementById("retypePasswordError").innerHTML = "Password and Retype Password is not same";
	}
	else {
		const user = await saveUser(objUser);
		window.location.href = "login";
	}
	
}

async function saveUser(user) {
	const res = await fetch("http://localhost:8080/users/add",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify(user)
	});
	if(res && res.json) {
		try {
			var data = await res.json();
			return data;
		}
		catch(e) {
			
		}
	}
	return null;
}