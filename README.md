# Tetris

This project was part of a course I took as part of my undergrad.

### What does it do?
This is an unpolished, but playable version of Tetris. The game is controlled using the keyboard:
* `left-arrow` and `right-arrow`: move the Tetromino to the left and right respectively.
* `down-arrow`: move the Tetromino down.
* `up-arrow`: rotates the Tetromino counter-clockwise.
* `space`: drops the Tetromino straight down. Shortcut for holding down `down-arrow`.
* `c`: puts the Tetromino in storage. If the storage is empty, the next Tetromino is called. If the storage already
contains a Tetromino, then the current and the stored Tetromino are swapped.

After finishing a round, the player has the option to save their score in a leaderboard.

## Appendix

### Terminology
I might be using some terminology that might be new or unclear to people that
have never played the game, so I will define any such terms under this heading.
- **Line**: A horizontal slice of a tetris board, usually contains at 
least one square.
- **Clearing a line**: Filling in a line, making it disappear.
- **Tetrominos**: The basic shapes of Tetris. They are made of different 
arrangements of squares. See the image below for examples.
(Note: Left handed versions of the L and the offset square shape exist 
as well)
![image](https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/All_5_free_tetrominoes.svg/1920px-All_5_free_tetrominoes.svg.png)
