class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        decoded = [first]
        for num in encoded:
            curr = decoded[-1] ^ num
            decoded.append(curr)
        return decoded