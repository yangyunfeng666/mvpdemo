package com.yunsoft.mvpdemo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


/**
 * user 对象
 */
@Entity(tableName = "LocalUserInfo")
public class LocalUserInfo implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "nickname")
    private String nickname;
    @ColumnInfo(name = "headurl")
    private String headurl;//头像
    @ColumnInfo(name = "cover")
    private String cover;//背景大图
    @ColumnInfo(name = "token")
    private String token;
    @ColumnInfo(name = "sex")
    private String sex;
    @ColumnInfo(name = "age")
    private String age;
    @ColumnInfo(name = "sign")
    private String sign;//签名
    @ColumnInfo(name = "emotion")
    private String emotion;//情感状态
    @ColumnInfo(name = "email")
    private String email;//邮箱
    @ColumnInfo(name = "telephone")
    private String telephone;//电话
    @ColumnInfo(name = "password")
    private String password;//密码
    @ColumnInfo(name = "lastLoginType")
    private int lastLoginType = 0;//0手机号 1qq 2微信

    @Ignore
    public LocalUserInfo(@NonNull int userid, String username, String nickname, String headurl, String cover, String token, String sex, String age, String sign, String emotion, String email, String telephone, String password, int lastLoginType) {
        this.userid = userid;
        this.username = username;
        this.nickname = nickname;
        this.headurl = headurl;
        this.cover = cover;
        this.token = token;
        this.sex = sex;
        this.age = age;
        this.sign = sign;
        this.emotion = emotion;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.lastLoginType = lastLoginType;
    }

    public int getLastLoginType() {
        return lastLoginType;
    }

    public void setLastLoginType(int lastLoginType) {
        this.lastLoginType = lastLoginType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    @Override
    public String toString() {
        return "LocalUserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headurl='" + headurl + '\'' +
                ", cover='" + cover + '\'' +
                ", token='" + token + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", sign='" + sign + '\'' +
                ", emotion='" + emotion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userid);
        dest.writeString(this.username);
        dest.writeString(this.nickname);
        dest.writeString(this.headurl);
        dest.writeString(this.cover);
        dest.writeString(this.token);
        dest.writeString(this.sex);
        dest.writeString(this.age);
        dest.writeString(this.sign);
        dest.writeString(this.emotion);
        dest.writeString(this.email);
        dest.writeString(this.telephone);
        dest.writeString(this.password);
        dest.writeInt(this.lastLoginType);
    }

    public LocalUserInfo() {
    }

    protected LocalUserInfo(Parcel in) {
        this.userid = in.readInt();
        this.username = in.readString();
        this.nickname = in.readString();
        this.headurl = in.readString();
        this.cover = in.readString();
        this.token = in.readString();
        this.sex = in.readString();
        this.age = in.readString();
        this.sign = in.readString();
        this.emotion = in.readString();
        this.email = in.readString();
        this.telephone = in.readString();
        this.password = in.readString();
        this.lastLoginType = in.readInt();
    }

    public static final Parcelable.Creator<LocalUserInfo> CREATOR = new Parcelable.Creator<LocalUserInfo>() {
        @Override
        public LocalUserInfo createFromParcel(Parcel source) {
            return new LocalUserInfo(source);
        }

        @Override
        public LocalUserInfo[] newArray(int size) {
            return new LocalUserInfo[size];
        }
    };
}
