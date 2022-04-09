package hash.hashspring.Dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dec {
    private String ciper;     // 암호문
    private String privateKey;// private key
}
