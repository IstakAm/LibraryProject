package Technical.Staff;

import DataManagement.SaveAndLoad;
import Technical.Abstracts.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static Services.Documents.File.*;

public class Employee extends Person implements SaveAndLoad {
    private int Salary;
    private int HolidayCount;
    private String Type;
    public static int EmployeeCount;
    public Employee(String firstName, String lastName, String national_Code, String address, String phoneNumber,String password,String type) {
        super(firstName, lastName, national_Code, address, phoneNumber,password);
        this.Type=type;
    }



    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getHolidayCount() {
        return HolidayCount;
    }

    public void setHolidayCount(int holidayCount) {
        HolidayCount = holidayCount;
    }

    public void ApplyForJob(String libName){
        Objects.requireNonNull(findLibraryByName(libName)).Requests.add(this);
    }

    @Override
    public void Save() {
        SaveEmployees();
    }

    @Override
    public void Load() {
        LoadEmployeeData();
    }
}
