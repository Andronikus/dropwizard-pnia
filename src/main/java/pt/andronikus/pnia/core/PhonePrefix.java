package pt.andronikus.pnia.core;

import java.util.HashSet;

public enum PhonePrefix {
    INSTANCE;

    private final HashSet<String> prefixList = new HashSet<>();

    public void addPrefix(String prefix) {
        this.prefixList.add(prefix);
    }

    public boolean prefixMatch(String prefix){
        return this.prefixList.contains(prefix);
    }

    public HashSet<String> getPrefixList() {
        return prefixList;
    }
}
