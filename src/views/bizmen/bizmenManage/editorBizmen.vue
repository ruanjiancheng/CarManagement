<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="业务员姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="员工 ID" prop="staffId">
        <el-input v-model="ruleForm.staffId"></el-input>
      </el-form-item>
      <el-form-item label="账号" prop="account">
        <el-input v-model="ruleForm.account"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="test">test</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      ruleForm: {
        name: null,
        account: null,
        password: null,
        unitPrice: null,
        type: 1,
        id: 2
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        staffId: [
          { required: true, message: '请输入工号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  created() {
    const that = this
    if (this.$route.query.id === undefined) {
      this.ruleForm.id = 12
    }
    else {
      this.ruleForm.id = this.$route.query.id
    }
    const url = this.$url + '/api/staff/query?id='
    axios.get(url + this.ruleForm.id).then(function (resp) {
      that.ruleForm = resp.data.data
    })
  },
  methods: {
    submitForm(formName) {
      console.log(sessionStorage.getItem('op'))
      if (sessionStorage.getItem('op') == null) {
        this.$message.error('你没有权限')
        return
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          console.log(this.ruleForm)
          const url = this.$url + '/api/staff/update'
          axios.post(url, this.ruleForm).then(function (resp) {
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '编辑成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('编辑失败，请检查数据库');
            }
            that.$router.push("/queryBizmen")
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test() {
      console.log(this.ruleForm)
    }
  }
}
</script>