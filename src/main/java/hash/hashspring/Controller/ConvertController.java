package hash.hashspring.Controller;

import hash.hashspring.Service.KeyService;
import hash.hashspring.Utils.BasicResponse;
import hash.hashspring.Utils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class ConvertController {

    private final KeyService keyService;

    public ConvertController(KeyService keyService) {
        this.keyService = keyService;
    }

    /**
     * public key - private key generator api
     */
    @GetMapping("/key")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> generateKeyController(){
        HashMap<String, String> hashMap;
        hashMap = keyService.createKeyPairAsString();
        return ResponseEntity.ok().body(new CommonResponse<>(hashMap));
    }

//    /**
//     * convert image to hash
//     */
//    @PostMapping("/hash")
//    @ResponseBody
//    public ResponseEntity<? extends BasicResponse> convertImgToHash(){
//
//    }
}
