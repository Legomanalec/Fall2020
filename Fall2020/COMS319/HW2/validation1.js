function validateForm(correct, wrong, nextWindow)
{
	alertStatus = false;
	alertStr = "";
	fieldCheck("first", correct, wrong);
	fieldCheck("last", correct, wrong);
	fieldCheck("gender", correct, wrong);
	fieldCheck("state", correct, wrong);
	if(alertStatus)
	{
		alert(alertStr);
		return false;
	}
	else
	{
		window.open(nextWindow);
	}
}

//This function checks the status of each field.
function fieldCheck(fieldId, correct, wrong)
{
	var temp = document.forms["form"][fieldId].value;
	
	if (temp == "" || !temp.match(/^[a-z0-9]+$/i))
	{
		if(fieldId == "first" || fieldId == "last")
		{
			alertStr += fieldId + " name must be alphanumeric\n";
		}
		else
		{
			alertStr += fieldId + " cannot be left blank\n";
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
	divElement.removeChild(divElement.lastChild);
	
	divElement.appendChild(img);
}