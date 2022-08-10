<template>
  <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
    @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
    @row-save="handleRowSave" @row-update="rowUpdate" @row-del="rowDelete" @on-load="getListData" v-model="recommend"
    :before-open="beforeOpen">
    <template slot-scope="" slot="reTimeSearch">
      <el-date-picker v-model="search.reTime" type="date" placeholder="选择推荐日期" value-format="yyyy-MM-dd">
      </el-date-picker>
    </template>
    <template slot="imgUrl" slot-scope="scope">
      <img :src="scope.row.imgUrlBase64" width="60" height="60" />
    </template>
    <!-- 自定义表单 -->
    <template slot-scope="" slot="productIdForm">
      <el-select v-model="recommend.productId" filterable placeholder="请选择">
        <el-option v-for="item in productList" :key="item.id" :label="item.title" :value="item.id">
        </el-option>
      </el-select>
    </template>

    <template slot-scope="" slot="reTimeForm">
      <el-date-picker v-model="recommend.reTime" type="date" placeholder="选择推荐日期" value-format="yyyy-MM-dd">
      </el-date-picker>
    </template>
  </avue-crud>
</template>

<script>
import { selectProductListRequest } from "@/api/product"
import { getRecommendListRequest, addRecommendRequest, updateRecommendRequest, deleteRecommendRequest } from "@/api/recommend"
export default {
  name: 'REcommendManageVue',
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
            label: '图片', prop: 'imgUrl', formslot: true, span: 24, addDisplay: false, editDisplay: false
          },
          {
            label: '标题', prop: 'title', search: true, searchSpan: 4, addDisplay: false, editDisplay: false
          },
          {
            label: '标题', prop: 'productId', hide: true, searchSpan: 4, formslot: true,
            rules: [{ required: true, message: "请选择标题", trigger: "blur" }]
          },
          {
            label: '类别', prop: 'type', span: 12, addDisplay: false, editDisplay: false
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
      getRecommendListRequest(params).then(response => {
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
        sort: row.sort,
        reTime: this.recommend.reTime,
        productId: this.recommend.productId
      }
      addRecommendRequest(params).then(response => {
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
        sort: row.sort,
        reTime: this.recommend.reTime,
        productId: this.recommend.productId
      }
      updateRecommendRequest(params).then(response => {
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
          deleteRecommendRequest(row.id).then(response => {
            this.$message.success(response.msg)
            this.getListData()
          }).finally(() => {
            loading()
          })
        })
    },

    // 弹窗之前
    beforeOpen (done, type) {
      selectProductListRequest().then(response => {
        this.productList = response.data
      })
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

