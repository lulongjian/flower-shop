<template>
  <header>
    <nav class="clear">
      <div class="left">
        <div class="imgbox left">
          <img src="@/assets/img/new-logo.svg" class="top_img" alt="">
        </div>
        <ul class="right">
          <router-link class="px16 pointer" to='/home' tag='li' replace>
            <span @click="flagT();homelist()">首页</span>
          </router-link>
          <router-link class="px16 pointer" to='/product' tag='li' replace>
            <span>全部商品</span>
          </router-link>
          <router-link class="px16 pointer" to='/recommend' tag='li' replace>
            <span>每日推荐</span>
          </router-link>
          <router-link class="px16 pointer" to='/newProduct' tag='li' replace>
            <span>新品上架</span>
          </router-link>
          <router-link class="px16 pointer" to='/hotProduct' tag='li' replace>
            <span>最近热卖</span>
          </router-link>
          <router-link class="px16 pointer" to='/flowerKnowledge' tag='li' replace>
            <span>养花小知识</span>
          </router-link>
          <router-link class="px16" style="cursor:help" to='/help' tag='li' replace>
            <span>帮助</span>
          </router-link>
        </ul>
      </div>
      <div class="right px14">
        <div class="px14" v-if="$store.state.img" style="padding-top: 20px">
          <el-dropdown>
            <span class="el-dropdown-link" style="padding-right: 25px;" @click="messageCenter">
              <el-badge :value="messageCount" :max="99" class="item" type="info">
                <i class="el-icon-message-solid"></i>
              </el-badge>
            </span>
            <el-dropdown-menu slot="dropdown">
            </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown>
            <span class="el-dropdown-link" style="padding-right: 25px;" @click="carCenter">
              <el-badge class="item" type="primary">
                <img src="@/assets/img/car_001.svg" style="width: 20px">
              </el-badge>
            </span>
            <el-dropdown-menu slot="dropdown">
            </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <el-badge value="" :max="99" class="item" type="primary">
                {{$store.state.name}}
                <i class="el-icon-arrow-down el-icon--right"></i>
              </el-badge>
            </span>
            <el-dropdown-menu slot="dropdown" class="px14">
              <el-dropdown-item command="">
                <router-link class="px14 pointer" to='/personCenter' tag='li' replace>
                  <span @click.native="pd">个人中心</span>
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item command="">
                <router-link class="px14 pointer" to='/order' tag='li' replace>
                  <span @click.native="pd">购物订单</span>
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item command="e">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <ul class="px14" v-else>
          <router-link class="px16 pointer" to="/login" tag='li' replace><span>登陆</span></router-link>
          <li>|</li>
          <router-link class="px16 pointer" to="/reg" tag='li' replace><span>注册</span></router-link>
        </ul>
      </div>
    </nav>

  </header>

</template>

<script>
import "../../assets/css/heder.css"
import '@/assets/css/car.css'
import "@/assets/css/home.css"
import '@/assets/css/goods.css'
import '@/assets/css/details.css'
import '@/assets/css/release.css'
import { getUserNoticeListRequest } from "@/api/index"

export default {
  name: 'TopBar',
  data () {
    return {
      imageUrl: '',
      dbimgUrl: '',
      Acolor: '',
      path: '/home',
      username: '',
      loginUser: [],
      account: [],
      accountRecord: [],
      addMoney: '',
      productCenterDialogVisible: false,
      myCenterDialogVisible: false,
      accountCenterDialogVisible: false,
      updateProductCenterDialogVisible: false,
      addMyBalance: false,
      addrCenterDialogVisible: false,
      updateAddressDialogVisible: false,
      collect: [],
      address: [],
      addr: [],
      addOrUpdate: '',
      msg: [],
      pname: [],
      productList: [],
      messageCount: 0,
    }
  },
  methods: {
    // 获取信息
    getAddrListData () {
      if (sessionStorage.getItem("userId")) {
        let params = {
          isRead: 0,
          userId: sessionStorage.getItem("userId")
        }
        params.page = 1
        params.number = 10
        getUserNoticeListRequest(params).then(response => {
          this.messageCount = response.count
          if (this.messageCount > 0) {
            // 消息通知
            this.$notify.info({
              title: '通知',
              message: '您有未读消息',
              position: 'bottom-right',
              onClick: this.messageCenter
            });
          }
        })
      }

    },
    flagT () {
      if (this.path != this.$route.path) {
        this.$router.replace(this.path)
      }
      this.$store.commit('decrementT')
    },
    homelist () {
      this.$store
        .dispatch('aHome')
        .then(res => {
        })
    },
    pd () {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录账号后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 0)
      } else {
        this.$router.push('/release')
      }
    },
    car () {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录账号后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 0)
      } else {
        this.$router.push('/car')
      }
    },
    product () {
      this.$router.push('/product')
    },
    recommend () {
      this.$router.push('/recommend')
    },
    newProduct () {
      this.$router.push('/newProduct')
    },
    hotProduct () {
      this.$router.push('/hotProduct')
    },
    flowerKnowledge () {
      this.$router.push('/flowerKnowledge')
    },
    order () {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录账号后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 0)
      } else {
        this.$router.push('/order')
      }
    },
    handleCommand (command) {
      if (command == 'a') {
        //个人中心
        this.myCenter()
      }
      if (command == 'e') {
        this.out()
      }
    },
    /**
     * 返回
     */
    centerBack () {
      this.myCenterDialogVisible = false
      this.accountCenterDialogVisible = false
    },
    /**
     * 注销登陆
     */
    out () {
      this.$confirm('确定退出账号吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        sessionStorage.clear()
        this.$message({
          type: 'success',
          message: '退出登录成功!'
        });
        this.$router.replace('/home')
        setTimeout(function () {
          location.reload();
        }, 1000)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '退出失败'
        });
      });
    },

    // 去消息中心
    messageCenter () {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录账号后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 0)
      } else {
        this.$router.push('/messageCenter')
      }
    },

    // 去购物车
    carCenter () {
      if (sessionStorage.getItem("user") == null) {
        this.$message.error("请登录账号后再进行操作")
        setTimeout(() => {
          this.$router.push('/login')
        }, 0)
      } else {
        this.$router.push('/car')
      }
    }
  },
  created () {
    this.homelist();
    this.getAddrListData() // 获取消息通知
    if (sessionStorage.getItem("user") != null) {
      this.$store.state.img = true
      this.$store.state.name = sessionStorage.getItem("user")
    }
  },
  computed () {
    this.homelist();
    this.getAddrListData() // 获取消息通知
  }
}
</script>

<style scoped>
.colorblue {
  color: #4da5ff;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}

.el-icon-arrow-down {
  font-size: 12px;
}

#paging {
  width: 1200px;
  margin: 0 auto;
}

#paging > div {
  text-align: center;
}

/**隐藏下拉条 */
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>
