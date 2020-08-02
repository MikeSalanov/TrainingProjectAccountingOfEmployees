package com.training.employees.service;

import com.training.employees.entity.Employee;
import com.training.employees.entity.Manager;
import com.training.employees.entity.Other;
import com.training.employees.entity.TypeOfEmployee;

import java.lang.module.FindException;
import java.time.LocalDate;
import java.util.*;

public class HRDepartment implements Cloneable {
    private final List<Employee> employees = new LinkedList(); //создали коллекцию для сотрудников

    @Override
    protected HRDepartment clone() {
        var loe = new HRDepartment();
        loe.employees.addAll(this.employees);
        return loe;
    }

    public HRDepartment addEmployee (Employee emp) {
        HRDepartment e = this.clone();
        e.employees.add(emp);
        return e;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

//Block of create new employee and add employee in collection of employees-----------------------------------------------------------------------------------------------------------------------------------------------------
    public HRDepartment createAndAddNewEmployee (String surname, String name, String patronymic, LocalDate dob,
                                         LocalDate startOfJob, int salary, TypeOfEmployee typeOfEmployee,
                                         String description) {
        /*создали нового сотрудника и после заполнения его полей, добавили в коллекцию employeesCollection*/
        Employee employee = new Employee(surname, name, patronymic, dob, startOfJob, salary, typeOfEmployee);
        if (employee.getTypeOfEmployee().equals(TypeOfEmployee.OTHER)){
            employee = new Other(employee.getSurname(), employee.getName(), employee.getPatronymic(),
                    employee.getDOB(), employee.getStartJobDate(), employee.getSalary(),
                    TypeOfEmployee.OTHER, description);
        }
        return addEmployee(employee);
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public HRDepartment upperToManager(String surname, String name, LocalDate dob, List<Employee> employeesForManager, int salary) {
        Employee step = searchEmployee(surname, name, dob);
        if (step == null) {
            throw new FindException("Сотрудник не найден");
        }
        if (step.getTypeOfEmployee().equals(TypeOfEmployee.MANAGER)) {
            throw new IllegalArgumentException("Данный сотрудник уже является менеджером");
        }
        HRDepartment hr = removeEmployee(step);
        Manager manager = new Manager(step.getSurname(), step.getName(), step.getPatronymic(), step.getDOB(), step.getStartJobDate(), salary, TypeOfEmployee.MANAGER, employeesForManager);
        hr = hr.addEmployee(manager);
        return hr;
    }

   private Employee changeSalary(Employee employee, int i) {
        if (employee.getTypeOfEmployee().equals(TypeOfEmployee.EMPLOYEE)) {
          Employee emp = new Employee(employee.getSurname(), employee.getName(), employee.getPatronymic(), employee.getDOB(), employee.getStartJobDate(), i, employee.getTypeOfEmployee());
          return emp;
        } else if (employee.getTypeOfEmployee().equals(TypeOfEmployee.OTHER)) {
            Other other = (Other) employee;
            Employee empOther = new Other(other.getSurname(), other.getName(), other.getPatronymic(), other.getDOB(), other.getStartJobDate(), i, other.getTypeOfEmployee(), other.getDescription());
            return empOther;
        }
            Manager mngr = (Manager) employee;
            Manager manager = new Manager(mngr.getSurname(), mngr.getName(), mngr.getPatronymic(), mngr.getDOB(), mngr.getStartJobDate(), i, mngr.getTypeOfEmployee(), mngr.getLeom());
            return manager;
        }

    public HRDepartment searchAndChangeSalaryOfEmployee(String surnm, String name, LocalDate dob, int salary) {
        Employee employee = searchEmployee(surnm, name, dob);
        if (employee == null) {
            return this;
        }
        HRDepartment hr = removeEmployee(employee);
        Employee newEmployeeWithChangedSalary = changeSalary(employee, salary);
        hr = hr.addEmployee(newEmployeeWithChangedSalary);
        return hr;
    }

    public Employee searchEmployee(String surnm, String name, LocalDate dob) {
        Iterator<Employee> iterator = getEmployees().iterator();
        while (iterator.hasNext()) {
            var step = iterator.next();
            if (surnm.equals(step.getSurname()) && name.equalsIgnoreCase(step.getName()) && dob.equals(step.getDOB())) {
                return step;
            }
        }
        return null;
    }

    private HRDepartment removeEmployee(Employee employee) {
        HRDepartment e = this.clone();
        e.employees.remove(employee);
        return e;
    }

    public HRDepartment searchAndRemoveEmployee(String surname, String name, LocalDate dob) {
        Employee employee = searchEmployee(surname, name, dob);
        if(employee == null) {
            return this;
        }
        return removeEmployee(employee);
    }

    public HRDepartment addEmployeeToManager(Manager manager, Employee employee) {
        if(employee.getTypeOfEmployee().equals(TypeOfEmployee.MANAGER)){
            throw new IllegalArgumentException("Данный сотрудник является менеджер");
        }
            Manager mngr = manager.addEmployee(employee);
            HRDepartment hr = removeEmployee(manager);
            hr = hr.addEmployee(mngr);
            return hr;
    }

}
