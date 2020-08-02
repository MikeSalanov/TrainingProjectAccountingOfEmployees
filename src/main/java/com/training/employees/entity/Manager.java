package com.training.employees.entity;

import java.time.LocalDate;
import java.util.*;

public class Manager extends Employee implements Cloneable {

    protected final List<Employee> leom = new LinkedList();

    public Manager(String Surname, String Name, String Patronymic, LocalDate DOB, LocalDate startJobDate,
                   int salary, TypeOfEmployee typeOfEmployee, List<Employee> listEmpls) {
        super(Surname, Name, Patronymic, DOB, startJobDate, salary, typeOfEmployee);
        leom.addAll(listEmpls);
    }

    public List<Employee> getLeom() {
        return Collections.unmodifiableList(leom);
    }

    @Override
    protected Manager clone() {
        var manager = new Manager(this.getSurname(), this.getName(), this.getPatronymic(), this.getDOB(), this.getStartJobDate(), this.getSalary(), this.getTypeOfEmployee(), this.getLeom());
        return manager;
    }

    public Manager addEmployee(Employee employee) {
        Manager mngr = this.clone();
        mngr.leom.add(employee);
        return mngr;
    }

    public Manager removeEmployee(Employee employee) {
        Manager mngr = this.clone();
        mngr.leom.add(employee);
        return mngr;
    }

    @Override
    public String toString() {
        return "Manager{" + "Фамилия: " + getSurname() + "; "
                + "Имя: " + getName() + "; "
                + "Отчество: " + getPatronymic() + "; "
                + "Дата рождения: " + getDOB() + "; "
                + "Дата приёма на работу: " + getStartJobDate() + "; "
                + "Зарплата: " + getSalary() + "; "
                + "Тип сотрудника: " + getTypeOfEmployee() + "}";
    }


}


