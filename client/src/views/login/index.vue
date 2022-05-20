<template>
  <div>
    <el-container>
      <el-header >
        <div style="text-align: center; font-size: 25px; font-weight: bolder">
          <i class="el-icon-s-home" style="margin-right: 25px"></i>
          车辆管理系统
        </div>
      </el-header>
      <el-main>
        <el-card class="login-module">
          <div slot="header" class="clearfix">
            <span style="text-align: center; font-size: 20px; font-family: 'Microsoft YaHei'">
              <p><i class="el-icon-office-building" style="margin-right: 18px"></i>登陆</p>
            </span>
          </div>
          <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username" prefix-icon="el-icon-lollipop"></el-input>
              </el-form-item>
              <el-form-item label="用户密码" prop="password">
                <el-input v-model="ruleForm.password" placeholder="请输入密码" show-password prefix-icon="el-icon-ice-cream-round"></el-input>
              </el-form-item>
              <el-form-item label="用户类型" prop="type">
                <el-radio-group v-model="ruleForm.type">
                  <el-radio label="client" value="client">客户</el-radio>
                  <el-radio label="staff" value="staff">员工</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
<!--                <el-button @click="test('ruleForm')">test</el-button>-->
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      ruleForm: {
        username: null,
        password: null,
        type: null,
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 0, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const that = this

      this.$refs[formName].validate((valid) => {
        if (valid) {
          let check = false
          let name = null
          let form = {username: that.ruleForm.username, password: that.ruleForm.password}
          const url = that.$url + '/api/' + that.ruleForm.type + '/login'

          axios.post(url, form).then(function (resp) {
              console.log(resp.data, resp.data.data.id)
              if (resp.data.message === '成功') {
                sessionStorage.setItem("token", 'true')
                sessionStorage.setItem("id", resp.data.data.id)
                sessionStorage.setItem("name", resp.data.data.name)
                let type = that.ruleForm.type
                if (type === 'staff') {
                  type = (resp.data.data.workerType === 1) ? 'worker' : 'bizmen'
                  if (type === 'bizmen' && that.ruleForm.username === 'admin') {
                    sessionStorage.setItem('op', resp.data.data.type)
                  }
                }
                else {
                  sessionStorage.setItem('clientId', resp.data.data.clientId)
                }
                sessionStorage.setItem("type", type)
                that.$notify({
                  title: '成功',
                  message: '登陆成功，欢迎 ' + resp.data.data.name + "!",
                  type: 'success'
                });
                that.$router.push('/' + type)
              }
              else {
                that.$notify.error({
                  title: '错误',
                  message: '登陆失败，请检查账号密码'
                });
              }
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.login-module {
  margin-top: 60px;
  position: absolute;
  right: 650px;
  text-align: center;
  width: 30%;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
</style>