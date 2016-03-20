/**
 * Copyright 2015 Charles-Eugene Loubao
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

package io.github.charly1811.captions;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.charly1811.captions.io.Parser;
import io.github.charly1811.captions.io.VttParser;

public class TimedTextResource implements Serializable {

    private int parserType;
    private ArrayList<TimedText> list;
    private String name = "Unknown";

    public TimedTextResource(Map<Double, TimedText> collection) {
        list = new ArrayList<>(collection.values());
    }

    public TimedTextResource(Map<Double, TimedText> read, int parserType) {
        this(read);
        this.parserType = parserType;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return toString(Parsers.getParser(parserType)).trim();
    }

    public TimedText getNextTimedText(Double position) {
        TimedText timedText = null;
        for (TimedText text : list) {
            boolean foundResource = position >= text.getPosition() && position <= text.getPosition() + text.getDuration();
            if (foundResource) {
                timedText = text;
                break;
            }
        }
        return timedText;
    }

    public String toString(Parser parser) {
        StringBuilder stringBuilder = new StringBuilder(parser.getHeader());

        for (TimedText timedText : list) {
            stringBuilder.append(timedText.toString(parser));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public ArrayList<TimedText> getTimedTexts() {
        return list;
    }

    public String getName() {
        return name;
    }
}
