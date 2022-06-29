package Services.Documents;

import Services.Books.Book;
import Technical.Staff.Employee;
import Technical.Library;
import Technical.Member;
import Technical.Staff.Manager;

import java.sql.*;
import java.util.ArrayList;

import static Technical.Member.MemberCount;

public class File {
    public static ArrayList<Manager> Managers=new ArrayList<Manager>();
    public static ArrayList<Library> Libraries = new ArrayList<Library>();
    public static ArrayList<Member> Members = new ArrayList<Member>();
    public static ArrayList<Employee> Employees=new ArrayList<Employee>();
    public static ArrayList<Book> Books=new ArrayList<Book>();
    public static ArrayList<Rent> Rents=new ArrayList<Rent>();


    public static Manager findManagerByNationalCode(String nc){
        for(Manager m:Managers){
            if(m.getNational_Code().equals(nc)) {
                return m;
            }
        }

        return null;
    }
    public static Member findMemberByNationalCode(String nc){
        for(Member m:Members){
            if(m.getNational_Code().equals(nc)) {
                return m;
            }
        }

        return null;
    }

    public static Employee findEmployeeByNationalCode(String nc){
        for(Employee m:Employees){
            if(m.getNational_Code().equals(nc)) {
                return m;
            }
        }

        return null;
    }

    public static Book findBookByName(String n){
        for(Book m:Books){
            if(m.getName().equals(n)) {
                return m;
            }
        }

        return null;
    }

    public static Library findLibraryByName(String n){
        for(Library m:Libraries){
            if(m.getName().equals(n)) {
                return m;
            }
        }

        return null;
    }



    public static ArrayList<Library> findLibraryByManager(Manager e){
        ArrayList<Library> LibraryList=new ArrayList<Library>();
        for(Library l:Libraries){
            if(l.getLibManager().equals(e))
                LibraryList.add(l);
        }
        if(LibraryList.isEmpty())
            return null;
        else
            return LibraryList;
    }



    public static void CreateNewDataBase(String name){
        String url="jdbc:postgresql://localhost/";
        String user="postgres";
        String pass="12345678";
        try(Connection con=DriverManager.getConnection(url,user,pass)){
            Statement st=con.createStatement();
            String sql ="CREATE DATABASE " + name;
            st.executeUpdate(sql);

        } catch (SQLException e) {
            if(e.getMessage().contains("already exists" )) {
                }
            else
                e.printStackTrace();
        }
    }


