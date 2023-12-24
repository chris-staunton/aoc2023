import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> lines = readFile("C:\\Users\\chris\\Desktop\\aoc2023\\aoc2023\\src\\input.txt");
        int result = 0;
        for (String tmp: lines){
            System.out.println(tmp);
            result += getCallibrationValWithLetters(tmp);
            System.out.println(getCallibrationValWithLetters(tmp));

        }

        System.out.println(result);

    }

    public static int isDigit(int startIdx, String line) {

        for (int i=2; i<=5; i++){
            if (startIdx+i>line.length()-1){
                return -1;
            }
            String maybeNumber = line.substring(startIdx,startIdx+i);
            if (isNumber(maybeNumber) != -1) {
                // return digit
                return isNumber(maybeNumber);
            }
        }

        return -1;


    }

    public static int isReverseDigit(int endIdx, String line) {

        for (int i=endIdx-2; i>=endIdx-4; i--){
            if (i<0){
                return -1;
            }
            String maybeNumber = line.substring(i,endIdx+1);
            if (isNumber(maybeNumber) != -1) {
                // return digit
                return isNumber(maybeNumber);
            }
        }

        return -1;


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
        boolean leftFound = false;
        boolean rightFound = false;

        while (start_idx<=end_idx) {
            if (leftFound && rightFound){
                break;
            }
            char starting_char = array[start_idx];
            char ending_char = array[end_idx];
            if ((Character.isDigit(array[start_idx]) && Character.isDigit(array[end_idx]))){
                // win
                String x = Character.toString(array[start_idx]) + Character.toString(array[end_idx]);
                return Integer.parseInt(x);
            }
            else if(Character.isDigit(array[start_idx]) && !leftFound){
                //found first
                // move end
                left_val=array[start_idx];
                leftFound=true;
//                end_idx--;
            }
            else if (!Character.isDigit(starting_char) && !leftFound) {
//                int digit = -1;
//
//                for (int i=2; i<5; i++){
//                    String maybeNumber = line.substring(start_idx,start_idx+i);
//                    if (isNumber(maybeNumber) != -1) {
//                        // return digit
//                    }
//                }
                int tmp = isDigit(start_idx, line);
                if (tmp == -1) {
                    // continue
                    start_idx++;
                }
                else{
                    // found left number
                    left_val= (char)(tmp+'0');
                    leftFound=true;
                }

//                String maybeNumber = array[start_idx]

                // try parse digit

            }

            else if (Character.isDigit(array[end_idx]) && !rightFound) {
                // found last
                // move right
                right_val=array[end_idx];
                rightFound = true;
//                start_idx++;
            } else if (!Character.isDigit(ending_char) && !rightFound) {

                int helper = isReverseDigit(end_idx, line);

                if(helper == -1){
                    end_idx--;
                }
                else{
                    right_val = (char)(helper+'0');
                    rightFound=true;
                    start_idx++;
                }


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
        else {
            String leftString = Character.toString(left_val);
            String rightString = Character.toString(right_val);

            return Integer.parseInt(leftString+rightString);
        }
//        return right_val*2 + left_val*2;
//        return 0;
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