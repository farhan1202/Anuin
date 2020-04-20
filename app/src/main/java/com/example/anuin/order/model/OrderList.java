package com.example.anuin.order.model;

import java.util.List;

public class OrderList {

    /**
     * STATUS : 200
     * MESSAGE : Booking List
     * DATA : [{"id":138,"member_id":46,"product_jasa_id":1,"member_address_id":55,"booking_code_id":138,"booking_payment_id":"","provinsi":"2","city":"7","kecamatan":"128","kelurahan":"1305","kode_post":26161,"detail_lokasi":"hjn","work_date":"2020-04-20 13:56:00","detail_pekerjaan":"g y gvg","biaya_panggil":1000000,"biaya_layanan":50000,"total_tagihan":1050000,"payment_method":"0","booking_status":"0","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04","product_jasa":{"id":1,"category_id":1,"product_jasa_index":"1","product_jasa_title":"Perbaikan/Instalasi","product_jasa_desc":"Desc","product_jasa_harga":1000000,"product_jasa_warning":"warning","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"2020-03-31 00:21:38","category":{"id":1,"master_category_id":1,"category_index":"1","category_title":"AC","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","category_banner":"","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"2020-03-10 05:01:33","unextimage":"default/category/default.png"}},"booking_code":{"id":138,"booking_id":138,"member_id":46,"code_name":"9284620200420","booking_status":"0","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04"},"booking_payment":null,"booking_image":[{"id":113,"booking_id":138,"member_id":46,"booking_status":"0","image_name":"http://104.248.149.236/anuin/gateway/public/media/images/member-booking/281650379.jpg","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04","unextimage":"images/member-booking/281650379.jpg"},{"id":114,"booking_id":138,"member_id":46,"booking_status":"0","image_name":"http://104.248.149.236/anuin/gateway/public/media/images/member-booking/802072760.jpg","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04","unextimage":"images/member-booking/802072760.jpg"}]}]
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
         * id : 138
         * member_id : 46
         * product_jasa_id : 1
         * member_address_id : 55
         * booking_code_id : 138
         * booking_payment_id :
         * provinsi : 2
         * city : 7
         * kecamatan : 128
         * kelurahan : 1305
         * kode_post : 26161
         * detail_lokasi : hjn
         * work_date : 2020-04-20 13:56:00
         * detail_pekerjaan : g y gvg
         * biaya_panggil : 1000000
         * biaya_layanan : 50000
         * total_tagihan : 1050000
         * payment_method : 0
         * booking_status : 0
         * deleted : 0
         * created_at : 2020-04-20 13:57:04
         * updated_at : 2020-04-20 13:57:04
         * product_jasa : {"id":1,"category_id":1,"product_jasa_index":"1","product_jasa_title":"Perbaikan/Instalasi","product_jasa_desc":"Desc","product_jasa_harga":1000000,"product_jasa_warning":"warning","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"2020-03-31 00:21:38","category":{"id":1,"master_category_id":1,"category_index":"1","category_title":"AC","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","category_banner":"","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"2020-03-10 05:01:33","unextimage":"default/category/default.png"}}
         * booking_code : {"id":138,"booking_id":138,"member_id":46,"code_name":"9284620200420","booking_status":"0","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04"}
         * booking_payment : null
         * booking_image : [{"id":113,"booking_id":138,"member_id":46,"booking_status":"0","image_name":"http://104.248.149.236/anuin/gateway/public/media/images/member-booking/281650379.jpg","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04","unextimage":"images/member-booking/281650379.jpg"},{"id":114,"booking_id":138,"member_id":46,"booking_status":"0","image_name":"http://104.248.149.236/anuin/gateway/public/media/images/member-booking/802072760.jpg","deleted":"0","created_at":"2020-04-20 13:57:04","updated_at":"2020-04-20 13:57:04","unextimage":"images/member-booking/802072760.jpg"}]
         */

        private int id;
        private int member_id;
        private int product_jasa_id;
        private int member_address_id;
        private int booking_code_id;
        private String booking_payment_id;
        private String provinsi;
        private String city;
        private String kecamatan;
        private String kelurahan;
        private int kode_post;
        private String detail_lokasi;
        private String work_date;
        private String detail_pekerjaan;
        private int biaya_panggil;
        private int biaya_layanan;
        private int total_tagihan;
        private String payment_method;
        private String booking_status;
        private String deleted;
        private String created_at;
        private String updated_at;
        private ProductJasaBean product_jasa;
        private BookingCodeBean booking_code;
        private Object booking_payment;
        private List<BookingImageBean> booking_image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getProduct_jasa_id() {
            return product_jasa_id;
        }

        public void setProduct_jasa_id(int product_jasa_id) {
            this.product_jasa_id = product_jasa_id;
        }

        public int getMember_address_id() {
            return member_address_id;
        }

        public void setMember_address_id(int member_address_id) {
            this.member_address_id = member_address_id;
        }

        public int getBooking_code_id() {
            return booking_code_id;
        }

        public void setBooking_code_id(int booking_code_id) {
            this.booking_code_id = booking_code_id;
        }

        public String getBooking_payment_id() {
            return booking_payment_id;
        }

