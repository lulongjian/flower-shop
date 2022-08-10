<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="hover" class="mgb20" style="height:252px;">
          <div class="user-info">
            <img src="../../assets/img/img.jpg" class="user-avator" alt />
            <div class="user-info-cont">
              <div class="user-info-name">{{name}}</div>
              <div>{{role}}</div>
            </div>
          </div>
          <div class="user-info-list">
            欢迎光临，系统当前时间：{{nowtime}}
          </div>
        </el-card>
        <el-card shadow="hover" style="height:252px;">
          <div class="echart" id="mychart" :style="{ float: 'left', width: '100%', height: '250px' }"></div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-1">
                <i class="el-icon-lx-people grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{SysMsgVO.authUserNum}}</div>
                  <div>有效用户</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-2">
                <i class="el-icon-lx-goods grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{SysMsgVO.authProductNum}}</div>
                  <div>在售鲜花</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-3">
                <i class="el-icon-s-order grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{SysMsgVO.userOrderNum}}</div>
                  <div>全部订单</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-2">
                <i class="el-icon-share grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{SysMsgVO.recommendNum}}</div>
                  <div>今日推荐</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-card shadow="hover" style="height:403px;">
          <div class="echart" id="mychart2" :style="{ float: 'left', width: '100%', height: '380px' }"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Schart from 'vue-schart';
import { getSystemData } from "@/api/common"
import * as echarts from 'echarts';

export default {
  name: 'index',
  data () {
    return {
      SysMsgVO: '',
      nowtime: "",
      name: localStorage.getItem('ms_username'),
    };
  },
  components: {
    Schart
  },
  computed: {
    role () {
      return '超级管理员';
    },
  },
  mounted () {
    this.initEcharts();
    this.initEcharts2();
  },
  created () {
    this.getTime();
    this.systemData();
  },
  methods: {
    initEcharts2 () {
      var chartDom = document.getElementById('mychart2');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: this.SysMsgVO.orderDateList,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '数量',
            type: 'bar',
            barWidth: '60%',
            data: this.SysMsgVO.orderNumList,
          }
        ]
      };

      option && myChart.setOption(option);

    },
    initEcharts () {
      var chartDom = document.getElementById('mychart');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '订单概况',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '20',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: this.SysMsgVO.orderNum1, name: '待发货' },
              { value: this.SysMsgVO.orderNum2, name: '已发货' },
              { value: this.SysMsgVO.orderNum3, name: '已完成' },
              { value: this.SysMsgVO.orderNum0, name: '已取消' },
            ]
          }
        ]
      };

      option && myChart.setOption(option);
    },
    /**
     * 获取系统数据
     */
    systemData () {
      Promise.all([
        getSystemData().then(response => {
          this.SysMsgVO = response.data;
          this.SysMsgVO.orderDateList = []
          this.SysMsgVO.orderNumList = []
          response.data.orderList.forEach(element => {
            this.SysMsgVO.orderDateList.push(element.date)
            this.SysMsgVO.orderNumList.push(element.num)
          });

        })
      ]).then(res => {
        this.initEcharts()
        this.initEcharts2()
      })

    },
    getTime () {
      setInterval(() => {
        this.nowtime = new Date().toLocaleString();
      }, 1000)
    },
    changeDate () {
      const now = new Date().getTime();
      this.data.forEach((item, index) => {
        const date = new Date(now - (6 - index) * 86400000);
        item.name = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
      });
    }
  },
};
</script>


<style scoped>
.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 70px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}
</style>
