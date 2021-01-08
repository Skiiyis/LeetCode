package leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class _1115_print_foobar_alternately {
    public static FooBar2 fooBar = new FooBar2(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class FooBar2 {
        private int n;

        public FooBar2(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    printFoo.run();
                    notify();
                    if (i < n - 1) {
                        wait();
                    }
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    printBar.run();
                    notify();
                    if (i < n - 1) {
                        wait();
                    }
                }
            }
        }
    }

    static class FooBar {
        private int n;
        private ReentrantLock lock = new ReentrantLock();
        private Condition cFoo = lock.newCondition();
        private Condition cBar = lock.newCondition();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    printFoo.run();
                    cBar.signal();
                    cFoo.await();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    printBar.run();
                    cFoo.signal();
                    cBar.await();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
