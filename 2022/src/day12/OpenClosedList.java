package day12;

import java.util.*;

public class OpenClosedList<T> implements List<T> {
    private List<T> values = new ArrayList<T>();

    @Override
    public boolean contains(Object o) {
        var segment = (day12.RouteSegment) (o);
        for (T s : values) {
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

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return values.toArray(a);
    }

    @Override
    public boolean add(T routeSegment) {
        return values.add(routeSegment);
    }

    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return values.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return values.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return values.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return values.retainAll(c);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public T get(int index) {
        return values.get(index);
    }

    @Override
    public T set(int index, T element) {
        return values.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        values.add(index, element);
    }

    @Override
    public T remove(int index) {
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

    @Override
    public ListIterator<T> listIterator() {
        return values.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return values.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        var result = new OpenClosedList<T>();
        result.values = this.values.subList(fromIndex, toIndex);
        return result;
    }
}
