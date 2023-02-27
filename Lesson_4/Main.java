package Lesson_4;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Phonebook.addContact("Yuriy");
        Phonebook.addContact("Maxim", "+77075764199");
        Phonebook.showAll();
        Phonebook.addPhone("Denis", "+77775764539");
        Phonebook.addPhone("Saha", "+77055863199");
        Phonebook.addPhone("Alex", "+77872764179");
        Phonebook.showAll();
        Phonebook.movePhoneToContact("Alex", "Yuriy", "+77972764179");
        Phonebook.replaceNumber("Alex", "+77055863199", "+77778764890");
        Phonebook.deletePhone("Alex", "+77778764890");
        Phonebook.showAll();
        Phonebook.deleteContact("Maxim");
        Phonebook.showAll();
        Phonebook.find("Alex");
        Phonebook.find("Maxim");
        Phonebook.eraseBook();
        Phonebook.showAll();
    }
}


abstract class Phonebook {
    private static final HashMap<String, HashSet<String>> phoneBook = new HashMap<>();

    public static void addContact(String login) {
        if (!phoneBook.containsKey(login)) {
            HashSet<String> PhoneBook = new HashSet<>();
            phoneBook.put(login, PhoneBook);
        } else {
            System.out.printf("Contact \"%s\"is there\n", login);
        }
    }

    public static void addContact(String login, String phone) {
        if (!phoneBook.containsKey(login)) {
            HashSet<String> PhoneBook = new HashSet<>();
            PhoneBook.add(phone);
            phoneBook.put(login, PhoneBook);
        } else {
            System.out.printf("Contact \"%s\"is there\n", login);
        }
    }

    public static void addPhone(String login, String phone) {
        if (!(login == null && phone == null)) {
            if (phoneBook.get(login) == null) {
                HashSet<String> PhoneBook = new HashSet<>();
                PhoneBook.add(phone);
                phoneBook.put(login, PhoneBook);
            } else if (phoneBook.get(login).contains(phone)) {
                System.out.println("This number is there\n");
            } else {
                phoneBook.get(login).add(phone);
            }
        } else {
            System.out.println("Something wrong\n");
        }
    }

    public static void movePhoneToContact(String fromContact, String toContact, String phone) {
        if (phoneBook.containsKey(fromContact) && phoneBook.containsKey(toContact)) {
            if (phoneBook.get(fromContact).contains(phone)) {
                addPhone(toContact, phone);
                deletePhone(fromContact, phone);
            } else {
                System.out.println("Something wrong\n");
            }
        }
    }

    public static void find(String login) {
        if (phoneBook.containsKey(login)) {
            System.out.println("Found contact ↓");
            System.out.println(login);
            phoneBook.get(login).forEach(System.out::println);
            System.out.println("Found contact ↑\n");
        } else {
            System.out.printf("Can`t find user \"%s\"\n", login);
        }
    }

    public static void showAll() {
        System.out.println("All contacts ↓");
        phoneBook.forEach((name, PhoneBook) -> {
            System.out.println(name);
            PhoneBook.forEach(System.out::println);
            System.out.println();
        });
        System.out.println("All contacts ↑\n");
    }

    public static void deleteContact(String name) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
        } else {
            System.out.println("Not found contact for delete");
        }
    }

    public static void deletePhone(String name, String phone) {
        if (phoneBook.containsKey(name)) {
            if (phoneBook.get(name).contains(phone)) {
                phoneBook.get(name).remove(phone);
            } else {
                System.out.println("Not found this number");
            }
        } else {
            System.out.println("User not found");
        }
    }

    public static void replaceNumber(String name, String oldPhone, String newPhone) {
        if (phoneBook.containsKey(name)) {
            if (phoneBook.get(name).contains(oldPhone)) {
                phoneBook.get(name).remove(oldPhone);
                phoneBook.get(name).add(newPhone);
            } else {
                System.out.println("Not found this number");
            }
        } else {
            System.out.println("User not found");
        }
    }

    public static void eraseBook(){
        phoneBook.clear();
        System.out.println("Phonebook erased");
    }

}
