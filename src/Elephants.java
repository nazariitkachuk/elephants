import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    private static boolean[] cykl;
    private static long result = 0;
    private static int minElephantWeight;
    static List<String> list = new ArrayList<>();

    public static void main(String args[]) {
        String fileName = "D:\\IntelliJ_Projects\\Elephant\\zadanie_B\\slo3.in";

        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream<String> lines = br.lines();
        list = br.lines().collect(Collectors.toList());

//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));


        int numberOfElephants = Integer.parseInt(list.get(0));
        currentElephantSetting = new int[numberOfElephants];
        weightOfElephants = new int[numberOfElephants];
        suggestedElephantSetting = new int[numberOfElephants];
        cykl  = new boolean[numberOfElephants];

        mutcherMethod(currentElephantSetting,2);
        mutcherMethod(weightOfElephants,1);
        mutcherMethod(suggestedElephantSetting,3);

        minElephantWeight = weightOfElephants[0];

        for(int i=0;i < weightOfElephants.length;i++)
        {
            minElephantWeight=min(minElephantWeight,weightOfElephants[i]);
        }

        long wynik = 0;
        for(int i = 0; i < numberOfElephants; ++i)
        {

            if(!cykl[i])
            {
                result = 0;
                int teraz = i;
                int miniteraz = 1 << 30;
                int ile = 0;
                do
                {
                    miniteraz = min(miniteraz, weightOfElephants[teraz]);
                    result += weightOfElephants[teraz];
                    teraz = suggestedElephantSetting[teraz];
                    teraz--;
                    cykl[teraz] = true;
                    ++ile;
                } while(teraz != i);
                wynik += min(result + (ile - 2) * miniteraz, result + miniteraz + (ile + 1) * minElephantWeight );
            }
        }

        System.out.println(wynik);


    }

    private static void mutcherMethod(int[] array,int line) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(list.get(line));
        int i=0;
        while (m.find()) {
            array[i] = Integer.parseInt(m.group());
            i++;
        }
    }
}
