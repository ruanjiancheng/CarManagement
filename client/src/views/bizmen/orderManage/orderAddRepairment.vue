<template>
  <div>
    <el-card>
      <el-form style="width: 100%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
        <el-form-item label="工单编号" prop="orderID">
          <el-input v-model="ruleForm.orderID" disabled></el-input>
        </el-form-item>
        <el-form-item label="维修项目选择" prop="detailID">
          <el-radio-group v-model="ruleForm.detailID">
            <el-radio-button v-for="repair in detailList" :value="repair.id" :key="repair.id" :label="repair.id">{{repair.name}}</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="维修员选择" prop="workerID">
          <el-radio-group v-model="ruleForm.workerID">
            <el-radio-button v-for="worker in workerList" :value="worker.id" :key="worker.id" :label="worker.id">{{worker.name + ' 工种：' + worker.workerName + ' 价格：' + worker.unitPrice}}</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工时" prop="workTime">
          <el-input v-model.number="ruleForm.workTime"></el-input>
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
      detailList: null,
      workerList: null,
      name: null,
      ruleForm: {
        orderID: null,
        detailID: null,
        workerID: null,
        workTime: null,
      },
      rules: {
        workTime: [
          { required: true, message: '请输入', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        detailID: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
        workerID: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
      }
    };
  },
  created() {
    this.name = this.$route.query.name
    this.ruleForm.orderID = parseInt(this.$route.query.id)
    const that = this
    const url1 = this.$url + '/api/repair/getAllRepairments'
    axios.get(url1).then(function (resp1) {
      console.log(resp1.data.data)
      that.detailList = resp1.data.data
    })
    const url2 = this.$url + '/api/staff/getAllWorkers'
    axios.get(url2).then(function (resp2) {
      console.log(resp2.data.data)
      that.workerList = resp2.data.data
      for (let i = 0; i < that.workerList.length; i++) {
        that.workerList[i].workerName = that.$workerType[that.workerList[i].workerType]
      }
    })
    // console.log(that.detailList, that.workerList)
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          const url = this.$url + '/api/order/addRepairment'
          console.log(this.ruleForm)
          axios.post(url, this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '增加成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('失败，请检查数据库');
            }
            that.$router.push("/orderList")
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