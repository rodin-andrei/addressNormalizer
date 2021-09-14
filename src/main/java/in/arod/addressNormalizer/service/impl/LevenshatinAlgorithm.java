package in.arod.addressNormalizer.service.impl;

import in.arod.addressNormalizer.service.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class LevenshatinAlgorithm implements Algorithm {
    @Override
    public float compare(String s1, String s2) {
       return    levenstain(s1, s2);
    }
    public  int levenstain(String str1, String str2) {
        int[] Di_1 = new int[str2.length() + 1];
        int[] Di = new int[str2.length() + 1];

        for (int j = 0; j <= str2.length(); j++) {
            Di[j] = j; // (i == 0)
        }

        for (int i = 1; i <= str1.length(); i++) {
            System.arraycopy(Di, 0, Di_1, 0, Di_1.length);

            Di[0] = i; // (j == 0)
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) != str2.charAt(j - 1)) ? 1 : 0;
                Di[j] = min(
                        Di_1[j] + 1,
                        Di[j - 1] + 1,
                        Di_1[j - 1] + cost
                );
            }
        }

        return Di[Di.length - 1];
    }

    private  int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }
}
