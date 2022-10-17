public class Envejecimiento extends Thread {
    private RAM ram;
    private Boolean running;

    public Envejecimiento(RAM ram) {
        this.ram = ram;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1);
                ram.envejecerMarcos();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}