        public void setBooking_payment_id(String booking_payment_id) {
            this.booking_payment_id = booking_payment_id;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getKecamatan() {
            return kecamatan;
        }

        public void setKecamatan(String kecamatan) {
            this.kecamatan = kecamatan;
        }

        public String getKelurahan() {
            return kelurahan;
        }

        public void setKelurahan(String kelurahan) {
            this.kelurahan = kelurahan;
        }

        public int getKode_post() {
            return kode_post;
        }

        public void setKode_post(int kode_post) {
            this.kode_post = kode_post;
        }

        public String getDetail_lokasi() {
            return detail_lokasi;
        }

        public void setDetail_lokasi(String detail_lokasi) {
            this.detail_lokasi = detail_lokasi;
        }

        public String getWork_date() {
            return work_date;
        }

        public void setWork_date(String work_date) {
            this.work_date = work_date;
        }

        public String getDetail_pekerjaan() {
            return detail_pekerjaan;
        }

        public void setDetail_pekerjaan(String detail_pekerjaan) {
            this.detail_pekerjaan = detail_pekerjaan;
        }

        public int getBiaya_panggil() {
            return biaya_panggil;
        }

        public void setBiaya_panggil(int biaya_panggil) {
            this.biaya_panggil = biaya_panggil;
        }

        public int getBiaya_layanan() {
            return biaya_layanan;
        }

        public void setBiaya_layanan(int biaya_layanan) {
            this.biaya_layanan = biaya_layanan;
        }

        public int getTotal_tagihan() {
            return total_tagihan;
        }

        public void setTotal_tagihan(int total_tagihan) {
            this.total_tagihan = total_tagihan;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
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

        public ProductJasaBean getProduct_jasa() {
            return product_jasa;
        }

        public void setProduct_jasa(ProductJasaBean product_jasa) {
            this.product_jasa = product_jasa;
        }

        public BookingCodeBean getBooking_code() {
            return booking_code;
        }

        public void setBooking_code(BookingCodeBean booking_code) {
            this.booking_code = booking_code;
        }

        public Object getBooking_payment() {
            return booking_payment;
        }

        public void setBooking_payment(Object booking_payment) {
            this.booking_payment = booking_payment;
        }

        public List<BookingImageBean> getBooking_image() {
            return booking_image;
        }

        public void setBooking_image(List<BookingImageBean> booking_image) {
            this.booking_image = booking_image;
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
             * updated_at : 2020-03-31 00:21:38
             * category : {"id":1,"master_category_id":1,"category_index":"1","category_title":"AC","category_desc":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.","category_image":"http://104.248.149.236/anuin/gateway/public/media/default/category/default.png","category_banner":"","deleted":"0","actived":"1","created_by":"1","updated_by":"1","created_at":"2020-01-01 00:00:00","updated_at":"2020-03-10 05:01:33","unextimage":"default/category/default.png"}
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
            private CategoryBean category;

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

            public CategoryBean getCategory() {
                return category;
            }

            public void setCategory(CategoryBean category) {
                this.category = category;
            }

            public static class CategoryBean {
                /**
                 * id : 1
                 * master_category_id : 1
                 * category_index : 1
                 * category_title : AC
                 * category_desc : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur egestas tellus a hendrerit luctus.
                 * category_image : http://104.248.149.236/anuin/gateway/public/media/default/category/default.png
                 * category_banner :
                 * deleted : 0
                 * actived : 1
                 * created_by : 1
                 * updated_by : 1
                 * created_at : 2020-01-01 00:00:00
                 * updated_at : 2020-03-10 05:01:33
                 * unextimage : default/category/default.png
                 */

                private int id;
                private int master_category_id;
                private String category_index;
                private String category_title;
                private String category_desc;
                private String category_image;
                private String category_banner;
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

                public String getCategory_banner() {
                    return category_banner;
                }

                public void setCategory_banner(String category_banner) {
                    this.category_banner = category_banner;
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

        public static class BookingCodeBean {
            /**
             * id : 138
             * booking_id : 138
             * member_id : 46
             * code_name : 9284620200420
             * booking_status : 0
             * deleted : 0
             * created_at : 2020-04-20 13:57:04
             * updated_at : 2020-04-20 13:57:04
             */

            private int id;
            private int booking_id;
            private int member_id;
            private String code_name;
            private String booking_status;
            private String deleted;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBooking_id() {
                return booking_id;
            }

            public void setBooking_id(int booking_id) {
                this.booking_id = booking_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getCode_name() {
                return code_name;
            }

            public void setCode_name(String code_name) {
                this.code_name = code_name;
            }

            public String getBooking_status() {
                return booking_status;
            }

            public void setBooking_status(String booking_status) {
                this.booking_status = booking_status;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
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

        public static class BookingImageBean {
            /**
             * id : 113
             * booking_id : 138
             * member_id : 46
             * booking_status : 0
             * image_name : http://104.248.149.236/anuin/gateway/public/media/images/member-booking/281650379.jpg
             * deleted : 0
             * created_at : 2020-04-20 13:57:04
             * updated_at : 2020-04-20 13:57:04
             * unextimage : images/member-booking/281650379.jpg
             */

            private int id;
            private int booking_id;
            private int member_id;
            private String booking_status;
            private String image_name;
            private String deleted;
            private String created_at;
            private String updated_at;
            private String unextimage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBooking_id() {
                return booking_id;
            }

            public void setBooking_id(int booking_id) {
                this.booking_id = booking_id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getBooking_status() {
                return booking_status;
            }

            public void setBooking_status(String booking_status) {
                this.booking_status = booking_status;
            }

            public String getImage_name() {
                return image_name;
            }

            public void setImage_name(String image_name) {
                this.image_name = image_name;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
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
