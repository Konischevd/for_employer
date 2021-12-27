package util.task2;

import java.util.Arrays;

public class KdaArrayUtil {

    public static void displace(Object[] array, int m) {
        int length = array.length;

        checkInput(length, m);

        System.out.println("Source array: " + Arrays.toString(array));

        int n = doDisplace(array, 0, m, length - 1);

        System.out.println("Result array: " + Arrays.toString(array));
        System.out.println("Total steps = " + n);
    }

    /**
     * Example:
     * <code>
     * <br>for m = 3
     * <br>[0 1 2 | 3 4 5 6]
     * <br>[l _ _ | m _ _ h]
     * <br>0-2 head
     * <br>3-6 tail
     * </code>
     * @param arr source array
     * @param l low index
     * @param m mid index
     * @param h high index
     * @return number of steps including all nested recursive calls
     */
    private static int doDisplace(Object[] arr, int l, int m, int h) {
        int n = 0;
        Object buf;

        int headLen = m - l;
        int tailLen = h - m + 1;

        // head == tail
        if (headLen == tailLen) {
            for (int i = l; i < m; i++) {
                buf = arr[i];
                arr[i] = arr[i + headLen];
                arr[i + headLen] = buf;
                n++;
            }
            buf = null;
            printStepInfo(arr, l, m, h, "head == tail", n);

        // 1 element at head
        } else if (headLen == 1) {
            buf = arr[l];
            for (int i = l; i < h; i++) {
                arr[i] = arr[i + 1];
                n++;
            }
            arr[h] = buf;
            buf = null;
            n++;
            printStepInfo(arr, l, m, h, "1 element at head", n);

        // 1 element at tail
        } else if (tailLen == 1) {
            buf = arr[h];
            for (int i = h; i > l; i--) {
                arr[i] = arr[i - 1];
                n++;
            }
            arr[l] = buf;
            buf = null;
            n++;
            printStepInfo(arr, l, m, h, "1 element at tail", n);

        // head < tail
        } else if (headLen < tailLen) {
            for (int i = l; i < m; i++) {
                buf = arr[i];
                arr[i] = arr[i + tailLen];
                arr[i + tailLen] = buf;
                n++;
            }
            printStepInfo(arr, l, m, h, "head < tail", n);
            n += doDisplace(arr, l, m, l + tailLen - 1);

        // head > tail
        } else {
            for (int i = h; i >= m; i--) {
                buf = arr[i];
                arr[i] = arr[i - headLen];
                arr[i - headLen] = buf;
                n++;
            }
            printStepInfo(arr, l, m, h, "head > tail", n);
            n += doDisplace(arr, l + tailLen, m, h);
        }

        return n;
    }

    private static void checkInput(int length, int m) {
        if (length < 2) {
            throw new IllegalArgumentException("Array length must be at least 2");
        } else {
            if (m < 1 || m >= length) {
                throw new IndexOutOfBoundsException(
                        "Unsuitable number of elements 'm', for this array 'm' must be between 1 and "
                                + (length - 1));
            }
        }
    }

    private static void printStepInfo(Object[] arr, int l, int m, int h, String type, int n) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < l; i++) {
            str.append("_, ");
        }
        for (int i = l; i <= h; i++) {
            if (i == arr.length - 1) {
                str.append(arr[i]);
                str.append("]");
                continue;
            }
            str.append(arr[i]);
            if(m == i + 1) {
                str.append("| ");
                continue;
            }
            str.append(", ");
        }
        for (int i = h + 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                str.append("_]");
                continue;
            }
            str.append("_, ");
        }
        str.append("   ");
        str.append(type);
        str.append(", ");
        str.append("n = ");
        str.append(n);
        System.out.println(str);
    }

}