package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solver {

    private String[] input;

    public Solver(String[] input) {
        this.input = input;
    }

    public Solver.FileSystem parseAndGetFileSystem() {
        FileSystem fileSystem = new FileSystem();
        var currentDir = fileSystem.addRoot();
        for (int i = 2; i < input.length; i++) {
            String s = input[i];
            if (s.matches("[0-9](.*)")) {
                currentDir.addFile(new File(s));
            }
            if (s.matches("dir (.*)")) {
                currentDir.addDirectory(s.split(" ")[1]);
            }
            if (s.matches("\\$ cd (.*)")) {
                currentDir = currentDir.navigateTo(s.split(" ")[2]);
            }
        }
        return fileSystem;
    }

    public class FileSystem implements Sizeable, Navigatable {

        private Directory root;

        public Directory getRoot() {
            return root;
        }

        public Directory addRoot() {
            this.root = new Directory("/");
            return root;
        }

        @Override
        public void getSize(SizableInfo infoCollector) {
            root.getSize(infoCollector);
        }

        @Override
        public Directory navigateTo(String directoryName) {
            return root.navigateTo(directoryName);
        }
    }

    public class Directory implements Sizeable, Navigatable {

        private List<File> files = new ArrayList<File>();
        private HashMap<String, Directory> subDirectories = new HashMap<String, Directory>();
        private Directory superDirectory;

        public Directory(String s) {
        }

        public Directory(String s, Directory superDirectory) {
            this.superDirectory = superDirectory;

        }

        @Override
        public Directory navigateTo(String directoryName) {
            if (directoryName.equals("..")) {
                return superDirectory;
            }
            return subDirectories.get(directoryName);
        }

        public Directory addDirectory(String name) {
            Directory sub = new Directory(name, this);
            subDirectories.put(name, sub);
            return sub;
        }

        public void addFile(File file) {
            this.files.add(file);
        }

        @Override
        public void getSize(SizableInfo infoCollector) {
            for (File file : files) {
                file.getSize(infoCollector);
            }
            // files.forEach(file -> file.getSize(infoCollector));
            subDirectories.forEach((name, dir) -> dir.getSize(infoCollector));
        }

    }

    public class File {
        private Integer size;

        public File(String file) {
            this.size = Integer.valueOf(file.split(" ")[0]);
        }

        public void getSize(SizableInfo accuIn) {
            accuIn.size(size);
        }
    }

    public Object solve1(FileSystem fileSystem) {
        return null;
    };

}
