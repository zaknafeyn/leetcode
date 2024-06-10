const recursiveSum = (arr) => {
  if (arr.length === 0) return 0;
  return arr[0] + recursiveSum(arr.slice(1));
}


const result = recursiveSum([1, 2, 3, 4, 5]); // 15
console.log(result);
