<template>
  <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
    @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
    @row-save="handleRowSave" @row-update="rowUpdate" @row-del="rowDelete" @on-load="getListData" v-model="recommend"
    :before-open="beforeOpen">
    <template slot-scope="" slot="reTimeSearch">
      <el-date-picker v-model="search.reTime" type="date" placeholder="选择推荐日期" value-format="yyyy-MM-dd">
      </el-date-picker>
    </template>

    <template slot-scope="" slot="reTimeForm">
      <el-date-picker v-model="recommend.reTime" type="date" placeholder="选择推荐日期" value-format="yyyy-MM-dd">
      </el-date-picker>
    </template>
  </avue-crud>
</template>

<script>
import { getFlowerLanguageListRequest, addFlowerLanguageRequest, updateFlowerLanguageRequest, deleteFlowerLanguageRequest } from "@/api/flowerLanguage"
export default {
  name: 'FlowerLanguageManageVue',
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
        delBtn: true,
        column: [
          {
            label: '标题1', prop: 'title1',
            rules: [{ required: true, message: "标题1不能为空", trigger: "blur" }]
          },
          {
            label: '标题2', prop: 'title2',
            rules: [{ required: true, message: "标题2不能为空", trigger: "blur" }]
          },
          {
            label: '推荐日期', prop: 'reTime', type: 'date', search: true, searchSpan: 4, span: 12,
            rules: [{ required: true, message: "请选择推荐日期", trigger: "blur" }]
          },
          {
            label: '创建时间', prop: 'createTime', span: 12, addDisplay: false, editDisplay: false
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
      getFlowerLanguageListRequest(params).then(response => {
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
        title1: this.recommend.title1,
        reTime: this.recommend.reTime,
        title2: this.recommend.title2
      }
      addFlowerLanguageRequest(params).then(response => {
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
        title1: this.recommend.title1,
        reTime: this.recommend.reTime,
        title2: this.recommend.title2
      }
      updateFlowerLanguageRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done(row)
      }).finally(() => {
        loading()
      })
    },

    // 删除
    rowDelete (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          deleteFlowerLanguageRequest(row.id).then(response => {
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

