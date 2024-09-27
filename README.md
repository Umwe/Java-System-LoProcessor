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

