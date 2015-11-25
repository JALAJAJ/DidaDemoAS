/**
 *
 */
package com.dida.first.bean;

import java.util.List;

/**
 * @author KingJA
 * @data 2015-9-9 上午10:31:53
 * @use
 */
public class MarketBean {

    private int code;
    private ResEntity res;

    public void setCode(int code) {
        this.code = code;
    }

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public ResEntity getRes() {
        return res;
    }

    public static class ResEntity {
        private int pages;
        private int rowCount;
        private ProductsEntity products;
        private List<ProductAdsEntity> productAds;

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public void setProducts(ProductsEntity products) {
            this.products = products;
        }

        public void setProductAds(List<ProductAdsEntity> productAds) {
            this.productAds = productAds;
        }

        public int getPages() {
            return pages;
        }

        public int getRowCount() {
            return rowCount;
        }

        public ProductsEntity getProducts() {
            return products;
        }

        public List<ProductAdsEntity> getProductAds() {
            return productAds;
        }

        public static class ProductsEntity {
            private int type;

            private List<StEntity> st;

            public void setType(int type) {
                this.type = type;
            }

            public void setSt(List<StEntity> st) {
                this.st = st;
            }

            public int getType() {
                return type;
            }

            public List<StEntity> getSt() {
                return st;
            }

            public static class StEntity {
                private String productNo;
                private String name;
                private String shopId;
                private double price;
                private String userId;
                private String des;
                private String thumb;
                private int stock;
                private boolean groupStatus;
                private String createTime;
                private String createTime2;
                private int startCount;
                private int endCount;

                public void setProductNo(String productNo) {
                    this.productNo = productNo;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setShopId(String shopId) {
                    this.shopId = shopId;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public void setDes(String des) {
                    this.des = des;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public void setGroupStatus(boolean groupStatus) {
                    this.groupStatus = groupStatus;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public void setCreateTime2(String createTime2) {
                    this.createTime2 = createTime2;
                }

                public void setStartCount(int startCount) {
                    this.startCount = startCount;
                }

                public void setEndCount(int endCount) {
                    this.endCount = endCount;
                }

                public String getProductNo() {
                    return productNo;
                }

                public String getName() {
                    return name;
                }

                public String getShopId() {
                    return shopId;
                }

                public double getPrice() {
                    return price;
                }

                public String getUserId() {
                    return userId;
                }

                public String getDes() {
                    return des;
                }

                public String getThumb() {
                    return thumb;
                }

                public int getStock() {
                    return stock;
                }

                public boolean isGroupStatus() {
                    return groupStatus;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public String getCreateTime2() {
                    return createTime2;
                }

                public int getStartCount() {
                    return startCount;
                }

                public int getEndCount() {
                    return endCount;
                }
            }
        }

        public static class ProductAdsEntity {
            private String id;
            private String adName;
            private String adUrl;
            private String adLink;
            private int adType;
            private int position;
            private String createTime;
            private String updateTime;
            private String createAdmin;
            private String updateAdmin;
            private String adTypeText;

            public void setId(String id) {
                this.id = id;
            }

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public void setAdUrl(String adUrl) {
                this.adUrl = adUrl;
            }

            public void setAdLink(String adLink) {
                this.adLink = adLink;
            }

            public void setAdType(int adType) {
                this.adType = adType;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public void setCreateAdmin(String createAdmin) {
                this.createAdmin = createAdmin;
            }

            public void setUpdateAdmin(String updateAdmin) {
                this.updateAdmin = updateAdmin;
            }

            public void setAdTypeText(String adTypeText) {
                this.adTypeText = adTypeText;
            }

            public String getId() {
                return id;
            }

            public String getAdName() {
                return adName;
            }

            public String getAdUrl() {
                return adUrl;
            }

            public String getAdLink() {
                return adLink;
            }

            public int getAdType() {
                return adType;
            }

            public int getPosition() {
                return position;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public String getCreateAdmin() {
                return createAdmin;
            }

            public String getUpdateAdmin() {
                return updateAdmin;
            }

            public String getAdTypeText() {
                return adTypeText;
            }
        }
    }

}
