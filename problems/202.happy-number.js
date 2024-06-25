var isHappy = function(n) {
  const set = new Set();
  const helper = (v) => {
    if (v === 1) return true;

    if (set.has(v)) return false;

    set.add(v);

    let val = v;

    let sum = 0;
    while (val > 0) {
      const left = val % 10;
      sum += left ** 2;
      val = Math.trunc((val - left) / 10)
    }

    return helper(sum);
  }

    return helper(n);
};

const res = isHappy(7);

console.log(res);
