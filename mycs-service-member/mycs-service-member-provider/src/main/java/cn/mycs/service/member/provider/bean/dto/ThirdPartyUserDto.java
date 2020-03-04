package cn.mycs.service.member.provider.bean.dto;

import java.util.List;

/**
 * <p>用户的openid</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/25 19:51
 * </pre>
 */
public class ThirdPartyUserDto {

    private List<ThirdPartyUser> users;

    public List<ThirdPartyUser> getUsers() {
        return users;
    }

    public void setUsers(List<ThirdPartyUser> users) {
        this.users = users;
    }

    public static class ThirdPartyUser {
        private Long userId;
        private String type;
        private String openid;
        private String unionid;
        private String appid;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

    }

}
