console.log(
  " The year having 1st January as a Sunday between years (2014 - 2050) is: "
);
for (let year = 2014; year <= 2050; year++) {
  let d = new Date(year, 0, 1);
  if (d.getDay() === 0) {
    console.log(year);
  }
}
