import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Folder {
	private String name;
	private List<Folder> folders;
	private List<File> files;
	private int size;
	private Folder parent;
	
	public Folder(String name, Folder parent) {
		this.name = name;
		this.folders = new ArrayList<>();
		this.files = new ArrayList<>();
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
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
	
	public boolean createFolder(String name) {
		if(this.folders.stream().map(Folder::getName).toList().contains(name))
			return false;
		else
			this.folders.add(new Folder(name, this));
		return true;
	}
	
	public Folder getFolder(String name) {
		Optional<Folder> optFolder = this.folders.stream().filter(folder -> folder.getName().equals(name)).findFirst();
		if(optFolder.isPresent())
			return optFolder.get();
		else
			return null;
	}
	
	public boolean createFile(String name, int size) {
		if(this.files.stream().map(File::getName).toList().contains(name))
			return false;
		else
			this.files.add(new File(name, size, this));
		return true;
	}
	
	@Override
	public String toString() {
		return "Folder [name=" + name + ", folders=" + folders + ", files=" + files + ", size=" + size + "]";
	}
}
