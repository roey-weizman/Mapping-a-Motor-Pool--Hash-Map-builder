/**
 * Created by Dolav on 03/09/2015.
 */
public abstract class OpenAddressingBGU extends HashTableBGU{
    boolean[] flags;
    public OpenAddressingBGU(Function hashFunction, int size) {
        super(hashFunction, size);
        flags = new boolean[size];
    }
    
}
