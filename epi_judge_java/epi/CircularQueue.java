package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
public class CircularQueue {

    public static class Queue{
        public Integer queueArray[];
        public int front;
        public int rear;
        public int maxSize;

        public Queue(int capacity) {
            maxSize = capacity + 1;
            front = 1;
            rear = 0;
            queueArray = new Integer[maxSize];
        }

        public void enqueue(Integer x) {
            if (((rear + 2) % maxSize) == front) {
                Integer[] newQueueArray = new Integer[maxSize * 2];
                for (int i = 0; i < size(); i++) {
                    newQueueArray[front + i] = queueArray[(front + i) % maxSize];
                }    
                rear = front + size() - 1;
                maxSize *= 2;
                queueArray = newQueueArray;
            }

            rear = (rear + 1) % maxSize;
            queueArray[rear] = x;
        } 

        public Integer dequeue() {
            if (size() == 0) {
                return null;
            }
            Integer retVal = queueArray[front];
            front = (front + 1) % maxSize;
            return retVal;
        }
        
        public int size() {

            return ((rear + maxSize) - front + 1) % maxSize;
        }

        @Override
        public String toString() {
            // TODO - you fill in here.
            return super.toString();
        }

    }
    // public static class Queue {
    //     private Integer queueArray[];
    //     private int front;
    //     private int rear;
    //     private int maxSize;

    //     public Queue(int capacity) {
    //         maxSize = capacity + 1;
    //         queueArray = new Integer[maxSize];
    //         front = 1;
    //         rear = 0;
    //     }

    //     public void enqueue(Integer x) {
    //         // TODO - you fill in here.
    //         if ((rear + 2) % maxSize == front) {
    //             Integer newQueueArray[] = new Integer[maxSize * 2];
    //             for (int i = 0; i < size(); i++) {
    //                 newQueueArray[front + i] = queueArray[(front + i) % maxSize];
    //             }
    //             rear = front + size() - 1;
    //             maxSize *= 2;


    //             queueArray = newQueueArray;

    //         }
    //         rear = (rear + 1) % maxSize;
    //         queueArray[rear] = x; 
    //         return;
    //     }

    //     public Integer dequeue() {
    //         // TODO - you fill in here.
    //         if (size() == 0) {
    //             return null;
    //         }
    //         Integer retVal = queueArray[front];
    //         front = (front + 1) % maxSize;

    //         return retVal;
    //     }
    //     public int size() {
    //         // TODO - you fill in here.
    //         return ((rear + maxSize) - front + 1) % maxSize;
    //     }
    //     @Override
    //     public String toString() {
    //         // TODO - you fill in here.
    //         return super.toString();
    //     }
    // }
    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }

        @Override
        public String toString() {
            return op;
        }
    }

    @EpiTest(testDataFile = "circular_queue.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        Queue q = new Queue(1);
        int opIdx = 0;
        for (QueueOp op : ops) {
            switch (op.op) {
                case "Queue":
                    q = new Queue(op.arg);
                    break;
                case "enqueue":
                    q.enqueue(op.arg);
                    break;
                case "dequeue":
                    int result = q.dequeue();
                    if (result != op.arg) {
                        throw new TestFailure()
                            .withProperty(TestFailure.PropertyName.STATE, q)
                            .withProperty(TestFailure.PropertyName.COMMAND, op)
                            .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                            .withProperty(TestFailure.PropertyName.STATE, q)
                            .withProperty(TestFailure.PropertyName.COMMAND, op)
                            .withMismatchInfo(opIdx, op.arg, s);
                    }
                    break;
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                .runFromAnnotations(args, "CircularQueue.java",
                    new Object() {}.getClass().getEnclosingClass())
                .ordinal());
    }
}
