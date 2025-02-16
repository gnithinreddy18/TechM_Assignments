function getCurrentDate() {
  const now = new Date();

  let day = String(now.getDate()).padStart(2, "0");
  let month = String(now.getMonth() + 1).padStart(2, "0"); // Months are 0-based
  let year = now.getFullYear();

  console.log(`MM-DD-YYYY: ${month}-${day}-${year}`);
  console.log(`MM/DD/YYYY: ${month}/${day}/${year}`);
  console.log(`DD-MM-YYYY: ${day}-${month}-${year}`);
  console.log(`DD/MM/YYYY: ${day}/${month}/${year}`);
}

getCurrentDate();
