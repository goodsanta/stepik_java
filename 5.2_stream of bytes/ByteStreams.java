package one;

import java.io.IOException;
import java.io.InputStream;

public class ByteStreams {

    /**
     * Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.
     * @param inputStream входной поток
     * @return контрольная сумма
     * @throws IOException
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    public static int checkSumOfStream (InputStream inputStream) throws IOException, NullPointerException, IndexOutOfBoundsException {
        int readByte = 0;
        int checkSum = 0;
        int shiftLeft = 0;
        try {
            readByte = inputStream.read();
            //если поток не пустой, обрабатываем
            if (readByte != -1) {
                checkSum = readByte;
                readByte = inputStream.read();
                //пока не конец потока
                while (readByte != -1) {
                    shiftLeft = Integer.rotateLeft(checkSum, 1);
                    checkSum = shiftLeft ^ readByte;
                    readByte = inputStream.read();
                }
            }
        }
        catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            throw e;
        }
        return checkSum;
    }

    /**
     * Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix.<br>
     * Требуется заменить все вхождения пары символов '\r' и '\n' на один символ '\n'.
     * @throws IOException
     */
    public static void deleteCR () throws IOException {
        int readByte1;
        int readByte2;
        readByte1 = System.in.read();
        while (readByte1 != '\n') {
            readByte2 = System.in.read();
            if (readByte1 != 13 || readByte2 != 10) {
                System.out.print(readByte1);
            }
            readByte1 = readByte2;
        }
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {
    }
}
