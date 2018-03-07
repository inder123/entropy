/*
 * Copyright (C) 2018 Inderjeet Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singhinderjeet.entropy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Class to represent JSON structure of Shakespeare text
 *
 * @author Inderjeet Singh
 */
public class Shakespeare {
    static final class ShakespeareTextData {
        @SerializedName("line_id")
        String lineId;
        @SerializedName("play_name")
        String playName;
        @SerializedName("speech_number")
        String speechNumber;
        @SerializedName("line_number")
        String lineNumber;
        @SerializedName("speaker")
        String speaker;
        @SerializedName("text_entry")
        String textEntry;
    }

    // Download https://download.elastic.co/demos/kibana/gettingstarted/shakespeare_6.0.json
    public static char[][] loadShakespeareanSentences(int count) throws IOException {
        Gson gson = new Gson();
        char[][] sentences = new char[count][];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("shakespeare_6.0.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ShakespeareTextData data = gson.fromJson(line, ShakespeareTextData.class);
                if (data != null && data.textEntry != null && !data.textEntry.trim().isEmpty()) {
                    sentences[i] = data.textEntry.toCharArray();
                    ++i;
                    if (i >= count) break;
                }
            }
        }
        return sentences;
    }
}
