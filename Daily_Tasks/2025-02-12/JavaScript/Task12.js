function findLargest(a, b) {
  console.log(`In these two numbers ${a},${b}`);
  if (a > b) {
    console.log(`The largest number is: ${a}`);
  } else if (b > a) {
    console.log(`The largest number is: ${b}`);
  } else {
    console.log(`Both numbers are equal: ${a}`);
  }
}

// Example usage
findLargest(10, 20); // Output: The largest number is: 20
findLargest(30, 15); // Output: The largest number is: 30
findLargest(5, 5); // Output: Both numbers are equal: 5
