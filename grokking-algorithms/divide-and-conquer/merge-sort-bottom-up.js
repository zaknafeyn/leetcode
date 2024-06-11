// merge sort bottom-up implementation
const mergeSortBottomUp = (arr) => {

  const N = arr.length;
  let aux = Array(N);
  for (let sz = 1; sz < N; sz = sz+sz) {
    for (let lo = 0; lo < N-sz; lo += sz+sz) {
      merge(arr, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
    // swap arrays to sort pre-sorted array on the next iteration
    let tmp = arr;
    arr = aux;
    aux = tmp;
  }

  return arr;
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
const a = mergeSortBottomUp(arr)
console.log('Sorted array: ', a);
