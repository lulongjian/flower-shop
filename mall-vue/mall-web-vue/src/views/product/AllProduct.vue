<template>
  <div>
    <div id="AllProduct" v-if="flag == true">
      <div class="SearchBd" style="width: 100%;height:130px;">
        <div class="HomeBd clear">
          <div class="left">
            <div class="leftBd">
              <div class="q322"><img src="@/assets/img/new-logo2.svg" alt=""></div>
            </div>
          </div>
          <div class="right">
            <div class="rightBd">
              <div id="input">
                <div class="inputBd">
                  <input v-model="searchName" type="text">
                </div>
                <div id="search">
                  <button class="searchBtn" @click="search">搜索</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <h3>注意:近期多发欺诈行为，本平台会保护您的购物隐私，谢谢支持！！！</h3>

      <div id="Goods">
        <!-- 分类搜索鲜花 -->
        <div class="search">
          <span>鲜花类别:</span>
          <el-select v-model="sort" placeholder="请选择" @change="getProductList()" filterable>
            <el-option v-for="item in productOptions" :key="item.id" :label="item.name" :value="item.name">
            </el-option>
          </el-select>
          <span style="margin-left: 30px">排序:</span>
          <el-select v-model="order" placeholder="默认" @change="getProductList()">
            <el-option v-for="item in orderOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </div>

        <div class="GoodsBd">
          <div class="single" v-for="(item, index) in productList" :key="item.id" @click="flagF(item)">
            <div class="imgbox">
              <!-- 图片展示 -->
              <img :src="item.imgUrlBase64" alt="">
            </div>
            <ul>
              <li>
                <!-- 描述 -->
                <p class="describe">
                  <!-- {{item.des}} -->
                </p>
              </li>
              <li class="clear">
                <!-- 二手价/原价 -->
                <p class="pricebox">
                  <span class="priTitle">{{item.title}}</span>
                </p>
              </li>
              <li style="padding-left:10px;">
                <span class="price">￥{{item.price}}</span><span>/</span><span class="cost">￥{{item.oldPrice}}</span>
              </li>
              <li class="lisc">
                <span class="licount">
                  <span>销量: </span><span>{{item.sallyNum}}</span>
                  <span></span><span></span>
                  <span>加购: </span><span>{{item.collectNum}}</span>
                  <img src="@/assets/img/car_001.svg" title="加购" @click="collectSwitchClickT(index,item.id,item.sc)">
                </span>
              </li>
              <li class="clear">
                <p>
                  <!-- 时间 -->
                  <!-- <span class="item">发布: {{item.createTime}}</span> -->
                  <!-- 收藏次数  浏览次数 点击收藏 -->
                </p>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <!-- 分页 -->
      <div id="paging">
        <div class="block">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="currentPage" :page-sizes="[10, 20, 30, 40, 50]" :page-size="currentSize"
            layout="total, sizes, prev, pager, next, jumper" :total="currentTotal">
          </el-pagination>
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
import { productListRequest, getMessageListRequest, addMessageRequest, addCarRequest, getProductTypeAllListRequest } from "@/api/index"

export default {
  name: 'AllProduct',
  components: {},
  data () {
    return {
      msg: [],
      flag: true,
      product: {},
      productList: [],
      currentPage: 1,
      currentSize: 10,
      currentTotal: 0,
      searchName: '',
      sort: '全部',
      order: '',
      productOptions: [],
      orderOptions: [
        {
          value: '',
          label: '默认'
        },
        {
          value: 'moneyHight',
          label: '价格降序'
        },
        {
          value: 'moneyLow',
          label: '价格升序'
        },
        {
          value: 'collectNum',
          label: '收藏数'
        },
        {
          value: 'time',
          label: '发布时间'
        }
      ],
    }
  },
  methods: {
    getProductType () {
      let params = {}
      getProductTypeAllListRequest(params).then(response => {
        this.productOptions = [{
          id: '',
          name: '全部'
        }]
        response.data.forEach(element => {
          this.productOptions.push({
            id: element.id,
            name: element.name
          })
        })
      }).finally(() => {

      })
    },
    // 查询图片列表
    getProductList () {
      let params = {
        page: this.currentPage,
        number: this.currentSize,
        sort: this.sort === '全部' ? '' : this.sort,
        order: this.order,
        title: this.searchName,
      }
      productListRequest(params).then(response => {
        this.productList = response.data
        this.currentTotal = response.count
      }).finally(() => {

      })
    },

    // 搜索
    search () {
      this.currentPage = 1
      this.currentSize = 10
      this.getProductList()
    },
    // 改变当前页数
    handleCurrentChange (val) {
      this.currentPage = val
      this.getProductList()
    },

    // 改变当前显示数
    handleSizeChange (val) {
      this.currentSize = val
      this.getProductList()
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
      this.getProductType()
    }
  },
  computed () {
    this.flag = true
  },
  created () {
    this.getProductList()
    this.getProductType()
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
</style>
