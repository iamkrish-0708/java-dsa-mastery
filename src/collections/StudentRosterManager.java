package collections;

import java.util.*;

// Student Roster Management System
public class StudentRosterManager {
    public static void main(String[] args) {

        List<String> studentNames=new ArrayList<>();
        studentNames.add("Aarav");
        studentNames.add("Bhavya");
        studentNames.add("Chirag");
        studentNames.add("Divya");

        // late enrolled student
        studentNames.add(1,"Esha");

        // removal of student names
        studentNames.remove("Chirag");
        studentNames.remove(0);

        // student name check in list
        boolean check1=studentNames.contains("Bhavya");
        System.out.println("Bhavya is in list: "+check1);

        // retrieve element and print
        System.out.println(studentNames.get(1));

        // replace name in list
        studentNames.set(1,"Bhavya Sharma");

        // total count of student
        System.out.println("Total number of students= "+ studentNames.size());

        // Iterating through list  below are two methods

        for(String name:studentNames){
            System.out.println(name);
        }

        Iterator<String> it=studentNames.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println(studentNames.isEmpty());


    }
}
