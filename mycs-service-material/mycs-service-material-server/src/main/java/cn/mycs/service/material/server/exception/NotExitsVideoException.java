package cn.mycs.service.material.server.exception;

/**
 * <p>没有视频异常</p>
 * <pre>
 * @author gitamacai
 * @date 2019/10/10 11:35
 * </pre>
 */
public class NotExitsVideoException extends Exception {
    private static final long serialVersionUID = -5192481809246880408L;

    public NotExitsVideoException() {
        super();
    }

    public NotExitsVideoException(String msg) {
        super(msg);
    }

}
