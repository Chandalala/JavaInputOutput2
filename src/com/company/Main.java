package com.company;

import java.io.*;
import java.util.Arrays;

public class Main {

    /*
     * To read data from a source, you use input stream and to write data to a source, you use output stream
     *
     * STREAM OPERATIONS
     *   1.Open a stream
     *   2.Read/write
     *   3.Close stream
     *
     * */

    /**
     * byte streams is for reading binary data such as images
     * character streams is for reading texts
     *
     * byte streams --->> InputStream and OutputStream --->> Object
     * character streams --->> Reader and Writer --->> Object
     *
     * InputStream class
     *  is a base abstract class for all byte input streams
     *  is used to read data in groups of 8-bit bytes
     *  int read() throws IOException --->> read 1 byte and returns int between 0 and 255(2^8-1) and returns -1 if end of stream has been detected
     *
     * OutputStream class
     *  is a base abstract class for all byte output streams
     *  is used to write data in groups of 8-bit bytes
     *  void write(int) throws IOException --->> writes only least significant byte
     *
     * Input Stream hierarchy
     *  ByteArrayInputStream --->> InputStream
     *  FileInputStream --->> InputStream --->> read bytes from files
     *  ObjectInputStream --->> InputStream
     *  PipedInputStream --->> InputStream
     *  BufferedInputStream --->>FilterInputStream --->> InputStream
     *  DataInputStream --->>FilterInputStream --->> InputStream
     *
     *      *  Output Stream hierarchy
     *      *  ByteArrayOutputStream --->> OutputStream
     *      *  FileOutputStream --->> OutputStream --->> write bytes to files
     *      *  ObjectOutputStream --->> OutputStream
     *      *  PipedOutputStream --->> OutputStream
     *      *  BufferedOutputStream --->>FilterOutputStream --->> OutputStream
     *      *  DataOutputStream --->>FilterOutputStream --->> OutputStream
     *      *  PrintStream --->>FilterOutputStream --->> OutputStream
     *
     *  read()/write() methods are expensive. Reading and writing single bytes is grossly inefficient
     *  efficient way is to use buffering which involves reading and writing a block of bytes into a memory buffer
     *  this can be implemented by BufferedInputStream and BufferedOutputStream which are subclasses of FilterInputStream and FilterOutputStream
     *  Buffer is simply a byte array
     *  default buffer size is 8192 bytes
     *  Buffered Streams do not work independently and require other streams such FileInput and Output streams to make chained streams
     *  The FileInputStream does the actual reading of the data from the file
     *
     *  EXAMPLE BufferedInputStream in = new BufferedInputStream(new FileInputStream("go.jpg"))
     *
     *  READER BASE CLASS for character streams
     *
     *  CharArrayReader --->> Reader
     *  BufferedReader  --->> Reader
     *  PipedReader --->> Reader
     *  StringReader  --->> Reader
     *  FilterReader  --->> Reader
     *  FileReader --->> InputStreamReader  --->> Reader -->> Read files that contain text
     *
     *      *  WRITER BASE CLASS for character streams
     *      *
     *      *  CharArrayWriter --->> Writer
     *      *  BufferedWriter  --->> Writer
     *      *  PipedWriter --->> Writer
     *      *  StringWriter  --->> Writer
     *      *  FilterWriter  --->> Writer
     *      *  PrintWriter --->> Writer
     *      *  FileWriter --->> OutputStreamWriter  --->> Writer -->> Write files that contain text
     *
     * EXAMPLE
     * FileReader in = new FileReader("go.txt")
     * FileWriter out = new FileWriter("go.txt")
     *
     * Uses FileInputStream and FileOutputStream classes
     * Uses default encoding
     * to get the type of encoding currently in use --->> System.getProperty("file.encoding") OR Charset.defaultCharset()
     *
     * Classes below can be used to change character encoding
     * InputStreamReader(InputStream in,String charsetName)
     * OutputStreamWriter(OutputStream out,String charsetName)
     *
     *      * EXAMPLE
     *      * FileInputStream in = new FileInputStream("go.txt")
     *          InputStreamReader reader = new InputStreamReader(in,"UTF-8")
     *
     *      * FileOutputStream out = new FileOutputStream("go.txt")
     *          OutputStreamWriter = new OutputStreamWriter(out,"UTF-8")
     *
     * BufferedReader and BufferedWriter class for efficiency
     * default buffer size is 8192 characters
     *
     */
        public static void applyEncoding() {
            System.out.println("\nInside applyEncoding ...");
            //System.out.println("Default Character Encoding: " + System.getProperty("file.encoding"));

            // Ensure Eclipse property is set as UTF8
            printEncodingDetails("Tinashe");
            printEncodingDetails("�"); // Euro (Reference: http://stackoverflow.com/questions/34922333/how-does-java-fit-a-3-byte-unicode-character-into-a-char-type)
            printEncodingDetails("\u1F602"); // Non-BMP Unicode Code Point ~ Tears of Joy Emoji (one of Smiley graphic symbol)
        }

        private static void printEncodingDetails(String symbol) {
            System.out.println("\nSymbol: " + symbol);
            try {
                System.out.println("ASCII: " + Arrays.toString(symbol.getBytes("US-ASCII")));
                System.out.println("ISO-8859-1: " + Arrays.toString(symbol.getBytes("ISO-8859-1")));
                System.out.println("UTF-8: " + Arrays.toString(symbol.getBytes("UTF-8")));
                System.out.println("UTF-16: " + Arrays.toString(symbol.getBytes("UTF-16")));
                System.out.println("UTF-16 BE: " + Arrays.toString(symbol.getBytes("UTF-16BE")));
                System.out.println("UTF-16 LE: " + Arrays.toString(symbol.getBytes("UTF-16LE")));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }



    public static void main(String[] args) throws IOException {
	// write your code here
    //  applyEncoding();
        //IODemo.ioDemo();

        StringBuilder text= new StringBuilder();

        try (BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(new FileInputStream("android.txt"),"UTF-8"))){

            String line;
            while ((line=bufferedReader.readLine()) != null){

                text.append(line).append("\n");
            }
        }

        System.out.println(text);



    }
}
