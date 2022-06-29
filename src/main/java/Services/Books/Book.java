package Services.Books;

import DataManagement.SaveAndLoad;

import java.util.Scanner;

import static Services.Documents.File.LoadBookData;
import static Services.Documents.File.SaveBooks;


public class Book implements SaveAndLoad {
    private String Name;
    private String Author;
    private int Book_id;
    public static int BookCount=0;


    public Book(String name, String a) {
        Name = name;
        Book_id=BookCount+ 1;
        Author=name;
        BookCount++;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return this.Author;
    }




    public void setAuthor(String author) {
        this.Author = author;
    }




    public int getBook_id() {
        return Book_id;
    }


    public void setBook_id(int book_id) {
        Book_id = book_id;
    }


    public static  Book Create(){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter book name:");
        String name=scan.nextLine();
        System.out.println("enter author name:");
        String a=scan.nextLine();
        return new Book(name,a);
    }


    @Override
    public void Save() {
        SaveBooks();
    }

    @Override
    public void Load() {
        LoadBookData();
    }
}


