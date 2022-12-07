import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> commands = LineReader.read();

		Folder systemRoot = new Folder("/", null);

		Folder current = systemRoot;

		for (String cmd : commands) {
			String[] splitCmd = cmd.split(" ");
			switch (splitCmd[0]) {
			case "$":
				switch (splitCmd[1]) {
				case "cd":
					if ("..".equals(splitCmd[2]))
						current = current.getParent();
					else if ("/".equals(splitCmd[2])) {
						current = systemRoot;
					} else {
						current.createFolder(splitCmd[2]);
						current = current.getFolder(splitCmd[2]);
					}
					break;
				case "ls":
					break;
				}
				break;
			case "dir":
				current.createFolder(splitCmd[1]);
				break;
			default:
				current.createFile(splitCmd[1], Integer.valueOf(splitCmd[0]));
				break;
			}
		}

		List<Folder> allFolders = getAllFolders(systemRoot, new ArrayList<Folder>());
		allFolders.add(systemRoot);

		for (Folder folder : allFolders) {
			List<File> files = getAllFiles(folder, new ArrayList<File>());
			folder.setSize(files.stream().mapToInt(file -> file.getSize()).sum());
		}

		int totalSpaceUsed = systemRoot.getSize();
		int totalSpace = 70000000;
		int freeSpace = totalSpace - totalSpaceUsed;
		int spaceNeeded = 30000000;
		int minimumSpaceToFree = spaceNeeded - freeSpace;

		allFolders.sort(Comparator.comparingInt(Folder::getSize));
		Folder deleteThisOne = allFolders.stream().filter(folder -> folder.getSize() >= minimumSpaceToFree).findFirst()
				.get();

		System.out.println(deleteThisOne);
	}

	public static void printify(Folder folder, String indent) {
		System.out.println(indent + folder.getName() + "(" + folder.getSize() + ")");
		for (Folder f : folder.getFolders()) {
			printify(f, "  " + indent);
		}
		for (File f : folder.getFiles()) {
			System.out.println("  " + indent + f.getName() + " (" + f.getSize() + ")");
		}
	}

	public static List<File> getAllFiles(Folder folder, List<File> files) {
		files.addAll(folder.getFiles());
		for (Folder f : folder.getFolders()) {
			getAllFiles(f, files);
		}
		return files;
	}

	public static List<Folder> getAllFolders(Folder folder, List<Folder> folders) {
		folders.addAll(folder.getFolders());
		for (Folder f : folder.getFolders()) {
			getAllFolders(f, folders);
		}
		return folders;
	}

}
