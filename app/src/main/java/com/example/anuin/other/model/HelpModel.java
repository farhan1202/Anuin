package com.example.anuin.other.model;

import java.util.List;

public class HelpModel {

    /**
     * STATUS : 200
     * MESSAGE : Success Get Helps List
     * DATA : [{"id":1,"helps_group_id":1,"title":"Helps Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-26 08:42:53","updated_at":"2020-03-26 08:42:53","helps_group":{"id":1,"title":"Helps Group Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-27 08:17:12","updated_at":"2020-03-27 08:17:12"}},{"id":2,"helps_group_id":2,"title":"Helps Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-26 08:42:53","updated_at":"2020-03-26 08:42:53","helps_group":{"id":2,"title":"Helps Group Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-27 08:17:12","updated_at":"2020-03-27 08:17:12"}},{"id":3,"helps_group_id":3,"title":"Helps Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-26 08:42:53","updated_at":"2020-03-26 08:42:53","helps_group":{"id":3,"title":"Helps Group Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-27 08:17:12","updated_at":"2020-03-27 08:17:12"}}]
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
         * helps_group_id : 1
         * title : Helps Title
         * content : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.
         * deleted : 0
         * created_by : 1
         * updated_by : 1
         * created_at : 2020-03-26 08:42:53
         * updated_at : 2020-03-26 08:42:53
         * helps_group : {"id":1,"title":"Helps Group Title","content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.","deleted":"0","created_by":1,"updated_by":1,"created_at":"2020-03-27 08:17:12","updated_at":"2020-03-27 08:17:12"}
         */

        private int id;
        private int helps_group_id;
        private String title;
        private String content;
        private String deleted;
        private int created_by;
        private int updated_by;
        private String created_at;
        private String updated_at;
        private HelpsGroupBean helps_group;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHelps_group_id() {
            return helps_group_id;
        }

        public void setHelps_group_id(int helps_group_id) {
            this.helps_group_id = helps_group_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public HelpsGroupBean getHelps_group() {
            return helps_group;
        }

        public void setHelps_group(HelpsGroupBean helps_group) {
            this.helps_group = helps_group;
        }

        public static class HelpsGroupBean {
            /**
             * id : 1
             * title : Helps Group Title
             * content : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget elit dapibus turpis porttitor sagittis vitae ac augue. Cras in porttitor purus. Donec fermentum malesuada urna, in rutrum mi feugiat in. Donec vel eros est. Morbi id convallis nulla, ut mattis nisi. Quisque mattis justo quam, aliquet convallis ante consequat non. Donec sodales, augue at elementum condimentum, nisl tortor posuere leo, vitae semper mauris mauris sed purus. Donec tincidunt blandit mi, a viverra tellus vestibulum vel. Maecenas a volutpat risus, nec dignissim nulla. Pellentesque vehicula nisl ac sollicitudin rhoncus. Quisque fringilla nunc nulla, sed dapibus turpis varius id.
             * deleted : 0
             * created_by : 1
             * updated_by : 1
             * created_at : 2020-03-27 08:17:12
             * updated_at : 2020-03-27 08:17:12
             */

            private int id;
            private String title;
            private String content;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
