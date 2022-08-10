<template>
  <div>
    <div id="AllProduct" v-if="flag == true">
      <div id="Release">
        <div id="homeImg">
          <img src="@/assets/img/home_1.svg" alt="" />
          <!-- <h3 class="h3Tile">我喜欢一些花儿，静静地开放，从不声张，有着清新得芬芳...</h3>
          <h3 class="h3Tile">I love flowers, quietly open, never quiet. Little flowers, with the...</h3> -->
          <h3 class="h3Tile">{{flowerLanguage.title1}}</h3>
          <h3 class="h3Tile">{{flowerLanguage.title2}}</h3>
          <el-divider v-if="productList.length > 0"></el-divider>
          <el-card class="box-card" style="width: 100%; background-color: rgba(250, 246, 246, 0.884) !important;"
            v-if="productList.length > 0">
            <div slot="header" class="clearfix">
              <span>每日推荐</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="openMore(0)">更多</el-button> -->
            </div>
            <div class="GoodsBd" style="margin-top: 0px">
              <div class="single" style="width: 265px" v-for="(item, index) in productList" :key="item.id"
                @click="flagF(item)">
                <div class="imgbox" style="width: 265px">
                  <!-- 图片展示 -->
                  <img :src="item.imgUrlBase64" alt="">
                </div>
                <ul>
                  <li class="clear">
                    <!-- 二手价/原价 -->
                    <p class="pricebox">
                      <span class="priTitle">{{item.title}}</span>
                    </p>
                  </li>
                  <li style="padding-left:10px;">
                    <span class="price">￥{{item.price}}</span><span></span><span class="cost">￥{{item.oldPrice}}</span>
                  </li>
                </ul>
              </div>
            </div>
          </el-card>
          <el-divider v-if="productListHot.length > 0"></el-divider>
          <el-card v-if="productListHot.length > 0" class="box-card"
            style="width: 100%; background-color: rgba(250, 246, 246, 0.884) !important;">
            <div slot="header" class="clearfix">
              <span>热门销量</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="openMore(1)">更多</el-button> -->
            </div>
            <div class="GoodsBd" style="margin-top: 0px">
              <div class="single" style="width: 265px" v-for="(item, index) in productListHot" :key="item.id"
                @click="flagF(item)">
                <div class="imgbox" style="width: 265px">
                  <!-- 图片展示 -->
                  <img :src="item.imgUrlBase64" alt="">
                </div>
                <ul>
                  <li class="clear">
                    <!-- 二手价/原价 -->
                    <p class="pricebox">
                      <span class="priTitle">{{item.title}}</span>
                    </p>
                  </li>
                  <li style="padding-left:10px;">
                    <span class="price">￥{{item.price}}</span><span></span><span class="cost">￥{{item.oldPrice}}</span>
                  </li>
                </ul>
              </div>
            </div>
          </el-card>
          <el-divider v-if="productListNew.length > 0"></el-divider>
          <el-card v-if="productListNew.length > 0" class="box-card"
            style="width: 100%; background-color: rgba(250, 246, 246, 0.884) !important;">
            <div slot="header" class="clearfix">
              <span>新品上架</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="openMore(3)">更多</el-button> -->
            </div>
            <div class="GoodsBd" style="margin-top: 0px">
              <div class="single" style="width: 265px" v-for="(item, index) in productListNew" :key="item.id"
                @click="flagF(item)">
                <div class="imgbox" style="width: 265px">
                  <!-- 图片展示 -->
                  <img :src="item.imgUrlBase64" alt="">
                </div>
                <ul>
                  <li class="clear">
                    <!-- 二手价/原价 -->
                    <p class="pricebox">
                      <span class="priTitle">{{item.title}}</span>
                    </p>
                  </li>
                  <li style="padding-left:10px;">
                    <span class="price">￥{{item.price}}</span><span></span><span class="cost">￥{{item.oldPrice}}</span>
                  </li>
                </ul>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
    <div id="Details" v-else>
      <p @click="back" style="cursor: pointer;">
        返回
      </p>
      <div class="clearfix">
        <div class="left">
          <ul>
            <li>
              <el-card shadow="always">
                <!-- 图片展示 -->
                <img :src="product.imgUrlBase64" alt="">
              </el-card>
            </li>
          </ul>
        </div>
        <div class="right">
          <ul>
            <li>
              <span>名称: </span><span class="name">{{product.title}}</span>
            </li>
            <li>
              <span>原价: </span><span>￥</span><span>{{product.oldPrice}}</span>
            </li>
            <li>
              <span>现价: </span><span><span style="color: red;font-size: 20px">￥{{product.price}}</span></span>
            </li>
            <li>
              <span>库存: </span><span class="name">{{product.number}}</span>
            </li>
            <li>
              <span>销量: </span><span class="name">{{product.sallyNum}}</span>
            </li>
            <li class="des">
              <span>描述: </span><span>{{product.des}}</span>
            </li>
            <li>
              <el-button type="primary" @click="addCollectNum(product.id)">
                加入购物车
              </el-button>
              <!-- <el-button type="success" @click="buy(product.id)">
                购买
              </el-button> -->
            </li>
          </ul>
        </div>
        <div style="float: left">
          <el-divider content-position="left"><span style="font-size: 15px">留言信息</span></el-divider>
          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          <el-link icon="el-icon-edit" style="float: right" @click="ly(product.id)">发布留言</el-link>
          <el-card class="box-card">
            <div class="block">
              <el-timeline>
                <el-timeline-item v-for="(item,index) in  msg" :key="item.id" :timestamp="item.time" placement="top">
                  <el-card>
                    <h4>{{item.text}}</h4>
                    <br>
                    <p> <small>用户：</small>{{item.name}} <small>提交于</small> {{item.createTime}}</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/home.css"
