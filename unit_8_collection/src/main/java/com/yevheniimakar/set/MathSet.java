package com.yevheniimakar.set;

import java.math.BigDecimal;
import java.util.Arrays;


public class MathSet<E extends Number> {

    private final static int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Number[] mathSet;

    public MathSet() {
        this.mathSet = new Number[DEFAULT_CAPACITY];
    }

    public MathSet(int capacity) {
        this.mathSet = new Number[capacity];
    }

    public MathSet(E[] es) {
        this.mathSet = new Number[es.length];
        this.addArrayToMathSet(es);
    }

    public MathSet(E[]... es) {
        for (E[] e : es) {
            this.addArrayToMathSet(e);
        }
    }

    public MathSet(MathSet<E> es) {
        this.addArrayToMathSet(es.toArray());
    }

    public MathSet(MathSet... es) {
        for (MathSet e : es) {
            this.addArrayToMathSet((E[]) e.toArray());
        }
    }

    public void add(E n) {
        if (this.isNotContainsDuplicate(n)) {
            if (this.mathSet.length == size) {
                this.increaseArray();
            }
            this.mathSet[size] = n;
            this.size++;
        }
    }

    public void add(E... n) {
        if (this.mathSet.length < (this.size + n.length)) {
            this.rebuildArray(this.size + n.length);
        }
        addArrayToMathSet(n);
    }

    public void join(MathSet<E> ms) {
        this.add((E[]) ms.toArray());
    }

    public void join(MathSet... ms) {
        for (MathSet s : ms) {
            this.add((E[]) s.toArray());
        }
    }

    public void intersection(MathSet<E> ms) {
        int mathSetLength = Math.max(this.mathSet.length, ms.size());
        Number[] mathSetNew = new Number[mathSetLength];
        Number[] mathSetIntersection = ms.toArray();
        int mathSetNewIndex = 0;
        for (int i = 0; i < this.mathSet.length; i++) {
            for (int j = 0; j < mathSetIntersection.length; j++) {
                if (compareNumber((E) mathSet[i], (E) mathSetIntersection[j]) == 0) {
                    mathSetNew[mathSetNewIndex] = this.mathSet[i];
                }
            }
        }
        this.mathSet = mathSetNew;
    }

    public void intersection(MathSet... ms) {
        for (MathSet m : ms) {
            intersection(m);
        }
    }

