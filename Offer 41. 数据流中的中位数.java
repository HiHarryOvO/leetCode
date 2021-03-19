class MedianFinder {
    PriorityQueue<Integer> small, big;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>((x, y) -> (y - x)); // 小的半边为大顶堆
        big = new PriorityQueue<>(); // 大的半边为小顶堆
    }
    
    public void addNum(int num) {
        if (small.size() == big.size()) {
            // 偶数
            big.offer(num);
            small.offer(big.poll());
        } else {
            // 奇数
            small.offer(num);
            big.offer(small.poll());
        }
    }
    
    public double findMedian() {
        return (small.size() == big.size())? (small.peek() + big.peek()) / 2.0 : small.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */