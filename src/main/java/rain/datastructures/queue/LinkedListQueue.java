package rain.datastructures.queue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 以单向环形带哨兵链表方式实现队列
 *
 * @author rain
 * @since myJava1.3
 */
public class LinkedListQueue<E> implements SimpleQueue<E>, Iterable<E> {
    /**
     * 头节点
     */
    private Node<E> head = new Node<>(null, null);
    /**
     * 尾节点
     */
    private Node<E> tail = head;
    /**
     * 节点数
     */
    private int size;
    /**
     * 容量
     */
    private int capacity = Integer.MAX_VALUE;

    {
        //该类的初始化方法
        head.next = tail;//实现链表循环
    }

    /**
     * 使用默认容量的队列
     */
    public LinkedListQueue() {
    }

    /**
     * 指定容量的构造方法
     *
     * @param capacity 指定容量
     */
    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * 迭代器中的当前元素
             */
            Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current != head;
            }

            @Override
            public E next() {
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 一个内部类链表
     *
     * @param <E>
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
