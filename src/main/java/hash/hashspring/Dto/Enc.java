package hash.hashspring.Dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enc {
    private String plainText;  // 평문
    private String publicKey;  // 공개키
}
