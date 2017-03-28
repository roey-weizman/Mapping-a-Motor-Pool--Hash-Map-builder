import java.util.LinkedList;

public class GarageSim {
	private HTChaining carHash;
	private HTChaining placesHash;
	private int minIndex;

	public GarageSim() {
		
		this.carHash = new HTChaining(new modSizeFunction(10000), 10000);
		this.placesHash = new HTChaining(new zeroFunction(), 10000);
		minIndex = -1;// TODO Auto-generated constructor stub
	}

	public void insert(Integer x) {
		if (carHash.find(x) != null)
			System.out.println("car" + " " + x + " " + "is already in the garage");
		else {// the car isn't there
			setMinIndex(0);
			HashObject toInsert= new HashObject(x, new HashObject(x, 0));
			getPlacesHash().insert(toInsert.key,toInsert.data);
			getCarHash().insert(toInsert.key,toInsert.data);
			System.out.println("car " + x + " is inserted");
		}
	}

	public void treat() {
		if (getMinIndex() == -1 || getMinIndex() == (getPlacesHash().getHashTable().length) - 1)
			System.out.println("no cars to treat");
		else {// there are cars in the garage
			((HashObject)((HashObject)((LinkedList) getPlacesHash().getHashTable()[getMinIndex()]).getFirst()).data).data=((Integer)((HashObject)((HashObject)((LinkedList) getPlacesHash().getHashTable()[getMinIndex()]).getFirst()).data).data+1);
			HashObject toTreat = ((HashObject) ((LinkedList) getPlacesHash().getHashTable()[getMinIndex()]).removeFirst());
			int j = ((Integer) ((HashObject) toTreat.data).data);
			((LinkedList) getPlacesHash().getHashTable()[getMinIndex() + 1]).addFirst((HashObject) toTreat);
			if (((LinkedList) getPlacesHash().getHashTable()[getMinIndex()]).isEmpty())
				UpMinIndex();
			System.out.println(
					"car" + " " + ((Integer) ((HashObject) toTreat).key) + " is moved to" + " " + "treatment " + +j);
		}

	}

	public int times(Integer x) {
		HashObject car = ((HashObject) getCarHash().find(x));
		if (car == null) {
			System.out.println("There is no car " + x);
			System.out.println();
			return -1;
		} else {
			int j = ((Integer)car.data);
			System.out.println("car " + x + " passed" + " " + j + " " + "treatments");
			return j;
		}
	}

	

	public int getMinIndex() {
		return minIndex;
	}

	public void setMinIndex(int num) {
		minIndex = num;
	}

	public void UpMinIndex() {
		setMinIndex(minIndex + 1);
		;
	}

	public HTChaining getCarHash() {
		return carHash;
	}

	public HTChaining getPlacesHash() {
		return placesHash;
	}
}
