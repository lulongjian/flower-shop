<template>
  <div>
    <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
      @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
      @row-save="handleRowSave" @row-update="rowUpdate" @row-del="rowDel" @on-load="getListData" v-model="notice"
      :before-open="beforeOpen">
    </avue-crud>
  </div>

</template>

<script>
import avueUeditor from 'avue-plugin-ueditor'
import { getProductTypeListRequest, addProduceTypeRequest, updateProductTypeRequest, deleteProductTypeRequest } from "@/api/productType"
export default {
  name: 'ProductTypeManageVue',
  data () {
    var validateNum = (rule, value, callback) => {
      const reg = /^-?[1-9]\d*$/;
      if (value == '' || value == undefined || value == null) {
        callback(new Error('不能为空'));
      } else {
        if (!reg.test(value)) {
          callback(new Error('请输入正确的数量'));
        } else {
          callback();
        }
      }
    }
    return {
      userList: [],
      productList: [],
      notice: {},
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
        column: [
          {
            label: '类别', prop: 'name', search: true,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          },
          {
            label: '排序', prop: 'sort',
            rules: [{ required: true, validator: validateNum, trigger: "blur" }]
          },
          {
            label: '创建时间', prop: 'createTime', addDisplay: false, editDisplay: false
          }
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
      getProductTypeListRequest(params).then(response => {
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
        name: row.name,
        sort: row.sort
      }
      addProduceTypeRequest(params).then(response => {
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
        name: row.name,
        sort: row.sort
      }
      updateProductTypeRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done(row)
      })
    },

    // 删除
    rowDel (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          let params = {
            id: row.id
          }
          deleteProductTypeRequest(params).then(response => {
            this.$message.success(response.msg)
            this.getListData()
            done(row)
          }).finally(() => {
            loading()
          })
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
  }
}
</script>

<style>
.el-dialog__body {
    padding-top: 0px;
}
</style>

