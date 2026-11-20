package ex6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {
    private final String theatreName;
    private final int seatsPerRow;
    private final TreeSet<Seat> seats= new TreeSet<>();

    public Theatre(String theatreName, int rows, int totalSeats) {
        if (rows < 1 || rows > 26) {
            throw new IllegalArgumentException("Rows must be between 1 and 26");
        }
        if (totalSeats < rows || totalSeats % rows != 0) {
            throw new IllegalArgumentException("Each row must have the same number of seats");
        }

        this.theatreName= theatreName;
        seatsPerRow= totalSeats/rows;
        for (int row=0; row<rows; row++) {
            for (int number=1; number<=seatsPerRow; number++) {
                seats.add(new Seat((char) ('A' + row), number));
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public boolean reserveSeat(char row, int number) {
        if (row<'A' || row>'Z' || number<1 || number>seatsPerRow) {
            return false;
        }

        Seat requested= new Seat(row, number);
        Seat seat= seats.ceiling(requested);
        if (seat==null || seat.compareTo(requested)!=0 || seat.isReserved()) {
            return false;
        }
        seat.setReserved(true);
        return true;
    }

    public boolean reserveSeats(int count, char fromRow, char toRow,
                                int fromSeat, int toSeat) {
        if (count<1 || fromRow<'A' || fromRow>toRow || toRow>'Z'
                || fromSeat<1 || fromSeat>toSeat || toSeat>seatsPerRow
                || seats.isEmpty() || toRow>seats.last().getSeatNumber().charAt(0)) {
            return false;
        }

        List<Seat> available= new ArrayList<>();
        for (Seat seat : seats) {
            char row= seat.getSeatNumber().charAt(0);
            int number= Integer.parseInt(seat.getSeatNumber().substring(1));
            if (row>=fromRow && row<=toRow && number>=fromSeat
                    && number<=toSeat && !seat.isReserved()) {
                available.add(seat);
                if (available.size()==count) {
                    break;
                }
            }
        }

        if (available.size()<count) {
            return false;
        }
        for (Seat seat : available) {
            seat.setReserved(true);
        }
        return true;
    }

    public void printSeatMap() {
        System.out.println(theatreName + " (* reserved)");
        char currentRow= 0;
        for (Seat seat : seats) {
            char row= seat.getSeatNumber().charAt(0);
            if (currentRow!=0 && row!=currentRow) {
                System.out.println();
            }
            System.out.print(seat.getSeatNumber() + (seat.isReserved() ? "* " : "  "));
            currentRow= row;
        }
        System.out.println();
    }

    public static class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved;

        public Seat(char row, int number) {
            seatNumber= String.format(Locale.ROOT, "%c%03d", row, number);
            reserved= false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved= reserved;
        }

        @Override
        public int compareTo(Seat other) {
            return seatNumber.compareTo(other.seatNumber);
        }
    }
}
