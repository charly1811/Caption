package io.github.charly1811.captions.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2016 Charles-Eugene Loubao
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TimeUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat("##.###");

    /**
     * @param millis The milliseconds to convert to a string
     * @return A Simple String representation of the milliseconds. Example: 5415040.999 > 01:30:15
     */
    public static String millisToString(double millis) {

        long seconds = TimeUnit.MILLISECONDS.toSeconds((long) millis);

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remaining = (seconds % 3600) % 60;


        String output = "";

        if (hours > 0) {
            output = output.concat(String.format("%02d:", hours));
        }
        output = output.concat(String.format("%02d:", minutes));
        output = output.concat(String.format("%02d", remaining));

        return output;
    }

    /**
     * @param millis The milliseconds to convert to a string
     * @return The exact String representation of the milliseconds. Example: 5415040.999 > 01:30:15.040999
     */
    public static String millisToStringDouble(double millis) {

        double seconds = millis / 1000d;

        long hours = (long) (seconds / 3600);
        long minutes = (long) ((seconds % 3600) / 60);
        double remaining = (seconds % 3600d) % 60d;

        String output = "";

        output = output.concat(String.format("%02d:", hours));
        output = output.concat(String.format("%02d:", minutes));

        String stringifiedSeconds = decimalFormat.format(remaining);

        // The decimal part of the seconds (Milliseconds) must be given in three digits.
        // We fill the remaining spaces (if any) with zeros

        String decimal = stringifiedSeconds.split("[.]")[1];
        int toFill = 3 - decimal.length();

        for (int i = 0; i < toFill; i++) {
            decimal = decimal.concat("0");
        }

        stringifiedSeconds = stringifiedSeconds.replace(stringifiedSeconds.split("[.]")[1], decimal);

        output = output.concat(stringifiedSeconds);

        return output;
    }

    /**
     * @param string The string to convert to milliseconds
     * @return the milliseconds representation of the string. Example:  01:30:15.040999 > 5415040.999
     */
    public static double stringToMillis(String string) {

        String[] tokens = string.split(":");

        int secondsIndex = tokens.length - 1;
        int minutesIndex = secondsIndex - 1;
        int hoursIndex = minutesIndex - 1;

        double seconds = (Double.parseDouble(tokens[secondsIndex].replace(",", "."))) * 1000d;
        long minutes = minutesIndex > 0 ? TimeUnit.MINUTES.toMillis(Long.parseLong(tokens[minutesIndex])) : 0;
        long hours = hoursIndex >= 0 ? TimeUnit.HOURS.toMillis(Long.parseLong(tokens[hoursIndex])) : 0;

        return seconds + minutes + hours;
    }

}
