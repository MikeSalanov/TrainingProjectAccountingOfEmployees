package com.training.employees.entity;
import java.time.LocalDate;
public class Other extends Employee{

    private final String description;

    public Other(String Surname, String Name, String Patronymic, LocalDate DOB, LocalDate startJobDate, int salary,
                 TypeOfEmployee typeOfEmployee, String desc) {
        super(Surname, Name, Patronymic, DOB, startJobDate, salary, typeOfEmployee);
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Other { "
        + "Фамилия: " + getSurname()
                + "; Имя: " + getName()
                + "; Отчество: " + getPatronymic()
                + "; Дата рождения: " + getDOB()
                + "; Дата начала работы " + getStartJobDate()
                + "; Зарплата " + getSalary()
                + "; Тип сотрудника " + getTypeOfEmployee()
                + "; Описание сотрудника " + getDescription() + "}";
    }
}
