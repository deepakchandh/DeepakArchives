package test.com.java.craft;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String name;
    private Node parent;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void removeChild(Node child) {
        child.setParent(null);
        this.children.remove(child);
    }
}
