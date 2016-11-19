package acmeindustries.boondoggletd.model;


public class Notification {

    private String text;
    private boolean status; // true = currently notifying

    public Notification(){
        this.text = "";
        this.status = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void newNotification(String text){
        this.setStatus(true);
        this.setText(text);
    }
}
