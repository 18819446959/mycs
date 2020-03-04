package cn.mycs.service.member.server.exception;

/**
 * <p>会员身份不存在异常</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/21 15:21
 * </pre>
 */
public class MemberIdentityNotExitException extends Exception {
    public MemberIdentityNotExitException() {
    }

    public MemberIdentityNotExitException(String msg) {
        super(msg);
    }

}
