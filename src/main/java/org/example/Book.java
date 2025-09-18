package org.example;
//Book
public class Book {
    private int bookId;
    private String title;
    genre Genre;
    private boolean borrowed=false;

    //Default parameterized
    public Book() {
        this.bookId = 0;
        this.title = "unknown";
        this.Genre = genre.SCIENCE;
    }


    public Book( int bookId, String title, genre Genre){

        this.bookId = bookId;
        this.title = title;
        this.Genre = Genre;


    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }




    public int getBookId () {
        return bookId;
    }

        public void setBookId ( int bookId){
        this.bookId = bookId;
    }

        public String getTitle () {
        return title;
    }

        public void setTitle (String title){
        this.title = title;
    }

        public genre getGenre () {
        return Genre;
    }

        public void setGenre (genre genre){
        Genre = genre;
    }
        @Override
        public String toString () {
        return String.format("Book{bookId=%d, title='%s, Genre=%s, borrowed=%s}",
                bookId, title, Genre, borrowed ? "Yes" : "No");
    }

        @Override
        public boolean equals (Object o){
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId == book.bookId;
    }





}
