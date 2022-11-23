package com.company.dao;

import com.company.Interface.ConsoleColor;
import com.company.bl.Filter;

import java.io.*;
import java.util.ArrayList;

/**
 * Клас книг
 */
public class Book extends ReaderFile {

    /**
     * Список книг
     */
    private static ArrayList<File> listOfBooks = new ArrayList<>();

    /**
     * Шлях до файлів з книгами
     */
    private static final String PATH_TO_BookS = new File("Data\\Books").getAbsolutePath();

    /**
     * Повертає список книг
     *
     * @return повертає список книг
     */
    public static ArrayList<File> getListOfBooks() {
        makeListOfBooks();
        return listOfBooks;
    }

    /**
     * Отримує список файлів, після їхньої фільтрації
     *
     * @param filterFiles відфільтровані файли
     */
    public static void setListOfBooks(ArrayList<File> filterFiles) {
        listOfBooks.clear();

        for (File file : filterFiles) {
            listOfBooks.add(file);
        }
    }

    /**
     * Виводить на екран список книг
     *
     * @return повертає true якщо вдалося вивести файли, якщо ні то false
     * @throws IOException виняток
     */
    public static boolean showListOfBooks() throws IOException {
        System.out.print(ConsoleColor.CYAN_BOLD_BRIGHT);
        System.out.println("Список книг:");

        boolean isBooks = makeListOfBooks();

        int i = 0;

        if (isBooks) {
            for (File Book : listOfBooks) {
                System.out.print(ConsoleColor.GREEN_BOLD_BRIGHT);
                System.out.println(i + " - " + Book.getName().substring(3, (Book.getName().length() - 4)));

                i++;
            }
            return true; //Є файли
        }
        return false;//Файлів немає
    }

    /**
     * Заповнює список книг файлами
     *
     * @return повертає true якщо вдалося знайти файли, якщо ні то false
     */
    public static boolean makeListOfBooks() {
        File file = new File(PATH_TO_BookS);

        if (Filter.getIsFilten() == false) {
            listOfBooks.clear();

            if (file.listFiles() != null) {
                for (File s : file.listFiles()) {
                    if (s.isFile()) {
                        listOfBooks.add(s);
                    }
                }
                return true;
            } else {
                System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                System.out.println("\nНе вдалося знайти файли з книгами\n");
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * Визначай файл, який вибрав користувач
     *
     * @return повертає вибраний файл
     * @throws IOException виняток
     */
    public static File selectedFile() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int index = 0;

        do {
            System.out.print(ConsoleColor.PURPLE_BOLD_BRIGHT);
            System.out.print("Виберіть книгу: ");
            try {
                index = Integer.parseInt(input.readLine());
                System.out.println("");
            } catch (Exception ex) {
                System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                System.out.println("Помилка! Не правильно введена команда. Спробуйте ще раз.\n");
                continue;
            }

            if (index < listOfBooks.size() && index >= 0) {
                break;
            } else {
                System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
                System.out.println("Помилка! Не правильно введене число. Спробуйте ще раз.\n");
                continue;
            }
        } while (true);

        return listOfBooks.get(index);
    }
}
