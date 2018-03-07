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

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        char[][] strings = new char[1000 * 10][];
        double[] entropiesOriginal = new double[strings.length];
        double[] entropiesCurrent = new double[strings.length];
        for (int i = 0; i < strings.length; ++i) { // initialize
            // strings[i] = "The quick brown fox jumped over the little lazy dog".toCharArray();
            strings[i] = getRandomString(40);
            entropiesOriginal[i] = REntropy.getShannonEntropy(strings[i]);
        }
        for (int iteration = 0; iteration < 1000; ++iteration) {
            for (int i = 0; i < strings.length; ++i) {
                flipAChar(strings[i]);
                entropiesCurrent[i] = REntropy.getShannonEntropy(strings[i]);
            }
        }
        int reducedEntropyCount = 0;
        for (int i = 0; i < strings.length; ++i) {
            if (entropiesCurrent[i] < entropiesOriginal[i]) {
                System.out.println("Reduced entropy for: " + new String(strings[i]));
                ++reducedEntropyCount;
            }
        }
        System.out.println(reducedEntropyCount + " strings reduced in entropy");
    }

    private static final char[] charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final Random rand = new Random();
    private static void flipAChar(char[] cs) {
        int position = rand.nextInt(cs.length);
        int newChar = rand.nextInt(charSet.length);
        cs[position] = charSet[newChar];
    }

    private static char[] getRandomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; ++i) {
            str[i] = charSet[rand.nextInt(charSet.length)];
        }
        return str;
    }
}
