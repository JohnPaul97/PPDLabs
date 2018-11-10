import sorted.SortedLinkedList1;
import sorted.SortedLinkedListIterator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Iterator;

class MyRunnable implements Runnable {

    private SortedLinkedList1 sortedLinkedList1;
    private int x;
    private int no_operations;
    private Method method;

    public MyRunnable(int x, SortedLinkedList1 list, int no_operations, Method method) {
        this.x = x;
        this.no_operations = no_operations;
        sortedLinkedList1 = list;
        this.method = method;
    }

    @Override
    public void run() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("log2.txt", true))) {
            for (int i = 0; i < no_operations; ++i) {
                method.invoke(sortedLinkedList1, i);
                pw.append("Thread no " + x + " method: " + method.getName() + " param:" + i +" at:" +LocalDateTime.now()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


public class Main {

    public static void main(String[] args) throws Exception {

        final SortedLinkedList1 sortedLinkedList1 = new SortedLinkedList1();

        Method methodAdd = SortedLinkedList1.class.getMethod("insert", double.class);

        Method methodDelete = SortedLinkedList1.class.getMethod("delete", double.class);

        Thread thread = new Thread(new MyRunnable(1, sortedLinkedList1, 10, methodAdd));

        Thread thread1 = new Thread(new MyRunnable(2, sortedLinkedList1, 5, methodAdd));

        Thread thread2 = new Thread(new MyRunnable(3, sortedLinkedList1, 3, methodDelete));

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Iterator<Double> it = sortedLinkedList1.getIterator();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (it.hasNext()) {
                        System.out.println("blabla iterez " + it.next());
                    }
                }

            }
        });
        LocalDateTime.now();
        thread1.run();
        thread.run();
        thread2.run();
        thread3.run();

//        Runnable r1 = new Runnable() {
//            @Override
//            public void run() {
//                try (PrintWriter pw = new PrintWriter(new FileWriter("List.log"))) {
//                    for (int i = 0; i < 10; i++) {
//                        sortedLinkedList1.insert(i);
//                        pw.println("thread 1: " + "insert value " + i);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Runnable r2 = new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 0; i < 5; i++)
//                    sortedLinkedList1.insert(3 * i);
//                pw.println("thread 2: " + "insert value " + 3 * i);
//            }
//        };
//
//        Runnable r3 = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 7; i++) {
//                    try {
//                        sortedLinkedList1.delete(i);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        new Thread(r1).run();
//        new Thread(r2).run();
//        new Thread(r3).run();

        SortedLinkedListIterator iterator = sortedLinkedList1.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        Set<String> set = new HashSet<String>();
//        set.addAll(Arrays.asList("al", "fg", "jhk"));
//        Iterator it = set.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next() + " ");
//            set.add("ESdfgh");
//        }
    }

}