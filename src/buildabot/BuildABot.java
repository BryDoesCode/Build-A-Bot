/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildabot;

import java.util.Scanner;

/**
 *
 * @author Bry
 */
public class BuildABot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Variables -------------------------------------------------------

        // Define size of robot printing area. Default is 25
        final int AREASIZE = 25;

        // Store information about the length and width of the various body
        // parts. Initialize to make a default robot.
        int headWidth = 9, headLength = 5, neckLength = 2,
                bodyLength = 10, bodyWidth = 9,
                legLength = 6, feetWidth = 5;

        // Store information about the characters used to make the various 
        // body parts and facial features. Initialize to make a default robot.
        char headTopBottomChar = '-', headSideChar = '|', headCornerChar = '*',
                eyeChar = 'o', noseChar = '<', mouthChar = '-',
                neckChar = '|', bodyChar = '#', legChar = '|', feetChar = '=';

        // Store robot name.
        String robotName = "Robot";

        // Create variables for looping
        String createNewRobot = "yes";
        String createCustomRobot = "yes";

        // Initialize Scanner for user input.
        Scanner keyboard = new Scanner(System.in);

        // Begin Main Program ------------------------------------------------
        printHeader("Welcome to the Custom Robot Creation Factory!");

        // Check if variable createNewRobot is "yes" or "y"
        while (createNewRobot.equalsIgnoreCase("yes") || createNewRobot.equalsIgnoreCase("y")) {

            // Ask user if they'd like to create a custom robot. 
            // Otherwise, a default will be used. 
            System.out.print("Would you like to create a custom robot?"
                    + "\n(If no, a default will be created for you.)"
                    + "\nYes / No: ");
            createCustomRobot = keyboard.nextLine();

            // Checks if user wanted to make a custom robot.
            if (createCustomRobot.equalsIgnoreCase("yes") || createCustomRobot.equalsIgnoreCase("y")) {

                // Begin getting input for custom robot attributes.
                printHeader("Design Head");
                headWidth = getUserInputSize("width", "head");
                headLength = getUserInputSize("length", "head");
                headTopBottomChar = getUserInputCharacter("head (top and bottom)");
                headSideChar = getUserInputCharacter("head (sides)");
                headCornerChar = getUserInputCharacter("head (corners)");

                printHeader("Design Face");
                eyeChar = getUserInputCharacter("eyes");
                noseChar = getUserInputCharacter("nose");
                mouthChar = getUserInputCharacter("mouth");

                printHeader("Design Neck");
                neckLength = getUserInputSize("length", "neck");
                neckChar = getUserInputCharacter("neck");

                printHeader("Design Body");
                bodyLength = getUserInputSize("length", "body");
                bodyWidth = getUserInputSize("width", "body");
                bodyChar = getUserInputCharacter("body");

                printHeader("Design Legs");
                legLength = getUserInputSize("length", "legs");
                legChar = getUserInputCharacter("legs");

                printHeader("Design Feet");
                feetWidth = getUserInputSize("width", "feet");
                feetChar = getUserInputCharacter("feet");
            }

            // Prompt user for a name for their robot. 
            printHeader("Name Robot");
            System.out.print("\nPlease enter a name for your robot: ");
            robotName = keyboard.nextLine();

            // Begin Creation of Robot
            printHeader("Welcome " + robotName + "!");
            printNewLine();
            head(headWidth, headLength, headTopBottomChar, headSideChar, headCornerChar, eyeChar, noseChar, mouthChar, AREASIZE);
            neck(neckLength, neckChar);
            body(bodyLength, bodyWidth, bodyChar, AREASIZE);
            legs(legLength, legChar);
            feet(feetWidth, feetChar, AREASIZE);

            // Ask user if they'd like to create another robot. 
            printHeader("Again?");
            System.out.print("Would you like to create another robot?"
                    + "\n(Yes / No): ");
            createNewRobot = keyboard.nextLine();

            // Check if user wanted to play again
            if (createNewRobot.equalsIgnoreCase("yes") || createNewRobot.equalsIgnoreCase("y")) {
                // Add in a header to inform user they are designing a new robot.
                printHeader("New Robot");
                // Also reset variables to their defaults
                headWidth = 9;
                headLength = 5;
                neckLength = 2;
                bodyLength = 10;
                bodyWidth = 9;
                legLength = 6;
                feetWidth = 5;
                headTopBottomChar = '-';
                headSideChar = '|';
                headCornerChar = '*';
                eyeChar = 'o';
                noseChar = '<';
                mouthChar = '-';
                neckChar = '|';
                bodyChar = '#';
                legChar = '|';
                feetChar = '=';
            }
        }
        // If user declined to play again, print a goodbye message
        printHeader("Goodbye");
        System.out.println("Thank you, come again!");

    }

    public static boolean validateSize(int size, String sizeType, String location) {
        // Takes an input of size and validates it according to defined
        // parameters. Robots cannot have a negative width/length.
        // Returns true or false depending on validation.

        // Validates differently depending on the type of size being validated.
        if (size <= 0) {
            // Checks for negatives
            System.out.printf("Please input a positive %s. \n", sizeType);
            return false;
        } else if (size < 3 && sizeType.equalsIgnoreCase("length") && location.equalsIgnoreCase("head")) {
            // Makes sure the head isn't too small to fit the face. 
            System.out.print("Our robots cannot have a head shorter than 3"
                    + " characters. \nPlease enter a length greater than 3.\n");
            return false;

        } else if (size < 7 && sizeType.equalsIgnoreCase("width") && location.equalsIgnoreCase("head")) {
            // Makes sure the head isn't too small to fit the face. 
            System.out.print("Our robots cannot have a head smaller than 7"
                    + " characters. \nPlease enter a width greater than 7.\n");
            return false;

        } else if (size > 10 && sizeType.equalsIgnoreCase("width") && location.equalsIgnoreCase("feet")) {
            // Makes sure feet aren't so wide they mess up alignment.
            System.out.print("Our robots cannot have a foot wider than 10"
                    + " characters. \nPlease enter a width less than 10.\n");
            return false;
        } else if (size > 25 && sizeType.equalsIgnoreCase("width")) {
            // Keeps robots less than 25 characters wide, for formatting.
            System.out.print("Our robots cannot be wider than 25"
                    + " characters. \nPlease enter a width less than 25.\n");
            return false;
        } else if (size > 30 && sizeType.equalsIgnoreCase("length")) {
            // Keeps robots pieces less than 30 characters long, for readability.
            System.out.print("Our robots cannot be longer than 30"
                    + " characters. \nPlease enter a length less than 30.\n");
            return false;

        } else {
            return true;
        }
    }

    public static int getUserInputSize(String sizeType, String location) {
        // Gets user input for the size of an object and returns it as an int.
        // Uses sizeValidation to validate the size input. 

        // Create variables to hold the size and whether it's validated. 
        int size = 0;
        boolean validation = false;

        // Create keyboard for user input
        Scanner keyboard = new Scanner(System.in);

        // Ask for user input while it is not validated.
        while (validation == false) {
            System.out.printf("Please enter the %s of the %s: ", sizeType, location);
            // Checks if the user has not entered an integer.
            while (!keyboard.hasNextInt()) {
                System.out.println("Please enter a positive, whole number.");
                // Takes away any non-integer characters the user has entered.
                keyboard.nextLine();
            }
            // Sets size to the integer entered by the user. 
            size = keyboard.nextInt();
            // Check if the input validates
            validation = validateSize(size, sizeType, location);
        }

        // When the input validates and the loop terminates, return the size
        return size;
    }

    public static char getUserInputCharacter(String location) {
        // Gets user input for the character used in creating a piece of the
        // robot. Returns a char. 

        // Variable to validate that a character was inputted
        boolean charInput = false;

        // Variable to hold the user input String and the returned character.
        String userInput = " ";
        char character;

        // Create keyboard for user input
        Scanner keyboard = new Scanner(System.in);

        // Prompt for user input
        while (!charInput) {
            System.out.printf("Please enter the character to use for the robot's %s: ", location);
            userInput = keyboard.nextLine();
            // Checks if the user entered an empty string.
            if (userInput.isEmpty()) {
                System.out.println("Please enter a character.");
                charInput = false;
            } else {
                charInput = true;
            }
        }

        // Get the first character of the user input. 
        character = userInput.charAt(0);

        // Return the character
        return character;

    }

    public static void printHeader(String title) {
        // Prints a formatted header with a given title. 
        printNewLine();
        printNewLine();
        System.out.println(title);
        System.out.println("---------------------------------------------");
    }

    public static void printNewLine() {
        // Prints an empty line. For formatting. 
        System.out.println("");
    }

    public static String calculateSpacing(int width, int area) {
        // Calculate necessary white spaces to center robot.
        // Returns a String of white spaces. 
        // Robot will be centered at 13.

        // Create variable to hold number of spaces needed. 
        int numSpaces;
        String spaces = "";

        // Calculates the spaces needed. 
        numSpaces = ((area - width) / 2);

        // Create String of empty spaces
        for (int i = 0; i < numSpaces; i++) {
            spaces += " ";
        }
        return spaces;
    }

    public static void head(int width, int length, char topBottomChar, char sideChar, char cornerChar, char eyeChar, char noseChar, char mouthChar, int area) {
        // Prints the head of the robot based on the length and width requested.
        // Uses a variety of given characters to print with. 

        // Create strings to hold different portions of the head
        String topBottomLine = "", middleLine = "",
                eyeLine = "", noseLine = "", mouthLine = "";

        // Create string of empty spaces based on width.        
        String spaces = calculateSpacing(width, area);

        // Adjusts width to account for edge characters on the head, 2 per line.
        width -= 2;

        // Begin to build the top and bottom line of the head 
        // Add the leading spaces
        topBottomLine += spaces;
        // Add first corner
        topBottomLine += cornerChar;
        // Loop through width
        for (int wid = 0; wid < width; wid++) {
            topBottomLine += topBottomChar;
        }
        // Add final corner
        topBottomLine += cornerChar;

        // Begin to build the middle line of the head 
        // Add the leading spaces
        middleLine += spaces;
        // Add first side
        middleLine += sideChar;
        // Loop through width
        for (int wid = 0; wid < width; wid++) {
            middleLine += " ";
        }
        // Add final side
        middleLine += sideChar;

        // Begin to build the eye line of the head
        // Add the leading spaces
        eyeLine += spaces;
        // Add first side
        eyeLine += sideChar;
        // Center eyes, assuming the eyes take up 5 characters, example: "o   o"
        // Uses the width of the head as the area to calculate from.
        eyeLine += calculateSpacing(5, width);
        // Add eye characters and spacing
        eyeLine += eyeChar + "   " + eyeChar;
        // Add the ending spaces
        eyeLine += calculateSpacing(5, width);
        // Add final side
        eyeLine += sideChar;

        // Begin to build the nose line of the head
        // Add the leading spaces
        noseLine += spaces;
        // Add first side
        noseLine += sideChar;
        // Center nose, assuming nose is 1 character wide.
        // Uses the width of the head as the area to calculate from.
        noseLine += calculateSpacing(1, width);
        // Add nose character
        noseLine += noseChar;
        // Add the ending spaces
        noseLine += calculateSpacing(1, width);
        // Add final side
        noseLine += sideChar;

        // Begin to build the mouth line of the head
        // Add the leading spaces
        mouthLine += spaces;
        // Add first side
        mouthLine += sideChar;
        // Center mouth, assuming the mouth takes up 5 characters, example: "_____"
        // Uses the width of the head as the area to calculate from.
        mouthLine += calculateSpacing(5, width);
        // Add mouth characters
        for (int i = 0; i < 5; i++) {
            mouthLine += mouthChar;
        }
        // Add the ending spaces
        mouthLine += calculateSpacing(5, width);
        // Add final side
        mouthLine += sideChar;

        // Begin printing the head
        // First, adjust length to account for the face and top/bottom of head.
        // Assumes face takes up 4 lines, while the top/bottom take 1 each.
        // Subtract 6 lines from original length
        length -= 6;
        // Print the top line
        System.out.println(topBottomLine);
        // Print the majority of the head length
        for (int len = 0; len < length; len++) {
            System.out.println(middleLine);
        }
        // Print the face lines
        System.out.println(eyeLine);
        System.out.println(noseLine);
        System.out.println(mouthLine);
        // Print the bottom line
        System.out.println(topBottomLine);

    }

    public static void neck(int length, char character) {
        // Prints the neck of the robot based on the length requested.
        // Uses a given character to print with. 

        // Creates string of empty characters to align each leg
        String spaces = "            ";

        // Begin loop to print the neck up to the amount of length
        for (int len = 0; len < length; len++) {
            System.out.println(spaces + character);
        }
    }

    public static void body(int length, int width, char character, int area) {
        // Prints the body of the robot based on the length and width requested.
        // Uses a given character to print with. 

        // Create string of empty spaces based on width.        
        String spaces = calculateSpacing(width, area);

        // Begin outer loop to print a new line up to the amount of length
        for (int len = 0; len < length; len++) {
            System.out.print(spaces);
            // Begin inner loop to print characters for each line
            for (int wid = 0; wid < width; wid++) {
                System.out.print(character);
            }
            printNewLine();
        }
    }

    public static void legs(int length, char character) {
        // Prints the legs of the robot based on the length requested.
        // Uses a given character to print with. 

        // Creates string of empty characters to align each leg
        String spaces = "          ";

        // Begin loop to print each leg up to the amount of length
        for (int len = 0; len < length; len++) {
            System.out.println(spaces + character + "   " + character);
        }
    }

    public static void feet(int width, char character, int area) {
        // Prints the feet of the robot based on the width requested.
        // Uses a given character to print with. 

        // Create string to hold foot information
        String foot = "";

        // Create string of empty spaces based on width.
        // Account for there being 2 feet and 3 spaces between them. 
        String spaces = calculateSpacing((width * 2) + 3, area);

        // Begin loop to build each foot up to the requested width
        for (int wid = 0; wid < width; wid++) {
            foot += character;
        }
        System.out.println(spaces + foot + "   " + foot);
    
    }
    
}
