<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="名字" prop="name">
              <el-input v-model.number="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="单价下限" prop="lowPrice">
              <el-input v-model.number="ruleForm.lowPrice"></el-input>
            </el-form-item>
            <el-form-item label="单价上限" prop="highPrice">
              <el-input v-model.number="ruleForm.highPrice"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card>
          <worker-list :rule-form="ruleForm" :is-active="true"></worker-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import WorkerList from "@/views/bizmen/wokerManage/workerList";
export default {
  name: "queryWorker",
  components: {WorkerList},
  data() {
    return {
      ruleForm: {
        lowPrice: "",
        highPrice: "",
        name: ""
      },
      rules: {
        lowPrice: [
          { type: 'number', message: '必须是数字类型' }
        ],
        highPrice: [
          { type: 'number', message: '必须是数字类型' }
        ],
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>