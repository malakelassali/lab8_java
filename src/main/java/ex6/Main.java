package ex6;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("My Theatre", 3, 15);
        theatre.printSeatMap();

        System.out.println("Reserve B003 = " + theatre.reserveSeat('B', 3));
        System.out.println("Reserve B003 again = " + theatre.reserveSeat('B', 3));
        System.out.println("Reserve 4 seats in A-C, seats 2-4 = "
                + theatre.reserveSeats(4, 'A', 'C', 2, 4));
        theatre.printSeatMap();

        System.out.println("Reserve nonexistent D001 = " + theatre.reserveSeat('D', 1));
        System.out.println("Reserve 20 seats = " + theatre.reserveSeats(20, 'A', 'C', 1, 5));
        theatre.printSeatMap();
    }
}
