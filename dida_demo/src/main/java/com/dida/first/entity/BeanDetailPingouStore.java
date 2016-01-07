package com.dida.first.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KingJA on 2015-12-28.
 */
public class BeanDetailPingouStore {

    /**
     * code : 1
     * msg : 详情加载成功
     * res : {"productImgs":["/upload/image/20151128/mobile/20151128103132_965.jpg","/upload/image/20151128/mobile/20151128103139_50.jpg","/upload/image/20151128/mobile/20151128103148_384.jpg"],"comGroupDetail":{"serviceId":2013,"userId":"1","isCollection":0,"groupMode":2,"groupName":"圣保罗的医生","price":11,"oldPrice":0.01,"count":6,"buyCount":0,"taskCount":5,"customDueDate":"2015-12-31 11:02:00","participates":[],"replys":[{"userId":"58","floorNo":5,"replyId":365,"hasNode":1,"content":"二货没交钱","createTime":"2015-12-04 10:01:59","userName":"hhh","thumb":"/upload/1.jpg","replyThumbList":["/upload/image/20151204/20151204100105_326.jpg"],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"hhh","parentId":365,"replyId":417,"hasNode":1,"content":"华盛顿回复","createTime":"2016-01-04 15:39:46","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]}]},"customAttrs":[{"attributeName":"品牌","attributeValue":"迪莎衣品"},{"attributeName":"服装版型","attributeValue":"直筒"},{"attributeName":"厚薄","attributeValue":"加厚"},{"attributeName":"风格","attributeValue":"通勤"},{"attributeName":"通勤","attributeValue":"韩版"},{"attributeName":"款式","attributeValue":"套头"},{"attributeName":"组合形式","attributeValue":"单件"},{"attributeName":"衣长","attributeValue":"常规款"},{"attributeName":"袖长","attributeValue":"长袖"},{"attributeName":"领子","attributeValue":"高领"},{"attributeName":"袖型","attributeValue":"常规"},{"attributeName":"衣门襟","attributeValue":"套头"},{"attributeName":"图案","attributeValue":"纯色"},{"attributeName":"流行元素/工艺","attributeValue":"纱网"},{"attributeName":"适用年龄","attributeValue":"25-29周岁"},{"attributeName":"上市年份/季节","attributeValue":"2015年冬季"},{"attributeName":"颜色分类","attributeValue":"黑色 棕色"},{"attributeName":"尺码","attributeValue":"XL L M S"}],"purchaseAttrs":[{"attrId":"7c4ca88612a3475f85d1ef18ff75d88e","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"颜色","attrValues":[{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]},{"attrId":"863e07aa273d4ee9a6b5f86fa411f495","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"尺寸","attrValues":[{"valId":"f5f98f01818c4fc2a2caf410c291ed7c","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"S"},{"valId":"5dd31b2419f54f8fb6b83f46590730e9","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"M"},{"valId":"f4b5d6ee34234f10820f2873f6303ebc","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"L"},{"valId":"9d68d5517b664b03a672a31a844962ab","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"XL"}]}],"sellerThumb":"/upload/145145917901652e9b4bf7b83.jpg"}
     */

