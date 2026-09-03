package oops.inheritance;

class Library{
    String bookTitle;
   int bookId;

    Library(String bookTitle,int bookId){
        this.bookTitle=bookTitle;
        this.bookId=bookId;
    }

    void displayInfo(){
        System.out.println();
        System.out.printf("item ID:%d | Title:%s",this.bookId,this.bookTitle);
    }
}

class Book extends Library{
    String author;
    int pageCount;
    int price;

    Book(String bookTitle,int bookId,String author,int pageCount,int price){
        super(bookTitle,bookId);
        this.author=author;
        this.pageCount=pageCount;
        this.price=price;
    }
    void displayBookDetails(){
        super.displayInfo();
        System.out.println();
        System.out.println("Author:"+this.author+" | Page Count:"+this.pageCount);
        System.out.println("Price: "+this.price+" rs");
    }
}

public class InheritanceMain2 {
    public static void main(String[] args) {
    Book b1=new Book("Effective Java", 1001, "Joshua Bloch", 412,149);
    b1.displayBookDetails();
    }
}
