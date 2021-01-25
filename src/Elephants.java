import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class Elephants {

    private static int[] currentElephantSetting;
    private static int[] weightOfElephants;
    private static int[] suggestedElephantSetting;
    private static boolean[] loop;
    private static long sumElephantsWeight = 0;
    private static int minElephantWeight;
    private static long result=0;

    static List<String> list = new ArrayList<>();

    public static void main(String args[]) {
        String fileName = args[0];

        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream<String> lines = br.lines();
        list = br.lines().collect(Collectors.toList());

        int numberOfElephants = Integer.parseInt(list.get(0));
        currentElephantSetting = new int[numberOfElephants];
        weightOfElephants = new int[numberOfElephants];
        suggestedElephantSetting = new int[numberOfElephants];
        loop  = new boolean[numberOfElephants];

        matcherMethod(currentElephantSetting,2);
        matcherMethod(weightOfElephants,1);
        matcherMethod(suggestedElephantSetting,3);

        for(int i = 0; i < numberOfElephants; ++i)
        {
            if(!loop[i])
            {
                sumElephantsWeight = 0;
                minElephantWeight = weightOfElephants[0];
                int currentElephant = i;
                int count = 0;
                do
                {
                    minElephantWeight = min(minElephantWeight, weightOfElephants[currentElephant]);
                    sumElephantsWeight += weightOfElephants[currentElephant];
                    currentElephant = suggestedElephantSetting[currentElephant];
                    currentElephant--;
                    loop [currentElephant] = true;
                    count++;
                } while(currentElephant != i);
                result += min(sumElephantsWeight + (long) (count - 2) * minElephantWeight, sumElephantsWeight + minElephantWeight + (long) (count + 1) * minElephantWeight );
            }
        }
        System.out.println(result);

    }

    private static void matcherMethod(int[] array,int line) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(list.get(line));
        int i=0;
        while (m.find()) {
            array[i] = Integer.parseInt(m.group());
            i++;
        }
    }
}
