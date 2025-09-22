package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static final String LOG_FILE = "logs.txt";

    // Méthode pour log normal
    public static void log(String message) {
        String logMessage = "[INFO] " + LocalDateTime.now() + " - " + message;
        System.out.println(logMessage);
        writeToFile(logMessage);
    }

    // Méthode pour log erreur
    public static void error(String message) {
        String logMessage = "[ERROR] " + LocalDateTime.now() + " - " + message;
        System.err.println(logMessage);
        writeToFile(logMessage);
    }

    // Sauvegarde dans fichier
    private static void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("Impossible d'écrire dans le fichier de log : " + e.getMessage());
        }
    }
}
