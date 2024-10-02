package datpt.spring.dto;

public class UserResponse {
    private String message;
    private int userId;
    private String username;

    public UserResponse() {
    }

    public UserResponse(String message, int userId, String username) {
        this.message = message;
        this.userId = userId;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
