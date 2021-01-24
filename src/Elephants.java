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

public class Elephants {

    private static int[] currentElephantSetting;
    private static int[] weightOfElephants;
    private static int[] suggestedElephantSetting;
    private static int result = 0;
    static List<String> list = new ArrayList<>();

    public static void main(String args[]) {
        String fileName = "D:\\IntelliJ_Projects\\Elephant\\zadanie_B\\slo1.in";

        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream<String> lines = br.lines();
        list = br.lines().collect(Collectors.toList());

        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));


        int numberOfElephants = Integer.parseInt(list.get(0));
        currentElephantSetting = new int[numberOfElephants];
        weightOfElephants = new int[numberOfElephants];
        suggestedElephantSetting = new int[numberOfElephants];

        mutcherMethod(currentElephantSetting,1);
        mutcherMethod(weightOfElephants,2);
        mutcherMethod(suggestedElephantSetting,3);

//        for(int i=0;i<currentElephantSetting.length;i++){
//            System.out.println(currentElephantSetting[i]);
//        }
//        for(int i=0;i<weightOfElephants.length;i++){
//            System.out.println(weightOfElephants[i]);
//        }
//        for(int i=0;i<suggestedElephantSetting.length;i++){
//            System.out.println(suggestedElephantSetting[i]);
//        }

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
