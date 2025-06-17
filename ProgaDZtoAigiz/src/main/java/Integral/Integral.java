package Integral;

import java.util.function.Consumer;

public class Integral implements Consumer<Double> { //консьюмер превращает действие в объект
    private static int N;
    private volatile double integral; //все потоки всегда видят актуальное значение
    public static double fucntion(double x) {
        return Math.exp(-x * x/2) * Math.sin(x);
    }

    public static void main(String[] args) throws InterruptedException { //если поток был прерван во время ожидания/сна
        Integral integralObject = new Integral();
        int countProc = Runtime.getRuntime().availableProcessors();// кол-во свободных процессов
        N = 10000/countProc; // количество столбцов для каждого потока
        double h = (3. - 1.) / countProc; //деление интервала интеграла на потоки
        Thread[] threads = new Thread[countProc];
        long start = System.nanoTime();
        for (int i = 0; i < countProc; i++) {
            threads[i] = new Thread(new PartSumCalculate(1+i*h,1+(i+1)*h,integralObject));
            threads[i].start();//создаем потоки
        }
        for (int i = 0; i < countProc; i++) {
            threads[i].join();//ждем завершения всех потоков
        }
        long end = System.nanoTime()-start; //временя многопоточности
        System.out.println("Многопоточность: " + integralObject.integral);
        integralObject.integral = 0;
        long start1 = System.nanoTime();
        for (int i = 0; i < countProc; i++) {
            //через конструктор в PartSumCalculate передаем данные по которым выполняются вычесленя и объект интеграл куда записываем ответ
            threads[i] = new Thread(new PartSumCalculate(1+i*h,1+(i+1)*h,integralObject));
            threads[i].start();
            threads[i].join();//создаем потоки, которые идут подряд за счет join
        }
        long end1 = System.nanoTime()-start1; //время однопоточности
        System.out.println("Однопоточность: " + integralObject.integral);
        System.out.println("Время с использованием многопоточности:  " + end);
        System.out.println("Время без использования многопоточности: "+ end1);


    }
    // Частичная сумма которая будет расчитываться в потоке
    public static double partSum(double a, double b, int N) {
        double h = (b - a)/N;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += h * (fucntion(a+h*i) + fucntion(a + h * (i+1)))/2;
        }
        return sum;
    }

    static class PartSumCalculate implements Runnable {
        private double a;
        private double b;
        private Consumer<Double> consumer; //передаем действие как объект

        public PartSumCalculate(double a, double b, Consumer<Double> consumer) {
            this.a = a;
            this.b = b;
            this.consumer = consumer;
        }

        public void run() {
            double result = partSum(a,b,N);
            consumer.accept(result);
        }
    }

    public synchronized void accept(Double d) {
        integral+=d;
    }
}

