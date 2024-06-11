// quick sort implementation
const mergeSort = (arr) => {
  if (arr.length === 0) return [];
  if (arr.length === 1) return arr;

  const mid = Math.floor((arr.length) / 2);
  
  const lArr = arr.slice(0, mid)
  const rArr = arr.slice(mid, arr.length)
  const left = mergeSort(lArr)
  const right = mergeSort(rArr)
  
  const result = [];
  let i = 0;
  let j = 0;
  while (true) {
    if (i >= left.length && j >= right.length)
      break;

    if (i >= left.length) {
      result.push(right[j++])
      continue;
    } 

    if (j >= right.length) {
      result.push(left[i++])
      continue;
    } 

    if (left[i] <= right[j]) {
      result.push(left[i++])
    } else {
      result.push(right[j++])
    }
  }

  return result;
}

const arr = [3, 0, 7, 12, 3, 5, 8, -1]
console.log('Original array: ', arr);
const sortedArr = mergeSort(arr)
console.log('Sorted array: ', sortedArr);
