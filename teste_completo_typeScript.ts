interface IPerson {
  name: string;
  dateBirth: string;
}

class Pessoa implements IPerson {
  name: string;
  dateBirth: string;

  constructor(name: string, dateBirth: string) {
    (this.name = name), (this.dateBirth = dateBirth);
  }
}

interface IFunc {
  salary: number;
  job: string;
}

class Funcionario extends Pessoa implements IFunc {
  salary: number;
  job: string;

  constructor(name: string, dateBirth: string, salary: number, job: string) {
    super(name, dateBirth);
    (this.salary = salary), (this.job = job);
  }
}

interface IEmployee {
  name: string;
  dateBirth: string;
  salary: number;
  job: string;
}

class Main {
  employee: IEmployee;
  employeeList: IEmployee[];

  constructor() {
    (this.employee = { name: "", dateBirth: "", salary: 0, job: "" }),
      (this.employeeList = []);
  }

  insert(employeeToInsert: any): void {
    employeeToInsert.forEach((e: any) => {
      this.employee = new Funcionario(
        e["name"],
        e["dateBirth"],
        e["salary"],
        e["job"]
      );
      this.employeeList = [...this.employeeList, this.employee];
      console.log(
        "Empregado(a) " + this.employee["name"] + " Adcionado na lista"
      );
    });
  }

  remove(query: { [key: string]: string }) {
    const queryKeys: string[] = Object.keys(query);
    const queryLength = queryKeys.length;

    if (queryLength === 0 || queryLength > 1) {
      return console.log("Query Invalida ");
    }

    if (queryKeys[0] === "name") {
      const newList: any = this.employeeList.filter((e) => {
        if (!(e["name"] === query["name"])) {
          return e;
        }
      });
      this.employeeList = newList;
      console.log("Funcionario " + query["name"] + " removido da lista");
    }
  }
  display(query: { [key: string]: string | number }) {
    const queryKeys: string[] = Object.keys(query);
    const queryLength = queryKeys.length;

    if (queryLength === 0 || queryLength > 1) {
      return console.log("Query Invalida");
    }

    if (query["search"] === "all") return console.log(this.employeeList);
    if (query["search"] === "alphabetical") {
      const nameList: string[] = this.employeeList.map((e) => e["name"]).sort();
      const sortedEmployees = nameList.map((e) => {
        const [employee] = this.employeeList.filter((f) => {
          if (f["name"] === e) return f;
        });
        return employee;
      });
      console.log(sortedEmployees);
    }
    if (query["search"] === "job") {
      const employeeMap = new Map<string, string[]>();
      this.employeeList.forEach((e) => {
        if (!employeeMap.has(e["job"])) {
          employeeMap.set(e["job"], []);
        }
        const namesOnTheMap = employeeMap.get(e["job"]);
        if (namesOnTheMap) {
          namesOnTheMap.push(e["name"]);
          employeeMap.set(e["job"], [...namesOnTheMap]);
        }
      });
      console.log("Lista de Empregados por Fun????o ");
      for (let [key, value] of employeeMap) {
        console.log(key, value);
      }
    }

    if (query["search"] === "oldest") {
      let age: number = 0;

      const process = this.employeeList.filter((e) => {
        const employeeAge = 2022 - Number(e["dateBirth"].slice(6));
        if (employeeAge > age) {
          age = employeeAge;
          return e;
        }
      });
      const oldest = process.pop();
      if (oldest) {
        return console.log(
          "Funcionario mais velho ?? " + oldest["name"] + " com " + age + " anos"
        );
      }
    }
  }
  salaryAll(query: { [key: string]: { [key: string]: number | string } }) {
    const queryKeys: string[] = Object.keys(query["operation"]);
    const queryLength = queryKeys.length;

    if (queryLength === 0 || queryLength > 1) {
      return console.log("Query Invalida");
    }
    if (queryKeys[0] === "salary_sum") {
      const total: number = this.employeeList
        .map((e) => e["salary"])
        .reduce((previousValue, currentValue) => previousValue + currentValue);
      return console.log(
        "Soma total de sal??rios de todos os funcionarios " + total.toFixed(2)
      );
    }

    if (queryKeys[0] === "update_percent") {
      const { update_percent } = query["operation"];
      const updatedList: IEmployee[] = this.employeeList.map((e) => {
        e["salary"] += e["salary"] * (Number(update_percent) / 100);
        e["salary"] = Number(e["salary"].toFixed(2));
        return e;
      });
      this.employeeList = updatedList;
      return console.log(
        "Salario ajustados em " +
          update_percent +
          "%" +
          "\n" +
          this.employeeList
      );
    }

    if (queryKeys[0] === "how_many_wages") {
      const { how_many_wages } = query["operation"];
      const employeeMap = new Map<string, number>();
      this.employeeList.forEach((e) => {
        if (!employeeMap.has(e["name"])) {
          const howMany = Math.floor(e["salary"] / Number(how_many_wages));
          employeeMap.set(e["name"], howMany);
        }
      });
      console.log("Rela????o Empregados x Salario inserido:  " + how_many_wages);
      for (let [key, value] of employeeMap) {
        console.log(key, value);
      }
    }
  }

