import java.util.*;

public class Day12 {
    public static void main(String[] args) {
        List<Map.Entry<Character, Integer>> inputs = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            inputs.add(new AbstractMap.SimpleImmutableEntry<>(
                    line.charAt(0), Integer.parseInt(line.substring(1))));
        }
        s.close();

        // Part 1.
        char orientation = 'E';
        char facing = 'E';
        int x = 0, y = 0;
        for (Map.Entry<Character, Integer> input : inputs) {
            // Update direction.
            char operation = input.getKey();
            int val = input.getValue();
            switch (operation) {
                case 'N':
                case 'S':
                case 'E':
                case 'W':
                    orientation = operation;
                    break;
                case 'L':
                    final List<Character> leftRound = Arrays.asList('E', 'N', 'W', 'S');
//                    System.out.println("left searching: " + orientation);
                    facing = leftRound.get((leftRound.indexOf(facing) + val / 90) % 4);
                    break;
                case 'R':
                    // JDK 11 bug, search result is -2.
                    // final char[] rightRound2 = new char[] {'E', 'S', 'W', 'N'};
                    // System.out.println("result array: " + Arrays.binarySearch(rightRound2, orientation));
                    final List<Character> rightRound = Arrays.asList('E', 'S', 'W', 'N');
                    facing = rightRound.get((rightRound.indexOf(facing) + val / 90) % 4);
                    break;
            }

            // Not move.
            if (operation == 'L' || operation == 'R') {
                continue;
            }

            // Move.
            switch (operation == 'F' ? facing : orientation) {
                case 'N':
                    x += val;
                    break;
                case 'S':
                    x -= val;
                    break;
                case 'E':
                    y += val;
                    break;
                case 'W':
                    y -= val;
                    break;
            }
            System.out.format("[%c] x: %d, y: %d\n", orientation, x, y);
        }
        System.out.println("part 1: " + (Math.abs(x) + Math.abs(y)));
    }
}
