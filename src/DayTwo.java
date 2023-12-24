import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwo {

    public static Map<String, Integer> limits;
    public static void main(String[] args) {
        System.out.println("Hello world!");

        limits = new HashMap<>();

        limits.put("red", 12);
        limits.put("green", 13);
        limits.put("blue", 14);

        List<String> lines = DayOne.readFile("C:\\Users\\chris\\Desktop\\aoc2023\\aoc2023\\src\\dayTwoInput.txt");
        int result = 0;

        for (String line: lines){
            System.out.println(getGameNumber(line));
            if (getGameNumber(line) == 1){
                System.out.println(parseGame(line));
            }
        }


        System.out.println(result);

        int sum = sumOfGames(lines);
        System.out.println(sum);

        int powerSum = sumOfPowerCubes(lines);
        System.out.println(powerSum);

    }

    public static int getGamePower(Map<String, Integer> mins){
        int result =1;
        for (Map.Entry<String, Integer> entry: mins.entrySet()
             ) {
            result = result * entry.getValue();


        }
        return result;
    }

    public static int sumOfPowerCubes(List<String> games){
        int result = 0;
        for (String game: games
        ) {


            String[] parseGame = parseGame(game);
            Map<String, Integer> mins = findMinimumCubes(parseGame);
            int gameNumber = getGamePower(mins);
            result += gameNumber;
//            int validGame = isValidGame(parseGame);
//            if (validGame == -1){
//                result+=gameNumber;
//
//            }



        }
        return result;
    }

    public static Map<String, Integer> findMinimumCubes(String[] pulls){

        Map<String, Integer> mins = new HashMap<>();

        for (String pull: pulls
        ) {

            String number = pull.split(" ")[0];
            String colour = pull.split(" ")[1];
            int num = Integer.parseInt(number);

            if (!mins.containsKey(colour)){
                mins.put(colour,num);
            } else if (mins.get(colour)<num) {
                mins.put(colour,num);

            }


        }
        return mins;

        }

    public static int getGameNumber(String game){

        String result = (game.split(":")[0]).split(" ")[1];
        return Integer.parseInt(result);
    }

    public static String[] parseGame(String game){

        HashMap<String, Integer> map = new HashMap<>();

        char[] chars = game.toCharArray();

        String gameString = game.split(": ")[1];

        String[] pulls = gameString.split(", |\\; ");

//        for (String pull: pulls
//             ) {
//            System.out.println(pull);
//
//        }








        return pulls;


    }



    public static int isValidGame(String[] pulls){

        for (String pull: pulls
             ) {

            String number = pull.split(" ")[0];
            String colour = pull.split(" ")[1];
            int num = Integer.parseInt(number);

            switch (colour){
                case "red":
                    if (num > limits.get(colour)){
                        return 0;
                    }
                case "green":
                    if (num > limits.get(colour)){
                        return 0;
                    }
                case "blue":
                    if (num > limits.get(colour)){
                        return 0;
                    }

            }

        }



        return -1;

    }


    public static int sumOfGames(List<String> games){
        int result = 0;
        for (String game: games
             ) {

            int gameNumber = getGameNumber(game);
            String[] parseGame = parseGame(game);
            int validGame = isValidGame(parseGame);
            if (validGame == -1){
                result+=gameNumber;

            }



        }
        return result;
    }
}
