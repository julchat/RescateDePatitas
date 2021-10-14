package domain.business.mascota;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCode {

    static final int ancho = 400;
    static final int altura = 400;
    // Provisoriamente es esta carpeta, en si, deberia guardarse en el Repositorio de Chapas
    static final String ruta = "D:\\Data User\\Desktop\\CodigosQR";

    public void crearQR(String mensaje, String nombre) throws IOException {
        BitMatrix matrix;
        Writer escritor = new QRCodeWriter();

       try {
           matrix = escritor.encode(mensaje, BarcodeFormat.QR_CODE, ancho, altura);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }

        BufferedImage imagen = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y < altura; y++) {
            for(int x = 0; x < ancho; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }

        // Por el momento se guarda en esta ruta
        String rutaFinal = ruta + "\\" + nombre;
        FileOutputStream qrCode = new FileOutputStream(rutaFinal);

        ImageIO.write(imagen, "png", qrCode);
        qrCode.close();
    }

}
