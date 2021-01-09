package ApplicationLogic;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.time.LocalTime;

public class Notification extends Thread {
    private String title;
    private String description;

    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
        start();
    }

    public void showNotification() {
        if (SystemTray.isSupported()) {
            Notification notification = new Notification(title, description);
            notification.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    private void displayTray() {
        try {
            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();

            // If you want to create an icon in the system tray to preview
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);

            // Display info notification:
            trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.INFO);
            // Error:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
            // Warning:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
        } catch(Exception ex){
            System.err.print(ex);
        }

    }

//    public static void main(String[] args) {
//        if (SystemTray.isSupported()) {
//            Notification notification = new Notification("You have new activity!", "Write a TimeManager for OOP");
//            notification.displayTray();
//        } else {
//            System.err.println("System tray not supported!");
//        }
//    }
}