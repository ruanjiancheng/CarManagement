<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="名字" prop="name">
              <el-input v-model.number="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="车数下限" prop="lowCarCount">
              <el-input v-model.number="ruleForm.lowCarCount"></el-input>
            </el-form-item>
            <el-form-item label="车数上限" prop="highCarCount">
              <el-input v-model.number="ruleForm.highCarCount"></el-input>
            </el-form-item>
            <el-form-item label="折扣下限" prop="lowDiscountRate">
              <el-input v-model.number="ruleForm.lowDiscountRate"></el-input>
            </el-form-item>
            <el-form-item label="折扣上限" prop="highDiscountRate">
              <el-input v-model.number="ruleForm.highDiscountRate"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card>
          <client-list :rule-form="ruleForm" :is-active="true"></client-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import ClientList from "@/views/bizmen/clientManage/clientManage/clientList";
export default {
  name: "queryClient",
  components: {ClientList},
  data() {
    return {
      ruleForm: {
        lowCarCount: "",
        highCarCount: "",
        lowDiscountRate: "",
        highDiscountRate: "",
        name: ""
      },
      rules: {
        lowCarCount: [
          { type: 'number', message: '必须是数字类型' }
        ],
        highCarCount: [
          { type: 'number', message: '必须是数字类型' }
        ],
        lowDiscountRate: [
          { type: 'number', message: '必须是数字类型' }
        ],
        highDiscountRate: [
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