import '@/assets/css/goods.css'
import '@/assets/css/details.css'
import {
  productListRequest, getRecommendListRequest,
  getMessageListRequest, addMessageRequest, addCarRequest,
  getFlowerLanguageRequest
} from "@/api/index"

export default {
  name: 'HomeVue',
  components: {},
  data () {
    return {
      productList: [],
      productListHot: [],
      productListNew: [],
      flag: true,
      product: {},
      msg: [],
      flowerLanguage: {
        title1: '',
        title2: ''
      }
    }
  },
  methods: {

    getProductList () {
      let params = {
        page: 1,
        number: 4
      }
      getRecommendListRequest(params).then(response => {
        this.productList = response.data
      }).finally(() => {

      })
    },

    getFlowerLanguage () {
      let params = {}
      getFlowerLanguageRequest(params).then(response => {
        this.flowerLanguage = {
          title1: '',
          title2: ''
        }
        if (response.data !== null) {
          this.flowerLanguage = response.data
        }
      }).finally(() => {

      })
    },

    getProductHotList () {
      let params = {
        page: 1,
        number: 4,
        sort: '',
        order: 'collectNum',
        title: '',
      }
      productListRequest(params).then(response => {
        this.productListHot = response.data
      }).finally(() => {

      })
    },

    getProductNewList () {
      let params = {
        page: 1,
        number: 4,
        sort: '',
        order: 'time',
        title: '',
      }
      productListRequest(params).then(response => {
        this.productListNew = response.data
      }).finally(() => {

      })
    },
    openMore (type) {
      if (type === 0) {
        this.$router.push('/recommend')
      }
      if (type === 1) {
        this.$router.push('/hotProduct')
      }
      if (type === 2) {
        this.$router.push('/newProduct')
      }
    },
    // 打开详情
    flagF (row) {
      this.flag = false
      this.product = row
      // 获取留言信息
      this.getMessageList(row.id)
    },

    getMessageList (id) {
      // 获取留言信息
      let params = {
        pId: id
      }
      getMessageListRequest(params).then(response => {
        this.msg = response.data
      }).finally(() => {

      })
    },

    // 返回
    back () {
      this.flag = true
    },

    // 留言
    ly (id) {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 1000)
      } else {
        this.$prompt('请输入留言内容', '发布留言信息', {
          confirmButtonText: '提交',
          cancelButtonText: '取消',
          inputType: 'textarea'
        }).then(({ value }) => {
          if (value == null || value == '') {
            this.$message.error("不能为空，请输入内容")
          } else {
            let params = {
              pId: id,
              text: value,
              name: sessionStorage.getItem("user")
            }
            addMessageRequest(params).then(response => {
              this.$message({
                type: 'success',
                message: '增加留言成功'
              })
              //刷新留言栏
              this.getMessageList(id)
            }).finally(() => {

            })
          }

        }).catch(() => {

        });
      }
    },

    // 跳到购物车购物
    buy (id) {
      this.addCollectNum(id)
      this.$router.push('/car')
    },

    // 加入购物车
    addCollectNum (id) {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 1000)
      } else {
        let params = {
          productId: id,
          userId: sessionStorage.getItem("userId")
        }
        addCarRequest(params).then(response => {
          this.$message({
            type: 'success',
            message: '加入成功'
          })
        }).finally(() => {

        })
      }
    },
    gethero () {
      this.getProductList()
      this.getProductHotList()
      this.getProductNewList()
      this.getFlowerLanguage()
    }
  },
  created () {
    this.getProductList()
    this.getProductHotList()
    this.getProductNewList()
    this.getFlowerLanguage()
  },
  watch: {
    $route () {
      this.flag = true
      this.gethero();
    }
  },
}
</script>

<style>
#homeImg {
  background-color: rgba(250, 246, 246, 0.884) !important;
  /* height: 500px; */
}
#homeImg > img {
  width: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
#Release ul li > #reset,
#Release ul li > button {
  height: 5px;
}
.carousel {
  padding: 10px 50px;
}
.h3Tile {
  color: #b0b0b0 !important;
}
#paging {
  width: 1200px;
  margin: 0 auto;
}

#paging > div {
  text-align: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 1200px;
}

#aaa {
  background-size: cover !important;
}
</style>
