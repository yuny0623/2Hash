package hash.hashspring.Dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MFile {
    private MultipartFile file;
}
