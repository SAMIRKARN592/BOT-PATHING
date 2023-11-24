import java.util.Scanner;

public class BotProgram {
    private int x, y;  // Current position of the bot
    private int direction;  // 0: North, 1: East, 2: South, 3: West

    public BotProgram() {
        x = 0;
        y = 0;
        direction = 0;
    }

    public void move(char instruction, int steps) {
	 switch (instruction) {
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            case 'M':
                moveForward(steps);
                break;
        }
    }

    private void turnLeft() {
        direction = (direction + 3) % 4;  // 90 degrees counterclockwise
    }

    private void turnRight() {
        direction = (direction + 1) % 4;  // 90 degrees clockwise
    }

    private void moveForward(int steps) {
        switch (direction) {
            case 0:
                y += steps;
                break;
            case 1:
                x += steps;
                break;
            case 2:
                y -= steps;
                break;
            case 3:
                x -= steps;
                break;
        }
    }

    public void printCurrentPosition() {
        String[] directions = {"North", "East", "South", "West"};
        System.out.println(directions[direction] + " (" + x + "," + y + ")");
    }

  public static void main(String[] args) {
    BotProgram bot = new BotProgram();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter movement commands (e.g., RM15 LM10): ");
    String input = scanner.nextLine().toUpperCase();

    for (int i = 0; i < input.length(); i++) {
        char instruction = input.charAt(i);
        
        // If the character is an instruction (L, R, or M)
        if (instruction == 'L' || instruction == 'R' || instruction == 'M') {
            int steps = 0;

            // Iterate through subsequent characters to read the numeric value
            while (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                steps = steps * 10 + Character.getNumericValue(input.charAt(++i));
            }

            bot.move(instruction, steps);
        }
    }

    // Print the final position of the bot
    bot.printCurrentPosition();
}

}
