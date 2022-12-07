package day7;

import java.util.ArrayList;
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
            // if (s.matches("dir ")) {
            // currentDir.addDirectory(new Directory(s));
            // }
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
        public void getSize(Accumulator AccuIn) {
            root.getSize(AccuIn);
        }

        @Override
        public Directory navigateTo(String directoryName) {
            return root.navigateTo(directoryName);
        }
    }

    public class Directory implements Sizeable, Navigatable {

        private List<File> files = new ArrayList<File>();

        public Directory(String s) {
        }

        @Override
        public Directory navigateTo(String directoryName) {
            return null;
        }

        public void addDirectory(Directory directory) {
        }

        public void addFile(File file) {
            this.files.add(file);
        }

        @Override
        public void getSize(Accumulator accuIn) {
            for (File file : files) {
                file.getSize(accuIn);
            }
        }

    }

    public class File {
        private Integer size;

        public File(String file) {
            this.size = Integer.valueOf(file.split(" ")[0]);
        }

        public void getSize(Accumulator accuIn) {
            accuIn.incrementBy(size);
        }
    }

    public Object solve1(FileSystem fileSystem) {
        return null;
    };

}
