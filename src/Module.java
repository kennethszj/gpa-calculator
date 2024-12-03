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
        if (grade.equalsIgnoreCase("S")) {
            return -1; // Special marker for "S" grades
        }
        return Double.parseDouble(grade);
    }

    // toString Method
    @Override
    public String toString() {
        return "Module{name='" + name + "', credit=" + credit + ", grade='" + grade + "'}";
    }
}
