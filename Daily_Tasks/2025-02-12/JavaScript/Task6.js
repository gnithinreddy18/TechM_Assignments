function isLeapYear(year) {
  return year % 100 === 0 ? year % 400 === 0 : year % 4 === 0;
}

console.log(`2004 is ${isLeapYear(2004) ? "a Leap Year" : "not a Leap Year"}`);
console.log(`2009 is ${isLeapYear(2009) ? "a Leap Year" : "not a Leap Year"}`);
console.log(`2024 is ${isLeapYear(2024) ? "a Leap Year" : "not a Leap Year"}`);
