package ApplicationLogic;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Notification {
    private String title;
    private String description;

    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void showNotification() throws AWTException{
        if (SystemTray.isSupported()) {
            Notification notification = new Notification(title, description);
            notification.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    private void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Time Manager Notification");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray Time Manager Notification");
        tray.add(trayIcon);

        trayIcon.displayMessage(title, description, MessageType.INFO);
    }

//    public static void main(String[] args) throws AWTException {
//        if (SystemTray.isSupported()) {
//            Notification notification = new Notification("You have new activity!", "Write a TimeManager for OOP");
//            notification.displayTray();
//        } else {
//            System.err.println("System tray not supported!");
//        }
//    }
}