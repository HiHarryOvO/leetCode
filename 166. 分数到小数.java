class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // 特殊情况
        if (numerator == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        // 判断负号
        if (numerator < 0 ^ denominator < 0) {
            ans.append("-");
        }
        
        // 这里注意要提前使用Long.valueOf，否则转换会出错的
        long num = Math.abs(Long.valueOf(numerator));
        long den = Math.abs(Long.valueOf(denominator));

        ans.append(String.valueOf(num / den));
        long rem = num % den;

        if (rem == 0) {
            return ans.toString();
        }

        ans.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                ans.insert(map.get(rem), "(");
                ans.append(")");
                break;
            }
            map.put(rem, ans.length());
            rem *= 10;
            ans.append(String.valueOf(rem / den));
            rem %= den;
        }
        return ans.toString();
    }
}
