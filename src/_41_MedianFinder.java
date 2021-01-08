import java.util.ArrayList;
import java.util.Comparator;

/**
 * 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class _41_MedianFinder {

    ArrayList<Integer> mArr = new ArrayList<>();

    /**
     * initialize your data structure here.
     */
    public _41_MedianFinder() {
    }

    public void addNum(int num) {
        mArr.add(num);
        mArr.sort(Comparator.comparingInt(o -> o));
    }

    public double findMedian() {
        if (mArr.size() % 2 == 0) {
            return (mArr.get(mArr.size() / 2) + mArr.get((mArr.size() / 2) - 1)) / 2.0D;
        } else {
            return mArr.get(mArr.size() / 2);
        }
    }
}
