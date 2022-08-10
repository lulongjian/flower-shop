<template>
  <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
    @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
    @row-save="handleRowSave" @row-update="rowUpdate" @row-del="rowDelete" @on-load="getListData" v-model="recommend"
    :before-open="beforeOpen">
  </avue-crud>
</template>

<script>
import { getMessageListRequest, deleteMessageRequest } from "@/api/message"
export default {
  name: 'MessageManageVue',
  data () {
    var avalidatePrice = (rule, value, callback) => {
      const reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
      if (value == '' || value == undefined || value == null) {
        callback(new Error('不能为空'));
      } else {
        if ((!reg.test(value)) && value != '') {
          callback(new Error('请输入正确的价格'));
        } else {
          callback();
        }
      }
    }
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
      productList: [],
      recommend: {
        imgUrl: null
      },
      imgUrl: null,
      imgUrlBase64: null,
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
        editBtn: false,
        delBtn: true,
        column: [
          {
            label: '商品标题', prop: 'productTitle'
          },
          {
            label: '会员', prop: 'name'
          },
          {
            label: '留言内容', prop: 'text', search: true, searchSpan: 4
          },
          {
            label: '留言时间', prop: 'createTime', span: 12, addDisplay: false, editDisplay: false
          }
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
      getMessageListRequest(params).then(response => {
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

    // 删除
    rowDelete (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          deleteMessageRequest(row.id).then(response => {
            this.$message.success(response.msg)
            this.getListData()
          }).finally(() => {
            loading()
          })
        })
    },

    // 弹窗之前
    beforeOpen (done, type) {
      done()
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
</style>

