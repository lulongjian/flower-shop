<template>
  <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
    @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
    @row-save="handleRowSave" @row-update="rowUpdate" @on-load="getListData">
    <template slot="permission" slot-scope="scope">
      <el-tag :type="scope.row.permission === '1' ?'success':(scope.row.permission === '0'?'danger':'')">
        {{scope.row.permission==='1'?'已授权':'未授权'}}
      </el-tag>
    </template>
    <template slot-scope="{type,size,row}" slot="menu">
      <el-button icon="el-icon-edit" :size="size" :type="type" @click="restPassword(row)">
        重置密码
      </el-button>
      <el-button v-if="row.role === '普通用户'" icon="el-icon-check" :style="buttonColor(row.permission)" :size="size"
        :type="type" @click="permissionDel(row)">
        {{row.permission==='1'?'冻结':'授权'}}
      </el-button>
    </template>
  </avue-crud>
</template>

<script>
import { getUserListRequest, addUserRequest, updateUserRequest, deleteUserRequest, passwordReset } from "@/api/user"
import App from '../../App.vue';
export default {
  components: { App },
  name: 'Test',
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
    return {
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
        addBtn: false,
        delBtn: false,
        editBtnText: '编辑个人信息',
        column: [
          { label: '账户', prop: 'sno', search: true, searchSpan: 5, rules: [{ required: true, message: "请输入账户", trigger: "blur" }] },
          { label: '角色', prop: 'role', search: true, searchSpan: 4, type: 'select', dicData: [{ label: '管理员', value: '管理员' }, { label: '普通用户', value: '普通用户' }], rules: [{ required: true, message: "请选择角色", trigger: "blur" }] },
          { label: '姓名', prop: 'name', search: true, searchSpan: 5, rules: [{ required: true, message: "请输入姓名", trigger: "blur" }] },
          { label: '手机', prop: 'phone', rules: [{ required: true, validator: validatePhone, trigger: "blur" }] },
          { label: '邮箱', prop: 'email', rules: [{ required: true, validator: validateEMail, trigger: "blur" }] },
          { label: '注册时间', prop: 'createTime', addDisplay: false, editDisplay: false },
          { label: '状态', prop: 'permission', search: true, searchSpan: 4, searchValue: '', addDisplay: false, editDisplay: false, slot: true, type: 'select', dicData: [{ label: '全部', value: '' }, { label: '已授权', value: '1' }, { label: '未授权', value: '2' }] }
        ],
        searchShowBtn: false,
      }
    }
  },
  methods: {
    // 获取列表数据
    getListData () {
      let params = this.search
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getUserListRequest(params).then(response => {
        this.tableListData = response.data
        this.page.total = response.count
      }).finally(() => {
        this.loading = false
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
    // 新增
    handleRowSave (row, done, loading) {
      let params = {
        sno: row.sno,
        name: row.name,
        phone: row.phone,
        email: row.email,
        role: row.role
      }
      addUserRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done()
      }).finally(() => {
        loading()
      })
    },
    // 编辑
    rowUpdate (row, index, done, loading) {
      let params = {
        id: row.id,
        sno: row.sno,
        name: row.name,
        phone: row.phone,
        email: row.email,
        role: row.role
      }
      updateUserRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done(row)
      }).finally(() => {
        loading()
      })
    },
    // 授权或冻结
    permissionDel (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          let params = {
            ids: row.id
          }
          deleteUserRequest(params).then(response => {
            this.$message.success(response.msg)
            this.getListData()
            done(row)
          }).finally(() => {
            loading()
          })
        })
    },

    // 重置密码
    restPassword (row, done, loading) {
      this.$confirm('确认要重置该用户密码操作吗？')
        .then(_ => {
          passwordReset(row.id).then(response => {
            this.$message.success(response.msg)
            this.getListData()
            done(row)
          }).finally(() => {
            loading()
          })
        })
    },

    // 按钮颜色
    buttonColor (type) {
      if (type === '1') {
        return 'color: red';
      } else {
        return '';
      }
    }

  },
  computed: {

  },
  created () {
    this.getListData()
  }
}
</script>

<style>
</style>

