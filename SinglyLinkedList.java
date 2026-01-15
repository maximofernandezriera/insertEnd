public class SinglyLinkedList<E> {
    private static final class Node<E> {
        private final E info;
        private Node<E> next;

        private Node(E info) {
            this.info = info;
        }
    }

    private Node<E> head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void insertFirst(E info) {
        Node<E> node = new Node<>(info);
        node.next = head;
        head = node;
        size++;
    }

    public void insertEnd(E info) {
        Node<E> node = new Node<>(info);

        if (isEmpty()) {
            head = node;
            size++;
            return;
        }

        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = node;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Node<E> current = head;
        while (current != null) {
            sb.append(current.info);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append(']');
        return sb.toString();
    }
}
