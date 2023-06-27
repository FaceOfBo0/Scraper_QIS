package helper;

import data.Lecture;
import java.util.*;

public class DayComparator implements Comparator<Lecture> {

    Set<List<String>> lessDaySet = new HashSet<>(Arrays.asList(Arrays.asList("Mo", "Di"),
            Arrays.asList("Mo", "Mi"), Arrays.asList("Mo", "Do"),Arrays.asList("Mo", "Fr"),
            Arrays.asList("Di", "Mi"),Arrays.asList("Di", "Do"),Arrays.asList("Di", "Fr"),
            Arrays.asList("Mi", "Do"),Arrays.asList("Mi", "Fr"),Arrays.asList("Do", "Fr")));

    private boolean lessDay (Lecture o1, Lecture o2){
        if (Objects.equals(o1.getDay(), "Block") || Objects.equals(o1.getDay(), "n.a.")) return false;
        else if (Objects.equals(o2.getDay(), "Block") || Objects.equals(o2.getDay(), "n.a.")) return true;
        else {
            List<String> dayTuple = new ArrayList<>(0);
            dayTuple.add(o1.getDay());
            System.out.println("o1 day: " + o1.getDay());
            System.out.println("o1 title: " + o1.getTitle());
            dayTuple.add(o2.getDay());
            System.out.println("o2 day: " + o2.getDay());
            System.out.println("o2 title: " + o2.getTitle());
            return this.lessDaySet.contains(dayTuple);
        }
    }

    private boolean lessTime (Lecture o1, Lecture o2){
        if (Objects.equals(o1.getTime(), "n.a.")) return false;
        else if (Objects.equals(o2.getTime(), "n.a.")) return true;
        else {
            int o1Time = Integer.parseInt(o1.getTime().substring(0,2));
            int o2Time = Integer.parseInt(o2.getTime().substring(0,2));
            return o1Time < o2Time;
        }
    }
    private int compareDay(Lecture o1, Lecture o2) {
        if (this.lessDay(o1,o2) || (Objects.equals(o1.getDay(), o2.getDay()) && this.lessTime(o1,o2))) return -1;
        else return 1;
    }


    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * The implementor must ensure that {@link Integer#signum
     * signum}{@code (compare(x, y)) == -signum(compare(y, x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * compare(x, y)} must throw an exception if and only if {@code
     * compare(y, x)} throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.<p>
     * <p>
     * Finally, the implementor must ensure that {@code compare(x,
     * y)==0} implies that {@code signum(compare(x,
     * z))==signum(compare(y, z))} for all {@code z}.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     * @apiNote It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     */
    @Override
    public int compare(Lecture o1, Lecture o2) {
        return compareDay(o1,o2);
    }

}
