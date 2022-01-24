//package cinema;
import java.util.Scanner;

public class Cinema {

    public static final int SEATS = 60;
    public static final int TICKET_PRICE_FRONT = 10;
    public static final int TICKET_PRICE_BACK = 8;

    public static void printCinema(int numRows, int numSeatsInRow, String[][] arr) {
        System.out.print("Cinema:\n" + " ");
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                for (int j = 0; j < numSeatsInRow; j++) {
                    System.out.print(" " + (j + 1));
                }
                System.out.println();
            }
            for (int j = 0; j < numSeatsInRow; j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " " + arr[i][j] + " ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void fillingCinemaWithSeats(int numRows, int numSeatsInRow, String[][] arr) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeatsInRow; j++) {
                arr[i][j] = "S";
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeatsInEachRow = scanner.nextInt();
        System.out.println();
        String[][] array = new String[numberOfRows][numberOfSeatsInEachRow];
        int totalSeats = numberOfRows * numberOfSeatsInEachRow;
        int income = 0;
        int curIncome = 0;
        float percentage = 0.00f;
        int numberOfPurchased = 0;
        fillingCinemaWithSeats(numberOfRows, numberOfSeatsInEachRow, array);
        boolean loop = true;
        while (loop){
            System.out.println("1. Show the seats" + "\n2. Buy a ticket" + "\n3. Statistics" + "\n0. Exit");
            int choose = scanner.nextInt();
            System.out.println();
            switch (choose) {
                case 0:
                    loop = false;
                    break;
                case 1:
                    printCinema(numberOfRows, numberOfSeatsInEachRow, array);
                    System.out.println();
                    break;
                case 2:
                    boolean reEnter = true;
                    while (reEnter) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();
                        if (rowNumber > numberOfRows || rowNumber < 0 || seatNumber > numberOfSeatsInEachRow || seatNumber < 0) {
                            System.out.println("Wrong input!");
                        } else if ("B".equals(array[rowNumber - 1][seatNumber - 1])) {
                            System.out.println();
                            System.out.println("That ticket has already been purchased!");
                            System.out.println();
                        } else {
                            if (totalSeats <= SEATS) {
                                System.out.println();
                                System.out.println("Ticket price: $" + TICKET_PRICE_FRONT);
                                curIncome += TICKET_PRICE_FRONT;
                            } else {
                                System.out.println();
                                if (rowNumber > numberOfRows / 2) {
                                    System.out.println("Ticket price: $" + TICKET_PRICE_BACK);
                                    curIncome += TICKET_PRICE_BACK;
                                } else {
                                    System.out.println("Ticket price: $" + TICKET_PRICE_FRONT);
                                    curIncome += TICKET_PRICE_FRONT;
                                }
                            }
                            System.out.println();
                            for (int i = 0; i < numberOfRows; i++) {
                                for (int j = 0; j < numberOfSeatsInEachRow; j++) {
                                    if (i + 1 == rowNumber && j + 1 == seatNumber) {
                                        array[i][j] = "B";
                                        reEnter = false;
                                    }
                                }
                            }
                            numberOfPurchased++;
                        }
                    }
                    break;
                case 3:
                    if (totalSeats <= SEATS) {
                        income = TICKET_PRICE_FRONT * totalSeats;
                    } else {
                        if (numberOfRows % 2 == 0) {
                            income = (TICKET_PRICE_FRONT * (numberOfRows / 2) + TICKET_PRICE_BACK * (numberOfRows / 2)) * numberOfSeatsInEachRow;
                        } else {
                            income = (TICKET_PRICE_FRONT * (numberOfRows / 2) + TICKET_PRICE_BACK * ((numberOfRows + 1) / 2)) * numberOfSeatsInEachRow;
                        }
                    }
                    float numberOfPurchasedF = numberOfPurchased;
                    float totalSeatsF = totalSeats;
                    percentage = numberOfPurchasedF / totalSeatsF * 100;
                    System.out.println(totalSeats);
                    String formattedDouble = String.format("%.2f", percentage).replace(",", ".");
                    System.out.println("Number of purchased tickets: " + numberOfPurchased);
                    System.out.println("Percentage: " + formattedDouble + "%");
                    System.out.println("Current income: $" + curIncome);
                    System.out.println("Total income: $" + income);
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
    }
}