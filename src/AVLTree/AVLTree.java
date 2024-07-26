package AVLTree;

public class AVLTree {
    Node root ;

    public AVLTree(int value){
        this.root = new Node(value);
    }

    Node insert(Node node, int value){
        if(node == null)
            return new Node(value);
        if(value > node.value)
            node.right = insert(node.right, value) ;
        else
            node.left = insert(node.left, value) ;
        setHeight(node);
        node = balance(node);
        return node ;
    }

    Node balance(Node node){
        if(isLeftHeavy(node)) { // right rotate
            if (balanceFactor(node.left) > 0) // left rotate
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }else if(isRightHeavy(node)){ // left rotate
            if(balanceFactor(node.right) > 0) // right rotate
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node ;
    }

    Node rotateLeft(Node node){
        Node newRoot = node.right ;
        node.right = newRoot.left ;
        newRoot.left = node ;
        setHeight(node);
        setHeight(newRoot);
        return newRoot ;
    }

    Node rotateRight(Node node){
        Node newRoot = node.left ;
        node.left = newRoot.right ;
        newRoot.right = node ;
        setHeight(node);
        setHeight(newRoot);
        return newRoot ;
    }

    void setHeight(Node node){
        node.height = Math.max(node.left.height, node.right.height) + 1 ;
    }

    boolean isLeftHeavy(Node node){
        return balanceFactor(node) > 1 ;
    }

    boolean isRightHeavy(Node node){
        return balanceFactor(node) < -1 ;
    }

    int balanceFactor(Node node){
        return (node == null) ? 0 : node.left.height - node.right.height ;
    }

    void preOrder(Node root){
        if(root == null)
            return ;
        System.out.println(root.value);
        preOrder(root.right);
        preOrder(root.left);
    }

    void inOrder(Node root){
        if(root == null)
            return ;
        inOrder(root.right);
        System.out.println(root.value);
        inOrder(root.left);
    }

    void postOrder(Node root){
        if(root == null)
            return ;
        postOrder(root.right);
        postOrder(root.left);
        System.out.println(root.value);
    }

    boolean isLeaf(Node node){
        return node.right == null && node.left == null ;
    }

    void nodeAtDistance(Node node, int d){
        if(node == null)
            return ;
        if(d == 0)
            System.out.println(node.value);
        else {
            nodeAtDistance(node.right, d - 1);
            nodeAtDistance(node.left, d - 1);
        }
    }

    void levelOrder(){
        for(int i=0 ; i<height(root) ; ++i){
            nodeAtDistance(root, i);
        }
    }

    int height(Node root){
        if(isLeaf(root))
            return 0 ;
        return 1 + Math.max(height(root.right), height(root.right));
    }

    boolean contain(int value){
        Node current = root ;
        while(current != null){
            if(value < current.value)
                current = current.left ;
            else if(value > current.value)
                current = current.right ;
            else
                return true ;
        }
        return false ;
    }

    static class Node{
        int value ;
        Node right ;
        Node left ;
        int height = -1 ;

        public Node(int value){
            this.value = value ;
        }

        public String toString(){
            return "value:" + value ;
        }
    }
}

