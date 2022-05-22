<!--
  提交
  Vue.prototype.$classification = ['小修', '中修', '大修']
Vue.prototype.$type = ['普通', '加急']
Vue.prototype.$settlementMode = ['自付', '三包', '进保']
-->
<template>
  <div>
    <el-card>
      <el-form style="width: 60%" :inline="false" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
        <el-form-item label="员工 ID" prop="staffId">
          <el-input v-model="ruleForm.staffId" disabled></el-input>
        </el-form-item>
        <el-form-item label="委托书编号" prop="commissionId">
          <el-input v-model.number="ruleForm.commissionId" disabled></el-input>
        </el-form-item>
        <el-form-item label="客户 ID" prop="clientId">
          <el-select v-model="ruleForm.clientId" placeholder="请选择">
            <el-option v-for="(name, idx) in this.clientName" :value="name.clientId" :label="name.name" :key="idx"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车牌号" prop="numberPlace">
          <el-select v-model="ruleForm.numberPlace" placeholder="请选择">
            <el-option v-for="(name, idx) in this.carList" :value="name.numberPlate" :label="name.numberPlate" :key="idx"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="维修类型" prop="type">
          <el-select v-model="ruleForm.type" placeholder="请选择">
            <el-option v-for="(name, idx) in this.$type" :value="name" :label="name" :key="idx"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作业类型" prop="classification">
          <el-select v-model="ruleForm.classification" placeholder="请选择">
            <el-option v-for="(name, idx) in this.$classification" :value="name" :label="name" :key="idx"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="结算类型" prop="settlementMode">
          <el-select v-model="ruleForm.settlementMode" placeholder="请选择">
            <el-option v-for="(name, idx) in this.$settlementMode" :value="name" :label="name" :key="idx"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="故障描述" prop="description">
          <el-input v-model="ruleForm.description"></el-input>
        </el-form-item>
        <el-form-item label="预计完工时间" prop="ndueDate">
          <el-date-picker
              v-model="ruleForm.ndueDate"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="登记时间" prop="ndate">
          <el-input v-model.number="ruleForm.ndate" disabled></el-input>
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
      clientName: null,
      carList: [],
      ruleForm: {
        clientId: null,
        commissionId: Math.floor(Math.random() * (100000000 - 90000000)) + 90000000,
        ndate: (new Date()),
        data: (new Date()).getDate(),
        description: null,
        dueDate: null,
        ndueDate: null,
        id: null,
        numberPlace: null,
        staffId: parseInt(sessionStorage.getItem('id')),

        settlementMode: null,
        classification: null,
        status: 0,
        type: null,
      },
      rules: {
        settlementMode: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        classification: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        date: [
          { required: true, message: '请输入', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        dueDate: [
          { required: true, message: '请输入', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        clientId: [
          { required: true, message: '请输入', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        commissionId: [
          { required: true, message: '请输入', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        description: [
          { required: true, message: '请输入', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        numberPlace: [
          { required: true, message: '请输入', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.ruleForm.dueDate = (this.ruleForm.ndueDate).getDate()
      this.ruleForm.date = (new Date()).getDate()
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          let url = this.$url + '/api/comm/insert'
          axios.post(url, this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '插入成功',
                type: 'success'
              });
              that.$router.push("/commList")
            }
            else {
              that.$message.error('插入失败，请检查数据库');
            }
            that.$router.push("/commList")
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
    },
  },
  created() {
    const that = this
    const url = this.$url + '/api/client/getAllClients'
    axios.get(url).then(function (resp) {
      that.clientName = resp.data.data
      console.log(that.clientName)
    })
  },
  watch: {
    ruleForm: {
      handler(newRuleForm, oldRuleForm) {
        const that = this
        const url = this.$url + '/api/client/getClientCars?clientID='
        if (newRuleForm.clientId !== null) {
          axios.get(url + newRuleForm.clientId).then(function (resp) {
            console.log(resp.data.data)
            that.carList = resp.data.data
          })
        }
      },
      deep: true,
      immediate: true
    }
  }
}
</script>

<style scoped>

</style>