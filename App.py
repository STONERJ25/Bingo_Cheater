import random
from BingoGame import BingoGame

def main():
    bingo = False
    nextround = "y"
    chip = 0

    size = int(input("Number of Boards: "))
    boards = [BingoGame(i) for i in range(size)]

    while nextround.lower() == "y":
        gametype = input("Input game type: Regular-R, Postage-P, X, T, Corners-C, Blackout-B: ")
        first_char = gametype[0]

        while not bingo:
            chip = int(input("Enter chip number: "))
            for index, board in enumerate(boards):
                if board.make_move(chip, first_char):
                    print(f"Board {index + 1} wins!")
                    bingo = True

        nextround = input("New Game? Y/N: ")
        bingo = False

if __name__ == "__main__":
    main()
