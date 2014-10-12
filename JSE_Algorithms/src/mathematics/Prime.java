package mathematics;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    private List<Integer> criba_eratostenes(int n) {
        int MAX_COUNT_PRIME = n;
        boolean prime[] = new boolean[MAX_COUNT_PRIME + 1];
        for (int i = 2; i * i <= MAX_COUNT_PRIME; i++) {
            if (!prime[i]) {
                for (int k = 2; (i * k) <= MAX_COUNT_PRIME; k++) {
                    prime[i * k] = true;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < prime.length; i++) {
            if (!prime[i]) {
                list.add(i);
            }
        }
        return list;
    }
}
