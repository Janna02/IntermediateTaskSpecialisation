package Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AppToys {
    public static void main(String[] args) {

        new StartOfGame();

    }
}

class StartOfGame {
    public StartOfGame() {
        GetAllTrophyes magazineOfToys = new GetAllTrophyes();

        Map<Toys, Integer> trophyes = new HashMap<>();

        Toys toy1 = new Toys("Заяц", 20, 30);
        Toys toy2 = new Toys("Пчела", 50, 40);
        Toys toy3 = new Toys("Волк", 30, 20);

        ArrayList<Toys> toysArray = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Введите число игрушек:(от 1 до 99 включительно)");
        int count = in.nextInt();

        toysArray.add(toy1);
        toysArray.add(toy2);
        toysArray.add(toy3);

        System.out.println(toysArray);
        ToysLottery tl = new ToysLottery();
        Toys prize = tl.getPrizeToy(toysArray);

        while (count > 0) {
            toysArray = tl.removeTrophyToy(toysArray, prize);
            magazineOfToys.savingTrophyes(prize, trophyes);
            prize = tl.getPrizeToy(toysArray);
            count--;
        }
        System.out.println("Все призы: ");
        System.out.println(magazineOfToys.getTrophyMap(trophyes));
        System.out.println(toysArray);
        in.close();
    }
}

class GetAllTrophyes {
    public GetAllTrophyes() {
    }

    public Map<Toys, Integer> savingTrophyes(Toys prizeToy, Map<Toys, Integer> trophyesMap) {
        if (trophyesMap.containsKey(prizeToy)) {
            trophyesMap.put(prizeToy, trophyesMap.get(prizeToy) + 1);
        } else {
            trophyesMap.put(prizeToy, 1);
        }
        return trophyesMap;
    }

    public String getTrophyMap(Map<Toys, Integer> trophyesMap) {
        StringBuilder sb = new StringBuilder();
        for (Toys t : trophyesMap.keySet()) {
            sb.append(t.getName());
            sb.append(" - " + trophyesMap.get(t) + "\n");
        }
        try {
            Path file = Path.of("Trophyes.txt");
            Files.writeString(file, sb);
        } catch (IOException e) {
            System.out.println("Неудачно");
        }

        return sb.toString();
    }
}

class Toys {
    private int id = 0;
    private static int uniq = 0;
    private String name;
    private int count;
    private int probability;

    public Toys(String name, int count, int probability) {
        this.name = name;
        this.count = count;
        this.probability = probability;
        this.id = getUniqId();
    }

    private int getUniqId() {
        return this.uniq++;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + "Игрушка: " + name + ", остаток " + count + " шт.";
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    public int editCount(int count) {
        this.count = count - 1;
        return this.count;
    }
}

class ToysLottery {
    public ToysLottery() {
    }

    Random rand = new Random();

    public int chanceNumber(int probability) {
        return rand.nextInt(probability);
    }

    public Toys getPrizeToy(ArrayList<Toys> array) {
        int maxChance = chanceNumber(array.get(0).getProbability());
        Toys trophyToy = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            int curChance = chanceNumber(array.get(i).getProbability());
            if (maxChance < curChance) {
                maxChance = curChance;
                trophyToy = array.get(i);
            }
        }
        return trophyToy;
    }

    public ArrayList<Toys> removeTrophyToy(ArrayList<Toys> array, Toys trophyToy) {
        for (int i = 0; i < array.size(); i++) {
            if (trophyToy.getId() == array.get(i).getId()) {
                trophyToy.editCount(trophyToy.getCount());
                array.remove(i);
                array.add(i, trophyToy);
                if (array.get(i).getCount() == 0) {
                    array.remove(array.get(i));
                }
            }
        }
        return array;
    }

}