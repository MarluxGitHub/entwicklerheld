package de.entwicklerheld.climbingStairs;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ClimbingStairs {

    public static int climbingStairs(int numberOfStairs) {
        if(numberOfStairs < 0) return 0;

        List<Integer> dp = new ArrayList<>(  Arrays.asList(0,1,2));

        for(int i = 3; i <= numberOfStairs; i++) {
            dp.add(dp.get(i-1)+dp.get(i-2));
        }

        return dp.get(numberOfStairs);

    }

}