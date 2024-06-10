const recursiveBinarySearch = (arr, target, lo = 0, hi = arr.length - 1) => {
  if (lo === hi) {
    return arr[lo] === target ? lo : -1;
  }

  const mid = Math.floor((lo + hi) / 2);

  if (arr[mid] === target) {
    return mid
  }

  if (arr[mid] > target) {
    return recursiveBinarySearch(arr, target, lo, mid - 1)
  } else {
    return recursiveBinarySearch(arr, target, mid + 1, hi)
  }
}

const sortedArr = [-1, 0, 2, 4, 5, 8]

const result1 = recursiveBinarySearch(sortedArr, 0);
const result2 = recursiveBinarySearch(sortedArr, 5);
const result3 = recursiveBinarySearch(sortedArr, 1);

console.log(`result1 = ${result1}`);
console.log(`result2 = ${result2}`);
console.log(`result3 = ${result3}`);
