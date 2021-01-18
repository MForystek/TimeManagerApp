package ApplicationLogic;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public abstract class Notification {

    public static void show(String title, String description, int type) {
        if (SystemTray.isSupported()) {
            displayTray(title, description, type);
        } else {
            System.err.println("System tray not supported!");
        }
    }

    private static void displayTray(String title, String description, int type) {
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
            switch (type) {
                case 2:
                    // Error:
                    trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
                case 3:
                    // Warning:
                    trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
                default:
                    // Info:
                    trayIcon.displayMessage(title, description, MessageType.INFO);
            }
            tray.remove(trayIcon);
        } catch(Exception e){
            System.out.print(e);
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