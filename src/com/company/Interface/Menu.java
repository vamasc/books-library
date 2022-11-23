package com.company.Interface;

import com.company.bl.Search;
import com.company.dao.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * Даний клас реалізує меню
 */
public class Menu {

    /**
     * Даний метод реалізує головне меню користувача
     *
     * @throws IOException        виняток
     * @throws URISyntaxException виняток
     */
    public static void mainMenu() throws IOException, URISyntaxException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int command = 0;

        do {
            System.out.print(ConsoleColor.CYAN_BOLD_BRIGHT);
            System.out.println("- - - - -МЕНЮ- - - - -");
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT);
            System.out.print("1 - Список книг\n" +
                    "2 - Загальна інформація про Бібліотек книг\n" +
                    "3 - Пошук\n" +
                    "4 - Режим автора\n" +
                    "5 - Довідка\n" +
                    "0 - Вийти з програми\n" + ConsoleColor.PURPLE_BOLD_BRIGHT +
                    "Команда: ");

            try {
                command = Integer.parseInt(input.readLine());
                System.out.println("");
            } catch (Exception ex) {
                System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                System.out.println("\nПомилка! Не правильно введена команда. Спробуйте ще раз.\n");
                continue;
            }

            switch (command) {
                case 1 -> {
                    //Якщо є файли
                    if (Book.showListOfBooks()) {
                        ReaderFile fileReader = new Book();
                        fileReader.readFile(Book.selectedFile());
                    }

                    backToMenu();
                    continue;
                }
                case 2 -> {
                    //Якщо є файли
                    if (GeneralInformation.showListOfGeneralInformation()) {
                        ReaderFile fileReader = new GeneralInformation();
                        fileReader.readFile(GeneralInformation.selectedFile());
                    }

                    backToMenu();
                    continue;
                }
                case 3 -> {
                    /*
                     * Перевірка на те, чи є файли у одному з списків.
                     * Якщо списки пусті то методи повертають false
                     */
                    if (Book.makeListOfBooks() | GeneralInformation.makeListOfGeneralInformation()) {
                        Search.find();
                    }

                    backToMenu();
                    continue;
                }
                case 4 -> {
                    Book.makeListOfBooks();
                    GeneralInformation.makeListOfGeneralInformation();

                    AuthorMode.userSelection();

                    backToMenu();
                    continue;
                }
                case 5 -> {
                    ReaderFile fileReader = new Certificate();
                    fileReader.readFile(Certificate.getFile());

                    backToMenu();
                    continue;
                }
                case 0 -> {
                    System.out.print(ConsoleColor.GREEN_BOLD_BRIGHT);
                    System.out.println("Допобачення");
                    System.exit(0);
                }
                default -> {
                    System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                    System.out.println("Помилка! Не правильно введена команда. Спробуйте ще раз.\n");
                }
            }
        } while (true);
    }

    /**
     * Метод реалізує повернення користувача назад до меню, або вихід з програми
     *
     * @throws IOException виняток
     */
    public static void backToMenu() throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        do {
            int command;
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT);
            System.out.print("1 - Повернутися в меню\n" +
                    "0 - Вийти з програми\n" + ConsoleColor.PURPLE_BOLD_BRIGHT +
                    "Команда: ");
            try {
                command = Integer.parseInt(input.readLine());
            } catch (Exception ex) {
                System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                System.out.println("\nПомилка! Не правильно введена команда. Спробуйте ще раз.\n");
                continue;
            }

            switch (command) {
                case 1 -> {
                    System.out.println("");
                    break;
                }
                case 0 -> {
                    System.out.print(ConsoleColor.GREEN_BOLD_BRIGHT);
                    System.out.println("\nДопобачення");
                    System.exit(0);
                }
                default -> {
                    System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                    System.out.println("Помилка! Не правильно введена команда. Спробуйте ще раз.\n");
                    continue;
                }
            }

            break;
        } while (true);
    }
}
