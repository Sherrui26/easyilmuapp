package com.example.loginregister.Model;

public class DataModel2 {

    private int BookId,Availability;
    private String Title,Publisher,Year;

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public int getAvailability() {
        return Availability;
    }

    public void setAvailability(int availability) {
        Availability = availability;
    }

    public String getTitle() {
        return "Book Title: "+Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPublisher() {
        return "Publisher: "+Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getYear() {
        return "Year: "+Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
