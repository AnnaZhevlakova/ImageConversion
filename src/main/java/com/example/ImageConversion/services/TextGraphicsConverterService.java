package com.example.ImageConversion.services;

import com.example.ImageConversion.dto.ImageParams;
import com.example.ImageConversion.exceptions.UserException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class TextGraphicsConverterService {
    private final TextColorSchemaService schema;

    public TextGraphicsConverterService(TextColorSchemaService schema) {
        this.schema = schema;
    }

    public String convert(byte[] file, ImageParams imageParams) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(file)) {
            BufferedImage img = ImageIO.read(bais);
            int width = img.getWidth();
            int height = img.getHeight();

            double maxRatio = imageParams.getMaxRatio();
            int maxWidth = imageParams.getMaxWidth();
            int maxHeight = imageParams.getMaxHeight();

            double ratio = width > height
                    ? (double) width / height
                    : (double) height / width;
            if (maxRatio != -1 && ratio > maxRatio) {
                throw new UserException("Максимальное соотношение сторон изображения " + maxRatio + ", а у этой " + ratio);

            }
            // Вычисляем новые размеры с сохранением пропорций
            int newWidth = width;
            int newHeight = height;

            if (maxWidth > 0 && maxHeight > 0) {
                // Если заданы и ширина, и высота, выбираем минимальный масштаб
                double widthRatio = (double) maxWidth / width;
                double heightRatio = (double) maxHeight / height;
                double scale = Math.min(widthRatio, heightRatio);

                newWidth = (int) (width * scale);
                newHeight = (int) (height * scale);
            } else if (maxWidth > 0) {
                // Если задана только ширина
                double scale = (double) maxWidth / width;
                newWidth = maxWidth;
                newHeight = (int) (height * scale);
            } else if (maxHeight > 0) {
                // Если задана только высота
                double scale = (double) maxHeight / height;
                newHeight = maxHeight;
                newWidth = (int) (width * scale);
            }
            // Теперь нам нужно попросить картинку изменить свои размеры на новые.
            // Последний параметр означает, что мы просим картинку плавно сузиться
            // на новые размеры. В результате мы получаем ссылку на новую картинку, которая
            // представляет собой суженную старую.
            Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

            // Теперь сделаем её чёрно-белой. Для этого поступим так:
            // Создадим новую пустую картинку нужных размеров, заранее указав последним
            // параметром чёрно-белую цветовую палитру:
            BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
            // Попросим у этой картинки инструмент для рисования на ней:
            Graphics2D graphics = bwImg.createGraphics();
            // А этому инструменту скажем, чтобы он скопировал содержимое из нашей суженной картинки:
            graphics.drawImage(scaledImage, 0, 0, null);

            // Теперь давайте пройдёмся по пикселям нашего изображения.
            // Если для рисования мы просили у картинки .createGraphics(),
            // то для прохода по пикселям нам нужен будет этот инструмент:
            WritableRaster bwRaster = bwImg.getRaster();


            StringBuilder sb = new StringBuilder(newWidth * newHeight + newHeight);
            for (int h = 0; h < newHeight; h++) {
                for (int w = 0; w < newWidth; w++) {
                    int color = bwRaster.getPixel(w, h, new int[3])[0];
                    char c = schema.convert(color);
                    sb.append(c);    //запоминаем символ c, например, в двумерном массиве или как-то ещё на ваше усмотрение
                }
                sb.append(System.lineSeparator());
            }


            return sb.toString();
        }

    }
}
