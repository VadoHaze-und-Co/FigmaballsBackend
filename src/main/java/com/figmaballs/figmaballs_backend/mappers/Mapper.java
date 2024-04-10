package com.figmaballs.figmaballs_backend.mappers;

import java.util.Arrays;
import java.util.List;

public class Mapper {

    public String idsToString(List<Long> ids) {
        return String.join(" ", ids.stream().map(Long::toUnsignedString).toList());
    }

    public List<Long> stringToIds(String str) {
        return Arrays.stream(str.split(" ")).map(Long::parseLong).toList();
    }
}
