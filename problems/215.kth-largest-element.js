/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findKthLargest = function(nums, k) {
  const target = nums.length - k - 1;

  const partition = (arr, lo, hi) => {
    let i = lo, j = hi+1;
    while (true)
    {
      while (arr[++i] < arr[lo])
        if (i === hi) break;

      while (arr[lo] < arr[--j])
        if (j === lo) break;

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

  let lo = 0, hi = nums.length - 1;
  while (lo <= hi) {
    const r = partition(nums, lo, hi);

    if (r === target) return nums[r];
    if (r > target) {
      hi = r;
    } else {
      lo = r;
    }
    
  }
};

const nums = [3,2,1,5,6,4]
const result = findKthLargest(nums, 2)

console.log(`Kth largest element is: ${result}`);
