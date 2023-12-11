import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> lines = readFile("C:\\Users\\chris\\Desktop\\aoc2023\\aoc2023\\src\\input.txt");
        int result = 0;
        for (String tmp: lines){
            System.out.println(tmp);
            result += getCallibrationVal(tmp);
            System.out.println(getCallibrationValWithLetters(tmp));

        }

        System.out.println(result);

    }
    public static int isNumber(String str) {
        switch (str) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            // Add more cases as needed
            default:
                return -1; // Default case for situations where the input doesn't match any of the specified cases
        }

    }

    public static int getCallibrationValWithLetters(String line){

        // if digit then take it
        // if not then check if number
        // if not number then move onto next idx
        int start_idx = 0;
        int end_idx = line.length()-1;
        char[] array = line.toCharArray();
        int result = 0;
        char left_val='x';
        char right_val='x';

        while (start_idx<=end_idx) {
            char starting_char = array[start_idx];
            char ending_char = array[end_idx];
            if (Character.isDigit(array[start_idx]) && Character.isDigit(array[end_idx])){
                // win
                String x = Character.toString(array[start_idx]) + Character.toString(array[end_idx]);
                return Integer.parseInt(x);
            }
            else if(Character.isDigit(array[start_idx])){
                //found first
                // move end
                left_val=array[start_idx];
                end_idx--;
            }
            else if (!Character.isDigit(starting_char)) {
                int digit = -1;

                for (int i=2; i<5; i++){
                    String maybeNumber = line.substring(start_idx,start_idx+i);
                    if (isNumber(maybeNumber) != -1) {
                        // return digit
                    }
                }

                if (digit == -1) {
                    // continue
                    start_idx++;
                }

//                String maybeNumber = array[start_idx]

                // try parse digit

            }

            else if (Character.isDigit(array[end_idx])) {
                // found last
                // move right
                right_val=array[end_idx];
                start_idx++;
            } else if (!Character.isDigit(ending_char)) {
                // try parse digit
                // resverse it
            } else {
                // move both
                start_idx++;
                end_idx--;

            }
        }
        if (right_val == 'x'){
            String combine = Character.toString(left_val);
            return Integer.parseInt(combine+combine);
        }
        else if (left_val == 'x') {
            String squish = Character.toString(right_val);
            return Integer.parseInt(squish+squish);
        }
//        return right_val*2 + left_val*2;
        return 0;
        }

//        return 0;
//    }

    public static int getCallibrationVal(String line){
        int start_idx = 0;
        int end_idx = line.length()-1;
        char[] array = line.toCharArray();
        int result = 0;
        char left_val='x';
        char right_val='x';

        while (start_idx<=end_idx){
            char starting_char = array[start_idx];
            char ending_char = array[end_idx];
            if (Character.isDigit(array[start_idx]) && Character.isDigit(array[end_idx])){
                // win
                String x = Character.toString(array[start_idx]) + Character.toString(array[end_idx]);
                return Integer.parseInt(x);
            }
            else if(Character.isDigit(array[start_idx])){
                //found first
                // move end
                left_val=array[start_idx];
                end_idx--;
            } else if (Character.isDigit(array[end_idx])) {
                // found last
                // move right
                right_val=array[end_idx];
                start_idx++;
            } else {
                // move both
                start_idx++;
                end_idx--;

            }
        }
        if (right_val == 'x'){
            String combine = Character.toString(left_val);
            return Integer.parseInt(combine+combine);
        }
        else if (left_val == 'x') {
            String squish = Character.toString(right_val);
            return Integer.parseInt(squish+squish);
        }
//        return right_val*2 + left_val*2;
        return 0;
    }


    public static List<String> readFile(String path){

        List<String> strings = new ArrayList<>();

       try {
            Scanner scanner = new Scanner(new File(path));

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            scanner.close();

//            return strings;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return strings;
    }
}