package processo_seletivo_eloware;

public class Employee extends Person {
    double salary;
    String job;

    public Employee(String personName, String personDatebirth, double salary, String job) {
        super(personName, personDatebirth);
        setSalary(salary);
        setJob(job);
    }

    public void display() {
        System.out.println("Name " + getName());
        System.out.println("Date " + getDateBirth());
        System.out.println("Salary " + getSalary());
        System.out.println("Job " + getJob());

    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }
}
