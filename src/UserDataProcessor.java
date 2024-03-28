import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserDataProcessor {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол: \n");
            String userInput = scanner.nextLine();

            String[] userData = userInput.split(" ");
            if (userData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String surname = userData[0];
            String name = userData[1];
            String patronymic = userData[2];
            String birthDate = userData[3];
            long phoneNumber = Long.parseLong(userData[4]);
            char gender = userData[5].charAt(0);

            if (!isValidDateFormat(birthDate)) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }

            String fileName = surname + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isValidDateFormat(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
