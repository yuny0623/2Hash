package hash.hashspring.Service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {

    public File multipartToFile(MultipartFile multipart) {
        File convFile = new File(multipart.getOriginalFilename());
        try {
            multipart.transferTo(convFile);
        }catch(Exception e){
            e.printStackTrace();
        }
        return convFile;
    }

    /**
     * 파일을 바이너리로 변환
     */
    public static String fileToBinary(File file) {
        String out = new String();
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Exception position : FileUtil - fileToString(File file)");
        }

        int len = 0;
        byte[] buf = new byte[1024];
        try {
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            byte[] fileArray = baos.toByteArray();
            out = new String(base64Enc(fileArray));

            fis.close();
            baos.close();
        } catch (IOException e) {
            System.out.println("Exception position : FileUtil - fileToString(File file)");
        }

        return out;
    }

    public static byte[] base64Enc(byte[] buffer) {
        return Base64.encodeBase64(buffer);
    }
}
