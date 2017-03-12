package com.test.thread;


/**
 * 
 * �߳�ͬ��������
 * 
 * @author zx2015
 *
 */
public class SynchronizedThread {

    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * ��ͬ������ʵ��
         * 
         * @param money
         */
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * ��ͬ�������ʵ��
         * 
         * @param money
         */
        public void saveTo(int money) {
            synchronized (this) {
                account += money;
            }
        }
    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.saveTo(10);
                bank.save(10);
                System.out.println(i + "�˻����Ϊ��" + bank.getAccount());
            }
        }

    }

    /**
     * �����̣߳������ڲ���
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("�߳�1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("�߳�2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    /**
     * main����
     * @param args
     */
    public static void main(String[] args) {
        SynchronizedThread st = new SynchronizedThread();
        st.useThread();
    }

}
