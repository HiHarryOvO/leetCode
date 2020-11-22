class Solution {
    public String simplifyPath(String path) {
        // 使用split方法，注意其中参数必须为String类型
        String[] pathArray = path.split("/");
        // 使用双向队列。和栈相比，最后从头到尾访问更为方便
        Deque<String> folders = new LinkedList<>();
        StringBuilder ans = new StringBuilder();

        // 分情况讨论
        for (String pth: pathArray) {
            if (pth.length() == 0 || pth.equals(".")) {
                continue;
            } else if (pth.equals("..") && folders.size() == 0) {
                continue;
            } else if (pth.equals("..")) {
                folders.removeLast();
            } else {
                folders.addLast(pth);
            }
        }

        if (folders.size() == 0) {
            return "/";
        }

        while (folders.size() != 0) {
            ans.append('/');
            ans.append(folders.pollFirst());
        }
        return ans.toString();
    }
}
