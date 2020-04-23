package com.example.anuin.order.model;

import java.util.List;

public class Payment {

    /**
     * STATUS : 200
     * MESSAGE : Success Payment Method Detail
     * DATA : {"id":2,"title":"Bank","actived":"1","deleted":"0","payment_type_index":2,"created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:25","updated_at":"2020-04-20 08:24:25","payment_method":[{"id":3,"title":"BCA Klik","payment_type_id":2,"variable_method":"bca_klikpay","payment_desc":"BCA Klik","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":4,"title":"Mandiri Click Pay","payment_type_id":2,"variable_method":"mandiri_clickpay","payment_desc":"Mandiri Click Pay","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":5,"title":"CIMB Clicks","payment_type_id":2,"variable_method":"cimb_clicks","payment_desc":"CIMB Click","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":6,"title":"Internet Banking","payment_type_id":2,"variable_method":"echannel","payment_desc":"Internet Banking","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":7,"title":"Other Virtual Account","payment_type_id":2,"variable_method":"other_va","payment_desc":"Other Virtual Account","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"}]}
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
         * id : 2
         * title : Bank
         * actived : 1
         * deleted : 0
         * payment_type_index : 2
         * created_by : 1
         * updated_by : 1
         * created_at : 2020-04-20 08:24:25
         * updated_at : 2020-04-20 08:24:25
         * payment_method : [{"id":3,"title":"BCA Klik","payment_type_id":2,"variable_method":"bca_klikpay","payment_desc":"BCA Klik","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":4,"title":"Mandiri Click Pay","payment_type_id":2,"variable_method":"mandiri_clickpay","payment_desc":"Mandiri Click Pay","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":5,"title":"CIMB Clicks","payment_type_id":2,"variable_method":"cimb_clicks","payment_desc":"CIMB Click","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":6,"title":"Internet Banking","payment_type_id":2,"variable_method":"echannel","payment_desc":"Internet Banking","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"},{"id":7,"title":"Other Virtual Account","payment_type_id":2,"variable_method":"other_va","payment_desc":"Other Virtual Account","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-04-20 08:24:39","updated_at":"2020-04-20 08:24:39"}]
         */

        private int id;
        private String title;
        private String actived;
        private String deleted;
        private int payment_type_index;
        private int created_by;
        private int updated_by;
        private String created_at;
        private String updated_at;
        private List<PaymentMethodBean> payment_method;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActived() {
            return actived;
        }

        public void setActived(String actived) {
            this.actived = actived;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public int getPayment_type_index() {
            return payment_type_index;
        }

        public void setPayment_type_index(int payment_type_index) {
            this.payment_type_index = payment_type_index;
        }

        public int getCreated_by() {
            return created_by;
        }

        public void setCreated_by(int created_by) {
            this.created_by = created_by;
        }

        public int getUpdated_by() {
            return updated_by;
        }

        public void setUpdated_by(int updated_by) {
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

        public List<PaymentMethodBean> getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(List<PaymentMethodBean> payment_method) {
            this.payment_method = payment_method;
        }

        public static class PaymentMethodBean {
            /**
             * id : 3
             * title : BCA Klik
             * payment_type_id : 2
             * variable_method : bca_klikpay
             * payment_desc : BCA Klik
             * deleted : 0
             * created_by : 1
             * updated_by : 1
             * created_at : 2020-04-20 08:24:39
             * updated_at : 2020-04-20 08:24:39
             */

            private int id;
            private String title;
            private int payment_type_id;
            private String variable_method;
            private String payment_desc;
            private String deleted;
            private int created_by;
            private int updated_by;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPayment_type_id() {
                return payment_type_id;
            }

            public void setPayment_type_id(int payment_type_id) {
                this.payment_type_id = payment_type_id;
            }

            public String getVariable_method() {
                return variable_method;
            }

            public void setVariable_method(String variable_method) {
                this.variable_method = variable_method;
            }

            public String getPayment_desc() {
                return payment_desc;
            }

            public void setPayment_desc(String payment_desc) {
                this.payment_desc = payment_desc;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }

            public int getCreated_by() {
                return created_by;
            }

            public void setCreated_by(int created_by) {
                this.created_by = created_by;
            }

            public int getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(int updated_by) {
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
