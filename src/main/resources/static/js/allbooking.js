function fetchAllBooking() {
	fetch("http://localhost:8080/booking/all",{
			method:"GET",
			headers: {
				'Content-Type':'application/json'
			}
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		renderBookingTable(data);
	});
}

function renderBookingTable(data) {
	const tblBookingBody = document.getElementById("tblBookingBody");
	tblBookingBody.innerHTML = "";
	if(data && data.length) {
		 document.getElementById("conBooking").style.display = "block";
		 for(var i=0;i < data.length;i++) {
			var item = data[i];
			var row = document.createElement("tr");
			var cell0 = row.insertCell();
			var anchorDelete = document.createElement("a");
			anchorDelete.setAttribute("href","javascript:void(0)");
			anchorDelete.addEventListener("click",deleteBooking.bind(null,item));
			anchorDelete.innerHTML = "Cancel";
	        cell0.appendChild(anchorDelete);
			var cell1 = row.insertCell();
	        cell1.innerHTML = item.airline.airlineName;
	        var cell2 = row.insertCell();
	        cell2.innerHTML = item.hotel.hotelName;
	        var cell3 = row.insertCell();
	        cell3.innerHTML = item.dateFrom;
	        var cell4 = row.insertCell();
	        cell4.innerHTML = item.dateTo;
	        var cell5 = row.insertCell();
	        cell5.innerHTML = item.wayType;
	        tblBookingBody.appendChild(row);
		}
	}
	else {
		document.getElementById("conBooking").style.display = "none";
	}
}

function deleteBooking(id,event) {
	event.preventDefault();
	fetch("http://localhost:8080/booking/delete",{
			method:"POST",
			headers: {
				'Content-Type':'application/json'
			},
			body: JSON.stringify({id: id})
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		fetchAllBooking();
	});
}

function convertBookingToObject(strPojo) {
	strPojo = strPojo.replaceAll('Booking' + '\(', '\{"').replaceAll('\)', '\"}').replaceAll('User' + '\(', '\{"').replaceAll('Airline' + '\(', '\{"')
	.replaceAll('Hotel' + '\(', '\{"').replaceAll('\)', '\"}')
	.replaceAll('\=','":"').replaceAll(',','","').replaceAll('," ',',"')
	.replaceAll(':"{',':{').replaceAll('}",','},');
	console.log(strPojo)
	var objData = JSON.parse(strPojo);
	//console.log(objData,objData.id);
	return objData;
}