package dev.thebjoredcraft.building.logger;

import dev.thebjoredcraft.building.Building3IX;
import dev.thebjoredcraft.building.server.Console;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DLogger {
    public static File loggerFile = new File(Building3IX.getInstance().getDataFolder(), "log.dLogger");

    public static List<String> getLogs(int lines) {
        List<String> lastLogs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loggerFile));
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                if (++lineCount > Math.max(0, lineCount - lines)) {
                    lastLogs.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            Console.logError(e.getMessage());
        }
        return lastLogs;
    }

    public static void log(LogType logType, String toLog) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Date date = new Date();

        String logEntry = dateFormat.format(date) + " " + logType.getPrefix() + " " + toLog;

        try {
            FileWriter writer = new FileWriter(loggerFile, true);
            writer.write(logEntry + "\n");

            writer.close();

        } catch (IOException e) {
            Console.logError(e.getMessage());
        }
    }
}
