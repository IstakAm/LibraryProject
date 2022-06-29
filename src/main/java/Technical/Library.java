package Technical;

import DataManagement.SaveAndLoad;
import Technical.Staff.Employee;
import Technical.Staff.Manager;

import java.util.ArrayList;
import java.util.Scanner;

import static Services.Documents.File.*;

public class Library implements SaveAndLoad {
    public boolean log=false;
    public static int LibraryCount=0;
    private String Address;
    private String Name;
    private Manager LibManager;
    private String ManagerSerial;
    private ArrayList<Employee> Employees = new ArrayList<Employee>();
    private ArrayList<Member> Members=new ArrayList<Member>();
    private StringBuilder MembersSerial= new StringBuilder("");
    private StringBuilder EmployeesSerial = new StringBuilder("");
    public ArrayList<Employee> Requests=new ArrayList<Employee>();
    @Override
        public void Save(){
        SaveLibraries();
        }

    @Override
    public void Load() {
        LoadLibraryData();
    }


    public String getManagerSerial() {
        return ManagerSerial;
    }

    public String getName() {
        return Name;
    }


    public void Accept(String nc){
        if(Requests.contains(findEmployeeByNationalCode(nc))){
            AppendEmployee(findEmployeeByNationalCode(nc));
            Requests.remove(findManagerByNationalCode(nc));
        }
    }


    public Manager getLibManager() {
        return LibManager;
    }

    public String getAddress() {
        return Address;
    }

    public ArrayList<Employee> getEmployees() {
        return Employees;
    }

    public ArrayList<Member> getMembers() {
        return Members;
    }

    public void setName(String name) {
        Name = name;
    }


    public void setAddress(String address) {
        Address = address;
    }

    public void setLibManager(Manager libManager) {
        LibManager = libManager;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        Employees = employees;
    }

    public void setMembers(ArrayList<Member> members) {
        Members = members;
    }

    public void setManagerSerial(String managerSerial) {
        ManagerSerial = managerSerial;
        setLibManager(findManagerByNationalCode(managerSerial));
    }

    public Library(String address, String name, String managerSerial) {
        Address = address;
        Name = name;
        ManagerSerial = managerSerial;
        LibManager=findManagerByNationalCode(managerSerial);
    }

    public static Library Create (){

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter address:");
        String address = scanner.nextLine();
        System.out.println("enter name:");
        String name = scanner.nextLine();
        System.out.println("enter manager's national code:");
        String nc=scanner.nextLine();
        Library l = new Library(address,name,nc);
        Libraries.add(l);
        LibraryCount=Libraries.size();
        System.out.println("Library is made");
        return l;
    }

    public void AppendMember(Member m){
        MembersSerial.append(m.getNational_Code()).append(" ");
    }


    public String getMembersSerial() {
        return String.valueOf(MembersSerial);
    }

    public void setMembersSerial(String s){
        MembersSerial.append(s);
        for(String str:s.split(" ")){
            this.Members.add(findMemberByNationalCode(str));
        }
    }

    public void AppendEmployee(Employee m){
        MembersSerial.append(m.getNational_Code()).append(" ");
    }


    public String getEmployeesSerial() {
        return String.valueOf(EmployeesSerial);
    }

    public void setEmployeesSerial(String s){
        MembersSerial.append(s);
        for(String str:s.split(" ")){
            this.Employees.add(findEmployeeByNationalCode(str));
        }
    }


    public void AddMember(Member m){

        this.Members.add(m);
        AppendMember(m);
        System.out.println("Member added successfully");

    }

    public void AddEmployee(Employee m){

        this.Employees.add(m);
        AppendEmployee(m);
        System.out.println("Employee added successfully");

    }






    public static void PrintLibraries(ArrayList<Library> libraries){
        System.out.println("number | name          | address                      ");
        for(int i=0;i<libraries.size();i++){
            if(i!=libraries.size()-1)
                System.out.println("------------------------------------------------------");
            System.out.printf("%7d|%15s|%30s\n");
        }
    }


}







