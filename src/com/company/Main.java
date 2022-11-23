package com.company;

import com.company.Interface.ConsoleColor;
import com.company.Interface.Menu;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Головний клас програми
 */
public class Main {
    /**
     * Точка входу в програму
     *
     * @param args аргументи що надійшли з командного рядка
     * @throws IOException        виняток
     * @throws URISyntaxException виняток
     */
    public static void main(String[] args) throws IOException, URISyntaxException {


        System.out.print(ConsoleColor.RED_BOLD_BRIGHT);
        System.out.println("""
                -----------------------
                ----Бібліотека книг----
                -----------------------""");


        //Виклик статичного методу mainMenu() класу Menu
        Menu.mainMenu();
    }
}
