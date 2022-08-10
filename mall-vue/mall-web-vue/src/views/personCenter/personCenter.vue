<template>
  <div id="Release">

    <!-- 切换tab -->
    <div class="title">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="个人信息" name="centerMsgTab"></el-tab-pane>
        <el-tab-pane label="地址信息" name="addrsTab"></el-tab-pane>
        <el-tab-pane label="账户信息" name="accountTab"></el-tab-pane>
      </el-tabs>
    </div>

    <!-- 个人信息 -->
    <div class="ReleaseBd" v-if="centerMsgTabVisbale">
      <avue-form ref="userForm" :option="userFormOption" v-model="user">
        <template slot="menuForm">
          <el-button icon="el-icon-check" type="primary" @click="handleSubmit">修改</el-button>
          <el-button icon="el-icon-delete" @click="handleEmpty">重置</el-button>
        </template>
      </avue-form>
    </div>

    <!-- 地址信息 -->
    <div class="ReleaseBd" style="width: 90%" v-if="addrsTabVisbale">
      <avue-crud :option="addrOption" :data="addrListData" @row-save="addrHandleRowSave" @row-update="addrRowUpdate"
        @row-del="addrRowDel" @on-load="getAddrListData">
      </avue-crud>
    </div>

    <!-- 账户 -->
    <div class="ReleaseBd" style="width: 90%" v-if="accountTabVisbale">
      <el-card class="box-card" style="width: 100%">
        账户余额：{{accountNum}} 元
      </el-card>
      <avue-crud :option="accountOption" :data="accountListData" @row-save="addAccountRecordRowSave"
        @on-load="getAccountRecordList" v-model="account">
        <template slot="money" slot-scope="scope">
          <el-tag style="color: red" v-if="scope.row.category == 0">-{{scope.row.money}}</el-tag>
          <el-tag style="color: green" v-if="scope.row.category == 1">+{{scope.row.money}}</el-tag>
          <el-tag style="color: green" v-if="scope.row.category == 3">+{{scope.row.money}}</el-tag>
          <el-tag style="color: red" v-if="scope.row.category == 4">-{{scope.row.money}}</el-tag>
        </template>
        <!-- 自定义表单 -->
        <template slot-scope="" slot="typeForm">
          <el-select v-model="account.type" placeholder="请选择">
            <el-option v-for="item in categoryDicData" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </template>
      </avue-crud>
    </div>
  </div>
</template>

<script>
import '@/assets/css/release.css'
import {
  getLoginUserRequest, updateUserRequest, getAddressListRequest, addOrUpdateAddressByIdRequest, deleteByAddressIdRequest,
  getAccountRecordListRequest, getMyAccountListRequest, addAccountByUserIdRequest
} from "@/api/index"

