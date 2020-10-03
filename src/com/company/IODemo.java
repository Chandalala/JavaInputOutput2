package com.company;

import java.io.*;

public class IODemo {
    static String inFileStr = "brown_dark.jpg";
    static String outFileStr = "brown_dark_out.jpg";

    public static void fileCopyNoBuffer() {
        System.out.println("\nInside fileCopyNoBuffer ...");

        long startTime, elapsedTime; // for speed benchmarking

        // Print file length
        File fileIn = new File(inFileStr);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileInputStream in = new FileInputStream(inFileStr);
             FileOutputStream out = new FileOutputStream(outFileStr)) {
            startTime = System.nanoTime();
            int byteRead;
            // Read a raw byte, returns an int of 0 to 255.
            while ((byteRead = in.read()) != -1) {
                // Write the least-significant byte of int, drop the upper 3
                // bytes
                out.write(byteRead);
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Most common way to read byte streams from a file
    public static void fileCopyWithBufferAndArray() {
        System.out.println("\nInside fileCopyWithBufferAndArray ...");

        long startTime, elapsedTime; // for speed benchmarking
        startTime = System.nanoTime();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {

            byte[] byteBuf = new byte[6000];
            int numBytesRead;
            while ((numBytesRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("fileCopyWithBufferAndArray: " + (elapsedTime / 1000000.0) + " msec");
    }

    public static void copyTextFile() {
        System.out.println("\nInside copyTextFile ...");

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("android.txt"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("android_two.txt"))){

            byte[] byteBuf = new byte[1000];
            while (bufferedInputStream.read(byteBuf) != -1){
                bufferedOutputStream.write(byteBuf,0,bufferedInputStream.read(byteBuf));
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void ioDemo() {
        //fileCopyNoBuffer();
        fileCopyWithBufferAndArray();
        fileCopyNoBuffer();
        copyTextFile();
        System.out.println(System.getProperty("file.encoding"));
    }
}
