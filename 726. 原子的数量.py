class Solution:
    def countOfAtoms(self, formula: str) -> str:
        stack, i = [{}], 0
        n = len(formula)
        
        # 使用num更新栈顶的哈希表，并将栈顶的哈希表和栈顶下的哈希表merge
        def update(stack, num):
            for k in stack[-1].keys():
                stack[-1][k] *= num
            
            dic = stack.pop()
            for k, v in dic.items():
                if k in stack[-1].keys():
                    stack[-1][k] += v
                else:
                    stack[-1][k] = v

        # 获取数字
        def parse_num():
            nonlocal i
            if i == n or not formula[i].isdigit():
                return 1
            else:
                num = 0
                while i < n and formula[i].isdigit():
                    num = num * 10 + int(formula[i])
                    i += 1
                return num

        # 获取原子名称
        def parse_atom():
            nonlocal i
            res = [formula[i]]
            i += 1
            while i < n and formula[i].islower():
                res.append(formula[i])
                i += 1
            return "".join(res)


        while i < n:
            if formula[i] == '(':
                stack.append({})
                i += 1
            elif formula[i] == ')':
                i += 1
                num = parse_num()
                update(stack, num)
            else:
                atom = parse_atom()
                num = parse_num()
                if atom in stack[-1].keys():
                    stack[-1][atom] += num
                else:
                    stack[-1][atom] = num
        
        mp = stack.pop()
        res = []
        # sorted(mp)获得的是排序后的键，不包含值
        for k in sorted(mp):
            res.append(k)
            # 注意特殊情况
            if mp[k] != 1:
                res.append(str(mp[k]))
        return "".join(res)
