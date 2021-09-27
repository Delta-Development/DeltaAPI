package club.deltapvp.deltacore.api.datastructure;

/**
 *
 * @param <T> Type of Data
 * @author Negative
 * @since September 27th, 2021
 *
 * This data class stored one value which can be changed
 */
public class StoredData<T> {

    private T data;

    public StoredData(T data) {
        this.data = data;
    }

    public T get() {
        return data;
    }

    public void set(T data) {
        this.data = data;
    }
}
