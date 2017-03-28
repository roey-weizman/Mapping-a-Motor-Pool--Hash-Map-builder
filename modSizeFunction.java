
public class modSizeFunction implements Function {
	private int size;

	public modSizeFunction(int size) {
		this.size = size;
	}

	@Override
	public int h(Object x) {
		return (Integer) x % getSize();
	}

	public int getSize() {
		return size;
	}
}
