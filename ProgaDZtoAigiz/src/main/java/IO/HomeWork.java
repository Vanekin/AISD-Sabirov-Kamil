package IO;

import java.io.*;

public class HomeWork {
    public static void main(String[] args) {
        fileByteSort();
        diagramma();
        copyText();
    }
    public static void fileByteSort() {
        try (FileInputStream fis = new FileInputStream("example.txt"); FileOutputStream fos = new FileOutputStream("example_sort_output")) {
            byte[] array = fis.readAllBytes();
            byte[] arrayOutput = sortCount(array);
            fos.write(arrayOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sortCount(byte[] array) {
        byte[] arrayCount = new byte[256];
        for (int i = 0; i < array.length; ++i) {
            arrayCount[array[i] + 128]++;
        }
        byte[] arrayOutput = new byte[array.length];
        int help = 0;
        for (int i = 0; i < arrayCount.length; i++) {
            while (arrayCount[i] > 0) {
                arrayOutput[help] = (byte) (i - 128);
                arrayCount[i]--;
                help++;
            }
        }
        return arrayOutput;
    }

    public static void diagramma() {
        try (FileInputStream fis = new FileInputStream("example_sort_output");
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                baos.write(byteRead);
            }
            byte[] byteArray = baos.toByteArray();
            byte[] arrayCount = countByte(byteArray);
            StringBuilder title = new StringBuilder("");
            for (int i = 0; i < 256; i++) {
                title.append(i + "|");
            }
            System.out.println(title);
            int flag = max(arrayCount);
            while (flag != 0) {
                for (int i = 0; i < 256; i++) {
                    if(i > 9) {
                        System.out.print(" ");
                    }
                    if(i > 99) {
                        System.out.print(" ");
                    }
                    if(arrayCount[i] == 0) {
                        System.out.print(" " + "|");
                    }
                    if(arrayCount[i] != 0) {
                        System.out.print("\u25A1" + "|");
                        arrayCount[i]--;
                    }
                }
                System.out.println();
                flag--;
            }
        } catch (IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] countByte(byte[] array) {
        byte[] arrayCount = new byte[256];
        for (int i = 0; i < array.length; ++i) {
            arrayCount[array[i] + 128]++;
        }
        return arrayCount;
    }

    public static int max(byte[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }

    public static void copyText() {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream("testHW.txt")) {
            byte[] arrayFIS = fis.readAllBytes();
            try(ByteArrayInputStream bais = new ByteArrayInputStream(arrayFIS)) {
                int read;
                while ((read = bais.read()) != -1) {
                    baos.write(read);
                }
                try(FileOutputStream fos = new FileOutputStream("testHWCopy.txt")) {
                    baos.writeTo(fos);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
