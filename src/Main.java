import com.ai.engine.Evaluator;
import com.ai.input.InputEvent;
import com.ai.input.InputFactory;
import com.ai.input.InputFromKeyboard;
import com.ai.storage.*;
import javafx.scene.input.InputMethodTextRun;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.*;
import java.util.logging.Level;
import com.ai.engine.*;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // debugger off
        LOGGER.setLevel(Level.OFF);

        PersonaFactory.init();
        Persona cperson = PersonaFactory.get("AI Person");

        FeelingFactory.init();
        InputFactory.init();

        InputFactory.run();

        InputEventQueue ieq = new InputEventQueue(cperson);
        ieq.start();

/*
        String command = "";
        do
        {

        }
        while (true);
        */
    }
}
