package connectfour;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Scanner;

public class Board {
    public static void main(String[] args) throws IOException {
        Game game = new Game('R');
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (count < game.MAX_TURN) {
            // Display the game
            game.display();
            game.printNextTurn();

            // waiting for user input & validate
            String s = in.nextLine().trim();
            if (!StringUtils.isNumeric(s)) {
                System.out.println("Please type a valid number in range [1..7]");
                continue;
            }
            Integer column = Integer.parseInt(s);
            if ( column < 1 || column > 7) {
                System.out.println("Please type a valid number in range [1..7]");
                continue;
            }
            if (!game.canInsert(column)) {
                System.out.println("Column " + column + " is full");
                continue;
            }

            // insert & switch player
            game.insert(column, game.getNextPlayer());
            if (game.isWin()) {
                game.displayWin();
                return;
            }
            ++count;
        }
        game.display();
        System.out.println("Game over, No winner!");
    }
}
