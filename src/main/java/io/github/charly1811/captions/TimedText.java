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

package io.github.charly1811.captions;

import java.io.Serializable;

import io.github.charly1811.captions.io.Parser;

public class TimedText implements Serializable {

    private int type;

    private int index;
    private String text;
    private double position;
    private double duration;

    public TimedText(int type, int index, String text, double position, double duration) {
        this.type = type;
        this.index = index;
        this.text = text;
        this.position = position;
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public double getDuration() {
        return duration;
    }

    public String getText() {
        return text;
    }

    public double getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {

        return o instanceof TimedText && ((TimedText) o).text.equals(text) && ((TimedText) o).position == position && ((TimedText) o).duration == duration ;
    }

    public String toString(Parser parser) {
        String indexLine = parser.getIndexLine(index);
        String timeLine = parser.getTimeLine(position,duration);
        String caption = parser.getCaptionLine(text);
        return indexLine+"\n"+timeLine+"\n"+caption+"\n";
    }
}