// Adapted from https://rosettacode.org/wiki/Entropy#Java
package com.singhinderjeet.entropy;

import java.util.HashMap;
import java.util.Map;

public class REntropy {
    public static double getShannonEntropy(String s) {
        return getShannonEntropy(s.toCharArray());
    }

    public static double getShannonEntropy(char[] s) {
        int n = 0;
        Map<Character, Integer> occ = new HashMap<>();
        for (int c_ = 0; c_ < s.length; ++c_) {
            char cx = s[c_];
            if (occ.containsKey(cx)) {
                occ.put(cx, occ.get(cx) + 1);
            } else {
                occ.put(cx, 1);
            }
            ++n;
        }
        double e = 0.0;
        for (Map.Entry<Character, Integer> entry : occ.entrySet()) {
            // char cx = entry.getKey();
            double p = (double) entry.getValue() / n;
            e += p * log2(p);
        }
        return -e;
    }

    private static double log2(double a) {
        return Math.log(a) / Math.log(2);
    }
}