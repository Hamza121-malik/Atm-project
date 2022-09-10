import com.javacourse.controller.AtmController;
import com.javacourse.model.AtmMachine;
import com.javacourse.model.DataProcessor;
import com.javacourse.view.UserInterface;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        AtmMachine atmMachine = new AtmMachine();
        UserInterface userInterface = new UserInterface();
        DataProcessor dataProcessor = new DataProcessor(atmMachine);
        AtmController atmController = new AtmController(atmMachine, dataProcessor, userInterface);
              atmController.init();
    }
}







