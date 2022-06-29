package Technical;

import DataManagement.SaveAndLoad;
import Technical.Abstracts.Person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static Services.Documents.File.*;

public class Member extends Person implements SaveAndLoad {
    private int Member_id;
    private LocalDate Join_Date;
    public static int MemberCount = 1;

    public Member() {
        super();
    }

    public void setMember_id(int member_id) {
        Member_id = member_id;
    }

    public static int getMemberCount() {
        return MemberCount;
    }

    public static void setMemberCount(int memberCount) {
        MemberCount = memberCount;
    }

    public int getMember_id() {
        return Member_id;
    }

    public Member(String firstName, String lastName, String national_Code, String address, String phoneNumber,String password) {
        super(firstName, lastName, national_Code, address, phoneNumber,password);


        this.Join_Date = LocalDate.now();

    }


    public static Member Create(String firstName, String lastName, String national_Code, String address, String phoneNumber,String password) {
        return new Member(firstName, lastName, national_Code, address, phoneNumber,password);
    }


    public LocalDate getJoin_Date() {
        return Join_Date;
    }

    public String getJoinDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        return formatter.format(Join_Date);
    }

    public void setJoin_Date(LocalDate join_Date) {
        Join_Date = join_Date;
    }

    public static Member Create() {
        Scanner input = new Scanner(System.in);
        String firstName = "", lastName = "", national_Code = "", address = "", phoneNumber = "",password="";
        System.out.println("enter first name");
        firstName = input.nextLine();
        System.out.println("enter last name:");
        lastName = input.nextLine();
        System.out.println("enter national code");
        national_Code = input.nextLine();
        System.out.println("enter address");
        address = input.nextLine();
        System.out.println("enter phone number");
        phoneNumber = input.nextLine();
        System.out.println("enter password");
        password= input.nextLine();
        Member m = new Member(firstName, lastName, national_Code, address, phoneNumber,password);
        m.setMember_id(MemberCount);
        MemberCount++;

        return m;

    }





    public void setJoin_Date(String date) {
        this.setJoin_Date(LocalDate.of(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8))));
    }


    @Override
    public void Save() {
        SaveMembers();
    }

    @Override
    public void Load() {
        LoadMemberData();
    }
}