    public void sortDesc() {
        this.mathSet = sortArray((E[]) Arrays.copyOfRange(mathSet, 0, size), false);
        increaseArray();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            sortedPartOfArray(firstIndex, lastIndex, false);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void sortDesc(E value) {
        int index = getIndexByValue(value);
        sortedPartOfArray(index, size, false);
    }

    public void sortAsc() {
        this.mathSet = sortArray((E[]) Arrays.copyOfRange(mathSet, 0, size), true);
        increaseArray();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            sortedPartOfArray(firstIndex, lastIndex, true);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void sortAsc(E value) {
        int index = getIndexByValue(value);
        sortedPartOfArray(index, size, true);
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if (indexInBounds(index)) {
            return (E) this.mathSet[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E getMax() {
        return sortArray((E[]) Arrays.copyOf(this.mathSet, this.size), false)[0];
    }

    public E getMin() {
        return sortArray((E[]) Arrays.copyOf(this.mathSet, this.size), true)[0];
    }

    public E getAverage() {
        if (size > 0) {

            Number sum = 0;
            Number result;
            for (int i = 0; i < this.size; i++) {

                sum = sum.floatValue() + this.mathSet[i].floatValue();
            }
            result = (sum.floatValue() / this.size);

            return (E) result;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E getMedian() {
        Number median = 0;
        if (size > 0) {
            Number[] numbersArr = sortArray((E[]) Arrays.copyOf(this.mathSet, this.size), true);
            if (size > 0 && size % 2 == 0) {
                median = (numbersArr[(size / 2) - 1].floatValue() + numbersArr[size / 2].floatValue()) / 2;
            }
            if (size > 0 && size % 2 == 1) {
                median = numbersArr[size / 2];
            }
        }
        return (E) median;
    }

    public E[] toArray() {
        return (E[]) Arrays.copyOfRange(this.mathSet, 0, size);
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        if (indexInBounds(firstIndex, lastIndex)) {
            return (E[]) Arrays.copyOfRange(this.mathSet, firstIndex, lastIndex + 1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clear() {
        this.mathSet = new Number[mathSet.length];
        this.size = 0;
    }

    public void clear(int firstIndex, int lastIndex) {

        if (indexInBounds(firstIndex, lastIndex)) {
            Number[] numbers = new Number[size];
            int indexNumber = 0;
            for (int i = 0; i < size; i++) {
                if (!(firstIndex <= i && lastIndex > i)) {
                    numbers[indexNumber] = mathSet[i];
                    indexNumber++;
                }
            }
            mathSet = numbers;
        }
    }

    public void clear(E[] es) {
        Number[] numbers = new Number[size];
        int indexNumber = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < es.length; j++) {
                if (compareNumber((E) this.mathSet[i], es[j]) != 0) {
                    numbers[indexNumber] = this.mathSet[i];
                    indexNumber++;
                }
            }
        }
        this.mathSet = numbers;
    }

    public int size() {
        return this.size;
    }

    private void addArrayToMathSet(E[] n) {
        for (E number : n) {
            this.add(number);
        }
    }

    private boolean isNotContainsDuplicate(E number) {
        for (int i = 0; i < size; i++) {
            if (mathSet[i] == number) {
                return false;
            }
        }
        return true;
    }

    private void increaseArray() {
        int newLength = mathSet.length + (mathSet.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        Number[] newmathSet = new Number[newLength];
        int indexCount = 0;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                newmathSet[indexCount] = mathSet[i];
                indexCount++;
            }
        }
        mathSet = newmathSet;
        size = indexCount;
    }

    private E[] sortArray(E[] e, boolean sortAsc) {
        Number[] result;
        Number[] startHalfOfArray;
        Number[] endHalfOfArray;
        Number[] startHalfOfArraySorted;
        Number[] endHalfOfArraySorted;

        if (e.length > 1) {
            result = new Number[e.length];
            startHalfOfArray = Arrays.copyOfRange(e, 0, (e.length / 2));
            endHalfOfArray = Arrays.copyOfRange(e, (e.length / 2), e.length);
            startHalfOfArraySorted = sortArray((E[]) startHalfOfArray, sortAsc);
            endHalfOfArraySorted = sortArray((E[]) endHalfOfArray, sortAsc);
            result = getMergedList((E[]) startHalfOfArraySorted, (E[]) endHalfOfArraySorted, sortAsc);
        } else {
            result = e;
        }
        return (E[]) result;
    }

    private E[] getMergedList(E[] e1, E[] e2, boolean sortAsc) {
        Number[] result = new Number[e2.length + e1.length];
        int e1Length = e1.length;
        int e2Length = e2.length;
        int e1Index = 0;
        int e2Index = 0;
        int resultIndex = 0;
        int sort = sortAsc ? 1 : -1;

        while (e1Index != e1Length || e2Index != e2Length) {
            if (e1Index != e1Length && e2Index != e2Length) {
                if (this.compareNumber(e1[e1Index], e2[e2Index]) * sort < 0) {
                    result[resultIndex] = e1[e1Index];
                    resultIndex++;
                    e1Index++;
                } else {
                    result[resultIndex] = e2[e2Index];
                    resultIndex++;
                    e2Index++;
                }
            } else if (e1Index == e1Length && e2Index != e2Length) {
                for (int i = e2Index; i < e2.length; i++) {
                    result[resultIndex] = e2[i];
                    resultIndex++;
                    e2Index++;
                }
            } else if (e1Index != e1Length && e2Index == e2Length) {
                for (int i = e1Index; i < e1.length; i++) {
                    result[resultIndex] = e1[i];
                    resultIndex++;
                    e1Index++;
                }
            }
        }
        return (E[]) result;
    }

    private boolean indexInBounds(int index) {
        if (index < 0 || index > size) {
            return false;
        }
        return true;
    }

    private boolean indexInBounds(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex > this.size) {
            return false;
        }
        if (lastIndex < 0 || lastIndex > this.size) {
            return false;
        }
        if (firstIndex > lastIndex) {
            return false;
        }
        return true;
    }

    private void sortedPartOfArray(int firstIndex, int lastIndex, boolean sortAs) {
        Number[] sortedPartOfArray = sortArray((E[]) Arrays.copyOfRange(mathSet, firstIndex, lastIndex + 1), true);
        for (Number number : sortedPartOfArray) {
            mathSet[firstIndex] = number;
            firstIndex++;
        }
    }

    private int getIndexByValue(E value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (compareNumber(value, (E) mathSet[i]) == 0) {
                index = i;
            }
        }
        return index;
    }

    private int compareNumber(E e1, E e2) {
        return new BigDecimal(e1.toString()).compareTo(new BigDecimal(e2.toString()));
    }

    @Override
    public String toString() {
        return "MathSet{" + Arrays.toString(Arrays.copyOfRange(mathSet, 0, size)) + "}";
    }

}
