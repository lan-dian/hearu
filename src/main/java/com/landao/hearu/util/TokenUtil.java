package com.landao.hearu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.model.user.BaseUserInfoDTO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * token工具类
 */
public class TokenUtil {

    private static final String privateKey="newxyydsnewxnb666";

    /**
     * 加密token
     */
    public static String getToken(BaseUserInfoDTO userInfo) {
        return getToken(userInfo.getUserId(), userInfo.getName());
    }

    /**
     * 加密token
     */
    public static String getToken(Long userId,String name) {
        return JWT.create()
                .withClaim("user_id",userId)
                .withClaim("name",name)
                .sign(Algorithm.HMAC256(privateKey));
    }

    /**
     * 解析token
     */
    public static BaseUserInfoDTO parseToken(String Authorization) {
        String[] tokenInfo = Authorization.split(" ");
        if(tokenInfo.length!=2){
            throw new BusinessException("token格式错误");
        }
        if(!Objects.equals("Bearer",tokenInfo[0])){
            throw new BusinessException("token必须以Bearer开头");
        }
        Claim userId=null;
        Claim name=null;
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(privateKey))
                    .build().verify(tokenInfo[1]);
            userId=decodedJWT.getClaim("user_id");
            name=decodedJWT.getClaim("name");
        }catch (Exception e){
            throw new BusinessException("token非法！");
        }
        return new BaseUserInfoDTO(userId.asLong(), name.asString());
    }


    /**
     * 检测是否有token
     * @return 有返回true，否则返回false
     */
    public static boolean hasToken(){
        String authorization = getAuthorization();
        return !StringUtils.isBlank(authorization);
    }

    /**
     * 获取token
     * @return 带Bearer前缀的token
     */
    public static String getAuthorization(){
        return NewxWebUtil.getRequest().getHeader("Authorization");
    }


    /**
     * 设置用户基本信息
     * @param baseUserInfo 用户基本信息
     */
    public static void setBaseUserInfo(BaseUserInfoDTO baseUserInfo){
        NewxWebUtil.setAttribute("user",baseUserInfo);
    }

    public static BaseUserInfoDTO getBaseUserInfo(){
        HttpServletRequest request = NewxWebUtil.getRequest();
        return (BaseUserInfoDTO) request.getAttribute("user");

    }

    public static boolean isLogin(){
        HttpServletRequest request = NewxWebUtil.getRequest();
        BaseUserInfoDTO user = (BaseUserInfoDTO) request.getAttribute("user");
        return user != null;
    }

    public static Long getUserId(){
        return getBaseUserInfo().getUserId();
    }

    public static String getUserName(){
        return getBaseUserInfo().getName();
    }


}
