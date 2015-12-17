
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

import io.github.charly1811.captions.io.Parser;
import io.github.charly1811.captions.io.SrtParser;
import io.github.charly1811.captions.io.VttParser;

public class Parsers {

    public static final VttParser VTT_PARSER = new VttParser();
    public static final SrtParser SRT_PARSER = new SrtParser();

    public static final int SRT = 0;
    public static final int VTT = 1;

    public static Parser getParser(int TYPE) {
        Parser parser = null;
        switch (TYPE) {
            case Parsers.SRT:
                parser = SRT_PARSER;
            break;
            case VTT:
                parser = VTT_PARSER;
                break;
        }
        return parser;
    }

}
