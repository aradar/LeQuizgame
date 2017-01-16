import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static spark.Spark.*;

/**
 * Created by rschlett on 1/6/17.
 */
public class Main {

    private static List<HighScore> highScores;

    private static void sort() {
        highScores.sort(Comparator.comparingInt(h -> h.movesTaken));
        for (int i = 0; i < highScores.size(); i++) {
            highScores.get(i).position = i+1;
        }
    }

    private static String getHighScoreEntities(int number) {
        return new Gson().toJson(highScores.subList(0,number).toArray());
    }

    public static void main(String[] args) {

        highScores = new ArrayList<>();

        get("/top/:number", (req, res) -> {
            int number = Integer.valueOf(req.params(":number"));
            res.type("application/json");
            return getHighScoreEntities(number);
        });

        get("/all", (req, res) -> {
            res.type("application/json");
            return getHighScoreEntities(highScores.size());
        });
        get("/name/:name", (req, res) -> {
            res.type("application/json");
            String name = req.params(":name");
            HighScore highScore = new HighScore();
            highScore.position = (int) (Math.random() * 10);
            highScore.movesTaken = (int) (Math.random() * 20);
            highScore.name = name;

            return new Gson().toJson(highScore);
        });
        put("/add", (req, res) -> {
            try {
                HighScore highScore = new Gson().fromJson(req.body(),HighScore.class);

                boolean exists = false;

                for(HighScore highScoreElem : highScores) {
                    if (Objects.equals(highScoreElem.name, highScore.name)) {
                        exists = true;
                        if (highScoreElem.movesTaken > highScore.movesTaken) {
                            highScoreElem.movesTaken = highScore.movesTaken;
                        }
                    }
                }


                if (!exists)
                    highScores.add(highScore);

                System.out.printf("[%s] %s : %s%n", LocalDateTime.now(),req.contentType(), req.body());
                sort();
                return "{success:true}";
            } catch (com.google.gson.JsonSyntaxException ex) {
                return "{success:false}";
            }
        });
    }

    private static class HighScore {
        int position;
        String name;
        int movesTaken;

        HighScore() {
        }
    }

}
