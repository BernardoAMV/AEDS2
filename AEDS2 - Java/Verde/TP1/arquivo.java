import java.io.*;
public class arquivo {

    public static void main(String[] args) throws Exception{

        int n = MyIO.readInt();
        RandomAccessFile raf = new RandomAccessFile("arquivo.txt", "rw");
        double numero;
        for(int i = 0; i < n; i++){
            numero = MyIO.readDouble();
            raf.writeDouble(numero);
        }
        raf.close();
        leituraInversa("arquivo.txt", n);

    }
    public static void leituraInversa(String arquivo, int n) throws Exception{
        RandomAccessFile raf = new RandomAccessFile(arquivo, "rw");
        double numero;
        for(int i = 1; i <= n; i++){
            raf.seek(n - i);
            numero = raf.readDouble();
            MyIO.println(numero);
        }
        raf.close();

    }

}