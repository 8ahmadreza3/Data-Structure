package Sort;

public class Sort {
    void bubbleSort(int[] a){
        for(int i=0 ; i<a.length ; ++i){
            for(int j=1 ; j<a.length-i ; ++j){
                if(a[j] < a[j-1])
                    swap(a, j-1, j);
            }
        }
    }

    void selectionSort(int[] a){
        for(int i=0 ; i<a.length ; ++i){
            int minIndex = 0 ;
            for(int j=i ; j<a.length ; ++j){
                if(a[j] < a[minIndex])
                    minIndex = j;
            }
            swap(a, minIndex, i);
        }
    }

    void insertionSort(int[] a){
        for(int i=1 ; i<a.length ; ++i){
            int current = a[i];
            int j ;
            for(j = i-1 ; j>=0 && a[j] > current ; --j){
                a[j+1] = a[j];
            }
            a[j+1] = current ;
        }
    }

    void mergeSort(int[] a){
        if(a.length < 2)
            return ;
        int mid = a.length /2 ;
        int[] left = new int[mid];
        int[] right = new int[a.length - mid];
        for(int i=0 ; i<a.length ; ++i){
            if(i<mid)
                left[i] = a[i] ;
            else
                right[i-mid] = a[i] ;
        }
        mergeSort(left);
        mergeSort(right);
        merge(left, right, a);
    }

    void merge(int[] left, int[] right, int[] result){
        int i = 0, k = 0, j = 0;
        while(i< left.length && j< right.length){
            if(left[i] <= right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];
        }
        while(i< left.length)
            result[k++] = left[i++];

        while(j< right.length)
            result[k++] = right[j++];
    }

    void quickSort(int[] a, int start, int end){
        if(start >= end)
            return ;
        int boundary = partition(a, start, end);
        quickSort(a, start, boundary-1);
        quickSort(a, boundary+1, end);
    }

    int partition(int[] a, int start, int end){
        int pivot = a[end];
        int boundary = start-1 ;
        for(int i=0 ; i<=end ; i++){
            if(a[i] <= pivot)
                swap(a, i, ++boundary);
        }
        return boundary ;
    }

    void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp ;
    }
}
