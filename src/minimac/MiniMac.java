package minimac;

import tools.Publisher;

import java.io.Serializable;
public class MiniMac extends Publisher implements Serializable {
    int size = 32;
    Integer[] memory = new Integer[size];
}
