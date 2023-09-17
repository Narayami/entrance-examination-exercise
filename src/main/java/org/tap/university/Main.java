package org.tap.university;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = 0;
        try {
            n = scanner.nextInt(); // number of examinees
        } catch (InputMismatchException e) {
            System.out.println("Only numbers accepted");
        }

        scanner.nextLine(); // newline

        int passCount = 0; // number of passed examinees

        boolean isValidInput = false;

        for (int i = 0; i < n; i++) {

            while (!isValidInput) {

                String line = scanner.nextLine(); // Read the input line

                // Split the input line
                String[] subjects = line.split(" ");

                isValidInput = validation(subjects);

                if (isValidInput) {
                    // Extract values from subjects
                    char t = subjects[0].charAt(0); //division: l=humanities or s=science

                    int e = Integer.parseInt(subjects[1]); //English
                    int m = Integer.parseInt(subjects[2]); //Math
                    int s = Integer.parseInt(subjects[3]); //Science
                    int j = Integer.parseInt(subjects[4]); //Japanese
                    int g = Integer.parseInt(subjects[5]); //Geography/History


                    int total = e + m + s + j + g;

                    if (t == 's') {
                        int scienceDivisionTotal = m + s;
                        if (total >= 350 && scienceDivisionTotal >= 160)
                            passCount++;
                    } else if (t == 'l') {
                        int humanitiesDivisionTotal = j + g;
                        if (total >= 350 && humanitiesDivisionTotal >= 160)
                            passCount++;
                    }
                }
            }
            isValidInput = false;
        }

        System.out.println("Number of Passers: " + passCount);

    }

    private static boolean validation(String[] subjects) {

        boolean isValid = false;

        if (subjects.length != 6) {
            System.out.println("There is something wrong. first character should be the division, following by 5 subject scores, all separated by a space.");
            return false;
        }

        if (!subjects[0].equals("s") && !subjects[0].equals("l")) { //check division
            System.out.println("Division must be s or l");
            return false;
        }

        for (int i = 1; i < subjects.length; i++) { //starts on subject score
            try {
                if (Integer.parseInt(subjects[i]) <= 100) {
                    isValid = true; //need to check each val
                } else {
                    System.out.println("Score value cannot be > 100");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("The only character accepted is the division. which is the first one.");
                return false;
            }
        }
        return isValid;
    }
}