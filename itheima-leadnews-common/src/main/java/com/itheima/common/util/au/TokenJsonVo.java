package com.itheima.common.util.au;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/7/30 16:22
 * @description 标题
 * @package com.itheima.common.pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenJsonVo {

    //访问令牌
    private String accessToken;

    //刷新令牌
    private String refreshToken;
}
