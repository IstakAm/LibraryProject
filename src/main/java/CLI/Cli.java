package CLI;

import Technical.Abstracts.Person;
import Technical.Library;
import Technical.Member;
import Technical.Staff.Employee;
import Technical.Staff.Manager;

import java.util.Objects;
import java.util.Scanner;

import static Services.Documents.File.findLibraryByManager;

public class Cli {

    public static void main(String[] args){
        Person user=null;
        Scanner scan=new Scanner(System.in);
        while(!scan.nextLine().equals("exit")){
            System.out.print(">>");
            String s=scan.nextLine();
            try {
                switch (s.split(" ")[0]) {
                    case "\\member":
                        user=Member.Create();
                        break;
                    case "\\manager":
                        user= Manager.Create();
                    case "\\library":
                        if(s.split(" ")[1].equals("accept")) {
                            assert user instanceof Manager;
                            for(Library l: Objects.requireNonNull(findLibraryByManager((Manager) user)))
                                l.Accept(s.split(" ")[2]);
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
