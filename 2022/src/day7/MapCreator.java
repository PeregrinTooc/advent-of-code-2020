package day7;

import day7.Solver.Directory;

public interface MapCreator extends Iterable<Solver.Directory> {

    void addDirectory(Directory dir);

}
