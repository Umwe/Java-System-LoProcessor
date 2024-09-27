import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

// Class for analyzing system logs
public class LogAnalyzer {
    
    // Enum to define log levels
    public enum LogLevel {
        INFO, WARN, ERROR, DEBUG;
    }

    // Class to represent a single log entry
    public static class LogEntry {
        Date timestamp;
        LogLevel level;
        String message;

        public LogEntry(Date timestamp, LogLevel level, String message) {
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
        }
        
        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(timestamp) + " [" + level + "] " + message;
        }
    }

    private List<LogEntry> logs = new ArrayList<>();

    // Method to load logs from a file
    public void loadLogs(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split("\\|");
                Date timestamp = sdf.parse(parts[0].trim());
                LogLevel level = LogLevel.valueOf(parts[1].trim().toUpperCase());
                String message = parts[2].trim();
                
                logs.add(new LogEntry(timestamp, level, message));
            } catch (Exception e) {
                System.out.println("Skipping malformed log entry: " + line);
            }
        }
        reader.close();
    }

    // Method to filter logs by log level
    public List<LogEntry> filterByLevel(LogLevel level) {
        List<LogEntry> filteredLogs = new ArrayList<>();
        for (LogEntry log : logs) {
            if (log.level == level) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs;
    }

    // Method to generate a summary report of logs
    public void generateReport() {
        Map<LogLevel, Integer> levelCounts = new HashMap<>();
        
        for (LogEntry log : logs) {
            levelCounts.put(log.level, levelCounts.getOrDefault(log.level, 0) + 1);
        }

        System.out.println("Log Report Summary:");
        for (LogLevel level : LogLevel.values()) {
            System.out.println(level + ": " + levelCounts.getOrDefault(level, 0));
        }
    }

    // Method to get logs within a certain time range
    public List<LogEntry> getLogsBetween(Date start, Date end) {
        List<LogEntry> timeFilteredLogs = new ArrayList<>();
        for (LogEntry log : logs) {
            if (!log.timestamp.before(start) && !log.timestamp.after(end)) {
                timeFilteredLogs.add(log);
            }
        }
        return timeFilteredLogs;
    }

    public static void main(String[] args) {
        try {
            LogAnalyzer analyzer = new LogAnalyzer();
            analyzer.loadLogs("system_logs.txt");
            
            System.out.println("ERROR logs:");
            for (LogEntry log : analyzer.filterByLevel(LogLevel.ERROR)) {
                System.out.println(log);
            }
            
            analyzer.generateReport();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = sdf.parse("2024-09-01 00:00:00");
            Date end = sdf.parse("2024-09-28 23:59:59");
            List<LogEntry> recentLogs = analyzer.getLogsBetween(start, end);
            
            System.out.println("\nLogs between " + start + " and " + end + ":");
            for (LogEntry log : recentLogs) {
                System.out.println(log);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
