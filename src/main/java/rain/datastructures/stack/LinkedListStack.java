package rain.datastructures.stack;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 链表形式的栈
 */
public class LinkedListStack<E> implements SimpleStack<E>, Iterable<E> {
    /**
     * 头节点
     */
    private Node<E> head = new Node<>(null, null);
    /**
     * 节点数
     */
    private int size;
    /**
     * 容量
     */
    private int capacity = Integer.MAX_VALUE;

    /**
     * 自定义容量的构造方法
     *
     * @param capacity 容量
     */
    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
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
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new Node<>(value, head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        head = first.next;
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
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
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
