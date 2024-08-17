package Graph;

import java.util.*;

public class Graph {
    Map<String, Node> nodes = new HashMap<>();
    Map<Node, List<Node>> adjacencyList = new HashMap<>();

    static class Node {
        String label ;

        public Node(String label){
            this.label = label ;
        }

        public String toString(){
            return label ;
        }
    }

    void addNode(String label){
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    void addEdge(String from, String to){
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if(fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    void removeNode(String label){
        Node node = nodes.get(label);
        if(node == null)
            return;
        for(Node n : adjacencyList.keySet())
            adjacencyList.get(n).remove(node);

        nodes.remove(node);
        adjacencyList.remove(node);
    }

    void removeEdge(String from, String to){
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if(fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    void depthFirst(String root){
        Node node = nodes.get(root);
        if(node == null)
            return;
        depthFirst(node, new HashSet<>());
    }

    void depthFirst(Node node, Set<Node> visited){
        System.out.println(node);
        visited.add(node);
        for(Node n: adjacencyList.get(node)){
            if(!visited.contains(n))
                depthFirst(n, visited);
        }
    }

    void depthFirstITE(String root){
        Node node = nodes.get(root);
        if(node == null)
            return;

        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();
        stack.push(node);

        while(!stack.isEmpty()){
            Node current = stack.pop();
            if(visited.contains(current))
                continue;
            System.out.println(current);
            visited.add(current);
            for(Node neighbor : adjacencyList.get(current)){
                if(!visited.contains(neighbor))
                    stack.push(neighbor);
            }
        }
    }

    void breathFirstITE(String root){
        Node node = nodes.get(root);
        if(node == null)
            return;

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            if(visited.contains(current))
                continue;
            System.out.println(current);
            visited.add(current);

            for(Node neighbor : adjacencyList.get(current)){
                if(!visited.contains(neighbor))
                    queue.add(neighbor);
            }
        }
    }

    boolean hasCycle(){
        Set<Node> all = new HashSet<>();
        all.addAll(nodes.values());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        while(!all.isEmpty()){
            Node current = all.toArray(new Node[0])[0];
            if(hasCycle(current, all, visiting, visited))
                return true;
        }
        return false;
    }

    boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited){
        all.remove(node);
        visited.add(node);

        for(Node neighbor : adjacencyList.get(node)){
            if(visited.contains(neighbor))
                continue;
            if(visiting.contains(neighbor))
                return true;
            if(hasCycle(neighbor, all, visiting, visited))
                return true ;
        }
        visiting.remove(node);
        visited.add(node);

        return false ;
    }

    void print(){
        for(Node source: adjacencyList.keySet()){
            List<Node> targets = adjacencyList.get(source);
            if(!targets.isEmpty())
                System.out.println(source + "is connected to " + targets);
        }
    }
}
