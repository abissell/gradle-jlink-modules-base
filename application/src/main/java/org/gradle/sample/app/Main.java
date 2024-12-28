package org.gradle.sample.app;

import org.gradle.sample.list.LinkedList;

import com.google.gson.Gson;

import static org.gradle.sample.utilities.StringUtils.join;
import static org.gradle.sample.utilities.StringUtils.split;
import static org.gradle.sample.app.MessageUtils.getMessage;
import org.gradle.sample.app.data.Message;

public class Main {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        System.out.println(join(tokens));

        String json = args[0];
        System.out.println("args[0]: " + args[0]);
        Message message = new Gson().fromJson(json == null ? "{}" : json, Message.class);
        System.out.println("deserialized Message: " + message);
    }
}
