class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        # 前缀异或
        xor_arr = [0]
        for i in range(len(arr)):
            num = xor_arr[-1] ^ arr[i]
            xor_arr.append(num)

        ans = []
        for query in queries:
            ans.append(xor_arr[query[0]] ^ xor_arr[query[1] + 1])
        return ans
