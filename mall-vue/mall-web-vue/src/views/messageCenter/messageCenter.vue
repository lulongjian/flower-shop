<template>
  <div id="Release">

    <!-- 切换tab -->
    <div class="title">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="全部" name="tab1"></el-tab-pane>
        <el-tab-pane label="已读" name="tab2"></el-tab-pane>
        <el-tab-pane label="未读" name="tab3"></el-tab-pane>
      </el-tabs>
    </div>

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

    <el-dialog title="通知详情" v-if="dialogVisible" :visible.sync="dialogVisible" width="50%"
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
import { getUserNoticeListRequest, readUserNoticeRequest } from "@/api/index"

export default {
  name: "messageCenter",
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
    // 切换tabs
    handleClick (tab, event) {
      if (tab.name == 'tab1') {
        this.isRead = null
      }
      if (tab.name == 'tab2') {
        this.isRead = 1
      }
      if (tab.name == 'tab3') {
        this.isRead = 0
      }
      this.getAddrListData()
    },

    // 获取信息
    getAddrListData () {
      let params = {
        isRead: this.isRead,
        userId: sessionStorage.getItem("userId")
      }
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      getUserNoticeListRequest(params).then(response => {
        this.addrListData = response.data
        this.page.total = response.count
      })
    },
    // 查看详情
    getDetails (row) {
      this.dialogVisible = true
      this.message = row
      if (!row.isRead) {
        // 更新已读
        let params = {
          noticeId: row.notice,
          userId: sessionStorage.getItem("userId")
        }
        readUserNoticeRequest(params).then(response => {

        })
      }
    }
  },
  created () {
    this.getAddrListData()
  }
}
</script>

<style>
</style>
