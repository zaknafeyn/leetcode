/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function(grid) { // O(n*Log(m))
    const n = grid.length;
    const m = grid[0].length;
    let r = m-1;
    let result = 0;

    for (let row of grid) {
      let ll = 0;
      let lr = r;
      while (ll <= lr) {
        const mid = Math.floor((ll+lr)/2);
        if (row[mid] >= 0) {
          ll = mid + 1;
        } else {
          lr = mid - 1;
        }
      }

      if (row[ll] < 0) {
          r = lr;
          result += m - 1 - r;
        }
    }

    return result;
};

// O(n+m)
var countNegativesLinearTime = function (grid) {
  const n = grid.length;
  const m = grid[0].length;
  let result = 0;

  // -- O(n+m) solution
  let p = m - 1;
  for (let i = 0; i < n; i++) {
    j = p;
    for (j = p; j >= 0 && grid[i][j] < 0; j--) { }
    result += m - 1 - j;
    p = j
  }

  return result;
}

const grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]];

console.log(`Number of negative values: ${countNegatives(grid)}, O(n*log(m))`)
console.log(`Number of negative values: ${countNegativesLinearTime(grid)}, O(n+m)`)
