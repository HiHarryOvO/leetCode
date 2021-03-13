// 暴力数组yyds（经典反例）
class MyHashSet {
    int[] set;

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new int[(int)10e6 + 1];
    }
    
    public void add(int key) {
        set[key] = 1;
    }
    
    public void remove(int key) {
        set[key] = 0;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set[key] == 1;
    }
}

// 取余法
class MyHashSet {
    // 注意关键字
    private static final int BASE = 769;
    // 数组中存储链表 -> 解决冲突问题
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }
    
    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iter = data[h].iterator();
        while (iter.hasNext()) {
            Integer element = iter.next();
            if (key == element) {
                return;
            }
        }
        data[h].addLast(key);
    }
    
    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iter = data[h].iterator();
        while (iter.hasNext()) {
            // 不可以写成 int element，否则remove会识别成List的remove方法而不是Collection的remove方法
            Integer element = iter.next();
            if (key == element) {
                data[h].remove(element);
                // 匹配后需要马上return，否则Iterator会报错（工作状态中不允许修改）
                return;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iter = data[h].iterator();
        while (iter.hasNext()) {
            Integer element = iter.next();
            if (key == element) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */