import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ModuleManager manager = new ModuleManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Module Management ===");
            System.out.println("1. Add Module");
            System.out.println("2. Delete Module");
            System.out.println("3. List Modules (and Calculate GPA)");
            System.out.println("4. Edit Module");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            String choice;
            try {
                choice = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice.toLowerCase()) {
                case "add":
                    addModule(manager, scanner);
                    break;
                case "delete":
                    deleteModule(manager, scanner);
                    break;
                case "list":
                    manager.listModules();
                    break;
                case "edit":
                    editModule(manager, scanner);
                    break;
                case "exit":
                    System.out.println("bye");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addModule(ModuleManager manager, Scanner scanner) {
        System.out.print("Enter module name: ");
        String name = scanner.nextLine();

        int credit = getPositiveInt(scanner, "Enter module credit (positive integer): ");
        String grade = getValidGrade(scanner, "Enter module grade (0.0 - 5.0 or S): ");

        Module module = new Module(name, credit, grade);
        manager.addModule(module);
    }

    private static void deleteModule(ModuleManager manager, Scanner scanner) {
        System.out.print("Enter the index of the module to delete: ");
        int index = getPositiveInt(scanner, "Index must be a positive integer: ");
        if (index >= 0) {
            manager.deleteModule(index);
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }


    private static int getPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    private static String getValidGrade(Scanner scanner, String prompt) {
        String[] validGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D+", "D", "F", "S"};
        while (true) {
            System.out.print(prompt);
            String grade = scanner.nextLine().toUpperCase();
            for (String validGrade : validGrades) {
                if (grade.equals(validGrade)) {
                    return grade;
                }
            }
            System.out.println("Invalid grade. Please enter a valid grade (A+, A, A-, B+, etc.).");
        }
    }

    private static void editModule(ModuleManager manager, Scanner scanner) {
        System.out.print("Enter the index of the module to edit (starting from 1): ");
        int index = getPositiveInt(scanner, "Index must be a positive integer: ");

        // Prompt for the new module name
        System.out.print("Enter new module name (or press Enter to keep unchanged): ");
        String newName = scanner.nextLine();

        // Prompt for the new module credit
        System.out.print("Enter new module credit (or press Enter to keep unchanged): ");
        String creditInput = scanner.nextLine(); // Read input as a string
        Integer newCredit = null; // Use null to represent unchanged credit
        if (!creditInput.isEmpty()) {
            try {
                newCredit = Integer.parseInt(creditInput); // Parse credit if provided
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Credit will remain unchanged.");
            }
        }

        // Prompt for the new module grade
        System.out.print("Enter new module grade (or press Enter to keep unchanged): ");
        String newGrade = scanner.nextLine();

        // Call the editModule method with validated inputs
        if (!manager.editModule(index, newName.isEmpty() ? null : newName, newCredit == null ? 0 : newCredit, newGrade.isEmpty() ? null : newGrade)) {
            System.out.println("Module edit failed.");
        }
    }



}
