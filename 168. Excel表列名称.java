class Solution {
    public String convertToTitle(int n) {
        StringBuilder ans = new StringBuilder();
        int div = n;
        while (div != 0) {
            // 这个减1是关键，不然26/26也太尴尬了
            div--;
            int rem = div % 26;
            ans.insert(0, (char)('A' + rem));
            div = div / 26;
        }
        return ans.toString();
    }
}
