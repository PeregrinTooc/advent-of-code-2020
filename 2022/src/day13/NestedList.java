package day13;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class NestedList {
    private final List innerList;


    private NestedList(List list) {
        this.innerList = list;
    }

    private NestedList(Integer listItem) {
        this.innerList = Arrays.asList(new Integer[]{listItem});
    }

    public static NestedList create(String serialized) {
        try {
            return new NestedList(new ObjectMapper().readValue(serialized, List.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(innerList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSmallerThan(NestedList other) {
        var otherInnerList = other.innerList;
        {
            for (int i = 0; i < innerList.size(); i++) {
                if (i >= otherInnerList.size())
                    return false;

                Object thisListItem = innerList.get(i);
                Object otherListItem = otherInnerList.get(i);
                if (thisListItem instanceof Integer && otherListItem instanceof Integer) {
                    var x = (Integer) thisListItem;
                    var y = (Integer) otherListItem;
                    if (x < y)
                        return true;
                    if (y < x)
                        return false;
                    continue;
                }
                if (thisListItem instanceof List && otherListItem instanceof List) {
                    var x = new NestedList((List) thisListItem);
                    var y = new NestedList((List) otherListItem);
                    if (x.isSmallerThan(y))
                        return true;
                    if (y.isSmallerThan(x))
                        return false;
                    continue;
                }
                if (thisListItem instanceof List) {
                    var x = new NestedList((List) thisListItem);
                    var y = new NestedList((Integer) otherListItem);
                    if (x.isSmallerThan(y))
                        return true;
                    if (y.isSmallerThan(x))
                        return false;
                    continue;
                }
                if (thisListItem instanceof Integer) {
                    var x = new NestedList((Integer) thisListItem);
                    var y = new NestedList((List) otherListItem);
                    if (x.isSmallerThan(y))
                        return true;
                    if (y.isSmallerThan(x))
                        return false;
                    continue;
                }
            }
            if (this.innerList.size() < otherInnerList.size())
                return true;
            return false;
        }

    }
}

