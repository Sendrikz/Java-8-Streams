package video_course.lamda;

public interface BussinessTask {
    void updateDB();

    default void updateStateInDBByDefault() {
        System.out.println("Default update method in interface");
    }
}
