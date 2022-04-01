package com.aca;

import java.util.*;

public class Contacts {

    public Map<String, String> numberName;
    public Map<String, PhoneType> numberType;
    public Map<String, String> emailName;
    public Map<String, EmailType> emailEmailType;
    public List<User> contacts;

    Scanner scanner = new Scanner(System.in);

    public Contacts() {
        this.contacts = new ArrayList<>();
        this.numberName = new TreeMap<>();
        this.numberType = new TreeMap<>();
        this.emailName = new TreeMap<>();
        this.emailEmailType = new TreeMap<>();
    }

    private PhoneType getPhoneType() {
        System.out.println("1.Mobile");
        System.out.println("2.Home");
        System.out.println("3.Work");
        System.out.println("4.School");
        System.out.println("5.Company");
        String phoneTypeQuestion = scanner.nextLine();
        switch (phoneTypeQuestion) {
            case "1":
                return PhoneType.MOBILE;
            case "2":
                return PhoneType.HOME;
            case "3":
                return PhoneType.WORK;
            case "4":
                return PhoneType.SCHOOL;
            case "5":
                return PhoneType.COMPANY;
            default:
                System.out.println("Wrong input !");
                System.out.println("Try again");
                return null;
        }
    }

    private String cuttingMailForCheckingType(String mail) {
        int substringStart = 0;
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == '@') {
                substringStart = i;
            }
        }
        return mail.substring(substringStart);
    }

    private EmailType getEmailType() {
        System.out.println("1.Mail");
        System.out.println("2.Gmail");
        System.out.println("3.Icloud");
        System.out.println("4.Yahoo");
        System.out.println("5.Yandex");
        System.out.println("6.Aca");
        System.out.println("Press anything for Other Mails");
        String emailTypeQuestion = scanner.nextLine();
        switch (emailTypeQuestion) {
            case "1":
                return EmailType.MAIL;
            case "2":
                return EmailType.GMAIL;
            case "3":
                return EmailType.ICLOUD;
            case "4":
                return EmailType.YAHOO;
            case "5":
                return EmailType.YANDEX;
            case "6":
                return EmailType.ACA;
            default:
                return EmailType.OTHER;
        }
    }

    public void creat() {
        User user = new User();
        String email;
        EmailType emailType;
        PhoneType phoneType;
        System.out.println("Input Name");
        String name = scanner.nextLine();
        name = Validator.replaceSpace(name);
        user.setName(name);
        System.out.println("Input Phone");
        String phone;
        do {
            phone = scanner.nextLine();
            phone = Validator.deleteSpace(phone);
            if (Validator.contains(phone, numberName)) {
                System.out.println("This phone number is buse. Try again.");
            }
            if (!Validator.isPhoneNumber(phone)) {
                System.out.println("Phone number is not valid");
            }
        } while (Validator.contains(phone, numberName) || !Validator.isPhoneNumber(phone));
        user.setPhoneNumber(phone);
        numberName.put(phone, name);
        System.out.println("Input Phone Type");
        do {
            phoneType = getPhoneType();
            user.setPhoneType(phoneType);
            numberType.put(phone, phoneType);
        } while (phoneType == null);
        System.out.println("You have email ? Y->Yes : Other input->No");
        String emailQuestion = scanner.nextLine();
        if (emailQuestion.equals("Y")) {
            System.out.println("Input Email");
            do {
                email = scanner.nextLine();
                if (Validator.contains(email, emailName)) {
                    System.out.println("This email is buse. Try again.");
                }
                if (!Validator.isMail(email)) {
                    System.out.println("This email is not valid");
                }
            } while (Validator.contains(email, emailName) || !Validator.isMail(email));
            user.setEmail(email);
            emailName.put(email, name);
            user.setEmailType(EmailType.getMailType(cuttingMailForCheckingType(email)));
            emailEmailType.put(email, EmailType.getMailType(cuttingMailForCheckingType(email)));
//            System.out.println("Input Email Type");
//            do {
//                emailType = getEmailType();
//                user.setEmailType(emailType);
//                emailEmailType.put(email, emailType);
//            } while (emailType == null);
        }
        contacts.add(user);
    }

    public void read() {
        for (User us : contacts) {
            System.out.println(us);
        }
    }

    public void update() {
        String question;
        String name;
        String mail;
        EmailType emailType;
        PhoneType phoneType;
        String TypeQuestion;
        boolean flag;
        System.out.println("Input the phone number which to be updated");
        String number = scanner.nextLine();
        if (numberName.containsKey(number)) {
            System.out.println("Update name ? Y->Yes : Other input->No");
            question = scanner.nextLine();
            if (question.equals("Y")) {
                System.out.println("Input new name.This time name is -> " + numberName.get(number));
                name = scanner.nextLine();
                name = Validator.replaceSpace(name);
                for (User user : contacts) {
                    if (user.getPhoneNumber().equals(number)) {
                        if (user.getEmail() != null) {
                            user.setName(name);
                            emailName.put(user.getEmail(), name);
                            numberName.put(number, name);
                        } else {
                            user.setName(name);
                            numberName.put(number, name);
                        }
                    }
                }
            }
            System.out.println("Update phone type ? Y->Yes : Other input->No");
            question = scanner.nextLine();
            if (question.equals("Y")) {
                System.out.println("Input new phone type.This time phone type is -> " + numberType.get(number));
                for (User user : contacts) {
                    {
                        do {
                            phoneType = getPhoneType();
                            if (user.getPhoneNumber().equals(number)) {
                                user.setPhoneType(phoneType);
                                numberType.put(number, phoneType);
                            }
                        } while (phoneType == null);
                        break;
                    }
                }
            }
            System.out.println("Update email ? Y->Yes : Other input->No");
            question = scanner.nextLine();

            if (question.equals("Y")) {
                for (User user : contacts) {
                    if (user.getPhoneNumber().equals(number)) {
                        System.out.println("Input new email.This time email is -> " + user.getEmail());
                        do {
                            flag = false;
                            mail = scanner.nextLine();
                            if (emailName.containsKey(mail)) {
                                System.out.println("This email is busy");
                                flag = true;
                            }
                            if (!Validator.isMail(mail)) {
                                System.out.println("Mail is not valid");
                            }
                        } while (flag || !Validator.isMail(mail));
//                            if (user.getEmail() != null) {
//                                emailName.remove(user.getEmail());
//                                emailName.put(mail, user.getName());
//                                emailEmailType.remove(user.getEmail());
//                                emailEmailType.put(mail, user.getEmailType());
//                                user.setEmail(mail);
//                            } else {
//                                emailName.put(mail, user.getName());
//                                emailEmailType.put(mail, user.getEmailType());
//                                user.setEmail(mail);
//                            }
                        emailName.put(mail, user.getName());
                        emailEmailType.put(mail, user.getEmailType());
                        user.setEmail(mail);
                        user.setEmailType(EmailType.getMailType(cuttingMailForCheckingType(mail)));
                        emailEmailType.put(mail, EmailType.getMailType(cuttingMailForCheckingType(mail)));

                        break;
                    }
                }
            }
//                for (User user : contacts) {
//                    if (user.getPhoneNumber().equals(number) && user.getEmail() != null) {
//
//                        System.out.println("Update email type ? Y->Yes : Other input->No");
//                        question = scanner.nextLine();
//                        if (question.equals("Y")) {
//                            System.out.println("Input new email type.This time email type is -> " + user.getEmailType());
//                            do {
//                                emailType = getEmailType();
//                                user.setEmailType(emailType);
//                                emailEmailType.put(user.getEmail(), emailType);
//                            } while (emailType == null);
//                            break;
//                        }
//                    }
//                }
        } else {
            System.out.println("Such a phone number does not exist");
        }

    }



    public void delete() {
        System.out.println("Input phone number which one want to delete");
        String deleteNumber = scanner.nextLine();
        if (numberName.containsKey(deleteNumber)) {
            for (User user : contacts) {
                if (user.getPhoneNumber().equals(deleteNumber)) {
                    if (user.getEmail() != null) {
                        numberName.remove(user.getPhoneNumber());
                        numberType.remove(user.getPhoneNumber());
                        emailName.remove(user.getEmail());
                        emailEmailType.remove(user.getEmail());
                    } else {
                        numberName.remove(user.getPhoneNumber());
                        numberType.remove(user.getPhoneNumber());
                    }
                    contacts.remove(user);
                    break;
                }
            }
        } else {
            System.out.println("Such a phone number does not exist");
        }
    }

    public void search() {
        System.out.println("Input search variant");
        System.out.println("1.Search from name");
        System.out.println("2.Search from phone number");
        System.out.println("3.Search from phone type");
        System.out.println("4.Search from email");
        System.out.println("5.Search from email type");
        System.out.println("Other input for back");
        String variant = scanner.nextLine();
        switch (variant) {
            case "1":
                searchFromName();
                break;
            case "2":
                searchFromNumber();
                break;
            case "3":
                searchFromNumberType();
                break;
            case "4":
                searchFromEmail();
                break;
            case "5":
                searchFromEmailType();
                break;
            default:
                break;
        }
    }

    public void searchFromNumberType() {
        System.out.println("Input Number Type for search");
        PhoneType phoneType = getPhoneType();
        for (String s : numberType.keySet()) {
            if (numberType.get(s) == phoneType) {
                System.out.println(numberName.get(s) + " : " + s + " : " + numberType.get(s));
            }
        }
    }

    public void searchFromEmail() {
        System.out.println("Input email for search");
        String email = scanner.nextLine();
        for (String s : emailName.keySet()) {
            if (s.toLowerCase().contains(Validator.deleteSpace(email.toLowerCase()))) {
                System.out.println(emailName.get(s) + " : " + s);
            }
        }
    }

    public void searchFromEmailType() {
        System.out.println("Input email type for search");
        EmailType emailType = getEmailType();
        for (String s : emailEmailType.keySet()) {
            if (emailEmailType.get(s) == emailType) {
                System.out.println(emailName.get(s) + " : " + s + " : " + emailEmailType.get(s));
            }
        }
    }

    public void searchFromNumber() {
        String searchNumber = scanner.nextLine();
        for (String s : numberName.keySet()) {
            if (Validator.deleteSpace(s).contains(Validator.deleteSpace(searchNumber))) {
                System.out.println(numberName.get(s) + " : " + s);
            }
        }
    }

    public void searchFromName() {
        String searchName = scanner.nextLine();
        for (String s : numberName.keySet()) {
            if (Validator.deleteSpace(numberName.get(s).toLowerCase()).contains(Validator.deleteSpace(searchName).toLowerCase())) {
                System.out.println(numberName.get(s) + " : " + s);
            }
        }
    }
}
