# System Log Analyzer

## Overview
**System Log Analyzer** is a Java-based tool designed to process, analyze, and generate reports from system log files. It categorizes logs by severity levels (INFO, WARN, ERROR, DEBUG) and provides a variety of functionalities, such as filtering logs by level, fetching logs within a specific time range, and generating summary reports.

## Features
- **Log Parsing**: Load log entries from a file in the format `TIMESTAMP | LEVEL | MESSAGE`.
- **Filtering by Log Level**: Retrieve logs based on severity (INFO, WARN, ERROR, DEBUG).
- **Time Range Filtering**: Fetch logs within a specific date and time range.
- **Summary Report**: Generate a report summarizing the number of log entries for each severity level.
- **Handles Malformed Entries**: Skips any improperly formatted log entries and continues processing.

## Log Format
The tool expects logs to be in the following format:

Each log entry must include:
- **Timestamp** in the format `yyyy-MM-dd HH:mm:ss`
- **Log Level** (`INFO`, `WARN`, `ERROR`, `DEBUG`)
- **Message** describing the log event

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/system-log-analyzer.git
    ```
2. Navigate to the project directory:
    ```bash
    cd system-log-analyzer
    ```

3. Compile the Java files:
    ```bash
    javac LogAnalyzer.java
    ```

4. Run the program:
    ```bash
    java LogAnalyzer
    ```

## Usage

1. **Loading Logs**: The tool reads log entries from a file and processes them. Ensure your log file is structured correctly before loading it into the program.

2. **Generate Summary Report**: After logs are loaded, you can generate a report that summarizes the count of each log level.

3. **Filter Logs**: Filter logs by their severity (INFO, WARN, ERROR, DEBUG) or by a date range.

Example of running the program:
```java
public class Main {
    public static void main(String[] args) {
        try {
            LogAnalyzer analyzer = new LogAnalyzer();
            analyzer.loadLogs("path/to/log_file.txt");
            
            // Generate the log report
            analyzer.generateReport();
            
            // Filter logs by level
            List<LogAnalyzer.LogEntry> errorLogs = analyzer.filterByLevel(LogAnalyzer.LogLevel.ERROR);
            for (LogAnalyzer.LogEntry log : errorLogs) {
                System.out.println(log);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

Log Report Summary:
INFO: 150
WARN: 23
ERROR: 5
DEBUG: 42

ERROR logs:
2024-09-28 14:33:52 [ERROR] Connection timed out

