
function searchHandler(event) {
	event.preventDefault();

	var placeFrom = document.getElementById("txtPlaceFrom").value;
	var placeTo = document.getElementById("txtPlaceTo").value;
	
	fetchAirlineByPlace(placeFrom,placeTo)
}

function fetchAirlineByPlace(placeFrom,placeTo) {
	fetch(`http://localhost:8080/airlines/allByPlace?placeFrom=${placeFrom}&placeTo=${placeTo}`,{
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
			var cell1 = row.insertCell();
	        cell1.innerHTML = item.airlineName;
	        var cell2 = row.insertCell();
	        cell2.innerHTML = item.placeFrom;
	        var cell3 = row.insertCell();
	        cell3.innerHTML = item.placeTo;
	        var cell4 = row.insertCell();
	        cell4.innerHTML = item.phone;
	        var cell5 = row.insertCell();
			var anchor = document.createElement("a");
			anchor.setAttribute("href","javascript:void(0)");
			anchor.addEventListener("click",bookAirline.bind(null,item));
			anchor.innerHTML = "Book";
			cell5.appendChild(anchor);
			tblAirlineBody.appendChild(row);
		}
	}
	else {
		document.getElementById("conAirline").style.display = "none";
	}
}

function bookAirline(objAirline,event) {
	var data = {airline: objAirline,wayType: getWayType(),dateFrom:document.getElementById("txtDateFrom").value ,dateTo: document.getElementById("txtDateTo").value};
	fetch("http://localhost:8080/booking/populateAddBooking",{
		method:"POST",
		headers: {
			'Content-Type':'application/json'
		},
		body: JSON.stringify(data)
	})
	.then((response) => response.json())
	.then((data) => {
		console.log(data);
		window.location.href = "/allbookings";
	});
}

function getWayType() {
    var ele = document.getElementsByName('radioType');
    for(i = 0; i < ele.length; i++) 
    {
        if(ele[i].checked){
        	return ele[i].value;
        }
    }
    return null;
}