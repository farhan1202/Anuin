package com.example.anuin.home.model;

import java.util.List;

public class Banner {

    /**
     * STATUS : 200
     * MESSAGE : Success Banner
     * DATA : [{"id":1,"banner_index":"1","banner_title":"Listrik","banner_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","banner_image":"http://104.248.149.236/anuin/gateway/public/media/default/banner/default.png","unextimage":"default/banner/default.png"},{"id":2,"banner_index":"2","banner_title":"Elektronik","banner_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","banner_image":"http://104.248.149.236/anuin/gateway/public/media/default/banner/default.png","unextimage":"default/banner/default.png"},{"id":3,"banner_index":"3","banner_title":"Listrik","banner_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","banner_image":"http://104.248.149.236/anuin/gateway/public/media/default/banner/default.png","unextimage":"default/banner/default.png"}]
     */

    private String STATUS;
    private String MESSAGE;
    private List<DATABean> DATA;

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

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * id : 1
         * banner_index : 1
         * banner_title : Listrik
         * banner_desc : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.
         * banner_image : http://104.248.149.236/anuin/gateway/public/media/default/banner/default.png
         * unextimage : default/banner/default.png
         */

        private int id;
        private String banner_index;
        private String banner_title;
        private String banner_desc;
        private String banner_image;
        private String unextimage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBanner_index() {
            return banner_index;
        }

        public void setBanner_index(String banner_index) {
            this.banner_index = banner_index;
        }

        public String getBanner_title() {
            return banner_title;
        }

        public void setBanner_title(String banner_title) {
            this.banner_title = banner_title;
        }

        public String getBanner_desc() {
            return banner_desc;
        }

        public void setBanner_desc(String banner_desc) {
            this.banner_desc = banner_desc;
        }

        public String getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }

        public String getUnextimage() {
            return unextimage;
        }

        public void setUnextimage(String unextimage) {
            this.unextimage = unextimage;
        }
    }
}
