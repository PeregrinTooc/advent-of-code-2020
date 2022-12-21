package day12;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OpenClosedList<RouteSegment> implements List<RouteSegment> {
    private List<RouteSegment> values = new ArrayList<RouteSegment>();

    @Override
    public boolean contains(Object o) {
        var segment = (day12.RouteSegment) (o);
        for (RouteSegment s : values
        ) {
            if (segment.hasSameEndpointAs(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @NotNull
    @Override
    public Iterator<RouteSegment> iterator() {
        return values.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return values.toArray(a);
    }

    @Override
    public boolean add(RouteSegment routeSegment) {
        return values.add(routeSegment);
    }

    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends RouteSegment> c) {
        return values.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends RouteSegment> c) {
        return values.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return values.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return values.retainAll(c);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public RouteSegment get(int index) {
        return values.get(index);
    }

    @Override
    public RouteSegment set(int index, RouteSegment element) {
        return values.set(index, element);
    }

    @Override
    public void add(int index, RouteSegment element) {
        values.add(index, element);
    }

    @Override
    public RouteSegment remove(int index) {
        return values.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        var result = -1;
        if (o instanceof day12.RouteSegment) {
            for (int i = 0; i < values.size(); i++) {
                day12.RouteSegment segment = (day12.RouteSegment) values.get(i);
                if (segment.hasSameEndpointAs((day12.RouteSegment) (o))) {
                    return i;
                }
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        return values.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<RouteSegment> listIterator() {
        return values.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<RouteSegment> listIterator(int index) {
        return values.listIterator(index);
    }

    @NotNull
    @Override
    public List<RouteSegment> subList(int fromIndex, int toIndex) {
        var result = new OpenClosedList<RouteSegment>();
        result.values = this.values.subList(fromIndex, toIndex);
        return result;
    }
}
