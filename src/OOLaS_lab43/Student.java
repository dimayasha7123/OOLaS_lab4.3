/**
 * |_|>
 * |_|>    Created by Dimyasha on 06.12.2021
 * |_|>
 */

package OOLaS_lab43;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Student {
    private final String surename;
    private final String name;
    private final String patronymic;
    private final String group;
    private final int[] scores;

    public Student(String surename, String name, String patronymic, String group, int[] scores) {
        this.surename = surename;
        this.name = name;
        this.patronymic = patronymic;
        this.group = group;
        this.scores = scores;
    }

    public String getGroup() {
        return group;
    }

    public boolean success() {
        return Arrays.stream(scores).allMatch((x) -> x > 2);
    }

    @Override
    public String toString() {
        final String[] output = {surename + " " + name + " " + patronymic + " " + group};
        Arrays.stream(scores)
                .mapToObj((x) -> ((Integer) x).toString())
                .forEach((x) -> output[0] += " " + x);
        return output[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return surename.equals(student.surename) && name.equals(student.name) && patronymic.equals(student.patronymic) && group.equals(student.group) && Arrays.equals(scores, student.scores);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(surename, name, patronymic, group);
        result = 31 * result + Arrays.hashCode(scores);
        return result;
    }


    public static Student parse(String input) {
        String[] arr = input.split("\s");
        return new Student(arr[0], arr[1], arr[2], arr[3], Arrays.stream(arr).skip(4).mapToInt(Integer::parseInt).toArray());
    }

    public static void generateToFile(int n, String path) {
        String[] surenameSet = new String[]{"Иванов", "Смирнов", "Кузнецов", "Попов", "Соколов", "Васильев", "Петров", "Михайлов", "Новиков"};
        String[] nameSet = new String[]{"Иван", "Федор", "Дмитрий", "Евгений", "Михаил", "Денис", "Никита", "Юрий", "Артур", "Валентин"};
        String[] patronymicSet = {"Иванович", "Федорович", "Дмитриевич", "Евгеньевич", "Михайлович", "Денисович", "Никитич", "Юрьевич", "Артурович", "Валентинович"};
        String[] groupSet = {"ФИИТ", "МКН", "ИВТ", "БИ"};

        try {
            FileWriter fw = new FileWriter(path, false);
            Random r = new Random();
            for (int i = 0; i < n; ++i) {
                fw.write(new Student(surenameSet[r.nextInt(surenameSet.length)],
                        nameSet[r.nextInt(nameSet.length)],
                        patronymicSet[r.nextInt(patronymicSet.length)],
                        groupSet[r.nextInt(groupSet.length)],
                        new int[]{r.nextInt(4) + 2, r.nextInt(4) + 2, r.nextInt(4) + 2}) + "\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}