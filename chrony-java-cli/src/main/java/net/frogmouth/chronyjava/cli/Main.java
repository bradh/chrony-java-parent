package net.frogmouth.chronyjava.cli;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChronyJavaCLI cli = new ChronyJavaCLI();
        cli.processArguments(args);
    }
    
}
