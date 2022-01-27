package cmdfilehandler;

public class HeapSort {
	
	static int x;
	static String[] heap;
	
	HeapSort(String[] strArr) {
		x = -1;
		heap = new String[strArr.length];
	}
	 
	static void heapForm(String k) {
	    x++;
	    heap[x] = k;
	    int child = x;
	    String tmp;
	    int index = x / 2;
	    
	    while (index >= 0) {
	        if (heap[index].compareTo(heap[child]) > 0) {
	            tmp = heap[index];
	            heap[index] = heap[child];
	            heap[child] = tmp;
	            child = index;
	            index = index / 2;
	        }
	        else {
	            break;
	        }
	    }
	}
	 
	static void heapSort() {
	    int left1, right1;
	    
	    while (x >= 0){
	        String k;
	        k = heap[0];
			System.out.println(">> " + k);
	        heap[0] = heap[x];
	        x = x - 1;
	        String tmp;
	        int index = 0;
	        int length = x;
	        left1 = 1;
	        right1 = left1 + 1;
	        
	        while (left1 <= length){
	            if (heap[index].compareTo(heap[left1]) <= 0 &&
	                heap[index].compareTo(heap[right1]) <= 0){
	                break;
	            } else {
	                if (heap[left1].compareTo(heap[right1])< 0){
	                    tmp = heap[index];
	                    heap[index] = heap[left1];
	                    heap[left1] = tmp;
	                    index = left1;
	                } else {
	                    tmp = heap[index];
	                    heap[index] = heap[right1];
	                    heap[right1] = tmp;
	                    index = right1;
	                }
	            }
	            left1 = 2 * left1;
	            right1 = left1 + 1;
	        }
	    }
	}
	 
	protected void sort(String k[], int n){
	    for (int i = 0; i < n; i++){
	        heapForm(k[i]);
	    }
	    heapSort();

	}
 
}
