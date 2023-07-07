"""
   ***
  ** **
 **   **
 **   **         ****
 **   **       **   ****
 **  **       *   **   **
  **  *      *  **  ***  **
   **  *    *  **     **  *
    ** **  ** **        **
    **   **  **
   *           *
  *             *
 *    0     0    *
 *   /   @   \   *
 *   \__/ \__/   *
   *     W     *
     **     **
       *****

You found the Easter Bunny! Now lets play a game of scissors, rocks and paper!
If you win more than half of 10 games (draws do not count), you can collect your reward!
Bunnytastic isn't it?
"""
from bunnygame.models import Hand
import random


def play_easter_bunny_game(games):
    # if games array is empty return rock
    if not games:
        return Hand.ROCK

    # get last element as Tuple of games array
    last_game = games[-1]
    stand = last_game[2]

    # if last game was a draw, return the same hand
    if stand == 1:
        return last_game[0]

    if stand == 0:
        if last_game[0] == Hand.ROCK:
            return Hand.PAPER
        if last_game[0] == Hand.PAPER:
            return Hand.SCISSORS
        return Hand.ROCK

    if stand == -1:
        return last_game[0]



