package ru.bstu.it31.romashenko.lab2;

import java.util.Scanner;
import org.apache.logging.log4j.*;

/**
 * <p>Главный класс программы.</p>
 *
 * @author Ромащенко Н.А.
 * @version 1.0
 * Дата: 21.02.19
 *
 * Класс: Main
 * Описание: Составить программу, которая по заданным году и номеру месяца т определяет количество дней в этом месяце.
 */
public class Main {
    /** */
    static final Logger Logger = LogManager.getLogger(Main.class);

    /**
     * <p>Точка входа в программу</p>
     *
     * @param args - аргументы для метода
     */
    public static void main(String[] args) {
        Logger.debug("Lets");

        System.out.println("Определение количества дней в определенному месяце определенного года.");
        System.out.println("\t> 1. Ввести в клавиатуры;");
        System.out.println("\t> 2. Считать из файла;");
        System.out.println("\t> 9. Выход.");
        // Режим ввода данных
        // 1 - клавиатура
        // 2 - файл
        // 9 - выход

        // Инициализация объекта "Сканер"
        Scanner scanner = new Scanner(System.in);

        int mode = scanner.nextInt();

        Calendar calendar = new Calendar();
        // Обработка режима работы

        switch (mode) {
            // Клавиатура
            case 1: {
                Logger.info("Пользователь выбрал режим работы 'чтение с консоли'");
                // Ввод года
                System.out.print("Год: ");
                calendar.setYear(scanner.nextInt());
                Logger.debug("Пользователь ввел = " + calendar.getYear());

                // Ввод месяца
                System.out.print("Месяц: ");
                calendar.setMounth(scanner.nextInt());
                Logger.debug("Пользователь ввел = " + calendar.getMounthInt());
                break;
            }

            // Файл
            case 2: {
                Logger.info("Пользователь выбрал режим работы 'чтение из файла'");

                // Функция для считывания из файла
                calendar.getValueFromFile();
                break;
            }

            // Выход
            case 9: {
                Logger.info("Пользователь выбрал режим работы 'выход'");
                return;
            }

            // Ошибка ввода
            default: {
                Logger.warn("Выбран не верный параметр, программа завершила свою работу.");

                System.out.println("Неправильный ввод месяца.");
                return;
            }
        }
        if (!calendar.setCountDaysOfMounth()) {
            Logger.error("Ошибка в дате.");

            System.out.println("Ошибка в дате.");
            return;
        }
        Logger.info("Удача в вычислении.");

        System.out.println("Количество дней в месяце " + calendar.getMounthString().toLowerCase() + ": " + calendar.getDays() + ".");

        calendar.printValueInFile();

        Logger.debug("The end.");
    }
}