import controller.Controller;
import view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View();
        controller.setView(view);
        view.setController(controller);
    }
}
