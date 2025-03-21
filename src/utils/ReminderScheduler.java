package utils;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Timer;

public class ReminderScheduler {
    private static Map<Integer, LocalTime> reminders = new HashMap<>();
    private static Timer timer = new Timer();

    // Schedule a reminder for the medicine
    public static void scheduleReminder(int medId, String medicineName, LocalTime reminderTime) {
        reminders.put(medId, reminderTime);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Time to take: " + medicineName);
            }
        };

        long delay = calculateDelay(reminderTime);
        timer.schedule(task, delay);
    }

    // Calculate delay for the reminder
    private static long calculateDelay(LocalTime reminderTime) {
        LocalTime now = LocalTime.now();
        long delay = java.time.Duration.between(now, reminderTime).toMillis();
        return delay > 0 ? delay : delay + 24 * 60 * 60 * 1000; // Handles next-day reminders
    }
}
