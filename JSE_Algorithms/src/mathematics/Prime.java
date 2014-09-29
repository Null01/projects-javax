/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematics;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    private final int MAX_COUNT_PRIME;
    public final boolean prime[];

    public Prime(int MAX_COUNT_PRIME) {
        this.MAX_COUNT_PRIME = MAX_COUNT_PRIME;
        prime = new boolean[MAX_COUNT_PRIME + 1];
        criba_eratostenes();
    }

    private void criba_eratostenes() {
        for (int i = 2; i * i <= MAX_COUNT_PRIME; i++) {
            if (!prime[i]) {
                for (int k = 2; (i * k) <= MAX_COUNT_PRIME; k++) {
                    prime[i * k] = true;
                }
            }
        }
    }

    public List<Integer> getNumbersPrime(boolean[] bs) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < bs.length; i++) {
            if (!bs[i]) {
                list.add(i);
                System.out.println(i+", ");
                
            }
        }
        return list;
    }

    public static void main(String args[]) {
        Prime p = new Prime(10000);
        p.getNumbersPrime(p.prime);
    }

}
