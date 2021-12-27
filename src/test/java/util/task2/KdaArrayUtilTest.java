package util.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KdaArrayUtilTest {
    Integer[] arr = new Integer[10];


    @BeforeEach
    void setUp() {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                arr[i] = 0;
                continue;
            }
            arr[i] = arr[i - 1] + 1;
        }
    }

    @Test
    void mustThrowExceptionOnBadInput() {
        assertAll("Incorrect input check",
            () -> assertThrows(IllegalArgumentException.class, () -> KdaArrayUtil.displace(new Object[1], 1)),
            () -> assertThrows(IllegalArgumentException.class, () -> KdaArrayUtil.displace(new Object[1], 1)),
            () -> assertThrows(IllegalArgumentException.class, () -> KdaArrayUtil.displace(new Object[0], 1)),

            () -> assertThrows(IndexOutOfBoundsException.class, () -> KdaArrayUtil.displace(new Object[4], -1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> KdaArrayUtil.displace(new Object[4], 0)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> KdaArrayUtil.displace(new Object[4], 4)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> KdaArrayUtil.displace(new Object[4], 5))
        );
    }

    @Test
    void tryDisplaceFirst() {
        KdaArrayUtil.displace(arr, 1);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, arr);
    }

    @Test
    void tryDisplaceAllExceptLast() {
        KdaArrayUtil.displace(arr, 9);
        assertArrayEquals(new Integer[]{9, 0, 1, 2, 3, 4, 5, 6, 7, 8}, arr);
    }

    @Test
    void tryDisplaceMinority() {
        KdaArrayUtil.displace(arr, 3);
        assertArrayEquals(new Integer[]{3, 4, 5, 6, 7, 8, 9, 0, 1, 2}, arr);
    }

    @Test
    void tryDisplaceMajority() {
        KdaArrayUtil.displace(arr, 7);
        assertArrayEquals(new Integer[]{7, 8, 9, 0, 1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    void tryDisplaceHalf() {
        KdaArrayUtil.displace(arr, 5);
        assertArrayEquals(new Integer[]{5, 6, 7, 8, 9, 0, 1, 2, 3, 4}, arr);
    }

}