    private int code;
    private String msg;
    /**
     * productImgs : ["/upload/image/20151128/mobile/20151128103132_965.jpg","/upload/image/20151128/mobile/20151128103139_50.jpg","/upload/image/20151128/mobile/20151128103148_384.jpg"]
     * comGroupDetail : {"serviceId":2013,"userId":"1","isCollection":0,"groupMode":2,"groupName":"圣保罗的医生","price":11,"oldPrice":0.01,"count":6,"buyCount":0,"taskCount":5,"customDueDate":"2015-12-31 11:02:00","participates":[],"replys":[{"userId":"58","floorNo":5,"replyId":365,"hasNode":1,"content":"二货没交钱","createTime":"2015-12-04 10:01:59","userName":"hhh","thumb":"/upload/1.jpg","replyThumbList":["/upload/image/20151204/20151204100105_326.jpg"],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"hhh","parentId":365,"replyId":417,"hasNode":1,"content":"华盛顿回复","createTime":"2016-01-04 15:39:46","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]}]}
     * customAttrs : [{"attributeName":"品牌","attributeValue":"迪莎衣品"},{"attributeName":"服装版型","attributeValue":"直筒"},{"attributeName":"厚薄","attributeValue":"加厚"},{"attributeName":"风格","attributeValue":"通勤"},{"attributeName":"通勤","attributeValue":"韩版"},{"attributeName":"款式","attributeValue":"套头"},{"attributeName":"组合形式","attributeValue":"单件"},{"attributeName":"衣长","attributeValue":"常规款"},{"attributeName":"袖长","attributeValue":"长袖"},{"attributeName":"领子","attributeValue":"高领"},{"attributeName":"袖型","attributeValue":"常规"},{"attributeName":"衣门襟","attributeValue":"套头"},{"attributeName":"图案","attributeValue":"纯色"},{"attributeName":"流行元素/工艺","attributeValue":"纱网"},{"attributeName":"适用年龄","attributeValue":"25-29周岁"},{"attributeName":"上市年份/季节","attributeValue":"2015年冬季"},{"attributeName":"颜色分类","attributeValue":"黑色 棕色"},{"attributeName":"尺码","attributeValue":"XL L M S"}]
     * purchaseAttrs : [{"attrId":"7c4ca88612a3475f85d1ef18ff75d88e","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"颜色","attrValues":[{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]},{"attrId":"863e07aa273d4ee9a6b5f86fa411f495","productNo":"f89e751773af47fda7f3added531c7a4","attributeName":"尺寸","attrValues":[{"valId":"f5f98f01818c4fc2a2caf410c291ed7c","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"S"},{"valId":"5dd31b2419f54f8fb6b83f46590730e9","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"M"},{"valId":"f4b5d6ee34234f10820f2873f6303ebc","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"L"},{"valId":"9d68d5517b664b03a672a31a844962ab","attrId":"863e07aa273d4ee9a6b5f86fa411f495","attrValue":"XL"}]}]
     * sellerThumb : /upload/145145917901652e9b4bf7b83.jpg
     */

    private ResEntity res;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResEntity getRes() {
        return res;
    }

    public static class ResEntity {
        /**
         * serviceId : 2013
         * userId : 1
         * isCollection : 0
         * groupMode : 2
         * groupName : 圣保罗的医生
         * price : 11.0
         * oldPrice : 0.01
         * count : 6
         * buyCount : 0
         * taskCount : 5
         * customDueDate : 2015-12-31 11:02:00
         * participates : []
         * replys : [{"userId":"58","floorNo":5,"replyId":365,"hasNode":1,"content":"二货没交钱","createTime":"2015-12-04 10:01:59","userName":"hhh","thumb":"/upload/1.jpg","replyThumbList":["/upload/image/20151204/20151204100105_326.jpg"],"subReplys":[{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"hhh","parentId":365,"replyId":417,"hasNode":1,"content":"华盛顿回复","createTime":"2016-01-04 15:39:46","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]}]
         */

        private ComGroupDetailEntity comGroupDetail;
        private String sellerThumb;
        private List<String> productImgs;
        /**
         * attributeName : 品牌
         * attributeValue : 迪莎衣品
         */

        private List<CustomAttrsEntity> customAttrs;
        /**
         * attrId : 7c4ca88612a3475f85d1ef18ff75d88e
         * productNo : f89e751773af47fda7f3added531c7a4
         * attributeName : 颜色
         * attrValues : [{"valId":"d5ff0117d6e0404eb5686ee38ba20b70","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"黑色"},{"valId":"a38003c8f03c4581ab7fabb6ce83e220","attrId":"7c4ca88612a3475f85d1ef18ff75d88e","attrValue":"棕色"}]
         */

        private List<PurchaseAttrsEntity> purchaseAttrs;

        public void setComGroupDetail(ComGroupDetailEntity comGroupDetail) {
            this.comGroupDetail = comGroupDetail;
        }

