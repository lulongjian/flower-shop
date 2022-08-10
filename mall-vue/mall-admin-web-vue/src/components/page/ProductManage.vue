<template>
  <avue-crud :option="option" :search.sync="search" :page.sync="page" :data="tableListData" :table-loading="loading"
    @search-change="searchChange" @refresh-change="refreshChange" @search-reset="refreshChange"
    @row-save="handleRowSave" @row-update="rowUpdate" @on-load="getListData" v-model="product"
    :before-open="beforeOpen">
    <template slot="sortSearch" slot-scope="scope">
      <el-select v-model="search.sort" filterable placeholder="请选择">
        <el-option v-for="item in productTypeList" :key="item.id" :label="item.name" :value="item.name">
        </el-option>
      </el-select>
    </template>
    <template slot="permission" slot-scope="scope">
      <el-tag :type="scope.row.permission === '1' ?'success':(scope.row.permission === '0'?'danger':'')">
        {{scope.row.permission==='1'?'在售':'未上架'}}
      </el-tag>
    </template>
    <template slot="imgUrl" slot-scope="scope">
      <img :src="scope.row.imgUrlBase64" width="60" height="60" />
    </template>
    <template slot-scope="{type,size,row}" slot="menu">
      <el-button icon="el-icon-check" :size="size" :type="type" :style="buttonColor(row.permission)"
        @click="permissionDel(row)">
        {{row.permission==='1'?'下架':'上架'}}
      </el-button>
    </template>

    <!-- 自定义表单 -->
    <template slot-scope="" slot="imgUrlForm">
      <el-input v-model="product.imgUrl" v-if="false"></el-input>
      <div>
        <el-upload class="avatar-uploader" action="/api/file/upload" :show-file-list="false"
          :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
          <img v-if="imgUrl" :src="imgUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
    </template>
    <!-- 自定义表单 -->
    <template slot-scope="" slot="sortForm">
      <el-select v-model="product.sort" filterable placeholder="请选择">
        <el-option v-for="item in productTypeList" :key="item.id" :label="item.name" :value="item.name">
        </el-option>
      </el-select>
    </template>
  </avue-crud>
</template>

<script>
import { getProductListRequest, addProductRequest, updateProductRequest, upProductRequest, deProductRequest } from "@/api/product"
import { getProductTypeAllListRequest, } from "@/api/productType"
export default {
  name: 'ProductManageVue',
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
      productTypeList: [],
      product: {
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
        delBtn: false,
        column: [
          {
            label: '图片', prop: 'imgUrl', formslot: true, span: 24,
            rules: [{ required: true, message: "请上传图片", trigger: "blur" }]
          },
          {
            label: '类别', prop: 'sort', formslot: true, search: true, searchSpan: 5,
            rules: [{ required: true, message: "请输入类别", trigger: "blur" }]
          },
          {
            label: '标题', prop: 'title', search: true, searchSpan: 4,
            rules: [{ required: true, message: "请输入标题", trigger: "blur" }]
          },
          {
            label: '状态', prop: 'permission', search: true, searchSpan: 5, type: 'select',
            dicData: [{ label: '在售', value: '1' }, { label: '未上架', value: '0' }],
            rules: [{ required: true, message: "请选择状态", trigger: "blur" }]
          },
          {
            label: '原价', prop: 'oldPrice',
            rules: [{ required: true, validator: avalidatePrice, trigger: "blur" }]
          },
          {
            label: '现价', prop: 'price',
            rules: [{ required: true, validator: avalidatePrice, trigger: "blur" }]
          },
          {
            label: '库存', prop: 'number',
            rules: [{ required: true, validator: validateNum, trigger: "blur" }]
          },
          {
            label: '销量', prop: 'sallyNum', addDisplay: false, editDisplay: false
          },
          {
            label: '收藏数', prop: 'collectNum', addDisplay: false, editDisplay: false
          },
          {
            label: '创建时间', prop: 'createTime', addDisplay: false, editDisplay: false
          },
          {
            label: '描述', prop: 'des', hide: true, type: 'textarea', minRows: 13, maxlength: 1000, span: 24,
            rules: [{ required: true, message: "请输入描述", trigger: "blur" }]
          }
        ],
        searchShowBtn: false,
      }
    }
  },
  methods: {
    getProductTypeAllList () {
      let params = {}
      getProductTypeAllListRequest(params).then(response => {
        this.productTypeList = response.data
      }).finally(() => {

      })
    },
    // 获取列表数据
    getListData () {
      let params = this.search
      params.page = this.page.currentPage
      params.number = this.page.pageSize
      params.order = 'time'
      getProductListRequest(params).then(response => {
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
        permission: row.permission,
        number: row.number,
        photitlene: row.title,
        sort: row.sort,
        des: row.des,
        price: row.price,
        oldPrice: row.oldPrice,
        title: row.title,
        imgUrl: this.product.imgUrl
      }
      addProductRequest(params).then(response => {
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
        permission: row.permission,
        number: row.number,
        photitlene: row.title,
        sort: this.product.sort,
        des: row.des,
        price: row.price,
        oldPrice: row.oldPrice,
        title: row.title,
        imgUrl: this.product.imgUrl
      }
      updateProductRequest(params).then(response => {
        this.$message.success(response.msg)
        this.getListData()
        done(row)
      }).finally(() => {
        loading()
      })
    },

    // 上架或下架
    permissionDel (row, done, loading) {
      this.$confirm('确认操作？')
        .then(_ => {
          if (row.permission === '0') {
            upProductRequest(row.id).then(response => {
              this.$message.success(response.msg)
              this.getListData()
              done(row)
            }).finally(() => {
              loading()
            })
          } else {
            deProductRequest(row.id).then(response => {
              this.$message.success(response.msg)
              this.getListData()
              done(row)
            }).finally(() => {
              loading()
            })
          }

        })
    },

    // 按钮颜色
    buttonColor (type) {
      if (type === '1') {
        return 'color: red';
      } else {
        return '';
      }
    },

    // 上传图片成功
    handleAvatarSuccess (res, file) {
      if (res.code == 0) {
        this.product.imgUrl = res.data.imgUrl
        this.imgUrlBase64 = res.data.imgUrlBase64
        this.imgUrl = URL.createObjectURL(file.raw)
      } else {
        this.$message.error("上传失败");
      }
    },

    // 上传图片前
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg';
      const isJPG2 = file.type === 'image/png';
      const isJPG3 = file.type === 'image/jpg';
      const isLt2M = file.size / 1024 / 1024 < 5;

      if (!(isJPG || isJPG2 || isJPG3)) {
        this.$message.error('上传头像图片只能是 jpeg/png/jpg 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5MB!');
      }
      return true;
    },


    // 弹窗之前
    beforeOpen (done, type, row) {
      this.getProductTypeAllList()
      if (type === 'edit') {
        this.imgUrl = this.product.imgUrlBase64
      } else {
        this.imgUrl = null
        this.product = {}
      }
      done()
    },
  },
  computed: {

  },
  created () {
    this.getListData()
    this.getProductTypeAllList()
  }
}
</script>

<style>
</style>

