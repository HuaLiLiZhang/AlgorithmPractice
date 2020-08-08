package leetcode.easyProblem;

/**
 * @author: Created by zhanghl
 */
public class _24ReverseList {
    public static void main(String[] args) {
        Link head = new Link(1);
        Link next1 = new Link(2);
        Link next2 = new Link(3);
        Link next3 = new Link(4);
        Link next4 = new Link(5);
        head.next = next1;
        next1.next = next2;
        next2.next = next3;
        next3.next = next4;
        next4.next = null;
        Link newhead = reverse(head);
        System.out.println(newhead.value);
        while (newhead!=null){
            System.out.println(newhead.value);
            newhead = newhead.next;
        }
    }

    public static Link reverse(Link head) {
        Link newhead = null;
        while (head != null) {
            Link tmp = head.next;
            head.next = newhead;
            newhead = head;
            head = tmp;
        }
        return newhead;
    }
}

class Link {
    public int value;
    public Link next;

    private Link() {
    }

    public Link(int value) {
        this.value = value;
    }

}
