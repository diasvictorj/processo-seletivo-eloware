package processo_seletivo_eloware;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    Employee currentEmployee;
    ArrayList<Employee> employeesList = new ArrayList<>();

    public static void main(String args[]) {
        Employee Maria = new Employee("Maria", "18/10/2000", 2009.44, "Operador");
        Employee Joao = new Employee("João", "12/05/1990", 2284.38, "Operador");
        Employee Caio = new Employee("Caio", "02/05/1961", 9836.14, "Coordenador");
        Employee Miguel = new Employee("Miguel", "14/10/1988", 19119.88, "Diretor");
        Employee Alice = new Employee("Alice", "05/01/1995", 2234.68, "Recepcionista");
        Employee Heitor = new Employee("Heitor", "19/11/1999", 1582.72, "Operador");
        Employee Arthur = new Employee("Arthur", "31/03/1993", 4071.84, "Contador");
        Employee Laura = new Employee("Laura", "08/07/1994", 3017.45, "Gerente");
        Employee Heloisa = new Employee("Heloísa", "08/07/1994", 1606.85, "Eletricista");
        Employee Helena = new Employee("Helena", "02/09/1996", 2799.93, "Gerente");

        Employee[] employeeArray = { Maria, Joao, Caio, Miguel, Alice, Heitor, Arthur, Laura, Heloisa, Helena };
        // 3 – Deve conter uma classe Principal para executar as seguintes ações:
        Main main = new Main();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
        // acima.
        main.insert(employeeArray);

        // 3.2 – Remover o funcionário “João” da lista.
        main.remove("João");

        /*
         * 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
         * • informação de data deve ser exibido no formato dd/mm/aaaa;
         * • informação de valor numérico deve ser exibida no formatado com separador de
         * milhar como ponto e decimal como vírgula.
         */
        main.displayFullList();

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista
        // de funcionários com novo valor.
        main.updateAllSalary(10); // teste com outros valores!

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função”
        // e o valor a “lista de funcionários”.
        // 3.6 – Imprimir os funcionários, agrupados por função.
        // main.displayByJob(); Infelizmente, não consegui fazer essa. Tive dificuldade
        // em manipular o array de valores com os nomes dentro do HashMap. A função está
        // funcionando corretamente no meu projeto em TypeScript.

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        int[] monthsToSearch = { 10, 12, 11 };
        main.searchMonthBday(monthsToSearch);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e
        // idade.
        main.displayOldest();

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        main.displayAlphabeticalOrder();

        // 3.11 – Imprimir o total dos salários dos funcionários.
        main.salarySum();

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando
        // que o salário mínimo é R$1212.00.
        main.minimunWages(1212); // teste com outros valores!

    };

    public void insert(Employee[] list) {
        for (Employee e : list) {
            this.currentEmployee = e;
            this.employeesList.add(this.currentEmployee);
            System.out.println(
                    "Funcionario Inserido " + this.currentEmployee.name + " Cargo: " + this.currentEmployee.job);
        }
    }

    public void displayFullList() {
        for (Employee e : this.employeesList) {
            System.out.println(
                    "Nome: " + e.name + "    " + "Cargo: " + e.job + "    " + "Salario: " + e.salary
                            + "    " + "Data de Nascimento: " + e.dateBirth);
        }
        System.out.println("");
    }

    public void displayAlphabeticalOrder() {

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Employee> employeesListSorted = new ArrayList<>();

        for (Employee employee : this.employeesList) {
            nameList.add(employee.name);
        }
        Object[] sortedNames = nameList.toArray();
        Arrays.sort(sortedNames);

        for (Object e : sortedNames) {
            for (Employee f : this.employeesList)
                if (e.equals(f.name))
                    employeesListSorted.add(f);
        }
        for (Employee e : employeesListSorted) {
            System.out.println(
                    "Nome: " + e.name + "    " + "Cargo: " + e.job + "    " + "Salario: " + e.salary
                            + "    " + "Data de Nascimento: " + e.dateBirth);
        }
        System.out.println("");
    }

    public void displayByJob() {

        Map<String, String[]> employeeMap = new HashMap<>();

        for (Employee employee : this.employeesList) {
            String currentJob = employee.job;
            String currentName = employee.name;
            String[] currentNameInArray = { currentName };

            if (!employeeMap.containsKey(currentJob)) {
                employeeMap.put(currentJob, currentNameInArray);
                break;
            }
            String[] nameList = employeeMap.get(currentJob);
            for (String e : nameList) {
                System.out.println(e);
            }
            System.out.println("");

        }
        // INCOMPLETO - ANOTAÇÔES DO PROJETO EM TYPESCRIPT PARA REFERENCIA
        /*
         * const employeeMap = new Map<string, string[]>();
         * this.employeeList.forEach((e) => {
         * if (!employeeMap.has(e["job"])) {
         * employeeMap.set(e["job"], []);
         * }
         * const namesOnTheMap = employeeMap.get(e["job"]);
         * if (namesOnTheMap) {
         * namesOnTheMap.push(e["name"]);
         * employeeMap.set(e["job"], [...namesOnTheMap]);
         * }
         * });
         * console.log("Lista de Empregados por Função ");
         * for (let [key, value] of employeeMap) {
         * console.log(key, value);
         * }
         * }
         */
    }

    public void displayOldest() {

        int age = 0;
        String nameOfTheFossil = "";
        for (Employee employee : this.employeesList) {
            String[] employeeBDay = employee.dateBirth.split("/");

            int employeeAge = 2022 - Integer.valueOf(employeeBDay[2]);

            if (employeeAge > age) {
                age = employeeAge;
                nameOfTheFossil = employee.name;
            }
        }
        System.out.println("O funcionário mais velho é:  " + nameOfTheFossil + " com " + age + " anos");
    }

    public void salarySum() {
        double total = 0;
        for (Employee employee : this.employeesList) {
            total = employee.salary + total;
        }
        System.out.println("Soma total de salários de todos os funcionarios " + total);

    }

    public void updateAllSalary(double update_percent) {
        for (Employee employee : this.employeesList) {
            double currentSalary = employee.salary;
            double newSalary = employee.salary + (employee.salary * (update_percent / 100));
            BigDecimal newSalaryFixed = new BigDecimal(newSalary).setScale(2, RoundingMode.HALF_UP);
            employee.salary = newSalaryFixed.doubleValue();
            System.out
                    .println("Salários de  " + employee.name + " Ajustado de: " + currentSalary + " para: "
                            + employee.salary);

        }
    }

    public void minimunWages(double currentWage) {
        Map<String, Double> employeeMap = new HashMap<>();

        for (Employee employee : this.employeesList) {

            double numberOfMinWages = (employee.salary / currentWage);
            BigDecimal numberOfFixed = new BigDecimal(numberOfMinWages).setScale(2, RoundingMode.HALF_UP);
            double output = numberOfFixed.intValue();
            String currentName = employee.name;

            employeeMap.put(currentName, output);
        }
        for (String employee : employeeMap.keySet()) {
            System.out.println(
                    "Funcionário: " + employee + " recebe: " + employeeMap.get(employee) + " salário(s) minimo(s) ");
        }
        System.out.println("Valor do salário minimo considerado: " + currentWage);

    }

    public void searchMonthBday(int[] monthArray) {

        for (Employee employee : this.employeesList) {
            String[] employeeBDay = employee.dateBirth.split("/");

            int employeeMonth = Integer.valueOf(employeeBDay[1]);

            for (int month : monthArray) {
                if (employeeMonth == month) {
                    System.out.println(
                            "Funcionário: " + employee.name + " faz aniversário no mês : " + month);
                }
            }

        }

    }

    public void remove(String name) {
        for (Employee employee : this.employeesList) {
            if (employee.getName().equals(name)) {
                this.employeesList.remove(employee);
                System.out.println("Funcionario Removido " + name);
                break;
            }
        }
        displayFullList();
    }
}
