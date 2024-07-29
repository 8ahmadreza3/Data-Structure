package Search;

public class Search {
    int linearSearch(int[] a, int target){
        for(int i=0; i<a.length ; ++i)
            if(a[i] == target)
                return i;
        return -1 ;
    }

    int binarySearch(int[] a, int target, int left, int right){
        // a is sorted array
        if(right < left)
            return -1 ;
        int mid = (left+right) / 2 ;
        if(a[mid] == target)
            return mid ;
        else if(target < a[mid])
            return binarySearch(a, target, left, mid-1);
        else
            return binarySearch(a, target, mid+1, right);
    }

    int ternarySearch(int[] a, int target, int left, int right){
        if(right < left)
            return -1 ;
        int partitionSize = (right-left) / 3 ;
        int firstMid = left + partitionSize ;
        int secondMid = right - partitionSize ;
        if(a[firstMid] == target)
            return firstMid ;
        if(a[secondMid] == target)
            return secondMid;
        if(target < a[firstMid])
            return ternarySearch(a, target, left, firstMid-1);
        if(target > a[secondMid])
            return ternarySearch(a, target, secondMid+1 , right);
        return ternarySearch(a, target, firstMid+1, secondMid-1);
    }
}
