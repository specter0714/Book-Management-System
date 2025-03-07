import java.util.*;
import java.io.*;

public class Main {

    private static  List<Book> LIST = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("============= 图书管理系统 ===========");
            System.out.println("1.录入书籍信息");
            System.out.println("2.查阅书籍信息");
            System.out.println("3.删除书籍信息");
            System.out.println("4.修改书籍信息");
            System.out.println("5.退出系统");
            System.out.println("====================================");

            switch(scanner.nextInt()){
                case 1:
                    insert(scanner);
                    break;
                case 2:
                    consult();
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    return ;
            }
        }
    }


    private static void insert(Scanner scanner){
        byte[] count = new byte[1];
        try(FileInputStream in = new FileInputStream("num.txt")){
            in.read(count);
            if(count[0] > '0'){
                try(ObjectInputStream stream_in = new ObjectInputStream(new FileInputStream("books.txt"))){
                    LIST = (List<Book>) stream_in.readObject();
                }catch (IOException | ClassNotFoundException e){
                    throw new RuntimeException(e);
                }
            }
        }catch(IOException e){

        }

        scanner.nextLine();
        System.out.println("输入书籍标题");
        Book book = Book.builder()
                .title(scanner.nextLine())
                .author(scanner.nextLine())
                .price(scanner.nextInt())
                .build();
        scanner.nextLine();
        LIST.add(book);

        try(FileOutputStream out = new FileOutputStream("num.txt");
            ObjectOutputStream stream_out = new ObjectOutputStream(new FileOutputStream("books.txt"))){
            int p = count[0] + 1;
            System.out.println((char)p);
            out.write(p);
            stream_out.writeObject(LIST);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("成功添加：" + book.toString());
    }

    private static void consult(){
        try(FileInputStream in = new FileInputStream("num.txt")){
            byte[] count = new byte[1];
            in.read(count);
            if(count[0] > '0'){
                try(ObjectInputStream stream_in = new ObjectInputStream(new FileInputStream("books.txt"))){
                    LIST = (List)stream_in.readObject();
                }catch(IOException | ClassNotFoundException e){
                    throw new RuntimeException(e);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        LIST.forEach(System.out::println);
    }
}