  searchMothBirth(query: { [key: string]: number[] }) {
    const invalidQuery = query["months"].find((e) => e < 1 || e > 12);

    if (invalidQuery) {
      return console.log("Query invalida, insira numero entre 1 a 12");
    }
    const employees = this.employeeList.filter((e) => {
      const employeeMonthBirth = Number(e["dateBirth"].slice(3, 5));
      const findInQuery = query["months"].find((e) => e == employeeMonthBirth);
      if (findInQuery) {
        return e;
      }
    });
    return console.log(employees);
  }
}

const table = [
  {
    name: "Maria",
    dateBirth: "18/12/2000",
    salary: 2500.44,
    job: "Operador",
  },
  {
    name: "Jo??o",
    dateBirth: "12/02/1990",
    salary: 1212.44,
    job: "Dibrador",
  },
  {
    name: "Caio",
    dateBirth: "02/03/1961",
    salary: 2284.38,
    job: "Coordenador",
  },
  {
    name: "Augusto",
    dateBirth: "02/04/1961",
    salary: 2284.38,
    job: "Coordenador",
  },
  {
    name: "Cazalb??",
    dateBirth: "02/05/1931",
    salary: 2284.38,
    job: "Coordenador",
  },
  {
    name: "Miguel",
    dateBirth: "14/06/1988",
    salary: 24000.14,
    job: "Dobrador",
  },
  {
    name: "Alice",
    dateBirth: "05/10/1995",
    salary: 10002.44,
    job: "Mega Dibradora",
  },
];
//3 ??? Deve conter uma classe Principal para executar as seguintes a????es:
const test = new Main();
//3.1 Inserir todos os funcionarios na ordem e informa????es da tabela
test.insert(table);
//3.2 ??? Remover o funcion??rio ???Jo??o??? da lista.
//test.remove({ name: "Jo??o" });
/* 3.3 ??? Imprimir todos os funcion??rios com todas suas informa????es, sendo que:
    ??? informa????o de data deve ser exibido no formato dd/mm/aaaa;
    ??? informa????o de valor num??rico deve ser exibida no formatado com separador de milhar como ponto e decimal como v??rgula. */
//test.display({ search: "all" });
/* 3.4 ??? Os funcion??rios receberam 10% de aumento de sal??rio, atualizar a lista de funcion??rios com novo valor. */
//test.salaryAll({ operation: { update_percent: 10 } }); //Tente tamb??m outros valores (negativos)!

/* 3.5 ??? Agrupar os funcion??rios por fun????o em um MAP, sendo a chave a ???fun????o??? e o valor a ???lista de funcion??rios???.
    3.6 ??? Imprimir os funcion??rios, agrupados por fun????o. */
//Tive dificuldade em entender a diferen??a entre os dois requisitos
//test.display({ search: "job" });

//3.8 ??? Imprimir os funcion??rios que fazem anivers??rio no m??s 10 e 12.
//test.searchMothBirth({ months: [10, 12] }); //tente tamb??m com mais de dois valores
//3.9 ??? Imprimir o funcion??rio com a maior idade, exibir os atributos: nome e idade.
//test.display({ search: "oldest" });
//3.10 ??? Imprimir a lista de funcion??rios por ordem alfab??tica.
//test.display({ search: "alphabetical" });

//3.11 ??? Imprimir o total dos sal??rios dos funcion??rios.
//test.salaryAll({ operation: { salary_sum: "salary_sum" } });

//3.12 ??? Imprimir quantos sal??rios m??nimos ganha cada funcion??rio, considerando que o sal??rio m??nimo ?? R$1212.00.
//test.salaryAll({ operation: { how_many_wages: 1212.0 } }); //salario minimo atual: 1212.00. Mas o c??digo funciona com outros valores.
