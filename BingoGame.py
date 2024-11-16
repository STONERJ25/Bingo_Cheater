import random
from tes import Tes

class BingoGame:
    def __init__(self, num):
        self.num = num
        self.rawboard = [[0] * 5 for _ in range(5)]  # 5x5 board of integers
        self.checkboard = [['-'] * 5 for _ in range(5)]  # 5x5 board of characters
        self.is_board_over = False
        self.initialize_board_check()
        self.initialize_board_raw()

    def initialize_board_check(self):
        for i in range(5):
            for j in range(5):
                self.checkboard[i][j] = '-'  # Empty cell
        self.checkboard[2][2] = 'c'  # Center cell marked

    def initialize_board_raw(self):
        tes_instance = Tes()  # Create an instance of Tes
        self.rawboard = tes_instance.call(self.num)  # Call the method on the instance

    def make_move(self, chip, wincon):
        if self.is_board_over:
            return False
        for i in range(5):
            for j in range(5):
                if self.rawboard[i][j] == chip:
                    self.checkboard[i][j] = 'c'
        if self.check_win(wincon):
            print("Bingo on Board!")
            self.print_board()
            self.is_board_over = True
            return True
        return False

    def check_win(self, wincon):
        if wincon == 'R':
            return self.check_bingo()
        elif wincon == 'P':
            return (self.checkboard[0][3] == 'c' and
                    self.checkboard[0][4] == 'c' and
                    self.checkboard[1][4] == 'c' and
                    self.checkboard[1][3] == 'c')
        elif wincon == 'X':
            return (self.checkboard[0][0] == 'c' and
                    self.checkboard[0][4] == 'c' and
                    self.checkboard[1][1] == 'c' and
                    self.checkboard[1][3] == 'c' and
                    self.checkboard[3][1] == 'c' and
                    self.checkboard[3][3] == 'c' and
                    self.checkboard[4][0] == 'c' and
                    self.checkboard[4][4] == 'c')
        elif wincon == 'T':
            return (all(self.checkboard[0][j] == 'c' for j in range(5)) and
                    all(self.checkboard[i][2] == 'c' for i in range(5)))
        elif wincon == 'C':
            return (self.checkboard[0][0] == 'c' and
                    self.checkboard[4][4] == 'c' and
                    self.checkboard[0][4] == 'c' and
                    self.checkboard[4][0] == 'c')
        elif wincon == 'B':
            return all(all(cell == 'c' for cell in row) for row in self.checkboard)
        else:
            print("Invalid Win Case")
            return False

    def print_board(self):
        for row in self.checkboard:
            print(" ".join(row))
        print()

    def check_bingo(self):
        return self.check_rows() or self.check_columns() or self.check_diagonals()

    def check_rows(self):
        for i in range(5):
            if self.check_row(i):
                return True
        return False

    def check_row(self, row):
        return all(self.checkboard[row][j] == 'c' for j in range(5))

    def check_columns(self):
        for j in range(5):
            if self.check_column(j):
                return True
        return False

    def check_column(self, column):
        return all(self.checkboard[i][column] == 'c' for i in range(5))

    def check_diagonals(self):
        return self.check_left_to_right_diagonal() or self.check_right_to_left_diagonal()

    def check_left_to_right_diagonal(self):
        return all(self.checkboard[i][i] == 'c' for i in range(5))

    def check_right_to_left_diagonal(self):
        return all(self.checkboard[i][4 - i] == 'c' for i in range(5))
