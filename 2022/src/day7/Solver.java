package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Solver {

    private String[] input;

    public Solver(String[] input) {
        this.input = input;
    }

    public Object solve1(FileSystem fileSystem) {
        Solver.SizeLimitedInfoCollector infoCollector = new Solver.SizeLimitedInfoCollector();
        MapCreatorImpl mapCreator = new MapCreatorImpl();
        fileSystem.createMap(mapCreator);
        while (mapCreator.iterator().hasNext()) {
            var directorySizeInfoCollector = new Accumulator();
            mapCreator.iterator().next().getSize(directorySizeInfoCollector);
            directorySizeInfoCollector.size(infoCollector);
        }
        return infoCollector.getSize();
    };

    public int solve2(FileSystem fileSystem) {
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        MapCreatorImpl mapCreator = new MapCreatorImpl();
        fileSystem.createMap(mapCreator);
        Accumulator infoCollector = new Accumulator();
        fileSystem.getRoot().getSize(infoCollector);
        while (mapCreator.hasNext()) {
            Accumulator directorySizeInfo = new Accumulator();
            mapCreator.next().getSize(directorySizeInfo);
            if (70000000 - infoCollector.getSize() + directorySizeInfo.getSize() > 30000000) {
                sizes.add(directorySizeInfo.getSize());
            }
        }
        sizes.sort(null);
        return sizes.get(0);
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

    public class MapCreatorImpl implements MapCreator, Iterator<Directory> {
        private List<Directory> directories = new ArrayList<Directory>();
        private ListIterator<Directory> iterator = directories.listIterator();

        @Override
        public Iterator<Directory> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasPrevious();
        }

        @Override
        public Directory next() {
            return iterator.previous();
        }

        @Override
        public void addDirectory(Directory dir) {
            iterator.add(dir);
        }

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

        @Override
        public void createMap(MapCreator MapCreator) {
            this.root.createMap(MapCreator);
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
            files.forEach(file -> file.getSize(infoCollector));
            subDirectories.forEach((name, dir) -> dir.getSize(infoCollector));
        }

        @Override
        public void createMap(MapCreator MapCreator) {
            MapCreator.addDirectory(this);
            subDirectories.forEach((name, dir) -> dir.createMap(MapCreator));
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

    public class SizeLimitedInfoCollector implements SizableInfo {
        private int size = 0;

        @Override
        public void size(Integer size) {
            this.size += (size < 100000 ? size : 0);
        }

        public int getSize() {
            return size;
        }

        @Override
        public void size(SizableInfo sizableInfo) {
            sizableInfo.size(size);
        }
    }

}
