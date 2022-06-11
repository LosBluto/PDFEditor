import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author
 * @version 1.0.0
 * @ClassName Main.java
 * @Description TODO
 * @createTime 2022年06月09日 17:28:00
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> paths = new ArrayList<>();
        System.out.println("请输入文件所在路径:(每行一个文件路径)");
        while (sc.hasNext()){
            paths.add(sc.nextLine());
        }
    }
}
