package Technical.Staff;

import DataManagement.SaveAndLoad;

import java.util.Scanner;

import static Services.Documents.File.*;

public class Manager extends Employee implements SaveAndLoad {

    public static int ManagerCount=0;

    public Manager(String firstName, String lastName, String national_Code, String address, String phoneNumber,String password) {
        super(firstName, lastName, national_Code, address, phoneNumber,password,"manager");


    }





    public static Manager Create(){
        String f;
        String l;
        String n ;
        String a ;
        String p ;
        String pw ;
        Scanner input = new Scanner(System.in);
        System.out.println("enter first name");
        f= input.nextLine();
        System.out.println("enter last name");
        l= input.nextLine();
        System.out.println("enter address");
        n= input.nextLine();
        System.out.println("enter phone number");
        p= input.nextLine();
        System.out.println("enter password");
        pw= input.nextLine();
        Manager m = new Manager(f,l,n,a,p,pw);
        Managers.add(m);
        return m;
    }


    @Override
    public void Save() {
        SaveManagers();
    }

    @Override
    public void Load() {
        LoadManagerData();
    }
}
