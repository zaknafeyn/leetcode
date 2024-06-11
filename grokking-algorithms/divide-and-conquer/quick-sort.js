// quick sort implementation
const quickSort = (arr) => {
  if (arr.length === 0) return [];
  if (arr.length === 1) return arr;

  const mid = Math.floor(arr.length / 2);
  const pivot = arr[mid];

  const less = [];
  const more = [];
  for (let i = 0; i < arr.length; i++) {
    if (i === mid) continue;

    if (arr[i] <= pivot) less.push(arr[i])
    else more.push(arr[i])
  }

  return [...quickSort(less), pivot, ...quickSort(more)];
}

const arr = [3, 0, 7, 12, 3, 5, 8, -1]
console.log('Original array: ', arr);
const sortedArr = quickSort(arr)
console.log('Sorted array: ', sortedArr);
