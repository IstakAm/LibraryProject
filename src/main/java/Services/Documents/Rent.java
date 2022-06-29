package Services.Documents;

import DataManagement.SaveAndLoad;
import Services.Books.Book;
import Technical.Member;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static Services.Documents.File.LoadRentData;
import static Services.Documents.File.SaveRents;

public class Rent implements SaveAndLoad {

    public static int RentCount=0;
    private Member member;
    private Book book;
    private LocalDate GettingDate;
    private LocalDate DeadLineDate;


    public Rent(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.setGettingDate(LocalDate.now());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }


    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getGettingDate() {
        return GettingDate;
    }

    public String getGettingDateString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        return formatter.format(GettingDate);
    }

    public void setGettingDate(LocalDate gettingDate) {
        GettingDate = gettingDate;
    }

    public LocalDate getDeadLineDate() {
        return DeadLineDate;
    }


    public String getDeadLineDateString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        return formatter.format(DeadLineDate);
    }


    public void setDeadLineDate(LocalDate deadLineDate) {
        DeadLineDate = deadLineDate;
    }

    @Override
    public void Save() {
        SaveRents();
    }

    @Override
    public void Load() {
        LoadRentData();
    }
}
