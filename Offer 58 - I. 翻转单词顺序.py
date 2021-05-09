# 分割 + 倒序
class Solution:
    def reverseWords(self, s: str) -> str:
        s = s.strip()
        words = s.split()
        words.reverse()
        return " ".join(words)

# 双指针
class Solution:
    def reverseWords(self, s: str) -> str:
        s = s.strip()
        # i 和 j 分别指向下一个单词的起点和终点，便于切片
        i = j = len(s) - 1
        words = []
        # 从后往前遍历
        while i >= 0:
            # 寻找下一个单词
            while i >= 0 and s[i] != " ":
                i -= 1
            # 添加单词
            words.append(s[i + 1: j + 1])

            # 跳过单词间的空格
            while i >= 0 and s[i] == " ":
                i -= 1
            # 更新右端点
            j = i
        return " ".join(words)