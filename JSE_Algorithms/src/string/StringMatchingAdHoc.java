package string;

public class StringMatchingAdHoc {

    /**
     * Halla la cantidad de veces que se repite una cadena @Ej: ababab = 3
     *
     * @param str
     * @return
     */
    public int findPeriodOfRepeat(String str) {
        int i, index = 1;
        char array[] = str.toCharArray();
        for (i = 1; i < array.length; i++) {
            while (array[i] != array[i % index]) {
                index++;
            }
        }
        return (i % index == 0) ? (i / index) : 1;
    }

}
