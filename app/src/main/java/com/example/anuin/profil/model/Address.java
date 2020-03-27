package com.example.anuin.profil.model;

import java.util.List;

public class Address {

    /**
     * STATUS : 200
     * MESSAGE : Member Address Found
     * DATA : [{"id":8,"member_id":46,"alamat":"Padang","lokasi_maps":"","city":"3","kecamatan":"3","kelurahan":"2","kode_post":26161,"property":"rumah","deleted":"0","actived":"1","created_at":"2020-03-26 08:03:32","updated_at":"2020-03-26 08:03:32","provinsi":""},{"id":9,"member_id":46,"alamat":"Padang","lokasi_maps":"","city":"3","kecamatan":"3","kelurahan":"2","kode_post":26161,"property":"rumah","deleted":"0","actived":"1","created_at":"2020-03-26 08:03:34","updated_at":"2020-03-26 08:03:34","provinsi":""},{"id":10,"member_id":46,"alamat":"rumah","lokasi_maps":"jsjzj","city":"7","kecamatan":"128","kelurahan":"1305","kode_post":14001,"property":"kosong","deleted":"0","actived":"1","created_at":"2020-03-26 08:31:45","updated_at":"2020-03-26 08:31:45","provinsi":"2"},{"id":11,"member_id":46,"alamat":"apartenen","lokasi_maps":"grinting 3","city":"9","kecamatan":"143","kelurahan":"1396","kode_post":10440,"property":"jsj","deleted":"0","actived":"1","created_at":"2020-03-26 08:35:52","updated_at":"2020-03-26 08:35:52","provinsi":"2"}]
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
         * id : 8
         * member_id : 46
         * alamat : Padang
         * lokasi_maps :
         * city : 3
         * kecamatan : 3
         * kelurahan : 2
         * kode_post : 26161
         * property : rumah
         * deleted : 0
         * actived : 1
         * created_at : 2020-03-26 08:03:32
         * updated_at : 2020-03-26 08:03:32
         * provinsi :
         */

        private int id;
        private int member_id;
        private String alamat;
        private String lokasi_maps;
        private String city;
        private String kecamatan;
        private String kelurahan;
        private int kode_post;
        private String property;
        private String deleted;
        private String actived;
        private String created_at;
        private String updated_at;
        private String provinsi;

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

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getLokasi_maps() {
            return lokasi_maps;
        }

        public void setLokasi_maps(String lokasi_maps) {
            this.lokasi_maps = lokasi_maps;
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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
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

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }
    }
}
