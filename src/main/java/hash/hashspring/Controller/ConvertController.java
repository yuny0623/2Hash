package hash.hashspring.Controller;

import hash.hashspring.Dto.MFile;
import hash.hashspring.Service.FileService;
import hash.hashspring.Service.KeyService;
import hash.hashspring.Utils.BasicResponse;
import hash.hashspring.Utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class ConvertController {

    private final KeyService keyService;
    private final FileService fileService;

    @Autowired
    public ConvertController(KeyService keyService, FileService fileService) {
        this.keyService = keyService;
        this.fileService = fileService;
    }

    /**
     * public key - private key generator api
     */
    @GetMapping("/key")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> generateKeyController(){
        System.out.println("새로운 키 생성을 요청합니다.");
        HashMap<String, String> hashMap;
        hashMap = keyService.createKeyPairAsString();
        return ResponseEntity.ok().body(new CommonResponse<>(hashMap));
    }

    /**
     * convert image to hash
     */
    @PostMapping("/hash")
    @ResponseBody
    public String convertImgToHash(@ModelAttribute MFile mFile) throws IOException {
        String converted = new String(mFile.getFile().getBytes());
        System.out.println("converted:" + converted);
        String encryptedData = keyService.encode(converted, mFile.getPublicKey());
        return encryptedData;
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(@ModelAttribute MFile mFile){
        System.out.println(mFile.getFile().toString());
        return "true";
    }
}
