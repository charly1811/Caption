
/**
 * Copyright 2015 Charles-Eugene Loubao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.charly1811.captions.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public abstract class Parser {

    public abstract int getLineType(String line);

    public abstract long[] extractTimeInterval(String line);

    public abstract String getHeader();

    public abstract String getTimeLine(double position, double duration);

    public abstract String getCaptionLine(String text);
    public abstract String getIndexLine(int index);
    public abstract int getType();

    public long stringToMillis(String string) {
        long millis = 0;

        long seconds = 0;
        long minutes = 0;
        long hours = 0;

        String[] tokens = string.split(":");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(tokens));
        Collections.reverse(list);

        seconds = TimeUnit.SECONDS.toMillis(Math.round(Double.parseDouble(list.get(0).replace(",", "."))));
        minutes = TimeUnit.MINUTES.toMillis(Math.round(list.size() > 1 ? Double.parseDouble(list.get(1).replace(",", ".")) : 0));
        hours = TimeUnit.HOURS.toMillis(Math.round(list.size() > 2 ? Double.parseDouble(list.get(2).replace(",", ".")) : 0));

        millis = seconds + minutes + hours;
        return millis;
    }

    public String millisToString(double millis) {

        double seconds = millis * 0.001;

        long hours = (long) (seconds / 3600);
        long minutes = (long) ((seconds % 3600) / 60);
        double remaining = (seconds % 3600d) % 60d;

        String output = "";

        output = output.concat(String.format("%02d:", hours));
        output = output.concat(String.format("%02d:", minutes));
        output = output.concat(String.format("%02f", remaining));

        return output;

    }
}
