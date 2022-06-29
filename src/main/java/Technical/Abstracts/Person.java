package Technical.Abstracts;

abstract public class Person {
    private String FirstName;
    private String LastName;
    private String National_Code;
    private String Address;
    private String PhoneNumber;
    private String Password;

    public Person(String firstName, String lastName, String national_Code, String address, String phoneNumber,String password) {
        FirstName = firstName;
        LastName = lastName;
        National_Code = national_Code;
        Address = address;
        PhoneNumber = phoneNumber;
        Password=password;
    }

    public Person() {

    }



    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getName(){
        return FirstName + " " + LastName;
    }


    public String getNational_Code() {
        return National_Code;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public void setNational_Code(String national_Code) {
        this.National_Code = national_Code;
    }
}
