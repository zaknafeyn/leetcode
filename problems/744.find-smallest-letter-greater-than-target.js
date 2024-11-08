/**
 * @param {character[]} letters
 * @param {character} target
 * @return {character}
 */
var nextGreatestLetter = function(letters, target) {
    
    let l = 0, r = letters.length-1;
    let resIdx = -1;
    while (l <= r) {
      let mid = Math.floor((l+r)/2);

      if (letters[mid] > target) {
        r = mid - 1;
        resIdx = mid;
      } else {
        l = mid + 1
      }
    }

    return resIdx >= 0 ? letters[resIdx] : letters[0];
};

const letters = ["c", "f", "j"];
const target = 'a';
console.log(`nextGreatestLetter = ${nextGreatestLetter(letters, target)}`);
