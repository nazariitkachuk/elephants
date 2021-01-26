import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class Elephants {
    private static final int WEIGHT = 1;
    private static final int CURRENT_SETTING = 2;
    private static final int SUGGESTED_SETTING = 3;
    private static String WHITESPACE_REGEX = "\\s+";
    private static String NUM_REGEX = "-?\\d+";
    private static final Predicate<String> NUMBER_MATCH = e -> e.matches(NUM_REGEX);

    private int[] currentElephantSetting;
    private int[] weightOfElephants;
    private int[] suggestedElephantSetting;
    private boolean[] loop;
    private long sumElephantsWeight = 0;
    private int minElephantWeight;
    private long result = 0;

    public static void main(String args[]) throws IOException {
        String fileName = args[0];
        new Elephants().findBestSetting(fileName);

    }

    public void findBestSetting(String fileName) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(fileName));



        List<String[]> dataSets = lines
                .map(e -> e.split(WHITESPACE_REGEX))
                .collect(Collectors.toList());
        int numberOfElephants= Arrays.stream(dataSets.get(0)).filter(NUMBER_MATCH)
                .mapToInt(Integer::parseInt).findFirst().orElseThrow();

        loop = new boolean[numberOfElephants];

        weightOfElephants = Arrays.stream(dataSets.get(WEIGHT)).filter(NUMBER_MATCH)
                .mapToInt(Integer::parseInt)
                .toArray();

        currentElephantSetting = Arrays.stream(dataSets.get(CURRENT_SETTING)).filter(NUMBER_MATCH)
                .mapToInt(Integer::parseInt)
                .toArray();

        suggestedElephantSetting = Arrays.stream(dataSets.get(SUGGESTED_SETTING)).filter(NUMBER_MATCH)
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < numberOfElephants; ++i) {
            if (!loop[i]) {
                sumElephantsWeight = 0;
                minElephantWeight = weightOfElephants[0];
                int currentElephant = i;
                int count = 0;
                do {
                    minElephantWeight = min(minElephantWeight, weightOfElephants[currentElephant]);
                    sumElephantsWeight += weightOfElephants[currentElephant];
                    currentElephant = suggestedElephantSetting[currentElephant];
                    currentElephant--;
                    loop[currentElephant] = true;
                    count++;
                } while (currentElephant != i);
                result += min(sumElephantsWeight + (long) (count - 2) * minElephantWeight, sumElephantsWeight + minElephantWeight + (long) (count + 1) * minElephantWeight);
            }
        }
        System.out.println(result);
    }


}
