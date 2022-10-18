async function loginSubmitHandler(event) {
	event.preventDefault();
	let objUser = {};
	objUser.email = document.getElementById("txtEmail").value;
	objUser.password = document.getElementById("txtPassword").value;
	
	
	const user = await authenticate(objUser);
	alert("Saved");
	
	
}

async function authenticate(user) {
	const res = fetch("http://localhost:8080/users/authenticate",{
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