package ru.bstu.it31.romashenko.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import org.apache.logging.log4j.*;

/** @author Ромащенко Н.А.
 *
 * @version 1. 21.02.19
 *
 * Имя класса: Calendar
 *
 * Назначение: нахождение количества дней в месяце
 */

public class Calendar {
    static final Logger Logger = LogManager.getLogger(Calendar.class);

    private int year;
    private int mounth;
    private int days;

    public int getMounthInt() {
        return mounth;
    }

    public String getMounthString() {
        String[] sMounth = {
                "Январь",
                "Февраль",
                "Март",
                "Апрель",
                "Май",
                "Июнь",
                "Июль",
                "Август",
                "Сентябрь",
                "Октябрь",
                "Ноябрь",
                "Декабрь"
        };
        return sMounth[this.mounth - 1];
    }

    public int getYear() {
        return year;
    }

    public int getDays() {
        return days;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Calendar(int year, int mounth) {
        this.year = year;
        this.mounth = mounth;
        setCountDaysOfMounth();
    }

    public Calendar() {
        this.year = -1;
        this.mounth = -1;
        this.days = -1;
    }

    private boolean check() {
        if (0 > this.year || 0 > this.mounth || 12 < this.mounth) {
            Logger.warn("Некорректная дата.");
            return false;
        }
        Logger.info("Корректная дата.");
        return true;
    }

    public boolean setCountDaysOfMounth() {
        // Проверка правильность даты
        if (!check()) {
            // Обработка ошибки
            return false;
        }
        // Если все ок, то работает
        switch (mounth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                this.days = 31;
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                this.days = 30;
                break;
            }
            case 2: {
                if (0 != year % 4) {
                    this.days = 28;
                } else if (0 != year % 100) {
                    this.days = 29;
                } else if (0 != year % 400) {
                    this.days = 28;
                } else {
                    this.days = 29;
                }
                break;
            }
            default: {
                this.days = -1;
                Logger.fatal("Некорректно пердался месяц.");
                return false;
            }
        }
        Logger.info("Дни были найдемы.");
        return true;
    }

    // Считываение года и месяца из файла
    public void getValueFromFile() {
        Logger.info("Начало чтения из файла.");
        try (FileReader reader = new FileReader("ex2.txt")) {
            BufferedReader r = new BufferedReader(reader);
            for (int i = 0; i < 2; ++i) {
                String sTemp = r.readLine();
                int iTemp = Integer.parseInt(sTemp);
                if (999 < iTemp && iTemp < 10000) {
                    this.year = iTemp;
                } else if (0 < iTemp && iTemp < 13) {
                    this.mounth = iTemp;
                } else {
                    System.out.println("Некорректные данные в файле.");
                }
            }
        } catch (IOException ex) {
            Logger.error("Файл не найден. ", ex);
            System.out.println(ex.getMessage());
        }
    }

    // Считываение данных из файла
    public void printValueInFile() {
        Logger.info("Начало записи в файл.");
        try (FileWriter writer = new FileWriter("ex2_otvet.txt", false)) {
            String text = ""
                    + "year = " + this.year + '\n'
                    + "mounth = " + this.mounth + '\n'
                    + "days = " + this.days + '\n';
            writer.write(text);
            Logger.info("В файл записали.");
        } catch (IOException ex) {
            Logger.error("В файл не записали. ", ex);
            System.out.println(ex.getMessage());
        }
    }
}