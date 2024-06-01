package rain.datastructures.stack;

/*
  Java栈（Java Stack）是一种后进先出（LIFO, Last-In-First-Out）的数据结构，
  它只允许在栈顶进行元素的添加和移除操作。
  栈的添加操作称为压栈（push），移除操作称为弹栈（pop）
  。与队列不同，栈不遵循FIFO原则，而是遵循LIFO原则。
 */

/**
 * 简单栈，栈也许可以视为弹夹
 *
 * @param <E>
 * @author rain
 * @since myJava1.3
 */
public interface SimpleStack<E> {
    /**
     * 向栈顶压入元素
     *
     * @param value 待压入值
     * @return 压入成功返回true，否则返回false
     */
    boolean push(E value);

    /**
     * 从栈顶弹出元素
     *
     * @return 栈非空返回栈顶元素，栈为空返回null
     */
    E pop();

    /**
     * 返回栈顶元素，不弹出
     *
     * @return 栈非空返回栈顶元素并弹出，栈为空返回null
     */
    E peek();

    /**
     * 判断栈是否为空
     *
     * @return 空则返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 判断栈是否已满
     *
     * @return 满则返回true，否则返回false
     */
    boolean isFull();

    /**
     * @return 返回栈的值的数量
     */
    int size();
}
