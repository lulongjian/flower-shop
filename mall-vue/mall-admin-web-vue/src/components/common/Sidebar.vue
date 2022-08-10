<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
      text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">
                  {{ threeItem.title }}</el-menu-item>
              </el-submenu>
              <el-menu-item v-else :index="subItem.index" :key="subItem.index">{{ subItem.title }}</el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from '../common/bus';
export default {
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'el-icon-lx-home',
          index: 'Index',
          title: '系统首页'
        },
        {
          icon: 'el-icon-mobile-phone',
          index: 'UserManage',
          title: '会员管理'
        },
        {
          icon: 'el-icon-goods',
          index: 'ProductManage',
          title: '鲜花管理'
        },
        {
          icon: 'el-icon-suitcase',
          index: 'ProductTypeManage',
          title: '类别管理'
        },
        {
          icon: 'el-icon-star-on',
          index: 'RecommendManage',
          title: '每日推荐'
        },
        {
          icon: 'el-icon-s-flag',
          index: 'FlowerLanguageManage',
          title: '每日花语'
        },
        {
          icon: 'el-icon-s-opportunity',
          index: 'FlowerKnowledgeManage',
          title: '养花知识'
        },
        {
          icon: 'el-icon-circle-check',
          index: 'OrderManage',
          title: '订单管理'
        },
        {
          icon: 'el-icon-money',
          index: 'AccountRecordManage',
          title: '交易记录'
        },
        {
          icon: 'el-icon-message',
          index: 'MessageManage',
          title: '留言管理'
        },
        {
          icon: 'el-icon-message-solid',
          index: 'NoticeManage',
          title: '公告管理'
        },
      ]
    };
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '');
    }
  },
  created () {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on('collapse', msg => {
      this.collapse = msg;
      bus.$emit('collapse-content', msg);
    });
  }
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 150px;
}
.sidebar > ul {
    height: 100%;
}
</style>
