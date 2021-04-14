class Trie:

    def __init__(self):
        self.children = [None] * 26
        # 记录以当前节点结尾是否构成单词
        self.isEnd = False

    # 搜索前缀，返回最后一个节点
    def searchPrefix(self, prefix: str) -> "Trie":
        node = self
        for ch in prefix:
            ind = ord(ch) - ord('a')
            if not node.children[ind]:
                return None
            node = node.children[ind]
        return node


    def insert(self, word: str) -> None:
        node = self
        for ch in word:
            ind = ord(ch) - ord('a')
            # 注意需要判断
            if not node.children[ind]:
                node.children[ind] = Trie()
            node = node.children[ind]
        node.isEnd = True


    def search(self, word: str) -> bool:
        node = self.searchPrefix(word)
        return node is not None and node.isEnd


    def startsWith(self, prefix: str) -> bool:
        node = self.searchPrefix(prefix)
        return node is not None



# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)