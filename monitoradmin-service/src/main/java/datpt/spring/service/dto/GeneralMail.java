package datpt.spring.service.dto;

public class GeneralMail {
    private String from;
    private String to;
    private String toName;
    private String subject;
    private String content;

    public GeneralMail() {
    }

    public GeneralMail(String from, String to, String toName, String subject, String content) {
        this.from = from;
        this.to = to;
        this.toName = toName;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GeneralMail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", toName='" + toName + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

