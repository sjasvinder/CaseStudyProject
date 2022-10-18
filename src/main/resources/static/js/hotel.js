var selectedHotel = null;

/*function hotelLoadHandler(event) {
	fetchAllHotels();
}

async function hotelSubmitHandler(event) {
	event.preventDefault();
	let objHotel = {id: null};
	if(selectedHotel) {
		objHotel = selectedHotel;
	}
	objHotel.hotelName = document.getElementById("txtName").value;
	objHotel.place = document.getElementById("txtPlace").value;
	objHotel.phone = document.getElementById("txtPhone").value;
	
	const hotel = await saveHotel(objHotel);
	resetFields();
	fetchAllHotels();
	//window.location.href = "login";
	
}*/

function fetchAllHotels() {
	fetch("http://localhost:8080/hotels/all",{
			method:"GET",
			headers: {
				'Content-Type':'application/json'
			}
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		renderHotelTable(data);
	});
}

function renderHotelTable(data) {
	const tblHotelBody = document.getElementById("tblHotelBody");
	tblHotelBody.innerHTML = "";
	if(data && data.length) {
		 document.getElementById("conHotels").style.display = "block";
		 for(var i=0;i < data.length;i++) {
			var item = data[i];
			var row = document.createElement("tr");
			var cell0 = row.insertCell();
			var anchorUpdate = document.createElement("a");
			anchorUpdate.setAttribute("href","javascript:void(0)");
			anchorUpdate.addEventListener("click",updateHotel.bind(null,item));
			anchorUpdate.innerHTML = "Update";
			anchorUpdate.style.paddingRight = "10px";
			cell0.appendChild(anchorUpdate);
			var anchorDelete = document.createElement("a");
			anchorDelete.setAttribute("href","javascript:void(0)");
			anchorDelete.addEventListener("click",deleteHotel.bind(null,item));
			anchorDelete.innerHTML = "Delete";
	        cell0.appendChild(anchorDelete);
			var cell1 = row.insertCell();
	        cell1.innerHTML = item.hotelName;
	        var cell2 = row.insertCell();
	        cell2.innerHTML = item.place;
	        var cell3 = row.insertCell();
	        cell3.innerHTML = item.phone;
			tblHotelBody.appendChild(row);
		}
	}
	else {
		document.getElementById("conHotels").style.display = "none";
	}
}

function updateHotel(hotel,event) {
	selectedHotel = hotel;
	document.getElementById("compId").value = hotel.id;
	document.getElementById("txtName").value = hotel.hotelName;
	document.getElementById("txtPlace").value = hotel.place;
	document.getElementById("txtPhone").value = hotel.phone;
}

function resetFields() {
	selectedHotel = null;
	document.getElementById("compId").value = "";
	document.getElementById("txtName").value = "";
	document.getElementById("txtPlace").value = "";
	document.getElementById("txtPhone").value = "";
}

function deleteHotel(hotel,event) {
	event.preventDefault();
	fetch("http://localhost:8080/hotels/delete",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify(hotel)
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		fetchAllHotels();
		resetFields();
	});
}

/*async function saveHotel(hotel) {
	const res = await fetch("http://localhost:8080/hotels/save",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify(hotel)
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