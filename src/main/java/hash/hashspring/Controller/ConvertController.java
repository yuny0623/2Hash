package hash.hashspring.Controller;

import hash.hashspring.Dto.MFile;
import hash.hashspring.Service.FileService;
import hash.hashspring.Service.KeyService;
import hash.hashspring.Utils.BasicResponse;
import hash.hashspring.Utils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class ConvertController {

    private final KeyService keyService;
    private final FileService fileService;

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
    public String convertImgToHash(@RequestBody MFile mfile){
        File resultFile = fileService.multipartToFile(mfile.getFile());
        String resultBinary = fileService.fileToBinary(resultFile);
        return resultBinary;
    }
}
