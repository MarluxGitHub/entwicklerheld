public class SayNumbersJava {

    public static void main(String[] args){
        System.out.println(SayNumbersJava.say(10002300));
    }

    // Convert Long Number to English Number word
    // When the method is called with the number 987.654.321.123, then it should return nine hundred eighty-seven billion six hundred fifty-four million three hundred twenty-one thousand one hundred twenty-three.
    public static String say(long number) {
        String[] units = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };
        String[] tens = {
            "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"
        };
        String[] teens = {
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
        };
        String[] thousandsGroups = {
            "", " thousand", " million", " billion"
        };

        if(number < 0 || number > 999999999999L) throw new IllegalArgumentException("Number must be between 0 and 999,999,999,999");

        if (number == 0) {
            return units[0];
        }

        String result = "";
        int thousandGroup = 0;

        while (number > 0) {
            int thousands = (int) (number % 1000);
            if (thousands != 0) {
                String thousandsResult = convert(thousands, units, tens, teens);
                result = thousandsResult + thousandsGroups[thousandGroup] + (result != "" ? " " : "") + result.trim();
            }
            thousandGroup++;
            number /= 1000;
        }

        return result.trim();
    }

    public static String convert(int number, String[] units, String[] tens, String[] teens) {
        String result = "";
        boolean minus = false;

        if (number >= 100) {
            result += units[number / 100] + " hundred";
            number %= 100;
        }

        if (number >= 10 && number <= 19) {
            result += (result != "" ? " " : "") + teens[number - 10];
            return result;
        } else if (number >= 20) {
            result += (result != "" ? " " : "") + tens[number / 10 - 1];
            number %= 10;
            minus = true;
        }

        if (number >= 1 && number <= 9) {
            result += (minus ? "-" : " ") + units[number];
        }

        return result;
    }
}

