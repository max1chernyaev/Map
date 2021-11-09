package HashMap;

public class Main {

    public static void main(String[] args) {

        MyMap myMap = new MyHashMap();
        myMap.put("1", "Dream Theater");
        myMap.put("2", "Pink Floyd");
        myMap.put("3", "Porcupine Tree");
        myMap.put("4", "Neal Morse Band");
        System.out.println("Dream Theater".hashCode());
        System.out.println("Pink Floyd".hashCode());
        System.out.println(myMap.get("2"));
        System.out.println(myMap.remove("3"));
        System.out.println(myMap.toString());

        MySet set = new MyHashSet();
        set.add("Metallica");
        set.add("AC/DC");
        set.add("Haken");
        set.add("OSI");
        System.out.println("Haken".hashCode());


    }
}
