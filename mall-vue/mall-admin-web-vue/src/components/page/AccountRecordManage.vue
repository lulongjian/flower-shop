<template>
  <div>
    <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
      @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange" @on-load="getListData"
      v-model="order" :before-open="beforeOpen">
      <template slot="money" slot-scope="scope">
        <el-tag style="color: red" v-if="scope.row.category == 0">-{{scope.row.money}}</el-tag>
        <el-tag style="color: green" v-if="scope.row.category == 1">+{{scope.row.money}}</el-tag>
        <el-tag style="color: green" v-if="scope.row.category == 3">+{{scope.row.money}}</el-tag>
        <el-tag style="color: red" v-if="scope.row.category == 4">-{{scope.row.money}}</el-tag>
      </template>
      <template slot="userIdSearch" slot-scope="">
        <el-select v-model="search.userId" filterable placeholder="请选择">
          <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
      </template>

      <!-- <template slot-scope="{type,size,row}" slot="menu">
                <el-button icon="el-icon-s-order" :size="size" :type="type" :style="buttonColor()" @click="getOrderDetails(row)">
                    订单详情
                </el-button>
            </template> -->
    </avue-crud>

    <el-dialog title="订单详情" v-if="orderDialogVisible" :visible.sync="orderDialogVisible" width="900px">
      <el-card class="box-card">
        <el-descriptions class="margin-top" :column="3" size="" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-order"></i>
              订单号
            </template>
            {{orderDetails.orderVO.orderNo}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-money"></i>
              价格
            </template>
            {{orderDetails.orderVO.price}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-circle-check"></i>
              状态
            </template>
            <el-tag v-if="orderDetails.orderVO.status === 0" type="info">已取消</el-tag>
            <el-tag v-if="orderDetails.orderVO.status === 1" type="warning">待发货</el-tag>
            <el-tag v-if="orderDetails.orderVO.status === 2">已发货</el-tag>
            <el-tag v-if="orderDetails.orderVO.status === 3" type="success">已完成</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              会员
            </template>
            {{orderDetails.orderVO.userName}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-date"></i>
              创建时间
            </template>
            {{orderDetails.orderVO.createTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-date"></i>
              更新时间
            </template>
            {{orderDetails.orderVO.updateTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              收货人
            </template>
            {{orderDetails.orderVO.addrName}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              联系方式
            </template>
            {{orderDetails.orderVO.addrPhone}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              详细地址
            </template>
            {{orderDetails.orderVO.addrDes}}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>

      <avue-crud :option="userOrderDetailsOption" :data="orderDetails.userOrderDetailsVOList">
        <template slot="imgUrl" slot-scope="scope">
          <img :src="scope.row.imgUrlBase64" width="60" height="60" />
        </template>
      </avue-crud>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="orderDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import { selectUserListRequest } from "@/api/user"
import { getOrderDetailsRequest } from "@/api/order"
import { getAccountRecordListRequest, updateOrderStausRequest } from "@/api/AccountRecordManage"
export default {
  name: 'AccountRecordManageVue',
  data () {
    return {
      userList: [],
      productList: [],
      order: {},
      loading: true,
      page: {
        currentPage: 1,
        pageSize: 10
      },
      search: {},
      tableListData: [],
      option: {
        card: true,
        align: 'center',
        menuAlign: 'center',
        searchMenuSpan: 6,
        index: true,
        delBtn: false,
        addBtn: false,
        editBtn: false,
        menu: false,
        column: [
          {
            label: '账户', prop: 'userSno', editDisplay: false
          },
          {
            label: '会员', prop: 'userId', hide: true, search: true, searchSpan: 4, editDisplay: false
          },
          {
            label: '姓名', prop: 'userName', editDisplay: false
          },
          {
            label: '手机', prop: 'userPhone', editDisplay: false
          },
          {
            label: '交易金额', prop: 'money', formslot: true, editDisplay: false
          },
          {
            label: '交易类型', prop: 'category', search: true, searchSpan: 4, type: 'select',
            dicData: [
              { label: '支出', value: '0' }, { label: '退款', value: '1' },
              { label: '充值', value: '3' }, { label: '提现', value: '4' }
            ],
            editDisplay: false
          },
          {
            label: '交易时间', prop: 'createTime', span: 12, editDisplay: false
          }
        ],
        searchShowBtn: false,
      },
      orderDialogVisible: false,
      orderDetails: {},
      activeName: 'first',
      userOrderDetailsOption: {
        card: true,
        align: 'center',
        menuAlign: 'center',
        index: true,
        delBtn: false,
        addBtn: false,
        editBtn: false,
        menu: false,
        column: [
          {
            label: '图片', prop: 'imgUrl', formslot: true, span: 24
          },
          {
            label: '标题', prop: 'title'
          },
          {
            label: '购买数量', prop: 'num'
          },
          {
            label: '购买单价', prop: 'productPrice'
          },
          {
            label: '总价', prop: 'price'
          },
        ],
        searchShowBtn: false,
      },
    }
  },
  methods: {
    // 获取列表数据
    getListData () {
      let params = this.search
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getAccountRecordListRequest(params).then(response => {
        this.tableListData = response.data
        this.page.total = response.count
      }).finally(() => {
        this.loading = false
      })
    },
    selectUserList () {
      selectUserListRequest().then(response => {
        this.userList = response.data
      })
    },
    // 搜索
    searchChange (params, done) {
      this.search = params
      this.getListData()
      done()
    },
    // 刷新
    refreshChange () {
      this.getListData()
      this.$message({
        message: '重置成功',
        type: 'success',
      });
    },
    // 编辑
    rowUpdate (row, status) {
      this.$confirm('确认操作？')
        .then(_ => {
          let params = {
            id: row.id,
            status: status
          }
          updateOrderStausRequest(params).then(response => {
            this.$message.success(response.msg)
            this.getListData()
          })
        })

    },

    // 订单详情
    getOrderDetails (row) {
      let params = {}
      params.id = row.id
      getOrderDetailsRequest(params).then(response => {
        this.orderDetails = response.data
        this.orderDialogVisible = true
      }).finally(() => {
        this.loading = false
      })

    },
    // 弹窗之前
    beforeOpen (done, type) {

      done()
    },

    // 按钮颜色
    buttonColor (type) {
      if (type === 0) {
        return 'color: red';
      } else {
        return '';
      }
    },
  },
  computed: {

  },
  created () {
    this.getListData()
    this.selectUserList()
  }
}
</script>

<style>
.el-dialog__body {
    padding-top: 0px;
}
</style>

