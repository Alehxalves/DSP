package br.ufc.dspersist;

public class CLS {
    public static void clearConsole() throws Exception {
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
}
