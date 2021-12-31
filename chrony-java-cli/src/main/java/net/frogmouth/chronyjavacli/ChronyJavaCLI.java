package net.frogmouth.chronyjavacli;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.frogmouth.chronyjava.Command;
import net.frogmouth.chronyjava.Request;
import net.frogmouth.chronyjava.Tracking;

class ChronyJavaCLI {

    void processArguments(String[] args) {
        try {
            Request request = new Request();
            request.setCommand(Command.Tracking);
            byte[] bytes = request.sendRequest();
            Tracking tracking = Tracking.fromBytes(bytes);
        } catch (IOException ex) {
            // TODO: SLF4J
            Logger.getLogger(ChronyJavaCLI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
