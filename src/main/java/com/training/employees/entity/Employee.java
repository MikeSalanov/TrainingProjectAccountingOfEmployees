package com.training.employees.entity;
import java.time.LocalDate;
import java.util.*;

 public class Employee{
    protected final String Surname;
    protected final String Name;
    protected final String Patronymic;
    protected final LocalDate DOB;
    protected final LocalDate startJobDate;
    protected final int salary;
    protected final TypeOfEmployee typeOfEmployee;

    public Employee (String Surname, String Name, String Patronymic, LocalDate DOB, LocalDate startJobDate, int salary, TypeOfEmployee typeOfEmployee) {
    this.Surname = Surname;
    this.Name = Name;
    this.Patronymic = Patronymic;
    this.DOB = DOB;
    this.startJobDate = startJobDate;
    this.salary = salary;
    this.typeOfEmployee = typeOfEmployee;
    }

    public LocalDate getDOB() {
        return DOB;
    }
    public String getSurname() {
        return Surname;
    }
    public String getName() {return Name;}
    public String getPatronymic() {return Patronymic;}
    public LocalDate getStartJobDate() {
       return startJobDate;
    }
    public int getSalary() {
       return salary;
    }
    public TypeOfEmployee getTypeOfEmployee() {
       return typeOfEmployee;
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (!(o instanceof Employee)) return false;
       Employee employee = (Employee) o;
       return getSalary() == employee.getSalary() &&
               Objects.equals(getSurname(), employee.getSurname()) &&
               Objects.equals(getName(), employee.getName()) &&
               Objects.equals(getPatronymic(), employee.getPatronymic()) &&
               Objects.equals(getDOB(), employee.getDOB()) &&
               Objects.equals(getStartJobDate(), employee.getStartJobDate()) &&
               getTypeOfEmployee() == employee.getTypeOfEmployee();
    }

    @Override
    public int hashCode() {
       return Objects.hash(getSurname(), getName(), getPatronymic(), getDOB(), getStartJobDate(), getSalary(), getTypeOfEmployee());
    }

    @Override
    public String toString() {
       return "Сотрудник{" +
               "Фамилия : '" + getSurname() + '\'' +
               ", Имя : '" + getName() + '\'' +
               ", Отчество : '" + getPatronymic() + '\'' +
               ", Дата рождения : " + getDOB() +
               ", Дата начала работы : " + getStartJobDate() +
               ", Зарплата : " + getSalary() +
               ", Тип сотрудника : " + getTypeOfEmployee() +
               '}';

          }
 }