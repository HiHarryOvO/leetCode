class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        total, n = 0, len(encoded) + 1
        # 获得1~n的异或结果
        for i in range(1, n + 1):
            total ^= i

        # 获得encoded中奇数下标的异或结果
        odd = 0
        for i in range(1, n - 1, 2):
            odd ^= encoded[i]

        # 获得答案的第一个数
        first = total ^ odd
        ans = [first]

        for enc in encoded:
            num = ans[-1] ^ enc
            ans.append(num)

        return ans