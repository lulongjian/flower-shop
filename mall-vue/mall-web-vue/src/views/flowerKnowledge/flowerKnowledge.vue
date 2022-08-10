<template>
  <div id="Release">

    <!-- 信息 -->
    <div class="ReleaseBd" style="width: 90%">
      <avue-crud :option="addrOption" :page.sync="page" :data="addrListData" :table-loading="addrListloading"
        @on-load="getAddrListData">
        <template slot-scope="{type,size,row}" slot="menu">
          <el-button icon="el-icon-info" :size="size" :type="type" @click="getDetails(row)">
            详情
          </el-button>
        </template>
      </avue-crud>
    </div>

    <el-dialog title="养花小知识" v-if="dialogVisible" :visible.sync="dialogVisible" width="50%"
      :modal-append-to-body="false">
      <h3 style="text-align: center;">{{message.title}}</h3>
      <el-divider></el-divider>
      <p v-html="message.content"></p>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import '@/assets/css/release.css'
import { getFlowerKnowledgeRequest } from "@/api/index"

export default {
  name: "flowerKnowledge",
  data () {
    return {
      addrListloading: false,
      addrListData: [],
      page: {
        currentPage: 1,
        pageSize: 10,
        layout: "total,prev, next",
        background: false,
      },
      isRead: null,
      addrOption: {
        card: true,
        align: 'left',
        menuAlign: 'center',
        index: true,
        addBtn: false,
        delBtn: false,
        editBtn: false,
        column: [
          {
            label: '标题', prop: 'title', span: 18
          },
          {
            label: '发布时间', prop: 'createTime', span: 5
          }
        ],
        searchShowBtn: false,
      },
      activeName: 'tab1',
      dialogVisible: false,
      message: {}
    }
  },
  methods: {

    // 获取信息
    getAddrListData () {
      let params = {
        isRead: this.isRead,
        userId: sessionStorage.getItem("userId")
      }
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getFlowerKnowledgeRequest(params).then(response => {
        this.addrListData = response.data
        this.page.total = response.count
      })
    },
    // 查看详情
    getDetails (row) {
      this.dialogVisible = true
      this.message = row
    }
  },
  created () {
    this.getAddrListData()
  },
  watch: {
    $route () {
      this.getAddrListData()
    }
  },
}
</script>

<style>
</style>
