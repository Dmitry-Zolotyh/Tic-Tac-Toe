import java.util.Scanner;

public class Game {
    public static char[] field = {' ', ' ', ' ',
                                  ' ', ' ', ' ',
                                  ' ', ' ', ' '};
    public static void main(String[] args) {
        boolean xTurn = true;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            boolean xWins = hasWinner('X');
            boolean oWins = hasWinner('O');
            //How many 'X' and 'O'?
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < 9; i++) {
                if (field[i] == 'X') {
                    xCount++;
                }
                if (field[i] == 'O') {
                    oCount++;
                }
            }
            //Impossible result
            if (oWins && xWins || xCount - oCount > 1 || oCount - xCount > 1) {
                System.out.print("Impossible");
                return;
            }
            //X wins
            if (xWins) {
                printField();
                System.out.print("X wins");
                return;
            }
            //O wins
            if (oWins) {
                printField();
                System.out.print("O wins");
                return;
            }
            // Draw or continue the game
            if (xCount + oCount < 9) {
                //Continue the game
                printField();
                //While user input is invalid
                while (true) {
                    int coordinateA;
                    int coordinateB;
                    int index;
                    System.out.print("Enter the coordinates: ");
                    String tokenA = scanner.next();

                    if (isOneDigitNumber(tokenA)) {
                        coordinateA = Integer.parseInt(tokenA);
                        String tokenB = scanner.next();
                        if (isOneDigitNumber(tokenB)) {
                            coordinateB = Integer.parseInt(tokenB);
                            if (isInArrayBounds(coordinateA) && isInArrayBounds(coordinateB)) {
                                //Convert coordinates in array index
                                index = (3 - coordinateB) * 3 + coordinateA - 1;
                                if (field[index] == ' ' || field[index] == '_') {
                                    if (xTurn) {
                                        field[index] = 'X';
                                        xTurn = false;
                                        break;
                                    } else {
                                        field[index] = 'O';
                                        xTurn = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("This cell is occupied! Choose another one!");
                                }
                            } else {
                                System.out.println("Coordinates should be from 1 to 3!");
                            }
                        } else {
                            System.out.println("You should enter numbers!");
                        }
                    } else {
                        System.out.println("You should enter numbers!");
                    }
                }
            } else {
                printField();
                System.out.print("Draw");
                return;
            }
        }
    }

    public static boolean isOneDigitNumber(String coordinate) {
        String digits = "0123456789";
        if (coordinate.length() != 1) {
            return false;
        }
        return digits.contains(coordinate);
    }

    public static boolean isInArrayBounds(int number) {
        return number >= 1 && number <= 3;
    }

    public static void printField() {
        System.out.println("---------");
        System.out.println("| " + field[0] + " " + field[1] + " " + field[2] + " |");
        System.out.println("| " + field[3] + " " + field[4] + " " + field[5] + " |");
        System.out.println("| " + field[6] + " " + field[7] + " " + field[8] + " |");
        System.out.println("---------");
    }

    public static boolean hasWinner(char c) {
        boolean hasWinner = false;
        for (int i = 0; i < 3; i++) {
            //Is it 3 same char in a row?
            if (field[i * 3] == c && field[i * 3 + 1] == c && field[i * 3 + 2] == c) {
                hasWinner = true;
            }
            //Is it 3 same char in a column?
            if (field[i] == c && field[i + 3] == c && field[i + 6] == c) {
                hasWinner = true;
            }
        }
        //Is it 3 same char in a diagonal?
        if (field[0] == c && field[4] == c && field[8] == c ||
                field[2] == c && field[4] == c && field[6] == c) {
            hasWinner = true;
        }
        return hasWinner;
    }
}