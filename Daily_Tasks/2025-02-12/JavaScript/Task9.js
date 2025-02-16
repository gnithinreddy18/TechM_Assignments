function daysUntilChristmas() {
  const today = new Date();
  const currentYear = today.getFullYear();
  const christmas = new Date(currentYear, 11, 25);

  if (today > christmas) {
    christmas.setFullYear(currentYear + 1);
  }

  const diffTime = christmas - today;
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

  return diffDays;
}

console.log(`Days left until Christmas: ${daysUntilChristmas()}`);
