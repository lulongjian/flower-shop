<template>
  <div id="Login">
    <h2>时茪集花店，欢迎登陆</h2>
    <div class="Login">
      <form class="LoginBd">
        <div class="user">
          <input type="text" id="user" name="user" v-model="userName" placeholder="请输入账户或邮箱" maxlength="20">
        </div>
        <div class="password">
          <input type="password" id="password" name="password" v-model="passWord" placeholder="请输入登陆密码" maxlength="16">
        </div>
        <div class="clear">
          <div class="left">
            <input id="remember" type="checkbox">
            <label for="remember">
              <span class="remember">记住密码</span>
            </label>
          </div>
          <div class="right">
            <el-link type="primary" @click="wj">忘记密码？</el-link>
          </div>
        </div>
        <div class="bottonbox">
          <input type="button" value="登陆" class="button pointer" @click="updataInfo">
        </div>
        <p>
          <span class="on">还没有账号？</span>
          <span class="reg pointer" @click="goReg">去注册</span>
        </p>
      </form>
    </div>
  </div>

</template>

<script>
import "@/assets/css/login.css"
import "../../assets/css/reg.css"
import JSEncrypt from 'jsencrypt';
import { lgoinRequest, updatePasswordPasswordRequest } from "@/api/index"

export default {
  name: 'Login',
  data () {
    return {
      userName: '',
      passWord: '',
      centerDialogVisible1: false,
      email: '',
      code: '',
      confirm_password: '',
      verify: '获取验证码',
      verifycood: false,
      op: false,
    }
  },
  methods: {
    getRsaCode (str) { // 注册方法
      let pubKey = `-----BEGIN PUBLIC KEY-----
      MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYYuxhoSASdeViU3Fd9IIGrsf8eWBYyy/dLU+gm39YgDB5IoaIJg8gRqhMvQ4+u2VDgm26NRbV5YJqruqXJVbGA4LN76uKHWiLsAq2OnQ70raB1UhhgAn0kAoekSsFTIjf2g5VGtTlpYUblOvVee5XmPDN0lt9daXWP7qwxIz4OwIDAQAB
        -----END PUBLIC KEY-----`;// ES6 模板字符串 引用 rsa 公钥
      let encryptStr = new JSEncrypt();
      encryptStr.setPublicKey(pubKey); // 设置 加密公钥
      let data = encryptStr.encrypt(str.toString());  // 进行加密
      return data;
    },
    wj () {
      this.$router.replace('/forgetPassword')
    },
    goReg () {
      this.$router.replace('/reg')
    },
    updataInfo () {
      let params = {
        userName: this.userName,
        passWord: this.getRsaCode(this.passWord),
      };
      lgoinRequest(params).then(response => {
        if (response.code != 0) {
          this.$message.error(response.msg);
        } else if (response.code == 0) {
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          this.$router.replace('/home')
          this.$store.state.img = true;
          this.$store.state.name = response.data.userName
          sessionStorage.setItem("user", response.data.userName.toString())
          sessionStorage.setItem("userId", response.data.user.id)
          sessionStorage.setItem("userName", response.data.userName)
        }
      }).finally(() => {

      })
    }
  },
  computed: {
    getVal: {
      get () {
        return this.$store.state.userName
      },
      set (newuserName) {
        this.$store.commit('handleVal', newuserName)
      }
    }
  }
}

</script>

<style>
</style>
