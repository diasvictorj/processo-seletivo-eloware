package processo_seletivo_eloware;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    public String name;
    public String dateBirth;
    public LocalDate dateBirthConverted;

    public Person(String personName, String personDatebirth) {
        setName(personName);
        setDateBirth(personDatebirth);
    }

    public void setName(String personName) {
        this.name = personName;
    }

    public void setDateBirth(String personDateBirth) {
        this.dateBirth = personDateBirth;
    }

    public void setDateBirthConverted(String personDateBirth) {
        String[] employeeBDay = this.dateBirth.split("/");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(employeeBDay[0] + "-" + employeeBDay[1] + "-" + employeeBDay[2], formatter);
        this.dateBirthConverted = date;
        System.out.println(date);

    }

    public String getName() {
        return name;
    }

    public String getDateBirth() {
        return dateBirth;
    }
}
