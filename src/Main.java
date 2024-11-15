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
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addModule(manager, scanner);
                    break;
                case 2:
                    deleteModule(manager, scanner);
                    break;
                case 3:
                    manager.listModules();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper to add a module
    private static void addModule(ModuleManager manager, Scanner scanner) {
        System.out.print("Enter module name: ");
        String name = scanner.nextLine();

        int credit = getPositiveInt(scanner, "Enter module credit (positive integer): ");
        String grade = getValidGrade(scanner, "Enter module grade (0.0 - 5.0 or S): ");

        Module module = new Module(name, credit, grade);
        manager.addModule(module);
    }

    // Helper to delete a module
    private static void deleteModule(ModuleManager manager, Scanner scanner) {
        System.out.print("Enter the name of the module to delete: ");
        String name = scanner.nextLine();
        manager.deleteModule(name);
    }

    // Get a positive integer with validation
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

    // Get a valid grade between 0.0 and 5.0 or S
    private static String getValidGrade(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String grade = scanner.nextLine();
            if (grade.equalsIgnoreCase("S")) {
                return "S";
            }
            try {
                double numericGrade = Double.parseDouble(grade);
                if (numericGrade >= 0.0 && numericGrade <= 5.0) {
                    return grade;
                } else {
                    System.out.println("Grade must be between 0.0 and 5.0, or 'S'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid grade.");
            }
        }
    }
}
