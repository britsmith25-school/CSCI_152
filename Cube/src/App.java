import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initial state of the Rubik's Cube
        String[][] cube = {
            {"R", "R", "R", "R", "R", "R", "R", "R", "R"}, // Red face
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"}, // Blue face
            {"G", "G", "G", "G", "G", "G", "G", "G", "G"}, // Green face
            {"O", "O", "O", "O", "O", "O", "O", "O", "O"}, // Orange face
            {"W", "W", "W", "W", "W", "W", "W", "W", "W"}, // White face
            {"Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y"}  // Yellow face
        };

        // Loop until the user exits
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Print the cube layout
            printCube(cube);

            // Ask the user for input
            System.out.println("Welcome to the Rubik's Cube!");
            System.out.println("Please enter a move you would like to make. Options are: ");
            System.out.println("1. Rotate middle row left");
            System.out.println("2. Rotate middle row right");
            System.out.println("3. Rotate rows up");
            System.out.println("4. Rotate rows down");
            System.out.println("5. Rotate top row left");
            System.out.println("6. Rotate top row right");
            System.out.println("7. Rotate bottom row left");
            System.out.println("8. Rotate bottom row right");
            System.out.println("9. Check if a face is correct");
            System.out.println("10. Check if the cube is valid");
            System.out.println("11. Exit");

            // Get user input
            int choice = scanner.nextInt();

            // Process the choice
            switch (choice) {
                case 1:
                    rotateMiddleRowHorizontally(cube, true);
                    break;
                case 2:
                    rotateMiddleRowHorizontally(cube, false);
                    break;
                case 3:
                    rotateRowsUp(cube);
                    break;
                case 4:
                    rotateRowsDown(cube);
                    break;
                case 5:
                    rotateTopOrBottomRowHorizontally(cube, true, true); // Rotate top row left
                    break;
                case 6:
                    rotateTopOrBottomRowHorizontally(cube, false, true); // Rotate top row right
                    break;
                case 7:
                    rotateTopOrBottomRowHorizontally(cube, true, false); // Rotate bottom row left
                    break;
                case 8:
                    rotateTopOrBottomRowHorizontally(cube, false, false); // Rotate bottom row right
                    break;
                case 9:
                    System.out.println("Enter the face number you would like to check (0-5): ");
                    int face = scanner.nextInt();
                    checkFaceCorrectness(cube, face);
                    break;
                case 10:
                    checkCubeValidity(cube);
                    break;
                    case 11:
                    System.out.println("Thanks for playing! Exiting the program...");
                    scanner.close(); // Close the scanner
                    System.exit(0);
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Print the cube layout
    public static void printCube(String[][] cube) {
        for (int i = 0; i < 6; i++) {
            System.out.println("Face " + i + ":");
            for (int j = 0; j < 3; j++) {
                System.out.println(cube[i][j * 3] + " " + cube[i][j * 3 + 1] + " " + cube[i][j * 3 + 2]);
            }
            System.out.println();
        }
    }

    // Rotate the middle row of the cube horizontally (left or right)
    public static void rotateMiddleRowHorizontally(String[][] cube, boolean left) {
        String[] temp = new String[3];
        if (left) {
            System.arraycopy(cube[0], 3, temp, 0, 3); // Red face middle row to temp
            System.arraycopy(cube[2], 3, cube[0], 3, 3); // Green -> Red
            System.arraycopy(cube[3], 3, cube[2], 3, 3); // Orange -> Green
            System.arraycopy(cube[1], 3, cube[3], 3, 3); // Blue -> Orange
            System.arraycopy(temp, 0, cube[1], 3, 3);    // Temp (Red) -> Blue
        } else {
            System.arraycopy(cube[0], 3, temp, 0, 3); // Red face middle row to temp
            System.arraycopy(cube[1], 3, cube[0], 3, 3); // Blue -> Red
            System.arraycopy(cube[3], 3, cube[1], 3, 3); // Orange -> Blue
            System.arraycopy(cube[2], 3, cube[3], 3, 3); // Green -> Orange
            System.arraycopy(temp, 0, cube[2], 3, 3);    // Temp (Red) -> Green
        }
        System.out.println(left ? "Middle row rotated left" : "Middle row rotated right");
    }

    // Rotate the rows upwards
    public static void rotateRowsUp(String[][] cube) {
        String[] temp = new String[3];
        System.arraycopy(cube[4], 0, temp, 0, 3); // White face top row to temp
        System.arraycopy(cube[0], 0, cube[4], 0, 3); // Red -> White
        System.arraycopy(cube[1], 0, cube[0], 0, 3); // Blue -> Red
        System.arraycopy(cube[2], 0, cube[1], 0, 3); // Green -> Blue
        System.arraycopy(cube[3], 0, cube[2], 0, 3); // Orange -> Green
        System.arraycopy(temp, 0, cube[3], 0, 3); // Temp -> Orange
        System.out.println("Rows rotated upwards");
    }

    // Rotate the rows downwards
    public static void rotateRowsDown(String[][] cube) {
        String[] temp = new String[3];
        System.arraycopy(cube[5], 0, temp, 0, 3); // Yellow face bottom row to temp
        System.arraycopy(cube[3], 0, cube[5], 0, 3); // Orange -> Yellow
        System.arraycopy(cube[2], 0, cube[3], 0, 3); // Green -> Orange
        System.arraycopy(cube[1], 0, cube[2], 0, 3); // Blue -> Green
        System.arraycopy(cube[0], 0, cube[1], 0, 3); // Red -> Blue
        System.arraycopy(temp, 0, cube[0], 0, 3); // Temp -> Red
        System.out.println("Rows rotated downwards");
    }

    public static void rotateTopOrBottomRowHorizontally(String[][] cube, boolean left, boolean isTop) {
        String[] temp = new String[3];
        int rowIndex = isTop ? 0 : 6; // Top row starts at index 0-2, bottom row starts at index 6-8
    
        if (left) {
            System.arraycopy(cube[0], rowIndex, temp, 0, 3); // Red face row to temp
            System.arraycopy(cube[2], rowIndex, cube[0], rowIndex, 3); // Green -> Red
            System.arraycopy(cube[3], rowIndex, cube[2], rowIndex, 3); // Orange -> Green
            System.arraycopy(cube[1], rowIndex, cube[3], rowIndex, 3); // Blue -> Orange
            System.arraycopy(temp, 0, cube[1], rowIndex, 3); // Temp (Red) -> Blue
        } else {
            System.arraycopy(cube[0], rowIndex, temp, 0, 3); // Red face row to temp
            System.arraycopy(cube[1], rowIndex, cube[0], rowIndex, 3); // Blue -> Red
            System.arraycopy(cube[3], rowIndex, cube[1], rowIndex, 3); // Orange -> Blue
            System.arraycopy(cube[2], rowIndex, cube[3], rowIndex, 3); // Green -> Orange
            System.arraycopy(temp, 0, cube[2], rowIndex, 3); // Temp (Red) -> Green
        }
    
        System.out.println(left ? 
            (isTop ? "Top row rotated left" : "Bottom row rotated left") : 
            (isTop ? "Top row rotated right" : "Bottom row rotated right"));
    }
    // Check if all squares on a face are the same color
public static void checkFaceCorrectness(String[][] cube, int face) {
    boolean correct = true;
    String color = cube[face][0];
    for (int i = 1; i < 9; i++) {
        if (!cube[face][i].equals(color)) {
            correct = false;
            break;
        }
    }
    System.out.println(correct ? "Face " + face + " is correct." : "Face " + face + " is not correct.");
}

// Check if the cube is valid (each face has 9 squares of the same color)
public static void checkCubeValidity(String[][] cube) {
    boolean valid = true;
    for (int i = 0; i < 6; i++) {
        String color = cube[i][0];
        for (int j = 1; j < 9; j++) {
            if (!cube[i][j].equals(color)) {
                valid = false;
                break;
            }
        }
    }
    System.out.println(valid ? "The cube is valid." : "The cube is not valid.");
}
}


