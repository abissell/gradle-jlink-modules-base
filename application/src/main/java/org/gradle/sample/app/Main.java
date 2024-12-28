package org.gradle.sample.app;

import org.gradle.sample.list.LinkedList;

import com.google.gson.Gson;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.gradle.sample.app.data.Message;

import static org.gradle.sample.utilities.StringUtils.join;
import static org.gradle.sample.utilities.StringUtils.split;
import static org.gradle.sample.app.MessageUtils.getMessage;

public class Main {
    public static void main(String[] args) throws Exception {
        LinkedList tokens;
        tokens = split(getMessage());
        System.out.println(join(tokens));

        Options options = new Options();
        options.addOption("json", true, "data to parse");
        options.addOption("debug", false, "prints module infos");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String json = cmd.getOptionValue("json");
        Message message = new Gson().fromJson(json == null ? "{}" : json, Message.class);
        System.out.println("deserialized Message: " + message);

        if (cmd.hasOption("debug")) {
            printModuleDebug(StringUtils.class);
        }
    }

    private static void printModuleDebug(Class<?> clazz) {
        System.out.println(clazz.getModule().getName() + " - " + clazz.getModule().getDescriptor().version().get());
    }
}
