import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TimSort {
    private static final int RUN = 32;
    private static int iterationCount = 0;
    public static void main(String[] args) {
        processFile("FirstSemWork2.txt", "outputFSW2.txt");
    }

    public static void processFile(String inputFile, String outputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            writer.write("Размер массива | Время (нс) | Итерации\n");
            writer.write("---------------------------------------\n");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                int size = Integer.parseInt(parts[0]);
                int[] array = parseArray(parts[1]);

                iterationCount = 0;
                long startTime = System.nanoTime();
                timSort(array);
                long duration = System.nanoTime() - startTime;

                writer.write(String.format("%12d | %10d | %8d\n",
                        size, duration, iterationCount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] parseArray(String str) {
        return Arrays.stream(str.trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // Генерация случайного массива
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    //Реализация алгоритма
    public static void timSort(int[] arr) {
        iterationCount = 0; // Сбрасываем счётчик

        // Остальной код TimSort
        for (int start = 0; start < arr.length; start += RUN) {
            int end = Math.min(start + RUN - 1, arr.length - 1);
            insertionSort(arr, start, end);
        }

        for (int size = RUN; size < arr.length; size *= 2) {
            for (int left = 0; left < arr.length; left += 2 * size) {
                int mid = Math.min(left + size - 1, arr.length - 1);
                int right = Math.min(left + 2 * size - 1, arr.length - 1);
                if (mid < right) {
                    unite(arr, left, mid, right);
                }
            }
        }
    }

    // сортировка вставками
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                iterationCount++;
            }
            arr[j + 1] = key;
            iterationCount++;
        }
    }

    //сортировка слиянием
    private static void unite(int[] arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];

        System.arraycopy(arr, left, leftArray, 0, len1);
        System.arraycopy(arr, mid + 1, rightArray, 0, len2);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < len1 && j < len2) {
            iterationCount++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
            iterationCount++;
        }

        while (i < len1) {
            arr[k++] = leftArray[i++];
            iterationCount++;
        }

        while (j < len2) {
            arr[k++] = rightArray[j++];
            iterationCount++;
        }
    }

    //Проверка на отсортированность
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

}
