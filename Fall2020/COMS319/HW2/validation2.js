function validateForm(correct, wrong)
{
	alertStatus = false;
	alertStr = "";
	fieldCheck("email", correct, wrong);
	fieldCheck("phone", correct, wrong);
	fieldCheck("address", correct, wrong);
	if(alertStatus)
	{
		alert(alertStr);
		return false;
	}
	else
	{
		window.open("success.html");
	}
}

//This function checks the status of each field.
function fieldCheck(fieldId, correct, wrong)
{
	var temp = document.forms["form"][fieldId].value;
	
	var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var phoneFormat = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	var addressFormat = /^([a-zA-Z])*,([A-Z]{2})$/g;
	
	if (temp == "" 
		|| (fieldId == "email" && !temp.match(emailFormat)) 
		|| (fieldId == "phone" && !temp.match(phoneFormat))
		|| (fieldId == "address" && !temp.match(addressFormat)))
	{
		
		if (fieldId == "email")
		{
			alertStr += "email must be in the form xxx@xxx.xxxx\n";
		}
		else if (fieldId == "phone")
		{
			alertStr += "phone must be in the form xxx-xxx-xxxx or xxxxxxxxxx\n";
		}
		else if (fieldId == "address")
		{
			alertStr += "address must be in the form city & state. example: Ames,IA\n";
		}
		alertStatus = true;
		imagePlace(wrong, fieldId);
	}
	else
	{
		imagePlace(correct, fieldId);
		
	}
}

//This function adds/removes the icons next to each field
function imagePlace(source, option)
{
	var img = document.createElement("img");
	var divElement = document.getElementById(option);
	img.src = source;
	
	
	//removes the images so they don't stack
	if(divElement.lastChild != null)
	{
		divElement.removeChild(divElement.lastChild);
	}
	divElement.appendChild(img);
}