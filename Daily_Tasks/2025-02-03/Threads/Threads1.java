package Example;

class Threads1 extends Thread {
    public void run() {
        System.out.println("Hello, Java!");
    }

    public static void main(String[] args) {
        Threads1 thread = new Threads1();
        thread.start();
    }
}
