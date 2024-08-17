package Graph;

import java.util.*;

public class WeightedGraph {
    Map<String, Node> nodes = new HashMap<>();

    static class Node {
        String label ;
        List<Edge> edges = new ArrayList<>();

        public Node(String label){
            this.label = label ;
        }

        void addEdge(Node to, int weight){
            edges.add(new Edge(this, to, weight));
        }

        public String toString(){
            return label ;
        }
    }

    static class Edge{
        Node from;
        Node to;
        int weight;

        public Edge(Node from, Node to, int weight){
            this.from = from ;
            this.to = to ;
            this.weight = weight;
        }

        public String toString() {
            return from + "->" + to + " with weight : " + weight;
        }
    }

    static class NodeEntry{
        Node node ;
        int priority ;

        public NodeEntry(Node node, int priority){
            this.node = node ;
            this.priority = priority ;
        }
    }

    void addNode(String label){
        nodes.putIfAbsent(label, new Node(label));
    }

    void addEdge(String from, String to, int weight){
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if(fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    void print(){
        for(Node node: nodes.values()){
            List<Edge> targets = node.edges;
            if(!targets.isEmpty())
                System.out.println(node + "is connected to " + targets);
        }
    }
}
