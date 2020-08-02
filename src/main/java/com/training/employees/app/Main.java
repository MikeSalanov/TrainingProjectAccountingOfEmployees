package com.training.employees.app;

import com.training.employees.entity.Employee;
import com.training.employees.entity.Manager;
import com.training.employees.service.HRDepartment;
import com.training.employees.entity.TypeOfEmployee;
import org.w3c.dom.ls.LSOutput;

import java.lang.module.FindException;
import java.time.*;
import java.util.*;

public class Main {
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /*public static void main1 (String[] args) {
        List<Employee> employeesCollection = new LinkedList();//создали коллекцию для сотрудников
        while (true) {
            int numOfMenuMethod = menu();
            if (numOfMenuMethod == 0) {
                break;
                //2 System.exit(0); //Выход или завершили работу программы
            } else if (numOfMenuMethod == 1) {
                while (true) {
                    //создали нового сотрудника и после заполнения его полей, добавили в коллекцию employeesCollection
                    var employee = new Employee(inputSurnameCreateEmployee(), inputNameCreateEmployee(), inputPatronymicCreateEmployee(), inputDateDOBCreateEmployee(), inputDateStartJob(), inputSalary(), chooseTypeOfEmployee());
                    employeesCollection.add(employee);
                    System.out.println(employee);
                    //спрашиваем пользователя закончить ли добавление сотрудников или нужно добавить ещё?
                    while (true) {
                            var y_n = new Scanner(System.in);
                            System.out.println("Всё? Y/N ");
                            String yesno = y_n.nextLine();
                            if (yesno.equals("Y") || yesno.equals("y")) {
                                break;//тут мы выйдем из цикла, после того как пользователь ввёл "Y" (yes)
                            } else if (yesno.equals("N") || yesno.equals("n")) {
                                continue;
                            }
                        }
                    break;
                }
            } else if (numOfMenuMethod == 2) {
                    if (employeesCollection.isEmpty()) {
                        System.out.println(" \n Пусто \n ");
                    }

                    Collections.sort(employeesCollection, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee o1, Employee o2) {
                            return (o1.getSurname()).compareTo(o2.Surname);
                        }
                    });

                    //Выводим всех сотрудников
                    Iterator<Employee> it = employeesCollection.iterator();
                    while (it.hasNext()) {
                        var employee = it.next();
                        System.out.println(employee);
                    }
                    continue;
                } else if (numOfMenuMethod == 3) {
                    Iterator<Employee> iter = employeesCollection.iterator();
                    String surn = inputSurnameRemoveEmployee();
                    while (iter.hasNext()) {
                        var employee = iter.next();
                        if (surn.equals(employee.getSurname()) && inputNameRemoveEmployee().equals(employee.getName()) && inputDateDOBRemoveEmployee().equals(employee.getDOB())) {
                            System.out.println(employee);
                            iter.remove();
                        }
                    }
                    continue;
                }
            }
        System.out.println("Программа успешно завершила своё выполнение");
        }*/
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private HRDepartment hrDepartment = new HRDepartment();

    public static void main(String[] args) {
        var main = new Main();
        main.start();
    }

