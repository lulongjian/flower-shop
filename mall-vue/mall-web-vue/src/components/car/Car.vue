<template>
  <div id="Release">

    <avue-crud :option="carOption" :page.sync="page" :data="carListData" :table-loading="carListloading"
      @on-load="getListData" @selection-change="selectionChange">
      <!-- 自定义按钮 -->
      <template slot="menuLeft">
        <el-button type="primary" icon="el-icon-check" size="small" @click="addOrder()">支付</el-button>
      </template>
      <!-- 自定义列 -->
      <template slot="imgUrl" slot-scope="scope">
        <img :src="scope.row.imgUrlBase64" width="60" height="60" />
      </template>
      <template slot="permission" slot-scope="scope">
        <el-tag :type="scope.row.permission === '1' ?'success':(scope.row.permission === '0'?'danger':'')">
          {{scope.row.permission==='1'?'在售':'失效'}}
        </el-tag>
      </template>
      <!-- 自定义提示 -->
      <template slot="tip">
        <el-tag type="success" size="mini">合计：{{allMoney}} 元</el-tag>
        <a class="rightSpan" href="#" @click="getHome">继续购物</a>
      </template>
      <template slot="number" slot-scope="scope">
        <div class="input-num">
          <button class="btn_minute" @click="btnMinute(scope.row, scope.row.number)">-</button>
          <input type="text" value="0" size="1" v-model="scope.row.number" readonly>
          <button class="btn_minute" @click="btnAdd(scope.row, scope.row.number)">+</button>
        </div>
      </template>

      <!-- 自定按钮 -->
      <template slot-scope="{type,size,row}" slot="menu">
        <el-button icon="el-icon-delete" :size="size" :type="type" @click="deleteRow(row)">
          删除
        </el-button>
      </template>
    </avue-crud>

    <!-- 生成订单界面 -->
    <el-dialog title="订单详情" v-if="orderDialogVisible" :visible.sync="orderDialogVisible" width="65%"
      :modal-append-to-body="false">
      <el-steps :active="active" finish-status="success">
        <el-step title="商品"></el-step>
        <el-step title="地址"></el-step>
        <el-step title="支付"></el-step>
      </el-steps>
      <div v-if="active === 0">
        <avue-crud :option="orderOption" :data="selectList">
          <!-- 自定义列 -->
          <template slot="imgUrl" slot-scope="scope">
            <img :src="scope.row.imgUrlBase64" width="30" height="30" />
          </template>
        </avue-crud>
      </div>
      <div v-if="active === 1">
        <el-card class="box-card" style="width: 100%">
          <el-form ref="form" :model="form" label-width="120px" size="mini">
            <el-form-item label="选择地址">
              <el-select v-model="addr" filterable placeholder="请选择" style="width: 300px">
                <el-option v-for="item in addrListData" :key="item"
                  :label="'姓名：' + item.userName +' 手机：' + item.userPhone +' 地址：'+ item.addr" :value="item">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="addr.userName" style="width: 300px" readonly=""></el-input>
            </el-form-item>
            <el-form-item label="手机">
              <el-input v-model="addr.userPhone" style="width: 300px" readonly=""></el-input>
            </el-form-item>
            <el-form-item label="地址">
              <el-input type="textarea" v-model="addr.addr" style="width: 120px" readonly=""></el-input>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
      <div v-if="active === 2">
        <el-card class="box-card" style="width: 100%">
          <el-alert v-if="accountNum >= allMoney"
            :title="`当前账户余额为：` + accountNum + `元，本次需要支付的金额为：` + allMoney  + `元，请确认支付。`" type="success"
            :closable="false">
          </el-alert>
          <el-alert v-if="accountNum < allMoney"
            :title="`当前账户余额为：` + accountNum + `元，本次需要支付的金额为：` + allMoney  + `元，余额不足请先充值。`" type="error"
            :closable="false">
          </el-alert>
        </el-card>
      </div>
      <div v-if="active === 3">
        <el-card class="box-card" style="width: 100%">
          <el-alert title="正在生成订单，请等待.... " type="info" :closable="false">
          </el-alert>
        </el-card>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-tag type="success" size="mini">合计：{{allMoney}} 元</el-tag>
        <el-button type="" size="mini" @click="orderDialogVisible = false">取消</el-button>
        <el-button type="primary" size="mini" @click="addOrderBtn">{{ active === 2 ?'确认':'确认' }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import '@/assets/css/release.css'
import {
  getCarListRequest, updateCarRequest, deleteCarRequest, getAddressListRequest, getMyAccountListRequest,
  addOrderRequest, getSelectPriceRequest
} from "@/api/index"

export default {
  name: "carVue",
  data () {
    return {
      accountNum: {},
      addr: {},
      addrListData: [],
      active: 0,
      selectList: [],
      allMoney: 0,
      product: {
        imgUrl: null
      },
      imgUrl: null,
      imgUrlBase64: null,
      carListloading: false,
      carListData: [],
      page: {
        currentPage: 1,
        pageSize: 10,
        layout: "total,prev, next",
        background: false,
      },
      isRead: null,
      carOption: {
        card: true,
        align: 'left',
        menuAlign: 'center',
        index: true,
        addBtn: false,
        delBtn: false,
        editBtn: false,
        selection: true,
        selectable: (row, index) => {
          return row.permission === '1';
        },
        column: [
          {
            label: '图片', prop: 'imgUrl', formslot: true, span: 24,
            rules: [{ required: true, message: "请上传图片", trigger: "blur" }]
          },
          {
            label: '标题', prop: 'title',
          },
          {
            label: '状态', prop: 'permission', type: 'select'
          },
          {
            label: '现价', prop: 'price'
          },
          {
            label: '加购数量', prop: 'number', span: 24
          },
        ],
        searchShowBtn: false,
      },
      orderOption: {
        card: true,
        align: 'left',
        menuAlign: 'center',
        index: true,
        addBtn: false,
        delBtn: false,
        editBtn: false,
        menu: false,
        selectable: (row, index) => {
          return row.permission === '1';
        },
        column: [
          {
            label: '图片', prop: 'imgUrl', formslot: true, span: 24
          },
          {
            label: '标题', prop: 'title',
          },
          {
            label: '数量', prop: 'number',
          },
          {
            label: '价格', prop: 'price',
          },
        ],
        searchShowBtn: false,
      },
      orderDialogVisible: false,
    }
  },
  methods: {
    // 去首页
    getHome () {
      this.$router.push('/product')
    },
    // 获取信息
    getListData () {
      let params = {
        userId: sessionStorage.getItem("userId")
      }
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getCarListRequest(params).then(response => {
        this.carListData = response.data
        this.page.total = response.count
      })
    },

    // 购买数量按钮+
    btnAdd (row, count) {
      if (row.permission === '1') {
        let params = {
          id: row.id,
          number: count + 1,
          userId: sessionStorage.getItem("userId")
        }
        updateCarRequest(params).then(response => {
          this.carListData[row.$index].number = count + 1
          if (this.selectList.length > 0) {
            this.selectionChange(this.selectList)
          }
        })
      }
    },
    // 购买数量按钮-
    btnMinute (row, count) {
      if (row.permission === '1') {
        if (count <= 1) {
          this.$message({
            type: 'error',
            message: '该宝贝不能减少了哟~'
          })
        } else {
          let params = {
            id: row.id,
            number: count - 1,
            userId: sessionStorage.getItem("userId")
          }
          updateCarRequest(params).then(response => {
            this.carListData[row.$index].number = count - 1
            if (this.selectList.length > 0) {
              this.selectionChange(this.selectList)
            }
          })
        }
      }
    },
    // 删除
    deleteRow (row) {
      this.$confirm('确认删除操作？')
        .then(_ => {
          let params = {
            id: row.id,
            userId: sessionStorage.getItem("userId")
          }
          deleteCarRequest(params).then(response => {
            this.$message.success(response.msg)
            this.getListData()
          })
        })
    },

    // 计算价格
    selectionChange (list) {
      this.allMoney = 0
      this.selectList = list
      Promise.all([
        this.getPlice()
      ]).then(res => {

      })
    },
    getPlice () {
      let productList = []
      this.selectList.forEach(element => {
        productList.push({
          productId: element.productId,
          price: element.price,
          number: element.number,
        })
      })
      let params = {
        productFormList: productList,
        userId: sessionStorage.getItem("userId")
      }
      getSelectPriceRequest(params).then(response => {
        this.allMoney = response.data
      })
    },

    // 生成订单页面
    addOrder () {
      this.active = 0
      this.addr = {}
      if (this.selectList.length === 0) {
        this.$message.error("请选择需要购买的商品")
      } else {
        this.orderDialogVisible = true
      }
    },

    // 下订单
    addOrderBtn () {
      this.active += 1
      if (this.active === 0) {
        // 商品信息
      }
      if (this.active === 1) {
        // 地址
        this.getAddrList()
      }
      if (this.active === 2) {
        // 支付
        if (!this.addr.id) {
          this.active--
          this.$message.error("请选择地址")
        }
        this.getAccount()
      }
      if (this.active === 3) {
        // 订单成功或失败提示
        if (this.accountNum < this.allMoney) {
          this.$message.error("支付失败，余额不足")
          setTimeout(() => {
            this.orderDialogVisible = false
          }, 1500);
        } else {

          // 处理商品
          let productList = []
          this.selectList.forEach(element => {
            productList.push({
              productId: element.productId,
              price: element.price,
              number: element.number,
            })
          })
          // 生成订单
          let params = {
            userId: sessionStorage.getItem("userId"),
            productFormList: productList,
            addressForm: {
              userName: this.addr.userName,
              userPhone: this.addr.userPhone,
              addr: this.addr.addr,
            },
          }
          addOrderRequest(params).then(response => {
            this.$message.success("下单成功，正在关闭页面...并跳转订单界面")
            setTimeout(() => {
              this.orderDialogVisible = false
              this.$router.push('/order')
            }, 1500);
          })
        }
      }
    },

    // 查找账户信息
    getAccount () {
      let params2 = {
        userId: sessionStorage.getItem("userId")
      }
      getMyAccountListRequest(params2).then(response => {
        this.accountNum = response.data
      })
    },

    // 地址列表
    getAddrList () {
      let params = {
        userId: sessionStorage.getItem("userId")
      }
      getAddressListRequest(params).then(response => {
        this.addrListData = response.data
      })
    }
  },
  created () {
    this.getListData()
  },
  watch: {
    $route () {
      this.getListData()
    }
  },
  computed: {

  },
}
</script>

<style>
.btn_minute {
  width: 20px;
}
</style>
