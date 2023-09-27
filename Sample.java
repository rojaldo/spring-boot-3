import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Sample {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String[] names = {"John", "Mary", "Bob"};
        // order by name
        System.out.println(Stream.of(names).sorted( (a, b) -> b.compareTo(a) ).collect(Collectors.toList()));
        // print each name in capital letters
        Stream.of(names).forEach( name -> System.out.println(name.toUpperCase()) );
        // print the array
        System.out.println(Stream.of(names).collect(Collectors.toList()));
        // transform the array content to uppercase
        names = Stream.of(names).map(name -> name.toUpperCase()).collect(Collectors.toList()).toArray(new String[0]);
        System.out.println(Stream.of(names).collect(Collectors.toList()));


        // System.out.println(Stream.of(names).filter(name -> name.startsWith("M")).count());
    }
}
