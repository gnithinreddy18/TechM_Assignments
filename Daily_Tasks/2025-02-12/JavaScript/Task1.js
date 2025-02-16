function getCurrentDayAndTime() {
  const days = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  ];
  const now = new Date();

  let day = days[now.getDay()];
  //   console.log(now.getDay());
  let hours = now.getHours();
  let minutes = now.getMinutes();
  let seconds = now.getSeconds();
  let ampm = hours >= 12 ? "PM" : "AM";

  hours = hours % 12 || 12;

  console.log(`Today is : ${day}.`);
  console.log(`Current time is : ${hours} ${ampm} : ${minutes} : ${seconds}`);
}

getCurrentDayAndTime();
