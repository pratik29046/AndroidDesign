package com.taak.pro.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePOJO {
    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public class Content {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("poster")
        @Expose
        private String poster;
        @SerializedName("v_poster")
        @Expose
        private String vPoster;
        @SerializedName("age_rating")
        @Expose
        private String ageRating;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getvPoster() {
            return vPoster;
        }

        public void setvPoster(String vPoster) {
            this.vPoster = vPoster;
        }

        public String getAgeRating() {
            return ageRating;
        }

        public void setAgeRating(String ageRating) {
            this.ageRating = ageRating;
        }

    }

    public class Category {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("vertical")
        @Expose
        private Boolean vertical;
        @SerializedName("see_more_url")
        @Expose
        private String seeMoreUrl;
        @SerializedName("content")
        @Expose
        private List<Content> content = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getVertical() {
            return vertical;
        }

        public void setVertical(Boolean vertical) {
            this.vertical = vertical;
        }

        public String getSeeMoreUrl() {
            return seeMoreUrl;
        }

        public void setSeeMoreUrl(String seeMoreUrl) {
            this.seeMoreUrl = seeMoreUrl;
        }

        public List<Content> getContent() {
            return content;
        }

        public void setContent(List<Content> content) {
            this.content = content;
        }

    }

    public class Banner {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("poster")
        @Expose
        private String poster;
        @SerializedName("content_link")
        @Expose
        private String contentLink;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("external")
        @Expose
        private Boolean external;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getContentLink() {
            return contentLink;
        }

        public void setContentLink(String contentLink) {
            this.contentLink = contentLink;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Boolean getExternal() {
            return external;
        }

        public void setExternal(Boolean external) {
            this.external = external;
        }

    }

    public class Member {

        @SerializedName("img")
        @Expose
        private int img;

        public int getImg() {
            return img;
        }
        public void setImg(int img) {
            this.img = img;
        }
    }
}
