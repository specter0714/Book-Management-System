import java.io.Serializable;

public class Book implements Serializable {
    private static final long SeriaVersionUID = 060714;

    private String title;
    private int price;
    private String author;

    private Book(String title, String author, int price){
        this.price = price;
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString(){
        return "《" + title + "》" + "作者:" + author + ' ' + "价格" + price;
    }

    public static BookBuilder builder(){
        return new BookBuilder();
    }

    public static class BookBuilder{//这种写法叫做建造者模式
        private String title;
        private String author;
        private int price;

        private BookBuilder(){}

        public BookBuilder title(String title){
            this.title = title;
            System.out.println("输入书籍作者");
            return this;
        }

        public BookBuilder author(String author){
            System.out.println("输入书籍价格");
            this.author = author;
            return this;
        }

        public BookBuilder price(int price){
            this.price = price;
            return this;
        }

        public Book build(){
            return new Book(title, author, price);
        }
    }


}
