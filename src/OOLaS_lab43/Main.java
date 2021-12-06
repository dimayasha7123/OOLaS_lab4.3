package OOLaS_lab43;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        System.out.println("List");
        System.out.println("На основе данных входного файла составить список студентов группы, включив следующие данные: ФИО, номер группы, результаты сдачи трех экзаменов. Вывести в новый файл информацию о студентах, успешно сдавших сессию, отсортировав по номеру группы.");
        System.out.println("==============================================================================================");
        Student.generateToFile(50, "input.txt");

        try (FileWriter fw = new FileWriter("output.txt", false)) {

            Files.lines(Path.of("input.txt"))
                    .map(Student::parse)
                    .filter(Student::success)
                    .sorted(Comparator.comparing(Student::getGroup))
                    .forEach((x) -> {
                        try {
                            fw.write(x + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("См. файл");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}