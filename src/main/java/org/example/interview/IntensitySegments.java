package org.example.interview;

import java.util.Arrays;

public class IntensitySegments {

    private PosNode head;

    public void add(int from,int to,int amount){
        if (from >= to) {
            return;
        }

        if (head == null) {
            head = new PosNode(Integer.MIN_VALUE,0);
            PosNode node = new PosNode(from,amount);
            node.setNext(new PosNode(to,0));
            head.setNext(node);
        } else {
            // find a position to insert change point "from"
            PosNode flag = head;
            int curAmount = flag.getAmount();

            while (flag.getNext() != null && flag.getNext().getPosition() < from){
                flag = flag.getNext();
                curAmount = flag.getAmount();
            }

            /**
             * if arrive here, flag point to the last change point before "from"
             * 3 cases:
             * 1. flag's next is null, we insert both "from point" and "to point"
             * 2. flag's next is same from point, we just add amount to it
             * 3. flag's next is larget than "from", we insert "from point" before its next
             */

            if (flag.getNext() == null ) {
                PosNode node = new PosNode(from,amount);
                node.setNext(new PosNode(to,curAmount));
                flag.setNext(node);
            } else if (flag.getNext().getPosition() == from) {
                flag.getNext().addAmount(amount);
                curAmount = flag.getNext().getAmount();
                flag = flag.getNext();
            } else {
                curAmount += amount;
                PosNode node = new PosNode(from,curAmount);
                node.setNext(flag.getNext());
                flag.setNext(node);
                flag = flag.getNext();
            }

            // find a position to insert change point to
            while (flag.getNext() != null && flag.getNext().getPosition() < to) {
                flag = flag.getNext();
                curAmount = flag.getAmount();
                // before we arrive "to point", all change point's amount will be added
                flag.addAmount(amount);
            }

            /**
             * if arrive here, flag point to the last change point before "to"
             * 3 cases:
             * 1. flag's next is null, we insert "to point"
             * 2. flag's next is same "to point", we do nothing
             * 3. flag's next is larget than "to", we insert "to point" before its next
             */

            if (flag.getNext() == null) {
                PosNode node = new PosNode(to,curAmount);
                flag.setNext(node);
            } else if (flag.getNext().getPosition() > to) {
                PosNode node = new PosNode(to,curAmount);
                node.setNext(flag.getNext());
                flag.setNext(node);
            }
        }
        removeDuplicate();
    }

    public void set(int from,int to,int amount){
        if (from >= to) {
            return;
        }
        if (head == null) {
            head = new PosNode(Integer.MIN_VALUE,0);
            PosNode node = new PosNode(from,amount);
            node.setNext(new PosNode(to,0));
            head.setNext(node);
        } else {
            // find a position to insert change point from
            PosNode flag = head;
            int curAmount = flag.getAmount();

            while (flag.getNext() != null && flag.getNext().getPosition() < from){
                flag = flag.getNext();
                curAmount = flag.getAmount();
            }

            /**
             * if arrive here, flag point to the last change point before "from"
             * 3 cases:
             * 1. flag's next is null, we insert both "from point" and "to point"
             * 2. flag's next is same from point, we just change amount to it
             * 3. flag's next is larget than "from", we insert "from point" before its next
             */

            if (flag.getNext() == null) {
                PosNode node = new PosNode(from,amount);
                node.setNext(new PosNode(to,0));
                flag.setNext(node);
            } else if (flag.getNext().getPosition() == from) {
                flag.getNext().setAmount(amount);
                flag = flag.getNext();
            } else {
                PosNode node = new PosNode(from,amount);
                node.setNext(flag.getNext());
                flag.setNext(node);
                flag = flag.getNext();
            }

            // now flag point to new from point, all point before "to" will be deleted
            PosNode newFlag = flag;
            while (newFlag.getNext() != null && newFlag.getNext().getPosition() < to) {
                newFlag = newFlag.getNext();
                curAmount = newFlag.getAmount();
            }

            /**
             * if arrive here, newflag point to the last change point before "to"
             * 3 cases:
             * 1. newflag's next is null, we insert "to point"
             * 2. newflag's next is same "to point", we set its next as flag's next
             * 3. newflag's next is larget than "to", we insert "to point" before its next
             */

            if (newFlag.getNext() == null) {
                PosNode node = new PosNode(to,curAmount);
                flag.setNext(node);
            } else if (newFlag.getNext().getPosition() == to) {
                flag.setNext(newFlag.getNext());
            } else if (newFlag.getNext().getPosition() > to) {
                PosNode node = new PosNode(to,curAmount);
                node.setNext(newFlag.getNext());
                flag.setNext(node);
            }
        }
        removeDuplicate();
    }

    /**
     * remove duplicate change point
     * when two sibling change point has same amount, delete the second one
     */
    private void removeDuplicate(){
        PosNode flag = head;
        while (flag != null && flag.getNext() != null) {
            if (flag.getNext().getAmount() == flag.getAmount()) {
                flag.setNext(flag.getNext().getNext());
            }
            flag = flag.getNext();
        }
    }

    @Override
    public String toString (){
        if (head == null) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            PosNode flag = head.getNext();
            while (flag != null) {
                sb.append(flag.toString());
                flag = flag.getNext();
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // test case 1
        IntensitySegments segments = new IntensitySegments();
        segments.add(10,30,1);
        System.out.println(segments.toString());
        segments.add(20, 40, 1);
        System.out.println(segments.toString());
        segments.add(10, 40, -2);
        System.out.println(segments.toString());

        //test case 2
        segments = new IntensitySegments();
        segments.add(10, 30, 1);
        System.out.println(segments.toString());
        segments.add(20, 40, 1);
        System.out.println(segments.toString());
        segments.add(10, 40, -1);
        System.out.println(segments.toString());
        segments.add(10, 40, -1);
        System.out.println(segments.toString());

        //test case set
        segments = new IntensitySegments();
        segments.add(10, 30, 1);
        System.out.println(segments.toString());
        segments.set(20,25,5);
        System.out.println(segments.toString());
        segments.set(23,33,8);
        System.out.println(segments.toString());
        segments.set(20,33,5);
        System.out.println(segments.toString());
        segments.set(9,33,3);
        System.out.println(segments.toString());
    }

    class PosNode {
        int position;
        int amount;
        PosNode next;

        public PosNode(int position,int amount){
            this.position = position;
            this.amount = amount;
            next = null;
        }

        public int getAmount() {
            return amount;
        }

        public void addAmount(int amount) {
            this.amount += amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public PosNode getNext() {
            return next;
        }

        public void setNext(PosNode node) {
            this.next = node;
        }

        @Override
        public String toString (){
            int [] ret = new int[2];
            ret[0] = this.position;
            ret[1] = this.amount;
            return Arrays.toString(ret);
        }
    }
}


