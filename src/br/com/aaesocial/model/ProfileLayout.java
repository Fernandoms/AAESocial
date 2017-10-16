package br.com.aaesocial.model;

public class ProfileLayout {

    private String photoUrl;
    private String bgColor;
    private String badge;

    public ProfileLayout() {}

    public ProfileLayout(String badge) {
        this.badge = badge;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
