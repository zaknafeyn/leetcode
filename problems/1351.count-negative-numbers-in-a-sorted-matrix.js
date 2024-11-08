/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function(grid) {
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

const grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]];

console.log(`Number of negative values: ${countNegatives(grid)}`)
