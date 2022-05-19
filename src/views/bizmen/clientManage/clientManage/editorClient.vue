<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="客户名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="account">
          <el-input v-model="ruleForm.account"></el-input>
        </el-form-item>
        <el-form-item label="客户 ID" prop="clientId">
          <el-input v-model="ruleForm.clientId"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="ruleForm.contact"></el-input>
        </el-form-item>
        <el-form-item label="折扣率" prop="discountRate">
          <el-input v-model.number="ruleForm.discountRate"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="tel">
          <el-input v-model="ruleForm.tel"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="ruleForm.type">
            <el-option value="个人" label="个人"></el-option>
            <el-option value="公司" label="公司"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button @click="test">test</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "addClient",
  data() {
    return {
      ruleForm: {
        id: 1,
        account: null,
        carCount: 1,
        clientId: null,
        contact: null,
        discountRate: null,
        name: null,
        password: null,
        tel: null,
        type: null
      },
      rules: {
        account: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        carCount: [
          { required: true, message: '请输入工号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        clientId: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        contact: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        discountRate: [
          { required: true, message: '请输入单价', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        tel: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
      }
    };
  },
  created() {
    const url = this.$url + '/api/client/query?id='
    const that = this
    if (this.$route.query.id !== undefined) {
      this.ruleForm.id = this.$route.query.id
    }
    axios.get(url + this.ruleForm.id).then(function (resp) {
      that.ruleForm = resp.data.data
    })
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          const url = this.$url + '/api/client/update'
          axios.post(url, this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '更新成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('插入失败，请检查数据库');
            }
            that.$router.push("/queryClient")
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

<style scoped>

</style>