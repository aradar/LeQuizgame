import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;

import static spark.Spark.*;

/**
 * Created by rschlett on 1/6/17.
 */
public class Main {

    public static class HighScoreEntity {
        public int position;
        public String name;
        public int movesTaken;
    }

    public static String getHighScoreEntities(int number) {
        HighScoreEntity[] highScoreEntities = new HighScoreEntity[number];
        for (int i = 0; i < highScoreEntities.length; i++) {
            HighScoreEntity highScoreEntity = new HighScoreEntity();
            highScoreEntity.position = i + 1;
            highScoreEntity.movesTaken = (int) (Math.random() * 20);
            highScoreEntity.name = "Hasso";
            highScoreEntities[i] = highScoreEntity;
        }

        return new Gson().toJson(highScoreEntities);
    }

    public static void main(String[] args) {
        get("/top/:number", (req, res) -> {
            int number = Integer.valueOf(req.params(":number"));
            res.type("application/json");
            return getHighScoreEntities(number);
        });

        get("/all", (req, res) -> {
            res.type("application/json");
            return getHighScoreEntities(5);
        });
        get("/name/:name", (req, res) -> {
            res.type("application/json");

            String name = req.params(":name");
            HighScoreEntity highScoreEntity = new HighScoreEntity();
            highScoreEntity.position = (int) (Math.random() * 10);
            highScoreEntity.movesTaken = (int) (Math.random() * 20);
            highScoreEntity.name = name;

            return new Gson().toJson(highScoreEntity);
        });
        put("/add", (req, res) -> {
            System.out.printf("[%s] %s : %s%n", LocalDateTime.now(),req.contentType(), req.body());
            return "{successss:false}";
        });
    }

}
