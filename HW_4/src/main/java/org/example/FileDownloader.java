package org.example;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.*;

public class FileDownloader {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество желаемых потоков: ");
        int numThreads = scanner.nextInt(); // Количество потоков для загрузки
        System.out.print("Введите ссылку для скачивания файла: ");
        String urlString = scanner.next();
        //https://www.gtp-tabs.ru/n/upload/fileManager/files/gtptabs.com.tgz  Использовал для тестов.


        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        URL url = new URL(urlString);
        InputStream input = url.openStream();
        int totalSize = input.available();
        int perThread = totalSize / numThreads;
        byte[] buffer = new byte[perThread];

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            executor.execute(() -> {
                try {
                    InputStream threadInput = url.openStream();
                    skipToThreadStart(threadInput, perThread, threadId);
                    downloadAndWrite(threadInput, buffer, latch);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    private static void downloadAndWrite(InputStream input, byte[] buffer, CountDownLatch latch) throws IOException {
        try (OutputStream output = new FileOutputStream("Файл")) {
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            System.out.println("Поток " + Thread.currentThread().getId() + " завершена загрузка и запись файла");
        } catch (IOException e) {
            throw new IOException("Ошибка записи", e);
        } finally {
            latch.countDown();
        }
    }

    private static void skipToThreadStart(InputStream input, int perThread, int threadId) throws IOException {
        long skipBytes = (long) perThread * threadId;
        input.skip(skipBytes);
    }
}