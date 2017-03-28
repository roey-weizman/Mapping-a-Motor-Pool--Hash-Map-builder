public class HTDoubleHashing extends OpenAddressingBGU {

	protected Function stepHashFunction;

	public HTDoubleHashing(Function hashFunction, Function stepHashFunction, int size) {
		super(hashFunction, size);

		this.stepHashFunction = stepHashFunction;
	}

	public void insert(Object key, Object data) {
		if (canReach()) {
			int index = hashFunction.h(key);
			int i = 0;
			int m = gethashTable().length;
			while (canReach()) {
				int j = (index + i * stepHashFunction.h(key)) % m;
				if ((gethashTable()[j] == null) || gethashTable()[j] instanceof Deleted) {
					gethashTable()[j] = new HashObject(key, data);
					flags[j] = false;
					return;
				}
				i++;
			}
		}

	}

	public void delete(Object key) {
		int index = hashFunction.h(key);
		int m=gethashTable().length;
		while (canReach()) {
			if ((gethashTable()[index] == null) || getFlags()[index] == true)
				return;
			else {
				if (getHashTable()[index].equals(key)) {
					getFlags()[index] = true;
					gethashTable()[index] = new Deleted(getFlags()[index]);
					return;
				}
				index=(index+stepHashFunction.h(key))%m;
			}
		}
	}

	public boolean isEmpty() {
		boolean flag = true;
		for (int i = 0; i < gethashTable().length && flag; i++) {
			if (gethashTable()[i] instanceof HashObject)
				flag = false;
		}
		return flag;

	}

	public Object find(Object key) {
		int m = gethashTable().length;
		int index = hashFunction.h(key);
		for (int i = 0; i < m; i++) {
			int j = (index + i * stepHashFunction.h(key))%m;
			if (gethashTable()[j] instanceof HashObject) {
				if (((HashObject) (gethashTable()[j])).getKey().equals(key))
					return ((HashObject) gethashTable()[j]).getData();
			}
			if (gethashTable()[j] == null)
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

	public Object[] gethashTable() {
		return hashTable;
	}

	public boolean[] getFlags() {
		return flags;
	}
}
