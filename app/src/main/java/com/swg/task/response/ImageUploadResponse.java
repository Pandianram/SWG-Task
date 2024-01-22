package com.swg.task.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageUploadResponse {

    @SerializedName("user")
    @Expose
    private LoginRegDts user;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Messagearabic")
    @Expose
    private String messagearabic;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Userid")
    @Expose
    private String Userid;
    @SerializedName("resetpassword")
    @Expose
    private String resetpassword;

    public LoginRegDts getUser() {
        return user;
    }

    public void setUser(LoginRegDts user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessagearabic() {
        return messagearabic;
    }

    public void setMessagearabic(String messagearabic) {
        this.messagearabic = messagearabic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }

    public String getResetpassword() {
        return resetpassword;
    }

    public void setResetpassword(String resetpassword) {
        this.resetpassword = resetpassword;
    }


    @SerializedName("dashboardmenu")
    @Expose
    private String dashboardmenu;
    @SerializedName("menu")
    @Expose
    private List<String> menu = null;

    public String getDashboardmenu() {
        return dashboardmenu;
    }

    public void setDashboardmenu(String dashboardmenu) {
        this.dashboardmenu = dashboardmenu;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }


    public class LoginRegDts {

        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("uniqueid")
        @Expose
        private String uniqueid;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("countrycode")
        @Expose
        private String countrycode;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("user_image")
        @Expose
        private String userImage;
        @SerializedName("notification")
        @Expose
        private String notification;
        @SerializedName("cardnamearabic")
        @Expose
        private String cardnamearabic;
        @SerializedName("cardnameenglish")
        @Expose
        private String cardnameenglish;
        @SerializedName("carddesignation")
        @Expose
        private String carddesignation;
        @SerializedName("cardmobileno")
        @Expose
        private String cardmobileno;
        @SerializedName("cardemail")
        @Expose
        private String cardemail;
        @SerializedName("cardaddress")
        @Expose
        private String cardaddress;
        @SerializedName("statusmessage")
        @Expose
        private String statusmessage;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUniqueid() {
            return uniqueid;
        }

        public void setUniqueid(String uniqueid) {
            this.uniqueid = uniqueid;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public String getCardnamearabic() {
            return cardnamearabic;
        }

        public void setCardnamearabic(String cardnamearabic) {
            this.cardnamearabic = cardnamearabic;
        }

        public String getCardnameenglish() {
            return cardnameenglish;
        }

        public void setCardnameenglish(String cardnameenglish) {
            this.cardnameenglish = cardnameenglish;
        }

        public String getCarddesignation() {
            return carddesignation;
        }

        public void setCarddesignation(String carddesignation) {
            this.carddesignation = carddesignation;
        }

        public String getCardmobileno() {
            return cardmobileno;
        }

        public void setCardmobileno(String cardmobileno) {
            this.cardmobileno = cardmobileno;
        }

        public String getCardemail() {
            return cardemail;
        }

        public void setCardemail(String cardemail) {
            this.cardemail = cardemail;
        }

        public String getCardaddress() {
            return cardaddress;
        }

        public void setCardaddress(String cardaddress) {
            this.cardaddress = cardaddress;
        }

        public String getStatusmessage() {
            return statusmessage;
        }

        public void setStatusmessage(String statusmessage) {
            this.statusmessage = statusmessage;
        }



        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("org_id")
        @Expose
        private String org_id;
        @SerializedName("organization_name")
        @Expose
        private String organizationName;
        @SerializedName("organization_logo")
        @Expose
        private String organizationLogo;

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getOrganizationLogo() {
            return organizationLogo;
        }

        public void setOrganizationLogo(String organizationLogo) {
            this.organizationLogo = organizationLogo;
        }

    }
}
