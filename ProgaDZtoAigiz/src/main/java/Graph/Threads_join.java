package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Threads_join extends Thread {
    private String name;
    private List<Threads_join> dependencies = new ArrayList<>();

    public Threads_join(String name) {
        this.name = name;
    }

    public void addDependency(Threads_join[] deps) {
        dependencies.addAll(Arrays.asList(deps));
    }

    @Override
    public void run() {
        try {
            // Ждем завершения всех зависимых потоков
            for (Threads_join dep : dependencies) {
                dep.join();
            }

            System.out.println("Поток " + name + " начал работу");
            Thread.sleep(1500);
            System.out.println("Поток " + name + " завершил работу");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        threadsLog();
    }

    public static void threadsLog() throws InterruptedException {
        // Создаем все потоки
        Threads_join[] threads = new Threads_join[11];
        for (int i = 1; i <= 10; i++) {
            threads[i] = new Threads_join(String.valueOf(i));
        }

        // Устанавливаем зависимости
        threads[2].addDependency(new Threads_join[] {threads[1]});
        threads[3].addDependency(new Threads_join[] {threads[1]});
        threads[4].addDependency(new Threads_join[] {threads[2], threads[3]});
        threads[5].addDependency(new Threads_join[] {threads[2]});
        threads[6].addDependency(new Threads_join[] {threads[5], threads[4]});
        threads[7].addDependency(new Threads_join[] {threads[3]});
        threads[8].addDependency(new Threads_join[] {threads[6], threads[7]});
        threads[9].addDependency(new Threads_join[] {threads[7], threads[8]});
        threads[10].addDependency(new Threads_join[] {threads[9]});

        // Запускаем все потоки
        for (int i = 1; i <= 10; i++) {
            threads[i].start();
        }

        // Ждем завершения всех потоков
        for (int i = 1; i <= 10; i++) {
            threads[i].join();
        }

        System.out.println("Все потоки завершили выполнение");
    }
}