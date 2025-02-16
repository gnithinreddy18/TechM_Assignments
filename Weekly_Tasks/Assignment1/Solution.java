package p1;
import java.util.Scanner;

class Associate {
    private int id;
    private String name;
    private String technology;
    private int experienceInYears;
    public Associate(int id, String name, String technology, int experienceInYears) {
        this.id = id;
        this.name = name;
        this.technology = technology;
        this.experienceInYears = experienceInYears;
    }

    public int getId() {
        return id;
    }

    public String getTechnology() {
        return technology;
    }
    
    public String name() {
        return name;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }
}

public class Solution {
    public static Associate[] associatesForGivenTechnology(Associate[] associates, String searchTechnology) {
        return java.util.Arrays.stream(associates)
                .filter(a -> a.getTechnology().equals(searchTechnology) && a.getExperienceInYears() % 5 == 0)
                .toArray(Associate[]::new);
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        Associate[] associates = new Associate[5];
        for (int i = 0; i < 5; i++) {
        	System.out.println("Enter the details: (id,name,technology,experience in years) by spaces");
            int id = sc.nextInt();
            String name = sc.next();
            String technology = sc.next().toLowerCase();
            int experienceInYears = sc.nextInt();
            associates[i] = new Associate(id, name, technology, experienceInYears);
        }

        System.out.println("Enter the search Technology String: ");
        String searchTechnology = sc.next();

        Associate[] result = associatesForGivenTechnology(associates, searchTechnology);

        for (Associate a : result) {
            System.out.println(a.getId());
        }

        sc.close();
    }
}
