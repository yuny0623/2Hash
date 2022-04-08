package hash.hashspring.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MFile {
    private String publicKey;
    private MultipartFile file;
}
