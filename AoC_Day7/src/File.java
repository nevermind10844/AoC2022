
public class File {
	private String name;
	private int size;
	private Folder parent;

	public File(String name, int size, Folder parent) {
		this.name = name;
		this.size = size;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", size=" + size + "]";
	}

}