export default {
  name: "personCenter",
  data () {
    var validatePhone = (rule, value, callback) => {
      const reg = /(1[34578]\d{9})$/;;
      if (value == '' || value == undefined || value == null) {
        callback(new Error('不能为空'));
      } else {
        if ((!reg.test(value)) && value != '') {
          callback(new Error('请输入正确的手机号码'));
        } else {
          callback();
        }
      }
    }
    var validateEMail = (rule, value, callback) => {
      const reg = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
      if (value == '' || value == undefined || value == null) {
        callback(new Error('不能为空'));
      } else {
        if (!reg.test(value)) {
          callback(new Error('请输入正确的邮箱'));
        } else {
          callback();
        }
      }
    }
    var validateMoney = (rule, value, callback) => {
      const reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
      if (value == '' || value == undefined || value == null) {
        callback(new Error('不能为空'));
      } else {
        if (!reg.test(value)) {
          callback(new Error('请输入正确的金额'));
        } else {
          callback();
        }
      }
    }
    return {
      account: {},
      accountNum: 0,
      centerMsgTabVisbale: true,
      addrsTabVisbale: false,
      accountTabVisbale: false,
      activeName: 'centerMsgTab',
      user: {},
      userFormOption: {
        emptyBtn: false,
        submitBtn: false,
        column: [
          {
            label: '账户',
            prop: 'sno',
            type: 'input',
            span: 14,
            rules: [{
              required: true,
              message: "请输入账户",
              trigger: "blur"
            }]
          },
          {
            label: '姓名',
            prop: 'name',
            type: 'input',
            span: 14,
            rules: [{
              required: true,
              message: "请输入姓名",
              trigger: "blur"
            }]
          },
          {
            label: '邮箱',
            prop: 'email',
            type: 'input',
            span: 14,
            rules: [{
              required: true,
              validator: validateEMail,
              trigger: "blur"
            }]
          },
          {
            label: '手机',
            prop: 'phone',
            type: 'input',
            span: 14,
            rules: [{
              required: true,
              validator: validatePhone,
              trigger: "blur"
            }]
          }
        ]
      },
      addrListData: [],
      addrOption: {
        card: true,
        align: 'left',
        menuAlign: 'center',
        index: true,
        column: [
          {
            label: '收货人', prop: 'userName', span: 18,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          },
          {
            label: '手机号码', prop: 'userPhone', span: 18,
            rules: [{ required: true, validator: validatePhone, trigger: "blur" }]
          },
          {
            label: '详细地址', prop: 'addr', span: 18,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          }
        ],
        searchShowBtn: false,
      },
      accountListData: [],
      accountOption: {
        card: true,
        align: 'left',
        menuAlign: 'center',
        index: true,
        menu: false,
        addBtnText: '充值 / 提现',
        column: [
          {
            label: '金额', prop: 'money', formslot: true, editDisplay: false,
            rules: [{ required: true, validator: validateMoney, trigger: "blur" }]
          },
          {
            label: '类型', prop: 'category', type: 'select',
            dicData: [
              { label: '支出', value: '0' }, { label: '退款', value: '1' },
              { label: '充值', value: '3' }, { label: '提现', value: '4' }
            ],
            addDisplay: false, editDisplay: false
          },
          {
            label: '类型', prop: 'type', hide: true, searchSpan: 4, formslot: true,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          },
          {
            label: '时间', prop: 'createTime', span: 12, addDisplay: false, editDisplay: false
          }
        ],
        searchShowBtn: false,
      },
      categoryDicData: [
        { label: '充值', value: '3' },
        { label: '提现', value: '4' }
      ],
    }
  },
  methods: {
    // 切换tabs
    handleClick (tab, event) {
      if (tab.name == 'centerMsgTab') {
        this.centerMsgTabVisbale = true
        this.addrsTabVisbale = false
        this.accountTabVisbale = false
      }
      if (tab.name == 'addrsTab') {
        this.centerMsgTabVisbale = false
        this.addrsTabVisbale = true
        this.accountTabVisbale = false
      }
      if (tab.name == 'accountTab') {
        this.centerMsgTabVisbale = false
        this.addrsTabVisbale = false
        this.accountTabVisbale = true
      }
      this.getLoginUser()
      this.getAddrListData()
      this.getAccountRecordList()
    },

    // 查找账户信息
    getAccountRecordList () {
      let params = {
        page: 1,
        number: 100000,
        userId: sessionStorage.getItem("userId")
      }
      getAccountRecordListRequest(params).then(response => {
        this.accountListData = response.data
      })

      let params2 = {
        userId: sessionStorage.getItem("userId")
      }
      getMyAccountListRequest(params2).then(response => {
        this.accountNum = response.data
      })
    },
    // 查询用户信息
    getLoginUser () {
      let params = {
        userId: sessionStorage.getItem("userId")
      }
      getLoginUserRequest(params).then(response => {
        this.user = response.data
      })
    },

    // 修改用户
    handleSubmit () {
      this.$refs.userForm.validate((vaild, done) => {
        if (vaild) {
          let params = {
            sno: this.user.sno,
            name: this.user.name,
            email: this.user.email,
            phone: this.user.phone,
            id: sessionStorage.getItem("userId")
          }
          updateUserRequest(params).then(response => {
            this.$message({
              message: response.msg,
              type: 'success',
            })
          })
          done()
        }
      })
    },

    // 重置用户
    handleEmpty () {
      this.getLoginUser()
      this.$message({
        message: '重置成功',
        type: 'success',
      })
    },

    // 获取地址信息
    getAddrListData () {
      let params = {
        sno: this.user.sno,
        name: this.user.name,
        email: this.user.email,
        phone: this.user.phone,
        userId: sessionStorage.getItem("userId")
      }
      getAddressListRequest(params).then(response => {
        this.addrListData = response.data
      })
    },

    // 新增地址
    addrHandleRowSave (row, done, loading) {
      let params = {
        userName: row.userName,
        userPhone: row.userPhone,
        addr: row.addr,
        userId: sessionStorage.getItem("userId")
      }
      addOrUpdateAddressByIdRequest(params).then(response => {
        this.$message.success(response.msg)
        done()
        this.getAddrListData()
      })
    },

    // 或修改地址
    addrRowUpdate (row, index, done, loading) {
      let params = {
        id: row.id,
        userName: row.userName,
        userPhone: row.userPhone,
        addr: row.addr,
        userId: sessionStorage.getItem("userId")
      }
      addOrUpdateAddressByIdRequest(params).then(response => {
        this.$message.success(response.msg)
        done()
        this.getAddrListData()
      })
    },

    // 删除地址
    addrRowDel (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          let params = {
            addressId: row.id
          }
          deleteByAddressIdRequest(params).then(response => {
            this.$message.success(response.msg)
            this.getAddrListData()
            done()
          }).finally(() => {
            loading()
          })
        })
    },

    // 充值/提现
    addAccountRecordRowSave (row, done, loading) {
      let params = {
        money: row.money,
        type: this.account.type,
        userId: sessionStorage.getItem("userId")
      }
      addAccountByUserIdRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getAccountRecordList()
        done()
      }).finally(() => {
        loading()
      })
    },
  },
  created () {
    this.getLoginUser()
    this.getAddrListData()
  }
}
</script>

<style>
</style>
