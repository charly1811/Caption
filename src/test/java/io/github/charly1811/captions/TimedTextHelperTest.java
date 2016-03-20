package io.github.charly1811.captions;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
public class TimedTextHelperTest {

    @Test
    public void testGetTimedTextResource() throws Exception {

        String srt = "1\n" +
                "00:02:17,440 --> 00:02:20,375\n" +
                "Senator, we're making\n" +
                "our final approach into Coruscant.\n" +
                "\n" +
                "2\n" +
                "00:02:20,476 --> 00:02:22,501\n" +
                "Very good, Lieutenant.";

        String expectedCaption1 = "Senator, we're making\nour final approach into Coruscant.";

        String expectedCaption2 = "Very good, Lieutenant.";

        TimedTextResource resource = TimedTextHelper.getTimedTextResource(srt, Parsers.SRT_PARSER);
        assertEquals(true, resource != null);

        ArrayList<TimedText> timedTexts = resource.getTimedTexts();
        assertEquals(expectedCaption1, timedTexts.get(0).getText());
        assertEquals(expectedCaption2, timedTexts.get(1).getText());
    }

    @Test
    public void testSrtParserToString() {
        String srt = "1\n" +
                "00:02:17,440 --> 00:02:20,375\n" +
                "Senator, we're making\n" +
                "our final approach into Coruscant.\n" +
                "\n" +
                "2\n" +
                "00:02:20,476 --> 00:02:22,501\n" +
                "Very good, Lieutenant.";

        TimedTextResource srtResource = TimedTextHelper.getTimedTextResource(srt, Parsers.SRT_PARSER);

        assertEquals(srt, srtResource.toString());
    }

    @Test
    public void testSrtToVtt() {
        String srt = "1\n" +
                "00:02:17,440 --> 00:02:20,375\n" +
                "Senator, we're making\n" +
                "our final approach into Coruscant.\n" +
                "\n" +
                "2\n" +
                "00:02:20,476 --> 00:02:22,501\n" +
                "Very good, Lieutenant.";

        String expectedVTT = "WEBVTT\n" +
                "\n" +
                "1\n" +
                "00:02:17.440 --> 00:02:20.375\n" +
                "Senator, we're making\n" +
                "our final approach into Coruscant.\n" +
                "\n" +
                "2\n" +
                "00:02:20.476 --> 00:02:22.501\n" +
                "Very good, Lieutenant.";

        TimedTextResource srtResource = TimedTextHelper.getTimedTextResource(srt, Parsers.SRT_PARSER);
        TimedTextResource vttResource = TimedTextHelper.getTimedTextResource(srtResource.toString(Parsers.VTT_PARSER), Parsers.VTT_PARSER);

        System.out.println(vttResource.toString());
        System.out.println(vttResource.toString(Parsers.SRT_PARSER));

        assertEquals(expectedVTT, vttResource.toString());
    }


}