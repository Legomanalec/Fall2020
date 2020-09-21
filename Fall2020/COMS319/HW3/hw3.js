var rs = require('readline-sync');

var num1 = rs.question('1st Number: ');
var num2 = rs.question('2st Number: ');
var num3 = rs.question('3st Number: ');
var num4 = rs.question('4st Number: ');

console.log("Factorial of the 1st number is = " + factorial(num1));
console.log("The sum of all of digits of the 2nd number is " + sumDigits(num2));
console.log("The reverse of the 3rd number is " + reverseNum(num3));
console.log("Is the 4th number a palindrome (True/False)? = " + isPalindrome(num4));

function factorial(num)
{
	if(num === 0)
		return 1;
	return num * factorial(num - 1);
	
}

function sumDigits(num)
{
	let sum = 0;
	
	while(num)
	{
		sum += num % 10;
		num = Math.floor(num / 10);
	}
	return sum;
}

function reverseNum(num)
{
	num = num + "";
	return num.split("").reverse().join("");
}

function isPalindrome(num)
{
	return num == reverseNum(num);
}
		