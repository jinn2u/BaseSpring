package com.cos.blog.model;

class KakaoAccount {

public class KakaoProfile {

    private Integer id;
    private String connectedAt;
    private Properties properties;
    private KakaoAccount kakaoAccount;

class Profile {

    private String nickname;
    private String thumbnailImageUrl;
    private String profileImageUrl;



}
-----------------------------------com.cos.blog.model.Properties.java-----------------------------------

        package com.cos.blog.model;

        import javax.annotation.Generated;

class Properties {

    private String nickname;
    private String profileImage;
    private String thumbnailImage;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

}