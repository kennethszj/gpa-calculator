import java.io.*;
import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<>();
    private static final String FILE_NAME = "modules.txt";

    // Constructor: Load data from file
    public ModuleManager() {
        loadFromFile();
    }

    // Add a module
    public void addModule(Module module) {
        modules.add(module);
        System.out.println("Module added successfully.");
        saveToFile(); // Save changes
    }

    // Delete a module by name
    public boolean deleteModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                modules.remove(module);
                System.out.println("Module deleted successfully.");
                saveToFile(); // Save changes
                return true;
            }
        }
        System.out.println("Module not found.");
        return false;
    }

    // List all modules and calculate GPA
    public void listModules() {
        if (modules.isEmpty()) {
            System.out.println("No modules available.");
            return;
        }

        double totalGradePoints = 0;
        int totalCredits = 0;

        System.out.println("\nList of Modules:");
        for (Module module : modules) {
            System.out.println("Name: " + module.getName() +
                    ", Credit: " + module.getCredit() +
                    ", Grade: " + module.getGrade());

            double numericGrade = module.getNumericGrade();
            if (numericGrade >= 0) { // Exclude "S" grades from GPA
                totalGradePoints += numericGrade * module.getCredit();
                totalCredits += module.getCredit();
            }
        }

        // Calculate and display GPA
        if (totalCredits > 0) {
            double gpa = totalGradePoints / totalCredits;
            System.out.printf("Final GPA: %.2f%n", gpa);
        } else {
            System.out.println("No grades contribute to the GPA calculation.");
        }
    }

    // Save modules to a file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Module module : modules) {
                writer.write(module.getName() + "," + module.getCredit() + "," + module.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load modules from a file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No file to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int credit = Integer.parseInt(parts[1]);
                    String grade = parts[2];
                    modules.add(new Module(name, credit, grade));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
