var selectedAirline = null;

/*function airlineLoadHandler(event) {
	fetchAllAirline();
}

async function airlineSubmitHandler(event) {
	event.preventDefault();
	let objAirline = {id: null};
	if(selectedAirline) {
		objAirline = selectedAirline;
	}
	objAirline.airlineName = document.getElementById("txtName").value;
	objAirline.placeFrom = document.getElementById("txtPlaceFrom").value;
	objAirline.placeTo = document.getElementById("txtPlaceTo").value;
	objAirline.phone = document.getElementById("txtPhone").value;
	
	const airline = await saveAirline(objAirline);
	resetFields();
	fetchAllAirline();
	//window.location.href = "login";
	
}*/

function fetchAllAirline() {
	fetch("http://localhost:8080/airlines/all",{
			method:"GET",
			headers: {
				'Content-Type':'application/json'
			}
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		renderAirlineTable(data);
	});
}

function renderAirlineTable(data) {
	const tblAirlineBody = document.getElementById("tblAirlineBody");
	tblAirlineBody.innerHTML = "";
	if(data && data.length) {
		 document.getElementById("conAirline").style.display = "block";
		 for(var i=0;i < data.length;i++) {
			var item = data[i];
			var row = document.createElement("tr");
			var cell0 = row.insertCell();
			var anchorUpdate = document.createElement("a");
			anchorUpdate.setAttribute("href","javascript:void(0)");
			anchorUpdate.addEventListener("click",updateAirline.bind(null,item));
			anchorUpdate.innerHTML = "Update";
			anchorUpdate.style.paddingRight = "10px";
			cell0.appendChild(anchorUpdate);
			var anchorDelete = document.createElement("a");
			anchorDelete.setAttribute("href","javascript:void(0)");
			anchorDelete.addEventListener("click",deleteAirline.bind(null,item));
			anchorDelete.innerHTML = "Delete";
	        cell0.appendChild(anchorDelete);
			var cell1 = row.insertCell();
	        cell1.innerHTML = item.airlineName;
	        var cell2 = row.insertCell();
	        cell2.innerHTML = item.placeFrom;
	        var cell3 = row.insertCell();
	        cell3.innerHTML = item.placeTo;
	        var cell4 = row.insertCell();
	        cell4.innerHTML = item.phone;
			tblAirlineBody.appendChild(row);
		}
	}
	else {
		document.getElementById("conAirline").style.display = "none";
	}
}

function updateAirline(airline,event) {
	selectedAirline = airline;
	document.getElementById("compId").value = airline.id;
	document.getElementById("txtName").value = airline.airlineName;
	document.getElementById("txtPlaceFrom").value = airline.placeFrom;
	document.getElementById("txtPlaceTo").value = airline.placeTo;
	document.getElementById("txtPhone").value = airline.phone;
}

function resetFields() {
	selectedAirline = null;
	document.getElementById("compId").value = "";
	document.getElementById("txtName").value = "";
	document.getElementById("txtPlaceFrom").value = "";
	document.getElementById("txtPlaceTo").value = "";
	document.getElementById("txtPhone").value = "";
}

function deleteAirline(airline,event) {
	event.preventDefault();
	fetch("http://localhost:8080/airlines/delete",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify(airline)
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		fetchAllAirline();
		resetFields();
	});
}

/*async function saveAirline(airline) {
	const res = await fetch("http://localhost:8080/airlines/save",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify(airline)
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
}*/