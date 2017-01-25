package de.spitak.twomanychoises.server;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.*;

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

    private static HighScore[] getHighScoreDummyEntities(int number) {
        HighScore[] highScoreEntities = new HighScore[number];
        for (int i = 0; i < highScoreEntities.length; i++) {
            HighScore highScoreEntity = new HighScore();
            highScoreEntity.position = i + 1;
            highScoreEntity.movesTaken = (int) (Math.random() * 20);
            highScoreEntity.name = "Hasso" + i;
            highScoreEntities[i] = highScoreEntity;
        }

        return highScoreEntities;
    }

    public static void main(String[] args) {

        highScores = new ArrayList<>();

        get("/top/:number", (req, res) -> {
            int number = Integer.valueOf(req.params(":number"));
            res.type("application/json");
            if (number > highScores.size())
                number = highScores.size();

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
        get("/dummy/:number", (req, res) -> {
            res.type("application/json");
            int number = Integer.valueOf(req.params(":number"));
            // dump demo data
            highScores.addAll(Arrays.asList(getHighScoreDummyEntities(number)));
            sort();
            return new Gson().toJson("{success:true}");
        });
        get("/remove", (req, res) -> {
            res.type("application/json");
            highScores.clear();
            return new Gson().toJson("{success:true}");
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