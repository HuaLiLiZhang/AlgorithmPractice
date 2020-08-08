package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 */
public class _24ReverseList {
    public static void main(String[] args) {
        LinkNode head = new LinkNode(1);
        LinkNode next1 = new LinkNode(2);
        LinkNode next2 = new LinkNode(3);
        LinkNode next3 = new LinkNode(4);
        LinkNode next4 = new LinkNode(5);
        head.next = next1;
        next1.next = next2;
        next2.next = next3;
        next3.next = next4;
        next4.next = null;
        LinkNode newhead = reverse(head);
        System.out.println(newhead.value);
        while (newhead!=null){
            System.out.println(newhead.value);
            newhead = newhead.next;
        }
    }

    public static LinkNode reverse(LinkNode head) {
        LinkNode newhead = null;
        while (head != null) {
            LinkNode tmp = head.next;
            head.next = newhead;
            newhead = head;
            head = tmp;
        }
        return newhead;
    }
}

class LinkNode {
    public int value;
    public LinkNode next;

    private LinkNode() {
    }

    public LinkNode(int value) {
        this.value = value;
    }

}
