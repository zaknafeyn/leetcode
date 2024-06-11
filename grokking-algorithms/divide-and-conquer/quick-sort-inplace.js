// quick sort implementation without using a new arrays
const quickSortInPlace = (arr, lo = 0, hi = arr.length - 1) => {
  if (hi <= lo) return;
  let j = partition(arr, lo, hi);
  quickSortInPlace(arr, lo, j-1);
  quickSortInPlace(arr, j+1, hi);
}

const partition = (arr, lo, hi) =>
{
  let i = lo, j = hi+1;
  while (true)
  {
    while (arr[++i] < arr[lo])
      if (i == hi) break;

    while (arr[lo] < arr[--j])
      if (j == lo) break;

    if (i >= j) break;

    const tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  const tmp = arr[lo];
  arr[lo] = arr[j];
  arr[j] = tmp;

  return j;
}

const arr = [3, 0, 7, 12, 3, 5, 8, -1]
console.log('Original array: ', arr);
quickSortInPlace(arr)
console.log('Sorted array: ', arr);
