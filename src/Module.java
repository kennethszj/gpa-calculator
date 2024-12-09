public class Module {
    private String name;
    private int credit;
    private String grade;

    // Constructor
    public Module(String name, int credit, String grade) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Convert grade to numeric value for GPA calculation
    public double getNumericGrade() {
        switch (grade.toUpperCase()) {
            case "A+": return 5.0;
            case "A": return 5.0;
            case "A-": return 4.5;
            case "B+": return 4.0;
            case "B": return 3.5;
            case "B-": return 3.0;
            case "C+": return 2.5;
            case "C": return 2.0;
            case "D+": return 1.5;
            case "D": return 1.0;
            case "F": return 0.0;
            case "S": return -1;
            default: return -1; // Invalid or unrecognized grade
        }
    }

    @Override
    public String toString() {
        return "Module{name='" + name + "', credit=" + credit + ", grade='" + grade + "'}";
    }
}
