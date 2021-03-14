// 自己写的：使用数组存储key, value
class MyHashMap {
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer[]>();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Integer[]> iter = data[h].iterator();
        while (iter.hasNext()) {
            Integer[] element = iter.next();
            if (element[0] == key) {
                element[1] = value;
                return;
            }
        }
        data[h].addLast(new Integer[]{key, value});
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Integer[]> iter = data[h].iterator();
        while (iter.hasNext()) {
            Integer[] element = iter.next();
            if (element[0] == key) {
                return element[1];
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer[]> iter = data[h].iterator();
        while (iter.hasNext()) {
            Integer[] element = iter.next();
            if (element[0] == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

// 但是使用新的Pair类存储更有oop的风格，而且更容易修改
class MyHashMap {
    private static final int BASE = 769;
    private LinkedList[] data;

    // 私有类Pair指代键值对
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int val) {
            this.value = val;
        }
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Pair>();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iter = data[h].iterator();
        while (iter.hasNext()) {
            Pair element = iter.next();
            if (element.getKey() == key) {
                element.setValue(value);
                return;
            }
        }
        data[h].addLast(new Pair(key, value));
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iter = data[h].iterator();
        while (iter.hasNext()) {
            Pair element = iter.next();
            if (element.getKey() == key) {
                return element.getValue();
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iter = data[h].iterator();
        while (iter.hasNext()) {
            Pair element = iter.next();
            if (element.getKey() == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}



/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */