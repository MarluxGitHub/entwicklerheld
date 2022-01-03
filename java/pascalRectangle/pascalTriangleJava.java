package de.entwicklerheld.pascalsTriangleJava;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleJava {
    public static List<Long> getPascalsTriangleRow(int rowNumber) {
        List<Long> row = new ArrayList<>();
        if(rowNumber == 0) {
            row.add(1L);
            return row;
        }
        if(rowNumber == 1) {
            row.add(1L);
            row.add(1L);
            return row;
        }
        List<Long> previousRow = getPascalsTriangleRow(rowNumber - 1);
        previousRow.add(1L);
        row.addAll(previousRow);
        for(int i = 1; i < previousRow.size() - 1; i++) {
            row.set(i, previousRow.get(i - 1) + previousRow.get(i));
        }

        return row;
    }
}