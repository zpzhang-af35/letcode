package org.example.interview;

import java.util.HashSet;
import java.util.Set;
/**
 * 滴滴一面
 * @date 2025/11/18
 * 实现一个简单的链表，检测有没有环
 */
public class Link {

    public static boolean haveCicle(Node head){
        Set<Node> traveled = new HashSet<>();
        while(head != null){
            if(traveled.contains(head)){
                return true;
            }
            traveled.add(head);
            head = head.next;
        }

        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node sec = new Node(2);
        Node third = new Node(3);
        Node forth = new Node(4);
        head.setNext(sec);
        sec.setNext(third);
        third.setNext(forth);
        forth.setNext(sec);
        System.out.println(haveCicle(head));
    }
}

class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }

    void setNext(Node node){
        this.next = node;
    }
}
