using System;
using System.Collections.Generic;
using System.Linq;

namespace CollatzChallengeImplementation
{
    public static class CollatzConjecture
    {
        public static int CountStepsToOne(int number)
        {
            if (number <= 0)
            {
                throw new ArgumentOutOfRangeException("Number must be positive");
            }

            int steps = 0;
            while (number != 1)
            {
                if (number % 2 == 0)
                {
                    number /= 2;
                }
                else
                {
                    number = 3 * number + 1;
                }
                steps++;
            }

            return steps;
        }
    }
}
