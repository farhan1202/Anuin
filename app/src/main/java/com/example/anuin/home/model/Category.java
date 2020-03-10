package com.example.anuin.home.model;

import java.util.List;

public class Category {

    /**
     * STATUS : 200
     * MESSAGE : Success Master Category List
     * DATA : [{"id":1,"master_category_index":"1","master_category_title":"Mekanikal & Elektronik","master_category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","created_at":"2020-01-01 00:00:00","updated_at":"","deleted":"0","actived":"1","sub_category":[{"id":1,"master_category_id":1,"category_index":"1","category_title":"AC","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"","unextimage":"default/category/default.png"}]},{"id":2,"master_category_index":"2","master_category_title":"Dekor & Bangunan","master_category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","created_at":"2020-01-01 00:00:00","updated_at":"","deleted":"0","actived":"1","sub_category":[{"id":2,"master_category_id":2,"category_index":"1","category_title":"Cat","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"","unextimage":"default/category/default.png"},{"id":4,"master_category_id":2,"category_index":"4","category_title":"Test Category image","category_desc":"Test category image","category_image":"http://104.248.149.236/anuin/gateway/public/media/images/category/238840693.png","deleted":"0","actived":"1","created_by":"1","updated_by":"","created_at":"2020-03-10 05:01:33","updated_at":"2020-03-10 05:01:33","unextimage":"images/category/238840693.png"}]},{"id":3,"master_category_index":"3","master_category_title":"Guru & Mentor","master_category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","created_at":"2020-01-01 00:00:00","updated_at":"","deleted":"0","actived":"1","sub_category":[{"id":3,"master_category_id":3,"category_index":"1","category_title":"Les Private","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"","unextimage":"default/category/default.png"}]}]
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
         * master_category_index : 1
         * master_category_title : Mekanikal & Elektronik
         * master_category_desc : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.
         * created_at : 2020-01-01 00:00:00
         * updated_at :
         * deleted : 0
         * actived : 1
         * sub_category : [{"id":1,"master_category_id":1,"category_index":"1","category_title":"AC","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"","unextimage":"default/category/default.png"}]
         */

        private int id;
        private String master_category_index;
        private String master_category_title;
        private String master_category_desc;
        private String created_at;
        private String updated_at;
        private String deleted;
        private String actived;
        private List<SubCategoryBean> sub_category;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMaster_category_index() {
            return master_category_index;
        }

        public void setMaster_category_index(String master_category_index) {
            this.master_category_index = master_category_index;
        }

        public String getMaster_category_title() {
            return master_category_title;
        }

        public void setMaster_category_title(String master_category_title) {
            this.master_category_title = master_category_title;
        }

        public String getMaster_category_desc() {
            return master_category_desc;
        }

        public void setMaster_category_desc(String master_category_desc) {
            this.master_category_desc = master_category_desc;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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

        public List<SubCategoryBean> getSub_category() {
            return sub_category;
        }

        public void setSub_category(List<SubCategoryBean> sub_category) {
            this.sub_category = sub_category;
        }

        public static class SubCategoryBean {
            /**
             * id : 1
             * master_category_id : 1
             * category_index : 1
             * category_title : AC
             * category_desc : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.
             * category_image : http://104.248.149.236/anuin/gateway/public/media/default/category/default.png
             * deleted : 0
             * actived : 1
             * created_by : 1
             * updated_by : 1
             * created_at : 2020-01-01 00:00:00
             * updated_at :
             * unextimage : default/category/default.png
             */

            private int id;
            private int master_category_id;
            private String category_index;
            private String category_title;
            private String category_desc;
            private String category_image;
            private String deleted;
            private String actived;
            private String created_by;
            private String updated_by;
            private String created_at;
            private String updated_at;
            private String unextimage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMaster_category_id() {
                return master_category_id;
            }

            public void setMaster_category_id(int master_category_id) {
                this.master_category_id = master_category_id;
            }

            public String getCategory_index() {
                return category_index;
            }

            public void setCategory_index(String category_index) {
                this.category_index = category_index;
            }

            public String getCategory_title() {
                return category_title;
            }

            public void setCategory_title(String category_title) {
                this.category_title = category_title;
            }

            public String getCategory_desc() {
                return category_desc;
            }

            public void setCategory_desc(String category_desc) {
                this.category_desc = category_desc;
            }

            public String getCategory_image() {
                return category_image;
            }

            public void setCategory_image(String category_image) {
                this.category_image = category_image;
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

            public String getCreated_by() {
                return created_by;
            }

            public void setCreated_by(String created_by) {
                this.created_by = created_by;
            }

            public String getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(String updated_by) {
                this.updated_by = updated_by;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getUnextimage() {
                return unextimage;
            }

            public void setUnextimage(String unextimage) {
                this.unextimage = unextimage;
            }
        }
    }
}