        public void setSellerThumb(String sellerThumb) {
            this.sellerThumb = sellerThumb;
        }

        public void setProductImgs(List<String> productImgs) {
            this.productImgs = productImgs;
        }

        public void setCustomAttrs(List<CustomAttrsEntity> customAttrs) {
            this.customAttrs = customAttrs;
        }

        public void setPurchaseAttrs(List<PurchaseAttrsEntity> purchaseAttrs) {
            this.purchaseAttrs = purchaseAttrs;
        }

        public ComGroupDetailEntity getComGroupDetail() {
            return comGroupDetail;
        }

        public String getSellerThumb() {
            return sellerThumb;
        }

        public List<String> getProductImgs() {
            return productImgs;
        }

        public List<CustomAttrsEntity> getCustomAttrs() {
            return customAttrs;
        }

        public List<PurchaseAttrsEntity> getPurchaseAttrs() {
            return purchaseAttrs;
        }

        public static class ComGroupDetailEntity {
            private int serviceId;
            private String userId;
            private int isCollection;
            private int groupMode;
            private String groupName;
            private double price;
            private double oldPrice;
            private int count;
            private int buyCount;
            private int taskCount;
            private String customDueDate;
            private List<ParticipatesEntity> participates;
            /**
             * userId : 58
             * floorNo : 5
             * replyId : 365
             * hasNode : 1
             * content : 二货没交钱
             * createTime : 2015-12-04 10:01:59
             * userName : hhh
             * thumb : /upload/1.jpg
             * replyThumbList : ["/upload/image/20151204/20151204100105_326.jpg"]
             * subReplys : [{"userId":"fb9a38d82cd3405a9b60ec54cdb5ecdf","replyedUserName":"hhh","parentId":365,"replyId":417,"hasNode":1,"content":"华盛顿回复","createTime":"2016-01-04 15:39:46","userName":"wujie0209","thumb":"/frontend/images/recommend-head4.jpg"}]
             */

            private List<ReplysEntity> replys;
            public static class ParticipatesEntity implements Serializable {
                private String userId;
                private String userName;
                private String thumb;
                private int sex;

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getUserId() {
                    return userId;
                }

                public String getUserName() {
                    return userName;
                }

                public String getThumb() {
                    return thumb;
                }

