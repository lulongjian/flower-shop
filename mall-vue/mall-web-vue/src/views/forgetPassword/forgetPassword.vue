<template>
  <div id="Reg">
    <h2>时茪集花店，修改密码</h2>
    <div class="Reg">
      <form action="" class="RegBd">
        <div>
          <input type="email" v-model.trim="email" @click="op = true" placeholder="请输入正确的邮箱地址用于验证">
        </div>
        <div id="verify">
          <input class="verify" type="email" v-model.trim="code" placeholder="请输入验证码" maxlength="4">
          <div v-on:click="verifyClick(verify)" :disabled="verifycood">{{verify}}</div>
        </div>
        <div>
          <input type="text" v-model.trim="passWordxxx" name="passwordxxx" placeholder="请输入密码" maxlength="16">
        </div>
        <div>
          <input type="text" v-model.trim="passWord" name="password" placeholder="请输入密码" maxlength="16">
        </div>
        <div>
          <input type="button" value="修改密码" id="button" class="pointer" @click="updatePassword">
        </div>
        <p>
          <span class="reg pointer" @click="goLogin">去登陆</span>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import "../../assets/css/reg.css"
import { getCodeUpdatePasswordRequest, updatePasswordPasswordRequest } from "@/api/index"

export default {
  name: 'forgetPassword',
  data () {
    return {
      name: '',
      sno: '',
      code: '',
      email: '',
      passWord: '',
      passWordxxx: '',
      confirm_password: '',
      verify: '获取验证码',
      verifycood: false,
      verify_ccood: false,
      phone: '',
      qqopenid: null,
      op: false,
      ps: false,
      loginUser: {}
    }
  },
  methods: {

    // rsaCode
    getRsaCode (str) {
      let pubKey = `-----BEGIN PUBLIC KEY-----
      MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYYuxhoSASdeViU3Fd9IIGrsf8eWBYyy/dLU+gm39YgDB5IoaIJg8gRqhMvQ4+u2VDgm26NRbV5YJqruqXJVbGA4LN76uKHWiLsAq2OnQ70raB1UhhgAn0kAoekSsFTIjf2g5VGtTlpYUblOvVee5XmPDN0lt9daXWP7qwxIz4OwIDAQAB
        -----END PUBLIC KEY-----`;// ES6 模板字符串 引用 rsa 公钥
      let encryptStr = new JSEncrypt();
      encryptStr.setPublicKey(pubKey); // 设置 加密公钥
      let data = encryptStr.encrypt(str.toString());  // 进行加密
      return data;
    },

    // 验证码
    verifyClick (verify) {
      const regemail = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
      if (regemail.test((this.email))) {
        this.verify = '60';
        this.verifycood = true
        let kong = '';
        clearInterval(kong);
        kong = setInterval(() => {
          this.verify--
          if (this.verify <= 0) {
            clearInterval(kong);
            this.verifycood = false;
            this.verify = '重新获取';
          }
        }, 1000)

        /**
         * 发送邮箱验证码
         */
        let params = {
          email: this.email
        };
        getCodeUpdatePasswordRequest(params).then(response => {
          if (response.code == 0) {
            this.$message.success(response.msg);
          } else {
            this.$message.error(response.msg);
            clearInterval(kong);
            this.verifycood = false;
            this.verify = '重新获取';
          }
        }).catch(() => {
          clearInterval(kong);
          this.verifycood = false;
          this.verify = '重新获取';
        })
          .finally(() => {

          })
      } else {
        this.$message.error('邮箱格式错误格式不正确！');
        clearInterval(kong);
        this.verifycood = false;
        this.verify = '重新获取';
      }
    },

    // 跳转登录页
    goLogin () {
      this.$router.push('/login')
    },

    // 修改密码
    updatePassword () {
      let params = {
        code: this.code,
        email: this.email,
        passWord: this.getRsaCode(this.passWord)
      }
      updatePasswordPasswordRequest(params).then(response => {
        if (response.code === 0) {
          this.$notify({
            title: '修改密码成功',
            message: '跳转至登录页面',
            type: 'success'
          });
          // 跳转登录
          this.$router.push('/login')
        }
        else {
          this.$message.error("网络异常，请稍后重试")
        }
      }).finally(() => {

      })

    }
  },
  computed: {},
  created () {
  }
}
</script>

<style>
</style>
