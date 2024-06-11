// merge sort implementation without using a new arrays
const mergeSort2 = (arr) => {
  const aux = Array(arr.length);
  sort(arr, aux, 0, arr.length - 1);
  return aux;
}

const sort = (a, aux, lo, hi) => {
  if (hi <= lo) return;
  let mid = Math.floor((lo + hi) / 2);
  sort(aux, a, lo, mid);
  sort(aux, a, mid+1, hi);
  merge(a, aux, lo, mid, hi);
}

const merge = (arr, aux, lo, mid, hi) => {
  let i = lo, j = mid+1;
  for (let k = lo; k <= hi; k++)
  {
    if (i > mid) aux[k] = arr[j++];
    else if (j > hi) aux[k] = arr[i++];
    else if (arr[j] <  arr[i]) aux[k] = arr[j++];
    else aux[k] = arr[i++];
  }
}

const arr = [3, 0, 7, 12, 3, 5, 8, -1]
console.log('Original array: ', arr);
const result = mergeSort2(arr)
console.log('Sorted array: ', result);
