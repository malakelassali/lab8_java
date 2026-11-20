package ex6;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {
    private final Set<Seat> seats= new TreeSet<>();

    public Set<Seat> getSeats() {
        return seats;
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
