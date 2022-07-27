package com.itheima.common.util.au;

import lombok.Data;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/7/30 16:44
 * @description 标题
 * @package com.itheima.common.pojo
 */
@Data
public class UserTokenInfoExp extends UserTokenInfo {
    //过期时间
    private Long exp;
}
