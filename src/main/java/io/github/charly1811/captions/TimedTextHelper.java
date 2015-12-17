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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import io.github.charly1811.captions.io.Parser;

public class TimedTextHelper {

    /**
     * Creates {@link TimedText} instance from an InputStream
     * @param inputStream
     * @param parser the parser to use
     * @return a {@link java.util.Map} containing each {@link TimedText} instances
     * @throws IOException
     */
    private static Map<Long, TimedText> read(InputStream inputStream, Parser parser) throws IOException {
        Scanner scanner = new Scanner(inputStream);

        if(parser == null) {
            System.err.println("Invalid parser");
            return Collections.emptyMap();
        }

        LinkedHashMap<Integer,Long[]> intervalMap = new LinkedHashMap<>();
        LinkedHashMap<Integer,String> captions = new LinkedHashMap<>();

        int activeIndex = 0;

        LinkedHashMap<Long,TimedText> timedTextMap = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            int lineType = parser.getLineType(line);
            if(lineType == Types.TYPE_INDEX) {
                activeIndex = Integer.parseInt(line);
                long[] longs = parser.extractTimeInterval(scanner.nextLine());
                intervalMap.put(activeIndex,new Long[]{longs[0],longs[1]});
            }
            else if(lineType == Types.TYPE_CAPTION) {
                String previousCaption = captions.containsKey(activeIndex) ? captions.get(activeIndex) : "";
                captions.put(activeIndex, previousCaption.concat("\n").concat(line).trim());
            }
        }
        for (int index : intervalMap.keySet()) {
            Long[] interval = intervalMap.get(index);
            TimedText timedText = new TimedText(Parsers.SRT, index,captions.get(index),interval[0],interval[1] - interval[0]);
            timedTextMap.put(timedText.getPosition(),timedText);

        }
        return timedTextMap;
    }

    public static Parser getParser(File subtitleFile) {
        Parser parser = null;
        String name = subtitleFile.getName();
        int i = name.lastIndexOf(".");
        String extension = i > 0?name.substring(i + 1):null;
        if(extension != null) {
            switch (extension) {
                case "srt":
                    parser = Parsers.SRT_PARSER;
                    break;
                case "vtt":
                    parser = Parsers.VTT_PARSER;
                    break;
            }
        }
        return parser;
    }

    public static TimedTextResource getTimedTextResource(File captionsFile) {
        Parser parser = getParser(captionsFile);
        if(parser == null)
            return null;
        try {
            return new TimedTextResource(read(new FileInputStream(captionsFile),parser),parser.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TimedTextResource getTimedTextResource(String srt,Parser parser) {
        try {
            return new TimedTextResource(read(new ByteArrayInputStream(srt.getBytes()),parser),parser.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

