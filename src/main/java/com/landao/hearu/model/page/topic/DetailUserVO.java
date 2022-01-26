package com.landao.hearu.model.page.topic;

import com.landao.hearu.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 详细用户信息
 */
@Data
@NoArgsConstructor
public class DetailUserVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;


    public static DetailUserVO convert(User user){
        DetailUserVO detailUserVO = new DetailUserVO();
        detailUserVO.setUserId(user.getId());
        detailUserVO.setName(user.getName());
        detailUserVO.setTelephone(user.getTelephone());
        detailUserVO.setSex(user.getSex());
        detailUserVO.setAvatar(user.getAvatar());
        detailUserVO.setSignature(user.getSignature());
        return detailUserVO;
    }


}
