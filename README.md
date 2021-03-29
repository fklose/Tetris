# CPSC210 Personal Project - Tetris

## Proposal
### What will the application do?
The application will be a playable version of Tetris.
The game will have a feature allowing players to track their highscores.
It will incorporate some standard features of Tetris, such as being able to see
the next five upcoming Tetrominos, as well as being able to move the Tetrominos
left and right, as well as rotate them. Initially the plan is to only include an
endless mode with other gamemodes being an option once the core of the game is
implemented.

### Who will use it?
Really anyone that likes to play Tetris and is looking for an offline
version of the game. The most popular version right now is found on an 
online website called Jstris, and I have yet to find a good offline
version of the game for myself.

### Why is this project of interest to you?
I enjoy playing Tetris and would like to be able to play the game offline,
since there are no good offline versions.

## User Stories
### Phase 1:
1. As a user I want to be able to see the highscores.
2. As a user I want to be able to add my own score to the list of highscores.
3. As a user I want to be able to see the next Tetrominos in queue.
4. As a user I want to be able to pull the next Tetramino from the queue.

## Phase 2:
1. As a user I want to have my scores saved when quitting the application.
2. As a user I want to load my saved scores when I start the application.

## Phase 3:
1. As a user I want to be able to add my score onto a leaderboard after playing a game.
2. As a user I want to add a Tetromino onto a board and move and rotate it.
3. As a user I want to stack multiple Tetrominos on top of each other.
4. As a user I want to see the Tetrominos on the board.
5. As a user I want to be able to load my leaderboard from a file.
6. As a user I want to be able to save my leaderboard.
7. As a user I want to be able to see my score.
8. As a user I want to be able to see if my leaderboard has been loaded or saved.
9. As a user I want to see the next five tetrominos.

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
