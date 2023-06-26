namespace RomanNumeralsCsharpImplementation
{
    public static class RomanNumeralsCsharp
    {
        public static string ToRoman(this int value)
        {
             string result = "";
            int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            string[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            for (int i = 0; i < values.Length; i++)
            {
                while (value >= values[i])
                {
                    result += numerals[i];
                    value -= values[i];
                }
            }
            return result;
        }
    }
}