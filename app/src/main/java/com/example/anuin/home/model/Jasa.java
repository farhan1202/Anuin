package com.example.anuin.home.model;

import java.util.List;

public class Jasa {


    /**
     * STATUS : 200
     * MESSAGE : Success Sub Category List
     * DATA : [{"id":1,"category_index":"1","category_title":"AC","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","category_banner":"","unextimage":"default/category/default.png","product_jasa":[{"id":1,"category_id":1,"product_jasa_index":"1","product_jasa_title":"Perbaikan/Instalasi","product_jasa_desc":"Desc","product_jasa_harga":1000000,"product_jasa_warning":"warning","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":""}]}]
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
         * category_index : 1
         * category_title : AC
         * category_image : http://104.248.149.236/anuin/gateway/public/media/default/category/default.png
         * category_banner :
         * unextimage : default/category/default.png
         * product_jasa : [{"id":1,"category_id":1,"product_jasa_index":"1","product_jasa_title":"Perbaikan/Instalasi","product_jasa_desc":"Desc","product_jasa_harga":1000000,"product_jasa_warning":"warning","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":""}]
         */

        private int id;
        private String category_index;
        private String category_title;
        private String category_image;
        private String category_banner;
        private String unextimage;
        private List<ProductJasaBean> product_jasa;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getCategory_image() {
            return category_image;
        }

        public void setCategory_image(String category_image) {
            this.category_image = category_image;
        }

        public String getCategory_banner() {
            return category_banner;
        }

        public void setCategory_banner(String category_banner) {
            this.category_banner = category_banner;
        }

        public String getUnextimage() {
            return unextimage;
        }

        public void setUnextimage(String unextimage) {
            this.unextimage = unextimage;
        }

        public List<ProductJasaBean> getProduct_jasa() {
            return product_jasa;
        }

        public void setProduct_jasa(List<ProductJasaBean> product_jasa) {
            this.product_jasa = product_jasa;
        }

        public static class ProductJasaBean {
            /**
             * id : 1
             * category_id : 1
             * product_jasa_index : 1
             * product_jasa_title : Perbaikan/Instalasi
             * product_jasa_desc : Desc
             * product_jasa_harga : 1000000
             * product_jasa_warning : warning
             * deleted : 0
             * actived : 1
             * created_by : 1
             * updated_by : 1
             * created_at : 2020-01-01 00:00:00
             * updated_at :
             */

            private int id;
            private int category_id;
            private String product_jasa_index;
            private String product_jasa_title;
            private String product_jasa_desc;
            private int product_jasa_harga;
            private String product_jasa_warning;
            private String deleted;
            private String actived;
            private String created_by;
            private String updated_by;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getProduct_jasa_index() {
                return product_jasa_index;
            }

            public void setProduct_jasa_index(String product_jasa_index) {
                this.product_jasa_index = product_jasa_index;
            }

            public String getProduct_jasa_title() {
                return product_jasa_title;
            }

            public void setProduct_jasa_title(String product_jasa_title) {
                this.product_jasa_title = product_jasa_title;
            }

            public String getProduct_jasa_desc() {
                return product_jasa_desc;
            }

            public void setProduct_jasa_desc(String product_jasa_desc) {
                this.product_jasa_desc = product_jasa_desc;
            }

            public int getProduct_jasa_harga() {
                return product_jasa_harga;
            }

            public void setProduct_jasa_harga(int product_jasa_harga) {
                this.product_jasa_harga = product_jasa_harga;
            }

            public String getProduct_jasa_warning() {
                return product_jasa_warning;
            }

            public void setProduct_jasa_warning(String product_jasa_warning) {
                this.product_jasa_warning = product_jasa_warning;
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
        }
    }
}
