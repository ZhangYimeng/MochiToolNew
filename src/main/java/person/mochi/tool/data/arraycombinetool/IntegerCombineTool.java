package person.mochi.tool.data.arraycombinetool;

import java.util.Arrays;

public class IntegerCombineTool {

    public static int[] append(int[] integerA, int[] integerB) {
        int[] integerTemp = Arrays.copyOf(integerA, integerA.length + integerB.length);
        System.arraycopy(integerB, 0, integerTemp, integerA.length, integerB.length);
        return integerTemp;
    }

    public static int[] combineThreeinteger(int[] integerA, int[] integerB, int[] integerC) {
        int[] integerTemp = Arrays.copyOf(integerA, integerA.length + integerB.length + integerC.length);
        System.arraycopy(integerB, 0, integerTemp, integerA.length, integerB.length);
        System.arraycopy(integerC, 0, integerTemp, integerA.length + integerB.length, integerC.length);
        return integerTemp;
    }

}
