class Solution:
    def permutation(self, s: str) -> List[str]:
        ch, res = list(s), []
        
        def dfs(x):
            if x == len(ch) - 1:
                res.append(''.join(ch))
                return

            # 记录固定前面字符后，本层使用字符的哈希表
            dic = set()
            for i in range(x, len(ch)):
                # 若ch[i]在dic中出现过，跳过
                if ch[i] in dic:
                    continue

                dic.add(ch[i])
                
                ch[x], ch[i] = ch[i], ch[x]
                dfs(x + 1)
                ch[x], ch[i] = ch[i], ch[x]
        
        dfs(0)
        return res