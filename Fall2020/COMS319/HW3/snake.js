var x = 5;
var y = x;
var dir = 0
var snakeArray = [];
var stop = false;



var main = setInterval(game, 50);
function game()
{
	var context = document.getElementById("canvasId").getContext("2d");
	context.fillStyle = "#ffc821";
	context.rect(x,y,5,5);
	context.fill();
	
	if(dir == 0)
		x++;
	if(dir == 1)
		y++;
	if(dir == 2)
		x--;
	if(dir == 3)
		y--;
	
	if(snakeArray.includes("" + x + y)
		|| x > 300
		|| x < 0
		|| y > 300
		|| y < 0)
		clearInterval(main);
	
	snakeArray.push("" + x + y);	
}

function leftTurn()
{
	if(!stop)
	{
	dir--;
	if(dir < 0) 
		dir = 3;
	}
}

function rightTurn()
{
	if(!stop)
	{
	dir++;
	if(dir > 3)
		dir = 0;
	}
}

function stopORstart()
{
	var button = document.getElementById("startStop");
	if(!stop)
	{
		clearInterval(main);
		button.value = "Start";
		stop = true;
	}
	else
	{
		main = setInterval(game, 50);
		button.value = "Stop";
		stop = false;
	}
}
		