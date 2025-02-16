function triangleArea(a, b, c) {
  const s = (a + b + c) / 2;
  const area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
  return area.toFixed(2);
}

const a = 5,
  b = 6,
  c = 7;
console.log(`The 3 sides of the traingle are:${a},${b},${c}`);
console.log(`The area of the triangle is: ${triangleArea(a, b, c)}`);
