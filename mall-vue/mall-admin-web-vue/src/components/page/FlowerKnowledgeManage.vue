<template>
  <div>
    <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
      @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
      @row-save="handleRowSave" @row-update="rowUpdate" @row-del="rowDel" @on-load="getListData" v-model="knowledge"
      :before-open="beforeOpen">
      <!-- 自定义表单 -->
      <template slot-scope="" slot="contentForm">
        <avue-ueditor v-model="knowledge.content" :options="ueditorOptions">

        </avue-ueditor>
      </template>
    </avue-crud>
  </div>

</template>

<script>
import avueUeditor from 'avue-plugin-ueditor'
import { getFlowerKnowledgeListRequest, addFlowerKnowledgeRequestRequest, updateFlowerKnowledgeRequestRequest, delFlowerKnowledgeRequestRequest } from "@/api/flowerKnowledge"
export default {
  name: 'FlowerKnowledgeManageVue',
  data () {
    return {
      userList: [],
      productList: [],
      knowledge: {},
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
            label: '标题', prop: 'title', search: true,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          },
          {
            label: '内容', prop: 'content', hide: true, formslot: true, span: 24,
            rules: [{ required: true, message: "不能为空", trigger: "blur" }]
          },
          {
            label: '创建时间', prop: 'createTime', addDisplay: false, editDisplay: false
          }
        ],
        searchShowBtn: false,
      },
      ueditorOptions: {
        customConfig: {},//wangEditor编辑的配置
      }
    }
  },
  methods: {

    // 获取列表数据
    getListData () {
      let params = this.search
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getFlowerKnowledgeListRequest(params).then(response => {
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
        title: row.title,
        content: row.content
      }
      addFlowerKnowledgeRequestRequest(params).then(response => {
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
        title: row.title,
        content: row.content
      }
      updateFlowerKnowledgeRequestRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done(row)
      })
    },

    // 删除
    rowDel (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          delFlowerKnowledgeRequestRequest(row.id).then(response => {
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

