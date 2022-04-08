package hash.hashspring.Utils;

public class ErrorResponse extends BasicResponse{
    private String message;
    public ErrorResponse(String message) {
        this.message = message;
    }
}