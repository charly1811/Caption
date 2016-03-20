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
package io.github.charly1811.captions.io;

import io.github.charly1811.captions.Parsers;
import io.github.charly1811.captions.Types;
import io.github.charly1811.captions.util.TimeUtils;

public class SrtParser extends Parser {

    public static final String INDEX_PATTERN = "\\d+";
    private static final String TIME_PATTERN = "(\\d{2}:(\\d{2}):(\\d{2}))((,(\\d+))?)(\\s*)(-->)(\\s*)(\\d{2}:(\\d{2}):(\\d{2}))((,(\\d+))?)";

    private static final Parser instance = new SrtParser();

    public static Parser getInstance() {
        return instance;
    }

    @Override
    public int getLineType(String line) {
        int type;
        if (line.matches(INDEX_PATTERN)) {
            type = Types.TYPE_INDEX;
        } else if (line.matches(TIME_PATTERN)) {
            type = Types.TYPE_TIME;
        } else {
            type = Types.TYPE_CAPTION;
        }
        return type;
    }

    @Override
    public long[] extractTimeInterval(String line) {
        String[] tokens = line.split("(\\s*)(-->)(\\s*)");
        return new long[]{(long) TimeUtils.stringToMillis(tokens[0]), (long) TimeUtils.stringToMillis(tokens[1])};
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getTimeLine(double position, double duration) {
        String positionStartString = TimeUtils.millisToStringDouble(position).replace(".", getDecimalSymbol());
        String positionEndString = TimeUtils.millisToStringDouble(position + duration).replace(".", getDecimalSymbol());

        return String.format("%s --> %s", positionStartString, positionEndString);
    }

    @Override
    public String getCaptionLine(String text) {
        return text;
    }

    @Override
    public String getIndexLine(int index) {
        return String.valueOf(index);
    }

    @Override
    public int getType() {
        return Parsers.SRT;
    }

    @Override
    public String getDecimalSymbol() {
        return ",";
    }
}
