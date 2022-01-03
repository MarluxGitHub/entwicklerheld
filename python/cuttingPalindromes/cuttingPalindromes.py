class CuttingPalindromesPython:

    def is_palindrome(self, string):
        return string == string[::-1]

    def minimum_palindrome_cuts(self, palindrome_string) -> int:
        if self.is_palindrome(palindrome_string):
            return 0
        min_cuts = float('inf')
        for i in range(len(palindrome_string)):
            if self.is_palindrome(palindrome_string[:i + 1]):
                min_cuts = min(min_cuts, 1 + self.minimum_palindrome_cuts(palindrome_string[i + 1:]))
        return min_cuts