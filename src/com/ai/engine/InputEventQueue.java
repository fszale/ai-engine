package com.ai.engine;

import com.ai.input.*;
import com.ai.storage.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputEventQueue extends Thread {

    Persona cperson;

    public InputEventQueue(Persona cperson) {
        this.cperson = cperson;
    }

    public void run() {

        do {
            if(InputFactory.eventQueue.size() > 0) {
                String command = InputFactory.eventQueue.remove(0).name;

                switch (command.toLowerCase()) {

                    case "quit.":
                        System.exit(0);
                        return;

                    case "save.":
                        PersonaFactory.save();
                        NeuronFactory.saveDictionary();
                        break;

                    case "load.":
                        PersonaFactory.load();
                        break;

                    case "trace.":
                        PersonaFactory.trace();
                        break;

                    case "help.":
                        showHelp();

                    case "optimize.":
                        // todo: add code to optimize and write code
                        break;

                    case "train.":
                        String name = InputFromKeyboard.readInputLine("Name to train? (default is 'AI Person')\n\r");
                        cperson = PersonaFactory.get(name);
                        break;

                    case "status.":
                        PersonaFactory.status();
                        NeuronFactory.status();
                        FeelingFactory.status();
                        System.out.println("Current persona is " + cperson.name);
                        break;

                    default:
                        // if this is a question and current person is AI then we need to create a quest account
                        if (command.trim().endsWith("?") && cperson.name == "AI Person") {
                            name = InputFromKeyboard.readInputLine("What is your name?\n\r");
                            cperson = PersonaFactory.get(name);
                        }
                        Evaluator.ProcessStatement(cperson, command);
                        break;
                }
            }
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("thread interrupted\n\r");
            }
        }
        while (true);

    }

    private static void showHelp() {

        try {
            List<String> lst = Files.readAllLines(Paths.get("data/help.txt"));
            String[] content = lst.toArray(new String[]{});
            for(int i=0;i<content.length;i++) {
                System.out.println(content[i]);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public String trace() {
        String ret = "";

        return ret;
    }

    public String status() {
        String ret = "";

        return ret;
    }

}