    public void start() {
        LocalDate ld1 = LocalDate.of(1998, 10, 13);
        LocalDate ld2 = LocalDate.of(1999, 05, 11);
        LocalDate startjob1 = LocalDate.of(2020, 05, 13);
        LocalDate startjob2 = LocalDate.of(2020, 07, 02);
        var employee1 = new Employee("Саланов", "Михаил", "Дмитриевич", ld1, startjob1, 300, TypeOfEmployee.EMPLOYEE);
        var employee2 = new Employee("Иванов", "Иван", "Иванович", ld2, startjob2, 300, TypeOfEmployee.EMPLOYEE);
        hrDepartment = hrDepartment.addEmployee(employee1);
        hrDepartment = hrDepartment.addEmployee(employee2);
        while(true) {
            Integer numOfMenuMethod = menu();
            if (numOfMenuMethod == 0) {
                System.out.println("Программа успешно завершила своё выполнение");
                break;
            } else if (numOfMenuMethod == 1) {
                createNewEmployee();/**/
            } else if (numOfMenuMethod == 2) {
                outputAllEmployees();/**/
            } else if (numOfMenuMethod == 3) {
                try {
                    hrDepartment = removeEmployee();
                } catch (EmptyStackException emptyEx) {
                    System.out.println("Пусто");
                } catch (FindException findEx) {
                    System.out.println("Сотрудник не найден");
                }/**/
            } else if (numOfMenuMethod == 4) {
                searchEmployee();/**/
            } else if (numOfMenuMethod == 5) {
                searchEmployeeForEditSalary();/**/
            } else if (numOfMenuMethod == 6) {
                try {
                    hrDepartment = createManager();/**/
                } catch (IllegalArgumentException | FindException e  ) {
                    System.out.println(e.getMessage());
                }
            } else if (numOfMenuMethod == 7) {
                try {
                    outputAllEmployeesOfManager();
                } catch (FindException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyStackException exp) {
                    System.out.println("У данного менеджера нет сотрудников");
                }
            } else if (numOfMenuMethod == 8) {
                try {
                    hrDepartment = addInListOfManagerYetEmployee();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (FindException exception) {
                    System.out.println(exception.getMessage());
                }
            } else if (numOfMenuMethod > 8 || numOfMenuMethod < 0) {
                System.err.println("Номеров таких действий НЕТ! Повторите ввод!");
            }
        }
    }

     public void createNewEmployee() {
        String surname = inputSurnameCreateEmployee();
        String name = inputNameCreateEmployee();
        String patronymic = inputPatronymicCreateEmployee();
        LocalDate dob = inputDateDOBCreateEmployee();
        LocalDate startJob = inputDateStartJob();
        int salary = inputSalary();
        TypeOfEmployee typeOfEmployee = chooseTypeOfEmployee();
        String desc = new String();
        if (typeOfEmployee.equals(TypeOfEmployee.OTHER)) {
            desc = inputDescription();
        }
        hrDepartment = hrDepartment.createAndAddNewEmployee(surname, name, patronymic, dob, startJob, salary, typeOfEmployee, desc);
        cycleYesOrNo();
     }

     private void cycleYesOrNo () {
        var y_n = new Scanner(System.in);
        /*спрашиваем пользователя закончить ли добавление сотрудников или нужно добавить ещё?*/
        System.out.println("Всё? Y/N ");
        String yesno = y_n.nextLine();
        if (yesno.equals("Y") || yesno.equals("y")) {
            //тут мы выйдем из цикла, после того как пользователь ввёл "Y" (yes)
        } else if (yesno.equals("N") || yesno.equals("n")) {
            createNewEmployee();
        }
     }

        public List<Employee> sortOfSurname () {
            List<Employee> list = new ArrayList<>(hrDepartment.getEmployees());
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (o1.getSurname()).compareTo(o2.getSurname());
                }
            });
            return list;
        }

        public List<Employee> sortOfDateStartJob () {
            List<Employee> list = new LinkedList(hrDepartment.getEmployees());
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (o1.getStartJobDate().compareTo(o2.getStartJobDate()));
                }
            });
            return list;
        }

        public void outputAllEmployees () {
            while (true) {
                if (hrDepartment.getEmployees().isEmpty()) {
                    System.out.println(" \n Пусто \n ");
                    break;
                }
                System.out.println("Выберите тип сортировки : 1. По фамилии; 2. По дате приёма на работу. ");
                var in = new Scanner(System.in);
                int chooseOfSort = in.nextInt();
                if (chooseOfSort == 1) {
                    List<Employee> list = new LinkedList();
                    list.addAll(sortOfSurname());
                    Iterator<Employee> it = list.iterator();
                    while (it.hasNext()) {
                        var employee = it.next();
                        System.out.println(employee);
                    }
                } else if (chooseOfSort == 2) {
                    List<Employee> list = new LinkedList();
                    list.addAll(sortOfDateStartJob());
                    Iterator<Employee> it = list.iterator();
                    while (it.hasNext()) {
                        var employee = it.next();
                        System.out.println(employee);
                    }
                }break;
            }
        }

        public HRDepartment removeEmployee () {
            if (hrDepartment.getEmployees().size() == 0) {
                throw new EmptyStackException();
            }
            String surnm = inputSurnameRemoveEmployee();
            String name = inputNameRemoveEmployee();
            LocalDate dob = inputDateDOBRemoveEmployee();
            Employee searchedEmployee = hrDepartment.searchEmployee(surnm, name, dob);
            if (searchedEmployee == null) {
                throw new FindException();
            } else {
                hrDepartment = hrDepartment.searchAndRemoveEmployee(surnm, name, dob);
                System.out.println("Удалён " + searchedEmployee);
                return hrDepartment;
            }
        }

        public Employee searchEmployee () {
        Employee emp = null;
        int count = 0;
            while (true) {
                if (hrDepartment.getEmployees().isEmpty()) {
                    System.out.println(" \n Пусто \n ");
                    break;
                }
                String surnm = inputSurnameRemoveEmployee();
                String name = inputNameRemoveEmployee();
                LocalDate DOB = inputDateDOBRemoveEmployee();
                Iterator<Employee> iterator = hrDepartment.getEmployees().iterator();
                while (iterator.hasNext()) {
                    var step = iterator.next();
                    if (surnm.equals(step.getSurname()) && name.equals(step.getName()) && DOB.equals(step.getDOB())) {
                        System.out.println(step);
                        emp = step;
                        break;
                    } else {
                        count += 1;
                        if (count == hrDepartment.getEmployees().size()) {
                            System.out.println("Нет таких сотрудников");
                            emp = null;
                            break;
                        }
                    }
                } break;
            } return emp;
        }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Статические методы

    public Integer menu() {
        var in = new Scanner(System.in);
        Integer choose = 1000;
        try {
                System.out.print("0. Выход | ");
                System.out.print("1. Создать сотрудника | ");
                System.out.print("2. Показать всех сотрудников, которые есть | ");
                System.out.print("3. Удалить сотрудника | ");
                System.out.print("4. Найти сотрудника | ");
                System.out.print("5. Изменить зарплату сотрудника | \n");
                System.out.print("6. Изменить тип сотрудника на менеджера | ");
                System.out.print("7. Вывести сотрудников менеджера | ");
                System.out.println("8. Добавить ещё сотрудника к менеджеру |");
                System.out.print("Введите номер действия : ");
                choose = in.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Введены неверные данные. Повторите ввод! " + System.lineSeparator() + "-----------------------------------------------------");
            }
        return choose;
    }

    public static TypeOfEmployee chooseTypeOfEmployee() {
        while (true) {
            System.out.println("Выберите номер типа сотрудника, который нужно установить для выбранного сотрудника : ");
            System.out.println(" 1. Сотрудник; 2. Другой тип сотрудника");
            var in = new Scanner(System.in);
            int chooseType = in.nextInt();
            //Выбор сотрудника
            if (chooseType == 1) {
                System.out.println("Успешно! Данному сотруднику присвоен тип 'Сотрудник'.");
                return TypeOfEmployee.EMPLOYEE;
            } else if (chooseType == 2) {
                System.out.println("Успешно! Данному сотруднику присвоен тип 'Сотрудник с описанием типа'.");
                return TypeOfEmployee.OTHER;
            } else {
                System.out.println("Вы ввели неверный номер типа сотрудника! Повторите ввод!");
                continue;
            }
        }
    }

    public String inputDescription() {
        var input = new Scanner(System.in);
        System.out.println("Введите описание сотрудника: ");
        String desc = input.nextLine();
        return desc;
    }

    public String inputSurnameRemoveEmployee() {
        var str = new Scanner(System.in);
        System.err.print("Введите ФАМИЛИЮ сотрудника, которого хотите удалить : ");
        String surname = str.nextLine();
        return surname;
    }

    public String inputNameRemoveEmployee() {
        var str = new Scanner(System.in);
        System.err.println("Введите ИМЯ сотрудника, которого хотите удалить : ");
        String name = str.nextLine();
        return name;
    }

    public LocalDate inputDateDOBRemoveEmployee() {
        Scanner str = new Scanner(System.in);
        System.err.println("Введите ДАТУ РОЖДЕНИЯ сотрудника, которого хотите удалить, в формате : Введите YYYY, далее нажмите Enter, введите MM, далее нажмите Enter, введите DD, нажмите Enter");
        var date = LocalDate.of(str.nextInt(), str.nextInt(), str.nextInt());
        System.out.printf("%d/%d/%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        System.out.println();
        return date;
    }

    public String inputSurnameCreateEmployee() {
        var str = new Scanner(System.in);
        System.err.print("Введите фамилию : ");
        String surname = str.nextLine();
        return surname;
    }

    public String inputNameCreateEmployee() {
        var str = new Scanner(System.in);
        System.err.print("Введите имя : ");
        String name = str.nextLine();
        return name;
    }

    public String inputPatronymicCreateEmployee() {
        var str = new Scanner(System.in);
        System.err.print("Введите отчество (если есть) : ");
        String patronymic = str.nextLine();
        return patronymic;
    }

    public LocalDate inputDateDOBCreateEmployee() {
        Scanner str = new Scanner(System.in);
        System.err.println("Введите дату рождения сотрудника в формате : Введите YYYY, далее нажмите Enter, введите MM, далее нажмите Enter, введите DD, нажмите Enter");
        var date = LocalDate.of(str.nextInt(), str.nextInt(), str.nextInt());
        System.out.printf("%d/%d/%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        System.out.println();
        return date;
    }

    public static LocalDate inputDateStartJob() {
        Scanner str = new Scanner(System.in);
        System.err.println("Введите дату приёма на работу сотрудника в формате : Введите YYYY, далее нажмите Enter, введите MM, далее нажмите Enter, введите DD, нажмите Enter");
        var date = LocalDate.of(str.nextInt(), str.nextInt(), str.nextInt());
        System.out.printf("%d/%d/%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        System.out.println();
        return date;
    }

    private int inputSalary() {
        try {
            Scanner str = new Scanner(System.in);
            System.out.print("Введите новую зарплату сотрудника : ");
            return str.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введен неверный формат зарплаты. Повторите ввод.");
            return inputSalary();
        }
    }

    public HRDepartment createManager() {
        var input = new Scanner(System.in);
        System.out.print("Введите фамилию сотрудника, которого хотите повысить до менеджера : ");
        String surname = input.nextLine();
        System.out.print("Введите имя сотрудника, которого хотите повысить до менеджера : ");
        String name = input.nextLine();
        System.out.print("Введите день рождения сотрудника, которого хотите повысить до менеджера : ");
        int day = input.nextInt();
        System.out.print("Введите номер месяца рождения сотрудника, которого хотите повысить до менеджера : ");
        int month = input.nextInt();
        System.out.print("Введите год рождения сотрудника, которого хотите повысить до менеджера : ");
        int year = input.nextInt();
        LocalDate dob = LocalDate.of(year, month, day);
        System.out.printf("%1$td.%1$tm.%1$tY", dob);
        System.out.println();
        return hrDepartment.upperToManager(surname, name, dob, addInListEmployeesOfManager(), inputSalary());
    }

    public void searchEmployeeForEditSalary() {
        var input = new Scanner(System.in);
        System.out.print("Введите фамилию сотрудника: ");
        String surname = input.nextLine();
        System.out.print("Введите имя сотрудника: ");
        String name = input.nextLine();
        System.out.print("Введите день рождения сотрудника: ");
        int day = input.nextInt();
        System.out.print("Введите номер месяца рождения: ");
        int month = input.nextInt();
        System.out.print("Введите год рождения сотрудника: ");
        int year = input.nextInt();
        LocalDate dob = LocalDate.of(year, month, day);
        System.out.printf("%1$td.%1$tm.%1$tY", dob);
        System.out.println();
        hrDepartment = hrDepartment.searchAndChangeSalaryOfEmployee(surname, name, dob, inputSalary());
    }

    private List<Employee> addInListEmployeesOfManager() {
        List<Employee> empls = new LinkedList();
        Employee empl = searchEmployeeForAddInList();
                if (empl == null) {
                    System.out.println("Сотрудник не найден");
                } else if (empl.getTypeOfEmployee().equals(TypeOfEmployee.EMPLOYEE)) {
                    empls.add(empl);
                } else {
                    throw new IllegalArgumentException("Это не простой сотрудник. Его невозможно прикрепить к менеджеру.");
                }
        System.out.println("Закончить добавление?");
        var in = new Scanner(System.in);
        String yn = in.nextLine();
        while (!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")) {
            System.out.println("Введены неверные данные, повторите ввод.");
            yn = in.nextLine();
        }
        if (yn.equalsIgnoreCase("n")) {
            empls.addAll(addInListEmployeesOfManager());
        }
        return empls;
    }

    public Employee searchEmployeeForAddInList() {
            var input = new Scanner(System.in);
            System.out.print("Введите фамилию сотрудника, которого хотите прикрепить к данному менеджеру : ");
            String surname = input.nextLine();
            System.out.print("Введите имя сотрудника, которого хотите прикрепить к данному менеджеру : ");
            String name = input.nextLine();
            System.out.print("Введите день рождения сотрудника, которого хотите прикрепить к данному менеджеру : ");
            int day = input.nextInt();
            System.out.print("Введите номер месяца рождения сотрудника, которого хотите прикрепить к данному менеджеру : ");
            int month = input.nextInt();
            System.out.print("Введите год рождения сотрудника, которого хотите прикрепить к данному менеджеру : ");
            int year = input.nextInt();
            LocalDate dob = LocalDate.of(year, month, day);
            System.out.printf("%1$td.%1$tm.%1$tY", dob);
            System.out.println();
            Employee step = hrDepartment.searchEmployee(surname, name, dob);
            return step;
        }

    public void outputAllEmployeesOfManager() {
        var input = new Scanner(System.in);
        System.out.print("Введите фамилию менеджера : ");
        String surname = input.nextLine();
        System.out.print("Введите имя менеджера : ");
        String name = input.nextLine();
        System.out.print("Введите день рождения менеджера : ");
        int day = input.nextInt();
        System.out.print("Введите номер месяца рождения менеджера : ");
        int month = input.nextInt();
        System.out.print("Введите год рождения менеджера : ");
        int year = input.nextInt();
        LocalDate dob = LocalDate.of(year, month, day);
        System.out.printf("%1$td.%1$tm.%1$tY", dob);
        System.out.println();
        Employee manager = hrDepartment.searchEmployee(surname, name, dob);
        if (manager.getTypeOfEmployee().equals(TypeOfEmployee.MANAGER)) {
            outputEmployeesOfManager((Manager) manager);
        } else {
            throw new FindException("Данный сотрудник не является менеджером!");
        }
    }

    public HRDepartment addInListOfManagerYetEmployee() {
       var input = new Scanner(System.in);
       System.out.print("Введите фамилию менеджера : ");
       String surname = input.nextLine();
       System.out.print("Введите имя менеджера : ");
       String name = input.nextLine();
       System.out.print("Введите день рождения менеджера : ");
       int day = input.nextInt();
       System.out.print("Введите номер месяца рождения менеджера : ");
       int month = input.nextInt();
       System.out.print("Введите год рождения менеджера : ");
       int year = input.nextInt();
       LocalDate dob = LocalDate.of(year, month, day);
       System.out.printf("%1$td.%1$tm.%1$tY", dob);
       System.out.println();
       Employee employee = searchEmployeeForAddInList();
       Employee manager = hrDepartment.searchEmployee(surname, name, dob);
       if (manager != null) {
           HRDepartment hrDep = hrDepartment.addEmployeeToManager((Manager)manager, employee);
           return hrDep;
       }
       throw new FindException("Менеджер не найден");
    }

    private void outputEmployeesOfManager(Manager manager) {
        System.out.println("Список сотрудников, относящихся к данному менеджеру:");
        Iterator<Employee> iter = manager.getLeom().iterator();
        while(iter.hasNext()) {
            Employee employeeOfManager = iter.next();
            System.out.println(employeeOfManager);
        }
        if (manager.getLeom().isEmpty()) {
            throw new EmptyStackException();
        }
    }
}

