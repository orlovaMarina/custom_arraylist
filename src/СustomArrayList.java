public class СustomArrayList<T>{

    /** default size of the collection */
    private final static int DEFAULT_SIZE = 10;

    /** operating array */
    private Object[] array = new Object[DEFAULT_SIZE];

    /** array size */
    private int size = 0;

    /**
     * Add object to the collection
     * @param o - object to be added
     */
    public void add(T o){
        if(size == array.length){
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = o;
        size++;
    }

    /**
     * Remove the object from the collection
     * @param index index of object
     */
    public void remove(int index){
        if(index >=0 && index < size){
            if (size - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size - 1 - index);
            size--;
        }
    }

    /**
     * Returns size of the collection
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Clears the collection
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Return object by the index
     * @param index object index
     * @return object by the index
     */
    public T get(int index){
        if (index >=0 && index < size) return (T) array[index];
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Sorts array with quicksort algorithm
     */
    public void quicksort() {
        quicksort(array, 0, getSize() - 1);
    }

    private void quicksort(Object[] array, int startIndex, int endIndex)
    {
        // verify that the start and end index have not overlapped
        if (startIndex < endIndex)
        {
            // calculate the pivotIndex
            int pivotIndex = partition(array, startIndex, endIndex);
            // sort the left sub-array
            quicksort(array, startIndex, pivotIndex);
            // sort the right sub-array
            quicksort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(Object[] array, int startIndex, int endIndex)
    {
        int pivotIndex = (startIndex + endIndex) / 2;
        Object pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true)
        {
            // start at the FIRST index of the sub-array and increment
            // FORWARD until we find a value that is > pivotValue
            do startIndex++; while (compare(array[startIndex], pivotValue) < 0) ;

            // start at the LAST index of the sub-array and increment
            // BACKWARD until we find a value that is < pivotValue
            do endIndex--; while (compare(array[endIndex], pivotValue) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            // swap values at the startIndex and endIndex
            Object temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }

    private int compare(Object first, Object second) {
        if (first instanceof Comparable && second instanceof Comparable) {
            return ((Comparable<T>) first).compareTo((T) second);
        } else return Integer.compare(first.hashCode(), second.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(array[i]);
            if (i < getSize() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        СustomArrayList<String> list   = new СustomArrayList<>();

        list.add("Vitya");
        list.add("Elena");
        list.add("Ivan");
        list.add("Danya");
        list.add("Timur");
        list.add("Alex");
//        list.add(22);
//        list.add(-90);
//        list.add(1000);
//        list.add(1);
//        list.add(5);
//        list.add(-8557);

        list.quicksort();
        System.out.println(list);
    }
}




