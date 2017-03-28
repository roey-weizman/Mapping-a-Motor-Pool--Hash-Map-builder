import java.util.LinkedList;
import java.util.List;
public class HTChaining extends HashTableBGU {

	public HTChaining(Function hashFunction, int size) {
		super(hashFunction, size);
		for(int i=0;i<hashTable.length;i++){
			hashTable[i]=new LinkedList<>();
		}
	}

	public void insert(Object key, Object data) {
		int index = hashFunction.h(key);
		if (getHashTable()[index] == null) {
			getHashTable()[index] = new LinkedList<>();
			((LinkedList) getHashTable()[index]).addFirst(new HashObject(key, data));
		} else
			((LinkedList) getHashTable()[index]).addFirst(new HashObject(key, data));

	}

	public void delete(Object key) {
		int index = hashFunction.h(key);int i=0;
		if (getHashTable()[index] != null) {
			if (!(((LinkedList) (getHashTable()[index])).isEmpty()))
				while(i<(((LinkedList) (getHashTable()[index])).size())){
					if((((LinkedList) (getHashTable()[index])).get(i).equals(key))){
						(((LinkedList)(getHashTable()[index]))).remove(i); return;
					}
				i++;
				//		((LinkedList) getHashTable()[index]).removeFirstOccurrence(key);
		}
			}
	}

	public boolean isEmpty() {
		boolean flag = true;
		for (int i = 0; i < getHashTable().length && flag; i++)
			if (getHashTable()[i] instanceof LinkedList && !((LinkedList)getHashTable()[i]).isEmpty())
				flag = false;
		return flag;
	}

	public Object find(Object key) {
		int  index = hashFunction.h(key);int i=0;
		if ((getHashTable()[index] instanceof LinkedList && !(((LinkedList)getHashTable()[index]).isEmpty()))) {
			while(i<((LinkedList)(getHashTable()[index])).size()){
				if(((LinkedList)(getHashTable()[index])).get(i).equals(key)){
					return ((HashObject)((LinkedList)(getHashTable()[index])).get(i)).getData();
				}
				i++;
			}
		}
		return null;
	}

	
	public Object[] getHashTable() {
		return super.getHashTable();
	}
	public Function getHashFunction(){
		return hashFunction;
	}

}
