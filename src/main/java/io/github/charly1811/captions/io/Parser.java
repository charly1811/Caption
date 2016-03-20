
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

    public String getDecimalSymbol() {
        return ".";
    }

}
