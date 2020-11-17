package one;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    /**
     * Method read bytes from <code>InputStream</code>, and write it as a <code>String</code> using certain charset.
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     */
    public static String readAsString (InputStream inputStream, Charset charset) throws IOException {
        int readByte;
        StringWriter stringWriter = new StringWriter();
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset)) {
            while ((readByte = inputStreamReader.read()) != '\n') {
                stringWriter.write(readByte);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    /**
     * Напишите программу, читающую текст из <code>System.in</code><br>
     * и выводящую в <code>System.out</code> сумму всех встреченных в тексте<br>
     * вещественных чисел с точностью до шестого знака после запятой.
     */
    public static void doubleFromString () {
        String s = "";
        double result = 0;
            try (Scanner scanner = new Scanner(System.in)) {
                while (scanner.hasNext()) {
                    s = scanner.next();
                    if (s.equals("stopscan")) { break;}
                    result += Double.parseDouble(s);
                }
            }
            catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "Wrong number format", e);
            }
        System.out.format("%,6f", result);
    }

    public static void main(String[] args) throws IOException{
//        System.out.println(readAsString(System.in, StandardCharsets.US_ASCII));
        doubleFromString();
    }
}
