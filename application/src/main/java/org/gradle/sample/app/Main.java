package org.gradle.sample.app;

import org.gradle.sample.list.LinkedList;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

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

        printModuleDebug(StringUtils.class);
    }

    private static void printModuleDebug(Class<?> clazz) {
        System.out.println(clazz.getModule().getName() + " - " + clazz.getModule().getDescriptor().version().get());
    }
}
