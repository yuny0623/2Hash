package hash.hashspring.Controller;

import hash.hashspring.Dto.Dec;
import hash.hashspring.Dto.Enc;
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
import java.security.Key;
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
     *  text2hash api
     */
    @GetMapping("/text2hash")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> text2HashController(@RequestBody Enc enc){
        String plainText = enc.getPlainText();
        String publicKey = enc.getPublicKey();
        String result = keyService.encode(plainText, publicKey);
        return ResponseEntity.ok().body(new CommonResponse<>(result));
    }

    /**
     *  hash2text api
     */
    @GetMapping("/hash2text")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> hash2TextController(@RequestBody Dec dec){
        String ciper = dec.getCiper();
        String privateKey = dec.getPrivateKey();
        String result = keyService.decode(ciper, privateKey);
        return ResponseEntity.ok().body(new CommonResponse<>(result));
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
