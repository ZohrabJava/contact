package com.aca;

import java.util.Scanner;

public class Service {
    public static void start() {
        Contacts contacts = new Contacts();
        Scanner scanner = new Scanner(System.in);
        String stepOne;
        boolean flagOne;
        do {
            System.out.println("1. Creat contact");
            System.out.println("2. Read contact");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Exit");
            flagOne = true;
            stepOne = scanner.nextLine();
            switch (stepOne) {
                case "1":
                    contacts.creat();
                    break;
                case "2":
                    contacts.read();
                    break;
                case "3":
                    contacts.update();
                    break;
                case "4":
                    contacts.delete();
                    break;
                case "5":
                    flagOne = false;
                    break;
                default:
                    System.out.println("Wrong Input !");
                    System.out.println("Try Again");
                    break;
            }
        } while (flagOne);
    }
}
