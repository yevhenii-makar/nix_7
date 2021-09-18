package com.yevheniimakar.uniquename;

import java.util.*;
import java.util.stream.Collectors;


public class UniqueName {

    public String getUniqueNameUsingLoop(List<String> namesList) {

        String name;
        Set<String> notUniqueName = new HashSet<String>();
        Set<String> uniqueName = new LinkedHashSet<String>();
        for (int i = 0; i < namesList.size(); i++) {
            name = namesList.get(i);
            if (!notUniqueName.contains(name)) {
                if (uniqueName.contains(name)) {
                    uniqueName.remove(name);
                    notUniqueName.add(name);
                } else {
                    uniqueName.add(name);
                }
            }
        }

        if (uniqueName.size() > 0) {
            return uniqueName.iterator().next();
        }

        return "Names not found";
    }

    public String getUniqueNameUsingStream(final List<String> namesList) {
        return namesList.stream().filter(name -> Collections.frequency(namesList, name) == 1).collect(Collectors.toList()).get(0);
    }

}
