package rain.datastructures.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表
 *
 * @author rain
 * @since myJava1.3
 */
public class SinglyLinkedList implements Iterable<Integer> {
    /**
     * 头指针
     */
    private Node head;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Node p = head;

            /**
             * @return 是否有下一个元素
             */
            @Override
            public boolean hasNext() {
                return p != null;
            }

            /**
             * 返回当前值，并指向下一个元素
             * @return 当前值
             */
            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    /**
     * 向链表头部添加
     *
     * @param value 待添加值
     */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    /**
     * 遍历链表
     *
     * @param consumer 操作
     */
    public void loop(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历链表
     *
     * @param consumer 操作
     */
    public void loopFor(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     * 寻找链表尾部节点
     *
     * @return 链表尾部节点
     */
    private Node findLast() {
        if (head == null) {
            return null;
        }
        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    /**
     * 向链表尾部添加
     *
     * @param value 待添加值
     */
    public void appLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    /**
     * 根据索引找到Node
     *
     * @param index 索引
     * @return Node
     */
    private Node findNode(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    /**
     * 根据索引查找值
     *
     * @param index 索引
     * @return 索引对应的值
     * @throws IllegalArgumentException 找不到该索引，抛出索引非法异常
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("索引 [%d] 不合法", index));
        }
        return node.value;
    }

    /**
     * 节点类
     */
    private static class Node {
        /**
         * 值
         */
        int value;
        /**
         * 下一个节点指针
         */
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}

