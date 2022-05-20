<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
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
        <el-form-item label="客户 ID" prop="clientId">
          <el-input v-model="ruleForm.clientId"></el-input>
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
  name: "addMaterial",
  data() {
    return {
      ruleForm: {
        id: 1,
        numberPlate: null,
        vin: null,
        color: null,
        model: null,
        category: null,
        clientId: null,
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
          { min: 1, max: 20, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        color: [
          { required: true, message: '请输入颜色', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        model: [
          { required: true, message: '请输入厂商', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        clientId: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  created() {
    const that = this
    const url = this.$url + '/api/car/query?id='
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
          const url = this.$url + '/api/car/update'
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
            that.$router.push("/car_list")
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
}
</script>

<style scoped>

</style>