package com.uob.server;

import java.net.URL;

/**
 *This class is for the game objects which will be used later
 * @author acer
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    public final URL question;
    public final int solution;

    public Game(
            @JsonProperty("question") URL question,
            @JsonProperty("solution") int solution) {
        this.question = question;
        this.solution = solution;
    }

    public URL getQuestion() {
        return question;
    }

    public int getSolution() {
        return solution;
    }
}