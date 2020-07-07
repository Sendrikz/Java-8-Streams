package video_course.lamda;

public class Main {

    public static void main(String[] args) {

        Runnable task = () -> {
            new BussinessTaskUpdateCustomerAge().updateDB();
            try {
                Thread.sleep(1000);
                System.out.println("Massive update");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executeTask(task);
        executeTask(() -> {
            ((BussinessTask) () -> {

            }).updateStateInDBByDefault();
            try {
                Thread.sleep(1000);
                System.out.println("Massive update");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    private static void executeTask(Runnable threadLogic) {
        new Thread(threadLogic).start();
    }
}
