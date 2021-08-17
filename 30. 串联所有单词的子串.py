from collections import Counter

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if not s or not words:
            return []

        one_word = len(words[0])
        word_num = len(words)
        n = len(s)
        res = []

        counter_w = Counter(words)

        # 由于每次步长为 one_word，因此起点的设置有 one_word 种
        # 例如，单词长度为 3 时，起点可以为 0,1,2 且不重复
        for i in range(one_word):
            left = right = i
            counter_s = Counter()
            word_count = 0

            while right + one_word <= n:
                w = s[right: right + one_word]
                right += one_word
                counter_s[w] += 1
                word_count += 1

                while counter_s[w] > counter_w[w]:
                    lw = s[left: left + one_word]
                    left += one_word
                    counter_s[lw] -= 1
                    word_count -= 1

                # 当 counter_s 中所有单词计数不超过 counter_w 且总单词数等于 words 中单词数时
                # 证明找到了串联的子串
                if word_count == word_num:
                    res.append(left)

        return res