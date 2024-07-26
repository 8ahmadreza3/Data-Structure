package Heap;

public class Heap {
    int[] items ;
    int size = 0 ;

    public Heap(int n){
        items = new int[n];
    }

    void insert(int value){
        if(isFull())
            throw new IllegalArgumentException() ;
        items[size++] = value ;
        int index = size -1 ;

        while(index >= 0 && items[index] > items[parent(index)]){
            swap(index, parent(index));
            index = parent(index) ;
        }
    }

    boolean isFull(){
        return size == items.length ;
    }

    int parent(int index){
        return (index-1) /2 ;
    }

    void remove(){
        if (size == 0)
            throw new IllegalArgumentException();
        items[0] = items[--size];
        int index = 0 ;
        while (index <= size && !isValidParent(index)){
            int larger = largeChild(index) ;
            swap(larger, index);
            index = larger ;
        }
    }

    int largeChild(int index){
        if(!hasLeftChild(index))
            return index ;
        if(!hasRightChild(index))
            return leftChild(index) ;
        return (items[leftChild(index)] > items[rightChild(index)]) ? leftChild(index) : rightChild(index) ;
    }

    boolean isValidParent(int index){
        if(!hasLeftChild(index))
            return true ;
        if(!hasRightChild(index))
            return items[index] >= items[leftChild(index)];
        return items[index] >= items[leftChild(index)] && items[index] >= items[rightChild(index)] ;
    }

    boolean hasLeftChild(int index){
        return leftChild(index) <= size;
    }

    boolean hasRightChild(int index){
        return rightChild(index) <= size;
    }

    int leftChild(int index){
        return index * 2 + 1 ;
    }

    int rightChild(int index){
        return (index+1) * 2;
    }

    void swap(int i, int j){
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp ;
    }
}
