<template>
  <div>
    <el-card>
      <el-form style="width: 100%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="维修项目名称" prop="name">
          <el-input v-model="name" disabled></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="materialID">
          <el-radio-group v-model="ruleForm.materialID">
            <el-radio-button v-for="material in materialList" :value="material.id" :key="material.id" :label="material.id">{{material.name + ' 价格：' + material.price + '\n'}}</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input v-model.number="ruleForm.count"></el-input>
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
      materialList: null,
      name: null,
      ruleForm: {
        materialID: null,
        repairmentID: null,
        count: null,
      },
      rules: {
        count: [
          { required: true, message: '请输入折扣率', trigger: 'change' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        materialID: [
          { required: true, message: '请选择型号', trigger: 'change' }
        ],
      }
    };
  },
  created() {
    this.name = this.$route.query.name
    this.ruleForm.repairmentID = parseInt(this.$route.query.id)
    const that = this
    const url = this.$url + '/api/material/getAllMaterials'
    axios.get(url).then(function (resp) {
      that.materialList = resp.data.data
    })
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          const url = this.$url + '/api/repair/insertMaterial'
          axios.post(url, this.ruleForm).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '删除成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('删除失败，请检查数据库');
            }
            that.$router.push("/repairmentList")
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