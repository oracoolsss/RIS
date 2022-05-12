package OSM;

import lombok.Getter;

@Getter
public class User implements Comparable<User> {
    private String name;
    private int changeCount;

    public User(String name, int changeCount) {
        this.name = name;
        this.changeCount = changeCount;
    }

    @Override
    public String toString() {
        return name + " " + changeCount;
    }

    @Override
    public int compareTo(User o) {
        int dif = changeCount - o.changeCount;
        if (dif < 0) {
            return -1;
        } else if (dif > 0) {
            return  1;
        }
        return 0;
    }
}