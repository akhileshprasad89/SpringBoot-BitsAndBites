package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
String name;
public Main(int i){

}
    public void setName(String n){
        this.name=n;
    }
    public static void main(String[] args) {

     Main m=new Main(8);
     m.setName("Akhilesh");
        System.out.println(m.name);

    }
}
