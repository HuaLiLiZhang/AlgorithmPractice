package leetcode.competition;

import java.util.*;

/**
 * @author: Created by zhanghl
 */
public class _57DoubleCompetion {
    public static void main(String[] args) {

//        System.out.println(new _57DoubleCompetion().areOccurrencesEqual("aaabb"));
//        System.out.println(new _57DoubleCompetion().areOccurrencesEqual("abacbc"));

//        int[][] times = {{3, 10}, {1, 5}, {2, 6}};
//        System.out.println(new _57DoubleCompetion().smallestChair(times, 0));
//        int[][] times1 = {{1, 4}, {2, 3}, {4, 6}};
//        System.out.println(new _57DoubleCompetion().smallestChair(times1, 1));
        int[][] times2 = {{33889, 98676}, {80071, 89737}, {44118, 52565}, {52992, 84310}, {78492, 88209}, {21695, 67063}, {84622, 95452}, {98048, 98856}, {98411, 99433}, {55333, 56548}, {65375, 88566}, {55011, 62821}, {48548, 48656}, {87396, 94825}, {55273, 81868}, {75629, 91467}};
        int[][] times3 = {{82057, 89365}, {32519, 49655}, {7573, 20592}, {8336, 11514}, {638, 70162}, {39511, 44262}, {70399,
                79785}, {8702, 63564}, {66644, 68330}, {75156, 90448}, {11884, 87096}, {99068, 99875}, {32555, 54053}, {5910, 77572}, {87649, 94390}, {40084, 56483}, {7911, 28699}, {93308, 96154}, {25562, 39605}, {73966, 93173}, {63450, 88007}, {58811, 80045}, {56160, 71952}, {14333, 79867}, {40342, 76876}, {69943, 82175}};
//        System.out.println(new _57DoubleCompetion().smallestChair(times2, 6));
        System.out.println(new _57DoubleCompetion().smallestChair(times3, 5));


    }

    public boolean areOccurrencesEqual(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        Map<Character, Integer> mapCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            mapCount.put(c, mapCount.getOrDefault(c, 1) + 1);
        }
        if (mapCount.size() == 1) {
            return true;
        }
        int num = 0;
        int startCount = 0;
        for (char key : mapCount.keySet()) {
            if (num == 0) {
                startCount = mapCount.get(key);
                num++;
                continue;
            }
            if (!mapCount.get(key).equals(startCount)) {
                return false;
            }
            num++;
        }
        return true;
    }


    public int smallestChair(int[][] times, int targetFriend) {
        if (times == null || times.length <= 1) {
            return 0;
        }
        ArrayList<people> arrayList = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            arrayList.add(new people(times[i][0], times[i][1]));
        }
        Collections.sort(arrayList, (a, b) -> {
            if (a.startTime != b.endTime) {
                return a.startTime - b.startTime;
            } else {
                return a.endTime - b.endTime;
            }
        });
        arrayList.stream().forEach(p -> {
            System.out.print("{" + p.startTime + ", " + p.endTime + "}, ");
        });
        int targetStartTime = times[targetFriend][0];
        int targetEndTime = times[targetFriend][1];
        int[] isSeat = new int[times.length];
        Arrays.fill(isSeat, -1);
        ArrayList<Integer> seatNum = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            people p = arrayList.get(i);
            for (int j = 0; j < isSeat.length; j++) {
                if (isSeat[j] == -1) {
                    isSeat[j] = i;
                    seatNum.add(j);
                    break;
                } else {
                    people temp = arrayList.get(isSeat[j]);
                    if (p.startTime >= temp.endTime) {
                        isSeat[j] = i;
                        seatNum.add(j);
                        break;
                    }
                }
            }
            if (p.startTime == targetStartTime && p.endTime == targetEndTime) {
                return seatNum.get(i);
            }
        }
        return 0;
    }

    class people {
        int startTime;
        int endTime;
        int zhanjuNum;

        people(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}