    public static Connection connect(){
        String url="jdbc:postgresql://localhost/Library";
        String user="postgres";
        String pass="12345678";
        Connection con=null;
        try{
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


    public static void AddMemberData(Member m) {

        String sql="INSERT INTO Members" +
                "  (firstname, lastname, nationalcode, address, phonenumber,id,joindate,password) VALUES " +
                " (?, ?, ?, ?, ?,?,?,?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, m.getFirstName());
            pst.setString(2,m.getLastName());
            pst.setString(3,m.getNational_Code());
            pst.setString(4,m.getAddress());
            pst.setString(5,m.getPhoneNumber());
            pst.setInt(6,m.getMember_id());
            pst.setObject(7,m.getJoin_Date());
            pst.setString(8,m.getPassword());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadMemberData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Members");
            while (rs.next()) {
                Member m = new Member(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(8));
                m.setMember_id(rs.getInt(1));
                m.setJoin_Date( rs.getString(7));
                System.out.println(rs.getString(8));
                Members.add(m);
                MemberCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void DeleteMemberData(Member m){
        String sql="DELETE FROM Members Where id = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,m.getMember_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteMemberData(int m){
        String sql="DELETE FROM Members Where id = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,m);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SaveMembers(){
        String sql ="INSERT OR UPDATE Members VALUES(?,?,?,?,?,?,?,?)";
        try(Connection con=connect()){
            for(Member m : Members) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, m.getFirstName());
                pst.setString(2, m.getLastName());
                pst.setString(3, m.getNational_Code());
                pst.setString(4, m.getAddress());
                pst.setString(5, m.getPhoneNumber());
                pst.setInt(6, m.getMember_id());
                pst.setObject(7, m.getJoin_Date());
                pst.setString(8,m.getPassword());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void AddManagerData(Manager m) {

        String sql="INSERT INTO Managers" +
                "  (firstname, lastname, nationalcode, address, phonenumber,holidaycount,salary,password) VALUES " +
                " (?, ?, ?, ?, ?,?,?,?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, m.getFirstName());
            pst.setString(2,m.getLastName());
            pst.setString(3,m.getNational_Code());
            pst.setString(4,m.getAddress());
            pst.setString(5,m.getPhoneNumber());
            pst.setInt(6,m.getHolidayCount());
            pst.setInt(7,m.getSalary());
            pst.setString(8,m.getPassword());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadManagerData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Managers");
            while (rs.next()) {
                Manager m = new Manager(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(8));
                m.setHolidayCount(rs.getInt(1));
                m.setSalary( rs.getInt(7));
                System.out.println(rs.getString(8));
                Managers.add(m);
              Manager.ManagerCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void DeleteManagerData(Manager m){
        String sql="DELETE FROM Managers Where nationalcode = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,m.getNational_Code());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteManagerData(String m){
        String sql="DELETE FROM Managerss Where nationalcode = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,m);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void SaveManagers(){
        String sql ="INSERT OR UPDATE Managers VALUES(?,?,?,?,?,?,?,?)";
        try(Connection con=connect()){
            for(Manager m : Managers) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, m.getFirstName());
                pst.setString(2,m.getLastName());
                pst.setString(3,m.getNational_Code());
                pst.setString(4,m.getAddress());
                pst.setString(5,m.getPhoneNumber());
                pst.setInt(6,m.getHolidayCount());
                pst.setInt(7,m.getSalary());
                pst.setString(8,m.getPassword());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void AddEmployeeData(Employee m) {

        String sql="INSERT INTO Employees" +
                "  (firstname, lastname, nationalcode, address, phonenumber,holidaycount,salary,password,type) VALUES " +
                " (?, ?, ?, ?, ?,?,?,?,?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, m.getFirstName());
            pst.setString(2,m.getLastName());
            pst.setString(3,m.getNational_Code());
            pst.setString(4,m.getAddress());
            pst.setString(5,m.getPhoneNumber());
            pst.setInt(6,m.getHolidayCount());
            pst.setInt(7,m.getSalary());
            pst.setString(8,m.getPassword());
            pst.setString(9,m.getType());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadEmployeeData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Employees");
            while (rs.next()) {
                Employee m = new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(8),rs.getString(9));


                Employees.add(m);
                Employee.EmployeeCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void DeleteEmployeeData(Employee m){
        String sql="DELETE FROM Employees Where nationalcode = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,m.getNational_Code());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteEmployeeData(String m){
        String sql="DELETE FROM Employees Where nationalcode = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,m);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void SaveEmployees(){
        String sql ="INSERT OR UPDATE Employees VALUES(?,?,?,?,?,?,?,?,?)";
        try(Connection con=connect()){
            for(Employee m : Employees) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, m.getFirstName());
                pst.setString(2,m.getLastName());
                pst.setString(3,m.getNational_Code());
                pst.setString(4,m.getAddress());
                pst.setString(5,m.getPhoneNumber());
                pst.setInt(6,m.getHolidayCount());
                pst.setInt(7,m.getSalary());
                pst.setString(8,m.getPassword());
                pst.setString(9,m.getType());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void AddLibraryData(Library m) {

        String sql="INSERT INTO Libraries" +
                "  (address, name, managerserial,memberserial , employeeserial) VALUES " +
                " (?, ?, ?, ?, ?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, m.getAddress());
            pst.setString(2,m.getName());
            pst.setString(3,m.getManagerSerial());
            pst.setString(4,m.getMembersSerial());
            pst.setString(5,m.getEmployeesSerial());

            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadLibraryData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Libraries");
            while (rs.next()) {
                Library m = new Library(rs.getString(1),rs.getString(2),rs.getString(3));
                m.setManagerSerial(rs.getString(4));
                m.setEmployeesSerial(rs.getString(5));
                System.out.println(rs.getString(1));


                Libraries.add(m);
                Library.LibraryCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void DeleteLibraryData(Library m){
        String sql="DELETE FROM Employees Where nationalcode = ?";
        try(Connection con=connect()){
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,m.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void SaveLibraries(){
        String sql ="INSERT OR UPDATE Libraries VALUES(?,?,?,?,?,?,?,?,?)";
        try(Connection con=connect()){
            for(Library m : Libraries) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, m.getAddress());
                pst.setString(2,m.getName());
                pst.setString(3,m.getManagerSerial());
                pst.setString(4,m.getMembersSerial());
                pst.setString(5,m.getEmployeesSerial());

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void AddBookData(Book m) {

        String sql="INSERT INTO Books" +
                "  (name,author,id) VALUES " +
                " (?, ?, ?, ?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, m.getName());
            pst.setString(2,m.getAuthor());
            pst.setInt(3,m.getBook_id());

            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadBookData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Books");
            while (rs.next()) {
                Book m = new Book(rs.getString(2),rs.getString(3));
                m.setBook_id(rs.getInt(1));


                Books.add(m);
                Book.BookCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }






    public static void SaveBooks(){
        String sql ="INSERT OR UPDATE Books VALUES(?,?,?)";
        try(Connection con=connect()){
            for(Book m : Books) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, m.getName());
                pst.setString(2,m.getAuthor());
                pst.setInt(3,m.getBook_id());

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static void AddRentData(Rent m) {

        String sql="INSERT INTO Books" +
                "  (book_id,get_date,dead_date,book_name,member_name) VALUES " +
                " (?, ?, ?, ?,?);";
        try(Connection con=connect()){
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, m.getBook().getBook_id());
            pst.setObject(2,m.getGettingDate());
            pst.setObject(3,m.getDeadLineDate());
            pst.setString(4,m.getBook().getName());
            pst.setString(5,m.getMember().getNational_Code());
            pst.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void LoadRentData(){
        try(Connection con=connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Rents");
            while (rs.next()) {
                Rent m = new Rent(findMemberByNationalCode(rs.getString(5)),findBookByName(rs.getString(4)));



                Rents.add(m);
                Rent.RentCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }






    public static void SaveRents(){
        String sql ="INSERT OR UPDATE Rents VALUES(?,?,?,?,?";
        try(Connection con=connect()){
            for(Rent m : Rents) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, m.getBook().getBook_id());
                pst.setObject(2,m.getGettingDate());
                pst.setObject(3,m.getDeadLineDate());
                pst.setString(4,m.getBook().getName());
                pst.setString(5,m.getMember().getNational_Code());

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








}
