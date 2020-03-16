package com.example.anuin.utils.model;

public class Users {

    /**
     * STATUS : 200
     * MESSAGE : Login Success
     * DATA : {"id":3,"name":"Farhan","email":"hafifi1202@gmail.com","username":"hafifi1202","deleted":"0","actived":"1","type":"0","member_image":"http://104.248.149.236/anuin/gateway/public/media/default/member/default.png","user_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QiLCJzdWIiOjMsImlhdCI6MTU4NDMzMDg5NSwiZXhwIjoxNTg0NDE3Mjk1LCJtZW1iZXJfaWQiOjMsImVtYWlsIjoiaGFmaWZpMTIwMkBnbWFpbC5jb20ifQ.uxIOfV6K57sq39Q2LW290XsQuf2D-QlPt3zqLlAfllk","unextimage":"default/member/default.png"}
     */

    private String STATUS;
    private String MESSAGE;
    private DATABean DATA;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public DATABean getDATA() {
        return DATA;
    }

    public void setDATA(DATABean DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * id : 3
         * name : Farhan
         * email : hafifi1202@gmail.com
         * username : hafifi1202
         * deleted : 0
         * actived : 1
         * type : 0
         * member_image : http://104.248.149.236/anuin/gateway/public/media/default/member/default.png
         * user_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QiLCJzdWIiOjMsImlhdCI6MTU4NDMzMDg5NSwiZXhwIjoxNTg0NDE3Mjk1LCJtZW1iZXJfaWQiOjMsImVtYWlsIjoiaGFmaWZpMTIwMkBnbWFpbC5jb20ifQ.uxIOfV6K57sq39Q2LW290XsQuf2D-QlPt3zqLlAfllk
         * unextimage : default/member/default.png
         */

        private int id;
        private String name;
        private String email;
        private String username;
        private String deleted;
        private String actived;
        private String type;
        private String member_image;
        private String user_token;
        private String unextimage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getActived() {
            return actived;
        }

        public void setActived(String actived) {
            this.actived = actived;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMember_image() {
            return member_image;
        }

        public void setMember_image(String member_image) {
            this.member_image = member_image;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getUnextimage() {
            return unextimage;
        }

        public void setUnextimage(String unextimage) {
            this.unextimage = unextimage;
        }
    }
}
