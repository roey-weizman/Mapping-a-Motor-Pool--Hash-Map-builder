public class HTLinearProbing extends OpenAddressingBGU {

	public HTLinearProbing(Function hashFunction, int size) {
		super(hashFunction, size);
		flags = new boolean[size];
	}

	public void insert(Object key, Object data) {
		if (canReach()) {
			int index = hashFunction.h(key);
			int m = getbHashTable().length;
			for (int i = 0; i < m; i++) {
				int j = index + i % m;
				if ((getbHashTable()[j] == null) || getbHashTable()[j] instanceof Deleted) {
					getbHashTable()[j] = new HashObject(key, data);
					flags[j] = false;
					return;
				}

			}
			System.out.println("hash table is full!!,no  object has been inserted");
		}
	}

	public void delete(Object key) {
		int index = hashFunction.h(key);
		int m = getbHashTable().length;
		while (index < m) {
			if ((getbHashTable()[index] == null) || getFlags()[index] == true)
				return;
			else {
				if (getbHashTable()[index].equals(key)) {
					getFlags()[index] = true;
					getbHashTable()[index] = new Deleted(getFlags()[index]);
					return;
				}
				index++;
			}
		}
	}

	public boolean isEmpty() {
		boolean flag = true;
		for (int i = 0; i < getbHashTable().length && flag; i++) {
			if (getbHashTable()[i] instanceof HashObject)
				flag = false;
		}
		return flag;
	}

	public Object find(Object key) {
		int m = getbHashTable().length;
		int index = hashFunction.h(key);
		for (int i = 0; i < m; i++) {
			int j = index + i % m;
			if (getbHashTable()[j] instanceof HashObject) {
				if (((HashObject) (getbHashTable()[j])).getKey().equals(key))
					return ((HashObject) getbHashTable()[j]).getData();
			}
			if (getbHashTable()[j] == null)
				return null;
		}
		return null;
	}

	private boolean canReach() {
		boolean flag = false;
		for (int i = 0; i < hashTable.length && !flag; i++) {
			if (hashTable[i] == null)
				flag = true;
		}
		return flag;
	}

	public Object[] getbHashTable() {
		return hashTable;
	}

	public boolean[] getFlags() {
		return flags;
	}
}
