package com.howardlewisship;

import java.util.*;

public class MapUtils {
  public static String sortedKeyList(Map<?, ?> map1, Map<?, ?> map2) {

    Set<Object> allKeys = new HashSet<Object>(map1.keySet());
    allKeys.addAll(map2.keySet());

    if (allKeys.isEmpty()) {
      return "<none>";
    }

    List<String> sortableKeys = new ArrayList<String>();

    for (Object k : allKeys) {
      sortableKeys.add(k.toString());
    }

    Collections.sort(sortableKeys);

    StringBuilder builder = new StringBuilder(100);
    boolean first = true;

    for (String s : sortableKeys) {
      if (!first) {
        builder.append(", ");
      }

      builder.append(s);

      first = false;
    }

    return builder.toString();
  }
}
