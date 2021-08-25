package club.deltapvp.deltacore.api.utilities;

public interface TimeConversion {

    String format(long l1, long l2);

    String format(long l1, long l2, boolean shortened);

    Long fromString(String string);

}
