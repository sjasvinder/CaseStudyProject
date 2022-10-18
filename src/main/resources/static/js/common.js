function convertPojoToObject(strPojo,replaceStr) {
	strPojo = strPojo.replaceAll(replaceStr + '\(', '\{"').replaceAll('\)', '\"}').replaceAll('\=','":"').replaceAll(',','","').replaceAll('," ',',"');
	//console.log(strPojo)
	var objData = JSON.parse(strPojo);
	//console.log(objData,objData.id);
	return objData;
}

function logoutClick(event) {
	alert("here");
}