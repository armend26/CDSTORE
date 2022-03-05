package model;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = -5083759422249745403L;
    private String employeeName;
    private String employeeSurname;
    private double employeeSalary;


    public Employee(){this("","",0);}

    public Employee(String name,String surname,double salary){
        super();
        this.employeeName = name;
        this.employeeSurname = surname;
        this.employeeSalary = salary;
    }

    public String getEmployeeName(){
        return employeeName;
    }
    public String getEmployeeSurname(){
        return employeeSurname;
    }

    public double getEmployeeSalary(){
        return employeeSalary;
    }
    public void setEmployeeName(String name){
        this.employeeName = name;
    }
    public void setEmployeeSurname(String surname){
        this.employeeSurname = surname;
    }
    public void setEmployeeSalary(double salary){
        this.employeeSalary = salary;
    }

}
