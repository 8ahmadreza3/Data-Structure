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

    Map shortestPath(String root){
        Node fromNode = nodes.get(root);
        if(fromNode == null)
            throw new IllegalArgumentException();

        Map<Node, Integer> distances = new HashMap<>();
        for(Node node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);

        distances.replace(fromNode, 0);
        Set<Node> visited = new HashSet<>();
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.priority)
        );
        queue.add(new NodeEntry(fromNode, 0));
        while(!queue.isEmpty()){
            Node current = queue.remove().node ;
            visited.add(current);
            for(Edge edge: current.edges){
                if(visited.contains(edge.to))
                    continue;

                int newDistance = distances.get(current) + edge.weight;
                if(newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to, newDistance);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }
        return distances;
    }

    public WeightedGraph getMST(){
        WeightedGraph tree = new WeightedGraph();
        PriorityQueue<Edge> edges = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight)
        );
        Node startNode = nodes.values().toArray(new Node[0])[0];
        edges.addAll(startNode.edges);
        tree.addNode(startNode.label);

        while(tree.nodes.size() < nodes.size()){
            Edge minEdge = edges.remove();
            Node nextNode = minEdge.to ;
            if(tree.nodes.containsKey(nextNode.label))
                continue;

            tree.addNode(nextNode.label);
            tree.addEdge(minEdge.from.label, nextNode.label, minEdge.weight);

            for(Edge edge: nextNode.edges){
                if(!tree.nodes.containsKey(edge.to.label))
                    edges.add(edge);
            }
        }
        return tree;
    }

    void print(){
        for(Node node: nodes.values()){
            List<Edge> targets = node.edges;
            if(!targets.isEmpty())
                System.out.println(node + "is connected to " + targets);
        }
    }
}
