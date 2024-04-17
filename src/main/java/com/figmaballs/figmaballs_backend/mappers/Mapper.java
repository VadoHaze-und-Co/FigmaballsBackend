package com.figmaballs.figmaballs_backend.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapper {

    public String idsToString(List<Long> ids) {
        if (ids.isEmpty()) {
            return "";
        }
        return String.join(" ", ids.stream().map(Long::toUnsignedString).toList());
    }

    public List<Long> stringToIds(String str) {
        if (str.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(" ")).map(Long::parseLong).toList();
    }
}
