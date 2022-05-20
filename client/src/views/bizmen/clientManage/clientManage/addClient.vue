<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :inline="false" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <span class="demonstration">车辆</span>
        <el-form-item label="车牌号" prop="numberPlate">
          <el-input v-model="ruleForm.numberPlate"></el-input>
        </el-form-item>
        <el-form-item label="车架号" prop="vin">
          <el-input v-model="ruleForm.vin"></el-input>
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="ruleForm.color"></el-input>
        </el-form-item>
        <el-form-item label="厂商" prop="model">
          <el-input v-model="ruleForm.model"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="category">
          <el-select v-model="ruleForm.category" placeholder="请选择">
            <el-option value="轿车" label="轿车"></el-option>
            <el-option value="货车" label="货车"></el-option>
          </el-select>
        </el-form-item>
        <el-divider></el-divider>
<!--        <span>用户信息</span>-->
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
        account: null,
        carCount: 1,
        clientId: null,
        contact: null,
        discountRate: null,
        name: null,
        password: null,
        tel: null,
        type: null,
        numberPlate: null,
        vin: null,
        color: null,
        model: null,
        category: null,
      },
      rules: {
        numberPlate: [
          { required: true, message: '请输入车牌号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
        vin: [
          { required: true, message: '请输入车架号', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        color: [
          { required: true, message: '请输入颜色', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        model: [
          { required: true, message: '请输入厂商', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请输入用户账号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        clientId: [
          { required: true, message: '请输入用户 ID', trigger: 'blur' },
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
          { required: true, message: '请输入折扣率', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
          { min: 1, max: 100, message: '请输入 0 - 100', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          let url = this.$url + '/api/car/insert'
          axios.post(url, this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 1000) {
              url = that.$url + '/api/client/insert'
              axios.post(url, this.ruleForm).then(function (resp) {
                console.log(resp)
                if (resp.data.code === 1000) {
                  that.$message({
                    showClose: true,
                    message: '插入成功',
                    type: 'success'
                  });
                }
                else {
                  that.$message.error('插入失败，请检查数据库');
                }
                that.$router.push("/queryCar")
              })
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