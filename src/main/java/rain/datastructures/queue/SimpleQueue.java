package rain.datastructures.queue;

/*
  队列（Queue）是一种特殊类型的线性数据结构，它遵循特定的操作原则，即先进先出（FIFO, First-In-First-Out）。
  这意味着元素被添加到队列的末尾（称为enqueue操作），并从队列的开头移除（称为dequeue操作）。
  队列不允许在队列中间进行插入或删除操作。
 */

/**
 * 这是一个简单队列，队列的定义也许可以视为排队
 *
 * @param <E>
 */
public interface SimpleQueue<E> {
    /**
     * 向队列尾插入值
     *
     * @param value 待插入值
     * @return 插入是否成功
     */
    boolean offer(E value);

    /**
     * 从队列头获取值，并移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    E poll();

    /**
     * 从队列头获取值，不移除
     *
     * @return 如果队列非空返回队头值，否则返回null
     */
    E peek();

    /**
     * 检查队列是否为空
     *
     * @return 队列是否为空
     */
    boolean isEmpty();

    /**
     * 检查队列是否已满
     *
     * @return 满则返回true
     */
    boolean isFull();

    /**
     * 返回队列中的元素数量
     *
     * @return 队列中的元素数量
     */
    int size();
}