                public int getSex() {
                    return sex;
                }
            }
            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public void setGroupMode(int groupMode) {
                this.groupMode = groupMode;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setOldPrice(double oldPrice) {
                this.oldPrice = oldPrice;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

            public void setTaskCount(int taskCount) {
                this.taskCount = taskCount;
            }

            public void setCustomDueDate(String customDueDate) {
                this.customDueDate = customDueDate;
            }

            public void setParticipates(List<ParticipatesEntity> participates) {
                this.participates = participates;
            }

            public void setReplys(List<ReplysEntity> replys) {
                this.replys = replys;
            }

            public int getServiceId() {
                return serviceId;
            }

            public String getUserId() {
                return userId;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public int getGroupMode() {
                return groupMode;
            }

            public String getGroupName() {
                return groupName;
            }

            public double getPrice() {
                return price;
            }

            public double getOldPrice() {
                return oldPrice;
            }

            public int getCount() {
                return count;
            }

            public int getBuyCount() {
                return buyCount;
            }

            public int getTaskCount() {
                return taskCount;
            }

            public String getCustomDueDate() {
                return customDueDate;
            }

            public List<ParticipatesEntity> getParticipates() {
                return participates;
            }

            public List<ReplysEntity> getReplys() {
                return replys;
            }

            public static class ReplysEntity {
                private String userId;
                private int floorNo;
                private int replyId;
                private int hasNode;
                private String content;
                private String createTime;
                private String userName;
                private String thumb;
                private List<String> replyThumbList;
                /**
                 * userId : fb9a38d82cd3405a9b60ec54cdb5ecdf
                 * replyedUserName : hhh
                 * parentId : 365
                 * replyId : 417
                 * hasNode : 1
                 * content : 华盛顿回复
                 * createTime : 2016-01-04 15:39:46
                 * userName : wujie0209
                 * thumb : /frontend/images/recommend-head4.jpg
                 */

                private List<SubReplysEntity> subReplys;

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setFloorNo(int floorNo) {
                    this.floorNo = floorNo;
                }

                public void setReplyId(int replyId) {
                    this.replyId = replyId;
                }

                public void setHasNode(int hasNode) {
                    this.hasNode = hasNode;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setReplyThumbList(List<String> replyThumbList) {
                    this.replyThumbList = replyThumbList;
                }

                public void setSubReplys(List<SubReplysEntity> subReplys) {
                    this.subReplys = subReplys;
                }

                public String getUserId() {
                    return userId;
                }

                public int getFloorNo() {
                    return floorNo;
                }

                public int getReplyId() {
                    return replyId;
                }

                public int getHasNode() {
                    return hasNode;
                }

                public String getContent() {
                    return content;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public String getUserName() {
                    return userName;
                }

                public String getThumb() {
                    return thumb;
                }

                public List<String> getReplyThumbList() {
                    return replyThumbList;
                }

                public List<SubReplysEntity> getSubReplys() {
                    return subReplys;
                }

                public static class SubReplysEntity {
                    private String userId;
                    private String replyedUserName;
                    private int parentId;
                    private int replyId;
                    private int hasNode;
                    private String content;
                    private String createTime;
                    private String userName;
                    private String thumb;

                    public void setUserId(String userId) {
                        this.userId = userId;
                    }

                    public void setReplyedUserName(String replyedUserName) {
                        this.replyedUserName = replyedUserName;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public void setReplyId(int replyId) {
                        this.replyId = replyId;
                    }

                    public void setHasNode(int hasNode) {
                        this.hasNode = hasNode;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public void setUserName(String userName) {
                        this.userName = userName;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getUserId() {
                        return userId;
                    }

                    public String getReplyedUserName() {
                        return replyedUserName;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public int getReplyId() {
                        return replyId;
                    }

                    public int getHasNode() {
                        return hasNode;
                    }

                    public String getContent() {
                        return content;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public String getUserName() {
                        return userName;
                    }

                    public String getThumb() {
                        return thumb;
                    }
                }
            }
        }

        public static class CustomAttrsEntity {
            private String attributeName;
            private String attributeValue;

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public void setAttributeValue(String attributeValue) {
                this.attributeValue = attributeValue;
            }

            public String getAttributeName() {
                return attributeName;
            }

            public String getAttributeValue() {
                return attributeValue;
            }
        }

        public static class PurchaseAttrsEntity {
            private String attrId;
            private String productNo;
            private String attributeName;
            /**
             * valId : d5ff0117d6e0404eb5686ee38ba20b70
             * attrId : 7c4ca88612a3475f85d1ef18ff75d88e
             * attrValue : 黑色
             */

            private List<AttrValuesEntity> attrValues;

            public void setAttrId(String attrId) {
                this.attrId = attrId;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public void setAttrValues(List<AttrValuesEntity> attrValues) {
                this.attrValues = attrValues;
            }

            public String getAttrId() {
                return attrId;
            }

            public String getProductNo() {
                return productNo;
            }

            public String getAttributeName() {
                return attributeName;
            }

            public List<AttrValuesEntity> getAttrValues() {
                return attrValues;
            }

            public static class AttrValuesEntity {
                private String valId;
                private String attrId;
                private String attrValue;
                private boolean isCheck;

                public void setValId(String valId) {
                    this.valId = valId;
                }

                public void setAttrId(String attrId) {
                    this.attrId = attrId;
                }

                public void setAttrValue(String attrValue) {
                    this.attrValue = attrValue;
                }

                public String getValId() {
                    return valId;
                }

                public String getAttrId() {
                    return attrId;
                }

                public String getAttrValue() {
                    return attrValue;
                }

                public boolean isCheck() {
                    return isCheck;
                }

                public void setCheck(boolean check) {
                    isCheck = check;
                }
            }
        }
    }
}
