package org.gradle.sample.app;

import java.lang.management.ManagementFactory;

import com.abissell.javautil.math.DoubleRounder;
import org.gradle.sample.list.LinkedList;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
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

        Object copy = BeanUtils.cloneBean(message);
        System.out.println();
        System.out.println("Original: " + copy.toString());
        System.out.println("Copy:     " + copy.toString());

        double numberToRound = 1234.56789d;
        System.out.println("Number to round: " + numberToRound);
        System.out.println("Rounded down to 2 places: " + DoubleRounder.roundDn(numberToRound, 2));
        System.out.println("Rounded up to 2 places: " + DoubleRounder.roundUp(numberToRound, 2));
        System.out.println("Rounded to 0 places: " + DoubleRounder.round(numberToRound, 0));

        if (cmd.hasOption("debug")) {
            System.out.println("Runtime JVM arguments: " + ManagementFactory.getRuntimeMXBean().getInputArguments());
            printModuleDebug(StringUtils.class);
            printModuleDebug(DoubleRounder.class);
        }
    }

    private static void printModuleDebug(Class<?> clazz) {
        System.out.println(clazz.getModule().getName() + " - " + clazz.getModule().getDescriptor().version().get());
    }
}
