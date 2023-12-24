import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DayThree {

    public static char[][] schematic;
    public static int height;
    public static int width;

    public static char[][] readFileInto2dArray(String path){

        List<String> strings = DayOne.readFile(path);
        height = strings.size();
        width = strings.get(0).length();
//        this.height = height;

        char[][] result = new char[height][width];

        for (int i = 0; i < height; i++) {
            String line = strings.get(i);
            for (int j = 0; j < width; j++) {
                result[i][j] = line.charAt(j);


            }

        }


        return result;
    }

    public static void main(String[] args){

        schematic = readFileInto2dArray("C:\\Users\\chris\\Desktop\\aoc2023\\aoc2023\\src\\dayThreeInput.txt");

        for (int i = 0; i < height; i++) {
            int y = i;
            for (int j = 0; j < width; j++) {
                int startX = j;
                int endX = -1;
                if (Character.isDigit(schematic[i][j])){
                    endX = getEndX(startX, y);

                    // got part
                    boolean isValid = isValidPart(startX, endX, y);

                    j = endX+1;



                }

            }

        }








//        height = schematic.length;
//        width = schematic[0].length;

    }

    public static int getEndX(int startX, int y){
        int i = startX+1;
        int endX = startX;

        while (i< width){

            if (!Character.isDigit(schematic[y][i])){
                break;
            }

            if(Character.isDigit(schematic[y][i])){
               endX = i;
            }
            else{
                break;
            }

            i++;
        }
        return endX;

    }

    public static boolean isValidPart(int startX, int endX, int y){

        boolean left = lookLeft(startX, y);

        return false;




    }

    public static boolean lookLeft(int startX, int startY){

        // if edge
        if (startX == 0){
            return false;
        }
        // if period
        if (Objects.equals(schematic[startY][startX-1], '.')){
            return false;
        }

        return true;

    }

    public static boolean lookAbove(int partLength, int startX, int endX, int y){

//        int lengthOfPart = getPartLength();
        if (y == 0){
            return false;

        }
        else{
            for(int i=startX; i<endX; i++){
                if(!(schematic[y-1][i] =='.')){
                    return true;
                }
            }
        }

        return false;


    }

    public static boolean lookBelow(int startX, int endX, int y){
        if (y == height-1){
            return false;

        }
        else{
            for(int i=startX; i<endX; i++){
                if(schematic[y+1][i]!='.'){
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean lookRight(int endX, int endY){

        // if edge
        if (endX == width-1){
            return false;
        }
        // if period
        if (Objects.equals(schematic[endY][endX+1], ".")){
            return false;
        }

        return true;

    }

    public static boolean lookDiagonal(int startIdx, int endIdx){
        return false;

    }

    public static int sumOfParts(String[][] schematic){
        return 0;

    }
}
