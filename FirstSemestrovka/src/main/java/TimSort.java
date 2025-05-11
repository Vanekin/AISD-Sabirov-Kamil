import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class TimSort {
    private static int iterationCount = 0;
    public static void main(String[] args) {
        processFile("FirstSemWorkRandomArrays.txt", "outputFSWRandom.txt");
    }

    private static class Run {
        int start;
        int length;
        Run(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    private static Stack<Run> runStack = new Stack<>();

    public static void processFile(String inputFile, String outputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile));
            FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Размер массива | Время (нс) | Итерации\n");
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
//    private static int[] generateRandomArray(int size) {
//        Random random = new Random();
//        int[] array = new int[size];
//        for (int i = 0; i < size; i++) {
//            array[i] = random.nextInt(10000);
//        }
//        return array;
//    }

    // Динамическое нахождение RUN
    private static int calcMinRun(int arraySize) {
        int r = 0;
        while (arraySize >= 64) {
            r |= arraySize & 1; // Сохраняем младший бит
            arraySize >>= 1;    // Делим n на 2(побитовый сдвиг вправо)
        }
        return arraySize + r;    // minrun ∈ [32, 64]
    }

    private static int findNaturalRun(int[] arr, int start, int end) {
        if (start >= end) return start;
        // Определяем направление (возрастание/убывание)
        boolean ascending = arr[start] <= arr[start + 1];
        int i = start;
        while (i < end) {
            if (ascending && arr[i] > arr[i + 1]) break;
            if (!ascending && arr[i] < arr[i + 1]) break;
            i++;
        }
        // Если RUN убывающий — разворачиваем
        if (!ascending) {
            reverse(arr, start, i);
        }
        return i;
    }

    //Реализация алгоритма
    public static void timSort(int[] arr) {
        iterationCount = 0;
        int minRun = calcMinRun(arr.length);
        int n = arr.length;
        int current = 0;
        // Поиск и сортировка RUNов
        while (current < n) {
            int start = current;
            int end = findNaturalRun(arr, start, n - 1);
            // Если RUN короче minRun — расширяем
            int required = Math.min(minRun, n - start);
            if (end - start + 1 < required) {
                end = start + required - 1;
                insertionSort(arr, start, end);
            }
            runStack.push(new Run(start, end - start + 1));
            current = end + 1;
            // проверка условий слияния
            mergeCollapse(arr);
        }
        // Финализации слиянияу
        while (runStack.size() > 1) {
            mergeForce(arr);
        }
    }

    private static void mergeCollapse(int[] arr) {
        while (runStack.size() > 2) {
            Run z = runStack.pop();
            Run y = runStack.pop();
            Run x = runStack.pop();
            // Условия оригиналного Timsort: X > Y + Z и Y > Z
            if (x.length <= y.length + z.length || y.length <= z.length) {
                iterationCount++;
                if (x.length < z.length) {
                    merge(arr, x.start, y.start + y.length - 1, z.start + z.length - 1);
                    runStack.push(x);
                    runStack.push(z);
                } else {
                    merge(arr, y.start, z.start + z.length - 1, z.start + z.length - 1);
                    runStack.push(x);
                    runStack.push(y);
                }
            } else {
                runStack.push(x);
                runStack.push(y);
                runStack.push(z);
                break;
            }
        }
    }

    private static void mergeForce(int[] arr) {
        while (runStack.size() > 1) {
            iterationCount++;
            Run y = runStack.pop();
            Run x = runStack.pop();
            merge(arr, x.start, y.start + y.length - 1, y.start + y.length - 1);
            runStack.push(new Run(x.start, x.length + y.length));
        }
    }


    // Сортировка вставками
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

    // сортировка слиянием
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            // Галоп для левого RUN
            int gallopEndLeft = gallop(arr, i, mid, arr[j]);
            if (gallopEndLeft > i) {
                int len = gallopEndLeft - i + 1;
                System.arraycopy(arr, i, temp, k, len);
                k += len;
                i += len;
                iterationCount += len;
            }
            // Галоп для правого RUN
            int gallopEndRight = gallop(arr, j, right, arr[i]);
            if (gallopEndRight > j) {
                int len = gallopEndRight - j + 1;
                System.arraycopy(arr, j, temp, k, len);
                k += len;
                j += len;
                iterationCount += len;
            }
            if (i <= mid && j <= right) {
                iterationCount++;
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
            iterationCount++;
        }
        while (j <= right) {
            temp[k++] = arr[j++];
            iterationCount++;
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // Метод реализующий галопный поиск
    private static int gallop(int[] arr, int start, int end, int key) {
        int step = 1;
        int current = start;
        while (current <= end && arr[current] <= key) {
            iterationCount++;
            current += step;
            step *= 2;
        }
        int left = start;
        int right = Math.min(current, end);
        while (left < right) {
            int mid = left + (right - left) / 2;
            iterationCount++;
            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    // Разворот подмассива массива
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
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
